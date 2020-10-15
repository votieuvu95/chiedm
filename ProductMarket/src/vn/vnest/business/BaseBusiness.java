package vn.vnest.business;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

import io.netty.handler.codec.http.HttpHeaders;
import vn.vnest.gson.GsonUtils;
import vn.vnest.request.BaseRequest;
import vn.vnest.response.BaseResponse;

public abstract class BaseBusiness implements IBusiness {
	private static final Logger logger = LogManager.getLogger(BaseBusiness.class);
	String topic;

	@Override
	public String getTopic() {
		return topic;
	}

	@Override
	public void setTopic(String topic) {
		this.topic = topic;
	}

	@Override
	public String procces(HttpHeaders headers, HashMap<String, String> params, String body) throws Exception {
		BaseResponse res;
//		if (!validInputData(body)) {
//			res = new BaseResponse(BaseResponse.FAILURE, "");
//		} else {
//			res = process(headers, params, body);
//		}
		res = process(headers, params, body);
		Gson gson = GsonUtils.createNormal();
		String response = gson.toJson(res);
		logger.info("Response:" + response);
		return response;
	}

//	public boolean validInputData(String data) {
//		if (data != null && data.trim().length() > 0) {
//			Gson gson = GsonUtils.createNormal();
//			BaseRequest rq = gson.fromJson(data, BaseRequest.class);
//			if (!rq.validRequest()) {
//				return false;
//			}
//			return extraValidData(data);
//		}
//		return true;
//	}
//
//	protected abstract boolean extraValidData(String input);

	public abstract BaseResponse process(HttpHeaders headers, HashMap<String, String> params, String body);

}
