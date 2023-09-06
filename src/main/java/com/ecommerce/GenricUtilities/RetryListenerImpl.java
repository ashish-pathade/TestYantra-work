package com.ecommerce.GenricUtilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryListenerImpl implements IRetryAnalyzer {
    int count=0;
    int retryLimit=4;
    @Override
    public boolean retry(ITestResult iTestResult) {
        if (count<retryLimit){
            count++;
            return true;
        }
        return false;
    }
}
