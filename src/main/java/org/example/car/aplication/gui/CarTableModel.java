package org.example.car.aplication.gui;

import org.example.car.aplication.Car;

import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

public class CarTableModel extends AbstractTableModel {

    private static final DecimalFormat PRICE_FORMAT =
            new DecimalFormat("#,##0.00", new DecimalFormatSymbols(Locale.ENGLISH));

    private static final String[] columns = {
            "№",
            "Идентификатор",
            "Марка",
            "Модель",
            "Год выпуска",
            "Цена",
            "Цвет"
    };

    private final List<Car> cars = new ArrayList<>();

    public void addCars(Collection<Car> cars) {
        this.cars.addAll(cars);
        fireTableDataChanged();
    }

    public void clear() {
        this.cars.clear();
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return cars.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columns[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Car car = cars.get(rowIndex);

        return switch (columnIndex) {
            case 0 -> car.id();
            case 1 -> car.uniqueId();
            case 2 -> car.brand();
            case 3 -> car.model();
            case 4 -> car.releaseYear();
            case 5 -> formatPrice(car.price());
            case 6 -> formatColor(car.color());
            default -> null;
        };
    }

    private String formatPrice(BigDecimal price) {
        BigDecimal roundedPrice = price.setScale(2, RoundingMode.UP);
        return "%s ₽".formatted(PRICE_FORMAT.format(roundedPrice));
    }

    private String formatColor(Color color) {
        return "%d, %d, %d".formatted(
                color.getRed(),
                color.getGreen(),
                color.getBlue()
        );
    }
}
