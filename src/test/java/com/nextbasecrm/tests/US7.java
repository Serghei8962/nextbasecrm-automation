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
        CRM_Utilities.crm_login(driver,ConfigurationReader.getProperty("username"),"UserUser");
    }

    @Test
    public void one_answer_for_a_poll(){
        WebElement searchInput = driver.findElement(By.xpath("//input[@id='search-textbox-input']"));
        searchInput.sendKeys("Trying the poll feature with single answer"+ Keys.ENTER);

        WebElement voteOrVoteAgainButton = driver.findElement(By.xpath("//div[@id='vote-nSLjLH1036']//button[.='Vote again']"));
        WebElement voteButton = driver.findElement(By.xpath("//button[.='Vote']"));
        voteOrVoteAgainButton.click();

        BrowserUtils.sleep(2);
        List<WebElement> answerOptions = driver.findElements(By.xpath("//label[@class='bx-vote-block-input-wrap-inner']//span"));
        for (WebElement eachAnswer : answerOptions) {
                BrowserUtils.sleep(2);
                eachAnswer.click();
                if(eachAnswer.getText().equals("Python")){

                }else if(eachAnswer.getText().equals("Python")){
                    Assert.assertTrue(eachAnswer.isSelected());
                 }
            }
        BrowserUtils.sleep(2);
        voteButton.click();
        }



//        for (int i = 0 , j= answerOptions.size()-1; i < answerOptions.size(); i++ , j--) {
//            BrowserUtils.sleep(2);
//            answerOptions.get(i).click();
//            if(answerOptions.get(i).isSelected() && !(answerOptions.get(j).isSelected())){
//                Assert.assertTrue(answerOptions.get(i).isSelected(),"One option is selected");
//                break;
//            }
//        }
//        voteOrVoteAgainButton.click();

//            BrowserUtils.sleep(1);
//            Assert.assertTrue(eachAnswer.isSelected());



    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}