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

public class ContactTypeList implements Serializable {

    private List contactCode = new ArrayList();
    private List contactType = new ArrayList();
    private List contactTypeUsage = new ArrayList();
    private List contactTypePageName = new ArrayList();
    private List contactTypeDesc = new ArrayList();

    private String cntTypeId = "";
    private String cntTypeScriptUsage = "";

    private String selectedCntTypeId = "";
    private String selectedCntTypeName = "";



    public ContactTypeList(){

    }

    public List getCntCode(){
        return contactCode;
    }

    public List getCntType(){
        return contactType;
    }

 /*   public List getCntTypeDesc(){
        return contactTypeDesc;
    }*/

    public List getCntUsage(){
        return contactTypeUsage;
    }

    public List getCntCreationPage(){
        return contactTypePageName;
    }
//

    public void setCntTypeId(String cntTypeId){
        this.cntTypeId=cntTypeId;
    }

    public void setCntTypeScrUsage(String cntTypeScriptUsage){
        this.cntTypeScriptUsage=cntTypeScriptUsage;
    }

    //these two below methods returns the contact type id and name that are collected by setting either the script usage or type
    public String getCntTypeId(){
        return selectedCntTypeId;
    }

     public String getCntTypeName(){
        return selectedCntTypeName;
    }


//the below method collects the list of all avilable contact types and returns them as ArrayList
    public void collectContactType(){

        try{
        DBconnection dbcon = new DBconnection();
        Connection con = dbcon.getSampleProperty();
        String resultTemp = "";

        Statement statement = con.createStatement();

        ResultSet rsGetContactType = statement.executeQuery("select type_id,type_name,script_usage,creation_page"
                + " from contacts_type_master");
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

          resultTemp =  rsGetContactType.getString("script_usage");
           if(rsGetContactType.wasNull()) {
               resultTemp="";
           }
           contactTypeUsage.add(resultTemp);

           resultTemp =  rsGetContactType.getString("creation_page");
           if(rsGetContactType.wasNull()) {
               resultTemp="";
           }
           contactTypePageName.add(resultTemp);


          /*resultTemp =  rsGetContactType.getString("description");
           if(rsGetContactType.wasNull()){
               resultTemp="";
           }
           contactTypeDesc.add(resultTemp);*/

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

//the below method retrives  the  contact type-id, name etc if the script usage or contact type-id etc are given as parameters to the query
    public void collectContactTypeId(){

        try{
        DBconnection dbcon = new DBconnection();
        Connection con = dbcon.getSampleProperty();
        String resultTemp = "";
        String whereClause = " where ";
        String whereCondition = "";
        selectedCntTypeName = "";
        selectedCntTypeId = "";

        Statement statement = con.createStatement();

        String cntTypeSql = "select type_id,type_name,script_usage "
                + " from contacts_type_master";

        if(!cntTypeScriptUsage.equals(""))
        {
            whereCondition += " script_usage = '"+cntTypeScriptUsage+"' ";
        }

        if(!cntTypeId.equals(""))
        {
              if(!cntTypeScriptUsage.equals(""))
              {
                  whereCondition += " and ";
              }
            whereCondition += " contacttype_id = '"+cntTypeId+"' ";
        }

            if(!whereCondition.equals("")){//if any one of the value is passsed then only the below query will be executed or else it will return an empty value

                    cntTypeSql += whereClause+whereCondition;

                    ResultSet rsGetContactType = statement.executeQuery(cntTypeSql);
                    while(rsGetContactType.next()){

                      resultTemp =  rsGetContactType.getString("type_id");
                       if(rsGetContactType.wasNull()) {
                           resultTemp="";
                       }
                       selectedCntTypeId = resultTemp;

                       resultTemp =  rsGetContactType.getString("type_name");
                       if(rsGetContactType.wasNull()) {
                           resultTemp="";
                       }
                      selectedCntTypeName = resultTemp;
                    }
                    rsGetContactType.close();
            }

        statement.close();
        con.close();
        }catch(SQLException sqle){

            System.out.println("SQLException in collecting ContactTypeList:"+sqle);
        }catch(Exception e){

            System.out.println("Exception in collecting ContactTypeList:"+e);
        }

    }









}
