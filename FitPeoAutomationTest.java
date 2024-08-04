package com.fitpeo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class FitPeoAutomationTest {
    private WebDriver driver;
    private HomePage homePage;
    private RevenueCalculatorPage calculatorPage;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        homePage = new HomePage(driver);
        
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("extent-report.html");
        ExtentReports extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Test
    public void testRevenueCalculator() throws Throwable  {
    	
    	ExtentReports extentReports = new ExtentReports();
		ExtentTest extentTest = extentReports.createTest("testRevenueCalculator");
        // Navigate to FitPeo Homepage
        driver.get("https://www.fitpeo.com"); // Use the actual FitPeo homepage URL

        // Navigate to the Revenue Calculator Page
        calculatorPage = homePage.navigateToRevenueCalculatorPage();

        // Scroll Down to the Slider section
        By sliderElementLocator = By.xpath("//span[@class='MuiSlider-rail css-3ndvyc']");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement sliderElement = wait.until(ExpectedConditions.presenceOfElementLocated(sliderElementLocator));
        //int yCoordinate = sliderElement.getLocation().getY();
        try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", sliderElement);
        
        
         Thread.sleep(5000);

        // Adjust the Slider
         WebElement slider = driver.findElement(By.xpath("//span[@class='MuiSlider-rail css-3ndvyc']"));
         moveSliderToValue(slider);
         Thread.sleep(5000);
         
        
         // Update the Text Field
         
         
         
        // Validate Slider Value
        WebElement textFieldElement1 = driver.findElement(By.xpath("//input[@id=':R57alklff9da:']"));
        textFieldElement1.click();

        // Enter the value "560" in the text field
        textFieldElement1.clear();
        textFieldElement1.sendKeys("560");
        
        //Assert.assertEquals(textFieldElement1.getAttribute("value"), "560", "Text field value did not update correctly");

        // Select CPT Codes
        calculatorPage.selectCPTCodes();

        // Validate Total Recurring Reimbursement
        String totalReimbursement = calculatorPage.getTotalRecurringReimbursement();
        Assert.assertEquals(totalReimbursement, "$110700", "Total recurring reimbursement is not as expected");
    
        extentTest.pass("Test passed successfully.");
    }
    
    
    

  

	

	private void moveSliderToValue(WebElement slider) throws InterruptedException {
		Actions actions = new Actions(driver);
		
        actions.clickAndHold(slider).moveByOffset(-26,0).release().perform();
        
        
        Thread.sleep(2000);
     
      
		
	}
	
	 

	@AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
