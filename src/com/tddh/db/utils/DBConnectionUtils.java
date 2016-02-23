package com.tddh.db.utils;

import java.sql.Connection;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.tddh.utils.PropertiesUtils;

//database utils
public class DBConnectionUtils {
	private static ComboPooledDataSource cpds = null;

	// initialize ComboPooledDataSource
	private static void initCPDS() {
		try {
			// create database connection pool
			String DRIVER_NAME = PropertiesUtils.readProperties("jdbc", "driver"); // Driver
			String DATABASE_URL = PropertiesUtils.readProperties("jdbc", "url"); // url
			String DATABASE_USER = PropertiesUtils.readProperties("jdbc", "user"); // user
																					// name
			String DATABASE_PASSWORD = PropertiesUtils.readProperties("jdbc", "password");// password
			int MIN_POOLSIZE = Integer.parseInt(PropertiesUtils.readProperties("jdbc", "minPoolSize"));
			int MAX_POOLSIZE = Integer.parseInt(PropertiesUtils.readProperties("jdbc", "maxPoolSize"));
			int ACQUIRE_INCREMENT = Integer.parseInt(PropertiesUtils.readProperties("jdbc", "acquireIncrement"));
			int INITIAL_POOLSIZE = Integer.parseInt(PropertiesUtils.readProperties("jdbc", "initialPoolSize"));
			// test weather the connection is usable every 3000s
			int IDLE_TEST_PERIOD = Integer.parseInt(PropertiesUtils.readProperties("jdbc", "idleConnectionTestPeriod"));
			int MAX_IDLE_TIME = Integer.parseInt(PropertiesUtils.readProperties("jdbc", "maxIdleTime"));
			// check weather connection is usable when connect
			boolean CONNECTION_ON_CHECKOUT = Boolean
					.parseBoolean(PropertiesUtils.readProperties("jdbc", "testConnectionOnCheckout"));
			boolean CONNECTION_ON_CHECKIN = Boolean
					.parseBoolean(PropertiesUtils.readProperties("jdbc", "testConnectionOnCheckin"));
			boolean AUTO_COMMIT_ON_CLOSE = Boolean
					.parseBoolean(PropertiesUtils.readProperties("jdbc", "autoCommitOnClose"));
			String AUTO_TEST_TABLE = PropertiesUtils.readProperties("jdbc", "automaticTestTable");
			int ACQUIRE_RETRY_DELAY = Integer.parseInt(PropertiesUtils.readProperties("jdbc", "acquireRetryDelay"));
			int ACQUIRE_RETRY_ATTEMPTS = Integer
					.parseInt(PropertiesUtils.readProperties("jdbc", "acquireRetryAttempts"));
			int CHECKOUT_TIMEOUT = Integer.parseInt(PropertiesUtils.readProperties("jdbc", "checkoutTimeout"));
			cpds = new ComboPooledDataSource();
			cpds.setDriverClass(DRIVER_NAME); // driver
			cpds.setJdbcUrl(DATABASE_URL); // url
			cpds.setUser(DATABASE_USER); // user
			cpds.setPassword(DATABASE_PASSWORD); // password
			cpds.setInitialPoolSize(INITIAL_POOLSIZE); // initialize pool size
			cpds.setMinPoolSize(MIN_POOLSIZE); // minimum connection counts
			cpds.setMaxPoolSize(MAX_POOLSIZE); // maximum connection counts
			cpds.setAcquireIncrement(ACQUIRE_INCREMENT);
			cpds.setAcquireRetryAttempts(ACQUIRE_RETRY_ATTEMPTS);
			cpds.setAcquireRetryDelay(ACQUIRE_RETRY_DELAY);
			cpds.setAutoCommitOnClose(AUTO_COMMIT_ON_CLOSE);
			cpds.setCheckoutTimeout(CHECKOUT_TIMEOUT);
			cpds.setIdleConnectionTestPeriod(IDLE_TEST_PERIOD);
			cpds.setMaxIdleTime(MAX_IDLE_TIME);
			cpds.setTestConnectionOnCheckin(CONNECTION_ON_CHECKIN);
			cpds.setTestConnectionOnCheckout(CONNECTION_ON_CHECKOUT);
			cpds.setAutomaticTestTable(AUTO_TEST_TABLE);
		} catch (Exception e) {

		}
	}

	// get database connection
	public static Connection getConnection() {
		Connection connection = null;
		if (cpds == null) {
			initCPDS();
		}
		try {
			connection = cpds.getConnection();
			return connection;
		} catch (Exception e) {
		}
		return null;
	}

	// release connection pool
	public static void releaseConnectionPool() {
		try {
			if (cpds != null) {
				cpds.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
