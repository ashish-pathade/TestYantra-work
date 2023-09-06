package com.practice;

import com.ecommerce.GenricUtilities.BaseClass;
import org.testng.annotations.*;


public class TestNgOrder extends PracticeTestNGBase {
    @Test(groups = "smoke")
    public void loginTest(){
        System.out.println("*** log into app test ***");
    }
    @Test(groups = "integration")
    public void signUp(){
        System.out.println("signup done successfully");
    }


}
