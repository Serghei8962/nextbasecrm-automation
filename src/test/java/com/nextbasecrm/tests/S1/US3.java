package com.nextbasecrm.tests.S1;

import com.nextbasecrm.utilities.WebDriverFactory;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.*;


import com.nextbasecrm.utilities.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

public class US3 {
    WebDriver webDriver;

    @BeforeMethod

    public void setup() {
        webDriver = WebDriverFactory.getDriver("chrome");
        webDriver.get("https://login2.nextbasecrm.com/");
    }

    @DataProvider(name = "Username")
    public Object[][] logIn_Username() {
        return new Object[][]{
                {"hr40@cydeo.com"}, {"helpdesk40@cydeo.com"}, {"marketing40@cydeo.com"},
                {"hr41@cydeo.com"}, {"helpdesk41@cydeo.com"}, {"marketing41@cydeo.com"},
                {"hr42@cydeo.com"}, {"helpdesk42@cydeo.com"}, {"marketing42@cydeo.com)"}
        };
    }

    @Test(dataProvider = "Username")
    public void all_username_method(String email) throws InterruptedException{
        //Sending valid email to username input
        WebElement usernameInput = webDriver.findElement(By.xpath("//input[@name='USER_LOGIN']"));
        usernameInput.sendKeys(email);

        //Sending valid password to password input
        WebElement userpasswordInput = webDriver.findElement(By.xpath("//input[@name='USER_PASSWORD']"));
        userpasswordInput.sendKeys("UserUser" + Keys.ENTER);
        JavascriptExecutor j = (JavascriptExecutor)webDriver;

        try {
            WebElement CRM24_HomeButton = webDriver.findElement(By.xpath("//a[@id='logo_24_a']"));
            CRM24_HomeButton.click();
        }catch (NoSuchElementException e){

        }
        j.executeScript("window.scrollBy(600,0)");


        // Locate the user Main menu on the webpage
        try {
            WebElement userMainMenu = webDriver.findElement(By.xpath("//div[@id='user-block']"));
            userMainMenu.click();
        }catch (NoSuchElementException e){

        }
        // Click the LogOut
        try {
            WebElement logOutButton = webDriver.findElement(By.xpath("//class[@href='\"/auth/?logout=yes&backurl=%2Fstream%2F']"));
            logOutButton.click();
        }catch (NoSuchElementException e){

        }


    }

    @Test
    public void logInPage() {
        // Check the web page title
        String expectedTitle = "Authorization";
        Assert.assertEquals(webDriver.getTitle(), expectedTitle);
    }
}