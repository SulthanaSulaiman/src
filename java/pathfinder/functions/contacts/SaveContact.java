/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.contacts;

import java.io.Serializable;
import connection.DBconnection;
import java.io.File;
import pathfinder.functions.generic.*;
import java.sql.*;
import java.util.*;
/**
 *
 * @author ramesh
 */
public class SaveContact implements Serializable, Cloneable {

    @Override
     public Object clone() throws CloneNotSupportedException {
         return super.clone();
     }
    private String firstName="";
    private String surName="";
    private String contactTypeID="";

    private String cntEmail="";
    private String cntWebsite="";
    private String cntCompany="";
    private String cntDivision="";
    private String isAlsoCustomer="";

    private String cntDesignation="";
    private String cntIsPerson="";
    private String cntAddress1="";

    private String cntAddress2="";
    private String cntCity="";
    private String cntState="";

    private String cntCountry="";
    private String cntZipCode="";
    private String cntSkype="";
    private String cntMsn="";
    private String cntAol="";

    private String cntGtalk="";
    private String cntYahoo="";
    private String cntLinkedin="";
    private String cntFacebook="";
    private String cntTwitter="";

    private String cntPhone1="";
    private String cntPhone2="";
    private String cntMobile="";

    private String cntFax1="";
    private String cntFax2="";
    private String cntFax3="";
    private String cntid="";
    private String newContactId;
    private  int addContact = 0;
    private String addOption="";
    private String cntDesc="";
    private String cntDept="";
    private  String maxCount="";
    private  String divisionCntid="";
    private  String divisionCompanyName="";
    private  String divisionCompanyid="";
    private  String companyid="";
    private  String sesEmpId="";
    private String cntVendorNum="";
    private String alertMessage="";
    private String contactSubSpcn = "";
    private String contactWorkingHrs = "";
    private String contactContactHrs = "";

    private String updateProjNames = "0";

    private String cntType = "";
    private String parellelCntId = "";
    private String baseParellelCntId = "";

    public String getCntType() {
        return cntType;
    }

    public void setCntType(String cntType) {
        this.cntType = cntType;
    }

    public String getParellelCntId() {
        return parellelCntId;
    }

    public void setParellelCntId(String parellelCntId) {
        this.parellelCntId = parellelCntId;
    }

    public String getBaseParellelCntId() {
        return baseParellelCntId;
    }

    public void setBaseParellelCntId(String baseParellelCntId) {
        this.baseParellelCntId = baseParellelCntId;
    }
    
    public String getContactContactHrs() {
        return contactContactHrs;
    }

    public void setContactContactHrs(String contactContactHrs) {
        this.contactContactHrs = contactContactHrs;
    }

    public String getContactSubSpcn() {
        return contactSubSpcn;
    }

    public void setContactSubSpcn(String contactSubSpcn) {
        this.contactSubSpcn = contactSubSpcn;
    }

    public String getContactWorkingHrs() {
        return contactWorkingHrs;
    }

    public void setContactWorkingHrs(String contactWorkingHrs) {
        this.contactWorkingHrs = contactWorkingHrs;
    }
    //this gives error message if the contact name exists already in the contacts table for the selected contact type
    /********************************/
    private String old_disp_firstName="";
    private String old_disp_surName="";
    private String old_disp_contactTypeID="";

    private String old_disp_cntEmail="";
    private String old_disp_cntWebsite="";
    private String old_disp_cntCompany="";
    private String old_disp_cntDivision="";
    private String old_disp_isAlsoCustomer="";

    private String old_disp_cntDesignation="";
    private String old_disp_cntIsPerson="";
    private String old_disp_cntAddress1="";

    private String old_disp_cntAddress2="";
    private String old_disp_cntCity="";
    private String old_disp_cntState="";

    private String old_disp_cntCountry="";
    private String old_disp_cntZipCode="";
    private String old_disp_cntSkype="";
    private String old_disp_cntMsn="";
    private String old_disp_cntAol="";

    private String old_disp_cntGtalk="";
    private String old_disp_cntYahoo="";
    private String old_disp_cntLinkedin="";
    private String old_disp_cntFacebook="";
    private String old_disp_cntTwitter="";

    private String old_disp_cntPhone1="";
    private String old_disp_cntPhone2="";
    private String old_disp_cntMobile="";

    private String old_disp_cntFax1="";
    private String old_disp_cntFax2="";
    private String old_disp_cntFax3="";
    private String old_disp_cntid="";
    private String old_disp_newContactId;
    private  int old_disp_addContact = 0;
    private String old_disp_addOption="";
    private String old_disp_cntDesc="";
    private String old_disp_cntDept="";
    private  String old_disp_maxCount="";
    private  String old_disp_divisionCntid="";
    private String old_disp_cntVendorNum="";
    private String old_contactSubSpcn = "";
    private String old_contactWorkingHrs = "";
    private String old_contactContactHrs = "";

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
    PFUtil pfu = new PFUtil();
    private Connection con = null;
    /*******************************/
public void collectOldValues(){
    ContactInfo cni=new ContactInfo();
    cni.setContactId(cntid);
    cni.collectContactInfo();

     old_disp_firstName = "'"+cni.getContactFirstName()+"'";
     old_disp_surName =  "'"+cni.getContactSecondName()+"'";
     old_disp_contactTypeID = "'"+cni.getContactTypeId()+"'";

     old_disp_cntEmail= "'"+cni.getContactEmail()+"'";
     old_disp_cntWebsite= "'"+cni.getCntWebSite()+"'";
     old_disp_cntCompany= "'"+cni.getContactCompany()+"'";
     old_disp_cntDivision= "'"+cni.getContactDivision()+"'";
     old_disp_isAlsoCustomer = "'"+cni.getContactIsAlsoCustomer()+"'";
     old_disp_cntDesignation="'"+cni.getContactDesig()+"'";
     old_disp_cntIsPerson="'"+cni.getContactCategory()+"'";
     old_disp_cntAddress1="'"+pfu.encodeSpecialCharacter(cni.getCntPriAdd())+"'";

     old_disp_cntAddress2="'"+pfu.encodeSpecialCharacter(cni.getCntSecAdd())+"'";

//pfu
     old_disp_cntCity="'"+cni.getContactCity()+"'";
     old_disp_cntState="'"+cni.getCntState()+"'";

     old_disp_cntCountry="'"+cni.getContactCountry()+"'";
     old_disp_cntZipCode="'"+cni.getCntZipCode()+"'";
     old_disp_cntSkype="'"+cni.getCntSkype()+"'";
     old_disp_cntMsn="'"+cni.getCntMSN()+"'";
     old_disp_cntAol="'"+cni.getCntAOL()+"'";

     old_disp_cntGtalk="'"+cni.getCntGtalk()+"'";
     old_disp_cntYahoo="'"+cni.getCntYahoo()+"'";
     old_disp_cntLinkedin="'"+cni.getCntLinkedIn()+"'";
     old_disp_cntFacebook="'"+cni.getCntFacebook()+"'";
     old_disp_cntTwitter="'"+cni.getCntTwitter()+"'";

     old_disp_cntPhone1="'"+cni.getContactPhone()+"'";
     old_disp_cntPhone2="'"+cni.getCntSecPhone()+"'";
     old_disp_cntMobile="'"+cni.getCntMobPhone()+"'";

    old_disp_cntFax1="'"+cni.getCntFax1()+"'";
    old_disp_cntFax2="'"+cni.getCntFax2()+"'";
    old_disp_addContact = 0;

    old_disp_cntDesc="'"+pfu.encodeSpecialCharacter(cni.getDescription())+"'";
    old_disp_cntDept="'"+cni.getcontactDepartment()+"'";
    old_disp_cntVendorNum="'"+cni.getcontactVendorNum()+"'";
    
    old_contactSubSpcn="'"+cni.getContactSubSpcn()+"'";
    old_contactWorkingHrs="'"+cni.getContactWorkingHrs()+"'";
    old_contactContactHrs="'"+cni.getContactContactHrs()+"'";
    
    old_disp_maxCount="";
    old_disp_divisionCntid="";
}

    public SaveContact() {
//System.out.println("savecontact called dump ");
    }

    public String getMaxContact(){
        return maxCount;
    }

   public void setContactId(String cntid){
        this.cntid=cntid;
            
   }

  public void setDivisionContactId(String divisionCntid){
        this.divisionCntid=divisionCntid;
   }

    public void setFName(String firstName){
        this.firstName=firstName;
    }

    public void setSName(String surName){
         this.surName=surName;
        // System.out.println("surName-setPoint:"+surName);
    }

   public void setContactType(String contactTypeID){
        this.contactTypeID=contactTypeID;
    }

   public void setCntEmail(String cntEmail){
        this.cntEmail=cntEmail;
    }

   public void setCntWebsite(String cntWebsite){
        this.cntWebsite=cntWebsite;
    }

    public void setCntCompany(String cntCompany){
        this.cntCompany=cntCompany;
      //  System.out.println("cntCompany:"+cntCompany);
    }
    
    public void setCntVendorNum(String cntVendorNum){
        this.cntVendorNum=cntVendorNum;
    }

   public void setCntCompanyId(String companyid){
        this.companyid=companyid;
      //  System.out.println("companyid for Division:"+companyid);
    }

    public void setIsAlsoCustomer(String isAlsoCustomer) {
        this.isAlsoCustomer = isAlsoCustomer;
    }

    //cntDivision

    public void setCntDivision(String cntDivision){
        this.cntDivision=cntDivision;
       // System.out.println("cntDivision:"+cntDivision);
    }

    public void setCntDesignation(String cntDesignation){
        this.cntDesignation=cntDesignation;
    }

    public void setCntIsPerson(String cntIsPerson){
        this.cntIsPerson=cntIsPerson;
    }

   public void setAddress1(String cntAddress1){
        this.cntAddress1=cntAddress1;
    }

    public void setAddress2(String cntAddress2){
        this.cntAddress2=cntAddress2;
    }

    public void setCity(String cntCity){
        this.cntCity=cntCity;
    }

    public void setState(String cntState){
        this.cntState=cntState;
    }

    public void setCountry(String cntCountry){
        this.cntCountry=cntCountry;
    }

    public void setZipCode(String cntZipCode){
        this.cntZipCode=cntZipCode;
    }

    public void setSkype(String cntSkype){
        this.cntSkype=cntSkype;
    }

    public void setMSN(String cntMsn){
        this.cntMsn=cntMsn;
    }

    public void setAOL(String cntAol){
        this.cntAol=cntAol;
    }

    public void setGtalk(String cntGtalk){
        this.cntGtalk=cntGtalk;
    }

    public void setCntYahoo(String cntYahoo){
        this.cntYahoo=cntYahoo;
    }

    public void setCntLinkedIn(String cntLinkedin){
        this.cntLinkedin=cntLinkedin;
    }

    public void setCntFacebook(String cntFacebook){
        this.cntFacebook=cntFacebook;
    }

    public void setCntTwitter(String cntTwitter){
        this.cntTwitter=cntTwitter;
    }

   public void setPhone1(String cntPhone1){
        this.cntPhone1=cntPhone1;
    }

    public void setPhone2(String cntPhone2){
        this.cntPhone2=cntPhone2;
    }

    public void setMobile(String cntMobile){
        this.cntMobile=cntMobile;
    }

   public void setFax1(String cntFax1){
        this.cntFax1=cntFax1;
    }

    public void setFax2(String cntFax2){
        this.cntFax2=cntFax2;
    }

    public void setFax3(String cntFax3){
        this.cntFax3=cntFax3;
    }

    public void setAddOption(String addOption){
        this.addOption=addOption;
    }
   public String getSaveContact(){
        return String.valueOf(addContact);
   }

   public String getContactId(){
       //System.out.println("newContactId:"+newContactId);
        return newContactId;
   }

   public void setDescription(String cntDesc){
        this.cntDesc=cntDesc;
   }

   public void setEmpId(String sesEmpId){
       this.sesEmpId=sesEmpId;
   }

   public void setDepartment(String cntDept){
       this.cntDept=cntDept;
   }

   public String getAlertMessage(){
       return alertMessage;
   }

   public void saveContact(){
  //System.out.println("savecontact called ");
      // collectOldValues();
       String logOption="";
       int log_Option=0;//integer equivalent of logOption
       int divFlag = 0;
       int divDupFlag = 0;
   String contactCategoryCheck=cntIsPerson;
//System.out.println("sample");
        pathfinder.util.SpecialCharHandler splChar = new pathfinder.util.SpecialCharHandler();
        try{
        DBconnection dbcon = new DBconnection();
        con = dbcon.getSampleProperty();

        Statement statement = con.createStatement();
        Statement statement1 = con.createStatement();
        ResultSet rsLogOption = statement.executeQuery("select log_option from dblog_preference where module_name='contacts' ");
        while(rsLogOption.next()){
            logOption=rsLogOption.getString("log_option");
        }
        rsLogOption.close();
        log_Option=Integer.parseInt(logOption);

        //the below four varaibles are to check wther the contact name already exists in the contacts table or not
        String checkCntNameSql = "";
        String checkInputName = "";
        String getExistingCntName = "";
        String getExistingCntId = "";
        int cntNameDuplicated=0;//If this variable hold the value as zero then only the contact will be added or else it will not be added to avoid duplication
//each contact is unique to a company and its divison and hence duplicates
        //will not be allowed if the contact entered already exists for the same client/company and Divison
         ResultSet rsCheckCntName = null;
         if(contactCategoryCheck.equals("1")){
             checkInputName=firstName.trim()+" "+surName.trim();
             
             checkCntNameSql = "select concat(cnt.firstname,' ',cnt.surname),cnt.contact_id from contacts cnt,contacttype_map cntm where cntm.contact_id=cnt.contact_id "
                     + " and cntm.type_id='"+contactTypeID+"' ";

             /*if(contactTypeID.equals("1") || contactTypeID.equals("2")){
                 checkCntNameSql += "and (cnt.company='"+cntCompany+"' and cnt.division='"+cntDivision+"' or concat(firstname,' ',surname) = '"+checkInputName+"') ";
             }*/
//             System.out.println("cntCompany:"+cntCompany);
//             System.out.println("cntDivision:"+cntDivision);
             //if(contactTypeID.equals("1") || contactTypeID.equals("2") || contactTypeID.equals("3") || contactTypeID.equals("4") || contactTypeID.equals("5") || contactTypeID.equals("6") || contactTypeID.equals("7") || contactTypeID.equals("8")){
                 if((cntCompany.equals("")&&cntDivision.equals("All"))||(cntCompany.equals("")&&cntDivision.equals(""))){
                     checkCntNameSql += "and (concat(firstname,' ',surname) = '"+checkInputName+"' OR firstname='"+firstName.trim()+"' AND surname='"+surName.trim()+"')  ";
                 }
                 else if((!cntCompany.equals("")&&!cntDivision.equals("All"))||(!cntCompany.equals("")&&!cntDivision.equals(""))){
                 checkCntNameSql += "and cnt.company='"+cntCompany.trim()+"' and (concat(firstname,' ',surname) = '"+checkInputName+"' OR firstname='"+firstName.trim()+"' AND surname='"+surName.trim()+"') ";
                 }
             //}


         
            rsCheckCntName = statement.executeQuery(checkCntNameSql);
             if(rsCheckCntName.next()){
                    rsCheckCntName = statement.executeQuery(checkCntNameSql);
                    while(rsCheckCntName.next()){
                        getExistingCntName = splChar.decoding(rsCheckCntName.getString(1).trim());
                        getExistingCntId = rsCheckCntName.getString(2).trim();
                        System.out.println("checkCntNameSql:"+getExistingCntName);
                    }
             }
             else{
                        getExistingCntName = "";
                        getExistingCntId = "";
             }


         }
        else if(contactCategoryCheck.equals("2")){
             checkInputName=cntCompany;             
          /*   checkCntNameSql = "select cnt.company,cnt.contact_id from contacts cnt,contacttype_map cntm where cntm.contact_id=cnt.contact_id "
                     + " and cntm.type_id='"+contactTypeID+"' "
                     + "and cnt.company='"+cntCompany+"' and cnt.division='"+cntDivision+"' ";
System.out.println("checkCntNameSql:"+checkCntNameSql);*/
             //System.out.println("cntCompany:"+cntCompany);
           //  System.out.println("cntDivision:"+cntDivision);


             

             if(!contactTypeID.equals("2")){
                 checkCntNameSql = " select cnt.company,cnt.contact_id,divi.company from contacts cnt, "
                     + "contacttype_map cntm,contacts divi,belongs_to  blt,contacts_type_master ctpm "
                     + "where cntm.contact_id=cnt.contact_id  and  cntm.type_id=ctpm.type_id and "
                     + "blt.parent_contact=cnt.contact_id and blt.contact_id=divi.contact_id ";

                 checkCntNameSql += " and ctpm.type_id='"+contactTypeID+"' and (cnt.company='"+cntCompany.trim()+"' and divi.company='"+cntDivision.trim()+"' or cnt.company='"+cntCompany.trim()+"') ";
             }

             if(contactTypeID.equals("2")){
                 checkCntNameSql = " select cnt.company,divi.contact_id,divi.company from contacts cnt, "
                     + "contacttype_map cntm,contacts divi,belongs_to  blt,contacts_type_master ctpm "
                     + "where cntm.contact_id=cnt.contact_id  and  cntm.type_id=ctpm.type_id and "
                     + "blt.parent_contact=cnt.contact_id and blt.contact_id=divi.contact_id ";

                 checkCntNameSql += " and cnt.company='"+cntCompany.trim()+"' and divi.company='"+cntDivision.trim()+"' ";
             }

          //  System.out.println("checkCntNameSql:"+checkCntNameSql);

            rsCheckCntName = statement.executeQuery(checkCntNameSql);

             if(rsCheckCntName.next()){
            rsCheckCntName = statement.executeQuery(checkCntNameSql);
                while(rsCheckCntName.next()){
                    getExistingCntName = splChar.decoding(rsCheckCntName.getString(1).trim());
                    getExistingCntId = rsCheckCntName.getString(2).trim();
                }
             }
             else{
                        getExistingCntName = "";
                        getExistingCntId = "";
             }

         }
         rsCheckCntName.close();

if(!getExistingCntName.equals("")){

            if(contactCategoryCheck.equals("2")&&!contactTypeID.equals("2")){
                divFlag++;
            }
            if(contactCategoryCheck.equals("2")&&contactTypeID.equals("2")){
                divDupFlag++;
            }

            
          //  System.out.println("divDupFlag:"+divDupFlag);

            if(cntid.equals("")){
                cntNameDuplicated=1;
                
            }
             else{
                if(addOption.equals("modify")){
                    
                    if(getExistingCntId.equals(cntid))
                    {
                        
                         cntNameDuplicated=0;
                    }
                     else{
                        System.out.println("divFlag:"+divFlag);
                        cntNameDuplicated=1;
                     }

                }

             }

            }

            if(cntNameDuplicated==1){
                //this IF condition will get executed to avoid duplicate contact name being added into the system
               alertMessage = "Already exists.";
               if (divFlag != 0){
                   alertMessage += " Try to add in a division contact type, if it is different division for this company.";
               }
               if (divDupFlag != 0){
                   alertMessage += " Please add different division for this company.";
               }
            }
            else{
             //the entire functionality will work in this else loop as this will get executed only after confirming that
             //the contact name doesnot exist in Pathfinder already
                       //System.out.println("logOption:"+logOption);


            }//close of bigger else loop that gets executed after the duplication name check


            String  updateContact_Sql=" update contacts set ";
            String updateField="";
            String where = " where contact_id='"+cntid+"' ";
            String chkContactType="";
   //    System.out.println(" select max(contact_id) from contacts ");
   //    System.out.println(" cntid: "+cntid);
        int incrementor=0;
//System.out.println("addOption:"+addOption);

        if(cntid.equals(""))
        {//create a new ID

             ResultSet rsMaxContactCount = statement.executeQuery("select max(contact_id) from contacts");
                   while(rsMaxContactCount.next()){
                        maxCount=rsMaxContactCount.getString(1);
                      // System.out.println("maxCount-after Query:"+maxCount);
                    }
             rsMaxContactCount.close();

                 if(maxCount==null||maxCount.equals("")){
                     maxCount="0";
                 }
                    incrementor=Integer.parseInt(maxCount);
                    incrementor=incrementor+1;
                    newContactId=String.valueOf(incrementor);
        }
        else{
                newContactId=cntid;//passed contact Id will be used for modification
        }


//System.out.println("newContactId-before cntIsPerson:"+newContactId);
boolean compareValues=true;
if(baseParellelCntId.equals("")) {
    if((firstName.equals("")||firstName.equals("All"))){
     firstName=null;
}
else{
     if(cntIsPerson.equals("1"))
            {
             firstName=" '"+firstName+"' ";
            }else{
                 firstName=null;
                 compareValues=false;//coz ehile modifying the records the logic should work correctly
            }
}

   if(addOption.equals("modify")){

               if(old_disp_firstName.trim().equals("''")&&firstName==null){
                    compareValues=true;
                }
                else{
                        if(cntIsPerson.equals("1")){//this IF condition is used to avoid NullPointerException
                            compareValues=old_disp_firstName.equals(firstName);
                        }
                }



                 if(!compareValues){
                            if(updateField.length()>0){
                            updateField += ", firstname="+firstName+" ";
                            }
                            else{
                             updateField += " firstname="+firstName+" ";
                            }
                            if(log_Option==1){
                       log_table_name.add("contacts");
                       log_field_name.add("firstname");
                       log_linked_field_name.add("contact_id");
                       log_linked_field_value.add(cntid);
                       log_old_value.add(old_disp_firstName);
                       log_new_value.add(firstName);
                       log_changed_by.add(sesEmpId);
                       log_reference_table.add("");
                       log_reference_field.add("");
                       log_reference_value.add("");
                   }
                }
       }
            }

if(baseParellelCntId.equals("")) {
if(surName.equals("")||surName.equals("All")){
     surName=null;
}
else{
     if(cntIsPerson.equals("1"))
            {
             surName=" '"+surName+"' ";
            }else{
                 surName=null;
                 compareValues=false;//coz ehile modifying the records the logic should work correctly
            }
}

   if(addOption.equals("modify")){
               if(old_disp_surName.trim().equals("''")&&surName==null){
                    compareValues=true;
                }
                else{
                        if(cntIsPerson.equals("1")){//this IF condition is used to avoid NullPointerException
                           compareValues=old_disp_surName.equals(surName);
                        }
                }

               if(!compareValues){
                updateProjNames = "1";
                if(updateField.length()>0){
                updateField += ", surName="+surName+" ";
                }
                else{
                 updateField += " surName="+surName+" ";
                }

                if(log_Option==1){
                       log_table_name.add("contacts");
                       log_field_name.add("surName");
                       log_linked_field_name.add("contact_id");
                       log_linked_field_value.add(cntid);
                       log_old_value.add(old_disp_surName);
                       log_new_value.add(surName);
                       log_changed_by.add(sesEmpId);
                       log_reference_table.add("");
                       log_reference_field.add("");
                       log_reference_value.add("");
                }
               }
       }
            }
//System.out.println("log_linked_field_value:"+log_linked_field_value);


if((cntEmail.equals("")||cntEmail.equals("All"))){
     cntEmail=null;
}
else{
             cntEmail=" '"+cntEmail+"' ";
}
   if(addOption.equals("modify")){
               if(old_disp_cntEmail.trim().equals("''")&&cntEmail==null){
                    compareValues=true;
                }
                else{
                    compareValues=old_disp_cntEmail.equals(cntEmail);
                }

                if(!compareValues){if(updateField.length()>0){
                updateField += ", email="+cntEmail+" ";
                }
                else{
                 updateField += " email="+cntEmail+" ";
                }

    if(log_Option==1){
                       log_table_name.add("contacts");
                       log_field_name.add("email");
                       log_linked_field_name.add("contact_id");
                       log_linked_field_value.add(cntid);
                       log_old_value.add(old_disp_cntEmail);
                       log_new_value.add(cntEmail);
                       log_changed_by.add(sesEmpId);
                       log_reference_table.add("");
                       log_reference_field.add("");
                       log_reference_value.add("");
    }
 }
}


if((cntWebsite.equals("")||cntWebsite.equals("All"))){
     cntWebsite=null;
}
else{
             cntWebsite=" '"+cntWebsite+"' ";
}
   if(addOption.equals("modify")){
               if(old_disp_cntWebsite.trim().equals("''")&&cntWebsite==null){
                    compareValues=true;
                }
                else{
                    compareValues=old_disp_cntWebsite.equals(cntWebsite);
                }

                if(!compareValues){if(updateField.length()>0){
                updateField += ", website="+cntWebsite+" ";
                }
                else{
                 updateField += " website="+cntWebsite+" ";
                }

 if(log_Option==1){
                       log_table_name.add("contacts");
                       log_field_name.add("website");
                       log_linked_field_name.add("contact_id");
                       log_linked_field_value.add(cntid);
                       log_old_value.add(old_disp_cntWebsite);
                       log_new_value.add(cntWebsite);
                       log_changed_by.add(sesEmpId);
                       log_reference_table.add("");
                       log_reference_field.add("");
                       log_reference_value.add("");
                    }
                }
       }



if((cntDept.equals("")||cntDept.equals("All"))){
     cntDept=null;
}
else{
             cntDept=" '"+cntDept+"' ";
}
   if(addOption.equals("modify")){
               if(old_disp_cntDept.trim().equals("''")&&cntDept==null){
                    compareValues=true;
                }
                else{
                    compareValues=old_disp_cntDept.equals(cntDept);
                }

                if(!compareValues){
                    if(updateField.length()>0){
                updateField += ", department="+cntDept+" ";
                }
                else{
                 updateField += " department="+cntDept+" ";
                }

 if(log_Option==1){
                       log_table_name.add("contacts");
                       log_field_name.add("department");
                       log_linked_field_name.add("contact_id");
                       log_linked_field_value.add(cntid);
                       log_old_value.add(old_disp_cntDept);
                       log_new_value.add(cntDept);
                       log_changed_by.add(sesEmpId);
                       log_reference_table.add("");
                       log_reference_field.add("");
                       log_reference_value.add("");
                    }
                }
       }


if(contactTypeID.equals("2")){
    divisionCompanyName=cntCompany;//If the Customer name has to be modofied for an existing diviosn this varaible s needed
    cntCompany=cntDivision;//If the selected contact type is divison then the value of Diviosn field should be saved as company
}

if((cntCompany.equals("")||cntCompany.equals("All"))){
     cntCompany=null;
}
else{
             cntCompany=" '"+cntCompany+"' ";
}

//System.out.println("cntCompany:"+cntCompany);


   if(addOption.equals("modify")){
               if(old_disp_cntCompany.trim().equals("''")&&cntCompany==null){
                    compareValues=true;
                }
                else{
                    compareValues=old_disp_cntCompany.equals(cntCompany);
                }

                if(!compareValues){
                    if(updateField.length()>0){
                updateField += ", company="+cntCompany+" ";
                }
                else{
                 updateField += " company="+cntCompany+" ";
                }

 if(log_Option==1){
                       log_table_name.add("contacts");
                       log_field_name.add("company");
                       log_linked_field_name.add("contact_id");
                       log_linked_field_value.add(cntid);
                       log_old_value.add(old_disp_cntCompany);
                       log_new_value.add(cntCompany);
                       log_changed_by.add(sesEmpId);
                       log_reference_table.add("");
                       log_reference_field.add("");
                       log_reference_value.add("");
                    }
                }
       }


if(!contactTypeID.equals("2")){//any type other than Diviosn if selected then the below block of code should get appended to the query
    if((cntDivision.equals("")||cntDivision.equals("All"))){
     cntDivision=null;
}
else{
             cntDivision=" '"+cntDivision+"' ";
}
   if(addOption.equals("modify")){
               if(old_disp_cntDivision.trim().equals("''")&&cntDivision==null){
                    compareValues=true;
                }
                else{
                   if(old_disp_cntDivision!=null&&cntDivision!=null){
                       compareValues=old_disp_cntDivision.equals(cntDivision);
                   }

                }

                if(!compareValues){
                    if(updateField.length()>0){
                updateField += ", division="+cntDivision+" ";
                }
                else{
                 updateField += " division="+cntDivision+" ";
                }

 if(log_Option==1){
                       log_table_name.add("contacts");
                       log_field_name.add("division");
                       log_linked_field_name.add("contact_id");
                       log_linked_field_value.add(cntid);
                       log_old_value.add(old_disp_cntDivision);
                       log_new_value.add(cntDivision);
                       log_changed_by.add(sesEmpId);
                       log_reference_table.add("");
                       log_reference_field.add("");
                       log_reference_value.add("");
                    }
                }
       }

}


/***********************************/
if((cntVendorNum.equals("")||cntVendorNum.equals("All"))){
     cntVendorNum=null;
}
else{
                cntVendorNum=" '"+cntVendorNum+"' ";
}
   if(addOption.equals("modify")){
               if(old_disp_cntVendorNum.trim().equals("''")&&cntVendorNum==null){
                    compareValues=true;
                }
                else{
                          compareValues=old_disp_cntVendorNum.equals(cntVendorNum);
                }

                if(!compareValues)
                {
                 if(updateField.length()>0){
                updateField += ", vendor_number="+cntVendorNum+" ";
                }
                else{
                 updateField += " vendor_number="+cntVendorNum+" ";
                }

 if(log_Option==1){
                       log_table_name.add("contacts");
                       log_field_name.add("vendor_number");
                       log_linked_field_name.add("contact_id");
                       log_linked_field_value.add(cntid);
                       log_old_value.add(old_disp_cntVendorNum);
                       log_new_value.add(cntVendorNum);
                       log_changed_by.add(sesEmpId);
                       log_reference_table.add("");
                       log_reference_field.add("");
                       log_reference_value.add("");
                    }
                }
       }
/***********************************/
   
   
   
/*freelancer related stuff***/
   
   /***********************************/
if((contactSubSpcn.equals("")||contactSubSpcn.equals("All"))){
     contactSubSpcn=null;
}
else{
                contactSubSpcn=" '"+contactSubSpcn+"' ";
}
   if(addOption.equals("modify")){
               if(old_contactSubSpcn.trim().equals("''")&&contactSubSpcn==null){
                    compareValues=true;
                }
                else{
                          compareValues=old_contactSubSpcn.equals(contactSubSpcn);
                }

                if(!compareValues)
                {
                 if(updateField.length()>0){
                updateField += ", sub_spcn="+contactSubSpcn+" ";
                }
                else{
                 updateField += " sub_spcn="+contactSubSpcn+" ";
                }

 if(log_Option==1){
                       log_table_name.add("contacts");
                       log_field_name.add("sub_spcn");
                       log_linked_field_name.add("contact_id");
                       log_linked_field_value.add(cntid);
                       log_old_value.add(old_contactSubSpcn);
                       log_new_value.add(contactSubSpcn);
                       log_changed_by.add(sesEmpId);
                       log_reference_table.add("");
                       log_reference_field.add("");
                       log_reference_value.add("");
                    }
                }
       }
/***********************************/


      /***********************************/
if((contactWorkingHrs.equals("")||contactWorkingHrs.equals("All"))){
     contactWorkingHrs=null;
}
else{
                contactWorkingHrs=" '"+contactWorkingHrs+"' ";
}
   if(addOption.equals("modify")){
               if(old_contactWorkingHrs.trim().equals("''")&&contactWorkingHrs==null){
                    compareValues=true;
                }
                else{
                          compareValues=old_contactWorkingHrs.equals(contactWorkingHrs);
                }

                if(!compareValues)
                {
                 if(updateField.length()>0){
                updateField += ", working_hrs="+contactWorkingHrs+" ";
                }
                else{
                 updateField += " working_hrs="+contactWorkingHrs+" ";
                }

 if(log_Option==1){
                       log_table_name.add("contacts");
                       log_field_name.add("working_hrs");
                       log_linked_field_name.add("contact_id");
                       log_linked_field_value.add(cntid);
                       log_old_value.add(old_contactWorkingHrs);
                       log_new_value.add(contactWorkingHrs);
                       log_changed_by.add(sesEmpId);
                       log_reference_table.add("");
                       log_reference_field.add("");
                       log_reference_value.add("");
                    }
                }
       }
/***********************************/
   
   
      /***********************************/
if((contactContactHrs.equals("")||contactContactHrs.equals("All"))){
     contactContactHrs=null;
}
else{
                contactContactHrs=" '"+contactContactHrs+"' ";
}
   if(addOption.equals("modify")){
               if(old_contactContactHrs.trim().equals("''")&&contactContactHrs==null){
                    compareValues=true;
                }
                else{
                          compareValues=old_contactContactHrs.equals(contactContactHrs);
                }

                if(!compareValues)
                {
                 if(updateField.length()>0){
                updateField += ", contact_hrs="+contactContactHrs+" ";
                }
                else{
                 updateField += " contact_hrs="+contactContactHrs+" ";
                }

 if(log_Option==1){
                       log_table_name.add("contacts");
                       log_field_name.add("contact_hrs ");
                       log_linked_field_name.add("contact_id");
                       log_linked_field_value.add(cntid);
                       log_old_value.add(old_contactContactHrs);
                       log_new_value.add(contactContactHrs);
                       log_changed_by.add(sesEmpId);
                       log_reference_table.add("");
                       log_reference_field.add("");
                       log_reference_value.add("");
                    }
                }
       }
/***********************************/
   
/*freelancer related stuff***/

if(baseParellelCntId.equals("")) {
if(cntDesignation.equals("")||cntDesignation.equals("All")){
     cntDesignation=null;
}
else{

        if(cntIsPerson.equals("1"))
            {
                cntDesignation=" '"+cntDesignation+"' ";
            }else{
                 cntDesignation=null;
                 compareValues=false;//coz ehile modifying the records the logic should work correctly
            }

}
   if(addOption.equals("modify")){
               if(old_disp_cntDesignation.trim().equals("''")&&cntDesignation==null){
                    compareValues=true;
                }
                else{
                        if(cntIsPerson.equals("1")){//this IF condition is used to avoid NullPointerException
                          compareValues=old_disp_cntDesignation.equals(cntDesignation);
                        }
                }

                if(!compareValues){if(updateField.length()>0){
                updateField += ", designation="+cntDesignation+" ";
                }
                else{
                 updateField += " designation="+cntDesignation+" ";
                }

 if(log_Option==1){
                       log_table_name.add("contacts");
                       log_field_name.add("designation");
                       log_linked_field_name.add("contact_id");
                       log_linked_field_value.add(cntid);
                       log_old_value.add(old_disp_cntDesignation);
                       log_new_value.add(cntDesignation);
                       log_changed_by.add(sesEmpId);
                       log_reference_table.add("");
                       log_reference_field.add("");
                       log_reference_value.add("");
                    }
                }
       }
            }


       // System.out.println("cntIsPerson:"+cntIsPerson);

if((cntIsPerson.equals("")||cntIsPerson.equals("All"))){
     cntIsPerson=null;
}
else{
             cntIsPerson=" '"+cntIsPerson+"' ";
}
   if(addOption.equals("modify")){
               if(old_disp_cntIsPerson.trim().equals("''")&&cntIsPerson==null){
                    compareValues=true;
                }
                else{
                    compareValues=old_disp_cntIsPerson.equals(cntIsPerson);
                }

                if(!compareValues){if(updateField.length()>0){
                updateField += ", is_person="+cntIsPerson+" ";
                }
                else{
                 updateField += " is_person="+cntIsPerson+" ";
                }

 if(log_Option==1){
                       log_table_name.add("contacts");
                       log_field_name.add("is_person");
                       log_linked_field_name.add("contact_id");
                       log_linked_field_value.add(cntid);
                       log_old_value.add(old_disp_cntIsPerson);
                       log_new_value.add(cntIsPerson);
                       log_changed_by.add(sesEmpId);
                       log_reference_table.add("contact_category");
                       log_reference_field.add("cnt_category");
                       log_reference_value.add("");
                    }
                }
       }


if((cntAddress1.equals("")||cntAddress1.equals("All"))){
     cntAddress1=null;
}
else{
            cntAddress1=pfu.encodeSpecialCharacter(cntAddress1);
            cntAddress1=" '"+cntAddress1+"' ";
}
   if(addOption.equals("modify")){
               if(old_disp_cntAddress1.trim().equals("''")&&cntAddress1==null){
                    compareValues=true;
                }
                else{
                    compareValues=old_disp_cntAddress1.equals(cntAddress1);
                }

                if(!compareValues){
                    if(updateField.length()>0){
                updateField += ", address_1="+cntAddress1+" ";
                }
                else{
                 updateField += " address_1="+cntAddress1+" ";
                }

 if(log_Option==1){
                       log_table_name.add("contacts");
                       log_field_name.add("address_1");
                       log_linked_field_name.add("contact_id");
                       log_linked_field_value.add(cntid);
                       log_old_value.add(old_disp_cntAddress1);
                       log_new_value.add(cntAddress1);
                       log_changed_by.add(sesEmpId);
                       log_reference_table.add("");
                       log_reference_field.add("");
                       log_reference_value.add("");
                    }
                }
       }

     if((cntAddress2.equals("")||cntAddress2.equals("All"))){
     cntAddress2=null;
}
else{
             cntAddress2=pfu.encodeSpecialCharacter(cntAddress2);
             cntAddress2=" '"+cntAddress2+"' ";
}
   if(addOption.equals("modify")){
               if(old_disp_cntAddress2.trim().equals("''")&&cntAddress2==null){
                    compareValues=true;
                }
                else{
                    compareValues=old_disp_cntAddress2.equals(cntAddress2);
                }

                if(!compareValues){
                    if(updateField.length()>0){
                updateField += ", address_2="+cntAddress2+" ";
                }
                else{
                 updateField += " address_2="+cntAddress2+" ";
                }

 if(log_Option==1){
                       log_table_name.add("contacts");
                       log_field_name.add("address_2");
                       log_linked_field_name.add("contact_id");
                       log_linked_field_value.add(cntid);
                       log_old_value.add(old_disp_cntAddress2);
                       log_new_value.add(cntAddress2);
                       log_changed_by.add(sesEmpId);
                       log_reference_table.add("");
                       log_reference_field.add("");
                       log_reference_value.add("");
                    }
                }
       }


if((cntCity.equals("")||cntCity.equals("All"))){
     cntCity=null;
}
else{
             cntCity=" '"+cntCity+"' ";
}
   if(addOption.equals("modify")){
               if(old_disp_cntCity.trim().equals("''")&&cntCity==null){
                    compareValues=true;
                }
                else{
                    compareValues=old_disp_cntCity.equals(cntCity);
                }

                if(!compareValues){
                    if(updateField.length()>0){
                updateField += ", city="+cntCity+" ";
                }
                else{
                 updateField += " city="+cntCity+" ";
                }

 if(log_Option==1){
                       log_table_name.add("contacts");
                       log_field_name.add("city");
                       log_linked_field_name.add("contact_id");
                       log_linked_field_value.add(cntid);
                       log_old_value.add(old_disp_cntCity);
                       log_new_value.add(cntCity);
                       log_changed_by.add(sesEmpId);
                       log_reference_table.add("");
                       log_reference_field.add("");
                       log_reference_value.add("");
                    }
                }
       }


if(( cntState.equals("")||cntState.equals("All"))){
      cntState=null;
}
else{
              cntState=" '"+cntState+"' ";

}
 //System.out.println("cntState in SaveContact:"+cntState);

   if(addOption.equals("modify")){


               if(old_disp_cntState.trim().equals("''")&&cntState==null){
                    compareValues=true;
                }
                else{
                    compareValues=old_disp_cntState.equals(cntState);
                }

                if(!compareValues){if(updateField.length()>0){
                updateField += ", state="+cntState+" ";
                }
                else{
                 updateField += " state="+cntState+" ";
                }

 if(log_Option==1){
                       log_table_name.add("contacts");
                       log_field_name.add("state");
                       log_linked_field_name.add("contact_id");
                       log_linked_field_value.add(cntid);
                       log_old_value.add(old_disp_cntState);
                       log_new_value.add(cntState);
                       log_changed_by.add(sesEmpId);
                       log_reference_table.add("");
                       log_reference_field.add("");
                       log_reference_value.add("");
                    }
                }
       }


if((cntCountry.equals("")||cntCountry.equals("All"))){
     cntCountry=null;
}
else{
             cntCountry=" '"+cntCountry+"' ";
}
       /*System.out.println("cntCountry:"+cntCountry);
        System.out.println("old_disp_cntCountry:"+old_disp_cntCountry);*/

   if(addOption.equals("modify")){
               if(old_disp_cntCountry.trim().equals("''")&&cntCountry==null){
                    compareValues=true;
                }
                else{
                    compareValues=old_disp_cntCountry.equals(cntCountry);
                }

                if(!compareValues){if(updateField.length()>0){
                updateField += ", country="+cntCountry+" ";
                }
                else{
                 updateField += " country="+cntCountry+" ";
                }

 if(log_Option==1){
                       log_table_name.add("contacts");
                       log_field_name.add("country");
                       log_linked_field_name.add("contact_id");
                       log_linked_field_value.add(cntid);
                       log_old_value.add(old_disp_cntCountry);
                       log_new_value.add(cntCountry);
                       log_changed_by.add(sesEmpId);
                       log_reference_table.add("");
                       log_reference_field.add("");
                       log_reference_value.add("");
                    }
                }
       }



if((cntZipCode.equals("")||cntZipCode.equals("All"))){
     cntZipCode=null;
}
else{
             cntZipCode=" '"+cntZipCode+"' ";
}
   if(addOption.equals("modify")){
               if(old_disp_cntZipCode.trim().equals("''")&&cntZipCode==null){
                    compareValues=true;
                }
                else{
                    compareValues=old_disp_cntZipCode.equals(cntZipCode);
                }

                if(!compareValues){if(updateField.length()>0){
                updateField += ", zipcode="+cntZipCode+" ";
                }
                else{
                 updateField += " zipcode="+cntZipCode+" ";
                }

 if(log_Option==1){
                       log_table_name.add("contacts");
                       log_field_name.add("zipcode");
                       log_linked_field_name.add("contact_id");
                       log_linked_field_value.add(cntid);
                       log_old_value.add(old_disp_cntZipCode);
                       log_new_value.add(cntZipCode);
                       log_changed_by.add(sesEmpId);
                       log_reference_table.add("");
                       log_reference_field.add("");
                       log_reference_value.add("");
                    }
                }
       }


        /**/

if((cntSkype.equals("")||cntSkype.equals("All"))){
     cntSkype=null;
}
else{
             cntSkype=" '"+cntSkype+"' ";
}
   if(addOption.equals("modify")){
               if(old_disp_cntSkype.trim().equals("''")&&cntSkype==null){
                    compareValues=true;
                }
                else{
                    compareValues=old_disp_cntSkype.equals(cntSkype);
                }

                if(!compareValues){if(updateField.length()>0){
                updateField += ", skype="+cntSkype+" ";
                }
                else{
                 updateField += " skype="+cntSkype+" ";
                }

 if(log_Option==1){
                       log_table_name.add("contacts");
                       log_field_name.add("skype");
                       log_linked_field_name.add("contact_id");
                       log_linked_field_value.add(cntid);
                       log_old_value.add(old_disp_cntSkype);
                       log_new_value.add(cntSkype);
                       log_changed_by.add(sesEmpId);
                       log_reference_table.add("");
                       log_reference_field.add("");
                       log_reference_value.add("");
                    }
                }
       }


if((cntMsn.equals("")||cntMsn.equals("All"))){
     cntMsn=null;
}
else{
             cntMsn=" '"+cntMsn+"' ";
}
   if(addOption.equals("modify")){
               if(old_disp_cntMsn.trim().equals("''")&&cntMsn==null){
                    compareValues=true;
                }
                else{
                    compareValues=old_disp_cntMsn.equals(cntMsn);
                }

                if(!compareValues){if(updateField.length()>0){
                updateField += ", msn="+cntMsn+" ";
                }
                else{
                 updateField += " msn="+cntMsn+" ";
                }

 if(log_Option==1){
                       log_table_name.add("contacts");
                       log_field_name.add("msn");
                       log_linked_field_name.add("contact_id");
                       log_linked_field_value.add(cntid);
                       log_old_value.add(old_disp_cntMsn);
                       log_new_value.add(cntMsn);
                       log_changed_by.add(sesEmpId);
                       log_reference_table.add("");
                       log_reference_field.add("");
                       log_reference_value.add("");
                    }
                }
       }

if((cntAol.equals("")||cntAol.equals("All"))){
     cntAol=null;
}
else{
             cntAol=" '"+cntAol+"' ";
}
   if(addOption.equals("modify")){
               if(old_disp_cntAol.trim().equals("''")&&cntAol==null){
                    compareValues=true;
                }
                else{
                    compareValues=old_disp_cntAol.equals(cntAol);
                }

                if(!compareValues){if(updateField.length()>0){
                updateField += ", aol="+cntAol+" ";
                }
                else{
                 updateField += " aol="+cntAol+" ";
                }

 if(log_Option==1){
                       log_table_name.add("contacts");
                       log_field_name.add("aol");
                       log_linked_field_name.add("contact_id");
                       log_linked_field_value.add(cntid);
                       log_old_value.add(old_disp_cntAol);
                       log_new_value.add(cntAol);
                       log_changed_by.add(sesEmpId);
                       log_reference_table.add("");
                       log_reference_field.add("");
                       log_reference_value.add("");
                    }
                }
       }


if((cntGtalk.equals("")||cntGtalk.equals("All"))){
     cntGtalk=null;
}
else{
             cntGtalk=" '"+cntGtalk+"' ";
}
   if(addOption.equals("modify")){
               if(old_disp_cntGtalk.trim().equals("''")&&cntGtalk==null){
                    compareValues=true;
                }
                else{
                    compareValues=old_disp_cntGtalk.equals(cntGtalk);
                }

                if(!compareValues){if(updateField.length()>0){
                updateField += ", gtalk="+cntGtalk+" ";
                }
                else{
                 updateField += " gtalk="+cntGtalk+" ";
                }

 if(log_Option==1){
                       log_table_name.add("contacts");
                       log_field_name.add("gtalk");
                       log_linked_field_name.add("contact_id");
                       log_linked_field_value.add(cntid);
                       log_old_value.add(old_disp_cntGtalk);
                       log_new_value.add(cntGtalk);
                       log_changed_by.add(sesEmpId);
                       log_reference_table.add("");
                       log_reference_field.add("");
                       log_reference_value.add("");
                    }
                }
       }

if((cntPhone1.equals("")||cntPhone1.equals("All"))){
     cntPhone1=null;
}
else{
             cntPhone1=" '"+cntPhone1+"' ";
}
   if(addOption.equals("modify")){
               if(old_disp_cntPhone1.trim().equals("''")&&cntPhone1==null){
                    compareValues=true;
                }
                else{
                    compareValues=old_disp_cntPhone1.equals(cntPhone1);
                }

                if(!compareValues){if(updateField.length()>0){
                updateField += ", phone_primary="+cntPhone1+" ";
                }
                else{
                 updateField += " phone_primary="+cntPhone1+" ";
                }

 if(log_Option==1){
                       log_table_name.add("contacts");
                       log_field_name.add("phone_primary");
                       log_linked_field_name.add("contact_id");
                       log_linked_field_value.add(cntid);
                       log_old_value.add(old_disp_cntPhone1);
                       log_new_value.add(cntPhone1);
                       log_changed_by.add(sesEmpId);
                       log_reference_table.add("");
                       log_reference_field.add("");
                       log_reference_value.add("");
                    }
                }
       }

if((cntPhone2.equals("")||cntPhone2.equals("All"))){
     cntPhone2=null;
}
else{
             cntPhone2=" '"+cntPhone2+"' ";
}
   if(addOption.equals("modify")){
               if(old_disp_cntPhone2.trim().equals("''")&&cntPhone2==null){
                    compareValues=true;
                }
                else{
                    compareValues=old_disp_cntPhone2.equals(cntPhone2);
                }

                if(!compareValues){if(updateField.length()>0){
                updateField += ", phone_secondary="+cntPhone2+" ";
                }
                else{
                 updateField += " phone_secondary="+cntPhone2+" ";
                }

 if(log_Option==1){
                       log_table_name.add("contacts");
                       log_field_name.add("phone_secondary");
                       log_linked_field_name.add("contact_id");
                       log_linked_field_value.add(cntid);
                       log_old_value.add(old_disp_cntPhone2);
                       log_new_value.add(cntPhone2);
                       log_changed_by.add(sesEmpId);
                       log_reference_table.add("");
                       log_reference_field.add("");
                       log_reference_value.add("");
                    }
                }
       }


        /**/


if((cntMobile.equals("")||cntMobile.equals("All"))){
     cntMobile=null;
}
else{
             cntMobile=" '"+cntMobile+"' ";
}
   if(addOption.equals("modify")){
               if(old_disp_cntMobile.trim().equals("''")&&cntMobile==null){
                    compareValues=true;
                }
                else{
                    compareValues=old_disp_cntMobile.equals(cntMobile);
                }

                if(!compareValues){if(updateField.length()>0){
                updateField += ", cell_phone="+cntMobile+" ";
                }
                else{
                 updateField += " cell_phone="+cntMobile+" ";
                }

 if(log_Option==1){
                       log_table_name.add("contacts");
                       log_field_name.add("cell_phone");
                       log_linked_field_name.add("contact_id");
                       log_linked_field_value.add(cntid);
                       log_old_value.add(old_disp_cntMobile);
                       log_new_value.add(cntMobile);
                       log_changed_by.add(sesEmpId);
                       log_reference_table.add("");
                       log_reference_field.add("");
                       log_reference_value.add("");
                    }
                }
       }




if((cntFax1.equals("")||cntFax1.equals("All"))){
     cntFax1=null;
}
else{
             cntFax1=" '"+cntFax1+"' ";
}
   if(addOption.equals("modify")){
               if(old_disp_cntFax1.trim().equals("''")&&cntFax1==null){
                    compareValues=true;
                }
                else{
                    compareValues=old_disp_cntFax1.equals(cntFax1);
                }

                if(!compareValues){if(updateField.length()>0){
                updateField += ", fax1="+cntFax1+" ";
                }
                else{
                 updateField += " fax1="+cntFax1+" ";
                }

 if(log_Option==1){
                       log_table_name.add("contacts");
                       log_field_name.add("fax1");
                       log_linked_field_name.add("contact_id");
                       log_linked_field_value.add(cntid);
                       log_old_value.add(old_disp_cntFax1);
                       log_new_value.add(cntFax1);
                       log_changed_by.add(sesEmpId);
                       log_reference_table.add("");
                       log_reference_field.add("");
                       log_reference_value.add("");
                    }
                }
       }


      if((cntFax2.equals("")||cntFax2.equals("All"))){
     cntFax2=null;
}
else{
             cntFax2=" '"+cntFax2+"' ";
}
   if(addOption.equals("modify")){
               if(old_disp_cntFax2.trim().equals("''")&&cntFax2==null){
                    compareValues=true;
                }
                else{
                    compareValues=old_disp_cntFax2.equals(cntFax2);
                }

                if(!compareValues){if(updateField.length()>0){
                updateField += ", fax2="+cntFax2+" ";
                }
                else{
                 updateField += " fax2="+cntFax2+" ";
                }

 if(log_Option==1){
                       log_table_name.add("contacts");
                       log_field_name.add("fax2");
                       log_linked_field_name.add("contact_id");
                       log_linked_field_value.add(cntid);
                       log_old_value.add(old_disp_cntFax2);
                       log_new_value.add(cntFax2);
                       log_changed_by.add(sesEmpId);
                       log_reference_table.add("");
                       log_reference_field.add("");
                       log_reference_value.add("");
                    }
                }
       }

        if((cntTwitter.equals("")||cntTwitter.equals("All"))){
     cntTwitter=null;
}
else{
             cntTwitter=" '"+cntTwitter+"' ";
}
   if(addOption.equals("modify")){
               if(old_disp_cntTwitter.trim().equals("''")&&cntTwitter==null){
                    compareValues=true;
                }
                else{
                    compareValues=old_disp_cntTwitter.equals(cntTwitter);
                }

                if(!compareValues){if(updateField.length()>0){
                updateField += ", twitter="+cntTwitter+" ";
                }
                else{
                 updateField += " twitter="+cntTwitter+" ";
                }

 if(log_Option==1){
                       log_table_name.add("contacts");
                       log_field_name.add("twitter");
                       log_linked_field_name.add("contact_id");
                       log_linked_field_value.add(cntid);
                       log_old_value.add(old_disp_cntTwitter);
                       log_new_value.add(cntTwitter);
                       log_changed_by.add(sesEmpId);
                       log_reference_table.add("");
                       log_reference_field.add("");
                       log_reference_value.add("");
                    }
                }
       }
//

if((cntYahoo.equals("")||cntYahoo.equals("All"))){
     cntYahoo=null;
}
else{
             cntYahoo=" '"+cntYahoo+"' ";
}
   if(addOption.equals("modify")){
               if(old_disp_cntYahoo.trim().equals("''")&&cntYahoo==null){
                    compareValues=true;
                }
                else{
                    compareValues=old_disp_cntYahoo.equals(cntYahoo);
                }

                if(!compareValues){if(updateField.length()>0){
                updateField += ", yahoo="+cntYahoo+" ";
                }
                else{
                 updateField += " yahoo="+cntYahoo+" ";
                }

 if(log_Option==1){
                       log_table_name.add("contacts");
                       log_field_name.add("yahoo");
                       log_linked_field_name.add("contact_id");
                       log_linked_field_value.add(cntid);
                       log_old_value.add(old_disp_cntYahoo);
                       log_new_value.add(cntYahoo);
                       log_changed_by.add(sesEmpId);
                       log_reference_table.add("");
                       log_reference_field.add("");
                       log_reference_value.add("");
                    }
                }
       }

if((cntLinkedin.equals("")||cntLinkedin.equals("All"))){
     cntLinkedin=null;
}
else{
             cntLinkedin=" '"+cntLinkedin+"' ";
}
   if(addOption.equals("modify")){
               if(old_disp_cntLinkedin.trim().equals("''")&&cntLinkedin==null){
                    compareValues=true;
                }
                else{
                    compareValues=old_disp_cntLinkedin.equals(cntLinkedin);
                }

                if(!compareValues){if(updateField.length()>0){
                updateField += ", linkedin="+cntLinkedin+" ";
                }
                else{
                 updateField += " linkedin="+cntLinkedin+" ";
                }

 if(log_Option==1){
                       log_table_name.add("contacts");
                       log_field_name.add("linkedin");
                       log_linked_field_name.add("contact_id");
                       log_linked_field_value.add(cntid);
                       log_old_value.add(old_disp_cntLinkedin);
                       log_new_value.add(cntLinkedin);
                       log_changed_by.add(sesEmpId);
                       log_reference_table.add("");
                       log_reference_field.add("");
                       log_reference_value.add("");
                    }
                }
       }

if((cntFacebook.equals("")||cntFacebook.equals("All"))){
     cntFacebook=null;
}
else{
             cntFacebook=" '"+cntFacebook+"' ";
}
   if(addOption.equals("modify")){
               if(old_disp_cntFacebook.trim().equals("''")&&cntFacebook==null){
                    compareValues=true;
                }
                else{
                    compareValues=old_disp_cntFacebook.equals(cntFacebook);
                }

                if(!compareValues){if(updateField.length()>0){
                updateField += ", facebook="+cntFacebook+" ";
                }
                else{
                 updateField += " facebook="+cntFacebook+" ";
                }
 if(log_Option==1){
                       log_table_name.add("contacts");
                       log_field_name.add("facebook");
                       log_linked_field_name.add("contact_id");
                       log_linked_field_value.add(cntid);
                       log_old_value.add(old_disp_cntFacebook);
                       log_new_value.add(cntFacebook);
                       log_changed_by.add(sesEmpId);
                       log_reference_table.add("");
                       log_reference_field.add("");
                       log_reference_value.add("");
                    }
                }
       }
if((cntDesc.equals("")||cntDesc.equals("All"))){
     cntDesc=null;
}
else{
            cntDesc = new String((cntDesc.getBytes("8859_1")), "UTF-8");
              cntDesc=pfu.encodeSpecialCharacter(cntDesc);
             cntDesc=" '"+cntDesc+"' ";
}
   if(addOption.equals("modify")){
               if(old_disp_cntDesc.trim().equals("''")&&cntDesc==null){
                    compareValues=true;
                }
                else{

                    compareValues=old_disp_cntDesc.equals(cntDesc);
                }

                if(!compareValues){if(updateField.length()>0){
                updateField += ", description="+cntDesc+" ";
                }
                else{
                 updateField += " description="+cntDesc+" ";
                }

 if(log_Option==1){
                       log_table_name.add("contacts");
                       log_field_name.add("description");
                       log_linked_field_name.add("contact_id");
                       log_linked_field_value.add(cntid);
                       log_old_value.add(old_disp_cntDesc);
                       log_new_value.add(cntDesc);
                       log_changed_by.add(sesEmpId);
                       log_reference_table.add("");
                       log_reference_field.add("");
                        log_reference_value.add("");
                        }
                }
       }

        //if(!contactTypeID.equals("1")) {
            if ((isAlsoCustomer.equals("") || isAlsoCustomer.equals("All"))) {
                isAlsoCustomer = null;
            } else {
                    isAlsoCustomer = " '" + isAlsoCustomer + "' ";
                    compareValues = false;//coz ehile modifying the records the logic should work correctly
            }

            if (addOption.equals("modify")) {
                if (old_disp_isAlsoCustomer.trim().equals("''") && isAlsoCustomer == null) {
                    compareValues = true;
                } else {
                    if (cntIsPerson.equals("1")) {//this IF condition is used to avoid NullPointerException
                        compareValues = old_disp_isAlsoCustomer.equals(isAlsoCustomer);
                    }
                }

                if (!compareValues) {
                    updateProjNames = "1";
                    if (updateField.length() > 0) {
                        updateField += ", is_also_customer=" + isAlsoCustomer + " ";
                    } else {
                        updateField += " is_also_customer=" + isAlsoCustomer + " ";
                    }

                    if (log_Option == 1) {
                        log_table_name.add("contacts");
                        log_field_name.add("surName");
                        log_linked_field_name.add("contact_id");
                        log_linked_field_value.add(cntid);
                        log_old_value.add(old_disp_surName);
                        log_new_value.add(surName);
                        log_changed_by.add(sesEmpId);
                        log_reference_table.add("");
                        log_reference_field.add("");
                        log_reference_value.add("");
                    }
                }
            }
       //}

 String contactType = "";

 /*System.out.println("select type_name from "
         + "contacts_type_master where type_id='"+contactTypeID+"' ");*/


 ResultSet rsGetContactTypeValue = statement.executeQuery("select type_name from "
         + "contacts_type_master where type_id='"+contactTypeID+"' ");
 while(rsGetContactTypeValue.next()){
     contactType = rsGetContactTypeValue.getString(1);
 }
rsGetContactTypeValue.close();
 //System.out.println("contactType:"+contactType);

     //   System.out.println("cntid:"+cntid);
        String addContactSql = "";
String addDivisionSql = "";//while adding a new custiomer if the division is entered
        //then the Division should also be added as a new contact. To do that this sqlvariable has been included and ths logic shud work if the new contact is Customer only.
        String divisionTypeId= "";
        String divContactId="";

        if(contactCategoryCheck.equals("1")){
            
            addContactSql="insert into contacts(contact_id,firstname,surname, "+
                    " address_1,address_2,city,state,country, "+
                    " zipcode,website,email,designation,company,division,phone_primary," +
                    " phone_secondary,cell_phone,fax1,fax2,skype,msn, " +
                    " aol,gtalk,is_person,yahoo,linkedin,facebook,twitter,description,department,"
                    + "vendor_number,sub_spcn,working_hrs,contact_hrs,created_by,created_date,is_also_customer) "+
                    " values ('"+newContactId+"',"+firstName+","+surName+","+cntAddress1+", " +
                    " "+cntAddress2+","+cntCity+","+cntState+","+cntCountry+","+cntZipCode+","+cntWebsite+", " +
                    " "+cntEmail+","+cntDesignation+","+cntCompany+","+cntDivision+", " +
                    " "+cntPhone1+","+cntPhone2+","+cntMobile+","+cntFax1+","+cntFax2+","+cntSkype+", " +
                    " "+cntMsn+","+cntAol+","+cntGtalk+","+cntIsPerson+","+cntYahoo+","+cntLinkedin+","
                    + " "+cntFacebook+","+cntTwitter+","+cntDesc+","+cntDept+","
                    + " "+cntVendorNum+","+contactSubSpcn+","+contactWorkingHrs+","+contactContactHrs+", "
                    + "'"+sesEmpId+"',CURRENT_TIMESTAMP(),"+isAlsoCustomer+" )  ";

        }
        else if(contactCategoryCheck.equals("2")){

            if(contactType.equals("Customer")||contactType.equals("Company")||contactType.equals("Division")){
                   addContactSql="insert into contacts(contact_id, "+
                    " address_1,address_2,city,state,country, "+
                    " zipcode,website,email,company,phone_primary," +
                    " phone_secondary,cell_phone,fax1,fax2,skype,msn, " +
                    " aol,gtalk,is_person,yahoo,linkedin,facebook,twitter,description,department"
                           + ",vendor_number,sub_spcn,working_hrs,contact_hrs,created_by,created_date,is_also_customer) "+
                    " values ('"+newContactId+"',"+cntAddress1+", " +
                    " "+cntAddress2+","+cntCity+","+cntState+","+cntCountry+","
                    + " "+cntZipCode+","+cntWebsite+", " +
                    " "+cntEmail+","+cntCompany+", " +
                    " "+cntPhone1+","+cntPhone2+","+cntMobile+","+cntFax1+","+cntFax2+","+cntSkype+", " +
                    " "+cntMsn+","+cntAol+","+cntGtalk+","+cntIsPerson+","+cntYahoo+","+cntLinkedin+","
                           + " "+cntFacebook+","+cntTwitter+","+cntDesc+","+cntDept+","
                           + " "+cntVendorNum+","+contactSubSpcn+","+contactWorkingHrs+","+contactContactHrs+","
                           + "'"+sesEmpId+"',CURRENT_TIMESTAMP(),"+isAlsoCustomer+" )  ";

            }
            else{
                    addContactSql="insert into contacts(contact_id, "+
                    " address_1,address_2,city,state,country, "+
                    " zipcode,website,email,company,division,phone_primary," +
                    " phone_secondary,cell_phone,fax1,fax2,skype,msn, " +
                    " aol,gtalk,is_person,yahoo,linkedin,facebook,twitter,description,department,"
                            + "vendor_number,sub_spcn,working_hrs,contact_hrs,created_by,created_date,is_also_customer) "+
                    " values ('"+newContactId+"',"+cntAddress1+", " +
                    " "+cntAddress2+","+cntCity+","+cntState+","+cntCountry+","
                    + " "+cntZipCode+","+cntWebsite+", " +
                    " "+cntEmail+","+cntCompany+","+cntDivision+", " +
                    " "+cntPhone1+","+cntPhone2+","+cntMobile+","+cntFax1+","+cntFax2+","+cntSkype+", " +
                    " "+cntMsn+","+cntAol+","+cntGtalk+","+cntIsPerson+","+cntYahoo+","+cntLinkedin+","
                            + " "+cntFacebook+","+cntTwitter+","+cntDesc+","+cntDept+","
                            + " "+cntVendorNum+","+contactSubSpcn+","+contactWorkingHrs+","+contactContactHrs+","
                            + "'"+sesEmpId+"',CURRENT_TIMESTAMP(),"+isAlsoCustomer+" )  ";
            }

        }

 updateContact_Sql=updateContact_Sql+updateField+where;

/*System.out.println("cntIsPerson-query check:"+contactCategoryCheck);*/
 //System.out.println("addContactSql:"+addContactSql);

 /*System.out.println("updateContact_Sql-Modified:"+updateContact_Sql);
 System.out.println("updateField-Modified:"+updateField);*/

 //the below IF block is to add the contact details by executing the query to add a contact
 /*System.out.println("Special:"+cntNameDuplicated);
 System.out.println("addContactSql:"+addContactSql);*/
 
 //System.out.println("updateContact_Sql:"+updateContact_Sql);


if(cntid.equals("")&&addOption.equals("Add")){
    //System.out.println("addContactSql:"+addContactSql);
    
  if(cntNameDuplicated==0){
      addContact = statement.executeUpdate(addContactSql);
      System.out.println("addContactSql:"+addContactSql);

  }
 // System.out.println("addContact:"+addContact);


  //System.out.println("addContactSql:"+addContactSql);



if(addContact>0){
    //block to set the selected author ID as the Primary Author ID for the project
   if(!contactTypeID.equals("")) {

         statement.executeUpdate("insert into contacttype_map(contact_id, type_id)" +
            "  values ("+newContactId+","+contactTypeID+")");
   }

   /* the below block is to add divison if the selected contact is customer*/
            if((contactType.equals("Customer")||contactType.equals("Company"))&&cntDivision!=null){

                ResultSet rsGetDivisionType = statement.executeQuery("select type_id from contacts_type_master where type_name='Division' ");
                while(rsGetDivisionType.next()){
                    divisionTypeId=rsGetDivisionType.getString(1);
                }
                rsGetDivisionType.close();
              ResultSet rsDivMaxContactCount = statement.executeQuery("select max(contact_id) from contacts");
                   while(rsDivMaxContactCount.next()){
                        maxCount=rsDivMaxContactCount.getString(1);
                      // System.out.println("maxCount-after Query:"+maxCount);
                    }
                    rsDivMaxContactCount.close();
                 if(maxCount==null||maxCount.equals("")){
                     maxCount="0";
                 }
                    incrementor=Integer.parseInt(maxCount);
                    incrementor=incrementor+1;
                    divContactId=String.valueOf(incrementor);

                addDivisionSql="insert into contacts(contact_id, "+
                    " address_1,address_2,city,state,country, "+
                    " zipcode,website,email,company,phone_primary," +
                    " phone_secondary,cell_phone,fax1,fax2,skype,msn, " +
                    " aol,gtalk,is_person,yahoo,linkedin,facebook,twitter,description,department) "+
                    " values ('"+divContactId+"',"+cntAddress1+", " +
                    " "+cntAddress2+","+cntCity+","+cntState+","+cntCountry+","+cntZipCode+","+cntWebsite+", " +
                    " "+cntEmail+","+cntDivision+"," +
                    " "+cntPhone1+","+cntPhone2+","+cntMobile+","+cntFax1+","+cntFax2+","+cntSkype+", " +
                    " "+cntMsn+","+cntAol+","+cntGtalk+","+cntIsPerson+","+cntYahoo+","+cntLinkedin+","+cntFacebook+","+cntTwitter+","+cntDesc+","+cntDept+" )  ";

        addContact = statement.executeUpdate(addDivisionSql);
        if(addContact>0){
            statement.executeUpdate("insert into contacttype_map(contact_id, type_id)" +
            "  values ('"+divContactId+"','"+divisionTypeId+"')");
        }


        /*System.out.println("insert into belongs_to(parent_contact,contact_id)" +
            "  values ('"+newContactId+"','"+divContactId+"')");*/

          statement.executeUpdate("insert into belongs_to(parent_contact,contact_id)" +
            "  values ('"+newContactId+"','"+divContactId+"')");

  }//if((contactType.equals("Customer")||contactType.equals("Company"))&&cntDivision!=null){

}
}
else if(addOption.equals("modify")){
     //block to update the contact details by executing the query to update a contac
//System.out.println("updateContact_Sql:"+updateContact_Sql);
 if(cntNameDuplicated==0){
     System.out.println("updateContact_Sql"+updateContact_Sql);
        if(!updateField.equals("")){
            
                    addContact = statement.executeUpdate(updateContact_Sql);

                    // If sur name modified then we have to modify the projects' name of the author
                    // i.e., according to the contact sur name the project name has to rename
                    if(updateProjNames.equals("1")) {
                        try {
                            String newProjName = "";
                            String tempProjId = "";
                            String oldProjName = "";
                            String Query = "SELECT CASE "
                                                + "WHEN p.ePace_id IS NULL THEN CONCAT(c.surname,'_',p.proj_id) "
                                                + "ELSE CONCAT(c.surname,'_',p.ePace_id,'_',p.proj_id) END as newProjName, p.proj_id, p.proj_name "
                                                + "FROM projects p, contacts c "
                                                + "WHERE p.author_id=c.contact_id "
                                                + "AND p.author_id='"+cntid+"'";
                            // Get the New project name according to the new sur name
                            ResultSet rsGetAuthorName = statement.executeQuery(Query);
                            while(rsGetAuthorName.next()){
                                if(!rsGetAuthorName.getString(1).equals(null)) {
                                    newProjName = splChar.decoding(rsGetAuthorName.getString(1));
                                }
                                if(!rsGetAuthorName.getString(2).equals(null)) {
                                    tempProjId = rsGetAuthorName.getString(2);
                                    if(!newProjName.equals("") && !newProjName.equals(null)) {
                                        // Update new Project name
                                        int updateStatus = statement1.executeUpdate("UPDATE projects p SET p.proj_name='"+newProjName+"' WHERE p.author_id='"+cntid+"' and p.proj_id='"+tempProjId+"'");
                                        if(updateStatus == 1) {
                                            if(!rsGetAuthorName.getString(3).equals(null)) {
                                                oldProjName = rsGetAuthorName.getString(3);
                                                renameProjectFolder(tempProjId, oldProjName, newProjName);
                                            }
                                        }
                                    }
                                }
                            }
                            rsGetAuthorName.close();
                        } catch (Exception e) {
                            System.out.println("Exception while update Project Name according to the Author Sur name : "+e);
                        }
                    }
            }
    }
/**the below block is to work only if the selected contact type is Division and if its Customer is to be modified*/
String oldParentName="";
String oldParentId="";

//if(contactTypeID.equals("2")){--for only Division the update was enabled earlier
if(addOption.equals("modify")){
ResultSet rsGetParentName = statement.executeQuery("select cnt.company,cnt.contact_id from contacts cnt, belongs_to blt where "
        + " cnt.contact_id=blt.parent_contact and blt.contact_id='"+cntid+"' ");
while(rsGetParentName.next()){
    oldParentName = splChar.decoding(rsGetParentName.getString(1)) ;
    if(rsGetParentName.wasNull()){
        oldParentName="";
    }
    oldParentId = rsGetParentName.getString(2) ;
    if(rsGetParentName.wasNull()){
        oldParentId="";
    }
}
rsGetParentName.close();
if(!oldParentName.equals("") && baseParellelCntId.equals("")){//&&!oldParentName.equals("divisionCompanyName")){ // If this is Parellel Update
    if(addContact>0){
        statement.executeUpdate("update belongs_to set parent_contact='"+companyid+"'"
                + " where contact_id='"+cntid+"' and parent_contact='"+oldParentId+"' ");
    }
}

}

      if(addOption.equals("modify")){

      //   System.out.println("log_table_name:"+log_table_name.size());

         if(log_table_name.size()>0&&addContact>0){
            /* System.out.println("log_field_name:"+log_field_name);
             System.out.println("log_old_value:"+log_old_value);
             System.out.println("log_new_value:"+log_new_value);*/

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
                dbLog.setRefFieldValueList(log_linked_field_value);
                dbLog.createLog();
         }

     }
 /**/

if(addContact>0){
    //block to set the selected author ID as the Primary Author ID for the project
     ResultSet rsChkCntType=statement.executeQuery("select type_id from contacttype_map where contact_id="+newContactId+" ");
     while(rsChkCntType.next()){
         chkContactType=rsChkCntType.getString("type_id");
     }
     rsChkCntType.close();
      if(!contactTypeID.equals("")) {
               if(!chkContactType.equals("")){
                    addContact=statement.executeUpdate("update contacttype_map set type_id="+contactTypeID+" where contact_id="+newContactId+" ");
                 }
                    else{
                          addContact=statement.executeUpdate("insert into contacttype_map(contact_id, type_id)" +
                            "  values ("+newContactId+","+contactTypeID+")");
                    }
      }

    }
 }
 else if(addOption.equals("delete")){
    /* System.out.println("cntid in saveContact:"+cntid);
     System.out.println("update contacts set status='0' where contact_id='"+newContactId+"' ");
*/
     try{

        addContact = statement.executeUpdate("update contacts set status='0' where contact_id="+newContactId+" ");
   //System.out.println("addContact in delete option:"+addContact);

     }catch(Exception e){
        e.printStackTrace();
    }

     }

 if(addContact>0&&addOption.equals("Add")){


/*if(contactTypeID.equals("2")){//If the selected contact type is divison then the Division should be mapped to company
    cntCompany=cntDivision;
             statement.executeUpdate("insert into belongs_to(parent_contact,contact_id)" +
            "  values ('"+companyid+"',"+newContactId+")");
}*/
//the below is the query where the aded contact will be mapped to a company in belongs to table
if(!companyid.equals("")){//If the selected contact type is divison then the Division should be mapped to company
    //cntCompany=cntDivision;
             statement.executeUpdate("insert into belongs_to(parent_contact,contact_id)" +
            "  values ('"+companyid+"',"+newContactId+")");
}

    //cntCity
    String cityId="";
if(cntCity!=null){
        ResultSet rsChkCity =  statement.executeQuery("select city_id from contacts_city where city_name="+cntCity+" ");
    while(rsChkCity.next()){
       cityId = rsChkCity.getString(1);
       if(rsChkCity.wasNull()){
           cityId = "";
       }
    }
        rsChkCity.close();

    if(cityId.equals("")){
      //System.out.println("insert into contacts_city(city_name) values ("+cntCity+")");
         statement.executeUpdate("insert into contacts_city(city_name) values ("+cntCity+")");
    }
}


String stateId="";
    if(cntState!=null){
            ResultSet rsChkState =  statement.executeQuery("select state_id from "
                + "contacts_state where state_name="+cntState+" ");
        while(rsChkState.next()){
           stateId = rsChkState.getString(1);
           if(rsChkState.wasNull()){
               stateId = "";
           }
        }
        rsChkState.close();
        if(stateId.equals("")){
          //System.out.println("insert into contacts_city(city_name) values ("+cntCity+")");
             statement.executeUpdate("insert into contacts_state(state_name) values ("+cntState+")");
        }
    }

 }







statement.close();
statement1.close();


       // System.out.println("insert into contacts(firstname,surname) values ('"+firstName+"','"+surName+"')  ");

       //addContact = statement.executeUpdate("insert into contacts(firstname,surname) values ('"+firstName+"','"+surName+"')  ");
//System.out.println("addContact:"+addContact);
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        //    System.out.println("Exception in Saving Contact:"+e.printStackTrace());
        }finally{
            try{
                con.close();
            }catch(NullPointerException npe){
                npe.printStackTrace();
            }catch(Exception e){
                e.printStackTrace();
            }
        }

}

   // To Rename Project Folder name in FM Driver into new one.
   public void renameProjectFolder(String projId, String oldProjectName, String newProjectName) {

        filters.generic.FolderDriveList fileDriver = new filters.generic.FolderDriveList();

        fileDriver.getDriveInfo();

        String driveValue = fileDriver.getDriveValue();
        String projOldPath = driveValue + "/" + oldProjectName;
        String projNewPath = driveValue + "/" + newProjectName;
        File oldProjFolderName = new File(projOldPath.trim());

        if (oldProjFolderName.exists()) {

            File newProjectFolderName = new File(projNewPath.trim());
            if (oldProjFolderName.isDirectory()) {
                boolean Rename = oldProjFolderName.renameTo(newProjectFolderName);
                if(Rename)
                {
                    //System.out.println("INFO : " + oldProjectName + " project folder is renamed into " + newProjectName + " Successfully");
                    renameProjectSubFolders(projId, newProjectName);
                } else {
                    //System.out.println("INFO : " + oldProjectName + " project folder is not renamed.");
                }
            }
        }
    }

    // To rename the Project's sub folder names like <newname>/<SubFolder>
    private void renameProjectSubFolders(String projId, String projectName) {
        filters.generic.FolderDriveList fileDriver = new filters.generic.FolderDriveList();
        filters.generic.FolderDriveInsertion fileDriverInsertion = new filters.generic.FolderDriveInsertion();

        String loopFileTypePath = "";
        String loopFileTypeId = "";

        // Get existing project folder path information
        fileDriver.setProjId(projId);
        fileDriver.getProjFolderPathInfo();
        List proj_filetype_id = fileDriver.getProjFileTypeId();
        List proj_folder_name = fileDriver.getProjFolderName();

        for (int i = 0; i < proj_filetype_id.size(); i++) {

            loopFileTypeId = proj_filetype_id.get(i).toString();
            // Arrange new Sub Folder name
            loopFileTypePath = "/" + proj_folder_name.get(i).toString() + "//";
            // Corodinate with the New Project Name
            loopFileTypePath = "/" + projectName + loopFileTypePath;
            fileDriverInsertion.setProjId(projId);
            fileDriverInsertion.setFileTypeId(loopFileTypeId);
            fileDriverInsertion.setFileTypePath(loopFileTypePath);
            // Update Folder Type Path
            fileDriverInsertion.ProjFolderTypePathUpdation();
        }
    }

    public void getParellelContact() {
        try {
            DBconnection dbconnection = new DBconnection();
            Connection con = (Connection) dbconnection.getSampleProperty();
            Statement st = (Statement) con.createStatement();
            String query = "";
            if(cntType.equals("1") || cntType.equals("7")) {
                if(cntType.equals("1")) {
                    query = "SELECT ab.contact_id "
                        + "FROM contacts c, contacts a, contacttype_map cm, belongs_to cb ,contacttype_map am, belongs_to ab "
                        + "WHERE c.contact_id=cb.parent_contact AND c.contact_id=cm.contact_id AND cm.type_id='1' AND "
                        + "a.contact_id=ab.contact_id AND a.contact_id=am.contact_id AND am.type_id='7' AND "
                        + "cb.parent_contact=ab.parent_contact AND cb.contact_id=ab.contact_id "
                        + "AND c.contact_id='"+baseParellelCntId+"'";
                } else if(cntType.equals("7")) {
                    query = "SELECT b.parent_contact FROM contacts c, contacttype_map m, belongs_to b "
                        + "WHERE c.contact_id=b.contact_id AND c.contact_id=m.contact_id AND m.type_id='7' AND c.is_also_customer='1' AND c.contact_id='"+baseParellelCntId+"'";
                } else {
                    
                }

                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    if (rs.getString(1) != null) {
                        parellelCntId = rs.getString(1).toString();
                    } else {
                        parellelCntId = "";
                    }
                }
                rs.close();
            }
            st.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Exception occured in SaveContact - getParellelContact() : "+e);
        }
    }
}
