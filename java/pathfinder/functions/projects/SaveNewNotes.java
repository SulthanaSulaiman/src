/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.projects;



import java.io.Serializable;
import connection.DBconnection;
import java.sql.*;
import java.util.*;
/**
 *
 * @author ramesh
 */
public class SaveNewNotes implements Serializable {
   
    private String note_category="";
    private String prjid="";
    private String cntid="";
    private String content="";
    private String emp_id="";
    private String created_at="";
    private String createdEmpId="";

         private String   noteID="";
         private String   createdEmp="";
         private String   noteCategory="";
         private String   noteTime="";
         private String   noteContent="";
         private String   notesType="";

         private String editNotesId="";
         private String editNotesContent="";

        private List noteIdList = new ArrayList();
        private List noteCategoryList = new ArrayList();
        private List contentList = new ArrayList();
        private List createdByList = new ArrayList();
        private List createdTimeList = new ArrayList();
        private List createdEmpIdList = new ArrayList();
       // private List noteId = new ArrayList();


    private int addNotes=0;


    public SaveNewNotes() {
        
    }


    public void setEditNotesId(String editNotesId){
        this.editNotesId=editNotesId;
    }

   public void setEditNotesContent(String editNotesContent){
       this.editNotesContent=editNotesContent;
   }
   
     public void setNotesCategory(String note_category){
        this.note_category=note_category;
    }

   public void setProjId(String prjid){
       this.prjid=prjid;
   }

  public void setContactId(String cntid){
       this.cntid=cntid;
   }

   public void setNotesContent(String content){
        this.content=content;
        
    }

   public void setNotesType(String notesType){
        this.notesType=notesType;
    }

   public void setEmpId(String emp_id){
       this.emp_id=emp_id;
   }
   public int getNotesAdded(){
        return addNotes;
   }


   public String getNotesId(){
       return noteID;
   }

   public String getNotesCategory(){
       return noteCategory;
   }

  public String getNotesContent(){
       return noteContent;
   }

   public String getCreatedBy(){
       return createdEmp;
   }

   public String getCreatedById(){
       return createdEmpId;
   }

      public String getCreatedDate(){
       return noteTime;
   }

   public List getNotesIdList(){
       return noteIdList;
   }

   /*public List getNotesCategoryList(){
       return noteCategory;
   }*/

  public List getNotesContentList(){
       return contentList;
   }

   public List getCreatedByList(){
       return createdByList;
   }

   public List getCreatedDateList(){
       return createdTimeList;
   }


   public List getCreatedByIdList(){
       return createdEmpIdList;
   }


       public String encodeSpecialCharacter(String text){

     int len= text.length();
     StringBuffer encodedString=new StringBuffer();

      for(int m=0;m<len;m++)
        {
          char c=text.charAt(m);

          if(c=='\'')
               encodedString.append("\\\'");
          /*else if(c=='>')
            encodedQuestion.append("&gt;");
          else if(c=='&')
             encodedQuestion.append("&amp;");
          else if(c=='"')
            encodedQuestion.append("&quot;");
          else if(c=='\n')
             encodedQuestion.append("<br>");*/
          else
             encodedString.append(c);

         }
               return encodedString.toString();
  }

public void deleteNotes(){

Connection con=null;
        try{

        DBconnection dbcon = new DBconnection();
        con = dbcon.getSampleProperty();
        Statement statement = con.createStatement();


      // if(chkProjId.equals("")){
      if(notesType.equals("Contacts")){
            //System.out.println("delete from contact_notes where note_id= '"+editNotesId+"' ");
             addNotes = statement.executeUpdate("delete from contact_notes where note_id= '"+editNotesId+"' ");

        }else{
         /*   System.out.println("insert into notes(note_category,content,created_by,created_at,proj_id) "+
                    " values ('"+note_category+"','"+content+"','"+emp_id+"',CURRENT_TIMESTAMP(),'"+prjid+"' )  ");
          */

           // System.out.println("delete from notes where note_id= '"+editNotesId+"' ");
           addNotes = statement.executeUpdate("delete from notes where note_id= '"+editNotesId+"' ");
     }
       statement.close();
        }catch(SQLException sqle){
            System.out.println("SQLException in Adding Notes:"+sqle);
        }catch(Exception e){
            System.out.println("Exception in Adding Notes:"+e);
        }finally{
            try{
              con.close();
            }catch(SQLException e){
            System.out.println(e);
            }

        }
}

public void modifyNotes(){

Connection con=null;
        try{
        editNotesContent=encodeSpecialCharacter(editNotesContent);
        //System.out.println("editNotesContent:"+editNotesContent);
        DBconnection dbcon = new DBconnection();
        con = dbcon.getSampleProperty();
        Statement statement = con.createStatement();

     
      // if(chkProjId.equals("")){
      if(notesType.equals("Contacts")){
            //  System.out.println("update contact_notes set content='"+content.trim()+"',created_by='"+emp_id+"',created_at=CURRENT_TIMESTAMP() where note_id= '"+editNotesId+"' ");
             addNotes = statement.executeUpdate("update contact_notes set content='"+editNotesContent+"',created_by='"+emp_id+"',created_at=CURRENT_TIMESTAMP() where note_id= '"+editNotesId+"' ");

        }else{
         /*   System.out.println("insert into notes(note_category,content,created_by,created_at,proj_id) "+
                    " values ('"+note_category+"','"+content+"','"+emp_id+"',CURRENT_TIMESTAMP(),'"+prjid+"' )  ");
          */
           addNotes = statement.executeUpdate("update notes set content='"+editNotesContent+"',created_by='"+emp_id+"',created_at=CURRENT_TIMESTAMP() where note_id= '"+editNotesId+"' ");
     }
        //System.out.println("update contact_notes set content='"+editNotesContent+"',created_by='"+emp_id+"',created_at=CURRENT_TIMESTAMP() where note_id= '"+editNotesId+"' ");

 statement.close();
//System.out.println("addContact:"+addContact);
        }catch(SQLException sqle){
            System.out.println("SQLException in Adding Notes:"+sqle);
        }catch(Exception e){
            System.out.println("Exception in Adding Notes:"+e);
        }finally{
            try{
              con.close();
            }catch(SQLException e){
            System.out.println(e);
            }

        }
}


public void addNotes(){

Connection con=null;
        try{
             content=encodeSpecialCharacter(content);
             //System.out.println("content:"+content);
        DBconnection dbcon = new DBconnection();
        con = dbcon.getSampleProperty();      
        Statement statement = con.createStatement();

      
//System.out.println("In Add Notes");
        if(notesType.equals("Contacts")){

            /*  System.out.println("insert into contact_notes(note_category,content,created_by,created_at,contact_id) "+
                    " values ('"+note_category+"','"+content.trim()+"','"+emp_id+"',CURRENT_TIMESTAMP(),'"+cntid+"' )  ");
            */
              addNotes = statement.executeUpdate("insert into contact_notes(note_category,content,created_by,created_at,contact_id) "+
                    " values ('"+note_category+"','"+content.trim()+"','"+emp_id+"',CURRENT_TIMESTAMP(),'"+cntid+"' )  ");
      
        }else{
            /*  System.out.println("insert into notes(note_category,content,created_by,created_at,proj_id) "+
                    " values ('"+note_category+"','"+content.trim()+"','"+emp_id+"',CURRENT_TIMESTAMP(),'"+prjid+"' )  ");
             */

            /*System.out.println("insert into notes(note_category,content,created_by,created_at,proj_id) "+
                    " values ('"+note_category+"','"+content.trim()+"','"+emp_id+"',CURRENT_TIMESTAMP(),'"+prjid+"' )  ");
                    */
              addNotes = statement.executeUpdate("insert into notes(note_category,content,created_by,created_at,proj_id) "+
                    " values ('"+note_category+"','"+content.trim()+"','"+emp_id+"',CURRENT_TIMESTAMP(),'"+prjid+"' )  ");
        }
     
       statement.close();
//System.out.println("addContact:"+addContact);
        }catch(SQLException sqle){
            System.out.println("SQLException in Adding Notes:"+sqle);
        }catch(Exception e){
            System.out.println("Exception in Adding Notes:"+e);
        }finally{
            try{
              con.close();
            }catch(SQLException e){
            System.out.println(e);
            }
        }
}


public void displayNotes(){
Connection con=null;
        try{
        DBconnection dbcon = new DBconnection();
        con = dbcon.getSampleProperty();

        String chkProjId = "";
        Statement statement = con.createStatement();

        String displayNotes_Sql = "";

         if(notesType.equals("Contacts")){
              displayNotes_Sql = "select nt.note_id,ntc.name,nt.content,u.emp_name,"
                      + "date_format(nt.created_at,'%d-%b-%Y %H:%i %p') as addedTime,nt.created_by "+
                                   "from contact_notes nt,notes_category ntc,user u where "+
                                   " nt.note_category=ntc.category_id and nt.created_by=u.emp_id and nt.contact_id='"+cntid+"' ";
         }
         else{
             displayNotes_Sql = "select nt.note_id,ntc.name,nt.content,u.emp_name,"
                     + "date_format(nt.created_at,'%d-%b-%Y %H:%i %p') as addedTime,nt.created_by "+
                                   "from notes nt,notes_category ntc,user u where "+
                                   "nt.note_category=ntc.category_id and nt.created_by=u.emp_id and nt.proj_id='"+prjid+"' and nt.note_category='"+note_category+"' ";
        }

        //System.out.println("displayNotes_Sql:"+displayNotes_Sql);

       ResultSet rsGetNotes = statement.executeQuery(displayNotes_Sql);
       while(rsGetNotes.next()){
           noteID=rsGetNotes.getString("nt.note_id");
           createdEmp=rsGetNotes.getString("u.emp_name");
           noteCategory=rsGetNotes.getString("ntc.name");
           noteTime=rsGetNotes.getString("addedTime");
           noteContent=rsGetNotes.getString("nt.content");
           createdEmpId=rsGetNotes.getString("nt.created_by");

           noteIdList.add(noteID);
           createdByList.add(createdEmp);
           createdTimeList.add(noteTime);
           contentList.add(noteContent.trim());
           createdEmpIdList.add(createdEmpId);
       }
       rsGetNotes.close();
       statement.close();
      
        }catch(SQLException sqle){
            System.out.println("SQLException in Adding Notes:"+sqle);
        }catch(Exception e){
            System.out.println("Exception in Adding Notes:"+e);
        }finally{
            try{
              con.close();
            }catch(SQLException e){
            System.out.println(e);
            }
        }
}


}
