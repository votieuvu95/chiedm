package vn.vnest.response;

public class DeviceResponse extends BaseResponse {
	public DeviceResponse(String code, String description) {
		super(code, description);
	}
	
	public DeviceResponse(int code) {
		if(code > 0) {
			setCode(SUCCESS);
			setDescription("Success");
		} else {
			setCode(FAILURE);
			setDescription("Fail");
		}
	}

}
