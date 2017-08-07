package de.ovolynets.discussiontree;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.ComponentScan;

@ComponentScan("de.ovolynets.discussiontree")
@SpringBootApplication
public class MainApp {
    public static void main(final String... args) {
        SpringApplication.run(MainApp.class, args);
    }
}
