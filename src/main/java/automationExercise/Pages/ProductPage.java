package automationExercise.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ProductPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By firstItem = By.cssSelector(".productinfo.text-center:first-child img");
    private By addToCart = By.cssSelector(".product-overlay .add-to-cart");
    private By viewCart = By.cssSelector("div.modal-content a[href='/view_cart']");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public ProductPage selectFirstItem() {
        Actions actions = new Actions(driver);
        //hover
        actions.moveToElement(wait.until(ExpectedConditions.visibilityOfElementLocated(firstItem))).perform();
        wait.until(ExpectedConditions.elementToBeClickable(addToCart)).click();
        return this;
    }

    public ProductPage addToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCart)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(viewCart));
        driver.findElement(viewCart).click();
        return this;
    }
}