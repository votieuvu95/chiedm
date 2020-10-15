package vn.vnest.response;

import java.util.List;

import vn.vnest.entities.ProduceUpdate;

public class GetProductMartketResponse extends BaseResponse {
	
	private List<ProduceUpdate> produce;
	
	public GetProductMartketResponse(List<ProduceUpdate> produceUpdate) {
		
		if(produceUpdate!=null) {
			setCode(SUCCESS);
			setDescription("Success");
			this.produce = produceUpdate;
		}
		else {
			setCode(FAILURE);
			setDescription("Fail");
		}
		
	}

	public List<ProduceUpdate> getProduce() {
		return produce;
	}

	public void setProduce(List<ProduceUpdate> produce) {
		this.produce = produce;
	}



}
