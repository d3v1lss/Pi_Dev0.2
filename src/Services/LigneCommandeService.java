/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Commande;
import Entities.LigneCommande;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
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
public class LigneCommandeService {

    private final Connection db;
    private PreparedStatement preparedStmt;

    public LigneCommandeService() throws SQLException {
        this.db = DbConnexion.getInstance().getConnection();
    }

    public void Add(LigneCommande t) {
        try {
            String query = "insert into LigneCommande (idProduit,idCommande,Quantite) values (?,?,?)";

            preparedStmt = db.prepareStatement(query);
            preparedStmt.setInt(1, t.getIdProduit());
            preparedStmt.setInt(2, t.getIdCommande());
            preparedStmt.setInt(3, t.getQuantite());

            preparedStmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(LigneCommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<LigneCommande> Display_Commande(int idCommande) {
        try {
            String query = "SELECT * FROM LigneCommande WHERE idCommande = ? ";

            preparedStmt = db.prepareStatement(query);
            preparedStmt.setInt(1, idCommande);

            ResultSet result = preparedStmt.executeQuery();

            List<LigneCommande> list = new ArrayList<>();
            while (result.next()) {

                LigneCommande LC = new LigneCommande();
                LC.setQuantite(result.getInt("Quantite"));
                LC.setIdProduit(result.getInt("idProduit"));

                list.add(LC);

            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(LigneCommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<LigneCommande> Display_CommandeBack(int idCommande) {
        try {
            String query = "SELECT lc.quantite,p.nom,lc.dateAjout,p.photo FROM LigneCommande lc INNER JOIN produit p ON p.id=lc.idProduit WHERE lc.idCommande = ? ";

            preparedStmt = db.prepareStatement(query);
            preparedStmt.setInt(1, idCommande);

            ResultSet result = preparedStmt.executeQuery();

            List<LigneCommande> list = new ArrayList<>();
            while (result.next()) {
                LigneCommande LC = new LigneCommande(result.getString("nom"),result.getString("photo"),result.getInt("Quantite"),result.getDate("dateAjout"));
                list.add(LC);

            }
            for(LigneCommande l:list){
                System.out.println(l.toString());
            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(LigneCommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    

    
    

}
