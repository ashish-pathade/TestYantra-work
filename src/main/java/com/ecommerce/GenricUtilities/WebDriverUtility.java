package com.ecommerce.GenricUtilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.WheelInput;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

public class WebDriverUtility {

    public void maximizeWindow(WebDriver driver){
        driver.manage().window().maximize();
    }
    public void implicitWait(WebDriver driver,int sec){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(sec));
    }

    public void waitTillVisibilityOfElement(WebDriver driver, WebElement element){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void selectValueOfDD(WebElement element,int index){
        Select select=new Select(element);
        select.selectByIndex(index);
    }
    public void selectValueOfDD(WebElement element,String value){
        Select select=new Select(element);
        select.selectByValue(value);
    }
    public void selectValueOfDD(String visibleText,WebElement element){
        Select select=new Select(element);
        select.selectByVisibleText(visibleText);
    }

    public void rightClick(WebDriver driver){
        Actions actions = new Actions(driver);
        actions.contextClick().perform();
    }
    public void dubleClick(WebDriver driver){
        Actions actions = new Actions(driver);
        actions.doubleClick().perform();
    }
    public void dubleClick(WebDriver driver,WebElement element){
        Actions actions = new Actions(driver);
        actions.doubleClick(element).perform();
    }
    public void dragAndDrop(WebDriver driver,WebElement src,WebElement trg){
        Actions actions=new Actions(driver);
        actions.dragAndDrop(src,trg).perform();
    }
    public void dragAndDropBy(WebDriver driver,WebElement element,int x,int y){
        Actions actions= new Actions(driver);
        actions.dragAndDropBy(element,x,y).perform();
    }

    public void scrollByActionClass(WebDriver driver , int x ,int y){
        Actions actions = new Actions(driver);
        actions.scrollByAmount(x,y).perform();
    }
    public void scrollByActionClass(WebDriver driver , WebElement element){
        Actions actions = new Actions(driver);
        actions.scrollToElement(element).perform();
    }
    public void scrollByActionClass(WebDriver driver,WebElement scrollBar,int x,int y){
        Actions actions = new Actions(driver);
        WheelInput.ScrollOrigin sc = WheelInput.ScrollOrigin.fromElement(scrollBar);
        actions.scrollFromOrigin(sc,x,y).perform();
    }

    public void mouseHover(WebDriver driver,WebElement element){
        Actions actions=new Actions(driver);
        actions.moveToElement(element).perform();
    }

    public void pressEnterKey(WebDriver driver){
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ENTER).perform();
    }

    public void enterKeyPressRobot() throws AWTException {
        Robot r=new Robot();
        r.keyPress(KeyEvent.VK_ENTER);
    }
    public void enterKeyReleaseRobot() throws AWTException {
        Robot r=new Robot();
        r.keyRelease(KeyEvent.VK_ENTER);
    }


    public void switchToFrame(WebDriver driver,int index){
        driver.switchTo().frame(index);
    }
    public void switchToFrame(WebDriver driver,String valueOfNameOrId){
        driver.switchTo().frame(valueOfNameOrId);
    }
    public void switchToFrame(WebDriver driver,WebElement element){
        driver.switchTo().frame(element);
    }


    public void acceptAlert(WebDriver driver,String action){
       Alert alert= driver.switchTo().alert();
       if (action.equalsIgnoreCase("ok")){
           alert.accept();
       }else {
           alert.dismiss();
       }
    }

    public String getAlertMessage(WebDriver driver){
        Alert alert = driver.switchTo().alert();
        return alert.getText();
    }

    public void switchToWindow(WebDriver driver, String expectedTitle){
        Set<String> allID=driver.getWindowHandles();
        Iterator<String> iterator = allID.iterator();
        while(iterator.hasNext()){
        String eachId=iterator.next();
        String currentPageTitle= driver.switchTo().window(eachId).getTitle();
        if (currentPageTitle.contains(expectedTitle)){
            break;
        }
        }
    }


    public void scrollByJSE(WebDriver driver,int x,int y){
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy("+x+","+y+")","");
    }

    public void scrollToElementJSE(WebDriver driver,WebElement element){
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        int y = element.getLocation().getY();
        jse.executeScript("window.scrollBy(0,"+y+")",element);
    }


    public String takeScreenShot(WebDriver driver, String screenshotname) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src =ts.getScreenshotAs(OutputType.FILE);
        String path =".\\screenshot\\"+screenshotname+".png";
        File trg = new File(path);
        FileUtils.copyFile(src,trg);
        return path;
    }

}
