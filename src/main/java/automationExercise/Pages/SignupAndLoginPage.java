package automationExercise.Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import utilities.ElementActions;

public class SignupAndLoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By nameField = By.cssSelector("input[name=name]");
    private By emailField = By.xpath("//input[@data-qa='signup-email']");
    private By signupButton = By.xpath("//button[@data-qa='signup-button']"); // Fixed typo

    public SignupAndLoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Step 3 : fill in username, mail & click on signup button")
    public void fillAccountInformation(String name, String Mail) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameField)).sendKeys(name);
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(Mail);
        wait.until(ExpectedConditions.elementToBeClickable(signupButton)).click();
    }
}