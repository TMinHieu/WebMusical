package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.NhacCu;
import jdbc.DatabaseConnection;


public class XoaDAO {

    // Lấy danh sách tất cả nhạc cụ
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
                nhacCu.setAnh(rs.getBinaryStream("anh"));
                nhacCuList.add(nhacCu);
            }
        }
        return nhacCuList;
    }

    // Xóa nhạc cụ theo mã nhạc cụ
    public void deleteNhacCu(String maNhacCu) throws SQLException {
        String sql = "DELETE FROM NhacCu WHERE maNhacCu = ?";
        try (Connection conn = DatabaseConnection.initializeDatabase();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, maNhacCu);
            pstmt.executeUpdate();
        }
    }
}
