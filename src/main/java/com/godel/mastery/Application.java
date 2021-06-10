package com.godel.mastery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

/**
 * Main class for running application.
 */
@SpringBootApplication
@EnableJms
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
