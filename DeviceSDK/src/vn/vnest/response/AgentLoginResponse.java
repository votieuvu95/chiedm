package vn.vnest.response;

public class AgentLoginResponse extends BaseResponse {
	private String token;
	public AgentLoginResponse(String code) {
		if (!code.isEmpty()) {
			setCode(SUCCESS);
			setDescription("Success");
			this.token = code;
		} else {
			setCode(FAILURE);
			setDescription("Fail");
		}
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	
}
