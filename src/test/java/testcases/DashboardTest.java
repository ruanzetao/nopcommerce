package testcases;
import common.BaseSetup;
import common.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.SignInPage;
public class DashboardTest extends BaseSetup {
    private WebDriver driver;

    private DashboardPage dashboardPage;
    private SignInPage signInPage;

    @BeforeClass
    public void setupBrowser(){
        driver=getDriver();
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
