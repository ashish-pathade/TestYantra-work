package com.practice;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderPrac {
    @DataProvider
    public Object[][] readData(){
     Object[][] data = new Object[3][2];
     data [0][0]="abc";
     data [1][0]= "def";
     data [2][0] = "ijk";
     data [0][1] = "lmn";
     data [1][1] = "opq";
     data [2][1] = "rst";
        return data;
    }
}
