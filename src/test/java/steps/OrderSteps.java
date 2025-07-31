package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.BasePage;
import utils.SeleniumDriver;

import java.util.List;
import java.util.Map;


public class OrderSteps extends BasePage {

//    public WebDriver driver;
    public String email;

    @Given("user has opened {}")
    public void userHasOpened(String arg0) {
        SeleniumDriver.setupDriver(arg0);
//        driver = SeleniumDriver.getDriver()
    }

    @When("user has navigated to the funnel {string}")
    public void userHasNavigatedToTheFunnel(String arg0) {
        driver.get(arg0);
    }

    @Then("user has filled up the {}")
    public void userHasFilledUpThe(String arg0) {
        if(arg0.equalsIgnoreCase("test")){
            arg0 += randomNoFrom1to20();

        } else if (arg0.equals("")) {
            arg0 = "test"+randomNoFrom1to20();
        } else if (arg0.equals("+")) {
            arg0 = "abhishek.debnath+"+(int)(Math.random()*1000);
        }
        email = arg0;
        type(By.name("email"), arg0+"@codeclouds.com");
    }

    @And("user has added the shipping information")
    public void userHasAddedTheShippingInformation(DataTable dataTable) {
        Map<String, String> dataMap = dataTable.asMaps().get(0);
        type(By.name("firstName"),dataMap.get("First Name"));
        type(By.name("lastName"), dataMap.get("Last Name"));
        type(By.name("phone"), dataMap.get("Phone Number"));

        type(By.name("shippingAddress1"),dataMap.get("Address"));
        type(By.name("shippingCity"), dataMap.get("City"));
        type(By.name("shippingZip"), dataMap.get("Zip Code"));
//        scroll(By.tagName("button"));
        select(By.name("shippingState"), dataMap.get("State"));
    }


    @Then("user has typed the {} and  {}")
    public void userHasTypedTheAnd(String arg0, String arg1) throws InterruptedException {
        SeleniumDriver.getWaitDriver().until(ExpectedConditions.urlContains("checkout"));

        Thread.sleep(1000);
//        SeleniumDriver.getWaitDriver().until(driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
        if (arg0.startsWith("4")){
            type(By.name("creditCardNumber"), "41");
            click(By.id("nomaster"));
            type(By.name("creditCardNumber"), "41");
            click(By.id("nomaster"));
        }

        type(By.name("creditCardNumber"), arg0);
        if(!(arg1.equals("Master")||(arg0.startsWith("4")))) try {
            click(By.id("nomaster"));
        } catch (Exception e) {
            log.info("Discount popup not coming");
        }
        select(By.name("expmonth"), "(05) May");
        select(By.name("expyear"), "2030");
        type(By.name("CVV"), "123");

    }

//    @Then("user has entered the {}")
//    public void userHasEnteredThe(String arg0) throws InterruptedException {
//        Thread.sleep(1000);
//        type(By.name("creditCardNumber"), arg0);
////        type(By.name("creditCardNumber"), arg0);
//        click(By.id("nomaster"));
//
////        try {
////            click(By.id("nomaster"));
////        } catch (Exception e){
////            log.info("Discount popup not coming");
////        }
//        select(By.name("expmonth"), "(05) May");
//        type(By.name("expyear"), "2030");
//        type(By.name("CVV"), "123");
//    }

    @And("user has selected the order type as {} and payment type as {}")
    public void userHasSelectedTheOrderTypeAsAndPaymentTypeAs(String arg0, String arg1) {
       log.info("Order type is: "+arg0);
       log.info(("Payment type is: "+arg1));
    }

    @Then("user submits the order")
    public void userSubmitsTheOrder() throws InterruptedException {
        submit(By.tagName("button"));

    }


    @And("collect the order information from url")
    public void collectTheOrderInformationFromUrl() {
        getOrderID();
        emailList.add(email);
    }


    @And("user has submitted the form")
    public void userHasSubmittedTheForm() {
        submit(By.xpath("//button[text()='Continue']"));

    }

    @And("user has entered the billing information")
    public void userHasEnteredTheBillingInformation(DataTable dataTable) {
    click(By.xpath("//span[@class='cust_check']"));
//        click(By.xpath("//*[@id='payment-form']/label"));
    Map<String, String> billData = dataTable.asMaps().get(0);
    type(By.name("billingFirstName"), billData.get("First Name"));
    type(By.name("billingLastName"), billData.get("Last Name"));
    type(By.name("billingZip"), billData.get("Zip Code"));
    type(By.name("billingAddress1"), billData.get("Address"));
    type(By.name("billingCity"), billData.get("City"));
    select(By.name("billingState"), billData.get("State"));

    }


    @Then("user opens a new tab")
    public void userOpensANewTab() {
    driver.switchTo().newWindow(WindowType.TAB);
    }

    @Then("user returns to the previous tab")
    public void userReturnsToThePreviousTab() {
   String parentTab = ((String)driver.getWindowHandles().toArray()[0]);
    driver.switchTo().window(parentTab);
    }

    @Then("user submits the orderType")
    public void userSubmitsTheOrderType() {
        submit(By.tagName("button"));
    }
}
