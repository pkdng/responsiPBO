package hargaBarang;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DetailBarangView extends JFrame implements ActionListener{
    private JLabel labelNama, labelMassa, labelHarga, labelBanyak, valNama, valMassa, valHarga;
    private JTextField fieldBanyak;
    private JButton btnTotal, btnKembali, btnEdit, btnHapus;
    private String id;
    
    public void openDetail(String[] data){
        this.id = data[0];
        setTitle(data[1]);
        setSize(325,300);
        
        labelNama   = new JLabel("Nama :");
        labelMassa  = new JLabel("Massa(gr) :");
        labelHarga  = new JLabel("Harga Satuan :");
        labelBanyak = new JLabel("Banyak :");
        fieldBanyak = new JTextField();
        
        valNama = new JLabel(data[1]);
        valMassa= new JLabel(data[2]);
        valHarga= new JLabel(data[3]);
        
        btnTotal    = new JButton("Total Harga");
        btnKembali  = new JButton("Kembali");
        btnEdit     = new JButton("Edit");
        btnHapus    = new JButton("Hapus");
        
        setLayout(null);
        add(labelNama);
        add(labelMassa);
        add(labelHarga);
        add(labelBanyak);
        add(fieldBanyak);
        add(valNama);
        add(valMassa);
        add(valHarga);
        add(btnTotal);
        add(btnKembali);
        add(btnEdit);
        add(btnHapus);
        
        labelNama.setBounds(15, 15, 100, 20);
        valNama.setBounds(115, 15, 100, 20);
        labelMassa.setBounds(15, 40, 100, 20);
        valMassa.setBounds(115, 40, 100, 20);
        labelHarga.setBounds(15, 65, 100, 20);
        valHarga.setBounds(115, 65, 100, 20);
        labelBanyak.setBounds(15, 90, 100, 20);
        fieldBanyak.setBounds(115, 90, 180, 20);
        btnTotal.setBounds(105, 150, 100, 20);
        btnTotal.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnTotal.addActionListener(this);
        btnKembali.setBounds(15, 220, 90, 20);
        btnKembali.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnKembali.addActionListener(this);
        btnEdit.setBounds(110, 220, 90, 20);
        btnEdit.setBackground(Color.blue);
        btnEdit.setForeground(Color.white);
        btnEdit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnEdit.addActionListener(this);
        btnHapus.setBounds(205, 220, 90, 20);
        btnHapus.setBackground(Color.red);
        btnHapus.setForeground(Color.white);
        btnHapus.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnHapus.addActionListener(this);
        
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnTotal){
            if(fieldBanyak.getText().equals("")){
                Message("Banyak barang harus diisi");
            }
            else{
                try{
                    int bb = Integer.parseInt(fieldBanyak.getText());
                    
                    if(bb < 0){
                        throw new MyException("Bilangan harus positif");
                    }
                                    
                    BarangController bt = new BarangController();
                    bt.totalHarga(id, fieldBanyak.getText());
                    dispose();
                }catch(NumberFormatException num){
                    Message("Banyak barang harus diisi angka");
                } catch (MyException ex) {
                    Message(ex.getMessage());
                }
            }
        }
        else if(e.getSource() == btnKembali){
            BarangController b = new BarangController();
            b.readData();
            dispose();
        }
        else if(e.getSource() == btnEdit){
            BarangController be = new BarangController();
            be.updateData(id);
            dispose();
        }
        else if(e.getSource() == btnHapus){
            OptionDialog();
        }
    }
    
    private void OptionDialog() {
        int jawab = JOptionPane.showOptionDialog(this, 
                        "Yakin Ingin Menghapus?", 
                        "Hapus", 
                        JOptionPane.YES_NO_OPTION, 
                        JOptionPane.QUESTION_MESSAGE, null, null, null);

        if(jawab == JOptionPane.YES_OPTION){
            BarangController db = new BarangController();
            db.delete(id);
            dispose();
        }
    }
    
    private void Message(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}