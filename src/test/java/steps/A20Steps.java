package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BasePage;
import utils.SeleniumDriver;

import java.util.Set;

public class A20Steps extends BasePage {
    private String mainWindow = "";
    private String companyID;
    private String orderNos;

    @Given("user navigates to the website")
    public void userNavigatesToTheWebsite() {
        SeleniumDriver.setupDriver("chrome");
        driver.get("https://app.billingmgr.net/login/");
    }

    @When("user clicks on google authentication")
    public void userClicksOnGoogleAuthentication() throws InterruptedException {
        mainWindow = driver.getWindowHandle();
        System.out.println(mainWindow);
        click(By.xpath("//*[@id='g-btn']"));
        Thread.sleep(5000);
        Set<String> windows = driver.getWindowHandles();
        for (String window : windows) {
            System.out.println(window);
            if (!window.equals(mainWindow)) {
                driver.switchTo().window(window);
                System.out.println(window);
                break;
            }
        }

    }

    @And("user enters {string} and {string}")
    public void userEntersAnd(String arg0, String arg1) throws InterruptedException {
        Thread.sleep(4000);
        System.out.println(driver.getWindowHandle());
        type(By.xpath("//*[@id='identifierId']"), arg0).sendKeys(Keys.ENTER);
        Thread.sleep(3000);
        type(By.name("Passwd"), arg1).sendKeys(Keys.ENTER);
        Thread.sleep(5000);
        driver.switchTo().window(mainWindow);
//        driver.switchTo().activeElement().submit();
//        driver.switchTo().alert().accept();

    }

    @Then("user naviagtes to A{int} accounts page")
    public void userNaviagtesToAAccountsPage(int arg0) {

    }

    @Then("user naviagtes to accounts page")
    public void userNaviagtesToAccountsPage() {
        SeleniumDriver.getWaitDriver().until(ExpectedConditions.urlContains("dashboard"));
    }

    @When("user submits id {string}")
    public void userSubmitsId(String arg0) throws InterruptedException {
        companyID = arg0;
        Thread.sleep(2000);
        type(By.id("fc-search-term"), arg0).sendKeys(Keys.ENTER);
        Thread.sleep(3000);
    }

    @Then("user signs into the A{int} control pages")
    public void userSignsIntoTheAControlPages(int arg0) throws InterruptedException {
        click(By.xpath("//*[@id='account-list-placeholder']/tr[1]/td[11]/div/a"));
        Thread.sleep(1000);
        click(By.xpath("//*[@id='account-list-placeholder']/tr[1]/td[11]/div/ul/li[4]/a"));
        Thread.sleep(2000);
    }

    @And("user navigates to Initial Control")
    public void userNavigatesToInitialControl() throws InterruptedException {
        click(By.xpath("//*[@id='sidebar_menu_scroll_div']/div[2]/div/nav/ul/li[2]/a"));
        Thread.sleep(1000);
        click(By.xpath("//*[@id='sidebar_menu_scroll_div']/div[2]/div/nav/ul/li[2]/ul/li[3]/a"));
        Thread.sleep(1000);
    }

    @Then("user provides the order numbers {string}")
    public void userProvidesTheOrderNumbers(String arg0) throws InterruptedException {
        orderNos = arg0.replaceAll("\\s","");

        click(By.xpath("//*[@id='filterForm']/div[1]/div[2]/div[5]/div[1]/div/div/a/span"));
        Thread.sleep(1000);
        click(By.xpath("//*[@id='filterForm']/div[1]/div[2]/div[5]/div[1]/div/div/div/ul/li[2]"));
        Thread.sleep(1000);
        type(By.xpath("//*[@id='filterForm']/div[1]/div[2]/div[5]/div[2]/input"), orderNos).sendKeys(Keys.ENTER);
    }

    @And("user navigates to Subscription Control")
    public void userNavigatesToSubscriptionControl() {
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://app.billingmgr.net/dashboard/#mids/subscription-control/");
    }

    @Then("user provides the same order numbers {string}")
    public void userProvidesTheSameOrderNumbers(String arg0) throws InterruptedException {
        click(By.xpath("//*[@id='filterForm']/div[1]/div/div[5]/div[1]/div/div/a/span"));
        Thread.sleep(1000);
        click(By.xpath("//*[@id='filterForm']/div[1]/div/div[5]/div[1]/div/div/div/ul/li[3]"));
        Thread.sleep(1000);
        type(By.xpath("//*[@id='filterForm']/div[1]/div/div[5]/div[2]/input"), orderNos).sendKeys(Keys.ENTER);
    }

    @And("user checks data capture")
    public void userChecksDataCapture() throws InterruptedException {
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("https://app.billingmgr.net/dashboard/#report/payload-check/");
        Thread.sleep(1000);
        click(By.xpath("//*[@id='client_id_chosen']/a/span"));
        Thread.sleep(1000);
        click(By.xpath("//*[@id='client_id_chosen']/div/ul/li[2]"));
        Thread.sleep(1000);
        type(By.id("order_id"), orderNos).sendKeys(Keys.ENTER);
    }

    @And("user edits order properties")
    public void userEditsOrderProperties() throws InterruptedException {
//       driver.navigate().refresh();
        Thread.sleep(1000);

        click(By.xpath("//*[@id='header-panel-container']/th[1]/label"));
        Thread.sleep(1000);
        click(By.xpath("//*[@id='filterForm']/div[1]/div/div[7]/a/i"));
        Thread.sleep(500);
        click(By.xpath("//*[@id='filterForm']/div[1]/div/div[7]/div/div/ul[2]/li[1]/a"));
        Thread.sleep(1000);
        click(By.xpath("//*[@id='force-gateway-form']/div[2]/div[1]/label/div"));
        Thread.sleep(500);
        type(By.xpath("//*[@id='force-gateway-form']/div[2]/div[2]/div[4]/div/div[1]/div/div[2]/div/input"), "1"  ).sendKeys(Keys.TAB, "5");
    }

    @Then("user adds the order numbers")
    public void userAddsTheOrderNumbers() {
        type(By.id("order_id"), orderNos).sendKeys(Keys.ENTER);
    }
}
