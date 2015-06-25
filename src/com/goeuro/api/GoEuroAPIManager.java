package com.goeuro.api;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;

import org.apache.http.client.HttpResponseException;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * This Class is used to connect GoEuro API
 * @author Udy
 *
 */
public class GoEuroAPIManager 
{

	/*** The API URL ***/
	private static final String GO_EURO_API_URL = "http://api.goeuro.com/api/v2/position/suggest/en/";
	
	/*** JSON  Parameters ***/
	private static final String PARAM_ID 				= "_id";
	private static final String PARAM_NAME 				= "name";
	private static final String PARAM_TYPE 				= "type";
	private static final String PARAM_GEO_POSITION 		= "geo_position";
	private static final String PARAM_LATITUDE 			= "latitude";
	private static final String PARAM_LONGTITUDE 		= "longitude";
	
	/**
	 * This function gets the query (user's input) and connect goeuro api to get JSONArray response
	 * @param query
	 * @return GoEuroPlacesList
	 * @throws Exception
	 */
	public GoEuroPlacesList getPlaces(String query)
			throws Exception 
	{
		JSONArray jsonResponse = null;//the JSON array response
		try {
			String getURL = GO_EURO_API_URL + query; 
			
			URL urlGet = new URL(getURL);
			
			// Open connection to GoEuro API
		    HttpURLConnection connection = (HttpURLConnection) urlGet.openConnection();
		    connection.setRequestMethod("GET");//get method
//		    System.out.println("GoEuro query request: " + connection.getURL().toString());
	
		    // Retrieve response from the GoEuro Places API
		    jsonResponse = getJSONResponse(connection);
		    
		    GoEuroPlacesList placesList = extractPlaces(jsonResponse);
            return placesList;

		} catch (HttpResponseException e) {
			System.out.println("Error: " +  e.getMessage());
            return null;
		}
	}


  
	/**
    * Helper function to get JSONArray Object as the response of the connection
    * 
    * 
    * @param connection
    * @return
    * @throws Exception
    */
	public JSONArray getJSONResponse(HttpURLConnection connection) throws Exception{
	    String line;
	    StringBuilder builder = new StringBuilder();
	    
	    // Must pass UTF_8 argument to ensure the input stream is correctly encoded
	    InputStreamReader isr = new InputStreamReader(connection.getInputStream(), HTTP.UTF_8);
	    BufferedReader reader = new BufferedReader(isr);
	    while((line = reader.readLine()) != null) {
	         builder.append(line);
	    }
	    
	    return new JSONArray(builder.toString());
	}

   /**
    * Helper function to extract GoEuro Places from the JSONObject
    * @param response
    * @return
    */
	private GoEuroPlacesList extractPlaces(JSONArray response){
		GoEuroPlacesList placesList = new GoEuroPlacesList();
		try {
            // Retrieve all places
			for (int i= 0; i< response.length(); i++)
			{
				JSONObject placeJSONObj = response.getJSONObject(i);

				GoEuroPlace place = new GoEuroPlace();
				
				//get values from JSON using opt - can get empty values.
				place.setId(placeJSONObj.optLong(PARAM_ID));
				place.setName(placeJSONObj.optString(PARAM_NAME));
				place.setType(placeJSONObj.optString(PARAM_TYPE));
				
				GoEuroGeoPosition geo_position = new GoEuroGeoPosition();
				
				JSONObject geo_position_obj = placeJSONObj.optJSONObject(PARAM_GEO_POSITION);
				if(null != geo_position_obj)
				{
					geo_position.setLatitude(geo_position_obj.optDouble(PARAM_LATITUDE));
					geo_position.setLongitude(geo_position_obj.optDouble(PARAM_LONGTITUDE));
				}					
				place.setGeoPosition(geo_position);
					
				
				placesList.places.add(place);
			}
		} catch (Exception e){
			e.printStackTrace();
		}
		return placesList;
	}
	
	
	
	
	/**
	 * Creating HTTP request Factory
	 * */
	public static HttpRequestFactory createRequestFactory(
			final HttpTransport transport) {
		return transport.createRequestFactory(new HttpRequestInitializer() {
			public void initialize(HttpRequest request) {
				JsonObjectParser parser = new JsonObjectParser(new JacksonFactory());
				request.setParser(parser);
			}
		});
	}
}
