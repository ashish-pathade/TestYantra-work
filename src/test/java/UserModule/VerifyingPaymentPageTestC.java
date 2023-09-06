package UserModule;

import com.ecommerce.GenricUtilities.BaseClass;
import com.ecommerce.ObjectRepository.User.*;
import org.openqa.selenium.By;
import org.testng.annotations.Test;


import java.io.IOException;
import java.util.Map;

public class VerifyingPaymentPageTestC extends BaseClass {
    @Test(groups = "smoke")
    public void paymentModVerification() throws IOException {
        // fetching test data from excel file for purchase product
        String productCatName = excelUtility.getSingleDataFromExcel("PurchaseProduct",1,0);
        String productSubCatName = excelUtility.getSingleDataFromExcel("PurchaseProduct",1,1);
        String productName = excelUtility.getSingleDataFromExcel("PurchaseProduct",1,2);
        // fetching for Verifying payment
        Map<String,String> paymentMod;
        paymentMod=excelUtility.getMultipleDataFromExcel("VerfyingPayment",0,1);

        // click on category , sub category , product
        driver.findElement(By.xpath("//a[contains(text(),'"+productCatName+"')]")).click();
        driver.findElement(By.xpath("//a[contains(text(),'"+productSubCatName+"')]")).click();
        driver.findElement(By.xpath("//a[contains(text(),'"+productName+"')]")).click();
        // adding the product to the cart
        ProductDetailsPage pdp =new ProductDetailsPage(driver);
        pdp.clickOnAddToCart("1",driver);
        // Click on proceed to checkout
        ShoppingCartPage scp = new ShoppingCartPage(driver);
        scp.clickOnProceedToCheckout(driver);
        // verifying all payment options
        PaymentPage pp = new PaymentPage(driver);
        String AllPaymentMod= pp.getAllPaymentMtdName();
        // validating all payment method is present or not
        pp.validatingPaymentMod(paymentMod);
        // paymentMod map using here
//        for (Map.Entry<String,String> eachPayment:paymentMod.entrySet()){
//            if (AllPaymentMod.contains(eachPayment.getValue())){
//                System.out.println(eachPayment.getValue()+"--> payment mode is present");
//            }else {
//                System.out.println(eachPayment.getValue()+"--> payment mode is not present");
//            }
//        }
        HomePage hp = new HomePage(driver);
        hp.clickOnLogout();
    }
}
