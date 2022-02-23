package com.nextbasecrm.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CRM_Utilities {

public WebDriver driver;

    public static void crm_login(WebDriver driver,String username, String password) {


        WebElement inputUserName = driver.findElement(By.xpath("//input[@name='USER_LOGIN']"));
        inputUserName.sendKeys(username);

        WebElement inputPassword = driver.findElement(By.xpath("//input[@name='USER_PASSWORD']"));
        inputPassword.sendKeys(password);

        WebElement loginBtb = driver.findElement(By.xpath("//input[@class= 'login-btn']"));
        loginBtb.click();

    }




    }

