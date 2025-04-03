package pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

public class SearchPage {

    private AndroidDriver driver;

    @AndroidFindBy(id = "com.google.android.apps.maps:id/search_omnibox_edit_text")
    private WebElement inputSearchBox;

    @AndroidFindBy(xpath =
            "//android.support.v7.widget.RecyclerView" +
            "[@resource-id=\"com.google.android.apps.maps:id/typed_suggest_container\"]" +
            "/android.widget.LinearLayout")
    private ArrayList<WebElement> listResults;

    public WebElement getResultByIndex(int index) {
        return listResults.get(index - 1);
    }

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").text(\"Your location\")")
    private WebElement optionYourLocation;

    public void enterSearchText(String text) {
        inputSearchBox.sendKeys(text);
    }

    public String getResultTextByIndex(int index) {
        return getResultByIndex(index).findElement(By.className("android.widget.TextView")).getText();
    }

    public void clickSearchResultByIndex(int index) {
        listResults.get(index - 1).click();

        LocationCard locationCard = new LocationCard(driver);
        locationCard.waitForLoaded();
    }

    public SearchPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    public void waitForLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(inputSearchBox));
    }

    public int getNumberOfResults() {
        return listResults.size() - 1;
    }

    public void clickYourLocation() {
        optionYourLocation.click();

        AllowLocationPage allowLocationPage = new AllowLocationPage(driver);
        allowLocationPage.waitForLoaded();
    }
}
