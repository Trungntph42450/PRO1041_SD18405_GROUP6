INSERT INTO TaiKhoan VALUES
('TK01','onePiece1','shopbanhang1','Admin',1),
('TK02','onePiece2','shopbanhang2','Staff',1),
('TK03','onePiece3','shopbanhang3','Staff',1),
('TK04','onePiece4','shopbanhang4','Staff',1),
('TK05','onePiece5','shopbanhang5','Staff',0)
INSERT INTO NhanVien VALUES
('NV01','TK01',N'Nguyễn Thành Trung',1,N'Ninh Bình','0334294889','037643953721','2023-09-15',1,'anh1.png'),
('NV02','TK02',N'Nguyễn Thảo Linh',1,N'Nam Định','0958655432','037637459321','2023-09-15',1,'anh2.png'),
('NV03','TK03',N'Lê Trọng An',0,N'Thanh Hóa','0394747324','0378475845','2023-08-13',1,'anh3.png'),
('NV04','TK04',N'Nguyễn Lọng',1,N'Hà Nội','0334294653','037643953435','2023-07-15',0,'anh4.png'),
('NV05','TK05',N'Trần Văn Thành',0,N'Bắc Ninh','0334294878','037643953267','2023-07-19',0,'anh5.png')
INSERT INTO KhachHang Values
('KH01',N'Phùng Duy Nam','2004-11-03','0934832745','bangdx@gmail.com',1,N'Nam Định'),
('KH02',N'Đào Ngọc Tâm','2004-07-09','0934832324','truongvmx@gmail.com',1,N'Đồng Nai'),
('KH03',N'Hoàng Nam Khánh','2004-01-15','0934832234','tamhm@gmail.com',0,N'Nghệ An'),
('KH04',N'Trịnh Văn Khang','2004-08-22','0934832789','khanhnt@gmail.com',1,N'Hà Tĩnh'),
('KH05',N'Hà Văn Búp','2004-07-23','0934832987','dunglt@gmail.com',1,N'Ninh Bình'),
('KH06',N'Phùng Duy Than','2004-12-06','0985667832','duongtl@gmail.com',0,N'Hải Phòng'),
('KH07',N'Nguyễn Cao Thượng','2004-11-23','0912356743','thangnc@gmail.com',1,N'Bắc Giang'),
('KH08',N'Nguyễn Phạm Minh ','2004-07-14','0933461234','anhnpm@gmail.com',1,N'Vĩnh Phúc'),
('KH09',N'Phạm Ngọc Nam','2004-04-18','0933461233','anhpn@gmail.com',1,N'Cao Bằng'),
('KH10',N'Nguyễn Tâm Thành','2004-02-14','0933461232','nhunt@gmail.com',1,N'Lạng Sơn'),
('KH11',N'Nguyễn Hùng Phong','2004-04-20','0933461238','phongnv@gmail.com',1,N'Cần Thơ'),
('KH12',N'Nguyễn Thành Quang','2004-01-23','0933461237','quangnv@gmail.com',1,N'Bình Dương'),
('KHNE',null,null,null,null,null,null)
INSERT INTO LoaiSanPham VALUES
('LSP01',N'Áo hoodie nam',1,N'Hàng đẹp'),
('LSP02',N'Áo sơ mi nam',1,N'Hàng đẹp'),
('LSP03',N'Áo khoác nam',1,N'Hàng đẹp'),
('LSP04',N'Áo len nam',1,N'Hàng đẹp'),
('LSP05',N'Áo jeans nam',1,N'Hàng đẹp')
INSERT INTO SanPham VALUES
('SP01',N'Nuker',1,'LSP05',N'Việt Nam'),
('SP02',N'GAP',1,'LSP05',N'Hàn Quốc'),
('SP03',N'LEVI',1,'LSP05',N'Thái Lan'),
('SP04',N'Basic Tee',1,'LSP01',N'Hàn Quốc'),
('SP05',N'Graphic Tee',1,'LSP01',N'Việt Nam'),
('SP06',N'Longline Tee',1,'LSP01',N'Campuchia'),
('SP07',N'Flannel Shirt',1,'LSP03',N'Việt Nam'),
('SP08',N'Inen Shirt',1,'LSP03',N'Việt Nam'),
('SP09',N'Western Shirt',1,'LSP03',N'Nhật Bản'),
('SP10',N'Denim Jacket',1,'LSP02',N'Việt Nam'),
('SP11',N'Down Jacket',1,'LSP02',N'Việt Nam'),
('SP12',N'Pea Coat ',1,'LSP02',N'Đài Loan')
INSERT INTO MauSac VALUES
('MS01',N'Đỏ',1),
('MS02',N'Đen',1),
('MS03',N'Trắng',1),
('MS04',N'Xanh',1),
('MS05',N'Vàng',1),
('MS06',N'Xám',1),
('MS07',N'Be',1),
('MS08',N'Hồng',1),
('MS09',N'Bạc',1),
('MS10',N'Tím',1)
INSERT INTO ChatLieu VALUES
('CL01',N'Vải Canvas',1),
('CL02',N'Vải Bò',1),
('CL03',N'Vải Kaki',1),
('CL04',N'Vải Lụa',1),
('CL05',N'Vải Nỉ',1),
('CL06',N'Vải Len',1)

INSERT INTO KichThuoc VALUES
('KT01','S',1),
('KT02','M',1),
('KT03','L',1),
('KT04','XL',1),
('KT05','XXL',1)
INSERT INTO Events VALUES
('EV01',N'Sinh nhật cửa hàng',1,'20000','2023-03-11','2023-03-15',N'Siêu giảm giá',1,1,'2000000'),
('EV02',N'Quốc tế Nam giới',1,'15000','2023-10-19','2023-10-21',N'Siêu giảm giá',1,0,''),
('EV03',N'Giáng sinh',0,'40%','2023-11-03','2023-11-12',N'Siêu giảm giá',1,1,'2500000'),
('EV04',N'Ngày nhà giáo Việt Nam 20/11',0,'40%','2023-11-12','2023-11-13',N'Siêu giảm giá',1,1,'7500000')

INSERT INTO ChiTietSanPham(MaSanPham,MaMauSac,MaChatLieu,MaKichThuoc,SoLuong,Gia,TrangThai,qrCode) VALUES
('SP01','MS10','CL06','KT05',215,410000,1,null),
('SP01','MS08','CL05','KT04',126,420000,1,null),
('SP01','MS07','CL04','KT03',99,430000,1,null),
('SP01','MS06','CL03','KT02',250,440000,1,null),
('SP01','MS05','CL02','KT01',210,450000,0,null),
('SP01','MS04','CL01','KT05',150,400000,1,null),
('SP02','MS03','CL06','KT04',110,650000,1,null),
('SP02','MS02','CL05','KT03',120,630000,1,null),
('SP02','MS01','CL04','KT02',123,640000,1,null),
('SP02','MS10','CL03','KT01',80,620000,1,null),
('SP02','MS09','CL02','KT05',78,610000,1,null),
('SP02','MS08','CL01','KT04',98,600000,1,null),
('SP03','MS07','CL06','KT03',67,980000,1,null),
('SP03','MS06','CL05','KT02',127,970000,1,null),
('SP03','MS05','CL04','KT01',140,960000,1,null),
('SP03','MS04','CL03','KT05',230,950000,1,null),
('SP03','MS03','CL02','KT04',170,940000,1,null),
('SP03','MS02','CL01','KT03',200,930000,1,null),
('SP04','MS10','CL06','KT05',215,410000,1,null),
('SP04','MS08','CL05','KT04',126,420000,0,null),
('SP04','MS07','CL04','KT03',99,430000,1,null),
('SP04','MS06','CL03','KT02',250,440000,1,null),
('SP04','MS05','CL02','KT01',210,450000,1,null),
('SP04','MS04','CL01','KT05',150,400000,1,null),
('SP05','MS03','CL06','KT04',110,650000,1,null),
('SP05','MS02','CL05','KT03',120,630000,1,null),
('SP05','MS01','CL04','KT02',123,640000,1,null),
('SP05','MS10','CL03','KT01',80,620000,1,null),
('SP05','MS09','CL02','KT05',78,610000,1,null),
('SP05','MS08','CL01','KT04',98,600000,1,null),
('SP06','MS07','CL06','KT03',67,980000,1,null),
('SP06','MS06','CL05','KT02',127,970000,1,null),
('SP06','MS05','CL04','KT01',140,960000,1,null),
('SP06','MS04','CL03','KT05',230,950000,1,null),
('SP06','MS03','CL02','KT04',170,940000,1,null),
('SP06','MS02','CL01','KT03',200,930000,1,null),
('SP07','MS03','CL06','KT04',110,650000,1,null),
('SP07','MS02','CL05','KT03',120,630000,1,null),
('SP07','MS01','CL04','KT02',123,640000,1,null),
('SP07','MS10','CL03','KT01',80,620000,1,null),
('SP07','MS09','CL02','KT05',78,610000,1,null),
('SP07','MS08','CL01','KT04',98,600000,1,null),
('SP08','MS10','CL06','KT05',215,410000,1,null),
('SP08','MS08','CL05','KT04',126,420000,1,null),
('SP08','MS07','CL04','KT03',99,430000,1,null),
('SP08','MS06','CL03','KT02',250,440000,1,null),
('SP08','MS05','CL02','KT01',210,450000,1,null),
('SP08','MS04','CL01','KT05',150,400000,1,null),
('SP09','MS07','CL06','KT03',67,980000,1,null),
('SP09','MS06','CL05','KT02',127,970000,1,null),
('SP09','MS05','CL04','KT01',140,960000,1,null),
('SP09','MS04','CL03','KT05',230,950000,1,null),
('SP09','MS03','CL02','KT04',170,940000,1,null),
('SP09','MS02','CL01','KT03',200,930000,1,null),
('SP10','MS07','CL06','KT03',67,980000,1,null),
('SP10','MS06','CL05','KT02',127,970000,1,null),
('SP10','MS05','CL04','KT01',140,960000,1,null),
('SP10','MS04','CL03','KT05',230,950000,1,null),
('SP10','MS03','CL02','KT04',170,940000,1,null),
('SP10','MS02','CL01','KT03',200,930000,1,null),
('SP11','MS10','CL06','KT05',215,410000,1,null),
('SP11','MS08','CL05','KT04',126,420000,1,null),
('SP11','MS07','CL04','KT03',99,430000,1,null),
('SP11','MS06','CL03','KT02',250,440000,1,null),
('SP11','MS05','CL02','KT01',210,450000,1,null),
('SP11','MS04','CL01','KT05',150,400000,1,null),
('SP12','MS10','CL06','KT05',215,410000,1,null),
('SP12','MS08','CL05','KT04',126,420000,1,null),
('SP12','MS07','CL04','KT03',99,430000,1,null),
('SP12','MS06','CL03','KT02',250,440000,1,null),
('SP12','MS05','CL02','KT01',210,450000,1,null),
('SP12','MS04','CL01','KT05',150,400000,1,null)

INSERT INTO HoaDon VALUES
('HD01','NV05','KH03',getDate(),450000,50000,400000,N'Đã thanh toán',N'Thành công','EV03'),
('HD02','NV03','KH02',getDate(),130000,50000,90000,N'Đã thanh toán',N'Thành công','EV02'),
('HD03','NV02','KH04',getDate(),750000,50000,700000,N'Đã đổi hàng',N'Thành công','EV01'),
('HD04','NV03','KH02',getDate(),130000,50000,90000,N'Đã thanh toán',N'Thành công','EV02'),
('HD05','NV02','KH01',getDate(),750000,50000,700000,N'Đã thanh toán',N'Thành công','EV01'),
('HD06','NV03','KH02','2023-12-20',130000,50000,90000,N'Đã đổi hàng',N'Thành công','EV02'),
('HD07','NV01','KH01','2023-12-1',750000,50000,700000,N'Đã thanh toán',N'Thành công','EV01'),
('HD08','NV02','KH05','2023-11-25',750000,50000,700000,N'Đã đổi hàng',N'Thành công','EV01'),
('HD09','NV01','KH05','2023-11-25',750000,50000,700000,N'Đã thanh toán',N'Thành công','EV01'),
('HD10','NV05','KH03','2023-11-1',450000,50000,400000,N'Đã đổi hàng',N'Thành công','EV03'),
('HD12','NV03','KH02','2023-11-1',130000,50000,90000,N'Đã thanh toán',N'Thành công','EV02'),
('HD11','NV05','KH01','2023-11-1',450000,50000,400000,N'Đã thanh toán',N'Thành công','EV03'),
('HD13','NV02','KH04','2023-11-3',750000,50000,700000,N'Đã thanh toán',N'Thành công','EV01'),
('HD14','NV03','KH02','2023-11-3',130000,50000,90000,N'Đã thanh toán',N'Thành công','EV02'),
('HD15','NV02','KH07','2023-11-3',750000,50000,700000,N'Đã thanh toán',N'Thành công','EV01'),
('HD16','NV03','KH06','2023-11-5',130000,50000,90000,N'Đã thanh toán',N'Thành công','EV02'),
('HD17','NV02','KH07','2023-11-5',750000,50000,700000,N'Đã thanh toán',N'Thành công','EV01'),
('HD18','NV01','KH12','2023-11-5',750000,50000,700000,N'Đã thanh toán',N'Thành công','EV01'),
('HD19','NV02','KH11','2023-11-15',750000,50000,700000,N'Đã thanh toán',N'Thành công','EV01'),
('HD20','NV03','KH09','2023-11-23',750000,50000,700000,N'Đã thanh toán',N'Thành công','EV01'),
('HD21','NV05','KH10','2023-12-23',750000,50000,700000,N'Đã thanh toán',N'Thành công','EV01'),
('HD22','NV01','KH09','2023-12-23',750000,50000,700000,N'Đã thanh toán',N'Thành công','EV03'),
('HD23','NV04','KH10','2023-12-23',750000,50000,700000,N'Đã thanh toán',N'Thành công','EV03')




 select * from ChiTietSanPham
  select * from DoiHangChiTiet
--đoạn code dưới để check HDCT nhé nào cảm thấy thiếu thì check có thể not in or in

--delete from HoaDonChiTiet

DECLARE @Counter INT = 1;

WHILE @Counter <= 23
BEGIN
    DECLARE @MaHoaDon NVARCHAR(10) = 'HD' + RIGHT('00' + CAST(@Counter AS VARCHAR(2)), 2);

    INSERT INTO HoaDonChiTiet (MaHoaDonChiTiet, MaCTSP, MaHoaDon, SoLuong, DonGia, GhiChu, TrangThai)
    SELECT @MaHoaDon + '_' + CAST((ROW_NUMBER() OVER (ORDER BY NEWID()) - 1) * 3 + 1 AS VARCHAR(10)),
           MaCTSP,
           @MaHoaDon,
           ROUND(RAND() * (10 - 1) + 1, 0), -- Số lượng ngẫu nhiên từ 1 đến 10 cho từng hàng
           Gia,
           N'OK',
           1
    FROM ChiTietSanPham ctsp
    ORDER BY NEWID()
    OFFSET 0 ROWS FETCH NEXT 1 ROWS ONLY; -- Chỉ chọn 1 hàng mỗi lần

    SET @Counter = @Counter + 1;
END

UPDATE HoaDon
SET TongTien = (
    SELECT SUM(HDCT.SoLuong * HDCT.DonGia) AS TongTien
    FROM HoaDonChiTiet HDCT
    WHERE HoaDon.MaHoaDon = HDCT.MaHoaDon
    GROUP BY HDCT.MaHoaDon
)
UPDATE HoaDon SET TongTienSauKM = TongTien - TongTienKM
SELECT*FROM HoaDonChiTiet

SELECT * FROM ChiTietSanPham where MaCTSP  not in(SELECT MaCTSP FROM HoaDonChiTiet)
SELECT MaHoaDon, COUNT(MaCTSP) FROM HoaDonChiTiet
GROUP BY MaHoaDon
HAVING COUNT(MaCTSP)=0
INSERT INTO DoiHang VALUES
('DH01','NV02','HD03','2023-11-07',N'Đổi thành công'),
('DH02','NV04','HD06','2023-11-14',N'Đổi thành công'),
('DH03','NV05','HD08','2023-11-19',N'Đổi thành công'),
('DH04','NV03','HD10','2023-11-29',N'Đổi thành công')

INSERT INTO DoiHangChiTiet VALUES
('DHCT01','CTSP0650DF','DH03','HD01_127',2,N'Áo bị chật',1),
('DHCT02','CTSP07E84A','DH01','HD01_148',1,N'Áo bị rộng',1),
('DHCT03','CTSP0CB037','DH02','HD01_157',1,N'Áo bị chật',1),
('DHCT04','CTSP10DAA4','DH03','HD01_169',1,N'Áo bị chật',1),
('DHCT05','CTSP1204BD','DH04','HD01_214',1,N'Áo bị rộng',1)

SELECT * FROM [TaiKhoan]
SELECT * FROM [NhanVien]
SELECT * FROM [KhachHang]
SELECT * FROM [Events]
SELECT * FROM [HoaDon]
SELECT * FROM [LoaiSanPham]
SELECT * FROM [SanPham]
SELECT * FROM [MauSac]
SELECT * FROM [ChatLieu]
SELECT * FROM [KichThuoc]
SELECT * FROM [ChiTietSanPham]
SELECT * FROM [HoaDonChiTiet]
SELECT * FROM [DoiHang]
SELECT * FROM [DoiHangChiTiet]











