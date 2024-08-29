package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AdminDAO;
import dao.CustomerDAO;
import entities.Admin;
import entities.Customer;

@WebServlet("/DangNhapServlet")
public class DangNhapServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CustomerDAO customerDAO;

	@Override
	public void init() throws ServletException {
		customerDAO = new CustomerDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String phoneNumber = request.getParameter("phoneNumber");
		String password = request.getParameter("password");

		CustomerDAO customerDAO = new CustomerDAO();
		AdminDAO adminDAO = new AdminDAO();
		try {
			Customer customer = customerDAO.validateCustomer(phoneNumber, password);
			if (customer != null) {
				HttpSession httpsession = request.getSession();
	            httpsession.setAttribute("currentCustomer", customer);
				response.sendRedirect("TrangChuKhach.jsp");
				return;
			} 

			Admin admin = adminDAO.validateAdmin(phoneNumber, password);
			if (admin != null) {
				response.sendRedirect("TrangChuAdmin.jsp");
				return;
			}

			response.sendRedirect("DangNhap.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
