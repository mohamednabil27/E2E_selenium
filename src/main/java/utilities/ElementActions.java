package utilities;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class ElementActions {
      private static int defaultWatingTime = 5;

    @Step("Click on Element: {elementLocator}")
    public static void click(WebDriver driver, By elementLocator) {
        // Single combined wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(defaultWatingTime));

        // Preferred solution (cleaner and safer)
        wait.until(ExpectedConditions.elementToBeClickable(elementLocator)).click();
    }

    public static void assertTrueOnElement(WebDriver driver, By elementLocator, String text){
        Assert.assertTrue(driver.findElement(elementLocator).getText().contains(text));
    }

    public static void assertEqualOnElement(WebDriver driver, By elementLocator, String text){
        Assert.assertEquals(driver.findElement(elementLocator).getText(),text);
    }

    public static void dropDownSelect(WebDriver driver, By elementLocator, String value){
        new Select(driver.findElement(elementLocator)).selectByVisibleText(value);

    }

}
