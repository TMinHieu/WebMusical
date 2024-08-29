package dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.NhacCu;
import jdbc.DatabaseConnection;


public class NhacCuDAO {

    public List<NhacCu> getAllNhacCu() throws SQLException {
        List<NhacCu> nhacCuList = new ArrayList<>();
        String query = "SELECT * FROM NhacCu";
        try (Connection connection = DatabaseConnection.initializeDatabase();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                NhacCu nhacCu = new NhacCu();
                nhacCu.setMaNhacCu(resultSet.getString("MaNhacCu"));
                nhacCu.setTenNhacCu(resultSet.getString("TenNhacCu"));
                nhacCu.setGia(resultSet.getInt("Gia"));
                nhacCu.setTinhTrang(resultSet.getString("TinhTrang"));
                nhacCu.setLoaiNhacCu(resultSet.getString("LoaiNhacCu"));
                nhacCu.setAnh(resultSet.getBinaryStream("Anh"));
                nhacCuList.add(nhacCu);
            }
        }
        return nhacCuList;
    }

    public NhacCu getNhacCuByMa(String maNhacCu) throws SQLException {
        NhacCu nhacCu = null;
        String query = "SELECT * FROM NhacCu WHERE MaNhacCu = ?";
        try (Connection connection = DatabaseConnection.initializeDatabase();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, maNhacCu);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                nhacCu = new NhacCu();
                nhacCu.setMaNhacCu(resultSet.getString("MaNhacCu"));
                nhacCu.setTenNhacCu(resultSet.getString("TenNhacCu"));
                nhacCu.setGia(resultSet.getInt("Gia"));
                nhacCu.setTinhTrang(resultSet.getString("TinhTrang"));
                nhacCu.setLoaiNhacCu(resultSet.getString("LoaiNhacCu"));
                nhacCu.setAnh(resultSet.getBinaryStream("Anh"));
            }
        }
        return nhacCu;
    }
    public List<NhacCu> searchNhacCuByCode(String maNhacCu) {
        List<NhacCu> nhacCuList = new ArrayList<>();
        String query = "SELECT * FROM NhacCu WHERE maNhacCu LIKE ?";
        try (Connection connection = DatabaseConnection.initializeDatabase();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            
            preparedStatement.setString(1, "%" + maNhacCu + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while (resultSet.next()) {
                String maNhacCuDB = resultSet.getString("maNhacCu");
                String tenNhacCu = resultSet.getString("tenNhacCu");
                int gia = resultSet.getInt("gia");
                String tinhTrang = resultSet.getString("tinhTrang");
                String loaiNhacCu = resultSet.getString("loaiNhacCu");
                InputStream anh = resultSet.getBinaryStream("anh"); // Đọc hình ảnh từ cơ sở dữ liệu
                
                NhacCu nhacCu = new NhacCu(maNhacCuDB, tenNhacCu, gia, tinhTrang, loaiNhacCu, anh);
                nhacCuList.add(nhacCu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nhacCuList;
    }
    
}
