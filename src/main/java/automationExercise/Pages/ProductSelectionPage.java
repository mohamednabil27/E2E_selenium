package automationExercise.Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ProductSelectionPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators
    private final By productsMenu = By.cssSelector("a[href='/products']");
    private final By firstProduct = By.cssSelector(".productinfo.text-center:first-child");
    private final By addToCartBtn = By.cssSelector(".product-overlay .add-to-cart");
    private final By viewCartModal = By.cssSelector("div.modal-content a[href='/view_cart']");
    private final By continueShoppingBtn = By.cssSelector(".close-modal");

    public ProductSelectionPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public ProductSelectionPage navigateToProducts() {
        clickWithRetry(productsMenu, 3);
        return this;
    }

    public ProductSelectionPage selectFirstProduct() {
        hoverAndClick(firstProduct, addToCartBtn);
        handlePopup();
        return this;
    }

    public CheckoutPage proceedToCheckout() {
        clickWithRetry(viewCartModal, 2);
        return new CheckoutPage(driver);
    }

    private void hoverAndClick(By elementToHover, By elementToClick) {
        WebElement product = wait.until(ExpectedConditions.visibilityOfElementLocated(elementToHover));
        new Actions(driver)
                .moveToElement(product)
                .pause(500)
                .perform();
        clickWithRetry(elementToClick, 2);
    }

    private void handlePopup() {
        try {
            clickWithRetry(continueShoppingBtn, 1);
        } catch (TimeoutException e) {
            System.out.println("No popup appeared");
        }
    }

    private void clickWithRetry(By locator, int maxAttempts) {
        for (int attempt = 0; attempt < maxAttempts; attempt++) {
            try {
                WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
                element.click();
                return;
            } catch (StaleElementReferenceException | ElementClickInterceptedException e) {
                if (attempt == maxAttempts - 1) throw e;
                wait.until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(driver.findElement(locator))));
            }
        }
    }
}