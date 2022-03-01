package com.nextbasecrm.tests;


import com.nextbasecrm.utilities.CRM_Utilities;
import com.nextbasecrm.utilities.ConfigurationReader;
import com.nextbasecrm.utilities.WebDriverFactory;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class US14 {
    WebDriver driver;

    @BeforeMethod
    public void setupMethod() {
        driver = WebDriverFactory.getDriver(ConfigurationReader.getProperty("browser"));
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(ConfigurationReader.getProperty("env"));
        CRM_Utilities.crm_login(driver, ConfigurationReader.getProperty("username"), "UserUser");
    }

    @Test
    public void user_see_3desktop_option_on_homepage() {
        //finding CRM button to reflesh the page so it can take me the homepage without issue
        WebElement CRM24_linkButton = driver.findElement(By.xpath("//a[@id='logo_24_a']"));
        CRM24_linkButton.click();

        //expected options names that user should see on the homepage
        List<String> expectedOptionName = new ArrayList<>(Arrays.asList("MAC OS", "WINDOWS", "LINUX"));

        //getting actual options names from browser
        List<String> actualOptionName = new ArrayList<>();
        List<WebElement> desktopOptions = driver.findElements(By.xpath("//div[@class='b24-app-block b24-app-desktop']//a"));
        for (WebElement eachOption : desktopOptions) {
            actualOptionName.add(eachOption.getText());
        }

        //comparing the expected and actual names
        Assert.assertEquals(actualOptionName, expectedOptionName, "User cannot see 3 desktop options");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
