/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.servicImp;

import java.sql.SQLException;
import java.util.List;
import model.SanPham;
import repository.SanPhamRepository;
import service.OnePieceFashtion;

/**
 *
 * @author user
 */
public class SanPhamServiceImp implements OnePieceFashtion<SanPham, String> {

    SanPhamRepository repo = new SanPhamRepository();

    @Override
    public List<SanPham> getAll() {
        return repo.getAll();
    }
    @Override
    public int them(SanPham k) {
        return repo.them(k);
    }
    @Override
    public int sua(SanPham k, String e) {
        return repo.sua(k, e);
    }
    @Override
    public int xoa(String e) {
        return repo.xoa(e);
    }
    @Override
    public SanPham getOne(String e) {
        return repo.getOne(e);
    }
    @Override
    public List<SanPham> getList(String e) {
        return repo.getList(e);
    }
    public List<SanPham> listPageSP(int index) {
        return repo.listPageSP(index);
    }
    public int tongBanGhi(){
        return repo.tongBanGhi();
    }
    public boolean checkExitSP(String maSP) throws SQLException{
        return repo.checkExitSP(maSP);
    }
    
}
