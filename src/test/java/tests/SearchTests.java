package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;

public class SearchTests extends BaseTest {
    PreHomePage preHomePage;
    HomePage homePage;
    SearchPage searchPage;
    LocationCard locationCard;

    String SEARCH_TEXT = "Wantage";

    @BeforeMethod
    public void setUpPages() {
        homePage = new HomePage(driver);
        searchPage = new SearchPage(driver);
        locationCard = new LocationCard(driver);
    }

    @Test
    public void testLocationSearch() {
        homePage.clickSearchBox();
        searchPage.enterSearchText(SEARCH_TEXT);

        for(int i = 1; i <= searchPage.getNumberOfResults(); i++) {
            Assert.assertTrue(searchPage.getResultTextByIndex(i).contains(SEARCH_TEXT));
        }

        searchPage.clickSearchResultByIndex(1);

        Assert.assertTrue(locationCard.getTitleText().contains(SEARCH_TEXT));
    }
}
