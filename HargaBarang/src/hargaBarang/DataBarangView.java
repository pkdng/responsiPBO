package hargaBarang;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DataBarangView extends JFrame implements ActionListener{
    private JTable tableData;
    private JButton btnKembali;
    private String selectedData;
    
    public DataBarangView(String[][] data){
        try{
            selectedData = data[0][0];
            final String[] tableTitle = {"Id","Nama","Massa","Harga"};
            
            setTitle("Data Barang");
            setSize(600,200);
            
            btnKembali= new JButton("Kembali");
            tableData = new JTable(data, tableTitle);
            
            JScrollPane s = new JScrollPane(tableData);
            this.getContentPane().add(BorderLayout.CENTER, s);
            
            this.getContentPane().add(BorderLayout.SOUTH, btnKembali);
            btnKembali.addActionListener(this);
            btnKembali.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            tableData.setCursor((Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)));
            tableData.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
                public void valueChanged(ListSelectionEvent event) {
                    dispose();
                    selectedData = tableData.getValueAt(tableData.getSelectedRow(), 0).toString();
                    BarangController bc = new BarangController();
                    bc.readData(selectedData);
                }
            });
            
            setLocationRelativeTo(null);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            
            setVisible(true);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnKembali){
            MenuController menu = new MenuController();
            menu.menu();
            dispose();
        }
    }
}
