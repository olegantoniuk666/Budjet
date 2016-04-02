
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
<a href="mainPage">main</a>

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
			<td>${item.category}</td>
			<td>${item.planPercent}</td>
			<td>${item.plan}</td>
			<td>${item.fakt}</td>
			<td>${item.balance}</td>
			<td><a href="removeCostItem?id=${item.id}">delete</a></td>	
		</tr>
		</c:forEach>
	</table>
	<br>
	<br>
	<br>

</body>
</html>
