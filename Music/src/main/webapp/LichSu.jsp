<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="entities.HoaDon"%>
<%@ page import="entities.ChiTietHoaDon"%>
<%@ page import="dao.HoaDonDAO"%>
<%@ page import="dao.ChiTietHoaDonDAO"%>
<%@ page import="entities.Customer"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%
HttpSession httpsession = request.getSession();
Customer Customer = (Customer) httpsession.getAttribute("currentCustomer");

if (Customer == null) {
	response.sendRedirect("DangNhap.jsp");
	return;
}

HoaDonDAO hoaDonDAO = new HoaDonDAO();
ChiTietHoaDonDAO chiTietHoaDonDAO = new ChiTietHoaDonDAO();
List<HoaDon> hoaDons = hoaDonDAO.getHoaDonsByCustomerId(Customer.getCustomerId());
%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Lịch Sử Mua Hàng</title>
<link rel="stylesheet" href="css/style.css">
<style>
/* Styles for the table */
table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
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
</style>
</head>
<body>
	<header>
		<h1>Lịch Sử Mua Hàng</h1>
		<nav>
			<ul>
				<li><a href="TrangChuKhach.jsp">Trang Chủ</a></li>
				<li><a href="GioHang.jsp">Giỏ Hàng</a></li>
				<li><a href="TaiKhoan.jsp">Tài Khoản</a></li>
				<li><a href="LogoutServlet">Đăng Xuất</a></li>
			</ul>
		</nav>
	</header>
	<section>
		<h2>Danh Sách Hóa Đơn</h2>
		<table>
			<thead>
				<tr>
					<th>Mã Hóa Đơn</th>
					<th>Ngày Đặt</th>
					<th>Chi Tiết</th>
				</tr>
			</thead>
			<tbody>
				<%
				if (hoaDons != null && !hoaDons.isEmpty()) {
					for (HoaDon hoaDon : hoaDons) {
				%>
				<tr>
					<td><%=hoaDon.getMaHoaDon()%></td>
					<td><%=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(hoaDon.getOrderDate())%></td>
					<td>
						<ul>
							<%
							List<ChiTietHoaDon> chiTietHoaDons = chiTietHoaDonDAO.getChiTietHoaDonsByMaHoaDon(hoaDon.getMaHoaDon());
							for (ChiTietHoaDon chiTiet : chiTietHoaDons) {
							%>
							<li>Mã Nhạc Cụ: <%=chiTiet.getMaNhacCu()%> - Giá: <%=chiTiet.getGia()%>
								VND
							</li>
							<%
							}
							%>
						</ul>
					</td>
				</tr>
				<%
				}
				} else {
				%>
				<tr>
					<td colspan="3">Không có hóa đơn nào.</td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
	</section>
</body>
</html>
