package com.practice;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class BrokenLinksTest {
    public static void main(String[] args) throws IOException {
        WebDriver driver = new ChromeDriver();
        BrokenLinksTest br = new BrokenLinksTest();
        // 1. http://www.example.com/
        br.checkForBrokenLinks("http://www.example.com/",driver);
        // 2. http://www.testwebsite.com/login
        br.checkForBrokenLinks("http://www.testwebsite.com/login",driver);
        // 3. http://www.reddit.com
       // br.checkForBrokenLinks("http://www.reddit.com",driver);
        // 4. http://omayo.blogspot.com/
        br.checkForBrokenLinks("http://omayo.blogspot.com/",driver);
        // 5. http://www.fakebook.com/profile/user123
       br.checkForBrokenLinks("http://www.fakebook.com/profile/user123",driver);
//---------------------------------------------------------------------------------------------------------//
        // for https links
        br.checkLinks("https://www.epfindia.gov.in/",driver);
    }

    public void checkForBrokenLinks(String webUrl,WebDriver driver){

        driver.manage().window().maximize();
        // launch the application
        driver.get(webUrl);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        List<WebElement> allLinks= driver.findElements(By.xpath("//a"));
        System.out.println(allLinks.size());
        ArrayList<String> brokenLinks = new ArrayList<>();

        for (WebElement allLink : allLinks) {

            String eachLink = null;
            int statuscode = 0;
            try {
                eachLink = allLink.getAttribute("href");
                // loading the url for eachlink
                URL url = new URL(eachLink);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                // getting the status code for each link
                statuscode = httpURLConnection.getResponseCode();
                if (statuscode >= 400) {
                    brokenLinks.add(eachLink + "---->" + statuscode);
                }
            } catch (Exception e) {
                brokenLinks.add(eachLink + "---->" + statuscode);
            }
        }
        // printing all the broken links
        System.out.println(brokenLinks);
        driver.close();
    }
    public void checkLinks(String link,WebDriver driver){

        driver.manage().window().maximize();
        driver.get(link);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        List<WebElement> allLinks = driver.findElements(By.xpath("//a"));
        List<String> brokenLinks = new ArrayList<>();
        System.out.println(allLinks.size());
        //for (int i=0;i<allLinks.size();i++){
            for (int i=0; i<10;i++) {
            String oneLink=allLinks.get(i).getAttribute("href");
            int statusCode=0;
            try {
                URL url=new URL(oneLink);
                HttpsURLConnection httpsURLConnection=(HttpsURLConnection) url.openConnection();
                statusCode=httpsURLConnection.getResponseCode();
                if (statusCode<=400){
                    brokenLinks.add(oneLink+"-------->"+statusCode);
                }
            } catch (IOException e) {
                brokenLinks.add(oneLink+"-------->"+statusCode);
            }
        }
        System.out.println(brokenLinks);
    }
}
