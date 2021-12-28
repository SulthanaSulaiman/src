/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.projects;

import java.io.Serializable;
import connection.DBconnection;
import java.sql.*;
/**
 *
 * @author ramesh
 */
public class SaveProjAuthors implements Serializable {

   
    private String prjid="";
    private String authorId="";
    private String primaryChecked="";
    private String saveType="";
    private String queryResult="";
    private String rmvAuthorId="";
    private String rmvPrjid="";
    private int addAuthor=0;
    private String priAuthMapped="";

    public SaveProjAuthors() {
        
    }

    public void setAuthorId(String authorId){
        this.authorId=authorId;
    }

    public void setPimaryChecked(String primaryChecked){
        this.primaryChecked=primaryChecked;
    }

   public void setProjId(String prjid){
        this.prjid=prjid;
       // System.out.println("getProjId:"+prjid);
   }

  public void setRemoveAuthorId(String rmvAuthorId){
        this.rmvAuthorId=rmvAuthorId;
       // System.out.println("getProjId:"+prjid);
   }

   public void setRemoveProjId(String rmvPrjid){
        this.rmvPrjid=rmvPrjid;
       // System.out.println("getProjId:"+prjid);
   }




   public int getQueryReturn(){
        return addAuthor;
   }

   public String getQueryResult(){
        return queryResult;
   }

public void deleteAuthorId(){
    Connection con=null;
try{
            //System.out.println("getProjId in method:"+prjid);
        DBconnection dbcon = new DBconnection();
        con = dbcon.getSampleProperty();
        Statement statement = con.createStatement();
        addAuthor=statement.executeUpdate("delete from project_authors where proj_id='"+rmvPrjid+"' and author_id='"+rmvAuthorId+"' ");

        if(addAuthor>0){
            queryResult="The selected author(s) have been removed successfully";
        }
statement.close();
        }
catch(SQLException sqle){
            String chkException = sqle.getMessage();
           /* if(chkException.startsWith("Duplicate entry")){
             System.out.println("SQLException in adding Proj Authors:"+sqle.getMessage());
                queryResult="Already the selected author has been mapped to the project";
              }*/
}
catch(Exception e){
            System.out.println("Exception in removing Proj Author:"+e);
        }finally{
            try{
              con.close();
            }catch(SQLException e){
            System.out.println(e);
            }
        }
}


public void addProjAuthor(){

Connection con=null;
try{
            //System.out.println("getProjId in method:"+prjid);
        DBconnection dbcon = new DBconnection();
        con = dbcon.getSampleProperty();
        String saveAuthorSQl="";
        String  chkPrimaryAuthor="";
        String confirmRecordAddition="0";//based on this variable the query will be executed or else a return alert message will be executed


        Statement statement = con.createStatement();

        ResultSet rsCheckPrimaryAuthor = statement.executeQuery("select author_id from project_authors where proj_id='"+prjid+"' and primary_author='1' ");
        while(rsCheckPrimaryAuthor.next()){
            chkPrimaryAuthor=rsCheckPrimaryAuthor.getString("author_id");
        }

        //System.out.println("chkPrimaryAuthor-SQL"+"select author_id from project_authors where proj_id='"+prjid+"' ");
        //System.out.println("chkPrimaryAuthor:"+chkPrimaryAuthor);

        //If Primary author is not yet mapped to the project then the primary author query will be executed or else the query return result will be zero


if(!authorId.equals("")){//to avoid Empty author getting added
        if(primaryChecked.equals("1")){
                    if(chkPrimaryAuthor.equals("")){
                    saveAuthorSQl="insert into project_authors(proj_id,author_id,primary_author) "+
                    " values ('"+prjid+"','"+authorId+"','"+primaryChecked+"' )  ";
                    confirmRecordAddition="1";
                    }
                    else{
                        queryResult="Primary Author has been already mapped to the project";
                    }

        }
        else{
            saveAuthorSQl="insert into project_authors(proj_id,author_id) "+
                    " values ('"+prjid+"','"+authorId+"' )  ";
            confirmRecordAddition="1";
        }
}
else{
    queryResult="Please select an author";
}

        //System.out.println("saveAuthorSQl:"+saveAuthorSQl);
      if(confirmRecordAddition.equals("1")){
        addAuthor = statement.executeUpdate(saveAuthorSQl);
        if(addAuthor>0){
            queryResult="Author has been added successfully";
        }
      }

       rsCheckPrimaryAuthor.close();
       statement.close();
//System.out.println("addContact:"+addContact);
        }catch(SQLException sqle){
            String chkException = sqle.getMessage();
            if(chkException.startsWith("Duplicate entry")){
             System.out.println("SQLException in adding Proj Authors:"+sqle.getMessage());
                queryResult="Already the selected author has been mapped to the project";
              }
        }catch(Exception e){
            System.out.println("Exception in adding Proj Authors:"+e);
        }finally{
            try{
              con.close();
            }catch(SQLException e){
            System.out.println(e);
            }
          
        }
               
prjid="";
  
//System.out.println("queryResult:"+queryResult);
//System.out.println("addAuthor:"+addAuthor);

}



}
