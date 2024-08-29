package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CustomerDAO;
import entities.Customer;

@WebServlet("/DangKyServlet")
public class DangKyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fullName = request.getParameter("fullName");
        String phoneNumber = request.getParameter("phoneNumber");
        String password = request.getParameter("password");
        String address = request.getParameter("address");

        Customer customer = new Customer();
        customer.setFullName(fullName);
        customer.setPhoneNumber(phoneNumber);
        customer.setPassword(password);
        customer.setAddress(address);

        CustomerDAO customerDAO = new CustomerDAO();
        try {
            customerDAO.registerCustomer(customer);
            response.sendRedirect("DangNhap.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
