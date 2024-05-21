package com.assist.automate_tests.settings;

import com.assist.automate_tests.test_launcher.Base;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import common.utils.ExtentReportManager;
import io.appium.java_client.AppiumBy;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Settings extends Base {
    public String section="Settings";

    @Test(priority=1, groups = {"smoke"})
    public void openAndCloseSettings(){
        ExtentTest test= ExtentReportManager.createTest("Open and close Settings panel",
                "Check if user is able to open and close Settings panel")
                .assignCategory(section);
        try {
            var settingsButton = driver.findElement(AppiumBy.xpath("//android.view.View[@resource-id=\"niaTopAppBar\"]/android.view.View[2]/android.widget.Button"));
            settingsButton.click();
            var panelHasDisplayed = driver.findElement(AppiumBy.
                    xpath("//android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.view.View")).isDisplayed();
            Assert.assertTrue(panelHasDisplayed,"Settings panel is not displayed");
                    test.log(Status.INFO,"Settings panel displayed: "+panelHasDisplayed);

            var okbutton = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"OK\"]"));
            okbutton.click();
            //Check if Settings panel has been closed.
            var mainElementIsdisplayed = driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Now in Android\"]")).isDisplayed();
            test.log(Status.INFO, "Elements under Settings panel are visible: "+mainElementIsdisplayed);
            Assert.assertTrue(mainElementIsdisplayed, "Main elements are not displayed/Settings section has not been closed");
        }
        catch(Exception e)
        {
            test.log(Status.FAIL,"Uer unable to start and stop " +e.getMessage());
        }
        finally {
            ExtentReportManager.flush();
        }
    }
}
