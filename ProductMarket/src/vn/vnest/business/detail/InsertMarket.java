package vn.vnest.business.detail;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

import io.netty.handler.codec.http.HttpHeaders;
import vn.vnest.business.BaseBusiness;
import vn.vnest.dbo.MarketDBO;
import vn.vnest.gson.GsonUtils;
import vn.vnest.request.MarketRequest;
import vn.vnest.response.BaseResponse;
import vn.vnest.response.MarketResponse;

public class InsertMarket extends BaseBusiness {
	private static final Logger log = LogManager.getLogger(InsertMarket.class);
	private static Gson gson = GsonUtils.createAllLower();

	@Override
	public BaseResponse process(HttpHeaders headers, HashMap<String, String> params, String body) {
		MarketRequest request = gson.fromJson(body, MarketRequest.class);

		try {
			int code = MarketDBO.postMarket(request);
			return new MarketResponse(code);
		} catch (Exception e) {
			log.info("ex :" + e.toString());
		}

		return null;
	}

}
