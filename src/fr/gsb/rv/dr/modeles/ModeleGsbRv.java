package fr.gsb.rv.dr.modeles;

import fr.gsb.rv.dr.entites.Visiteur;
import fr.gsb.rv.dr.technique.ConnexionBD;
import fr.gsb.rv.dr.technique.ConnexionException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ModeleGsbRv {
    
    public static Visiteur seConnecter( String matricule , String mdp ) throws ConnexionException{
        
        // Code de test à compléter
        
        Connection connexion = ConnexionBD.getConnexion() ;
        
        String requete = "SELECT t.vis_matricule, t.tra_role, t.jjmmaa, V.vis_prenom, V.vis_nom" +
                "FROM Travailler t " +
                "INNER JOIN (SELECT tra_role, vis_matricule, MAX(jjmmaa) AS jjmmaa" +
                "            FROM Travailler" +
                "            GROUP BY vis_matricule) AS s" +
                "INNER JOIN Visiteur AS V" +
                "ON s.vis_matricule = t.vis_matricule" +
                "AND t.jjmmaa = s.jjmmaa" +
                "AND V.vis_matricule = t.vis_matricule" +
                "WHERE t.tra_role = 'Délégué'" +
                "AND V.vis_matricule = '" + matricule  +"'"+
                "AND V.vis_mdp = '" + mdp + "'";
        
        try {
            PreparedStatement requetePreparee = (PreparedStatement) connexion.prepareStatement( requete ) ;
            requetePreparee.setString( 1, matricule );
            requetePreparee.setString(2, mdp);
            ResultSet resultat = requetePreparee.executeQuery() ;

            if (resultat.next()) {
                Visiteur visiteur = new Visiteur();
                visiteur.setMatricule(matricule);
                visiteur.setNom(resultat.getString("vis_nom"));
                visiteur.setPrenom(resultat.getString("vis_prenom"));
                requetePreparee.close() ;
                return visiteur ;
            }
            else {
                return null ;
            }
        }
        catch( SQLException e ){
           
                System.out.println("Description:"+ e.getMessage());
           
            return null ;
        } 
    }
}