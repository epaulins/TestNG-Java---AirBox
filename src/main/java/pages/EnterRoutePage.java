package pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EnterRoutePage {

    private AndroidDriver driver;

    @AndroidFindBy(xpath = "//*[@content-desc='Choose start location'] | //*[contains(@content-desc, 'Start location')]")
    private WebElement inputOrigin;

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"Start driving navigation\")")
    private WebElement btnStart;

    public EnterRoutePage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    public void waitForLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(inputOrigin));
    }

    public void clickOrigin() {
        inputOrigin.click();

        SearchPage searchPage = new SearchPage(driver);
        searchPage.waitForLoaded();
    }

    public void clickStart() {
        btnStart.click();

        WelcomeToNavigationPopUp welcomeToNavigationPopUp = new WelcomeToNavigationPopUp(driver);
        NavigationPage navigationPage = new NavigationPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(d -> {
            try {
                return welcomeToNavigationPopUp.isLoaded();
            } catch (Exception e) {
                return navigationPage.isLoaded();
            }
        });
    }
}
