/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/

package pathfinder.functions.projects;

import java.io.Serializable;
import connection.DBconnection;

import pathfinder.functions.generic.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
/**
*
* @author ramesh
*/
public class CreateProjId implements Serializable {

    private String clientCode="";
    private String division="";
    private String buyer="";
    private String salesperon="";
    private String contact="";
    private String printer="";
    private String status="";
    private String projtype="";
    private String projcategory="";
    private String discipline="";
    private String title="";
    private String edition="";
    private String name="";
    private String authorName="";
    private String prjid="";
    private String paperInf="";
    private String priority="";
    private String copyright="";
    private String mss="";
    private String estimated="";
    private String actual="";
    private String isbn10="";
    private String isbn13="";
    private String colorID="";
    private String addOption="";
    private String insertFields="";
    private String insertValues="";
   private String billLocationId="";

    private String surName;
    private String FirstName;
    private String newProjId;
    private String newProjName;
    private String maxprojId = "";
    private String maxprojCount = "";
    private String pageHeight="";
    private String pageWidth="";
    private String primaryAuthorID="";
    private String sesEmpId="";
    private String projLevelID="";
    private String projEISBNCategID="";
    private String projEISBN="";
    private String planner="";
    private String hybridPlanner="";
    private String projectedPrinterDt="";
    private String eFtpDt="";
    private String projectedEstSentDt="";
    private String customerPO="";
    private String customerPOdate="";
    private String actualShipDate="";
    private String authorId="";
    private String noOfChapters="";
    private String mssFormat="";
    private String fstPresent="";
    private String xmlProp="";
    private int incrementor=0;

    private int addProj=0;
    private int projStatusChk=0;
    private String projStatusStrChk = "";
    //varaiables for PFKeyHandler-class to handle duplicate key constraint error

    private String preInsertSql="";
    private String postInsertSql="";
    private String handlerInsertField="";
    private String midInsertSql="";
    private String handlerCountSql="";

    private List log_table_name = new ArrayList();
    private List log_field_name = new ArrayList();
    private List log_linked_field_name = new ArrayList();
    private List log_reference_table = new ArrayList();
    private List log_reference_field= new ArrayList();
    private List log_old_value = new ArrayList();
    private List log_new_value = new ArrayList();
    private List log_changed_by = new ArrayList();
    private List log_linked_field_value = new ArrayList();
    private List log_reference_value= new ArrayList();


    private List proj_author_id = new ArrayList(); //List for store author_id from Projects Table

    //  private String preInsertSql="";

    /*variables to retrieve old values in case of project modification **/

     private String old_disp_clientName="";
     private String old_disp_clientCode="";
     private String old_division="";
     private String old_buyer="";
     private String old_salesperson="";
     private String old_contact="";
     private String old_printer="";
     private String old_disp_status="";
     private String old_projtype="";
     private String old_projcategory="";
     private String old_discipline="";
     private String old_title="";
     private String old_edition="";
     private String old_name="";
     private String old_prjid="";
     private String old_paperInf="";
    private String old_priority="";
    private String old_copyright="";
    private String old_mss="";
    private String old_estimated="";
    private String old_actual="";
    private String old_isbn10="";
    private String old_isbn13="";
    private String old_billLocationId="";

    private String old_divisionID="";
    private String old_buyerID="";
    private String old_salespersonID="";
    private String old_contactID="";
    private String old_printerID="";
    private String old_statusCode="";
    private String old_projtypeID="";
    private String old_projcategoryID="";
    private String old_disciplineID="";
    private String old_priorityID="";
    private String old_colorID="";
    private String old_colorValue="";
    private String old_isSelected="";
    private String old_disp_projName="";
    private String old_authorName="";
    private String old_pageHeight="";
    //private String old_pageWidth="";
    private String old_plannerId="";
      private String old_hybridPlannerId="";
    private String old_projectedPrinterDt="";
     private String old_eFtpDt="";
    private String old_projectedEstSentDt="";
    private String old_customerPO="";
    private String old_customerPOdate="";
    private String old_actualShipDate="";
    private String old_authorId="";
    private String old_noOfChapters="";
    private String old_mssFormat="";
    private String old_fstPresent="";
    private String old_xmlProp="";
    private String old_jobLostCode="";
    private String jobLostCode="";
    //String projName;

    public String getJobLostCode() {
        return jobLostCode;
    }

    public void setJobLostCode(String jobLostCode) {
        this.jobLostCode = jobLostCode;
    }

    public String getMssFormat() {
        return mssFormat;
    }

    public void setMssFormat(String mssFormat) {
        this.mssFormat = mssFormat;
    }

    public String getFstPresent() {
        return fstPresent;
    }

    public void setFstPresent(String fstPresent) {
        this.fstPresent = fstPresent;
    }

    public String getNoOfChapters() {
        return noOfChapters;
    }

    public void setNoOfChapters(String noOfChapters) {
        this.noOfChapters = noOfChapters;
    }

    public String getXmlProp() {
        return xmlProp;
    }

    public void setXmlProp(String xmlProp) {
        this.xmlProp = xmlProp;
    }

    public void setCustomerPOdate(String customerPOdate) {
        this.customerPOdate = customerPOdate;
    }

    public String getCustomerPOdate() {
        return customerPOdate;
    }


    public String getCustomerPO() {
        return customerPO;
    }

    public void setCustomerPO(String customerPO) {
        this.customerPO = customerPO;
    }

    public String getAuthorId() {
        return authorId;
    }

    public String getActualShipDate() {
        return actualShipDate;
    }

    public void setActualShipDate(String actualShipDate) {
        this.actualShipDate = actualShipDate;
    }

    public CreateProjId() {

    }

    public void setPrimaryAuthor(String primaryAuthorID){
        this.primaryAuthorID=primaryAuthorID;
    }

    public void setHeight(String pageHeight){
        this.pageHeight=pageHeight;
    }

    public void setWidth(String pageWidth){
        this.pageWidth=pageWidth;
    }

    public void setClient(String clientCode){
        this.clientCode=clientCode;
    }

    public void setDivision(String division){
        this.division=division;
    }

   public void setBuyer(String buyer){
        this.buyer=buyer;
    }

   public void setSalesPerson(String salesperon){
        this.salesperon=salesperon;
    }


    public void setContact(String contact){
        this.contact=contact;
    }

    public void setPrinter(String printer){
        this.printer=printer;
    }


    public void setStatus(String status){
        this.status=status;
    }

     public void setType(String projtype){
        this.projtype=projtype;
    }

    public void setCategory(String projcategory){
        this.projcategory=projcategory;
    }

    public void setProjEISBN(String projEISBN) {
        this.projEISBN = projEISBN;
    }

    public void setProjEISBNCategID(String projEISBNCategID) {
        this.projEISBNCategID = projEISBNCategID;
    }

    public void setProjLevelID(String projLevelID) {
        this.projLevelID = projLevelID;
    }

    public void setDiscipline(String discipline){
        this.discipline=discipline;
    }

    public void setTitle(String title){
        this.title=title;
    }


    public void setEdition(String edition){
        this.edition=edition;
    }

    public void setAuthor(String name){
        this.name=name;
    }

    public void setPriority(String priority){
        this.priority=priority;
    }
      public void setPaprInf(String paperInf){
        this.paperInf=paperInf;
    }

   public void setCopyRight(String copyright){
        this.copyright=copyright;
    }

   public void setColorId(String colorID){
        this.colorID=colorID;
    }

     public void setMss(String mss){
        this.mss=mss;
    }

      public void setEstimated(String estimated){
        this.estimated=estimated;
    }

       public void setActual(String actual){
        this.actual=actual;
    }

       public void setISBN10(String isbn10){
        this.isbn10=isbn10;
    }

      public void setISBN13(String isbn13){
        this.isbn13=isbn13;
    }

   public void setBillLocation(String billLocationId){
        this.billLocationId=billLocationId;
    }

    public void setPlanner(String planner){
        this.planner=planner;
    }
    
    public void setHybridPlanner(String hybridplanner){
      
        this.hybridPlanner=hybridplanner;
    }

     public void setProjectedPrinterDt(String projectedPrinterDt){
        this.projectedPrinterDt=projectedPrinterDt;
    }


     public String getProjectedPrinterDt(){
       return projectedPrinterDt;
    }
     public void seteFtpDt(String eFtpDt){
        this.eFtpDt=eFtpDt;
    }


     public String geteFtpDtDt(){
       return eFtpDt;
    }

    public String getPlanner(){
       return planner;
    }
      public String getHybridPlanner(){
       return hybridPlanner;
    }

   public String getProjAdded(){
        return String.valueOf(addProj);
   }

   public void setProjId(String prjid){
        this.prjid=prjid;
   }

   public void setAddOption(String addOption){
        this.addOption=addOption;
   }

     public String getProjId(){
       return newProjId;
    }

    public String getProjName(){
       return newProjName;
    }


    public String getProjectedEstSentDt() {
        return projectedEstSentDt;
    }

    public void setProjectedEstSentDt(String projectedEstSentDt) {
        this.projectedEstSentDt = projectedEstSentDt;
    }


    public String getProjCount(){
       return maxprojCount;
    }

    public void setSurName(String surName){
        this.surName=surName;
        this.authorName=surName;
    }

   public void setFirstName(String FirstName){
     this.FirstName=FirstName;
   }

   public void setEmpId(String sesEmpId){
       this.sesEmpId=sesEmpId;
   }

    public String encodeSpecialCharacter(String text){
     int len= text.length();
     StringBuffer encodedString=new StringBuffer();
      for(int m=0;m<len;m++)
        {
          char c=text.charAt(m);
          if(c=='\'')
               encodedString.append("\\\'");
          /*else if(c=='>')
            encodedQuestion.append("&gt;");
          else if(c=='&')
             encodedQuestion.append("&amp;");
          else if(c=='"')
            encodedQuestion.append("&quot;");
          else if(c=='\n')
             encodedQuestion.append("<br>");*/
          else
             encodedString.append(c);
         }
               return encodedString.toString();
  }

public void collectOldValues(){
    if(!prjid.equals("")){
        PFUtil pfu = new PFUtil();

        ProjIdGeneralInfo pia = new ProjIdGeneralInfo();
        pia.setProjId(prjid);
        pia.collectProjectName();
        old_authorName=pia.getAuthor();
        old_authorId=pia.getPrimaryAuthorID(); // Exist authod id from projects table
old_disp_projName="'"+pia.getAuthor()+"'";
old_disp_clientName="'"+pia.getClient()+"'";
old_disp_clientCode="'"+pia.getClientCode()+"'";
old_division="'"+pia.getDivision()+"'";
old_buyer="'"+pia.getBuyer()+"'";
old_salesperson="'"+pia.getSalesPerson()+"'";
old_contact="'"+pia.getContact()+"'";
old_printer="'"+pia.getPrinter()+"'";
old_disp_status="'"+pia.getStatus()+"'";
old_projtype="'"+pia.getType()+"'";
old_projcategory="'"+pia.getCategory()+"'";
old_discipline="'"+pia.getDiscipline()+"'";
old_title="'"+pfu.encodeSpecialCharacter(pia.getTitle())+"'";
old_edition="'"+pia.getEdition()+"'";
old_name="'"+pia.getAuthor()+"'";
old_colorValue="'"+pia.getColorName()+"'";
old_paperInf ="'"+pia.getCollectPaperInfo()+"'";
old_priority="'"+pia.getPriority()+"'";
old_copyright="'"+pia.getCopyRight()+"'";
old_mss="'"+pia.getMss()+"'";
old_estimated="'"+pia.getEstimated()+"'";
old_actual="'"+pia.getActual()+"'";
old_isbn10="'"+pia.getISBN10()+"'";
old_isbn13="'"+pia.getISBN13()+"'";
old_billLocationId="'"+pia.getBillLocationId()+"'";
old_pageHeight="'"+pia.getPageHeight()+"'";
//old_pageWidth="'"+pia.getPageWidth()+"'";

old_divisionID="'"+pia.getDivisionID()+"'";
old_buyerID="'"+pia.getBuyerID()+"'";
old_salespersonID="'"+pia.getSalespersonID()+"'";
old_contactID="'"+pia.getContactID()+"'";
old_printerID="'"+pia.getPrinterID()+"'";
old_statusCode="'"+pia.getStatusCode()+"'";
old_projtypeID="'"+pia.getprojtypeID()+"'";
old_projcategoryID="'"+pia.getProjcategoryID()+"'";
old_disciplineID="'"+pia.getDisciplineID()+"'";
old_priorityID="'"+pia.getPriorityID()+"'";
old_colorID="'"+pia.getColorID()+"'";
old_plannerId="'"+pia.getPlannerId()+"'";
old_hybridPlannerId="'"+pia.getHybridPlanner()+"'";
old_projectedPrinterDt="'"+pia.getProjectedPrinterDt()+"'";
old_eFtpDt="'"+pia.geteFtpDt()+"'";
old_projectedEstSentDt="'"+pia.getProjectedEstSentDt()+"'";
old_customerPO="'"+pia.getCustomerPO()+"'";
old_customerPOdate="'"+pia.getCustomerPODate()+"'";
old_actualShipDate="'"+pia.getActualShipDate()+"'";
old_noOfChapters = "'" + pia.getNoOfChapters() + "'";
old_mssFormat = "'" + pia.getMssFormat() + "'";
old_fstPresent = "'" + pia.getFstPresent() + "'";
old_xmlProp = "'" + pia.getXmlProp() + "'";
old_jobLostCode = "'" + pia.getJobLostReasonCode() + "'";


}
}


public void addProject(){
    
    collectOldValues();//call to collect old values so that if the project is modified then the old values could logged
    //class takes care of adding or updating project
Connection con=null;
String logOption="";
int log_Option=0;//integer equivalent of logOption

        try{
        DBconnection dbcon = new DBconnection();
        con = dbcon.getSampleProperty();
        filters.generic.FolderDriveInsertion pfdi = new filters.generic.FolderDriveInsertion();
        pathfinder.util.SpecialCharHandler splChar = new pathfinder.util.SpecialCharHandler();
        Statement statement = con.createStatement();
        ResultSet rsLogOption = statement.executeQuery("select log_option from dblog_preference where module_name='projects' ");
        while(rsLogOption.next()){
            logOption=rsLogOption.getString("log_option");
        }

        log_Option=Integer.parseInt(logOption);
        rsLogOption.close();
        //the below block checks for the project ID as it should start from 106000 and starts adding the project ID from that
        //Generate Project ID

        //actually the code should be checking the max(ProjID) since roght now the field is varcha tilthe development completes the belo logic is used
        
if(prjid.equals("")){

    handlerCountSql=" select max(project_count) from projects ";
        ResultSet rsMaxProjCount = statement.executeQuery(" select max(project_count) from projects ");
        while(rsMaxProjCount.next()){
            maxprojCount=rsMaxProjCount.getString("max(project_count)");
        }
        rsMaxProjCount.close();

      ResultSet rsMaxProjId = statement.executeQuery(" select proj_id from projects where project_count='"+maxprojCount+"' ");
        while(rsMaxProjId.next()){
            maxprojId=rsMaxProjId.getString("proj_id");
        }
      rsMaxProjId.close();

        incrementor=Integer.parseInt(maxprojCount);
        incrementor=incrementor+1;

        maxprojId=String.valueOf(incrementor);
        newProjId=maxprojId;

        incrementor=Integer.parseInt(maxprojCount);
        incrementor=incrementor+1;
        maxprojCount=String.valueOf(incrementor);

        //the below should be used in the case of creating a projet with the ID appended to it
        name=createProjName(maxprojId);//a sub method called to form the new project name
       // name=surName;
        //projName=name;
        //System.out.println("the name after funcion call "+name);
}
else{
            newProjId=prjid;

            

            String t1= "";
            String t2="";
            int t1length = 0;
            int t2length = 0;

            try{
                 t1 = old_authorName.substring(0,old_authorName.indexOf(newProjId));
                 t1length = t1.length();
                 t1length = t1length-1;
                 t1=t1.substring(0, t1length);
            }catch(StringIndexOutOfBoundsException sioe)
            {
                t1= old_authorName;
                t1length = t1.length();
            }

            try{
                 t2= surName.substring(0,surName.indexOf(newProjId));
                
                 t2length = t2.length();
                 t2length = t2length-1;
                 t2=t2.substring(0, t2length);
            }catch(StringIndexOutOfBoundsException sioe)
            {
                t2 = surName;
                t2length = t2.length();
            }

           /* System.out.println("t1:"+t1);
            System.out.println("t2:"+t2);*/

            if(!t1.equals(t2)){
               surName=t2;
               name=createProjName(newProjId);
              
            }
            else{
                name=old_authorName;
               
             }
}

            String chkProjId = "";
            String updateProj_Sql="";

            updateProj_Sql=" update projects set ";

            String updateField="";
            String where = " where proj_id='"+prjid+"' ";

            boolean compareValues=true;

         if(name.equals("")||name.equals("All")){
             name=null;
         }
         else{
             name="'"+name+"'";
         }

   if(addOption.equals("Modify")){
       
               if(old_disp_projName.trim().equals("''")&&name==null){
                    compareValues=true;
                      
                }
                else{
                    compareValues=old_disp_projName.equals(name);
                     
                }
               //read this comment
               if(!compareValues){//if there is difference between old value and new value then append the below part in the update query
                    if(updateField.length()>0){
                    updateField += ", proj_name="+name+" ";
                    }
                    else{
                     updateField += " proj_name="+name+" ";
                    }

                    if(log_Option==1){//if the log option is set
                       log_table_name.add("projects");
                       log_field_name.add("proj_name");
                       log_linked_field_name.add("proj_id");
                       log_linked_field_value.add(prjid);
                       log_old_value.add(old_disp_projName);
                       log_new_value.add(name);
                       log_changed_by.add(sesEmpId);
                       log_reference_table.add("");
                       log_reference_field.add("");
                       log_reference_value.add("");
                    }
                }
       }


        if(clientCode.equals("")||clientCode.equals("All")){
             clientCode=null;
         }
         else{
             clientCode="'"+clientCode+"'";
        }


if(addOption.equals("Modify")){
 
    if(old_disp_clientCode.trim().equals("''")&&clientCode==null){
        compareValues=true;
    }
    else{
        compareValues=old_disp_clientCode.equals(clientCode);
    }

      if(!compareValues){
            if(updateField.length()>0){
                updateField += ", client_name="+clientCode+" ";
                }
                else{
                 updateField += " client_name="+clientCode+" ";
                }
           if(log_Option==1){log_table_name.add("projects");
           log_field_name.add("client_name");
           log_linked_field_name.add("proj_id");
           log_linked_field_value.add(prjid);
           log_old_value.add(old_disp_clientCode);
           log_new_value.add(clientCode);
           log_changed_by.add(sesEmpId);
           log_reference_table.add("client");
           log_reference_field.add("client_code");
           log_reference_value.add("");
           }
     }
}

       if(projtype.equals("")||projtype.equals("All")){
             projtype=null;
         }
         else{
             projtype="'"+projtype+"'";
        }


if(addOption.equals("Modify")){
//compareValues=true;

    /*if(old_projtypeID.trim().equals("''")&&projtype==null){
        compareValues=true;
    }
    else{
        compareValues=old_projtypeID.equals(projtype);
    }*/
/*System.out.println("old_projtypeID:"+old_projtypeID);
System.out.println("projtype:"+projtype);
  System.out.println("compareValues:"+compareValues);*/
    
  if(projtype!=null&&!old_projtypeID.equals(projtype)) {
      if(updateField.length()>0){
      
                updateField += ", proj_workflow="+projtype+" ";
                 }
                else{
                 updateField += " proj_workflow="+projtype+" ";
                }
    }
      /*if(!compareValues){


           if(log_Option==1){log_table_name.add("projects");
           log_field_name.add("proj_type");
           log_linked_field_name.add("proj_id");
           log_linked_field_value.add(prjid);
           log_old_value.add(old_projtypeID);
           log_new_value.add(projtype);
           log_changed_by.add(sesEmpId);
           log_reference_table.add("project_type");
           log_reference_field.add("type_code");
           log_reference_value.add("");
           }
     }*/
}


       if(colorID.equals("")||colorID.equals("All")){
             colorID=null;
         }
         else{
             colorID="'"+colorID+"'";
        }


if(addOption.equals("Modify")){
     compareValues=true;
    if(old_colorID.trim().equals("''")&&colorID==null){
        compareValues=true;
    }
    else{
        compareValues=old_colorID.equals(colorID);
    }

      if(!compareValues){

             if(updateField.length()>0){
                updateField += ", color_id="+colorID+" ";
             }
             else{
                 updateField += " color_id="+colorID+" ";
             }

           if(log_Option==1){log_table_name.add("projects");
           log_field_name.add("color_id");
           log_linked_field_name.add("proj_id");
           log_linked_field_value.add(prjid);
           log_old_value.add(old_colorID);
           log_new_value.add(colorID);
           log_changed_by.add(sesEmpId);
           log_reference_table.add("proj_color");
           log_reference_field.add("color_id");
           log_reference_value.add("");
           }
     }
}

  //planner
            if(planner.equals("")||planner.equals("All")){
             planner=null;
         }
         else{
             planner="'"+planner+"'";
        }
            // hybrid planner
            
             if(hybridPlanner.equals("")||hybridPlanner.equals("All")){
             hybridPlanner=null;
         }
         else{
             hybridPlanner="'"+hybridPlanner+"'";
        }


if(addOption.equals("Modify")){
     compareValues=true;
    if(old_plannerId.trim().equals("''")&&planner==null){
        compareValues=true;
    }
    else{
        compareValues=old_plannerId.equals(planner);
    }

      if(!compareValues){

             if(updateField.length()>0){
                updateField += ", planner="+planner+" ";
             }
             else{
                 updateField += " planner="+planner+" ";
             }


     }
}

// hybrid planner

if(addOption.equals("Modify")){
     compareValues=true;
    if(old_hybridPlannerId.trim().equals("''")&&hybridPlanner==null){
        compareValues=true;
    }
    else{
        compareValues=old_hybridPlannerId.equals(hybridPlanner);
    }

      if(!compareValues){

             if(updateField.length()>0){
                updateField += ",hybrid_Planner="+hybridPlanner+" ";
             }
             else{
                 updateField += " hybrid_Planner="+hybridPlanner+" ";
             }


     }
}

//projected printer date

        if(projectedPrinterDt.equals("")||projectedPrinterDt.equals("All")){
             projectedPrinterDt=null;
         }
         else{
             projectedPrinterDt="'"+projectedPrinterDt+"'";
        }
               if(eFtpDt.equals("")||eFtpDt.equals("All")){
             eFtpDt=null;
         }
         else{
             eFtpDt="'"+eFtpDt+"'";
        }

         if(projectedEstSentDt.equals("")||projectedEstSentDt.equals("All")){
             projectedEstSentDt=null;
         }
         else{
             projectedEstSentDt="'"+projectedEstSentDt+"'";
        }


 if(addOption.equals("Modify")){
     compareValues=true;
    if(old_projectedPrinterDt.trim().equals("''")&&projectedPrinterDt==null){
        compareValues=true;
    }
    else{
        compareValues=old_projectedPrinterDt.equals(projectedPrinterDt);
    }

       if(!compareValues){

             if(updateField.length()>0){
                updateField += ", projected_printer_date="+ projectedPrinterDt+" ";
             }
             else{
                 updateField += " projected_printer_date="+projectedPrinterDt+" ";
             }


     }
}

if(addOption.equals("Modify")){
     compareValues=true;
    if(old_eFtpDt.trim().equals("''")&&eFtpDt==null){
        compareValues=true;
    }
    else{
        compareValues=old_eFtpDt.equals(eFtpDt);
    }

       if(!compareValues){

             if(updateField.length()>0){
                updateField += ", eFTP_date="+ eFtpDt+" ";
             }
             else{
                 updateField += " eFTP_date="+eFtpDt+" ";
             }


     }
}

if(addOption.equals("Modify")){
     compareValues=true;
    if(old_projectedEstSentDt.trim().equals("''")&&projectedEstSentDt==null){
        compareValues=true;
    }
    else{
        compareValues=old_projectedEstSentDt.equals(projectedEstSentDt);
    }

      if(!compareValues){

             if(updateField.length()>0){
                updateField += ", est_sent_date="+projectedEstSentDt+" ";
             }
             else{
                 updateField += " est_sent_date="+projectedEstSentDt+" ";
             }


     }
}


            //title
if(title.equals("")){
title=null;
}
else{
  title=encodeSpecialCharacter(title);
title="'"+title+"'";
}

//title
if(addOption.equals("Modify")){
compareValues=true;
    if(old_title.trim().equals("''")&&title==null){
        compareValues=true;
    }
    else{
        compareValues=old_title.equals(title);
    }

      if(!compareValues){

             if(updateField.length()>0){
                updateField += ", proj_bktitle="+title+" ";
             }
             else{
                 updateField += " proj_bktitle="+title+" ";
             }
           if(log_Option==1){log_table_name.add("projects");
           log_field_name.add("proj_bktitle");
           log_linked_field_name.add("proj_id");
           log_linked_field_value.add(prjid);
           log_old_value.add(old_title);
           log_new_value.add(title);
           log_changed_by.add(sesEmpId);
           log_reference_table.add("");
           log_reference_field.add("");
           log_reference_value.add("");
           }
     }
}

        if(mss.equals("")){
             mss=null;
         }
         else{
             mss="'"+mss+"'";
        }


if(addOption.equals("Modify")){

    if(old_mss.trim().equals("''")&&mss==null){
        compareValues=true;
    }
    else{
        compareValues=old_mss.equals(mss);
    }

if(!compareValues){

   if(updateField.length()>0){
                updateField += ", mss_pages="+mss+" ";
             }
             else{
                 updateField += " mss_pages="+mss+" ";
             }

           if(log_Option==1){log_table_name.add("projects");
           log_field_name.add("mss_pages");
           log_linked_field_name.add("proj_id");
           log_linked_field_value.add(prjid);
           log_old_value.add(old_mss);
           log_new_value.add(mss);
           log_changed_by.add(sesEmpId);
           log_reference_table.add("");
           log_reference_field.add("");
           log_reference_value.add("");
           }
     }
}


         if(estimated.equals("")){
             estimated=null;
         }
         else{
             estimated="'"+estimated+"'";
         }

if(addOption.equals("Modify")){

    if(old_estimated.trim().equals("''")&&estimated==null){
        compareValues=true;
    }
    else{
        compareValues=old_estimated.equals(estimated);
    }

    /*System.out.println("old_estimated:"+old_estimated);
    System.out.println("estimated:"+estimated);
    System.out.println("compareValues Estimate:"+compareValues);*/

if(!compareValues){

            if(updateField.length()>0){
                updateField += ", estimated_pages="+estimated+" ";
             }
             else{
                 updateField += " estimated_pages="+estimated+" ";
             }

           if(log_Option==1){log_table_name.add("projects");
           log_field_name.add("estimated_pages");
           log_linked_field_name.add("proj_id");
           log_linked_field_value.add(prjid);
           log_old_value.add(old_estimated);
           log_new_value.add(estimated);
           log_changed_by.add(sesEmpId);
           log_reference_table.add("");
           log_reference_field.add("");
           log_reference_value.add("");
           }
     }
}

/**Actual Pages should be automatically retrieved based on the BookMap input**/
         if(actual.equals("")){
             actual=null;
         }
         else{
             actual="'"+actual+"'";
        }

            if(actual!=null) {
             if(updateField.length()>0){
                updateField += ", actual_pages="+actual+" ";
             }
             else{
                 updateField += " actual_pages="+actual+" ";
             }
            }


            if (projLevelID.equals("")||projLevelID.equals("All")) {
                projLevelID = null;
            } else {
                projLevelID = "'" + projLevelID + "'";
            }

            if(projLevelID!=null) {
            if (updateField.length() > 0) {
                updateField += ", proj_level=" + projLevelID + " ";
            } else {
                updateField += " proj_level=" + projLevelID + " ";
            }
            }


            if(projEISBNCategID.equals("")&&projEISBN.equals("")||projEISBNCategID.equals("All")&&projEISBN.equals("")){
            } else {
                projEISBNCategID = ""+projEISBNCategID+"";
                projEISBN = "'" + projEISBN + "'";
            }

            if (!projEISBNCategID.equals("") && !projEISBN.equals("") || !projEISBNCategID.equals("All") && !projEISBN.equals("")) {
                if (updateField.length() > 0) {
                    updateField += ", " + projEISBNCategID + "=" + projEISBN + " ";
                } else {
                    updateField += " " + projEISBNCategID + "=" + projEISBN + " ";
                }
            }


         if(division.equals("")||division.equals("All")){
             division=null;
         }
         else{
             division="'"+division+"'";
        }

if(addOption.equals("Modify")){

    if(old_divisionID.trim().equals("''")&&division==null){
        compareValues=true;
    }
    else{
        compareValues=old_divisionID.equals(division);
    }

      if(!compareValues){
            if(updateField.length()>0){
                updateField += ", division_id="+division+" ";
             }
             else{
                 updateField += " division_id="+division+" ";
             }

           if(log_Option==1){log_table_name.add("projects");
           log_field_name.add("division_id");
           log_linked_field_name.add("proj_id");
           log_linked_field_value.add(prjid);
           log_old_value.add(old_divisionID);
           log_new_value.add(division);
           log_changed_by.add(sesEmpId);
           log_reference_table.add("contacts");
           log_reference_field.add("contact_id");
           log_reference_value.add("");
           }
     }
}

//stop

         if(buyer.equals("")||buyer.equals("All")){
             buyer=null;
         }
         else{
             buyer="'"+buyer+"'";
        }


if(addOption.equals("Modify")){
    if(old_buyerID.trim().equals("''")&&buyer==null){
        compareValues=true;
    }
    else{
        compareValues=old_buyerID.equals(buyer);
    }

      if(!compareValues){

            if(updateField.length()>0){
                updateField += ", buyer_id="+buyer+" ";
             }
             else{
                 updateField += " buyer_id="+buyer+" ";
             }
           if(log_Option==1){log_table_name.add("projects");
           log_field_name.add("buyer_id");
           log_linked_field_name.add("proj_id");
           log_linked_field_value.add(prjid);
           log_old_value.add(old_buyerID);
           log_new_value.add(buyer);
           log_changed_by.add(sesEmpId);
           log_reference_table.add("contacts");
           log_reference_field.add("contact_id");
           log_reference_value.add("");
           }
     }
}




        if(salesperon.equals("")||salesperon.equals("All")){
             salesperon=null;
         }
         else{
             salesperon="'"+salesperon+"'";
        }

   if(addOption.equals("Modify")){
    if(old_salespersonID.trim().equals("''")&&salesperon==null){
        compareValues=true;
    }
    else{
        compareValues=old_salespersonID.equals(salesperon);
    }

      if(!compareValues){
             if(updateField.length()>0){
                updateField += ", salesperson_id="+salesperon+" ";
             }
             else{
                 updateField += " salesperson_id="+salesperon+" ";
             }

           if(log_Option==1){log_table_name.add("projects");
           log_field_name.add("salesperson_id");
           log_linked_field_name.add("proj_id");
           log_linked_field_value.add(prjid);
           log_old_value.add(old_salespersonID);
           log_new_value.add(salesperon);
           log_changed_by.add(sesEmpId);
           log_reference_table.add("contacts");
           log_reference_field.add("contact_id");
           log_reference_value.add("");
           }
     }
}


         if(contact.equals("")||contact.equals("All")){
             contact=null;
         }
         else{
             contact="'"+contact+"'";
        }

if(addOption.equals("Modify")){
    if(old_contactID.trim().equals("''")&&contact==null){
        compareValues=true;
    }
    else{
        compareValues=old_contactID.equals(contact);
    }

      if(!compareValues){
            if(updateField.length()>0){
                updateField += ", contact_id="+contact+" ";
             }
             else{
                 updateField += " contact_id="+contact+" ";
             }

           if(log_Option==1){log_table_name.add("projects");
           log_field_name.add("contact_id");
           log_linked_field_name.add("proj_id");
           log_linked_field_value.add(prjid);
           log_old_value.add(old_contactID);
           log_new_value.add(contact);
           log_changed_by.add(sesEmpId);
           log_reference_table.add("contacts");
           log_reference_field.add("contact_id");
           log_reference_value.add("");
           }
     }
}

         if(printer.equals("")||printer.equals("All")){
             printer=null;
         }
         else{
             printer="'"+printer+"'";
        }


if(addOption.equals("Modify")){
    if(old_printerID.trim().equals("''")&&printer==null){
        compareValues=true;
    }
    else{
        compareValues=old_printerID.equals(printer);
    }

      if(!compareValues){
            if(updateField.length()>0){
                updateField += ", proj_printer="+printer+" ";
             }
             else{
                 updateField += " proj_printer="+printer+" ";
             }
           if(log_Option==1){log_table_name.add("projects");
           log_field_name.add("proj_printer");
           log_linked_field_name.add("proj_id");
           log_linked_field_value.add(prjid);
           log_old_value.add(old_printerID);
           log_new_value.add(printer);
           log_changed_by.add(sesEmpId);
           log_reference_table.add("contacts");
           log_reference_field.add("contact_id");
           log_reference_value.add("");
           }
     }
}

    if (addOption.equals("Modify")) {

        old_jobLostCode = old_jobLostCode.replace("'", "");
        if (!old_jobLostCode.equals(jobLostCode)) {
            projStatusStrChk = status;
            projStatusStrChk = projStatusStrChk.replace("'", "");
            projStatusChk = Integer.parseInt(projStatusStrChk);
            if (projStatusChk == 21) {
                if (updateField.length() > 0) {
                    updateField += ", job_lost_date=CURRENT_TIMESTAMP(), job_lost_reason_code = '" + jobLostCode + "' ";
                    
                } else {
                    updateField += " job_lost_date=CURRENT_TIMESTAMP(), job_lost_reason_code = '" + jobLostCode + "' ";
                    
                }
            }
        }
    }
         if(status.equals("")||status.equals("All")){
             status=null;
         }
         else{
             status="'"+status+"'";
        }

if(addOption.equals("Modify")){
    if(old_statusCode.trim().equals("''")&&status==null){
        compareValues=true;
    }
    else{
        compareValues=old_statusCode.equals(status);
    }

      if(!compareValues){
          if(updateField.length()>0){
              projStatusStrChk = status;
              projStatusStrChk = projStatusStrChk.replace("'", "");
              projStatusChk = Integer.parseInt(projStatusStrChk);
              if(projStatusChk==22) {

                updateField += ", ready_to_bill_date=CURRENT_TIMESTAMP() ";

              } else  if(projStatusChk==23) {

                updateField += ", billed_date=CURRENT_TIMESTAMP() ";

              }

                updateField += ", project_status="+status+" ";

             } else {
                  if(projStatusChk==22) {

                updateField += " ready_to_bill_date=CURRENT_TIMESTAMP() ";

                  } else  if(projStatusChk==23) {

                updateField += " billed_date=CURRENT_TIMESTAMP() ";
                  
                  }

                updateField += " project_status="+status+" ";
             }

           if(log_Option==1){log_table_name.add("projects");
           log_field_name.add("project_status");
           log_linked_field_name.add("proj_id");
           log_linked_field_value.add(prjid);
           log_old_value.add(old_statusCode);
           log_new_value.add(status);
           log_changed_by.add(sesEmpId);
           log_reference_table.add("status");
           log_reference_field.add("status_id");
           log_reference_value.add("");
           }
     }
}



         if(discipline.equals("")||discipline.equals("All")){
             discipline=null;
         }
         else{
             discipline="'"+discipline+"'";
        }


    /*System.out.println("old_discipline:"+old_disciplineID);
    System.out.println("discipline:"+discipline);
    System.out.println("compareValues discipline:"+compareValues);*/
if(addOption.equals("Modify")){
    if(old_disciplineID.trim().equals("''")&&discipline==null){
        compareValues=true;
    }
    else{
        compareValues=old_disciplineID.equals(discipline);
    }
      if(!compareValues){
           if(updateField.length()>0){
                updateField += ", projdiscipline_id="+discipline+" ";
             }
             else{
                 updateField += " projdiscipline_id="+discipline+" ";
             }
           if(log_Option==1){log_table_name.add("projects");
           log_field_name.add("projdiscipline_id");
           log_linked_field_name.add("proj_id");
           log_linked_field_value.add(prjid);
           log_old_value.add(old_disciplineID);
           log_new_value.add(discipline);
           log_changed_by.add(sesEmpId);
           log_reference_table.add("proj_discipline");
           log_reference_field.add("projdiscipline_id");
           log_reference_value.add("");
           }
     }
}


         if(projcategory.equals("")||projcategory.equals("All")){
             projcategory=null;
         }
         else{
             projcategory="'"+projcategory+"'";
        }

if(addOption.equals("Modify")){
    if(old_projcategoryID.trim().equals("''")&&projcategory==null){
        compareValues=true;
    }
    else{
        compareValues=old_projcategoryID.equals(projcategory);
    }
      if(!compareValues){
           if(updateField.length()>0){
                updateField += ", projcategory_id="+projcategory+" ";
             }
             else{
                 updateField += " projcategory_id="+projcategory+" ";
             }
           if(log_Option==1){log_table_name.add("projects");
           log_field_name.add("projcategory_id");
           log_linked_field_name.add("proj_id");
           log_linked_field_value.add(prjid);
           log_old_value.add(old_projcategoryID);
           log_new_value.add(projcategory);
           log_changed_by.add(sesEmpId);
           log_reference_table.add("proj_category");
           log_reference_field.add("projcategory_id");
           log_reference_value.add("");
           }
     }
}

         if(edition.equals("")||edition.equals("All")){
             edition=null;
         }
         else{
             edition="'"+edition+"'";
         }
if(addOption.equals("Modify")){
    if(old_edition.trim().equals("''")&&edition==null){
        compareValues=true;
    }
    else{
        compareValues=old_edition.equals(edition);
    }
      if(!compareValues){
           if(updateField.length()>0){
                updateField += ", edition="+edition+" ";
             }
             else{
                 updateField += " edition="+edition+" ";
             }

           if(log_Option==1){log_table_name.add("projects");
           log_field_name.add("edition");
           log_linked_field_name.add("proj_id");
           log_linked_field_value.add(prjid);
           log_old_value.add(old_edition);
           log_new_value.add(edition);
           log_changed_by.add(sesEmpId);
           log_reference_table.add("");
           log_reference_field.add("");
           log_reference_value.add("");
           }
     }
}

        if(priority.equals("")||priority.equals("All")){
             priority=null;
         }
         else{
             priority="'"+priority+"'";
        }

if(addOption.equals("Modify")){
    if(old_priorityID.trim().equals("''")&&priority==null){
        compareValues=true;
    }
    else{
        compareValues=old_priorityID.equals(priority);
    }
      if(!compareValues){
           if(updateField.length()>0){
                updateField += ", priority_id="+priority+" ";
             }
             else{
                 updateField += " priority_id="+priority+" ";
             }
           if(log_Option==1){log_table_name.add("projects");
           log_field_name.add("priority_id");
           log_linked_field_name.add("proj_id");
           log_linked_field_value.add(prjid);
           log_old_value.add(old_priorityID);
           log_new_value.add(priority);
           log_changed_by.add(sesEmpId);
           log_reference_table.add("proj_priority");
           log_reference_field.add("priority_id");
           log_reference_value.add("");
           }
     }
}

        if(copyright.equals("")||copyright.equals("All")){
             copyright=null;
         }
         else{
             copyright="'"+copyright+"'";
         }

if(addOption.equals("Modify")){
    if(old_copyright.trim().equals("''")&&copyright==null){
        compareValues=true;
    }
    else{
        compareValues=old_copyright.equals(copyright);
    }
      if(!compareValues){
            if(updateField.length()>0){
                updateField += ", copyright_year="+copyright+" ";
             }
             else{
                 updateField += " copyright_year="+copyright+" ";
             }

           if(log_Option==1){log_table_name.add("projects");
           log_field_name.add("copyright_year");
           log_linked_field_name.add("proj_id");
           log_linked_field_value.add(prjid);
           log_old_value.add(old_copyright);
           log_new_value.add(copyright);
           log_changed_by.add(sesEmpId);
           log_reference_table.add("");
           log_reference_field.add("");
           log_reference_value.add("");
           }
     }
}


         if(isbn10.equals("")||isbn10.equals("All")){
             isbn10=null;
         }
         else{
             isbn10="'"+isbn10+"'";
        }

if(addOption.equals("Modify")){
    if(old_isbn10.trim().equals("''")&&isbn10==null){
        compareValues=true;
    }
    else{
        compareValues=old_isbn10.equals(isbn10);
    }
      if(!compareValues){
             if(updateField.length()>0){
                updateField += ", proj_isbn_10="+isbn10+" ";
             }
             else{
                 updateField += " proj_isbn_10="+isbn10+" ";
             }

           if(log_Option==1){log_table_name.add("projects");
           log_field_name.add("proj_isbn_10");
           log_linked_field_name.add("proj_id");
           log_linked_field_value.add(prjid);
           log_old_value.add(old_isbn10);
           log_new_value.add(isbn10);
           log_changed_by.add(sesEmpId);
           log_reference_table.add("");
           log_reference_field.add("");
           log_reference_value.add("");
           }
     }
}
//////////////////////////Author ///////////////////////////////
            
        if(primaryAuthorID.equals("")){
             primaryAuthorID=null;
        } else {
             primaryAuthorID="'"+primaryAuthorID+"'";
        }

        if(addOption.equals("Modify")){
             compareValues=true;
            if(old_authorId.trim().equals("''")&&primaryAuthorID==null){
                compareValues=true;
            }
            else{
                compareValues=old_authorId.equals(primaryAuthorID);
            }

              if(!compareValues){

                     if(updateField.length()>0){
                        updateField += ", author_id="+primaryAuthorID+" ";
                     }
                     else{
                         updateField += " author_id="+primaryAuthorID+" ";
                     }
             }
        }
            
//////////////////////////////////////////////////////////////

            if(actualShipDate.equals("")){
             actualShipDate=null;
         }
         else{
             actualShipDate="'"+actualShipDate+"'";
        }


if(addOption.equals("Modify")){
     compareValues=true;
    if(old_actualShipDate.trim().equals("''")&&actualShipDate==null){
        compareValues=true;
    }
    else{
        compareValues=old_actualShipDate.equals(actualShipDate);
    }

      if(!compareValues){

             if(updateField.length()>0){
                updateField += ", act_ship_date="+actualShipDate+" ";
             }
             else{
                 updateField += " act_ship_date="+actualShipDate+" ";
             }


     }
}

             if(customerPO.equals("")){
             customerPO=null;
         }
         else{
             customerPO="'"+customerPO+"'";
        }
            
//Customer PO
if(addOption.equals("Modify")){
     compareValues=true;
    if(old_customerPO.trim().equals("''")&&customerPO==null){
        compareValues=true;
    }
    else{
        compareValues=old_customerPO.equals(customerPO);
    }
    
      if(!compareValues){

             if(updateField.length()>0){
                updateField += ", customer_po="+customerPO+" ";
             }
             else{
                 updateField += " customer_po="+customerPO+" ";
             }
     }
}
            //

            if(customerPOdate.equals("")){
             customerPOdate=null;
         }
         else{
             customerPOdate="'"+customerPOdate+"'";
        }

       if(addOption.equals("Modify")){
     compareValues=true;
         if(old_customerPOdate.trim().equals("''")&&customerPOdate==null){
        compareValues=true;
    }
    else{
        compareValues=old_customerPOdate.equals(customerPOdate);
    }

      if(!compareValues){
            
if(updateField.length()>0){
                updateField += ", customer_PODate="+customerPOdate+" ";
             }
             else{
                 updateField += " customer_PODate="+customerPOdate+" ";
             }

     }
}


   
            //Number of Chapters
            if (noOfChapters.equals("")) {
                noOfChapters = null;
            } else {
                noOfChapters = "'" + noOfChapters + "'";
            }

            if (addOption.equals("Modify")) {
                compareValues = true;
                if (old_noOfChapters.trim().equals("''") && noOfChapters == null) {
                    compareValues = true;
                } else {
                    compareValues = old_noOfChapters.equals(noOfChapters);
                }

                if (!compareValues) {

                    if (updateField.length() > 0) {
                        updateField += ", no_of_chapters=" + noOfChapters + " ";
                    } else {
                        updateField += " no_of_chapters=" + noOfChapters + " ";
                    }


                }
            }

            //MSS Format
            if (mssFormat.equals("")) {
                mssFormat = null;
            } else {
                mssFormat = "'" + mssFormat + "'";
            }


            if (addOption.equals("Modify")) {
                compareValues = true;
                if (old_mssFormat.trim().equals("''") && mssFormat == null) {
                    compareValues = true;
                } else {
                    compareValues = old_mssFormat.equals(mssFormat);
                }

                if (!compareValues) {

                    if (updateField.length() > 0) {
                        updateField += ", mss_format=" + mssFormat + " ";
                    } else {
                        updateField += " mss_format=" + mssFormat + " ";
                    }


                }
            }
//Paper Information
              //MSS Format
            if (paperInf.equals("")) {
                paperInf = null;
            } else {
                paperInf = "'" + paperInf + "'";
            }


            if (addOption.equals("Modify")) {
                compareValues = true;
                if (old_paperInf.trim().equals("''") && paperInf == null) {
                    compareValues = true;
                } else {
                    compareValues = old_paperInf.equals(paperInf);
                }

                if (!compareValues) {

                    if (updateField.length() > 0) {
                        updateField += ", paper_info=" + paperInf + " ";
                    } else {
                        updateField += " paper_info=" + paperInf + " ";
                    }


                }
            }
            //FST Present
            if (fstPresent.equals("")) {
                fstPresent = null;
            } else {
                fstPresent = "'" + fstPresent + "'";
            }

            if (addOption.equals("Modify")) {
                compareValues = true;
                if (old_fstPresent.trim().equals("''") && fstPresent == null) {
                    compareValues = true;
                } else {
                    compareValues = old_fstPresent.equals(fstPresent);
                }

                if (!compareValues) {

                    if (updateField.length() > 0) {
                        updateField += ", fst_present=" + fstPresent + " ";
                    } else {
                        updateField += " fst_present=" + fstPresent + " ";
                    }


                }
            }

            //XML Prop
            if (xmlProp.equals("")) {
                xmlProp = null;
            } else {
                xmlProp = "'" + xmlProp + "'";
            }


            if (addOption.equals("Modify")) {
                compareValues = true;
                if (old_xmlProp.trim().equals("''") && xmlProp == null) {
                    compareValues = true;
                } else {
                    compareValues = old_xmlProp.equals(xmlProp);
                }

                if (!compareValues) {

                    if (updateField.length() > 0) {
                        updateField += ", xml_prop=" + xmlProp + " ";
                    } else {
                        updateField += " xml_prop=" + xmlProp + " ";
                    }


                }
            }

if(isbn13.equals("")||isbn13.equals("All")){
     isbn13=null;
}
else{
     isbn13="'"+isbn13+"'";
}

if(addOption.equals("Modify")){
    if(old_isbn13.trim().equals("''")&&isbn13==null){
        compareValues=true;
    }
    else{
        compareValues=old_isbn13.equals(isbn13);
    }
      if(!compareValues){
            if(updateField.length()>0){
                updateField += ", proj_isbn_13="+isbn13+"  ";
             }
             else{
                 updateField += " proj_isbn_13="+isbn13+"  ";
             }
           if(log_Option==1){log_table_name.add("projects");
           log_field_name.add("proj_isbn_13");
           log_linked_field_name.add("proj_id");
           log_linked_field_value.add(prjid);
           log_old_value.add(old_isbn13);
           log_new_value.add(isbn13);
           log_changed_by.add(sesEmpId);
           log_reference_table.add("");
           log_reference_field.add("");
           log_reference_value.add("");
           }
     }
}

if(billLocationId.equals("")||billLocationId.equals("All")){
     billLocationId=null;
}
else{
     billLocationId="'"+billLocationId+"'";
}

if(addOption.equals("Modify")){
    if(old_billLocationId.trim().equals("''")&&billLocationId==null){
        compareValues=true;
    }
    else{
        compareValues=old_billLocationId.equals(billLocationId);
    }
      if(!compareValues){
            if(updateField.length()>0){
                updateField += ", facility_id="+billLocationId+"  ";
             }
             else{
                 updateField += " facility_id="+billLocationId+"  ";
             }
           if(log_Option==1){log_table_name.add("projects");
           log_field_name.add("facility_id");
           log_linked_field_name.add("proj_id");
           log_linked_field_value.add(prjid);
           log_old_value.add(old_billLocationId);
           log_new_value.add(billLocationId);
           log_changed_by.add(sesEmpId);
           log_reference_table.add("");
           log_reference_field.add("");
           log_reference_value.add("");
           }
     }
}



/* if(pageWidth.equals("")||pageWidth.equals("All")){
             pageWidth=null;
         }
         else{
             pageWidth="'"+pageWidth+"'";
         }
if(updateField.length()>0){
                updateField += ", page_width="+pageWidth+" ";
             }
             else{
                 updateField += " page_width="+pageWidth+" ";
             }*/

if(pageHeight.equals("")||pageHeight.equals("All")){
             pageHeight=null;
         }
         else{
             pageHeight="'"+pageHeight+"'";//old_pageHeight
         }

if(addOption.equals("Modify")){
    if(old_pageHeight.trim().equals("''")&&old_pageHeight==null){
        compareValues=true;
    }
    else{
        compareValues=old_pageHeight.equals(pageHeight);
    }
      if(!compareValues){
           if(pageHeight!=null) {
            if(updateField.length()>0){
                updateField += ", trim_size="+pageHeight+"  ";
             }
             else{
                 updateField += " trim_size="+pageHeight+"  ";
             }
          }
           if(log_Option==1){log_table_name.add("projects");
           log_field_name.add("trim_size");
           log_linked_field_name.add("proj_id");
           log_linked_field_value.add(prjid);
           log_old_value.add(old_pageHeight);
           log_new_value.add(pageHeight);
           log_changed_by.add(sesEmpId);
           log_reference_table.add("");
           log_reference_field.add("");
           log_reference_value.add("");
           }
     }
}


if(addOption.equals("Add")){

     if(projEISBNCategID.equals("")&&projEISBN.equals("")||projEISBNCategID.equals("All")&&projEISBN.equals("")){
    
     //the below variables are for PFKeyHandler methods
     preInsertSql="insert into projects(";
     handlerInsertField=" proj_id,project_count,";
     midInsertSql=" client_name,division_id, "+
                    " buyer_id,salesperson_id,contact_id,proj_printer,project_status, "+
                    " proj_workflow,projcategory_id,projdiscipline_id,proj_bktitle,edition,proj_name," +
                    " priority_id,copyright_year,mss_pages,estimated_pages,actual_pages,proj_isbn_10,proj_isbn_13, "+
                    " trim_size,color_id,facility_id,emp_id,proj_date,proj_level,planner,hybrid_planner,eFTP_date,projected_printer_date,est_sent_date,customer_po,customer_PODate,act_ship_date,author_id, "
                    + "no_of_chapters, mss_format, fst_present, xml_prop, paper_info) "+
                    " values (";
     postInsertSql= " "+clientCode+","+division+","+buyer+","+salesperon+", " +
                    " "+contact+","+printer+","+status+","+projtype+","+projcategory+","+discipline+", " +
                    " "+title+","+edition+","+name+"," +
                    " "+priority+","+copyright+","+mss+","+estimated+","+actual+","+isbn10+","+isbn13+", " +
                    " "+pageHeight+","+colorID+","+billLocationId+",'"+sesEmpId+"',CURRENT_TIMESTAMP(),"+projLevelID+","+planner+","+hybridPlanner+","+eFtpDt+","+projectedPrinterDt+" ,"+projectedEstSentDt+","+customerPO+","+customerPOdate+","+actualShipDate+","+primaryAuthorID+"," +
                    " "+ noOfChapters + "," + mssFormat + "," + fstPresent + "," + xmlProp + "," + paperInf + ")";

    // System.out.println("authorName:"+authorName);

if(!authorName.equals("")&&!authorName.equals("All")){
    
    try{
        System.out.println("insert into projects(proj_id,project_count,client_name,division_id, "+
                    " buyer_id,salesperson_id,contact_id,proj_printer,project_status, "+
                    " proj_workflow,projcategory_id,projdiscipline_id,proj_bktitle,edition,proj_name," +
                    " priority_id,copyright_year,mss_pages,estimated_pages,actual_pages,proj_isbn_10,proj_isbn_13, " +
                    " trim_size,color_id,facility_id,emp_id,proj_date,proj_level,planner,hybrid_planner,eFTP_date,projected_printer_date,est_sent_date,customer_po,customer_PODate,act_ship_date,author_id,"
                    + "no_of_chapters, mss_format, fst_present, xml_prop, paper_info) "+
                    " values ('"+maxprojId+"','"+maxprojCount+"',"+clientCode+","+division+","+buyer+","+salesperon+", " +
                    " "+contact+","+printer+","+status+","+projtype+","+projcategory+","+discipline+", " +
                    " "+title+","+edition+","+name+"," +
                    " "+priority+","+copyright+","+mss+","+estimated+","+actual+","+isbn10+","+isbn13+", " +
                    " "+pageHeight+","+colorID+","+billLocationId+",'"+sesEmpId+"',CURRENT_TIMESTAMP(),"+projLevelID+","+planner+","+hybridPlanner+","+eFtpDt+","+projectedPrinterDt+","+projectedEstSentDt+","+customerPO+","+customerPOdate+","+actualShipDate+","+primaryAuthorID+"," +
                    " "+ noOfChapters + "," + mssFormat + "," + fstPresent + "," + xmlProp + ",'" + paperInf + "')");
      addProj = statement.executeUpdate("insert into projects(proj_id,project_count,client_name,division_id, "+
                    " buyer_id,salesperson_id,contact_id,proj_printer,project_status, "+
                    " proj_workflow,projcategory_id,projdiscipline_id,proj_bktitle,edition,proj_name," +
                    " priority_id,copyright_year,mss_pages,estimated_pages,actual_pages,proj_isbn_10,proj_isbn_13, " +
                    " trim_size,color_id,facility_id,emp_id,proj_date,proj_level,planner,hybrid_planner,eFTP_date,projected_printer_date,est_sent_date,customer_po,customer_PODate,act_ship_date,author_id,"
                    + "no_of_chapters, mss_format, fst_present, xml_prop, paper_info) "+
                    " values ('"+maxprojId+"','"+maxprojCount+"',"+clientCode+","+division+","+buyer+","+salesperon+", " +
                    " "+contact+","+printer+","+status+","+projtype+","+projcategory+","+discipline+", " +
                    " "+title+","+edition+","+name+"," +
                    " "+priority+","+copyright+","+mss+","+estimated+","+actual+","+isbn10+","+isbn13+", " +
                    " "+pageHeight+","+colorID+","+billLocationId+",'"+sesEmpId+"',CURRENT_TIMESTAMP(),"+projLevelID+","+planner+","+hybridPlanner+","+eFtpDt+","+projectedPrinterDt+","+projectedEstSentDt+","+customerPO+","+customerPOdate+","+actualShipDate+","+primaryAuthorID+"," +
                    " "+ noOfChapters + "," + mssFormat + "," + fstPresent + "," + xmlProp + "," + paperInf + ")");
    
    }catch(Exception e){
        System.out.println("add project exception"+e.toString());
    }

        if(addProj>0){
            //if(!primaryAuthorID.equals("")) {
            if(primaryAuthorID!=null) {
            //block to set the selected author ID as the Primary Author ID for the project
     
            statement.executeUpdate("insert into project_authors(proj_id, author_id, primary_author)" +
                    "  values ("+maxprojId+","+primaryAuthorID+",'1')");
            }
            if("'2'".equals(projcategory)){
            statement.executeUpdate("INSERT INTO chapter (chapter_no,mss_count,receipt_date,proj_id,stage,added_date) "
                    + " VALUES ('Complete Book','0',CURRENT_TIMESTAMP(),'"+maxprojId+"','DEV',CURRENT_TIMESTAMP())");
            statement.executeUpdate("INSERT INTO chapter_plan(chapter_id,milestone_id,planned_Date) "
                    + "SELECT MAX(c.chapter_id),78,CURRENT_TIMESTAMP() FROM chapter c");
            statement.executeUpdate("INSERT INTO chapter_plan(chapter_id,milestone_id,planned_Date) "
                    + "SELECT MAX(c.chapter_id),74,CURRENT_TIMESTAMP() FROM chapter c");
            }else{
            statement.executeUpdate("INSERT INTO chapter (chapter_no,mss_count,receipt_date,proj_id,stage,added_date) "
                    + " VALUES ('Complete Book','0',CURRENT_TIMESTAMP(),'"+maxprojId+"','FSPM',CURRENT_TIMESTAMP())");
            statement.executeUpdate("INSERT INTO chapter_plan(chapter_id,milestone_id,planned_Date) "
                    + "SELECT MAX(c.chapter_id),77,CURRENT_TIMESTAMP() FROM chapter c");
            statement.executeUpdate("INSERT INTO chapter_plan(chapter_id,milestone_id,planned_Date) "
                    + "SELECT MAX(c.chapter_id),74,CURRENT_TIMESTAMP() FROM chapter c");
            }
        }
  }
     }

else if(!projEISBNCategID.equals("")&&!projEISBN.equals("")||!projEISBNCategID.equals("All")&&!projEISBN.equals("")){
     //the below variables are for PFKeyHandler methods
    // System.out.println("projLevelID2:"+projLevelID);
     preInsertSql="insert into projects(";
     handlerInsertField=" proj_id,project_count,";
     midInsertSql=" client_name,division_id, "+
                    " buyer_id,salesperson_id,contact_id,proj_printer,project_status, "+
                    " proj_workflow,projcategory_id,projdiscipline_id,proj_bktitle,edition,proj_name," +
                    " priority_id,copyright_year,mss_pages,estimated_pages,actual_pages,proj_isbn_10,proj_isbn_13, "+
                    " trim_size,color_id,facility_id,emp_id,proj_date,proj_level,"+projEISBNCategID+",planner,hybrid_planner,eFTP_date,projected_printer_date,est_sent_date,customer_po,customer_PODate,act_ship_date,author_id,"
                    + "no_of_chapters, mss_format, fst_present, xml_prop, paper_info)"+
                    " values (";
     postInsertSql= " "+clientCode+","+division+","+buyer+","+salesperon+", " +
                    " "+contact+","+printer+","+status+","+projtype+","+projcategory+","+discipline+", " +
                    " "+title+","+edition+","+name+"," +
                    " "+priority+","+copyright+","+mss+","+estimated+","+actual+","+isbn10+","+isbn13+", " +
                    " "+pageHeight+","+colorID+","+billLocationId+",'"+sesEmpId+"',CURRENT_TIMESTAMP(),"+projLevelID+","+projEISBN+","+planner+","+hybridPlanner+","+eFtpDt+","+projectedPrinterDt+","+projectedEstSentDt+","+customerPO+","+customerPOdate+","+actualShipDate+","+primaryAuthorID+","+
                    " "+ noOfChapters + "," + mssFormat + "," + fstPresent + "," + xmlProp + "," + paperInf + ")";

    // System.out.println("authorName:"+authorName);

if(!authorName.equals("")&&!authorName.equals("All")){
   // System.out.println("projLevelID3:"+projLevelID);
      addProj = statement.executeUpdate("insert into projects(proj_id,project_count,client_name,division_id, "+
                    " buyer_id,salesperson_id,contact_id,proj_printer,project_status, "+
                    " proj_workflow,projcategory_id,projdiscipline_id,proj_bktitle,edition,proj_name," +
                    " priority_id,copyright_year,mss_pages,estimated_pages,actual_pages,proj_isbn_10,proj_isbn_13, " +
                    " trim_size,color_id,facility_id,emp_id,proj_date,proj_level,"+projEISBNCategID+",planner,hybrid_planner,eFTP_date,projected_printer_date,est_sent_date,customer_po,customer_PODate,act_ship_date,author_id,"
                    + "no_of_chapters, mss_format, fst_present, xml_prop,paper_info)"+
                    " values ('"+maxprojId+"','"+maxprojCount+"',"+clientCode+","+division+","+buyer+","+salesperon+", " +
                    " "+contact+","+printer+","+status+","+projtype+","+projcategory+","+discipline+", " +
                    " "+title+","+edition+","+name+"," +
                    " "+priority+","+copyright+","+mss+","+estimated+","+actual+","+isbn10+","+isbn13+", " +
                    " "+pageHeight+","+colorID+","+billLocationId+",'"+sesEmpId+"',CURRENT_TIMESTAMP(),"+projLevelID+","+projEISBN+","+planner+","+hybridPlanner+","+eFtpDt+","+projectedPrinterDt+","+projectedEstSentDt+","+customerPO+","+customerPOdate+","+actualShipDate+","+primaryAuthorID+","+
                    " "+ noOfChapters + "," + mssFormat + "," + fstPresent + "," + xmlProp + "," + paperInf + ")");

        if(addProj>0){
            //if(!primaryAuthorID.equals("")) {
            if(primaryAuthorID!=null) {
            //block to set the selected author ID as the Primary Author ID for the project
            statement.executeUpdate("insert into project_authors(proj_id, author_id, primary_author)" +
                    "  values ("+maxprojId+","+primaryAuthorID+",'1')");
            }
            if("'2'".equals(projcategory)){
            statement.executeUpdate("INSERT INTO chapter (chapter_no,mss_count,receipt_date,proj_id,stage,added_date) "
                    + " VALUES ('Complete Book','0',CURRENT_TIMESTAMP(),'"+maxprojId+"','DEV',CURRENT_TIMESTAMP())");
            statement.executeUpdate("INSERT INTO chapter_plan(chapter_id,milestone_id,planned_Date) "
                    + "SELECT MAX(c.chapter_id),78,CURRENT_TIMESTAMP() FROM chapter c");
            statement.executeUpdate("INSERT INTO chapter_plan(chapter_id,milestone_id,planned_Date) "
                    + "SELECT MAX(c.chapter_id),74,CURRENT_TIMESTAMP() FROM chapter c");
            }else{
            statement.executeUpdate("INSERT INTO chapter (chapter_no,mss_count,receipt_date,proj_id,stage,added_date) "
                    + " VALUES ('Complete Book','0',CURRENT_TIMESTAMP(),'"+maxprojId+"','FSPM',CURRENT_TIMESTAMP())");
            statement.executeUpdate("INSERT INTO chapter_plan(chapter_id,milestone_id,planned_Date) "
                    + "SELECT MAX(c.chapter_id),77,CURRENT_TIMESTAMP() FROM chapter c");
            statement.executeUpdate("INSERT INTO chapter_plan(chapter_id,milestone_id,planned_Date) "
                    + "SELECT MAX(c.chapter_id),74,CURRENT_TIMESTAMP() FROM chapter c");
            }
        }
  }
     }
}
else{

//statement.executeUpdate("update project_authors set author_id="+primaryAuthorID+",primary_author = '1' where proj_id='"+prjid+"'");
    if (!old_disp_projName.equals(name)) {
        int projFolderRenamed = pfdi.ProjFolderUpdation(old_disp_projName, name, prjid);
        if (projFolderRenamed == 1) {
            updateProj_Sql = updateProj_Sql + updateField + where;
            addProj = statement.executeUpdate(updateProj_Sql);
            statement.executeUpdate("update project_authors set author_id=" + primaryAuthorID + ",primary_author = '1' where proj_id='" + prjid + "'");
        }
    } else {
        updateProj_Sql = updateProj_Sql + updateField + where;
        addProj = statement.executeUpdate(updateProj_Sql);
        statement.executeUpdate("update project_authors set author_id=" + primaryAuthorID + ",primary_author = '1' where proj_id='" + prjid + "'");
    }
//        if(addProj>0){
//            //block to set the selected author ID as the Primary Author ID for the project
//           if(!old_disp_projName.equals(surName)){
//               statement.executeUpdate("delete from project_authors "
//                       + " where proj_id='"+newProjId+"' and primary_author='1' ");
//               //query to check whether the selectwed author has been already mapped to the project or not
//               String authorMapped="";
//               ResultSet rsCheckAuthor = statement.executeQuery("select primary_author from project_authors "
//                       + "where proj_id='"+newProjId+"' and author_id='"+primaryAuthorID+"' ");
//               while(rsCheckAuthor.next()){
//                   authorMapped=rsCheckAuthor.getString(1);
//               }
//               if(authorMapped.equals("")){
//                     statement.executeUpdate("insert into project_authors(proj_id, author_id, primary_author)" +
//                    "  values ('"+newProjId+"','"+primaryAuthorID+"','1')");
//               }
//                 else{
//                       statement.executeUpdate("update project_authors set primary_author = '1' " +
//                        " where proj_id='"+newProjId+"' and author_id='"+primaryAuthorID+"' ");
//              }
//
//            }
//             else{
//
//             }
//        }

}

if(addProj>0){
    // newProjId=newProjId;

     if(addOption.equals("Modify")){
         

         if(log_table_name.size()>0){
                DBLog dbLog = new DBLog();
                dbLog.setTableNameList(log_table_name);
                dbLog.setFieldNameList(log_field_name);
                dbLog.setLinkFieldNameList(log_linked_field_name);
                dbLog.setLinkFieldValueList(log_linked_field_value);
                dbLog.setOldValueList(log_old_value);
                dbLog.setNewValueList(log_new_value);
                dbLog.setChangedByList(log_changed_by);
                dbLog.setReferenceTableName(log_reference_table);
                dbLog.setReferenceTableField(log_reference_field);
                dbLog.setRefFieldValueList(log_reference_value);
                dbLog.createLog();
         }
     }

}
else{
     newProjId="";
}

statement.close();
//System.out.println("addProj:"+addProj);
        }catch(SQLException sqle){
            //If Project addition fails due to duplicate key constratint then PFKeyHandler will be instantiated to increment the projectcount and add new project project ID without the user reentering all the information
            int getSqlCode=sqle.getErrorCode();
        //SQL Error Code - 1062 will result for the error --> Duplicate entry 'Proj_Id' for key 1
            if(getSqlCode==1062)
            {
               
               PFKeyHandler pfkh =  new PFKeyHandler();
               pfkh.setCountSql(contact);
               pfkh.setInsertField(handlerInsertField);
               pfkh.setPreInsertSql(preInsertSql);
               pfkh.setPostInsertSql(postInsertSql);
               pfkh.setMidInsertSql(midInsertSql);
               pfkh.setCountSql(handlerCountSql);
               pfkh.setProjAdd("1");//this flag field is set so that the handler will use the incremented ID to get added in two fields proj_count and proj_id. For almost rest of the other tables only one field need to be updated with the incremented value
              // pfkh.createNewId();//If the project insertion is success then setmaxProjId varaible to the returned ID
               if(pfkh.getRecordadded()>0){
                   maxprojId=pfkh.getcreatedId();
                   addProj=1;
               }

             
               sqle.printStackTrace();
            }

        }catch(Exception e){
           
            e.printStackTrace();
        }finally{
            try{
              con.close();
            }catch(SQLException e){
            System.out.println(e);
            }

        }


}

    public String createProjName(String newProjID){
        newProjName=surName+"_"+newProjID;
        return newProjName;
    }

}
