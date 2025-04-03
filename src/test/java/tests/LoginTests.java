package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;
import utils.EnvConfig;

public class LoginTests extends BaseTest {

    PreHomePage preHomePage;
    HomePage homePage;
    ProfileMenu profileMenu;
    EnterEmailPage enterEmailPage;
    EnterPasswordPage enterPasswordPage;
    TermsOfServicePage termsOfServicePage;
    GoogleServicesPage googleServicesPage;

    String FULL_NAME = EnvConfig.getEnv("FULL_NAME");
    String EMAIL = EnvConfig.getEnv("EMAIL");
    String PASSWORD = EnvConfig.getEnv("PASSWORD");
    String INVALID_PASSWORD = "INVALID_PASSWORD";

    @BeforeMethod
    public void setUpPages() {
        homePage = new HomePage(driver);
        profileMenu = new ProfileMenu(driver);
        enterEmailPage = new EnterEmailPage(driver);
        enterPasswordPage = new EnterPasswordPage(driver);
        termsOfServicePage = new TermsOfServicePage(driver);
        googleServicesPage = new GoogleServicesPage(driver);
    }

    @Test (priority = 1)
    public void testInvalidLogin() {
        homePage.clickProfile();

        profileMenu.clickSignIn();

        enterEmailPage.enterEmail(EMAIL);
        enterEmailPage.clickNext();

        enterPasswordPage.enterPassword(INVALID_PASSWORD);
        enterPasswordPage.clickNext();

        Assert.assertTrue(enterPasswordPage.isErrorMessageDisplayed());
        String expectedError = "Wrong password. Try again or click Forgot password to reset it.";
        Assert.assertEquals(enterPasswordPage.getErrorMessageText(), expectedError);
    }

    @Test (priority = 2)
    public void testValidLogin() {
        homePage.clickProfile();

        profileMenu.clickSignIn();

        enterEmailPage.enterEmail(EMAIL);
        enterEmailPage.clickNext();

        enterPasswordPage.enterPassword(PASSWORD);
        enterPasswordPage.clickNext();

        termsOfServicePage.clickIAgree();
        googleServicesPage.clickAccept();

        homePage.clickProfile();

        Assert.assertTrue(profileMenu.isAvatarDisplayed());
        Assert.assertEquals(profileMenu.getAccountName(), FULL_NAME);
        Assert.assertEquals(profileMenu.getAccountEmail(), EMAIL);
    }
}
