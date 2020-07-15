package vn.vnest.response;

public class CreateActivationCodeResponse extends BaseResponse {
	private String activationCode;

	public CreateActivationCodeResponse(String activationCode) {
		if (activationCode != null) {
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
