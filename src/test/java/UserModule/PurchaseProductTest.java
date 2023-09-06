package UserModule;

import com.ecommerce.GenricUtilities.*;
import com.ecommerce.ObjectRepository.User.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import java.io.IOException;

public class PurchaseProductTest  {
    public static void main(String[] args) throws IOException, InterruptedException {
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


        // fetching test data from excel file

        String productCatName = excelUtility.getSingleDataFromExcel("PurchaseProduct",1,0);
        String productSubCatName = excelUtility.getSingleDataFromExcel("PurchaseProduct",1,1);
        String productName = excelUtility.getSingleDataFromExcel("PurchaseProduct",1,2);

//        FileInputStream fi = new FileInputStream(".\\src\\test\\resources\\demoCommon.xlsx");
//        Workbook workbook= WorkbookFactory.create(fi);
//        String productCatName=workbook.getSheet("PurchaseProduct").getRow(1).getCell(0).getStringCellValue();
//        String productSubCatName=workbook.getSheet("PurchaseProduct").getRow(1).getCell(1).getStringCellValue();
//        String productName=workbook.getSheet("PurchaseProduct").getRow(1).getCell(2).getStringCellValue();
//        System.out.println(productName);


        // launching the browser
        WebDriver driver= launchBrowser(BrowserName);
        wbUtility.maximizeWindow(driver);
        driver.get(UserUrl);
        wbUtility.implicitWait(driver,10);

        // login as user
        HomePage hp = new HomePage(driver);
        hp.clickOnLogin();
        CustomerLoginPage clp = new CustomerLoginPage(driver);
        clp.customerLogin(CustomerUserName,CustomerPwd);


        // click on category , sub category , product
        driver.findElement(By.xpath("//a[contains(text(),'"+productCatName+"')]")).click();
        driver.findElement(By.xpath("//a[contains(text(),'"+productSubCatName+"')]")).click();
        driver.findElement(By.xpath("//a[contains(text(),'"+productName+"')]")).click();




        // adding the product to the cart
        ProductDetailsPage pdp = new ProductDetailsPage(driver);
        pdp.clickOnAddToCart("1",driver);
//        Alert alert =driver.switchTo().alert();
//        String cartMsg=alert.getText();
//        alert.accept();
        // click on proceed to payment
        ShoppingCartPage scp = new ShoppingCartPage(driver);
        scp.clickOnProceedToCheckout(driver);

        // select payment mode And buy
        PaymentPage pp =new PaymentPage(driver);
        pp.selectCOD();

        hp.clickOnLogout();

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
