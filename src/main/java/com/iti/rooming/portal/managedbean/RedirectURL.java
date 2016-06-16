package com.iti.rooming.portal.managedbean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.iti.rooming.common.exception.RoomingException;

@ManagedBean(name = "redirectURL")
public class RedirectURL implements Serializable {
	private static final long serialVersionUID = 1L;
	private String access_token;
	private String graph;
	private String id;
	private String data;

	@PostConstruct
	public void init() throws RoomingException{
//		HttpServletRequest request = (HttpServletRequest) FacesContext
//				.getCurrentInstance().getExternalContext().getRequest();
//		String error = request.getParameter("erro_reason");
//		if (error != null) {
//
//		}
//		String code = request.getParameter("code");
//		if (code != null) {
//			retrieveToken(code);
//			retrieveID(code);
//			retrieveInfo(code);
//		}
		throw new RoomingException("test log4j");

	}

	private int retrieveToken(String code) throws ClientProtocolException,
			IOException {
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(
				"https://graph.facebook.com/oauth/access_token");

		String[][] parameter = { { "client_id", "106715529744218" },
				{ "client_secret", "a39cc04b3ea3b970576085ed3aaf013e" },
				{ "redirect_uri", "http://localhost:8080/rooming/home.xhtml" },
				{ "code", code } };
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		for (int i = 0; i < parameter.length; i++) {
			nameValuePairs.add(new BasicNameValuePair(parameter[i][0],
					parameter[i][1]));
		}
		post.setEntity(new UrlEncodedFormEntity(nameValuePairs));

		HttpResponse response = client.execute(post);
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				response.getEntity().getContent()));
		String message = "";
		String lineData;
		while ((lineData = reader.readLine()) != null) {
			message += lineData;
		}
		String token = null;

		String[] params = message.split("&");
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				if (params[i].contains("access_token")) {
					String[] tmp = params[i].split("=");
					token = tmp[1];
				}
				break;
			}
		} else {
			return 0;
		}
		access_token = token;
		return 1;
	}

	private int retrieveID(String code) throws ClientProtocolException,
			IOException {
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet("https://graph.facebook.com/me?access_token="
				+ access_token);

		HttpResponse response = client.execute(get);
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				response.getEntity().getContent()));
		String message = "";
		String lineData;
		while ((lineData = reader.readLine()) != null) {
			message += lineData;
		}

		String[] params = message.split(",");
		if (params != null) {

			for (int i = 1; i < params.length; i++) {

				if (params[i].contains("id")) {
					String ids = params[i].split(":")[1];
					id = ids.substring(1, ids.length() - 2);
					break;
				}

			}
		} else {
			return 0;
		}

		return 1;
	}

	private int retrieveInfo(String code) throws ClientProtocolException,
			IOException {
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(
				"https://graph.facebook.com/"
						+ id
						+ "?fields=id,first_name,email,last_name,age_range,gender,timezone&access_token="
						+ access_token);

		HttpResponse response = client.execute(get);
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				response.getEntity().getContent()));
		String message = "";
		String lineData;
		while ((lineData = reader.readLine()) != null) {
			message += lineData;
		}
		String token = null;
		data = message;

		return 1;
	}

	public void setFbGraph() throws IOException {
		String g = "https://graph.facebook.com/me?access_token="
				+ getAccess_token();
		URL url = new URL(g);
		URLConnection connection = url.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));
		String inputLine;
		StringBuffer sb = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			sb.append(inputLine + "\n");
		}
		in.close();
		graph = sb.toString();
	}

	public String getAccess_token() {
		return access_token;
	}

	public String getGraph() {
		return graph;
	}

	public String getData() {
		return data;
	}
}
