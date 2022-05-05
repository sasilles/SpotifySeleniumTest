import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

class PageBase {

    protected WebDriver driver;
    protected WebDriverWait wait;
    
    public PageBase(WebDriver driver) {

        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    public PageBase(WebDriver driver, Boolean load, String pageToLoad) {

        this(driver);
        
        if (load)
            this.driver.get(pageToLoad);
    }
    
    protected WebElement waitAndReturnElement(By locator) {

        this.wait.until(ExpectedConditions.elementToBeClickable(locator));
        return this.driver.findElement(locator);
    }

    public String getUrl() {
        
        return driver.getCurrentUrl();
    }

    public void goBack() {

        driver.navigate().back();
    }
    
    public String getPageTitle() {

        return driver.getTitle();
    }

    public String getHeaderText() {

        WebElement headerElement = waitAndReturnElement(By.tagName("header"));
        return headerElement.getText();
    }

    public String getBodyText() {

        WebElement bodyElement = waitAndReturnElement(By.tagName("body"));
        return bodyElement.getText();
    }
   
}