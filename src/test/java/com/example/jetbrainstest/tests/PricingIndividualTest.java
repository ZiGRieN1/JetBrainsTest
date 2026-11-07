package com.example.jetbrainstest.tests;

import com.example.jetbrainstest.pages.CookieSettingsPage;
import com.example.jetbrainstest.pages.PricingIndividualPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PricingIndividualTest extends BaseTest {

    private PricingIndividualPage pricingIndividualPage;
    private CookieSettingsPage cookieSettingsPage;
    private WebDriverWait wait;


    @BeforeEach
    public void setup() {
        getDriver().get("https://www.jetbrains.com/store/?section=personal&billing=yearly");
        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
        pricingIndividualPage = new PricingIndividualPage(getDriver(), wait);
        cookieSettingsPage = new CookieSettingsPage(getDriver(), wait);
    }

    @Test
    @DisplayName("Проверка, что кнопка покупки пакета на год доступна")
    public void enabledBuyYear() {
        Assertions.assertTrue(pricingIndividualPage.enabledButtonPackYear(),
                "Кнопка покупки пакета на год недоступна");
    }

    @BeforeEach
    @DisplayName("Проверка отображения и принятия согласия по куки")
    public void clickCookie() {
        cookieSettingsPage.displayTitle();
        Assertions.assertTrue(cookieSettingsPage.displayButton(),
                "Кнопка не отображается");
        cookieSettingsPage.clickButtonAcceptAll();
    }

    @Test
    @DisplayName("Проверка, что можно переключиться на страницу")
    public void clickSwitchMonthly() {
        Assertions.assertTrue(pricingIndividualPage.enabledSwitchMonthly(),
                "Не получилось переключиться на страницу месячных подписок");
        pricingIndividualPage.clickSwitchMonthly();
        pricingIndividualPage.NoDisplayTitleSecondYear();
    }

    @Test
    @DisplayName("Проверка, что переключились на вкладку для студентов и учителей")
    public void clickTabStudents() {
        pricingIndividualPage.clickTabStudents();
        Assertions.assertTrue(pricingIndividualPage.displayTitleClassroom(),
                "Заголовок \"For classroom assistance free\" не отобразился");
    }
}
