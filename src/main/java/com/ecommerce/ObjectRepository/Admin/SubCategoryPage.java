package com.ecommerce.ObjectRepository.Admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SubCategoryPage {
    @FindBy(xpath = "//select[@name='category']")
    private WebElement selectCatDD;

    @FindBy(xpath = "//input[@name='subcategory']")
    private WebElement subCatName;

    @FindBy(xpath = "//button[text()='Create']")
    private WebElement createBtn;


    public SubCategoryPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }


    public WebElement getSelectCatDD() {
        return selectCatDD;
    }

    public WebElement getSubCatName() {
        return subCatName;
    }

    public WebElement getCreateBtn() {
        return createBtn;
    }
}
