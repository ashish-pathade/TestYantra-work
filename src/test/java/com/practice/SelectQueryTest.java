package com.practice;


import com.mysql.cj.jdbc.Driver;

import java.sql.*;

public class SelectQueryTest {
    public static void main(String[] args) {

        Connection connection= null;
        try {
            //register the driver
            Driver driver= new Driver();
            DriverManager.registerDriver(driver);

            //create connection
             connection=DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/online_shopping","root","root");

             //Create statement
            Statement statement= connection.createStatement();
            String query= "select * from buykart;";


            //excecute the query
             ResultSet result = statement.executeQuery(query);
             while(result.next()){
                 System.out.println
                         (result.getString(1)+" || "+result.getString(2)+" || "+result.getString
                                 (3)+" || "+result.getString(4)+" || "+result.getString(5));
             }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
