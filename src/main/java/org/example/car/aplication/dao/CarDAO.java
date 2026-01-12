package org.example.car.aplication.dao;
import org.example.car.aplication.Car;
import org.example.car.aplication.CarBrand;
import org.example.car.aplication.Main;
import org.example.car.aplication.database.DataSource;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class CarDAO {

    public static List<Car> getAllCars() throws SQLException {
        List<Car> cars = new ArrayList<>();
        String sql = "SELECT unique_id, brand, model, release_year, price, color FROM cars";

        try (Connection connection = Main.getDataSource().createConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {

                Car car = Car.builder()
                        .withUniqueId(new UUID(resultSet.getInt("unique_id"),0L))
                        .withBrand(CarBrand.valueOf(resultSet.getString("brand")))
                        .withModel(resultSet.getString("model"))
                        .withReleaseYear(resultSet.getInt("release_year"))
                        .withPrice(resultSet.getBigDecimal("price"))
                        .withColor(Color.decode(resultSet.getString("color")))
                        .build();

                cars.add(car);
            }
        }

        return Collections.unmodifiableList(cars);
    }

    public static void addNewCar(Car newCar) throws Exception{
       // String sql = "insert into Cars(unique_id,brand,model,release_year,price,color) values("+newCar.uniqueId()+","+newCar.brand()+","+
         //       newCar.model()+","+newCar.releaseYear()+","+newCar.price()+","+newCar.color()+")";
        String sql="insert into Cars(unique_id,brand,model,release_year,price,color) values(?,?,?,?,?,?)";
        try (Connection connection=Main.getDataSource().createConnection();
             PreparedStatement  statement = connection.prepareStatement(sql);
            ) {

            statement.setString(1,newCar.uniqueId().toString());
            statement.setString(2,newCar.brand().toString());
            statement.setString(3,newCar.model());
            statement.setString(4,newCar.releaseYear()+"");
            statement.setString(5,newCar.price()+"");
            statement.setString(6,newCar.color()+"");


            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Новая машина была добавлена!");
            }

        }
    }
}