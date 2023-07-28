package com.practice;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class NonSelectQueryTest {
    public static void main(String[] args) throws SQLException {
        int result=0;
        Connection connection=null;
       try{
            // register driver
//            Driver driver =null;
//            DriverManager.registerDriver(driver);
           Driver driver = new com.mysql.jdbc.Driver();
           DriverManager.registerDriver(driver);


           // create connection
            connection = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/online_shopping", "root", "root");

            // create statement
            Statement statement = connection.createStatement();
            String query = "UPDATE buykart " +
                    "SET description='order management is in admin module'" +
                    "WHERE emp_name='ATHAR';";

            // execute non selected query
            result = statement.executeUpdate(query);
        }
        catch (Exception e)
        {

        }
        finally {
            if(result>0){
                System.out.println("data updated successfully to the Table");
            }else {
                System.err.println("Something Went Wrong");
            }
                connection.close();

        }
    }
}
