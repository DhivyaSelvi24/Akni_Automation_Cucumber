package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class Login {
    private WebDriver driver;

    public Login(WebDriver driver){
        this.driver = driver;
    }

    // Locators
    private By usernameInput = By.id("username");
    private By passwordInput = By.id("password");
    private By pageDropdown = By.id("go-to-page");
    private By rememberCheckbox = By.id("remember");
    private By loginButton = By.id("login-button");
    private By messageLabel = By.id("login-title"); // 
    private By companyLink = By.id("company-link");

    // Methods
    public void enterUsername(String username){
        driver.findElement(usernameInput).clear();
        driver.findElement(usernameInput).sendKeys(username);
    }

    public void enterPassword(String password){
        driver.findElement(passwordInput).clear();
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void selectPage(String page){
        Select select = new Select(driver.findElement(pageDropdown));
        select.selectByVisibleText(page);
    }

    public void setRememberMe(boolean value){
        if(driver.findElement(rememberCheckbox).isSelected() != value){
            driver.findElement(rememberCheckbox).click();
        }
    }

    public void clickLogin(){
        driver.findElement(loginButton).click();
    }

    public String getMessage(){
        
        return driver.findElement(messageLabel).getText();
    }

    public void verifyMessage(String expected){
        String actual = getMessage();
        if(!actual.equals(expected)){
            throw new AssertionError("Expected: " + expected + " but got: " + actual);
        }
    }
    public void clickCompanyLink() {
        driver.findElement(companyLink).click();
    }
    public String switchToNewTabAndGetUrl() {
    	String parentWindow=driver.getWindowHandle();
        for (String handle : driver.getWindowHandles()) {
        	if(!handle.equals(parentWindow)) {
            driver.switchTo().window(handle);
            System.out.println("CURRENT URL:  *********##### "+ driver.getCurrentUrl());
        	}
        }
        return driver.getCurrentUrl();
   
    }
}
