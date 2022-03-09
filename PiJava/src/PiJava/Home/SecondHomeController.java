/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PiJava.Home;

import PiJava.Article_Commentaire.ArticleAdminController;
import PiJava.Article_Commentaire.ArticleExpertController;
import PiJava.Evenements.AdminEvenementController;
import PiJava.Evenements.ExpertEvenementController;
import PiJava.Evenements.PatientEvenementController;
import PiJava.PiJava;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author SeifBS
 */
public class SecondHomeController implements Initializable {

    @FXML
    private Button articles;
    @FXML
    private Button events;
    int idPatient;
    int idExpert;
    int idAdmin;

    public String role;
    @FXML
    private Button retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void initializeFxml(int id) {

    }

    @FXML
    private void articles(ActionEvent event) throws IOException {
        if (this.role.equals("Expert")) {
            this.expertArticles();
        } else if (this.role.equals("Admin")) {
            this.adminArticles();
        }

    }

    public void expertArticles() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/PiJava/Article_Commentaire/articleExpert.fxml"));

        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(SecondHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        ArticleExpertController HomeScene = loader.getController();
        HomeScene.id_user = idExpert;
        HomeScene.initializeFxml();
        Stage window = (Stage) articles.getScene().getWindow();
        String css = PiJava.class.getResource("Style.css").toExternalForm();
        root.getStylesheets().add(css);

        window.setScene(new Scene(root, 1182, 718));
    }

    public void adminArticles() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/PiJava/Article_Commentaire/ArticleAdmin.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(SecondHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        ArticleAdminController HomeScene = loader.getController();
        HomeScene.id_user = idAdmin;

        HomeScene.initializeFxml(idAdmin);
        Stage window = (Stage) articles.getScene().getWindow();
        String css = PiJava.class.getResource("Style.css").toExternalForm();
        root.getStylesheets().add(css);

        window.setScene(new Scene(root, 1182, 718));
    }

    public void expertEvenement() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/PiJava/Evenements/ExpertEvenement.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(SecondHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        ExpertEvenementController HomeScene = loader.getController();
        HomeScene.idUser = idExpert;
        HomeScene.initializeFxml();

        Stage window = (Stage) events.getScene().getWindow();
        String css = PiJava.class.getResource("Style.css").toExternalForm();
        root.getStylesheets().add(css);

        window.setScene(new Scene(root, 1182, 718));
    }

    public void adminEvenements() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/PiJava/Evenements/AdminEvenement.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(SecondHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        AdminEvenementController HomeScene = loader.getController();
        HomeScene.initializeFxml();

        Stage window = (Stage) events.getScene().getWindow();
        String css = PiJava.class.getResource("Style.css").toExternalForm();
        root.getStylesheets().add(css);

        window.setScene(new Scene(root, 1182, 718));
    }

    public void patientEvenement() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/PiJava/Evenements/PatientEvenement.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(SecondHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        PatientEvenementController HomeScene = loader.getController();
                HomeScene.idUser = idPatient;

        HomeScene.initializeFxml();

        Stage window = (Stage) events.getScene().getWindow();
        String css = PiJava.class.getResource("Style.css").toExternalForm();
        root.getStylesheets().add(css);

        window.setScene(new Scene(root, 1182, 718));

    }

    @FXML
    private void events(ActionEvent event) {
        if (this.role.equals("Expert")) {
            this.expertEvenement();
        } else if (this.role.equals("Admin")) {
            this.adminEvenements();
        } else if (this.role.equals("Patient")) {
            this.patientEvenement();
        }
    }

    @FXML
    private void retour(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(SecondHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Stage window = (Stage) events.getScene().getWindow();
        String css = PiJava.class.getResource("Style.css").toExternalForm();
        root.getStylesheets().add(css);

        window.setScene(new Scene(root, 600, 400));

    }
}
