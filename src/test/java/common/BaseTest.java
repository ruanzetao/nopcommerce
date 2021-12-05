package common;

import common.utilities.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;
import common.browsers.BrowserFactory;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
    public static WebDriver driver;
    public static final int IMPLICIT_WAIT = Constants.IMPLICIT_WAIT;
    public static final int PAGE_LOAD_TIMEOUT  = Constants.PAGE_LOAD_TIMEOUT;

//    private static InheritableThreadLocal<WebDriver> driverInstance = new InheritableThreadLocal<>();

    public static WebDriver getDriver() {
//        if (driverInstance.get() == null) {
//            return null;
//        } else
//            return driverInstance.get();
        return driver;
    }

    public static WebDriver initChromeDriver(){
        System.out.println("Launching Chrome browser...");
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
        return driver;
    }

    public static WebDriver initFirefoxDriver(){
        System.out.println("Launching Chrome browser...");
        WebDriverManager.edgedriver().setup();
        driver=new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
        return driver;
    }

    public static WebDriver createDriver(String browserType) {
        switch (browserType.trim()) {
            case "chrome":
                System.out.println("Launching " + browserType + " browser...");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                System.out.println("Launching " + browserType + " browser...");
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
//            case "opera":
//                System.out.println("Launching " + browserType + " browser...");
//                WebDriverManager.operadriver().setup();
//                driver = new OperaDriver();
//                break;
//            case "edge":
//                System.out.println("Launching " + browserType + " browser...");
//                WebDriverManager.edgedriver().setup();
//                driver = new EdgeDriver();
//                break;
            default:
                System.out.println("Launching Chrome browser default...");
                System.out.println("Browser: " + browserType + " is invalid, Launching Chrome as browser of choice...");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
        // Set driver instance to get driver everywhere
        BrowserFactory.setDriver(driver);
        return driver;
    }

    public static void setDriver(String browserType) {
        switch (browserType) {
            case "chrome":
                driver = initChromeDriver();
                BrowserFactory.setDriver(driver);
                break;
            case "firefox":
                driver = initFirefoxDriver();
                break;
            default:
                System.out.println("Browser: " + browserType + " is invalid, Launching Chrome as browser of choice...");
                driver = initChromeDriver();
                BrowserFactory.setDriver(driver);
                break;
        }
    }

    // Hàm initBaseTestSetup sẽ chạy trước nhất trong project này khi chạy file TestNG.xml)
    @Parameters({"browserType"})
    @BeforeSuite
    public void initBaseTestSetup(String browserType) {
        try {
            // Thực thi để khởi tạo driver và browser
            setDriver(browserType);
        } catch (Exception e) {
            System.out.println("Error initialize driver..." + e.getStackTrace());
        }
    }

    @AfterSuite
    public void tearDown() {
        if(driver != null) {
            driver.quit();
        }
    }

}
