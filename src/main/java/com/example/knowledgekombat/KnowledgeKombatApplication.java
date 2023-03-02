package com.example.knowledgekombat;

import com.example.knowledgekombat.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class KnowledgeKombatApplication {

    public static void main(String[] args) {
        SpringApplication.run(KnowledgeKombatApplication.class, args);
    }

}
