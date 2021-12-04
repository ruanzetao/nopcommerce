package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import common.helpers.UIHelpers;
public class DashboardPage {
    public WebDriver driver;

    public DashboardPage(WebDriver driver){
        this.driver=driver;
    }

    private By btnLogin=By.xpath("//a[@class='ico-login']");
    //a[@class='ico-login']

    public SignInPage navigateToSignInPage(){
        driver.findElement(btnLogin).click();
        return new SignInPage(driver);
    }
}
