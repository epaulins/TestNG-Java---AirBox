package pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PreHomePage {

    private AndroidDriver driver;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Make it your map\")")
    private WebElement textTitle;


    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"SKIP\")")
    private WebElement btnSkip;

    public PreHomePage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    public void waitForLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(textTitle));
    }

    public boolean isPageDisplayed() {
        try {
            return textTitle.isDisplayed();
        } catch (NoSuchElementException e) {
            System.out.println("PreHome Page is not displayed. Continuing...");
            return false;
        }
    }

    public void clickSkip() {
        btnSkip.click();

        HomePage homePage = new HomePage(driver);
        homePage.waitForLoaded();
    }
}
