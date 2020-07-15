package vn.vnest.response;

public class CreateAgentResponse extends BaseResponse {
	public CreateAgentResponse(int code) {
		if (code > 0) {
			setCode(SUCCESS);
			setDescription("Success");
		} else {
			setCode(FAILURE);
			setDescription("Fail");
		}
	}
	
}
