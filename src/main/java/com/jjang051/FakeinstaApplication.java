package com.jjang051;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FakeinstaApplication {

    public static void main(String[] args) {
        SpringApplication.run(FakeinstaApplication.class, args);
    }

}
