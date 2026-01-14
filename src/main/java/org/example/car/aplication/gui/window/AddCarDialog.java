package org.example.car.aplication.gui.window;

import org.example.car.aplication.Car;
import org.example.car.aplication.CarBrand;
import org.example.car.aplication.dao.CarDAO;
import org.example.car.aplication.Main;


import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.time.Year;
import java.util.UUID;

public class AddCarDialog extends JDialog {

    private final MainWindow parent;

    private Color selectedCarColor = Color.WHITE;

    public AddCarDialog(MainWindow parent) {
        super(parent, true);

        this.parent = parent;
        initializeDialog();
    }

    private void initializeDialog() {
        setTitle("Cars: Add a new car");
        setResizable(false);
        setLayout(null);

        Dimension preferredSize = new Dimension(600, 500);
        setPreferredSize(preferredSize);

        pack();
        setLocationRelativeTo(parent);

        initializeContent();
        setVisible(true);
    }

    private void initializeContent() {
        // Car unique id

        JLabel uniqueIdLabel = new JLabel("Уникальный идентификатор машины:");
        uniqueIdLabel.setBounds(10, 10, 564, 20);

        add(uniqueIdLabel);

        JTextField uniqueIdInputField = new JTextField();
        uniqueIdInputField.setBounds(10, 40, 564, 25);
        uniqueIdInputField.setText(UUID.randomUUID().toString());
        uniqueIdInputField.setEditable(false);

        add(uniqueIdInputField);

        // Car brand

        JLabel brandLabel = new JLabel("Марка машины");
        brandLabel.setBounds(10, 75, 564, 20);

        add(brandLabel);

        JComboBox<CarBrand> brandComboBox = new JComboBox<>(CarBrand.values());
        brandComboBox.setBounds(10, 105, 564, 25);

        add(brandComboBox);

        // Car model

        JLabel modelLabel = new JLabel("Модель машины:");
        modelLabel.setBounds(10, 140, 564, 20);

        add(modelLabel);

        JTextField modelInputField = new JTextField();
        modelInputField.setBounds(10, 170, 564, 25);

        add(modelInputField);

        // Car release year

        JLabel releaseYearLabel = new JLabel("Год выпуска машины:");
        releaseYearLabel.setBounds(10, 205, 564, 20);

        add(releaseYearLabel);

        SpinnerModel releaseYearModel = new SpinnerNumberModel(
                Year.now().getValue(),
                Car.MIN_RELEASE_YEAR,
                Year.now().getValue(),
                1
        );

        JSpinner releaseYearSpinner = new JSpinner(releaseYearModel);
        releaseYearSpinner.setBounds(10, 235, 564, 25);

        add(releaseYearSpinner);

        // Car price

        JLabel carPriceLabel = new JLabel("Цена машины:");
        carPriceLabel.setBounds(10, 270, 564, 20);

        add(carPriceLabel);

        JTextField carPriceInputField = new JTextField();
        carPriceInputField.setBounds(10, 300, 564, 25);

        add(carPriceInputField);

        // Car color

        JLabel carColorLabel = new JLabel("Цвет машины:");
        carColorLabel.setBounds(10, 335, 564, 20);

        add(carColorLabel);

        JButton chooseCarColor = new JButton("Выбрать цвет машины");
        chooseCarColor.setBounds(10, 365, 564, 25);

        chooseCarColor.addActionListener(actionEvent -> {
            selectedCarColor = JColorChooser.showDialog(
                    this,
                    "Car: Choose color",
                    selectedCarColor,
                    false
            );
        });

        add(chooseCarColor);

        // Add car button

        JButton addCarButton = new JButton("Добавить машину");
        addCarButton.setBounds(10, 400, 564, 25);

        addCarButton.addActionListener(actionEvent -> {
            try {
                UUID uniqueId = UUID.fromString(uniqueIdInputField.getText());
                CarBrand brand = (CarBrand) brandComboBox.getSelectedItem();
                String model = modelInputField.getText();
                int releaseYear = (Integer) releaseYearSpinner.getValue();
                BigDecimal price = new BigDecimal(carPriceInputField.getText());
                Color color = selectedCarColor;

                Car car = Car.builder()
                        .withId(1)
                        .withUniqueId(uniqueId)
                        .withBrand(brand)
                        .withModel(model)
                        .withReleaseYear(releaseYear)
                        .withPrice(price)
                        .withColor(color)
                        .build();

                CarDAO.addCar(car);
                dispose();

                Main.getAsyncTaskManager().doAsync(parent::refreshData);
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(
                        this,
                        exception.getMessage(),
                        "Failed to add a new car",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        add(addCarButton);
    }
}