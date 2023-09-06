package AdminModule;

import com.ecommerce.GenricUtilities.BaseClass;
import com.ecommerce.ObjectRepository.Admin.AdminHomePage;
import com.ecommerce.ObjectRepository.Admin.AdminLoginPage;
import com.ecommerce.ObjectRepository.Admin.CreateCategoryPage;
import com.ecommerce.ObjectRepository.User.HomePage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
//@Listeners(com.ecommerce.GenricUtilities.ListenerImpl.class)
public class CreateCategoryTestC extends BaseClass {
    @Test(groups = {"smoke"})
    public void createCategory() throws IOException, InterruptedException {
        HomePage hp = new HomePage(driver);
        hp.clickOnLogout();
        // fetching test data from excel file
        String categoryName = excelUtility.getSingleDataFromExcel("CreateCategory",1,1);
        String description = excelUtility.getSingleDataFromExcel("CreateCategory",2,1);
        String expectedSuccessmsg = excelUtility.getSingleDataFromExcel("CreateCategory",3,1);
        driver.get(fileUtility.getDataFromFile("AdminUrl"));
        AdminLoginPage alp = new AdminLoginPage(driver);
        alp.adminLogin(fileUtility.getDataFromFile("AdminUsername"),fileUtility.getDataFromFile("AdminPassword"));
        // creating the category
        // click on Create cat By POM
        AdminHomePage ahp = new AdminHomePage(driver);
        ahp.clickOnCategory();
        //create Category By POM
        CreateCategoryPage ccp = new CreateCategoryPage(driver);
       // ccp.createCategory(categoryName,description);
        // fetching the success message
//        String cnfmsg=driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
//        // validating the success message
//        if (cnfmsg.equals(expectedSuccessmsg)){
//            System.out.println(categoryName+" : is created successfully");
//        }else {
//            System.out.println(categoryName+" : is not created successfully");
//        }
        // validating create category
        ccp.validatingCreateCategory(categoryName,description,expectedSuccessmsg);
        ahp.clickOnLogout();
        // here we need to comment the @AfterClass Method for single execution
        // for group execution just exclude the @AfterMethod (logoutAsUser)
    }
}
