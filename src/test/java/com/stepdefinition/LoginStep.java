package com.stepdefinition;

import java.io.IOException;

import org.testng.Assert;

import com.base.APIBaseClass;
import com.endpoint.EndPoint;
import com.pojo.Login_Output_Pojo;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;

public class LoginStep extends APIBaseClass {
	static String  logtoken;
	static Response response;
	@Given("User add Header")
	public void userAddHeader() {
		//Header
		addheader("accept", "application/json");

	}

	@Given("User add basic authentication for login")
	public void userAddBasicAuthenticationForLogin() throws IOException {
		//basic  Auth
		basicAuth(getPropertyFileValue("username"),getPropertyFileValue("password") );

	}

	@When("User send {string} request for Login endpoint")
	public void userSendRequestForLoginEndpoint(String string) {

		response = requesttype("POST",EndPoint.POSTMANBASICAUTHLOGIN);

	}

	@Then("User verify the login response body firstName present as {string} and get the logtoken")
	public void userVerifyTheLoginResponseBodyFirstNamePresentAsAndGetTheLogtoken(String string) {
		Login_Output_Pojo output_Pojo = response.as(Login_Output_Pojo.class);
		String first_name = output_Pojo.getData().getFirst_name();
		System.out.println("FirstName in :"+first_name);
		logtoken = output_Pojo.getData().getLogtoken();
		System.out.println("Logtoken is :"+logtoken);
		Assert.assertEquals(first_name,"Suresh", "verify firstname");

	}



}
