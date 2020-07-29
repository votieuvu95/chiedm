package vn.vnest.request;

import java.math.BigDecimal;

public class DeviceServiceRequest {
	private String deviceId,action,startDate,endDate,question;
	private String amount;
	private String count,quantity;
	
	
	
	public DeviceServiceRequest(String deviceId, String action, String startDate, String endDate,
			String amount, String count, String quantity) {
		super();
		this.deviceId = deviceId;
		this.action = action;
		this.startDate = startDate;
		this.endDate = endDate;
		this.amount = amount;
		this.count = count;
		this.quantity = quantity;
	}
	
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
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
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	
}
