import org.junit.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;

class WebPlayerPage extends PageBase {
    
    private By mainDiv = By.xpath("//body/div[@id='main']");

    public WebPlayerPage(WebDriver driver) {

        super(driver);
    }

    public WebPlayerPage(WebDriver driver, Boolean reload) {

        super(driver, reload, "https://open.spotify.com/");
    }

    public String getMainText() {

        WebElement mainDivElement = waitAndReturnElement(mainDiv);
        return mainDivElement.getText();
    }

}