<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page import="entities.NhacCu"%>
<%@ page import="java.sql.SQLException"%>
<%@ page import="dao.NhacCuDAO"%>
<%
String maNhacCu = request.getParameter("maNhacCu");
NhacCuDAO nhacCuDAO = new NhacCuDAO();
NhacCu nhacCu = null;
try {
	nhacCu = nhacCuDAO.getNhacCuByMa(maNhacCu);
} catch (SQLException e) {
	e.printStackTrace();
}
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Chi Tiết Nhạc Cụ</title>
<link rel="stylesheet" href="css/style.css">
<style>
.detail-container {
	width: 80%;
	margin: 0 auto;
	padding: 20px;
	border: 1px solid #ddd;
	border-radius: 8px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	background-color: #fff;
}

.detail-container img {
	max-width: 100%;
	height: auto;
	border-radius: 8px;
}

.detail-container h2 {
	font-size: 24px;
	margin: 10px 0;
	text-align: center;
}

.detail-container p {
	font-size: 18px;
	margin: 10px 0;
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
		<h1>Chi Tiết Nhạc Cụ</h1>
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
		<div class="detail-container">
			<h2><%=nhacCu.getTenNhacCu()%></h2>
			<img src="img/<%=nhacCu.getMaNhacCu()%>.jpg"
				alt="<%=nhacCu.getTenNhacCu()%>">
			<p>
				<strong>Giá:</strong>
				<%=nhacCu.getGia()%>
				VND
			</p>
			<p>
				<strong>Tình Trạng:</strong>
				<%=nhacCu.getTinhTrang()%></p>
			<p>
				<strong>Loại Nhạc Cụ:</strong>
				<%=nhacCu.getLoaiNhacCu()%></p>
			<div class="button-group">
				<form action="GioHangServlet" method="post">
					<input type="hidden" name="maNhacCu"
						value="<%=nhacCu.getMaNhacCu()%>">
					<button type="submit" class="button">Mua Hàng</button>
				</form>
				<a href="TrangChuKhach.jsp" class="button">Quay Lại</a>
			</div>
		</div>
	</section>
</body>
</html>
