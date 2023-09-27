
package org.NNS.library.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

    private static final String USERNAME = "";

    private static final String PASSWORD = "";
    public static Connection getConnectedtoDB(){

        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", USERNAME, PASSWORD);
            System.out.println("Connection established successfully!");
            System.out.println("-----------------------------------------");

        }catch (SQLException exception){
            exception.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }


}