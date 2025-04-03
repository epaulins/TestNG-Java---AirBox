package pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private AndroidDriver driver;

    @AndroidFindBy(id = "com.google.android.apps.maps:id/search_omnibox_text_box")
    private WebElement inputSearchBox;

    @AndroidFindBy(id = "com.google.android.apps.maps:id/search_omnibox_one_google_account_disc")
    private WebElement btnProfile;

    @AndroidFindBy(accessibility = "Directions")
    private WebElement btnDirections;

    public HomePage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    public void waitForLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(inputSearchBox));
    }

    public boolean isSearchBoxDisplayed() {
        return inputSearchBox.isDisplayed();
    }

    public void clickSearchBox() {
        inputSearchBox.click();
        SearchPage searchPage = new SearchPage(driver);
        searchPage.waitForLoaded();
    }

    public void searchLocation(String location) {
        inputSearchBox.sendKeys(location);
    }

    public void clickProfile() {
        btnProfile.click();

        ProfileMenu profileMenu = new ProfileMenu(driver);
        profileMenu.waitForLoaded();
    }

    public void clickDirections() {
        btnDirections.click();
    }
}
