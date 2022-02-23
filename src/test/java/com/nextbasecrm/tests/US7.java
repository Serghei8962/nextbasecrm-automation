package com.nextbasecrm.tests;

import com.nextbasecrm.utilities.WebDriverFactory;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class US7 {WebDriver driver;

    @BeforeMethod
    public void setupMethod() {
        driver = WebDriverFactory.getDriver("chrome");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("https://login2.nextbasecrm.com/");
    }
    @DataProvider(name = "T1")
    public Object[][] login_Datas() {
        return new Object[][]{
                {"hr40@cydeo.com"}, {"hr41@cydeo.com"}, {"hr42@cydeo.com"},
                {"helpdesk40@cydeo.com"}, {"helpdesk41@cydeo.com"}, {"helpdesk42@cydeo.com"},
                {"marketing40@cydeo.com"}, {"marketing41@cydeo.com"}, {"marketing42@cydeo.com"}
        };
    }
    @Test(dataProvider = "T1")
    public void one_answer_for_a_poll(String email) throws InterruptedException {
        //Sending valid email to username input
        WebElement usernameInput = driver.findElement(By.xpath("//input[@name='USER_LOGIN']"));
        usernameInput.sendKeys(email);

        //Sending valid password to password input and hit the enter
        WebElement passwordInput = driver.findElement(By.xpath("//input[@name='USER_PASSWORD']"));
        passwordInput.sendKeys("UserUser" + Keys.ENTER);

        //Users click the one answer for a poll
        //User should be able to click one answer
        WebElement pollButton = driver.findElement(By.xpath("(//span[.='Poll'])[2]"));
        pollButton.click();
        WebElement searchInput = driver.findElement(By.xpath("//input[@id='search-textbox-input']"));
        searchInput.sendKeys("Trying the poll feature with single answer"+ Keys.ENTER);

        WebElement voteOption1 = driver.findElement(By.xpath("//*[@id='question1077']//label[.='Python']"));
        WebElement voteOption2 = driver.findElement(By.xpath("//*[@id='question1077']/table/tbody//label[.='Java']"));
        voteOption1.click();                                  //label[@for='vote_radio_1077_2435'][.='Java']"
        Assert.assertTrue(!voteOption2.isSelected());

        //Verify one option is selected/user voted

        System.out.println(voteOption1.getText());

        //User click the “VOTE” button to vote for a poll.
        WebElement voteButton = driver.findElement(By.xpath("//div[@id='blg-post-5860']//button[@class='ui-btn ui-btn-lg ui-btn-primary']"));
        WebElement voteAgainButton = driver.findElement(By.xpath("//div[@id='vote-nSLjLH1036']//button[.='Vote again']"));
        try {
            voteButton.click();
        }catch (ElementNotInteractableException e){
            voteAgainButton.click();
        }catch (StaleElementReferenceException e){
            voteButton.click();
        }
    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}