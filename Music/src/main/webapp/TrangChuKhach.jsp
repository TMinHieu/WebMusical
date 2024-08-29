<%@ page import="java.util.List"%>
<%@ page import="dao.NhacCuDAO"%>
<%@ page import="entities.NhacCu"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
NhacCuDAO nhacCuDAO = new NhacCuDAO();
List<NhacCu> nhacCuList = nhacCuDAO.getAllNhacCu();
%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Trang Chủ Khách Hàng</title>
<link rel="stylesheet" href="css/style.css">
<style>
.card-container {
	display: flex;
	flex-wrap: wrap;
	gap: 20px;
}

.card {
	border: 1px solid #ccc;
	border-radius: 5px;
	padding: 20px;
	width: calc(33.333% - 20px);
	box-sizing: border-box;
	text-align: center;
	transition: box-shadow 0.3s;
}

.card:hover {
	box-shadow: 0 0 15px rgba(0, 0, 0, 0.3);
}

.card img {
	max-width: 100%;
	height: auto;
	border-radius: 5px;
}

.card h2 {
	margin: 15px 0;
	font-size: 1.2em;
}

.card p {
	margin: 10px 0;
	font-size: 1em;
}

.card a {
	display: inline-block;
	padding: 10px 20px;
	color: #fff;
	background-color: #007bff;
	text-decoration: none;
	border-radius: 5px;
	transition: background-color 0.3s;
}

.card a:hover {
	background-color: #0056b3;
}

.search-container {
	margin: 20px;
	text-align: center;
}

.search-container input[type="text"] {
	padding: 10px;
	width: 300px;
	font-size: 16px;
}

.search-container input[type="submit"] {
	padding: 10px 20px;
	font-size: 16px;
	color: #fff;
	background-color: #007bff;
	border: none;
	border-radius: 5px;
	cursor: pointer;
}

.search-container input[type="submit"]:hover {
	background-color: #0056b3;
}
</style>
</head>
<body>
	<header>
		<h1>Trang Chủ Khách Hàng</h1>
		<nav>
			<ul>
				<li><a href="TrangChuKhach.jsp">Trang Chủ</a></li>
				<li><a href="GioHang.jsp">Giỏ Hàng</a></li>
				<li><a href="TaiKhoan.jsp">Tài Khoản</a></li>
				<li><a href="LogoutServlet">Đăng Xuất</a></li>
				<!-- Thêm liên kết đăng xuất -->
			</ul>
		</nav>
		<section class="search-container">
			<form action="SearchNhacCuServlet" method="get">
				<input type="text" name="search"
					placeholder="Nhập mã nhạc cụ để tìm kiếm" /> <input type="submit"
					value="Tìm" />
			</form>
		</section>
		<section class="card-container">
			<%
			for (NhacCu nhacCu : nhacCuList) {
			%>
			<div class="card">
				<%
				// Tạo đường dẫn hình ảnh từ mã nhạc cụ
				String maNhacCu = nhacCu.getMaNhacCu();
				if (maNhacCu != null && !maNhacCu.isEmpty()) {
					// Đường dẫn hình ảnh
					String imagePath = request.getContextPath() + "/img/" + maNhacCu + ".jpg";
				%>
				<img src="<%=imagePath%>" alt="Hình Ảnh" width="100" />
				<%
				} else {
				%>
				Không có hình ảnh
				<%
				}
				%>
				<h2><%=nhacCu.getTenNhacCu()%></h2>
				<p>
					Giá:
					<%=nhacCu.getGia()%>
					VND
				</p>
				<p>
					Tình Trạng:
					<%=nhacCu.getTinhTrang()%></p>
				<a href="ChiTietNhacCuServlet?maNhacCu=<%=nhacCu.getMaNhacCu()%>">Xem
					Chi Tiết</a>
			</div>
			<%
			}
			%>
		</section>
</body>
</html>
