package fr.gsb.rv.dr.panneaux;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class PanneauRapports extends StackPane {
    
    public PanneauRapports(){
        super();

     Pane root = new Pane();
     Label label = new Label("Rapports");
 
      root.getChildren().add(label);
   
     }
}
