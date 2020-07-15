package vn.vnest.business.detail;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

import io.netty.handler.codec.http.HttpHeaders;
import vn.vnest.business.BaseBusiness;
import vn.vnest.dbo.DeviceDBO;
import vn.vnest.gson.GsonUtils;
import vn.vnest.request.HistoryAppointmentRequest;
import vn.vnest.response.BaseResponse;
import vn.vnest.response.HistoryAppointmentResponse;

public class HistoryAppointment extends BaseBusiness {
	private static final Logger logger = LogManager.getLogger(HistoryAppointmentRequest.class);
	private static Gson gson = GsonUtils.createAllLower();

	@Override
	protected boolean extraValidData(String input) {
		return true;
	}

	@Override
	public BaseResponse process(HttpHeaders headers, HashMap<String, String> params, String body) {
		HistoryAppointmentRequest appointmentRequest = gson.fromJson(body, HistoryAppointmentRequest.class);
		try {
			int res = DeviceDBO.historyAppointment(appointmentRequest);
			return new HistoryAppointmentResponse(res);
		} catch (Exception e) {
			logger.info("", e);
		}
		return null;
	}

}
