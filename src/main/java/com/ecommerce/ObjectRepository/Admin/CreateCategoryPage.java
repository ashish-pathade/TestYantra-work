package com.ecommerce.ObjectRepository.Admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class CreateCategoryPage {
    @FindBy(name = "category")
    private WebElement createCat;

    @FindBy(name = "description")
    private WebElement catDescription;

    @FindBy(xpath = "//button[text()='Create']")
    private WebElement createBtn;

    @FindBy(xpath = "//div[@class='alert alert-success']")
    private WebElement createCategoryCnfMsg;


    public CreateCategoryPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }

    public WebElement getCreateCat() {
        return createCat;
    }

    public WebElement getCatDescription() {
        return catDescription;
    }

    public WebElement getCreateBtn() {
        return createBtn;
    }

    public WebElement getCreateCategoryCnfMsg() {
        return createCategoryCnfMsg;
    }

    // Business Library
    public void createCategory(String categoryName,String description){
        createCat.sendKeys(categoryName);
        catDescription.sendKeys(description);
        createBtn.click();
    }
    public void validatingCreateCategory(String categoryName,String description,String expectedMsg){
        createCat.sendKeys(categoryName);
        catDescription.sendKeys(description);
        createBtn.click();
        Assert.assertTrue(createCategoryCnfMsg.getText().contains(expectedMsg),"category is not created");
    }
}
