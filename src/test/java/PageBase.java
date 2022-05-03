import org.junit.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;

import java.util.function.*;

class PageBase {

    protected WebDriver driver;
    protected WebDriverWait wait;
    
    public PageBase(WebDriver driver) {

        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    public PageBase(WebDriver driver, Boolean reload, String pageToGet) {

        this(driver);
        
        if (reload)
            this.driver.get(pageToGet);
    }
    
    protected WebElement waitAndReturnElement(By locator) {

        this.wait.until(ExpectedConditions.elementToBeClickable(locator));
        return this.driver.findElement(locator);
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