<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@include file="transactionCreator.jsp"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<a href="mainPage">main page</a>***
<a href="transactionViewer">all</a>***
<a href="incomeTransactions">dohod</a>***
<a href="expenseTransactions">rashod</a><br><br><br>

	<table border="3" cellspacing="2" align="left" width="300">
		<tr>
			<th>name</th>
			<th>money</th>
			<th>date</th>
		</tr>
		<c:forEach var="transaction" items="${allTransactions}">
			<tr>
				<td>${transaction.category}</td>
				<td>${transaction.quantity}</td>
				<td>${transaction.localDate}</td>
				<td><a href="removeTransaction?id=${transaction.id}">delete</a></td>
			</tr>
		</c:forEach>
	</table>		

</body>
</html>