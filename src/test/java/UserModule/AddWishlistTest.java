package UserModule;

import com.ecommerce.GenricUtilities.*;
import com.ecommerce.ObjectRepository.User.CustomerLoginPage;
import com.ecommerce.ObjectRepository.User.HomePage;
import com.ecommerce.ObjectRepository.User.ProductDetailsPage;
import com.ecommerce.ObjectRepository.User.WishListPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import java.io.IOException;



public class AddWishlistTest {
    public static void main(String[] args) throws IOException {
        ExcelUtility excelUtility = new ExcelUtility();
        FileUtility fileUtility = new FileUtility();
        WebDriverUtility wbUtility= new WebDriverUtility();


        // fetching common data from properties file

           //   using Generic library
        String BrowserName=fileUtility.getDataFromFile("browsername");
        String CustomerUserName=fileUtility.getDataFromFile("CustomerUsername");
        String CustomerPwd = fileUtility.getDataFromFile("CustomerPassword");
        String UserUrl = fileUtility.getDataFromFile("url");


//        FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\commonData.properties");
//        Properties properties = new Properties();
//        properties.load(fis);
//        String BrowserName=properties.getProperty("browsername");
//        String CustomerUserName=properties.getProperty("CustomerUsername");
//        String CustomerPwd = properties.getProperty("CustomerPassword");
//        String UserUrl= properties.getProperty("url");
//        System.out.println(BrowserName);
//        System.out.println( CustomerUserName);

        // fetching test data from excel file

//        FileInputStream fi = new FileInputStream(".\\src\\test\\resources\\demoCommon.xlsx");
//        Workbook workbook= WorkbookFactory.create(fi);
//        String productCatName=workbook.getSheet("AddWishlist").getRow(1).getCell(0).getStringCellValue();
//        String productSubCatName=workbook.getSheet("AddWishlist").getRow(1).getCell(1).getStringCellValue();
//        String productName=workbook.getSheet("AddWishlist").getRow(1).getCell(2).getStringCellValue();
//        String page=workbook.getSheet("AddWishlist").getRow(1).getCell(3).getStringCellValue();

        // using Generic library
        String productCatName = excelUtility.getSingleDataFromExcel("AddWishlist",1,0);
        String productSubCatName = excelUtility.getSingleDataFromExcel("AddWishlist",1,1);
        String productName = excelUtility.getSingleDataFromExcel("AddWishlist",1,2);
        String page = excelUtility.getSingleDataFromExcel("AddWishlist",1,3);

        // launching the browser
        WebDriver driver= launchBrowser(BrowserName);
        wbUtility.maximizeWindow(driver);
        driver.get(UserUrl);
        wbUtility.implicitWait(driver,10);

        // login as user

//        driver.findElement(By.linkText("Login")).click();
//        driver.findElement(By.name("email")).sendKeys(CustomerUserName);
//        driver.findElement(By.name("password")).sendKeys(CustomerPwd);
//        driver.findElement(By.xpath("//button[text()='Login']")).click();

        // Using POM
        HomePage hp = new HomePage(driver);
        hp.clickOnLogin();
        CustomerLoginPage clp = new CustomerLoginPage(driver);
        clp.customerLogin(CustomerUserName,CustomerPwd);

        // click on category
        driver.findElement(By.xpath("//a[contains(text(),'"+productCatName+"')]")).click();
        driver.findElement(By.xpath("//a[contains(text(),'"+productSubCatName+"')]")).click();
        driver.findElement(By.xpath("//a[contains(text(),'"+productName+"')]")).click();

        // wishlist button
        ProductDetailsPage pdp =new ProductDetailsPage(driver);
        pdp.clickOnWishList("1");

        // validating the wishlish page
        WishListPage wlp = new WishListPage(driver);
        wlp.getPageText(page);

        // here i need to pass the product name as an argument !
        wlp.fetchProductName(driver,"Apple iPhone 6 (Silver, 16 GB)",productName);

        // logout
        hp.clickOnLogout();
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
