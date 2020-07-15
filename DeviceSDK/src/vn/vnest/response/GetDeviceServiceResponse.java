package vn.vnest.response;

import java.util.ArrayList;

import vn.vnest.request.DeviceServiceRequest;

public class GetDeviceServiceResponse extends BaseResponse {

	
	private ArrayList<DeviceServiceRequest> deviceService;
	public GetDeviceServiceResponse(ArrayList<DeviceServiceRequest> deviceService) {
		if (deviceService != null) {
			setCode(SUCCESS);
			setDescription("Success");
			this.deviceService = deviceService;
		} else {
			setCode(FAILURE);
			setDescription("Fail");
		}

	}
	
	public ArrayList<DeviceServiceRequest> getDeviceService() {
		return deviceService;
	}
	public void setDeviceService(ArrayList<DeviceServiceRequest> deviceService) {
		this.deviceService = deviceService;
	}

	
	
}
