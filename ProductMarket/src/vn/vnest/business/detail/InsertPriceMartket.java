package vn.vnest.business.detail;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

import io.netty.handler.codec.http.HttpHeaders;
import vn.vnest.business.BaseBusiness;
import vn.vnest.dbo.MarketDBO;
import vn.vnest.gson.GsonUtils;
import vn.vnest.request.PriceMarketRequest;
import vn.vnest.response.BaseResponse;
import vn.vnest.response.MarketResponse;

public class InsertPriceMartket extends BaseBusiness {

	private static final Logger log = LogManager.getLogger(InsertPriceMartket.class);
	private static Gson gson = GsonUtils.createAllLower();
	
	@Override
	public BaseResponse process(HttpHeaders headers, HashMap<String, String> params, String body) {
		PriceMarketRequest request = gson.fromJson(body, PriceMarketRequest.class);

		try {
			int code = MarketDBO.postPriceMarket(request);
			return new MarketResponse(code);
		} catch (Exception e) {
			log.info("ex :" + e.toString());
		}

		return null;
	}

}
