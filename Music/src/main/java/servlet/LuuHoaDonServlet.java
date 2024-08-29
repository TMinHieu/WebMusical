package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dao.HoaDonDAO;
import dao.ChiTietHoaDonDAO;
import entities.Customer;
import entities.HoaDon;
import entities.ChiTietHoaDon;
import entities.NhacCu;

@WebServlet("/LuuHoaDonServlet")
public class LuuHoaDonServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession httpsession = request.getSession();
        Customer Customer = (Customer) httpsession.getAttribute("currentCustomer");

        if (Customer == null) {
            response.sendRedirect("DangNhap.jsp");
            return;
        }

        String orderDateStr = (String) httpsession.getAttribute("orderDate");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date orderDate = new Date();
        try {
            orderDate = dateFormat.parse(orderDateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Tạo đối tượng HoaDon
        HoaDon hoaDon = new HoaDon();
        hoaDon.setCustomerId(Customer.getCustomerId());
        hoaDon.setOrderDate(orderDate);

        // Lưu hóa đơn vào cơ sở dữ liệu
        HoaDonDAO hoaDonDAO = new HoaDonDAO();
        int maHoaDon = hoaDonDAO.saveHoaDon(hoaDon);

        if (maHoaDon != -1) {
            // Lưu chi tiết hóa đơn vào cơ sở dữ liệu
            List<NhacCu> gioHang = (List<NhacCu>) httpsession.getAttribute("gioHang");
            ChiTietHoaDonDAO chiTietHoaDonDAO = new ChiTietHoaDonDAO();

            for (NhacCu nhacCu : gioHang) {
                ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
                chiTietHoaDon.setMaHoaDon(maHoaDon);
                chiTietHoaDon.setMaNhacCu(nhacCu.getMaNhacCu());
                chiTietHoaDon.setGia(nhacCu.getGia());
                chiTietHoaDonDAO.saveChiTietHoaDon(chiTietHoaDon);
            }
        }
        response.sendRedirect("ThanhToanThanhCong.jsp");
    }
}
