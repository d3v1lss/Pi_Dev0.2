/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Commande;
import Entities.LigneCommande;
import Entities.Produit;
import java.security.SecureRandom;
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

    static final String AB = "0123456789";

    static SecureRandom rnd = new SecureRandom();

    public String randomString(int len) {
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        return sb.toString();
    }

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
                C.setReference(result.getInt("reference"));
                LigneCommandeService LCS = new LigneCommandeService();
                List<LigneCommande> list_lcs = LCS.Display_Commande(C.getIdCommande());
                for (LigneCommande item_lc : list_lcs) {
                    ProduitService PS = new ProduitService();
                    Produit P1 = PS.Get_Produit(item_lc.getIdProduit());

                    C.setTotal(C.getTotal() + (item_lc.getQuantite() * P1.getPrix()));

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
        String req = "select c.id,c.reference,c.valider,u.username from commande c inner join user u on u.id=c.user_id";
        try {
            Statement statement = db.createStatement();
            ResultSet resultat = statement.executeQuery(req);

            while (resultat.next()) {
                int id = resultat.getInt("id");
                int reference = resultat.getInt("reference");
                boolean valider = resultat.getBoolean("valider");

                String user = resultat.getString("username");

                Commande c = new Commande(id,reference, valider, user);
                Commandes.add(c);
                System.out.println(c.toString());
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Commandes;
    }

    public int Add_Commande(Commande t) {

        try {
            String query = "insert into commande (user_id,reference)"
                    + " values ('" + t.getIdUtilisateur() + "'," + Integer.parseInt(this.randomString(5)) + ")";

            Statement st = db.createStatement();
            int numero = st.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                System.out.println(rs.getInt(1));
                return rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;

    }

    public void remove(int idCommande) {
       String req = "Delete from commande where id=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = db.prepareStatement(req);
            preparedStatement.setInt(1, idCommande);
            preparedStatement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateEtat(int idCommande) {
        try {
            String query = "update commande set valider=1 where id=?";

            preparedStmt = db.prepareStatement(query);
            preparedStmt.setInt(1, idCommande);

            preparedStmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(LigneCommandeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
