package com.iti.rooming.portal.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import com.iti.rooming.common.enums.WebMethodType;
import com.iti.rooming.common.enums.WebParamType;

public class WebUtils {

	public static String callWebService(String urlName,
			WebMethodType webMethodType, WebParamType webParamType,
			Map<String, String> params) { /*
										 * Template ::
										 * https://maps.googleapis.com
										 * /maps/api/geocode
										 * /json?latlng=30.022435,31.132851
										 */
		try {
			StringBuilder queryParams = new StringBuilder();
			/******/
			if (params != null) {
				for (Map.Entry<String, String> entry : params.entrySet()) {
					if (queryParams.length() == 0) {
						queryParams.append("?");
					} else {
						queryParams.append("&");

					}
					queryParams.append(entry.getKey() + "=" + entry.getValue());
				}
			}
				
			URL url;
			url = new URL(urlName+queryParams.toString());
			System.out.println("++\n"+url.toString()+"\n+++");
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestMethod(webMethodType.toString());
			connection.setRequestProperty("Accept", "application/json");

			if (connection.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ connection.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(connection.getInputStream())));

			String output = "";
			StringBuilder result = new StringBuilder();
			while ((output = br.readLine()) != null) {
				result.append(output);
			}

			connection.disconnect();
			return result.toString();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
