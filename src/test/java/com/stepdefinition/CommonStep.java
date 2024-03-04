package com.stepdefinition;

import org.testng.Assert;

import com.base.APIBaseClass;

import cucumber.api.java.en.Then;
import io.restassured.response.Response;

public class CommonStep extends APIBaseClass {
static Response response=LoginStep.response;
	@Then("User verify the status code is {int}")
	public void userVerifyTheStatusCodeIs(Integer int1) {
		
		int statuscode = getstatuscode(response);
		System.out.println(statuscode);
		Assert.assertEquals(statuscode,200,"verify ststus code");
}

}
