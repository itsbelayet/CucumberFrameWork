package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = {"Features"},
        glue = "stepDefinition",
        dryRun = false,
        monochrome = true,
        tags = "@Sanity or @Regration", //="@Sanity or @Regration"
                                        //="@Sanity and @Regration"
                                        //="@Sanity and not @Regration"
                                        //="(@Sanity or @Regration) and not @Important"
        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"} // For extent Report

//        plugin = {"pretty", "html:target/cucumber-reports/reports_html.html",   // For html report
//                            "junit:target/cucumber-reports/reports_xml.xml",    // For xml report
//                            "json:target/cucumber-reports/reports_json.json"}   // For Json report

)
public class RunLoginPage extends AbstractTestNGCucumberTests {

}
