package com.mbbm.app.taager.webscraping;

import com.mbbm.app.taager.TaagerCredentials;
import com.mbbm.app.taager.TaagerLinks;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class TaagerSeleniumLogin {

    public void login(WebDriver driver){
        try{
            driver.get(TaagerLinks.LOGIN_PAGE);
            WebElement emailOrPhoneInput = driver.findElement(By.xpath("//input[@formcontrolname='phoneNumber']"));
            WebElement passwordInput = driver.findElement(By.xpath("//input[@formcontrolname='password']"));
            emailOrPhoneInput.sendKeys(TaagerCredentials.TAAGER_EMAIL_OR_PHONE);
            passwordInput.sendKeys(TaagerCredentials.TAAGER_PASSWORD);
            WebElement submitButtonClickable = new WebDriverWait(driver,Duration.ofMillis(2000))
                    .until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']")));
            submitButtonClickable.submit();
//            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
            new WebDriverWait(driver, Duration.ofMillis(2000)).until(
                    webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
            if(driver.getCurrentUrl().equals(TaagerLinks.HOME_PAGE)){
                TaagerSeleniumHome taagerSeleniumHome = new TaagerSeleniumHome();
                taagerSeleniumHome.manageHomePageDialog(driver);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}