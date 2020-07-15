package vn.vnest.response;

public class ActiveDeviceResponse extends BaseResponse {
	private int activationCode;
	
	public ActiveDeviceResponse(int activationCode) {
		if(activationCode > 0 ) {
			setCode(SUCCESS);
			setDescription("Success");
		} else {
			setCode(FAILURE);
			setDescription("Fail");
		}
	}

	public int getActivationCode() {
		return activationCode;
	}

	public void setActivationCode(int activationCode) {
		this.activationCode = activationCode;
	}
	
}
