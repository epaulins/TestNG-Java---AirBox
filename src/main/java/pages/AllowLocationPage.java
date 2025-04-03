package pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AllowLocationPage {

    private AndroidDriver driver;

    @AndroidFindBy(id = "com.android.permissioncontroller:id/permission_message")
    private WebElement textTitle;


    @AndroidFindBy(id = "com.android.permissioncontroller:id/permission_allow_foreground_only_button")
    private WebElement btnWhileUsingTheApp;

    public AllowLocationPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    public void waitForLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(textTitle));
    }

    public void allowWhileUsingTheApp() {
        btnWhileUsingTheApp.click();

        LocationAccuracyPage locationAccuracyPage = new LocationAccuracyPage(driver);
        locationAccuracyPage.waitForLoaded();
    }
}
