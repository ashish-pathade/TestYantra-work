package com.practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class RedBusCalaendarTest {
    public static void main(String[] args) {
        int year = 2024;
        String month = "Nov";
        int date = 10;
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.redbus.in/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

//		driver.findElement(By.xpath("//label[text()='From']/preceding-sibling::input")).sendKeys("Hyderabad", Keys.TAB, "Bangalore");
        driver.findElement(By.xpath("//text[text()='Date']")).click();
        for (; ; ) {
            try {
                driver.findElement(By.xpath("//div[text()='"+month+"'and'"+year+"']/ancestor::div/descendant::span[text()='" + date + "']")).click();
                break;
            } catch (Exception e) {
                driver.findElement(By.xpath("//div[@class='DayNavigator__CalendarHeader-qj8jdz-1 fxvMrr']//div[3]")).click();
            }

        }
    }

}
