package common.constant;
import common.utils.ConfigLoader;

public class CommonConstants {
    public static ConfigLoader loadConstants =new ConfigLoader("startup_data/appiumConfiguraiton.properties");
    public static final String APPIUM_LOCAL_URL= loadConstants.getProperty("service.appiumLocalPath");
    public static  final String APPIUM_SERVICE_PORT= loadConstants.getProperty("service.port");

    public static final String URL_SERVER= loadConstants.getProperty("appium.serverURL");

    public static final String DEVICE_NAME= loadConstants.getProperty("appium.deviceName");

    public static final String BUILD_PATH= loadConstants.getProperty("build.path");

    public static final String APPIUM_IP= loadConstants.getProperty("service.ip");


}
