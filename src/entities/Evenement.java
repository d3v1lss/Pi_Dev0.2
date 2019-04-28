/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import javafx.collections.ObservableList;

/**
 *
 * @author Info-Sys
 */
public class Evenement {
    
    private int id;
    private int user_id;
    private int theme_id;
    private String nom;
    private String photo;
    private String discription; 
    private int nombreplaces;
    private Date datedebut ;
    private Date datefin;
  //  SimpleDateFormat datedebut = new SimpleDateFormat("yyyy-mm-dd");
   // SimpleDateFormat datefin = new SimpleDateFormat("yyyy-mm-dd");

    public Evenement( int user_id, int theme_id, String nom, String photo, String discription, int nombreplaces, Date datedebut, Date datefin) throws ParseException {
      //  this.id = id;
        this.user_id = user_id;
        this.theme_id = theme_id;
        this.nom = nom;
        this.photo = photo;
        this.discription = discription;
        this.nombreplaces = nombreplaces;
        this.datedebut = datedebut;
        this.datefin = datefin;
    }

    public Evenement(int id, String nom, String discription, int nombreplaces) {
        this.id = id;
        this.nom = nom;
        this.discription = discription;
        this.nombreplaces = nombreplaces;
    }

    public Evenement() {
    }

    public Evenement(int id, int theme_id, String nom, String photo, String discription, int nombreplaces) {
        this.id = id;
        this.user_id = user_id;
        this.theme_id = theme_id;
        this.nom = nom;
        this.photo = photo;
        this.discription = discription;
        this.nombreplaces = nombreplaces;
    }


   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getTheme_id() {
        return theme_id;
    }

    public void setTheme_id(int theme_id) {
        this.theme_id = theme_id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public int getNombreplaces() {
        return nombreplaces;
    }

    public void setNombreplaces(int nombreplaces) {
        this.nombreplaces = nombreplaces;
    }

    public Date getDatedebut() {
        return datedebut;
    }

    public void setDatedebut( Date datedebut) {
        this.datedebut = datedebut;
    }

    public Date getDatefin() {
        return datefin;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", user_id=" + user_id + ", theme_id=" + theme_id + ", nom=" + nom + ", photo=" + photo + ", discription=" + discription + ", nombreplaces=" + nombreplaces + ", datedebut=" + datedebut + ", datefin=" + datefin + '}';
    }

    public Evenement(int id, int user_id, int theme_id, String nom, String photo, String discription, int nombreplaces, Date datedebut, Date datefin) {
        this.id = id;
        this.user_id = user_id;
        this.theme_id = theme_id;
        this.nom = nom;
        this.photo = photo;
        this.discription = discription;
        this.nombreplaces = nombreplaces;
        this.datedebut = datedebut;
        this.datefin = datefin;
    }
    
    

   /* 
     public static String convert(java.sql.Date d) {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String text = df.format(d);
        return text;
    }
     */
    
     public java.sql.Date convert(String date) throws ParseException {

        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date date1 = sdf1.parse(date);
        java.sql.Date sqlDate = new java.sql.Date(date1.getTime());

        return sqlDate;
    }

   
    public Evenement(int theme_id, String nom, String photo, String discription, int nombreplaces, Date datedebut, Date datefin) {
        this.theme_id = theme_id;
        this.nom = nom;
        this.photo = photo;
        this.discription = discription;
        this.nombreplaces = nombreplaces;
        this.datedebut = datedebut;
        this.datefin = datefin;
    }

    public void setTheme_id(String theme) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setImage(String nameImage1) {
    }

    public Evenement( String nom, String discription, int nombreplaces) {
        this.nom = nom;
        this.discription = discription;
        this.nombreplaces = nombreplaces;
    }

    

   

    
    
    
}
