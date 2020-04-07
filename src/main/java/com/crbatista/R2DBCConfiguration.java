package com.crbatista;

import io.r2dbc.pool.ConnectionPool;
import io.r2dbc.pool.ConnectionPoolConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@Configuration
@EnableR2dbcRepositories
public class R2DBCConfiguration extends AbstractR2dbcConfiguration {

    @Value("${admin.database.host}")
    private String host;

    @Value("${admin.database.port}")
    private int port;

    @Value("${admin.database.databaseName}")
    private String databaseName;

    @Value("${admin.database.username}")
    private String username;

    @Value("${admin.database.password}")
    private String password;

    @Value("${admin.database.poolMaxSize:10}")
    private int poolMaxSize;

    @Value("${admin.database.poolInitialSize:10}")
    private int poolInitialSize;

    /**
     * R2DBC PostgreSQL Connection created wrapped by a R2DBC Connection Pool.
     *
     */
    @Bean
    public ConnectionFactory connectionFactory() {
        return new ConnectionPool(ConnectionPoolConfiguration.builder(
                new PostgresqlConnectionFactory(
                        PostgresqlConnectionConfiguration.builder()
                                .host(host)
                                .port(port)
                                .database(databaseName)
                                .username(username)
                                .password(password)
                                .build()))
                .maxSize(poolMaxSize)
                .initialSize(poolInitialSize)
                .build()
        );
    }
}
