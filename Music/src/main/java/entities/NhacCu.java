package entities;

import java.io.InputStream;

public class NhacCu {
	private String maNhacCu;
	private String tenNhacCu;
	private int gia;
	private String tinhTrang;
	private String loaiNhacCu;
	private InputStream anh;

	public NhacCu() {
	}

	public NhacCu(String maNhacCu, String tenNhacCu, int gia, String tinhTrang, String loaiNhacCu, InputStream anh) {
		this.maNhacCu = maNhacCu;
		this.tenNhacCu = tenNhacCu;
		this.gia = gia;
		this.tinhTrang = tinhTrang;
		this.loaiNhacCu = loaiNhacCu;
		this.anh = anh;
	}

	// Getters and Setters
	public String getMaNhacCu() {
		return maNhacCu;
	}

	public void setMaNhacCu(String maNhacCu) {
		this.maNhacCu = maNhacCu;
	}

	public String getTenNhacCu() {
		return tenNhacCu;
	}

	public void setTenNhacCu(String tenNhacCu) {
		this.tenNhacCu = tenNhacCu;
	}

	public int getGia() {
		return gia;
	}

	public void setGia(int gia) {
		this.gia = gia;
	}

	public String getTinhTrang() {
		return tinhTrang;
	}

	public void setTinhTrang(String tinhTrang) {
		this.tinhTrang = tinhTrang;
	}

	public String getLoaiNhacCu() {
		return loaiNhacCu;
	}

	public void setLoaiNhacCu(String loaiNhacCu) {
		this.loaiNhacCu = loaiNhacCu;
	}

	public InputStream getAnh() {
		return anh;
	}

	public void setAnh(InputStream anh) {
		this.anh = anh;
	}
}
