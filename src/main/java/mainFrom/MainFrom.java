/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package mainFrom;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Huu Vinh
 */
public final class MainFrom extends javax.swing.JFrame {

    private ArrayList<QLSV> list = new ArrayList<>();
    private DefaultTableModel tblmModel;
    String TenAnh = "";

    /**
     * Creates new form MainFrom
     */
    public MainFrom() {
        initComponents();
        setLocationRelativeTo(null);
        ban();
        data();
        fillTable();
    }

    String ma = "(PC)\\d{5,8}";
    String EMAIL = "^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$";

    public boolean kiemtra() {
        if (txtMaSV.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Mã SV không được để trống");
            txtMaSV.requestFocus();
            return false;
        }
        if (!txtMaSV.getText().matches(ma)) {
            JOptionPane.showMessageDialog(this, "Mã SV không đúng định dạng VD: PC01234");
            txtMaSV.requestFocus();
            return false;
        }
        if (!txtEmail.getText().matches(EMAIL)) {
            JOptionPane.showMessageDialog(this, "Email không đúng định dạng \nVD: vinhnhpc05037@gmail.com");
            txtEmail.requestFocus();
            return false;
        }
        int i = 0;
        for (QLSV qlsv : list) {
            if (qlsv.getMaSV().equals(txtMaSV.getText())) {
                i = 1;
            }
            if (i==1) {
                JOptionPane.showMessageDialog(this, "Mã SV bị trùng !! ");
                return false;
            }
        }
        return true;
    }

    public void Hienthianh() {
        JFileChooser jfc = new JFileChooser();
        jfc.showOpenDialog(null);
        File file = jfc.getSelectedFile();
        try {
            if (file == null) {
                return;
            }
            Image img = ImageIO.read(file);
            TenAnh = file.getName();
            txtAnh.setText(null);
            txtAnh.setIcon(new ImageIcon(img.getScaledInstance(170, 150, WIDTH)));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void ban() {
        tblmModel = new DefaultTableModel();
        String[] tbl = new String[]{"MaSV", "Ho tên", "Email", "Số ĐT", "Giới tính", "Địa chỉ", "Hình"};
        tblmModel.setColumnIdentifiers(tbl);
        tableQLSV.setModel(tblmModel);
    }

    public void data() {
        list.add(new QLSV("PC05037", "Nguyễn Hữu Vinh", "vinh05037@gmail.com", "0877579840", "Nam", "Cà Mau", "back1.png"));
        list.add(new QLSV("PC01292", "Dương Kiều Diểm", "diem01292@gmail.com", "0877819291", "Nữ", "Cà Mau", "back2.png"));
        list.add(new QLSV("PC45632", "Trần Nhật Linh", "linh45632@gmail.com", "0877342275", "Nam", "Bạc Liêu", "new.png"));
        list.add(new QLSV("PC04322", "Huỳnh Thảo Vy", "vy04322@gmail.com", "08778229174", "Nữ", "Cần Thơ", "next1.png"));
        list.add(new QLSV("PC05542", "Nguyễn Văn Nghĩa", "nghia05542@gmail.com", "0877298739", "Nam", "Hậu Giang", "save.png"));
    }

    public void fillTable() {
        tblmModel.setRowCount(0);
        for (QLSV qlsv : list) {
            Object[] tbl = new Object[]{qlsv.getMaSV(), qlsv.getHoTen(), qlsv.getEmail(), qlsv.getSdt(), qlsv.getGioiTinh(), qlsv.getDiaChi(), qlsv.getHinh()};
            tblmModel.addRow(tbl);
        }
    }

    public void HienThiLen() {
        int chon = tableQLSV.getSelectedRow();
        String name = (String) tblmModel.getValueAt(chon, 0);
        for (QLSV qlsv : list) {
            if (qlsv.getMaSV().equals(name)) {
                txtMaSV.setText(qlsv.getMaSV());
                txtHoTen.setText(qlsv.getHoTen());
                txtEmail.setText(qlsv.getEmail());
                txtSdt.setText(qlsv.getSdt());
                if (qlsv.getGioiTinh().equalsIgnoreCase("Nam")) {
                    rdoNam.setSelected(true);
                } else {
                    rdoNu.setSelected(true);
                }
                txtDiaChi.setText(qlsv.getDiaChi());
                if (qlsv.getHinh().equals("Chưa có ảnh")) {
                    txtAnh.setText("Hình ảnh");
                    txtAnh.setIcon(null);
                } else {
                    txtAnh.setText(null);
                    ImageIcon imgic = new ImageIcon(getClass().getResource("/icon/" + qlsv.getHinh()));
                    Image img = imgic.getImage();
                    txtAnh.setIcon(new ImageIcon(img.getScaledInstance(170, 150, WIDTH)));
                }

            }

        }
    }

    public void New() {
        txtMaSV.setText(null);
        txtHoTen.setText(null);
        txtEmail.setText(null);
        txtSdt.setText(null);
        GroubGT.clearSelection();
        txtDiaChi.setText(null);
        txtAnh.setIcon(null);
    }

     public void save() {
        QLSV qlsv = new QLSV();
        if (kiemtra()) {
            qlsv.setMaSV(txtMaSV.getText());
            qlsv.setHoTen(txtHoTen.getText());
            qlsv.setEmail(txtEmail.getText());
            qlsv.setSdt(txtSdt.getText());
            if (rdoNam.isSelected()) {
                qlsv.setGioiTinh("Nam");
            } else {
                qlsv.setGioiTinh("nữ");
            }
            qlsv.setDiaChi(txtDiaChi.getText());
            if (TenAnh == "") {
                qlsv.setHinh("Chưa có ảnh");
            } else {
                qlsv.setHinh(TenAnh);
            }
            list.add(qlsv);
            fillTable();
            JOptionPane.showMessageDialog(this, "Thêm sinh viên thành công @@ ");
            New();
        }
    }

    public void detele() {
        int chon = tableQLSV.getSelectedRow();
        if (chon < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sinh viên trong danh sách");
            return;
        }
        for (QLSV qlsv : list) {
            if (qlsv.getMaSV().equals(txtMaSV.getText())) {
                int chonsv = JOptionPane.showConfirmDialog(this, "Bạn muốn xóa không", "Bạn muốn xóa SV không", JOptionPane.YES_NO_OPTION);
                if (chonsv==JOptionPane.YES_OPTION) {
                    list.remove(qlsv);
                    fillTable();
                    JOptionPane.showMessageDialog(this, "Xóa thành công @@ ");
                    New();
                    return;
                    
                }
            }
        }
    }
    
    public void update() {
        int chon = tableQLSV.getSelectedRow();
        if (chon < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sinh viên trong danh sách");
            return;
        }
        QLSV qlsv = list.get(chon);
        
        qlsv.setMaSV(txtMaSV.getText());
            qlsv.setHoTen(txtHoTen.getText());
            qlsv.setEmail(txtEmail.getText());
            qlsv.setSdt(txtSdt.getText());
            if (rdoNam.isSelected()) {
                qlsv.setGioiTinh("Nam");
            } else {
                qlsv.setGioiTinh("nữ");
            }
            qlsv.setDiaChi(txtDiaChi.getText());
            if (TenAnh == "") {
                qlsv.setHinh(qlsv.getHinh());
            } else {
                qlsv.setHinh(TenAnh);
            }
            fillTable();
            JOptionPane.showMessageDialog(this, "Cập nhật sinh viên thành công @@ ");
            New();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        GroubGT = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtMaSV = new javax.swing.JTextField();
        txtHoTen = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtSdt = new javax.swing.JTextField();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDiaChi = new javax.swing.JTextArea();
        btnNew = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnDetele = new javax.swing.JButton();
        btnUpDate = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableQLSV = new javax.swing.JTable();
        txtAnh = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 255));
        jLabel1.setText("Quản Lý Sinh Viên");

        jLabel3.setText("MaSV:");

        jLabel4.setText("Họ tên:");

        jLabel5.setText("Email:");

        jLabel6.setText("Số ĐT:");

        jLabel7.setText("Giới tính:");

        jLabel2.setText("Địa chỉ:");

        GroubGT.add(rdoNam);
        rdoNam.setText("Nam");

        GroubGT.add(rdoNu);
        rdoNu.setText("Nữ");

        txtDiaChi.setColumns(20);
        txtDiaChi.setRows(5);
        jScrollPane1.setViewportView(txtDiaChi);

        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/new.png"))); // NOI18N
        btnNew.setText("New");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/save.png"))); // NOI18N
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnDetele.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/detele.png"))); // NOI18N
        btnDetele.setText("Delete");
        btnDetele.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeteleActionPerformed(evt);
            }
        });

        btnUpDate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/update.png"))); // NOI18N
        btnUpDate.setText("Update");
        btnUpDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpDateActionPerformed(evt);
            }
        });

        tableQLSV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableQLSV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableQLSVMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableQLSV);

        txtAnh.setText("Ảnh");
        txtAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtAnhMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2)))
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnDetele, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnNew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnUpDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtMaSV, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGap(28, 28, 28)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtHoTen, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                                        .addComponent(txtEmail)))
                                .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(160, 160, 160)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdoNam)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdoNu)))
                        .addGap(82, 82, 82)
                        .addComponent(txtAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 597, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaSV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(rdoNam)
                                .addComponent(rdoNu)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnNew)
                            .addComponent(btnSave))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnDetele)
                            .addComponent(btnUpDate)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtAnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAnhMouseClicked
        // TODO add your handling code here:
        Hienthianh();
    }//GEN-LAST:event_txtAnhMouseClicked

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:

    }//GEN-LAST:event_formWindowActivated

    private void tableQLSVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableQLSVMouseClicked
        // TODO add your handling code here:
        HienThiLen();
    }//GEN-LAST:event_tableQLSVMouseClicked

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        New();
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        save();
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnDeteleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeteleActionPerformed
        // TODO add your handling code here:
        detele();
    }//GEN-LAST:event_btnDeteleActionPerformed

    private void btnUpDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpDateActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_btnUpDateActionPerformed

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
            java.util.logging.Logger.getLogger(MainFrom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrom().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup GroubGT;
    private javax.swing.JButton btnDetele;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTable tableQLSV;
    private javax.swing.JLabel txtAnh;
    private javax.swing.JTextArea txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMaSV;
    private javax.swing.JTextField txtSdt;
    // End of variables declaration//GEN-END:variables

}
