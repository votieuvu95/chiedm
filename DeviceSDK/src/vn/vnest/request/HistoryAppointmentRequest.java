package vn.vnest.request;


public class HistoryAppointmentRequest extends BaseRequest {
	private String deviceId, content;
	private String startDate, endDate,question;
	private String status;

	public HistoryAppointmentRequest( String deviceId, String content, String startDate, String endDate) {
		super();
		this.deviceId = deviceId;
		this.content = content;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
