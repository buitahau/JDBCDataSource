package com.haubui.datasource;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class MyDataSourceFactory {

	public static DataSource getMySQLDataSource() {

		Properties props = new Properties();
		FileInputStream fis;
		MysqlDataSource mysqlDataSource = null;

		try {
			fis = new FileInputStream("db.properties");
			props.load(fis);

			mysqlDataSource = new MysqlDataSource();
			mysqlDataSource.setURL(props.getProperty("DB_URL"));
			mysqlDataSource.setUser(props.getProperty("DB_USERNAME"));
			mysqlDataSource.setPassword(props.getProperty("DB_PASSWORD"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		return mysqlDataSource;
	}
}
