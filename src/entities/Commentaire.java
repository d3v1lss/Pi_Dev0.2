/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Vladio
 */
public class Commentaire {
    private int id;
    private int publication_id;
    private String contenu;

    public Commentaire(String contenu) {
        this.contenu = contenu;
    }

    public Commentaire(int id, int publication_id, String contenu) {
        this.id = id;
        this.publication_id = publication_id;
        this.contenu = contenu;
    }
    private int user_id;

    public Commentaire(int id, int publication_id, String contenu, int user_id) {
        this.id = id;
        this.publication_id = publication_id;
        this.contenu = contenu;
        this.user_id = user_id;
    }

    public Commentaire() {
    }

    public Commentaire(int publication_id, String contenu, int user_id) {
        this.publication_id = publication_id;
        this.contenu = contenu;
        this.user_id = user_id;
    }

    public Commentaire(int aInt, int aInt0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public int getPublication_id() {
        return publication_id;
    }

    public String getContenu() {
        return contenu;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPublication_id(int publication_id) {
        this.publication_id = publication_id;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "id=" + id + ", publication_id=" + publication_id + ", contenu=" + contenu + ", user_id=" + user_id + '}';
    }
    
    
    
}
