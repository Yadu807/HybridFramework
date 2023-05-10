package AutomationFramework.Commons;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Base1 {
    public WebDriver driver;
    public static ExtentReports extent;
    public ExtentTest mytest;

    /**
     * initilize the report
     */
    @BeforeSuite(groups = {"smoke","Reg","Prod","Dev"})
    public void init_setup(){
        ExtentReports extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("target/spark.html");
        extent.attachReporter(spark);
    }

    /**
     * to launch application
     */
    @Parameters("browser")
    @BeforeMethod(groups = {"smoke","Reg","Prod","Dev"})
    public void launchapp(String browser){

        switch (browser) {

            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(chromeOptions);
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
        }

            driver.navigate().to("https://books.rediff.com/");
            driver.manage().window().maximize();

    }

    /**
     * to close the application
     */

    @AfterMethod(groups = {"smoke","Reg","Prod","Dev"})
    public void teardown(ITestResult result) throws IOException {
        if (result.getStatus()==ITestResult.FAILURE){
            mytest.log(Status.FAIL,"Error occured with Exeption",result.getThrowable(),
                    MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot().replace(System.getProperty("user.dir"),"")).build());
        }
        driver.quit();
        extent.flush();

    }

    /**
     * to create screanshot and get the filepath
     * @return : the screenshot filepath
     * @throws IOException
     */
    public String getScreenshot() throws IOException {
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File screenshotAs = screenshot.getScreenshotAs(OutputType.FILE);
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-mm-yy hh-mm-ss-SS");
        String format = simpleDateFormat.format(date);
        String filePath = System.getProperty("user.dir") + "\\src\\main\\Screenshot\\test" + format + ".png";
        FileUtils.copyFile(screenshotAs, new File(filePath));

        return filePath;

    }


}
