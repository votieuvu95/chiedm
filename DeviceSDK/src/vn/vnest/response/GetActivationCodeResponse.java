package vn.vnest.response;

public class GetActivationCodeResponse extends BaseResponse {
	private String activationCode;
	
	public GetActivationCodeResponse(String activationCode) {
		if(activationCode != null) {
			this.activationCode = activationCode;
		} else {
			setCode(FAILURE);
			setDescription("Fail");
		}
	}

	public String getActivationCode() {
		return activationCode;
	}

	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}

	
}
