package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WaitUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ProductDetailsPage {

    private static final Logger log =
            LogManager.getLogger(ProductDetailsPage.class);

    private WebDriver driver;

    // Robust product title locator
    private By productTitle =
            By.xpath("//span[@id='productTitle' or @id='title']");

    // Robust Add to Cart locator
    private By addToCartBtn =
            By.xpath("//input[@id='add-to-cart-button' or @name='submit.addToCart']");

    public ProductDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isProductPageLoaded() {
        log.info("Validating product details page");
        return WaitUtils.waitForVisible(driver, productTitle).isDisplayed();
    }

    public String getProductTitle() {
        String title = WaitUtils.waitForVisible(driver, productTitle).getText();
        log.info("Product title captured: {}", title);
        return title;
    }

    public boolean isAddToCartDisplayed() {
        return WaitUtils.waitForVisible(driver, addToCartBtn).isDisplayed();
    }
}
