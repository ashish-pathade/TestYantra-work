package com.ecommerce.ObjectRepository.User;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccountPage {
    @FindBy(xpath = "//a[text()='Shipping / Billing Address']")
    private WebElement shippingAndBillingAdd;

    @FindBy(xpath = "//a[text()='Order History']")
    private WebElement orderHistoryLink;

    @FindBy(xpath = "//a[text()='Payment Pending Order']")
    private WebElement PaymentPending;

    @FindBy(xpath = "//input[@id='name']")
    private WebElement editProfileName;

    @FindBy(id = "contactno")
    private WebElement editProfileContact;

    @FindBy(name = "update")
    private WebElement ProfileUpdateBtn;

    @FindBy(xpath = "//a[contains(text(),'Change Password')]")
    private WebElement changePwdPanel;

    @FindBy(id = "cpass")
    private WebElement currentPwdTextField;

    @FindBy(id = "newpass")
    private WebElement newPwdTextField;

    @FindBy(id = "cnfpass")
    private WebElement confirmPwdTextField;

    @FindBy(xpath = "//button[text()='Change ']")
    private WebElement changeBtn;


    public MyAccountPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }


    public WebElement getShippingAndBillingAdd() {
        return shippingAndBillingAdd;
    }

    public WebElement getOrderHistoryLink() {
        return orderHistoryLink;
    }

    public WebElement getPaymentPending() {
        return PaymentPending;
    }

    public WebElement getEditProfileName() {
        return editProfileName;
    }

    public WebElement getEditProfileContact() {
        return editProfileContact;
    }

    public WebElement getProfileUpdateBtn() {
        return ProfileUpdateBtn;
    }

    public WebElement getChangePwdPanel() {
        return changePwdPanel;
    }

    public WebElement getCurrentPwdTextField() {
        return currentPwdTextField;
    }

    public WebElement getNewPwdTextField() {
        return newPwdTextField;
    }

    public WebElement getConfirmPwdTextField() {
        return confirmPwdTextField;
    }

    public WebElement getChangeBtn() {
        return changeBtn;
    }


    //Business Library
    public void clickOnChangeAddress(){
        shippingAndBillingAdd.click();
    }
}
