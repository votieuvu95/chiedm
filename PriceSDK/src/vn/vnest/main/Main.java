package vn.vnest.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import vn.vnest.business.BusinessManager;
import vn.vnest.server.HttpServer;

public class Main {
	private static final Logger log = LogManager.getLogger(Main.class);
	public static void main(String[] args) {
		try {
			new ContanstUpdate().start();
			BusinessManager.getInstance().init();
			HttpServer.start();
		} catch (Exception e) {
			log.info("ERROR" + e.getMessage());
		}
	}
}
