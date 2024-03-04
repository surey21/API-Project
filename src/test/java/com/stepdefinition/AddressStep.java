package com.stepdefinition;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import com.base.APIBaseClass;
import com.endpoint.EndPoint;
import com.pojo.AddAddress_Input_Pojo;
import com.pojo.AddAddress_Output_Pojo;
import com.pojo.DeleteAddress_Input_Pojo;
import com.pojo.DeleteAddress_OutPut_Pojo;
import com.pojo.UpdateAddress_Input_Pojo;
import com.pojo.UpdateAddress_Output_Pojo;
import com.pojo.getAddresses_OutPut_Pojo;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.http.Header;
import io.restassured.response.Response;

public class AddressStep extends APIBaseClass {
	static String logtoken=LoginStep.logtoken;
	static Response response;
	static String address_id;

	@Given("User add Headers and bearer authorization for accessing address endpoints")
	public void userAddHeadersAndBearerAuthorizationForAccessingAddressEndpoints() {
		List<Header>h = new ArrayList<>();
		Header h1 = new Header("accept","application/json");
		Header h2 = new Header("Authorization","Bearer "+logtoken);
		Header h3 = new Header("Content-Type","application/json");
		h.add(h1);
		h.add(h2);
		h.add(h3);
		addHeaders(h);

	}

	@Given("User add request body for add new address {string},{string},{string},{string},{string},{string},{string},{string},{string},{string}")
	public void userAddRequestBodyForAddNewAddress(String first_name, String last_name, String mobile, String apartment, String state, String city, String country, String zipcode, String address, String address_type) {
		AddAddress_Input_Pojo input_Pojo = new AddAddress_Input_Pojo("Suresh", "Kumar", "9876543210",
				"L&T", 33, 3378, 101, "600100", "Pallikaranai", "HOME");
		addbody(input_Pojo);
	}

	@When("User send {string} request for add new address")
	public void userSendRequestForAddNewAddress(String string) {
		response = requesttype("POST",EndPoint.ADDADDRESS);

	}

	@Then("User verify the create address response message matches {string}")
	public void userVerifyTheCreateAddressResponseMessageMatches(String string) {
		AddAddress_Output_Pojo as = response.as(AddAddress_Output_Pojo.class);
		String message = as.getMessage();
		System.out.println("Verify :"+message);
		int id = as.getAddress_id();
		address_id = String.valueOf(id);
		Assert.assertEquals(message,"Address added successfully", "Verify Address added successfully");

	}

	@Given("User add request body for  update address {string}{string},{string},{string},{string},{string},{string},{string},{string},{string},{string}")
	public void userAddRequestBodyForUpdateAddress(String address_id,String first_name, String last_name, String mobile, String apartment, String state, String city, String country, String zipcode, String address, String address_type) {
		UpdateAddress_Input_Pojo address_Input_Pojo = new UpdateAddress_Input_Pojo(address_id,"Suresh", "Kumar", "9876543210",
				"L&T", 33, 3378, 101, "600100", "Pallikaranai", "HOME");
		addbody(address_Input_Pojo);

	}

	@When("User send {string} request for update address")
	public void userSendRequestForUpdateAddress(String string) {
		response = requesttype("PUT",EndPoint.UPDATEADDRESS);

	}

	@Then("User verify the update address response message matches {string}")
	public void userVerifyTheUpdateAddressResponseMessageMatches(String string) {
		UpdateAddress_Output_Pojo updateAddress_Output_Pojo =	response.as(UpdateAddress_Output_Pojo.class);
		String message = updateAddress_Output_Pojo.getMessage();
		System.out.println("Verify :"+message);
		Assert.assertEquals(message,"Address updated successfully", "Verify Address updated successfully");	

	}

	@Given("User add request body for delete address {string}")
	public void userAddRequestBodyForDeleteAddress(String string) {
		DeleteAddress_Input_Pojo deleteAddress_Input_Pojo = new DeleteAddress_Input_Pojo(address_id);
		addbody(deleteAddress_Input_Pojo);
	}

	@When("User send {string} request for delete address")
	public void userSendRequestForDeleteAddress(String string) {
		response = requesttype("DELETE",EndPoint.DELETEADDRESS);

	}

	@Then("User verify the delete address response message matches {string}")
	public void userVerifyTheDeleteAddressResponseMessageMatches(String string) {
		DeleteAddress_OutPut_Pojo deleteAddress_OutPut_Pojo = response.as(DeleteAddress_OutPut_Pojo.class); 
		String message = deleteAddress_OutPut_Pojo.getMessage();
		System.out.println("Verify :"+message);
		Assert.assertEquals(message,"Address deleted successfully", "Verify Address deleted successfully");

	}

	@When("User send {string} request for get address")
	public void userSendRequestForGetAddress(String string) {
		response = requesttype("GET",EndPoint.GETADDRESS);


	}

	@Then("User verify the get address response message matches {string}")
	public void userVerifyTheGetAddressResponseMessageMatches(String string) {
		getAddresses_OutPut_Pojo getAddresses_OutPut_Pojo = response.as(getAddresses_OutPut_Pojo.class);
		String message = getAddresses_OutPut_Pojo.getMessage();
		System.out.println("Verify :"+message);
		Assert.assertEquals(message,"OK", "Verify OK");

	}


}
