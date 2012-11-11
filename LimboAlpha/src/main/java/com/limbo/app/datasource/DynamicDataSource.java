package com.limbo.app.datasource;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.AbstractDriverBasedDataSource;

import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import com.limbo.app.web.ClientController;

public class DynamicDataSource extends AbstractDriverBasedDataSource {

	private static final org.slf4j.Logger logger = LoggerFactory
			.getLogger(ClientController.class);

	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Connection getConnectionFromDriver(Properties args)
			throws SQLException {
		logger.info("kacenar1: Connection estableshing");
		// TODO Auto-generated method stub
		String url = getUrl();
		url +="skupka";
		logger.info("Creating new JDBC DriverManager Connection to ["
				+ url + "]");
		return getConnectionFromDriverManager(url, args);
	}

	public void setDriverClassName(String driverClassName) {
		Assert.hasText(driverClassName,
				"Property 'driverClassName' must not be empty");
		String driverClassNameToUse = driverClassName.trim();
		try {
			Class.forName(driverClassNameToUse, true,
					ClassUtils.getDefaultClassLoader());
		} catch (ClassNotFoundException ex) {
			throw new IllegalStateException(
					"Could not load JDBC driver class [" + driverClassNameToUse
							+ "]", ex);
		}
		if (logger.isInfoEnabled()) {
			logger.info("kacenar1: Loaded JDBC driver: " + driverClassNameToUse);
		}
	}
	
	protected Connection getConnectionFromDriverManager(String url, Properties props) throws SQLException {
				return DriverManager.getConnection(url, props);
			}
}
