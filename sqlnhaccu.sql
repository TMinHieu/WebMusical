CREATE TABLE NhacCu (
    maNhacCu VARCHAR(50) PRIMARY KEY,
    tenNhacCu VARCHAR(100) NOT NULL,
    gia INT NOT NULL,
    tinhTrang VARCHAR(50),
    loaiNhacCu VARCHAR(50),
    anh VARBINARY(MAX)
);
