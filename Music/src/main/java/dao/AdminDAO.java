package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entities.Admin;
import jdbc.DatabaseConnection;

public class AdminDAO {
    private Connection conn;

    public AdminDAO() {
        conn = DatabaseConnection.initializeDatabase();
    }

    public Admin validateAdmin(String phoneNumber, String password) throws SQLException {
        String sql = "SELECT * FROM Admins WHERE PhoneNumber = ? AND Password = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, phoneNumber);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            Admin admin = new Admin();
            admin.setAdminId(rs.getInt("AdminID"));
            admin.setPhoneNumber(rs.getString("PhoneNumber"));
            admin.setPassword(rs.getString("Password"));
            return admin;
        }

        return null;
    }
}

