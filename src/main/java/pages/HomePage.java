package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WaitUtils;

public class HomePage {

    private static final Logger log =
            LogManager.getLogger(HomePage.class);

    private WebDriver driver;

    private By searchBox = By.id("twotabsearchtextbox");
    private By searchButton = By.id("nav-search-submit-button");

    // Location popup dismiss button
    private By dismissPopupBtn = By.xpath("//input[@data-action-type='DISMISS']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        log.info("HomePage initialized");
    }

    public void dismissLocationPopupIfPresent() {
        try {
            log.info("Checking for location popup");
            WaitUtils.waitForClickable(driver, dismissPopupBtn);
            driver.findElement(dismissPopupBtn).click();
            log.info("Location popup dismissed");
        } catch (Exception e) {
            log.info("Location popup not present");
        }
    }

    public void searchProduct(String product) {
        log.info("Starting product search: {}", product);

        dismissLocationPopupIfPresent();

        WaitUtils.waitForVisible(driver, searchBox);
        driver.findElement(searchBox).sendKeys(product);
        log.info("Entered product name in search box");

        driver.findElement(searchButton).click();
        log.info("Clicked search button");
    }
}
