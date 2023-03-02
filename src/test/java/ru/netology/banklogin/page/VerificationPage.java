package ru.netology.banklogin.page;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private SelenideElement codeField = $("[data-test-id=code] input");
    private SelenideElement verifyButton = $("[data-test-id=action-verify]");
    private SelenideElement errorNotification = $(".notification__content");

    public void verifyVerificationPage() {
        codeField.shouldBe(visible);
    }

    public void verifyErrorNatification() {
        errorNotification.shouldBe(visible);
    }

    public DashboardPage validVerify(String verificationcode) {
        verify(verificationcode);
        return Selenide.page(DashboardPage.class);
    }

    private void verify(String verificationcode) {
        codeField.setValue(verificationcode);
        verifyButton.click();
    }


}

