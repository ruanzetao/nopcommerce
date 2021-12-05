package testcases;
//import common.BaseSetup;
import common.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.SignInPage;

public class DashboardTest extends BaseTest {
    private WebDriver driver;

    private DashboardPage dashboardPage;
    private SignInPage signInPage;


    @BeforeClass
    @Parameters("appURL")
    public void setupBrowser(String appURL){
        driver=getDriver();
        driver.navigate().to(appURL);
    }

    @Test(priority = 1)
    public void navigateToSignInPage() throws InterruptedException {
        dashboardPage=new DashboardPage(driver);
        dashboardPage.navigateToSignInPage();
        Thread.sleep(2000);
    }

    @AfterClass
    public void closeBrowser(){
        driver.close();
    }
}
