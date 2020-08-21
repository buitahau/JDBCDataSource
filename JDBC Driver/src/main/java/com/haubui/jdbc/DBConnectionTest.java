package com.haubui.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnectionTest {

	private static final String QUERY =
		"select id, name, email, country, password from Users";

	public static void main(String[] args) {

		try {
			Connection con = DBConnection.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(QUERY);

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String country = rs.getString("country");
				String password = rs.getString("password");
				System.out.println(id + ", " + name + ", " + email + ", " +
								country + ", " + password);
			}
			close(con, stmt, rs);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void close(Connection con, Statement stmt, ResultSet rs)
					throws SQLException {

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
}
