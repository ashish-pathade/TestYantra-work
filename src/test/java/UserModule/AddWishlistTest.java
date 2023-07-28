package UserModule;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class AddWishlistTest {
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
        String productCatName=workbook.getSheet("AddWishlist").getRow(1).getCell(0).getStringCellValue();
        String productSubCatName=workbook.getSheet("AddWishlist").getRow(1).getCell(1).getStringCellValue();
        String productName=workbook.getSheet("AddWishlist").getRow(1).getCell(2).getStringCellValue();
        String page=workbook.getSheet("AddWishlist").getRow(1).getCell(3).getStringCellValue();

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

        // click on category
        driver.findElement(By.xpath("//a[contains(text(),'"+productCatName+"')]")).click();
        driver.findElement(By.xpath("//a[contains(text(),'"+productSubCatName+"')]")).click();
        driver.findElement(By.xpath("//a[contains(text(),'"+productName+"')]")).click();
        driver.findElement(By.xpath
                ("//div[@class='arrows']/following-sibling::input[@type='text']")).sendKeys("1");
        driver.findElement(By.xpath("//div[@class='favorite-button m-t-10']//a")).click();
        // validating the wishlish page
        String currentPage=driver.findElement(By.xpath("//li[text()='Wishlish']")).getText();
        String actualProduct=driver.findElement(By.linkText("Apple iPhone 6 (Silver, 16 GB)")).getText();
        if (currentPage.equalsIgnoreCase(page) && actualProduct.contains(productName)){
            System.out.println(productName+" --> is added to whishlist successfully");
        }else {
            System.out.println(productName+" --> is not added successfully");
        }
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
