package fr.gsb.rv.dr.panneaux;

import javafx.scene.control.Label;

import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;


public class PanneauAccueil extends StackPane {
 
    public PanneauAccueil(){
       super();
    Pane root = new Pane();
    Label label = new Label("Accueil");

     root.getChildren().add(label);
     this.getChildren().add(root);
    }
   
}
