package Utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

public class Driver {

    public static WebDriver driver;

    public static WebDriver getDriver(){

        String browserTercihi = GlobalVars.browser;

        if (driver == null){
            switch (browserTercihi){
                case "firefox" :
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case "edge" :
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;
                case "safari" :
                    WebDriverManager.safaridriver().setup();
                    driver = new SafariDriver();
                    break;
                default:
                    WebDriverManager.chromedriver();
                    driver = new ChromeDriver();
            }
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalVars.DefaultExplicitTimeOut));
        }
        return driver;
    }
    public static void closeDriver(){
        if (driver != null){
            driver.close();
            driver = null;}}
    public static void quitDriver(){
        if (driver != null){
            driver.quit();
            driver = null;}}
}
