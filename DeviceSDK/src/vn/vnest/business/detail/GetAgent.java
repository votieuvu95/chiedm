package vn.vnest.business.detail;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.netty.handler.codec.http.HttpHeaders;
import vn.vnest.business.BaseBusiness;
import vn.vnest.dbo.DeviceDBO;
import vn.vnest.entities.InfoAgent;
import vn.vnest.response.BaseResponse;
import vn.vnest.response.GetAgentResponse;

public class GetAgent extends BaseBusiness {
	private static final Logger log = LogManager.getLogger(GetAgent.class);

	@Override
	protected boolean extraValidData(String input) {
		return true;
	}

	@Override
	public BaseResponse process(HttpHeaders headers, HashMap<String, String> params, String body) {
		String agentCode = params.get("agentCode");
		try {
			ArrayList<InfoAgent> agents = DeviceDBO.getAgent(agentCode);
			return new GetAgentResponse(agents);
		} catch (Exception e) {
			log.info("", e);
		}
		return null;
	}

}
