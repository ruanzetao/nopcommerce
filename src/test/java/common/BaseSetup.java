//package common;
//
//import io.github.bonigarcia.wdm.WebDriverManager;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.testng.annotations.AfterClass;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Parameters;
//
//import java.util.concurrent.TimeUnit;
//
//public class BaseSetup {
//    public WebDriver driver;
//
//    public WebDriver getDriver() {
//        return driver;
//    }
//
//    //Hàm này để tùy chọn Browser. Cho chạy trước khi gọi class này (BeforeClass)
//    private void setDriver(String browserType, String appURL) {
//        switch (browserType) {
//            case "chrome":
//                driver = initChromeDriver(appURL);
//                break;
//            case "firefox":
//                driver = initFirefoxDriver(appURL);
//                break;
//            default:
//                System.out.println("Browser: " + browserType + " is invalid, Launching Chrome as browser of choice...");
//                driver = initChromeDriver(appURL);
//        }
//    }
//
//
//    //Khởi tạo cấu hình của các Browser để đưa vào Switch Case
//
//    private WebDriver initChromeDriver(String appURL) {
//        System.out.println("Launching Chrome browser...");
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        driver.navigate().to(appURL);
//        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
//        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//        return driver;
//    }
//
//    private WebDriver initFirefoxDriver(String appURL) {
//        System.out.println("Launching Firefox browser...");
//        WebDriverManager.firefoxdriver().setup();
//        WebDriver driver = new FirefoxDriver();
//        driver.manage().window().maximize();
//        driver.navigate().to(appURL);
//        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
//        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//        return driver;
//    }
//
//    // Chạy hàm initializeTestBaseSetup trước hết khi class này được gọi
//    @Parameters({ "browserType", "appURL" })
//    @BeforeClass
//    public void initializeTestBaseSetup(String browserType, String appURL) {
//        try {
//            // Khởi tạo driver và browser
//            setDriver(browserType, appURL);
//        } catch (Exception e) {
//            System.out.println("Error..." + e.getStackTrace());
//        }
//    }
//
//    @AfterClass
//    public void tearDown() throws Exception {
//        Thread.sleep(2000);
//        driver.quit();
//    }
//}
