package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.CapNhatDAO;
import entities.NhacCu;

@WebServlet("/CapNhatServlet")
public class CapNhatServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CapNhatDAO capNhatDAO;

    public void init() {
        capNhatDAO = new CapNhatDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<NhacCu> nhacCuList = capNhatDAO.getAllNhacCu();
            request.setAttribute("nhacCuList", nhacCuList);
            request.getRequestDispatcher("CapNhat.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String maNhacCu = request.getParameter("maNhacCu");
        String tenNhacCu = request.getParameter("tenNhacCu");
        int gia = Integer.parseInt(request.getParameter("gia"));
        String tinhTrang = request.getParameter("tinhTrang");
        String loaiNhacCu = request.getParameter("loaiNhacCu");

        NhacCu nhacCu = new NhacCu();
        nhacCu.setMaNhacCu(maNhacCu);
        nhacCu.setTenNhacCu(tenNhacCu);
        nhacCu.setGia(gia);
        nhacCu.setTinhTrang(tinhTrang);
        nhacCu.setLoaiNhacCu(loaiNhacCu);

        try {
            capNhatDAO.updateNhacCu(nhacCu);
            response.sendRedirect("CapNhat.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}
