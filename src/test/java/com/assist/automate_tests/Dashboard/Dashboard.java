package com.assist.automate_tests.dashboard;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import common.utils.ExtentReportManager;
import com.assist.automate_tests.test_launcher.*;
import com.aventstack.extentreports.*;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static common.constant.forYouScreenConstants.*;

public class Dashboard extends Base{
    private String category= "Dashboard";

    @Test(priority = 1, groups = {"smoke"})
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

    @Test(priority = 2, groups={"smoke"})
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

    @Test(priority = 3, groups = {"smoke"})
    private void checkIfUserIsAbleToSwipeRight()
    {
        ExtentTest test = ExtentReportManager.createTest("Check if user si able to swipe",
                "Check application behavior while user is swiping through sections").assignCategory(category);
        try {

            final var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            var start = new Point(994, 968);
            var end = new Point(654, 972);
            var swipe = new Sequence(finger, 1);
            swipe.addAction(finger.createPointerMove(Duration.ofMillis(0),
                    PointerInput.Origin.viewport(), start.getX(), start.getY()));
            swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            swipe.addAction(finger.createPointerMove(Duration.ofMillis(1000),
                    PointerInput.Origin.viewport(), end.getX(), end.getY()));
            swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
            driver.perform(Arrays.asList(swipe));

        }
        catch (Exception e)
        {
            test.log(Status.FAIL, "Test failed " + e.getMessage());

        }
        finally {
            ExtentReportManager.flush();
        }
    }


}

