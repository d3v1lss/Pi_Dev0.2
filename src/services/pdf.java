/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.itextpdf.text.Document;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;

/**
 *
 * @author HCHAICHI
 */
public class pdf {

    public void creation() {

        try {

            String nom = "C:\\Users\\HCHAICHI\\Downloads\\PDFJAVA\\ListeWorkshops.pdf";
            Document doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream(nom));

            doc.open();

            Paragraph paragraph = new Paragraph("SHOPETAL");
            doc.add(paragraph);
            System.out.println("test");
            doc.close();

        } catch (Exception e) {
            System.err.println(e);
        }

    }

}
