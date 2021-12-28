/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.projects;

import java.io.Serializable;
import connection.DBconnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ramesh
 */
public class SaveProjBookMap implements Serializable {

private String empId="";
private String genericName="";
private String chapterName="";
private String numberedIndex="";
private String estStartPage="";
//private String estEndPage="";
//private String estTotPage="";
//private String pagePrefix="";
//private String estBlanks="";
private String actStartPage="";
private String actEndPage="";
private String actBlanks="";
private String actTotal="";
private int addBookMap=0;
private String castOffChapterChk;
private String proofpageChapterChk;
private String prjid="";
private String addOption="";
private String chapterId="";
private String numberedIndexLetter="";
private String sortOrder="";
private String chapterCast= "";
private String chapterProof= "";
private String castChapterId= "";
private String proofChapterId= "";
private String mssPageCount = "";
private String mssartCount = "";
private String keystrokeCount="";
private String remarks="";


    public SaveProjBookMap() {

    }
public void setEmpId(String empId) {
        this.empId = empId;
}
public void setChapterName(String genericName){
    this.genericName=genericName;
}

public void setNumberedIndex(String numberedIndex){
    this.numberedIndex=numberedIndex;
}

public void setNumberedIndexLetter(String numberedIndexLetter){
    this.numberedIndexLetter=numberedIndexLetter;
}

public String getMssPageCount() {
    return mssPageCount;
}

public void setMssPageCount(String mssPageCount) {
    this.mssPageCount = mssPageCount;
}
public String getMssartCount() {
    return mssartCount;
}

public void setartCount(String mssartCount) {
    this.mssartCount = mssartCount;
}
public void setKeystrokeCount(String keystrokeCount)
    {
    this.keystrokeCount=keystrokeCount;
}

public void setEstStartPage(String estStartPage){
    this.estStartPage=estStartPage;
}

//public void setEstEndPage(String estEndPage){
//    this.estEndPage=estEndPage;
//}
//
//public void setEstTotPage(String estTotPage){
//    this.estTotPage=estTotPage;
//}
//
//public void setPagePrefix(String pagePrefix){
//    this.pagePrefix=pagePrefix;
//}
//
//public void setEstBlanks(String estBlanks){
//    this.estBlanks=estBlanks;
//}

public void setActStartPage(String actStartPage){
    this.actStartPage=actStartPage;
}

public void setActEndPage(String actEndPage){
    this.actEndPage=actEndPage;
}

public void setActBlanks(String actBlanks){
    this.actBlanks=actBlanks;
    //System.out.println("actBlanks:"+actBlanks);
}

public void setActTotal(String actTotal){
    this.actTotal=actTotal;
}

   public void setProjId(String prjid){
         this.prjid=prjid;
        // System.out.println("prjid:"+prjid);
   }


   public void setAddOption(String addOption){
        this.addOption=addOption;
   }


public void setChapterId(String chapterId){
    this.chapterId=chapterId;
}

public int getQueryReturn(){
        return addBookMap;
}

    public String getChapterId() {
        return chapterId;
    }

    public String getChapterName() {
        return chapterName;
    }

     public String getCastOffChapterChk() {
        return castOffChapterChk;
    }

    public String getProofpageChapterChk() {
        return proofpageChapterChk;
    }

    public String getChapterCast() {
        return chapterCast;
    }

    public void setChapterCast(String chapterCast) {
        this.chapterCast = chapterCast;
    }

    public String getChapterProof() {
        return chapterProof;
    }

    public void setChapterProof(String chapterProof) {
        this.chapterProof = chapterProof;
    }

    public String getCastChapterId() {
        return castChapterId;
    }

    public void setCastChapterId(String castChapterId) {
        this.castChapterId = castChapterId;
    }

    public String getProofChapterId() {
        return proofChapterId;
    }

    public void setProofChapterId(String proofChapterId) {
        this.proofChapterId = proofChapterId;
    }

public void setRemarks(String remarks){
    this.remarks = remarks;
}
public String getRemarks(){
    return remarks;
}
public void addProjBookMap(){

//    System.out.println("JAVA >>>>>>>>>>:"+genericName);
//    System.out.println("JAVA ***********:"+estStartPage);
//     System.out.println("JAVA <<<<<<<<<<:"+numberedIndex);
//     System.out.println("JAVA //////////:"+actStartPage);
//     System.out.println("JAVA ++++++++++:"+actEndPage);
//     System.out.println("JAVA ----------:"+actTotal);

    String updateBookMap_Sql=" update project_bookmap set ";
    String updateField="";
    String where = " where chapter_id='"+chapterId+"' ";

    Connection con=null;
        try{
        DBconnection dbcon = new DBconnection();
        con = dbcon.getSampleProperty();


        if(!genericName.equals("SELECT")){
            if(numberedIndex.equals("SELECT")){
                chapterName=genericName;
            }else{
                 if(!numberedIndex.equals("SELECT")){
                chapterName=genericName+" "+numberedIndex;
                }
            }
           if(!numberedIndexLetter.equals("SELECT")){
            chapterName=chapterName+" "+numberedIndexLetter;
            }
        }

        String chkProjId = "";
        int incrSortOrder=0;
        Statement statement = con.createStatement();
        ResultSet rsGetMaxOrder = null;
if(!addOption.equals("Delete")){
//since the below string operations might throw a NullPointerException in case if delete is clicked the IF condition has been used
        rsGetMaxOrder = statement.executeQuery("select max(sort_order) from project_bookmap where proj_id='"+prjid+"' ");
        while(rsGetMaxOrder.next()){
            sortOrder = rsGetMaxOrder.getString(1);
            if(rsGetMaxOrder.wasNull()){
                sortOrder="0";
            }
        }
        rsGetMaxOrder.close();

        incrSortOrder = Integer.parseInt(sortOrder)+1;
        sortOrder=String.valueOf(incrSortOrder);

if(chapterName.equals("")){
             chapterName=null;
         }
         else{
            chapterName=chapterName.trim();
             chapterName="'"+chapterName+"'";
        }



if(!addOption.equals("Add")){
                if(updateField.length()>0){
                updateField += ", chapter_no="+chapterName+" ";
                }
                else{
                 updateField += " chapter_no="+chapterName+" ";
                }
}


        if(estStartPage.equals("")){
        estStartPage=null;
        }else{
        estStartPage =" '"+estStartPage+"' ";
        }
                     if(!addOption.equals("Add")){
                        if(updateField.length()>0){
                        updateField += ", cast_off_pages="+estStartPage+" ";
                        }
                        else{
                         updateField += " cast_off_pages="+estStartPage+" ";
                        }
                     }

        if(mssPageCount.equals("")){
        mssPageCount=null;
        }else{
        mssPageCount =" '"+mssPageCount+"' ";
        }
                     if(!addOption.equals("Add")){
                        if(updateField.length()>0){
                        updateField += ", mss_page_count="+mssPageCount+" ";
                        }
                        else{
                         updateField += " mss_page_count="+mssPageCount+" ";
                        }
                     }

        if(mssartCount.equals("")){
        mssartCount=null;
        }else{
        mssartCount =" '"+mssartCount+"' ";
        }
                     if(!addOption.equals("Add")){
                        if(updateField.length()>0){
                        updateField += ", artcount="+mssartCount+" ";
                        }
                        else{
                         updateField += " artcount="+mssartCount+" ";
                        }
                     }

if(keystrokeCount.equals("")){
        keystrokeCount=null;
        }else{
        keystrokeCount =" '"+keystrokeCount+"' ";
        }
                     if(!addOption.equals("Add")){
                        if(updateField.length()>0){
                        updateField += ", keystroke_count="+keystrokeCount+" ";
                        }
                        else{
                         updateField += " keystroke_count="+keystrokeCount+" ";
                        }
                     }
        //setRemarks

        if(remarks.equals("")){
        remarks=null;
        }else{
        remarks =" '"+remarks+"' ";
        }
                     if(!addOption.equals("Add")){
                        if(updateField.length()>0){
                        updateField += ", remarks="+remarks+" ";
                        }
                        else{
                         updateField += " remarks="+remarks+" ";
                        }
                     }

/*System.out.println("estEndPage:"+estEndPage);
System.out.println("estEndPage:"+estEndPage.length());*/
//        if(estEndPage.equals("")){
//        estEndPage=null;
//        }else{
//        estEndPage =" '"+estEndPage+"' ";
//        }
//                     if(!addOption.equals("Add")){
//                        if(updateField.length()>0){
//                        updateField += ", estimated_end_page="+estEndPage+" ";
//                        }
//                        else{
//                         updateField += " estimated_end_page="+estEndPage+" ";
//                        }
//                     }
//
//
//        if(estTotPage.equals("")){
//        estTotPage=null;
//        }else{
//        estTotPage =" '"+estTotPage+"' ";
//        }
//                     if(!addOption.equals("Add")){
//                        if(updateField.length()>0){
//                        updateField += ", estimated_total_page="+estTotPage+" ";
//                        }
//                        else{
//                         updateField += " estimated_total_page="+estTotPage+" ";
//                        }
//                     }
//
//
//
//
//
//        if(pagePrefix.equals("")){
//        pagePrefix=null;
//        }else{
//        pagePrefix =" '"+pagePrefix+"' ";
//        }
//                     if(!addOption.equals("Add")){
//                        if(updateField.length()>0){
//                        updateField += ", page_prefix="+pagePrefix+" ";
//                        }
//                        else{
//                         updateField += " page_prefix="+pagePrefix+" ";
//                        }
//                     }
//
//
//        //System.out.println("updateBookMap_Sql:"+updateBookMap_Sql);
//        if(estBlanks.equals("")){
//            estBlanks=null;
//        }else{
//        estBlanks =" '"+estBlanks+"' ";
//        }
//                     if(!addOption.equals("Add")){
//                        if(updateField.length()>0){
//                        updateField += ", estimated_blanks="+estBlanks+" ";
//                        }
//                        else{
//                         updateField += " estimated_blanks="+estBlanks+" ";
//                        }
//                     }

if(actStartPage.equals("")){
actStartPage=null;
}else{
actStartPage =" '"+actStartPage+"' ";
}
             if(!addOption.equals("Add")){
                if(updateField.length()>0){
                updateField += ", actual_start_page="+actStartPage+" ";
                }
                else{
                 updateField += " actual_start_page="+actStartPage+" ";
                }
             }



if(actEndPage.equals("")){
actEndPage=null;
}else{
actEndPage =" '"+actEndPage+"' ";
}

             if(!addOption.equals("Add")){
                if(updateField.length()>0){
                updateField += ", actual_end_page="+actEndPage+" ";
                }
                else{
                 updateField += " actual_end_page="+actEndPage+" ";
                }
             }



if(actBlanks.equals("")){
actBlanks=null;
}else{
actBlanks =" '"+actBlanks+"' ";
}
             if(!addOption.equals("Add")){
                if(updateField.length()>0){
                updateField += ", actual_blanks="+actBlanks+" ";
                }
                else{
                 updateField += " actual_blanks="+actBlanks+" ";
                }
             }


if(actTotal.equals("")){
actTotal=null;
}else{
actTotal =" '"+actTotal+"' ";
}
             if(!addOption.equals("Add")){
                if(updateField.length()>0){
                updateField += ", total_pages="+actTotal+" ";
                }
                else{
                 updateField += " total_pages="+actTotal+" ";
                }
             }
             
if(empId.equals("")){
    empId=null;
}else{
    empId = " '"+empId+"' ";
}

    if(!addOption.equals("Add")){
        if(updateField.length()>0){
            updateField += ", modified_date=CURRENT_TIMESTAMP, modified_by="+empId+" ";
        }else{
            updateField += " modified_date=CURRENT_TIMESTAMP, modified_by="+empId+" ";
        }
    }
    
}//close of if(!addOption.equals("Delete"))

        if(addOption.equals("Add")&&!chapterName.equals("\'SELECT\'")){

                  // System.out.println("chapterName:"+chapterName);

         addBookMap = statement.executeUpdate("insert into project_bookmap(proj_id,chapter_no,sort_order,"+
                    " cast_off_pages,actual_start_page,actual_end_page,total_pages,actual_blanks,keystroke_count, mss_page_count, added_date, added_by, remarks, artcount) "+
                    " values ('"+prjid+"',"+chapterName+",'"+sortOrder+"',"+estStartPage+","+actStartPage+","+actEndPage+","+actTotal+","+actBlanks+","+keystrokeCount+","+mssPageCount+",CURRENT_TIMESTAMP,"+empId+","+remarks+","+mssartCount+")");

//System.out.println("Insert Query:"+addBookMap);


        }
        else if(addOption.equals("Modify")&&!chapterName.equals("\'SELECT\'")){
//System.out.println("chapterName:"+chapterName);

        updateBookMap_Sql=updateBookMap_Sql+updateField+where;
System.out.println("updateBookMap_Sql:"+updateBookMap_Sql);
        addBookMap = statement.executeUpdate(updateBookMap_Sql);
        //System.out.println("Update Book Map:"+addBookMap);

            ResultSet rsGetrPrjCastOff, rsGetrPrjActProof=null;

                String tempResult="";

            /***************** Select Query for Estimation Pages from Chapter table *************************/

            String castOffSelectQuery = "SELECT chapter_no, MAX(chapter_id) AS chapter_id FROM chapter WHERE proj_id='"+prjid+"' "
                                        + " AND chapter_no ="+chapterName+" AND stage='CTOF' AND ship_date IS NOT NULL AND chapter_deleted=0";
            //System.out.println("SELECT chapter_no, MAX(chapter_id) AS chapter_id FROM chapter WHERE proj_id='"+prjid+"' "
                                        //+ " AND chapter_no ="+chapterName+" AND stage='CTOF' AND ship_date IS NOT NULL AND chapter_deleted=0");
            rsGetrPrjCastOff=statement.executeQuery(castOffSelectQuery);
            while(rsGetrPrjCastOff.next()){
                    genericName=rsGetrPrjCastOff.getString("chapter_no");
                    if(rsGetrPrjCastOff.wasNull()){
                     genericName="";
                    }
                    //System.out.println("chapterName:  "+genericName);

                    castChapterId=rsGetrPrjCastOff.getString("chapter_id");
                    if(rsGetrPrjCastOff.wasNull()){
                     chapterId="";
                    }
                    //System.out.println("castChapterId:  "+castChapterId);


            }

            /**************** update query for estimation pages in chapter table *********************/

            if(castChapterId != null){
            //if (castChapterId != null || castChapterId.equals("")) {
                //System.out.println("isEmpty:" + (castChapterId != null));

                        //System.out.println("update estimation pages_Sql:" + "UPDATE chapter SET est_pages="+estStartPage+" WHERE proj_id='"+prjid+"' AND chapter_no='"+genericName+"' AND chapter_id='"+castChapterId+"' ");
                        addBookMap = statement.executeUpdate("UPDATE chapter c SET c.est_pages="+estStartPage+" WHERE c.proj_id='"+prjid+"' AND c.chapter_no='"+genericName+"' AND c.chapter_id='"+castChapterId+"' ");
            }

            /***************** Select Query for Proof pages from Chapter table *************************/

            String proofPageSelectQuery = "SELECT chapter_no, MAX(chapter_id) AS chapter_id FROM chapter WHERE proj_id='"+prjid+"' "
                                        + " AND chapter_no ="+chapterName+" AND stage='FP' AND ship_date IS NOT NULL AND chapter_deleted=0 ";

            rsGetrPrjCastOff=statement.executeQuery(proofPageSelectQuery);
            while(rsGetrPrjCastOff.next()){
                    genericName=rsGetrPrjCastOff.getString("chapter_no");
                    if(rsGetrPrjCastOff.wasNull()){
                     genericName="";
                    }
                    //System.out.println("chapterName:  "+genericName);

                    proofChapterId=rsGetrPrjCastOff.getString("chapter_id");
                    if(rsGetrPrjCastOff.wasNull()){
                     chapterId="";
                    }
                    //System.out.println("castChapterId:  "+proofChapterId);


            }
            rsGetrPrjCastOff.close();

            /**************** update query for proof pages in chapter table *********************/
            if(proofChapterId != null){
            //if (proofChapterId != null || proofChapterId.equals("")) {

                        //System.out.println("update proof pages_Sql:" + "UPDATE chapter SET proof_page="+actTotal+" WHERE proj_id='"+prjid+"' AND chapter_no='"+genericName+"' AND chapter_id='"+proofChapterId+"'");
                        addBookMap = statement.executeUpdate("UPDATE chapter c SET c.proof_page="+actTotal+" WHERE c.proj_id='"+prjid+"' AND c.chapter_no='"+genericName+"' AND chapter_id='"+proofChapterId+"'");

            }
     //System.out.println(addBookMap);
        }
        else if(addOption.equals("Delete")){
            updateBookMap_Sql="delete from project_bookmap "+where;
            //System.out.println("updateBookMap_Sql:"+updateBookMap_Sql);
            addBookMap = statement.executeUpdate(updateBookMap_Sql);
        }

        //if(!addOption.equals("Delete")){
            //System.out.println("test");
            if(addBookMap>0){
                    String getTotalPageCount = "0";
                    ResultSet rsGetPageCount = statement.executeQuery("select sum(total_pages) from project_bookmap where proj_id='"+prjid+"' ");
                    while(rsGetPageCount.next()){
                        getTotalPageCount = rsGetPageCount.getString(1);
                        if(rsGetPageCount.wasNull()){
                            getTotalPageCount = "0";
                        }
                    }
                    rsGetPageCount.close();
                    statement.executeUpdate("update projects set actual_pages='"+getTotalPageCount+"' where proj_id='"+prjid+"' ");


            }

     //}
statement.close();
        }catch(SQLException sqle){
            System.out.println("SQLException in Saving Proj BookMap:"+sqle);
        }catch(Exception e){
          //  System.out.println("Exception in Saving Proj BookMap:"+e);
            e.printStackTrace();
        }finally{
            try{
              con.close();
            }catch(SQLException e){
            System.out.println(e);
            }
        }
}
}
