package com.ecommerce.GenricUtilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.formula.functions.Even;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.File;
import java.io.IOException;

public class ListenerImpl implements ITestListener {
    public  ExtentReports reports;
    ExtentTest extentTest;
    @Override
    public void onTestStart(ITestResult result) {
        String methodName= result.getMethod().getMethodName();
        extentTest=reports.createTest(methodName);
        Reporter.log(methodName+"--> test starts");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
       String methodName= result.getMethod().getMethodName();
       extentTest.log(Status.PASS,"");
       Reporter.log(methodName+"--> pass successfully");


    }

    @Override
    public void onTestFailure(ITestResult result) {
        String methodename = result.getMethod().getMethodName();

        TakesScreenshot ts = (TakesScreenshot) BaseClass.driver;
       // EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(BaseClass.driver);
        File src =ts.getScreenshotAs(OutputType.FILE);
        File trg = new File("./ScreenShot/Failed_Test_cases/failed2.png");
        try {
            FileUtils.copyFile(src,trg);
        } catch (IOException e) {
        }//C:\Users\ASHISH\Desktop\com.ecommerce.tyss\ScreenShot
        // .. we have to give for screenshot
         extentTest.addScreenCaptureFromPath("../ScreenShot/Failed_Test_cases/failed2.png");
       // extentTest.log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromPath("c:/Users/ASHISH/Desktop/com.ecommerce.tyss/ScreenShot/Failed_Test_cases/failed2.png").build());
        //extentTest.log(Status.INFO,"screenshot"+extentTest.addScreenCaptureFromPath("c:/Users/ASHISH/Desktop/com.ecommerce.tyss/ScreenShot/Failed_Test_cases/failed2.png"));
        extentTest.log(Status.FAIL,methodename+"--> is failed");
        extentTest.log(Status.FAIL,result.getThrowable());
        Reporter.log(methodename+"--> test case Got failed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String methodName= result.getMethod().getMethodName();
        extentTest.log(Status.SKIP,"Test skipped");
        extentTest.log(Status.SKIP,result.getThrowable());
        Reporter.log(methodName+"--> is skipped");

    }

    @Override
    public void onStart(ITestContext context) {
        ExtentSparkReporter htmlReport = new ExtentSparkReporter("./Extent-Report/report.html");
        htmlReport.config().setDocumentTitle("Online shopping Application");
        htmlReport.config().setTheme(Theme.STANDARD);
        htmlReport.config().setReportName("BuyKart");
        reports = new ExtentReports();
        reports.attachReporter(htmlReport);
        reports.setSystemInfo("OS","WINDOWS");
        reports.setSystemInfo("BROWSER-VERSION","CHROME");
        reports.setSystemInfo("BASE-URL","http://rmgtestingserver/domain/Online_Shopping_Application/");
        reports.setSystemInfo("REPORTER-NAME","ASHISH");

    }

    @Override
    public void onFinish(ITestContext context) {
        reports.flush();
        Reporter.log("Test finish");

    }
}
