package vn.vnest.business.detail;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.netty.handler.codec.http.HttpHeaders;
import vn.vnest.business.BaseBusiness;
import vn.vnest.dbo.DeviceDBO;
import vn.vnest.request.DeviceServiceRequest;
import vn.vnest.response.BaseResponse;
import vn.vnest.response.GetDeviceServiceResponse;

public class GetInfoDeviceService extends BaseBusiness {
	private static final Logger logger = LogManager.getLogger(GetInfoDeviceService.class);

	@Override
	protected boolean extraValidData(String input) {
		return true;
	}

	@Override
	public BaseResponse process(HttpHeaders headers, HashMap<String, String> params, String body) {
		String deviceId = params.get("deviceId");
		String startDate = params.get("startDate");
		String endDate = params.get("endDate");
		String action = params.get("action");
		String count = params.get("count");
		String quantity = params.get("quantity");
		String amount = params.get("amount");

		try {
			ArrayList<DeviceServiceRequest> resquest = DeviceDBO.getInfoDeviceService(deviceId,startDate,endDate,action,count,quantity,amount);
			return new GetDeviceServiceResponse(resquest);
		} catch (Exception e) {
			logger.info("", e);
		}
		return null;
	}

}
