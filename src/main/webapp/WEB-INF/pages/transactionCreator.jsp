<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.reckless.businessobject.*" import="java.util.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<pre >
	<form action="addTransaction" method="post" >
	
		Transaction   <input type="text" name="category"> <br>
		Money         <input type="text" name="money"><br>
		Date          <input type="date" name="date"> <br>
		Dohod :-)	 <input type="checkbox" name="isIncome" value="true">
		Zatrata :-(      <input type="checkbox" name="isIncome" value="false">
					  <input type="submit" name="create"value="create">
	</form>
	</pre>

</body>
</html>