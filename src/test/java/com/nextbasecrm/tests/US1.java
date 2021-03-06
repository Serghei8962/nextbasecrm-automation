package com.nextbasecrm.tests;

import com.nextbasecrm.utilities.BrowserUtils;
import com.nextbasecrm.utilities.ConfigurationReader;
import com.nextbasecrm.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class US1 {

    //Login function

    /*
    1.users go to login page
    2.Users can see the title as "Authorization"
     */

    WebDriver driver;



    @BeforeMethod
    public void setUp() {
        driver = WebDriverFactory.getDriver(ConfigurationReader.getProperty("browser"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //  1-go to login page
        driver.get(ConfigurationReader.getProperty("env"));

    }

    @AfterClass
    public void afterClass() {
        BrowserUtils.sleep(3);
        driver.close();
    }

    @Test
    public void verifyingLoginPageTitle() {
        WebElement title= driver.findElement(By.xpath("//div[.='Authorization']"));

        String expectedTitle= "Authorization";
        //String actualTitle= driver.getTitle();
        //Assert.assertEquals(actualTitle,expectedTitle);
        BrowserUtils.verifyTitle(driver,expectedTitle);

}

    @Test
    public void login_with_valid_credentials_with_enter_btn() {
        // 2-write username
        WebElement userName = driver.findElement(By.xpath("(//input[@class='login-inp'])[1]"));
       userName.sendKeys(ConfigurationReader.getProperty("username"));
        //     * 3-write password
        WebElement password = driver.findElement(By.xpath("(//input[@class='login-inp'])[2]"));
        //     * 4-click login button
       password.sendKeys(ConfigurationReader.getProperty("password")+ Keys.ENTER);


    }
    //negative scnerio
    // valid username invalid password
    @Test
    public void login_with_valid_username_invalid_password(){
        // 2-write valid username
        WebElement userName = driver.findElement(By.xpath("(//input[@class='login-inp'])[1]"));
       userName.sendKeys(ConfigurationReader.getProperty("username"));
        //  3-write invalid password
        WebElement password = driver.findElement(By.xpath("(//input[@class='login-inp'])[2]"));
        password.sendKeys("abcd");
        //  4-click login button
        WebElement loginBtn = driver.findElement(By.xpath("//input[@type='submit']"));
        BrowserUtils.sleep(3);
        loginBtn.click();
        //  5 verify error message
        String expectedErrorMessage="Incorrect username or password";
        String actualErrorMessage=driver.findElement(By.xpath("//div[@class='errortext']")).getText();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);

    }
     ///invalid username and invalid password
    @Test
    public void login_with_invalid_username_invalid_password() {
        //2.write invalid username
        WebElement userName = driver.findElement(By.xpath("(//input[@class='login-inp'])[1]"));
        userName.sendKeys("abcd123");
        //  3-write invalid password
        WebElement password = driver.findElement(By.xpath("(//input[@class='login-inp'])[2]"));
        password.sendKeys("abcd");
        //  4-click login button
        WebElement loginBtn = driver.findElement(By.xpath("//input[@type='submit']"));
        BrowserUtils.sleep(3);
        loginBtn.click();
        //  5 verify error message
        String expectedErrorMessage="Incorrect username or password";
        String actualErrorMessage=driver.findElement(By.xpath("//div[@class='errortext']")).getText();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);


    }
    //invalid username and valid password
    @Test
    public void login_with_invalid_username_valid_password() {
        //2.write invalid username
        WebElement userName = driver.findElement(By.xpath("(//input[@class='login-inp'])[1]"));
        userName.sendKeys("abcd123");
        //  3-write invalid password
        WebElement password = driver.findElement(By.xpath("(//input[@class='login-inp'])[2]"));
        password.sendKeys(ConfigurationReader.getProperty("password"));
        //  4-click login button
        WebElement loginBtn = driver.findElement(By.xpath("//input[@type='submit']"));
        BrowserUtils.sleep(3);
        loginBtn.click();
        //  5 verify error message
        String expectedErrorMessage="Incorrect username or password";
        String actualErrorMessage=driver.findElement(By.xpath("//div[@class='errortext']")).getText();
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);


    }
}
