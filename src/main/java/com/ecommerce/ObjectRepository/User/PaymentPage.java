package com.ecommerce.ObjectRepository.User;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.Map;

public class PaymentPage {
  //  @FindBys({@FindBy(name = "paymethod")},{@FindBy(xpath = "//*[@value='COD']")})
    @FindBy(xpath = "//*[@value='COD']")
    private WebElement CODRadioBtn;

    @FindBy(xpath = "//*[@value='Internet Banking']")
    private WebElement internetBankingRadioBtn;

    @FindBy(xpath = "//*[@value='Debit / Credit card']")
    private WebElement ccAndDcRadioBtn;

    @FindBy(xpath = "//input[contains(@class,'btn-primary')]")
    private WebElement paymentSubmitBtn;

    @FindBy(name = "payment")
    private WebElement allPaymentMtd;

    public PaymentPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public WebElement getCODRadioBtn() {
        return CODRadioBtn;
    }

    public WebElement getInternetBankingRadioBtn() {
        return internetBankingRadioBtn;
    }

    public WebElement getCcAndDcRadioBtn() {
        return ccAndDcRadioBtn;
    }

    public WebElement getPaymentSubmitBtn() {
        return paymentSubmitBtn;
    }

    public WebElement getAllPaymentMtd(){ return allPaymentMtd;}

    // business Library
    public void selectCOD(){
        CODRadioBtn.click();
        paymentSubmitBtn.click();
    }
    public void selectCCandDC(){
        ccAndDcRadioBtn.click();
        paymentSubmitBtn.click();
    }
    public void selectInternetBanking(){
        internetBankingRadioBtn.click();
        paymentSubmitBtn.click();
    }

    public String getAllPaymentMtdName(){
       return allPaymentMtd.getText();
    }
    public void validatingPaymentMod(Map<String,String> paymentMod){
        for (Map.Entry<String,String> eachPayment:paymentMod.entrySet()) {
            Assert.assertTrue(allPaymentMtd.getText().contains(eachPayment.getValue()));
        }

    }
}
