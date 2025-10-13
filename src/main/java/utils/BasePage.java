package utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class BasePage {

    //INITIALIZE A STATIC LOGGER
    public static Logger log = Logger.getLogger(BasePage.class.getName());
    public static WebDriver driver;
    public static ArrayList<String> orderIDList = new ArrayList<>();
    public static ArrayList<String> emailList = new ArrayList<>();


    public static boolean isElementPresent(By element) {
        try {
            SeleniumDriver.getDriver().findElement(element);
        } catch (Throwable t) {
            log.error("Element is not present!!!", t);
            return false;
        }
        return true;
    }


    //COMMON CLICK METHOD INVOKED BY ALL PAGE OBJECTS
    public static void click(By element) {
        if (isElementPresent(element)) {
            try {
                SeleniumDriver.getDriver().findElement(element).click();
                log.info("Clicking on a button: " + SeleniumDriver.getDriver().findElement(element).getText());
            } catch (Exception e) {
                jsClick(element);
            }
        }
    }

    //COMMON CLICK METHOD FOR ELEMENTS IN A LIST ACCESSED BY INDEX
    public static void click(By element, String index) {
        WebElement webElement = SeleniumDriver.getDriver().findElements(element).get(Integer.parseInt(index) - 1);
        if (isElementPresent(element)) {
            try {
                webElement.click();
                log.info("Clicking on an item: " + webElement.getText());
            } catch (Throwable t) {
//               log.info("Trying to click using javascript!");
//               ((JavascriptExecutor)SeleniumDriver.getDriver()).executeScript("arguments[0].click();", webElement);
                jsClick(element, index);
            }
        }
    }

    //COMMON CLICK METHOD FOR JAVASCRIPT BUTTONS
    public static void jsClick(By element) {
        WebElement webElement = SeleniumDriver.getDriver().findElement(element);
        if (isElementPresent(element)) {
            ((JavascriptExecutor) SeleniumDriver.getDriver()).executeScript("arguments[0].click();", webElement);
            log.info("Clicking on button: " + webElement.getText());
        }
    }

    //COMMON CLICK METHOD FOR JAVASCRIPT BUTTONS USING INDEX
    public static void jsClick(By element, String index) {
        if ((isElementPresent(element))) {
            WebElement webElement = SeleniumDriver.getDriver().findElements(element).get(Integer.parseInt(index) - 1);
            ((JavascriptExecutor) SeleniumDriver.getDriver()).executeScript("arguments[0].click();", webElement);
            log.info("Clicking on an item: " + webElement.getText());
        }
    }


    //COMMON TYPE METHOD
//    public static void type(WebElement element, String value) {
//        element.sendKeys(value);
//        if (element.getAttribute("placeholder").equals("Enter Your Phone no")) {
//            log.info("Typing the Phone no: " + value);
//
//        } else {
//            log.info("Typing the " + element.getAttribute("placeholder") + ": " + value);
//        }
//    }

    public static WebElement type(By element, String value) {
        WebElement webElement = SeleniumDriver.getDriver().findElement(element);
        webElement.clear();
        if (isElementPresent(element)) {

            webElement.sendKeys(value);
//        element.sendKeys(value);

            if (value.equals("2030")) {
                log.info("Typing the expiry year: " + value);
            } else if (SeleniumDriver.getDriver().findElement(element).getAttribute("placeholder").equals("Enter Your Phone no")) {
                log.info("Typing the Phone no: " + value);

            } else {
                log.info("Typing the " + SeleniumDriver.getDriver().findElement(element).getAttribute("placeholder") + ": " + value);
            }
        }
        return webElement;
    }

    public static void select(By element, String value) {
        if (isElementPresent(element)) {

            WebElement webElement = SeleniumDriver.getDriver().findElement(element);
            Select select = new Select(webElement);
            if (value.equals("+")) {
                int noOfOptions = select.getOptions().size();
                select.selectByIndex(randomNoOfDropdownSize(noOfOptions));
            } else {
                new Select(webElement).selectByVisibleText(value);

//        elementName = webElement.getAttribute("placeholder");
                log.info("Selecting the option: " + value);
            }
        }
    }

    private static int randomNoOfDropdownSize(int noOfOptions) {
        int number = 0;
        while (true) {
            number = (int) (Math.random() * 100);
            if (number<noOfOptions){
                break;
            }
        }
        return number;
    }

    public static void submit(By element) {
        if (isElementPresent(element)) {
            SeleniumDriver.getDriver().findElement(element).submit();
            log.info("Submitting the form: " + SeleniumDriver.getDriver().findElement(element).getText());
        }
    }

    public static void scroll(By element) {
        WebElement webElement = SeleniumDriver.getDriver().findElement(element);
        WebDriver driver = SeleniumDriver.getDriver();
        if (isElementPresent(element)) {
//            new Actions(SeleniumDriver.getDriver()).moveToElement(webElement).perform();
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", webElement);

        }
    }

    public static void getOrderID() {
        SeleniumDriver.getWaitDriver().until(ExpectedConditions.urlContains("thank-you"));
        String currentURL = SeleniumDriver.getDriver().getCurrentUrl();
        String[] orderContents = currentURL.split("=");
        String orderID = "";
        for (int i = 0; i < orderContents.length; i++) {
            if (orderContents[i].endsWith("prospect_id")) {
                log.info("Prospect ID is: " + orderContents[i + 1].split("&")[0]);
            } else if (orderContents[i].endsWith("order_id")) {
                orderID = orderContents[i + 1].split("&")[0];
                log.info("Order ID is: " + orderID);
//                log.info("Order ID is: " + orderContents[i + 1].split("&")[0]);
                break;
            }
        }
        if (orderIDList != null) {
            orderIDList.add(orderID);
        }
    }

    public static String randomNoFrom1to20() {
        int no = 0;
        while (true) {
            if (no > 0 && no < 21) {
                break;
            }
            no = (int) (Math.random() * 100);
        }
        return String.valueOf(no);
    }


}

