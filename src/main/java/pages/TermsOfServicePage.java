package pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TermsOfServicePage {

    private AndroidDriver driver;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Google Terms of Service\")")
    private WebElement textTitle;


    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"I agree\")")
    private WebElement btnIAgree;

    public TermsOfServicePage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    public void waitForLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(textTitle));
    }

    public boolean isLoaded() {
        return textTitle.isDisplayed();
    }

    public void clickIAgree() {
        btnIAgree.click();

        GoogleServicesPage googleServicesPage = new GoogleServicesPage(driver);
        googleServicesPage.waitForLoaded();
    }
}
