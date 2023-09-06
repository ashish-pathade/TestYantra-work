package com.ecommerce.ObjectRepository.User;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import javax.xml.xpath.XPath;

public class HomePage {
    @FindBy(xpath = "//a[text()='Login']")
    private WebElement loginLink;
    @FindBy(xpath = "//a[text()='My Account']")
    private WebElement myAccountLink;

    @FindBy(xpath = "//a[text()='Wishlist']")
    private WebElement wishlistLink;

    @FindBy(xpath = "My Cart")
    private WebElement myCartLink;

    @FindBy(xpath = "//a[text()='Logout']")
    private WebElement logoutLink;

    @FindBy(className = "search-field")
    private WebElement searchBox;


    @FindBy(className = "search-button")
    private WebElement seaechBtn;


    @FindBy(xpath = "//span[text()='Track Order']")
    private WebElement trackOrderLink;

    @FindBy(className = "basket")
    private WebElement cartBtn;

    @FindBy(xpath = "//a[text()=' Electronics']")
    private WebElement electronicCategory;

    @FindBy(xpath = "//a[text()=' Fashion']")
    private WebElement fashionCategory;

    @FindBy(xpath = "//a[contains(text(),'Welcome')]")
    private WebElement welcomeMsg;


    // Initialization

    public HomePage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }


    // Utilize

    public WebElement getMyAccountLink() {
        return myAccountLink;
    }

    public WebElement getWishlistLink() {
        return wishlistLink;
    }

    public WebElement getMyCartLink() {
        return myCartLink;
    }

    public WebElement getLogoutLink() {
        return logoutLink;
    }

    public WebElement getSearchBox() {
        return searchBox;
    }

    public WebElement getSeaechBtn() {
        return seaechBtn;
    }

    public WebElement getTrackOrderLink() {
        return trackOrderLink;
    }

    public WebElement getCartBtn() {
        return cartBtn;
    }

    public WebElement getElectronicCategory() {
        return electronicCategory;
    }

    public WebElement getFashionCategory() {
        return fashionCategory;
    }

    public WebElement getWelcomeMsg() {
        return welcomeMsg;
    }


    //Business Library
    public void clickOnLogin(){
        loginLink.click();
    }
    public void clickOnLogout(){
        logoutLink.click();
    }
    public void clickOnMyAccount(){
        myAccountLink.click();
    }

}
