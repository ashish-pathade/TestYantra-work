package com.practice;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.sql.*;
import java.time.Duration;

public class CreateProjectForDB {
    public static void main(String[] args) throws InterruptedException, SQLException {
        String projectname="BuyKart2000";
//        WebDriver driver=new ChromeDriver();
//        driver.manage().window().maximize();
//        driver.get("http://rmgtestingserver:8084/");
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
//        driver.findElement(By.id("usernmae")).sendKeys("rmgyantra");
//        driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999", Keys.ENTER);
//        driver.findElement(By.linkText("Projects")).click();
//       //String mainId= driver.getWindowHandle();
//        driver.findElement(By.xpath("//span[text()='Create Project']")).click();
//        driver.findElement(By.xpath("//input[@name='projectName']")).sendKeys(projectname);
//        driver.findElement(By.xpath("//input[@name='createdBy']")).sendKeys("Darshil");
//        WebElement projectStatusDd = driver.findElement(By.xpath("//option[text()='Select Value']/.."));
//        Select select=new Select(projectStatusDd);
//        select.selectByVisibleText("On Gogin");
//        driver.findElement(By.xpath("//input[@value='Add Project']")).click();
//        //Thread.sleep(5000);
//        driver.close();
       //verifyingDataInDB(projectname);
        verifyingDataInDBCQ(projectname);

    }
    public static void verifyingDataInDB(String projectname) throws SQLException {
        String projectFromDB = null;
        Connection connection = null;
        ResultSet result = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://rmgtestingserver:3333/projects", "root@%", "root");
            Statement statement = connection.createStatement();
            String query = "select * from project where project_name IS 'BuyKart21';";
            query = "select * from project;";
            result = statement.executeQuery(query);
            //  projectFromDB= result.getString(4);
            boolean flag = false;
            while (result.next()) {
                String actual = result.getString(4);
                if (projectname.equalsIgnoreCase(actual)) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                System.out.println("project is created successfully in the database");
            } else {
                System.out.println("project is not created  in the database");
            }
        } catch (Exception e) {

        } finally {
            connection.close();
        }
    }
    public static void verifyingDataInDBCQ(String projectname) throws SQLException {

        Connection connection = null;
        ResultSet result = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://rmgtestingserver:3333/projects", "root@%", "root");
            Statement statement = connection.createStatement();
            String query = "select * from project where project_name='BuyKart2000';";

            result = statement.executeQuery(query);
            //***** NOT WORKING BECAUSE OF WHILE LOOP****//
//            if(result.getString(4).equalsIgnoreCase(projectname)){
//                System.out.println("project");
//            }else {
//                System.out.println("no project");
//            }

            boolean flag = false;
            while (result.next()) {
                String actual = result.getString(4);
                if (projectname.equalsIgnoreCase(actual)) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                System.out.println("project is created successfully in the database");
            } else {
                System.out.println("project is not created  in the database");
            }
        } catch (Exception e) {

        } finally {
            connection.close();
        }
    }

    }

