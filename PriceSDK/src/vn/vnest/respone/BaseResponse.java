package vn.vnest.respone;

public class BaseResponse {
	public static final String SUCCESS = "1";
	public static final String FAIL = "0";

	private String code;
	private String description;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean issSuccess() {
		return SUCCESS.equals(code);
	}

	public BaseResponse(String code, String description) {
		super();
		this.code = code;
		this.description = description;
	}
	
	public BaseResponse() {
		
	}
	
	
}
