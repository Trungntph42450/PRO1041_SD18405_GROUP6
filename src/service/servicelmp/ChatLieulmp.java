/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.servicelmp;

import java.util.List;
import model.ChatLieu;
import repository.ChatLieuRepository;
import service.OnePieceFashtion;

/**
 *
 * @author ADMIN
 */
public class ChatLieulmp implements OnePieceFashtion<ChatLieu, String>{
        
    ChatLieuRepository repo = new ChatLieuRepository();

    @Override
    public List<ChatLieu> getAll() {
        return repo.getAll();
    }

    @Override
    public int them(ChatLieu k) {
        return repo.them(k);
    }

    @Override
    public int sua(ChatLieu k, String e) {
        return repo.sua(k, e);
    }

    @Override
    public int xoa(String e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ChatLieu getOne(String e) {
        return repo.getOne(e);
    }

    @Override
    public List<ChatLieu> getList(String e) {
        return repo.getList(e);
    }
     public List<ChatLieu> listPageCL(int index){
        return repo.listPageCL(index);
    }
    public int tongBanGhi(){
        return repo.tongBanGhi();
    }
    
    public boolean checkTrungCL(String name1) {
        return repo.checkTrungCL(name1);
    }
    
}
