/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.servicImp;

import java.util.List;
import model.NhanVien;
import model.TaiKhoan;
import repository.NhanVienRepository;
import service.OnePieceFashtion;

/**
 *
 * @author ADMIN
 */
public class NhanVienServiceImp implements OnePieceFashtion<NhanVien, String>{
    
    NhanVienRepository repo = new NhanVienRepository();
    public TaiKhoan getTK(String ma){
        return repo.getTK(ma);
    }
    
    @Override
    public List<NhanVien> getAll(){
        return  repo.getAll();
    }

    @Override
    public int them(NhanVien k) {
        return repo.insert(k);
    }

    @Override
    public int sua(NhanVien k, String e) {
        return repo.update(k, e);
    }

   

    @Override
    public NhanVien getOne(String e) {
        return  repo.getOne(e);
    }

    @Override
    public List<NhanVien> getList(String e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int xoa(String e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
     public List<NhanVien> getList2(String e, String f){
        return repo.getList2(e, f);
    }
    public List<NhanVien> getList3(String e, String f){
        return repo.getList3(e, f);
    }
    public List<NhanVien> listPageNV(int tt,int index) {
        return repo.listPageNV(tt,index);
    }
    public int tongBanGhi(int index) {
        return repo.tongBanGhi(index);
    }
    public NhanVien timTheoUserName(String e) {
        return repo.timTheoUserName(e);
    }
    public int insertTK(TaiKhoan tk){
        return repo.insertTK(tk);
    }
    public int updateTK(TaiKhoan tk) {
        return repo.updateTK(tk);
    }
    
}
