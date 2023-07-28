package com.ecommerce.GenricUtilities;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseUtility {
    Connection con=null;

    /**
     * @ this method is used to create the connection to the database
     * @author ashish
     * @throws SQLException
     */
    public void createConnectionToDB() throws SQLException {
        Driver driverRef = new Driver();
        DriverManager.registerDriver(driverRef);
        con=DriverManager.getConnection
                (IPathConstants.databaseURL,IPathConstants.databaseUSERNAME,IPathConstants.databasePASSWORD);
    }


    /**
     * @ this method is used to execute the query
     * @author Ashish
     * @param query
     * @param columnNum
     * @param expectedData
     * @throws SQLException
     */
    public void executeQuery(String query , int columnNum , String expectedData) throws SQLException {
       ResultSet result= con.createStatement().executeQuery(query);
       boolean flag=false;
        while (result.next()){
          String data=result.getString(columnNum);
          if (data.equalsIgnoreCase(expectedData)){
              flag=true;
              break;
          }
        }
        if (flag){
            System.out.println(expectedData+" is present in the database");
        }else {
            System.out.println(expectedData+" is not present in the database");
        }
    }


    /**
     * @ this method is used to update the data in database
     * @author Ashish
     * @param query
     * @throws SQLException
     */
    public void updateQuery(String query) throws SQLException {
        int result= con.createStatement().executeUpdate(query);

        if (result>0){
            System.out.println("Data updated successfully");
        }else {
            System.out.println("Data is not updated ");
        }

    }


    /**
     * @ this method is used to close the database connection
     * @author Ashish
     * @throws SQLException
     */
    public void closeConnectOfDB() throws SQLException {
        con.close();
    }
}
