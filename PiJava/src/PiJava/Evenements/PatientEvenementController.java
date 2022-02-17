/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PiJava.Evenements;

import Entities.Reservation;
import Entities.evenement;
import PiJava.Home.SecondHomeController;
import PiJava.PiJava;
import Service.EvenementService;
import Service.ServiceNotification;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author SeifBS
 */
public class PatientEvenementController implements Initializable {

     @FXML
    private TableColumn<evenement, String> ev_name;
    @FXML
    private TableColumn<evenement, String> ev_descr;
    @FXML
    private TableColumn<evenement, Date> event_date;
    @FXML
    private TableColumn<evenement, String> event_prix;
    @FXML
    private TableColumn<evenement, String> event_amount;
    @FXML
    private TableView<evenement> table;
    private Button Leave;
    @FXML
    private Button join;
    @FXML
    private Button leave;
    @FXML
    private TableColumn<evenement, Integer > participants;
    public int idUser;
    @FXML
    private Button retour;

    /**
     * Initializes the controller class.
     */
    
      public void show()
    { EvenementService cr = new EvenementService();
    ObservableList<evenement> data = FXCollections.observableArrayList(cr.getAll("Patient",idUser));
          // TODO
        ev_name.setCellValueFactory(new PropertyValueFactory("nom_event"));
        ev_descr.setCellValueFactory(new PropertyValueFactory("description_event"));
        event_date.setCellValueFactory(new PropertyValueFactory("date"));
        event_prix.setCellValueFactory(new PropertyValueFactory("prix_event"));
        event_amount.setCellValueFactory(new PropertyValueFactory("nbr_place"));
                participants.setCellValueFactory(new PropertyValueFactory("participants"));


        table.setItems(data);
    }
        public void initializeFxml() {
         show();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   join.setVisible(false);
        leave.setVisible(false);   
    }    
    
        @FXML
    private void Join(ActionEvent event) {
  evenement evenement = table.getSelectionModel().getSelectedItem();
  EvenementService evs = new EvenementService();
            Reservation r = new Reservation(idUser,evenement.getId());
  evs.JoinEvent(r);
   ServiceNotification Notification = new ServiceNotification();
    Notification.Notification("Succee", "participation confirme");
  join.setVisible(false);
        leave.setVisible(false);
        show();

    }

    @FXML
    private void Leave(ActionEvent event) {
         evenement evenement = table.getSelectionModel().getSelectedItem();
  EvenementService evs = new EvenementService();
    Reservation r = new Reservation(idUser,evenement.getId());
  evs.DeleteReservation(r);
       ServiceNotification Notification = new ServiceNotification();
            Notification.Notification("Succee", "participation annulée");
  join.setVisible(false);
        leave.setVisible(false);
                show();

  
    }

    @FXML
    private void clickTab(MouseEvent event) {
        
           evenement evenement = table.getSelectionModel().getSelectedItem();
          
            EvenementService evs = new EvenementService();
    if(evs.verifierUser(idUser, evenement.getId())){
        join.setVisible(false);
        leave.setVisible(true);
        
        
    }
    else{
           join.setVisible(true);
        leave.setVisible(false);
    }

    
    }

     @FXML
    private void retour(ActionEvent event) {
           FXMLLoader loader = new FXMLLoader(getClass().getResource("/PiJava/Home/SecondHome.fxml"));

        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(ExpertEvenementController.class.getName()).log(Level.SEVERE, null, ex);
        }

        SecondHomeController HomeScene = loader.getController();
        //HomeScene.id_user = this.id;
        HomeScene.role = "Patient";

        //HomeScene.initializeFxml(idExpert);
        Stage window = (Stage) retour.getScene().getWindow();
        String css = PiJava.class.getResource("Style.css").toExternalForm();
        root.getStylesheets().add(css);

        window.setScene(new Scene(root, 600, 400));
    }
    
}
