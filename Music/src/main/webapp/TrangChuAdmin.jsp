<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Trang Chủ Quản Trị</title>
<link rel="stylesheet" href="css/style.css">
<style>
h2 {
	text-align: center;
}

.main-background {
	flex: 1; /* Chiếm toàn bộ chiều cao còn lại giữa header và footer */
	background-image: url('img/BackGround.jpg');
	/* Đường dẫn tới hình ảnh */
	background-size: cover; /* Đảm bảo hình ảnh bao phủ toàn bộ phần tử */
	background-position: center; /* Căn giữa hình ảnh */
	background-repeat: no-repeat; /* Không lặp lại hình ảnh */
	padding: 20px; /* Thêm khoảng cách bên trong phần tử */
	color: #fff; /* Màu chữ để nổi bật trên nền hình ảnh */
	box-sizing: border-box;
	height: 600px;
	/* Bao gồm padding và border vào kích thước phần tử */
}
</style>
</head>
<body>
	<header>
		<h1>Trang Chu Quan Tri</h1>
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
	<main class="main-background">
		<h2>Chào Mừng Đến Với Hệ Thống Quản Lý Nhạc Cụ</h2>
		<!-- Nội dung khác của trang -->
	</main>
	<footer>
		<p>&copy; 2024 Quan ly nhac cu</p>
	</footer>
</body>
</html>
