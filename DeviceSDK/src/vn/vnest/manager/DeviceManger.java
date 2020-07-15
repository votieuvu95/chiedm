package vn.vnest.manager;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import vn.vnest.entities.InfoDevice;

public class DeviceManger {

	private static final Logger logger = LogManager.getLogger(DeviceManger.class);
	private static DeviceManger instance;

	public static DeviceManger getInstance() {
		if (instance == null) {
			instance = new DeviceManger();
		}
		return instance;
	}

	public DeviceManger() {

	}

	private ArrayList<InfoDevice> DeivceInfo;

	public ArrayList<InfoDevice> getDeivceInfo(String deviceId) {
		return DeivceInfo;
	}

	public void setDeivceInfo(ArrayList<InfoDevice> deivceInfo) {
		DeivceInfo = deivceInfo;
	}

	public void init() {
		DeivceInfo = new ArrayList<InfoDevice>();
		load();
		new Monitor().start();
		;
	}

	public void load() {
//		try {        
//			DeivceInfo = DeviceDBO.getDeivceInfo();
//		} catch (Exception e) {
//			logger.info("", e);
//		}
	}

	private void reload() {

		load();
	}

	private class Monitor extends Thread {
		@Override
		public void run() {
			while (true) {
				try {
					TimeUnit.MINUTES.sleep(10);
				} catch (Exception e) {
				}
				reload();
			}
		}
	}
}
