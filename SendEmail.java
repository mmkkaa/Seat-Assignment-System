
import java.util.*;  
import javax.mail.*;  
import javax.mail.internet.*;  
 
public class SendEmail  
{  
	
	static public void sendEmail () { 
      String to = SeatAssignment.getEmail(); 
      String from = "majan5110@gmail.com"; 
      String host = "localhost";  
  
     //Get the session object  
      Properties props = System.getProperties();  
      props.setProperty("mail.smtp.host", "smtp.gmail.com");
      props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
      props.setProperty("mail.smtp.socketFactory.fallback", "false");
      props.setProperty("mail.smtp.port", "465");
      props.setProperty("mail.smtp.socketFactory.port", "465");
      props.put("mail.smtp.auth", "true");
      props.put("mail.debug", "true");
      props.put("mail.store.protocol", "pop3");
      props.put("mail.transport.protocol", "smtp"); 
      
      Session  session = Session.getDefaultInstance(props, 
              new Authenticator(){
          protected PasswordAuthentication getPasswordAuthentication() {
             return new PasswordAuthentication(from, "!majan!91119609!");
          }});
      
     //compose the message  
      try{  
         MimeMessage message = new MimeMessage(session);  
         message.setFrom(new InternetAddress(from ));  
         message.setRecipients(Message.RecipientType.TO, 
                 InternetAddress.parse(to,false)); 
         message.setSubject("Booking Refrence");  
         message.setText("Dear passenger," + "\r\n" + "Your booking reference is: " + SeatAssignment.forgotRef());  
         message.setSentDate(new Date());
  
         // Send message  
         Transport.send(message);  
         System.out.println("message sent successfully....");  
  
      }catch (MessagingException mex) {mex.printStackTrace();}  
	}
}  
