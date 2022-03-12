package com;

import java.sql.*;


public class Items {
  String ItemCode;
  String ItemName;
  String ItemDesc;
    Double ItemPrice;
	
	
	
   public Items() {
	   
   }
	
	public Connection connect() 
	{ 
	 Connection con = null; 
	 
	 try 
	 { 
	 Class.forName("com.mysql.jdbc.Driver"); 
	 con= DriverManager.getConnection("jdbc:mysql://localhost:3306/store", 
	 "root", ""); 
	 //For testing
	 System.out.print("Successfully connected---------------------------------------------------------------------------------------------------------------"); 
	 } 
	 catch(Exception e) 
	 { 
	 e.printStackTrace(); 
	 } 
	 
	 return con; 

	
	}
	
	
	public String insertItem(String code, String name, String price, String desc) {
		Connection con = connect(); 
		String output;
		if (con == null) 
		{ 
		return "Error while connecting to the database"; 
		}
		
		else {
			String query="insert into items(ItemID,ItemCode,ItemName,ItemPrice,ItemDesc) values(?,?,?,?,?)";
		   try {
			PreparedStatement ps=con.prepareStatement(query);
            
			
			ps.setInt(1, 0);
			ps.setString(2, code);
			ps.setString(3, name);
			ps.setDouble(4, Double.parseDouble(price));
			ps.setString(5, desc);
			
			ps.execute();
			output="inserted";
			con.close();
			
		} catch (SQLException e) {
			output="not";
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   return output;
		     
		
		}
		
	}
    public String ReadItems() {
		
    	
    	String out="";
    	
    	try
    	{ 
    	 Connection con = connect(); 
    	if (con == null) 
    	 { 
    	 return "Error while connecting to the database for reading."; 
    	 }
    	
    	out="<table border='1'>"
    			+ "<tr>"
    			+ "<th>ItemCode</th><th>ItemName</th><th>Item Price</th><th>Item desc</th><th>Update</th><th>delete</th>"
    			+ "</tr>";
    	
    	Statement stmt=con.createStatement();
    	String Query="select * from Items";
    	ResultSet rs=stmt.executeQuery(Query);
    	
    	while(rs.next()) {
    		String ItemID= Integer.toString(rs.getInt("ItemID"));
    		String ItemCode=rs.getString("ItemCode");
    		String ItemName=rs.getString("ItemName");
    		String ItemPrice=Double.toString(rs.getDouble("ItemPrice"));
    	    String ItemDesc=rs.getString("ItemDesc");
    	    
    	    out+="<tr>"
    	    		+ "<td>"+ ItemCode + "</td><td>"+ItemName +"</td><td>"+ItemPrice+"</td><td>"+ItemDesc+"</td>";
    	    
    		
    		out+="<td><input type='submit' name='update' value='update'> </td>"
    				+ "<td><form action='Items.jsp' method='post'>"
    				+ "<input type='submit' value='delete' name='btnRemove'>"
    				+ "<input type='hidden'   name='ItemID' value='"+ ItemID +"' > "
    				+ "</form>"
    				+ "</td>"
    				+ "</tr>";
    		
    	}
    	
    	out+="</table>";
    	con.close();
    	
    	
    	
    	} 
    	catch (Exception e) 
    	{ 
    	 out = "Error while reading the items."; 
    	 System.err.println(e.getMessage()); 
    	}
    	
    	
    	
    	return out;
    	
    }
	
	
	
	
	
}
