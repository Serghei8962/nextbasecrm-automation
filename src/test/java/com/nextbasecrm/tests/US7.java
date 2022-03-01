package com.nextbasecrm.tests;

import com.nextbasecrm.utilities.BrowserUtils;
import com.nextbasecrm.utilities.CRM_Utilities;
import com.nextbasecrm.utilities.ConfigurationReader;
import com.nextbasecrm.utilities.WebDriverFactory;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class US7 {WebDriver driver;

    @BeforeMethod
    public void setupMethod() {
        driver = WebDriverFactory.getDriver(ConfigurationReader.getProperty("browser"));
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(ConfigurationReader.getProperty("env"));
        CRM_Utilities.crm_login(driver);
    }

    @Test
    public void one_answer_for_a_poll() {
        //Writing "Programming language" in the search box and finding existing poll
        WebElement searchInput = driver.findElement(By.xpath("//input[@id='search-textbox-input']"));
        searchInput.sendKeys("Programming language" + Keys.ENTER);

        //finding "vote" button, and after clicking the vote, finding the "vote again" button locator
        WebElement voteAgainButton = driver.findElement(By.xpath("//input[@id='sessid']/..//button[.='Vote again']"));
        WebElement voteButton = driver.findElement(By.xpath("//input[@id='sessid']/..//button[.='Vote']"));
        voteAgainButton.click();
        //Location java and python answer options locator for clicking
        WebElement javaOption = driver.findElement(By.xpath("//li[@id='question1166']//label[.='Java']"));
        WebElement pythonOption = driver.findElement(By.xpath("//li[@id='question1166']//label[.='Python']"));

        //Java and python locator was not able to answer isSelected() method,
        // so I find other input locator option for java and python
        // This locator able to answer isSelected() method
        List<WebElement> IsSelectedOptions = driver.findElements(By.xpath("//input[@name='vote_radio_1166']/..//input"));

        //Java   value attribute value  =>2616
        //Python value attribute value  =>2617

        //Verifying one option is selected at a time by verifying if it matches the attribute in their locator
        for (WebElement each : IsSelectedOptions) {
            javaOption.click();
            BrowserUtils.sleep(2);
            if (each.getAttribute("value").equals("2616")) {
                Assert.assertTrue(each.isSelected());
            }
        }

        //User click vote button and see if it is selected
        try {
            voteButton.click();
        }catch (StaleElementReferenceException e){
            Assert.assertTrue(voteButton.isSelected());
        }

    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}