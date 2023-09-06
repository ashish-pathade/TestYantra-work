package com.ecommerce.ObjectRepository.Admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class ManageUserPage {
    @FindBy(xpath = "//input[@aria-controls='DataTables_Table_0']")
    private WebElement searchBox;

    @FindBy(xpath = "//tbody[@role='alert']/tr/td[3]")
    private List<WebElement> allUserEmailId;

    public ManageUserPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public WebElement getSearchBox() {
        return searchBox;
    }

    public List<WebElement> getAllUserEmailId() {
        return allUserEmailId;
    }
    //Business Library

    public void searchForUser(String emailId) {
        searchBox.sendKeys(emailId);
        boolean flag = false;
        for (WebElement eachUserEmailId : allUserEmailId) {
            String eachEId = eachUserEmailId.getText();
            if (eachEId.equals(emailId)) {
                flag = true;
            }
        }
        Assert.assertTrue(flag, emailId + " --> user is not present on the application");
        System.out.println(emailId + " --> user is present on the application **********");
    }
}
