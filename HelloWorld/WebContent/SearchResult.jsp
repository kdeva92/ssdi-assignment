<%@page import="java.util.ArrayList"%>
<%@page import="model.User"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Search Result</title>
</head>
<body>

	<table border ="2" style="width=50%" >
		<tr>
			<th>User ID</th>
			<th>User name</th>
			<th>Salary</th>
		</tr>
		<%
	//System.out.println("demo: "+request.getAttribute("demo"));
	Object usersObj = request.getAttribute("myUsers");
	System.out.println("in jsp "+ usersObj);	
	//ArrayList<User> list1 = (ArrayList<User>)usersObj;
	//System.out.println("in jsp "+ list1.get(1));	
		ArrayList<User> list = (ArrayList<User>)usersObj;
		String name;
		double sal;
		int id;
		
		for(User u : list){
			if(u == null){
				%>
		<br> User not present in database
		<%
				break;	
			}

			sal = u.getSalary();	
			id =  u.getId();
			name = u.getName();
%>
		<tr>
			<td><%= id %></td>
			<td><%= name %></td>
			<td><%= u.getSalary() %></td>
		</tr>
		<%
		}
	

%>


	</table>

	<br> click
	<a href="index.html"> back</a> to go to prev page

</body>
</html>