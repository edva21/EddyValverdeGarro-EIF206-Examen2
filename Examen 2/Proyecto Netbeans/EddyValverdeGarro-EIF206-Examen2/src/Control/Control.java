/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import LogicaDeNegocio.Curso;
import LogicaDeNegocio.Grado;
import Modelo.Modelo;
import Vista.Vista;
import java.util.stream.Collectors;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author edva5
 */
public class Control implements EventHandler{
    private Vista vista;
    private Modelo modelo;

    public Control(Vista vista, Modelo modelo) {
        this.vista = vista;
        this.modelo = modelo;
        vista.setControl(this);
        modelo.addObserver(vista);
        modelo.setGrados(Dao.GradoDao.getInstance().readAll());
        modelo.fixComboBox(vista.getTipoCmbBx());
        modelo.setTableColumnsNames(vista.getAdmiTableColumns());
        vista.getAdmiTableColumns().forEach(x->vista.getTable().getColumns().add(x));
        vista.getTipoSearchCmbBx().setVisible(false);
        vista.getTipoCmbBx().getSelectionModel().selectFirst();
        vista.getStage().show();
        vista.getImpordatoChckBx().setVisible(false);
        vista.getTable().setItems(Dao.CursoDao.getInstance().readAll());
    }
    @Override
    public void handle(Event event) {
        if (event instanceof MouseEvent) {
            if (vista.getSaveBtn().getText().equals("Crear")) {
                vista.getCodigoTxtFld().setText("");
                vista.getCreditosTxtFld().setText("");
                vista.getNombreTxtFld().setText("");
            }
            else if (vista.getSaveBtn().getText().equals("Guardar")) {
                if (vista.getTable().getSelectionModel().getSelectedItem()!=null&&((MouseEvent)event).getClickCount()==1) {                
                    Curso c = vista.getTable().getSelectionModel().getSelectedItem();
                    vista.getCodigoTxtFld().setText(c.getId());
                    vista.getCreditosTxtFld().setText(Integer.toString(c.getCreditos()));
                    vista.getNombreTxtFld().setText(c.getNombre());
                    Grado g = modelo.getGrados().stream().filter(x->x.getCodigo().equals(c.getGrado())).collect(Collectors.toList()).get(0);
                    vista.getTipoCmbBx().getSelectionModel().select(g);
                }
            }            
        }
        else if (event.getSource() instanceof Button) {
            switch(((Button)event.getSource()).getText()){
                case "Buscar":
                    vista.getTable().setItems(Dao.CursoDao.getInstance().readByNombre(vista.getSearchNombreTxtFld().getText()));
                break;
                case "Guardar":
                       Curso cursoAux = new Curso();
                       cursoAux.setCreditos(Integer.valueOf(vista.getCreditosTxtFld().getText()));
                       cursoAux.setGrado(vista.getTipoCmbBx().getSelectionModel().getSelectedItem().getCodigo());
                       cursoAux.setId(vista.getCodigoTxtFld().getText());
                       cursoAux.setNombre(vista.getNombreTxtFld().getText());
                       Dao.CursoDao.getInstance().update(cursoAux);
                       modelo.notifyLaJugada();
                break;
                case "Crear":
                    if (vista.getTipoCmbBx().getSelectionModel()!=null) {                        
                       Curso curso = new Curso();
                       curso.setCreditos(Integer.valueOf(vista.getCreditosTxtFld().getText()));
                       curso.setGrado(vista.getTipoCmbBx().getSelectionModel().getSelectedItem().getCodigo());
                       curso.setId(vista.getCodigoTxtFld().getText());
                       curso.setNombre(vista.getNombreTxtFld().getText());
                       Dao.CursoDao.getInstance().create(curso);
                       modelo.notifyLaJugada();
                    }                    
                break;   
                case "Agregar":
                    vista.getAgregarBtn().setText("Editar");
                    vista.getSaveBtn().setText("Crear");  
                    vista.getCodigoTxtFld().setText("");
                    vista.getCreditosTxtFld().setText("");
                    vista.getNombreTxtFld().setText("");                    
                break; 
                case "Editar":
                    vista.getAgregarBtn().setText("Agregar");
                    vista.getSaveBtn().setText("Guardar");                    
                break; 
            }
        }
        
        
    }
}
