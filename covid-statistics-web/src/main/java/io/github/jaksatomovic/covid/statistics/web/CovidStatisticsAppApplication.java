package io.github.jaksatomovic.covid.statistics.web;

import io.github.jaksatomovic.covid.statistics.core.configuration.properties.DatabaseProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication (scanBasePackages = "io.github.jaksatomovic")
@EnableJpaRepositories (basePackages = "io.github.jaksatomovic.covid.statistics.core.persistence.repository")
@EnableConfigurationProperties ({DatabaseProperties.class, LiquibaseProperties.class})
public class CovidStatisticsAppApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(CovidStatisticsAppApplication.class, args);
    }

}
