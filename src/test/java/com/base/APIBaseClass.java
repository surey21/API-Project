package com.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class APIBaseClass {
	RequestSpecification reqspec;
	Response response;

	public void addheader(String key,String value) {
		reqspec = RestAssured.given().header(key,value);

	}
	// N of Headers
	public void addHeaders(List<Header>h){
		Headers headers = new Headers(h);
		reqspec = RestAssured.given().headers(headers);
	}

	public void pathparam(String key,String value) {
		reqspec = reqspec.pathParam(key, value);
	}

	public void queryparam(String key,String value) {
		reqspec.queryParam(key, value);

	}
	public void addbody(String payload) {
		reqspec = reqspec.body(payload);
	}
	public void addbody(Object payload) {
		reqspec = reqspec.body(payload);
	}

	public void formData() throws IOException {
		reqspec = reqspec.multiPart("profile_picture", new File(getPropertyFileValue("Picture")));

	}
	
	
	public Response requesttype(String RequestType,String Endpoint) {

		switch (RequestType) {
		case "GET":
			response = reqspec.log().all().get(Endpoint);
			break;
		case "POST":
			response = reqspec.log().all().post(Endpoint);
			break;

		case "PUT":
			response = reqspec.log().all().put(Endpoint);
			break;

		case "DELETE":
			response = reqspec.log().all().delete(Endpoint);
			break;

		default:
			break;		
		}
		return response;

	}
	public int getstatuscode(Response response) {
		int statusCode = response.getStatusCode();
		return statusCode;

	}
	public ResponseBody getresbody(Response response) {
		ResponseBody body = response.getBody();
		return body;

	}
	public String getresbodyasstring(Response response) {
		String asString = getresbody(response).asString();
		return asString;


	}
	public String getresbodyasprettystring(Response response) {
		String asPrettyString = getresbody(response).asPrettyString();
		return asPrettyString;
	}

	public void basicAuth(String userName, String password) {
		reqspec = reqspec.auth().preemptive().basic(userName, password);


	}
	public String getPropertyFileValue(String key) throws IOException {
		FileInputStream stream = new FileInputStream(System.getProperty("user.dir")+"\\config.properties");
		Properties properties = new Properties();
		properties.load(stream);
		Object name=properties.get(key);
		String value = (String)name;
		return value;

	}





}
