package hargaBarang;

public class BarangController {
    public void createData(){ //buka tampilan form input barang
        FormInputView formView = new FormInputView();
        formView.openForm();
    }
    
    public void createData(String[] data){ //tambah barang
        BarangModel bm = new BarangModel();
        bm.createBarang(data);
    }
    
    public void readData(){ //baca semua data
        BarangModel bmRead = new BarangModel();
        String[][] data = bmRead.readBarang();
        if(data == null)
            new MenuView();
        else
            new DataBarangView(data);
    }
    
    public void readData(String selectedData){ //baca data yg dipilih
        BarangModel bmRead = new BarangModel();
        DetailBarangView detail = new DetailBarangView();
        detail.openDetail(bmRead.readBarang(selectedData));
    }
    
    public void updateData(String id){ //edit data
        BarangModel be = new BarangModel();
        String selectedData = be.getData("id", "barang", "id", id);
        FormEditView edit = new FormEditView();
        edit.openForm(be.readBarang(selectedData));
    }
    
    public void updateData(String[] data){
        BarangModel bu = new BarangModel();
        bu.updateData(data);
    }
    
    public void delete(String id){
        BarangModel db = new BarangModel();
        db.delete(id);
    }
    
    public void totalHarga(String id, String banyak){
        BarangModel tb = new BarangModel();
        String selectedData = tb.getData("id", "barang", "id", id);
        TotalHargaView total = new TotalHargaView();
        total.openTotal(tb.readBarang(selectedData), banyak);
    }
}
