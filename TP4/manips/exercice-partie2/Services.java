package fr.info.user_interface.controleur;

import org.springframework.stereotype.Service;
import org.springframework.context.annotation.ComponentScan;

import fr.info.user_interface.modele.Catalogue;

import java.util.ArrayList;

public class Services { 
    
  public Services() {
    init();
  }
  
  public void init() {
    titre = { "Asterix gladiateur",
                        "Lucky Luke, Sur la piste des Dalton",
                        "Garfield prend du poids",
                        "L'Anniversaire d'Astérix et Obélix "};
    auteur = {
                        "R. Goscinny, A. Uderzo",
                        "Morris,  R. Goscinny",
                        "J. Davis",
                        "R. Goscinny, A. Uderzo"};
    String categorie = "bande dessinee";
    
  }
  
  public Catalogue selection() {
    Catalogue catalogue = new Catalogue();
    catalogue.init("Asterix, Le Bouclier Arverne","R. Goscinny, A. Uderzo", "bande dessinee");
    return catalogue;
  }
}
