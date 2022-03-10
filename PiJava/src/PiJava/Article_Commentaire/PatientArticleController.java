/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PiJava.Article_Commentaire;

import Entities.Article;
import PiJava.Evenements.ExpertEvenementController;
import PiJava.Home.SecondHomeController;
import PiJava.PiJava;
import Service.ArticleService;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TextField;
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
public class PatientArticleController implements Initializable {

    private Button Leave;
    
    public int idUser;
    @FXML
    private Button retour;
    @FXML
    private HBox hbox;
    @FXML
    private ScrollPane scroll_obj;
    @FXML
    private GridPane grid_obj;
    private TextField hidden;
  

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }  
    
    
    private void setChosenEvenement(Article article) {
        //select row 
        hidden.setText("" + article.getId());

    }
    
      public void show()
    {    ArticleService articleService = new ArticleService();
        ObservableList<Article> articles = FXCollections.observableArrayList(articleService.showArticle(idUser,"Patient"));
        grid_obj.getChildren().clear();
       
        
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < articles.size(); i++) {
                FXMLLoader fxmlloader = new FXMLLoader();
                fxmlloader.setLocation(getClass().getResource("/PiJava/Article_Commentaire/ObjArticle.fxml"));
                AnchorPane anchorPane = fxmlloader.load();
                ObjArticleController itmc = fxmlloader.getController();
                itmc.idUser=idUser;
                itmc.setData(articles.get(i),idUser);
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

                GridPane.setMargin(anchorPane, new Insets(10));

            }
        } catch (IOException ex) {
            Logger.getLogger(PatientArticleController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
        public void initializeFxml() {
         show();
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
        HomeScene.idPatient = this.idUser;

        HomeScene.role = "Patient";

        //HomeScene.initializeFxml(idExpert);
        Stage window = (Stage) retour.getScene().getWindow();
        String css = PiJava.class.getResource("Style.css").toExternalForm();
        root.getStylesheets().add(css);

        window.setScene(new Scene(root, 600, 400));
    }


   
    
}
