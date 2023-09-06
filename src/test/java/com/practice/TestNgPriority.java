package com.practice;


import com.ecommerce.GenricUtilities.BaseClass;
import org.testng.annotations.*;

public class TestNgPriority extends PracticeTestNGBase {
    @Test(groups = "smoke")
    public void createCat(){
        System.out.println("creating category");
    }
    @Test(groups = "regression")
    public void createSubcategory(){
        System.out.println("After category we can create sub category");
    }
    @Test(groups = "integration")
    public void createProduct(){
        System.out.println("After sub category we can inser the product in to it");
    }
    @Test(groups = {"integration" ,  "smoke"})
    public void purchase(){
        System.out.println("product purchased");
    }
}
