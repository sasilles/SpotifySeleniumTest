import org.openqa.selenium.*;

class AccountPage extends PageBase {

    private By editProfileButton = By.xpath("//li[@id='profile']/a");

    private By birthDayInput = By.xpath("//div/input[@id='dob-date']");
    private By saveProfileButton = By.xpath("//body/div[@id='__next']/div/div/div[2]/div[2]/div[2]/div/article/section/form/div/button/div[1]");

    public AccountPage(WebDriver driver) {

        super(driver);
    }

    public AccountPage(WebDriver driver, Boolean load) {

        super(driver, load, new Config().getUrl("account"));
    }

    public void clickEditProfile() {

        WebElement editProfileButtonElement = waitAndReturnElement(editProfileButton);
        editProfileButtonElement.click();
    }

    public void editBirthDay() {

        WebElement birthDayInputElement = waitAndReturnElement(birthDayInput);
        birthDayInputElement.clear();
        birthDayInputElement.sendKeys("10");

        WebElement saveProfileButtonElement = waitAndReturnElement(saveProfileButton);
        saveProfileButtonElement.click();
    }

}