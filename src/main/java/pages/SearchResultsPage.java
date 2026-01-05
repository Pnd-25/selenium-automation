package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchResultsPage {

    private WebDriver driver;

    // Organic (non-sponsored) product links only
    private By organicProductLinks =
            By.xpath("//a[contains(@href,'/dp/') and not(contains(@href,'/sspa/'))]");

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickFirstProduct() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Wait for products to be present
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(organicProductLinks));

        List<WebElement> products = driver.findElements(organicProductLinks);

        for (int i = 0; i < products.size(); i++) {
            try {
                WebElement product = products.get(i);

                if (product.isDisplayed()) {

                    // Scroll into view
                    ((JavascriptExecutor) driver)
                            .executeScript("arguments[0].scrollIntoView({block:'center'});", product);

                    // JS click avoids stale issues
                    ((JavascriptExecutor) driver)
                            .executeScript("arguments[0].click();", product);

                    return;
                }
            } catch (StaleElementReferenceException e) {
                // Re-locate elements and retry
                products = driver.findElements(organicProductLinks);
            }
        }

        throw new RuntimeException("No clickable organic product found");
    }
}
