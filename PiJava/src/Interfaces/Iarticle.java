/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.Article;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author akram
 */
 public interface Iarticle {
    public void ajouter (Article o);
    public List<Article> showArticle(int id_user);
    public void modifier (Article a);
     public ObservableList<Article> trier(int id_user);
     public ObservableList<Article> trierc();
    public ObservableList<Article> recherche(String theme );
    public void supprimer(Article c);
     public String recup_article (String art);
     public void approuver(Article c,String approuver);
     public void supprimerad(Article c);
     
 }