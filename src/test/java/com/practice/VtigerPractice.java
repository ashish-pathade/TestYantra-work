package com.practice;

import com.ecommerce.GenricUtilities.JavaUtility;
import com.ecommerce.GenricUtilities.WebDriverUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class VtigerPractice {
    public static void main(String[] args) throws InterruptedException {


        WebDriverUtility wbUtility = new WebDriverUtility();
        JavaUtility javaUtility = new JavaUtility();

        WebDriver driver = new ChromeDriver();
        wbUtility.maximizeWindow(driver);
        driver.get("http://rmgtestingserver:8888/index.php");
        wbUtility.implicitWait(driver,10);
        driver.findElement(By.name("user_name")).sendKeys("admin");
        driver.findElement(By.name("user_password")).sendKeys("admin");
        driver.findElement(By.id("submitButton")).click();
        driver.findElement(By.xpath("//a[.='Calendar']")).click();
        WebElement addCal= driver.findElement(By.className("calAddButton"));
        wbUtility.mouseHover(driver,addCal);
        driver.findElement(By.xpath("//a[.='To Do']")).click();
        driver.findElement(By.name("task_subject")).sendKeys("abcd");
        driver.findElement(By.name("task_description")).sendKeys("trial");
        WebElement statusDD= driver.findElement(By.id("taskstatus"));
        wbUtility.selectValueOfDD(statusDD,"Pending Input");
        WebElement priorityDD = driver.findElement(By.xpath("//b[.='Status']/../../following-sibling::tr//select[@id='taskpriority']"));
        wbUtility.selectValueOfDD(priorityDD,2);
        // //select[@id='taskpriority']/../following-sibling::td
        driver.findElement(By.xpath("//input[@name='task_assigntype'][2]")).click();
        WebElement assignToDD = driver.findElement(By.name("task_assigned_group_id"));
        wbUtility.selectValueOfDD("Support Group",assignToDD);
        driver.findElement(By.xpath("//b[.='Time & Date']/../../following-sibling::tr//select//option[.='12']")).click();
        WebElement minDD=driver.findElement(By.xpath("//b[.='Time & Date']/../../following-sibling::tr//select[@name='startmin']"));
        wbUtility.selectValueOfDD("30",minDD);
        driver.findElement(By.xpath("//b[.='Time & Date']/../../following-sibling::tr//option[@value='pm']")).click();
        driver.findElement(By.id("jscal_trigger_task_date_start")).click();

        driver.findElement(By.xpath("(//td[.='July, 2023'])[4]/../../following-sibling::tbody//td[.='20']")).click();
        Thread.sleep(3000);
        driver.close();


    }
}
