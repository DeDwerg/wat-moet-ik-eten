package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import watmoetiketen.Gebruiker;
import watmoetiketen.Gerecht;
import watmoetiketen.Ingredient;

@Slf4j
@Component
public class ExcelWriter {

    private static final String FILE_NAME = "src/main/resources/gegevens.xlsx";
    XSSFSheet sheet;
    XSSFWorkbook workbook;

    public void maakNieuwIngredient(List<Ingredient> ingredienten) {

        initieerExcelFile();
        this.sheet = this.workbook.getSheet("ingredienten");
        for (Ingredient ingredient2 : ingredienten) {
            Row legeRij = zoekLegeRij();
            writeIngredient(ingredient2, legeRij);
        }
        sluitExcel();
    }

    public void maakNieuwGerecht(Gerecht gerecht) {
        initieerExcelFile();
        this.sheet = this.workbook.getSheet("gerechten");
        Row legeRij = zoekLegeRij();
        writeGerecht(gerecht, legeRij);
        sluitExcel();
    }

    public HttpStatus maakNieuweGebruiker(Gebruiker gebruiker) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        initieerExcelFile();
        this.sheet = this.workbook.getSheet("personen");
        Row legeRij = zoekLegeRij();
        boolean gebruikerBestaat = checkGebruikerBestaat(gebruiker.getNaam());
        if (gebruikerBestaat) {
            httpStatus = HttpStatus.CONFLICT;
        } else {
            maakNieuweGebruiker(gebruiker, legeRij);
            httpStatus = HttpStatus.CREATED;
        }
        sluitExcel();
        return httpStatus;
    }

    public HttpStatus loginGebruiker(Gebruiker gebruiker) {
        initieerExcelFile();
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        this.sheet = this.workbook.getSheet("personen");
        boolean geldigeLogin = checkLoginGegevensKloppen(gebruiker);
        if (geldigeLogin) {
            httpStatus = HttpStatus.OK;
        }
        sluitExcel();
        return httpStatus;
    }

    public void verwijderGerecht(Gerecht gerecht) {
        initieerExcelFile();
        verwijderGegevens("gerechten", gerecht.getNaam());
    }

    public void verwijderIngredient(Ingredient ingredient) {
        initieerExcelFile();
        verwijderGegevens("ingredienten", ingredient.getNaam());
    }

    public List<Ingredient> geeftIngredientenBijGerecht(Gerecht gerecht) {
        initieerExcelFile();
        this.sheet = this.workbook.getSheet("ingredienten");
        int rowCounter = 0;
        List<Row> gevondenRijen = new ArrayList<>();
        Row gevondenRij = null;
        while (!(gevondenRij.getCell(0) == null || gevondenRij.getCell(0).getCellType() == Cell.CELL_TYPE_BLANK)) {
            gevondenRij = this.sheet.getRow(rowCounter);
            if (gevondenRij.getCell(5/* nummervangerechtid */).getNumericCellValue() == gerecht.getId()) {
                gevondenRijen.add(gevondenRij);
            }
        }
        List<Ingredient> gevondenIngredienten = new ArrayList<>();
        for (Row row : gevondenRijen) {
            gevondenIngredienten.add(getIngredient(row));
        }

        return gevondenIngredienten;
    }

    public Optional<Gerecht> zoekRandomGerecht(Gebruiker gebruiker) throws NullPointerException {
        // zoeken op basis van gebruiker id
        initieerExcelFile();
        this.sheet = this.workbook.getSheet("gerechten");
        int rowCounter = 1;
        List<Row> gevondenRijen = new ArrayList<>();
        Row row = sheet.getRow(rowCounter);
        while (true) {
            row = this.sheet.getRow(rowCounter);
            if (row != null) {
                if (!(row.getCell(0) == null || row.getCell(0).getCellType() == Cell.CELL_TYPE_BLANK)) {
                    if (row.getCell(5/* nummervangebruikerid */).getNumericCellValue() == gebruiker.getId()) {
                        gevondenRijen.add(row);
                    }
                    rowCounter++;
                } 
            } else {
                break;
            }
        }
        int randomGetal = (int) (Math.random() * gevondenRijen.size() + 1);
        Row resultaat = gevondenRijen.get(randomGetal-1);
        return Optional.of(maakGerecht(resultaat));
    }

    private void verwijderGegevens(String sheetNaam, String targetNaam) {
        initieerExcelFile();
        this.sheet = this.workbook.getSheet(sheetNaam);
        boolean verwijderd = false;
        int rowCounter = 0;
        Row row = null;
        while (!(verwijderd)) {
            row = this.sheet.getRow(rowCounter);
            if (!(row.getCell(0) == null || row.getCell(0).getCellType() == Cell.CELL_TYPE_BLANK)) { // predicate
                                                                                                     // maken
                if (targetNaam.equals(row.getCell(1).getStringCellValue())) {
                    this.sheet.removeRow(row);
                    verwijderd = true;
                }
            }
        }
    }

    private boolean checkLoginGegevensKloppen(Gebruiker gebruiker) {
        boolean juisteGegevens = false;
        int rowCounter = 0;
        Row row = null;
        while (!(juisteGegevens)) {
            row = this.sheet.getRow(rowCounter);
            if (row != null) {
                if (!(row.getCell(0) == null || row.getCell(0).getCellType() == Cell.CELL_TYPE_BLANK)) {
                    if (row.getCell(1).getStringCellValue().equals(gebruiker.getNaam())) {
                        if (row.getCell(2).getStringCellValue().equals(gebruiker.getWachtwoord())) {
                            juisteGegevens = true;
                        } else {
                            break;
                        }
                    } else {
                        rowCounter++;
                    }
                } else {
                    break;
                }
            } else {
                break;
            }
        }
        return juisteGegevens;

    }

    private boolean checkGebruikerBestaat(String gebruikersNaam) {
        boolean gebruikerGevonden = false;
        int rowCounter = 0;
        Row row = null;
        while (!(gebruikerGevonden)) {
            row = this.sheet.getRow(rowCounter);
            if (row != null) {
                Cell cell = row.getCell(1);
                if (!(cell == null || cell.getCellType() == Cell.CELL_TYPE_BLANK)) {
                    if (cell.getStringCellValue().equals(gebruikersNaam)) {
                        gebruikerGevonden = true;
                    }
                }
            } else {
                break;
            }
            rowCounter++;
        }
        return gebruikerGevonden;
    }

    private Row zoekLegeRij() {
        boolean legeRijGevonden = false;
        int rowCounter = 0;
        Row row = null;
        while (!(legeRijGevonden)) {
            row = this.sheet.getRow(rowCounter);
            if (row != null) {
                Cell cell = row.getCell(0);
                if (cell == null || cell.getCellType() == Cell.CELL_TYPE_BLANK) {
                    row = sheet.getRow(rowCounter);
                    legeRijGevonden = true;
                } else {
                    rowCounter = rowCounter + 1;
                }
            } else {
                break;
            }
        }
        Row nieuweRij = sheet.createRow(rowCounter);
        return nieuweRij;
    }

    private void sluitExcel() {
        try (FileOutputStream outputStream = new FileOutputStream(FILE_NAME)) {
            workbook.write(outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Ingredient getIngredient(Row row) {
        // excelvolgorde: naam hoeveelheid eenheid gerechtid
        return new Ingredient(row.getCell(0).getStringCellValue(), (int) row.getCell(1).getNumericCellValue(),
                row.getCell(2).getStringCellValue(), (int) row.getCell(3).getNumericCellValue());
    }

    private Gerecht maakGerecht(Row row) {
        // excelvolgorde: id naam vis vlees aantalpersonen gebruikerId
        return new Gerecht(row.getCell(1).getStringCellValue(), row.getCell(2).getBooleanCellValue(),
                row.getCell(3).getBooleanCellValue(), (int) row.getCell(4).getNumericCellValue(),
                (int) row.getCell(5).getNumericCellValue());
    }

    private void writeIngredient(Ingredient ingredient, Row row) {
        Cell cell = row.createCell(0);
        cell.setCellValue(ingredient.getNaam());

        cell = row.createCell(1);
        cell.setCellValue(ingredient.getHoeveelheid());

        cell = row.createCell(2);
        cell.setCellValue(ingredient.getEenheid());

        cell = row.createCell(3);
        cell.setCellValue(ingredient.getGerechtId());
    }

    private void writeGerecht(Gerecht gerecht, Row row) {
        // volgorde id naam vis vlees aantalpersonen gebruikerId
        Cell cell = row.createCell(0);
        cell.setCellValue(gerecht.getId());

        cell = row.createCell(1);
        cell.setCellValue(gerecht.getNaam());

        cell = row.createCell(2);
        cell.setCellValue(gerecht.isVis());

        cell = row.createCell(3);
        cell.setCellValue(gerecht.isVlees());

        cell = row.createCell(4);
        cell.setCellValue(gerecht.getAantalPersonen());

        cell = row.createCell(5);
        cell.setCellValue(gerecht.getGebruikerId());
    }

    private void maakNieuweGebruiker(Gebruiker gebruiker, Row row) {
        Cell cell = row.createCell(0);
        cell.setCellValue(gebruiker.getId());

        cell = row.createCell(1);
        cell.setCellValue(gebruiker.getNaam());

        cell = row.createCell(2);
        cell.setCellValue(gebruiker.getWachtwoord());
    }

    private void initieerExcelFile() {
        File file = new File(FILE_NAME);
        FileInputStream excelFile = null;
        try {
            excelFile = new FileInputStream(file);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }

        try {
            this.workbook = new XSSFWorkbook(excelFile);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}