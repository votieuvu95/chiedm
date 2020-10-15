package vn.vnest.main;


import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import vn.vnest.business.BusinessManager;
import vn.vnest.gson.GsonUtils;
import vn.vnest.manager.DeviceManger;
import vn.vnest.server.HttpClient;
import vn.vnest.server.HttpServer;
import vn.vnest.session.ChannelManager;

public class HTTPServer {
	private static final Logger log = LogManager.getLogger(HTTPServer.class);

	private static HashMap< String, String> products = new HashMap<>();
	private static Gson gson = GsonUtils.createAllLower();

	private static ChannelManager channelManager;
	
	public static ChannelManager getChannelManager() {
		return channelManager;
	}


	public static void main(String[] args) {
		try {
			new ConstantUpdate().start();
			new AutoGetProduct().start();
	
//			autoGetProduct();
			DeviceManger.getInstance().init();
			BusinessManager.getInstance().init();
			channelManager = new ChannelManager();
			HttpServer.start();
		} catch (Throwable e) {
			log.info("ERROR", e);
		}
	}
	
	
}
