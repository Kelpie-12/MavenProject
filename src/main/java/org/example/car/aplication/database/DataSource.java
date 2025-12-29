package org.example.car.aplication.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {

    private static final String HOSTNAME = "85.193.91.74";
    private static final int PORT = 61512;

    private static final String SERVICE_NAME = "XEPDB1";

    private static final String USERNAME = "student6";
    private static final String PASSWORD = "";

    private static final String URL = "jdbc:oracle:thin:@" + HOSTNAME + ":" + PORT + "/" + SERVICE_NAME;

    public static Connection createConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
