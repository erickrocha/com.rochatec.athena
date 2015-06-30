package com.rochatec.pos.athena.remote.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class HTTPUtils {

	private final static String USER_AGENT = "Mozilla/5.0";

	public static String get(String path, Map<String, Object> params)
			throws MalformedURLException, IOException {
		URL url = null;
	    if (params.isEmpty()){
	    	url = new URL(path);
	    }else{
	    	String queryString = buildParams(params);
	    	url = new URL(path+"?"+URLEncoder.encode(queryString, "UTF-8"));
	    }
				
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("User-Agent", USER_AGENT);
		connection.setRequestProperty("Content-Type", "application/json;UTF-8");
		int responseCode = connection.getResponseCode();
		if (responseCode == 200) {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			return response.toString();
		}
		return null;
	}
	
	public static String post(String path, Map<String, Object> params)
			throws MalformedURLException, IOException {
		URL url = new URL(path);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		connection.setRequestProperty("User-Agent", USER_AGENT);
		connection.setRequestProperty("Content-Type", "application/json;UTF-8");
		
		String urlParameters = URLEncoder.encode(buildParams(params), "UTF-8");;
		
		// Send post request
		connection.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
		 
		int responseCode = connection.getResponseCode();
		if (responseCode == 200) {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			return response.toString();
		}
		return null;
	}

	private static String buildParams(Map<String, Object> params) {
		StringBuilder builder = new StringBuilder();
		for (String key : params.keySet()){
			builder.append(key+"="+params.get(key).toString());
			builder.append("&");
		}
		return builder.substring(0,builder.toString().length()-1);
	}
}
