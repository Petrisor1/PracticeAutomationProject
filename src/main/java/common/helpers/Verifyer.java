package common.helpers;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.WebElement;

import java.util.List;

public  class Verifyer {

    public static boolean CheckIfSpecificElementIsNoLongerDisplayed(AndroidDriver driver, String XpathSelector){
        List<WebElement> elementList = driver.findElements(AppiumBy.xpath(XpathSelector));
        if(elementList.isEmpty())
        {
            return true;
        }
        else  {
            return false;
        }
    }

}
