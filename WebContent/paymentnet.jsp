<!DOCTYPE HTML><%@page language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<html>
<head>
<title>paymentnet</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<h1 align ="center">Enter Detail For Payment</h1>
	<form action="paymentnet" method =post>
		Name <input type="text" name="name"> <br><br>
		Contact<input type="text" name="contact"> <br><br>
		Email<input type="email" name ="Email"><br><br>
		Branch
		<select name="branch">
				
  <option value="Icici">icici</option>
  <option value="sbi">sbi</option>
  <option value="bob">bob</option>
  <option value="axis">axis</option>
		</select>
<br><br>
	Account No<input type ="text" name="account"> <br><br>
		Expire Date<input type="text" name="expire"><br><br>
		Amount <input typee="text" name="amount"><br><br>
     <button>submit</button>

	</form>
</body>
</html>