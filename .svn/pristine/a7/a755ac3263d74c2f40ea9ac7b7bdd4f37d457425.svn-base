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
public class CompanyNameList implements Serializable {

    private List contactId = new ArrayList();
    private List contactName = new ArrayList();
    private List firstName = new ArrayList();
    private List surName = new ArrayList();

    private String contactType="";
    private String contactSearchKey="";
    private Connection con = null;

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

        String contactName_Sql = " select cnt.company,cnt.contact_id" +
                                    //" ,cnt.firstname,cnt.surname,cnt.is_person,cnt.company " +
                                    " from contacts cnt,contacttype_map cntm,contacts_type_master ctms "
                                    + " where cnt.contact_id=cntm.contact_id and cntm.type_id=ctms.type_id "
                                    + "and ctms.type_name = 'Customer' ";

        String where="";
        String condition="";

        contactName.clear();
        contactId.clear();

        where += "where";
        if(!contactSearchKey.equals("")){
            condition+=" and (cnt.company like '"+contactSearchKey+"%') ";
        }
        
        contactName_Sql += condition+" group by cnt.company order by cnt.company asc limit 20";

        ResultSet rsGetContactName = statement.executeQuery(contactName_Sql);
        while(rsGetContactName.next()){
           resultTemp =  rsGetContactName.getString("cnt.contact_id");
           if(rsGetContactName.wasNull()) {
               resultTemp="";
           }
           contactId.add(resultTemp);

          resultTemp =  splChar.decoding(rsGetContactName.getString("cnt.company"));
           if(rsGetContactName.wasNull()) {
               resultTemp="";
           }
           contactName.add(resultTemp);
        }


        //since the Comapny in the addContacts interfaqce shud show
        //both the client and the Companies that have been included
        //in the contacts table the above query is executed with the
        //contact type being changed to Company
        contactName_Sql = " select cnt.company,cnt.contact_id" +
                                    //" ,cnt.firstname,cnt.surname,cnt.is_person,cnt.company " +
                                    " from contacts cnt,contacttype_map cntm,contacts_type_master ctms "
                                    + " where cnt.contact_id=cntm.contact_id and cntm.type_id=ctms.type_id "
                                    + "and ctms.type_name = 'Company' ";

         where="";
         condition="";

        where += "where";
        if(!contactSearchKey.equals("")){
            condition+=" and (cnt.company like '"+contactSearchKey+"%') ";
        }

        contactName_Sql += condition+" group by cnt.company order by cnt.company asc limit 20";

        rsGetContactName = statement.executeQuery(contactName_Sql);
        while(rsGetContactName.next()){
           resultTemp =  rsGetContactName.getString("cnt.contact_id");
           if(rsGetContactName.wasNull()) {
               resultTemp="";
           }
           contactId.add(resultTemp);

          resultTemp =  splChar.decoding(rsGetContactName.getString("cnt.company"));
           if(rsGetContactName.wasNull()) {
               resultTemp="";
           }
           contactName.add(resultTemp);
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
