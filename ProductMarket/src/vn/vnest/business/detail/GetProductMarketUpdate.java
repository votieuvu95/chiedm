package vn.vnest.business.detail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import io.netty.handler.codec.http.HttpHeaders;
import vn.vnest.business.BaseBusiness;
import vn.vnest.dbo.MarketDBO;
import vn.vnest.entities.ProduceUpdate;
import vn.vnest.response.BaseResponse;
import vn.vnest.response.GetProductMartketResponse;

public class GetProductMarketUpdate extends BaseBusiness {
	
	private static final Logger log = LogManager.getLogger(GetProductMarketUpdate.class);

	@Override
	public BaseResponse process(HttpHeaders headers, HashMap<String, String> params, String body) {
		String codeMarket = params.get("codeMarket");
		String products = params.get("products");
		List<ProduceUpdate> listproduct  = new ArrayList<ProduceUpdate>();
	
		try {
			if (products == null || products.isEmpty()) {
				listproduct = MarketDBO.getProductUpdate(codeMarket);
			} else {
				listproduct = MarketDBO.getListProduct(codeMarket, products);

			}			
			return new GetProductMartketResponse(listproduct);
		} catch (Exception e) {
			log.info("ex :" + e.toString());
		}

		return null;
	}

}
