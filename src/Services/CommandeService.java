/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Commande;
import Entities.LigneCommande;
import Entities.Produit;
import java.sql.Connection;
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
public class CommandeService {
    private final Connection db;
    private PreparedStatement preparedStmt;
    
    public CommandeService() throws SQLException {
        this.db = DbConnexion.getInstance().getConnection();
}
     public List<Commande> Display_User_Commande(int idUser) {
        try {
            String query = "SELECT * FROM commande WHERE idUtilisateur = ? ";

            preparedStmt = db.prepareStatement(query);
            preparedStmt.setInt(1, idUser);

            ResultSet result = preparedStmt.executeQuery();

            List<Commande> list = new ArrayList<>();
            while (result.next()) {

                Commande C = new Commande();
                C.setValider(result.getBoolean("Valider"));
                C.setIdCommande(result.getInt("id"));
                C.setNom(result.getString("Nom"));
                LigneCommandeService LCS  =  new LigneCommandeService();
                List<LigneCommande> list_lcs = LCS.Display_Commande(C.getIdCommande());
                for(LigneCommande item_lc :list_lcs)
                {
                    ProduitService PS = new ProduitService();
                    Produit P1 =PS.Get_Produit(item_lc.getIdProduit());
                    
                    C.setTotal(C.getTotal()+(item_lc.getQuantite()*P1.getPrix()));
                    
                }

                list.add(C);

            }
            return list;
        } catch (SQLException ex) {
            Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
     }
    public void Delete(Commande t) {
        try {
            String query = "delete from commande where idCommande = ?";
            preparedStmt = db.prepareStatement(query);
            preparedStmt.setInt(1, t.getIdCommande());

            preparedStmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
   
    public List<Commande> AfficherCommandes() {
        List<Commande> Commandes = new ArrayList();
        String req = "select * from commande";
        try {
            Statement statement = db.createStatement();
            ResultSet resultat = statement.executeQuery(req);

            while (resultat.next()) {
                int idCommande= resultat.getInt("id");
                boolean valider = resultat.getBoolean("valider");
                
               int idUser = resultat.getInt("user_id");
                
                
            
               
                Commande c = new Commande(idCommande, valider,idUser);
                               Commandes.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Commandes;
    }
     public int Add_Commande(Commande t) {

        try {
            String query = "insert into commande (user_id)"
                    + " values ('" + t.getIdUtilisateur() + "')";

            Statement st = db.createStatement();
            int numero = st.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;

    }


}
