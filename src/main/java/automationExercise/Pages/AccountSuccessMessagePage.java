package automationExercise.Pages;
import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.ElementActions;

public class AccountSuccessMessagePage {
    private WebDriver driver;



   // Locators

    private By getAccountCreated = By.xpath("//h2[@data-qa='account-created']");
    private By clickOnCreateButton = By.cssSelector("a[data-qa=continue-button]");





    public AccountSuccessMessagePage clickContinueButton() {
        ElementActions.click(driver, clickOnCreateButton);
        return this;
    }



    public AccountSuccessMessagePage assertAccountCreatedMessageIsDisplayed() {
        Assert.isTrue(driver.findElement(getAccountCreated).isDisplayed(), "ACCOUNT CREATED!not found");
        return this;

    }
    public AccountSuccessMessagePage(WebDriver driver) {
        this.driver = driver;
    }


}
