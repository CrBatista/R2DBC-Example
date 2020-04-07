package com.crbatista;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;

import javax.annotation.PostConstruct;

@Configuration
@ActiveProfiles("test")
public class FlywayConfiguration {

    @Value("${spring.database.username}")
    private String username;

    @Value("${spring.database.password}")
    private String password;

    @Value("${spring.database.location}")
    private String location;

    @Value("${spring.database.url}")
    private String url;

    @Value("${spring.database.encoding}")
    private String encoding;

    @Value("${spring.database.table}")
    private String table;

    @Value("${spring.database.placeholderReplacement}")
    private boolean placeholderReplacement;

    @PostConstruct
    public void updateDatabase() {
        new Flyway(
                new FluentConfiguration()
                        .encoding(encoding)
                        .table(table)
                        .locations(location)
                        .dataSource(
                                "jdbc:h2:" + url,
                                username,
                                password)
                        .placeholderReplacement(placeholderReplacement)
        ).migrate();
    }
}
