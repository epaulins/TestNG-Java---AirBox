package pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NavigationPage {

    private AndroidDriver driver;

    @AndroidFindBy(id = "com.google.android.apps.maps:id/top_cue_text")
    private WebElement textNextDirection;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Rerouting...\")")
    private WebElement textRerouting;

    @AndroidFindBy(id = "com.google.android.apps.maps:id/navigation_time_remaining_label")
    private WebElement textTimeRemaining;

    public NavigationPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    public void waitForLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(textTimeRemaining));
    }

    public boolean isLoaded() {
        return textTimeRemaining.isDisplayed();
    }

    public String getNextDirection() {
        return textNextDirection.getText();
    }

    public String getTimeRemaining() {
        return textTimeRemaining.getText();
    }

    public boolean isNextDirectionDisplayed() {
        return textNextDirection.isDisplayed();
    }

    public boolean isTimeRemainingDisplayed() {
        return textTimeRemaining.isDisplayed();
    }

    public boolean isReroutingDisplayed() {
        return textRerouting.isDisplayed();
    }
}
