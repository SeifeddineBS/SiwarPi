/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PiJava.Article_Commentaire;

import Entities.Article;
import Service.ArticleService;
import Service.EvenementService;
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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Chirine
 */
public class ObjArticleController implements Initializable {

    private Article article;

    @FXML
    private ImageView icone;
    Image image;
    public int idUser;
    @FXML
    private Label labelTitre;
    @FXML
    private Label labelAuteur;
    @FXML
    private Label labelTheme;
    @FXML
    private Label labelDate;
    @FXML
    private Button showCommentaire;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void initializeFxml(int idUser) {

    }

    public void setData(Article article, int idUser) {
        this.idUser = idUser;
        ArticleService evs = new ArticleService();

        this.article = article;
        labelTitre.setWrapText(true);
        labelTitre.setText(article.getTitre());
        labelAuteur.setText("" + article.getNom_auteur());
        labelTheme.setText(article.getTheme());
        labelDate.setText("" + article.getDate());

        try {
            image = new Image(getClass().getResourceAsStream("/Images/article.png"));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (image != null) {
            icone.setImage(image);
        }
    }

  

    @FXML
    private void click(MouseEvent event) {
    }

    @FXML
    private void show(MouseEvent event) {
        
       
        try {
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + "src\\pdfArticles\\" + article.getArticle());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "check yor file");
        
        
    }}

    @FXML
    private void showCommentaire(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CommentairesPatient.fxml"));
        Parent modif = null;
        try {
            modif = (Parent) fxmlLoader.load();
           
            CommentairesPatientController HomeScene = fxmlLoader.getController();
            HomeScene.idUser=idUser;
            HomeScene.initializeFxml(article);

        } catch (IOException ex) {
            Logger.getLogger(ObjArticleController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Stage stage = new Stage();
        stage.setTitle(article.getTitre());
        stage.setScene(new Scene(modif));
        stage.show();
    }

}
