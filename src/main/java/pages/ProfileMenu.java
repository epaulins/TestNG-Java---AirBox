package pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfileMenu {

    private AndroidDriver driver;

    @AndroidFindBy(id = "com.google.android.apps.maps:id/og_header_container")
    private WebElement imgGoogleLogo;

    @AndroidFindBy(id = "com.google.android.apps.maps:id/sign_in_button")
    private WebElement btnSignIn;

    @AndroidFindBy(id = "com.google.android.apps.maps:id/account_avatar")
    private WebElement imgAccountAvatar;

    @AndroidFindBy(id = "com.google.android.apps.maps:id/og_primary_account_information")
    private WebElement textAccountName;

    @AndroidFindBy(id = "com.google.android.apps.maps:id/og_secondary_account_information")
    private WebElement textAccountEmail;

    @AndroidFindBy(accessibility = "Close")
    private WebElement btnClose;

    public ProfileMenu(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    public void waitForLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(imgGoogleLogo));
    }

    public boolean isAvatarDisplayed() {
        return imgAccountAvatar.isDisplayed();
    }

    public boolean isNameDisplayed() {
        return textAccountName.isDisplayed();
    }

    public boolean isEmailDisplayed() {
        return textAccountEmail.isDisplayed();
    }

    public String  getAccountName() {
        return textAccountName.getText();
    }

    public String  getAccountEmail() {
        return textAccountEmail.getText();
    }

    public void clickSignIn() {
        btnSignIn.click();
        EnterEmailPage enterEmailPage = new EnterEmailPage(driver);
        enterEmailPage.waitForLoaded();
    }

    public void clickClose() {
        btnClose.click();
    }
}
