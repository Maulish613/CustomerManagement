

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class paymentnet
 */
@WebServlet("/paymentnet")
public class paymentnet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public paymentnet() {
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
      	PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String name,contact,Email,branch,account,expire,amount;
			  
		  //(String)session.getAttribute("uid"); String
		   name=request.getParameter("name"); 
		   contact=request.getParameter("contact");
		   Email=request.getParameter("Email"); 
		   branch=request.getParameter("branch");
		   account=request.getParameter("account");
		   expire=request.getParameter("expire");
		   amount=request.getParameter("amount");
		  System.out.println("check succesfully");
	
		  if(name==null || contact==null || Email==null || account==null ||expire==null || amount==null)  {
			  out.print("<font color=\"red\">All details are mandatory</font>");
			  RequestDispatcher  rd=request.getRequestDispatcher("paymentnet.jsp");
			  rd.include(request, response); 
			  }
			  System.out.println("go to registration");
		
		
		 
		  if(account.length()!=16) {
		  out.print("<font color=\"red\">Card number must be of 16 digits</font>" );
		  RequestDispatcher rd=request.getRequestDispatcher("paymentnet.jsp");
		  rd.include(request, response); }
	
		//  java.sql.Date d=new java.sql.Date(date.getTime()); 
		 try {
		  Class.forName("com.mysql.jdbc.Driver").newInstance(); 
		  Connection con=DriverManager.getConnection("jdbc:mysql:///project", "root", "admin"); 
		  Statement stmt=con.createStatement();
		 
		  String sql="insert into paymentnet values(?,?,?,?,?,?,?)";
		  PreparedStatement pstmt = con.prepareStatement(sql);
		  pstmt.setString(1,name);
		  pstmt.setString(2,Email); 
		  pstmt.setString(3,contact);
		  pstmt.setString(4,branch);
		  pstmt.setString(5,account);
		pstmt.setString(6,expire);
		  pstmt.setString(7,amount);
		  
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


