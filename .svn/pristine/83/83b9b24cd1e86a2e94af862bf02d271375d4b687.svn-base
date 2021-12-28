/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.contacts;

import java.io.Serializable;
import java.util.*;
import connection.DBconnection;
import java.sql.*;

/**
 *
 * @author ramesh
 */
public class UserGroupsContactTypes implements Serializable {

    private List contactTypeIdList = new ArrayList();
    private String empId ="";
    private Connection con = null;
    //private List contactTypeIdList = new ArrayList();

    public UserGroupsContactTypes() {
        
    }

    public void setEmpId(String empId){
        this.empId=empId;
    }

    public List getContactTypeId(){
        return contactTypeIdList;
    }

    public void collectContactType(){

        try{
        DBconnection dbcon = new DBconnection();
        con = dbcon.getSampleProperty();
        String resultTemp = "";
        Statement statement = con.createStatement();


        String contactsTypeList_Sql = " select gct.contacttype_id from groups_contact_type gct,groups gps"
                + " where gps.group_id=gct.group_id "
                + " and gps.emp_id='"+empId+"' " ;

        ResultSet rsGetCntTypeId = statement.executeQuery(contactsTypeList_Sql);
        while(rsGetCntTypeId.next()){
            resultTemp = rsGetCntTypeId.getString(1);
            if(rsGetCntTypeId.wasNull()){
               resultTemp = "" ;
            }

            if(!resultTemp.equals(""))
            contactTypeIdList.add(resultTemp);
        }
        rsGetCntTypeId.close();
        statement.close();
                                    
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }
finally{
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
