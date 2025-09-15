package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="D:\\eclispe-workspace\\Akni\\resources\\features", glue={"stepDefinitions","hooks"},monochrome=true, plugin= {"pretty","html:reports/HTMLREPORT.html"})
public class TestRunner extends AbstractTestNGCucumberTests{

}
