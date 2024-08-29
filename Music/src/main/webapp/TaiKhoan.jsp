
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="entities.Customer"%>
<%
HttpSession httpsession = request.getSession();
Customer customer = (Customer) session.getAttribute("currentCustomer");

if (customer == null) {
	response.sendRedirect("DangNhap.jsp");
	return;
}
%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Thông Tin Tài Khoản</title>
<link rel="stylesheet" href="css/style.css">
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f4f4f4;
	margin: 0;
	padding: 0;
}

header {
	padding: 10px 0;
	text-align: center;
}

header h1 {
	margin: 0;
}

nav ul {
	list-style: none;
	padding: 0;
	margin: 0;
	text-align: center;
}

nav ul li {
	display: inline;
	margin: 0 10px;
}

nav ul li a {
	color: #fff;
	text-decoration: none;
	font-weight: bold;
}

.profile-container {
	width: 50%;
	margin: 50px auto;
	padding: 20px;
	background-color: #fff;
	border-radius: 10px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

h2 {
	text-align: center;
	margin-bottom: 20px;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-bottom: 20px;
}

table, th, td {
	border: 1px solid #ddd;
}

th, td {
	padding: 12px;
	text-align: left;
}

th {
	background-color: #f4f4f4;
}

.button-container {
	text-align: center;
}

.button {
	display: inline-block;
	padding: 10px 20px;
	font-size: 16px;
	color: #fff;
	background-color: #007bff;
	border: none;
	border-radius: 5px;
	text-align: center;
	text-decoration: none;
	cursor: pointer;
}

.button:hover {
	background-color: #0056b3;
}
</style>
</head>
<body>
	<header>
		<h1>Thông Tin Tài Khoản</h1>
		<nav>
			<ul>
				<li><a href="TrangChuKhach.jsp">Trang Chủ</a></li>
				<li><a href="GioHang.jsp">Giỏ Hàng</a></li>
				<li><a href="TaiKhoan.jsp">Tài Khoản</a></li>
				<li><a href="LogoutServlet">Đăng Xuất</a></li>
			</ul>
		</nav>
	</header>
	<section class="profile-container">
		<h2>Thông Tin Cá Nhân</h2>
		<table>
			<tr>
				<th>Id</th>
				<td><%=customer.getCustomerId()%></td>
			</tr>
			<tr>
				<th>Họ Tên</th>
				<td><%=customer.getFullName()%></td>
			</tr>
			<tr>
				<th>Số Điện Thoại</th>
				<td><%=customer.getPhoneNumber()%></td>
			</tr>
			<tr>
				<th>Địa Chỉ</th>
				<td><%=customer.getAddress()%></td>
			</tr>
		</table>
		<div>
			<a href="LichSu.jsp" class="button">Lịch Sử Mua Hàng</a>
		</div>
		<div class="button-container">
			<a href="TrangChuKhach.jsp" class="button">Quay lại Trang Chủ</a>
		</div>

	</section>
</body>
</html>
