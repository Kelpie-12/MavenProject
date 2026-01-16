package org.example.car.aplication.database;

import org.example.car.aplication.config.AppConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {

    private final String url;
    private final Credentials credentials;

    public DataSource(AppConfiguration appConfiguration) {
        String hostName = appConfiguration.getString("database.hostname", "localhost");
        int port = appConfiguration.getInt("database.port", 5432);
        String database = appConfiguration.getString("database.name", "postgres");

        this.url = "jdbc:postgresql://" + hostName + ":" + port + "/" + database;
        this.credentials = new Credentials(appConfiguration);
    }

    public Connection createConnection() throws SQLException {
        return DriverManager.getConnection(url, credentials.userName(), credentials.password());
    }
}

