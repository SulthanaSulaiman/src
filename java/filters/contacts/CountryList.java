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

public class CountryList implements Serializable {


    private List countryCode = new ArrayList();
    private List countryName = new ArrayList();
    private Connection con = null;
    
    public CountryList(){

    }

    public List getCountryCode(){
        return countryCode;
    }

    public List getCountryName(){
        return countryName;
    }
 

    public void collectCountryList(){

        try{
        DBconnection dbcon = new DBconnection();
        con = dbcon.getSampleProperty();
        String resultTemp = "";

        Statement statement = con.createStatement();

        String countryList_Sql = " select cty.Code,cty.Name" +
                  " from country cty  where recently_used is not null"
                  + " order by recently_used asc";
                               //     " where cnt.contact_id=ctmp.contact_id and ctmp.type_id=ctm.type_id ";

        ResultSet rsGetCountryList = statement.executeQuery(countryList_Sql);
        while(rsGetCountryList.next()){
          resultTemp =  rsGetCountryList.getString("cty.Code");
           if(rsGetCountryList.wasNull()) {
               resultTemp="";
           }
           countryCode.add(resultTemp);

           resultTemp =  rsGetCountryList.getString("cty.Name");
           if(rsGetCountryList.wasNull()) {
               resultTemp="";
           }
           countryName.add(resultTemp);
        }

        countryList_Sql = " select cty.Code,cty.Name" +
                  " from country cty  where recently_used is null";


        rsGetCountryList = statement.executeQuery(countryList_Sql);
        while(rsGetCountryList.next()){
          resultTemp =  rsGetCountryList.getString("cty.Code");
           if(rsGetCountryList.wasNull()) {
               resultTemp="";
           }
           countryCode.add(resultTemp);

           resultTemp =  rsGetCountryList.getString("cty.Name");
           if(rsGetCountryList.wasNull()) {
               resultTemp="";
           }
           countryName.add(resultTemp);
        }
        rsGetCountryList.close();
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
