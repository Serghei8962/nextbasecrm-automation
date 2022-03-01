package com.nextbasecrm.utilities;

/*
In this class only general utility methods that are not related to some specific page.
 */

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.Set;

public class Browser_Utiles {
    public static void switchWindowandVerify(WebDriver driver, String expectedInURL, String expectedInTitle) {

        Set<String> allWindowHandles = driver.getWindowHandles();
        for (String each : allWindowHandles) {
            driver.switchTo().window(each);

            System.out.println("Current URL: " + driver.getCurrentUrl());

            if (driver.getCurrentUrl().contains(expectedInURL)) {
                break;
            }
        }

        String actualTitle = driver.getTitle();


        Assert.assertTrue(actualTitle.contains(expectedInTitle));
    }

    public static void verifyTitle(WebDriver driver, String expectedTitle){
        Assert.assertEquals(driver.getTitle(),expectedTitle);
    }
}
