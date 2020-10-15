package vn.vnest.response;

public class MarketResponse extends BaseResponse {
	private int codestatus;
	
	public MarketResponse(int code) {
		
		if(code > 0) {
			setCode(SUCCESS);
			setDescription("Succsess");
		} else {
			setCode(FAILURE);
			setDescription("Fail");
		}
	}

	public int getCodestatus() {
		return codestatus;
	}

	public void setCodestatus(int codestatus) {
		this.codestatus = codestatus;
	}


	
}
