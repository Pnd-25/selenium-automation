package base;

import driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

   public WebDriver driver;   // keep it private (best practice)

    @BeforeMethod
    public void setup() {
        driver = DriverFactory.initDriver();
        driver.get("https://www.amazon.in");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // ✅ Getter method
    public WebDriver getDriver() {
        return driver;
    }
}
