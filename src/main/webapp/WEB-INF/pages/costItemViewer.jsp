
<%@page import="com.reckless.services.CostItemService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.reckless.businessobject.*" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="costItemCreator.jsp"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Insert title here</title>
</head>
<body>

	<br><br><br>
	<a href="costItemViewer"> Show all CostItems</a>
	<table border="3" >
		<tr>
			<th>name</th>
			<th>%</th>
			<th>plan</th>
			<th>fakt</th>
			<th>balance</th>
		</tr>
		<c:forEach var="item" items="${allCostItems}">
		
		<tr>			
			<th>${item.category}</th>
			<th>${item.planPercent}</th>
			<th>${item.plan}</th>
			<th>${item.fakt}</th>
			<th>${item.balance}</th>
			
		</tr>
		</c:forEach>
	</table>
	<br>
	<br>
	<br>

</body>
</html>
