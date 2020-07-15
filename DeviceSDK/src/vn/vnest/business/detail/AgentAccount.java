package vn.vnest.business.detail;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

import io.netty.handler.codec.http.HttpHeaders;
import vn.vnest.business.BaseBusiness;
import vn.vnest.dbo.DeviceDBO;
import vn.vnest.gson.GsonUtils;
import vn.vnest.request.AgentAccountRequest;
import vn.vnest.response.AgentAccountResponse;
import vn.vnest.response.BaseResponse;

public class AgentAccount extends BaseBusiness {
	private static final Logger log = LogManager.getLogger(AgentAccount.class);
	private static Gson gson = GsonUtils.createAllLower();

	@Override
	protected boolean extraValidData(String input) {
		return true;
	}

	@Override
	public BaseResponse process(HttpHeaders headers, HashMap<String, String> params, String body) {
		AgentAccountRequest request = gson.fromJson(body, AgentAccountRequest.class);
		try {
			int code = DeviceDBO.createAgentAccount(request);
			return new AgentAccountResponse(code);
		} catch (Exception e) {
			log.info("", e);
		}
		return null;
	}

}