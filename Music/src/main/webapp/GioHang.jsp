<%@ page import="entities.NhacCu"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="entities.NhacCu"%>
<%@ page import="java.util.List"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Giỏ Hàng</title>
<link rel="stylesheet" href="css/style.css">
<style>
.cart-container {
	width: 80%;
	margin: 0 auto;
	padding: 20px;
	border: 1px solid #ddd;
	border-radius: 8px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	background-color: #fff;
}

.cart-container h2 {
	font-size: 24px;
	margin: 10px 0;
	text-align: center;
}

.cart-container table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
}

.cart-container table, .cart-container th, .cart-container td {
	border: 1px solid #ddd;
}

.cart-container th, .cart-container td {
	padding: 10px;
	text-align: left;
}

.cart-container th {
	background-color: #f4f4f4;
}

.total-price {
	font-size: 20px;
	font-weight: bold;
	margin-top: 20px;
	text-align: right;
}

.button-group {
	margin-top: 20px;
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
		<h1>Giỏ Hàng</h1>
		<nav>
			<ul>
				<li><a href="TrangChuKhach.jsp">Trang Chủ</a></li>
				<li><a href="GioHangServlet">Giỏ Hàng</a></li>
				<li><a href="TaiKhoan.jsp">Tài Khoản</a></li>
				<li><a href="LogoutServlet">Đăng Xuất</a></li>
			</ul>
		</nav>
	</header>
	<section>
		<div class="cart-container">
			<h2>Giỏ Hàng của bạn</h2>
			<table>
				<thead>
					<tr>
						<th>Mã Nhạc Cụ</th>
						<th>Tên Nhạc Cụ</th>
						<th>Giá</th>
					</tr>
				</thead>
				<tbody>
					<%
					List<NhacCu> gioHang = (List<NhacCu>) session.getAttribute("gioHang");
					int totalPrice = 0;
					if (gioHang != null) {
						for (NhacCu nhacCu : gioHang) {
							totalPrice += nhacCu.getGia();
					%>
					<tr>
						<td><%=nhacCu.getMaNhacCu()%></td>
						<td><%=nhacCu.getTenNhacCu()%></td>
						<td><%=nhacCu.getGia()%> VND</td>
					</tr>
					<%
					}
					}
					%>
				</tbody>
			</table>
			<div class="total-price">
				Tổng tiền:
				<%=totalPrice%>
				VND
			</div>
			<div class="button-group">
				<form action="GioHangServlet" method="post">
					<input type="hidden" name="action" value="checkout">
					<button type="submit" class="button">Thanh Toán Ngay</button>
				</form>
				<a href="TrangChuKhach.jsp" class="button">Mua Tiếp</a>
			</div>
		</div>
	</section>
</body>
</html>
