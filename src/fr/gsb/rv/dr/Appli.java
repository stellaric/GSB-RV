
package fr.gsb.rv.dr;
import java.util.Optional;
import fr.gsb.rv.dr.VueConnexion;
import fr.gsb.rv.dr.entites.Visiteur;
import fr.gsb.rv.dr.modeles.ModeleGsbRv;
import fr.gsb.rv.dr.panneaux.PanneauAccueil;
import fr.gsb.rv.dr.panneaux.PanneauPraticiens;
import fr.gsb.rv.dr.panneaux.PanneauRapports;
import fr.gsb.rv.dr.technique.ConnexionException;
import fr.gsb.rv.dr.technique.Session;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Pair;



public class Appli extends Application {
    @Override
    
    public void start(Stage primaryStage){
        

       
    // Création barre menu
    MenuBar barreMenus = new MenuBar();
    //création du  menu
    Menu menuFichier =new Menu("Fichier");
    MenuItem itemSeConnecter = new MenuItem("Se connecter");
    MenuItem itemSeDeconnecter= new MenuItem("Se déconnecter");
    MenuItem itemQuitter = new MenuItem("Quitter");
    menuFichier.getItems().addAll(itemSeConnecter ,itemSeDeconnecter,itemQuitter);
    menuFichier.setDisable(false);

    Menu menuRapport = new Menu ("Rapports");
    MenuItem itemConsulter =new MenuItem("Consulter");
    menuRapport.getItems().addAll(itemConsulter);
    menuRapport.setDisable(true);

    Menu menuPraticien = new Menu ("Praticiens");
    MenuItem itemHesitant = new MenuItem ("Hésitant");
    menuPraticien.getItems().addAll(itemHesitant);
    menuPraticien.setDisable(true);
    

    barreMenus.getMenus().addAll( menuFichier,menuPraticien,menuRapport);
    itemSeDeconnecter.setDisable(true);

    
//

// création Panneau
PanneauAccueil vueAccueil = new PanneauAccueil();
PanneauPraticiens vuePraticiens = new PanneauPraticiens();
PanneauRapports vueRapports =new PanneauRapports();
StackPane pile = new StackPane();

pile.getChildren().addAll(vueAccueil, vuePraticiens, vueRapports);
vueAccueil.toFront();

//


//session
//menu
menuRapport.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle (ActionEvent event){        
        menuPraticien.setDisable(false);
        menuRapport.setDisable(false);
        itemSeConnecter.setDisable(true);
        itemSeDeconnecter.setDisable(false);
    }
});


        //item
    itemQuitter.setOnAction(
            new EventHandler<ActionEvent>(){
                @Override
                public void handle (ActionEvent event){
                    Alert alertQuitter = new Alert (Alert.AlertType.CONFIRMATION);
                    alertQuitter.setTitle("Quitter");
                    alertQuitter.setHeaderText("Demande de Confirmation");
                    alertQuitter.setContentText("Voulez-vous vraiment quitter l'application ?");
                    ButtonType btnOui = new ButtonType("Oui"); 
                    ButtonType btnNon = new ButtonType("Non");
                    alertQuitter.getButtonTypes().setAll(btnOui , btnNon);

                    Optional<ButtonType> reponse = alertQuitter.showAndWait();
                    if (reponse.get()== btnOui) {
                        Session.fermer();
                        Platform.exit();
                        
                    }
                }
            }
        );

        //item

        itemSeConnecter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent event){
    
                VueConnexion vue = new VueConnexion();
        
                Optional<Pair<String, String>> reponse = vue.showAndWait();
                
                if(reponse.isPresent()){
                    try{
                        Visiteur delegue = ModeleGsbRv.seConnecter(reponse.get().getKey(), reponse.get().getValue());
                        if (delegue != null){
                            Session.ouvrir(delegue);
                            menuPraticien.setDisable(false);
                            menuRapport.setDisable(false);
                            itemSeConnecter.setDisable(true);
                            itemSeDeconnecter.setDisable(false);
                            primaryStage.setTitle(Session.getSession().getLeVisiteur().getNom() + " " + Session.getSession().getLeVisiteur().getPrenom());
                          }
                
                          else {
                            Alert alert = new Alert(AlertType.ERROR);
                            alert.setTitle("Erreur ");
                            alert.setHeaderText("Erreur de Connexion");
                            alert.setContentText("Impossible de se connecter, veuillez ré-essayer");
                            alert.setContentText("Matricule ou Mot de passe incorrect");      
                            alert.showAndWait();
                
                        }
                    }
                         catch (ConnexionException e) {
                          e.getMessage();
                          e.printStackTrace();
                
                         }
                        
                    };
                    //Session.ouvrir(new Visiteur("OB001","BOUAICHI","Oumayma"));
              
            }
            
    } );

    //item

    itemSeDeconnecter.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle (ActionEvent event){


           menuPraticien.setDisable(true);
           menuRapport.setDisable(true);
           itemSeConnecter.setDisable(false);
           itemSeDeconnecter.setDisable(true);           
           primaryStage.setTitle("GSB-RV");
           Session.fermer();
           vueAccueil.toFront();
        }
    });

 

  
// item

   itemConsulter.setOnAction(new EventHandler<ActionEvent>() {
       @Override
       public void handle (ActionEvent event){
          
      
       }
       
   });

// item
   itemHesitant.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle (ActionEvent event){

   
    }
    
});
 

//scene builder 

    BorderPane root = new BorderPane();
    root.setTop(barreMenus);
    root.setCenter(pile);
    Scene scene = new Scene(root,500,250);
    primaryStage.setTitle("GSB-RV");
    primaryStage.setScene(scene);
    primaryStage.show();

    }

    public static void main(String[] args) throws Exception {
        launch();

       
        }
    }
