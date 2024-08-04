package com.fitpeo;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class RevenueCalculatorPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By slider = By.xpath("//span[@class='MuiSlider-rail css-3ndvyc']"); 
    private By textField =  By.xpath("//input[@class='MuiInputBase-input MuiOutlinedInput-input MuiInputBase-inputSizeSmall css-1o6z5ng']");
    private By cptCheckboxes = By.xpath("//input[@type='checkbox' and @class='PrivateSwitchBase-input css-1m9pwf3']");
    private By totalReimbursementHeader = By.xpath("(//p[@class='MuiTypography-root MuiTypography-body1 inter css-hocx5c'])[4]"); 
   
    public RevenueCalculatorPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    

       
        

    public void selectCPTCodes() {
    	WebElement cptCheckboxesSection = wait.until(ExpectedConditions.visibilityOfElementLocated(cptCheckboxes));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", cptCheckboxesSection);

        // Select the checkboxes for specified CPT codes
        List<WebElement> checkboxes = driver.findElements(cptCheckboxes);
        for (WebElement checkbox : checkboxes) {
            String checkboxValue = checkbox.getAttribute("value");
            if ("99091".equals(checkboxValue) || "99453".equals(checkboxValue) ||
                "99454".equals(checkboxValue) || "99474".equals(checkboxValue)) {
                if (!checkbox.isSelected()) {
                    checkbox.click();
                }
            }
        }

    }

    public String getTotalRecurringReimbursement() {
    	WebElement totalReimbursementElement = wait.until(ExpectedConditions.visibilityOfElementLocated(totalReimbursementHeader));
        String totalReimbursementText = totalReimbursementElement.getText();
		return totalReimbursementText;
        
    }
}
