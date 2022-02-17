/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author HAMMOUDA
 */
public class evenement {

    private int id;
    private String nom_event;
    private String description_event;
    private Date date;
    private String prix_event;
    private String nbr_place;
    private String image;
    private String valide;
    private int participants;
    private int idUser;

    public evenement() {
    }

    public evenement(String nom_event, String description_event, Date date, String prix_event, String nbr_place, String image,int idUser) {
        this.nom_event = nom_event;
        this.description_event = description_event;
        this.date = date;
        this.prix_event = prix_event;
        this.nbr_place = nbr_place;
        this.image = image;
        this.idUser=idUser;
    }
    
    public evenement(String nom_event, String description_event, Date date, String prix_event, String nbr_place, String image) {
        this.nom_event = nom_event;
        this.description_event = description_event;
        this.date = date;
        this.prix_event = prix_event;
        this.nbr_place = nbr_place;
        this.image = image;
    }

    public evenement(int id, String nom_event, String description_event, Date date, String prix_event, String nbr_place, String image) {
        this.id = id;
        this.nom_event = nom_event;
        this.description_event = description_event;
        this.date = date;
        this.prix_event = prix_event;
        this.nbr_place = nbr_place;
        this.image = image;
    }

    public evenement(int id, String nom_event, String description_event, Date date, String prix_event, String nbr_place, String image, String valide, int participants) {
        this.id = id;
        this.nom_event = nom_event;
        this.description_event = description_event;
        this.date = date;
        this.prix_event = prix_event;
        this.nbr_place = nbr_place;
        this.image = image;
        this.valide = valide;
        this.participants = participants;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_event() {
        return nom_event;
    }

    public void setNom_event(String nom_event) {
        this.nom_event = nom_event;
    }

    public String getDescription_event() {
        return description_event;
    }

    public void setDescription_event(String description_event) {
        this.description_event = description_event;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPrix_event() {
        return prix_event;
    }

    public void setPrix_event(String prix_event) {
        this.prix_event = prix_event;
    }

    public String getNbr_place() {
        return nbr_place;
    }

    public void setNbr_place(String nbr_place) {
        this.nbr_place = nbr_place;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getValide() {
        return valide;

    }

    public void setValide(String valide) {
        this.valide = valide;
    }

    public int getParticipants() {
        return participants;
    }

    public void setParticipants(int participants) {
        this.participants = participants;
    }

    @Override
    public String toString() {
        return "evenement{" + "id=" + id + ", nom_event=" + nom_event + ", description_event=" + description_event + ", date=" + date + ", prix_event=" + prix_event + ", nbr_place=" + nbr_place + ", image=" + image + ", valide=" + valide + ", participants=" + participants + '}';
    }
 

}
