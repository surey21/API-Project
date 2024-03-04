package com.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.base.APIBaseClass;
import com.endpoint.EndPoint;
import com.pojo.AddAddress_Input_Pojo;
import com.pojo.AddAddress_Output_Pojo;
import com.pojo.ChangeProfilePic_Output_Pojo;
import com.pojo.DeleteAddress_Input_Pojo;
import com.pojo.DeleteAddress_OutPut_Pojo;
import com.pojo.GetCartItem_output_pojo;
import com.pojo.Login_Output_Pojo;
import com.pojo.UpdateAddress_Input_Pojo;
import com.pojo.UpdateAddress_Output_Pojo;
import com.pojo.getAddresses_OutPut_Pojo;

import io.restassured.http.Header;
import io.restassured.response.Response;

public class OMRBranchClub extends APIBaseClass {
	String logtoken;
	String address_id;
	@Test(priority = 1)
	public void LoginPage() throws IOException {

		//Header
		addheader("accept", "application/json");
		//basic  Auth
		basicAuth(getPropertyFileValue("username"),getPropertyFileValue("password") );
		//reqType
		Response response = requesttype("POST",EndPoint.POSTMANBASICAUTHLOGIN);
		int statuscode = getstatuscode(response);
		System.out.println(statuscode);

		Login_Output_Pojo output_Pojo = response.as(Login_Output_Pojo.class);
		String first_name = output_Pojo.getData().getFirst_name();
		System.out.println(first_name);
		logtoken = output_Pojo.getData().getLogtoken();
		Assert.assertEquals(statuscode,200, "verify status code");
		Assert.assertEquals(first_name,"Suresh", "verify firstname");

	}

	@Test(priority = 2)
	public void AddAddress() {

		List<Header>h = new ArrayList<>();
		Header h1 = new Header("accept","application/json");
		Header h2 = new Header("Authorization","Bearer "+logtoken);
		Header h3 = new Header("Content-Type","application/json");
		h.add(h1);
		h.add(h2);
		h.add(h3);
		addHeaders(h);

		AddAddress_Input_Pojo input_Pojo = new AddAddress_Input_Pojo("Suresh", "Kumar", "9876543210",
				"L&T", 33, 3378, 101, "600100", "Pallikaranai", "HOME");
		addbody(input_Pojo);
		Response response = requesttype("POST",EndPoint.ADDADDRESS);
		int statuscode = getstatuscode(response);
		System.out.println(statuscode);

		AddAddress_Output_Pojo as = response.as(AddAddress_Output_Pojo.class);
		String message = as.getMessage();

		int id = as.getAddress_id();
		address_id = String.valueOf(id);

		Assert.assertEquals(statuscode,200,"verify ststus code");
		Assert.assertEquals(message,"Address added successfully", "Verify Address added successfully");




	}

	@Test(priority = 3)
	public void UpdateAddress() {

		List<Header>h = new ArrayList<>();
		Header h1 = new Header("accept","application/json");
		Header h2 = new Header("Authorization","Bearer "+logtoken);
		Header h3 = new Header("Content-Type","application/json");
		h.add(h1);
		h.add(h2);
		h.add(h3);
		addHeaders(h);

		UpdateAddress_Input_Pojo address_Input_Pojo = new UpdateAddress_Input_Pojo(address_id,"Suresh", "Kumar", "9876543210",
				"L&T", 33, 3378, 101, "600100", "Pallikaranai", "HOME");
		addbody(address_Input_Pojo);
		Response response = requesttype("PUT",EndPoint.UPDATEADDRESS);
		int statuscode = getstatuscode(response);
		System.out.println(statuscode);
		Assert.assertEquals(statuscode,200,"verify ststus code");
		UpdateAddress_Output_Pojo updateAddress_Output_Pojo =	response.as(UpdateAddress_Output_Pojo.class);
		String message = updateAddress_Output_Pojo.getMessage();
		Assert.assertEquals(message,"Address updated successfully", "Verify Address updated successfully");	

	}

	@Test(priority = 4)
	public void DeleteAddress() {
		List<Header>h = new ArrayList<>();
		Header h1 = new Header("accept","application/json");
		Header h2 = new Header("Authorization","Bearer "+logtoken);
		Header h3 = new Header("Content-Type","application/json");
		h.add(h1);
		h.add(h2);
		h.add(h3);
		addHeaders(h);	
		DeleteAddress_Input_Pojo deleteAddress_Input_Pojo = new DeleteAddress_Input_Pojo(address_id);
		addbody(deleteAddress_Input_Pojo);
		Response response = requesttype("DELETE",EndPoint.DELETEADDRESS);
		int statuscode = getstatuscode(response);
		System.out.println(statuscode);
		Assert.assertEquals(statuscode,200,"verify ststus code");
		DeleteAddress_OutPut_Pojo deleteAddress_OutPut_Pojo = response.as(DeleteAddress_OutPut_Pojo.class); 
		String message = deleteAddress_OutPut_Pojo.getMessage();
		Assert.assertEquals(message,"Address deleted successfully", "Verify Address deleted successfully");


	}

	@Test(priority = 5)
	private void getAddresses() {

		List<Header>h = new ArrayList<>();
		Header h1 = new Header("accept","application/json");
		Header h2 = new Header("Authorization","Bearer "+logtoken);
		h.add(h1);
		h.add(h2);
		addHeaders(h);
		Response response = requesttype("GET",EndPoint.GETADDRESS);

		int statuscode = getstatuscode(response);
		System.out.println(statuscode);
		Assert.assertEquals(statuscode,200,"verify ststus code");

		getAddresses_OutPut_Pojo getAddresses_OutPut_Pojo = response.as(getAddresses_OutPut_Pojo.class);
		String message = getAddresses_OutPut_Pojo.getMessage();
		Assert.assertEquals(message,"OK", "Verify OK");

	}

	@Test(priority = 6)
	public void ChangeProfilepic() throws IOException {

		List<Header>h = new ArrayList<>();
		Header h1 = new Header("accept","application/json");
		Header h2 = new Header("Authorization","Bearer "+logtoken);
		Header h3 = new Header("Content-Type","multipart/form-data");
		h.add(h1);
		h.add(h2);
		h.add(h3);
		addHeaders(h);

		formData();

		Response response = requesttype("POST", EndPoint.CHANGEPROFILEPIC);

		int statuscode = getstatuscode(response);
		System.out.println(statuscode);
		Assert.assertEquals(statuscode,200,"verify ststus code");

		ChangeProfilePic_Output_Pojo changeProfilePic_Output_Pojo = response.as(ChangeProfilePic_Output_Pojo.class);
		String message = changeProfilePic_Output_Pojo.getMessage();
		Assert.assertEquals(message,"Profile updated Successfully", "Verify Profile updated Successfully");		

	}

	@Test(priority = 7)
	public void getCartItems() throws IOException {

		List<Header>h = new ArrayList<>();
		Header h1 = new Header("accept","application/json");
		Header h2 = new Header("Authorization","Bearer "+logtoken);

		h.add(h1);
		h.add(h2);
		addHeaders(h);

		Response response = requesttype("GET", EndPoint.GETCARTITEMS);

		int statuscode = getstatuscode(response);
		System.out.println(statuscode);
		Assert.assertEquals(statuscode,200,"verify ststus code");
		GetCartItem_output_pojo cartItem_output_pojo = response.as(GetCartItem_output_pojo.class);
		String message = cartItem_output_pojo.getMessage();
		Assert.assertEquals(message,"OK", "OK");
		
		
		

	}
}
