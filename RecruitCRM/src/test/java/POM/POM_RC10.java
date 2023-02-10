package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class POM_RC10 {
WebDriver driver;
	@FindBy (xpath="//a[@class='menu-link report']")
	private WebElement sideBarReportsIcon;
	
	@FindBy (xpath="//a[text()='Client Performance Report']")
	private WebElement clientPerformanceReportLink;
	@FindBy (xpath="//div[@class='vs__selected-options']")
	private WebElement dropDownDuration;
	@FindBy(xpath="//button[text()='Generate Report']")
	private WebElement generateReportBtn;
	
	public WebElement getGenerateReportBtn() {
		return generateReportBtn;
	}
	public POM_RC10(WebDriver driver) {
		// TODO Auto-generated constructor stub
	this.driver=driver;
	PageFactory.initElements(driver, this);
	}
	public WebElement getSideBarReportsIcon() {
		return sideBarReportsIcon;
	}
	public void setSideBarReportsIcon(WebElement sideBarReportsIcon) {
		this.sideBarReportsIcon = sideBarReportsIcon;
	}
	public WebElement getClientPerformanceReportLink() {
		return clientPerformanceReportLink;
	}
	public void setClientPerformanceReportLink(WebElement clientPerformanceReportLink) {
		this.clientPerformanceReportLink = clientPerformanceReportLink;
	}
	public WebElement getDropDownDuration() {
		return dropDownDuration;
	}
	public void setDropDownDuration(WebElement dropDownDuration) {
		this.dropDownDuration = dropDownDuration;
	}
	

}
