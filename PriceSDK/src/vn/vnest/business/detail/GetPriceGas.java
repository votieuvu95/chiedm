package vn.vnest.business.detail;

import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import io.netty.handler.codec.http.HttpHeaders;
import redis.clients.jedis.Jedis;
import vn.vnest.business.BaseBusiness;
import vn.vnest.cache.JedisFactory;
import vn.vnest.price.PricePetroleum;
import vn.vnest.respone.BaseResponse;
import vn.vnest.respone.PriceResponse;

public class GetPriceGas extends BaseBusiness {

	@Override
	public BaseResponse process(HttpHeaders headers, HashMap<String, String> params, String body) {
		String type = params.get("object");	
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		SimpleDateFormat fomart = new SimpleDateFormat("yyyyMMdd");
		String dat = fomart.format(date);
		
		try {
			Jedis jd = JedisFactory.getInstance().getJedisPool();
			String keyjd = type + dat; 
			if(jd.get(keyjd) !=null && !jd.get(keyjd).isEmpty() ) {
				return new PriceResponse(jd.get(keyjd));
			}
			
			jd.set(keyjd , PricePetroleum.getPrice(type));
			return  new PriceResponse(PricePetroleum.getPrice(type));
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

}
