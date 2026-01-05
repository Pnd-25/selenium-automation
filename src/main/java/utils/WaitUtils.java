package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {

    public static WebElement waitForVisible(WebDriver driver, By locator) {
        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(30));
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );
    }

    public static WebElement waitForClickable(WebDriver driver, By locator) {
        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(30));
        return wait.until(
                ExpectedConditions.elementToBeClickable(locator)
        );
    }
}
