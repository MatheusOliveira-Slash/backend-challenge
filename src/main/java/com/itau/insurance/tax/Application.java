package com.itau.insurance.tax;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class Application {

    public static void main(String[] args){
        TimeZone.setDefault(TimeZone.getTimeZone("GMT-3"));
        SpringApplication.run(Application.class, args);
    }

}
