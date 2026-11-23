package com.toolshop.ui.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LoginPage extends BasePage {

    private final Locator emailInput;
    private final Locator passwordInput;
    private final Locator loginButton;
    private final Locator loginPageInput;

    public LoginPage(Page page) {
        super(page);
        this.emailInput = page.locator("[data-test='email']");
        this.passwordInput = page.locator("[data-test='password']");
        this.loginButton = page.locator("[data-test='login-submit']");
        this.loginPageInput = page.locator("[data-test='login-submit']");
    }

    public void LoginAs(String email, String password) {
        emailInput.fill(email);
        passwordInput.fill(password);
        loginButton.click();
    }

    public void navigateToLoginPage() {
        loginPageInput.click();
    }
}
