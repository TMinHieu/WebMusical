package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Customer;
import jdbc.DatabaseConnection;

public class CustomerDAO {
	private Connection conn;

	public CustomerDAO() {
		conn = DatabaseConnection.initializeDatabase();
	}

	public void registerCustomer(Customer customer) throws SQLException {
		String sql = "INSERT INTO Customers (FullName, PhoneNumber, Password, Address) VALUES (?, ?, ?, ?)";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, customer.getFullName());
			ps.setString(2, customer.getPhoneNumber());
			ps.setString(3, customer.getPassword());
			ps.setString(4, customer.getAddress());
			ps.executeUpdate();
		}
	}

	public Customer validateCustomer(String phoneNumber, String password) throws SQLException {
		String sql = "SELECT * FROM Customers WHERE PhoneNumber = ? AND Password = ?";
		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, phoneNumber);
			ps.setString(2, password);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Customer customer = new Customer();
					customer.setCustomerId(rs.getInt("CustomerID"));
					customer.setFullName(rs.getString("FullName"));
					customer.setPhoneNumber(rs.getString("PhoneNumber"));
					customer.setPassword(rs.getString("Password"));
					customer.setAddress(rs.getString("Address"));
					return customer;
				}
			}
		}
		return null;
	}

	public List<Customer> getAllCustomers() throws SQLException {
		List<Customer> customers = new ArrayList<>();
		String sql = "SELECT * FROM Customers";
		try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				Customer customer = new Customer();
				customer.setCustomerId(rs.getInt("CustomerID"));
				customer.setFullName(rs.getString("FullName"));
				customer.setPhoneNumber(rs.getString("PhoneNumber"));
				customer.setPassword(rs.getString("Password"));
				customer.setAddress(rs.getString("Address"));
				customers.add(customer);
			}
		}
		return customers;
	}

	public void deleteCustomer(int customerId) throws SQLException {
		String sql = "DELETE FROM Customers WHERE CustomerID = ?";
		try (Connection conn = DatabaseConnection.initializeDatabase();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, customerId);
			pstmt.executeUpdate();
		}
	}

	public void updateCustomer(int customerId, String fullName, String phoneNumber, String password, String address)
			throws SQLException {
		String sql = "UPDATE Customers SET FullName = ?, PhoneNumber = ?, Password = ?, Address = ? WHERE CustomerID = ?";
		try (Connection conn = DatabaseConnection.initializeDatabase();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, fullName);
			pstmt.setString(2, phoneNumber);
			pstmt.setString(3, password);
			pstmt.setString(4, address);
			pstmt.setInt(5, customerId);
			pstmt.executeUpdate();
		}
	}

	public Customer getCustomerByPhoneNumberAndPassword(String phoneNumber, String password) throws SQLException {
        Customer customer = null;
        String sql = "SELECT * FROM Customers WHERE phoneNumber = ? AND password = ?";
        try (Connection conn = DatabaseConnection.initializeDatabase();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, phoneNumber);
            pstmt.setString(2, password);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    customer = new Customer();
                    customer.setCustomerId(rs.getInt("customerId"));
                    customer.setFullName(rs.getString("fullName"));
                    customer.setPhoneNumber(rs.getString("phoneNumber"));
                    customer.setPassword(rs.getString("password"));
                    customer.setAddress(rs.getString("address"));
                }
            }
        }
        return customer;
    }

    public Customer getCustomerById(int customerId) throws SQLException {
        Customer customer = null;
        String sql = "SELECT * FROM Customers WHERE customerId = ?";
        try (Connection conn = DatabaseConnection.initializeDatabase();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, customerId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    customer = new Customer();
                    customer.setCustomerId(rs.getInt("customerId"));
                    customer.setFullName(rs.getString("fullName"));
                    customer.setPhoneNumber(rs.getString("phoneNumber"));
                    customer.setPassword(rs.getString("password"));
                    customer.setAddress(rs.getString("address"));
                }
            }
        }
        return customer;
    }
    public Customer authenticate(String username, String password) {
        Connection conn = DatabaseConnection.initializeDatabase();
        String sql = "SELECT * FROM Customer WHERE username = ? AND password = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Customer customer = new Customer();
                    customer.setCustomerId(rs.getInt("id"));
                    customer.setFullName(rs.getString("fullname"));
                    customer.setPhoneNumber(rs.getString("phone_number"));
                    customer.setAddress(rs.getString("address"));
                    // Các thuộc tính khác của Customer
                    return customer;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
