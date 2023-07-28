package com.practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class MakeMyTripTest {
    public static void main(String[] args) throws InterruptedException {
        String monthAndYear="January 2024";
        int date = 29;
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://www.makemytrip.com/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.navigate().refresh();
        //        Actions actions=new Actions(driver);
//        actions.moveByOffset(40,40).click().perform();

        //************ for dynamic drop down************
        driver.findElement(By.xpath("//span[text()='From']")).click();
        driver.findElement(By.className("react-autosuggest__input--open")).sendKeys("bangalore");
        List<WebElement> fromCity=driver.findElements(By.xpath("//ul[@class='react-autosuggest__suggestions-list']/li"));
        for (WebElement city:fromCity){
            if (city.getText().equalsIgnoreCase("bangalore")){
                city.click();
            }
        }
//        driver.findElement(By.xpath("//span[text()='Departure']")).click();
//        String actual="//div[text()='"+monthAndYear+"']/ancestor::div[@class='DayPicker-Month']/descendant::p[text()='"+date+"']";
//        while(true){
//            try{
//                driver.findElement(By.xpath(actual)).click();
//                break;
//            }catch(Exception e){
//                driver.findElement(By.xpath("//span[@aria-label='Next Month']")).click();
//            }
//        }
        Thread.sleep(5000);
       driver.close();
    }
}
