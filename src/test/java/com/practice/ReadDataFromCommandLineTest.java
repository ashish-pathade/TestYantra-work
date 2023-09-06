package com.practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class ReadDataFromCommandLineTest {
    @Test
    public void readDataFromCmdLine(){
      String BROWSER = System.getProperty("browser");
        String url = System.getProperty("url");
        String username = System.getProperty("uernme");
        String password = System.getProperty("password");
        WebDriver cd = new ChromeDriver();
        cd.manage().window().maximize();
        cd.get(url);
        cd.findElement(By.name("email")).sendKeys(username);
        cd.findElement(By.name("password")).sendKeys(password);
        cd.findElement(By.xpath("//button[text()='Login']")).click();
    }
}
