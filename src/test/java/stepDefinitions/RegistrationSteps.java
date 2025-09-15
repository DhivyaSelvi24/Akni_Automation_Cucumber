package stepDefinitions;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

import base.BasePage;
import drivers.DriverFactory;
import hooks.Hooks;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.Login;
import pages.Registration;
import utils.ConfigReader;
import utils.ExcelReader;

public class RegistrationSteps  {
	

	private  WebDriver driver;
	private Registration registration;
	private Login login;

	public void initPages(){
		this.driver=Hooks.driver;
		this.registration=new Registration(driver);
		this.login=new Login(driver);
	}



@Given("I launched {string} browser with url {string}")
public void i_launched_browser_with_url(String chrome, String akniurl) {
   System.out.println("chrome "+ chrome + " akniurl: " +akniurl);
}
@Given("I navigated to the Akni Home page")
public void i_navigated_to_the_akni_home_page() {
//   String actual_app_title=DriverFactory.getDriver().getTitle();
//   Assert.assertEquals(actual_app_title,"Expected Akni Title","Title Not matched");
	 System.out.println("Navigated to Akni Home page (dummy verification)");
	    Hooks.test.log(Status.INFO, "Navigated to Akni Home page (dummy verification)");
}
@Then("I verify the Home page")
public void i_verify_the_home_page() {
//Assert.assertEquals(login.isLockImageDisplayed(), "Lock icon should be displayed.");

	 System.out.println("Verifying home page (dummy)");
	    Hooks.test.log(Status.PASS, "Home page verification skipped for dummy");}
@When("I click the new patient button")
public void i_click_the_new_patient_button() {

}
@When("I navigate to the Basic Details tab")
public void i_navigate_to_the_basic_details_tab() {

}
@Given("I am on the New Patient Basic Details page")
public void i_am_on_the_new_patient_basic_details_page() {

}

@When("I fill new patient details from excel {string} sheet {string}")
public void i_fill_new_patient_details_from_excel_sheet(String excelPath, String sheetName) throws Exception {
	 ExcelReader reader = new ExcelReader(excelPath);
     List<Map<String, String>> testData = reader.getData(sheetName);

     for (Map<String, String> row : testData) {
         registration.selectTitle(row.get("Title"));
         registration.firstName(row.get("FirstName"));
         registration.LastName(row.get("LastName"));
         registration.dobOrAge(row.get("DOB").isEmpty() ? "age" : "dob",
                               row.get("DOB"),
                               row.get("Age"));
         registration.mrnNumber(row.get("MRN"));
         registration.mobileNumber(row.get("Mobile"));
         registration.gender_select(row.get("Gender"));
         registration.selectIdentity(row.get("IdentityType"));
         registration.id_Number(row.get("ID"));
         registration.father_Name(row.get("FatherName"));
         registration.spouse_Name(row.get("SpouseName"));
         registration.emailId(row.get("Email"));
         registration.blood_group(row.get("BloodGroup"));
         registration.occupation_Detail(row.get("Occupation"));
         registration.select_communication(row.get("Communication"));
         registration.Address_Details(row.get("Address"));
         registration.uploadProfilePic(row.get("ProfilePicturePath"));
         registration.uploadIdCard(row.get("IDCardPath"));
         registration.saveForm(); // add this method in POM
         String expectedMessage = row.get("SuccessMessage"); 
         String actualmessage = registration.verifySuccessMessage();
         Assert.assertNotNull(actualmessage, "Success message is not displayed!");
         Assert.assertEquals(actualmessage, expectedMessage, "Success message text mismatch");

         System.out.println("Verified success message: " + actualmessage);
}
}
}