/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.servicImp;

import java.util.List;
import model.ChiTietSanPham;
import repository.ChiTietSanPhamRepository;
import service.OnePieceFashtion;

/**
 *
 * @author user
 */
public class ChiTietSanPhamServiceImp implements OnePieceFashtion<ChiTietSanPham, String> {

    ChiTietSanPhamRepository repo = new ChiTietSanPhamRepository();

    @Override
    public List<ChiTietSanPham> getAll() {
        return repo.getAll();
    }

    @Override
    public int them(ChiTietSanPham k) {
        return repo.them(k);
    }

    @Override
    public int sua(ChiTietSanPham k, String e) {
        return repo.suas(k, e);
    }

    @Override
    public int xoa(String e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ChiTietSanPham getOne(String e) {
        return repo.getOne(e);
    }

    @Override
    public List<ChiTietSanPham> getList(String e) {
        return repo.getList(e);
    }

    public List<ChiTietSanPham> getListGia(double giaMin, double giaMax, String name) {
        return repo.getListGia(giaMin, giaMax, name);
    }

    public List<ChiTietSanPham> listPageCTSP(int index, String name) {
        return repo.listPageCTSP(index, name);
    }

    public List<ChiTietSanPham> listPageCTSP2(int index) {
        return repo.listPageCTSP2(index);
    }

    public int tongBanGhi(String name) {
        return repo.tongBanGhi(name);
    }

    public int tongBanGhi2() {
        return repo.tongBanGhi2();
    }

    public int updateTrangThaiSoLuong() {
        return repo.updateTrangThaiSoLuong();
    }

    public boolean checkMaQR(String qrCode) {
        return repo.checkMaQR(qrCode);
    }

    public int taoQR(String qrCode) {
        return repo.taoQR(qrCode);
    }

    public List<ChiTietSanPham> getDanhSachSPCT(String maHoadon) {
        return repo.getDanhSachSPCT(maHoadon);
    }

    public boolean checkTrungCTSP(String name1, String name2, String name3, String name4) {
        return repo.checkTrungCTSP(name1, name2, name3, name4);
    }

    public List<ChiTietSanPham> getListLoc(String name1, String name2, String name3, String name4) {
        return repo.getListLoc(name1, name2, name3, name4);
    }

    public List<ChiTietSanPham> listPageCTSPDoiHang(int index, String name, String tenCL) {
        return repo.listPageCTSPDoiHang(index, name, tenCL);
    }

    public List<ChiTietSanPham> getListLocMauSacOrKichThuoc(String name1, String name2, String name3, String name4) {
        return repo.getListLocMauSacOrKichThuoc(name1, name2, name3, name4);
    }

    public String getTenCL(String maCTSP) {
        return repo.getTenCL(maCTSP);
    }

    public int tangSoLuong(int soLuong, String maCTSP) {
        return repo.tangSoLuong(soLuong, maCTSP);
    }

    public int giamSoLuong(int soLuong, String maCTSP) {
        return repo.giamSoLuong(soLuong, maCTSP);
    }

    public int capNhatSoLuongThanhToanCong(int soLuong, String ma) {
        return repo.capNhatSoLuongThanhToanCong(soLuong, ma);
    }

    public int capNhatSoLuongThanhToanTru(int soLuong, String ma) {
        return repo.capNhatSoLuongThanhToanTru(soLuong, ma);
    }

    public int capNhatSoLuongThanhToan(int soLuong, String ma) {
        return repo.capNhatSoLuongThanhToan(soLuong, ma);
    }

    //
    public List<ChiTietSanPham> getListLocCL(String name1, String name2) {
        return repo.getListLocCL(name1, name2);
    }

    public List<ChiTietSanPham> getListLocMS(String name1, String name2) {
        return repo.getListLocMS(name1, name2);
    }

    public List<ChiTietSanPham> getListLocKT(String name1, String name2) {
        return repo.getListLocKT(name1, name2);
    }

    public List<ChiTietSanPham> getListLocCLMS(String name1, String name2, String name3) {
        return repo.getListLocCLMS(name1, name2, name3);
    }

    public List<ChiTietSanPham> getListLocMSKT(String name1, String name2, String name3) {
        return repo.getListLocMSKT(name1, name2, name3);
    }

    public List<ChiTietSanPham> getListLocCLKT(String name1, String name2, String name3) {
        return repo.getListLocCLKT(name1, name2, name3);
    }

    public List<ChiTietSanPham> getThuocTinh(String maCTSP) {
        return repo.getThuocTinh(maCTSP);
    }

}
