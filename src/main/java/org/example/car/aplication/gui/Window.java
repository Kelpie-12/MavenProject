package org.example.car.aplication.gui;

import org.example.car.aplication.CarFactory;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    private CarTableModel carTableModel;

    public Window() {
        super();
        initializeFrame();

        CarFactory carFactory = new CarFactory();
        carTableModel.addCars(carFactory.create());
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
        carTableModel = new CarTableModel();
        JTable table = new JTable(carTableModel);

        JScrollPane carTableScrollPane = new JScrollPane(table);
        carTableScrollPane.setBounds(10, 10, 764, 540);

        add(carTableScrollPane);
    }
}
