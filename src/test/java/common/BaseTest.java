package common;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    public static WebDriver driver;
    public static final int IMPLICIT_WAIT = 3000;
    public static final int PAGE_LOAD_TIMEOUT  = 3000;

    public static WebDriver getDriver(){
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
//            default:
//                System.out.println("Launching Chrome browser default...");
//                System.out.println("Browser: " + browserType + " is invalid, Launching Chrome as browser of choice...");
//                WebDriverManager.chromedriver().setup();
//                driver = new ChromeDriver();
//                break;
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
        // Set driver instance to get driver everywhere
        BrowserFactory.setDriver(driver);
        return driver;
    }

}
