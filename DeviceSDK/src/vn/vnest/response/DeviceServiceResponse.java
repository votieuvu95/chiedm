package vn.vnest.response;

public class DeviceServiceResponse extends BaseResponse {
	public DeviceServiceResponse(String code, String description) {
		super(code, description);
	}

	public DeviceServiceResponse(int code) {
		if (code > 0) {
			setCode(SUCCESS);
			setDescription("Success");
		} else {
			setCode(FAILURE);
			setDescription("Fail");
		}
	}
}
