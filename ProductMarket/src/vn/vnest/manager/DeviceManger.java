package vn.vnest.manager;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

	public void init() {
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
