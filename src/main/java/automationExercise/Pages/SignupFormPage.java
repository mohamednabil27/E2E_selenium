package automationExercise.Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class SignupFormPage {
    WebDriver driver;
    private WebDriverWait wait;

    ////   Locators  \\\\
    private final By gender = By.id("id_gender1");
    private final By passDataToPasswordField = By.cssSelector("input.form-control[id=password]");
    private final By newsLetterCheckBox = By.id("newsletter");
    private final By optionCheckBox = By.id("optin");
    private final By firstNamefield = By.id("first_name");
    private final By lastNameField = By.id("last_name");
    private final By CompanyName = By.id("company");
    private final By address1 = By.xpath("//input[@data-qa='address']");
    private final By address2 = By.xpath("//input[@data-qa='address2']");
    private final By country = By.id("country");
    private final By stateFiled = By.xpath("//input[@name='state']");
    private final By cityField = By.xpath("//input[@name='city']");
    private final By zippcode = By.id("zipcode");
    private final By mobileNummber = By.id("mobile_number");
    private final By clickOnCreateAccoiuntButton = By.xpath("//button[@data-qa='create-account']");

    public SignupFormPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    ////   Enhanced Actions  \\\\
    @Step("Step 6: Fill Address Information")
    public SignupFormPage fillAddressInformation(String password, String firstname, String lastName, String company, String add1,
                                                 String add2, String contry, String Stat, String City, String zip, String fonNumber) {

        waitAndClick(gender);
        waitAndSendKeys(passDataToPasswordField, password);
        waitAndSendKeys(firstNamefield, firstname);
        waitAndSendKeys(lastNameField, lastName);
        checkCheckbox(newsLetterCheckBox);
        checkCheckbox(optionCheckBox);
        waitAndSendKeys(CompanyName, company);
        waitAndSendKeys(address1, add1);
        waitAndSendKeys(address2, add2);
        selectCountryByText(contry);
        waitAndSendKeys(stateFiled, Stat);
        waitAndSendKeys(cityField, City);
        waitAndSendKeys(zippcode, zip);
        waitAndSendKeys(mobileNummber, fonNumber);

        clickCreateAccountWithRetry();
        return this;
    }

    private void clickCreateAccountWithRetry() {
        final int MAX_ATTEMPTS = 3;
        for (int attempt = 0; attempt < MAX_ATTEMPTS; attempt++) {
            try {
                handleOverlays();
                WebElement button = wait.until(ExpectedConditions.presenceOfElementLocated(clickOnCreateAccoiuntButton));
                scrollIntoView(button);
                button.click();
                return;
            } catch (StaleElementReferenceException | ElementClickInterceptedException | TimeoutException e) {
                if (attempt == MAX_ATTEMPTS - 1) throw e;
                System.out.println("Retrying click attempt " + (attempt + 1));
                refreshPage();
            }
        }
    }

    private void handleOverlays() {
        try {
            // Remove common ad containers
            List<WebElement> ads = driver.findElements(
                    By.cssSelector("div[id^='aswift'], div[id^='google_ads_iframe'], div[class*='adsbygoogle']")
            );
            ads.forEach(ad -> {
                if (ad.isDisplayed()) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].remove()", ad);
                }
            });
        } catch (Exception e) {
            System.out.println("No overlays found: " + e.getMessage());
        }
    }

    private void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior: 'auto', block: 'center', inline: 'center'})",
                element
        );
    }

    private void refreshPage() {
        driver.navigate().refresh();
        wait.until(d -> ((JavascriptExecutor) d)
                .executeScript("return document.readyState").equals("complete"));
    }

    ////   Improved Helper Methods  \\\\
    private void checkCheckbox(By checkboxLocator) {
        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(checkboxLocator));
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    private void waitAndClick(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            handleOverlays();
            element.click();
        }
    }

    private void waitAndSendKeys(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
    }

    private void selectCountryByText(String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(country));
        new Select(element).selectByVisibleText(text);
    }

    ////   Restored Method  \\\\
    @Step("Step 5: Select Date Of Birth")
    public SignupFormPage SelectDateOfBirth(String day, String month, String year) {
        selectDropdownByValue(By.id("days"), day);
        selectDropdownByText(By.id("months"), month);
        selectDropdownByValue(By.id("years"), year);
        return this;
    }
    private void selectDropdownByValue(By locator, String value) {
        WebDriverWait extendedWait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement element = extendedWait.until(ExpectedConditions.presenceOfElementLocated(locator));
        new Select(element).selectByValue(value);
    }

    private void selectDropdownByText(By locator, String text) {
        WebDriverWait extendedWait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement element = extendedWait.until(ExpectedConditions.presenceOfElementLocated(locator));
        new Select(element).selectByVisibleText(text);
    }

    ////   LogoutPage Inner Class (Unchanged)  \\//
    public static class LogoutPage {
        WebDriver driver;
        private WebDriverWait wait;

        private By logoutButton = By.xpath("//a[@href='/logout']");
        private By emailAddressFieldforLogin = By.xpath("//input[@data-qa='login-email']");
        private By passwordField = By.xpath("//input[@data-qa='login-password']");
        private By loginButton = By.xpath("//button[@data-qa='login-button']");

        public LogoutPage(WebDriver driver) {
            this.driver = driver;
            this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        }

        public LogoutPage clickOnLoggoutButton() {
            wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
            return this;
        }

        public LogoutPage loginWithEmailAndPassword(String email, String Password) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(emailAddressFieldforLogin)).sendKeys(email);
            driver.findElement(passwordField).sendKeys(Password);
            wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
            return this;
        }
    }
}