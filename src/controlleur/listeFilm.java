/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import entities.Salle;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Houssem
 */
public class listeFilm extends ListCell<Salle> {
    
    private final GridPane gridPane = new GridPane();
    private final Label Nom = new Label();
    private final Label Capacite = new Label();
    private final Label txtNom = new Label();
    private final Label txtCapacite = new Label();
    
    
}
