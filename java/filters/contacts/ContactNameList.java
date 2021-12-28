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
public class ContactNameList implements Serializable {

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
    private String isSuggest="";
    private Connection con = null;

    public void setIsSuggest(String isSuggest)
    {
        this.isSuggest = isSuggest;
    }
    
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
        con = dbcon.getSampleProperty();
        String resultTemp = "";
        String contactCategory="";
        String contactCompany="";

        Statement statement = con.createStatement();

        String contactName_Sql = " select concat_ws('',cnt.firstname,' ', cnt.surname),cnt.contact_id" +
                                    " ,cnt.firstname,cnt.surname,cnt.is_person,cnt.company,concat_ws('',cnt.firstname,' ', cnt.surname) as searchList " +
                                    " from contacts cnt ";

        String where="";
        String condition="";

        contactName.clear();
        contactId.clear();

        where += "where";

        if(!contactSearchKey.equals("")){
            condition+=" (cnt.firstname like '"+splChar.encoding(contactSearchKey)+"%' or cnt.surname like '"+splChar.encoding(contactSearchKey)+"%' or concat(firstname,' ', surname) like  '"+splChar.encoding(contactSearchKey)+"%' or cnt.company like '"+splChar.encoding(contactSearchKey)+"%' ) ";
        }

        if(!contactType.equals("")){
            contactName_Sql += " ,contacttype_map cntm ";
            if(condition.length()>0){
                condition+=" and ";
            }
            condition+=" cnt.contact_id=cntm.contact_id and cntm.type_id='"+contactType+"' ";
        }


        if(!clientId.equals("")){
            contactName_Sql += " ,belongs_to blt ";
            if(condition.length()>0){
                condition+=" and ";
            }
            condition+="   blt.parent_contact='"+clientId+"' and blt.contact_id=cnt.contact_id ";
        }


        contactName_Sql += where+condition+" order by cnt.firstname asc limit 20";

//System.out.println("contactName_Sql:"+contactName_Sql);

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

           resultTemp =  splChar.decoding(rsGetContactName.getString("searchList"));
           if(rsGetContactName.wasNull()) {
               resultTemp="";
           }
           contactNameList.add(resultTemp);

           //System.out.println("contactNameList:"+contactNameList);

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

        }catch(SQLException sqle){
            System.out.println("SQLException in collecting ContactNameList:"+sqle);
        }catch(Exception e){
            System.out.println("Exception in collecting ContactNameList:"+e);
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

}
