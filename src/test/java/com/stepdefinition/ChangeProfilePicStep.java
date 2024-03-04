package com.stepdefinition;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import com.base.APIBaseClass;
import com.endpoint.EndPoint;
import com.pojo.ChangeProfilePic_Output_Pojo;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.Header;
import io.restassured.response.Response;

public class ChangeProfilePicStep extends APIBaseClass {
	static String logtoken=LoginStep.logtoken;
	Response response;
	@Given("User add Headers and bearer authorization for accessing ChangeProfilePic endpoints")
	public void userAddHeadersAndBearerAuthorizationForAccessingChangeProfilePicEndpoints() {
		List<Header>h = new ArrayList<>();
		Header h1 = new Header("accept","application/json");
		Header h2 = new Header("Authorization","Bearer "+logtoken);
		Header h3 = new Header("Content-Type","multipart/form-data");
		h.add(h1);
		h.add(h2);
		h.add(h3);
		addHeaders(h);
	}

	@When("User add multipart for upload profile pic")
	public void userAddMultipartForUploadProfilePic() throws IOException {
		formData();

	}

	@When("User send {string} request for change Profile Pic")
	public void userSendRequestForChangeProfilePic(String string) {

		response = requesttype("POST", EndPoint.CHANGEPROFILEPIC);

	}

	@Then("User verify the create change profile pic response message matches {string}")
	public void userVerifyTheCreateChangeProfilePicResponseMessageMatches(String string) {
		ChangeProfilePic_Output_Pojo changeProfilePic_Output_Pojo = response.as(ChangeProfilePic_Output_Pojo.class);
		String message = changeProfilePic_Output_Pojo.getMessage();
		Assert.assertEquals(message,"Profile updated Successfully", "Verify Profile updated Successfully");

	}


}
