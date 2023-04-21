package ch.ilv.scrapbook.dataaccess;

import org.springframework.boot.jdbc.DataSourceBuilder;

import javax.sql.DataSource;

//@Configuration
public class DataSourceConfig {

    //@Bean
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url("jdbc:postgresql://localhost:5432/scrapbook");
        dataSourceBuilder.username("postgres");
        dataSourceBuilder.password("postgres");
        return dataSourceBuilder.build();
    }
}
