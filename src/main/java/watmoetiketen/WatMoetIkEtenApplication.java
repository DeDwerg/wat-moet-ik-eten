package watmoetiketen;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@SpringBootApplication
public class WatMoetIkEtenApplication {

    public static void main(String[] args) {
        SpringApplication.run(WatMoetIkEtenApplication.class, args);
    }

    // ook een inloggen iets bouwen (voor database alvast)

    @Bean
    @Profile("dev")
    public JdbcTemplate getJdbcTemplateDev() {
//        logger.error("dev");
        return new JdbcTemplate(this.getDataSource());
    }

    @Bean
    @Profile("live")
    public JdbcTemplate getJdbcTemplateLive() {
//        logger.error("live");
        return new JdbcTemplate(this.getDataSource());
    }
    
    @Bean
    public DataSource getDataSource() {
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver"); // je                                                                                                                         // nodig
            dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe"); // properties                                                                                                         // database
            dataSource.setUsername("mark"); // elke gebruiker heeft maar 1 database
            dataSource.setPassword("mark");
            return dataSource;
    }

}
