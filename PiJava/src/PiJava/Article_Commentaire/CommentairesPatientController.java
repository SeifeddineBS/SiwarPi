/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PiJava.Article_Commentaire;

import Entities.Article;
import Entities.Commentaire;
import Service.CommentaireService;
import Service.ServiceNotification;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;

/**
 * FXML Controller class
 *
 * @author SeifBS
 */
public class CommentairesPatientController implements Initializable {

    public int idUser;

    @FXML
    private TableColumn<Commentaire, String> contenu;
    @FXML
    private Button supprimer;
    @FXML
    private Button modifier;
    @FXML
    private TextField inputAjout;
    @FXML
    private Button ajouter;
    ServiceNotification Notification = new ServiceNotification();
    @FXML
    private Label dateCommentaire;
    CommentaireService commentaireService = new CommentaireService();
    @FXML
    private TableView<Commentaire> commentairesTab;
    @FXML
    private Label articleName;
    @FXML
    private Label userName;
    public Article article;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        dateCommentaire.setVisible(false);

    }

    public void show() {
        ObservableList<Commentaire> commentaires = commentaireService.showCommentaires(this.article.getId());
        contenu.setCellValueFactory(new PropertyValueFactory<>("contenu"));

        commentairesTab.setItems(commentaires);
        modifier.setVisible(false);
        supprimer.setVisible(false);
        dateCommentaire.setVisible(false);
        userName.setVisible(false);

    }

    public void initializeFxml(Article article) {
        this.article=article;
        
        System.out.println(idUser);
        articleName.setText(article.getTitre());
        show();
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

    @FXML
    private void supprimer(ActionEvent event) {
        Commentaire commentaire = commentairesTab.getSelectionModel().getSelectedItem();

        Boolean suppressionBox = Suppression_Box("Commentaire", "Vous etes sur le point de supprimer votre commentaire");
        if (suppressionBox) {
            commentaireService.supprimer(commentaire);

            Notification.Notification("Commentaire", "Commentaire supprimé");
            show();
        }

    }

    @FXML
    private void modifier(ActionEvent event) {
        String input = inputAjout.getText();
        Commentaire commentaire = commentairesTab.getSelectionModel().getSelectedItem();
        commentaire.setContenu(input);

        commentaire.setId_user(idUser);

        commentaireService.modifier(commentaire);
        Notification.Notification("Commentaire", "Commentaire modifié");
        show();
    }

    @FXML
    private void ajouter(ActionEvent event) {
        String input = inputAjout.getText();
        Commentaire commentaire = new Commentaire();
        commentaire.setContenu(input);
        commentaire.setId_user(idUser);
        commentaire.setIdArticle(article.getId());
        commentaireService.ajouter(commentaire);
        Notification.Notification("Commentaire", "Commentaire ajouté");
        inputAjout.clear();
        show();

    }

    @FXML
    private void commentairesClicked(MouseEvent event) {
        Commentaire commentaire = commentairesTab.getSelectionModel().getSelectedItem();
        dateCommentaire.setVisible(true);
        dateCommentaire.setText(commentaire.getDate());
      

        if (commentaireService.verifCommentaire(commentaire, idUser)) {
            modifier.setVisible(true);
            supprimer.setVisible(true);
            inputAjout.setText(commentaire.getContenu());
            userName.setText("");


        } else {
        userName.setVisible(true);
        userName.setText(commentaireService.getPatient(idUser));
            modifier.setVisible(false);
            supprimer.setVisible(false);
        }

    }

}
