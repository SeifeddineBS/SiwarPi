/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PiJava.Home;

import PiJava.Article_Commentaire.ArticleAdminController;
import PiJava.Article_Commentaire.ArticleExpertController;
import PiJava.PiJava;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;
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
public class HomeController implements Initializable {

    @FXML
    private Button admin;
    @FXML
    private Button expert;
    @FXML
    private Button patient;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    //String idAdmin="69876542";
    int idExpert=1;
    int idPatient=01;

    @FXML
    private void admin(ActionEvent event) throws IOException, ParseException {
        
        

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/PiJava/Home/SecondHome.fxml"));            
        Parent root = loader.load();

        SecondHomeController HomeScene = loader.getController(); 
       // HomeScene.id_user = idAdmin;
        HomeScene.role="Admin";

        
        //HomeScene.initializeFxml(idAdmin);
        Stage window = (Stage) admin.getScene().getWindow();
        String css = PiJava.class.getResource("Style.css").toExternalForm();
        root.getStylesheets().add(css);

        window.setScene(new Scene(root, 600, 400));
        
        
    }

    @FXML
    private void expert(ActionEvent event)throws IOException, ParseException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/PiJava/Home/SecondHome.fxml"));            

        Parent root = loader.load();

        SecondHomeController HomeScene = loader.getController(); 
        HomeScene.idExpert = idExpert;
        HomeScene.role="Expert";
        
        HomeScene.initializeFxml(idExpert);
        Stage window = (Stage) expert.getScene().getWindow();
        String css = PiJava.class.getResource("Style.css").toExternalForm();
        root.getStylesheets().add(css);

        window.setScene(new Scene(root, 600, 400));

    }

    @FXML
    private void patient(ActionEvent event) throws IOException, ParseException{
        
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/PiJava/Home/SecondHome.fxml"));            

        Parent root = loader.load();

        SecondHomeController HomeScene = loader.getController(); 
        HomeScene.idPatient = idPatient;
        HomeScene.role="Patient";
        
        HomeScene.initializeFxml(idExpert);
        Stage window = (Stage) expert.getScene().getWindow();
        String css = PiJava.class.getResource("Style.css").toExternalForm();
        root.getStylesheets().add(css);

        window.setScene(new Scene(root, 600, 400));
        
        
        
    }
    
}
