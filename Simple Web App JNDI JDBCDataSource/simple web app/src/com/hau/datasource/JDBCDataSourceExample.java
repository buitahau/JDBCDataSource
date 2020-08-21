package com.hau.datasource;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/jdbcdatasourceexample")
public class JDBCDataSourceExample extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static final String NODE_JNDI_TREE = "java:/comp/env";

	private static final String QUERY = "select id, name, email, country, password from Users";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		Context ctx = null;
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {

			ctx = new InitialContext();

			Context initCtx  = (Context) ctx.lookup(NODE_JNDI_TREE);

			DataSource ds = (DataSource) initCtx.lookup("jdbc/MyLocalDB");

			con = ds.getConnection();

			stmt = con.createStatement();

			rs = stmt.executeQuery(QUERY);

			PrintWriter out = response.getWriter();

			response.setContentType("text/html");
			out.print("<html><body><h2>Users Details</h2>");
			out.print("<table border=\"1\" cellspacing=10 cellpadding=5>");
			out.print("<th>User ID</th>");
            out.print("<th>User Name</th>");
            out.print("<th>Email</th>");
            out.print("<th>Country</th>");
            out.print("<th>Password</th>");

			while (rs.next()) {
				out.print("<tr>");
				out.print("<td>" + rs.getInt("id") + "</td>");
				out.print("<td>" + rs.getString("name") + "</td>");
				out.print("<td>" + rs.getString("email") + "</td>");
				out.print("<td>" + rs.getString("country") + "</td>");
				out.print("<td>" + rs.getString("password") + "</td>");
				out.print("</tr>");
			}

			out.print("</table>");
			out.println("<br>");

			out.print("<h3>Database Detail</h3>");
			out.print("Database Product: " + con.getMetaData().getDatabaseProductName() + "<br/>");
			out.print("Database Driver: " + con.getMetaData().getDriverName());

			out.print("</body></html>");
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				con.close();
				ctx.close();
			} catch (SQLException e) {
				System.out.println("Exception in closing DB resources");
			} catch (NamingException e) {
				System.out.println("Exception in closing Context");
			}
		}
	}
}
