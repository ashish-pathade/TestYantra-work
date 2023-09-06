package UserModule;

import com.ecommerce.GenricUtilities.*;
import com.ecommerce.ObjectRepository.User.CustomerLoginPage;
import com.ecommerce.ObjectRepository.User.HomePage;
import org.testng.annotations.Test;
import java.io.IOException;


public class VerifyingSignUpTestC extends BaseClass {
    @Test(groups = {"smoke","regression"})
    public void signUpTest() throws IOException {

        HomePage hp = new HomePage(driver);
        hp.clickOnLogout();

        // fetching test data from excel file
        String expectedSignupMsg = excelUtility.getSingleDataFromExcel("VerifyingSignUp",2,3);
        //   String expectedSignupMsg=workbook.getSheet("VerifyingSignUp").getRow(2).getCell(3).getStringCellValue();
        // login as user
        hp.clickOnLogin();
        // using signupData map
        CustomerLoginPage clp = new CustomerLoginPage(driver);
//        String signupMsg= clp.signUpByAltMsg(excelUtility.getMultipleDataFromExcel
//                ("VerifyingSignUp",1,0,1),driver,javaUtility);
        //validating user is able to signup or not *******
        clp.validatingSignUpByAltMsg
                (driver,excelUtility.getMultipleDataFromExcel("VerifyingSignUp",1,0,1)
                        ,javaUtility,expectedSignupMsg);
//        if (expectedSignupMsg.equalsIgnoreCase(signupMsg)){
//            System.out.println("user is successfully register to application");
//        }else {
//            System.out.println("user is not successfully register to application");
//        }
        // here while running through xml file just exclude @AfterMethod(logout) then it will work . In single run just disable the AfterClass
    }
}
