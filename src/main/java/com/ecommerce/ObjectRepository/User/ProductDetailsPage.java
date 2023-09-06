package com.ecommerce.ObjectRepository.User;

import com.ecommerce.GenricUtilities.WebDriverUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailsPage extends WebDriverUtility {
    @FindBy(xpath = "//a[text()=' ADD TO CART']")
    private WebElement addToCartBtn;

    @FindBy(xpath = "//div[@class='favorite-button m-t-10']//a")
    private WebElement wishListBtn;

    @FindBy(xpath = "//div[@class='arrows']/following-sibling::input[@type='text']")
    private WebElement productQTY;

    public ProductDetailsPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public WebElement getAddToCartBtn() {
        return addToCartBtn;
    }

    public WebElement getWishListBtn() {
        return wishListBtn;
    }



    // Business Library
    public void clickOnWishList(String quantity){
        productQTY.clear();
        productQTY.sendKeys(quantity);
        wishListBtn.click();
    }

    public void clickOnAddToCart(String quantity,WebDriver driver){
        productQTY.clear();
        productQTY.sendKeys(quantity);
        addToCartBtn.click();
        acceptAlert(driver,"ok");
    }
}
