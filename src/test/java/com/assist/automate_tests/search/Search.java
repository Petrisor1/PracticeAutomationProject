package com.assist.automate_tests.search;
import com.assist.automate_tests.test_launcher.Base;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import common.helpers.Actions;
import common.utils.ExtentReportManager;
import io.appium.java_client.AppiumBy;
import org.testng.Assert;
import org.testng.annotations.Test;
import common.helpers.Actions.*;
public class Search extends Base {
    private String section="Search";

    @Test(priority = 1, groups = {"regression"})
    public void CheckIfUserIsAbleToSearchAtopic() {
        ExtentTest test = ExtentReportManager.createTest("Check search functionality",
                "Check if user is able to search for a specific topic")
                .assignCategory(section);
        try {

        var searchIcon  = driver.findElement(AppiumBy.xpath("//android.view.View[@resource-id=\"niaTopAppBar\"]/android.view.View[1]/android.widget.Button"));
        searchIcon.click();
        var inputSearchField = driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id='searchTextField']"));
        //clear input values
        inputSearchField.clear();
        inputSearchField.sendKeys("Watch");

        Actions.PressAtCoordinates(1011,2053, driver);
        boolean searchResults = driver.findElement(AppiumBy.xpath("//android.view.View[@resource-id=\"search:newsResources\"]/android.view.View")).isDisplayed();
        Assert.assertTrue(searchResults, "No results are displayed");
        test.log(Status.INFO, "Results are displayed: "+ searchResults );

    } catch(Exception e)
    {
        test.log(Status.FAIL,"User is not able to perform a search"+ e.getMessage());
    } finally {
            ExtentReportManager.flush();
        }
    }

    @Test(priority = 2, groups = {"regression"})
    public void checkIfRecentSearchesAreDisplayed() {
        ExtentTest test = ExtentReportManager.createTest("Recent searches are displayed",
                "Check if user is able to search for a specific section")
                .assignCategory(section);
        try {

            var inputSearchField = driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id='searchTextField']"));
            //clear input values
            inputSearchField.clear();
            inputSearchField.sendKeys("");
            Thread.sleep(500);
            test.log(Status.INFO, "Search input displayed: "+ inputSearchField.isDisplayed());
            var recentSearch =driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Watch\"]")).isDisplayed();
            Assert.assertTrue(recentSearch, "The recent search was not registered");
            test.log(Status.INFO, "Recent search state: "+ recentSearch);

        } catch(Exception e)
        {
            test.log(Status.FAIL,"Recent searches are not displayed"+ e.getMessage());
        } finally {
            ExtentReportManager.flush();
        }
    }

    @Test(priority = 3)
    public void noResultsWork(){
        ExtentTest test = ExtentReportManager.createTest("User able to navigate through tests",
                "Check if user is able to search for a specific section")
                .assignCategory(section);
        try {
            var inputSearchField = driver.findElement(AppiumBy.xpath("//android.widget.EditText[@resource-id='searchTextField']"));
            //clear input values
            inputSearchField.sendKeys("");
            inputSearchField.sendKeys("OZN and Alines");

            var infoForUserDispalyed=driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Try another search or explorer Interests to browse topics\"]")).isDisplayed();
            Assert.assertTrue(infoForUserDispalyed, "No results info for user is not displayed");
            test.log(Status.INFO,"No results for user displayed: "+ infoForUserDispalyed);
        } catch(Exception e)
        {
            test.log(Status.FAIL,"User is not informed at all");
        } finally {
            ExtentReportManager.flush();
        }
    }
}
