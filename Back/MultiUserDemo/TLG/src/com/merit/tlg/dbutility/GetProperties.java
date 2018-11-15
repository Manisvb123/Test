package com.merit.tlg.dbutility;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Date;
import java.util.Properties;

public class GetProperties {
    public static String JDBC_DRIVER =null;
    public static String JDBC_DB_URL =null;
    public static String USER_NAME =null;
    public static String PASSWORD =null;
    
	public Properties getProperties() {
		InputStream inputStream = null;
		Properties prop = new Properties();

		try {
			String propFileName = "config.properties";
 
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
 
			Date time = new Date(System.currentTimeMillis());
 
			// get the property value and print it out
		    JDBC_DRIVER = prop.getProperty("JDBC_DRIVER");
		    JDBC_DB_URL = prop.getProperty("JDBC_DB_URL");
		    USER_NAME = prop.getProperty("USER_NAME");
		    PASSWORD = prop.getProperty("PASSWORD");

	
		
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
		}
		return prop;
		
		
	}


}
