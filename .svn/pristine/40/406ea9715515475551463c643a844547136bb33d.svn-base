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
public class NotesCategoryList implements Serializable {

    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;

    private List categoryNameList = new ArrayList();
    private List categoryIdList = new ArrayList();

    private String categoryName = "";
    private String category = "";
    private String categoryId = "";

    private String testcode = "";
    private String testname = "";


    private String sql_select = "";
    private String sql_from = "";

 public void setCategoryName(String category){
     this.category=category;
 }


    public void collectNotesCateogryList(){
        sql_select = "";
        sql_from = "";

        sql_select = "select nt.category_id, nt.name ";
        sql_from = "from notes_category nt ";

        sql_select +=sql_from;

        if(!category.equals("")){
            sql_select+= " where nt.name='"+category+"' ";
        }

        //System.out.println("sql_select for NotesCategory:"+sql_select);

        try {
            DBconnection connection = new DBconnection();
            con = connection.getSampleProperty();
            st = con.createStatement();
            rs = st.executeQuery(sql_select);
            categoryIdList.clear();
            categoryNameList.clear();
            while(rs.next()){
		testcode = rs.getString(1);
                testname = rs.getString(2);
                categoryIdList.add(testcode);
                categoryNameList.add(testname);

                categoryId=testcode;
                categoryName=testname;
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

    public List getCategoryId() {
        return categoryIdList;
    }

    public List getCategoryName() {
        return categoryNameList;
    }

    public String getCategoryIdValue() {
        //System.out.println("categoryId:"+categoryId);
        return categoryId;
    }

    public String getCategoryNameValue() {
        //System.out.println("categoryName:"+categoryName);
        return categoryName;
    }



}
