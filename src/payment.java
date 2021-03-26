
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
 * Servlet implementation class payment
 */
@WebServlet("/payment")
public class payment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public payment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("text/html");
      	PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String name,contact,Email,card,amount;
			  
		  //(String)session.getAttribute("uid"); String
		   name=request.getParameter("name"); 
		   contact=request.getParameter("contact");
		   Email=request.getParameter("Email"); 
		   card=request.getParameter("card");
		   amount=request.getParameter("amount");
		  System.out.println("check succesfully");
	
		  if(name==null || contact==null || Email==null || card==null || amount==null)  {
			  out.print("<font color=\"red\">All details are mandatory</font>");
			  RequestDispatcher  rd=request.getRequestDispatcher("payment.jsp");
			  rd.include(request, response); 
			  }
			  System.out.println("go to registration");
		
		
		 
		  if(card.length()!=16) {
		  out.print("<font color=\"red\">Card number must be of 16 digits</font>" );
		  RequestDispatcher rd=request.getRequestDispatcher("payment.jsp");
		  rd.include(request, response); }
		 System.out.println("Contact check");
		//  java.sql.Date d=new java.sql.Date(date.getTime()); 
		 try {
		  Class.forName("com.mysql.jdbc.Driver").newInstance(); 
		  Connection con=DriverManager.getConnection("jdbc:mysql:///project", "root", "admin"); 
		  Statement stmt=con.createStatement();
		 
		  String sql="insert into payment values(?,?,?,?,?)";
		  PreparedStatement pstmt = con.prepareStatement(sql);
		  pstmt.setString(1,name);
		  pstmt.setString(2,Email); 
		  pstmt.setString(3,contact);
		  pstmt.setString(4,card);
		  pstmt.setString(5,amount);
		  
		  System.out.println("enterd succesfully");
		  pstmt.executeUpdate();
		  }
		 
		  catch(SQLException c) { c.printStackTrace();
		  
		  } catch (ClassNotFoundException c) { // TODO Auto-generated catch block
		  c.printStackTrace(); } 
		  catch (IllegalAccessException c) {
		  c.printStackTrace(); // TODO Auto-generated catch block
		  c.printStackTrace(); } 
		  catch (InstantiationException c) {
			  // TODO Auto-generated   catch block
			  c.printStackTrace(); }
		  response.sendRedirect("Homepage.jsp");
	
		  }

	}

