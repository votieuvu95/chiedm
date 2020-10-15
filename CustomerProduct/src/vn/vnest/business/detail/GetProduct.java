package vn.vnest.business.detail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import io.netty.handler.codec.http.HttpHeaders;
import vn.vnest.business.BaseBusiness;
import vn.vnest.entities.ProduceUpdate;
import vn.vnest.main.AutoGetProduct;
import vn.vnest.main.Constant;
import vn.vnest.response.BaseResponse;
import vn.vnest.response.ProductResponse;
import vn.vnest.server.HttpClient;

public class GetProduct extends BaseBusiness {

	private static final Logger log = LogManager.getLogger(GetProduct.class);
	private static Gson gson = new Gson();
	HashMap<String, String> keysproduct = new HashMap<>();

	@Override
	public BaseResponse process(HttpHeaders headers, HashMap<String, String> params, String body) {
		String values = params.get("value");
		String[] array = values.split(",");
		List<ProduceUpdate> produceUpdates = new ArrayList<ProduceUpdate>();
		StringBuilder products = new StringBuilder();
		for (String product : array) {

			if (AutoGetProduct.getProducts().get(Constant.getCodeMarket() + product) == null) {
				HttpClient client = new HttpClient();
				String url = Constant.getUrl() + Constant.getCodeMarket() + "?product=" + product;
				try {
					String response = client.get(url);
					if (response != null) {
						JsonObject data = gson.fromJson(response, JsonObject.class);
						JsonArray productarray = data.getAsJsonArray("products");
						int size = productarray.size();
						for (int i = 0; i < size; i++) {
							JsonObject object = productarray.get(i).getAsJsonObject();
							keysproduct.put(Constant.getCodeMarket() + object.get("codeProduct").getAsString(), object.toString());
							
							ProduceUpdate produceUpdate = new ProduceUpdate(object.get("price").getAsString(),
									object.get("name").getAsString(), object.get("pricesale").getAsString(), object.get("code").getAsString());
							produceUpdates.add(produceUpdate);
						}
					}
				} catch (IOException e) {
					log.info("ex:" + e.toString());
				}
				continue;
			}

			JsonObject object = new JsonParser().parse(AutoGetProduct.getProducts().get(Constant.getCodeMarket() + product))
					.getAsJsonObject();
			ProduceUpdate produceUpdate = new ProduceUpdate(object.get("price").getAsString(),
					object.get("name").getAsString(), object.get("pricesale").getAsString(), object.get("code").getAsString());
			produceUpdates.add(produceUpdate);
		}

//		if (products.length() > 0) {
//			products.deleteCharAt(products.length() - 1);
//			HttpClient client = new HttpClient();
//			String url = Constant.getUrl() + Constant.getCodeMarket() + "?product=" + products.toString();
//			try {
//				String response = client.get(url);
//				if (response != null) {
//					JsonObject data = gson.fromJson(response, JsonObject.class);
//					JsonArray productarray = data.getAsJsonArray("products");
//					int size = productarray.size();
//					for (int i = 0; i < size; i++) {
//						JsonObject object = productarray.get(i).getAsJsonObject();
//						keysproduct.put(Constant.getCodeMarket() + object.get("codeProduct").getAsString(), object.toString());
//						
//						ProduceUpdate produceUpdate = new ProduceUpdate(object.get("price").getAsString(),
//								object.get("name").getAsString(), object.get("pricesale").getAsString(), object.get("code").getAsString());
//						produceUpdates.add(produceUpdate);
//					}
//				}
//			} catch (IOException e) {
//				log.info("ex:" + e.toString());
//			}
//		}
		
		return new ProductResponse(produceUpdates);
		// try {
		// Jedis jedis = JedisFactory.getInstance().getJedisPool();
		// String s = jedis.get(values+Constant.getCodeMarket());
		//
		// if (s != null) {
		// JsonObject object = new JsonParser().parse(s).getAsJsonObject();
		// ProduceUpdate produceUpdate = new
		// ProduceUpdate(object.get("price").getAsString(),object.get("name").getAsString(),
		// object.get("pricesale").getAsString());
		// produceUpdates.add(produceUpdate);
		// 
		// }
		// else {
		// HttpClient client = new HttpClient();
		// String url = Constant.getUrl() + "?value=" + value + "?codemarket=" +
		// Constant.getCodeMarket();
		// String response = client.get(url);
		// JsonObject data = gson.fromJson(response, JsonObject.class);
		//
		// String price = data.get("price").getAsString();
		// jedis.set(value, price);
		// }

		// }

	}
	
	
	 

}
