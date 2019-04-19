/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.User;
import utils.DbConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Dorra
 */
public class UserService {

    Connection cnx = null;
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserService() {
        cnx = DbConnexion.getInstance().getConnection();
    }

    public int insert(User p) {
        String req = "INSERT INTO user (username,email,password) VALUES (?,?,?);";
        String req2 = "SELECT username FROM user where username=?";
        String req3 = "SELECT email from user where email=?";
        String req4 = "SELECT roles from user where email=?";

        try {

            PreparedStatement preparedStatment2 = cnx.prepareStatement(req2);
            preparedStatment2.setString(1, p.getUsername());

            ResultSet resultat = preparedStatment2.executeQuery();

            if (!resultat.next()) {
                PreparedStatement preparedStatment3 = cnx.prepareStatement(req3);
                preparedStatment3.setString(1, p.getEmail());
                ResultSet resultat2 = preparedStatment3.executeQuery();

                if (!resultat2.next()) {
                    PreparedStatement preparedStatement = cnx.prepareStatement(req);
                    preparedStatement.setString(1, p.getUsername());
                    preparedStatement.setString(2, p.getEmail());
                    preparedStatement.setString(3, BCrypt.hashpw(p.getMdp(), BCrypt.gensalt()).replaceFirst("a", "y"));
                    preparedStatement.executeUpdate();
                   
                    return 0;
                } else {
                    return 2;
                }

            } else {
                return 1;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    public User logMeIn(String password, String login) {
        try {
            String req = "SELECT * from USER where username=? ";
            PreparedStatement preparedStatement = cnx.prepareStatement(req);
            preparedStatement.setString(1, login);
            System.out.println("login:" + login);
            System.out.println("pass:" + password);
            ResultSet res = preparedStatement.executeQuery();
            if (res.next()) {
                if (BCrypt.checkpw(password, res.getString("password").replaceFirst("y", "a"))) {
                    //  public User(int Id, String Username, String Mdp, String Email,String role) {
                    return new User(res.getInt("id"), res.getString("username"), res.getString("password").replaceFirst("y", "a"), res.getString("email"), res.getString("roles"));
                }
                return null;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return null;
    }

    public boolean UserCode(int code, String mail) {
        String req1 = "Select email from user where email='" + mail + "'";

        String req2 = "UPDATE user SET confirmation_token='" + code + "' Where email='" + mail + "'";
        try {
            PreparedStatement preparedStatment2 = cnx.prepareStatement(req1);

            ResultSet rs = preparedStatment2.executeQuery();
            if (rs.next()) {
                cnx.createStatement().execute(req2);
                return true;
            } else {
                return false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public boolean VerifCode(int code, String mail) {
        String req2 = "Select username from user where email='" + mail + "' And confirmation_token='" + code + "'";
        try {
            PreparedStatement preparedStatment2 = cnx.prepareStatement(req2);
            ResultSet rs = preparedStatment2.executeQuery();
           
            if (rs.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean verifierToken(User u) throws SQLException {

        String req = "Select confirmation_token from user where confirmation_token=? and id=?";
        PreparedStatement pstm = cnx.prepareStatement(req);
        pstm.setString(1, u.getConfirmationToken());
        pstm.setInt(2, u.getId());
        ResultSet result = pstm.executeQuery();

        if (!result.next()) {
            return false;
        } else {
            String req2 = "Update user Set enabled= 1 , confirmation_token= null where id=?";
            PreparedStatement pstm2 = cnx.prepareStatement(req2);
            pstm2.setInt(1, u.getId());
            pstm2.executeUpdate();

            return true;
        }
    }

    public boolean NouveauMdp(String mail, String mdp) {
        String req2 = "UPDATE user SET 	password='" + BCrypt.hashpw(mdp, BCrypt.gensalt()).replaceFirst("a", "y") + "' Where email='" + mail + "'";
        try {
            cnx.createStatement().execute(req2);

            return true;

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public String getName(int id) {

        String req2 = "SELECT username FROM user where id=?";

        try {

            PreparedStatement preparedStatment2 = cnx.prepareStatement(req2);
            preparedStatment2.setInt(1, id);

            ResultSet resultat = preparedStatment2.executeQuery();

            if (resultat.next()) {
                return resultat.getString("username");
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Object StatsNbrUser(int mois) {
        String req2 = "select count(*) from lignecommande WHERE month(dateAjout)='" + mois + "'";
        try {
            PreparedStatement preparedStatment2 = cnx.prepareStatement(req2);

            ResultSet rs = preparedStatment2.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }

    }
    
    public User getIdUser(String username){
        String req2 = "SELECT * FROM user where username=?";

        try {

            PreparedStatement preparedStatment2 = cnx.prepareStatement(req2);
            preparedStatment2.setString(1, username);

            ResultSet resultat = preparedStatment2.executeQuery();

            if (resultat.next()) {
                int id=resultat.getInt("id");
                String conf=resultat.getString("confirmation_token");
                User u=new User();
                u.setConfirmationToken(conf);
                u.setId(id);
                return u;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public User getUser(int idU) {
         String req2 = "SELECT * FROM user where id=?";

        try {

            PreparedStatement preparedStatment2 = cnx.prepareStatement(req2);
            preparedStatment2.setInt(1, idU);

            ResultSet resultat = preparedStatment2.executeQuery();

            if (resultat.next()) {
        
                String conf=resultat.getString("confirmation_token");
                User u=new User(resultat.getString("nom"),resultat.getString("prenom"),resultat.getString("adresse"),resultat.getString("numero"),resultat.getString("Email"),resultat.getString("username"));
                u.setConfirmationToken(conf);
                u.setId(id);
                return u;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void updateUser(User u) {
        String req2 = "UPDATE user SET 	nom='" + u.getNom()+ "' , prenom='"+u.getPrenom()+ "', numero='"+u.getTelephone()+"', adresse='"+u.getAdresse()+"' Where id='" + u.getId() + "'";
        try {
            cnx.createStatement().execute(req2);


        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
