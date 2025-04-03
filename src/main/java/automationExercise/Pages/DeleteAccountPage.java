package automationExercise.Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utilities.ElementActions;
import utilities.JsonFileManager;

public class DeleteAccountPage {

    ///// Locators \\\\\
    private WebDriver driver;
    private JsonFileManager testData;
    private By getAccountDeleteed = By.xpath("//b[text()='Account Deleted!']");
    private By deleteAccountButton = By.cssSelector("a[href='/delete_account']");
    private By loggedInAsUsername = By.cssSelector("a>b");



    ///// Actions \\\\\

    public DeleteAccountPage clickOnDeleteAccountButton() {
        ElementActions.click(driver, deleteAccountButton);

        return this;
    }


    ///// Validations \\\\\


    @Step("Step 9: assert Account Deleted Message IsDisplayed")
    public DeleteAccountPage assertAccountDeletedMessageIsDisplayed() {
        Assert.assertTrue(driver.findElement(getAccountDeleteed).isDisplayed(), "ACCOUNT DELETED! not found");
        System.out.println("Account Deleted message is displayed: " + driver.findElement(getAccountDeleteed).isDisplayed());
        return this;
    }


    public DeleteAccountPage(WebDriver driver) {
        this.driver = driver;
        testData = new JsonFileManager("src/test/resources/TestDataJsonFiles/TestData.json");
    }
}
