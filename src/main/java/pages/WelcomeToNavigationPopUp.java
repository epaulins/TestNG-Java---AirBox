package pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WelcomeToNavigationPopUp {

    private AndroidDriver driver;

    @AndroidFindBy(id = "com.google.android.apps.maps:id/navigation_welcome_title_text")
    private WebElement textTitle;


    @AndroidFindBy(id = "android:id/button1")
    private WebElement btnGotIt;

    public WelcomeToNavigationPopUp(AndroidDriver driver) {
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

    public void clickGotIt() {
        btnGotIt.click();

        NavigationDataPopUp navigationDataPopUp = new NavigationDataPopUp(driver);
        navigationDataPopUp.waitForLoaded();
    }
}
