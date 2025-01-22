package avApiCall;

import java.io.IOException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.http.*;


/**
 * Simple Object wich can send querys and retrieve responses. <br>
 * Built for the json file and not the xml file! <br>
 * Results with xml may vary, though it should work.
 */
public class ApiCall {
	private String apiKey;
	private URL request;
	private String globalQuery;
	
	/**
	 * ApiCall Constructor
	 * @param url -> Should be alphaVantage api url
	 * @param apiKey -> your personal apiKey
	 */
	public ApiCall(String apiKey) {
		this.apiKey = apiKey;
	}
	
	/**
	 * For the use of function and symbol, please refer to the offical alphaVantage documentation!
	 * @param preQuery
	 */
	public void setRequest(String preQuery) {
		StringBuffer query = new StringBuffer("https://www.alphavantage.co/query?").append(preQuery).append("&apikey=").append(apiKey);
		globalQuery = query.toString();
	}
	
	/**
	 * For the use of function and symbol, please refer to the offical alphaVantage documentation! 
	 * Output will be a CSV file and not a json
	 * @param preQuery
	 */
	public void setRequestCSV(String preQuery) {
		StringBuffer query = new StringBuffer("https://www.alphavantage.co/query?").append(preQuery).append("&apikey=").append(apiKey).append("&datatype=csv");
		globalQuery = query.toString();
	}
	
	public void formRequest(){
		try {
			this.request = new URL(globalQuery);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Trys to get a response for the request
	 * @return 
	 */
	public String retrieveResponse() {
		try {
			URLConnection connection = request.openConnection();
			connection.setConnectTimeout(3000);
			
			InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream(), "UTF-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			StringBuilder responseBuilder = new StringBuilder();
			
			String line;
			
			while ((line = bufferedReader.readLine()) != null) {
				responseBuilder.append(line);
			}
			
			bufferedReader.close();
			return responseBuilder.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Only used when an IOException occurs!
		return null;
	}
	
	/**
	 * This one is the same as the other retrieveResponse(), but this one also executes the setRequest() method.<br>
	 * For the use of function and symbol, please refer to the offical alphaVantage documentation!
	 * @param function
	 * @param symbol
	 * @return 
	 */
//	public String retrieveResponse(String symbol) {
//		setRequestCSV(new TimeSeries(new Stock(symbol).getUSStock()).getTimeSeriesDaily());
//		try {
//			URLConnection connection = request.openConnection();
//			connection.setConnectTimeout(3000);
//			
//			InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream(), "UTF-8");
//			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//			StringBuilder responseBuilder = new StringBuilder();
//			
//			String line;
//			
//			while ((line = bufferedReader.readLine()) != null) {
//				responseBuilder.append(line);
//			}
//			
//			bufferedReader.close();
//			return responseBuilder.toString();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		// Only used when an IOException occurs!
//		return null;
//	}
//	
	/**
	 * Requests CSV instead of JSON file <br>
	 * Trys to get a response for the request
	 * @return 
	 */
	public String retrieveResponseCSV() {
		try {
			URLConnection connection = request.openConnection();
			connection.setConnectTimeout(3000);
			
			InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream(), "UTF-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			StringBuilder responseBuilder = new StringBuilder();
			
			String line;
			
			while ((line = bufferedReader.readLine()) != null) {
				responseBuilder.append(line);
			}
			
			bufferedReader.close();
			return responseBuilder.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
	// Only used when an IOException occurs!
		return null;
	}
//	
//	/**
//	 * Requests CSV instead of JSON file <br>
//	 * his one is the same as the other retrieveResponse(), but this one also executes the setRequest() method.<br>
//	 * For the use of function and symbol, please refer to the offical alphaVantage documentation!
//	 * @param symbol
//	 */
//	public String retrieveResponseCSV(String symbol) {
//		setRequestCSV(new TimeSeries(new Stock(symbol).getUSStock()).getTimeSeriesDaily());
//		formRequest();
//		try {
//			URLConnection connection = request.openConnection();
//			connection.setConnectTimeout(8000); // To low -> not full data Set
//			
//			InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream(), "UTF-8");
//			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//			StringBuilder responseBuilder = new StringBuilder();
//			
//			String line;
//			
//			while ((line = bufferedReader.readLine()) != null) {
//				responseBuilder.append(line);
//			}
//			
//			inputStreamReader.close();
//			bufferedReader.close();
//			return responseBuilder.toString();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		// Only used when an IOException occurs!
//		return null;
//	}
	
	/**
	 * Prints Request (used for testing putposes);
	 */
	public void printRequest() {
		System.out.println(request.toString());
	}
}
