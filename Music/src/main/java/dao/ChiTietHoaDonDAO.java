package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.DatabaseConnection;
import entities.ChiTietHoaDon;

public class ChiTietHoaDonDAO {

	public void saveChiTietHoaDon(ChiTietHoaDon chiTietHoaDon) {
		try (Connection conn = DatabaseConnection.initializeDatabase()) {
			String sql = "INSERT INTO ChiTietHoaDon (maHoaDon, maNhacCu, gia) VALUES (?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, chiTietHoaDon.getMaHoaDon());
			ps.setString(2, chiTietHoaDon.getMaNhacCu());
			ps.setInt(3, chiTietHoaDon.getGia());

			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<ChiTietHoaDon> getChiTietHoaDonsByMaHoaDon(int maHoaDon) {
		List<ChiTietHoaDon> chiTietHoaDons = new ArrayList<>();
		String query = "SELECT * FROM ChiTietHoaDon WHERE maHoaDon = ?";

		try (Connection conn = DatabaseConnection.initializeDatabase();
				PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setInt(1, maHoaDon);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					ChiTietHoaDon chiTiet = new ChiTietHoaDon();
					chiTiet.setMaHoaDon(rs.getInt("maHoaDon"));
					chiTiet.setMaNhacCu(rs.getString("maNhacCu"));
					chiTiet.setGia(rs.getInt("gia"));
					chiTietHoaDons.add(chiTiet);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return chiTietHoaDons;
	}
}
