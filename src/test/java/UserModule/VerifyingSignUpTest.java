package UserModule;


import com.ecommerce.GenricUtilities.*;
import com.ecommerce.ObjectRepository.User.CustomerLoginPage;
import com.ecommerce.ObjectRepository.User.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;


public class VerifyingSignUpTest {
    public static void main(String[] args) throws IOException {
        ExcelUtility excelUtility = new ExcelUtility();
        FileUtility fileUtility = new FileUtility();
        JavaUtility javaUtility = new JavaUtility();
        WebDriverUtility wbUtility= new WebDriverUtility();


        // generating random number



        // fetching common data from properties file

        //   using Generic library
        String BrowserName=fileUtility.getDataFromFile("browsername");
        String UserUrl = fileUtility.getDataFromFile("url");
//        // fetching common data from properties file
//        FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\commonData.properties");
//        Properties properties = new Properties();
//        properties.load(fis);
//        String BrowserName=properties.getProperty("browsername");
//        String UserUrl= properties.getProperty("url");

//        // fetching test data from excel file
//        Map<String,String> signupData=new HashMap<>();
//       signupData = excelUtility.getMultipleDataFromExcel("VerifyingSignUp",1,0,1);
//        FileInputStream fi = new FileInputStream(".\\src\\test\\resources\\demoCommon.xlsx");
//        Workbook workbook= WorkbookFactory.create(fi);
//        int rowCount= workbook.getSheet("VerifyingSignUp").getLastRowNum();
//        for (int i=1;i<=rowCount;i++){
//            String key= workbook.getSheet("VerifyingSignUp").getRow(i).getCell(0).getStringCellValue();
//            String value=workbook.getSheet("VerifyingSignUp").getRow(i).getCell(1).getStringCellValue();
//            signupData.put(key,value);
//        }

        String expectedSignupMsg = excelUtility.getSingleDataFromExcel("VerifyingSignUp",2,3);
     //   String expectedSignupMsg=workbook.getSheet("VerifyingSignUp").getRow(2).getCell(3).getStringCellValue();



        // launching the browser
        WebDriver driver=new ChromeDriver();
        wbUtility.maximizeWindow(driver);
        driver.get(UserUrl);
        wbUtility.implicitWait(driver,10);
        // login as user
        HomePage hp = new HomePage(driver);
        hp.clickOnLogin();
        // using signupData map
        CustomerLoginPage clp = new CustomerLoginPage(driver);
        String signupMsg= clp.signUpByAltMsg(excelUtility.getMultipleDataFromExcel
                ("VerifyingSignUp",1,0,1),driver,javaUtility);


//        Alert alert=driver.switchTo().alert();
//        String signUpMsg=alert.getText();
//        alert.accept();
        if (expectedSignupMsg.equalsIgnoreCase(signupMsg)){
            System.out.println("user is successfully register to application");
        }else {
            System.out.println("user is not successfully register to application");
        }
    driver.quit();
    }
    @DataProvider
    public Object[][] getData() throws IOException {
        ExcelUtility excelUtility = new ExcelUtility();
       return excelUtility.getDataByDP("VerifyingSignUp",1);
    }
    @Test(dataProvider = "getData")
    public void SignUpTest(String loc,String value) throws IOException {
        System.out.println(Arrays.deepToString(getData()));
//        ExcelUtility excelUtility = new ExcelUtility();
//        FileUtility fileUtility = new FileUtility();
//        JavaUtility javaUtility = new JavaUtility();
//        WebDriverUtility wbUtility= new WebDriverUtility();
//        String BrowserName=fileUtility.getDataFromFile("browsername");
//        String UserUrl = fileUtility.getDataFromFile("url");
//        WebDriver driver=new ChromeDriver();
//        wbUtility.maximizeWindow(driver);
//        driver.get(UserUrl);
//        wbUtility.implicitWait(driver,10);
//        // login as user
//        HomePage hp = new HomePage(driver);
//        hp.clickOnLogin();
//        driver.findElement(By.name(loc)).sendKeys(value);
    }
}
