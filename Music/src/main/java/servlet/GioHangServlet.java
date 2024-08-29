package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.NhacCuDAO;
import entities.NhacCu;

@WebServlet("/GioHangServlet")
public class GioHangServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private NhacCuDAO nhacCuDAO;

    @Override
    public void init() {
        nhacCuDAO = new NhacCuDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        List<NhacCu> gioHang = (List<NhacCu>) session.getAttribute("gioHang");

        if (gioHang == null) {
            gioHang = new ArrayList<>();
            session.setAttribute("gioHang", gioHang);
        }

        List<NhacCu> nhacCuList = new ArrayList<>();
        int totalPrice = 0;

        try {
            for (NhacCu nhacCu : gioHang) {
                NhacCu nhacCuDetail = nhacCuDAO.getNhacCuByMa(nhacCu.getMaNhacCu());
                if (nhacCuDetail != null) {
                    nhacCuList.add(nhacCuDetail);
                    totalPrice += nhacCuDetail.getGia();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
            return;
        }

        request.setAttribute("nhacCuList", nhacCuList);
        request.setAttribute("totalPrice", totalPrice);
        request.getRequestDispatcher("GioHang.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        List<NhacCu> gioHang = (List<NhacCu>) session.getAttribute("gioHang");

        if (gioHang == null) {
            gioHang = new ArrayList<>();
            session.setAttribute("gioHang", gioHang);
        }

        if ("checkout".equals(action)) {
            try {
                // Get the current date and time
                Date now = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                String orderDate = dateFormat.format(now);

                // Save the order details (you might want to add this functionality)
                // Example: Save order to database (implementation required)

                // Save order date to session
                session.setAttribute("orderDate", orderDate);

                // Redirect to success page
                response.sendRedirect("ThanhToanThanhCong.jsp");
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("error.jsp");
            }
        } else {
            // Handle "add to cart" action or other actions
            String maNhacCu = request.getParameter("maNhacCu");
            try {
                NhacCu nhacCu = nhacCuDAO.getNhacCuByMa(maNhacCu);
                if (nhacCu != null) {
                    gioHang.add(nhacCu);
                    session.setAttribute("gioHang", gioHang);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendRedirect("error.jsp");
            }
            response.sendRedirect("ChiTietNhacCu.jsp?maNhacCu=" + maNhacCu);
        }
    }

}