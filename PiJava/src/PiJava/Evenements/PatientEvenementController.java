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
import Service.SendMail;
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
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author SeifBS
 */
public class PatientEvenementController implements Initializable {

    private TableView<evenement> table;
    private Button Leave;
    @FXML
    private Button join;
    @FXML
    private Button leave;
    public int idUser;
    @FXML
    private Button retour;
    @FXML
    private HBox hbox;
    @FXML
    private ScrollPane scroll_obj;
    @FXML
    private GridPane grid_obj;
    private EvenementListenner listenner;
    private TextField hidden;

    /**
     * Initializes the controller class.
     */
    
    
     private void setChosenEvenement(evenement evenement) {
        //select row 
        hidden.setText("" + evenement.getId());

    }
    
      public void show()
    {    EvenementService cr = new EvenementService();
        ObservableList<evenement> events = FXCollections.observableArrayList(cr.getAll("Patient",idUser));
        grid_obj.getChildren().clear();
        if (events.size() > 0) {
        listenner = new EvenementListenner() {
            @Override
            public void onClickListener(evenement evenement) {
                setChosenEvenement(evenement);
            }};}
        
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < events.size(); i++) {
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("/PiJava/Evenements/ItemObj.fxml"));
                AnchorPane anchorPane = fxmlloader.load();
                ItemObjController itmc = fxmlloader.getController();
                itmc.idUser=idUser;
                itmc.setData(events.get(i), listenner,idUser);
                if (column == 2) {
                    column = 0;
                    row++;
                }
                grid_obj.add(anchorPane, column++, row);
                grid_obj.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid_obj.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid_obj.setMaxWidth(Region.USE_PREF_SIZE);

                grid_obj.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid_obj.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid_obj.setMaxHeight(Region.USE_PREF_SIZE);

                //grid_obj.setStyle("-fx-background-image: url(\"/Images/back.png\");");
                grid_obj.setStyle("-fx-background-color: rgba(220,245,198,0.5)");

                GridPane.setMargin(anchorPane, new Insets(10));

            }
        } catch (IOException ex) {
            Logger.getLogger(PatientEvenementController.class.getName()).log(Level.SEVERE, null, ex);

        }
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
