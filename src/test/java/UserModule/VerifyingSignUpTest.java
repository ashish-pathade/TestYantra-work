package UserModule;

import Utilities.CommonUtilities;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;

public class VerifyingSignUpTest {
    public static void main(String[] args) throws IOException {
        Random random=new Random();
        int ran= random.nextInt(300);

        // fetching common data from properties file
        FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\commonData.properties");
        Properties properties = new Properties();
        properties.load(fis);
        String BrowserName=properties.getProperty("browsername");
        String UserUrl= properties.getProperty("url");


        // fetching test data from excel file
        FileInputStream fi = new FileInputStream(".\\src\\test\\resources\\demoCommon.xlsx");
        Workbook workbook= WorkbookFactory.create(fi);
        int rowCount= workbook.getSheet("VerifyingSignUp").getLastRowNum();
        Map<String,String> signupData=new HashMap<>();
        for (int i=1;i<=rowCount;i++){
            String key= workbook.getSheet("VerifyingSignUp").getRow(i).getCell(0).getStringCellValue();
            String value=workbook.getSheet("VerifyingSignUp").getRow(i).getCell(1).getStringCellValue();
            signupData.put(key,value);
        }
        String expectedSignupMsg=workbook.getSheet("VerifyingSignUp").getRow(2).getCell(3).getStringCellValue();



        // launching the browser
        WebDriver driver=new CommonUtilities().launchBrowser(UserUrl);
        driver.manage().window().maximize();
        driver.get(UserUrl);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        // login as user
        driver.findElement(By.linkText("Login")).click();
        String emailId=null;
        for (Map.Entry<String,String>eachData:signupData.entrySet()) {
            if (eachData.getKey().equalsIgnoreCase("email")) {
                emailId=eachData.getValue()+ran;
                driver.findElement(By.id(eachData.getKey())).sendKeys(emailId);
            } else {
                driver.findElement(By.id(eachData.getKey())).sendKeys(eachData.getValue());
            }
        }
        driver.findElement(By.name("submit")).click();
        Alert alert=driver.switchTo().alert();
        String signUpMsg=alert.getText();
        alert.accept();
        if (expectedSignupMsg.equalsIgnoreCase(signUpMsg)){
            System.out.println("user is successfully register to application");
        }else {
            System.out.println("user is not successfully register to application");
        }
    driver.quit();
    }
}
