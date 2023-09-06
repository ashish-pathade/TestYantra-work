package com.ecommerce.ObjectRepository.User;

import com.ecommerce.GenricUtilities.JavaUtility;
import com.ecommerce.GenricUtilities.WebDriverUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;
import java.util.Map;

public class CustomerLoginPage extends WebDriverUtility {


    //Declaration
    @FindBy(name = "email")
    private WebElement userName;

    @FindBy(id = "exampleInputPassword1")
    private WebElement password;

    @FindBy(xpath="//button[text()='Login']")
    private WebElement loginBtn;
// not created getter
    @FindBy(xpath = "//button[text()='Sign Up']")
    private WebElement signupBtn;


    // initialization
    public CustomerLoginPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    //Utilization
    public WebElement getUserName() {
        return userName;
    }

    public WebElement getPassword() {
        return password;
    }

    public WebElement getLoginBtn() {
        return loginBtn;
    }


    //Business Library

    public void customerLogin(String CustomerUserName,String CustomerPwd){
        userName.sendKeys(CustomerUserName);
        password.sendKeys(CustomerPwd);
        loginBtn.click();

    }
    public void validatingCustomerLogin(WebDriver driver,String expectedTitle){
        SoftAssert sa = new SoftAssert();
        sa.assertEquals(driver.getTitle(),expectedTitle);
        sa.assertAll();
    }
    public String signUp(Map<String,String> signupData, WebDriver driver, JavaUtility jLib) throws InterruptedException {
        int ran = jLib.getRandomNum();
        String emailId=null;
        for (Map.Entry<String,String>eachData:signupData.entrySet()) {
            if (eachData.getKey().equalsIgnoreCase("email")) {
                emailId=eachData.getValue()+ran;
                driver.findElement(By.id(eachData.getKey())).sendKeys(emailId);
            } else {
                driver.findElement(By.id(eachData.getKey())).sendKeys(eachData.getValue());
                Thread.sleep(2000);
            }
        }
        signupBtn.click();
        acceptAlert(driver,"ok");
        return emailId;
    }
    public String signUpByAltMsg(Map<String,String> signupData, WebDriver driver, JavaUtility jLib) {
        int ran = jLib.getRandomNum();
        String emailId = null;
        for (Map.Entry<String, String> eachData : signupData.entrySet()) {
            if (eachData.getKey().equalsIgnoreCase("email")) {
                emailId = eachData.getValue() + ran;
                driver.findElement(By.id(eachData.getKey())).sendKeys(emailId);
            } else {
                driver.findElement(By.id(eachData.getKey())).sendKeys(eachData.getValue());
            }
        }
        signupBtn.click();
        String msg=getAlertMessage(driver);
        acceptAlert(driver,"ok");
        return msg;
    }
        public void validatingSignUpByAltMsg( WebDriver driver,Map<String,String> signupData, JavaUtility jLib,String expectedSignupMsg) {
            int ran = jLib.getRandomNum();
            String emailId = null;
            for (Map.Entry<String, String> eachData : signupData.entrySet()) {
                if (eachData.getKey().equalsIgnoreCase("email")) {
                    emailId = eachData.getValue() + ran;
                    driver.findElement(By.id(eachData.getKey())).sendKeys(emailId);
                } else {
                    driver.findElement(By.id(eachData.getKey())).sendKeys(eachData.getValue());
                }
            }
            signupBtn.click();
            String msg=getAlertMessage(driver);
            acceptAlert(driver,"ok");
            Assert.assertEquals(msg,expectedSignupMsg,"User is not able to signUp successfully");

        }
}
