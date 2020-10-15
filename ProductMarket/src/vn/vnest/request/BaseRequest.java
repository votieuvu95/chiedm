package vn.vnest.request;

public class BaseRequest {
	private String phone;
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
//	public boolean validRequest() {
//		return validPhone();
//	}
//	private boolean validPhone() {
//		try {
//			String phone = getPhone();
//			Long.parseLong(phone);
//			return phone != null && (phone.length() == 10 ||phone.length() == 11) && phone.startsWith("0");
//		}catch (Exception e) {
//			return false;
//		}
//		
//	}
}
