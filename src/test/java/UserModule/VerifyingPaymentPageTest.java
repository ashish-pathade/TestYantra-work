package UserModule;

import Utilities.CommonUtilities;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class VerifyingPaymentPageTest {
    public static void main(String[] args) throws IOException {
        CommonUtilities commonUtilities=new CommonUtilities();

        // fetching common data from properties file
        FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\commonData.properties");
        Properties properties = new Properties();
        properties.load(fis);
        String BrowserName=properties.getProperty("browsername");
        String CustomerUserName=properties.getProperty("CustomerUsername");
        String CustomerPwd = properties.getProperty("CustomerPassword");
        String UserUrl= properties.getProperty("url");


        // fetching test data from excel file for purchase product
        FileInputStream fi = new FileInputStream(".\\src\\test\\resources\\demoCommon.xlsx");
        Workbook workbook= WorkbookFactory.create(fi);
        String productCatName=workbook.getSheet("PurchaseProduct").getRow(1).getCell(0).getStringCellValue();
        String productSubCatName=workbook.getSheet("PurchaseProduct").getRow(1).getCell(1).getStringCellValue();
        String productName=workbook.getSheet("PurchaseProduct").getRow(1).getCell(2).getStringCellValue();
        System.out.println(productName);


        // fetching for Verifying payment
        Map<Integer,String> paymentMod=new HashMap<Integer,String>();
        int rowCount=workbook.getSheet("VerfyingPayment").getLastRowNum();
        for (int i=0;i<=rowCount;i++){
            int key= (int) workbook.getSheet("VerfyingPayment").getRow(i).getCell(0).getNumericCellValue();
            String value=workbook.getSheet("VerfyingPayment").getRow(i).getCell(1).getStringCellValue();
            paymentMod.put(key,value);
        }


        // launching the browser
        WebDriver driver=commonUtilities.launchBrowser(BrowserName);
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
        alert.accept();

        // Click on proceed to checkout
        driver.findElement(By.name("ordersubmit")).click();

        // verifying all payment options
        String AllPaymentMod = driver.findElement(By.name("payment")).getText();

        for (Map.Entry<Integer,String> eachPayment:paymentMod.entrySet()){

            if (AllPaymentMod.contains(eachPayment.getValue())){
                System.out.println(eachPayment.getValue()+"--> payment mode is present");
            }else {
                System.out.println(eachPayment.getValue()+"--> payment mode is not present");
            }
        }



        driver.close();

    }



}
