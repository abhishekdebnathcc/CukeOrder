package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.BasePage;
import utils.SeleniumDriver;

import java.util.Set;

public class SecuritySteps extends BasePage {
    public WebDriver driver;
    public String baseURL;
    public String securityURL;
    public String insecureURL;
    public String checkoutURL;
    public String originalURL;

    @Given("The base url is {string}")
    public void theBaseUrlIs(String arg0) {
        originalURL = arg0;
        baseURL = arg0+"?AFFID=test&C1=testc1&C2=testc2&C3=testc3";
    }

    @When("user has started {}")
    public void userHasStarted(String arg0) {
        if (arg0.equals("")) {
            arg0 = "chrome";
        }
        SeleniumDriver.setupDriver(arg0);
        driver = SeleniumDriver.getDriver();
    }

    @Then("user navigates to security testing {}")
    public void userNavigatesToSecurityTesting(String arg0) {
        if (!arg0.contains("robots.txt")) {
            driver.get(arg0);
        }
        securityURL = arg0;
    }

    @And("user generates the report")
    public void userGeneratesTheReport() throws InterruptedException {
        if (securityURL.equals("https://transparencyreport.google.com/safe-browsing/search?hl=en")) {
//            type(By.xpath("//*[@placeholder='Search by URL']"), baseURL);
            type(By.xpath("//*[@id='mat-tab-nav-panel-0']/safe-browsing-report/ng-component/section/div/search-box/input"), baseURL).sendKeys(Keys.ENTER);
//            click(By.xpath("//*[@class='material-icons']"));
        } else if (securityURL.equals("https://wheregoes.com/")) {
            type(By.name("url"), baseURL.replace("https", "http"));
            click(By.id("form_button"));
        } else if (securityURL.equals("https://pagespeed.web.dev/")) {
            Thread.sleep(2000);
            type(By.xpath("//*[@id='i2']"), baseURL).sendKeys(Keys.ENTER);
//            click(By.xpath("/html/body/c-wiz/div[2]/div/div[2]/form/div[2]/button/span"));
            SeleniumDriver.getWaitDriver().until(ExpectedConditions.urlContains("mobile"));
            String currentURL = driver.getCurrentUrl();
            driver.switchTo().newWindow(WindowType.TAB);
            driver.get(currentURL.replace("mobile", "desktop"));
        } else if (securityURL.equals("https://www.webpagetest.org/")) {
//            new Actions(driver).moveToElement(driver.findElement(By.id("url"))).perform();
            Thread.sleep(1000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[@id='url']")));
            Thread.sleep(1000);

            type(By.xpath("//*[@id='url']"), baseURL).sendKeys(Keys.ENTER);

        } else if (securityURL.equals("https://www.opengraph.xyz/")) {
            Thread.sleep(1000);
//            click(By.xpath("//*[@id='radix-:Re6:']/div[2]/button[1]"));
            try {
                click(By.xpath("//button[text()='Reject All']"));
            } catch (Exception e) {
//                Thread.sleep(1000);
//                type(By.name("website"), baseURL).sendKeys(Keys.ENTER);
////                SeleniumDriver.getWaitDriver().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='headlessui-dialog-panel-:ri:']/button/svg")));
//                Thread.sleep(15000);
//                click(By.xpath("//*[@id='headlessui-dialog-panel-:rh:']/button"));
            }
            type(By.name("website"), baseURL).sendKeys(Keys.ENTER);
//                SeleniumDriver.getWaitDriver().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='headlessui-dialog-panel-:ri:']/button/svg")));
            Thread.sleep(10000);
            click(By.xpath("//*[@id='headlessui-dialog-panel-:rh:']/button"));
            Thread.sleep(1000);
            scroll(By.xpath("//*[@id='__next']/div/div/main/div[2]/div/div/div[2]/div/h2"));
//            type(By.name("website"), baseURL).sendKeys(Keys.ENTER);

//            click(By.xpath("//*[@id='__next']/div/main/section[1]/div/div/div/form/div[2]/button/span"));
        } else if (securityURL.equals("https://tools.keycdn.com/http2-test")) {
            type(By.id("url"), baseURL).sendKeys(Keys.ENTER);
        } else if (securityURL.equals("https://validator.w3.org/nu/")) {
            type(By.id("doc"), baseURL).sendKeys(Keys.ENTER);
            scroll(By.xpath("//*[@id='submit']"));
        } else if (securityURL.equals("https://www.whatsmydns.net/")) {
            type(By.id("q"), baseURL).sendKeys(Keys.ENTER);
            driver.switchTo().newWindow(WindowType.TAB);
            driver.get(securityURL);
            if (!baseURL.contains("www")) {
                type(By.id("q"), new StringBuilder(baseURL).insert(8, "www.").toString()).sendKeys(Keys.ENTER);
            } else {
                type(By.id("q"), baseURL.replace("www.", "")).sendKeys(Keys.ENTER);
            }
        } else if (securityURL.equals("https://www.ssllabs.com/ssltest/")) {
            type(By.xpath("//input[@type='text']"), baseURL).sendKeys(Keys.ENTER);
        } else if (securityURL.equals("https://gtmetrix.com/")) {
            String checkoutURL = "";
            jsClick(By.xpath("//*[@id='user-nav-login']/a"));
            type(By.id("li-email"), "dev@codeclouds.biz");
            type(By.id("li-password"), "c0decl0uds@");
            submit(By.xpath("//*[@id='menu-site-nav']/div[2]/div[1]/form/div[5]/button"));
//            type(By.xpath("/html/body/div[1]/main/article/div[3]/form/div[2]/div[1]"), baseURL).sendKeys(Keys.ENTER);
//            SeleniumDriver.getWaitDriver().until(ExpectedConditions.)
            Thread.sleep(3000);
            type(By.xpath("//div[@class='clearable-input']/input"), baseURL).sendKeys(Keys.ENTER);
            driver.switchTo().newWindow(WindowType.TAB);
            driver.get(securityURL);
//             checkoutURL = baseURL.replace("/?", "/checkout.php?");
            if (baseURL.contains("affid")) {
//            checkoutURL = baseURL.split("/?")[0]+"/checkout.php?AFFID=test&C1=testc1&C2=testc2&C3=testc3";
                checkoutURL = baseURL.replace("/affid=1", "/checkout.php?AFFID=test&C1=testc1&C2=testc2&C3=testc3");
            } else {
                if (!originalURL.endsWith("/")) {
                    originalURL += "/";
                }
                originalURL = originalURL + "checkout.php?AFFID=test&C1=testc1&C2=testc2&C3=testc3";
            }
            type(By.name("url"), originalURL).sendKeys(Keys.ENTER);
        } else if (securityURL.equals("https://www.digicert.com/help/")) {
            type(By.id("host"), baseURL.split("/")[2]).sendKeys(Keys.ENTER);
//        } else if (securityURL.equals("")) {
////            driver.get(baseURL.split("\\?")[0]+securityURL);
        } else if (securityURL.contains("robots.txt")) {
//            System.out.println(baseURL.replace("https", securityURL));
            driver.get(baseURL.replace("https", "http"));
            String firstTab = driver.getWindowHandle();
            driver.switchTo().newWindow(WindowType.TAB);
//            String domainName = baseURL.split("\\?")[0];
            String domainName = baseURL.split("/")[2];
            System.out.println(domainName);
            String robotsURL = "robots.txt";
            if (!domainName.endsWith("/")) {
                robotsURL = "/" + robotsURL;
            }
            System.out.println(robotsURL);
            driver.get("https://" + domainName + robotsURL);
//            driver.switchTo().window(firstTab);
            Set<String> windowhandles = driver.getWindowHandles();
//            for (String handle : windowhandles) {
//                System.out.println(handle);
//            }
            Object[] windows = windowhandles.toArray();
            String[] newSet = windowhandles.toArray(new String[0]);
//            String set[] = (String[]) windows;
            System.out.println((String) windows[0]);
//            driver.switchTo().window((String) windows[0]);

        }

    }

    @And("the checkout url is {string}")
    public void theCheckoutUrlIs(String arg0) {
        this.checkoutURL = arg0;
    }
}

