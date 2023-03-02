package ru.netology.banklogin.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.banklogin.data.DataHelper;
import ru.netology.banklogin.data.SQLHelper;
import ru.netology.banklogin.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.banklogin.data.SQLHelper.cleanDataBase;


public class BankloginTest {

    @AfterAll
    static void teardown() { cleanDataBase(); }

    @Test
    @DisplayName("Should successfully login to dashboard with exist login and password from SUT data")
    void ShouldSuccessfulLogin() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfoWithTestData();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.verifyVerificationPage();
        var verificationCode = SQLHelper.getVerificationCode();
        verificationPage.validVerify(verificationCode.getCode());
    }

    @Test
    @DisplayName("Should get error notification if user is not exist in base")
    void ShouldGetErrorNotificationIfLoginWithRandomUserWithoutAddingToBase() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.generateRandomUser();
        loginPage.validLogin(authInfo);
        loginPage.verifyErrorNatification();
    }

    @Test
    @DisplayName("Should get error notification if login with random is exist in base fnd user active and random verification code")
    void ShouldGetErrorNotificationIfLoginWithRandomIsExistInBaseAndActiveAndRandomVerificationCode() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfoWithTestData();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.verifyVerificationPage();
        var verificationCode = DataHelper.generateRandomVerificationCode();
        verificationPage.validVerify(verificationCode.getCode());
        verificationPage.verifyErrorNatification();
    }

}
