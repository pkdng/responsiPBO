package hargaBarang;

import javax.swing.*;
import java.sql.*;

public class BarangModel {
    private Connection connection;
    private Statement statement;
    
    public BarangModel(){
        Koneksi konek = new Koneksi();
        connection = konek.Koneksi();
    }
    
    public void createBarang(String[] data){ //tambah barang
        try{
            String query = "INSERT INTO barang(nama,massa,harga)VALUES('"+data[0]+"','"+data[1]+"','"+data[2]+"')";
            statement = connection.createStatement();
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan!", "Hasil", JOptionPane.INFORMATION_MESSAGE);            
            statement.close();
            connection.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public String[][] readBarang(){ //lihat barang
        try{
            int row = 0;
            int numRows = numRows("barang");
            if(numRows==0){
                return null;
            }
            String[][] data = new String[numRows][4];
            statement = connection.createStatement();
            String query = "SELECT * FROM barang";
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()){
                data[row][0] = rs.getString("id");
                data[row][1] = rs.getString("nama");
                data[row][2] = rs.getString("massa");
                data[row][3] = rs.getString("harga");
                row++;
            }
            return data;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }
    
    public String[] readBarang(String selectedData){
        try{
            String[] data = new String[4];
            statement = connection.createStatement();
            String query = "SELECT * FROM barang WHERE id = '"+selectedData+"'";
            ResultSet rs = statement.executeQuery(query);
            while(rs.next()){
                data[0] = rs.getString("id");
                data[1] = rs.getString("nama");
                data[2] = rs.getString("massa");
                data[3] = rs.getString("harga");
            }
            return data;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }
    
    public void updateData(String[] data){
        try{
            String query = "UPDATE barang SET nama = '"+data[1]+"', massa = '"+data[2]+"', harga = '"+data[3]+"' WHERE id = '"+data[0]+"'";
            statement = connection.createStatement();
            statement.executeUpdate(query);    
            
            statement.close();
            connection.close();
            JOptionPane.showMessageDialog(null, "Update Berhasil");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void delete(String id){
        try {
            String query = " DELETE FROM barang WHERE id = '"+id+"'";
            statement = connection.createStatement();
            statement.executeUpdate(query);
            
            statement.close();
            connection.close();
            JOptionPane.showMessageDialog(null, "Hapus Berhasil");
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        BarangController b = new BarangController();
        b.readData();
    }

    private int numRows(String table) {
        int jmlData = 0;
        try{
            statement = connection.createStatement();
            String query = "SELECT * FROM " + table;
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                jmlData++;
            }
            return jmlData;

        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            return 0;
        }
    }
    
    public String getData(String column, String table, String where, String value){
        try{
            String data = new String();
            statement = connection.createStatement();
            String query = "SELECT "+column+" FROM "+table+" WHERE "+where+" = '" + value + "'";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                data = rs.getString(column);
            }
            return data;
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }
    
}
