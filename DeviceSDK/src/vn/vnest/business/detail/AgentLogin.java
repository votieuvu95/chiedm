package vn.vnest.business.detail;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

import io.netty.handler.codec.http.HttpHeaders;
import vn.vnest.business.BaseBusiness;
import vn.vnest.dbo.DeviceDBO;
import vn.vnest.gson.GsonUtils;
import vn.vnest.request.AgentLoginRequest;
import vn.vnest.response.AgentLoginResponse;
import vn.vnest.response.BaseResponse;

public class AgentLogin extends BaseBusiness {
	private static final Logger log = LogManager.getLogger(AgentLogin.class);
	private static Gson gson = GsonUtils.createAllLower();

	@Override
	protected boolean extraValidData(String input) {
		return true;
	}

	@Override
	public BaseResponse process(HttpHeaders headers, HashMap<String, String> params, String body) {
		AgentLoginRequest request = gson.fromJson(body, AgentLoginRequest.class);
		try {
			String code = DeviceDBO.agentLogin(request);
			return new AgentLoginResponse(code);
		} catch (Exception e) {
			log.info("", e);
		}
		return null;
	}

}
