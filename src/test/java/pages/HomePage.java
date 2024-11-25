package pages;


import Utilities.Driver;
import Utilities.ReusableMethods;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.SourceType;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;


public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    Actions actions = new Actions(Driver.getDriver());
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    //<<<<<<<HomePage locate<<<<<<<<<<<<<<

    @FindBy(css = "ul#menu-1-5dc673f1 > li")
    public static List<WebElement> navbarMenu1;

    @FindBy(css = "ul.sub-menu > li > a")
    public static List<WebElement> navbarAltMenu;

    @FindBy(xpath = "(//*[@class='elementor-widget-cmsmasters-search__container'])[1]")
    public WebElement homePageSearchBox;

    @FindBy(xpath = "(//*[@class='elementor-widget-cmsmasters-search__field '])[1]")
    public WebElement inputBox;

    @FindBy(xpath = "//h3[@class='entry-title cmsmasters-widget-title__heading']")
    public List<WebElement> haberList;

    @FindBy(xpath = "//*[@class='cmsmasters-widget-image__wrap']")
    public List<WebElement> haberGörseliList;

    @FindBy(css = "ul#menu-1-5dc673f1 > li")
    public static List<WebElement> navbarMenu;

    public void hoverAndClickSubMenus() {

        Actions actions = new Actions(Driver.getDriver());
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        for (int i = 0; i < navbarMenu.size(); i++) {
            WebElement mainMenu = navbarMenu.get(i);
            actions.moveToElement(mainMenu).perform();
            System.out.println("Üst Menüye Hover Yapılıyor: " + mainMenu.getText());
            WebElement subMenu = wait.until(ExpectedConditions.visibilityOf(navbarMenu.get(i)));
            ReusableMethods.bekle(1);
            if (subMenu.isDisplayed()) {
                for (WebElement subMenuItem : subMenu.findElements(By.tagName("li"))) {
                    System.out.println("Alt Menüye Tıklanıyor: " + subMenuItem.getText());
                    try {
                        String pageTitle = Driver.getDriver().getTitle();
                        String expectedSubMenuName = subMenuItem.getText();
                        subMenuItem.click();
                        ReusableMethods.bekle(1);
                        String actualTitle = Driver.getDriver().getTitle();
                        Assert.assertTrue(actualTitle.contains(expectedSubMenuName));
                    } catch (StaleElementReferenceException e) {
                        System.out.println("StaleElementReferenceException: Öğeye tıklanırken geçersiz öğe referansı alındı. Yeniden bulmaya çalışıyoruz.");
                        subMenuItem = wait.until(ExpectedConditions.elementToBeClickable(subMenuItem));
                        subMenuItem.click();
                    }
                    wait.until(ExpectedConditions.urlToBe(driver.getCurrentUrl()));
                    driver.navigate().back();
                    actions.moveToElement(mainMenu).perform();
                    wait.until(ExpectedConditions.visibilityOf(subMenu));
                }
            } else {
                System.out.println("Alt menü görünür değil: " + mainMenu.getText());}}}

    public void searchAndClickHaberListElement(int ResultIndex) throws InterruptedException {

        ReusableMethods.scrollToElementWithAction(haberGörseliList.get(ResultIndex - 1));
        ReusableMethods.bekle(2);
        if (haberList.size() >= ResultIndex - 1) {
            String expectedText = haberList.get(ResultIndex - 1).getText().replaceAll("[^a-zA-Z0-9\\s]", "");
            expectedText = expectedText.replaceAll(" ", "");
            haberGörseliList.get(ResultIndex - 1).click();
            String actualText = Driver.getDriver().getTitle().replaceAll("[^a-zA-Z0-9\\s]", "");
            actualText = actualText.replaceAll(" ", "");
            Assert.assertTrue(actualText.contains(expectedText));
        } else {
            throw new AssertionError("Belirtilen index arama sonuçları arasında yok. Sonuç sayısı: " + haberList.size());}}

//<<<<<<<<<<<HR Page Locate><<<<<<<<<<<<<<<<<

    @FindBy(xpath = "//*[@id='name']")
    public WebElement nameInputBox;

    @FindBy(xpath = "//*[@id='birth']")
    public WebElement birthDayInputBox;

    @FindBy(xpath = "//*[@id='tcKimlik']")
    public WebElement tcKimlikInputBox;

    @FindBy(xpath = "//*[@id='phone']")
    public WebElement phoneInputBox;

    @FindBy(xpath = "//*[@id='email']")
    public WebElement emailInputBox;

    @FindBy(xpath = "(//*[@for='cv_field'])[1]")
    public WebElement cvUploadInputBox;

    @FindBy(xpath = "(//*[@for='cv_field'])[2]")
    public WebElement cvUploadButton;

    @FindBy(xpath = "//button[@type='button']")
    public List<WebElement> egitimBilgileriButtonList;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement nextButton;

    @FindBy(xpath = "//*[@class='flex flex-col gap-2 group rounded-lg py-3 px-4 cursor-pointer transition-all bg-[#DF1F29] text-white']")
    public WebElement testEngineerOption;

    @FindBy(xpath = "//*[text()='Test Engineer']")
    public WebElement secilenText;

    @FindBy(xpath = "//*[@class='text-white flex justify-center items-center text-[14px] py-2 px-4 rounded-full bg-[#DF1F29] cursor-pointer']")
    public WebElement gonderButton;

    @FindBy(xpath = "//*[@role='status']")
    public WebElement hataMesaji;

    @FindBy(xpath = "//*[@class='w-full flex justify-start py-10']")
    public WebElement basariliKayitDogrulama;

    public  void egitimOptionClick(String EgitimSeviyesi){
        for (WebElement each:egitimBilgileriButtonList){
            if (each.getText().equals(EgitimSeviyesi)){
                each.click();}}}}





