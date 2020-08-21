package com.haubui.datasource;

import com.haubui.jdbc.DBConnection;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataSourceTest {

	private static final String QUERY =
					"select id, name, email, country, password from Users";

	public static void main(String[] args) {

		testDataSource();
	}

	private static void testDataSource() {

		DataSource ds;
		ds = MyDataSourceFactory.getMySQLDataSource();

		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(QUERY);

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String country = rs.getString("country");
				String password = rs.getString("password");
				System.out.println(id + ", " + name + ", " + email + ", " +
								country + ", " + password);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (con != null) {
					con.close();
				}
			}
			catch (SQLException throwables) {
				throwables.printStackTrace();
			}
		}
	}
}
