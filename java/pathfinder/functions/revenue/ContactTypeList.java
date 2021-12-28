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
 * class to collect the contact type and return them
 */

public class ContactTypeList implements Serializable {


    private List contactCode = new ArrayList();
    private List contactType = new ArrayList();
    private List contactTypeDesc = new ArrayList();

    public ContactTypeList(){

    }

    public List getCntCode(){
        return contactCode;
    }

    public List getCntType(){
        return contactType;
    }

      public List getCntTypeDesc(){
        return contactTypeDesc;
    }


    public void collectContactType(){

        try{
        DBconnection dbcon = new DBconnection();
        Connection con = dbcon.getSampleProperty();
        String resultTemp = "";

        Statement statement = con.createStatement();

        ResultSet rsGetContactType = statement.executeQuery("select type_id,type_name,description from contacts_type_master");
        while(rsGetContactType.next()){

          resultTemp =  rsGetContactType.getString("type_id");
           if(rsGetContactType.wasNull()) {
               resultTemp="";
           }
           contactCode.add(resultTemp);

           resultTemp =  rsGetContactType.getString("type_name");
           if(rsGetContactType.wasNull()) {
               resultTemp="";
           }
           contactType.add(resultTemp);

          resultTemp =  rsGetContactType.getString("description");
           if(rsGetContactType.wasNull()) {
               resultTemp="";
           }
           contactTypeDesc.add(resultTemp);


        }
        rsGetContactType.close();
        statement.close();
        con.close();

        }catch(SQLException sqle){

            System.out.println("SQLException in collecting ContactTypeList:"+sqle);
        }catch(Exception e){

            System.out.println("Exception in collecting ContactTypeList:"+e);
        }

    }


  
}
