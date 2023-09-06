package UserModule;

import com.ecommerce.GenricUtilities.*;
import com.ecommerce.ObjectRepository.User.CustomerLoginPage;
import com.ecommerce.ObjectRepository.User.HomePage;
import com.ecommerce.ObjectRepository.User.MyAccountPage;
import com.ecommerce.ObjectRepository.User.UserAddressPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import java.io.IOException;




public class UpdateAddressTest {
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

        // fetching test data from excel file

        String billingAdd= excelUtility.getSingleDataFromExcel("UpdatingAddress",1,0);
        String billingState=  excelUtility.getSingleDataFromExcel("UpdatingAddress",1,1);
        String billingCity=  excelUtility.getSingleDataFromExcel("UpdatingAddress",1,2);
        String billingPincode = excelUtility.getSingleDataFromExcel("UpdatingAddress",1,3);

        String shippingAdd= excelUtility.getSingleDataFromExcel("UpdatingAddress",1,4);
        String shippingState= excelUtility.getSingleDataFromExcel("UpdatingAddress",1,5);
        String shippingCity=excelUtility.getSingleDataFromExcel("UpdatingAddress",1,6);
        String shippingPincode = excelUtility.getSingleDataFromExcel("UpdatingAddress",1,7);


//        FileInputStream fi = new FileInputStream(".\\src\\test\\resources\\demoCommon.xlsx");
//        Workbook workbook= WorkbookFactory.create(fi);
//        String billingAdd=workbook.getSheet("UpdatingAddress").getRow(1).getCell(0).getStringCellValue();
//        String billingState=workbook.getSheet("UpdatingAddress").getRow(1).getCell(1).getStringCellValue();
//        String billingCity=workbook.getSheet("UpdatingAddress").getRow(1).getCell(2).getStringCellValue();
//        DataFormatter dataFormatter=new DataFormatter();
//        String billingPincode =dataFormatter.formatCellValue
//                (workbook.getSheet("UpdatingAddress").getRow(1).getCell(3));
//        String shippingAdd=workbook.getSheet("UpdatingAddress").getRow(1).getCell(4).getStringCellValue();
//        String shippingState=workbook.getSheet("UpdatingAddress").getRow(1).getCell(5).getStringCellValue();
//        String shippingCity=workbook.getSheet("UpdatingAddress").getRow(1).getCell(6).getStringCellValue();
//        String shippingPincode =dataFormatter.formatCellValue
//                (workbook.getSheet("UpdatingAddress").getRow(1).getCell(7));



         // updating billing and shipping address
        WebDriver driver=launchBrowser(BrowserName);
        wbUtility.maximizeWindow(driver);
        driver.get(UserUrl);
        wbUtility.implicitWait(driver,10);

        // login as user
        HomePage hp = new HomePage(driver);
        hp.clickOnLogin();
        CustomerLoginPage clp = new CustomerLoginPage(driver);
        clp.customerLogin(CustomerUserName,CustomerPwd);

        // click on my account link AND billing add
        hp.clickOnMyAccount();
        MyAccountPage macp = new MyAccountPage(driver);
        macp.clickOnChangeAddress();

        // enter billing details
        UserAddressPage uap =new UserAddressPage(driver);
        String billingMessage= uap.updateBillingAdd(billingAdd,billingState,billingCity,billingPincode,driver);


//        Alert alert= driver.switchTo().alert();
//        String billingMessage=alert.getText();
        // validating the billing address
        if (billingMessage.contains("Billing Address has been updated")){
            System.out.println("billing address is updated successfully");
        }else {
            System.out.println("billing address is not updated");
        }

        // updating shipping address
        uap.clickOnShippingAddTab();
       String shippingMessage=uap.updateShippingAdd(shippingAdd,shippingState,shippingCity,shippingPincode,driver);
        if (shippingMessage.contains("Shipping Address has been updated")){
            System.out.println("shipping address is updated successfully");
        }else {
            System.out.println("shipping address is not updated");
        }


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
