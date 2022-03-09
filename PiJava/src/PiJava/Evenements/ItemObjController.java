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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Chirine
 */
public class ItemObjController implements Initializable {

    @FXML
    private Label lb_desc;
    @FXML
    private Label lb_rep;

    private evenement evenement;
    private EvenementListenner listenner;
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

    public void setData(evenement evenement, EvenementListenner ol,int idUser) {
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
        listenner = ol;
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

    @FXML
    private void Join(ActionEvent event) {
        System.out.println("Hey"+idUser);
        EvenementService evs = new EvenementService();
        Reservation r = new Reservation(idUser, evenement.getId());
        evs.JoinEvent(r);
        ServiceNotification Notification = new ServiceNotification();
        
        Notification.Notification("Succee", "participation confirme");
        join.setVisible(false);
        leave.setVisible(true);
            SendMail sendMail = new SendMail();

    sendMail.envoyerMail(evs.getExpert(evenement.getIdUser()).getAdresseMail(), "Une nouvelle reservation a votre evenement", "Une nouvelle reservation a votre evenement "+evenement.getNom_event());
    

    }

    @FXML
    private void Leave(ActionEvent event) {
        System.out.println("Hey"+idUser);

        EvenementService evs = new EvenementService();
        Reservation r = new Reservation(idUser, evenement.getId());
        evs.DeleteReservation(r);
        ServiceNotification Notification = new ServiceNotification();
        Notification.Notification("Succee", "participation annulée");
        join.setVisible(true);
        leave.setVisible(false);
        
            SendMail sendMail = new SendMail();
            sendMail.envoyerMail(evs.getExpert(evenement.getIdUser()).getAdresseMail(), "Une  reservation annulée a votre evenement", "Une  reservation a ete annulée a votre evenement "+evenement.getNom_event());
    

    }
}
