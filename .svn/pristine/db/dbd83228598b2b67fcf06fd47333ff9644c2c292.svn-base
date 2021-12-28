package pathfinder.util;
import java.util.*;
import connection.DBconnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Properties;
import javax.mail.BodyPart;
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

/**
 *
 * @author Aravindhanj
 */
public class MailerDAO {

    ArrayList<String> recipientList = new ArrayList<String>();
    //HashSet<String> recipientList=new HashSet<String>();
//test
    public Session getMailConnection() {

        Properties props = new Properties();
        props.put("mail.smtp.host", "10.2.12.28");
        props.put("mail.smtp.auth", "false");
        props.put("mail.smtp.port", "25");


        //String[] to = {"gandhimathidevic@s4carlisle.com","aravindhanj@s4carlisle.com"};
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("pathfinder.report", "AsTaPJL0");
                    }
                });
            return session;
    }

    public boolean sendReportMail(MailerVO mailVO) {

        String projName = "";
        String stageName="";
        String chaptersName = "";
        Session session = getMailConnection();

        SimpleDateFormat dateFormat =  new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();
        String currentDay = dateFormat.format(today);

//        System.out.println("sendReportMail - Recipients List : " + recipientList);
//        recipientList.clear();
//        recipientList.add("karthigar@s4carlisle.com");


        try {


            projName = mailVO.getProjName();
            stageName = mailVO.getStageName();
            chaptersName = mailVO.getChapters();

            Message message = new MimeMessage(session);
            Multipart multipart = new MimeMultipart();
            BodyPart messageBodyPart;

            message.setFrom(new InternetAddress("pathfinder.report@s4carlisle.com"));

            InternetAddress[] addressTo = new InternetAddress[recipientList.size()];
            for (int i = 0; i < recipientList.size(); i++) {
                addressTo[i] = new InternetAddress(recipientList.get(i));
                }

            message.setRecipients(Message.RecipientType.TO, addressTo);
            message.setSubject("Today's Due-"+projName+"-"+stageName);



            messageBodyPart = new MimeBodyPart();
            String msgContent = ""
                    + "<html>"
                        + "<body>"
                            + "<font face='Calibri'><p>Hi,</p>"
                            + "<p>The Below Batches are scheduled to dispatch today<i><b>("+currentDay+")</b></i></p>"
                            + "<table border='1'><tr style='font-weight:bold'><th>Project Name</th><th>Stage</th><th>Chapters</th></tr>"
                            + "<tr><td>"+projName+"</td><td>"+stageName+"</td><td>"+chaptersName+"</td></tr></table><br>"
                            + "Regards,<br>"
                            + "Pathfinder Team<br><br></font>"
                            + "<p><font color=\"gray\"><i>Note :</i> This is an automated mail and please do not reply.</font></p>"
                        + "</body>"
                    + "</html>";
            messageBodyPart.setContent(msgContent,"text/html");
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);
            Transport.send(message);

        } catch (MessagingException e) {
            System.out.println("MessagingException: " + e);
        } finally {
            return true;
        }
    }

    public List getRecipientList(MailerVO mailVO) {

        String sql="";
        String sql1="";
        String cellcode="";
        String cellcode1="";
        String projId = mailVO.getProjId().toString();
        try {
            DBconnection conToDB = new DBconnection();
            Connection con = conToDB.getSampleProperty();
            Statement st = con.createStatement();
            ResultSet rs;
            sql1 = "select cell_code from projects where proj_id='" + projId + "'";
            rs = st.executeQuery(sql1);
            while (rs.next()) {
                cellcode = rs.getString(1);
            }
                  sql = "select u.emp_id, u.desig_code, p.proj_name, u.mailid "
                    + " from projects p, department dt, user u, designation dg,cell c "
                    + " where ((p.dept_code=dt.dept_code and u.dept_code=p.dept_code and p.cell_code=u.cell_code_for_mail)) and u.desig_code=dg.desig_code and proj_id='" + projId + "' "
                    + " and (u.desig_code='PDNMGR' OR u.desig_code='TL') and u.status='1'";
                rs = st.executeQuery(sql);
             System.out.println("sql3"+sql);
                while (rs.next()) {
                    if(rs.getString(4)!=null && !rs.getString(4).equals("") && !recipientList.contains(rs.getString(4).toString())) {
                        recipientList.add(rs.getString(4));
                    }
                }
        } catch (Exception e) {
            System.out.println("The Exception in getRecipientList() of MailerDAO()" + e);
        }
        return recipientList;
    }

    public int sendBatchTicketMail(MailerVO mailVO) {

        String projName = "";
        String stageName="";
        String note="";
        List chapterIdList = new ArrayList();
        List chapterNameList = new ArrayList();
        List chapterDueDateList = new ArrayList();
        List chapterNotesFlagList = new ArrayList();
        List chaptermssPages = new ArrayList();
        List chapterArtCount = new ArrayList();

         List note1 = new ArrayList();
         List chapRemarkList=new ArrayList();

        Session session = getMailConnection();

        SimpleDateFormat dateFormat =  new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();
        String dueDateStr = "";
        boolean todayBatch = false;
        Date dueDate = new Date();

        getBatchTicketRecipientList(mailVO);

//        System.out.println("sendBatchTicketMail - Recipients List : " + recipientList);
//        recipientList.clear();
//        recipientList.add("karthigar@s4carlisle.com");
       try {
             DBconnection conToDB = new DBconnection();
            Connection con = conToDB.getSampleProperty();
            Statement st = con.createStatement();


            projName = mailVO.getProjName();
            stageName = mailVO.getStageName();
            chapterIdList = mailVO.getChapterIdList();
            chapterNameList = mailVO.getChapterNameList();
              //note=mailVO.getChapRemark();
            chapterDueDateList = mailVO.getChapterDueDateList();
            chapterNotesFlagList = mailVO.getChapterNotesFlagList();
            chaptermssPages  = mailVO.getMssPageList();
            chapterArtCount  = mailVO.getArtCountList();
            chapRemarkList=mailVO.getChapterIdList();
            Message message = new MimeMessage(session);
            Multipart multipart = new MimeMultipart();
          
            BodyPart messageBodyPart;
            message.setFrom(new InternetAddress("pathfinder.report@s4carlisle.com"));
//System.out.println("stageNamess"+stageName);
for (int i=0; i <recipientList.size();i++){
                for (int j=i+1; j <recipientList.size();j++){
                if(recipientList.get(i).equals(recipientList.get(j))){
                recipientList.remove(i);
                }
                }
                }
            InternetAddress[] addressTo = new InternetAddress[recipientList.size()];
            for (int i = 0; i < recipientList.size(); i++) {
                addressTo[i] = new InternetAddress(recipientList.get(i));
            }
              String client_code="";
             String sql="SELECT c.company FROM projects p LEFT JOIN contacts c ON p.client_name=c.contact_id WHERE p.proj_name='"+projName+"'";
             ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {

                    client_code = rs.getString(1).toString();
                }

             // String note1="";
                for(int idx=0;idx<chapterIdList.size();idx++){
             String sql1="SELECT c.remark FROM chapter c left join projects p on p.proj_id=c.proj_id WHERE p.proj_name='"+projName+"'and c.chapter_id='"+chapterIdList.get(idx)+"'";
           
               System.out.println("SQL1"+sql1);
                 ResultSet rs1 = st.executeQuery(sql1);
           while (rs1.next()) {

                    note1.add(rs1.getString(1).toString());
                }
            
            System.out.println("clientnote1"+note1);
                }
            // System.out.println("client Name"+note1);
            message.setRecipients(Message.RecipientType.TO, addressTo);
            message.setSubject(projName+" - "+stageName+" - Scheduled in Pathfinder");
            messageBodyPart = new MimeBodyPart();
            String msgContent = ""
                    + "<html>"
                    + "<body>"
                    + "<font face='Calibri'><p>Hi,</p>"
                    + "<p>The Below Batches are scheduled</p>"
                    + "<p>Project Name: <b>"+projName+"</b></p>"
                    +"<p>Client Name:<b>"+client_code+"</b></p>"
                    + "<p>Stage: <b>"+stageName+"</b></p>"

                    + "<table border='1'>"
                    + "<tr style='font-weight:bold'><th>Chapter</th><th>Mss Page</th>";
                    if(stageName.contains("Illustration")){
                        msgContent += "<th>Art Count</th>";
                    }
                    msgContent +=  "<th>Due Date</th>";
                    msgContent += "<th>Note</th></tr>";
                

            for(int idx=0; idx<chapterNameList.size(); idx++) {
                if(chapterNotesFlagList.get(idx).equals("1")) {
                    msgContent += "<tr><td><a href=\"http://10.1.1.5:8080/pathfinderv2/BatchTicketReport.jsp?chapId=" + chapterIdList.get(idx).toString() + "\">"
                            + ""+chapterNameList.get(idx).toString()+"</a></td>";
                } else {
                    msgContent += "<tr><td>"+chapterNameList.get(idx).toString()+"</td>";
                }

               dueDateStr = chapterDueDateList.get(idx).toString();
            //    System.out.println("dueDateStr"+dueDateStr);
                try {
                    dueDate = dateFormat.parse(dueDateStr);
                    if(dueDate.equals(today) || dueDate.before(today)) {
                        todayBatch = true;
                    } else {
                        todayBatch = false;
                    }
                } catch (ParseException e) {
                    System.out.println("MailerDAO - sendBatchTicketMail() : " + e);
                }
try
                {

                if((chaptermssPages.get(idx).toString().equals("")||chaptermssPages.get(idx).toString()==null)) {

                    msgContent += "<td><font color='RED'>page missing</font></td>";
                } else {
                    msgContent += "<td>"+chaptermssPages.get(idx).toString()+"</td>";
                }
                 if(stageName.contains("Illustration")){
                        msgContent += "<td>"+chapterArtCount.get(idx).toString()+"</td>";
                    }
}
                 catch (Exception e) {
                    System.out.println("MailerDAO - pages : " + e);
                }
                if(todayBatch) {
                    msgContent += "<td><font color='RED'>"+chapterDueDateList.get(idx).toString()+"</font></td>";
                    msgContent += "<td>"+note1.get(idx).toString()+"</td></tr>";
                } else {
                    msgContent += "<td>"+chapterDueDateList.get(idx).toString()+"</td>";
                    msgContent += "<td>"+note1.get(idx).toString()+"</td></tr>";

                }
            }
            msgContent +=  "</table><br>"
                    + "Regards,<br>"
                    + "Pathfinder Team<br><br></font>"
                    + "<p><font color=\"gray\"><i>Note :</i> This is an automated mail and please do not reply.</font></p>"
                    + "</body>"
                    + "</html>";

            messageBodyPart.setContent(msgContent,"text/html");
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);
            Transport.send(message);

            mailVO.setRecipientList(recipientList);
            //System.out.println("test"+mailVO.getRecipientList());
        } catch (MessagingException e) {
            System.out.println("MessagingException: " + e);
        } catch (Exception e) {
            //System.out.println("Exception: " + e);
            e.printStackTrace();
        }  finally {
            return 1;
        }
    }

    public void getBatchTicketRecipientList(MailerVO mailVO) {
        String sql="";
        String stage = mailVO.getStage();
        String projId = mailVO.getProjId();
        String empId = mailVO.getEmpId();
        String projDeptCode = mailVO.getProjDeptCode();
        String stagelocationCode = "";
        String clientcode = mailVO.getClientName();
        boolean notaspen = false;
        boolean projectTeam = false;
        pathfinder.functions.projects.chapters.ChapterAddDetails chapterAddDet1 = new pathfinder.functions.projects.chapters.ChapterAddDetails();
        chapterAddDet1.SetStageproj_id(projId);
        chapterAddDet1.SetStageforLoc(stage);
        stagelocationCode=chapterAddDet1.getStagemappedToLocation().toString();
        try {
            DBconnection conToDB = new DBconnection();
            Connection con = conToDB.getSampleProperty();
            Statement st = con.createStatement();
            ResultSet rs;
            sql = "SELECT dept_group FROM department_group WHERE dept_code='" + projDeptCode + "'";
            rs = st.executeQuery(sql);
            boolean deptGroup = false;
            String deptGroupId = "";
            while (rs.next()) {
                if(rs.getString(1)!=null) {
                    deptGroup = true;
                    deptGroupId = rs.getString(1).toString();
                    // If any new department groups needed, then we could use this deptGroupId in below query to filter out the appropriate recipients accordingly
                }
            }
            String sqlclie = "SELECT client_name FROM projects WHERE proj_id='" + projId + "'";
            rs = st.executeQuery(sqlclie);
            while (rs.next()) {
                if(rs.getString(1)!=null) {
                    clientcode = rs.getString(1).toString();
                }
            }
           String clientcodechk = "SELECT DISTINCT (client_id) FROM clientchkfor_mail WHERE client_id IS NOT NULL and client_status=1";
            boolean chkmailclient = false;
            rs =st.executeQuery(clientcodechk);
            while (rs.next()){
                System.out.print("clientcode"+clientcode);

                    if (rs.getString(1).equals(clientcode)){
                        chkmailclient=true;
                    }
            }
           String stagecodechk = "SELECT DISTINCT (stage_code) FROM clientchkfor_mail WHERE client_id IS NULL and stage_status=1";
           boolean chkmailStage = false;
           while (rs.next()){
                    if (rs.getString(1).equals(stage)){
                        chkmailStage=true;
                    }
            }

//            if((stage.equals("CYD") || stage.equals("EDPF")||stage.equals("IDXNG")) && stagelocationCode.equals("OnShore"))  {
//                 sql = "SELECT ps.stage_code, ps.stage, smm.mailid_type,"
//                    + " case"
//                    + "  WHEN mailid_type='0' THEN 0 "
//                    + "  WHEN mailid_type='2' THEN (SELECT mail_group FROM department WHERE dept_code=smm.dept_code)"
//                    + "  WHEN mailid_type='3' THEN (SELECT mail_group FROM cell WHERE mail_group=smm.cell_code)"
//                    + "  WHEN mailid_type='4' THEN smm.other "
//                    + "  end as MailId "
//                    + " FROM project_stage ps, stage_mail_map smm where ps.stage_code=smm.stage_code "
//                    + " and ps.stage_code='" + stage + "' ";
//            }

             //if(!clientcode.equals("10657")||clientcode.equals("10658")||clientcode.equals("197")||clientcode.equals("198")||clientcode.equals("2211"))
            if (!chkmailclient)
             {
                if(!chkmailStage)
               // if (!stage.equals("DES") || stage.equals("DESCXS")){
                {
 if(stage.equals("FP")||stage.equals("DES")||stage.equals("RP")||stage.equals("EDPFCXS")||stage.equals("PDF")||stage.equals("PRN")||stage.equals("ART")||stage.equals("ARTCXN")||stage.equals("ARC")){
                sql = "SELECT ps.stage_code, ps.stage, smm.mailid_type,"
                    + " case"
                    + "  WHEN mailid_type='0' THEN 0 "
                    + "  WHEN mailid_type='1' THEN (SELECT mailid from user where emp_id=smm.emp_id)"
                    + "  WHEN mailid_type='2' THEN (SELECT mail_group FROM department WHERE dept_code=smm.dept_code)"
                    + "  WHEN mailid_type='3'"
                    + " THEN (SELECT mail_group FROM cell WHERE mail_group=smm.cell_code)"
                    + "  WHEN mailid_type='4' THEN smm.other "
                    + "  end as MailId "
                    + " FROM project_stage ps, stage_mail_map smm where ps.stage_code=smm.stage_code "
                    + " and ps.stage_code='" + stage + "'"
                    + "AND dept_code='"+projDeptCode+"'" ;
            System.out.println("test"+sql);
            rs = st.executeQuery(sql);
            while (rs.next()) {
                if(rs.getString(4)!=null && !rs.getString(4).equals("")) {
                    if(rs.getString(4).equals("0")) {
                        projectTeam = true;
                    } else {
                        if(!recipientList.contains(rs.getString(4).toString())) {
                            recipientList.add(rs.getString(4));
                        }
                    }
                }
            }
                 }else{
      sql = "SELECT ps.stage_code, ps.stage, smm.mailid_type,"
                    + " case"
                    + "  WHEN mailid_type='0' THEN 0 "
                    + "  WHEN mailid_type='1' THEN (SELECT mailid from user where emp_id=smm.emp_id)"
                    + "  WHEN mailid_type='2' THEN (SELECT mail_group FROM department WHERE dept_code=smm.dept_code)"
                    + "  WHEN mailid_type='3'"
                    + " THEN (SELECT mail_group FROM cell WHERE mail_group=smm.cell_code)"
                    + "  WHEN mailid_type='4' THEN smm.other "
                    + "  end as MailId "
                    + " FROM project_stage ps, stage_mail_map smm where ps.stage_code=smm.stage_code "
                    + " and ps.stage_code='" + stage + "'";
                 //  + "AND dept_code='"+projDeptCode+"'OR dept_code=" ;
            System.out.println("test1"+sql);
            rs = st.executeQuery(sql);
             while (rs.next()) {
                if(rs.getString(4)!=null && !rs.getString(4).equals("")) {
                    if(rs.getString(4).equals("0")) {
                        projectTeam = true;
                    } else {
                        if(!recipientList.contains(rs.getString(4).toString())) {
                            recipientList.add(rs.getString(4));
                        }
                    }
                }
            }
                 }
                }
      //  }
 //else if (stage.equals("DES") || stage.equals("DESCXS")){
                 else if(chkmailStage)
                notaspen = true;
                sql = "SELECT mail_id from stage_mail_map_client where stage_code='" + stage + "' AND "
                        + "client_id is null AND dept_code='"+projDeptCode+"'";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                if(rs.getString(1)!=null && !rs.getString(1).equals("")) {
                    if(rs.getString(1).equals("0")) {
                        projectTeam = true;
                    } else {
                        if(!recipientList.contains(rs.getString(1).toString())) {
                            recipientList.add(rs.getString(1));
                        }
                    }
                }
            }
                }

//

            if(deptGroup==false && notaspen == false) {
                sql += " and dept_group='0'";
            }

 if (chkmailclient)
 //if(clientcode.equals("10657")||clientcode.equals("10658")||clientcode.equals("197")||clientcode.equals("198")||clientcode.equals("198")||clientcode.equals("2211"))
 {
              String  sqlASpen = "SELECT mail_id from stage_mail_map_client where stage_code='" + stage + "' AND client_id='"+clientcode+"' AND dept_code='"+projDeptCode+"'";
                System.out.println("sqlas"+sqlASpen);
                rs = st.executeQuery(sqlASpen);
            while (rs.next()) {
                  System.out.println("rece1");
                 System.out.println("rece"+rs.getString(1).toString());
                if(!recipientList.contains(rs.getString(1).toString())) {

                    recipientList.add(rs.getString(1));
                        }
                    }
            }
String cellcode="";
String cellcode1="";
String sql1="";
String sql2="";

            sql1 = "select cell_code from projects where proj_id='" + projId + "'";

            rs = st.executeQuery(sql1);
            while (rs.next()) {
                cellcode = rs.getString(1);
            }
            sql2 = "SELECT mailid FROM USER WHERE emp_id='" + empId + "' OR emp_id=(select planner from projects where proj_id='" + projId + "') and facility_id In ('1','2')"
                    + " OR emp_id=(SELECT hybrid_planner FROM projects WHERE proj_id='" + projId + "')";
            //System.out.println(sql2);
            rs = st.executeQuery(sql2);
            while (rs.next()) {
                if(rs.getString(1)!=null && !rs.getString(1).equals("") && !recipientList.contains(rs.getString(1).toString())) {
                    recipientList.add(rs.getString(1));
                }
            }

            if (cellcode.equals("CPD-01")){
            cellcode="PEA-01";
            }
            else if(cellcode.equals("CPD-02"))
            {
            cellcode="SPR-01";
            }
 else{
 cellcode1 = cellcode;
 }
             if(projectTeam) {
                 System.out.print("tst");
            sql = "select u.emp_id, u.desig_code, p.proj_name, u.mailid "
                    + " from projects p, department dt, user u, designation dg,cell c "
                    + " where ((p.dept_code=dt.dept_code and u.dept_code=p.dept_code and u.cell_code_for_mail=p.cell_code)) and u.desig_code=dg.desig_code and proj_id='" + projId + "' "
                    + " and (u.desig_code='PDNMGR' OR u.desig_code='TL') and u.status='1'";
                rs = st.executeQuery(sql);
                while (rs.next()) {
                    if(rs.getString(4)!=null && !rs.getString(4).equals("") && !recipientList.contains(rs.getString(4).toString())) {
                        recipientList.add(rs.getString(4));
                    }
                }
            }
System.out.println(sql);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("The Exception in getBatchTicketRecipientList() of MailerDAO()" + e);
        }
    }

    public int sendShippedBatchMail(MailerVO mailVO) {

        String projName = "";
        boolean stageChk = false;
        boolean stageChk1 = false;
        HashSet projNameForSubject = new HashSet();
        List chapterNameList = new ArrayList();
        List chapterStageList = new ArrayList();
        List chapterStageDispList = new ArrayList();
        List chapterNameDispList = new ArrayList();
        List chapterDueDateList = new ArrayList();
        List shipmentNotes = new ArrayList();
        Session session = getMailConnection();
        String clientcode = mailVO.getClientName();
        SimpleDateFormat dateFormat =  new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();
        String currentDay = dateFormat.format(today);
        String deptcodeForNotes = mailVO.getProjDeptCode();
        getBatchTicketRecipientList(mailVO);
//
//        System.out.println("sendShipedBatchMail - Recipients List : " + recipientList);
//        recipientList.clear();
//        recipientList.add("karthigar@s4carlisle.com");


        try {

            projNameForSubject = mailVO.getProjName1();
            chapterNameList = mailVO.getChapterNameList();
            chapterStageList = mailVO.getStageList();
            chapterDueDateList = mailVO.getChapterDueDateList();

            String stagelocationCode = "";
            shipmentNotes = mailVO.getShipmentNotes();
            Message message = new MimeMessage(session);
            Multipart multipart = new MimeMultipart();
            BodyPart messageBodyPart;
            message.setFrom(new InternetAddress("pathfinder.report@s4carlisle.com"));
            HashSet<String> set = new HashSet(chapterStageList);
            HashSet<String> setSt = new HashSet(chapterNameList);
            chapterNameDispList = new ArrayList(setSt);
            String Str = new String(projNameForSubject.toString());
            int pos = Str.lastIndexOf('_');
            String Str1 = new String(projNameForSubject.toString());
            int pos1 = Str1.lastIndexOf(']');
            String projid1 = Str.substring(pos+1,pos1);
            String StageForl = "";
            List stageforsppl = new ArrayList();
            List stagecodeforsppl = new ArrayList();

            DBconnection conToDB = new DBconnection();
            Connection con = conToDB.getSampleProperty();
            Statement st = con.createStatement();
            ResultSet rs;
            try {
            String sql = "SELECT stage,stage_code FROM shipment_mail_for_somstage_ppl WHERE stage_status=1";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                stageforsppl.add(rs.getString(1));
                stagecodeforsppl.add(rs.getString(2));
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
               String projId = mailVO.getProjId();
            pathfinder.functions.projects.chapters.ChapterAddDetails chapterAddDet1 = new pathfinder.functions.projects.chapters.ChapterAddDetails();
            chapterStageDispList = new ArrayList(set);
            		for (int i = 0; i < chapterStageList.size(); i++) {
                            for(int j=0; j<stageforsppl.size();j++){

                            if(chapterStageList.get(i).equals(stageforsppl.get(j))){
                                  System.out.print ("stageforsppl"+stageforsppl.get(j));
                                System.out.print ("chapterStageList"+chapterStageList.get(i));
                                System.out.print ("deptcodeForNotes"+deptcodeForNotes);
                                 String cell_code="";
                                String getCell_code="select cell_code from projects where proj_id='" + projId + "'";
                               ResultSet rs1=st.executeQuery(getCell_code);
                                 while (rs1.next()){
                                      cell_code=rs1.getString(1);
                               System.out.print ("getcell code"+rs1.getString(1));
                            //recipientList.add(rs.getString(1));
                           }
                           String getMail1 = "select mail_id, dept_wise from shipmail_stage_splppl where stage_code='"+stagecodeforsppl.get(j)+"' and dept_wise is not null and cell_code='"+cell_code+"'";
                           rs = st.executeQuery(getMail1);
                           while (rs.next()){
                               System.out.print ("getMail"+rs.getString(2));
                            recipientList.add(rs.getString(1));

                           }
//                            System.out.println("sendBatchTicketMail - Recipients List : " + recipientList);
//                             recipientList.clear();
//                             recipientList.add("karthigar@s4carlisle.com");

                           if(stagecodeforsppl.get(j).equals("XML")){
                                String getMail = "select mail_id from shipmail_stage_splppl where stage_code='"+stagecodeforsppl.get(j)+"'and dept_wise is not null and cell_code='"+cell_code+"'";
                           System.out.print ("getMail"+getMail);
                           rs = st.executeQuery(getMail);
                           while (rs.next()){
                               System.out.print ("getMail"+rs.getString(1));
                            recipientList.add(rs.getString(1));

                           }
//                    System.out.println("sendBatchTicketMail - Recipients List : " + recipientList);
//                            recipientList.clear();
//                             recipientList.add("karthigar@s4carlisle.com");
                           }
 else{
                           String getMail = "select mail_id from shipmail_stage_splppl where stage_code='"+stagecodeforsppl.get(j)+"'and dept_wise is  null ";
                           System.out.print ("getMail"+getMail);
                           rs = st.executeQuery(getMail);
                           while (rs.next()){
                               System.out.print ("getMail"+rs.getString(1));
                            recipientList.add(rs.getString(1));

                           }
//                     System.out.println("sendBatchTicketMail - Recipients List : " + recipientList);
//                            recipientList.clear();
//                             recipientList.add("karthigar@s4carlisle.com");


                         }
                                }
                            }
//                            System.out.println("art"+chapterStageList.get(i));
//                                   //        System.out.println("artcode"+clientcode);
//			 if(chapterStageList.get(i).equals("Copy Editing")||chapterStageList.get(i).equals("Editorial Proof Reading")||chapterStageList.get(i).equals("Indexing")||chapterStageList.get(i).equals("Tagging-SF")||chapterStageList.get(i).equals("Illustration")||chapterStageList.get(i).equals("Alt Text writing"))  {
//                                          if (chapterStageList.get(i).equals("Copy Editing")){
//                     StageForl="CYD";
//                     stageChk = true;
//                     }
//                         else if(chapterStageList.get(i).equals("Editorial Proof Reading"))
//                         {
//                      StageForl="EDPF";
//                      stageChk = true;
//                     }
//                     else if(chapterStageList.get(i).equals("Indexing"))
//                         {
//                      StageForl="IDXNG";
//                      stageChk = true;
//                     }
//                                          else if(chapterStageList.get(i).equals("Tagging-SF"))
//                         {
//                      StageForl="TSF";
//                      stageChk1 = true;
//
//                     }
//                                       else if(chapterStageList.get(i).equals("Illustration"))
//                         {
//                                           //System.out.println("art"+chapterStageList.get(i));
//                                           //System.out.println("artcode"+clientcode);
//                      StageForl="ART";
//                      stageChk1 = true;
//                     }
//                       else if(chapterStageList.get(i).equals("Art Corrections"))
//                         {
//                      StageForl="ARTCXN";
//                      stageChk1 = true;
//                     }
//                      else if(chapterStageList.get(i).equals("Alt Text writing"))
//                         {
//                      StageForl="ATW";
//                      stageChk1 = true;
//                     }
//                     }
		}
            chapterAddDet1.SetStageproj_id(projid1);
            chapterAddDet1.SetStageforLoc(StageForl);
            stagelocationCode=chapterAddDet1.getStagemappedToLocation().toString();
//                         if (stageChk == true || stageChk1 == true){
//                for (int i = 0; i < recipientList.size(); i++) {
//                    		if(recipientList.get(i).equals("MuthukumarS@s4carlisle.com"))  {
//                  recipientList.remove(i);
//                    }
//        else if(recipientList.get(i).equals("DeepikaV@s4carlisle.com"))
//                {
//            recipientList.remove(i);
//        }
//  }
//
//
////                if (!stagelocationCode.equals("OnShore") && !StageForl.equals("IDXNG") && !StageForl.equals("ATW")){
////                //recipientList.add("arunap@s4carlisle.com");
////                recipientList.add("kannans@s4carlisle.com");
////                recipientList.add("MadhuMN@s4carlisle.com");
////                recipientList.add("MuthukumarS@s4carlisle.com");
////              recipientList.add("arunmozhip@s4carlisle.com");
////                }
////                             else if(StageForl.equals("IDXNG"))
////                             {
////                recipientList.add("UmasangeethaP@s4carlisle.com");
////                recipientList.add("MuthukumarS@s4carlisle.com");
////                recipientList.add("arunmozhip@s4carlisle.com");
////                }
////                             else if(StageForl.equals("ATW"))
////                             {
////                recipientList.add("MadhuMN@s4carlisle.com");
////                recipientList.add("MuthukumarS@s4carlisle.com");
////               recipientList.add("arunmozhip@s4carlisle.com");
////                }
//          System.out.println("sendShipedBatchMail - Recipients List : " + recipientList);
//          recipientList.clear();
//          recipientList.add("karthigar@s4carlisle.com");
//
//            InternetAddress[] addressTo = new InternetAddress[recipientList.size()];
//            for (int i = 0; i < recipientList.size(); i++) {
//                addressTo[i] = new InternetAddress(recipientList.get(i));
//            }
//            int mailChk = 0;
//
//               // recipientList.add("gandhimathidevic@s4carlisle.com");
//            message.setRecipients(Message.RecipientType.TO, addressTo);
//            message.setSubject(projNameForSubject+ " - "+chapterNameDispList +" - "+chapterStageDispList+" - Posted in Usual Path");
//            messageBodyPart = new MimeBodyPart();
//            String msgContent = "<html>"
//                    + "<body><font face='Calibri'>"
//                    + "            <p>Hi,</p>"
//                    + "            <p>Below Files are available in the Usual Path for your review.</p>"
//                    + "            <p>Project Name: <b>"+projNameForSubject+"</b></p>"
//                    + "            <table border='1'>"
//                    + "                <tr style='font-weight:bold'>"
//                    + "                    <th>Chapter</th>"
//                    + "                    <th>Stage</th>"
//                    + "                    <th>Due Date</th>"
//                    + "                    <th>Shipment Notes</th>"
//                    + "                </tr>";
//                                        for(int idx=0; idx<chapterNameList.size(); idx++) {
//                                           msgContent += "<tr><td>"+chapterNameList.get(idx).toString()+"</td>";
//                                           msgContent += "<td>"+chapterStageList.get(idx).toString()+"</td>";
//                                           msgContent += "<td>"+chapterDueDateList.get(idx).toString()+"</td>";
//                                           if (!deptcodeForNotes.equals("CHN-WK")){
//                                           msgContent += "<td>"+shipmentNotes.get(idx).toString()+"</td></tr>";
//                                           }
//                                             else{
//                                              msgContent +=   "</tr>";
//                                             }
//                                        }
//            msgContent += "            </tr>"
//                    + "            </table><br>"
//                    + "            Regards,<br>"
//                    + "            Pathfinder Team<br><br>"
//                    + "        </font>"
//                    + "        <p><font color=\"gray\"><i>Note :</i> This is an automated mail and please do not reply.</font></p>"
//                    + "    </body>"
//                    + "</html>";
//            messageBodyPart.setContent(msgContent,"text/html");
//            multipart.addBodyPart(messageBodyPart);
//            message.setContent(multipart);
//            Transport.send(message);
//            mailVO.setRecipientList(recipientList);
//            return 1;
//            }
 //else{
//                recipientList.clear();
//                recipientList.add("karthigar@s4carlisle.com");
                //remove duplicates mail id
                for (int i=0; i <recipientList.size();i++){
                for (int j=i+1; j <recipientList.size();j++){
                if(recipientList.get(i).equals(recipientList.get(j))){
                recipientList.remove(i);
                }
                }
                }
                InternetAddress[] addressTo = new InternetAddress[recipientList.size()];
            for (int i = 0; i < recipientList.size(); i++) {
                addressTo[i] = new InternetAddress(recipientList.get(i));
            }
            message.setRecipients(Message.RecipientType.TO, addressTo);
            message.setSubject(projNameForSubject+" - "+chapterStageDispList+" - Posted in File Management");
            messageBodyPart = new MimeBodyPart();
            String msgContent = "<html>"
                    + "<body><font face='Calibri'>"
                    + "            <p>Hi,</p>"
                    + "            <p>Below Files are available in File Management for your review.</p>"
                    + "            <p>Project Name: <b>"+projNameForSubject+"</b></p>"
                    + "            <table border='1'>"
                    + "                <tr style='font-weight:bold'>"
                    + "                    <th>Chapter</th>"
                    + "                    <th>Stage</th>"
                    + "                    <th>Due Date</th>"
                    + "                    <th>Shipment Notes</th>"
                    + "                </tr>";
                                        for(int idx=0; idx<chapterNameList.size(); idx++) {
                                           msgContent += "<tr><td>"+chapterNameList.get(idx).toString()+"</td>";
                                           msgContent += "<td>"+chapterStageList.get(idx).toString()+"</td>";
                                           msgContent += "<td>"+chapterDueDateList.get(idx).toString()+"</td>";
                                         if (!deptcodeForNotes.equals("CHN-WK")){
                                           msgContent += "<td>"+shipmentNotes.get(idx).toString()+"</td></tr>";
                                           }
                                        else{ msgContent += "</tr>";}
                                        }
            msgContent += "            </tr>"
                    + "            </table><br>"
                    + "            Regards,<br>"
                    + "            Pathfinder Team<br><br>"
                    + "        </font>"
                    + "        <p><font color=\"gray\"><i>Note :</i> This is an automated mail and please do not reply.</font></p>"
                    + "    </body>"
                    + "</html>";

            messageBodyPart.setContent(msgContent,"text/html");
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);
            Transport.send(message);

            mailVO.setRecipientList(recipientList);
            return 1;//}
        } catch (MessagingException e) {
            System.out.println("MessagingException: " + e);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return 0;
    }
    public void getShippedBatchRecipientList(MailerVO mailVO) {

        String sql="";
        String stage = mailVO.getStage();
        String projId = mailVO.getProjId();
        String empId = mailVO.getEmpId();

        try {
            DBconnection conToDB = new DBconnection();
            Connection con = conToDB.getSampleProperty();
            Statement st = con.createStatement();
            ResultSet rs;

            sql = "SELECT u.mailid FROM projects p, USER u WHERE p.planner=u.emp_id AND u.facility_id='1' AND p.proj_id='" + projId + "'";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                if(rs.getString(1)!=null && !rs.getString(1).equals("") && !recipientList.contains(rs.getString(1).toString())) {
                    recipientList.add(rs.getString(1));
                }
            }
            System.out.println("sql1" + sql);

            sql = "SELECT u.mailid FROM projects p, USER u WHERE p.hybrid_planner=u.emp_id AND p.proj_id='" + projId + "'";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                if(rs.getString(1)!=null && !rs.getString(1).equals("") && !recipientList.contains(rs.getString(1).toString())) {
                    recipientList.add(rs.getString(1));
                }
            }
            String cell_code="";
              String getCell_code="select cell_code from projects where proj_id='" + projId + "'";
                               ResultSet rs1=st.executeQuery(getCell_code);
                                 while (rs1.next()){
                                      cell_code=rs1.getString(1);
                               System.out.print ("getcell code"+rs1.getString(1));
                            //recipientList.add(rs.getString(1));
                           }
            System.out.println("sql2" + sql);
           // sql = "SELECT mailid FROM USER WHERE emp_id='" + empId + "'OR cell_code_for_mail='"+cell_code+"'";
            sql = "SELECT mailid FROM USER WHERE emp_id='" + empId+"'" ;

            rs = st.executeQuery(sql);
            while (rs.next()) {
                if(rs.getString(1)!=null && !rs.getString(1).equals("") && !recipientList.contains(rs.getString(1).toString())) {
                    recipientList.add(rs.getString(1));
                }
            }
System.out.println("sql3" + sql);
        } catch (Exception e) {
            System.out.println("The Exception in getShippedBatchRecipientList() of MailerDAO()" + e);
        }
    }

      public int sendBatchTicketMail1(MailerVO mailVO) {

        String projName = "";
        String stageName="";
        List chapterIdList = new ArrayList();
        List chapterNameList = new ArrayList();
        List chapterDueDateList = new ArrayList();
        List chapterNotesFlagList = new ArrayList();
        List chaptermssPages = new ArrayList();
        List chapterArtCount = new ArrayList();
        String clientcode="";

        Session session = getMailConnection();

        SimpleDateFormat dateFormat =  new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();
        String dueDateStr = "";
        boolean todayBatch = false;
        Date dueDate = new Date();

        getBatchTicketRecipientList(mailVO);

//        System.out.println("sendBatchTicketMail - Recipients List : " + recipientList);
//        recipientList.clear();
//        recipientList.add("karthigar@s4carlisle.com");


        try {

            projName = mailVO.getProjName();
            stageName = mailVO.getStageName();
            chapterIdList = mailVO.getChapterIdList();
            chapterNameList = mailVO.getChapterNameList();
            chapterDueDateList = mailVO.getChapterDueDateList();
            chapterNotesFlagList = mailVO.getChapterNotesFlagList();
            chaptermssPages  = mailVO.getMssPageList();
            chapterArtCount  = mailVO.getArtCountList();
            Message message = new MimeMessage(session);
            Multipart multipart = new MimeMultipart();
            BodyPart messageBodyPart;
            message.setFrom(new InternetAddress("pathfinder.report@s4carlisle.com"));
//System.out.println("stageNamess"+chapterNameList);

            InternetAddress[] addressTo = new InternetAddress[recipientList.size()];
            for (int i = 0; i < recipientList.size(); i++) {
                addressTo[i] = new InternetAddress(recipientList.get(i));
            }
            message.setRecipients(Message.RecipientType.TO, addressTo);
            message.setSubject(projName+" - "+stageName+" - Scheduled in Pathfinder");
            messageBodyPart = new MimeBodyPart();
            String msgContent = ""
                    + "<html>"
                    + "<body>"
                    + "<font face='Calibri'><p>Hi,</p>"
                    + "<p>The Below Batches are scheduled</p>"
                    + "<p>Project Name: <b>"+projName+"</b></p>"
                    + "<p>Stage: <b>"+stageName+"</b></p>"
                    + "<table border='1'>"
                    + "<tr style='font-weight:bold'><th>Chapter</th><th>Mss Page</th>";
                    if(stageName.contains("Illustration")){
                        msgContent += "<th>Art Count</th>";
                    }
                    msgContent += "<th>Due Date</th></tr>";

            for(int idx=0; idx<chapterNameList.size(); idx++) {

               dueDateStr = chapterDueDateList.get(idx).toString();
                //System.out.println("dueDateStr"+chaptermssPages);
                try {
                    dueDate = dateFormat.parse(dueDateStr);
                    if(dueDate.equals(today) || dueDate.before(today)) {
                        todayBatch = true;
                    } else {
                        todayBatch = false;
                    }
                } catch (ParseException e) {
                    System.out.println("MailerDAO - sendBatchTicketMail() : " + e);
                }
try
                {
                msgContent += "<tr><td>"+chapterNameList.get(idx).toString()+"</td>";
                if((chaptermssPages.get(idx).toString().equals("")||chaptermssPages.get(idx).toString()==null)) {

                    msgContent += "<td><font color='RED'>page missing</font></td>";
                } else {
                    msgContent += "<td>"+chaptermssPages.get(idx).toString()+"</td>";
                }
                 if(stageName.contains("Illustration")){
                        msgContent += "<td>"+chapterArtCount.get(idx).toString()+"</td>";
                    }
}
                 catch (Exception e) {
                    System.out.println("MailerDAO - pages : " + e);
                }
                if(todayBatch) {
                    msgContent += "<td><font color='RED'>"+chapterDueDateList.get(idx).toString()+"</font></td></tr>";
                } else {
                    msgContent += "<td>"+chapterDueDateList.get(idx).toString()+"</td></tr>";
                }

            }
            msgContent +=  "</table><br>"
                    + "Regards,<br>"
                    + "Pathfinder Team<br><br></font>"
                    + "<p><font color=\"gray\"><i>Note :</i> This is an automated mail and please do not reply.</font></p>"
                    + "</body>"
                    + "</html>";

            messageBodyPart.setContent(msgContent,"text/html");
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);
            Transport.send(message);

            mailVO.setRecipientList(recipientList);
            //System.out.println("test"+mailVO.getRecipientList());
        } catch (MessagingException e) {
            System.out.println("MessagingException: " + e);
        } catch (Exception e) {
            //System.out.println("Exception: " + e);
            e.printStackTrace();
        }  finally {
            return 1;
        }
    }
      public boolean sendpdfattach() {

        Session session = getMailConnection();
        try {
            Message message = new MimeMessage(session);
            Multipart multipart = new MimeMultipart();
            BodyPart messageBodyPart;
            message.setFrom(new InternetAddress("pathfinder.report@s4carlisle.com"));
            InternetAddress[] addressTo = new InternetAddress[0];
          //  addressTo[0] = new InternetAddress("gandhimathidevic@s4carlisle.com");
            message.setRecipients(Message.RecipientType.TO, addressTo);
            message.setSubject("Pay Slip - ");

            messageBodyPart = new MimeBodyPart();
            String msgContent = ""
                    + "<html>"
                        + "<body>"
                            + "<font face='Calibri'><p>Hi,</p>"
                            + "<p>Please find the attached payslip for the month of </b></i></p>"

                            + "<p><font color=\"gray\"><i>Note :</i> Salary Grievances if any, will be addressed on 14th & 15th July 2017 between 3.00 p.m. and 4.00 p.m.</font></p>"
                        + "</body>"
                    + "</html>";
		MimeBodyPart attachPart = new MimeBodyPart();
		try {
                    attachPart.attachFile("C:/ScheduleExcel/Taylor_163132.xls");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

             multipart.addBodyPart(attachPart);
            messageBodyPart.setContent(msgContent,"text/html");
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);
            Transport.send(message);

        } catch (MessagingException e) {
            System.out.println("MessagingException: " + e);
        } finally {
            return true;
        }
    }
}