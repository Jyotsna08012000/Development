<%@page import="Dao.WishListDao"%>
<%@page import="Model.WishList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
int id= Integer.parseInt(request.getParameter("wid"));
WishListDao.removeFromWishlist(id);
response.sendRedirect("Customer-wishlist.jsp");
%>

</body>
</html>