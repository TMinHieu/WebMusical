package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ResetCartServlet")
public class ResetCartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        // Remove cart and order date
        session.removeAttribute("gioHang");
        session.removeAttribute("orderDate");

        // Redirect to TrangChuKhach.jsp
        response.sendRedirect("TrangChuKhach.jsp");
    }
}
