package com.mbbm.app.taager.webscraping;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TaagerSeleniumHome {

    public void manageHomePageDialog(WebDriver driver){
        boolean welcomeDialogDisplayed = driver.findElement(By.className("dialog")).isDisplayed();
        //check if dialog is present
        if(welcomeDialogDisplayed){
            driver = dismissWelcomeDialog(driver);
            goToProductsSection(driver);
        }else{
            System.out.println(driver.getCurrentUrl());
        }
    }

    public WebDriver dismissWelcomeDialog(WebDriver driver){
        //get start button and click on it
        WebElement startButton = driver.findElement(By.className("dialog__action-btn"));
        startButton.click();
        driver.manage().window().maximize();
        JavascriptExecutor javascriptExecutor =(JavascriptExecutor)driver;

        try{
            //click on all onboarding tool tip dialogs
            boolean onBoardingNextStepToolTipElementDisplayed = driver.findElement(By.tagName("app-onboarding-next-step-tooltip")).isDisplayed();
            do{
                WebElement onBoardingNextStepToolTipElement = new WebDriverWait(driver, Duration.ofMillis(5000))
                        .until(ExpectedConditions.visibilityOfElementLocated(By.tagName("app-onboarding-next-step-tooltip")));
                if(onBoardingNextStepToolTipElement != null){
                    //get next button and click on it
                    WebElement nextButtonClickable = new WebDriverWait(driver, Duration.ofMillis(2000))
                            .until(ExpectedConditions.elementToBeClickable(By.className("wrapper__footer__button")));

                    //get x,y axises of element (go to element location on the browser)
                    javascriptExecutor.executeScript("window.scrollTo(0,"+onBoardingNextStepToolTipElement.getLocation().x+")");
                    javascriptExecutor.executeScript("window.scrollTo(0,"+onBoardingNextStepToolTipElement.getLocation().y+")");

                    nextButtonClickable.click();
                }
                onBoardingNextStepToolTipElementDisplayed = driver.findElement(By.tagName("app-onboarding-next-step-tooltip")).isDisplayed();
            }while(onBoardingNextStepToolTipElementDisplayed);


            boolean onBoardingLastStepToolTipElementDisplayed = driver.findElement(By.tagName("app-onboarding-last-step-dialog")).isDisplayed();
            if(onBoardingLastStepToolTipElementDisplayed){
                WebElement onBoardingLastStepToolTipElement = driver.findElement(By.tagName("app-onboarding-last-step-dialog"));
                if(onBoardingLastStepToolTipElement != null){
                    //get next button and click on it
                    WebElement backToHomePageButton = new WebDriverWait(driver, Duration.ofMillis(2000))
                            .until(ExpectedConditions.elementToBeClickable(By.className("wrapper__section-two__button")));

                    javascriptExecutor.executeScript("window.scrollTo(0,"+backToHomePageButton.getLocation().x+")");
                    javascriptExecutor.executeScript("window.scrollTo(0,"+backToHomePageButton.getLocation().y+")");
                    backToHomePageButton.click();
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return driver;
    }

    public void goToProductsSection(WebDriver driver){
        System.out.println("in Hoooooooome products");
    }
}
