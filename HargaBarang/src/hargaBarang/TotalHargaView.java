package hargaBarang;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TotalHargaView extends JFrame implements ActionListener{
    private JLabel labelNama, labelMassa, labelHarga, labelTotal, valNama, valMassa, valHarga, valTotal;
    private JButton btnKembali;
    private String id, total;
    
    public void openTotal(String[] data, String h){
        id = data[0];
        double harga = Double.parseDouble(data[3]);
        double banyak = Double.parseDouble(h);
        totalBarang(harga, banyak);
        
        setTitle(data[1]);
        setSize(325,300);
        
        labelNama   = new JLabel("Nama :");
        labelMassa  = new JLabel("Massa(gr) :");
        labelHarga  = new JLabel("Harga Satuan :");
        labelTotal  = new JLabel("Total Harga :");
        
        valNama = new JLabel(data[1]);
        valMassa= new JLabel(data[2]);
        valHarga= new JLabel(data[3]);
        valTotal= new JLabel(total);
        
        btnKembali  = new JButton("Kembali");
        
        setLayout(null);
        add(labelNama);
        add(labelMassa);
        add(labelHarga);
        add(labelTotal);
        add(valNama);
        add(valMassa);
        add(valHarga);
        add(valTotal);
        add(btnKembali);
        
        labelNama.setBounds(15, 15, 100, 20);
        valNama.setBounds(115, 15, 100, 20);
        labelMassa.setBounds(15, 40, 100, 20);
        valMassa.setBounds(115, 40, 100, 20);
        labelHarga.setBounds(15, 65, 100, 20);
        valHarga.setBounds(115, 65, 100, 20);
        labelTotal.setBounds(15, 90, 100, 20);
        valTotal.setBounds(115, 90, 180, 20);
        btnKembali.setBounds(15, 220, 90, 20);
        btnKembali.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnKembali.addActionListener(this);
        
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnKembali){
            BarangController b = new BarangController();
            b.readData(id);
            dispose();
        }
    }
    
    private void totalBarang(double harga, double banyak){
        double t;
                
        if(banyak == 12){
            t = 0.95*harga*banyak;
        }else if(banyak == 20){
            t = 0.9*harga*banyak;
        }else if(banyak == 144){
            t = 0.75*harga*banyak;
        }else
            t = harga*banyak;
        
        total = String.valueOf(t);
    }
}
