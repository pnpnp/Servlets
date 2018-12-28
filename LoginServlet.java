package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.ConnectionManager;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet({ "/LoginServlet", "/login" })
public class LoginServlet extends HttpServlet {

	private static Connection con;
	private static PreparedStatement ps;
	private static ResultSet rs;
	private static String sql = "select * from appusers where user_name=? and password = ?";
	private RequestDispatcher rd;

	public LoginServlet() {
		super();

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		con = ConnectionManager.getConnection();

		String uname = request.getParameter("username");
		String pwd = request.getParameter("password");

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, uname);
			ps.setString(2, pwd);
			rs = ps.executeQuery();

			while (rs.next())

			{
				request.setAttribute("req_user", uname);
				rd = request.getRequestDispatcher("success.jsp");
				rd.forward(request, response);
			}
			// } else {
			// RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			// rd.forward(request, response);
			// }

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
