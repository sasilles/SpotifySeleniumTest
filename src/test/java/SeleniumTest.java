import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;

import io.github.bonigarcia.wdm.WebDriverManager;

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
    public void loginTest() {

        HomePage homePage = new HomePage(this.driver);
        LoginPage loginPage = homePage.clickLogin();
        WebPlayerPage webPlayerPage = loginPage.login();

        String mainText = webPlayerPage.getMainText();
        Assert.assertTrue(mainText.contains("Joe Komposztor"));
    }
    
    @After
    public void close() {

        if (driver != null) {
            
            driver.quit();
        }
    }
}