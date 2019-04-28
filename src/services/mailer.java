/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author chabene
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class mailer {

    private static final String HOST = "in-v3.mailjet.com";
    private static final String FROM = "hchaichi-akrem@outlook.fr";
    private static final String LOGIN = "d24928702b8bf4a0267249863d91077a";
    private static final String PASS = "f768bbe77e99d64fae4da662876b43d9";

    public static void sendMail(String recipient, String title, String content) {
        Thread mailThread = new Thread(() -> {
            Transport transport = null;
            Properties properties = System.getProperties();
            properties.put("in-v3.mailjet.com", HOST);
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.port", "587");
            properties.put("mail.smtp.ssl.trust", "in-v3.mailjet.com");
            Session session = Session.getInstance(properties,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(LOGIN, PASS);
                        }
                    });
            MimeMessage message = new MimeMessage(session);
            try {
                message.setFrom(new InternetAddress("hchaichi-akrem@outlook.fr"));

                message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

                message.setSubject(title);

                message.setText(content);

                System.out.println("Tentative de connexion");

                transport = session.getTransport("smtp");
                transport.connect(HOST, LOGIN, PASS);

                System.out.println("Envoi de mail...");

                transport.sendMessage(message, message.getAllRecipients());

                System.out.println("Mail enovyé avecc succés.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        mailThread.start();
    }
}
