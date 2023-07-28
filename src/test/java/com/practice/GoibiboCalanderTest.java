package com.practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class GoibiboCalanderTest {
    public static void main(String[] args) {
        String month="November 2023";
        int date=25;
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.goibibo.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath("//span[@class='logSprite icClose']")).click();
        driver.findElement(By.xpath("//span[text()='Departure']")).click();
//        driver.findElement(By.xpath
//                ("//div[text()='"+month+"']/ancestor::" +
//                        "div[@class='DayPicker-Month']/descendant::p[text()='"+date+"']")).click();
//        driver.findElement(By.xpath("//span[text()='Done']")).click();


        // if we want to go to different month and year
        String actualDateClick="//div[text()='"+month+"']/ancestor::div[@class='DayPicker-Month']/descendant::p[text()='"+date+"']";
        String nextMonthXpath="//span[@aria-label='Next Month']";
        while (true){
            try{
                driver.findElement(By.xpath(actualDateClick)).click();
                break;
            }catch(Exception e) {
                driver.findElement(By.xpath(nextMonthXpath)).click();
            }
        }
        driver.findElement(By.xpath("//span[text()='Done']")).click();

    }
}
