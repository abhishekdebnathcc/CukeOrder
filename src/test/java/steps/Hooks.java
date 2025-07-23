package steps;

import io.cucumber.java.*;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.BasePage;
import utils.SeleniumDriver;

public class Hooks extends BasePage {

    public WebDriver driver;

    @BeforeAll
    public static void setup(){
//        SeleniumDriver.setupDriver();
        PropertyConfigurator.configure("./src/test/resources/properties/log4j.properties");
    }

    @Before("@Assertion or @Test")
    public void beforeTest(){
        SeleniumDriver.setupDriver("chrome");
        SeleniumDriver.getDriver().get("https://offer.buynobluvision.com/offer/1-beta2/checkout-now-v2.php/");
    }


//    @Before()
//    public void blankStart(){
//
//    }

    @After
    public void afterScenario(Scenario scenario){
        if (scenario.isFailed()){
            byte[] screenshot = ((TakesScreenshot)SeleniumDriver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Failure screenshot");
        }
        System.out.println("Tags used : "+scenario.getSourceTagNames().toString());
        System.out.println(scenario.getName()+ ": "+scenario.getStatus().toString());
        SeleniumDriver.tearDown();
    }

    @AfterAll
    public static void tearDown(){
//        log.info("Order IDs are :" +orderIDList);
        log.info("All tests are complete !!!");
    }
}
