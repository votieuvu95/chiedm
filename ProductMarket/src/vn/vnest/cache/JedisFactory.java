package vn.vnest.cache;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import redis.clients.jedis.Jedis;

public class JedisFactory {
	private static Logger log = LogManager.getLogger(JedisFactory.class);
	private  Jedis jedisPool;
	private  static JedisFactory jedisFactory;

	private JedisFactory() throws FileNotFoundException, IOException {
		Properties appConfig = new Properties();
		appConfig.load(new FileInputStream("conf/jedis.properties"));
		try {
			jedisPool = new Jedis( appConfig.getProperty("HOST"),
					Integer.parseInt(appConfig.getProperty("PORT")));
		} catch (Exception e) {
			log.info("jedisPool" + e.toString(), e);
		}

	}

	public static  JedisFactory getInstance() throws FileNotFoundException, IOException {
		if (jedisFactory == null) {
			jedisFactory = new JedisFactory();
		}
		return jedisFactory;
	}

	public Jedis getJedisPool() {
		return jedisPool;
	}

	public void setJedisPool(Jedis jedisPool) {
		this.jedisPool = jedisPool;
	}



}
