package common.utils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExtentReportManager {
    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy_MM_dd-" +
                    "HH_mm_ss");
            String formattedDateTime = currentDateTime.format(formatter);
            String fileName="test-output/ExtentReport"+formattedDateTime+".html";
            extent = new ExtentReports();
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(fileName);
            extent.attachReporter(sparkReporter);
        }
        return extent;
    }

    public static ExtentTest createTest(String testName, String description) {
        ExtentTest  test = getInstance().createTest(testName, description);
        return test;
    }

    public static void flush() {
        if (extent != null) {
            extent.flush();
        }
    }
}
