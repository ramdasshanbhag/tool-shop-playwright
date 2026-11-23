# Tool Shop — Playwright + Java (Page Object Model)

UI test automation for the Tool Shop practice site
(`https://practicesoftwaretesting.com`) using **Playwright for Java 1.60.0**,
**JUnit 5**, and the **Page Object Model** pattern.

## Layout

```
tool-shop-playwright/
├── pom.xml
└── src/test/java/com/toolshop/
    ├── pages/                 # Page Objects — one class per page
    │   ├── BasePage.java       # base URL + shared helpers
    │   ├── HomePage.java       # product grid + search
    │   ├── LoginPage.java      # /auth/login
    │   └── ProductPage.java    # product detail + add to cart
    └── tests/                  # Tests — call page objects, hold assertions
        ├── BaseTest.java        # Playwright lifecycle + per-test context
        ├── LoginTest.java
        └── SearchTest.java
```

## Prerequisites

- **JDK 17+**
- **Maven 3.8+**

## First-time setup

Install the Playwright browsers (one time):

```bash
mvn clean compile
mvn exec:java -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="install"
```

> Playwright Java also auto-downloads browsers on first run, so this step is
> optional but makes the first `mvn test` faster.

## Run the tests

```bash
mvn test                    # headless (default)
mvn test -Dheaded=true      # watch the browser
mvn test -Dtest=LoginTest   # one class
mvn test -DbaseUrl=https://your-env.example.com   # point at another environment
```

## How the pattern works

- **Page objects** (`pages/`) own the locators and the actions for one page.
  Tests never call `page.click(...)` directly — they call methods like
  `login(email, password)` or `search("hammer")`.
- **Tests** (`tests/`) own the **assertions** and read like plain steps.
- Locators use `getByTestId(...)`, configured in `BaseTest` to read the site's
  `data-test` attribute. Prefer these stable hooks over CSS/XPath tied to layout.

## Notes on locators

This site is updated from time to time. If a test fails to find an element,
open the live page, confirm the `data-test` value, and update the matching
page object — that single change fixes every test that uses it (the whole point
of POM). Current values assumed here include `email`, `password`,
`login-submit`, `login-error`, `search-query`, `search-submit`, `product-name`,
`unit-price`, `add-to-cart`, and `nav-sign-in`.
