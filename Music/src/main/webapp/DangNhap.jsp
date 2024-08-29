<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Đăng Nhập</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #e0f7fa;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	margin: 0;
}

.container {
	background-color: #ffffff;
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	max-width: 400px;
	width: 100%;
	text-align: center;
}

h2 {
	color: #007bb5;
}

input[type="text"], input[type="password"], input[type="submit"] {
	width: calc(100% - 20px);
	padding: 10px;
	margin: 10px 0;
	border: 1px solid #ddd;
	border-radius: 5px;
}

input[type="submit"] {
	background-color: #007bb5;
	color: #fff;
	border: none;
	cursor: pointer;
}

input[type="submit"]:hover {
	background-color: #005f8b;
}

.register-link {
	margin-top: 15px;
	display: block;
}

.register-link a {
	color: #007bb5;
	text-decoration: none;
}

.register-link a:hover {
	text-decoration: underline;
}
</style>
</head>
<body>
	<div class="container">
		<h2>Đăng Nhập</h2>
		<form action="DangNhapServlet" method="post">
			SĐT: <input type="text" name="phoneNumber" required><br>
			Mật khẩu: <input type="password" name="password" required><br>
			<input type="submit" value="Đăng Nhập">
		</form>
		<div class="register-link">
			<p>
				Chưa có tài khoản? <a href="DangKy.jsp">Đăng ký</a>
			</p>
		</div>
	</div>
</body>
</html>
