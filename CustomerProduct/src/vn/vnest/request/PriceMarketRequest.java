package vn.vnest.request;

import java.sql.Timestamp;

public class PriceMarketRequest {

	private String code, codeMartket, price, priceSale, info, name;
	private Timestamp craetedDate, UpdatedDate;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCodeMartket() {
		return codeMartket;
	}
	public void setCodeMartket(String codeMartket) {
		this.codeMartket = codeMartket;
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
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public Timestamp getCraetedDate() {
		return craetedDate;
	}
	public void setCraetedDate(Timestamp craetedDate) {
		this.craetedDate = craetedDate;
	}
	public Timestamp getUpdatedDate() {
		return UpdatedDate;
	}
	public void setUpdatedDate(Timestamp updatedDate) {
		UpdatedDate = updatedDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
