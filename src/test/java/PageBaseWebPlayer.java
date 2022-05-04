import org.junit.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;

import java.util.function.*;

class PageBaseWebPlayer extends PageBase {
    
    private By mainDiv = By.xpath("//body/div[@id='main']");

    public PageBaseWebPlayer(WebDriver driver) {

        super(driver);
    }

    public PageBaseWebPlayer(WebDriver driver, Boolean reload, String pageToGet) {

        super(driver, reload, pageToGet);
    }
    
    protected WebElement waitAndReturnElement(By locator) {

        this.wait.until(ExpectedConditions.elementToBeClickable(locator));
        return this.driver.findElement(locator);
    }

    public String getMainText() {

        WebElement mainDivElement = waitAndReturnElement(mainDiv);
        return mainDivElement.getText();
    }
   
}