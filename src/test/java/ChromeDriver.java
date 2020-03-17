import com.codeborne.selenide.WebDriverProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class ChromeDriver implements WebDriverProvider {

    public static final String USERNAME = "furboslav1";
    public static final String AUTOMATE_KEY = "9DLmpfmorshAiHkejKxi";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

    @Override
    public WebDriver createDriver(DesiredCapabilities desiredCapabilities) {
        DesiredCapabilities caps = TestBase.getCapabilities();
        caps.setCapability("browser", "Chrome");
        caps.setCapability("browser_version", "80.0");
        caps.setCapability("os", "Windows");
        caps.setCapability("os_version", "10");
        caps.setCapability("resolution", "1920x1080");
        URL url = null;
        try {
            url = new URL(URL);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Cannot create browser");
        }
        return new RemoteWebDriver(url, caps);
    }
}
