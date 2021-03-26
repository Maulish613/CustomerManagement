

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class alogin
 */
@WebServlet("/alogin")
public class alogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public alogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String name,password;
		name=request.getParameter("name");
		password=request.getParameter("password");
     
		try
		{	
			Class.forName("com.mysql.jdbc.Driver").newInstance(); 
			Connection con=DriverManager.getConnection("jdbc:mysql:///project", "root", "admin");

			PreparedStatement pstmt = con.prepareStatement("select * from aregistration where name=? and password=?");
			pstmt.setString(1,name);
			pstmt.setString(2,password);
			ResultSet rs=pstmt.executeQuery();
			String q="insert into alogin values('"+name+"','"+password+"')";
			java.sql.Statement stmt=con.createStatement();
			stmt.executeUpdate(q);
			
			if(rs.next())
			{
				HttpSession session=request.getSession();
				session.setAttribute("name",name);
				response.sendRedirect("Homepage.jsp");
			}
			
			else
			{
				out.print("<span class=\"error\"><Invalid user name or password</error>");
				RequestDispatcher rd=request.getRequestDispatcher("alogin.jsp");
				rd.include(request, response);
			}
		}
			

		catch(SQLException e)
		{
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	}

