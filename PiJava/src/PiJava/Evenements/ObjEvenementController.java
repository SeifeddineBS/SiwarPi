/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PiJava.Evenements;

import Entities.Reservation;
import Entities.evenement;
import Service.EvenementService;
import Service.SendMail;
import Service.ServiceNotification;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;

/**
 * FXML Controller class
 *
 * @author Chirine
 */
public class ObjEvenementController implements Initializable {

    @FXML
    private Label lb_desc;
    @FXML
    private Label lb_rep;

    private evenement evenement;
  
    @FXML
    private Label lb_date;
    @FXML
    private Label lb_duree;
    @FXML
    private ImageView icone;
    Image image;
    @FXML
    private Label lb_duree1;
    @FXML
    private Label lb_desc1;
    @FXML
    private Button join;
    @FXML
    private Button leave;
    public int idUser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void initializeFxml(int idUser) {

    }

    @FXML
    private void click(MouseEvent actionEvent) {
        PatientEvenementController oc = new PatientEvenementController();

    }

    public void setData(evenement evenement,int idUser) {
        this.idUser=idUser;
        EvenementService evs = new EvenementService();
        if (evs.verifierUser(idUser, evenement.getId())) {
            join.setVisible(false);
            leave.setVisible(true);

        } else {
            join.setVisible(true);
            leave.setVisible(false);
        }

        this.evenement = evenement;
        lb_desc.setWrapText(true);
        lb_desc.setText(evenement.getNom_event());
        lb_rep.setText("" + evenement.getPrix_event());
        lb_date.setText(evenement.getDate().toString());
        lb_duree.setText("" + evenement.getPrix_event());
        lb_duree1.setText("" + evenement.getNbr_place());
        lb_desc1.setText("" + evenement.getDescription_event());
        image = new Image(getClass().getResourceAsStream("/Images/evenements/default.png"));

        try {
            image = new Image(getClass().getResourceAsStream("/Images/evenements/" + evenement.getImage()));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (image != null) {
            icone.setImage(image);
        }
    }
    
       public boolean Suppression_Box(String title, String message) {
        boolean sortie = false;
        Alert.AlertType Type = Alert.AlertType.CONFIRMATION;
        Alert alert = new Alert(Type, "");
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setTitle(title);
        alert.setContentText(message);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            sortie = true;
        } else if (result.get() == ButtonType.CANCEL) {
            sortie = false;
        }

        return sortie;

    }
         public void alert_Box(String title, String message) {
        Alert dg = new Alert(Alert.AlertType.WARNING);
        dg.setTitle(title);
        dg.setContentText(message);
        dg.show();
    }

    @FXML
    private void Join(ActionEvent event) {
        Boolean suppressionBox = Suppression_Box("Evenement", "Vous etes sur le point de rejoindre cet evenement");

        if (suppressionBox) {
        System.out.println("Hey"+idUser);
        EvenementService evs = new EvenementService();
        Reservation r = new Reservation(idUser, evenement.getId());
        
        if(evs.JoinEvent(r))
        {
              ServiceNotification Notification = new ServiceNotification();
        
        join.setVisible(false);
        leave.setVisible(true);
            SendMail sendMail = new SendMail();

    sendMail.envoyerMail(evs.getExpert(evenement.getIdUser()).getAdresseMail(), "Une nouvelle reservation a votre evenement", "Une nouvelle reservation a votre evenement "+evenement.getNom_event());
                Notification.Notification("Succee", "participation confirme");
                setData(evenement,idUser);
                
        }
        else {
            alert_Box("Evenement"," Mlaheureusement les places sont limitées");
        }
      

        
        }

    }

    @FXML
    private void Leave(ActionEvent event) {
          Boolean suppressionBox = Suppression_Box("Evenement", "Vous etes sur le point d'annuler votre reservation");

        if (suppressionBox) {

        EvenementService evs = new EvenementService();
        Reservation r = new Reservation(idUser, evenement.getId());
        evs.DeleteReservation(r);
        ServiceNotification Notification = new ServiceNotification();
        join.setVisible(true);
        leave.setVisible(false);
        
            SendMail sendMail = new SendMail();
            sendMail.envoyerMail(evs.getExpert(evenement.getIdUser()).getAdresseMail(), "Une  reservation annulée a votre evenement", "Une  reservation a ete annulée a votre evenement "+evenement.getNom_event());
            Notification.Notification("Succee", "participation annulée");

        }
    }
}
