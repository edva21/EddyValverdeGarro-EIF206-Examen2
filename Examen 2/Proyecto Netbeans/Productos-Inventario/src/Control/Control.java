/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import LogicaDeNegocio.Producto;
import LogicaDeNegocio.Tipo;
import Modelo.Modelo;
import Vista.FXML.Vista;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;



/**
 *
 * @author edva5
 */
public class Control implements EventHandler{
    Modelo modelo;
    Vista vista;    

    public Control(Modelo modelo, Vista vista) {
        this.modelo = modelo;
        this.vista = vista;
        modelo.fixComboBox(vista.getTipoCmbBx());
        modelo.fixComboBox(vista.getTipoSearchCmbBx());        
        vista.getTipoSearchCmbBx().setItems(Dao.TipoDao.getInstance().Listar());
        vista.getTipoCmbBx().setItems(Dao.TipoDao.getInstance().Listar());
        modelo.addObserver(vista);
        modelo.setTableColumnsNames(vista.getAdmiTableColumns());
        vista.getStage().show();
        vista.getAdmiTableColumns().forEach(x->vista.getTable().getColumns().add(x));
        vista.getTable().setItems(Dao.ProductoDao.getInstance().readAll());
        vista.setControl(this);
        vista.getTipoSearchCmbBx().getSelectionModel().selectFirst();
        vista.getTipoCmbBx().getSelectionModel().selectFirst();
    }

    @Override
    public void handle(Event event) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if (event.getSource() instanceof Button) {
            
            switch(((Button)event.getSource()).getId()){
                case "searchNombreBtn":
                    vista.getTable().setItems(Dao.ProductoDao.getInstance().readByNombre(vista.getSearchNombreTxtFld().getText()));
                break;
                case "searchTipoBtn":
                    if (!vista.getTipoSearchCmbBx().getSelectionModel().isEmpty()||
                            vista.getTipoSearchCmbBx().getSelectionModel().getSelectedItem()!=null) {
                                vista.getTable().setItems(
                                        Dao.ProductoDao.getInstance().
                                                readbyTipo(
                                                        vista.getTipoSearchCmbBx().
                                                                getSelectionModel().getSelectedItem().getCodigo()
                                                )
                                );
                    }                    
                break;
                case "saveBtn":
                    if (vista.getTipoCmbBx().getSelectionModel()!=null) {
                       Producto aux= new Producto();
                    aux.setCodigoProducto(vista.getCodigoTxtFld().getText());
                    aux.setNombreProducto(vista.getNombreTxtFld().getText());
                    aux.setPrecioProducto(Integer.valueOf(vista.getPrecioTxtFld().getText()));
                    aux.setTipoProducto(vista.getTipoCmbBx().getSelectionModel().getSelectedItem().getCodigo());
                    aux.setImportadoProducto(vista.getImpordatoChckBx().isSelected());
                    modelo.salvar(aux); 
                    }                    
                break;                
            }
        }
    }

   
    
    
}
