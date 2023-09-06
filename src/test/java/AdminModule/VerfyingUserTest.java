package AdminModule;


import com.ecommerce.GenricUtilities.ExcelUtility;
import com.ecommerce.GenricUtilities.FileUtility;
import com.ecommerce.GenricUtilities.JavaUtility;
import com.ecommerce.GenricUtilities.WebDriverUtility;
import com.ecommerce.ObjectRepository.Admin.AdminHomePage;
import com.ecommerce.ObjectRepository.Admin.AdminLoginPage;
import com.ecommerce.ObjectRepository.Admin.ManageUserPage;
import com.ecommerce.ObjectRepository.User.CustomerLoginPage;
import com.ecommerce.ObjectRepository.User.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.IOException;
import java.util.*;

public class VerfyingUserTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        WebDriverUtility wbUtility = new WebDriverUtility();
        ExcelUtility excelUtility = new ExcelUtility();
        FileUtility fileUtility = new FileUtility();
        JavaUtility javaUtility =new JavaUtility();


        int ran = javaUtility.getRandomNum();
//        Random random=new Random();
//        int ran= random.nextInt(300);


        // fetching common data from properties file

        //   using Generic library
        String BrowserName=fileUtility.getDataFromFile("browsername");
        String AdminUrl= fileUtility.getDataFromFile("AdminUrl");
        String AdminUserName= fileUtility.getDataFromFile("AdminUsername");
        String AdminPassword= fileUtility.getDataFromFile("AdminPassword");
        String UserUrl = fileUtility.getDataFromFile("url");

//        // fetching common data from properties file
//        FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\commonData.properties");
//        Properties properties = new Properties();
//        properties.load(fis);
//        String BrowserName=properties.getProperty("browsername");
//        String UserUrl= properties.getProperty("url");
//        String AdminUrl=properties.getProperty("AdminUrl");
//        String AdminUserName= properties.getProperty("AdminUsername");
//        String AdminPassword= properties.getProperty("AdminPassword");


        // fetching test data from excel file of signup user

//        Map<String,String> signupData;
//        signupData = excelUtility.getMultipleDataFromExcel("VerifyingSignUp",1,0,1);



        // launching the browser
        WebDriver driver=new ChromeDriver();
        wbUtility.maximizeWindow(driver);
        driver.get(UserUrl);
        wbUtility.implicitWait(driver,10);


        // click on login button
        HomePage hp = new HomePage(driver);
        hp.clickOnLogin();


        //  signUp by using Map
        //Using POM
        CustomerLoginPage clp = new CustomerLoginPage(driver);
       String emailId= clp.signUp(excelUtility.getMultipleDataFromExcel
                ("VerifyingSignUp",1,0,1),driver,javaUtility);
//        String emailId=null;
//        for (Map.Entry<String,String>eachData:signupData.entrySet()) {
//            if (eachData.getKey().equalsIgnoreCase("email")) {
//                emailId=eachData.getValue()+ran;
//                driver.findElement(By.id(eachData.getKey())).sendKeys(emailId);
//            } else {
//                driver.findElement(By.id(eachData.getKey())).sendKeys(eachData.getValue());
//            }
//        }

//        Alert alert=driver.switchTo().alert();
//        alert.accept();

        // login as admin
        driver.get(AdminUrl);
        wbUtility.implicitWait(driver,10);
        // Using POM
        AdminLoginPage alp =new AdminLoginPage(driver);
        alp.adminLogin(AdminUserName,AdminPassword);
//        driver.findElement(By.name("username")).sendKeys(AdminUserName);
//        driver.findElement(By.name("password")).sendKeys(AdminPassword);
//        driver.findElement(By.name("submit")).click();


        // click on manage user link
      //  driver.findElement(By.xpath("//i[@class='menu-icon icon-group']")).click();
        // Using POM
        AdminHomePage ahp = new AdminHomePage(driver);
        ahp.clickOnManageUser();


        // searching the user by email id

//        driver.findElement(By.xpath("//input[@aria-controls='DataTables_Table_0']")).sendKeys(emailId);
//        List<WebElement> AlluserEmailId=driver.findElements(By.xpath("//tbody[@role='alert']/tr/td[3]"));
//        boolean flag=false;
//        for (WebElement eachUserEmailId :AlluserEmailId){
//            String eachEId=eachUserEmailId.getText();
//            if (eachEId.equals(emailId)){
//                flag=true;
//            }
//        }
//        if (flag){
//            System.out.println(emailId+" --> user is present on the application");
//        }else {
//            System.out.println(emailId+" --> user is not present on the application");
//        }
        //Using POM
        // click on search and pass emailId
        ManageUserPage mup = new ManageUserPage(driver);
        // verifying the user is created or not
        mup.searchForUser(emailId);

    // logout as admin
        // Using POM
        ahp.clickOnLogout();
       // driver.findElement(By.xpath("//i[@class='menu-icon icon-signout']")).click();
        driver.quit();
    }
}
