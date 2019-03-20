/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import LogicaDeNegocio.Producto;
import LogicaDeNegocio.Tipo;
import java.util.ArrayList;
import java.util.Observable;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author edva5
 */
public class Modelo extends Observable {

    public Modelo() {
    }
    public boolean salvar(Producto p){
        if (Dao.ProductoDao.getInstance().create(p)) 
        {
            setChanged();
            notifyObservers(p);
            return true;
        }
       else                    
             return false;
    }
    public void fixComboBox(ComboBox<Tipo> cmbBx){
        cmbBx.setCellFactory(new Callback<ListView<Tipo>, ListCell<Tipo>>() {
            @Override
            public ListCell<Tipo> call(ListView<Tipo> param) {
                return new ListCell<Tipo>(){
                    @Override
                    protected void updateItem(Tipo item, boolean empty) {
                        super.updateItem(item, empty); //To change body of generated methods, choose Tools | Templates.
                        if (!empty)
                        {
                            setText(item.getNombre());      
                            setGraphic(null);
                        }                  
                        else
                            setGraphic(null);
                    }
                    
                        
                };
            }
        });   
        
    }
   public void setTableColumnsNames(ArrayList<TableColumn<Producto,String>> columns){
        for (int i = 0; i < Producto.getClassNames().length; i++) {            
            columns.add(new TableColumn<Producto, String>(Producto.getClassNames()[i]));   //Add names to Columns' headers
            OrderTableViewInfo(columns.get(i),Producto.getClassNames()[i]);            
            if (Producto.getClassNames()[i].equals(Producto.ATTRIBUTE_TIPO)||
                    Producto.getClassNames()[i].equals(Producto.ATTRIBUTE_PORCENTAJE)||
                    Producto.getClassNames()[i].equals(Producto.ATTRIBUTE_IMPUESTO)||
                    Producto.getClassNames()[i].equals(Producto.ATTRIBUTE_PRECIOFINAL)) 
                columns.get(i).setSortable(false);
            
        }        
    }
    private void OrderTableViewInfo(TableColumn<Producto, String> tablecolumn,String columnName){
        switch(columnName){//Depending on the Header's Name, it assings a specific value of the object to a specific column
                case Producto.ATTRIBUTE_IMPORTADO:                        
                tablecolumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Producto, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<Producto, String> p) {
                        // p.getValue() returns the Person instance for a particular TableView row
                        if (p.getValue().getImportadoProducto())
                            return new ReadOnlyObjectWrapper<>("Si");                        
                        else
                            return new ReadOnlyObjectWrapper<>("No");                        
                    }
                });            
                    break;
                case Producto.ATTRIBUTE_NOMBRE:
                tablecolumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Producto, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<Producto, String> p) {
                        // p.getValue() returns the Person instance for a particular TableView row
                        return new ReadOnlyObjectWrapper<>(p.getValue().getNombreProducto());
                    }
                });
                    break;
                case Producto.ATTRIBUTE_PRECIO:
                    tablecolumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Producto, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<Producto, String> p) {
                        // p.getValue() returns the Person instance for a particular TableView row
                        return new ReadOnlyObjectWrapper<>(Integer.toString(p.getValue().getPrecioProducto()));
                    }
                });
                    break;                            
                    case Producto.ATTRIBUTE_TIPO:
                    tablecolumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Producto, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<Producto, String> p) {
                        // p.getValue() returns the Person instance for a particular TableView row
                        return new ReadOnlyObjectWrapper<>(Dao.TipoDao.getInstance().read(p.getValue().getTipoProducto()).getNombre());
                    }
                });
                    break;   
                    case Producto.ATTRIBUTE_PORCENTAJE:
                    tablecolumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Producto, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<Producto, String> p) {
                        // p.getValue() returns the Person instance for a particular TableView row
                        return new ReadOnlyObjectWrapper<>(Integer.toString(Dao.TipoDao.getInstance().read(p.getValue().getTipoProducto()).getPorcentaje())+" %");
                    }
                });
                    break;  
                    case Producto.ATTRIBUTE_IMPUESTO:
                    tablecolumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Producto, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<Producto, String> p) {
                        // p.getValue() returns the Person instance for a particular TableView row
                        if (p.getValue().getImportadoProducto())
                         return new ReadOnlyObjectWrapper<>(Integer.toString(p.getValue().getPrecioProducto()/2+(p.getValue().getPrecioProducto()/100)*Dao.TipoDao.getInstance().read(p.getValue().getTipoProducto()).getPorcentaje()));   
                        else
                         return new ReadOnlyObjectWrapper<>(Integer.toString((p.getValue().getPrecioProducto()/100)*Dao.TipoDao.getInstance().read(p.getValue().getTipoProducto()).getPorcentaje()));      
                    }
                });
                    break; 
                    case Producto.ATTRIBUTE_PRECIOFINAL:
                    tablecolumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Producto, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<Producto, String> p) {
                        // p.getValue() returns the Person instance for a particular TableView row
                        if (p.getValue().getImportadoProducto())
                         return new ReadOnlyObjectWrapper<>(Integer.toString(p.getValue().getPrecioProducto()+p.getValue().getPrecioProducto()/2+(p.getValue().getPrecioProducto()/100)*Dao.TipoDao.getInstance().read(p.getValue().getTipoProducto()).getPorcentaje()));   
                        else
                         return new ReadOnlyObjectWrapper<>(Integer.toString(p.getValue().getPrecioProducto()+(p.getValue().getPrecioProducto()/100)*Dao.TipoDao.getInstance().read(p.getValue().getTipoProducto()).getPorcentaje()));   
                    }
                });
                    break; 
            }
    }
}
