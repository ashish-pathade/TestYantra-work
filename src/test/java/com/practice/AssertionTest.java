package com.practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class AssertionTest {
    @Test
    public void loginTestActiHardAssert(){
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demo.actitime.com/login.do");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.name("pwd")).sendKeys("manager");
        driver.findElement(By.id("loginButton")).click();
        WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("logout")));
        String actualTitle= driver.getTitle();
        Assert.assertEquals(actualTitle,"actiTIME - Enter Time-Track","title is not matching ");
        driver.findElement(By.className("logout")).click();
        driver.close();
    }
    @Test
    public void loginTestActiSoftAssert(){
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demo.actitime.com/login.do");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.name("pwd")).sendKeys("manager");
        driver.findElement(By.id("loginButton")).click();
        WebDriverWait wait =new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("logout")));
        String actualTitle= driver.getTitle();
        SoftAssert sa = new SoftAssert();
        sa.assertEquals(actualTitle,"actiTIME - Enter Time-Track","title is not matching ");
        driver.findElement(By.className("logout")).click();
        driver.close();
        sa.assertAll();
    }


}
