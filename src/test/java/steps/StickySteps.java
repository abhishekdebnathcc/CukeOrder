package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.BasePage;
import utils.SeleniumDriver;


public class StickySteps extends BasePage {
    String orderPage = "";

    @When("user provides {string} and {string}")
    public void userProvidesAnd(String arg0, String arg1) {
        type(By.name("admin_name"), arg0).sendKeys(Keys.TAB, arg1, Keys.ENTER);
    }

    @And("user logs in to the admin")
    public void userLogsInToTheAdmin() {
        SeleniumDriver.getWaitDriver().until(ExpectedConditions.urlContains("dashboard.php"));
        String adminURL = driver.getCurrentUrl();
        orderPage = adminURL.replace("dashboard", "orders");
        driver.get(orderPage);
    }

    @Then("user searches with order no")
    public void userSearchesWithOrderNo() {
        SeleniumDriver.getWaitDriver().until(ExpectedConditions.urlContains("order"));
        for (String orderNo : orderIDList) {
            type(By.name("order_id_filter"), orderNo).sendKeys(Keys.ENTER);
            for (int i = 1; i < orderIDList.size(); i++) {
                driver.switchTo().newWindow(WindowType.TAB);
                driver.get(orderPage);
            }
        }
    }

    @Given("user has navigated to the admin {string}")
    public void userHasNavigatedToTheAdmin(String arg0) {
        SeleniumDriver.setupDriver("firefox");
        driver.get(arg0);
    }

    @Then("user searches with email address")
    public void userSearchesWithEmailAddress() {
        SeleniumDriver.getWaitDriver().until(ExpectedConditions.urlContains("order"));
        for (String email : emailList) {
            type(By.id("email_filter"), email).sendKeys(Keys.ENTER);
            for (int i = 1; i < emailList.size(); i++) {
                driver.switchTo().newWindow(WindowType.TAB);
                driver.get(orderPage);
            }
        }
    }
}
