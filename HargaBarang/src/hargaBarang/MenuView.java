package hargaBarang;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuView extends JFrame implements ActionListener{
    private JLabel labelMenu;
    private JButton btnTambah, btnLihat;
    
    public MenuView(){
        setTitle("Main Menu");
        labelMenu = new JLabel("Main Menu");
        labelMenu.setHorizontalAlignment(SwingConstants.CENTER);
        btnTambah = new JButton("Tambah Barang");
        btnTambah.addActionListener(this);
        btnLihat = new JButton("Lihat Barang");
        btnLihat.addActionListener(this);
        
        setLayout(new GridLayout(3,1));
        add(labelMenu);
        add(btnTambah);
        add(btnLihat);
        
        btnTambah.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnLihat.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        pack();        
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnTambah){
            BarangController barang = new BarangController();
            barang.createData();
            dispose();
        }
        else if(e.getSource() == btnLihat){
            BarangController bc = new BarangController();
            bc.readData();
            dispose();
        }
    }
}
