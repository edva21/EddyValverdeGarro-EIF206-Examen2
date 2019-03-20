/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eddyvalverdegarro.eif206.examen2;

import Control.Control;
import Modelo.Modelo;
import Vista.Vista;
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
public class examen2 extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Vista vista = new Vista();
        Modelo modelo = new Modelo();
        Control control = new Control(vista, modelo);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
