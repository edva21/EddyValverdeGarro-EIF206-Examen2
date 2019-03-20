package Modelo;

import LogicaDeNegocio.Curso;
import LogicaDeNegocio.Grado;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.stream.Collectors;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author edva5
 */
public class Modelo extends Observable{


       
    /**
     * @param grados the grados to set
     */    
    public void setGrados(ObservableList<Grado> grados) {
        this.grados = grados;
    }
    private ObservableList<Grado> grados;
     public void fixComboBox(ComboBox<Grado> cmbBx){
        cmbBx.setCellFactory(new Callback<ListView<Grado>, ListCell<Grado>>() {
            @Override
            public ListCell<Grado> call(ListView<Grado> param) {
                return new ListCell<Grado>(){
                    @Override
                    protected void updateItem(Grado item, boolean empty) {
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
        cmbBx.getItems().addAll(getGrados());
    }
   public void setTableColumnsNames(ArrayList<TableColumn<Curso,String>> columns){
        for (int i = 0; i < Curso.getClassNames().length; i++) {            
            columns.add(new TableColumn<Curso, String>(Curso.getClassNames()[i]));   //Add names to Columns' headers
            OrderTableViewInfo(columns.get(i),Curso.getClassNames()[i]);                        
        }        
    }
   public void notifyLaJugada(){
       setChanged();
       notifyObservers();
   }
    private void OrderTableViewInfo(TableColumn<Curso, String> tablecolumn,String columnName){
        switch(columnName){//Depending on the Header's Name, it assings a specific value of the object to a specific column
                case Curso.ATTRIBUTE_ID:                        
                tablecolumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Curso, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<Curso, String> p) {
                        // p.getValue() returns the Person instance for a particular TableView row
                        return new ReadOnlyObjectWrapper<>(p.getValue().getId());                        
                    }
                });            
                    break;
                case Curso.ATTRIBUTE_NOMBRE:
                tablecolumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Curso, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<Curso, String> p) {
                        // p.getValue() returns the Person instance for a particular TableView row
                        return new ReadOnlyObjectWrapper<>(p.getValue().getNombre());
                    }
                });
                    break;
                case Curso.ATTRIBUTE_CREDITOS:
                    tablecolumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Curso, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<Curso, String> p) {
                        // p.getValue() returns the Person instance for a particular TableView row
                        return new ReadOnlyObjectWrapper<>(Integer.toString(p.getValue().getCreditos()));
                    }
                });
                    break;                            
                    case Curso.ATTRIBUTE_GRADO:
                    tablecolumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Curso, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<Curso, String> p) {
                        // p.getValue() returns the Person instance for a particular TableView row
                        if (getGrados().stream().anyMatch(x->x.getCodigo().equals(p.getValue().getGrado()))) {
                            List<Grado> aux = getGrados().stream().filter(x->x.getCodigo().equals(p.getValue().getGrado())).collect(Collectors.toList());
                            return new ReadOnlyObjectWrapper<>(aux.get(0).getNombre());
                        }
                        else
                            return new ReadOnlyObjectWrapper<>("Error");
                        
                    }
                });
                    break;   
                    case Curso.ATTRIBUTE_PRECIO:
                    tablecolumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Curso, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<Curso, String> p) {
                        // p.getValue() returns the Person instance for a particular TableView row
                        if (getGrados().stream().anyMatch(x->x.getCodigo().equals(p.getValue().getGrado()))) {
                            List<Grado> aux = getGrados().stream().filter(x->x.getCodigo().equals(p.getValue().getGrado())).collect(Collectors.toList());
                            Grado gradoAux = aux.get(0);
                            return new ReadOnlyObjectWrapper<>(Integer.toString(gradoAux.getPrecio()));
                        }
                        else
                            return new ReadOnlyObjectWrapper<>("Error");
                    }
                });
                    break;  
                    case Curso.ATTRIBUTE_COSTO:
                    tablecolumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Curso, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<Curso, String> p) {
                        // p.getValue() returns the Person instance for a particular TableView row
                        if (getGrados().stream().anyMatch(x->x.getCodigo().equals(p.getValue().getGrado()))) {
                            List<Grado> aux = getGrados().stream().filter(x->x.getCodigo().equals(p.getValue().getGrado())).collect(Collectors.toList());
                            Grado gradoAux = aux.get(0);
                            int auxCosto=0;
                            for (int i = 0; i < p.getValue().getCreditos(); i++) {
                                if (i>=3)
                                    auxCosto = auxCosto +((gradoAux.getPrecio())/2);                                
                                else
                                    auxCosto = auxCosto +gradoAux.getPrecio();                                
                            }
                            return new ReadOnlyObjectWrapper<>(Integer.toString(auxCosto));
                        }
                        else
                            return new ReadOnlyObjectWrapper<>("Error");
                    }
                });
                    break;                    
            }
    }
        /**
     * @return the grados
     */
    public ObservableList<Grado> getGrados() {
        return grados;
    }

    public Modelo() {
        grados = FXCollections.observableArrayList(new ArrayList<Grado>());
    }

}
