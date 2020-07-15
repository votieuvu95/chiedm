package vn.vnest.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;
import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Constant {
	private static final Logger log = LogManager.getLogger(Constant.class);

	private static int port = 8082;
	public static final String configFileName = "conf/base.properties";
	
	private static String rasaUrl = "http://localhost:5050/model/parse?emulation_mode=dialogflow";
	private static String s2tUrl = "http://localhost:9090";
	private static String t2sUrl = "http://localhost:9091";
	private static String mapUrl = "http://localhost:9092";
	private static String mediaUrl = "http://localhost:9093";

	public static String getMapUrl() {
		return mapUrl;
	}
	public static String getMediaUrl() {
		return mediaUrl;
	}
	public static String getRasaUrl() {
		return rasaUrl;
	}
	public static String getS2tUrl() {
		return s2tUrl;
	}
	public static String getT2sUrl() {
		return t2sUrl;
	}
	
	private static String prefixTopic = "/";

	public static int getPort() {
		return port;
	}

	public static String getPrefixTopic() {
		return prefixTopic;
	}

//	public static void init() {
//		Properties appConfig = new Properties();
//		try {
//			appConfig.load(new FileInputStream(configFileName));
//			init_(appConfig);
//		} catch (FileNotFoundException e) {
//			log.info("ReadingConfig error:", e);
//		} catch (IOException e) {
//			log.info("ReadingConfig error:", e);
//		}
//	}
	public static void changeConfig(String content) {
		Properties appConfig = new Properties();
		try {
			appConfig.load(new StringReader(content));
			init_(appConfig);
		} catch (FileNotFoundException e) {
			log.info("ReadingConfig error:", e);
		} catch (IOException e) {
			log.info("ReadingConfig error:", e);
		}
	}
	private static void init_(Properties appConfig) {
		if (appConfig != null) {
			port = Integer.parseInt(appConfig.getProperty("PORT", port + ""));
			rasaUrl = appConfig.getProperty("rasaUrl", rasaUrl);
			s2tUrl = appConfig.getProperty("s2tUrl", s2tUrl);
			prefixTopic = appConfig.getProperty("prefixTopic", prefixTopic);
			t2sUrl = appConfig.getProperty("t2sUrl", t2sUrl);
			mapUrl = appConfig.getProperty("mapUrl", mapUrl);
			mediaUrl = appConfig.getProperty("mediaUrl", mediaUrl);
			log.info("ReadingConfig complete:");
			log.info("port=" + port 
					+ "\n rasaUrl=" + rasaUrl 
					+ "\n prefixTopic=" + prefixTopic 
					+ "\n s2tUrl=" + s2tUrl
					+ "\n t2sUrl=" + t2sUrl 
					+ "\n mapUrl=" + mapUrl 
					+ "\n mediaUrl=" + mediaUrl);
		}
	}

	public static String makeUniqueId() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}

