/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Produit;
import Entities.Tva;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DbConnexion;

/**
 *
 * @author Dorra
 */
public class TvaService {
      Connection cnx = null;

    public TvaService() {
        cnx = DbConnexion.getInstance().getConnection();
    }
    public List<Tva> getTva() {
        List<Tva> Tvas = new ArrayList();
        String req = "select * from tva";
        try {
            Statement statement = cnx.createStatement();
            ResultSet resultat = statement.executeQuery(req);

            while (resultat.next()) {
                int id = resultat.getInt("id");
                String nom = resultat.getString("nom");
               double multiplicate = resultat.getDouble("multiplicate");
                double valeur = resultat.getDouble("valeur");
               
                Tva t  = new Tva(id,nom,valeur,multiplicate);
                System.out.println(t.toString());
                Tvas.add(t);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Tvas;
    }

    
}
