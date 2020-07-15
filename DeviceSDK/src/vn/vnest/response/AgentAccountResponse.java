package vn.vnest.response;

public class AgentAccountResponse extends BaseResponse {
	public AgentAccountResponse(int code) {
		if (code > 0) {
			setCode(SUCCESS);
			setDescription("Success");
		} else {
			setCode(FAILURE);
			setDescription("Fail");
		}
	}
	
}
