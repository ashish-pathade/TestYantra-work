package com.practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;

public class InterViewQuestion {
    public static void main(String[] args) {
        ChromeOptions options=new ChromeOptions();
        options.addArguments("--lang=en");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.aveda.de/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath("//button[.='Akzeptieren']")).click();
       WebElement ele=driver.findElement(By.xpath
               ("//div[@class='site-header__menu-desktop']//a[text()='MAKEUP']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(ele).perform();
       List<WebElement> all= driver.findElements(By.xpath("//div[@class='site-header__menu-desktop']//a[.='MAKEUP']/..//a"));
       for (WebElement el:all){
           System.out.println(el.getText());
       }
       driver.close();

    }
}
