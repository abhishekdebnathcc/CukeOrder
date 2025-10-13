package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.interactions.Actions;
import utils.BasePage;

import java.util.List;

public class ChampSteps extends BasePage {
    @When("user gives {string} and {string} and logs in")
    public void userGivesAndAndLogsIn(String arg0, String arg1) throws InterruptedException {
        type(By.name("userName"), arg0).sendKeys(Keys.TAB, arg1, Keys.ENTER);
        Thread.sleep(5000);
        driver.get("https://crm.checkoutchamp.com/customer/cs/");
    }

    @Then("user provides the orders")
    public void userProvidesTheOrders(DataTable dataTable) throws InterruptedException {
        List<String> dataList = dataTable.asLists().get(0);
        for (String orderNo: dataList){
            type(By.name("clientOrderId"), orderNo);
            click(By.xpath("//*[text()='Search Orders']"));
            Thread.sleep(2000);
            scroll(By.xpath("//*[text()='Customers']"));
            Thread.sleep(1000);
            click(By.xpath("//*[@class='rowToggle']"));
            Thread.sleep(1000);
            click(By.xpath("//*[text()='"+orderNo+"']"));
            Thread.sleep(2000);
            new Actions(driver).keyDown(Keys.CONTROL).sendKeys(Keys.SUBTRACT).keyUp(Keys.CONTROL).perform();
            Thread.sleep(1000);
            scroll(By.tagName("h4"));
            driver.switchTo().newWindow(WindowType.TAB);
            driver.get("https://crm.checkoutchamp.com/customer/cs/");

        }
    }
}
