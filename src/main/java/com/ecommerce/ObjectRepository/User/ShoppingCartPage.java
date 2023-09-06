package com.ecommerce.ObjectRepository.User;

import com.ecommerce.GenricUtilities.WebDriverUtility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShoppingCartPage extends WebDriverUtility {
    @FindBy(name = "remove_code[]")
    private WebElement removeCheckBox;

    @FindBy(xpath = "//input[contains(@class,'pull-right outer-right-xs')]")
    private WebElement updateCartBtn;

    @FindBy(name = "ordersubmit")
    private WebElement proceedToCheckOutBtn;


    public ShoppingCartPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public WebElement getRemoveCheckBox() {
        return removeCheckBox;
    }

    public WebElement getUpdateCartBtn() {
        return updateCartBtn;
    }

    public WebElement getProceedToCheckOutBtn() {
        return proceedToCheckOutBtn;
    }

    //Business Library
    public String clickOnProceedToCheckout(WebDriver driver){
        proceedToCheckOutBtn.click();
//        String altMsg=getAlertMessage(driver);
//        acceptAlert(driver,"ok");
//        return altMsg;
        return null;
    }
}
