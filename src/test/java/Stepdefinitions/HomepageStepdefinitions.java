package Stepdefinitions;

import Utilities.ConfigReader;
import Utilities.Driver;
import Utilities.ReusableMethods;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pages.HomePage;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomepageStepdefinitions {

HomePage homePage=new HomePage(Driver.getDriver());

    @Given("anasayfaya tıkla")
    public void anasayfaya_tıkla() {
       Driver.getDriver().get(ConfigReader.getProperty("2nHaberUrl"));
    }
    @When("Navbar elementlerini görüntüler ve dogrular")
    public void navbar_elementlerini_görüntüler_ve_dogrular() throws InterruptedException {
        homePage.hoverAndClickSubMenus();
    }

    @Then("Input box alanına {string} degerini girer ve arama yapar")
    public void input_box_alanına_degerini_girer_ve_arama_yapar(String SearchKeyword) {
        homePage.inputBox.sendKeys(SearchKeyword+Keys.ENTER);}
    @Given("Kullanıcı {string} adresine gider")
    public void kullanıcı_adresine_gider(String istenilenUrl) throws InterruptedException {
        Driver.getDriver().get(ConfigReader.getProperty(istenilenUrl));
        ReusableMethods.bekle(2);}

    @Then("Search butonuna tıklar")
    public void butonuna_tıklar() throws InterruptedException {
        ReusableMethods.click(homePage.homePageSearchBox);
        ReusableMethods.bekle(2);}

    @Then("Kullanıcı {int} habere tıklar ve detaylarını görüntüler")
    public void kullanıcı_habere_tıklar_ve_detaylarını_görüntüler(int ResultIndex) throws InterruptedException {
        homePage.searchAndClickHaberListElement(ResultIndex);
    }
/////////////////////////////
    @Given("Ad Soyad alanına {string} girer")
    public void ad_soyad_alanına_girer(String AdSoyad) {
        homePage.nameInputBox.sendKeys(AdSoyad);
        ReusableMethods.bekle(1);
    }
    @Given("Doğum Tarihi alanına {string} girerim")
    public void doğum_tarihi_alanına_girerim(String DogumTarihi) {
        homePage.birthDayInputBox.sendKeys(DogumTarihi);
        ReusableMethods.bekle(1);
    }
    @Given("T.C. Kimlik No alanına {string} girerim")
    public void t_c_kimlik_no_alanına_girerim(String TcKimlikNo) {
        homePage.tcKimlikInputBox.sendKeys(TcKimlikNo);
        ReusableMethods.bekle(1);
    }
    @Given("Cep Telefonu alanına {string} girerim")
    public void cep_telefonu_alanına_girerim(String CepTelefonu) {
        homePage.phoneInputBox.sendKeys(CepTelefonu);
        ReusableMethods.bekle(1);
    }
    @Given("E-posta alanına {string} girerim")
    public void e_posta_alanına_girerim(String Email) {
        homePage.emailInputBox.sendKeys(Email);
        ReusableMethods.bekle(1);
    }
    @Given("Cv dosyasını yukler")
    public void cv_dosyasını_yukler() throws AWTException {

        String filePath = System.getProperty("user.home") + "\\Desktop\\2nTask\\Resume.pdf";
        StringSelection selection = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
        homePage.cvUploadButton.click();
        Robot robot = new Robot();
        robot.delay(2000);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        ReusableMethods.bekle(1);}

    @Given("Egitim seviyesini {string} secer")
    public void egitim_seviyesini_secer(String EgitimSeviyesi) {
        homePage.egitimOptionClick(EgitimSeviyesi);
        ReusableMethods.bekle(1);
    }
    @Given("Sonraki adım icin next butonuna tıklar")
    public void sonraki_adım_icin_next_butonuna_tıklar() {
        ReusableMethods.clickWithJs1(homePage.nextButton);
    }
    @Given("{string} secer")
    public void secer(String Pozisyon) {
        WebElement locateText=Driver.getDriver().findElement(By.xpath("//*[text()='"+Pozisyon+"']"));
        ReusableMethods.bekle(1);
        locateText.click();
    }
    @Given("Gonder butonuna tıklar")
    public void gonder_butonuna_tıklar() {
        ReusableMethods.clickWithJs1(homePage.gonderButton);
        ReusableMethods.bekle(2);
        assertTrue(homePage.basariliKayitDogrulama.isDisplayed());
    }

    @And("Gonder butonuna tıklar ve kayıtın yapılamadığı doğrulanır")
    public void gonder_butonuna_tıklar_ve_kayıtın_yapılamadığı_doğrulanır() {
        ReusableMethods.clickWithJs1(homePage.gonderButton);
        ReusableMethods.bekle(2);
        assertTrue(homePage.hataMesaji.isDisplayed());
    }
    @And("Sayfayı kapatır")
    public void sayfayı_kapatır() {
        Driver.quitDriver();
    }
}
