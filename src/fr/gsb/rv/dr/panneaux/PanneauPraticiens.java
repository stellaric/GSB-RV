package fr.gsb.rv.dr.panneaux;


import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class PanneauPraticiens extends StackPane {
    
    public PanneauPraticiens(){
        super();

     Pane root = new Pane();
     Label label = new Label("Practiciens");
 
      root.getChildren().add(label);
   
     }
    
}
