package org.example.car.aplication.gui.window;

import org.example.car.aplication.Car;
import org.example.car.aplication.dao.CarDAO;
import org.example.car.aplication.gui.CarTableModel;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class MainWindow extends JFrame {

    private CarTableModel carTableModel;

    public MainWindow() {
        super();
        initializeFrame();
        Runnable asyncTask = () -> {
            try {
                List<Car> carsFromDatabase = CarDAO.getAllCars();

                carTableModel.clear();
                carTableModel.addCars(carsFromDatabase);
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        };

        new Thread(asyncTask).start();
        // CarFactory carFactory = new CarFactory();
        // carTableModel.addCars(carFactory.create());
    }

    private void initializeFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cars");
        setResizable(false);
        setLayout(null);

        Dimension preferredSize = new Dimension(800, 600);
        setPreferredSize(preferredSize);

        pack();
        setLocationRelativeTo(null);

        initializeContent();
        setVisible(true);
    }

    private void initializeContent() {
        JButton addCarButton = new JButton("Добавить машину");
        addCarButton.setBounds(10, 10, 150, 25);

        addCarButton.addActionListener(actionEvent -> {
            new AddCarDialog(this);
        });

        add(addCarButton);

        carTableModel = new CarTableModel();
        JTable table = new JTable(carTableModel);

        JScrollPane carTableScrollPane = new JScrollPane(table);
        carTableScrollPane.setBounds(10, 45, 764, 505);

        add(carTableScrollPane);
    }
}
