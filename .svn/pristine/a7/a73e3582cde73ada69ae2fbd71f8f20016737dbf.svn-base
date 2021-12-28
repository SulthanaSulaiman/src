/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.contacts;

import java.io.Serializable;
import connection.DBconnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import pathfinder.functions.generic.*;
/**
 *
 * @author ramesh
 * class to collect the contact type and return them
 */

public class ContactInfo implements Serializable {

    private String contactId="";
    private String contactFirstName="";
    private String contactSecondName="";
    private String contactDesig="";
    private String contactCompany="";
    private String parentContactId="";
    private String contactPhone="";
    private String contactEmail="";
    private String contactCity="";
    private String contactCountry="";
    private String contactDivision="";
    private String contactDivisionId="";
    private String contactCompanyId="";
    private String contactType="";
    private String contactTypeId="";
    private String contactCategory="";
    private String searchWord = "";
    private String cntVendorNumber="";
    private String cntPriAddress="";
    private String cntSecAddress="";
    private String cntState="";
    private String cntZipCode="";
    private String cntWebsite="";
    private String cntSecPhone="";
    private String cntMobPhone="";
    private String cntFax1="";
    private String cntFax2="";
    private String cntFax3="";
    private String contactCategoryName="";
    private String cntSkype="";
    private String cntMSN="";
    private String cntAOL="";
    private String cntGtalk="";
    private String cntYahoo="";
    private String cntLinkedin="";
    private String cntFacebook="";
    private String cntTwitter="";
    private String contactCountryName="";
    private String cntStateName="";
    private String cntDesc="";
    private String cntDept="";
    private String contactSubSpcn = "";
    private List patentCntDetail = new ArrayList();
    private List patentCntId = new ArrayList();
    
    public String getSearchWord() {
        return searchWord;
    }

    public List getPatentCntDetail() {
        return patentCntDetail;
    }

    public void setSearchWord(String searchWord) {
        this.searchWord = searchWord;
    }

    public List getPatentCntId() {
        return patentCntId;
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
    private String contactWorkingHrs = "";
    private String contactContactHrs = "";
    private String contactIsAlsoCustomer = "";
    private String cntCreatedBy="";
    private Connection con = null;

    public ContactInfo(){

    }

    public void setContactId(String contactId){
        this.contactId=contactId;
    }

    public String getContactId() {
        return contactId;
    }
    
    public String getContactFirstName(){
        return contactFirstName;
    }

    public String getContactSecondName(){
        return contactSecondName;
    }

    public String getContactDesig(){
        return contactDesig;
    }

    public String getContactCompany(){
        return contactCompany;
    }

    public String getParentContactId() {
        return parentContactId;
    }

    public String getContactCompanyId(){
        return  contactCompanyId;
    }


    public String getContactDivision(){
        return contactDivision;
    }

    public String getContactDivisionId(){
        return contactDivisionId;
    }

    public String getcontactVendorNum(){
       return cntVendorNumber;
    }

    public String getContactPhone(){
        return contactPhone;
    }

    public String getContactEmail(){
        return contactEmail;
    }

     public String getContactCity(){
        return contactCity;
    }

     public String getContactCountry(){
        return contactCountry;
    }

    public String getContactCountryName(){
        return contactCountryName;
    }

    public String getCntStateName(){
        return cntStateName;
    }

    public String getContactType(){
        return contactType;
    }

     public String getContactTypeId(){
        return contactTypeId;
    }

    public String getContactCategoryName(){
        return contactCategoryName;
    }

    public String getContactCategory(){
        return contactCategory;
    }

        public String getCntPriAdd(){
        return cntPriAddress;
    }

    public String getCntSecAdd(){
        return cntSecAddress;
    }

    public String getCntState(){
        return cntState;
    }


    public String getCntZipCode(){
        return cntZipCode;
    }

    public String getCntWebSite(){
        return cntWebsite;
    }

     public String getCntSecPhone(){
        return cntSecPhone;
    }

     public String getCntMobPhone(){
        return cntMobPhone;
    }

    public String getCntFax1(){
        return cntFax1;
    }

   public String getCntFax2(){
        return cntFax2;
    }

    public String getCntSkype(){
        return cntSkype;
    }

    public String getCntMSN(){
        return cntMSN;
    }

     public String getCntAOL(){
        return cntAOL;
    }

     public String getCntGtalk(){
        return cntGtalk;
    }

    public String getCntYahoo(){
        return cntYahoo;
    }

    public String getCntLinkedIn(){
        return cntLinkedin;
    }

    public String getCntFacebook(){
        return cntFacebook;
    }

    public String getCntTwitter(){
        return cntTwitter;
    }

    public String getDescription(){
        return cntDesc;
    }

    public String getcontactDepartment(){
        return cntDept;
    }

    public String getCntCreatedby(){
        return cntCreatedBy;
    }

    public String getContactIsAlsoCustomer() {
        return contactIsAlsoCustomer;
    }

    public void collectContactInfo(){
        pathfinder.util.SpecialCharHandler splChar = new pathfinder.util.SpecialCharHandler();
        try{
        DBconnection dbcon = new DBconnection();
        con = dbcon.getSampleProperty();
        String resultTemp = "";

        Statement statement = con.createStatement(); 
        String contactsInfo_Sql = " select cnt.contact_id,cnt.firstname,cnt.surname," +
                                  " cnt.designation,cnt.company,cnt.phone_primary,"+
                                    " cnt.email,cnt.city,cnt.country,cnt.is_person,cnt.vendor_number, " +
                                    " cnt.address_1,cnt.address_2,cnt.state,cnt.zipcode,cnt.website,cnt.phone_secondary, " +
                                    " cnt.cell_phone,cnt.fax1,cnt.fax2,cnt.fax3, " +
                                    " cnt.skype,cnt.msn,cnt.aol,cnt.gtalk,cnt.yahoo,cnt.linkedin,cnt.facebook,cnt.twitter," +
                                    " cnt.description,cnt.department,cnt.division,cnt.created_by,cnt.sub_spcn,cnt.working_hrs," +
                                    " cnt.contact_hrs, cnt.is_also_customer "+
                                    " from contacts cnt" +
                                    " where  cnt.contact_id='"+contactId+"' ";

      //System.out.println("contactsInfo_Sql:"+contactsInfo_Sql);

        ResultSet rsGetContactInfo = statement.executeQuery(contactsInfo_Sql);
        while(rsGetContactInfo.next()){
        //System.out.println("olddat val");
          resultTemp =  rsGetContactInfo.getString("cnt.contact_id");
           if(rsGetContactInfo.wasNull()) {
               resultTemp="";
           }
           contactId=resultTemp;

           resultTemp =  splChar.decoding(rsGetContactInfo.getString("cnt.firstname"));
           if(rsGetContactInfo.wasNull()) {
               resultTemp="";
           }
           contactFirstName=resultTemp;

           resultTemp =  splChar.decoding(rsGetContactInfo.getString("cnt.surname"));
           if(rsGetContactInfo.wasNull()) {
               resultTemp="";
           }
           contactSecondName=resultTemp;         

       

           resultTemp =  rsGetContactInfo.getString("cnt.designation");
           if(rsGetContactInfo.wasNull()) {
               resultTemp="";
           }
           contactDesig=resultTemp;

           resultTemp =  rsGetContactInfo.getString("cnt.department");
           if(rsGetContactInfo.wasNull()) {
               resultTemp="";
           }
           cntDept=resultTemp;

           resultTemp =  splChar.decoding(rsGetContactInfo.getString("cnt.company"));
           if(rsGetContactInfo.wasNull()) {
               resultTemp="";
           }
           contactCompany=resultTemp;

           resultTemp =  splChar.decoding(rsGetContactInfo.getString("cnt.division"));
           if(rsGetContactInfo.wasNull()) {
               resultTemp="";
           }
           contactDivision=resultTemp;
//System.out.println(contactDivision);
           resultTemp =  rsGetContactInfo.getString("cnt.vendor_number");
           if(rsGetContactInfo.wasNull()) {
               resultTemp="";
           }
           cntVendorNumber=resultTemp;


           //System.out.println("contactDivision:"+contactDivision);

           resultTemp =  rsGetContactInfo.getString("cnt.phone_primary");
           if(rsGetContactInfo.wasNull()) {
               resultTemp="";
           }
           contactPhone=resultTemp;

           resultTemp =  rsGetContactInfo.getString("cnt.email");
           if(rsGetContactInfo.wasNull()) {
               resultTemp="";
           }
           contactEmail=resultTemp;

           resultTemp =  rsGetContactInfo.getString("cnt.city");
           if(rsGetContactInfo.wasNull()) {
               resultTemp="";
           }
           contactCity=resultTemp;

           resultTemp =  rsGetContactInfo.getString("cnt.country");
           if(rsGetContactInfo.wasNull()) {
               resultTemp="";
           }
           if(resultTemp.equals("All")){
               resultTemp="";
           }
           contactCountry=resultTemp;

           resultTemp =  rsGetContactInfo.getString("cnt.is_person");
           if(rsGetContactInfo.wasNull()) {
               resultTemp="";
           }
           contactCategory=resultTemp;

                resultTemp   =  rsGetContactInfo.getString("cnt.address_1");
           if(rsGetContactInfo.wasNull()) {
               resultTemp  ="";
           }
           cntPriAddress  =resultTemp;

           resultTemp   =  rsGetContactInfo.getString("cnt.address_2");
           if(rsGetContactInfo.wasNull()) {
               resultTemp  ="";
           }
           cntSecAddress    =resultTemp;

           resultTemp   =  rsGetContactInfo.getString("cnt.state");
           if(rsGetContactInfo.wasNull()) {
               resultTemp  ="";
           }
//System.out.println("resultTemp:"+resultTemp);

           cntStateName=resultTemp;

           resultTemp   =  rsGetContactInfo.getString("cnt.zipcode");
           if(rsGetContactInfo.wasNull()) {
               resultTemp  ="";
           }
           cntZipCode=resultTemp;


           resultTemp   =  rsGetContactInfo.getString("cnt.website");
           if(rsGetContactInfo.wasNull()) {
               resultTemp  ="";
           }
           cntWebsite=resultTemp;

           resultTemp   =  rsGetContactInfo.getString("cnt.phone_secondary");
           if(rsGetContactInfo.wasNull()) {
               resultTemp  ="";
           }
           cntSecPhone=resultTemp;

           resultTemp   =  rsGetContactInfo.getString("cnt.cell_phone");
           if(rsGetContactInfo.wasNull()) {
               resultTemp  ="";
           }
           cntMobPhone=resultTemp;

           resultTemp   =  rsGetContactInfo.getString("cnt.fax1");
           if(rsGetContactInfo.wasNull()) {
               resultTemp  ="";
           }
           cntFax1=resultTemp;

           resultTemp   =  rsGetContactInfo.getString("cnt.fax2");
           if(rsGetContactInfo.wasNull()) {
               resultTemp  ="";
           }
           cntFax2=resultTemp;

           resultTemp   =  rsGetContactInfo.getString("cnt.skype");
           if(rsGetContactInfo.wasNull()) {
               resultTemp  ="";
           }
           cntSkype=resultTemp;

           resultTemp   =  rsGetContactInfo.getString("cnt.msn");
           if(rsGetContactInfo.wasNull()) {
               resultTemp  ="";
           }
           cntMSN=resultTemp;

           resultTemp   =  rsGetContactInfo.getString("cnt.aol");
           if(rsGetContactInfo.wasNull()) {
               resultTemp  ="";
           }
           cntAOL=resultTemp;

           resultTemp   =  rsGetContactInfo.getString("cnt.gtalk");
           if(rsGetContactInfo.wasNull()) {
               resultTemp  ="";
           }
           cntGtalk=resultTemp;

           resultTemp   =  rsGetContactInfo.getString("cnt.yahoo");
           if(rsGetContactInfo.wasNull()) {
               resultTemp  ="";
           }
           cntYahoo=resultTemp;

           resultTemp   =  rsGetContactInfo.getString("cnt.linkedin");
           if(rsGetContactInfo.wasNull()) {
               resultTemp  ="";
           }
           cntLinkedin=resultTemp;

           resultTemp   =  rsGetContactInfo.getString("cnt.facebook");
           if(rsGetContactInfo.wasNull()) {
               resultTemp  ="";
           }
           cntFacebook=resultTemp;

           resultTemp   =  rsGetContactInfo.getString("cnt.twitter");
           if(rsGetContactInfo.wasNull()) {
               resultTemp  ="";
           }
           cntTwitter=resultTemp;

           resultTemp   =  rsGetContactInfo.getString("cnt.description");
           if(rsGetContactInfo.wasNull()) {
               resultTemp  ="";
           }
           cntDesc=resultTemp;
          // cntDesc = decodeToHtmlTag(cntDesc);
           //cntDesc = decodeToHtmlTag(cntDesc);
           cntDesc = new PFUtil().decodeToHtmlTag(cntDesc);


           resultTemp = rsGetContactInfo.getString("cnt.created_by");
           if(rsGetContactInfo.wasNull()) {
               resultTemp  ="";
           }
           cntCreatedBy=resultTemp;

           resultTemp = rsGetContactInfo.getString("cnt.sub_spcn");
           if(rsGetContactInfo.wasNull()) {
               resultTemp  ="";
           }
           contactSubSpcn=resultTemp;
          // System.out.println("cntCreatedBy:"+cntCreatedBy);
           resultTemp = rsGetContactInfo.getString("cnt.working_hrs");
           if(rsGetContactInfo.wasNull()) {
               resultTemp  ="";
           }
           contactWorkingHrs=resultTemp;

           resultTemp = rsGetContactInfo.getString("cnt.contact_hrs");
           if(rsGetContactInfo.wasNull()) {
               resultTemp  ="";
           }
           contactContactHrs=resultTemp;

           resultTemp = rsGetContactInfo.getString("cnt.is_also_customer");
           if(rsGetContactInfo.wasNull()) {
               resultTemp  ="";
           }
           contactIsAlsoCustomer=resultTemp;

        }

        if(!contactDivision.equals("")){
            ResultSet rsDivisionId = statement.executeQuery("select cnt.contact_id from contacts cnt,contacttype_map cntm  where"
                    + " cnt.company='"+splChar.encoding(contactDivision)+"' and cnt.contact_id=cntm.contact_id and cntm.type_id='2' ");
            while(rsDivisionId.next()){
                contactDivisionId = rsDivisionId.getString(1);
            }

        }

       if(!contactCompany.equals("")){
            ResultSet rsCompanyId = statement.executeQuery("select cnt.contact_id from contacts cnt,contacttype_map cntm  where"
                    + " cnt.company='"+splChar.encoding(contactCompany)+"' and cnt.contact_id=cntm.contact_id and cntm.type_id='1' ");
            while(rsCompanyId.next()){
                contactCompanyId = rsCompanyId.getString(1);
            }

        }

            //query to collect state name if the state list in DB is used
    /*    contactsInfo_Sql = " select sta.state_name" +
                                    " from contacts cnt,state sta " +
                                    " where  cnt.contact_id='"+contactId+"' and cnt.state=sta.state_id ";

        //System.out.println("contactsInfo_Sql:"+contactsInfo_Sql);

        rsGetContactInfo = statement.executeQuery(contactsInfo_Sql);
        while(rsGetContactInfo.next()){
          resultTemp =  rsGetContactInfo.getString("sta.state_name");
           if(rsGetContactInfo.wasNull()) {
               resultTemp="";
           }
           cntStateName=resultTemp;
        }*/





    //query to collect country name
        contactsInfo_Sql = " select cty.name" +
                                    " from contacts cnt,country cty " +
                                    " where  cnt.contact_id='"+contactId+"' and cnt.country=cty.code ";
        //System.out.println("contactsInfo_Sql:"+contactsInfo_Sql);
        rsGetContactInfo = statement.executeQuery(contactsInfo_Sql);
        while(rsGetContactInfo.next()){
          resultTemp =  rsGetContactInfo.getString("cty.name");
           if(rsGetContactInfo.wasNull()) {
               resultTemp="";
           }
           contactCountryName=resultTemp;
        }


       contactsInfo_Sql = " select ctm.type_name,ctm.type_id " +
                                    " from contacts cnt,contacts_type_master ctm,contacttype_map ctmp " +
                                    " where  cnt.contact_id='"+contactId+"' and cnt.contact_id=ctmp.contact_id and ctmp.type_id=ctm.type_id ";

        //System.out.println("contactsInfo_Sql:"+contactsInfo_Sql);

        rsGetContactInfo = statement.executeQuery(contactsInfo_Sql);
        while(rsGetContactInfo.next()){
           resultTemp =  rsGetContactInfo.getString("ctm.type_name");
           if(rsGetContactInfo.wasNull()) {
               resultTemp="";
           }
           contactType=resultTemp;

           resultTemp =  rsGetContactInfo.getString("ctm.type_id");
           if(rsGetContactInfo.wasNull()) {
               resultTemp="";
           }
           contactTypeId=resultTemp;
        }


//System.out.println("contactTypeId11:"+contactTypeId);
           if(contactTypeId.equals("2") || contactTypeId.equals("3")){//If the contact type is Division then the below block will be executed to show the company/customer name in the customer field and divison in the Division name in the division field
           //contactDivision=contactCompany;
           String companySql = "select cnt.company,blt.parent_contact from contacts cnt,belongs_to blt where "
                   + "blt.contact_id='"+contactId+"' and blt.parent_contact=cnt.contact_id ";
//System.out.println("companySql:"+companySql);
            ResultSet rsGetCompany = statement.executeQuery(companySql);
                while(rsGetCompany.next()){
                          contactCompany = splChar.decoding(rsGetCompany.getString(1));
                          if(rsGetCompany.wasNull()){
                              contactCompany="";
                          }
                          parentContactId = rsGetCompany.getString(2) != null ? rsGetCompany.getString(2).toString() : "";
                }
           }


        contactsInfo_Sql = " select cct.cnt_category" +
                                    " from contacts cnt,contact_category cct " +
                                    " where  cnt.contact_id='"+contactId+"' and cnt.is_person=cct.cnt_categoryid ";

        //System.out.println("contactsInfo_Sql:"+contactsInfo_Sql);

        rsGetContactInfo = statement.executeQuery(contactsInfo_Sql);
        while(rsGetContactInfo.next()){
       
        resultTemp =  rsGetContactInfo.getString("cct.cnt_category");
           if(rsGetContactInfo.wasNull()) {
               resultTemp="";
           }
           contactCategoryName=resultTemp;
        }

        /*/
         *If the division combo should be made as selected then add a block here
         *
         */

         rsGetContactInfo.close();
         statement.close();



        }catch(SQLException sqle){

            System.out.println("SQLException in collecting ContactInfo:"+sqle);
        }catch(Exception e){

            System.out.println("Exception in collecting ContactInfo:"+e);
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

    public void getParentContactDetail() {

        String ContactId = searchWord;
        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setContactId(ContactId);
        contactInfo.collectContactInfo();

        JSONObject jsonObject = new JSONObject();

        try {

            jsonObject.put("contactId", contactInfo.getContactId().toString());
            jsonObject.put("email", contactInfo.getContactEmail().toString());
            jsonObject.put("website", contactInfo.getCntWebSite().toString());
            jsonObject.put("vendorNo", contactInfo.getcontactVendorNum().toString());
            jsonObject.put("add1", contactInfo.getCntPriAdd().toString());
            jsonObject.put("add2", contactInfo.getCntSecAdd().toString());
            jsonObject.put("city", contactInfo.getContactCity().toString());
            jsonObject.put("state", contactInfo.getCntStateName().toString());
            jsonObject.put("country", contactInfo.getContactCountry().toString());
            jsonObject.put("zip", contactInfo.getCntZipCode().toString());
            jsonObject.put("sub", contactInfo.getContactSubSpcn().toString());
            jsonObject.put("wrkHrs", contactInfo.getContactWorkingHrs().toString());
            jsonObject.put("cntHrs", contactInfo.getContactContactHrs().toString());
            jsonObject.put("phone1", contactInfo.getContactPhone().toString());
            jsonObject.put("phone2", contactInfo.getCntSecPhone().toString());
            jsonObject.put("cell", contactInfo.getCntMobPhone().toString());
            jsonObject.put("fax1", contactInfo.getCntFax1().toString());
            jsonObject.put("fax2", contactInfo.getCntFax2().toString());
            jsonObject.put("skype", contactInfo.getCntSkype().toString());
            jsonObject.put("msn", contactInfo.getCntMSN().toString());
            jsonObject.put("aol", contactInfo.getCntAOL().toString());
            jsonObject.put("gtalk", contactInfo.getCntGtalk().toString());
            jsonObject.put("yahoo", contactInfo.getCntYahoo().toString());
            jsonObject.put("linkedin", contactInfo.getCntLinkedIn().toString());
            jsonObject.put("facebook", contactInfo.getCntFacebook().toString());
            jsonObject.put("twitter", contactInfo.getCntTwitter().toString());

            patentCntId.add(ContactId);
            patentCntDetail.add(jsonObject);

        } catch(Exception e) {
            System.out.println("ContactInfo - getParentContactDetail() : " + e);
        }
        return;
    }



/*public String decodeToHtmlTag(String text){
        int len= text.length();
        StringBuffer encodedQuestion=new StringBuffer();
	String testString = "";
	testString  = text.replaceAll("&lt;", "<");
	testString  = testString.replaceAll("&gt;", ">");
	testString  = testString.replaceAll("\n", "<br/>");
	return testString;
    }
*/

  
}
