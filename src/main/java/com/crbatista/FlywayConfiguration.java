package com.crbatista;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.FluentConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.HashMap;

@Configuration
public class FlywayConfiguration {

    @Value("${admin.flyway.adminSchema}")
    private String adminSchema;

    @Value("${admin.flyway.encoding}")
    private String encoding;

    @Value("${admin.flyway.table}")
    private String table;

    @Value("${admin.flyway.location}")
    private String location;

    @Value("${admin.flyway.username}")
    private String username;

    @Value("${admin.flyway.password}")
    private String password;

    @Value("${admin.flyway.outOfOrder}")
    private boolean outOfOrder;

    @Value("${admin.flyway.cleanDisabled}")
    private boolean cleanDisabled;

    @Value("${admin.flyway.baselineOnMigrate}")
    private boolean baselineOnMigrate;

    @Value("${admin.flyway.placeholderReplacement}")
    private boolean placeholderReplacement;

    @Value("${admin.database.schema}")
    private String applicationSchema;

    @Value("${admin.database.defaultSchema}")
    private String defaultSchema;

    @Value("${admin.database.host}")
    private String host;

    @Value("${admin.database.port}")
    private int port;

    @Value("${admin.database.databaseName}")
    private String databaseName;

    @Value("${admin.database.username}")
    private String applicationUsername;

    @PostConstruct
    public void updateDatabase() {
        final Flyway flyway = new Flyway(
                new FluentConfiguration()
                        .encoding(encoding)
                        .table(table)
                        .locations(location)
                        .schemas(adminSchema)
                        .defaultSchema(defaultSchema)
                        .dataSource(
                                String.format("jdbc:postgresql://%s:%d/%s", host, port, databaseName),
                                username,
                                password)
                        .cleanDisabled(cleanDisabled)
                        .baselineOnMigrate(baselineOnMigrate)
                        .outOfOrder(outOfOrder)
                        .placeholderReplacement(placeholderReplacement)
                        .placeholders(new HashMap<String, String>() {{
                            put("APPLICATION-SCHEMA", applicationSchema);
                            put("APPLICATION-USER", applicationUsername);
                        }})
        );

        flyway.repair();
        flyway.migrate();
    }
}
