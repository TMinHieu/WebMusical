package dao;

import entities.NhacCu;
import jdbc.DatabaseConnection;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ThemDAO {
    private Connection connection;

    public ThemDAO() {
        this.connection = DatabaseConnection.initializeDatabase();
    }

    public void addNhacCu(NhacCu nhacCu) {
        String sql = "INSERT INTO NhacCu (maNhacCu, tenNhacCu, gia, tinhTrang, loaiNhacCu, anh) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nhacCu.getMaNhacCu());
            statement.setString(2, nhacCu.getTenNhacCu());
            statement.setInt(3, nhacCu.getGia());
            statement.setString(4, nhacCu.getTinhTrang());
            statement.setString(5, nhacCu.getLoaiNhacCu());
            statement.setBlob(6, nhacCu.getAnh());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
