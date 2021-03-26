import java.io.IOException;
import java.sql.Connection;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class signup
 */
@WebServlet("/signup")
public class signup extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public signup() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ")
				.append(request.getContextPath());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();

		String userid = request.getParameter("userid");// (String)session.getAttribute("uid");
														// String
		String name = request.getParameter("name");
		String email = request.getParameter("Email");
		String password = request.getParameter("password");
		String adress = request.getParameter("adress");
		String DOB = request.getParameter("DOB");
		Date date = null;
		String contact = request.getParameter("contact");
		System.out.println("check succesfully");
		try {
			date = new SimpleDateFormat("MM-yyyy").parse(DOB);
		} catch (ParseException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("check date");

		if (userid == null || name == null || email == null || password == null
				|| adress == null || DOB == null || contact == null) {
			out.print("<font color=\"red\">All details are mandatory</font>");
			RequestDispatcher rd = request
					.getRequestDispatcher("Registration.jsp");
			rd.include(request, response);
		}

		if (contact.length() != 10) {
			out.print("<font color=\"red\">Mobile number must be of 10 digits</font>");
			RequestDispatcher rd = request
					.getRequestDispatcher("Registration.jsp");
			rd.include(request, response);
		}
		System.out.println("Contact check");
		java.sql.Date d = new java.sql.Date(date.getTime());
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection(
					"jdbc:mysql:///project", "root", "admin");
			Statement stmt = con.createStatement();

			String sql = "insert into registration values(?,?,?,?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.setString(2, name);
			pstmt.setString(3, email);
			pstmt.setString(4, password);
			pstmt.setString(5, adress);
			pstmt.setDate(6, d);
			pstmt.setString(7, contact);

			System.out.println("enterd succesfully");
			pstmt.executeUpdate();
		}

		catch (SQLException e) {
			e.printStackTrace();

		} catch (ClassNotFoundException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace(); // TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		response.sendRedirect("Homepage.jsp");
	}
}
