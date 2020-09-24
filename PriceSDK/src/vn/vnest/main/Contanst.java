package vn.vnest.main;

import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Contanst {

	private static final Logger log = LogManager.getLogger(Contanst.class);
	public static final String configName = "conf/Petroleum.properties";
	private static int  port = 8082;
	private static String url = "";


	public static int getPort() {
		return port;
	}

	public static void setPort(int port) {
		Contanst.port = port;
	}



	public static String getUrl() {
		return url;
	}

	public static void setUrl(String url) {
		Contanst.url = url;
	}

	public static void changeConfig(String content) {
		Properties properties = new Properties();
		try {
			properties.load(new StringReader(content));
			if(properties!=null) {
				port = Integer.parseInt(properties.getProperty("PORT" , port + ""));
				url = properties.getProperty("URL", url + "");
			log.info("port = " + port 
					+ "\n url = " +  url);
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}
