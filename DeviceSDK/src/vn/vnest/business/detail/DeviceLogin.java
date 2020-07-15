package vn.vnest.business.detail;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

import io.netty.handler.codec.http.HttpHeaders;
import vn.vnest.business.BaseBusiness;
import vn.vnest.dbo.DeviceDBO;
import vn.vnest.gson.GsonUtils;
import vn.vnest.request.DeviceLoginRequest;
import vn.vnest.response.BaseResponse;
import vn.vnest.response.DeviceResponse;

public class DeviceLogin extends BaseBusiness {
	private static final Logger logger = LogManager.getLogger(DeviceLogin.class);
	private static Gson gson = GsonUtils.createAllLower();

	@Override
	protected boolean extraValidData(String body) {
		return true;
	}

	@Override
	public BaseResponse process(HttpHeaders headers, HashMap<String, String> params, String body) {
		DeviceLoginRequest devReq = gson.fromJson(body, DeviceLoginRequest.class);
		try {
			int res = DeviceDBO.deviceLogin(devReq);
			return new DeviceResponse(res);
		} catch (Exception e) {
			logger.info("Ex:" + e.toString(), e);
		}
		return null;
	}

}
