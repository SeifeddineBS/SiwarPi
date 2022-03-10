/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Commentaire;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.Connexion;

/**
 *
 * @author SeifBS
 */
public class CommentaireService {
    
      Connection cnx;
      public String  date()
      {
         SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");  
            Date date = new Date(); 
            return formatter.format(date); 
      }
          


    public CommentaireService() {
        cnx = Connexion.getInstance().getConnection();
           System.out.println(cnx);
}
    
    
    public void ajouter(Commentaire a) {
        try {
            if (cnx != null) {
                

                String requete = "INSERT INTO `Commentaire` ( `id`,`contenu`, `date`, `idUser`,`idArticle`) VALUES ( '" + a.getId() + "','" + a.getContenu() + "', '" + date() + "', '" + a.getId_user()+ "', '" + a.getIdArticle()+ "') ";
                Statement st = Connexion.getInstance().getConnection().createStatement();
                st.executeUpdate(requete);
                System.out.println(" ajouté");
                 
            } else {
                System.out.println("non");
            }
        } catch (SQLException ex) {
            System.out.println("erreur ");
            System.out.println(ex);
        }
    }

   
             
     
    public ObservableList<Commentaire> showCommentaires(int idArticle) {
        Statement stm = null;
        ObservableList<Commentaire> commentaires = FXCollections.observableArrayList();

        try {

            stm = Connexion.getInstance().getConnection().createStatement();
           
                String  query = "SELECT * FROM Commentaire where idArticle="+idArticle;

        


            ResultSet rs = stm.executeQuery(query);
            
            while (rs.next()) {
                Commentaire c= new Commentaire();
                c.setId(rs.getInt(1));
                c.setContenu(rs.getString(2));
                c.setDate(rs.getString(3));
                c.setId_user(rs.getInt(4)); 
                c.setIdArticle(rs.getInt(5)); 

                commentaires.add(c);
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(ArticleService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return commentaires;
    }

    public void modifier(Commentaire c) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        try {
            Statement st = cnx.createStatement();
            String query = "UPDATE  Commentaire SET contenu= '" + c.getContenu()+ "', date = '" + date()+ "', idUser = '" + c.getId_user()+ "' WHERE id = '" + c.getId()+ "' ";
            st.executeUpdate(query);
            System.out.println("modification avec succes");
        } catch (SQLException ex) {
            System.out.println("erreur ");
            System.out.println(ex);
        }
    }
    
   public void supprimer(Commentaire c) {
       
       
        try {
            Statement st = cnx.createStatement();
           
            String query = "DELETE FROM Commentaire WHERE id = '" + c.getId()+ "' ";
            st.executeUpdate(query);
            System.out.println("modification avec succes");
        } catch (SQLException ex) {
            System.out.println("erreur ");
            System.out.println(ex);
        }
    }
   
   
   public boolean verifCommentaire(Commentaire commentaire,int idUser)
   {
       Statement stm = null;
        ObservableList<Commentaire> commentaires = FXCollections.observableArrayList();

        try {

            stm = Connexion.getInstance().getConnection().createStatement();
            
               String  query = "SELECT * FROM Commentaire where id="+commentaire.getId()+" AND idUser="+idUser;

        


            ResultSet rs = stm.executeQuery(query);
            
            while (rs.next()) {
                return true;
            }
           
        } catch (SQLException ex) {
            return false;
        }

        return false;
   }
   
   
   
         public String getPatient(int id) { // charger données Client pour la modification

        Statement stm = null;
        String nom=null;
        String prenom=null;

        try {
            stm = cnx.createStatement();
            String query = "SELECT * FROM `patient`  WHERE ID_patient="+id;
            ResultSet rst = stm.executeQuery(query);

            while (rst.next()) {
                nom=rst.getString("nom");
                prenom=rst.getString("prenom");

             

               

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return nom+" "+prenom;

    }
    
    
    
}
