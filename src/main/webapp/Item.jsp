<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    
    
<%@ page import="com.*, java.util.*, java.io.*" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Items management</h1>
<form  method="post" action="Item.jsp"  >
Item name:<input type="text" name="name" size="100"></br>
Item code:<input type="text" name ="code" size="100"></br>
price:    <input type="text" name ="price" size="100"></br>
description:<input type="text" name ="description" size="100"></br>
<input type="submit" value="save" name="submitBtn">
</form>

<% 
if (request.getParameter("code") != null) 
{ 
 Items it=new Items();
// it.connect();
String status=it.insertItem(
		  request.getParameter("code"),
		 request.getParameter("name"),
		  request.getParameter("price"),
		 request.getParameter("description")
		
		);
session.setAttribute("statusm",status);



 session.setAttribute("itemCode", request.getParameter("code")); 
 session.setAttribute("itemName", request.getParameter("name")); 
 session.setAttribute("itemPrice", request.getParameter("price")); 
 session.setAttribute("itemDesc", request.getParameter("description"));
 
} 


%>


<table border="1">
<tr>
<th>Item name</th>
<th>Item code</th>
<th>Item price</th>
<th>Item description</th>
<th>update</th>
<th>delete</th>
</tr>

<tr>
<td><%out.print(session.getAttribute("itemName")); %></td>
<td><%out.print(session.getAttribute("itemCode")); %></td>
<td><%out.print(session.getAttribute("itemPrice")); %></td>
<td><%out.print(session.getAttribute("itemDesc")); %></td>
<td><input name="btnUpdate" type="button" value="Update"></td>
<td><input name="btnRemove" type="button" value="Remove"></td>
</tr>


</table>
<%out.print(session.getAttribute("statusm")); %>

Output table<br>
<% 
Items it=new Items();
out.print(it.ReadItems());
%>



</body>
</html>