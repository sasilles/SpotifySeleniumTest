import org.junit.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;

class HomePage extends PageBase {
    
    private By loginButton = By.xpath("//body/div[@id='__next']/div[1]/header/div/nav/ul/li[6]/a");
    private By signOutButton = By.xpath("//div[@id='profileMenu']/ul/li[2]/a");
    private By profileDropdown = By.xpath("//body/div[@id='__next']/div[1]/header/div/nav/ul/li[5]/button");

    public HomePage(WebDriver driver) {

        super(driver);
    }

    public HomePage(WebDriver driver, Boolean reload) {

        super(driver, reload, new Config().getUrl("home"));
    }

    public LoginPage clickLogin() {

        WebElement loginButtonElement = waitAndReturnElement(loginButton);
        loginButtonElement.click();

        return new LoginPage(this.driver);
    }

    public void clickLogout() {

        WebElement profileDropdownElement = waitAndReturnElement(profileDropdown);
        profileDropdownElement.click();

        WebElement signOutButtonElement = waitAndReturnElement(signOutButton);
        signOutButtonElement.click();
    }

}