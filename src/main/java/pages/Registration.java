package pages;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import base.BasePage;

public class Registration extends BasePage {

	
public static final String DOB_VALUE="dob";
public static final String AGE_VALUE="age";

//public static final Map<String, String> monthMap = new HashMap<>();
//
//static {
//    monthMap.put("01", "January");
//    monthMap.put("02", "February");
//    monthMap.put("03", "March");
//    monthMap.put("04", "April");
//    monthMap.put("05", "May");
//    monthMap.put("06", "June");
//    monthMap.put("07", "July");
//    monthMap.put("08", "August");
//    monthMap.put("09", "September");
//    monthMap.put("10", "October");
//    monthMap.put("11", "November");
//    monthMap.put("12", "December");
//}


public Registration(WebDriver driver) {
super(driver);
	
}
	                     //Locators

private By titleNames=By.xpath("//input[@name='radioButton']");
private By firstname=By.name("firstname");
private By lastname=By.name("lastname");
private By dobOrAgeRadios=By.xpath("//input[@id='radios']");
private By dobinput=By.id("dobinput");
private By calendarYearDropdown=By.xpath("//select[@class='ui-yearpicker']");
private By calendarMonthPicker=By.xpath("//select[@class='ui-monthpicker']");
private By 	date=By.name("date-calendar");
private By ageinput=By.name("ageinput");
private By mrn=By.id("mrn");
private By mobile=By.id("mobile");
private By genderradios=By.xpath("//input[@id='gender']");
private By maritalradios=By.xpath("//input[@id='maritalradio']");

private By identityNumber=By.id("id-typeNumber");
private By fatherName=By.id("fathername");
private By spouseName=By.id("spouse");
private By email=By.id("email");

private By occupation=By.id("occupation");
private By communicationCheckboxes=By.xpath("//*[@id='checkbox-example']/fieldset/label");
private By address=By.id("address");
private By profilePicUpload=By.xpath("//img[@id='profilepicimage']");
private By IdCardUpload=By.xpath("//img[@id='Idcard-image']");
private By saveBtn=By.id("savebtn");
private By successmsg=By.id("successmsg");

//Dynamic calendar xpath for day
private By calendarCell(String day) {
	String dayFormat=String.format("%02d",Integer.parseInt(day));
	int dayInt=Integer.parseInt(day);
	String xpath="//td[(normalize-space(text())='"+dayFormat+"' or normalize-space(text())='"+ dayInt + "') and not (contains(@class,'disabled'))]";
	return By.xpath(xpath);
}

//Dynamic calendar xpath for month
private By calendarMonth(String monthvalue) {
	int parsedMonthValue=Integer.parseInt(monthvalue);
	Month month=Month.of(parsedMonthValue);
	
	//For xpath dynamic
	String fullMonthName=month.getDisplayName(TextStyle.FULL, Locale.ENGLISH);
	String shortMonthName=month.getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
	String doubleDigitMonth=String.format("%02d",parsedMonthValue);
	String monthString= String.valueOf(parsedMonthValue);
	String xpath=String.format("//option[normalize-space(text())='%s' or normalize-space(text())='%s' or @value='%s' or @value='%s']",fullMonthName,shortMonthName,doubleDigitMonth,monthString);
	return By.xpath(xpath);
}
//Identity dropdown dynamic
private By identityOption(String value) {
    String normalizedValue = value.trim().toLowerCase();
    String xpath = String.format(
        "//select[@id='id-type']/option[translate(normalize-space(text()), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')='%s']",
        normalizedValue
    );
    return By.xpath(xpath);
}

//blood group dynamic dropdown 
private By blood_group_option(String value) {
    String normalizedValue = value.trim().toLowerCase();
    String xpath = String.format(
        "//select[@id='bloodgroup']/option[translate(normalize-space(text()), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')='%s']",
        normalizedValue
    );
    return By.xpath(xpath);
}


                               //************methods**********
//title
public void selectTitle(String title) //Miss
{
	for (WebElement titleName  : driver.findElements(titleNames)) {
		if (titleName.getAttribute("value").equalsIgnoreCase(title)) {
			click(titleName);
			break;
		}
	}
	
}

//firstname
public void firstName(String firstName) {
	input(firstname,firstName);
}

//Lastname

public void LastName(String lastName) {
	input(lastname,lastName);
}


//dobRadio
public void dobOrAge(String option,String dobvalue,String Agevalue) {
	
	for (WebElement radio : driver.findElements(dobOrAgeRadios)) {
		String radiovalue=radio.getAttribute("value");
		if(radiovalue.equalsIgnoreCase(option) && !radio.isSelected()) {
			click(radio);		
		}
	}
	
	//if user chooses doboption
if(option.equalsIgnoreCase(DOB_VALUE) && dobvalue!=null && !dobvalue.isEmpty()) {
	
	selectDOB(dobvalue);
}
	else if(option.equalsIgnoreCase(AGE_VALUE) && Agevalue!=null && !Agevalue.isEmpty() ) {
//		driver.findElement(ageinput).sendKeys(Agevalue);
		input(ageinput,Agevalue);
		
	}

}

public void selectDOB(String dobvalue) {
	String[] split=dobvalue.split("-");
	String day=split[0];
	String month=split[1];
	String year=split[2];

	
	//year
	new Select(driver.findElement(calendarYearDropdown)).selectByVisibleText(year);
	//month
	click(calendarMonth(month));
	//day
	click(calendarCell(day));
}

//MRN
public void mrnNumber(String mrnNumber) {
	input(mrn,mrnNumber);
}

//Mobile
public void mobileNumber(String mobileNumber) {
	input(mobile,mobileNumber);
	
}
//IDtype

public void selectIdentity(String identityValue) {
	click(identityOption(identityValue));
}

//ID
public void id_Number(String idnumber) {
	
	input(identityNumber,idnumber);
}

//FatherName
public void father_Name(String fathername) {
	
	input(fatherName,fathername);
}

//spouseName
public void spouse_Name(String spousename) {
	
	input(spouseName,spousename);
}

//Email
public void emailId(String emailid) {
	input(email,emailid);
}


//BloodGroup
//IDtype

public void blood_group(String bloodgroup) {
	click(blood_group_option(bloodgroup));
}


//occupation
public void occupation_Detail(String occupationDetails) {
	
	input(occupation,occupationDetails);
}
//Address
public void Address_Details(String addressDetails) {
	input(address,addressDetails);
}

//Gender
public void gender_select(String genderselect) //Miss
{
	for (WebElement genderradio  : driver.findElements(genderradios)) {
		if (genderradio.getAttribute("value").equalsIgnoreCase(genderselect)) {
			click(genderradio);
			break;
		}
	}
	
}
//marital status
public void marital_select(String maritalselect) //Miss
{
	for (WebElement maritalradio  : driver.findElements(maritalradios)) {
		if (maritalradio.getAttribute("value").equalsIgnoreCase(maritalselect)) {
			click(maritalradio);
			break;
		}
	}
	
}

//Communication
public void select_communication(String selectcommunication) {
	String[] values=Arrays.stream(selectcommunication.split(",")).map(String::trim).toArray(String[]::new);
	for (WebElement communicationCheckbox : driver.findElements(communicationCheckboxes)) {
		String communicationCheckboxText=communicationCheckbox.getAttribute("value").trim();
		for (String value : values) {
			
			if(communicationCheckboxText.equalsIgnoreCase(value)) {
				WebElement checkbox=communicationCheckbox.findElement(By.tagName("input"));
				if (!checkbox.isSelected()) {
					click(checkbox);
				}
			}
		}
	}
}
//Profile picture
public void uploadProfilePic(String filePath) {
  
	input(profilePicUpload,filePath);
 
}

//ID Card
public void uploadIdCard(String filePath) {
	input(IdCardUpload,filePath);
 
}

//saveForm
public void saveForm() {
	click(saveBtn);
}

public boolean isSubmitDisplayed() {
	System.out.println("Save button displayed"+ saveBtn);
	return isDisplayed(saveBtn);
}
public String verifySuccessMessage() {
	return getText(successmsg);
}

}
