package com.sicredi.voting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Classe principal da aplicação de votação.
 * @author Yasmim Roeder
 */
@SpringBootApplication
@EnableSwagger2
@EnableScheduling
public class VotingApplication {

    public static void main(String[] args) {
        SpringApplication.run(VotingApplication.class, args);
    }

}
