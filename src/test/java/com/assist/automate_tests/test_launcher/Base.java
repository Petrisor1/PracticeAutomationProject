package com.assist.automate_tests.test_launcher;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import static common.constant.CommonConstants.*;

public class Base {
    public static AndroidDriver driver;
    public static  AppiumDriverLocalService service;
    @BeforeClass(description = "Start the server, connect to the emulator, C23292 add wavelight server")
    public void configureAppium() throws MalformedURLException {

        service = RunAppiumServer();
        driver = setAppiumCapabilities();
    }

    @AfterClass(description = "Log out from app")
    public void tearDown() {
        try {

            driver.quit();
            service.stop();

        }catch (Exception e)
        {
            System.out.println( "Error message"+ e.getMessage());
        }
    }

    private AppiumDriverLocalService RunAppiumServer() {
        AppiumDriverLocalService serv = new AppiumServiceBuilder()
                .withAppiumJS(new File(APPIUM_LOCAL_URL))
                .withIPAddress(APPIUM_IP)
                .usingPort(Integer.parseInt(APPIUM_SERVICE_PORT))
                .withTimeout(Duration.ofSeconds(300))
                .build();
        serv.start();
        return serv;
    }

    private AndroidDriver setAppiumCapabilities() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(DEVICE_NAME);
        options.setApp(BUILD_PATH);

        AndroidDriver drive = new AndroidDriver(new URL(URL_SERVER), options);
        drive.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        return drive;
    }
}
