package vn.vnest.entities;

public class ProduceUpdate {
	private String price,name,pricesale,code;

	
	public ProduceUpdate(String price, String name, String pricesale, String code) {
		super();
		this.price = price;
		this.name = name;
		this.pricesale = pricesale;
		this.code = code;
	}
	

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPricesale() {
		return pricesale;
	}

	public void setPricesale(String pricesale) {
		this.pricesale = pricesale;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}

	
	
	

}
