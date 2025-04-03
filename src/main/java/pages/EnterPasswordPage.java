package pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EnterPasswordPage {

    private AndroidDriver driver;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").textStartsWith(\"Hi\")")
    private WebElement textTitle;

    @AndroidFindBy(xpath = "//android.widget.EditText[@hint='Enter your password']")
    private WebElement inputPassword;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Wrong password. Try again or click Forgot password to reset it.\")")
    private WebElement textErrorMessage;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"NEXT\")")
    private WebElement btnNext;

    public EnterPasswordPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    public void waitForLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(textTitle));
    }

    public void enterPassword(String email) {
        inputPassword.sendKeys(email);
    }

    public void clickNext() {
        btnNext.click();

        TermsOfServicePage termsOfServicePage = new TermsOfServicePage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(d -> {
            try {
                return termsOfServicePage.isLoaded();
            } catch (Exception e) {
                return isErrorMessageDisplayed();
            }
        });
    }

    public boolean isErrorMessageDisplayed() {
        return textErrorMessage.isDisplayed();
    }

    public String getErrorMessageText() {
        return textErrorMessage.getText();
    }
}
