package UserModule;

import com.ecommerce.GenricUtilities.BaseClass;
import com.ecommerce.ObjectRepository.User.CustomerLoginPage;
import com.ecommerce.ObjectRepository.User.HomePage;
import com.ecommerce.ObjectRepository.User.MyAccountPage;
import com.ecommerce.ObjectRepository.User.UserAddressPage;
import org.testng.annotations.Test;

import java.io.IOException;

public class UpdateAddressTestC extends BaseClass {
    @Test(groups = "smoke")
    public void updateAddress() throws IOException {

        // fetching test data from excel file
        //for billing
        String billingAdd= excelUtility.getSingleDataFromExcel("UpdatingAddress",1,0);
        String billingState=  excelUtility.getSingleDataFromExcel("UpdatingAddress",1,1);
        String billingCity=  excelUtility.getSingleDataFromExcel("UpdatingAddress",1,2);
        String billingPincode = excelUtility.getSingleDataFromExcel("UpdatingAddress",1,3);
        // for shipping
        String shippingAdd= excelUtility.getSingleDataFromExcel("UpdatingAddress",1,4);
        String shippingState= excelUtility.getSingleDataFromExcel("UpdatingAddress",1,5);
        String shippingCity=excelUtility.getSingleDataFromExcel("UpdatingAddress",1,6);
        String shippingPincode = excelUtility.getSingleDataFromExcel("UpdatingAddress",1,7);
        //validating the login of user
        CustomerLoginPage clp = new CustomerLoginPage(driver);
        clp.validatingCustomerLogin(driver,"My Cart");
        // click on my account link AND billing add
        HomePage hp = new HomePage(driver);
        hp.clickOnMyAccount();
        MyAccountPage macp = new MyAccountPage(driver);
        macp.clickOnChangeAddress();
        // enter billing details
        UserAddressPage uap =new UserAddressPage(driver);
       // String billingMessage= uap.updateBillingAdd(billingAdd,billingState,billingCity,billingPincode,driver);

        // validating the billing address
        uap.validatingUpdateBillingAdd(billingAdd,billingState,billingCity,billingPincode,driver,"Billing Address has been updated");

        // updating shipping address
        uap.clickOnShippingAddTab();
//        String shippingMessage=uap.updateShippingAdd(shippingAdd,shippingState,shippingCity,shippingPincode,driver);
//        if (shippingMessage.contains("Shipping Address has been updated")){
//            System.out.println("shipping address is updated successfully");
//        }else {
//            System.out.println("shipping address is not updated");
//        }
        uap.validatingUpdateShippingAdd(shippingAdd,shippingState,shippingCity,shippingPincode,driver,"Shipping Address has been updated");
        hp.clickOnLogout();
    }
}
