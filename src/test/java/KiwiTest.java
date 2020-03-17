import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Config;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class KiwiTest extends TestBase {

    @BeforeAll
    static void configuration() {
        Configuration.baseUrl = "https://www.kiwi.com/en";
        Configuration.startMaximized = true;
    }

    @RepeatedTest(100)
    void itShouldSuggestDestinationAndDisplayPriceOnDetail() {
        open("");
        setConsentCookieAndRefresh();
        System.out.println("ahoj ako sa mas?");

        $(byText("Explore")).waitUntil(visible, 5000).click();
        $(byAttribute("data-test", "PictureCard"))
                .waitUntil(Condition.visible, 10000)
                .click();
        $(byAttribute("data-test", "ResultCardPrice"))
                .waitUntil(Condition.visible, 10000)
                .shouldNotBe(Condition.empty);

        $(byAttribute("data-test", "ResultCardBadges")).click();
        $(byAttribute("data-test", "TripPopupWrapper")).shouldBe(visible);
        $(byAttribute("data-test", "DetailBookingButton")).shouldBe(enabled);
    }

    @Test
    void itShouldDisableButtonWhenNoLocationSelected() {
        open("");
        setConsentCookieAndRefresh();
        $(byAttribute("data-test", "SearchPlaceField-origin"))
                .find(byAttribute("data-test", "PlacePickerInputPlace-close"))
                .click();
        $(byAttribute("data-test", "LandingSearchButton")).shouldBe(disabled);
    }

    @Test
    void itShouldChangeCurrencyAndDisplayPrice() {
        open("");
        setConsentCookieAndRefresh();
        $$(byAttribute("data-test", "SearchField-input"))
                .get(1)
                .setValue("Tokyo");
        $(byAttribute("data-test", "PlacePickerRow-airport")).click();
        $(byAttribute("data-test", "LandingSearchButton")).click();

        $(byAttribute("data-test", "ResultCardPrice"))
                .waitUntil(Condition.visible, 15000)
                .shouldNotBe(Condition.empty);

        $(byAttribute("data-test", "Currency")).click();
        $(byAttribute("data-test", "Currency-Item-rub")).click();
        $(byAttribute("data-test", "ResultCardPrice"))
                .should(matchText(".* р\\."));
    }

    private void setConsentCookieAndRefresh() {
        Cookie cookie = new Cookie("cookie_consent", "agreed");
        WebDriverRunner.getWebDriver().manage().addCookie(cookie);
        refresh();
    }
}
