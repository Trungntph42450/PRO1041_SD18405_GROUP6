/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.servicelmp;

import java.util.List;
import model.MauSac;
import repository.MauSacRepository;
import service.OnePieceFashtion;

/**
 *
 * @author ADMIN
 */
public class MauSacServicelmp implements OnePieceFashtion<MauSac, String>{
    
    MauSacRepository repo = new MauSacRepository();
    
    @Override
    public List<MauSac> getAll() {
        return repo.getAll();
    }

    @Override
    public int them(MauSac k) {
        return repo.them(k);
    }

    @Override
    public int sua(MauSac k, String e) {
        return repo.sua(k, e);
    }

    @Override
    public int xoa(String e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public MauSac getOne(String e) {
        return repo.getOne(e);
    }

    @Override
    public List<MauSac> getList(String e) {
        return repo.getList(e);
    }
     public List<MauSac> listPageMS(int index) {
        return repo.listPageMS(index);
    }

    public int tongBanGhi() {
        return repo.tongBanGhi();
    }
     public boolean checkTrungMS(String name1){
         return repo.checkTrungMS(name1);
     }
}
