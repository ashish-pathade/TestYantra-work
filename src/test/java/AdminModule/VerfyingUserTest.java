package AdminModule;

import Utilities.CommonUtilities;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.*;

public class VerfyingUserTest {
    public static void main(String[] args) throws IOException {
        Random random=new Random();
        int ran= random.nextInt(300);

        // fetching common data from properties file
        FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\commonData.properties");
        Properties properties = new Properties();
        properties.load(fis);
        String BrowserName=properties.getProperty("browsername");
        String UserUrl= properties.getProperty("url");
        String AdminUrl=properties.getProperty("AdminUrl");
        String AdminUserName= properties.getProperty("AdminUsername");
        String AdminPassword= properties.getProperty("AdiminPassword");


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


        // launching the browser
        WebDriver driver=new CommonUtilities().launchBrowser(UserUrl);
        driver.manage().window().maximize();
        driver.get(UserUrl);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


        // click on login button
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


        // click on sign up button
        driver.findElement(By.name("submit")).click();
        Alert alert=driver.switchTo().alert();
        alert.accept();

        //login as new user
        driver.findElement(By.linkText("Login")).click();
        driver.findElement(By.name("email")).sendKeys(emailId);
        driver.findElement(By.name("password")).sendKeys("Test@123");
        driver.findElement(By.xpath("//button[text()='Login']")).click();
        driver.findElement(By.linkText("Logout")).click();


        // login as admin
        driver.get(AdminUrl);
        driver.findElement(By.name("username")).sendKeys(AdminUserName);
        driver.findElement(By.name("password")).sendKeys(AdminPassword);
        driver.findElement(By.name("submit")).click();


        // click on manage user link
        driver.findElement(By.xpath("//i[@class='menu-icon icon-group']")).click();

        // searching the user by email id
        driver.findElement(By.xpath("//input[@aria-controls='DataTables_Table_0']")).sendKeys(emailId);
        List<WebElement> AlluserEmailId=driver.findElements(By.xpath("//tbody[@role='alert']/tr/td[3]"));
        boolean flag=false;
        for (WebElement eachUserEmailId :AlluserEmailId){
            String eachEId=eachUserEmailId.getText();
            if (eachEId.equals(emailId)){
                flag=true;
            }
        }
        if (flag){
            System.out.println(emailId+" --> user is present on the application");
        }else {
            System.out.println(emailId+" --> user is not present on the application");
        }

    // logout as admin
        driver.findElement(By.xpath("//i[@class='menu-icon icon-signout']")).click();
        driver.quit();
    }
}
