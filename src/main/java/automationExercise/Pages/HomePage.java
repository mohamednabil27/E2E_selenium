package automationExercise.Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.ElementActions;

public class HomePage {



    private WebDriver driver;
    public String url = "https://automationexercise.com/ ";

    // Locators
    private By signupOrLoginButton = By.xpath("//a[@href='/login']");



    @Step("Step 1 : get the url {url}")
    public HomePage navigate() {
        System.out.println("navigating to url: " + url);
        driver.navigate().to(url);

        // 2. Delete all cookies
        driver.manage().deleteAllCookies();

        // 3. Force fresh page load
        driver.navigate().refresh();
        return this;
    }


    public HomePage(WebDriver driver) {
        this.driver = driver;

    }

    @Step("Step 2: click on Signup button")
    public HomePage clickOnsignupOrLoginButton() {
        ElementActions.click(driver, signupOrLoginButton);

        return this;
    }





}
