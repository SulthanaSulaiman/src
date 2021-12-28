/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.projects;

import java.io.Serializable;
import connection.DBconnection;
import java.sql.*;
import java.util.*;
import pathfinder.functions.generic.*;
/**
 *
 * @author ramesh
 */
public class SaveProjProps implements Serializable {

   
    private String prjid="";
    private String propId="";
    private String propValue="";
    private String propertyType="";
    private String propertyTypeIndx="";
    private String propertyTypePcn_Cip="";
    private String ftpServerID="";
    private String cmsServerID="";
    private String sesEmpId="";

    private List log_table_name = new ArrayList();
    private List log_field_name = new ArrayList();
    private List log_linked_field_name = new ArrayList();
    private List log_linked_field_value = new ArrayList();
    private List log_reference_table = new ArrayList();
    private List log_reference_field= new ArrayList();
    private List log_reference_value= new ArrayList();
    private List log_old_value = new ArrayList();
    private List log_new_value = new ArrayList();
    private List log_changed_by = new ArrayList();

    private int addPropValue=0;

    public SaveProjProps() {
        
    }

    public void setPropId(String propId){
        this.propId=propId;
    }

    public void setPropValue(String propValue){
        this.propValue=propValue;
    }

   public void setProjId(String prjid){
        this.prjid=prjid;
     //   System.out.println("getProjId:"+prjid);
   }

   public void setPropertyType(String propertyType){
       this.propertyType=propertyType;
   }

   public void setPropertyTypeIndx(String propertyTypeIndx){
       this.propertyTypeIndx=propertyTypeIndx;
   }
    public void setPropertyTypePcn_Cip(String propertyTypePcn_Cip){
       this.propertyTypePcn_Cip=propertyTypePcn_Cip;
   }
//addPropValue

   public void setFtpServer(String ftpServerID){
       this.ftpServerID=ftpServerID;
   }

   public void setCmsServer(String cmsServerID){
       this.cmsServerID=cmsServerID;
   }

   public void setEmpId(String sesEmpId){
       this.sesEmpId=sesEmpId;
   }


   public int getPropAdded(){
        return addPropValue;
   }

public void addProjProperty(){

Connection con=null;
String logOption="";
int log_Option=0;//integer equivalent of logOption
try{

            //System.out.println("getProjId in method:"+prjid);
        DBconnection dbcon = new DBconnection();
        con = dbcon.getSampleProperty();
        String savePropertySQl="";
        String queryPropertyType="";       
        Statement statement = con.createStatement();
        
        //ususally the query to retrieve the log option will have the module name hard coded to retireve the logging option as whether
        //it has been turnd on or off.
        //since this class handles three different properties that has to be saved in the same table the below if else condition has been used o determine the looging option fo the repective module.
        if(propertyType.equals("Paging"))
        {
            queryPropertyType="paging_properties";
        }
        else if(propertyType.equals("Art")){
            queryPropertyType="art_properties";
        }
        else if(propertyType.equals("Editorial")){
            queryPropertyType="editorial_properties";
        }
 else if(propertyType.equals("Indexing")){
            queryPropertyType="indexing_properties";
        }
        else if(propertyType.equals("PCN/CIP")){
            queryPropertyType="Pcn_Cip_properties";
        }
 else if(propertyType.equals("Cover")){
            queryPropertyType="Cover_properties";
        }

        ResultSet rsLogOption = statement.executeQuery("select log_option from dblog_preference where module_name='"+queryPropertyType+"' ");
        while(rsLogOption.next()){
            log_Option=rsLogOption.getInt("log_option");
        }
        rsLogOption.close();
        //check the value if the value is already present and there is a value other than "" or "All" then the query should be update query.
 //           If the value is all or "" then the query should be delete query
String checkValue="";
            ResultSet rscheckValue = statement.executeQuery("select value from project_properties where proj_id='"+prjid+"' and property_id='"+propId+"' ");
            while(rscheckValue.next()){
                checkValue=rscheckValue.getString(1);
            }
rscheckValue.close();
        //the below block checks for the property ID as it should start from 106000 and starts adding the project ID from that
        //Generate Project ID
               
       // System.out.println("insert into contacts(firstname,surname) values ('"+firstName+"','"+surName+"')  ");
 if(propValue.equals("checked")){
             if(!propValue.equals("")&&!propValue.equals("All")&&!propValue.equals("Remove")&&!checkValue.equals("")){
                     savePropertySQl="update project_properties set value ='True' "+
                    " where proj_id='"+prjid+"' and property_id='"+propId+"'   ";
                }
             else{
                          savePropertySQl="insert into project_properties(proj_id,property_id,value) "+
                    " values ('"+prjid+"','"+propId+"','True' )  ";
             }
 } 
 else if(propValue.equals("unchecked")){
         if(!propValue.equals("")&&!propValue.equals("All")&&!propValue.equals("Remove")&&!checkValue.equals("")){
                     savePropertySQl="update project_properties set value ='False' "+
                    " where proj_id='"+prjid+"' and property_id='"+propId+"'   ";
            }else{
                      savePropertySQl="insert into project_properties(proj_id,property_id,value) "+
                    " values ('"+prjid+"','"+propId+"','False' )  ";
            }
 }
 else{

//If the value is present then the below if block updates or removeds it from DB
//the below else part inserts the record into table
        if(!checkValue.equals("")){
            if(!propValue.equals("")&&!propValue.equals("All")&&!propValue.equals("Remove")){
                    if(!checkValue.equals(propValue)){
                     savePropertySQl="update project_properties set value ='"+propValue+"' "+
                    " where proj_id='"+prjid+"' and property_id='"+propId+"'   ";
                    
                   if(log_Option==1){//if the log option is set
                       /*log_table_name.add("project_properties");
                       log_field_name.add("value");
                       log_linked_field_name.add("");
                       log_old_value.add(checkValue);
                       log_new_value.add(propValue);
                       log_changed_by.add(sesEmp);
                       log_reference_table.add("project_properties_master");
                       log_reference_field.add("property_id");*/

                    if(!checkValue.equals(propValue)){
                                    DBLog dbLog = new DBLog();
                                    dbLog.setTableName("project_properties");
                                    dbLog.setFieldName("property_id");
                                    dbLog.setLinkFieldName("proj_id");
                                    dbLog.setLinkFieldValue(prjid);
                                    dbLog.setNewValue(propValue);
                                    dbLog.setOldValue(checkValue);
                                    dbLog.setRefField("property_id");
                                    dbLog.setRefTable("project_properties_master");
                                    dbLog.setRefFieldValue(propId);
                                    dbLog.setChangedBy(sesEmpId);
                                    dbLog.createLog();
                             }

                    }

                    }else{
                        savePropertySQl="";
                    }

            }
            else{
                if(propValue.equals("Remove")){
                     savePropertySQl="delete from project_properties "+
                    " where proj_id='"+prjid+"' and property_id='"+propId+"'   ";
                }
                
            }
        }
        else{
                 if(!propValue.equals("")&&!propValue.equals("All")&&!propValue.equals("Remove")){
                     savePropertySQl="insert into project_properties(proj_id,property_id,value) "+
                    " values ('"+prjid+"','"+propId+"','"+propValue+"' )  ";
                 }
        }


 }
//System.out.println("propValue:"+propValue);
//System.out.println("savePropertySQl:"+savePropertySQl);
if(!savePropertySQl.equals("")){
 addPropValue = statement.executeUpdate(savePropertySQl);
}
//System.out.println("addPropValue:"+addPropValue);
    
       statement.close();
//System.out.println("addContact:"+addContact);
        }catch(SQLException sqle){
            System.out.println("SQLException in adding Proj Props:"+sqle);
        }catch(Exception e){
            System.out.println("Exception in adding Proj Props:"+e);
        }finally{
            try{
              con.close();
            }catch(SQLException e){
            System.out.println(e);
            }

        }
             
}


public void saveFtpServer(){
    Connection con=null;
        try{

            //System.out.println("getProjId in method:"+prjid);
        DBconnection dbcon = new DBconnection();
        con = dbcon.getSampleProperty();
        Statement statement = con.createStatement();

         addPropValue = statement.executeUpdate(" update projects set ftp_serverid='"+ftpServerID+"' where proj_id='"+prjid+"' ");

statement.close();
          }catch(SQLException sqle){
            System.out.println("SQLException in saving Proj FTP Server:"+sqle);
        }catch(Exception e){
            System.out.println("Exception in creating Proj FTP Server:"+e);
        }finally{
            try{
              con.close();
            }catch(SQLException e){
            System.out.println(e);
            }

        }
}


public void saveCmsServer(){
    Connection con=null;
        try{

            //System.out.println("getProjId in method:"+prjid);
        DBconnection dbcon = new DBconnection();
        con = dbcon.getSampleProperty();
        Statement statement = con.createStatement();

        //System.out.println(" update projects set cms_serverid='"+cmsServerID+"' where proj_id='"+prjid+"' ");
          addPropValue = statement.executeUpdate(" update projects set cms_serverid='"+cmsServerID+"' where proj_id='"+prjid+"' ");

statement.close();
          }catch(SQLException sqle){
            System.out.println("SQLException in saving Proj FTP Server:"+sqle);
        }catch(Exception e){
            System.out.println("Exception in creating Proj FTP Server:"+e);
        }finally{
            try{
              con.close();
            }catch(SQLException e){
            System.out.println(e);
            }

        }
}

}
