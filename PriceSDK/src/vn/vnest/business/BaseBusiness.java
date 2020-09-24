package vn.vnest.business;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

import io.netty.handler.codec.http.HttpHeaders;
import vn.vnest.respone.BaseResponse;
import vn.vnest.utils.GsonUtils;

public abstract class BaseBusiness implements IBusiness  {
	private static final Logger log = LogManager.getLogger(BaseBusiness.class);
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
	public String proces(HttpHeaders headers, HashMap<String, String> params, String body) throws Exception {
		BaseResponse res;
		res = process(headers, params, body);
		Gson gson = GsonUtils.createNormal();
		String respone = gson.toJson(res);
		
		return respone;
	}

	public abstract BaseResponse process(HttpHeaders headers, HashMap<String, String> params, String body);

}
