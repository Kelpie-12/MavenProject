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
        int port = appConfiguration.getInt("database.port", 1512);
        String serviceName = appConfiguration.getString("database.service.name", "XEPDB1");

        this.url = "jdbc:oracle:thin:@" + hostName + ":" + port + "/" + serviceName;
        this.credentials = new Credentials(appConfiguration);
    }

    public Connection createConnection() throws SQLException {
        return DriverManager.getConnection(url, credentials.userName(), credentials.password());
    }
}
