package base;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
public WebDriver driver;
public WebDriverWait wait;

public BasePage(WebDriver driver) {
	this.driver=driver;
	wait=new WebDriverWait(driver, Duration.ofSeconds(10));
}

//click
public void click(By locator) {
	wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
}
//click
public void click(WebElement element) {
	wait.until(ExpectedConditions.elementToBeClickable(element)).click();
}
//input
public void input(By locator, String text) {
	WebElement inputElement=wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	inputElement.clear();
	inputElement.sendKeys(text);
}

//getText
public String getText(By locator) {
	return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText().trim();
}

//isDisplayed for verification
public boolean isDisplayed(By locator) {
	try {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
	}
	catch(Exception e){
		return false;
	}
	 
}

}
