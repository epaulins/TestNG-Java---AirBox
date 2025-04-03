package pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GoogleServicesPage {

    private AndroidDriver driver;

    @AndroidFindBy(id = "com.google.android.gms:id/suc_layout_title")
    private WebElement textTitle;


    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.Button\").text(\"ACCEPT\")")
    private WebElement btnAccept;

    public GoogleServicesPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
    }

    public void waitForLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(textTitle));
    }

    public void clickAccept() {
        btnAccept.click();

        HomePage homePage = new HomePage(driver);
        homePage.waitForLoaded();
    }
}
