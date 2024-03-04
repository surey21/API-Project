package com.runner;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.base.APIBaseClass;
import com.report.Reporting;

import cucumber.api.CucumberOptions;
import cucumber.api.Scenario;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(tags = {}, snippets = SnippetType.CAMELCASE, strict = true, dryRun = false, plugin = {
		"pretty", "json:target/JVMReport.json" }, monochrome = true, features = {
				"src\\test\\resources" }, glue = { "com.stepdefinition" })

public class TestRunnerClass extends APIBaseClass {

	@AfterClass
	public static void afterClass() {

		Reporting.GeneratesJVMReport("C:\\Users\\DELL\\eclipse-workspace\\OMRBranchApiAutomation\\target\\JVMReport.json");
		
	}


}

