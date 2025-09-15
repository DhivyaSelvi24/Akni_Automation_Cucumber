package stepDefinitions;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.testng.Assert;

import hooks.Hooks;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.Login;
import utils.ExcelReader;

public class LoginSteps {

    private Login loginPage;

    public LoginSteps(){
        loginPage = new Login(Hooks.driver);
    }
    
    @Given("user is on the Login page")
    public void user_is_on_login_page() {
        String currentUrl = Hooks.driver.getCurrentUrl();
        if(!currentUrl.contains("logintestsample")) {
            throw new AssertionError("Not on Login Page, current URL: " + currentUrl);
        }
    }


    @When("user logs in using data from excel {string} sheet {string}")
    public void login_using_excel(String fileName, String sheetName) throws IOException {
        loginPage = new Login(Hooks.driver);

        
        String filePath = System.getProperty("user.dir") + "/resources/testData/" + fileName;

        ExcelReader reader = new ExcelReader(filePath);
        List<Map<String,String>> data = reader.getData(sheetName);

        for (Map<String,String> row : data) {
            System.out.println("Username from Excel: " + row.get("Username")); 
            loginPage.enterUsername(row.get("Username"));
            loginPage.enterPassword(row.get("Password"));
            loginPage.selectPage(row.get("Page"));
            loginPage.setRememberMe(Boolean.parseBoolean(row.get("RememberMe")));
            loginPage.clickLogin();
            loginPage.verifyMessage(row.get("ExpectedMessage"));
        }
    }
    @Then("user clicks on MyCompany link and verifies navigation")
    public void user_clicks_on_mycompany_link_and_verifies_navigation() {
        loginPage.clickCompanyLink();
        String url = loginPage.switchToNewTabAndGetUrl();
        Assert.assertEquals(url, "https://example.com/");
    }


}
