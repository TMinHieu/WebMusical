package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entities.NhacCu;
import dao.NhacCuDAO;
import java.sql.SQLException;

@WebServlet("/ChiTietNhacCuServlet")
public class ChiTietNhacCuServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private NhacCuDAO nhacCuDAO;

    @Override
    public void init() {
        nhacCuDAO = new NhacCuDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String maNhacCu = request.getParameter("maNhacCu");
        NhacCu nhacCu = null;
        try {
            nhacCu = nhacCuDAO.getNhacCuByMa(maNhacCu);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("nhacCu", nhacCu);
        request.getRequestDispatcher("ChiTietNhacCu.jsp").forward(request, response);
    }
}
