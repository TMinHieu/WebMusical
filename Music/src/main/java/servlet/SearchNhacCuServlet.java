package servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.NhacCuDAO;
import entities.NhacCu;

@WebServlet("/SearchNhacCuServlet")
public class SearchNhacCuServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchCode = request.getParameter("search");
        NhacCuDAO nhacCuDAO = new NhacCuDAO();
        List<NhacCu> nhacCuList = nhacCuDAO.searchNhacCuByCode(searchCode);

        // Set kết quả tìm kiếm vào request
        request.setAttribute("nhacCuList", nhacCuList);

        // Chuyển hướng đến trang kết quả tìm kiếm
        request.getRequestDispatcher("SearchResult.jsp").forward(request, response);
    }
}
