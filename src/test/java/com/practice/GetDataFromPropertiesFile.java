package com.practice;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class GetDataFromPropertiesFile {
    public static void main(String[] args) throws IOException, InterruptedException {
        FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\commonData.properties");
        Properties pObj = new Properties();
        pObj.load(fis);
       String url= pObj.getProperty("url");
       String adminUrl=pObj.getProperty("AdminUrl");
        String AdminUsername= pObj.getProperty("AdminUsername");
        String AdiminPassword= pObj.getProperty("AdiminPassword");
        String CustomerUsername= pObj.getProperty("CustomerUsername");
        String CustomerPassword= pObj.getProperty("CustomerPassword");
        String browserName= pObj.getProperty("browsername");
        WebDriver driver=launchBrowser(browserName);
        driver.manage().window().maximize();


        // **************login as a user***********


        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.findElement(By.linkText("Login")).click();
        driver.findElement(By.xpath
                ("//input[contains(@id,'exampleInputEmail')]")).sendKeys(CustomerUsername);
        driver.findElement(By.xpath
                ("//input[contains(@id,'exampleInputPassword')]")).sendKeys
                                                    (CustomerPassword, Keys.ENTER);

        // ******************login as a admin***********************

        driver.get(adminUrl);
        driver.findElement(By.id("inputEmail")).sendKeys(AdminUsername);
        driver.findElement(By.id("inputPassword")).sendKeys(AdiminPassword, Keys.ENTER);

        //*******************creating the category as a admin******************

        driver.findElement(By.partialLinkText("Create Category")).click();
        driver.findElement(By.xpath("//input[@name='category']")).sendKeys("EQUIPMENTS");
        driver.findElement(By.name("description")).sendKeys
                ("This category is belongs to all the equip.");
        driver.findElement(By.name("submit")).click();
        Thread.sleep(5000);
        driver.close();

    }

    public static WebDriver launchBrowser(String browserName){
        WebDriver driver = null;
        if (browserName.equalsIgnoreCase("chrome")){
            driver=new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {
            driver=new EdgeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            driver=new FirefoxDriver();
        }else {
            driver=new ChromeDriver();
        }
        return driver;
    }
}
