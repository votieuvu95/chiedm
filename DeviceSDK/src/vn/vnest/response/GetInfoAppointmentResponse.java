package vn.vnest.response;

import java.util.ArrayList;

import vn.vnest.entities.InfoAppointment;

public class GetInfoAppointmentResponse extends BaseResponse {
	private ArrayList<InfoAppointment> infoAppointment;
	public GetInfoAppointmentResponse(ArrayList<InfoAppointment> infoAppointment) {
		if (infoAppointment != null) {
			setCode(SUCCESS);
			setDescription("Success");
			this.infoAppointment = infoAppointment;

		} else {
			setCode(FAILURE);
			setDescription("Customer not found");
		}
	}
	public ArrayList<InfoAppointment> getInfoAppointment() {
		return infoAppointment;
	}
	public void setInfoAppointment(ArrayList<InfoAppointment> infoAppointment) {
		this.infoAppointment = infoAppointment;
	}
	
	
}
