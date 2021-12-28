/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.revenue;


import java.io.Serializable;
import java.util.*;
import connection.DBconnection;
import java.sql.*;
/**
 *
 * @author ramesh
 * class to retrieve the Contact Name List based on their type or whole list
 */
public class ContactNameList implements Serializable {


    private List contactId = new ArrayList();
    private List contactName = new ArrayList();
    private List firstName = new ArrayList();
    private List surName = new ArrayList();

    private String contactType="";



    public List getCntID(){
        return contactId;
    }

    public List getCntName(){
        return contactName;
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


    public void collectContactName(){

        pathfinder.util.SpecialCharHandler splChar = new pathfinder.util.SpecialCharHandler();
        try{
        DBconnection dbcon = new DBconnection();
        Connection con = dbcon.getSampleProperty();
        String resultTemp = "";

        Statement statement = con.createStatement();

            String contactName_Sql = " select concat(firstname,' ', surname),cnt.contact_id" +
                                    " ,cnt.firstname,cnt.surname " +
                                    " from contacts cnt ";

        String where="";
        String condition="";

        contactName.clear();
        contactId.clear();

        if(!contactType.equals("")){
            contactName_Sql += " ,contacttype_map cntm ";
            where += "where";
            condition=" cnt.contact_id=cntm.contact_id and cntm.type_id='"+contactType+"' ";
        }

        contactName_Sql += where+condition;


        ResultSet rsGetContactName = statement.executeQuery(contactName_Sql);
        while(rsGetContactName.next()){

          resultTemp =  splChar.decoding(rsGetContactName.getString("concat(firstname,' ', surname)"));
           if(rsGetContactName.wasNull()) {
               resultTemp="";
           }
           contactName.add(resultTemp);

           resultTemp =  rsGetContactName.getString("cnt.contact_id");
           if(rsGetContactName.wasNull()) {
               resultTemp="";
           }
           contactId.add(resultTemp);

//
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
        rsGetContactName.close();
        statement.close();
        con.close();
        }catch(SQLException sqle){

            System.out.println("SQLException in collecting ContactNameList:"+sqle);
        }catch(Exception e){

            System.out.println("Exception in collecting ContactNameList:"+e);
        }

    }



   
}
