package vn.vnest.main;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import vn.vnest.gson.GsonUtils;
import vn.vnest.server.HttpClient;

public class AutoGetProduct extends Thread {
	private static final Logger log = LogManager.getLogger(AutoGetProduct.class);
	private static Gson gson = GsonUtils.createAllLower();
	private static HashMap< String, String> products = new HashMap<>();
	@Override
	public void run() {
		while (true) {
			try {				
				Calendar cal = Calendar.getInstance();
				cal.set(Calendar.MILLISECOND, 0);
				int minutes  = cal.get(Calendar.MINUTE);
				if (minutes < 1) {
					getData();
				}
				TimeUnit.SECONDS.sleep(30);
			} catch (Exception e) {
				log.info("",e);
			}
		}
	}
	public static HashMap<String, String> getProducts() {
		return products;
	}
	public static void setProducts(HashMap<String, String> products) {
		AutoGetProduct.products = products;
	}
	
	private static void getData() throws IOException {
		HttpClient client = new HttpClient();
		String response = client.get(Constant.getUrl() +  Constant.getCodeMarket());
		log.info( "Response :" + response);
		JsonObject data = gson.fromJson(response, JsonObject.class);
		
		JsonArray produce = data.getAsJsonArray("produce");
		
		
		for (int i = 0; i < produce.size(); i++) {
			JsonObject object = produce.get(i).getAsJsonObject();
			String codeproduct = object.get("code").getAsString();
			products.put(Constant.getCodeMarket() + codeproduct  , object.toString());
			
		}
	}
}
