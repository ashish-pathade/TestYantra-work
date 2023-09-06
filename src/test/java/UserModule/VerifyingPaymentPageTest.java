package UserModule;

import com.ecommerce.GenricUtilities.ExcelUtility;
import com.ecommerce.GenricUtilities.FileUtility;
import com.ecommerce.GenricUtilities.WebDriverUtility;
import com.ecommerce.ObjectRepository.User.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.Map;


public class VerifyingPaymentPageTest {
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

//        // fetching common data from properties file
//        FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\commonData.properties");
//        Properties properties = new Properties();
//        properties.load(fis);
//        String BrowserName=properties.getProperty("browsername");
//        String CustomerUserName=properties.getProperty("CustomerUsername");
//        String CustomerPwd = properties.getProperty("CustomerPassword");
//        String UserUrl= properties.getProperty("url");


        // fetching test data from excel file for purchase product

        String productCatName = excelUtility.getSingleDataFromExcel("PurchaseProduct",1,0);
        String productSubCatName = excelUtility.getSingleDataFromExcel("PurchaseProduct",1,1);
        String productName = excelUtility.getSingleDataFromExcel("PurchaseProduct",1,2);
//        FileInputStream fi = new FileInputStream(".\\src\\test\\resources\\demoCommon.xlsx");
//        Workbook workbook= WorkbookFactory.create(fi);
//        String productCatName=workbook.getSheet("PurchaseProduct").getRow(1).getCell(0).getStringCellValue();
//        String productSubCatName=workbook.getSheet("PurchaseProduct").getRow(1).getCell(1).getStringCellValue();
//        String productName=workbook.getSheet("PurchaseProduct").getRow(1).getCell(2).getStringCellValue();
//        System.out.println(productName);


        // fetching for Verifying payment
        Map<String,String> paymentMod;
        paymentMod=excelUtility.getMultipleDataFromExcel("VerfyingPayment",0,1);
//        int rowCount=workbook.getSheet("VerfyingPayment").getLastRowNum();
//        for (int i=0;i<=rowCount;i++){
//            int key= (int) workbook.getSheet("VerfyingPayment").getRow(i).getCell(0).getNumericCellValue();
//            String value=workbook.getSheet("VerfyingPayment").getRow(i).getCell(1).getStringCellValue();
//            paymentMod.put(key,value);
//        }


        // launching the browser
        WebDriver driver= new ChromeDriver();
        wbUtility.maximizeWindow(driver);
        driver.get(UserUrl);
        wbUtility.implicitWait(driver,10);

        // click on login
        HomePage hp = new HomePage(driver);
        hp.clickOnLogin();

        // login as user
        CustomerLoginPage clp =new CustomerLoginPage(driver);
        clp.customerLogin(CustomerUserName,CustomerPwd);


        // click on category , sub category , product
        driver.findElement(By.xpath("//a[contains(text(),'"+productCatName+"')]")).click();
        driver.findElement(By.xpath("//a[contains(text(),'"+productSubCatName+"')]")).click();
        driver.findElement(By.xpath("//a[contains(text(),'"+productName+"')]")).click();


        // adding the product to the cart
        ProductDetailsPage pdp =new ProductDetailsPage(driver);
        pdp.clickOnAddToCart("1",driver);

        // Click on proceed to checkout

        ShoppingCartPage scp = new ShoppingCartPage(driver);
        scp.clickOnProceedToCheckout(driver);


        // verifying all payment options

        PaymentPage pp = new PaymentPage(driver);
       String AllPaymentMod= pp.getAllPaymentMtdName();


        // paymentMod map using here

        for (Map.Entry<String,String> eachPayment:paymentMod.entrySet()){

            if (AllPaymentMod.contains(eachPayment.getValue())){
                System.out.println(eachPayment.getValue()+"--> payment mode is present");
            }else {
                System.out.println(eachPayment.getValue()+"--> payment mode is not present");
            }
        }
        hp.clickOnLogout();
        driver.close();

    }



}
