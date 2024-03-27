CREATE DATABASE OnePieceFashtion
GO
USE OnePieceFashtion
GO 
CREATE TABLE [TaiKhoan] (
  [MaTK] varchar(10),
  [UserName] varchar(20),
  [PassWord] varchar(20),
  [Role] varchar(10),
  [TrangThai] bit,
  PRIMARY KEY ([MaTK])
);
CREATE TABLE [NhanVien] (
  [MaNV] varchar(10),
  [MaTK] varchar(10) not null,
  CONSTRAINT FK_NV_TK foreign key(MaTK) references TaiKhoan(MaTK),
  [HoTen] nvarchar(50),
  [GioiTinh] bit,
  [DiaChi] nvarchar(100),
  [SoDienThoai] varchar(10),
  [CCCD] nvarchar(50),
  [NgayVaoLam] date,
  [TrangThai] bit,
  [Anh] varchar(MAX),
  PRIMARY KEY ([MaNV])
);

CREATE TABLE [KhachHang] (
  [MaKH] varchar(10),
  [HoTen] nvarchar(50),
  [NgaySinh] date,
  [SoDienThoai] varchar(10),
  [Email] varchar(100),
  [GioiTInh] bit,
  [DiaChi] nvarchar(100),
  PRIMARY KEY ([MaKH])
);

CREATE TABLE [Events] (
  [MaEV] varchar(10),
  [TenEV] nvarchar(50),
  [HinhThuc] bit,
  [MucGiamGia] varchar(100),
  [ThoiGianBatDau] date,
  [ThoiGianKetThuc] date,
  [MoTa] nvarchar(100),
  [TrangThai] bit,
  [DieuKienApDung] bit,
  [DieuKienTongTien] nvarchar(50)
  PRIMARY KEY ([MaEV])
);


CREATE TABLE [HoaDon] (
  [MaHoaDon] varchar(10),
  [MaNV] varchar(10) not null,
  CONSTRAINT FK_HD_NV foreign key(MaNV) references NhanVien(MaNV),
  [MaKH] varchar(10),
  CONSTRAINT FK_HD_KH foreign key(MaKH) references KhachHang(MaKH),
  [NgayTao] datetime,
  [TongTien] Money,
  [TongTienKM] Money,
  [TongTienSauKM] Money,
  [TrangThai] nvarchar(30),
  [GhiChu] nvarchar(100),
   [MaEV] varchar(10),
   CONSTRAINT FK_HD_EV foreign key(MaEV) references Events(MaEV),
  PRIMARY KEY ([MaHoaDon])
);


CREATE TABLE [LoaiSanPham] (
  [MaLSP] varchar(10),
  [TenLSP] nvarchar(50),
  [TrangThai] bit,
  [Mota] nvarchar(100),
  PRIMARY KEY ([MaLSP])
);
CREATE TABLE [SanPham] (
  [MaSanPham] varchar(10),
  [TenSanPham] nvarchar(50),
  [TrangThai] bit,
  [MaLSP] varchar(10) not null,
  CONSTRAINT FK_SP_LSP foreign key(MaLSP) references LoaiSanPham(MaLSP),
  [XuatXu] nvarchar(50),
  PRIMARY KEY ([MaSanPham])
);

CREATE TABLE [MauSac] (
  [MaMauSac] varchar(10),
  [TenMauSac] nvarchar(50),
  [TrangThai] bit,
  PRIMARY KEY ([MaMauSac])
);

CREATE TABLE [ChatLieu] (
  [MaChatLieu] varchar(10),
  [TenChatLieu] nvarchar(50),
  [TrangThai] bit,
  PRIMARY KEY ([MaChatLieu])
);

CREATE TABLE [KichThuoc] (
  [MaKichThuoc] varchar(10),
  [TenKichThuoc] nvarchar(50),
  [TrangThai] bit,
PRIMARY KEY ([MaKichThuoc])
);

CREATE TABLE [ChiTietSanPham] (
 --[MaCTSP] VARCHAR(10) NOT NULL DEFAULT SUBSTRING(CONVERT(varchar(255), NEWID()), 25, 5),
 [MaCTSP] VARCHAR(10) NOT NULL DEFAULT CONCAT('CTSP', SUBSTRING(CONVERT(varchar(255), NEWID()), 25, 6)),
  [MaSanPham] varchar(10) not null,
  CONSTRAINT FK_CTSP_SP foreign key(MaSanPham) references SanPham(MaSanPham),
  [MaMauSac] varchar(10) not null,
  CONSTRAINT FK_CTSP_MS foreign key(MaMauSac) references MauSac(MaMauSac),
  [MaChatLieu] varchar(10) not null,
   CONSTRAINT FK_CTSP_CL foreign key(MaChatLieu) references ChatLieu(MaChatLieu),
  [MaKichThuoc] varchar(10) not null,
   CONSTRAINT FK_CTSP_KT foreign key(MaKichThuoc) references KichThuoc(MaKichThuoc),
  [SoLuong] int,
  [Gia] money,
  [TrangThai] bit,
  qrCode varchar(50),
  PRIMARY KEY ([MaCTSP])
);


CREATE TABLE [HoaDonChiTiet] (
  [MaHoaDonChiTiet] varchar(20),
  [MaCTSP] varchar(10) not null,
   CONSTRAINT FK_HDCT_SPCT foreign key(MaCTSP) references ChiTietSanPham(MaCTSP),
   [MaHoaDon] varchar(10) not null,
   CONSTRAINT FK_HDCT_HD foreign key(MaHoaDon) references HoaDon(MaHoaDon),
  [SoLuong] int,
  [DonGia] money,
  [GhiChu] nvarchar(100),
  [TrangThai] bit,
  PRIMARY KEY ([MaHoaDonChiTiet])
);

