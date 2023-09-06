package AdminModule;

import com.ecommerce.GenricUtilities.*;
import com.ecommerce.ObjectRepository.Admin.AdminHomePage;
import com.ecommerce.ObjectRepository.Admin.AdminLoginPage;
import com.ecommerce.ObjectRepository.Admin.CreateCategoryPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;


public class CreateCategoryTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        ExcelUtility excelUtility = new ExcelUtility();
        FileUtility fileUtility = new FileUtility();
        WebDriverUtility wbUtility= new WebDriverUtility();


        // fetching common data from properties file

        //   using Generic library
        String BrowserName=fileUtility.getDataFromFile("browsername");
        String AdminUrl= fileUtility.getDataFromFile("AdminUrl");
        String AdminUserName= fileUtility.getDataFromFile("AdminUsername");
        String AdminPassword= fileUtility.getDataFromFile("AdminPassword");
        // fetching common data from properties file
//        FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\commonData.properties");
//        Properties properties = new Properties();
//        properties.load(fis);
//        String BrowserName=properties.getProperty("browsername");
//        String AdminUrl=properties.getProperty("AdminUrl");
//        String AdminUserName= properties.getProperty("AdminUsername");
//        String AdminPassword= properties.getProperty("AdiminPassword");


        // fetching test data from excel file
        String categoryName = excelUtility.getSingleDataFromExcel("CreateCategory",1,1);
       String description = excelUtility.getSingleDataFromExcel("CreateCategory",2,1);
       String expectedSuccessmsg = excelUtility.getSingleDataFromExcel("CreateCategory",3,1);

//        FileInputStream fi = new FileInputStream(".\\src\\test\\resources\\demoCommon.xlsx");
//        Workbook workbook= WorkbookFactory.create(fi);
//       String categoryName= workbook.getSheet("CreateCategoryPage").getRow(1).getCell(1).getStringCellValue();
//       String description= workbook.getSheet("CreateCategoryPage").getRow(2).getCell(1).getStringCellValue();
//       String expectedSuccessmsg=workbook.getSheet("CreateCategoryPage").getRow(3).getCell(1).getStringCellValue();


        // launching the browser
        WebDriver driver = new ChromeDriver();
        wbUtility.maximizeWindow(driver);
        driver.get(AdminUrl);
        wbUtility.implicitWait(driver,10);


        // login as admin
        driver.get(AdminUrl);
        // login by using POM
        AdminLoginPage alp = new AdminLoginPage(driver);
        alp.adminLogin(AdminUserName,AdminPassword);
//        driver.findElement(By.name("username")).sendKeys(AdminUserName);
//        driver.findElement(By.name("password")).sendKeys(AdminPassword);
//        driver.findElement(By.name("submit")).click();


         // creating the category
        // click on Create cat By POM
        AdminHomePage ahp = new AdminHomePage(driver);
        ahp.clickOnCategory();
//        driver.findElement(By.partialLinkText("Create Category")).click();
        //create Category By POM
        CreateCategoryPage ccp = new CreateCategoryPage(driver);
        ccp.createCategory(categoryName,description);

//        driver.findElement(By.xpath("//input[@name='category']")).sendKeys(categoryName);
//        driver.findElement(By.xpath("//textarea[@name='description']")).sendKeys(description);
//        driver.findElement(By.xpath("//button[@name='submit']")).click();

        // fetching the success message
        String alertMsg=driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();


        // validating the success message
        if (alertMsg.contains(expectedSuccessmsg)){
            System.out.println(categoryName+" : is created successfully");
        }else {
            System.out.println(categoryName+" : is not created successfully");
        }
        driver.close();
    }
}
