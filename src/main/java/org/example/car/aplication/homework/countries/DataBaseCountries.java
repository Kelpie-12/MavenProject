package org.example.car.aplication.homework.countries;


import org.example.car.aplication.database.Credentials;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseCountries {
    private final String url;
    private final Credentials credentials;

    public DataBaseCountries() {
        String hostName =  "localhost";
        int port =  6152;
        String database =  "postgres";
        this.url = "jdbc:postgresql://" + hostName + ":" + port + "/" + database;
        this.credentials = new Credentials("postgres","12345");
    }

    public Connection createConnection() throws SQLException {
        return DriverManager.getConnection(url, credentials.userName(), credentials.password());
    }

    public List<Countre> printAllCountries() throws SQLException{
        List<Countre> countres = new ArrayList<>();
        String sql = "SELECT * FROM public.countries; ";

        try (Connection connection = createConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)){
            while (resultSet.next()) {
                Countre countre=new Countre();
                countre.setCapital(resultSet.getString(1));
                countre.setPopulation(resultSet.getLong(2));
                countre.setName(resultSet.getString(3));
                countres.add(countre);
            }
        }
        return  countres;
    }

    public void main(){
        try {
            List<Countre> countres= countres=printAllCountries();

            for (int i = 0; i < countres.size(); i++) {
                System.out.println("Страна -> "+countres.get(i).getName()+"; Население -> "+countres.get(i).getPopulation()+"; Столица -> "+countres.get(i).getCapital());
            }

        } catch (SQLException e) {
            System.out.println(e);
        }

    }

}
