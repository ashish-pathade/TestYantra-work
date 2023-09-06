package com.practice;

import org.testng.annotations.*;

public class PracticeTestNGBase {
    @BeforeSuite
    public void createDBConnection(){
        System.out.println("db connection created");
    }

    @BeforeClass
    public void launchBrowser(){
        System.out.println("launching the browser");
    }

    @BeforeMethod
    public void login(){
        System.out.println("login to app");
    }

    @AfterMethod
    public void logout(){
        System.out.println("logout to app");
    }
    @AfterClass
    public void closeBrowser(){
        System.out.println("close browser");
    }
    @AfterSuite
    public void closeDBConnection(){
        System.out.println("close DB");
    }
}
