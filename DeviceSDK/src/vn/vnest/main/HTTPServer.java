package vn.vnest.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import vn.vnest.business.BusinessManager;
import vn.vnest.manager.DeviceManger;
import vn.vnest.server.HttpServer;
import vn.vnest.session.ChannelManager;

public class HTTPServer {
	private static final Logger log = LogManager.getLogger(HTTPServer.class);


	private static ChannelManager channelManager;
	
	public static ChannelManager getChannelManager() {
		return channelManager;
	}


	public static void main(String[] args) {
		try {
			new ConstantUpdate().start();
			DeviceManger.getInstance().init();
			BusinessManager.getInstance().init();
			channelManager = new ChannelManager();
			HttpServer.start();
		} catch (Throwable e) {
			log.info("ERROR", e);
		}
	}
}
