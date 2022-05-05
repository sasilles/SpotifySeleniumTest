import org.openqa.selenium.*;

class PlayerBase extends PageBase {
    
    private By mainDiv = By.xpath("//body/div[@id='main']");

    public PlayerBase(WebDriver driver) {

        super(driver);
    }

    public PlayerBase(WebDriver driver, Boolean load, String pageToLoad) {

        super(driver, load, pageToLoad);
    }

    public String getMainText() {

        WebElement mainDivElement = waitAndReturnElement(mainDiv);
        return mainDivElement.getText();
    }
   
}