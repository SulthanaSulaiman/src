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
public class GenericChapterNameList implements Serializable {

    private Connection con = null;
    private Statement statement = null;
    private ResultSet rs = null;

    private List projdiscipline = new ArrayList();
    private List projdiscipline_id = new ArrayList();
    private String tempResult = "";
    
    private String sql_select = "";
    private String sql_from = "";

private List addChapName=new ArrayList();
private List addChapVal=new ArrayList();

private List addChapNumber=new ArrayList();
private List addChapLetter=new ArrayList();
private List addChapMisc=new ArrayList();

private List addArea=new ArrayList();
    public void collectChapterList(){
     



        try {
            DBconnection connection = new DBconnection();
            con = connection.getSampleProperty();
            statement = con.createStatement();
            ResultSet rsCh=null;


String chap_name="";
String chap_display="";

rsCh=statement.executeQuery("SELECT filename,Numbered FROM generic_filename WHERE area='Body matter' ");
            while(rsCh.next())
            {
                addChapName.add(rsCh.getString(1));
                chap_name=rsCh.getString(2);
                addChapVal.add(chap_name);
            }
       addChapVal.add("");
       addChapName.add("");
       rsCh=statement.executeQuery("SELECT filename,Numbered FROM generic_filename WHERE area='Rear matter' ");
            while(rsCh.next())
            {
               chap_name=rsCh.getString(2);
                addChapVal.add(chap_name);
                addChapName.add(rsCh.getString(1));
            }
       addChapVal.add("");
       addChapName.add("");
        rsCh=statement.executeQuery("SELECT filename,Numbered FROM generic_filename WHERE area='Front matter' ");
            while(rsCh.next())
            {
                chap_name=rsCh.getString(2);
                addChapVal.add(chap_name);
                addChapName.add(rsCh.getString(1));
            }
        rsCh=statement.executeQuery("SELECT distinct filename FROM generic_filename WHERE Activated=1 AND filename_retrieve IN('C','N') ");
            while(rsCh.next())
            {
                addChapNumber.add(rsCh.getString(1));
            }
        rsCh=statement.executeQuery("SELECT distinct filename FROM generic_filename WHERE Activated=1 AND filename_retrieve IN('C','L') ");
            while(rsCh.next())
            {
                addChapLetter.add(rsCh.getString(1));
            }

            rsCh.close();


        }catch(SQLException sqle){
            sqle.printStackTrace();
        }finally{
            try{
           
            statement.close();
            con.close();
            }catch(SQLException sqle){
            sqle.printStackTrace();
        }

        }

    }

    public List getChapterNumbered() {
        return addChapVal;
    }

    public List getChapterName() {
        return addChapName;
    }
    public List getChapterNumber()
    {
        return addChapNumber;
    }
    public List getChapterLetter()
    {
        return addChapLetter;
    }

}
