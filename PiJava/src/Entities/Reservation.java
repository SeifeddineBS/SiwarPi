/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author REZIGUE
 */
public class Reservation {
        private int id_user;
    private int id_evenement;

    public Reservation() {
    }

    public Reservation(int id_user, int id_evenement) {
        this.id_user = id_user;
        this.id_evenement = id_evenement;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_evenement() {
        return id_evenement;
    }

    public void setId_evenement(int id_evenement) {
        this.id_evenement = id_evenement;
    }

    @Override
    public String toString() {
        return "Reservation{" + "id_user=" + id_user + ", id_evenement=" + id_evenement + '}';
    }


    
    

}
