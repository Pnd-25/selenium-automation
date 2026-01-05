package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.SearchResultsPage;
import pages.ProductDetailsPage;
import utils.ScreenshotUtil;
@Listeners(listeners.ExtentTestListener.class)

public class AmazonProductFlowTest extends BaseTest {

    @Test
    public void validateProductDetailsPage() {

        HomePage homePage = new HomePage(driver);
        SearchResultsPage resultsPage = new SearchResultsPage(driver);
        ProductDetailsPage productPage = new ProductDetailsPage(driver);

        // 1. Handle Amazon location popup if present
        homePage.dismissLocationPopupIfPresent();

        // 2. Search for a product
        homePage.searchProduct("laptop");

        // 3. Click the first product from search results
        resultsPage.clickFirstProduct();

        // 4. Switch to new tab if product opens in a new window
        String parentWindow = driver.getWindowHandle();
        for (String window : driver.getWindowHandles()) {
            if (!window.equals(parentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }

        // 5. Assert Product Details Page is loaded
        Assert.assertTrue(
                productPage.isProductPageLoaded(),
                "Product details page did not load correctly"
        );

        // 6. Optional: Validate product title (partial, safe assertion)
        String productTitle = productPage.getProductTitle();
        Assert.assertTrue(
                productTitle.toLowerCase().contains("laptop")
                        || productTitle.toLowerCase().contains("macbook"),
                "Unexpected product title: " + productTitle
        );

        // 7. Capture screenshot after successful validation
        ScreenshotUtil.takeScreenshot(driver, "ProductDetailsPage");
    }
}
