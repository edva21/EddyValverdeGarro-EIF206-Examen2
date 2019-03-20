/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productos.inventario;

import Control.Control;
import Modelo.Modelo;
import Vista.FXML.Vista;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author edva5
 */
public class NewFXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Vista v = new Vista();
        Modelo m = new Modelo();
        Control c = new Control(m, v);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
