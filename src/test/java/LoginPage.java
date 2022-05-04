import org.junit.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;

class LoginPage extends PageBase {
    
    private By emailField = By.xpath("//div/input[@id='login-username']");
    private By passwordField = By.xpath("//div/input[@id='login-password']");
    private By rememberMeCheckBox = By.cssSelector("label[for='login-remember']");
    private By loginButton = By.xpath("//div/button[@id='login-button']");

    public LoginPage(WebDriver driver) {

        super(driver);
    }

    public LoginPage(WebDriver driver, Boolean reload) {

        super(driver, reload, new Config().getUrl("login"));
    }

    public WebPlayerPage login() {

        WebElement emailFieldElement = waitAndReturnElement(emailField);
        emailFieldElement.sendKeys(new Config().getEmail());

        WebElement passwordFieldElement = waitAndReturnElement(passwordField);
        passwordFieldElement.sendKeys(new Config().getPassword());

        WebElement rememberMeCheckBoxElement = waitAndReturnElement(rememberMeCheckBox);
        rememberMeCheckBoxElement.click();

        WebElement loginButtonElement = waitAndReturnElement(loginButton);
        loginButtonElement.click();

        return new WebPlayerPage(this.driver);
    }

}