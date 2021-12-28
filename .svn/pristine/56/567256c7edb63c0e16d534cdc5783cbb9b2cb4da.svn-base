/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package filters.contacts;

import java.io.Serializable;
import java.util.*;
import connection.DBconnection;
import java.sql.*;
/**
 *
 * @author ramesh
 */
public class UnmappedContactNameList implements Serializable {

    private List contactId = new ArrayList();
    private List contactName = new ArrayList();
    private List contactNameList = new ArrayList();
    private List firstName = new ArrayList();
    private List surName = new ArrayList();

    private String contactType="";
    private String clientId="";
    private String contactSearchKey="";
    private String parentContact="";
    private String belongsTo="";

    public List getCntID(){
        return contactId;
    }

    public List getCntName(){
        return contactName;
    }

    public List getCntNameList(){
        return contactNameList;
    }

    public List getFirstName(){
        return firstName;
    }

    public List getSurName(){
        return surName;
    }

    public void setContactType(String contactType){
        this.contactType=contactType;
    }

    public void setClientId(String clientId){
        this.clientId=clientId;
       // System.out.println("clientId in CNM List Bean:"+clientId);
    }

   public void setParentContact(String parentContact){
        this.parentContact=parentContact;
       // System.out.println("clientId in CNM List Bean:"+clientId);
    }

   //the below setter is to form a query in scenarios whee we need to filter data based on the belongs to mapping.
   //ContactList belongs to a parent contact
   //ContactList does not belong to parent contact
    public void setBelongsTo(String belongsTo){
        this.belongsTo=belongsTo;
       // System.out.println("clientId in CNM List Bean:"+clientId);
    }


    public void setCntSearchKey(String contactSearchKey){
        this.contactSearchKey=contactSearchKey;
    }

    public void collectContactName(){
        pathfinder.util.SpecialCharHandler splChar = new pathfinder.util.SpecialCharHandler();
        try{
        DBconnection dbcon = new DBconnection();
        Connection con = dbcon.getSampleProperty();
        String resultTemp = "";
        String contactCategory="";
        String contactCompany="";

        Statement statement = con.createStatement();

        String contactName_Sql = 
                " select concat_ws('',cnt.firstname,' ', cnt.surname),cnt.contact_id ,cnt.firstname,cnt.surname, " +
                " cnt.is_person,cnt.company,concat_ws('',cnt.firstname,'_', cnt.surname)  from contacts cnt ";

        String where="";
        String condition="";

        contactName.clear();
        contactId.clear();

        where += "where";

        if(!contactSearchKey.equals("")){
            condition+=" (cnt.firstname like '"+contactSearchKey+"%' or"
                    + " cnt.surname like '"+contactSearchKey+"%' or "
                    + "concat(cnt.firstname,' ',cnt.surname) "
                    + "like  '"+contactSearchKey+"%' or "
                    + "cnt.company like '"+contactSearchKey+"%' ) ";
        }

        if(!contactType.equals("")){
            contactName_Sql += " ,contacttype_map cntm ";
            if(condition.length()>0){
                condition+=" and ";
            }
            condition+=" cnt.contact_id=cntm.contact_id and cntm.type_id='"+contactType+"' ";
        }


        contactName_Sql += where+condition;

            if(condition.length()>0){
                contactName_Sql+=" and ";
            }

               contactName_Sql +=  "cnt.contact_id not in"
                + "(select cnt.contact_id from contacts cnt,belongs_to blt,contacttype_map cntm "
                + "where cnt.contact_id=blt.contact_id and blt.parent_contact='"+parentContact+"' "
                + "and cnt.contact_id=cntm.contact_id and cntm.type_id='"+contactType+"')";


           contactName_Sql += " order by cnt.firstname asc limit 20";

//System.out.println("unMappedcontactName_Sql:"+contactName_Sql);

        ResultSet rsGetContactName = statement.executeQuery(contactName_Sql);
        while(rsGetContactName.next()){

           contactCategory =  rsGetContactName.getString("cnt.is_person");
           resultTemp =  rsGetContactName.getString("cnt.contact_id");
           if(rsGetContactName.wasNull()) {
               resultTemp="";
           }
           contactId.add(resultTemp);

        if(contactCategory.equals("1")){
                      resultTemp =  splChar.decoding(rsGetContactName.getString("concat_ws('',cnt.firstname,' ', cnt.surname)"));
           if(rsGetContactName.wasNull()) {
               resultTemp="";
           }
           contactName.add(resultTemp);

           resultTemp =  splChar.decoding(rsGetContactName.getString("concat_ws('',cnt.firstname,'_', cnt.surname)"));
           if(rsGetContactName.wasNull()) {
               resultTemp="";
           }
           contactNameList.add(resultTemp);

          resultTemp =  splChar.decoding(rsGetContactName.getString("cnt.firstname"));
           if(rsGetContactName.wasNull()) {
               resultTemp="";
           }
           firstName.add(resultTemp);

          resultTemp =  splChar.decoding(rsGetContactName.getString("cnt.surname"));
           if(rsGetContactName.wasNull()) {
               resultTemp="";
           }
           surName.add(resultTemp);
        }
        else{
          resultTemp =  splChar.decoding(rsGetContactName.getString("cnt.company"));
           if(rsGetContactName.wasNull()) {
               resultTemp="";
           }
           contactName.add(resultTemp);
           contactNameList.add(resultTemp);
           firstName.add(resultTemp);
           surName.add("");
        }
           //contactNameList

        }
        rsGetContactName.close();
        statement.close();
         con.close();
        }catch(SQLException sqle){
            System.out.println("SQLException in collecting unMappedContactNameList:"+sqle);
        }catch(Exception e){
            System.out.println("Exception in collecting unMappedContactNameList:"+e);
        }

    }

}
