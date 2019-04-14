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
 * @author Med Amine Bouarrouj
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
                    preparedStatement.setString(3, BCrypt.hashpw( p.getMdp(), BCrypt.gensalt()).replaceFirst("a", "y"));
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

    public int login(User p) {
        String req = "SELECT password from USER where username=?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = cnx.prepareStatement(req);
            preparedStatement.setString(1, p.getUsername());
            ResultSet resultat=preparedStatement.executeQuery();
            if(resultat.next()){// username existe
                String pwd=resultat.getString("password");
                if(BCrypt.checkpw(p.getMdp(), pwd.replaceFirst("y", "a"))){
                    return 0; //connected
                }else{//wrong password
                    return 1;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    public User logMeIn (String password ,String login){
        try {
            String req = "SELECT * from USER where username=? ";
            PreparedStatement preparedStatement =cnx.prepareStatement(req);
            preparedStatement.setString(1, login);
            System.out.println("login:"+login);
                    System.out.println("pass:"+password);
            ResultSet res= preparedStatement.executeQuery();
            if(res.next()){
                if(BCrypt.checkpw(password,res.getString("password").replaceFirst("y","a"))){
                //  public User(int Id, String Username, String Mdp, String Email,String role) {
               return new User(res.getInt("id"),res.getString("username"),res.getString("password").replaceFirst("y","a"),res.getString("email"),res.getString("roles")) ;
            }
                return null;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return null;
    }
  
}
