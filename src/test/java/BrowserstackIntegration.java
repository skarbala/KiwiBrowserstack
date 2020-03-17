import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class BrowserstackIntegration {

    public static String getRemoteUrl() {
        Config config = ConfigFactory.load("browserstack");
        return "https://" + config.getString("browserstack.name") + ":" + config.getString("browserstack.key") + "@hub-cloud.browserstack.com/wd/hub";
    }
}
