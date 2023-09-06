package AdminModule;

import com.ecommerce.GenricUtilities.*;
import com.ecommerce.ObjectRepository.Admin.AdminHomePage;
import com.ecommerce.ObjectRepository.Admin.AdminLoginPage;
import com.ecommerce.ObjectRepository.Admin.ManageUserPage;
import com.ecommerce.ObjectRepository.User.CustomerLoginPage;

import com.ecommerce.ObjectRepository.User.HomePage;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.IOException;
@Listeners(com.ecommerce.GenricUtilities.ListenerImpl.class)
public class VerifyingUserTestC extends BaseClass {

    // in xml file we need to write exclude @BeforeClass (loginMethod) for the perfect execution
    @Test(groups = "regression")
    public void SignUpUser() throws IOException, InterruptedException {
        HomePage hp = new HomePage(driver);
        hp.clickOnLogout();
        String AdminUrl = fileUtility.getDataFromFile("AdminUrl");
        String AdminUserName=fileUtility.getDataFromFile("AdminUsername");
        String AdminPassword = fileUtility.getDataFromFile("AdminPassword");
        //  signUp by using Map
        //Using POM
        hp.clickOnLogin();
        CustomerLoginPage clp = new CustomerLoginPage(driver);
        String  emailId = clp.signUp(excelUtility.getMultipleDataFromExcel
                    ("VerifyingSignUp",1,0,1),driver,javaUtility);
        driver.get(AdminUrl);
        AdminLoginPage alp = new AdminLoginPage(driver);
        alp.adminLogin(AdminUserName,AdminPassword);
        AdminHomePage ahp = new AdminHomePage(driver);
        ahp.clickOnManageUser();
        Assert.fail();
        ManageUserPage mup = new ManageUserPage(driver);
        // verifying the user is created or not
        mup.searchForUser(emailId);
        // here we need to exclude logout method which is @AfterMethod in xml file
        // for single run comment the @AfterClass
    }

}
