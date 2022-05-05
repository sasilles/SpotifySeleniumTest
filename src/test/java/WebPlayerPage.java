import org.openqa.selenium.*;

class WebPlayerPage extends PlayerBase {
    
    private By loginButton = By.xpath("//body/div[@id='main']/div/div[2]/div[1]/header/div[5]/button[2]");

    private By searchButton = By.xpath("//body/div[@id='main']/div/div[2]/nav/div[1]/ul/li[2]/a");

    private By playlist = By.xpath("//body/div[@id='main']/div/div[2]/nav/div[1]/div[2]/div/div[4]/div[4]/div/div/ul/div/div[2]/li/div/a");
    private By track1 = By.xpath("//body/div[@id='main']/div/div[2]/div[3]/div[1]/div[2]/div[2]/div/div/div[2]/main/div/section/div[2]/div[3]/div[1]/div[2]/div[2]/div[1]/div");
    private By track2 = By.xpath("//body/div[@id='main']/div/div[2]/div[3]/div[1]/div[2]/div[2]/div/div/div[2]/main/div/section/div[2]/div[3]/div[1]/div[2]/div[2]/div[2]/div");
    private By track1Title = By.xpath("//body/div[@id='main']/div/div[2]/div[3]/div[1]/div[2]/div[2]/div/div/div[2]/main/div/section/div[2]/div[3]/div[1]/div[2]/div[2]/div[1]/div/div[2]");
    private By track2Title = By.xpath("//body/div[@id='main']/div/div[2]/div[3]/div[1]/div[2]/div[2]/div/div/div[2]/main/div/section/div[2]/div[3]/div[1]/div[2]/div[2]/div[2]/div/div[2]");

    public WebPlayerPage(WebDriver driver) {

        super(driver);
    }

    public WebPlayerPage(WebDriver driver, Boolean load) {

        super(driver, load, new Config().getUrl("webplayer"));
    }

    public LoginPage clickLogin() {

        WebElement loginButtonElement = waitAndReturnElement(loginButton);
        loginButtonElement.click();

        return new LoginPage(this.driver);
    }

    public SearchPage clickSearch() {

        WebElement searchButtonElement = waitAndReturnElement(searchButton);
        searchButtonElement.click();

        return new SearchPage(this.driver);
    }

    public String reorderPlaylistWithDragAndDrop() {

        WebElement playlistButtonElement = waitAndReturnElement(playlist);
        playlistButtonElement.click();

        WebElement track1Element = waitAndReturnElement(track1);
        WebElement track2Element = waitAndReturnElement(track2);

        String futureTrack1 = waitAndReturnElement(track2Title).getText();

        // I know, this is ugly, but it was the only solution that worked for Spotify (probably due to some JS related stuff)
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("function createEvent(typeOfEvent) {\n" +"var event =document.createEvent(\"CustomEvent\");\n" +"event.initCustomEvent(typeOfEvent,true, true, null);\n" +"event.dataTransfer = {\n" +"data: {},\n" +"setData: function (key, value) {\n" +"this.data[key] = value;\n" +"},\n" +"getData: function (key) {\n" +"return this.data[key];\n" +"}\n" +"};\n" +"return event;\n" +"}\n" +"\n" +"function dispatchEvent(element, event,transferData) {\n" +"if (transferData !== undefined) {\n" +"event.dataTransfer = transferData;\n" +"}\n" +"if (element.dispatchEvent) {\n" + "element.dispatchEvent(event);\n" +"} else if (element.fireEvent) {\n" +"element.fireEvent(\"on\" + event.type, event);\n" +"}\n" +"}\n" +"\n" +"function simulateHTML5DragAndDrop(element, destination) {\n" +"var dragStartEvent =createEvent('dragstart');\n" +"dispatchEvent(element, dragStartEvent);\n" +"var dropEvent = createEvent('drop');\n" +"dispatchEvent(destination, dropEvent,dragStartEvent.dataTransfer);\n" +"var dragEndEvent = createEvent('dragend');\n" +"dispatchEvent(element, dragEndEvent,dropEvent.dataTransfer);\n" +"}\n" +"\n" +"var source = arguments[0];\n" +"var destination = arguments[1];\n" +"simulateHTML5DragAndDrop(source,destination);", track1Element, track2Element);
        
        // Implicit wait for JS
        try { Thread.sleep(3000); } catch (Exception e) { System.out.println(e.getMessage()); }

        return futureTrack1;
    }

    public String getTrack1() {

        return waitAndReturnElement(track1Title).getText();
    }

}