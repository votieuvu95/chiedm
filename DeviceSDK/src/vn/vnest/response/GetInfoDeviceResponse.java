package vn.vnest.response;

import java.util.ArrayList;


import vn.vnest.entities.InfoDevice;

public class GetInfoDeviceResponse extends BaseResponse {

	public GetInfoDeviceResponse(ArrayList<InfoDevice> infoDevices) {
		if (infoDevices != null) {
			setCode(SUCCESS);
			setDescription("Success");

		} else {
			setCode(FAILURE);
			setDescription("Customer not found");
		}
	}

}
