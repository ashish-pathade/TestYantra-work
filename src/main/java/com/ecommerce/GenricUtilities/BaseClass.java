package com.ecommerce.GenricUtilities;
import com.ecommerce.ObjectRepository.User.CustomerLoginPage;
import com.ecommerce.ObjectRepository.User.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.*;
import java.io.IOException;
import java.sql.SQLException;

public class BaseClass {
    public static WebDriver driver ;
    public static WebDriver listenerDriver;
   public WebDriverUtility wbUtility = new WebDriverUtility();
   public ExcelUtility excelUtility = new ExcelUtility();
   public FileUtility fileUtility = new FileUtility();
   public DatabaseUtility dbUtility = new DatabaseUtility();
   public JavaUtility javaUtility = new JavaUtility();

    @BeforeSuite(alwaysRun = true)
    public void dataBaseConnection() throws SQLException {
        dbUtility.createConnectionToDB();
        Reporter.log(" connecting to the database ",true);

    }
    // this mtd is foe cross browser by xml file
//    @Parameters("BROWSER")
//    @BeforeClass
//    public void launchBrowser(String BROWSER) throws IOException {
//       //String BROWSER= fileUtility.getDataFromFile("browsername");
//       String URL= fileUtility.getDataFromFile("url");
//       if (BROWSER.equalsIgnoreCase("chrome")){
//           driver= new ChromeDriver();
//       }else if (BROWSER.equalsIgnoreCase("firefox")) {
//            driver=new FirefoxDriver();
//        }else {
//           driver= new EdgeDriver();
//       }
//       wbUtility.maximizeWindow(driver);
//       driver.get(URL);
//       wbUtility.implicitWait(driver,10);
//        Reporter.log(" Launching the Browser ",true);
//    }

    // this method is for diff browser by properties file
    @BeforeClass (alwaysRun = true)
    public void launchBrowser() throws IOException {
        String BROWSER= fileUtility.getDataFromFile("browsername");
        String URL= fileUtility.getDataFromFile("url");
        if (BROWSER.equalsIgnoreCase("chrome")){
            driver= new ChromeDriver();
        } else if (BROWSER.equalsIgnoreCase("firefox")) {
            driver=new FirefoxDriver();
        } else {
            driver= new EdgeDriver();
        }
        listenerDriver=driver;
        wbUtility.maximizeWindow(driver);
        driver.get(URL);
        wbUtility.implicitWait(driver,10);
        Reporter.log(" Launching the Browser ",true);
    }

    @BeforeMethod(alwaysRun = true)
    public void loginToApplication() throws IOException {
        HomePage hp = new HomePage(driver);
        hp.clickOnLogin();
        String CustomerUserName=fileUtility.getDataFromFile("CustomerUsername");
        String CustomerPwd = fileUtility.getDataFromFile("CustomerPassword");
        CustomerLoginPage clp = new CustomerLoginPage(driver);
        clp.customerLogin(CustomerUserName,CustomerPwd);
        Reporter.log(" Login to the Application ",true);
    }
//    @BeforeMethod (alwaysRun = true)
//    public void loginToApplication() throws IOException {
//        String URL= fileUtility.getDataFromFile("url");
//        if (URL.equals("http://rmgtestingserver/domain/Online_Shopping_Application/admin/")) {
//            String AdminUserName=fileUtility.getDataFromFile("AdminUsername");
//            String AdminPassword = fileUtility.getDataFromFile("AdminPassword");
//            AdminLoginPage alp = new AdminLoginPage(driver);
//            alp.adminLogin(AdminUserName,AdminPassword);
//        }else {
//            HomePage hp = new HomePage(driver);
//            hp.clickOnLogin();
//            String CustomerUserName=fileUtility.getDataFromFile("CustomerUsername");
//            String CustomerPwd = fileUtility.getDataFromFile("CustomerPassword");
//            CustomerLoginPage clp = new CustomerLoginPage(driver);
//            clp.customerLogin(CustomerUserName,CustomerPwd);
//        }
//        Reporter.log(" Login to the Application ",true);
//    }
    @AfterMethod(dependsOnGroups = "user")
    public void logoutFromApplicationAsUser(){
//        HomePage hp = new HomePage(driver);
//        hp.clickOnLogout();
        Reporter.log(" Logout from the Application ",true);
    }


//    @AfterMethod (alwaysRun = true)
//    public void logoutFromApplicationAsAdmin() throws IOException {
//        String URL= fileUtility.getDataFromFile("url");
//        if (URL.equals("http://rmgtestingserver/domain/Online_Shopping_Application/admin/")){
//        AdminHomePage ahp = new AdminHomePage(driver);
//        ahp.clickOnLogout();
//        }else {
//            HomePage hp = new HomePage(driver);
//            hp.clickOnLogout();
//        }
//        Reporter.log(" Logout from the Application ",true);
//    }
    @AfterClass (alwaysRun = true)
    public void tearDown(){
        driver.quit();
        Reporter.log(" Closing the browser ",true);
    }

    @AfterSuite (alwaysRun = true)
    public void closeDBConnection() throws SQLException {
        dbUtility.closeConnectOfDB();
        Reporter.log(" Closing the Database connection ",true);
    }

}
