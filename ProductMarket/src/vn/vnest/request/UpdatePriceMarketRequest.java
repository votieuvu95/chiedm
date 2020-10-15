package vn.vnest.request;

public class UpdatePriceMarketRequest {
	private String codeProduct,codeMarket,price,priceSale;

	public String getCodeProduct() {
		return codeProduct;
	}

	public void setCodeProduct(String codeProduct) {
		this.codeProduct = codeProduct;
	}

	public String getCodeMarket() {
		return codeMarket;
	}

	public void setCodeMarket(String codeMarket) {
		this.codeMarket = codeMarket;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPriceSale() {
		return priceSale;
	}

	public void setPriceSale(String priceSale) {
		this.priceSale = priceSale;
	}
	
	

}
