package hargaBarang;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormInputView extends JFrame implements ActionListener{
    private JLabel labelInput, labelNama, labelMassa, labelHarga;
    private JTextField fieldNama, fieldMassa, fieldHarga;
    private JButton btnSubmit, btnReset, btnKembali;
    
    public void openForm(){
        setTitle("Input Barang");
        setSize(365,230);
        
        labelInput  = new JLabel("Input Barang");
        labelNama   = new JLabel("Nama");
        labelMassa  = new JLabel("Massa(gr)");
        labelHarga  = new JLabel("Harga Satuan");
        fieldNama   = new JTextField();
        fieldMassa  = new JTextField();
        fieldHarga  = new JTextField();
        btnSubmit   = new JButton("Submit");
        btnReset    = new JButton("Reset");
        btnKembali  = new JButton("Kembali");
        
        setLayout(null);
        add(labelInput);
        add(labelNama);
        add(fieldNama);
        add(labelMassa);
        add(fieldMassa);
        add(labelHarga);
        add(fieldHarga);
        add(btnSubmit);
        add(btnReset);
        add(btnKembali);
        
        labelInput.setBounds(15, 15, 100, 20);
        labelNama.setBounds(15, 40, 100, 20);
        fieldNama.setBounds(115, 40, 220, 20);
        labelMassa.setBounds(15, 65, 100, 20);
        fieldMassa.setBounds(115, 65, 220, 20);
        labelHarga.setBounds(15, 90, 100, 20);
        fieldHarga.setBounds(115, 90, 220, 20);
        btnSubmit.setBounds(115, 125, 105, 20);
        btnSubmit.setBackground(Color.blue);
        btnSubmit.setForeground(Color.white);
        btnSubmit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSubmit.addActionListener(this);
        btnReset.setBounds(230, 125, 105, 20);
        btnReset.setBackground(Color.red);
        btnReset.setForeground(Color.white);
        btnReset.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnReset.addActionListener(this);
        btnKembali.setBounds(15, 160, 320, 20);
        btnKembali.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnKembali.addActionListener(this);
        
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnSubmit){
            if(fieldNama.getText().equals("")){
                Message("Nama harus diisi");
            }
            if(fieldMassa.getText().equals("")){
                Message("Massa harus diisi");
            }
            if(fieldHarga.getText().equals("")){
                Message("Harga harus diisi");
            }
            else{
                try{
                    int cekHarga = Integer.parseInt(fieldHarga.getText());
                    int cekMassa = Integer.parseInt(fieldMassa.getText());
                    
                    if(cekHarga < 0 || cekMassa < 0){
                        throw new MyException("Bilangan harus positif");
                    }
                                    
                    String[] data = {
                        fieldNama.getText(), fieldMassa.getText(), fieldHarga.getText()
                    };
                    BarangController b = new BarangController();
                    b.createData(data);
                }catch(NumberFormatException num){
                    Message("Massa dan Harga harus bilangan");
                } catch (MyException ex) {
                    Message(ex.getMessage());
                }
            }
        }
        else if(e.getSource() == btnReset){
            fieldNama.setText("");
            fieldMassa.setText("");
            fieldHarga.setText("");
        }
        else if(e.getSource() == btnKembali){
            dispose();
            MenuController m = new MenuController();
            m.menu();
        }
    }

    private void Message(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
