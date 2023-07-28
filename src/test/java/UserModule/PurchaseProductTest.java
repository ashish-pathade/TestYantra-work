package UserModule;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import javax.swing.text.Utilities;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class PurchaseProductTest  {
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
        String productCatName=workbook.getSheet("PurchaseProduct").getRow(1).getCell(0).getStringCellValue();
        String productSubCatName=workbook.getSheet("PurchaseProduct").getRow(1).getCell(1).getStringCellValue();
        String productName=workbook.getSheet("PurchaseProduct").getRow(1).getCell(2).getStringCellValue();
        System.out.println(productName);


        // launching the browser
        WebDriver driver= launchBrowser(BrowserName);
        driver.manage().window().maximize();
        driver.get(UserUrl);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // login as user
        driver.findElement(By.linkText("Login")).click();
        driver.findElement(By.name("email")).sendKeys(CustomerUserName);
        driver.findElement(By.name("password")).sendKeys(CustomerPwd);
        driver.findElement(By.xpath("//button[text()='Login']")).click();


        // click on category , sub category , product
        driver.findElement(By.xpath("//a[contains(text(),'"+productCatName+"')]")).click();
        driver.findElement(By.xpath("//a[contains(text(),'"+productSubCatName+"')]")).click();
        driver.findElement(By.xpath("//a[contains(text(),'"+productName+"')]")).click();
        driver.findElement(By.xpath
                ("//div[@class='arrows']/following-sibling::input[@type='text']")).clear();
        driver.findElement(By.xpath
                ("//div[@class='arrows']/following-sibling::input[@type='text']")).sendKeys("1");


        // adding the product to the cart
        driver.findElement(By.xpath
                ("//div[@class='cart-quantity']/../following-sibling::div//a[@class='btn btn-primary']")).click();
        Alert alert =driver.switchTo().alert();
        String cartMsg=alert.getText();
        alert.accept();


        // validating the product in the cart
        String actualProductName=driver.findElement(By.xpath("//h4[@class='cart-product-description']//a")).getText();
        if (cartMsg.equalsIgnoreCase("Product has been added to the cart") && actualProductName.contains(productName.toUpperCase())){
            System.out.println(productName+"--> is added to the cart successfully");
        }else {
            System.out.println(productName+"--> is not added to the cart successfully");
        }
        driver.findElement(By.linkText("Logout")).click();
        driver.quit();
    }

    //browser launching
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
