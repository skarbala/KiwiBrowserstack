import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TestBase {

    private static DesiredCapabilities caps;

    public static DesiredCapabilities getCapabilities() {
        return caps;
    }

    @BeforeEach
    public void setSessionId(TestInfo testInfo) {
        caps = new DesiredCapabilities();
        caps.setCapability("name", testInfo.getDisplayName());
        caps.setCapability("build", "BuildName 123");
    }

    @AfterEach
    public void tearDown() {
        WebDriverRunner.getWebDriver().quit();
    }
}
