package com.assist.prezentare.Dashboard;
import com.assist.helpers.ExtentReportManager;
import com.assist.prezentare.TestLauncher.*;
import com.aventstack.extentreports.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Dashboard extends Base{
    private String section= "Dashboard";

    @Test
    private void checkDashboardElements()
    {
        ExtentTest test = ExtentReportManager.createTest("CheckPrivaceYearIsDisplayed",
                "C23315 Check if privacy policy year is displayed").assignCategory("App access -> Footer");
        try {
            boolean yearFromPrivacyPolicyIsDisplayed =
                    driver.findElement(By.id("com.rocknaaudio.wavelight:id/tvcopyrights")).isDisplayed();

            Assert.assertTrue(yearFromPrivacyPolicyIsDisplayed,"The year from privacy policy is not visible");
        } catch (Exception e) {

            test.log(Status.FAIL, "Test failed: " + e.getMessage());
        } finally {

            ExtentReportManager.flush();
        }
    }

}
