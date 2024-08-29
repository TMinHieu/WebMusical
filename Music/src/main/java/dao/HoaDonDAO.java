package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jdbc.DatabaseConnection;
import entities.HoaDon;

public class HoaDonDAO {

	public int saveHoaDon(HoaDon hoaDon) {
		int maHoaDon = -1;

		try (Connection conn = DatabaseConnection.initializeDatabase()) {
			String sql = "INSERT INTO HoaDon (customerId, orderDate) VALUES (?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, hoaDon.getCustomerId());
			ps.setTimestamp(2, new java.sql.Timestamp(hoaDon.getOrderDate().getTime()));

			int affectedRows = ps.executeUpdate();

			if (affectedRows > 0) {
				try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						maHoaDon = generatedKeys.getInt(1);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return maHoaDon;
	}

	public List<HoaDon> getHoaDonsByCustomerId(int customerId) {
		List<HoaDon> hoaDons = new ArrayList<>();
		try (Connection conn = DatabaseConnection.initializeDatabase()) {
			String query = "SELECT * FROM HoaDon WHERE customerId = ?";
			try (PreparedStatement ps = conn.prepareStatement(query)) {
				ps.setInt(1, customerId);
				try (ResultSet rs = ps.executeQuery()) {
					while (rs.next()) {
						HoaDon hoaDon = new HoaDon();
						hoaDon.setMaHoaDon(rs.getInt("maHoaDon"));
						hoaDon.setCustomerId(rs.getInt("customerId"));
						hoaDon.setOrderDate(rs.getTimestamp("orderDate"));
						hoaDons.add(hoaDon);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hoaDons;
	}
}
