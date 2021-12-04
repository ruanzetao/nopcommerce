package common.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class UIHelpers {
    private WebDriver driver;
    private WebDriverWait wait;
    JavascriptExecutor js;
    public final int timeoutWaitForPageLoaded=60;

    public UIHelpers(WebDriver driver){
        this.driver=driver;
        wait = new WebDriverWait(driver, 5);
        js=(JavascriptExecutor) driver;
    }

    public void setText(By element, String value){
        wait.until(ExpectedConditions.elementToBeClickable(element));
        driver.findElement(element).clear();
        driver.findElement(element).sendKeys(value);
    }

    public void clickElementByJs(By element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
        //Click co 2 truong hop
        //1 la cua Selenium
        //driver.findElement(element).click();

        //2 la cua Js
        js=(JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", driver.findElement(element));
    }

    public void waitForPageLoaded(){
        ExpectedCondition<Boolean> expectation =new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
            }};
        try{
            WebDriverWait wait=new WebDriverWait(driver, timeoutWaitForPageLoaded);
            wait.until(expectation);
        } catch(Throwable error){
            Assert.fail("Timeout waiting for Page Load request");
        }
    }

    public String getPageTitle(){
        waitForPageLoaded();
        String title =driver.getTitle();
        return title;
    }

    public boolean verifyPageTitle(String pageTitle){
        waitForPageLoaded();
        return  getPageTitle().equals(pageTitle);
    }
}
