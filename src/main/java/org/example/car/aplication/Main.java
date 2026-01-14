package org.example.car.aplication;

import org.example.car.aplication.async.AsyncTaskManager;
import org.example.car.aplication.config.AppConfiguration;
import org.example.car.aplication.database.DataSource;
import org.example.car.aplication.gui.window.MainWindow;

import javax.swing.*;
import java.io.IOException;

public class Main {

    private static final String APP_CONFIG_FILE_PATH = "app.properties";
    private static final int ASYNC_TASKS_THREAD_COUNT = 2;

    private static AppConfiguration appConfiguration;
    private static DataSource dataSource;

    private static AsyncTaskManager asyncTaskManager;

    static void main() {
        try {
            initialize();
            SwingUtilities.invokeLater(MainWindow::new);
        } catch (Exception exception) {
            System.err.println("Failed to run program: " + exception.getMessage());
        }
    }

    private static void initialize() throws IOException {
        asyncTaskManager = new AsyncTaskManager(ASYNC_TASKS_THREAD_COUNT);

        appConfiguration = new AppConfiguration(APP_CONFIG_FILE_PATH);
        appConfiguration.load();

        dataSource = new DataSource(appConfiguration);
    }

    public static AppConfiguration getAppConfiguration() {
        return appConfiguration;
    }

    public static DataSource getDataSource() {
        return dataSource;
    }

    public static AsyncTaskManager getAsyncTaskManager() {
        return asyncTaskManager;
    }
}
