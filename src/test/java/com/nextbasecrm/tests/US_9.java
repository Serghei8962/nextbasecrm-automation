package com.nextbasecrm.tests;

import com.nextbasecrm.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class US_9 {
    WebDriver driver;


    @BeforeClass
    public void setupMethod() {
        driver = WebDriverFactory.getDriver("chrome");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://login2.nextbasecrm.com/");
    }

    @Test
    public void StreamActivity() throws InterruptedException {


        WebElement login = driver.findElement(By.xpath("//input[@type='text']"));

        login.sendKeys("hr40@cydeo.com");

        WebElement password = driver.findElement(By.xpath("//input[@type='password']"));

        password.sendKeys("UserUser");

        WebElement submit = driver.findElement(By.xpath("//input[@type='submit']"));
        submit.click();


        List<WebElement> modules = driver.findElements(By.xpath("//span[@class='menu-item-link-text']"));

        List<String> expectedTittles = new ArrayList<>(Arrays.asList("Portal", "Site map", "Chat and Calls", "Workgroups and projects", "Site map", "Site map", "Contact Center", "Absence Chart", "Company Structure", "Meeting Rooms", "Company"));

        List<String> actualTittles = new ArrayList<>();
        for (int i = 0; i < modules.size() - 2; i++) {
            try {
                if (i==6){
                    continue;
                }
                modules.get(i).click();
              // actualTittles.add(driver.getTitle());
                System.out.println("driver.getTitle() = " + driver.getTitle());

            } catch (StaleElementReferenceException e) {


                }

            }

          Assert.assertEquals(actualTittles,expectedTittles,"//Test Failed!");
            System.out.println("actualTittles = " + actualTittles);
            System.out.println("expectedTittles = " + expectedTittles);
        }
    }



