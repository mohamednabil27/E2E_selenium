package automationExercise.Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import utilities.JsonFileManager;
import utilities.ElementActions;

public class MainMenuPage {
    private WebDriver driver;
    private JsonFileManager testData;

    // Locators
    private By deleteAccountButton = By.cssSelector("a[href='/delete_account']");
    private By loggedInAsUsername = By.cssSelector("a>b");

    public MainMenuPage(WebDriver driver) {
        this.driver = driver;
        testData = new JsonFileManager("src/test/resources/TestDataJsonFiles/TestData.json");
    }



    @Step("Step 8 : Click on Delete Account Button")
    public MainMenuPage deleteAccount() {
        ElementActions.click(driver, deleteAccountButton);
        return this;
    }

    @Step("Step 7 : Assert on Logged In As User Name ")
    public MainMenuPage assertOnLoggedInAsUserName() {
        Assert.assertEquals(driver.findElement(loggedInAsUsername).getText(),
                testData.getjsonTestData("username"),
                "Username not as expected!");
        return this;
    }
}