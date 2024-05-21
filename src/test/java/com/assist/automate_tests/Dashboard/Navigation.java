package com.assist.automate_tests.dashboard;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumBy;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import common.utils.ExtentReportManager;
import java.util.List;
import com.assist.automate_tests.test_launcher.Base;
import static common.helpers.Verifyer.CheckIfSpecificElementIsNoLongerDisplayed;


public class Navigation extends Base {

    public final String  category = "Navigation";

    public String[] screensNames={"Now in Android","Saved","Interests"};

    @Test(priority=1)
    public void CheckFunctionalityOfApplicationNavigation()
    {
        ExtentTest test = ExtentReportManager.createTest("Navigation check",
                "Check if user is able to change navigate through screens").assignCategory(category);
        try {
            List<WebElement> nabigationButtons= driver.findElements(AppiumBy.xpath("//android.view.View[@resource-id=\"NiaBottomBar\"]/android.view.View/android.view.View"));

            test.log(Status.INFO, "Buttons found: " + nabigationButtons );
            test.log(Status.INFO, "NumberOfButtons: " + nabigationButtons.size() );
            int i=0;
            for(WebElement button:nabigationButtons){
                button.click();
                test.log(Status.INFO,"Elementul cautat: " + button);
                boolean noLongerDisplayed=
                        CheckIfSpecificElementIsNoLongerDisplayed(driver,"(//android.widget.TextView[@text='"+screensNames[i]+"'])[1]");
                test.log(Status.INFO,"No longerDisplayed: "+noLongerDisplayed);
                Assert.assertFalse(noLongerDisplayed,"The button "+ button + " is still displayed");
                i++;
            }
        }
        catch (Exception e)
        {
            System.out.println(Status.INFO + "Test failed: "+ e.getMessage());
        }
        finally{
            ExtentReportManager.flush();
        }
    }
}
