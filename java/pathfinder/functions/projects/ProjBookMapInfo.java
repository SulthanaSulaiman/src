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
public class ProjBookMapInfo implements Serializable {

private List genericName =new ArrayList();
private List remarks =new ArrayList();
private List chapterId =new ArrayList();
private List chapterName =new ArrayList();
private List numberedIndex =new ArrayList();
private List estStartPage =new ArrayList();
private List estEndPage =new ArrayList();
private List estTotPage =new ArrayList();
private List pagePrefix =new ArrayList();
private List estBlanks =new ArrayList();
private List actStartPage= new ArrayList();
private List actEndPage =new ArrayList();
private List actBlanks= new ArrayList();
private List actTotal= new ArrayList();
private List actProofTotal= new ArrayList();
private List castOffPages=new ArrayList();
private List mssPageCount=new ArrayList();
private List artCount=new ArrayList();
private List keystrokeCount=new ArrayList();
private int addBookMap=0;
private String prjid="";
private String castChapterName="";


    public ProjBookMapInfo() {

    }

    public List getActProofTotal() {
        return actProofTotal;
    }

    public void setActProofTotal(List actProofTotal) {
        this.actProofTotal = actProofTotal;
    }

    public List getCastOffPages() {
        return castOffPages;
    }

    public void setCastOffPages(List caastOffPages) {
        this.castOffPages = caastOffPages;
    }
public List getChapterName(){
    return genericName;
}
public List getRemarksdisp(){
    return remarks;
}


    public void setGenericName(List genericName) {
        this.genericName = genericName;
    }

public List getNumberedIndex(){
    return numberedIndex;
}

public List getEstStartPage(){
    return estStartPage;
}

public List getEstEndPage(){
    return estEndPage;
}

public List getEstTotPage(){
    return estTotPage;
}

public List getPagePrefix(){
    return pagePrefix;
}

public List getEstBlanks(){
    return estBlanks;
}

public List getActStartPage(){
    return actStartPage;
}

public List getActEndPage(){
    return actEndPage;
}

public List getActBlanks(){
    return actBlanks;
}

public List getActTotal(){
    return actTotal;
}

public List getChapterId(){
    return chapterId;
}

public List getMssPageCount() {
    return mssPageCount;
}
public List getartCount() {
    return artCount;
}
public void setartCount(List artCount) {
    this.artCount=artCount;
}
//artCount
public List getKeystrokeCount(){
    return keystrokeCount;
}


   public void setProjId(String prjid){
         this.prjid=prjid;
        // System.out.println("prjid:"+prjid);
   }

    public void setCastChapterName(String castChapterName) {
        this.castChapterName = castChapterName;
    }

public void collectProjectBookMap(){

Connection con=null;
        try{
        DBconnection dbcon = new DBconnection();
        con = dbcon.getSampleProperty();
        ResultSet rsGetrPrjBkMap=null;
        String tempResult="";
        Statement statement = con.createStatement();


        String projBookMapSQL = "SELECT DISTINCT(chapter_no), chapter_id, actual_start_page, actual_end_page, actual_blanks, cast_off_pages, total_pages, keystroke_count, mss_page_count, remarks, artcount "
                                + " FROM project_bookmap WHERE proj_id='"+prjid+"' order by sort_order";

//        System.out.println("Select Query: "+ "SELECT DISTINCT(chapter_no), chapter_id, actual_start_page, actual_end_page, actual_blanks, cast_off_pages, total_pages "
//                                + " FROM project_bookmap WHERE proj_id='"+prjid+"'");


        //the below block is for retreiving the Book Map information for the projectId passed

                rsGetrPrjBkMap=statement.executeQuery(projBookMapSQL);
                while(rsGetrPrjBkMap.next()){
                    tempResult=rsGetrPrjBkMap.getString("chapter_no");
                    if(rsGetrPrjBkMap.wasNull()){
                     tempResult="";
                    }
                    genericName.add(tempResult);

                    tempResult=rsGetrPrjBkMap.getString("chapter_id");
                    if(rsGetrPrjBkMap.wasNull()){
                     tempResult="";
                    }
                    chapterId.add(tempResult);

//                    tempResult=rsGetrPrjBkMap.getString("estimated_start_page");
//                    if(rsGetrPrjBkMap.wasNull()){
//                        tempResult="";
//                    }
//                    estStartPage.add(tempResult);

//                    tempResult=rsGetrPrjBkMap.getString("estimated_end_page");
//                    if(rsGetrPrjBkMap.wasNull()){
//                        tempResult="";
//                    }
//                    estEndPage.add(tempResult);
//
//                    //estTotPage
//                   tempResult=rsGetrPrjBkMap.getString("estimated_total_page");
//                    if(rsGetrPrjBkMap.wasNull()){
//                        tempResult="";
//                    }
//                    estTotPage.add(tempResult);
//
//
//
//                    tempResult=rsGetrPrjBkMap.getString("page_prefix");
//                    if(rsGetrPrjBkMap.wasNull()){
//                        tempResult="";
//                    }
//                   pagePrefix.add(tempResult);
//
//                    tempResult=rsGetrPrjBkMap.getString("estimated_blanks");
//                    if(rsGetrPrjBkMap.wasNull()){
//                     tempResult="";
//                    }
//                    estBlanks.add(tempResult);

                    tempResult=rsGetrPrjBkMap.getString("actual_start_page");
                    if(rsGetrPrjBkMap.wasNull()){
                     tempResult="";
                    }
                    actStartPage.add(tempResult);//

                     tempResult=rsGetrPrjBkMap.getString("actual_end_page");
                    if(rsGetrPrjBkMap.wasNull()){
                     tempResult="";
                    }
                    actEndPage.add(tempResult);

                    tempResult=rsGetrPrjBkMap.getString("actual_blanks");
                    if(rsGetrPrjBkMap.wasNull()){
                     tempResult="";
                    }
                    actBlanks.add(tempResult);

                    tempResult=rsGetrPrjBkMap.getString(7);
                    if(rsGetrPrjBkMap.wasNull()){
                     tempResult="";
                    }
                    actTotal.add(tempResult);

                    tempResult=rsGetrPrjBkMap.getString(6);
                    if(rsGetrPrjBkMap.wasNull()){
                        tempResult="";
                    }
                    estStartPage.add(tempResult);

                    tempResult=rsGetrPrjBkMap.getString(8);
                    if(rsGetrPrjBkMap.wasNull()){
                        tempResult="";
                    }
                    keystrokeCount.add(tempResult);

                    tempResult=rsGetrPrjBkMap.getString(9);
                    if(rsGetrPrjBkMap.wasNull()){
                        tempResult="";
                    }
                    mssPageCount.add(tempResult);
                    tempResult=rsGetrPrjBkMap.getString(10);
                    if(rsGetrPrjBkMap.wasNull()){
                        tempResult="";
                    }
                    remarks.add(tempResult);
                    tempResult=rsGetrPrjBkMap.getString(11);
                    if(rsGetrPrjBkMap.wasNull()){
                        tempResult="";
                    }
                    artCount.add(tempResult);
            }
                rsGetrPrjBkMap.close();
                statement.close();


        }catch(SQLException sqle){
            System.out.println("SQLException in ProjIdAttribs:"+sqle);
        }catch(Exception e){
            System.out.println("Exception in ProjIdAttribs:"+e);
        }finally{
            try{
              con.close();
            }catch(SQLException e){
            System.out.println(e);
            }

        }


}
public void collectActualProofPages(){
Connection con=null;
        try{
        DBconnection dbcon = new DBconnection();
        con = dbcon.getSampleProperty();
        ResultSet rsGetrPrjActProof=null;
        String tempResult="";
        Statement statement = con.createStatement();
        String projBookMapProofPageSQL;
        String inString = "";

//System.out.println("Generic Name Proof pages:"+castChapterName);
//for (int i=0;i<genericName.size();i++)
//{
//               if(i==0)
//                    inString = inString + "'" +(String)genericName.get(i) + "'";
//                else
//                    inString = inString + ",'" + (String)genericName.get(i) + "'";


        projBookMapProofPageSQL = "SELECT chapter_no, MAX(chapter_id) as chapter_id, proof_page FROM chapter WHERE proj_id='"+prjid+"'"
                                +" AND chapter_no = '"+castChapterName+"' AND stage='FP' AND ship_date IS NOT NULL AND chapter_deleted=0 ";
//        System.out.println(castChapterName);
//        System.out.println("Select Query for Proof pages: "+ "SELECT chapter_no, MAX(chapter_id),proof_page FROM chapter WHERE proj_id='"+prjid+"'"
//                                +" AND chapter_no = '"+castChapterName+"' AND stage='FP' AND ship_date IS NOT NULL AND chapter_deleted=0 ");

                rsGetrPrjActProof=statement.executeQuery(projBookMapProofPageSQL);
                while(rsGetrPrjActProof.next()){
                    tempResult=rsGetrPrjActProof.getString("chapter_no");
                    if(rsGetrPrjActProof.wasNull()){
                     tempResult="";
                    }
                    genericName.add(tempResult);


                    tempResult=rsGetrPrjActProof.getString("chapter_id");
                    if(rsGetrPrjActProof.wasNull()){
                     tempResult="";
                    }
                    chapterId.add(tempResult);

                    tempResult=rsGetrPrjActProof.getString("proof_page");
                    if(rsGetrPrjActProof.wasNull()){
                     tempResult="";
                    }
                    actProofTotal.add(tempResult);//
            }
                rsGetrPrjActProof.close();
                statement.close();
//            }
        }catch(SQLException sqle){
            System.out.println("SQLException in Proof ProjIdAttribs:"+sqle);
        }catch(Exception e){
            System.out.println("Exception in Proof ProjIdAttribs:"+e);
        }finally{
            try{
              con.close();
            }catch(SQLException e){
            System.out.println(e);
            }
        }
}

public void collectCastOffPages(){
Connection con=null;
        try{
        DBconnection dbcon = new DBconnection();
        con = dbcon.getSampleProperty();
        ResultSet rsGetrPrjCastOff=null;
        String tempResult="";
        Statement statement = con.createStatement();
        String inString = "";
 //       System.out.println("Generic Name Cast-Off Pages:"+castChapterName);
//        for (int i=0;i<genericName.size();i++)
//        {
//                if(i==0)
//                    inString = inString + "'" +(String)genericName.get(i) + "'";
//                else
//                    inString = inString + ",'" + (String)genericName.get(i) + "'";
        String projBookMapSQL = "SELECT chapter_no, MAX(chapter_id) as chapter_id, est_pages FROM chapter WHERE proj_id='"+prjid+"'"
                                +" AND chapter_no = '"+castChapterName+"' AND stage='CTOF' AND ship_date IS NOT NULL AND chapter_deleted=0 ";

//        System.out.println("Select Query for Cast-Off pages: "+ "SELECT chapter_no, MAX(chapter_id),est_pages FROM chapter WHERE proj_id='"+prjid+"'"
//                                +" AND chapter_no = '"+castChapterName+"' AND stage='CTOF' AND ship_date IS NOT NULL AND chapter_deleted=0 ");

                rsGetrPrjCastOff=statement.executeQuery(projBookMapSQL);
                while(rsGetrPrjCastOff.next()){
                    tempResult=rsGetrPrjCastOff.getString("chapter_no");
                    if(rsGetrPrjCastOff.wasNull()){
                     tempResult="";
                    }
                    genericName.add(tempResult);


                    tempResult=rsGetrPrjCastOff.getString("chapter_id");
                    if(rsGetrPrjCastOff.wasNull()){
                     tempResult="";
                    }
                    chapterId.add(tempResult);

                    tempResult=rsGetrPrjCastOff.getString("est_pages");
                    if(rsGetrPrjCastOff.wasNull()){
                     tempResult="";
                    }
                    castOffPages.add(tempResult);
            }
                rsGetrPrjCastOff.close();
                statement.close();
//                        }

        }catch(SQLException sqle){
            System.out.println("SQLException in ProjIdAttribs:"+sqle);
        }catch(Exception e){
            System.out.println("Exception in ProjIdAttribs:"+e);
        }finally{
            try{
              con.close();
            }catch(SQLException e){
            System.out.println(e);
            }

        }
}


}
