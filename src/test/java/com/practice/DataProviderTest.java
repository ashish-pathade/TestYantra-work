package com.practice;

import org.testng.annotations.Test;

public class DataProviderTest {
    @Test(dataProviderClass = DataProviderPrac.class,dataProvider = "readData")
     public void useData(String a,String b){
        System.out.println(a);
        System.out.println(b);
    }

}
