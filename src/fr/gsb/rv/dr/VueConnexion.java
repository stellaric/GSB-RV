package fr.gsb.rv.dr;



import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.util.Pair;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;


public class VueConnexion extends Dialog<Pair <String,String>> {
 

public VueConnexion() {
    super();

 this.setTitle("Authentification");
 this.setHeaderText("Saisir vos données de connexion.");

    Label matricule = new Label("Matricule : ");
    TextField inputMatricule = new TextField();

    Label mdp = new Label("Mot de passe : ");
    PasswordField inputMdp = new PasswordField();

 GridPane gridPane = new GridPane();
gridPane.add(matricule,0,1);
gridPane.add(inputMatricule,1,1);
gridPane.add(mdp,0,2);
gridPane.add(inputMdp,1,2);
this.getDialogPane().setContent(gridPane);

ButtonType btnSeConnecter = new ButtonType("Se Connecter" , ButtonBar.ButtonData.OK_DONE);
ButtonType btnAnnuler = new ButtonType("Annuler" , ButtonBar.ButtonData.CANCEL_CLOSE);

 this.getDialogPane().getButtonTypes().addAll(btnSeConnecter , btnAnnuler);

this.setResultConverter(new Callback<ButtonType, Pair<String, String>>() {
        @Override
        public Pair<String, String> call(ButtonType buttonType) {
            if(buttonType == btnSeConnecter){
                return new Pair<String,String>(matricule.getText(),mdp.getText());
            }
            return null;
        }
    });
}
}

