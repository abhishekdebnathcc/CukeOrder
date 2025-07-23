package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SeleniumDriver {

    //DECLARING AN INSTANCE VARIABLE OF THE SAME CLASS
    private static SeleniumDriver seleniumDriver;

    //DECLARING A STATIC WEBDRIVER VARIABLE
    private static WebDriver driver;

    //DECLARING A STATIC WEBDRIVER WAIT VARIABLE
    private static WebDriverWait waitDriver;

    //INITIALIZING A CONSTANT TIMEOUT VALUE OF 30 SECONDS
    private final static int TIMEOUT = 15;

    //INITIALIZING A CONSTANT PAGE LOAD TIMEOUT VALUE OF 50 SECONDS
    private final static int PAGE_LOAD_TIMEOUT = 60;

    //CREATING A PRIVATE CONSTRUCTOR BY FOLLOWING SINGLETON PATTERN
    private SeleniumDriver(String browser) {

        //CHOOSE WHICH BROWSER TO USE
        getBrowserDriver(browser);

        //INITIALIZING DRIVER WITH CHROME BROWSER
        // driver = new ChromeDriver();
//        driver.manage().window().maximize();
        if (!browser.equalsIgnoreCase("chrome")) {
            driver.manage().window().maximize();
        }

        //INITIALIZE WEBDRIVER WAIT WITH TIMEOUT
        waitDriver = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));

//        BasePage.driver = getDriver();
        BasePage.driver = driver;
        //INITIALIZE IMPLICIT WAIT WITH TIMEOUT
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));

        //INITIALIZE PAGE LOAD WITH PAGE LOAD TIMEOUT
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(PAGE_LOAD_TIMEOUT));
    }

    //CREATE THE STATIC DRIVER GETTER METHOD
    public static WebDriver getDriver() {
        return driver;
    }

    //CREATE THE STATIC DRIVER SETTER METHOD
    //THIS WILL CALL THE PRIVATE CONSTRUCTOR AND INITIALIZE WEB DRIVER
    public static void setupDriver(String browser) {
        if (seleniumDriver == null) {
            seleniumDriver = new SeleniumDriver(browser);
        }
    }

    //CREATE A TEARDOWN METHOD TO DESTROY THE OBJECT
    public static void tearDown() {
        if (driver != null) {
//            driver.close();         //CLOSE CURRENT BROWSER WINDOW
//            driver.quit();          //CLOSE ALL BROWSER WINDOWS
        }
        seleniumDriver = null;        //CLEAR THE SELENIUM DRIVER VARIABLE
    }

    //CREATE A UTILITY METHOD FOR OPENING THE WEB PAGE
    public static void openPage(String url) {
        driver.get(url);
    }

    public  ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.addArguments("--disable-notifications");
//        chromeOptions.addArguments("--disable-infobars");

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        prefs.put("autofill.profile_enabled", false);               //DISABLE SAVE ADDRESS ALERT
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager.enabled", false);       //DISABLE SAVE PASSWORD ALERT
        prefs.put("autofill.credit_card_enabled", false);           //DISABLE SAVE CARD ALERT
//        prefs.put("profile.default_content_setting_values.geolocation", 2);
//        prefs.put("profile.managed_default_content_settings.images", 2);

        chromeOptions.setExperimentalOption("prefs", prefs);
        chromeOptions.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));
        chromeOptions.addArguments("--start-maximized");
        return chromeOptions;
    }

    public static WebDriverWait getWaitDriver() {
        return waitDriver;
    }

    public static FirefoxOptions getFirefoxOptions() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        FirefoxProfile firefoxProfile = new FirefoxProfile();

        //ADD THE ARGUMENT TO THE PROFILE
        firefoxProfile.setPreference("dom.disable_beforeunload", true);

        firefoxOptions.setProfile(firefoxProfile);
        return firefoxOptions;
    }

    public void getBrowserDriver(String browser){

        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver(getChromeOptions());
        } else if (browser.equalsIgnoreCase("firefox")||browser.equalsIgnoreCase("fox")) {
            driver = new FirefoxDriver(getFirefoxOptions());
        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else if (browser.equalsIgnoreCase("safari")) {
            driver = new SafariDriver();
        } else {
            System.out.println("Correct browser name not entered!!!\nDefaulting to Chrome browser!!!");
            driver = new ChromeDriver(getChromeOptions());
        }
    }
}
