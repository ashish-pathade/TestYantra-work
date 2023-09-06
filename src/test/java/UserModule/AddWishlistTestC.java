package UserModule;

import com.ecommerce.GenricUtilities.BaseClass;

import com.ecommerce.ObjectRepository.User.HomePage;
import com.ecommerce.ObjectRepository.User.ProductDetailsPage;
import com.ecommerce.ObjectRepository.User.WishListPage;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import java.io.IOException;

public class AddWishlistTestC  extends BaseClass {
    @Test(groups = "regression")
    public void wishlist() throws IOException {
        // using Generic library
        String productCatName = excelUtility.getSingleDataFromExcel("AddWishlist",1,0);
        String productSubCatName = excelUtility.getSingleDataFromExcel("AddWishlist",1,1);
        String productName = excelUtility.getSingleDataFromExcel("AddWishlist",1,2);
        String page = excelUtility.getSingleDataFromExcel("AddWishlist",1,3);

        // click on category
        driver.findElement(By.xpath("//a[contains(text(),'"+productCatName+"')]")).click();
        driver.findElement(By.xpath("//a[contains(text(),'"+productSubCatName+"')]")).click();
        driver.findElement(By.xpath("//a[contains(text(),'"+productName+"')]")).click();

        // wishlist button
        ProductDetailsPage pdp =new ProductDetailsPage(driver);
        pdp.clickOnWishList("1");

        // validating the wishlish page
        WishListPage wlp = new WishListPage(driver);
        wlp.getPageText(page);

        // here i need to pass the product name as an argument !
        // validating the product name
        wlp.fetchProductName(driver,"Apple iPhone 6 (Silver, 16 GB)",productName);
        HomePage hp = new HomePage(driver);
        hp.clickOnLogout();
    }

}
