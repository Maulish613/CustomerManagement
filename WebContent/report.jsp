<!DOCTYPE HTML><%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<html>
<head>
<title>report</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>

</body>
<form method="post">

<table border="2">
<tr>
<td>Name</td>
<td>Amount</td>
</tr>
<%@page import="java.sql.*"%>
<%
try
{
Class.forName("com.mysql.jdbc.Driver").newInstance(); 
Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "admin");

String uid=session.getAttribute("name").toString();
String query="select * from payment where name='"+uid+"'";

Statement stmt=con.createStatement();
ResultSet rs=stmt.executeQuery(query);
while(rs.next())
{

%>
    <tr><td><%=rs.getString(1)%></td>
    	<td><%=rs.getString(5)%></td></tr>
        
        <%

}
%>
    </table>
    <%
    rs.close();
    stmt.close();
  
    }
catch(Exception e)
{
    e.printStackTrace();
    }




%>

</form>
</body>
</html>