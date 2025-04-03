package pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LocationCard {

    private AndroidDriver driver;

    @AndroidFindBy(id = "com.google.android.apps.maps:id/place_page_view")
    private WebElement containerLocation;


    @AndroidFindBy(xpath =
            "//*[@resource-id='com.google.android.apps.maps:id/place_page_view']" +
            "//android.widget.TextView[@content-desc]"
    )
    private WebElement textTitle;

    @AndroidFindBy(uiAutomator = "new UiSelector().descriptionStartsWith(\"Directions to\")")
    private WebElement btnDirections;

    public LocationCard(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    public void waitForLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(containerLocation));
    }

    public String getTitleText() {
        return textTitle.getText();
    }

    public void clickDirections() {
        btnDirections.click();

        EnterRoutePage enterRoutePage = new EnterRoutePage(driver);
        enterRoutePage.waitForLoaded();
    }
}

