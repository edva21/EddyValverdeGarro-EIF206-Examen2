/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.FXML;

import Control.Control;
import LogicaDeNegocio.Producto;
import LogicaDeNegocio.Tipo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author edva5
 */
public class Vista implements Observer{

    /**
     * @return the impordatoChckBx
     */
    public CheckBox getImpordatoChckBx() {
        return impordatoChckBx;
    }

    private Control control;
    private Stage stage;
    private Parent root;
    private Scene scene;
    private TableView<Producto> table;
    private ArrayList<TableColumn<Producto,String>> admiTableColumns;
    
    private CheckBox impordatoChckBx;
    private Button searchNombreBtn,searchTipoBtn,saveBtn,agregarBtn;
    private ComboBox<Tipo> tipoCmbBx,tipoSearchCmbBx;
    private TextField searchNombreTxtFld,codigoTxtFld,nombreTxtFld,precioTxtFld;
    public Vista() {
        admiTableColumns= new ArrayList<>();
        try {
            this.root = FXMLLoader.load(getClass().getResource("vista.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        }
    admiTableColumns = new ArrayList<>();
    impordatoChckBx = (CheckBox) root.lookup("#impordatoChckBx");
    searchNombreBtn= (Button) root.lookup("#searchNombreBtn");
    searchTipoBtn= (Button) root.lookup("#searchTipoBtn");
    saveBtn= (Button) root.lookup("#saveBtn");
    agregarBtn = (Button) root.lookup("#agregarBtn");
    tipoCmbBx =(ComboBox) root.lookup("#tipoCmbBx");
    tipoSearchCmbBx =(ComboBox) root.lookup("#tipoSearchCmbBx");
    searchNombreTxtFld =(TextField) root.lookup("#searchNombreTxtFld");
    codigoTxtFld=(TextField) root.lookup("#codigoTxtFld");
    nombreTxtFld    =(TextField) root.lookup("#nombreTxtFld");
    precioTxtFld    =(TextField) root.lookup("#precioTxtFld");
    table = (TableView<Producto>) root.lookup("#table");
    
        
        scene = new Scene(getRoot());
        stage = new Stage();
        stage.setScene(scene);
    }
    
    /**
     * @return the stage
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * @return the root
     */
    public Parent getRoot() {
        return root;
    }

    /**
     * @return the scene
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * @return the table
     */
    public TableView<Producto> getTable() {
        return table;
    }

    /**
     * @return the admiTableColumns
     */
    public ArrayList<TableColumn<Producto,String>> getAdmiTableColumns() {
        return admiTableColumns;
    }

    /**
     * @return the searchNombreBtn
     */
    public Button getSearchNombreBtn() {
        return searchNombreBtn;
    }

    /**
     * @return the searchTipoBtn
     */
    public Button getSearchTipoBtn() {
        return searchTipoBtn;
    }

    /**
     * @return the saveBtn
     */
    public Button getSaveBtn() {
        return saveBtn;
    }

    /**
     * @return the tipoCmbBx
     */
    public ComboBox<Tipo> getTipoCmbBx() {
        return tipoCmbBx;
    }

    /**
     * @return the tipoSearchCmbBx
     */
    public ComboBox<Tipo> getTipoSearchCmbBx() {
        return tipoSearchCmbBx;
    }

    /**
     * @return the searchNombreTxtFld
     */
    public TextField getSearchNombreTxtFld() {
        return searchNombreTxtFld;
    }

    /**
     * @return the codigoTxtFld
     */
    public TextField getCodigoTxtFld() {
        return codigoTxtFld;
    }

    /**
     * @return the nombreTxtFld
     */
    public TextField getNombreTxtFld() {
        return nombreTxtFld;
    }

    /**
     * @return the precioTxtFld
     */
    public TextField getPrecioTxtFld() {
        return precioTxtFld;
    }
    
    /**
     * @return the control
     */
    public Control getControl() {
        return control;
    }

    /**
     * @param control the control to set
     */
    public void setControl(Control control) {
        this.control = control;
        searchNombreBtn.setOnAction(control);
        searchTipoBtn.setOnAction(control);
        saveBtn.setOnAction(control);
    }
    @Override
    public void update(Observable o, Object arg) {
        //table.getItems().add((Producto)arg);
        table.setItems(Dao.ProductoDao.getInstance().readAll());
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
