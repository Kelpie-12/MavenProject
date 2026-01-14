package org.example.car.aplication.database;

import org.example.car.aplication.config.AppConfiguration;


import java.util.Objects;

public record Credentials(
        String userName,
        String password
) {
    public Credentials {
        Objects.requireNonNull(userName, "User name must not be null");

        if (userName.isBlank())
            throw new IllegalArgumentException("User name must not be empty");
    }

    public Credentials(AppConfiguration appConfiguration) {
        String userName = appConfiguration.getString("database.username");
        String password = appConfiguration.getString("database.password");

        this(userName, password);
    }
}