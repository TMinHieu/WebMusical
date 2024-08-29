<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="dao.CapNhatDAO"%>
<%@ page import="entities.NhacCu"%>
<%
CapNhatDAO capNhatDAO = new CapNhatDAO();
List<NhacCu> nhacCuList = capNhatDAO.getAllNhacCu();
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Cập Nhật</title>
<link rel="stylesheet" href="css/style.css">
<style>
table {
	width: 100%;
	border-collapse: collapse;
}

table, th, td {
	border: 1px solid black;
}

th, td {
	padding: 8px;
	text-align: left;
}

th {
	background-color: #f2f2f2;
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
	margin-top: 20px;
}

.button:hover {
	background-color: #0056b3;
}

h2 {
	text-align: center; /* Căn giữa nội dung theo chiều ngang */
	margin-top: 20px; /* Thêm khoảng cách phía trên nếu cần */
}
</style>
</head>
<body>
	<header>
		<h1>Trang Chủ Quản Trị</h1>
		<nav>
			<ul>
				<li><a href="Them.jsp">Thêm</a></li>
				<li><a href="Xoa.jsp">Xóa</a></li>
				<li><a href="CapNhat.jsp">Cập Nhật</a></li>
				<li><a href="QuanLyTaiKhoan.jsp">Tài Khoản</a></li>
				<li><a href="LogoutServlet">Đăng Xuất</a></li>
				<!-- Thêm liên kết đăng xuất -->
			</ul>
		</nav>
	</header>
	<section>
		<h2>Cập Nhật Thông Tin Nhạc Cụ</h2>
		<div>
			<a href="TrangChuAdmin.jsp" class="button">Quay Lại Trang Chủ</a>
		</div>
		<form action="CapNhatServlet" method="post">
			<table>
				<tr>
					<th>Mã Nhạc Cụ</th>
					<th>Tên Nhạc Cụ</th>
					<th>Giá</th>
					<th>Tình Trạng</th>
					<th>Loại Nhạc Cụ</th>
					<th>Hành Động</th>
				</tr>
				<%
				for (NhacCu nhacCu : nhacCuList) {
				%>
				<tr>
					<form action="CapNhatServlet" method="post">
						<td><%=nhacCu.getMaNhacCu()%><input type="hidden"
							name="maNhacCu" value="<%=nhacCu.getMaNhacCu()%>"></td>
						<td><input type="text" name="tenNhacCu"
							value="<%=nhacCu.getTenNhacCu()%>"></td>
						<td><input type="number" name="gia"
							value="<%=nhacCu.getGia()%>"></td>
						<td><input type="text" name="tinhTrang"
							value="<%=nhacCu.getTinhTrang()%>"></td>
						<td><input type="text" name="loaiNhacCu"
							value="<%=nhacCu.getLoaiNhacCu()%>"></td>
						<td><input type="submit" value="Cập Nhật"></td>
					</form>
				</tr>
				<%
				}
				%>
			</table>
		</form>
	</section>

</body>
</html>
