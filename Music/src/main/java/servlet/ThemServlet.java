package servlet;

import dao.ThemDAO;
import entities.NhacCu;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;

@WebServlet("/them")
@MultipartConfig
public class ThemServlet extends HttpServlet {
    private ThemDAO themDAO;

    @Override
    public void init() throws ServletException {
        themDAO = new ThemDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("Them.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String maNhacCu = request.getParameter("maNhacCu");
        String tenNhacCu = request.getParameter("tenNhacCu");
        String giaStr = request.getParameter("gia");
        String tinhTrang = request.getParameter("tinhTrang");
        String loaiNhacCu = request.getParameter("loaiNhacCu");

        if (maNhacCu == null || maNhacCu.isEmpty() ||
            tenNhacCu == null || tenNhacCu.isEmpty() ||
            giaStr == null || giaStr.isEmpty() ||
            tinhTrang == null || tinhTrang.isEmpty() ||
            loaiNhacCu == null || loaiNhacCu.isEmpty()) {
            request.setAttribute("errorMessage", "Vui lòng điền đầy đủ thông tin.");
            request.getRequestDispatcher("Them.jsp").forward(request, response);
            return;
        }

        int gia = Integer.parseInt(giaStr);

        Part filePart = request.getPart("anh");
        InputStream anhStream = filePart != null ? filePart.getInputStream() : null;

        NhacCu nhacCu = new NhacCu(maNhacCu, tenNhacCu, gia, tinhTrang, loaiNhacCu, anhStream);
        themDAO.addNhacCu(nhacCu);

        response.sendRedirect("TrangChuAdmin.jsp");
    }
}
