package com.nextbasecrm.tests;

import com.nextbasecrm.utilities.WebDriverFactory;
import org.openqa.selenium.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class US14 {
    WebDriver driver;

    @BeforeMethod
    public void setupMethod(){
        driver = WebDriverFactory.getDriver("chrome");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://login2.nextbasecrm.com/");
    }
    @DataProvider(name="T1")
    public Object[][] login_Datas(){
        return new Object[][]{
                {"hr40@cydeo.com"}, {"hr41@cydeo.com"}, {"hr42@cydeo.com"},
                {"helpdesk40@cydeo.com"}, {"helpdesk41@cydeo.com"}, {"helpdesk42@cydeo.com"},
                {"marketing40@cydeo.com"}, {"marketing41@cydeo.com"}, {"marketing42@cydeo.com"}
        };
    }
    @Test(dataProvider = "T1")
    public void user_see_3desktop_option_on_homepage(String email){

        //Sending valid email to username input
        WebElement usernameInput = driver.findElement(By.xpath("//input[@name='USER_LOGIN']"));
        usernameInput.sendKeys(email);
        JavascriptExecutor j = (JavascriptExecutor)driver;
        //Sending valid password to password input and hit the enter
        WebElement passwordInput = driver.findElement(By.xpath("//input[@name='USER_PASSWORD']"));
        passwordInput.sendKeys("UserUser"+ Keys.ENTER);
        j.executeScript("window.scrollBy(600,0)");
        WebElement CRM24_linkButton = driver.findElement(By.xpath("//a[@id='logo_24_a']"));
        CRM24_linkButton.click();
        ////div[.='Desktop client']/..//a
        List<String> expectedOptionName = new ArrayList<>(Arrays.asList("MAC OS","WINDOWS","LINUX"));
        List<String > actualOptionName = new ArrayList<>();

        j.executeScript("window.scrollBy(0,600)");
        List<WebElement> desktopOptions = driver.findElements(By.xpath("//div[@class='b24-app-block b24-app-desktop']//a"));
        for (WebElement eachOption : desktopOptions) {
            actualOptionName.add(eachOption.getText());
        }

    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
