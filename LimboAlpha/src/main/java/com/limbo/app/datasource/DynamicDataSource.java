package com.limbo.app.datasource;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.AbstractDriverBasedDataSource;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

import com.limbo.app.authentication.LoggedUser;
import com.limbo.app.web.ClientController;

public class DynamicDataSource extends AbstractDriverBasedDataSource {

	private static final org.slf4j.Logger logger = LoggerFactory
			.getLogger(ClientController.class);

	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		return null;
	}

	@Override
	protected Connection getConnectionFromDriver(Properties args)
			throws SQLException {
		logger.info("kacenar1: Connection estableshing");
		String url = null;
		try {
			LoggedUser user = (LoggedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			url = getUrl();
			url += user.getCurrentDatabase();
		logger.info("Creating new JDBC DriverManager Connection to ["
				+ url + "]");
		} catch(Exception e){
			logger.info("Need to login befor accessing dynamic DB");
		}
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
