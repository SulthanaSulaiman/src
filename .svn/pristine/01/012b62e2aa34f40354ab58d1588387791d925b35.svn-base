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

public class StateList implements Serializable {


    private List stateId = new ArrayList();
    private List stateName = new ArrayList();
    private String countryCode="" ;
    private Connection con = null;

    public StateList(){

    }

    public void setCountry(String countryCode){
        this.countryCode=countryCode;
    }

    public List getStateId(){
        return stateId;
    }

    public List getStateName(){
        return stateName;
    }
 

    public void collectStateList(){

        try{
        DBconnection dbcon = new DBconnection();
        con = dbcon.getSampleProperty();
        String resultTemp = "";

        Statement statement = con.createStatement();

        String stateList_Sql = " select stt.state_id,stt.state_name" +
                  " from state stt ";
                               //     " where cnt.contact_id=ctmp.contact_id and ctmp.type_id=ctm.type_id ";
          if(!countryCode.equals("")&&!countryCode.equals("All")){
              stateList_Sql += " where stt.countrycode='"+countryCode+"' ";
          }

        //System.out.println("stateList_Sql;"+stateList_Sql);

        ResultSet rsGetStateList = statement.executeQuery(stateList_Sql);
        while(rsGetStateList.next()){

          resultTemp =  rsGetStateList.getString("stt.state_id");
           if(rsGetStateList.wasNull()) {
               resultTemp="";
           }
           stateId.add(resultTemp);

           resultTemp =  rsGetStateList.getString("stt.state_name");
           if(rsGetStateList.wasNull()) {
               resultTemp="";
           }
           stateName.add(resultTemp);

           
        }
        rsGetStateList.close();
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
