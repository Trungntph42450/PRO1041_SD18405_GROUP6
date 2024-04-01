/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Nguyen Thanh Trung
 */
public class OnePieceFashtionView extends javax.swing.JFrame {

 Color defaultColor = new Color(255,51,51);// mau do khi buton 
    Color selectedColor = new Color(255,51,153);// mau khi buton onclick
    Color enterColor = new Color(255,51,102);
    String tenNV;
    private boolean isAdmin;

    /**
     * Creates new form Main
     */
    public OnePieceFashtionView() {
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public void showView() {
        initComponents();
        this.dispose();
        setUndecorated(true);
        setSize(1500, 820);
        this.setLocationRelativeTo(null);
        execute(isAdmin);
        changePanelBody(new BanHangView());
    }

    public void changePanelBody(JPanel panel) {
        panelBody.removeAll();
        panelBody.add(panel);
        panelBody.repaint();
        panelBody.revalidate();
    }

    public void sangCTSP() {
        panelBody.removeAll();
        panelBody.add(new ChiTietSanPhamView());
        panelBody.repaint();
        panelBody.revalidate();
    }

    public void setTenNV(String text) {
        lbltenNV.setText(text);
    }

    public String getTenNV() {
        return lbltenNV.getText();
    }

    private void execute(boolean isAdmin) {
        //Thêm icon vào 
        ImageIcon iconThongKe = new ImageIcon(getClass().getResource("/icon/document.png"));
        ImageIcon iconSanPham = new ImageIcon(getClass().getResource("/icon/shirt.png"));
        ImageIcon iconDot = new ImageIcon(getClass().getResource("/icon/black-circle.png"));
        ImageIcon iconNhanVien = new ImageIcon(getClass().getResource("/icon/multiple-users-silhouette.png"));
        ImageIcon iconBanHang = new ImageIcon(getClass().getResource("/icon/shopping-cart.png"));
        ImageIcon iconKhachHang = new ImageIcon(getClass().getResource("/icon/user (1).png"));
        ImageIcon iconHoaDon = new ImageIcon(getClass().getResource("/icon/bill.png"));
        ImageIcon iconKhuyenMai = new ImageIcon(getClass().getResource("/icon/discount.png"));
        ImageIcon iconDoiMatKhau = new ImageIcon(getClass().getResource("/icon/reset-password.png"));
        ImageIcon iconDoiHang = new ImageIcon(getClass().getResource("/icon/exchange.png"));
        ImageIcon iconLichSuDoiHang = new ImageIcon(getClass().getResource("/icon/schedule.png"));
        ImageIcon iconDangXuat = new ImageIcon(getClass().getResource("/icon/power-off.png"));
        //Tạo thanh thống kê
        MenuItem menuThongKeDT = new MenuItem(iconDot, "Thống kê doanh thu", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                changePanelBody(new ThongKeDoanhThuView());
            }
        });

        MenuItem menuThongKeSL = new MenuItem(iconDot, "Thống kê số lượng", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                changePanelBody(new ThongKeSoLuongView());
            }
        });
        MenuItem menuThongKe3 = new MenuItem(iconDot, "Thống kê khác", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                changePanelBody(new ThongKeKhacView());
            }
        });
        // Sản phẩm chung
        MenuItem menuThongKe = new MenuItem(iconThongKe, "Thống kê", null, menuThongKeDT, menuThongKeSL, menuThongKe3);
        //Thanh bên trong sản phẩm
        MenuItem menuSanPham1 = new MenuItem(iconDot, "Sản phẩm", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                changePanelBody(new SanPhamView());
                setBackground(selectedColor);
            }
        });
        MenuItem menuCtsp = new MenuItem(iconDot, "Chi tiết sản phẩm", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                changePanelBody(new ChiTietSanPhamView());
            }
        });

        MenuItem menuThuoctinh = new MenuItem(iconDot, "Thuộc tính", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                changePanelBody(new ThuocTinhView());
            }
        });
        // Sản phẩm chung
        MenuItem menuMatHang = new MenuItem(iconSanPham, "Mặt hàng", null, menuSanPham1, menuThuoctinh);
        //Nhân viên
        MenuItem menuNhanVien = new MenuItem(iconNhanVien, "Nhân viên", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                changePanelBody(new NhanVienView());
            }
        });
        //Bán hàng
        MenuItem menuBanHang = new MenuItem(iconBanHang, "Bán hàng", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                changePanelBody(new BanHangView());
            }
        });
        //Khách  hàng
        MenuItem menuKhachHang = new MenuItem(iconKhachHang, "Khách hàng", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                changePanelBody(new KhachHangView());
            }
        });
        //Hóa đơn- lịch sử
        MenuItem menuHoaDon = new MenuItem(iconHoaDon, "Hóa đơn", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                changePanelBody(new HoaDonView());
            }
        });



        //Khuyến mại
        MenuItem menuKm = new MenuItem(iconKhuyenMai, "Khuyến mại", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                changePanelBody(new KhuyenMaiView());
            }
        });

        //DoiMatKhau
        MenuItem menuDoiMatKhau = new MenuItem(iconDoiMatKhau, "Đổi mật khẩu", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                changePanelBody(new DoiMatKhauView());
            }
        });

        //DangXuat
        MenuItem menuDangXuat = new MenuItem(iconDangXuat, "Đăng xuất", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // TODO add your handling code here:
                int check = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn đăng xuất?");
                if (check == 0) {
                    DangNhapView dangNhapView = new DangNhapView();
                    dispose();
                    dangNhapView.setVisible(true);
                }

            }
        });

        //Câu lệnh thêm vào menu
        if (isAdmin) {
            addMenu(menuThongKe, menuMatHang, menuNhanVien, menuBanHang, menuKhachHang, menuHoaDon, menuKm, menuDoiMatKhau, menuDangXuat);
        } else {
            addMenu(menuBanHang, menuKhachHang, menuHoaDon, menuDoiMatKhau, menuDangXuat);
        }
        MenuItemColor(menuThongKe);
        MenuItemColor(menuMatHang);
        MenuItemColor(menuSanPham1);
        MenuItemColor(menuThuoctinh);
        MenuItemColor(menuNhanVien);
        MenuItemColor(menuBanHang);
        MenuItemColor(menuKhachHang);
        MenuItemColor(menuHoaDon);
        MenuItemColor(menuKm);
        MenuItemColor(menuThongKeDT);
        MenuItemColor(menuThongKeSL);
        MenuItemColor(menuThongKe3);
        MenuItemColor(menuDoiMatKhau);
        MenuItemColor(menuDangXuat);
    }

    private void addMenu(MenuItem... menu) {
        for (int i = 0; i < menu.length; i++) {
            menus.add(menu[i]);
            ArrayList<MenuItem> subMenu = menu[i].getSubMenu();
            for (MenuItem m : subMenu) {
                addMenu(m);
            }
        }
        menus.revalidate();
    }

    public void MenuItemColor(JPanel panel) {
        panel.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                if (panel.getBackground().equals(defaultColor)) {
                    panel.setBackground(enterColor);
                }
            }

            public void mouseExited(MouseEvent e) {
                if (panel.getBackground().equals(enterColor)) {
                    panel.setBackground(defaultColor);
                }
            }

            public void mouseClicked(MouseEvent e) {
                reColor((JPanel) panel.getParent());
                panel.setBackground(selectedColor);
            }
        });
    }

    public void reColor(JPanel panel) {
        for (int i = 0; i < panel.getComponentCount(); i++) {
            if (panel.getComponent(i) instanceof JPanel) {
                panel.getComponent(i).setBackground(defaultColor);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelHeader = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lbltenNV = new javax.swing.JLabel();
        panelMenu = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        menus = new javax.swing.JPanel();
        panelBody = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelHeader.setBackground(new java.awt.Color(255, 51, 51));
        panelHeader.setPreferredSize(new java.awt.Dimension(561, 50));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/close.png"))); // NOI18N
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("OnePieceFashtion");

        lbltenNV.setForeground(new java.awt.Color(255, 255, 255));
        lbltenNV.setText("Tên nhân viên");

        javax.swing.GroupLayout panelHeaderLayout = new javax.swing.GroupLayout(panelHeader);
        panelHeader.setLayout(panelHeaderLayout);
        panelHeaderLayout.setHorizontalGroup(
            panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelHeaderLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1076, Short.MAX_VALUE)
                .addComponent(lbltenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelHeaderLayout.setVerticalGroup(
            panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelHeaderLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelHeaderLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelHeaderLayout.createSequentialGroup()
                        .addComponent(lbltenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        getContentPane().add(panelHeader, java.awt.BorderLayout.PAGE_START);

        panelMenu.setBackground(new java.awt.Color(115, 120, 230));
        panelMenu.setPreferredSize(new java.awt.Dimension(250, 384));

        jScrollPane1.setBorder(null);

        menus.setBackground(new java.awt.Color(255, 51, 51));
        menus.setLayout(new javax.swing.BoxLayout(menus, javax.swing.BoxLayout.Y_AXIS));
        jScrollPane1.setViewportView(menus);

        javax.swing.GroupLayout panelMenuLayout = new javax.swing.GroupLayout(panelMenu);
        panelMenu.setLayout(panelMenuLayout);
        panelMenuLayout.setHorizontalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
        );
        panelMenuLayout.setVerticalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 925, Short.MAX_VALUE)
        );

        getContentPane().add(panelMenu, java.awt.BorderLayout.LINE_START);

        panelBody.setBackground(new java.awt.Color(255, 255, 255));
        panelBody.setLayout(new java.awt.BorderLayout());
        getContentPane().add(panelBody, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(1515, 982));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jButton1MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(OnePieceFashtionView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OnePieceFashtionView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OnePieceFashtionView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OnePieceFashtionView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OnePieceFashtionView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbltenNV;
    private javax.swing.JPanel menus;
    private javax.swing.JPanel panelBody;
    private javax.swing.JPanel panelHeader;
    private javax.swing.JPanel panelMenu;
    // End of variables declaration//GEN-END:variables
}
