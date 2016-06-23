package com.homeAway.services;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.homeAway.util.CommonUtils;

/**
 * @author mahendranPandurangan
 *
 */
public class TestExercise2 {

	String queryparam_location = "AUSTIN,TX";
	String queryparam_evNetwork = "ChargePoint+Network";
	String queryparam_format = "json";
	Object stationId = null;

	public String URL = "http://developer.nrel.gov";
	public String findStationURI = "/api/alt-fuel-stations/v1/nearest." + queryparam_format;
	public String findStationQueryParam = "?location=" + queryparam_location + "&ev_network=" + queryparam_evNetwork;
	public String getStationAddressURI = "/api/alt-fuel-stations/v1/";

	CommonUtils testUtil = new CommonUtils();

	@Test(dataProvider = "getURLParams")
	public void getNearestStationTest(String url, String uri, String queryParam) {
		String response;
		String vUrl = url + uri + queryParam;

		try {
			response = CommonUtils.getOperation(vUrl, getHeaders());

			// Requirement : Verifying the presence of text "HYATT AUSTIN"
			Boolean verifyText = response.toString().contains("HYATT AUSTIN");
			Assert.assertEquals(verifyText, Boolean.TRUE);

			JSONArray respJson = new JSONObject(response).getJSONArray("fuel_stations");
			for (int count = 0; count < respJson.length(); count++) {
				if (respJson.getJSONObject(count).get("station_name").equals("HYATT AUSTIN")) {
					stationId = respJson.getJSONObject(count).get("id");
				}
			}
			// Requirement : Generated StationId
			System.out.println("Test 1 : StationId for Austin,TX Location - " + stationId);

		}

		catch (Exception ex) {
			Assert.fail("Could'nt get the nearest station!" + ex);
		}
	}

	@DataProvider(name = "getURLParams")
	public Object[][] getURLParams() {
		return new Object[][] { { URL, findStationURI, findStationQueryParam } };
	}

	@Test(dependsOnMethods = "getNearestStationTest")
	public void getStationAddressTest() {
		String response;
		String vUrl = URL + getStationAddressURI + stationId + ".json";
		try {
			response = CommonUtils.getOperation(vUrl, getHeaders());
			JSONObject jobj = new JSONObject(response.toString());
			Assert.assertEquals(jobj.getJSONObject("alt_fuel_station").get("street_address"), "208 Barton Springs Rd");
			Assert.assertEquals(jobj.getJSONObject("alt_fuel_station").get("city"), "Austin");
			Assert.assertEquals(jobj.getJSONObject("alt_fuel_station").get("state"), "TX");
			Assert.assertEquals(jobj.getJSONObject("alt_fuel_station").get("zip"), "78704");
			System.out.println(
					"Test 2 : Station Address - " + jobj.getJSONObject("alt_fuel_station").get("street_address") + " , "
							+ jobj.getJSONObject("alt_fuel_station").get("city") + " , "
							+ jobj.getJSONObject("alt_fuel_station").get("state") + " , "
							+ jobj.getJSONObject("alt_fuel_station").get("zip"));
		}

		catch (Exception ex) {
			Assert.fail("Could'nt get the station address due to -" + ex);
		}
	}

	/*
	 * Requirement : Used API Key
	 */
	protected Map<String, String> getHeaders() {
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("X-Api-Key", "GmzU61EmH4RiGv6C18Y1fBaeOcSBinoA3rfNrZ2F");
		return headers;
	}

}
