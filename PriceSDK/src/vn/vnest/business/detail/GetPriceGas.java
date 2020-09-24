package vn.vnest.business.detail;

import java.util.HashMap;

import io.netty.handler.codec.http.HttpHeaders;
import vn.vnest.business.BaseBusiness;
import vn.vnest.price.PricePetroleum;
import vn.vnest.respone.BaseResponse;
import vn.vnest.respone.PriceResponse;

public class GetPriceGas extends BaseBusiness {

	@Override
	public BaseResponse process(HttpHeaders headers, HashMap<String, String> params, String body) {
		String type = params.get("object");
		String s = PricePetroleum.getPrice(type);
		return  new PriceResponse(s);
	}

}
