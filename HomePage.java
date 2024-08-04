package com.fitpeo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locator for the Revenue Calculator link
    private By revenueCalculatorLink = By.xpath("//div[contains(text(), 'Revenue Calculator')]"); 

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public RevenueCalculatorPage navigateToRevenueCalculatorPage() {
        WebElement revenueCalculator = wait.until(ExpectedConditions.elementToBeClickable(revenueCalculatorLink));
        revenueCalculator.click();
        return new RevenueCalculatorPage(driver);
		
    }
}
