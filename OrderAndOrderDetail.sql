CREATE TABLE HoaDon (
    maHoaDon INT PRIMARY KEY IDENTITY(1,1),
    customerId INT NOT NULL,
    orderDate DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (customerId) REFERENCES Customers(customerId)
);

CREATE TABLE ChiTietHoaDon (
    maChiTiet INT PRIMARY KEY IDENTITY(1,1),
    maHoaDon INT,
    maNhacCu VARCHAR(50),
    gia INT,
    FOREIGN KEY (maHoaDon) REFERENCES HoaDon(maHoaDon),
    FOREIGN KEY (maNhacCu) REFERENCES NhacCu(maNhacCu)
);
