package vn.vnest.business.detail;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

import io.netty.handler.codec.http.HttpHeaders;
import vn.vnest.business.BaseBusiness;
import vn.vnest.dbo.DeviceDBO;
import vn.vnest.gson.GsonUtils;
import vn.vnest.request.ActiveDeviceRequest;
import vn.vnest.response.ActiveDeviceResponse;
import vn.vnest.response.BaseResponse;

public class ActiveDevice extends BaseBusiness {
	private static final Logger log = LogManager.getLogger(CreateActivationCode.class);
	private static Gson gson = GsonUtils.createAllLower();

	@Override
	protected boolean extraValidData(String input) {
		return true;
	}

	@Override
	public BaseResponse process(HttpHeaders headers, HashMap<String, String> params, String body) {
		ActiveDeviceRequest request = gson.fromJson(body, ActiveDeviceRequest.class);
		try {
			int res = DeviceDBO.ActiveDevice(request);
			return new ActiveDeviceResponse(res);
		} catch (Exception e) {
			log.info("", e);
		}

		return null;
	}

}
