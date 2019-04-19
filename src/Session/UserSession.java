/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author zorgati
 */
 public final class UserSession {

    private static UserSession instance;
    
    private int id;
    private String userName;
    private int role;

    private UserSession(int id,String userName, int role) {
        this.id= id;
        this.userName = userName;
        this.role = role;
    }

    public static UserSession getInstace(int id,String userName, int role) {
        if(instance == null) {
            instance = new UserSession(id,userName, role);
        }
        return instance;
    }

    public String getUserName() {
        return userName;
    }

    public int getPrivileges() {
        return role;
    }
    public int getId() {
        return id;
    }

    

    public void cleanUserSession() {
        id=0;
        userName = "";// or null
        role = 0;// or null
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "id='" + id + '\'' +
                "userName='" + userName + '\'' +
                ", privileges=" + role +
                '}';
    }
}
