package com.assist.prezentare.TestLauncher;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
public class Base {
    public AndroidDriver driver;
    public AppiumDriverLocalService service;
    ConfigLoader data = new ConfigLoader();
    @BeforeClass(description = "Start the server, connect to the emulator, C23292 add wavelight server")
    public void configureAppium() throws MalformedURLException, InterruptedException {
        //run appium server automatically
        service=new AppiumServiceBuilder().withAppiumJS(
                        new File(data.getProperty("service.appiumLocalPath")))
                .withIPAddress(data.getProperty("service.ip"))
                .usingPort(Integer.parseInt(data.getProperty("service.port"))).withTimeout(Duration.ofSeconds(300)).build();

        service.start();
        //create capabilities
        UiAutomator2Options options= new UiAutomator2Options();
        options.setDeviceName(data.getProperty("appium.deviceName"));
        options.setApp(data.getProperty("build.path"));

        //create object for Android driver/ IOSDriver
        driver=new AndroidDriver(new URL(data.getProperty("appium.serverURL")), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));

        //Add wavelight server
        WebElement addButton=driver.findElement(By.id("com.rocknaaudio.wavelight:id/tvConnectManuallyConnect"));
        addButton.click();
        WebElement editText=driver.findElement(By.id("com.rocknaaudio.wavelight:id/etIpAddress"));
        editText.sendKeys("mosulica.go.ro:8089");
        WebElement saveButton =driver.findElement(By.id("com.rocknaaudio.wavelight:id/btnSave"));
        saveButton.click();
    }
    @AfterClass(description = "Log out from app")
    public void tearDown() {
        //LogOut Wavelight Server
        try {
            WebElement setButton = driver.findElement(By.id("com.rocknaaudio.wavelight:id/btnSettings"));
            setButton.click();
            WebElement logOut = driver.findElement(By.id("com.rocknaaudio.wavelight:id/ivlogOut"));
            logOut.click();
            WebElement okButton = driver.findElement(By.id("com.rocknaaudio.wavelight:id/tvOk"));
            okButton.click();
            driver.quit();
            service.stop();
        }catch (Exception e)
        {
            System.out.println( "Error message"+ e.getMessage());
        }
    }


}
