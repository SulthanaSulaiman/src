/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.contacts;



import java.io.Serializable;
import java.util.*;
import connection.DBconnection;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ramesh
 * class to collect the contact type and return them
 */

public class BrowseContactsList implements Serializable {

    private String searchKey = "";
    private List contactId = new ArrayList();
    private List contactName = new ArrayList();
    private List contactDesig = new ArrayList();
    private List contactCompany = new ArrayList();
    private List contactPhone = new ArrayList();
    private List contactEmail = new ArrayList();
    private List contactCity = new ArrayList();
    private     List contactCountry = new ArrayList();
    private List contactType = new ArrayList();
    private List contactCategory = new ArrayList();
    private List groupContactTypeId = new ArrayList();
    private String limitSql="";
    private String cntTypeId = "";
    private List contactSubSpcn = new ArrayList();
   private List contactWorkingHrs = new ArrayList();
    private List contactContactHrs  = new ArrayList();
    private List attentionId  = new ArrayList();
    private List attentionName  = new ArrayList();

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public List getContactContactHrs() {
        return contactContactHrs;
    }

    public void setContactContactHrs(List contactContactHrs) {
        this.contactContactHrs = contactContactHrs;
    }

    public List getContactSubSpcn() {
        return contactSubSpcn;
    }

    public void setContactSubSpcn(List contactSubSpcn) {
        this.contactSubSpcn = contactSubSpcn;
    }

    public List getContactWorkingHrs() {
        return contactWorkingHrs;
    }

    public void setContactWorkingHrs(List contactWorkingHrs) {
        this.contactWorkingHrs = contactWorkingHrs;
    }

    public List getAttentionId() {
        return attentionId;
    }

    public void setAttentionId(List attentionId) {
        this.attentionId = attentionId;
    }

    public List getAttentionName() {
        return attentionName;
    }

    public void setAttentionName(List attentionName) {
        this.attentionName = attentionName;
    }

    public BrowseContactsList(){

    }

    public List getContactId(){
        return contactId;
    }

    public List getContactName(){
        return contactName;
    }

    public List getContactDesig(){
        return contactDesig;
    }

    public List getContactCompany(){
        return contactCompany;
    }


    public List getContactPhone(){
        return contactPhone;
    }

    public List getContactEmail(){
        return contactEmail;
    }

     public List getContactCity(){
        return contactCity;
    }

     public List getContactCountry(){
        return contactCountry;
    }

    public List getContactType(){
        return contactType;
    }

    public List getContactCategory(){
        return contactCategory;
    }

    public void setLimitSql(String limitSql){
        this.limitSql=limitSql;
    }

    public void setContactType(String cntTypeId){
        this.cntTypeId=cntTypeId;
    }

    public void setGroupContactType(List groupContactTypeId){
        this.groupContactTypeId=groupContactTypeId;
    }


    public void collectContactList(){

        pathfinder.util.SpecialCharHandler splChar = new pathfinder.util.SpecialCharHandler();
       // System.out.println("groupContactTypeId:"+groupContactTypeId);

        try{
        DBconnection dbcon = new DBconnection();
        Connection con = dbcon.getSampleProperty();
        String resultTemp = "";

        Statement statement = con.createStatement();


        String groupTypeId = "";
        for(int idx=0;idx<groupContactTypeId.size();idx++){

            if(idx>0&&idx<groupContactTypeId.size()){
                 groupTypeId += ",";
            }
            groupTypeId += "'"+groupContactTypeId.get(idx).toString()+"'";


        }

        String contactsList_Sql = " select cnt.contact_id,cnt.firstname,cnt.surname," +
                                    " cnt.designation,cnt.company,cnt.phone_primary,"+
                                    " cnt.email,cnt.city,cnt.is_person,cnt.sub_spcn,cnt.working_hrs,cnt.contact_hrs"+
                                    " from contacts cnt";
        if(!cntTypeId.equals("")){
            contactsList_Sql += "  ,contacttype_map cntm where cntm.contact_id=cnt.contact_id and cntm.type_id='"+cntTypeId+"' ";
        }
        else{
            //if(!groupTypeId.equals(""))
             contactsList_Sql += "  ,contacttype_map cntm where cntm.contact_id=cnt.contact_id and cntm.type_id in ("+groupTypeId+") ";
        }

           contactsList_Sql = contactsList_Sql+limitSql;
        // System.out.println("groupTypeId:"+groupTypeId);
      //   System.out.println("contactsList_Sql:"+contactsList_Sql);

        ResultSet rsGetContactList = statement.executeQuery(contactsList_Sql);
        while(rsGetContactList.next()){

          resultTemp =  rsGetContactList.getString("cnt.contact_id");
           if(rsGetContactList.wasNull()) {
               resultTemp="";
           }
           contactId.add(resultTemp);

           resultTemp =  splChar.decoding(rsGetContactList.getString("cnt.firstname"));
           if(rsGetContactList.wasNull()) {
               resultTemp="";
           }
           resultTemp +=  " "+splChar.decoding(rsGetContactList.getString("cnt.surname"));
           if(rsGetContactList.wasNull()) {
               resultTemp="";
           }
           contactName.add(resultTemp);

           resultTemp =  rsGetContactList.getString("cnt.designation");
           if(rsGetContactList.wasNull()) {
               resultTemp="";
           }
           contactDesig.add(resultTemp);

           resultTemp =  splChar.decoding(rsGetContactList.getString("cnt.company"));
           if(rsGetContactList.wasNull()) {
               resultTemp="";
           }
           contactCompany.add(resultTemp);


           resultTemp =  rsGetContactList.getString("cnt.phone_primary");
           if(rsGetContactList.wasNull()) {
               resultTemp="";
           }
           contactPhone.add(resultTemp);

           resultTemp =  rsGetContactList.getString("cnt.email");
           if(rsGetContactList.wasNull()) {
               resultTemp="";
           }
           contactEmail.add(resultTemp);

           resultTemp =  rsGetContactList.getString("cnt.city");
           if(rsGetContactList.wasNull()) {
               resultTemp="";
           }
           contactCity.add(resultTemp);

           resultTemp =  rsGetContactList.getString("cnt.is_person");
           if(rsGetContactList.wasNull()) {
               resultTemp="";
           }
           contactCategory.add(resultTemp);//to set the hyperlink either on the contact name or the company name in the contacts list page
       
           resultTemp = rsGetContactList.getString("cnt.sub_spcn");
           if(rsGetContactList.wasNull()) {
               resultTemp  ="";
           }
           contactSubSpcn.add(resultTemp);
          // System.out.println("cntCreatedBy:"+cntCreatedBy);
           resultTemp = rsGetContactList.getString("cnt.working_hrs");
           if(rsGetContactList.wasNull()) {
                  resultTemp  ="";
           }
           contactWorkingHrs.add(resultTemp);

           resultTemp = rsGetContactList.getString("cnt.contact_hrs");
           if(rsGetContactList.wasNull()) {
               resultTemp  ="";
           }
           contactContactHrs.add(resultTemp);
        
        
        }

        //System.out.println("contactsList_Sql:"+contactsList_Sql);

        String tempContactId="";
        for(int idx=0;idx<contactId.size();idx++){
            tempContactId=contactId.get(idx).toString();

        contactsList_Sql = " select cty.name"+
                                    " from contacts cnt,country cty "+
                                    " where cnt.contact_id='"+tempContactId+"' and cnt.country=cty.code ";

        //System.out.println("contactsList_Sql:"+contactsList_Sql);
resultTemp="";
        rsGetContactList = statement.executeQuery(contactsList_Sql);
        while(rsGetContactList.next()){
           resultTemp =  rsGetContactList.getString("cty.name");
           if(rsGetContactList.wasNull()) {
               resultTemp="";
           }

        }
  contactCountry.add(resultTemp);

      contactsList_Sql = " select ctm.type_name"+
                                    " from contacts cnt,contacts_type_master ctm,contacttype_map ctmp "+
                                    " where cnt.contact_id='"+tempContactId+"' and cnt.contact_id=ctmp.contact_id and ctmp.type_id=ctm.type_id ";

     // System.out.println("contactsList_Sql:"+contactsList_Sql);
resultTemp="";
        rsGetContactList = statement.executeQuery(contactsList_Sql);
        while(rsGetContactList.next()){
           resultTemp =  rsGetContactList.getString("ctm.type_name");
           if(rsGetContactList.wasNull()) {
               resultTemp="";
           }

        }

 contactType.add(resultTemp);

        }
        rsGetContactList.close();
        statement.close();
        con.close();


        }catch(SQLException sqle){

            System.out.println("SQLException in collecting ContactTypeList:"+sqle);
        }catch(Exception e){

            System.out.println("Exception in collecting ContactTypeList:"+e);
        }

    }

    public void GetClientCompanies() {
        DBconnection dbcon = new DBconnection();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        pathfinder.util.SpecialCharHandler splChar = new pathfinder.util.SpecialCharHandler();
        try {
            con = (Connection) dbcon.getSampleProperty();
            st = (Statement) con.createStatement();

            String transmittalQuery = "SELECT c.contact_id, c.company FROM contacts c, contacttype_map ctm, belongs_to b "
                    + " WHERE c.contact_id=ctm.contact_id AND ctm.type_id='1' AND c.contact_id=b.parent_contact and "
                    + " (c.company LIKE '"+searchKey+"%' OR c.contact_id LIKE '') group by c.contact_id";

            rs = st.executeQuery(transmittalQuery);
            while (rs.next()) {
                if (rs.getString(1) != null) {
                    contactId.add(rs.getString(1));
                } else {
                    contactId.add("");
                }
                if (rs.getString(2) != null) {
                    contactCompany.add(splChar.decoding(rs.getString(2)));
                } else {
                    contactCompany.add("");
                }
            }
        } catch(Exception e) {
            System.out.println("Exception while getting client list : "+e);
        } finally {

            //Close resultset
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException se) {


                    System.out.println("Exception while closing resultset:"+se.getMessage());

                }

            }

            //Close statement
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException se) {


                    System.out.println("Exception while closing resultset:"+se.getMessage());

                }
            }

            //Close connection
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException se) {


                    System.out.println("Exception while closing resultset:"+se.getMessage());

                }
            }

    }
    }
    
    public void getAllContacts() {
        DBconnection dbcon = new DBconnection();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        pathfinder.util.SpecialCharHandler splChar = new pathfinder.util.SpecialCharHandler();
        try {
            con = (Connection) dbcon.getSampleProperty();
            st = (Statement) con.createStatement();

            String transmittalQuery = "SELECT c.contact_id, "
                    + " CASE "
                    //+ "	    WHEN ctmr.type_id=1 OR ctmr.type_id=2 OR ctmr.type_id=5 THEN CONCAT(c.company,' (',ctmr.type_name,')') "
                    + "	    WHEN ctmr.type_id=1 OR ctmr.type_id=2 OR ctmr.type_id=5 THEN c.company "
                    + "	    ELSE TRIM(CONCAT(ifnull(firstname,''),' ',ifnull(surname,''))) "
                    + "	END AS DispName "
                    + " FROM contacts c, contacttype_map ctm, contacts_type_master ctmr "
                    + " WHERE c.contact_id=ctm.contact_id AND ctm.type_id=ctmr.type_id AND "
                    + " (c.company LIKE '" + searchKey + "%' OR c.firstname LIKE '" + searchKey + "%' OR c.surname LIKE '" + searchKey + "%') "
                    + " GROUP BY c.contact_id LIMIT 15";

            rs = st.executeQuery(transmittalQuery);
            while (rs.next()) {
                if (rs.getString(1) != null) {
                    contactId.add(rs.getString(1));
                } else {
                    contactId.add("");
                }
                if (rs.getString(2) != null) {
                    contactCompany.add(splChar.decoding(rs.getString(2)));
                } else {
                    contactCompany.add("");
                }
            }
        } catch (Exception e) {
            System.out.println("Exception while getting client list : " + e);
        } finally {
            try {
                st.close();
                rs.close();
                con.close();
            } catch (SQLException se) {
                System.out.println("SQLException : " + se.getMessage());
            }
        }
    }

    public void GetClientAttentions() {
        DBconnection dbcon = new DBconnection();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        pathfinder.util.SpecialCharHandler splChar = new pathfinder.util.SpecialCharHandler();
        try {
            con = (Connection) dbcon.getSampleProperty();
            st = (Statement) con.createStatement();

            String transmittalQuery = "SELECT a.contact_id, CONCAT(a.firstname, ' ',a.surname) FROM contacts c, contacttype_map ct, contacts a, contacttype_map AT, belongs_to b "
                    + "WHERE c.contact_id=ct.contact_id AND ct.type_id='1' AND a.contact_id=at.contact_id AND at.type_id='3' AND "
                    + "c.contact_id=b.parent_contact AND a.contact_id=b.contact_id AND c.contact_id='"+searchKey+"'";
            rs = st.executeQuery(transmittalQuery);
            while (rs.next()) {
                if (rs.getString(1) != null) {
                    attentionId.add(rs.getString(1));
                } else {
                    attentionId.add("");
                }
                if (rs.getString(2) != null) {
                    attentionName.add(splChar.decoding(rs.getString(2)));
                } else {
                    attentionName.add("");
                }
            }
            contactId.add("1");
            contactCompany.add("1");
        } catch(Exception e) {
            System.out.println("Exception while getting client list : "+e);
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println("SQLException in BrowseContactList - GetClientCompanies : "+ex);
            }
        }
    }
}
