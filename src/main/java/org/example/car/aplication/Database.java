package org.example.car.aplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.function.Consumer;

public class Database {

    private final String url;

    public Database(String url) {
        this.url = url;
    }

    public void getConnection(Consumer<Connection> consumer) {
        try (Connection connection = DriverManager.getConnection(url)) {
            consumer.accept(connection);
        } catch (SQLException exception) {
            throw new IllegalStateException("Database error: " + url, exception);
        }
    }
}