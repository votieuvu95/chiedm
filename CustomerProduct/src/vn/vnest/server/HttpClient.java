package vn.vnest.server;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;


public class HttpClient {
	private static final Logger log = LogManager.getLogger(HttpClient.class);
	private HttpTransport HTTP_TRANSPORT;
	private JsonFactory JSON_FACTORY;
	
	public HttpClient() {
		HTTP_TRANSPORT = new NetHttpTransport();
		JSON_FACTORY = new JacksonFactory();
	}
	
	public String post(String url, String data, HttpHeaders headers, boolean noLog) throws Exception {
		
		String response = null;
		
		HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory(new HttpRequestInitializer() {
			
			@Override
			public void initialize(HttpRequest arg0) throws IOException {
				// TODO Auto-generated method stub
				
			}
		});
				
		GenericUrl urlG = new GenericUrl(url);
		HttpContent content = ByteArrayContent.fromString("application/json", data);
		HttpRequest request = requestFactory.buildPostRequest(urlG, content);
		request.setHeaders(headers);
		request.setConnectTimeout(10);
		HttpResponse res = request.execute();
		response = res.parseAsString();
		res.disconnect();
		if (!noLog) {
			log.info("Response:" + response);
		}
		return response;
	}
	
	public  String get(String url) throws IOException {
		String response = null;
		HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory(new HttpRequestInitializer() {
			@Override
			public void initialize(HttpRequest request) {
				request.setParser(new JsonObjectParser(JSON_FACTORY));
			}
		});
		
		
		GenericUrl urlG = new GenericUrl(url);
		HttpRequest request = requestFactory.buildGetRequest(urlG);
		request.setConnectTimeout(10000);
		request.setReadTimeout(10000);
		HttpResponse res = request.execute();
		response = res.parseAsString();
		res.disconnect();
		return response;
	}

}
