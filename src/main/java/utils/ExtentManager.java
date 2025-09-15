package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

	private static ExtentReports extent;
	
	public static ExtentReports getInstance() {
		if(extent==null) {
			
			String reportPath=System.getProperty("user.dir")+ "\\reports\\ExtentReports\\MyReport.html";
			System.out.println("Report Path ****** "+reportPath);
			ExtentSparkReporter spark=new ExtentSparkReporter(reportPath);
			spark.config().setReportName("Healdroid Akni Registration suite");
			spark.config().setDocumentTitle("Akni Automation test report-HealdroidTitle");
			spark.config().setTheme(Theme.DARK);
			extent=new ExtentReports();
			extent.attachReporter(spark);
			extent.setSystemInfo("Project name : ", "Healdroid Automation");
			extent.setSystemInfo("Tester : ", "Dhivya Dharmarajan");
		}
		return extent;
	}
	
}
