package AdminModule;

import Utilities.CommonUtilities;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class CreateCategoryTest {
    public static void main(String[] args) throws IOException {
        // fetching common data from properties file
        FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\commonData.properties");
        Properties properties = new Properties();
        properties.load(fis);
        String BrowserName=properties.getProperty("browsername");
        String AdminUrl=properties.getProperty("AdminUrl");
        String AdminUserName= properties.getProperty("AdminUsername");
        String AdminPassword= properties.getProperty("AdiminPassword");


        // fetching test data from excel file
        FileInputStream fi = new FileInputStream(".\\src\\test\\resources\\demoCommon.xlsx");
        Workbook workbook= WorkbookFactory.create(fi);
       String categoryName= workbook.getSheet("CreateCategory").getRow(1).getCell(1).getStringCellValue();
       String description= workbook.getSheet("CreateCategory").getRow(2).getCell(1).getStringCellValue();
       String expectedSuccessmsg=workbook.getSheet("CreateCategory").getRow(3).getCell(1).getStringCellValue();


        // launching the browser
        WebDriver driver=new CommonUtilities().launchBrowser(BrowserName);
        driver.manage().window().maximize();
        driver.get(AdminUrl);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // login as admin
        driver.get(AdminUrl);
        driver.findElement(By.name("username")).sendKeys(AdminUserName);
        driver.findElement(By.name("password")).sendKeys(AdminPassword);
        driver.findElement(By.name("submit")).click();

        // creating the category
        driver.findElement(By.partialLinkText("Create Category")).click();
        driver.findElement(By.xpath("//input[@name='category']")).sendKeys(categoryName);
        driver.findElement(By.xpath("//textarea[@name='description']")).sendKeys(description);
        driver.findElement(By.xpath("//button[@name='submit']")).click();

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
