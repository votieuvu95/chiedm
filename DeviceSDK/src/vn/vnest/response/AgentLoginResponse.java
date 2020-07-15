package vn.vnest.response;

public class AgentLoginResponse extends BaseResponse {
	
	public AgentLoginResponse(int code) {
		if (code > 0) {
			setCode(SUCCESS);
			setDescription("Success");
		} else {
			setCode(FAILURE);
			setDescription("Fail");
		}
	}
}
