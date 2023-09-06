package com.ecommerce.ObjectRepository.Admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminLoginPage {
   @FindBy(id = "inputEmail")
    private WebElement adminUserName;

   @FindBy(id = "inputPassword")
    private WebElement adminPassword;

   @FindBy(xpath = "//button[text()='Login']")
    private WebElement loginBtn;

   public AdminLoginPage(WebDriver driver){
       PageFactory.initElements(driver,this);
   }

    public WebElement getAdminUserName() {
        return adminUserName;
    }

    public WebElement getAdminPassword() {
        return adminPassword;
    }

    public WebElement getLoginBtn() {
        return loginBtn;
    }


    // Business Library

    public void adminLogin(String AdminUserName, String AdminPassword){
       adminUserName.sendKeys(AdminUserName);
       adminPassword.sendKeys(AdminPassword);
       loginBtn.click();
    }
}
