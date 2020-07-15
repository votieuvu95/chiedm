package vn.vnest.business.detail;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.netty.handler.codec.http.HttpHeaders;
import vn.vnest.business.BaseBusiness;
import vn.vnest.dbo.DeviceDBO;
import vn.vnest.response.BaseResponse;
import vn.vnest.response.GetActivationCodeResponse;

public class GetActivationCode extends BaseBusiness {
	private static final Logger log = LogManager.getLogger(CreateActivationCode.class);

	@Override
	protected boolean extraValidData(String input) {
		return true;
	}

	@Override
	public BaseResponse process(HttpHeaders headers, HashMap<String, String> params, String body) {
		String param = params.get("phone");
		try {
			String res = DeviceDBO.GetActivationCode(param);
			return new GetActivationCodeResponse(res);
		} catch (Exception e) {
			log.info("", e);
		}
		return null;
	}

}
