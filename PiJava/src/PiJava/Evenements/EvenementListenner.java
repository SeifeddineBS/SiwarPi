/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PiJava.Evenements;

import Entities.evenement;

/**
 *
 * @author Chirine
 */
public interface EvenementListenner {
    public void onClickListener(evenement objectif);
}
