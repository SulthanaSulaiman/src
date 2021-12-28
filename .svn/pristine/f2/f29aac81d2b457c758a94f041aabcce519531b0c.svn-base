/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.leave;




import java.io.Serializable;
import java.util.*;
import connection.DBconnection;
import java.sql.*;
/**
 *
 * @author ramesh
 * class to collect the contact type and return them
 */

public class GenericValues implements Serializable {

private String timeStamp="";

    public GenericValues(){

    }

    public String getTimeStamp(){
        return timeStamp;
    }



    public void collectCountryList(){

        try{
        DBconnection dbcon = new DBconnection();
        Connection con = dbcon.getSampleProperty();
        String resultTemp = "";

        Statement statement = con.createStatement();

        String countryList_Sql = " select cty.Code,cty.Name" +
                  " from country cty ";
                               //     " where cnt.contact_id=ctmp.contact_id and ctmp.type_id=ctm.type_id ";

        ResultSet rsGetTimeStamp = statement.executeQuery("select CURRENT_TIMESTAMP()");
        while(rsGetTimeStamp.next()){
          resultTemp =  rsGetTimeStamp.getString(1);
           if(rsGetTimeStamp.wasNull()) {
               resultTemp="";
           }
           timeStamp=resultTemp;
        }

        rsGetTimeStamp.close();
        statement.close();
        con.close();

        }catch(SQLException sqle){

            System.out.println("SQLException in collecting ContactTypeList:"+sqle);
        }catch(Exception e){

            System.out.println("Exception in collecting ContactTypeList:"+e);
        }

    }



}
