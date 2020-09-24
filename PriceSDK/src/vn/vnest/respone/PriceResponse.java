package vn.vnest.respone;

public class PriceResponse extends BaseResponse {
	private String price;
	
	public PriceResponse(String price) {
		setCode("1");
		setDescription("Success");
		setPrice(price);
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}



	
}
