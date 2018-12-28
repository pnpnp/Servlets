package servlets;

import java.io.IOException;
import java.io.PrintWriter;
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

import model.User;
import utils.ConnectionManager;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {

	private static Connection con;
	private static PreparedStatement ps;
	private static ResultSet rs;
	private static String sql = "insert into appusers values (?, ?)";

	// private static String sql = "update appusers set password = ? where user_name
	// =?";

	private RequestDispatcher rd;
	User user;

	int retval;

	public RegisterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		con = ConnectionManager.getConnection();
		PrintWriter out = response.getWriter();
		String uname = request.getParameter("username");
		String pwd = request.getParameter("password");
		String pwd2 = request.getParameter("password2");

		if (pwd.equals(pwd2)) {
			user = new User(uname, pwd);
			try {
				ps = con.prepareStatement(sql);
				ps.setString(1, uname);
				ps.setString(2, pwd);
				retval = ps.executeUpdate();
				System.out.println(retval);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		if (retval == 1) {
			response.setContentType("text/html");
			out.println("You have registered successfully.");
			/*
			 * try { con.commit(); } catch (SQLException e) { // TODO Auto-generated catch
			 * block e.printStackTrace(); }
			 */
			request.setAttribute("UserObj", user);
			rd = request.getRequestDispatcher("index.jsp");
			rd.include(request, response);
		}
	}

}
