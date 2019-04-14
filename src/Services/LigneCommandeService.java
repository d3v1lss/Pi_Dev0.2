/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import Entities.Commande;
import Entities.LigneCommande;
//import Utils.DBConnection;
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
 * @author ignitedev
 */
public class LigneCommandeService implements DBService<LigneCommande> {

    private final Connection db;
    private PreparedStatement preparedStmt;

    public LigneCommandeService() throws SQLException {
        this.db =DbConnexion.getInstance().getConnection();
    }

    @Override
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

    @Override
    public List<LigneCommande> DisplayAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Delete(LigneCommande t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Update(LigneCommande t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    public List<LigneCommande> Display_Commande(int idCommande) throws SQLException
    {
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
    
    }
    
    
    /* public List<LigneCommande> rechercherProduitEtQuantite(Date date){
         List<LigneCommande> lignecommandes = new ArrayList<>();
        try {
           
            PreparedStatement statement = db.prepareStatement("SELECT SUM(L.quantite),P.nom from lignecommande L JOIN produit P on L.idProduit = P.idProduit Join commande C on L.idCommande= C.idCommande where C.dateAjout = ? group by P.nom ");    
            statement.setDate(1, date);    
            
           
            ResultSet rest = statement.executeQuery();
            while (rest.next()) {
                LigneCommande l = new LigneCommande();
              
                l.setQuantiteTotale(rest.getInt(1));
                 l.setLibelle(rest.getString(2));
                lignecommandes.add(l);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lignecommandes;
     }*/
     public List<LigneCommande> rechercherProduitEtQuantite(){
         List<LigneCommande> lignecommandes = new ArrayList<>();
        try {
           
            PreparedStatement statement = db.prepareStatement("SELECT SUM(L.quantite),P.nom from lignecommande L JOIN produit P on L.idProduit = P.idProduit group by P.nom ");    
   
            
           
            ResultSet rest = statement.executeQuery();
            while (rest.next()) {
                LigneCommande l = new LigneCommande();
              
                l.setQuantiteTotale(rest.getInt(1));
                 l.setLibelle(rest.getString(2));
                lignecommandes.add(l);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lignecommandes;
     }
     
      
     

}
