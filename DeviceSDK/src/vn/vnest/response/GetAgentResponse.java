package vn.vnest.response;

import java.util.ArrayList;

import vn.vnest.entities.InfoAgent;

public class GetAgentResponse extends BaseResponse {

	private ArrayList<InfoAgent> agents;

	public GetAgentResponse(ArrayList<InfoAgent> agents) {
		if (agents != null) {
			setCode(SUCCESS);
			setDescription("Success");
			this.agents = agents;
		} else {
			setCode(FAILURE);
			setDescription("Fail");
		}
	}

	public ArrayList<InfoAgent> getAgents() {
		return agents;
	}

	public void setAgents(ArrayList<InfoAgent> agents) {
		this.agents = agents;
	}

}
