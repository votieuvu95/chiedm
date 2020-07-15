package vn.vnest.response;

public class HistoryAppointmentResponse extends BaseResponse {
	
	public HistoryAppointmentResponse(String code, String description) {
		super(code, description);
	}
	
	public HistoryAppointmentResponse(int code) {
		if(code != 0) {
			setCode(SUCCESS);
			setDescription("Success");
		} else {
			setCode(FAILURE);
			setDescription("Fail");
		}
	}
}
