/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Control.Control;
import LogicaDeNegocio.Curso;
import LogicaDeNegocio.Grado;
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
     private Control control;
    private Stage stage;
    private Parent root;
    private Scene scene;
    private TableView<Curso> table;
    private ArrayList<TableColumn<Curso,String>> admiTableColumns;
    
    private CheckBox impordatoChckBx;
    private Button searchNombreBtn,searchTipoBtn,saveBtn,agregarBtn;
    private ComboBox<Grado> gradoCmbBx,tipoSearchCmbBx;
    private TextField searchNombreTxtFld,codigoTxtFld,nombreTxtFld,creditosTxtFld;
    public Vista() {
        admiTableColumns= new ArrayList<>();
        try {
            this.root = FXMLLoader.load(getClass().getResource("vista.fxml"));
        } catch (IOException ex) {
            //Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        }
    admiTableColumns = new ArrayList<>();
    impordatoChckBx = (CheckBox) root.lookup("#impordatoChckBx");
    searchNombreBtn= (Button) root.lookup("#searchNombreBtn");
    searchTipoBtn= (Button) root.lookup("#searchTipoBtn");
    saveBtn= (Button) root.lookup("#saveBtn");
    agregarBtn = (Button) root.lookup("#agregarBtn");
    gradoCmbBx =(ComboBox) root.lookup("#gradoCmbBx");
    tipoSearchCmbBx =(ComboBox) root.lookup("#tipoSearchCmbBx");
    searchNombreTxtFld =(TextField) root.lookup("#searchNombreTxtFld");
    codigoTxtFld=(TextField) root.lookup("#codigoTxtFld");
    nombreTxtFld    =(TextField) root.lookup("#nombreTxtFld");
    creditosTxtFld    =(TextField) root.lookup("#creditosTxtFld");
    table = (TableView<Curso>) root.lookup("#table");
    
        searchTipoBtn.setVisible(false);
        scene = new Scene(getRoot());
        stage = new Stage();
        stage.setScene(scene);
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
        searchNombreBtn.setOnAction(control);
        agregarBtn.setOnAction(control);
        saveBtn.setOnAction(control);
        table.setOnMouseClicked(control);
        this.control = control;
    }

    /**
     * @return the stage
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * @param stage the stage to set
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * @return the root
     */
    public Parent getRoot() {
        return root;
    }

    /**
     * @param root the root to set
     */
    public void setRoot(Parent root) {
        this.root = root;
    }

    /**
     * @return the scene
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * @param scene the scene to set
     */
    public void setScene(Scene scene) {
        this.scene = scene;
    }

    /**
     * @return the table
     */
    public TableView<Curso> getTable() {
        return table;
    }

    /**
     * @param table the table to set
     */
    public void setTable(TableView<Curso> table) {
        this.table = table;
    }

    /**
     * @return the admiTableColumns
     */
    public ArrayList<TableColumn<Curso,String>> getAdmiTableColumns() {
        return admiTableColumns;
    }

    /**
     * @param admiTableColumns the admiTableColumns to set
     */
    public void setAdmiTableColumns(ArrayList<TableColumn<Curso,String>> admiTableColumns) {
        this.admiTableColumns = admiTableColumns;
    }

    /**
     * @return the impordatoChckBx
     */
    public CheckBox getImpordatoChckBx() {
        return impordatoChckBx;
    }

    /**
     * @param impordatoChckBx the impordatoChckBx to set
     */
    public void setImpordatoChckBx(CheckBox impordatoChckBx) {
        this.impordatoChckBx = impordatoChckBx;
    }

    /**
     * @return the searchNombreBtn
     */
    public Button getSearchNombreBtn() {
        return searchNombreBtn;
    }

    /**
     * @param searchNombreBtn the searchNombreBtn to set
     */
    public void setSearchNombreBtn(Button searchNombreBtn) {
        this.searchNombreBtn = searchNombreBtn;
    }

    /**
     * @return the searchTipoBtn
     */
    public Button getSearchTipoBtn() {
        return searchTipoBtn;
    }

    /**
     * @param searchTipoBtn the searchTipoBtn to set
     */
    public void setSearchTipoBtn(Button searchTipoBtn) {
        this.searchTipoBtn = searchTipoBtn;
    }

    /**
     * @return the saveBtn
     */
    public Button getSaveBtn() {
        return saveBtn;
    }

    /**
     * @param saveBtn the saveBtn to set
     */
    public void setSaveBtn(Button saveBtn) {
        this.saveBtn = saveBtn;
    }

    /**
     * @return the agregarBtn
     */
    public Button getAgregarBtn() {
        return agregarBtn;
    }

    /**
     * @param agregarBtn the agregarBtn to set
     */
    public void setAgregarBtn(Button agregarBtn) {
        this.agregarBtn = agregarBtn;
    }

    /**
     * @return the gradoCmbBx
     */
    public ComboBox<Grado> getTipoCmbBx() {
        return gradoCmbBx;
    }

    /**
     * @param gradoCmbBx the gradoCmbBx to set
     */
    public void setTipoCmbBx(ComboBox<Grado> gradoCmbBx) {
        this.gradoCmbBx = gradoCmbBx;
    }

    /**
     * @return the tipoSearchCmbBx
     */
    public ComboBox<Grado> getTipoSearchCmbBx() {
        return tipoSearchCmbBx;
    }

    /**
     * @param tipoSearchCmbBx the tipoSearchCmbBx to set
     */
    public void setTipoSearchCmbBx(ComboBox<Grado> tipoSearchCmbBx) {
        this.tipoSearchCmbBx = tipoSearchCmbBx;
    }

    /**
     * @return the searchNombreTxtFld
     */
    public TextField getSearchNombreTxtFld() {
        return searchNombreTxtFld;
    }

    /**
     * @param searchNombreTxtFld the searchNombreTxtFld to set
     */
    public void setSearchNombreTxtFld(TextField searchNombreTxtFld) {
        this.searchNombreTxtFld = searchNombreTxtFld;
    }

    /**
     * @return the codigoTxtFld
     */
    public TextField getCodigoTxtFld() {
        return codigoTxtFld;
    }

    /**
     * @param codigoTxtFld the codigoTxtFld to set
     */
    public void setCodigoTxtFld(TextField codigoTxtFld) {
        this.codigoTxtFld = codigoTxtFld;
    }

    /**
     * @return the nombreTxtFld
     */
    public TextField getNombreTxtFld() {
        return nombreTxtFld;
    }

    /**
     * @param nombreTxtFld the nombreTxtFld to set
     */
    public void setNombreTxtFld(TextField nombreTxtFld) {
        this.nombreTxtFld = nombreTxtFld;
    }

    /**
     * @return the creditosTxtFld
     */
    public TextField getCreditosTxtFld() {
        return creditosTxtFld;
    }

    /**
     * @param creditosTxtFld the creditosTxtFld to set
     */
    public void setCreditosTxtFld(TextField creditosTxtFld) {
        this.creditosTxtFld = creditosTxtFld;
    }

    @Override
    public void update(Observable o, Object arg) {
        table.setItems(Dao.CursoDao.getInstance().readAll());
    }
    
}
