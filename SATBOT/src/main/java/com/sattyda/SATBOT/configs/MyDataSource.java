package com.sattyda.SATBOT.configs;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class MyDataSource {

    @Bean
    public DataSource dataSource(){

        DataSourceBuilder db = DataSourceBuilder.create();
        db.driverClassName("com.mysql.cj.jdbc.Driver");
        db.url("jdbc:mysql://localhost:3306/satbot");
        db.username("root");
        db.password("Sattyda1!");
        return db.build();
    }
}