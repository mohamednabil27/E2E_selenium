package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;

public class PageRefactory {
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
        String osInfo = "Operating System: " + System.getProperty("os.name") +
                " (Version: " + System.getProperty("os.version") + ")";

        switch (browserName.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                if (headless) {
                    System.out.println("Initializing Chrome in Headless mode");
                    chromeOptions.addArguments("--headless=new");
                }
                System.out.println("Initializing Chrome Browser on " + osInfo);
                chromeOptions.addArguments(
                        "--disable-notifications",
                        "--disable-popup-blocking",
                        "--no-sandbox"
                );
                driver = new RemoteWebDriver(gridUrl, chromeOptions);
                break;

            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (headless) {
                    System.out.println("Initializing Firefox in Headless mode");
                    firefoxOptions.addArguments("--headless");
                }
                System.out.println("Initializing Firefox Browser on " + osInfo);
                driver = new RemoteWebDriver(gridUrl, firefoxOptions);
                break;

            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                if (headless) {
                    System.out.println("Initializing Edge in Headless mode");
                    edgeOptions.addArguments("--headless=new");
                }
                System.out.println("Initializing Edge Browser on " + osInfo);
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