package vn.vnest.response;

import java.util.List;

import vn.vnest.entities.ProduceUpdate;

public class ProductResponse extends BaseResponse {

	private List<ProduceUpdate> json;
	
	public ProductResponse(List<ProduceUpdate> json) {
		setCode("1");
		setDescription("Success");
		this.json = json;
	}
}
