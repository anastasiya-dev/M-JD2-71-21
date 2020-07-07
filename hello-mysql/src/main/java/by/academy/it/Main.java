package by.academy.it;

import java.sql.*;
import java.util.Properties;

public class Main {

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Properties properties = new Properties();
            properties.put("user", "root");
            properties.put("password", "root");
            properties.put("useSSL", "false");
            properties.put("serverTimezone", "UTC");

            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/test",
                    properties
            );
            System.out.println("Is connected: " + !connection.isClosed());

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from test.table1");
            System.out.println(resultSet);
            while (resultSet.next()) {
                System.out.print(resultSet.getInt(1) + " ");
                System.out.print(resultSet.getString(2) + " ");
                System.out.print(resultSet.getString(3) + " ");
                System.out.print(resultSet.getDate(4) + " ");
                System.out.print(resultSet.getString(5) + " ");
                //System.out.println(resultSet.getString(6)); //Gender
             }


        } catch (ClassNotFoundException|SQLException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Finished");
        }
    }

}
