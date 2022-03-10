/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PiJava.Article_Commentaire;

import java.nio.file.Files;
import java.nio.file.Path;
import Entities.Article;
import PiJava.Evenements.ExpertEvenementController;
import PiJava.Home.SecondHomeController;
import PiJava.PiJava;
import Service.ServiceNotification;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import Service.ArticleService;

import java.nio.file.CopyOption;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author akram
 */
public class ArticleAdminController implements Initializable {

    @FXML
    private TableView<Article> lab;
    @FXML
    private TableColumn<Article, String> coltitre;
    @FXML
    private TableColumn<Article, String> coltheme;
    @FXML
    private TableColumn<Article, String> colauteur;
    @FXML
    private TableColumn<Article, String> coldate;
    @FXML
    private TableColumn<Article, String> colarticle;
    @FXML
    private TableColumn<Article, Integer> colapp;
    File[] filesdoc = Paths.get("src\\pdfArticles\\").toFile().listFiles();
    @FXML
    private Button trier;
    /**
     * Initializes the controller class.
     */
    public int idUser ;

    @FXML
    private Button approuverarticle;
    @FXML
    private Button nonApprouverArticle;
    @FXML
    private Button retour;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        showArticles();
    }

    public void showArticles() {
        ArticleService sop = new ArticleService();
        ObservableList<Article> articles = sop.showArticleAdmin();
        coltitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        coltheme.setCellValueFactory(new PropertyValueFactory<>("theme"));
        colauteur.setCellValueFactory(new PropertyValueFactory<>("nom_auteur"));
        colarticle.setCellValueFactory(new PropertyValueFactory<>("article"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colapp.setCellValueFactory(new PropertyValueFactory<>("approuver"));

        lab.setItems(articles);

        nonApprouverArticle.setVisible(false);
        approuverarticle.setVisible(false);
    }

    public void initializeFxml(int id) {
    }

    @FXML
    private void approuverarticle(ActionEvent event) throws IOException {

        Article a = lab.getSelectionModel().getSelectedItem();
        String k;
        k = a.getArticle();


        ArticleService sa = new ArticleService();

        String approuver = null;
        if (a.getApprouver().equals("Oui")) {
            approuver = "Non";
        } else {

            approuver = "Oui";

        }

        sa.approuver(a, approuver);
        ServiceNotification Notification = new ServiceNotification();
        Notification.Notification("Articles", "article approuve");
        showArticles();

    }

    @FXML
    private void tabClicked(MouseEvent event) {
        Article a = lab.getSelectionModel().getSelectedItem();
        if (a.getApprouver().equals("Oui")) {
            nonApprouverArticle.setVisible(true);
            approuverarticle.setVisible(false);

        } else if (a.getApprouver().equals("Non")) {
            nonApprouverArticle.setVisible(false);
            approuverarticle.setVisible(true);
        };

    }

    @FXML
    private void nonApprouverArticle(ActionEvent event) throws IOException {

        Article a = lab.getSelectionModel().getSelectedItem();
        System.out.println(a.getId());
        String k;
        k = a.getArticle();

        ArticleService sa = new ArticleService();

        String approuver = null;
        if (a.getApprouver().equals("Oui")) {
            approuver = "Non";
        } else {

            approuver = "Oui";

        }

        sa.approuver(a, approuver);
        ServiceNotification Notification = new ServiceNotification();
        Notification.Notification("Articles", "article non approuvé");

        showArticles();
    }

    @FXML
    private void supprimer(ActionEvent event) {
        Article a = lab.getSelectionModel().getSelectedItem();
        ArticleService sa = new ArticleService();
        sa.supprimerad(a);
        showArticles();

    }

    @FXML
    private void review(ActionEvent event) {

        Article a = lab.getSelectionModel().getSelectedItem();
        String k;
        k = a.getArticle();
        System.out.println(k);
        try {
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + "src\\pdfArticles\\" + k);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "check yor file");
        }
    }

    @FXML
    private void trier(ActionEvent event) {
        ArticleService sop = new ArticleService();
        ObservableList<Article> articles = sop.trierc();

        coltitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        coltheme.setCellValueFactory(new PropertyValueFactory<>("theme"));
        colauteur.setCellValueFactory(new PropertyValueFactory<>("nom_auteur"));
        colarticle.setCellValueFactory(new PropertyValueFactory<>("article"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colapp.setCellValueFactory(new PropertyValueFactory<>("approuver"));
        lab.setItems(articles);
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
                    HomeScene.idAdmin = this.idUser;

        HomeScene.role = "Admin";

        //HomeScene.initializeFxml(idExpert);
        Stage window = (Stage) retour.getScene().getWindow();
        String css = PiJava.class.getResource("Style.css").toExternalForm();
        root.getStylesheets().add(css);

        window.setScene(new Scene(root, 600, 400));
    }

}
