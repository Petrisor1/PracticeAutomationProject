package com.assist.automate_tests.dashboard;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import common.utils.ExtentReportManager;
import com.assist.automate_tests.test_launcher.*;
import com.aventstack.extentreports.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static common.constant.forYouScreenConstants.*;

public class Dashboard extends Base{
    private String category= "Dashboard";

    @Test(priority = 1)
    private void checkDashboardTextsAreDisplayed()
    {

        String[] textsToCheck={TITLE, SUBTITLE, EXPLANATIONS};
        ExtentTest test = ExtentReportManager.createTest("CheckPrivaceYearIsDisplayed",
                "C23315 Check if privacy policy year is displayed").assignCategory(category);
        try {
            boolean  textElementIsDisplayed;
            for(String text: textsToCheck)
            {
                textElementIsDisplayed=driver.findElement(By.xpath("//android.widget.TextView[@text='"+text+"']")).isDisplayed();;
//                 driver.findElement(By.xpath("//*[text()='"+text+"']")).isDisplayed();

//                driver.findElement(By.xpath("//*[  contains(@text, '"+text+"')]")).isDisplayed();
                Assert.assertTrue(textElementIsDisplayed,"The text '"+ text+"' is not visible");
            }

        } catch (Exception e) {
            test.log(Status.FAIL, "Test failed: " + e.getMessage());
        } finally {

            ExtentReportManager.flush();
        }
    }

    @Test(priority = 2)
    private void checkIfAllButtonsAreDisplayed()
    {
        ExtentTest test = ExtentReportManager.createTest("Check if buttons main buttons are displayed",
                "C23315 Check if privacy policy year is displayed").assignCategory(category);
        try {
            boolean navigationBarDisplayed = driver.findElement(AppiumBy.id("NiaBottomBar")).isDisplayed();
            Assert.assertTrue(navigationBarDisplayed,"The year from privacy policy is not visible");

            List<WebElement> buttonsList= driver.findElements(AppiumBy.xpath("//*[id='NiaBottomBar' and contains(@class, 'android.view.View')]"));
            for(WebElement button:buttonsList)
            {
                button.click();
            }
        } catch (Exception e) {

            test.log(Status.FAIL, "Test failed: " + e.getMessage());
        } finally {

            ExtentReportManager.flush();
        }
    }




}

