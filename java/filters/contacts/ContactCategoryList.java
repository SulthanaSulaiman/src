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
 * class to collect the contact type and return them
 */

public class ContactCategoryList implements Serializable {


    private List contactCategoryCode = new ArrayList();
    private List contactCategoryType = new ArrayList();
    private Connection con = null;
    
    public ContactCategoryList(){

    }

    public List getCntCategoryCode(){
        return contactCategoryCode;
    }

    public List getCntCategoryType(){
        return contactCategoryType;
    }



    public void collectContactCategory(){

        try{
        DBconnection dbcon = new DBconnection();
        con = dbcon.getSampleProperty();
        String resultTemp = "";

        Statement statement = con.createStatement();

        ResultSet rsGetContactType = statement.executeQuery("select cnt_categoryid,cnt_category from contact_category");
        while(rsGetContactType.next()){

          resultTemp =  rsGetContactType.getString("cnt_categoryid");
           if(rsGetContactType.wasNull()) {
               resultTemp="";
           }
           contactCategoryCode.add(resultTemp);

           resultTemp =  rsGetContactType.getString("cnt_category");
           if(rsGetContactType.wasNull()) {
               resultTemp="";
           }
           contactCategoryType.add(resultTemp);

        }
        rsGetContactType.close();
        statement.close();


        }catch(SQLException sqle){

            System.out.println("SQLException in collecting ContactTypeList:"+sqle);
        }catch(Exception e){

            System.out.println("Exception in collecting ContactTypeList:"+e);
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
