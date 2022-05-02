import org.junit.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;

class HomePage extends PageBase {
    
    private By loginButton = By.xpath("//body/div[@id='__next']/div[1]/header/div/nav/ul/li[6]/a");

    public HomePage(WebDriver driver) {

        super(driver);
        this.driver.get("https://www.spotify.com/us/");
    }

    public LoginPage clickLogin() {

        WebElement loginButtonElement = waitAndReturnElement(loginButton);
        loginButtonElement.click();

        return new LoginPage(this.driver);
    }

}