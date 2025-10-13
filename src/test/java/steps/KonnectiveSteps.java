package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.BasePage;
import utils.SeleniumDriver;

public class KonnectiveSteps extends BasePage {

    @Then("user submits the form")
    public void userSubmitsTheForm() {
        type(By.id("inputZip"), "12345");               //ISSUE PRESENT IN SITE...REMOVE IT FOR OTHER SITES
        submit(By.tagName("button"));
    }

    @And("user enters payment information {}")
    public void userEntersPaymentInformation(String arg0) throws InterruptedException {
       Thread.sleep(1000);
//        if (isElementPresent(By.id("error_handler_overlay_close"))&&SeleniumDriver.getDriver().findElement(By.id("error_handler_overlay_close")).isDisplayed()){
//           try{
//            click(By.id("error_handler_overlay_close"));
//        } catch (Exception e) {
//               System.out.println("Alert closed!");
//           }
//        }

        type(By.name("creditCardNumber"), arg0);
//        select(By.name("expmonth"), "05 - May");
        select(By.name("expmonth"), "(05) May");
        select(By.name("expyear"), "2030");
        type(By.name("CVV"), "123");
    }

    @And("user buys first upsell item")
    public void userBuysFirstUpsellItem() throws InterruptedException {
        SeleniumDriver.getWaitDriver().until(ExpectedConditions.urlContains("upsell1"));
        Thread.sleep(2000);
        click(By.tagName("button"));
    }


    @Then("user buys second upsell item")
    public void userBuysSecondUpsellItem() throws InterruptedException {

        SeleniumDriver.getWaitDriver().until(ExpectedConditions.urlContains("upsell2"));
        Thread.sleep(3000);
        click(By.tagName("button"));
    }


    @Then("user chooses {}")
    public void userChooses(String arg0) throws InterruptedException {
        SeleniumDriver.getWaitDriver().until(ExpectedConditions.urlContains("upsell1"));
        Thread.sleep(2000);
        WebDriver driver = SeleniumDriver.getDriver();
        if (arg0.equals("0")) {
            click(By.partialLinkText("NO"));
            SeleniumDriver.getWaitDriver().until(ExpectedConditions.urlContains("upsell2"));
            Thread.sleep(2000);
//            click(By.partialLinkText("NO"));
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElement(By.partialLinkText("NO")));
            click(By.xpath("/html/body/div[2]/div/div[2]/div[2]/span/a"));
        } else if (arg0.equals("1")) {
            click(By.tagName("button"));
            SeleniumDriver.getWaitDriver().until(ExpectedConditions.urlContains("upsell2"));
            Thread.sleep(2000);
            click(By.partialLinkText("NO"));
        } else if (arg0.equals("2")) {
            click(By.linkText("NO, I DON’T WANT TO TURBO CHARGE MY RESULTS"));
            SeleniumDriver.getWaitDriver().until(ExpectedConditions.urlContains("upsell2"));
            Thread.sleep(1500);
            click(By.tagName("button"));
        } else if (arg0.equals("3")) {
            click(By.tagName("button"));
            SeleniumDriver.getWaitDriver().until(ExpectedConditions.urlContains("upsell2"));
            Thread.sleep(1500);
            click(By.tagName("button"));
        }
    }

    @And("user selects an item")
    public void userSelectsAnItem() {
        SeleniumDriver.getDriver().findElements(By.xpath("//span[@class='package-info__btn']")).get(1).click();
    }

    @And("user selects an {}")
    public void userSelectsAn(String arg0) throws InterruptedException {
//        WebDriver driver = SeleniumDriver.getDriver();
//        int itemNo = Integer.valueOf(arg0);
//        SeleniumDriver.getDriver().findElements(By.xpath("//span[@class='package-info__btn']")).get(itemNo - 1).click();
//        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", driver.findElements(By.xpath("//span[@class='package-info__btn']")).get(itemNo - 1));
//        SeleniumDriver.getWaitDriver().until(ExpectedConditions.urlContains("payment"));
        Thread.sleep(1000);
//        if (isElementPresent(By.id("error_handler_overlay_close"))&&SeleniumDriver.getDriver().findElement(By.id("error_handler_overlay_close")).isDisplayed()){
//            try{
//                click(By.id("error_handler_overlay_close"));
//            } catch (Exception e) {
//                System.out.println("Alert closed!");
//            }
//        }
//        click(By.xpath("//span[@class='package-info__btn']"), arg0);
       jsClick(By.xpath("//span[@class='package-info__btn']"), arg0);
        Thread.sleep(1000);
    }


    @And("user selects the {}")
    public void userSelectsThe(String arg0) throws InterruptedException {
        WebElement suggestion = type(By.id("inputAddress"), arg0);
        Thread.sleep(1500);
        suggestion.sendKeys(Keys.ARROW_DOWN, Keys.TAB);
        Thread.sleep(2000);

    }

    @Then("user submits the payment form")
    public void userSubmitsThePaymentForm() {
//        if (isElementPresent(By.id("error_handler_overlay_close"))&&SeleniumDriver.getDriver().findElement(By.id("error_handler_overlay_close")).isDisplayed()){
//            jsClick(By.id("error_handler_overlay_close"));
//        }
        click(By.tagName("button"));
    }

    @And("user has selected the product type {string}")
    public void userHasSelectedTheProductType(String arg0) {
        if (arg0.equals("2")){
            click(By.xpath("//*[@id='single']/p"));
        }
    }
}
