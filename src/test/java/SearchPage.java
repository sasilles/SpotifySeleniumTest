import org.junit.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;

class SearchPage extends PageBaseWebPlayer {

    public SearchPage(WebDriver driver) {

        super(driver);
    }

    public SearchPage(WebDriver driver, Boolean reload) {

        super(driver, reload, new Config().getUrl("search"));
    }

}