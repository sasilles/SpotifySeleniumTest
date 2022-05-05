import java.util.Map;
import java.util.HashMap;

import static org.junit.Assert.*;

import org.junit.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void setup() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--lang=en"); // It is needed for the webplayer to appear in English

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void testPageTitleHomePage() {

        HomePage homePage = new HomePage(this.driver, true);
        String pageTitle = homePage.getPageTitle();
        Assert.assertTrue(pageTitle.equals("Listening is everything - Spotify"));
    }

    @Test
    public void testStaticContentHomePage() {

        HomePage homePage = new HomePage(this.driver, true);
        String bodyText = homePage.getBodyText();

        Assert.assertTrue(bodyText.contains("SPOTIFY PREMIUM"));
        Assert.assertTrue(bodyText.contains("GET 3 MONTHS FREE"));
        Assert.assertTrue(bodyText.contains("SPOTIFY FREE"));
        Assert.assertTrue(bodyText.contains("GET SPOTIFY FREE"));
    }

    @Test
    public void testStaticContentMultiplePages() {

        HashMap<String, String> pages = new HashMap<String, String>();

        pages.put(new Config().getUrl("home"), "SPOTIFY FREE");
        pages.put(new Config().getUrl("support"), "How can we help you?");
        pages.put(new Config().getUrl("about_us"), "Customer Service and Support");

        for (String url : pages.keySet()) {

            PageBase page = new PageBase(this.driver, true, url);
            String bodyText = page.getBodyText();

            Assert.assertTrue(bodyText.contains(pages.get(url)));
        }
    }

    @Test
    public void testLogin() {

        HomePage homePage = new HomePage(this.driver, true);
        LoginPage loginPage = homePage.clickLogin();
        WebPlayerPage webPlayerPage = loginPage.login();

        // Explicit wait for main text -> needed for webplayer to be loaded completely
        webPlayerPage.getMainText();

        AccountPage accountPage = new AccountPage(this.driver, true);
        String bodyText = accountPage.getBodyText();

        Assert.assertTrue(bodyText.contains(new Config().getEmail()));
    }

    @Test
    public void testLogout() {

        HomePage homePage = new HomePage(this.driver, true);
        LoginPage loginPage = homePage.clickLogin();
        WebPlayerPage webPlayerPage = loginPage.login();

        // Explicit wait for main text -> needed for webplayer to be loaded completely
        webPlayerPage.getMainText();

        homePage = new HomePage(this.driver, true);
        homePage.clickLogout();

        String headerText = homePage.getHeaderText();
        Assert.assertTrue(headerText.contains("Sign up"));
        Assert.assertTrue(headerText.contains("Log in"));
    }

    @Test
    public void testBirthDateChangeAccountPage() {

        AccountPage accountPage = new AccountPage(this.driver, true);
        LoginPage loginPage = new LoginPage(this.driver);
        loginPage.login();

        accountPage.clickEditProfile();
        accountPage.editBirthDay();

        String bodyText = accountPage.getBodyText();
        Assert.assertTrue(bodyText.contains("Profile saved"));
    }

    @Test
    public void testBrowserHistory() {

        WebPlayerPage webPlayerPage = new WebPlayerPage(this.driver, true);
        String previousUrl = webPlayerPage.getUrl();

        LoginPage loginPage = webPlayerPage.clickLogin();
        webPlayerPage = loginPage.login();

        SearchPage searchPage = webPlayerPage.clickSearch();
        searchPage.searchForArtist();
        searchPage.goBack();

        Assert.assertTrue(previousUrl.equals(webPlayerPage.getUrl()));
    }

    @Test
    public void testPlaylistReorderWebPlayer() {

        WebPlayerPage webPlayerPage = new WebPlayerPage(this.driver, true);
        LoginPage loginPage = webPlayerPage.clickLogin();
        loginPage.login();

        String track = webPlayerPage.reorderPlaylistWithDragAndDrop();

        Assert.assertTrue(track.equals(webPlayerPage.getTrack1()));
    }
    
    @After
    public void close() {

        if (driver != null) {
            
            driver.quit();
        }
    }
}