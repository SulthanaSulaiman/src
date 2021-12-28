/*
 * ProjectSearchBean.java
 *
 * Created on April 2, 2009, 1:33 PM
 */

package pathfinder.functions.revenue;

import java.beans.*;
import java.io.Serializable;

import connection.DBconnection;
import java.sql.*;
import java.util.*;
import java.io.*;

/**
 *
 * @author ramyams
 */
public class ProjectSearchBean {
    
    DBconnection connection = new DBconnection();
    Connection con = null;
    java.sql.Statement stmt = null;
    ResultSet rs = null;
    
    private String sql_select = "";
    private String sql_from = "";
    private String sql_where = "";
    private String sql_group = "";
    private String sql_order = "";
    private String projEISBNCategoryParam = "";
    private String EISBNFieldParam = "";
    private String projID = "";
    
    private String testCode = "";
    private String testName = "";
    private String testTitle = "";
    private String testCliName = "";
    private String setClientName = "";
    private String nextToken = "";
    public String setSearchKey = "";
    private StringTokenizer stk;
    String isSuggest="";
    
    private List searchWordList = new ArrayList();
    private List projIDList = new ArrayList();
    private List projNameList = new ArrayList();
    private List projTitleList = new ArrayList();
    private List clientNameList = new ArrayList();
    private List EISBNName = new ArrayList();
    private List EISBNId = new ArrayList();
    
   
    /** Creates a new instance of ProjectSearchBean */
    public ProjectSearchBean() {
    }
    
    public void setSearchKey(String setSearchKey){
        this.setSearchKey = setSearchKey;
        
        //System.out.println("setSearchKey-Bean:"+setSearchKey);
      
    }

    public void setIsSuggest(String isSuggest)
    {
        this.isSuggest = isSuggest;
    }
   
    
    public void setClientName(String setClientName){
        this.setClientName = setClientName;
    }
    
    public void getValue(){
        sql_select="";
        sql_from="";
        sql_where="";
        sql_group="";
        sql_order="";
        searchWordList.clear();
        //System.out.println("setSearchKey : "+setSearchKey);
        stk=new StringTokenizer(setSearchKey," ");
        nextToken = "";
        while(stk.hasMoreTokens()) {
            nextToken+=stk.nextToken()+"%";             
        }
        
        searchWordList.add(nextToken);
        
        //System.out.println("sql_select ProjectSearchBean : "+sql_select);
        //System.out.println("searchWordList : "+searchWordList);
        sql_select = "SELECT distinct(proj.proj_id),proj.proj_name,proj.proj_bktitle ";
        //sql_from = "from projects proj,client cli ";
        sql_from = "from projects proj,contacts c ";
        //sql_where = "where (proj_name like '"+searchWordList.get(0)+"' or proj_bktitle like '"+searchWordList.get(0)+"') and cli.client_code=proj.client_name ";
        sql_where = "where (proj_id like '"+searchWordList.get(0)+"' or proj_name like '"+searchWordList.get(0)+"' or proj_bktitle like '"+searchWordList.get(0)+"' "+
                     " or proj_isbn_13 like '"+searchWordList.get(0)+"' or proj_isbn_10 like '"+searchWordList.get(0)+"' "+
                     " or c.company like '"+searchWordList.get(0)+"' or c.firstname like '"+searchWordList.get(0)+"' or c.surname like '"+searchWordList.get(0)+"' or proj_name like '"+"%"+searchWordList.get(0)+"') and c.contact_id=client_name";
        
        if(!setClientName.equals("")){
            
            if(!sql_where.equals("")){
                    sql_where += " and ";
            }
            sql_where +=" cli.client_code = '"+setClientName+"'";
        }
        
        sql_group = "";
        sql_order = "";
        
        sql_select +=sql_from+sql_where+sql_group+sql_order;

        if(isSuggest.equals("1"))
        {
            sql_select = sql_select + " LIMIT 10";
        }
        
        //System.out.println("sql_select ProjectSearchBean : "+sql_select);
        
        try {
            con = connection.getSampleProperty();
            //System.out.println("con : "+con);
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql_select);
            projIDList.clear();
            projNameList.clear();
            clientNameList.clear();
            projTitleList.clear();
            
            while(rs.next()){
                testCode = rs.getString(1);
                testName = rs.getString(2);
               // testCliName = rs.getString(3);
                testTitle = rs.getString(3);
    
                projIDList.add(testCode);
                projNameList.add(testName);
                clientNameList.add(testCliName);
                projTitleList.add(testTitle);
            }
            rs.close();
            stmt.close();
            con.close();
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }catch(NullPointerException npe){
            npe.printStackTrace();
        }
        
        
    }

      public void getValue1(){
        sql_select="";
        sql_from="";
        sql_where="";
        sql_group="";
        sql_order="";
        searchWordList.clear();
        //System.out.println("setSearchKey : "+setSearchKey);
        stk=new StringTokenizer(setSearchKey," ");
        nextToken = "";
        while(stk.hasMoreTokens()) {
            nextToken+=stk.nextToken()+"%";
        }

        searchWordList.add(nextToken);

        //System.out.println("sql_select ProjectSearchBean : "+sql_select);
        //System.out.println("searchWordList : "+searchWordList);
        sql_select = "SELECT distinct(proj.proj_id),proj.proj_name,proj.proj_bktitle ";
        //sql_from = "from projects proj,client cli ";
        sql_from = "from projects proj,contacts c ";
        //sql_where = "where (proj_name like '"+searchWordList.get(0)+"' or proj_bktitle like '"+searchWordList.get(0)+"') and cli.client_code=proj.client_name ";
        sql_where = "where (proj_id like '"+searchWordList.get(0)+"' or proj_name like '"+searchWordList.get(0)+"' or proj_bktitle like '"+searchWordList.get(0)+"' "+
                     " or proj_isbn_13 like '"+searchWordList.get(0)+"' or proj_isbn_10 like '"+searchWordList.get(0)+"' "+
                     " or c.company like '"+searchWordList.get(0)+"' or c.firstname like '"+searchWordList.get(0)+"' or c.surname like '"+searchWordList.get(0)+"' or proj_name like '"+"%"+searchWordList.get(0)+"') and c.contact_id=client_name and project_status NOT IN (2,22,23) ";

        if(!setClientName.equals("")){

            if(!sql_where.equals("")){
                    sql_where += " and ";
            }
            sql_where +=" cli.client_code = '"+setClientName+"'";
        }

        sql_group = "";
        sql_order = "";

        sql_select +=sql_from+sql_where+sql_group+sql_order;

        if(isSuggest.equals("1"))
        {
            sql_select = sql_select + " LIMIT 10";
        }

        

        try {
            con = connection.getSampleProperty();
            System.out.println("con : "+sql_select);
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql_select);
            projIDList.clear();
            projNameList.clear();
            clientNameList.clear();
            projTitleList.clear();

            while(rs.next()){
                testCode = rs.getString(1);
                testName = rs.getString(2);
               // testCliName = rs.getString(3);
                testTitle = rs.getString(3);

                projIDList.add(testCode);
                projNameList.add(testName);
                clientNameList.add(testCliName);
                projTitleList.add(testTitle);
            }
            rs.close();
            stmt.close();
            con.close();
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }catch(NullPointerException npe){
            npe.printStackTrace();
        }


    }

    public List getProjCode(){
        return projIDList;
    }
    
    public List getProjName(){
        return projNameList;
    }
    
    public List getProjTitle(){
        return projTitleList;
    }
    
    public List getClientName(){
        return clientNameList;
    }
    
    public void setprojEISBNCategoryParam(String projEISBNCategoryParam)
    {
        this.projEISBNCategoryParam=projEISBNCategoryParam;
    }
    public void setprojID(String projID)
    {
        this.projID=projID;
    }

    public List getEISBNName()
    {
        return EISBNName;
    }

    public List getEISBNId()
    {
        return EISBNId;
    }



    public void getEISBN() {

        projEISBNCategoryParam = setSearchKey;
        String storeData = "";

        if (!projEISBNCategoryParam.equals("") || !projEISBNCategoryParam.equals("All")) {
            if (projEISBNCategoryParam.equals("CSXML")) {
                EISBNFieldParam = "proj_isbn_CSXML";
            } else if (projEISBNCategoryParam.equals("EPUB2.0")) {
                EISBNFieldParam = "proj_isbn_EPUB2_0";
            } else if (projEISBNCategoryParam.equals("EPUB3.0")) {
                EISBNFieldParam = "proj_isbn_EPUB3_0";
            } else if (projEISBNCategoryParam.equals("EPUBFL")) {
                EISBNFieldParam = "proj_isbn_EPUBFL";
            } else if (projEISBNCategoryParam.equals("ESTR")) {
                EISBNFieldParam = "proj_isbn_ESTR";
            } else if (projEISBNCategoryParam.equals("ESTRKNDL")) {
                EISBNFieldParam = "proj_isbn_ESTRKNDL";
            } else if (projEISBNCategoryParam.equals("ESTRKPAG")) {
                EISBNFieldParam = "proj_isbn_ESTRKPAG";
            } else if (projEISBNCategoryParam.equals("ETXT")) {
                EISBNFieldParam = "proj_isbn_ETXT";
            } else if (projEISBNCategoryParam.equals("ETXTHSPT")) {
                EISBNFieldParam = "proj_isbn_ETXTHSPT";
            } else if (projEISBNCategoryParam.equals("KF8")) {
                EISBNFieldParam = "proj_isbn_KF8";
            } else if (projEISBNCategoryParam.equals("KNDL")) {
                EISBNFieldParam = "proj_isbn_KNDL";
            } else if (projEISBNCategoryParam.equals("KNDLPAG")) {
                EISBNFieldParam = "proj_isbn_KNDLPAG";
            } else if (projEISBNCategoryParam.equals("LC")) {
                EISBNFieldParam = "proj_isbn_LC";
            } else if (projEISBNCategoryParam.equals("NC")) {
                EISBNFieldParam = "proj_isbn_NC";
            } else if (projEISBNCategoryParam.equals("PR")) {
                EISBNFieldParam = "proj_isbn_PR";
            } else if (projEISBNCategoryParam.equals("SIMPUB")) {
                EISBNFieldParam = "proj_isbn_SIMPUB";
            } else if (projEISBNCategoryParam.equals("SITB")) {
                EISBNFieldParam = "proj_isbn_SITB";
            } else if (projEISBNCategoryParam.equals("SMRTBK")) {
                EISBNFieldParam = "proj_isbn_SMRTBK";
            } else if (projEISBNCategoryParam.equals("VST")) {
                EISBNFieldParam = "proj_isbn_VST";
            } else if (projEISBNCategoryParam.equals("WBPDF")) {
                EISBNFieldParam = "proj_isbn_WBPDF";
            } else if (projEISBNCategoryParam.equals("WKR")) {
                EISBNFieldParam = "proj_isbn_WKR";
            }
        }

        String queryToGetIESBNVal = "select '" + projEISBNCategoryParam + "', " + EISBNFieldParam + " from projects where proj_id='" + projID + "' ";

        try {
            con = connection.getSampleProperty();
            stmt = con.createStatement();
            rs = stmt.executeQuery(queryToGetIESBNVal);

            while (rs.next()) {
                EISBNId.add(rs.getString(1));
                storeData = rs.getString(2);
                if (rs.wasNull()) {
                    storeData = "";
                }
                EISBNName.add(storeData);
            }

            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }


    }
}
