package com.homeAway.util;

import java.util.Map;

import org.testng.Assert;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class CommonUtils {

	public static ClientResponse responseGet = null;
	public static WebResource webResource;
	public static Client client;
	public static String response = null;
	
	public static String getOperation(String vURI, Map<String, String> headers) throws Exception {
		String output = null;
		try {
			client = Client.create();
			webResource = client.resource(vURI);
			WebResource.Builder builder = webResource.getRequestBuilder();
			if (null != headers) {
				for (String key : headers.keySet()) {
					builder = builder.header(key, headers.get(key));
				}
			}
			responseGet = builder.type("application/json").get(ClientResponse.class);
			output = responseGet.getEntity(String.class);

		} catch (Exception ex) {
			Assert.fail("Get operation failed due to -"+ex);
		}
		return output;
	}
}
