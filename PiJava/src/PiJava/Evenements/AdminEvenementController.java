/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PiJava.Evenements;

import Entities.evenement;
import PiJava.Home.SecondHomeController;
import PiJava.PiJava;
import Service.EvenementService;
import Service.ServiceNotification;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
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
public class AdminEvenementController implements Initializable {

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
    @FXML
    private Button valider;
    @FXML
    private Button nonValider;
    @FXML
    private TableColumn<evenement, String> valide;
    @FXML
    private Button retour;
    @FXML
    private PieChart statEvents;

    /**
     * Initializes the controller class.
     */
    public void show() {
        EvenementService cr = new EvenementService();
        ObservableList<evenement> data = FXCollections.observableArrayList(cr.getAll("Admin", -1));
        // TODO
        ev_name.setCellValueFactory(new PropertyValueFactory("nom_event"));
        ev_descr.setCellValueFactory(new PropertyValueFactory("description_event"));
        event_date.setCellValueFactory(new PropertyValueFactory("date"));
        event_prix.setCellValueFactory(new PropertyValueFactory("prix_event"));
        event_amount.setCellValueFactory(new PropertyValueFactory("nbr_place"));
        valide.setCellValueFactory(new PropertyValueFactory("valide"));

        table.setItems(data);
        statEvents();
    }

    public void initializeFxml() {
        show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //show();

        valider.setVisible(false);
        nonValider.setVisible(false);
        // TODO
    }

    @FXML
    private void valider(ActionEvent event) {
        evenement evenement = table.getSelectionModel().getSelectedItem();
        EvenementService evs = new EvenementService();
        evs.valider(evenement, "Oui");
        valider.setVisible(false);
        nonValider.setVisible(false);
        ServiceNotification Notification = new ServiceNotification();
        Notification.Notification("Evenements", "evenement  valide");
        show();

    }

    @FXML
    private void nonValider(ActionEvent event) {
        evenement evenement = table.getSelectionModel().getSelectedItem();
        EvenementService evs = new EvenementService();
        evs.valider(evenement, "Non");
        nonValider.setVisible(false);
        valider.setVisible(false);

        ServiceNotification Notification = new ServiceNotification();
        Notification.Notification("Evenements", "evenement non valide");
        show();

    }

    @FXML
    private void clickTab(MouseEvent event) {
        evenement evenement = table.getSelectionModel().getSelectedItem();
        if (evenement.getValide().equals("Oui")) {
            nonValider.setVisible(true);
            valider.setVisible(false);

        } else {
            valider.setVisible(true);
            nonValider.setVisible(false);

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
                        //HomeScene.idAdmin = this.idUser;

        HomeScene.role = "Admin";

        //HomeScene.initializeFxml(idExpert);
        Stage window = (Stage) retour.getScene().getWindow();
        String css = PiJava.class.getResource("Style.css").toExternalForm();
        root.getStylesheets().add(css);

        window.setScene(new Scene(root, 600, 400));
    }

    public String formatDate(String Date) {
        SimpleDateFormat sdf = null;
        Date d = null;
        try {
            sdf = new SimpleDateFormat("yy-MM-dd");
            d = (Date) sdf.parse(Date);
            sdf.applyPattern("EEEE d MM yyyy");
        } catch (ParseException e) {
            System.out.println(e);

        }
        return sdf.format(d);
    }

   
    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void statEvents() {
        EvenementService cr = new EvenementService();

        ObservableList<evenement> data = FXCollections.observableArrayList(cr.getAll("Patient", -1));
        
        int prix1=0;
        int prix2=0;
        int prix3=0;
        
        
         for (evenement evenement : data) {
             
             if(isNumeric(evenement.getPrix_event()))
             {
            if(Integer.parseInt(evenement.getPrix_event())<50)
                prix1++;
            if(Integer.parseInt(evenement.getPrix_event())>=50 && Integer.parseInt(evenement.getPrix_event())<100)
                prix2++;
              if( Integer.parseInt(evenement.getPrix_event())>100)
                prix3++;
        

        
        int all = prix1 + prix2 + prix3;

        ObservableList<Data> list_stat = FXCollections.observableArrayList(
                new PieChart.Data("Prix moins de 50 Dinars: " + (prix1 * 100) / all + "%", prix1),
                new PieChart.Data("Prix entre  50 Dinars et 100 Dinars:" + (prix2 * 100) / all + "%", prix2),
                new PieChart.Data("Prix plus que 100 Dinars:" + (prix3 * 100) / all + "%", prix3)
        );
        statEvents.setData(list_stat);

    }}

}}
