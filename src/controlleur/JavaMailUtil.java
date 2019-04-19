/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

/**
 *
 * @author Houssem
 */
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMailUtil {
    
    
    
    
    public static void sendMail(String recepient) throws Exception
    {
        System.out.println("envoyer debut");
        Properties properties = new Properties();
        

        //configuration
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");
        
        String monCompte =  "hbouzid80@gmail.com";
        String password =  "houssem12394";
        
        
        Session session =Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(monCompte, password);
            }         
        });
        
        Message message = prepareMessage(session , monCompte,recepient);
        
        //Message message = prepareMessage(session , monCompte,recepient );
        
        
        Transport.send(message);
        System.out.println("message envoyé");
        
        
    }

    private static Message prepareMessage(Session session, String monCompte,String recepient) {
        try{
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(monCompte));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
        message.setSubject("Test");
        message.setText("ye5dem");
        return message;
        } catch (Exception ex){
            Logger.getLogger(JavaMailUtil.class.getName()).log(Level.SEVERE , null ,ex);
                    }
        return null;
                    
    };
    
    
    
    
}
