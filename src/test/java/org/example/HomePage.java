package org.example;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;

public class HomePage {
    private static final String PAGE_URL = "https://magento.softwaretestingboard.com/";

    private final WebDriver driver;


    @FindBy(id = "email-error")
    private WebElement emailErrorMessage;

    @FindBy(id = "pass-error")
    private WebElement passwordErrorMessage;

    @FindBy(css = "#maincontent > div.page.messages > div:nth-child(2) > div > div > div")
    private WebElement loginErrorMessage;


    private static final Map<String, By> textFields = Map.of(
            "Email Address", By.id("customer-email"),
            "First Name", By.id("H7JPN0M"),
            "Last Name", By.id("NGQLD4Y"),
            "Street Address", By.id("E1HS7LM"),
            "City", By.id("E1HS7LM"),
            "Zip Code", By.id("TGRQ8FM"),
            "Phone Number", By.id("OKD5J77"),
            // in login page
            "Email", By.id("email"),
            "Password", By.id("pass")
    );


    private static final Map<String, By> navigationButtons = Map.of(
            "SignIn", By.cssSelector("body > div.page-wrapper > header > div.panel.wrapper > div > ul > li.authorization-link > a"),
            "Create an Account", By.cssSelector("body > div.page-wrapper > header > div.panel.wrapper > div > ul > li:nth-child(3) > a"),
            "Cart", By.cssSelector("body > div.page-wrapper > header > div.header.content > div.minicart-wrapper > a"),
            "Checkout", By.id("top-cart-btn-checkout"),
            "Side Bar", By.cssSelector("body > div.page-wrapper > header > div.header.content > span"),
            // login page
            "LogIn", By.id("send2"),
            // expand sign out option
            "Expand Sign Out Menu", By.cssSelector("body > div.page-wrapper > header > div.panel.wrapper > div > ul > li.customer-welcome > span > button"),
            "Sign Out", By.cssSelector("body > div.page-wrapper > header > div.panel.wrapper > div > ul > li.customer-welcome.active > div > ul > li.authorization-link > a")
    );


    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void openPage() {
        driver.get(PAGE_URL);
        PageFactory.initElements(driver, this);
    }

    public void closePage() {
        driver.quit();
    }

    public void fillOutField(String field, String text) {
        driver.findElement(textFields.get(field)).sendKeys(text);
    }

    public void clickButton(String button) {
        driver.findElement(navigationButtons.get(button)).click();
    }


    public String getEmailErrorMessage() {
        return emailErrorMessage.getText();
    }

    public String getPasswordErrorMessage() {
        return passwordErrorMessage.getText();
    }


    public String getLoginErrorMessage() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(loginErrorMessage));
            return loginErrorMessage.getText().trim();
        } catch (org.openqa.selenium.StaleElementReferenceException ex) {
            // If the element becomes stale, refresh the reference and try again
            PageFactory.initElements(driver, this);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(loginErrorMessage));
            return loginErrorMessage.getText().trim();
        }
    }

    public String getPageUrl() {
        return driver.getCurrentUrl();
    }

}