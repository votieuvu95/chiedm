package vn.vnest.entities;

public class InfoAgent {
	
	private String agentCode, name, address;

	
	
	public InfoAgent(String agentCode, String name, String address) {
		super();
		this.agentCode = agentCode;
		this.name = name;
		this.address = address;
	}

	public String getAgentCode() {
		return agentCode;
	}

	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
