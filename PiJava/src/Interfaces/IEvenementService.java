/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.evenement;
import java.util.List;

/**
 *
 * @author HAMMOUDA
 */
public interface IEvenementService {
     public void createEvenement(evenement e);

    public List<evenement> getAll(String role,int idUser);

    public void update(evenement e,int id);

    public void delete(int id);
    
}
