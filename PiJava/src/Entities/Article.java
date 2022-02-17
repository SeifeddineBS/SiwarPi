/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;
import javafx.collections.ObservableList;

/**
 *
 * @author akram
 */
public class Article {

    public static void setItems(ObservableList<Article> dataList) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /*public static void setText(String toString) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
    
   private int id;
   private String titre;
   private String theme;
   private String nom_auteur;
   private String date;
   private String article ;
   private int id_user;
   private String approuver ;

    public Article(int id, String titre, String theme, String nom_auteur, String date, String article, int id_user, String approuver) {
        this.id = id;
        this.titre = titre;
        this.theme = theme;
        this.nom_auteur = nom_auteur;
        this.date = date;
        this.article = article;
        this.id_user = id_user;
        this.approuver = approuver;
    }

    public Article() {
    }

    public Article(int aInt, String string, String string0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Article(int aInt, String string, String string0, String string1, String string2, String string3) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getTheme() {
        return theme;
    }

    public String getNom_auteur() {
        return nom_auteur;
    }

    public String getDate() {
        return date;
    }

    public String getArticle() {
        return article;
    }

    public int getId_user() {
        return id_user;
    }

    public String getApprouver() {
        return approuver;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public void setNom_auteur(String nom_auteur) {
        this.nom_auteur = nom_auteur;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setApprouver(String approuver) {
        this.approuver = approuver;
    }

    @Override
    public String toString() {
        return "article{" + "id=" + id + ", titre=" + titre + ", theme=" + theme + ", nom_auteur=" + nom_auteur + ", date=" + date + ", article=" + article + ", id_user=" + id_user + ", approuver=" + approuver + '}';
    }

   
}