package vn.vnest.dbo;

import java.beans.PropertyVetoException;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

public class DeviceDataSource {
	private static final Logger log = LogManager.getLogger(DeviceDataSource.class);
	private static DeviceDataSource datasource;
	private BoneCP connectionPool;

	public void shutdown() {
		try {
			if (connectionPool != null)
				connectionPool.close();
		} catch (Throwable e) {
			log.info("", e);
		}
	}

	private DeviceDataSource() throws IOException, SQLException,
			PropertyVetoException {
		Properties appConfig = new Properties();
		appConfig.load(new FileInputStream("conf/bus-sdk-db.properties"));
		try {
			Class.forName(appConfig.getProperty("forName"));
		} catch (Exception e) {
			log.info("DataSource forName ex: " + e.toString(), e);
			return;
		}
		try {
			BoneCPConfig config = new BoneCPConfig();
			config.setJdbcUrl(appConfig.getProperty("jdbcUrl"));
			config.setUsername(appConfig.getProperty("user"));
			config.setPassword(appConfig.getProperty("password"));
			config.setMinConnectionsPerPartition(Integer.parseInt(appConfig
					.getProperty("minPoolSize")));
			config.setMaxConnectionsPerPartition(Integer.parseInt(appConfig
					.getProperty("maxPoolSize")));
//			config.setInitSQL("SELECT 1 FROM DUAL");
			config.setIdleConnectionTestPeriodInMinutes(5);
			config.setConnectionTimeoutInMs(5000);
			config.setAcquireRetryAttempts(10);
			config.setIdleMaxAgeInMinutes(10);
			config.setMaxConnectionAgeInSeconds(36000);
			config.setAcquireIncrement(Integer.parseInt(appConfig
					.getProperty("acquireIncrement")));
			config.setIdleMaxAgeInSeconds(Integer.parseInt(appConfig
					.getProperty("maxIdleTime")));
			config.setPartitionCount(2);
			connectionPool = new BoneCP(config);


		} catch (Exception e) {
			log.info("DataSource BoneCP config ex: " + e.toString(), e);
		}

	}

	public static DeviceDataSource getInstance() throws IOException, SQLException,
			PropertyVetoException {
		if (datasource == null) {
			datasource = new DeviceDataSource();
			return datasource;
		} else {
			return datasource;
		}
	}

	public Connection getConnection() throws SQLException {
		return this.connectionPool.getConnection();
	}
}