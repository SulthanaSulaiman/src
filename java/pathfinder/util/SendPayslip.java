/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.util;

/**
 *
 * @author pathfinder
 */

import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
 public class SendPayslip {
    public void sendEmailWithAttachments(String empname,String to,String filename,String monthAndY){
    to = to;
  String from = "hr.chennai@s4carlisle.com";
  String host = "10.2.12.28";
  //filename = "C:/ScheduleExcel/Taylor_163132.xls";
  //String msgText1 = "\nDear "+empname+",\n\nPlease find attached the payslip for the month of "+ monthAndY+".\n\nSalary grievances, if any, will be addressed on 7th of every month between 3.00 p.m. and 4.00 p.m. (or the next working day if 7th is a holiday for any reason)\n\nRegards,\nHuman Resources";
  String msgText1 = "\nDear "+empname+",\n\nPlease find attached the payslip for the month of "+ monthAndY+".\n\nSalary grievances, if any, will be addressed on 8th of "+monthAndY+" between 3.00 p.m. to 4.00 p.m.\n\nRegards,\nHuman Resources";
  //String msgText1 ="\nDear "+empname+",\n\nKindly, Recall the earlier payslip sent to you.\n\nplease, find attached the correct one.";
  String msgText2="\n";
  String subject = "Pay slip of";

  // create some properties and get the default Session
  Properties props = System.getProperties();
  props.put("mail.smtp.host", host);

  Session session = Session.getInstance(props, null);

  try
  {
      // create a message
      MimeMessage msg = new MimeMessage(session);
      msg.setFrom(new InternetAddress(from));
      InternetAddress[] address = {new InternetAddress(to)};
      msg.setRecipients(Message.RecipientType.TO, address);
      msg.setSubject(subject+" "+monthAndY);

      // create and fill the first message part
      MimeBodyPart mbp1 = new MimeBodyPart();
      mbp1.setText(msgText1+msgText2);


      // create the second message part
      MimeBodyPart mbp2 = new MimeBodyPart();

            // attach the file to the message
         FileDataSource fds = new FileDataSource(filename);
      mbp2.setDataHandler(new DataHandler(fds));
      mbp2.setFileName(fds.getName());

      // create the Multipart and add its parts to it
      Multipart mp = new MimeMultipart();
      mp.addBodyPart(mbp1);
      mp.addBodyPart(mbp2);

      // add the Multipart to the message
      msg.setContent(mp);

      // set the Date: header
      msg.setSentDate(new Date());

      // send the message
      Transport.send(msg);

  }
  catch (MessagingException mex)
  {
      mex.printStackTrace();
      Exception ex = null;
      if ((ex = mex.getNextException()) != null) {
    ex.printStackTrace();
      }
    }
}
}
