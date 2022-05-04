import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.Map;
import java.util.HashMap;

public class SeleniumTest {

    public WebElement waitVisibiltyAndFindElement(By locator) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator);
    }

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setup() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--lang=en");

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void pageTitleTest() {

        HomePage homePage = new HomePage(this.driver, true);
        String pageTitle = homePage.getPageTitle();
        Assert.assertTrue(pageTitle.equals("Listening is everything - Spotify"));
    }

    @Test
    public void homePageLoadTest() {

        HomePage homePage = new HomePage(this.driver, true);
        String bodyText = homePage.getBodyText();
        Assert.assertTrue(bodyText.contains("SPOTIFY PREMIUM"));
        Assert.assertTrue(bodyText.contains("GET 3 MONTHS FREE"));
        Assert.assertTrue(bodyText.contains("SPOTIFY FREE"));
        Assert.assertTrue(bodyText.contains("GET SPOTIFY FREE"));
    }

    @Test
    public void loginTest() {

        HomePage homePage = new HomePage(this.driver, true);
        LoginPage loginPage = homePage.clickLogin();
        WebPlayerPage webPlayerPage = loginPage.login();

        webPlayerPage.getMainText();

        AccountPage accountPage = new AccountPage(this.driver, true);
        String bodyText = accountPage.getBodyText();

        Assert.assertTrue(bodyText.contains(new Config().getEmail()));
    }

    @Test
    public void logoutTest() {

        HomePage homePage = new HomePage(this.driver, true);
        LoginPage loginPage = homePage.clickLogin();
        WebPlayerPage webPlayerPage = loginPage.login();
        webPlayerPage.getMainText();

        homePage = new HomePage(this.driver, true);
        homePage.clickLogout();

        String headerText = homePage.getHeaderText();
        Assert.assertTrue(headerText.contains("Sign up"));
        Assert.assertTrue(headerText.contains("Log in"));
    }

    @Test
    public void changeBirthDateTest() {

        AccountPage accountPage = new AccountPage(this.driver, true);
        LoginPage loginPage = new LoginPage(this.driver);
        loginPage.login();

        accountPage.clickEditProfile();
        accountPage.editBirthDay();

        String bodyText = accountPage.getBodyText();
        Assert.assertTrue(bodyText.contains("Profile saved"));
    }

    @Test
    public void webPlayerTest() {

        WebPlayerPage webPlayerPage = new WebPlayerPage(this.driver, true);
        LoginPage loginPage = webPlayerPage.clickLogin();
        loginPage.login();

        String track = webPlayerPage.reorderPlaylistWithDragAndDrop();

        System.out.println(webPlayerPage.getUrl());

        Assert.assertTrue(track.equals(webPlayerPage.getTrack1()));
    }

    @Test
    public void backTest() {

        HomePage homePage = new HomePage(this.driver, true);
        String prevUrl = homePage.getUrl();

        LoginPage loginPage = homePage.clickLogin();
        loginPage.getBodyText();

        loginPage.goBack();

        homePage.getBodyText();

        Assert.assertTrue(prevUrl.equals(homePage.getUrl()));
    }

    @Test
    public void staticPageTest() {

        HashMap<PageBase, String> pages = new HashMap<PageBase, String>();
        pages.put(new HomePage(this.driver, true), "SPOTIFY FREE");

        for (Map.Entry<PageBase, String> p : pages.entrySet()) {

            String bodyText = p.getKey().getBodyText();
            Assert.assertTrue(bodyText.contains(p.getValue()));
        }
    }
    
    @After
    public void close() {

        if (driver != null) {
            
            driver.quit();
        }
    }
}