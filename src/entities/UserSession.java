/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author zorgati
 */
public final class UserSession {

    private static UserSession instance;

    private int id;
    private String userName;
    private String role;

    public static UserSession getInstace() {
        if (instance == null) {
            instance = new UserSession();
        }
        return instance;
    }

    private UserSession(int id, String userName, String role) {
        this.id = id;
        this.userName = userName;
        this.role = role;
    }

    public static UserSession getInstace(int id, String userName, String role) {
        if (instance == null) {
            instance = new UserSession(id, userName, role);
        }
        return instance;
    }

    private UserSession() {
        userName = "";
        role = "";
        id = 0;
    }

    public String getUserName() {
        return userName;
    }

    public String getPrivileges() {
        return role;
    }

    public int getId() {
        return id;
    }

    public void cleanUserSession() {
        id = 0;
        userName = "";// or null
        role = "";// or null
    }

    @Override
    public String toString() {
        return "UserSession{"
                + "id='" + id + '\''
                + "userName='" + userName + '\''
                + ", privileges=" + role
                + '}';
    }

   
}
