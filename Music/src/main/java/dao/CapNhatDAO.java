package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.DatabaseConnection;
import entities.NhacCu;

public class CapNhatDAO {

    // Phương thức để cập nhật nhạc cụ
    public void updateNhacCu(NhacCu nhacCu) throws SQLException {
        String sql = "UPDATE NhacCu SET tenNhacCu = ?, gia = ?, tinhTrang = ?, loaiNhacCu = ? WHERE maNhacCu = ?";
        try (Connection conn = DatabaseConnection.initializeDatabase();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nhacCu.getTenNhacCu());
            pstmt.setInt(2, nhacCu.getGia());
            pstmt.setString(3, nhacCu.getTinhTrang());
            pstmt.setString(4, nhacCu.getLoaiNhacCu());
            pstmt.setString(5, nhacCu.getMaNhacCu());
            pstmt.executeUpdate();
        }
    }

    // Phương thức để lấy tất cả nhạc cụ
    public List<NhacCu> getAllNhacCu() throws SQLException {
        List<NhacCu> nhacCuList = new ArrayList<>();
        String sql = "SELECT * FROM NhacCu";
        try (Connection conn = DatabaseConnection.initializeDatabase();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                NhacCu nhacCu = new NhacCu();
                nhacCu.setMaNhacCu(rs.getString("maNhacCu"));
                nhacCu.setTenNhacCu(rs.getString("tenNhacCu"));
                nhacCu.setGia(rs.getInt("gia"));
                nhacCu.setTinhTrang(rs.getString("tinhTrang"));
                nhacCu.setLoaiNhacCu(rs.getString("loaiNhacCu"));
                nhacCuList.add(nhacCu);
            }
        }
        return nhacCuList;
    }
}
