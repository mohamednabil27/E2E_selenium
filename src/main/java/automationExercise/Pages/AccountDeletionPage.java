package automationExercise.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountDeletionPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators
    private final By deleteAccountLink = By.cssSelector("a[href='/delete_account']");
    private final By deleteConfirm = By.cssSelector(".btn-danger");
    private final By deletionMessage = By.cssSelector("#delete_message");

    public AccountDeletionPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public AccountDeletionPage navigateToDeleteAccount() {
        wait.until(ExpectedConditions.elementToBeClickable(deleteAccountLink)).click();
        return this;
    }

    public AccountDeletionPage confirmDeletion() {
        wait.until(ExpectedConditions.elementToBeClickable(deleteConfirm)).click();
        return this;
    }

    public void verifyAccountDeleted() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(deletionMessage));
    }
}