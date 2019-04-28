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
public class Publication {
    private int id;
    private int forum_id;
    private int user_id;
    private String contenu;
    private String titre;
     private String nom;
      private String nomForum;

    public Publication() {
    }

    public Publication(int forum_id, int user_id, String contenu, String titre) {
        this.forum_id = forum_id;
        this.user_id = user_id;
        this.contenu = contenu;
        this.titre = titre;
    }

    public Publication(int id, int forum_id, int user_id, String contenu, String titre) {
        this.id = id;
        this.forum_id = forum_id;
        this.user_id = user_id;
        this.contenu = contenu;
        this.titre = titre;
    }

    public Publication(int id, String contenu, String titre, String nom, String nomForum) {
        this.id = id;
        this.contenu = contenu;
        this.titre = titre;
        this.nom = nom;
        this.nomForum = nomForum;
    }

    public String getNom() {
        return nom;
    }

    public String getNomForum() {
        return nomForum;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNomForum(String nomForum) {
        this.nomForum = nomForum;
    }
    

    public Publication(int id, int forum_id, String contenu, String titre, String nom) {
        this.id = id;
        this.forum_id = forum_id;
        this.contenu = contenu;
        this.titre = titre;
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public int getForum_id() {
        return forum_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getContenu() {
        return contenu;
    }

    public String getTitre() {
        return titre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setForum_id(int forum_id) {
        this.forum_id = forum_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    @Override
    public String toString() {
        return "Publication{" + "id=" + id + ", forum_id=" + forum_id + ", user_id=" + user_id + ", contenu=" + contenu + ", titre=" + titre + '}';
    }
    
    
    
}
