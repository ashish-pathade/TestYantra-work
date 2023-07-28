package UserModule;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

public class UpdateAddressTest {
    public static void main(String[] args) throws IOException {

        // fetching common data from properties file

        FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\commonData.properties");
        Properties properties = new Properties();
        properties.load(fis);
        String BrowserName=properties.getProperty("browsername");
        String CustomerUserName=properties.getProperty("CustomerUsername");
        String CustomerPwd = properties.getProperty("CustomerPassword");
        String UserUrl= properties.getProperty("url");

        // fetching test data from excel file

        FileInputStream fi = new FileInputStream(".\\src\\test\\resources\\demoCommon.xlsx");
        Workbook workbook= WorkbookFactory.create(fi);
        String billingAdd=workbook.getSheet("UpdatingAddress").getRow(1).getCell(0).getStringCellValue();
        String billingState=workbook.getSheet("UpdatingAddress").getRow(1).getCell(1).getStringCellValue();
        String billingCity=workbook.getSheet("UpdatingAddress").getRow(1).getCell(2).getStringCellValue();
        DataFormatter dataFormatter=new DataFormatter();
        String billingPincode =dataFormatter.formatCellValue
                (workbook.getSheet("UpdatingAddress").getRow(1).getCell(3));
        String shippingAdd=workbook.getSheet("UpdatingAddress").getRow(1).getCell(4).getStringCellValue();
        String shippingState=workbook.getSheet("UpdatingAddress").getRow(1).getCell(5).getStringCellValue();
        String shippingCity=workbook.getSheet("UpdatingAddress").getRow(1).getCell(6).getStringCellValue();
        String shippingPincode =dataFormatter.formatCellValue
                (workbook.getSheet("UpdatingAddress").getRow(1).getCell(7));



         // updating billing and shipping address
        WebDriver driver=launchBrowser(BrowserName);
        driver.manage().window().maximize();
        driver.get(UserUrl);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // login as user
        driver.findElement(By.linkText("Login")).click();
        driver.findElement(By.name("email")).sendKeys(CustomerUserName);
        driver.findElement(By.name("password")).sendKeys(CustomerPwd);
        driver.findElement(By.xpath("//button[text()='Login']")).click();

        // click on my account link AND billing add

        driver.findElement(By.linkText("My Account")).click();
        driver.findElement(By.linkText("Shipping / Billing Address")).click();

        // enter billing details
        driver.findElement(By.name("billingaddress")).sendKeys(billingAdd);
        driver.findElement(By.name("bilingstate")).sendKeys(billingState);
        driver.findElement(By.name("billingcity")).sendKeys(billingCity);
        driver.findElement(By.name("billingpincode")).sendKeys(billingPincode);
        driver.findElement(By.xpath("//button[@name='update']")).click();
        Alert alert= driver.switchTo().alert();
        String billingMessage=alert.getText();
        if (billingMessage.contains("Billing Address has been updated")){
            System.out.println("billing address is updated successfully");
        }else {
            System.out.println("billing address is not updated");
        }
        alert.accept();


        // updating shipping address
        driver.findElement(By.cssSelector("[class='collapsed']")).click();
        driver.findElement(By.name("shippingaddress")).sendKeys(shippingAdd);
        driver.findElement(By.name("shippingstate")).sendKeys(shippingState);
        driver.findElement(By.name("shippingcity")).sendKeys(shippingCity);
        driver.findElement(By.name("shippingpincode")).sendKeys(shippingPincode);
        driver.findElement(By.name("shipupdate")).click();
        String shippingMessage=alert.getText();
        if (shippingMessage.contains("Shipping Address has been updated")){
            System.out.println("shipping address is updated successfully");
        }else {
            System.out.println("shipping address is not updated");
        }
        alert.accept();

        // logout
        driver.findElement(By.linkText("Logout")).click();
        driver.quit();
    }
    public static WebDriver launchBrowser(String browserName){
        WebDriver driver;
        if (browserName.equalsIgnoreCase("chrome")){
            driver=new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {
            driver= new EdgeDriver();
        }else {
            driver=new ChromeDriver();
        }
        return driver;
    }
}
