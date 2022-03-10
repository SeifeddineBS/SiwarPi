/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PiJava.Article_Commentaire;

import Service.ServiceNotification;
import Entities.Article;
import PiJava.Evenements.ExpertEvenementController;
import PiJava.Home.SecondHomeController;
import PiJava.PiJava;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import Service.ArticleService;
import utils.Connexion;
import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javafx.application.HostServices;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;
import Service.PDFManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author akram
 */
public class ArticleExpertController implements Initializable {

    @FXML
    private TextField estitre;
    @FXML
    private TextField estheme;
    @FXML
    private TextField esauteur;
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
    private TableColumn<Article, String> colidc;
    private TableColumn<Article, String> colidcl;
    private Label labcom;
    private Label espart;
    private Pagination pagination;

    File[] filesdoc = Paths.get("src\\pdfArticles\\").toFile().listFiles();
    @FXML
    private Label label;
    public int idUser;
    
    @FXML
    private Button retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        /*         pagination.setPageFactory(new Callback < Integer , Node >()
                 {@Override
                 public Node call(Integer pageIndex){
                     try {
                         return createPage(pageIndex);
                     } catch (IOException ex) {
                         Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                     }
                     return null;
                 }

         
                 });*/
        //  List<String> list_commentaire=Arrays.asList(sc.showCommentaire_combobox());
    }

    public void initializeFxml() {
        ArticleService sop = new ArticleService();
        showart();

        search_user(idUser);

    }

    private void showart() {

        ArticleService sop = new ArticleService();
        
        ObservableList<Article> articles = sop.showArticle(idUser,"");
        System.out.println(articles.toString());
        coltitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        coltheme.setCellValueFactory(new PropertyValueFactory<>("theme"));
        colauteur.setCellValueFactory(new PropertyValueFactory<>("nom_auteur"));
        colarticle.setCellValueFactory(new PropertyValueFactory<>("article"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));

        lab.setItems(articles);
    }

    @FXML
    private void addart(ActionEvent event) throws IOException {
        ArticleService s = new ArticleService();
        PDFManager pdfManager = new PDFManager();
        Article a = new Article();
        String k;
        k = file();
        System.out.println(k);
        if ((estitre.getText().equals("")) || (estheme.getText().equals("")) || (esauteur.getText().equals("")) || (k == null)) {

            estitre.setStyle("-fx-border-color: red; -fx-borfrt-width: 2px;");
            new animatefx.animation.Shake(estitre).play();
            estheme.setStyle("-fx-border-color: red; -fx-borfrt-width: 2px;");
            new animatefx.animation.Shake(estheme).play();
            esauteur.setStyle("-fx-border-color: red; -fx-borfrt-width: 2px;");
            new animatefx.animation.Shake(esauteur).play();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Champ manquant");
            alert.setHeaderText(null);
            alert.setContentText("IL FAUT REMPLIR TOUS LES CHAMPS");
            alert.showAndWait();
        } else {

            a.setTitre(estitre.getText());
            a.setTheme(estheme.getText());
            a.setNom_auteur(esauteur.getText());
            a.setId_user(idUser);
            //  a.setArticle(esarticle.getText());

            a.setArticle(k);
            s.ajouter(a);
            ServiceNotification Notification = new ServiceNotification();
            Notification.Notification("Feel better", "article ajouté");
            showart();
            //espart.setText(s.recup_article(esarticle.getText()));

        }
    }

    public VBox createPage(int index) throws IOException {

        PDFManager pdfManager = new PDFManager();

        File f = filesdoc[index];
        String J = f.getAbsolutePath();

        pdfManager.setFilePath(J);
        String text = pdfManager.toText();
        espart.setText(pdfManager.toText());

        VBox pageBox = new VBox();
        pageBox.getChildren();

        return pageBox;
    }

    private String code_random() {

        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString 
        StringBuilder sb = new StringBuilder(8);

        for (int i = 0; i < 8; i++) {

            // generate a random number between 
            // 0 to AlphaNumericString variable length 
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb 
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();

    }

    public String file() throws IOException {
        String k = null;

        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new ExtensionFilter("PDF Files ", "*.pdf"));
        File selectedFile = fc.showOpenDialog(null);

        if (selectedFile != null) {
            //System.out.println("file ok");

            //s = selectedFile.getName();
            k = this.code_random() + ".pdf";

            Path from = Paths.get(selectedFile.toURI());
            Path to = Paths.get("src\\pdfArticles\\" + k);

            CopyOption[] options = new CopyOption[]{
                StandardCopyOption.REPLACE_EXISTING, // StandardCopyOption.COPY_ATTRIBUTES
            };
            Files.copy(from, to, options);

        } else {
            System.out.println("file is not valid");

        }
        return k;
    }

    @FXML
    private void modar(ActionEvent event) throws IOException {

        if ((estitre.getText().equals("")) || (estheme.getText().equals("")) || (esauteur.getText().equals(""))) {
            estitre.setStyle("-fx-border-color: red; -fx-borfrt-width: 2px;");
            new animatefx.animation.Shake(estitre).play();
            estheme.setStyle("-fx-border-color: red; -fx-borfrt-width: 2px;");
            new animatefx.animation.Shake(estheme).play();
            esauteur.setStyle("-fx-border-color: red; -fx-borfrt-width: 2px;");
            new animatefx.animation.Shake(esauteur).play();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Champ manquant");
            alert.setHeaderText(null);
            alert.setContentText("IL FAUT REMPLIR TOUS LES CHAMPS");
            alert.showAndWait();

        } else {

            ArticleService sa = new ArticleService();

            Article a = lab.getSelectionModel().getSelectedItem();

            a.setTitre(estitre.getText());
            a.setTheme(estheme.getText());
            a.setNom_auteur(esauteur.getText());

            sa.modifier(a);

            showart();

         

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
    private void suppar(ActionEvent event) {
        Article a = lab.getSelectionModel().getSelectedItem();
        ArticleService sa = new ArticleService();

        if (a == null) {
            alert_Box("Verifier", "Veuillez selectionner un article");

        } else {
            boolean Alert_Box = Suppression_Box("Attention", "Vous Etes sur le point de Supprimer Votre Article,Vous voulez Continuez ? ");

            if (Alert_Box) {
                sa.supprimer(a);

                showart();

            }

        }

        {

        }
    }

    @FXML
    private void triert(ActionEvent event) {
        ArticleService sop = new ArticleService();
        ObservableList<Article> articles = sop.trier(idUser);

        coltitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        coltheme.setCellValueFactory(new PropertyValueFactory<>("theme"));
        colauteur.setCellValueFactory(new PropertyValueFactory<>("nom_auteur"));
        colarticle.setCellValueFactory(new PropertyValueFactory<>("article"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));

        lab.setItems(articles);
    }


    private void search_user(int id_user) {
        ArticleService sop = new ArticleService();
        //ObservableList<article> articles = sop.showArticle(id_user);
        ObservableList<Article> articles = FXCollections.observableArrayList();

        coltitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        coltheme.setCellValueFactory(new PropertyValueFactory<>("theme"));
        colauteur.setCellValueFactory(new PropertyValueFactory<>("nom_auteur"));
        colarticle.setCellValueFactory(new PropertyValueFactory<>("article"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("date"));

        try {
            Article A = new Article();

            String requete = "SELECT * FROM article WHERE id_user= '" + id_user + "' ";
            System.out.println(id_user);

            Connection cnx = Connexion.getInstance().getConnection();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {

                articles.add(new Article(rs.getInt("id"), rs.getString("titre"), rs.getString("theme"), rs.getString("nom_auteur"), rs.getString("date"), rs.getString("article"), rs.getInt("id_user"), rs.getString("approuver")));

                System.out.println("!!!");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        ObservableList<Article> dataList = articles;
        lab.setItems(articles);
        FilteredList<Article> filteredData = new FilteredList<>(dataList, b -> true);
      

        lab.setItems(filteredData);

    }
    private HostServices hostServices;

    public HostServices getHostServices() {
        return hostServices;
    }

    public void setHostServices(HostServices hostServices) {
        this.hostServices = hostServices;
    }

    @FXML
    private void review(ActionEvent event) {

        Article a = lab.getSelectionModel().getSelectedItem();
        String k;
        k = a.getArticle();
        try {
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + "src\\pdfArticles\\" + k);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "check yor file");
        }
    }



    @FXML
    private void labClicked(MouseEvent event) {

        Article a = lab.getSelectionModel().getSelectedItem();
        estitre.setText(a.getTitre());
        estheme.setText(a.getTheme());
        esauteur.setText(a.getNom_auteur());

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
                HomeScene.idExpert = this.idUser;

        HomeScene.role = "Expert";

        //HomeScene.initializeFxml(idExpert);
        Stage window = (Stage) retour.getScene().getWindow();
        String css = PiJava.class.getResource("Style.css").toExternalForm();
        root.getStylesheets().add(css);

        window.setScene(new Scene(root, 600, 400));
    }
}
