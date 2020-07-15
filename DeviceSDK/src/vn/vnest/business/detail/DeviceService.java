package vn.vnest.business.detail;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

import io.netty.handler.codec.http.HttpHeaders;
import vn.vnest.business.BaseBusiness;
import vn.vnest.dbo.DeviceDBO;
import vn.vnest.gson.GsonUtils;
import vn.vnest.request.DeviceServiceRequest;
import vn.vnest.response.BaseResponse;
import vn.vnest.response.DeviceResponse;

public class DeviceService extends BaseBusiness {
	private static final Logger log = LogManager.getLogger(DeviceService.class);
	private static Gson gson = GsonUtils.createAllLower();

	@Override
	protected boolean extraValidData(String input) {
		return true;
	}

	@Override
	public BaseResponse process(HttpHeaders headers, HashMap<String, String> params, String body) {
		DeviceServiceRequest service = gson.fromJson(body, DeviceServiceRequest.class);
		try {
			int res = DeviceDBO.deviceService(service);
			return new DeviceResponse(res);
		} catch (Exception e) {
			log.info("", e);
		}
		return null;
	}

}
