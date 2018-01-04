package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import watmoetiketen.Ingredient;

@Component
public class ExcelWriter {

    private static final String FILE_NAME = "src/main/resources/gegevens.xlsx";

    public void kaas() {
    	
    	File file = new File(FILE_NAME);
    	 FileInputStream excelFile = null;
		try {
			excelFile = new FileInputStream(file);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

    	 XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook(excelFile);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

        XSSFSheet sheet = workbook.getSheet("ingredienten");
         
        int rowCount = 0;
        
        List<Ingredient> ingredienten = getLijst();
        
        for (Ingredient ingredient2 : ingredienten) {
        	
        	// per 1 overschrijft row en begint op 0 ipv 1
        	
            Row row = sheet.createRow(++rowCount);
            writeIngredient(ingredient2, row);
        }
     
        try (FileOutputStream outputStream = new FileOutputStream(FILE_NAME)) {
            workbook.write(outputStream);
        } catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
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
    
    private List<Ingredient> getLijst() {
        List<Ingredient> ingredienten = new ArrayList<>();
        
    	Ingredient ingredient = new Ingredient();
    	ingredient.setEenheid("mg");
    	ingredient.setGerechtId(1);
    	ingredient.setHoeveelheid(6);
    	ingredient.setNaam("getTestIngredient");
    	
    	inspect(Ingredient.class);
    	
    	ingredienten.add(ingredient);
    	return ingredienten;
    }
    
    <T> void inspect(Class<T> klazz) {
        Field[] fields = klazz.getDeclaredFields();
        System.out.printf("%d fields:%n", fields.length);
        for (Field field : fields) {
            System.out.printf("%s %s %s%n",
                Modifier.toString(field.getModifiers()),
                field.getType().getSimpleName(),
                field.getName()
            );
        }
        
    }
}