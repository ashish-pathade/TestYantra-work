package com.ecommerce.ObjectRepository.Admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AdminHomePage {
    @FindBy(xpath = "//a[contains(.,'Order Management')]")
    private WebElement orderManagementDD;

//    @FindBy(xpath = "//i[@class='menu-icon icon-group']")
//    private WebElement manageUser;
   @FindBy(xpath = "//a[contains(.,'Manage users')]")
   private WebElement manageUser;

    @FindBy(xpath = "//a[contains(.,'Create Category')]")
    private WebElement createCatLink;

    @FindBy(xpath = "//a[contains(.,'Sub Category')]")
    private WebElement createSubCatLink;

    @FindBy(xpath = "//a[contains(.,'Insert Product')]")
    private WebElement insertProductLink;

    @FindBy(xpath = "//a[contains(.,'Manage Product')]")
    private WebElement manageProductLink;


    @FindBy(xpath = "//a[contains(.,'User Login Log')]")
    private WebElement userLoginLogLink;


    @FindBy(css = ".menu-icon.icon-signout")
    private WebElement AdminLogout;


    public AdminHomePage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }


    public WebElement getOrderManagementDD() {
        return orderManagementDD;
    }

    public WebElement getManageUser() {
        return manageUser;
    }

    public WebElement getCreateCatLink() {
        return createCatLink;
    }

    public WebElement getCreateSubCatLink() {
        return createSubCatLink;
    }

    public WebElement getInsertProductLink() {
        return insertProductLink;
    }

    public WebElement getManageProductLink() {
        return manageProductLink;
    }

    public WebElement getUserLoginLogLink() {
        return userLoginLogLink;
    }

    public WebElement getAdminLogout() {
        return AdminLogout;
    }



    // Business Library
    public void clickOnCategory(){
        createCatLink.click();
    }

    public void clickOnManageUser(){
        manageUser.click();
    }

    public void clickOnLogout(){
        AdminLogout.click();
    }

}
