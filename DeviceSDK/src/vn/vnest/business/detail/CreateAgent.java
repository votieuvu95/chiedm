package vn.vnest.business.detail;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

import io.netty.handler.codec.http.HttpHeaders;
import vn.vnest.business.BaseBusiness;
import vn.vnest.dbo.DeviceDBO;
import vn.vnest.gson.GsonUtils;
import vn.vnest.request.CreateAgentRequest;
import vn.vnest.response.BaseResponse;
import vn.vnest.response.CreateAgentResponse;

public class CreateAgent extends BaseBusiness {
	private static final Logger log = LogManager.getLogger(CreateAgent.class);
	private static Gson gson = GsonUtils.createAllLower();
	@Override
	protected boolean extraValidData(String input) {
		return true;
	}

	@Override
	public BaseResponse process(HttpHeaders headers, HashMap<String, String> params, String body) {
		CreateAgentRequest request = gson.fromJson(body, CreateAgentRequest.class);
		try {
			int code = DeviceDBO.createAgent(request);
			return new CreateAgentResponse(code);
		} catch (Exception e) {
			log.info("",e);
		}
		return null;
	}

}
