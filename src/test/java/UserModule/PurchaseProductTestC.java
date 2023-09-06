package UserModule;

import com.ecommerce.GenricUtilities.BaseClass;
import com.ecommerce.GenricUtilities.ExcelUtility;
import com.ecommerce.GenricUtilities.FileUtility;
import com.ecommerce.GenricUtilities.WebDriverUtility;
import com.ecommerce.ObjectRepository.User.*;
import org.openqa.selenium.By;

import org.testng.annotations.Test;

import java.io.IOException;

public class PurchaseProductTestC extends BaseClass {
    @Test(groups = "regression")
    public void purchaseProduct() throws IOException, InterruptedException {

        // fetching test data from excel file
        String productCatName = excelUtility.getSingleDataFromExcel("PurchaseProduct",1,0);
        String productSubCatName = excelUtility.getSingleDataFromExcel("PurchaseProduct",1,1);
        String productName = excelUtility.getSingleDataFromExcel("PurchaseProduct",1,2);
        // click on category , sub category , product
        driver.findElement(By.xpath("//a[contains(text(),'"+productCatName+"')]")).click();
        driver.findElement(By.xpath("//a[contains(text(),'"+productSubCatName+"')]")).click();
        driver.findElement(By.xpath("//a[contains(text(),'"+productName+"')]")).click();
        // adding the product to the cart
        ProductDetailsPage pdp = new ProductDetailsPage(driver);
        pdp.clickOnAddToCart("1",driver);
        // click on proceed to payment
        ShoppingCartPage scp = new ShoppingCartPage(driver);
        scp.clickOnProceedToCheckout(driver);
        // select payment mode And buy
        PaymentPage pp =new PaymentPage(driver);
        pp.selectCOD();
        HomePage hp = new HomePage(driver);
        hp.clickOnLogout();
    }
}
