/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Med Amine Bouarrouj
 */
public class User {

    private int Id;
    private String Username;
    private String Mdp;
    private String Email;

    public User(String Username, String Mdp, String Email) {
        this.Username = Username;
        this.Mdp = Mdp;
        this.Email = Email;
    }

    public User(int Id, String Username, String Mdp, String Email) {
        this.Id = Id;
        this.Username = Username;
        this.Mdp = Mdp;
        this.Email = Email;
    }

    public User() {
    }

    public User(String Username, String Mdp) {
        this.Username = Username;
        this.Mdp = Mdp;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getMdp() {
        return Mdp;
    }

    public void setMdp(String Mdp) {
        this.Mdp = Mdp;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    @Override
    public String toString() {
        return "User{" + "Id=" + Id + ", Username=" + Username + ", Mdp=" + Mdp + ", Email=" + Email + '}';
    }

}
