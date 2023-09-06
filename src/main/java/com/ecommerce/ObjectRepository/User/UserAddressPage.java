package com.ecommerce.ObjectRepository.User;

import com.ecommerce.GenricUtilities.WebDriverUtility;
import org.apache.commons.math3.geometry.partitioning.BSPTree;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static org.testng.Assert.*;

public class UserAddressPage extends WebDriverUtility {

    @FindBy(name = "billingaddress")
    private WebElement billingAddTxtField;

    @FindBy(name = "bilingstate")
    private WebElement billingStateTxtField;

    @FindBy(name = "billingcity")
    private WebElement billingCityTxtField;

    @FindBy(name = "billingpincode")
    private WebElement billingPinCodeTxtField;

    @FindBy(xpath = "//div[@id='collapseOne']//button[.='Update']")
    private WebElement billAddUpdateBtn;


    @FindBy(xpath = "//a[contains(text(),'Shipping Address')]")
    private WebElement shippingAddTab;


    @FindBy(name = "shippingaddress")
    private WebElement shippingAddTxtField;

    @FindBy(name = "shippingstate")
    private WebElement shippingStateTxtField;

    @FindBy(name = "shippingcity")
    private WebElement shippingCityTxtField;

    @FindBy(name = "shippingpincode")
    private WebElement shippingPinCodeTxtField;

    @FindBy(xpath = "//div[@id='collapseTwo']//button[text()='Update']")
    private WebElement shippingAddUpdateBtn;



    public UserAddressPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }


    public WebElement getBillingAddTxtField() {
        return billingAddTxtField;
    }

    public WebElement getBillingStateTxtField() {
        return billingStateTxtField;
    }

    public WebElement getBillingCityTxtField() {
        return billingCityTxtField;
    }

    public WebElement getBillingPinCodeTxtField() {
        return billingPinCodeTxtField;
    }

    public WebElement getBillAddUpdateBtn() {
        return billAddUpdateBtn;
    }

    public WebElement getShippingAddTxtField() {
        return shippingAddTxtField;
    }

    public WebElement getShippingStateTxtField() {
        return shippingStateTxtField;
    }

    public WebElement getShippingCityTxtField() {
        return shippingCityTxtField;
    }

    public WebElement getShippingPinCodeTxtField() {
        return shippingPinCodeTxtField;
    }

    public WebElement getShippingAddUpdateBtn() {
        return shippingAddUpdateBtn;
    }


    //Business Library

    public void clickOnShippingAddTab(){
        shippingAddTab.click();
    }

    public String updateBillingAdd(String billingAdd, String billingState,String billingCity,String billingPincode,WebDriver driver){
        billingAddTxtField.sendKeys(billingAdd);
        billingStateTxtField.sendKeys(billingState);
        billingCityTxtField.sendKeys(billingCity);
        billingPinCodeTxtField.sendKeys(billingPincode);
        billAddUpdateBtn.click();
        String billingMessage=getAlertMessage(driver);
        acceptAlert(driver,"ok");
        return billingMessage;
    }
    public void validatingUpdateBillingAdd(String billingAdd, String billingState,String billingCity,String billingPincode,WebDriver driver,String expectedmsg){
        billingAddTxtField.sendKeys(billingAdd);
        billingStateTxtField.sendKeys(billingState);
        billingCityTxtField.sendKeys(billingCity);
        billingPinCodeTxtField.sendKeys(billingPincode);
        billAddUpdateBtn.click();
        String billingMessage=getAlertMessage(driver);
        acceptAlert(driver,"ok");
        assertTrue(billingMessage.contains(expectedmsg),"billing address is not updated successfully");
    }

    public String updateShippingAdd(String shippingAdd, String shippingState,String shippingCity,String shippingPincode,WebDriver driver){
        shippingAddTxtField.sendKeys(shippingAdd);
        shippingStateTxtField.sendKeys(shippingState);
        shippingCityTxtField.sendKeys(shippingCity);
        shippingPinCodeTxtField.sendKeys(shippingPincode);
        shippingAddUpdateBtn.click();
        String shippingMessage=getAlertMessage(driver);
        acceptAlert(driver,"ok");
        return shippingMessage;
    }
    public void validatingUpdateShippingAdd(String shippingAdd, String shippingState,String shippingCity,String shippingPincode,WebDriver driver,String expectedMsg){
        shippingAddTxtField.sendKeys(shippingAdd);
        shippingStateTxtField.sendKeys(shippingState);
        shippingCityTxtField.sendKeys(shippingCity);
        shippingPinCodeTxtField.sendKeys(shippingPincode);
        shippingAddUpdateBtn.click();
        String shippingMessage=getAlertMessage(driver);
        acceptAlert(driver,"ok");
        // here i need to use assertTrue because contains we need to use
        assertEquals(shippingMessage,expectedMsg,"shipping address is not updated ");
    }
}
