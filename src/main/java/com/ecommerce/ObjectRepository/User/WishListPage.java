package com.ecommerce.ObjectRepository.User;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static org.testng.Assert.*;

public class WishListPage {
    @FindBy(xpath = "//li[text()='Wishlish']")
    private WebElement pageName;

    public WishListPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public WebElement getPageName() {
        return pageName;
    }

    public void getPageText(String expectedPageName){
        assertEquals(pageName.getText(),expectedPageName,"page title is not matching");
    }
    public void fetchProductName(WebDriver driver, String linkTextValue,String expectedProductName){
       String actualProduct= driver.findElement(By.linkText(linkTextValue)).getText();
        assertTrue(actualProduct.contains(expectedProductName),"product is not added to the wishlist");
    }
}
