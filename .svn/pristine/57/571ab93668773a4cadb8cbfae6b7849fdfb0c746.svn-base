/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package filters.generic;

import java.io.Serializable;
import connection.DBconnection;
import java.sql.*;
import java.util.*;

/**
 *
 * @author ramesh
 */
public class ProjCategoryList implements Serializable {

    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;

    private List projCategory = new ArrayList();
    private List projCategoryId = new ArrayList();
    private List projLevelId = new ArrayList();
    private List projLevelName = new ArrayList();
    private List projEISBNStageCode = new ArrayList();
    private List projEISBNStageName = new ArrayList();
    private String tempResult = "";

    private String sql_select = "";
    private String sql_from = "";
    private String customerNameLevel = "";
    public String getCustomerNameLevel() {
        return customerNameLevel;
    }

    public void setCustomerNameLevel(String customerNameLevel) {
        this.customerNameLevel = customerNameLevel;
    }


    public void collectProjType(){
        sql_select = "";
        sql_from = "";

        sql_select = "select prc.projcategory_id , prc.proj_category ";
        sql_from = "from proj_category prc ";

        sql_select +=sql_from;


        try {
            DBconnection connection = new DBconnection();
            con = connection.getSampleProperty();
            st = con.createStatement();
            rs = st.executeQuery(sql_select);
            projCategoryId.clear();
            projCategory.clear();
            while(rs.next()){
		tempResult = rs.getString(1);
                projCategoryId.add(tempResult);
                 tempResult = rs.getString(2);
                projCategory.add(tempResult);
            }

        }catch(SQLException sqle){
            sqle.printStackTrace();
        }finally{
            try{
            rs.close();
            st.close();
            con.close();
            }catch(SQLException sqle){
            sqle.printStackTrace();
        }

        }

    }

    public void collectProjLevelType(){
        sql_select = "";
        sql_from = "";

        sql_select = "select pl.level_id,pl.proj_level ";

        if (customerNameLevel.startsWith("Pearson")) {
            sql_from = "from project_level pl where pl.cust_name='Pearson' ";
        } else {
            sql_from = "from project_level pl where pl.cust_name='Others' ";
        }
        

        sql_select +=sql_from;
        //System.out.println("Testing 402:"+sql_select);


        try {
            DBconnection connection = new DBconnection();
            con = connection.getSampleProperty();
            st = con.createStatement();
            rs = st.executeQuery(sql_select);
            projLevelId.clear();
            projLevelName.clear();
            while(rs.next()){
		tempResult = rs.getString(1);
                projLevelId.add(tempResult);
                 tempResult = rs.getString(2);
                projLevelName.add(tempResult);
            }
            
            rs = st.executeQuery("select projcategory_id ,proj_category from proj_category ");
            while(rs.next()){
                projCategoryId.add(rs.getString(1));
                projCategory.add(rs.getString(2));
            }
            
            rs = st.executeQuery("select stage_code,stage from project_stage  where stage_code in ('CSXML','EPUB2.0','EPUB3.0','EPUBFL','ESTR','ESTRKNDL','ESTRKPAG', 'ETXT','ETXTHSPT','KF8','KNDL','KNDLPAG','LC','NC','PR','SIMPUB','SITB','SMRTBK','VST','WBPDF','WKR')");
            while(rs.next()){
                projEISBNStageCode.add(rs.getString(1));
                projEISBNStageName.add(rs.getString(2));
            }

        }catch(SQLException sqle){
            sqle.printStackTrace();
        }finally{
            try{
            rs.close();
            st.close();
            con.close();
            }catch(SQLException sqle){
            sqle.printStackTrace();
        }

        }

    }

    public void collectProjEISBNList(){
        sql_select = "";
        sql_from = "";

        sql_select = "select ps.stage_code,ps.stage ";
        sql_from = "from project_stage ps where ps.stage_code in ('CSXML','EPUB2.0','EPUB3.0','EPUBFL','ESTR','ESTRKNDL','ESTRKPAG', "
                + " 'ETXT','ETXTHSPT','KF8','KNDL','KNDLPAG','LC','NC','PR','SIMPUB','SITB','SMRTBK','VST','WBPDF','WKR') ";

        sql_select +=sql_from;


        try {
            DBconnection connection = new DBconnection();
            con = connection.getSampleProperty();
            st = con.createStatement();
            rs = st.executeQuery(sql_select);
            projEISBNStageCode.clear();
            projEISBNStageName.clear();
            while(rs.next()){
		tempResult = rs.getString(1);
                projEISBNStageCode.add(tempResult);
                 tempResult = rs.getString(2);
                projEISBNStageName.add(tempResult);
            }

        }catch(SQLException sqle){
            sqle.printStackTrace();
        }finally{
            try{
            rs.close();
            st.close();
            con.close();
            }catch(SQLException sqle){
            sqle.printStackTrace();
        }

        }

    }

    public List getProjCatID() {
        return projCategoryId;
    }

    public List getProjCategory() {
        return projCategory;
    }

    public List getProjLevelId() {
        return projLevelId;
    }

    public List getProjLevelName() {
        return projLevelName;
    }

    public List getProjEISBNStageCode() {
        return projEISBNStageCode;
    }

    public List getProjEISBNStageName() {
        return projEISBNStageName;
    }
    
}
