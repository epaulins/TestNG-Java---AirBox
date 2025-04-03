package pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EnterEmailPage {

    private AndroidDriver driver;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Sign in\")")
    private WebElement textTitle;

    @AndroidFindBy(xpath = "//android.widget.EditText[@hint='Email or phone']")
    private WebElement inputEmail;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"NEXT\")")
    private WebElement btnNext;

    public EnterEmailPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(30)), this);
    }

    public void waitForLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.visibilityOf(textTitle));
    }

    public void enterEmail(String email) {
        inputEmail.sendKeys(email);
    }

    public void clickNext() {
        btnNext.click();

        EnterPasswordPage enterPasswordPage = new EnterPasswordPage(driver);
        enterPasswordPage.waitForLoaded();
    }
}
