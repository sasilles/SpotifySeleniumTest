import org.openqa.selenium.*;

class SearchPage extends PlayerBase {

    private By searchInput = By.xpath("//body/div[@id='main']/div/div[2]/div[1]/header/div[3]/div/div/form/input");

    public SearchPage(WebDriver driver) {

        super(driver);
    }

    public SearchPage(WebDriver driver, Boolean load) {

        super(driver, load, new Config().getUrl("search"));
    }

    public void searchForArtist() {

        WebElement searchInputElement = waitAndReturnElement(searchInput);
        searchInputElement.sendKeys("car seat headrest");
    }

}