/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.util;

/**
 *
 * @author pathfinder
 */

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
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
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Calendar;
import java.util.TimeZone;
import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class Testcase {
  private static final Logger LOGGER = Logger.getAnonymousLogger();

    //private static final String SERVIDOR_SMTP = "smtp.office365.com";
    private static final String SERVIDOR_SMTP = "10.2.12.28";
    private static final int PORTA_SERVIDOR_SMTP = 587;
    private static final String CONTA_PADRAO = "karthigar@s4carlisle.com";
    private String SENHA_CONTA_PADRAO = "karthi2311";
    private final String from = "hr.chennai@s4carlisle.com";
    private String to = "";
    private final String subject = "Test";
    private final String messageContent = "test";

    //public static void main (String arg[]){
      //  new Testcase().sendEmailWithAttachments();

    //}

    public void sendEmailWithAttachments(String empname,String to,String filename,String monthAndY,String pass,String filenameForsheet3){
    to = to;
    pass ="karthi2311";
    SENHA_CONTA_PADRAO = pass;
     String from = "hr.chennai@s4carlisle.com";
     String host = "10.2.12.28";
     String  value = "";
     Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
      //getTime() returns the current date in default time zone
      Date date = calendar.getTime();
     int dayOfMonth = calendar.get(Calendar.MONTH);
     int dateOnly = calendar.get(Calendar.DAY_OF_MONTH);
     int dayAndmonth=calendar.get(calendar.MONTH);

     String[] monthName = { "January", "February", "March", "April", "May", "June", "July",
        "August", "September", "October", "November", "December" };

    Calendar cal = Calendar.getInstance();
    String month = monthName[cal.get(Calendar.MONTH)];

      dateOnly = dateOnly + 1;
     try {
         System.out.println ("filenameForsheet3"+filenameForsheet3);
      FileInputStream file = new FileInputStream(new File(filenameForsheet3));

            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(2);
             System.out.println("Sheet : "+ sheet.getSheetName());
             XSSFRow row = sheet.getRow(2);
             DataFormatter formatter = new DataFormatter();

                  //int cells = row.getPhysicalNumberOfCells();
            /* try{
	XSSFCell cell = row.getCell(0);
        if (cell.getCellType() == cell.CELL_TYPE_BLANK){
                 value = "Salary grievances, if any, will be addressed on "+dateOnly +" between 3.00 p.m. and 4.00 p.m.";
 }

 else{
                value = formatter.formatCellValue(cell);
                System.out.println("cell"+cell);

 }
             }
             catch (Exception e){

//XSSFCell cell = row.getCell(1);
//System.out.println("cell"+cell);
     e.printStackTrace();
     } */

     }
     catch (IOException e){



     e.printStackTrace();
     } 
  //filename = "C:/ScheduleExcel/Taylor_163132.xls";
     String msgText1 = "\nDear "+empname+",\n\nPlease find attached the payslip for the month of "+ monthAndY+".\n\n Salary grievances, if any, will be addressed on "+dateOnly+"th of "+month+" between 3.00 p.m. and 4.00 p.m. (or the next working day if is a holiday for any reason).\n\nRegards,\nHuman Resources";
     //String msgText1 ="\nDear "+empname+",\n\nKindly, Recall the earlier payslip sent to you.\n\nplease, find attached the correct one.";
     String msgText2="\n";
    String subject = "Pay slip of";

  // create some properties and get the default Session
     Properties props = System.getProperties();
     props.put("mail.smtp.host", host);

     final Session session = Session.getInstance(this.getEmailProperties(), new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(CONTA_PADRAO, SENHA_CONTA_PADRAO);
            }

        });

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




    public void sendEmail() {
        final Session session = Session.getInstance(this.getEmailProperties(), new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(CONTA_PADRAO, SENHA_CONTA_PADRAO);
            }

        });

        try {
            final Message message = new MimeMessage(session);
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setFrom(new InternetAddress(from));
            message.setSubject(subject);
            message.setText(messageContent);
            message.setSentDate(new Date());
            Transport.send(message);
        } catch (final MessagingException ex) {
            LOGGER.log(Level.WARNING, "Erro ao enviar mensagem: " + ex.getMessage(), ex);
        }
    }

    public Properties getEmailProperties() {
        final Properties config = new Properties();
        config.put("mail.smtp.auth", true);
        config.put("mail.smtp.startssl.enable", "true");
        config.put("mail.smtp.host", SERVIDOR_SMTP);
        config.put("mail.smtp.port", PORTA_SERVIDOR_SMTP);
        return config;
    }


//public void sendMail() {
//
//        //Setting up configurations for the email connection to the Google SMTP server using TLS
//
//        Properties props = new Properties();
//
//        props.put("mail.smtp.host", "true");
//
//        props.put("mail.smtp.starttsl.enable", "true");
//
//        props.put("mail.smtp.host", "smtp.gmail.com");
//
//        props.put("mail.smtp.port", "587");
//
//        props.put("mail.smtp.auth", "true");
//
//        //Establishing a session with required user details
//
//        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
//
//            protected PasswordAuthentication getPasswordAuthentication() {
//
//                return new PasswordAuthentication("gandhichelakanu06@gmail.com", "lineysha21");
//
//            }
//
//        });
//
//        try {
//
//            //Creating a Message object to set the email content
//
//            MimeMessage msg = new MimeMessage(session);
//
//            //Storing the comma seperated values to email addresses
//
//            String to = "hariprasathr@pagemajik.com";
//
//            /*Parsing the String with defualt delimiter as a comma by marking the boolean as true and storing the email
//
//            addresses in an array of InternetAddress objects*/
//
//            InternetAddress[] address = InternetAddress.parse(to, true);
//
//            //Setting the recepients from the address variable
//
//            msg.setRecipients(Message.RecipientType.TO, address);
//
//            //String timeStamp = new SimpleDateFormat("yyyymmdd_hh-mm-ss").format(new Date());
//
//            msg.setSubject("Sample Mail : ");
//
//            msg.setSentDate(new Date());
//
//            msg.setText("Sampel System Generated mail");
//
//            msg.setHeader("XPriority", "1");
//
//            Transport.send(msg);
//
//            System.out.println("Mail has been sent successfully");
//
//        } catch (MessagingException mex) {
//
//            System.out.println("Unable to send an email" + mex);
//
//        }
//
//    }
}