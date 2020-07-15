package vn.vnest.business.detail;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.netty.handler.codec.http.HttpHeaders;
import vn.vnest.business.BaseBusiness;
import vn.vnest.dbo.DeviceDBO;
import vn.vnest.entities.InfoDevice;
import vn.vnest.response.BaseResponse;
import vn.vnest.response.GetInfoDeviceResponse;

public class GetInfoDevice extends BaseBusiness {
	private static final Logger logger = LogManager.getLogger(GetInfoDevice.class);

	@Override
	protected boolean extraValidData(String input) {
		return true;
	}

	@Override
	public BaseResponse process(HttpHeaders headers, HashMap<String, String> params, String body) {
		String deviceId = params.get("deviceId");
		try {
			ArrayList<InfoDevice> infoDevices = DeviceDBO.getDeivceInfo(deviceId);
			return new GetInfoDeviceResponse(infoDevices);
		} catch (Exception e) {
			logger.info("", e);
		}
		return null;
	}

}
