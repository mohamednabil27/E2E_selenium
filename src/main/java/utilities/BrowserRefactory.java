package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;

public class BrowserRefactory {
    private static WebDriver driver;
    private static final URL gridUrl;

    static {
        try {
            gridUrl = new URL("http://localhost:4444/wd/hub");
        } catch (MalformedURLException e) {
            throw new RuntimeException("Grid URL is invalid", e);
        }
    }

    public static WebDriver initiateDriver(String browserName, boolean maximize, boolean headless) {

        switch (browserName.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                if (headless) {
                    chromeOptions.addArguments("--headless=new");
                }
                chromeOptions.addArguments(
                        "--disable-notifications",
                        "--disable-popup-blocking",
                        "--no-sandbox",
                        "--disable-dev-shm-usage",
                        "--remote-allow-origins=*",
                        "--disable-gpu",
                        "--window-size=1920,1080"
                );
                driver = new RemoteWebDriver(gridUrl, chromeOptions);
                break;

            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (headless) {
                    System.out.println("Initializing Firefox in Headless mode");
                    firefoxOptions.addArguments("--headless");
                }
                driver = new RemoteWebDriver(gridUrl, firefoxOptions);
                break;

            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                if (headless) {
                    System.out.println("Initializing Edge in Headless mode");
                    edgeOptions.addArguments("--headless=new");
                }
                driver = new RemoteWebDriver(gridUrl, edgeOptions);
                break;

            default:
                throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }

        if (maximize) {
            System.out.println("Maximizing browser window");
            driver.manage().window().maximize();
        } else {
            System.out.println("Window size: " + driver.manage().window().getSize());
        }

        return driver;
    }
}