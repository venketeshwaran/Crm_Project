package org.test;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.base.BaseClass;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Sleeper;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import POM.Page_Object_Manager;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.ITestResult;
import org.testng.annotations.*;

public class RecruitCRM extends BaseClass {

	Page_Object_Manager pom;
	ExtentTest logger;
	ExtentReports extent;

	@BeforeTest
	private void extent_Report() {
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extent.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		logger = extent.createTest("My TestSuite", "My Reports");
	}

	@Parameters({ "mailId", "pass" })
	@BeforeClass
	private void crendentials(@Optional("ponnagarajchitrambalam@gmail.com") String mailId,
			@Optional("Naren0150") String pass) {
		browserLaunch("chrome");
		urlLaunch("https://neo-mr.recruitcrm.io/");
		pom = new Page_Object_Manager(driver);
		implicitWait(20);
		sendKeys(pom.getLoginPage().getTxtBoxMail(), mailId);
		sendKeys(pom.getLoginPage().getTxtBoxPass(), pass);
		click(pom.getLoginPage().getBtnLogin());
	}

	@Parameters({ "number", "city" })
	@Test(priority = 1)
	private void canditate_Input_Field(String number, String city) throws AWTException, InterruptedException {
		click(pom.get_POM_RC01().getSideBarCanditateIcon());
		click(pom.get_POM_RC01().getCanditateName());
		click(pom.get_POM_RC01().getEditIcon());
		sendKeysAfterDeletingExistingText(pom.get_POM_RC01().getTxtBoxCanditateMob(), number);
		sendKeysAfterDeletingExistingText(pom.get_POM_RC01().getTxtBoxCanditateCity(), city);
		javascriptScrollDownTo(pom.get_POM_RC01().getDropDownYearOfExp());
		actionsClick(pom.get_POM_RC01().getDropDownYearOfExp());
		sleep(3000);
		click(pom.get_POM_RC01().getDropDownYear_5());
		click(pom.get_POM_RC01().getSubmitBtn());
	}

	@Parameters({ "organization" })
	@Test(priority = 2)
	private void input_Field(String organization) throws InterruptedException, AWTException {
		sleep(3000);
		click(pom.get_POM_RC02().getEditIcon());
		sendKeysAfterDeletingExistingText(pom.get_POM_RC02().getTxtBoxCurrentOrganization(), organization);
		sleep(2000);
		btnEnter();
	}

	@Test(priority = 3)
	private void job_List() throws InterruptedException, AWTException {

		sleep(2000);
		click(pom.get_POM_RC03().getSideBarJobsIcon());
		sleep(4000);
		click(pom.get_POM_RC03().getEditIcon());
		sleep(2000);
		boolean actualId = isSelected(pom.get_POM_RC03().getCheckBoxID());
		if (actualId == false) {
			click(pom.get_POM_RC03().getCheckBoxID());
		}
		sleep(2000);
		boolean actualSpec = isSelected(pom.get_POM_RC03().getCheckBoxSpecialization());
		if (actualSpec == false) {
			waitUntillWebelementClickable(pom.get_POM_RC03().getCheckBoxSpecialization(), 8);
			click(pom.get_POM_RC03().getCheckBoxSpecialization());
		}
		sleep(2000);
		
		sendKeys(pom.get_POM_RC03().getSearchBar(), "city");

		boolean actualCity = isSelected(pom.get_POM_RC03().getCheckBoxCity());
		if (actualCity == false) {
			click(pom.get_POM_RC03().getCheckBoxCity());
		}
		click(pom.get_POM_RC03().getExitBtn());

		List<String> li = new ArrayList<String>();
		List<WebElement> tableHeadings = driver.findElements(By.tagName("th"));
		for (int i = 0; i < tableHeadings.size(); i++) {
			li.add(tableHeadings.get(i).getText());
		}
		softAssertEquals(li.contains("ID"), true, "ID is not Found in heading");
		softAssertEquals(li.contains("SPECIALIZATION"), true, "Specialization is not Found in heading");
		softAssertEquals(li.contains("CITY"), true, "City is not Found in heading");
	}

	@Parameters({ "number", "city", "language" })
	@Test(priority = 4)
	private void candidate_Page(String number, String city, String language) throws InterruptedException, AWTException {
		sleep(3000);
		click(pom.get_POM_RC04().getSideBarCanditateIcon());
		click(pom.get_POM_RC04().getCandtitateName());

		click(pom.get_POM_RC04().getEditIcon());
		sendKeysAfterDeletingExistingText(pom.get_POM_RC04().getTxtBoxCanditateMob(), number);
		sendKeysAfterDeletingExistingText(pom.get_POM_RC04().getTxtBoxCity(), city);
		javascriptScrollDownTo(pom.get_POM_RC04().getDropDownYearOfExp());
		actionsClick(pom.get_POM_RC04().getDropDownYearOfExp());
		sleep(3000);
		click(pom.get_POM_RC04().getDropDownYear_5());
		click(pom.get_POM_RC04().getAddLanguageBtn());
		sendKeysAfterDeletingExistingText(pom.get_POM_RC04().getDropDownLanguage(), language);//
		click(pom.get_POM_RC04().getDropDoenLanguageProficiency());
		javascriptScrollDownTo(pom.get_POM_RC04().getDropDownLevelNative());
		click(pom.get_POM_RC04().getDropDownLevelNative());
		click(pom.get_POM_RC04().getAddLanguageBtn());
		click(pom.get_POM_RC04().getBtnSubmit());
	}

	@Test(priority = 5)
	private void contact_Profile_Picture_Change() throws InterruptedException, AWTException {
		sleep(3000);
		click(pom.get_POM_RC05().getSideBarContactsIcon());
		waitUntillWebelement(pom.get_POM_RC05().getContactName(), 10);
		click(pom.get_POM_RC05().getContactName());
		sleep(3000);
		actionsClick(pom.get_POM_RC05().getProfilePic());

		click(pom.get_POM_RC05().getChangeBtn());
		String loc = System.getProperty("user.dir");
		imageUpload(loc + "\\Image\\profile.jpg");
		btnEsc();
	}

	@Test(priority = 6)
	private void mailBox_Page() throws InterruptedException, AWTException {
		sleep(8000);

		click(pom.get_POM_RC06().getSideBarMailBoxIcon());
		urlCheck("https://neo-mr.recruitcrm.io/folder");
		click(pom.get_POM_RC06().getOpenedMailLink());
		urlCheck("opened");
		click(pom.get_POM_RC06().getFailedMailLink());
		urlCheck("failed");
		click(pom.get_POM_RC06().getTemplatesMailLink());
		urlCheck("templates");
		click(pom.get_POM_RC06().getStandardMailLink());
		urlCheck("standard");
	}

	@Test(priority = 7)
	private void edit_Candidate_Add_Language() throws InterruptedException, AWTException {
		sleep(3000);
		click(pom.get_POM_RC07().getSideBarCanditateIcon());
		click(pom.get_POM_RC07().getCanditateName());

		click(pom.get_POM_RC07().getEditIcon());
		sleep(8000);
		javascriptScrollDownTo(pom.get_POM_RC07().getEduQualification());

		String eduQualification = getAttribute(pom.get_POM_RC07().getEduQualification(), "value");
		softAssertContains(eduQualification, "Bachelor's Degree", "Mismatch Found in Educational Qualification");

		String eduSpecialization = getAttribute(pom.get_POM_RC07().getEduSpecialization(), "value");
		softAssertContains(eduSpecialization, "MBA Marketing", "Mismatch Found in Educational Specialization");

		String academicOrganisation = getAttribute(pom.get_POM_RC07().getAcademicOrganization(), "value");
		softAssertContains(academicOrganisation, "Sinapse Software", "Mismatch Found in Organisation Name");

		String currentSalary = getAttribute(pom.get_POM_RC07().getAcademicCurrentSalary(), "value");

		softAssertContains(currentSalary, "80000", "Mismatch Found in Current Salary");

		String expectedSalary = getAttribute(pom.get_POM_RC07().getAcademicExpectedSalary(), "value");
		softAssertContains(expectedSalary, "100000", "Mismatch Found in Expected Salary");

		click(pom.get_POM_RC07().getExitBtn());
	}

	@Test(priority = 8, enabled = true)
	private void tasks_And_Appointments() throws InterruptedException, AWTException {
		btnEsc();
		sleep(3000);
		click(pom.get_POM_RC08().getSideBarTaskandMeetings());
		sleep(3000);
		click(pom.get_POM_RC08().getCheckBoxOfMeeting01());
		String alertMsg = getText(pom.get_POM_RC08().getAlertMsg());
		softAssertContains(alertMsg, "Field Updated Successfully", "Found Mismatch in Alert Message");
		sleep(3000);
		click(pom.get_POM_RC08().getCheckBoxOfMeeting01());
		String alertMsg1 = getText(pom.get_POM_RC08().getAlertMsg());
		softAssertContains(alertMsg1, "Field Updated Successfully", "Found Mismatch in Alert Message");
		sleep(3000);
	}

	@Test(priority = 9, enabled = true)
	private void dash_Board() throws InterruptedException, AWTException {
		btnEsc();
		sleep(3000);
		click(pom.get_POM_RC09().getSideBarDashBoardIcon());
		sleep(3000);
		click(pom.get_POM_RC09().getExportToSpreadSheetBtn());
		boolean fileDownloaded = isFileDownloaded("C:\\Users\\mages\\Downloads",
				"Candidate Pipeline Of Ponnagaraj Chitrambalam's Recruiter.csv");
		softAssertEquals(fileDownloaded, true, "Found Error in File Downloading");
	}

	@Test(priority = 10, enabled = true)
	private void report_Page(Method m) throws InterruptedException, AWTException, IOException {
		sleep(3000);
		click(pom.get_POM_RC10().getSideBarReportsIcon());
		click(pom.get_POM_RC10().getClientPerformanceReportLink());
		sleep(3000);
		javascriptScrollDownTo(pom.get_POM_RC10().getDropDownDuration());
		sleep(3000);
		click(pom.get_POM_RC10().getDropDownDuration());

		Robot r = new Robot();
		r.keyPress(KeyEvent.VK_Y);
		r.keyRelease(KeyEvent.VK_Y);
		r.keyPress(KeyEvent.VK_E);
		r.keyRelease(KeyEvent.VK_E);
		r.keyPress(KeyEvent.VK_A);
		r.keyRelease(KeyEvent.VK_A);
		r.keyPress(KeyEvent.VK_R);
		r.keyRelease(KeyEvent.VK_R);

		btnEnter();
		click(pom.get_POM_RC10().getGenerateReportBtn());
		takeScrnsht(m.getName());
	}

	@AfterMethod
	private void screenShot_Extent_Report(ITestResult i, Method m) throws IOException {
		if (i.getStatus() == 2) {
			takeScrnsht(m.getName());
			logger.fail(i.getThrowable().getMessage());
		} else {
			logger.pass("Passed");
		}
		extent.flush();
	}

	@AfterTest
	private void quit_The_Program() {
		browserQuit();
	}

}