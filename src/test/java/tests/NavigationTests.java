package tests;

import base.BaseTest;
import io.appium.java_client.Location;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

import java.time.Duration;

public class NavigationTests extends BaseTest {
    PreHomePage preHomePage;
    HomePage homePage;
    SearchPage searchPage;
    LocationCard locationCard;
    EnterRoutePage enterRoutePage;
    AllowLocationPage allowLocationPage;
    LocationAccuracyPage locationAccuracyPage;
    WelcomeToNavigationPopUp welcomeToNavigationPopUp;
    NavigationDataPopUp navigationDataPopUp;
    NavigationPage navigationPage;

    String SEARCH_TEXT = "Wantage";
    Location DIFFERENT_LOCATION = new Location(51.75269214233551, -1.270396090104989);

    @BeforeMethod
    public void setUpPages() {
        homePage = new HomePage(driver);
        searchPage = new SearchPage(driver);
        locationCard = new LocationCard(driver);
        enterRoutePage = new EnterRoutePage(driver);
        allowLocationPage = new AllowLocationPage(driver);
        locationAccuracyPage = new LocationAccuracyPage(driver);
        welcomeToNavigationPopUp = new WelcomeToNavigationPopUp(driver);
        navigationDataPopUp = new NavigationDataPopUp(driver);
        navigationPage = new NavigationPage(driver);
    }

    @Test (priority = 1)
    public void testRouteNavigation() {
        homePage.clickSearchBox();
        searchPage.enterSearchText(SEARCH_TEXT);
        searchPage.clickSearchResultByIndex(1);

        locationCard.clickDirections();

        enterRoutePage.clickOrigin();
        searchPage.clickYourLocation();

        allowLocationPage.allowWhileUsingTheApp();
        locationAccuracyPage.clickTurnOn();
        enterRoutePage.clickStart();
        welcomeToNavigationPopUp.clickGotIt();
        navigationDataPopUp.clickDismiss();

        Assert.assertTrue(navigationPage.isNextDirectionDisplayed());
        Assert.assertTrue(navigationPage.isTimeRemainingDisplayed());
    }

    @Test (priority = 2)
    public void testNavigationRerouting() {
        homePage.clickSearchBox();
        searchPage.enterSearchText(SEARCH_TEXT);
        searchPage.clickSearchResultByIndex(1);

        locationCard.clickDirections();
        enterRoutePage.clickStart();

        Assert.assertTrue(navigationPage.isNextDirectionDisplayed());
        Assert.assertTrue(navigationPage.isTimeRemainingDisplayed());

        String nextDirectionBefore = navigationPage.getNextDirection();
        String remainingTimeBefore = navigationPage.getTimeRemaining();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.setLocation(DIFFERENT_LOCATION);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(d -> {
            return !navigationPage.getTimeRemaining().equals(remainingTimeBefore);
        });

        String nextDirectionAfter = navigationPage.getNextDirection();
        String remainingTimeAfter = navigationPage.getTimeRemaining();

        Assert.assertNotEquals(nextDirectionBefore, nextDirectionAfter);
        Assert.assertNotEquals(remainingTimeBefore, remainingTimeAfter);
    }
}
