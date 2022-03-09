/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entities.Expert;
import Entities.evenement;
import Entities.Reservation;
import Interfaces.IEvenementService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.Connexion;

/**
 *
 * @author HAMMOUDA
 */
public class EvenementService implements IEvenementService{
   Connection cnx;

    public EvenementService() {
        cnx = Connexion.getInstance().getConnection();
           System.out.println(cnx);
}
    @Override
    public void createEvenement(evenement e) {
           try {

            String requete2 = "INSERT INTO evenement (nom_event,description_event,date,prix_event,nbr_place,image,valide,participants,id_user) VALUES (?,?,?,?,?,?,?,?,?)";

            PreparedStatement pst = cnx.prepareStatement(requete2);
            pst.setString(1, e.getNom_event());
            pst.setString(2, e.getDescription_event());
            pst.setDate(3,e.getDate());
            pst.setString(4, e.getPrix_event());
            pst.setString(5, e.getNbr_place());
            pst.setString(6, e.getImage());
            pst.setString(7,"non");
            pst.setInt(8, 0);
            pst.setInt(9,e.getIdUser());

            pst.executeUpdate();
            System.out.println("Event added");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

      @Override
    public void update(evenement e,int id) {
 try {
            String requete3 = "UPDATE evenement SET nom_event=?,description_event=?,date=?,prix_event=?,nbr_place=? , image=? ,participants=? WHERE id=?";
            PreparedStatement pst2 = cnx.prepareStatement(requete3);
            pst2.setInt(8,id);
            pst2.setString(1, e.getNom_event());
            pst2.setString(2, e.getDescription_event());
            pst2.setDate(3,e.getDate());
            pst2.setString(4, e.getPrix_event());
            pst2.setString(5, e.getNbr_place());
            pst2.setString(6, e.getImage());
            pst2.setInt(7, e.getParticipants());


            
            pst2.executeUpdate();
            System.out.println("Event updated");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }  
    }

    @Override
    public void delete(int id) {
 try {
            String requete7 = "DELETE FROM evenement WHERE id=?";
            PreparedStatement pst7 = cnx.prepareStatement(requete7);
            pst7.setInt(1, id);
            pst7.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
    }
    }
    
    
     @Override
    public List<evenement> getAll(String role,int idUser) {
ObservableList<evenement> myList = FXCollections.observableArrayList();
    String requete=null;

        try {
            if(role.equals("Patient"))
             requete = "SELECT * FROM evenement WHERE valide='Oui'";
            else if(role.equals("Expert"))

            {
             requete = "SELECT * FROM evenement WHERE id_user="+idUser;

            }
            else{
                
                        requete = "SELECT * FROM evenement ";

                
            }
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                evenement e = new evenement();
                e.setId(rs.getInt(1));
                e.setNom_event(rs.getString("nom_event"));
                  e.setDescription_event(rs.getString("description_event"));
                    e.setDate(rs.getDate("date"));
                    e.setPrix_event(rs.getString("prix_event"));
                    e.setNbr_place(rs.getString("nbr_place"));
                    e.setImage(rs.getString("image"));
                        e.setValide(rs.getString("valide"));
                   e.setParticipants(rs.getInt("participants"));
                    e.setIdUser(rs.getInt("id_user"));




                  

                myList.add(e);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());        }
   
      return myList;

    }
  public ObservableList<evenement> FindEvent() {

        ObservableList<evenement> list = FXCollections.observableArrayList();
        try {
            String requete4 = "SELECT * FROM evenement";
            Statement st5 =cnx.createStatement();
            ResultSet rs = st5.executeQuery(requete4);
            while (rs.next()) {
                evenement e = new evenement();
                e.setId(rs.getInt("id"));
                e.setDate(rs.getDate("date"));
                
                e.setNom_event(
                        rs.getString("nom_event"));
                e.setDescription_event(rs.getString("description_event"));
                e.setPrix_event(rs.getString("prix_event"));
                    e.setNbr_place(rs.getString("nbr_place"));
              e.setImage(rs.getString("image"));
                list.add(e);
            }

        } catch (SQLException ex) {
            System.out.println("error");
        }
        return list;

    }
  
  
     public void valider(evenement e,String valide) {
 try {
            String requete3 = "UPDATE evenement SET valide=? WHERE id=?";
            PreparedStatement pst2 = cnx.prepareStatement(requete3);
            pst2.setInt(2,e.getId());
            pst2.setString(1, valide);
     

            
            pst2.executeUpdate();
            System.out.println("Event updated");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }  
    } 
     
     public evenement findEventById(int id){
                     evenement e = new evenement();

         try{
           String requete1="SELECT * FROM evenement WHERE id="+id;
            Statement st5 =cnx.createStatement();
            ResultSet rs = st5.executeQuery(requete1);
            while (rs.next()) {

                e.setId(rs.getInt("id"));
                e.setDate(rs.getDate("date"));
                
                e.setNom_event(
                        rs.getString("nom_event"));
                e.setDescription_event(rs.getString("description_event"));
                e.setPrix_event(rs.getString("prix_event"));
                    e.setNbr_place(rs.getString("nbr_place"));
              e.setImage(rs.getString("image"));
              e.setParticipants(rs.getInt("participants"));
            }

         }catch (SQLException ex) {
            System.out.println("error");
        }
        return e;

     }
     
     public void JoinEvent(Reservation r){
          try {
                evenement e =  findEventById(r.getId_evenement());
                if(Integer.parseInt(e.getNbr_place())> e.getParticipants()){
                    
                
                
                
           try {
         String requete="INSERT INTO `reservation`(`id_user`, `id_event`) VALUES (?,?)";
         PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, r.getId_user());
            pst.setInt(2, r.getId_evenement());
               pst.executeUpdate();
            System.out.println("Reservation added");
            
                     System.out.println(e.getParticipants());

            e.setParticipants(e.getParticipants()+1);
            System.out.println(e);
            update(e,e.getId());
            System.out.println("participant added");

         } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }}
                else{
                                System.out.println("Evenement fermer");

                }
          }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
           
    }     
     
     
     public Boolean verifierUser(int idUser,int idEvent){
                 Statement stm = null;

       try {

             stm = Connexion.getInstance().getConnection().createStatement();

            String query = "SELECT * FROM reservation WHERE id_user= "+idUser+" AND id_event="+idEvent;
            ResultSet rs = stm.executeQuery(query);
            
            while (rs.next()) {
                System.out.println("here");
             return true;
            }
       }catch (SQLException ex) {
             Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
         }
         return false;
     }
    
     
     
     
     
          public void DeleteReservation(Reservation r){
          try {
                evenement e =  findEventById(r.getId_evenement());                             
           try {
         String requete="DELETE FROM `reservation` WHERE id_user= "+r.getId_user()+" AND id_event="+r.getId_evenement();
         PreparedStatement pst = cnx.prepareStatement(requete);
          pst.executeUpdate();
         System.out.println("Reservation DELETE");
         System.out.println(e.getParticipants());
            e.setParticipants(e.getParticipants()-1);
            System.out.println(e);
            update(e,e.getId());
            System.out.println("participant DELETE");

         } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
               
          }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
           
    }
          
          
          
           public Expert getExpert(int id) { // charger données Client pour la modification

        Statement stm = null;
        Expert c = new Expert();

        try {
            stm = cnx.createStatement();
            String query = "SELECT * FROM `expert`  WHERE idExpert="+id;
            ResultSet rst = stm.executeQuery(query);

            while (rst.next()) {
                c.setNom(rst.getString("nom"));
                c.setPrenom(rst.getString("prenom"));
                    c.setAdresseMail(rst.getString("adresseMail"));

               

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
        return c;

    }
     
     
     
}
