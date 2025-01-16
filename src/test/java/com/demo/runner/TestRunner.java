package com.demo.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {
                "src/test/resources/features/login/loginAdmin.feature",
                "src/test/resources/features/pim/pimAdd.feature"
        },
        glue = {
                "com.demo.stepdefinition",
        },
        plugin = {
                "pretty",
                "html:target/cucumberReports/Report.html",
                "json:target/cucumberReports/Report.json",
                "junit:target/cucumberReports/Report.xml",
        }
)

public class TestRunner extends AbstractTestNGCucumberTests {

}
