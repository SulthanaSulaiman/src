/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.projects;

import java.io.Serializable;
import java.sql.*;
import java.util.*;
import connection.DBconnection;

/**
 *
 * @author ramesh
 */
public class CheckISBN10Dgt implements Serializable {

    
private List isbn13List = new ArrayList();
private List isbn10List = new ArrayList();
private Connection con = null;

private String chkIsbn10 = "";
private String chkIsbn13 = "";
private String chkEisbn = "";
private String returnISBN10Alert = "";
private String returnISBN13Alert = "";
private String returnEISBNAlert = "";

private String dbIsbn10 = "";
private String dbIsbn13 = "";
private String dbEISBNProjID = "";
private String projid = "";

public void setISBN10(String chkIsbn10)
    {
        this.chkIsbn10=chkIsbn10;
    }

public void setProjId(String projid)
    {
        this.projid=projid;
    }

public String getIsbn10Alert()
    {
        return returnISBN10Alert;
    }

public void setISBN13(String chkIsbn13)
    {
        this.chkIsbn13=chkIsbn13;
    }

public void setChkEISBN(String chkEisbn) {
    this.chkEisbn = chkEisbn;
}

public String getIsbn13Alert()
    {
        return returnISBN13Alert;
    }

public String getEISBNAlert() {
    return returnEISBNAlert;
}


public void collectIsbn(){
    try{
        DBconnection dbcon = new DBconnection();
        con = dbcon.getSampleProperty();
        Statement st = con.createStatement();

        //System.out.println("Query:"+"select replace(proj_isbn_10,'-','') from projects where replace(proj_isbn_10,'-','')='"+chkIsbn10+"' and proj_id!='"+projid+"' ");

        ResultSet rsGetISB10 = st.executeQuery("select replace(proj_isbn_10,'-','') from projects where replace(proj_isbn_10,'-','')='"+chkIsbn10+"' and proj_id!='"+projid+"' ");
        if(rsGetISB10.next()){
            rsGetISB10 = st.executeQuery("select replace(proj_isbn_10,'-','') from projects where replace(proj_isbn_10,'-','')='"+chkIsbn10+"' and proj_id!='"+projid+"' ");
        while(rsGetISB10.next()){
            dbIsbn10 = rsGetISB10.getString(1);
                if(rsGetISB10.wasNull()){
                    dbIsbn10="";
                }
            }
        }

        if(dbIsbn10.equals(chkIsbn10)&&!dbIsbn10.equals("")){
            returnISBN10Alert = "ISBN-10 already exists";
        }
        else{
            returnISBN10Alert = "";
        }

        /*if(dbIsbn10.equals(chkIsbn10)){
            returnISBN10Alert = "ISBN-10 already exists";
        }*/

      //System.out.println("dbIsbn10:"+dbIsbn10);
      //System.out.println("returnISBN10Alert:"+returnISBN10Alert);

      rsGetISB10.close();
      st.close();
    }catch(SQLException sqle){
        sqle.printStackTrace();
    }
    finally{
        try{
            con.close();
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }
    }
}

public void collectIsbn13(){
    try{
        DBconnection dbcon = new DBconnection();
        con = dbcon.getSampleProperty();
        Statement st = con.createStatement();

        //System.out.println("Query:"+"select replace(proj_isbn_10,'-','') from projects where replace(proj_isbn_10,'-','')='"+chkIsbn10+"' and proj_id!='"+projid+"' ");

        ResultSet rsGetISB13 = st.executeQuery("select replace(proj_isbn_13,'-','') from projects where replace(proj_isbn_13,'-','')='"+chkIsbn13+"' and proj_id!='"+projid+"' ");
        if(rsGetISB13.next()){
            rsGetISB13 = st.executeQuery("select replace(proj_isbn_13,'-','') from projects where replace(proj_isbn_13,'-','')='"+chkIsbn13+"' and proj_id!='"+projid+"' ");
        while(rsGetISB13.next()){
            dbIsbn13 = rsGetISB13.getString(1);
                if(rsGetISB13.wasNull()){
                    dbIsbn13="";
                }
            }
        }

        if(dbIsbn13.equals(chkIsbn13)&&!dbIsbn13.equals("")){
            returnISBN13Alert = "ISBN-13 already exists";
        }
        else{
            returnISBN13Alert = "";
        }

        /*if(dbIsbn10.equals(chkIsbn10)){
            returnISBN10Alert = "ISBN-10 already exists";
        }*/

        //System.out.println("dbIsbn13:"+dbIsbn13);
        //System.out.println("returnISBN13Alert:"+returnISBN13Alert);

    }catch(SQLException sqle){
        sqle.printStackTrace();
    }
    finally{
        try{
            con.close();
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }
    }
}

public void collectEIsbn(){
    try{
        DBconnection dbcon = new DBconnection();
        con = dbcon.getSampleProperty();
        Statement st = con.createStatement();

        //System.out.println("Query:"+"select replace(proj_isbn_10,'-','') from projects where replace(proj_isbn_10,'-','')='"+chkIsbn10+"' and proj_id!='"+projid+"' ");

        ResultSet rsGetISB13 = st.executeQuery("select proj_id,replace(proj_isbn_CSXML,'-',''),replace(proj_isbn_EPUB2_0,'-',''),replace(proj_isbn_EPUB3_0,'-',''),"
                + " replace(proj_isbn_EPUBFL,'-',''),replace(proj_isbn_ESTR,'-',''),replace(proj_isbn_ESTRKNDL,'-',''),replace(proj_isbn_ESTRKPAG,'-',''),"
                + " replace(proj_isbn_ETXT,'-',''),replace(proj_isbn_ETXTHSPT,'-',''),replace(proj_isbn_KF8,'-',''),replace(proj_isbn_KNDL,'-',''),"
                + " replace(proj_isbn_KNDLPAG,'-',''),replace(proj_isbn_LC,'-',''),replace(proj_isbn_NC,'-',''),replace(proj_isbn_PR,'-',''),"
                + " replace(proj_isbn_SIMPUB,'-',''),replace(proj_isbn_SITB,'-',''),replace(proj_isbn_SMRTBK,'-',''),replace(proj_isbn_VST,'-',''),"
                + " replace(proj_isbn_WBPDF,'-',''),replace(proj_isbn_WKR,'-','') "
                + " from projects WHERE (replace(proj_isbn_CSXML,'-','')='"+chkEisbn+"' or replace(proj_isbn_EPUB2_0,'-','')='"+chkEisbn+"' or "
                + " replace(proj_isbn_EPUB3_0,'-','')='"+chkEisbn+"' or replace(proj_isbn_EPUBFL,'-','')='"+chkEisbn+"' or "
                + " replace(proj_isbn_ESTR,'-','')='"+chkEisbn+"' or replace(proj_isbn_ESTRKNDL,'-','')='"+chkEisbn+"' or "
                + " replace(proj_isbn_ESTRKPAG,'-','')='"+chkEisbn+"' or replace(proj_isbn_ETXT,'-','')='"+chkEisbn+"' or "
                + " replace(proj_isbn_ETXTHSPT,'-','')='"+chkEisbn+"' or replace(proj_isbn_KF8,'-','')='"+chkEisbn+"' or "
                + " replace(proj_isbn_KNDL,'-','')='"+chkEisbn+"' or replace(proj_isbn_KNDLPAG,'-','')='"+chkEisbn+"' or "
                + " replace(proj_isbn_LC,'-','')='"+chkEisbn+"' or replace(proj_isbn_NC,'-','')='"+chkEisbn+"' or "
                + " replace(proj_isbn_PR,'-','')='"+chkEisbn+"' or replace(proj_isbn_SIMPUB,'-','')='"+chkEisbn+"' or "
                + " replace(proj_isbn_SITB,'-','')='"+chkEisbn+"' or replace(proj_isbn_SMRTBK,'-','')='"+chkEisbn+"' or "
                + " replace(proj_isbn_VST,'-','')='"+chkEisbn+"' or replace(proj_isbn_WBPDF,'-','')='"+chkEisbn+"' or "
                + " replace(proj_isbn_WKR,'-','')='"+chkEisbn+"') and proj_id!='"+projid+"' ");
        if(rsGetISB13.next()){
            rsGetISB13 = st.executeQuery("select proj_id,replace(proj_isbn_CSXML,'-',''),replace(proj_isbn_EPUB2_0,'-',''),replace(proj_isbn_EPUB3_0,'-',''),"
                + " replace(proj_isbn_EPUBFL,'-',''),replace(proj_isbn_ESTR,'-',''),replace(proj_isbn_ESTRKNDL,'-',''),replace(proj_isbn_ESTRKPAG,'-',''),"
                + " replace(proj_isbn_ETXT,'-',''),replace(proj_isbn_ETXTHSPT,'-',''),replace(proj_isbn_KF8,'-',''),replace(proj_isbn_KNDL,'-',''),"
                + " replace(proj_isbn_KNDLPAG,'-',''),replace(proj_isbn_LC,'-',''),replace(proj_isbn_NC,'-',''),replace(proj_isbn_PR,'-',''),"
                + " replace(proj_isbn_SIMPUB,'-',''),replace(proj_isbn_SITB,'-',''),replace(proj_isbn_SMRTBK,'-',''),replace(proj_isbn_VST,'-',''),"
                + " replace(proj_isbn_WBPDF,'-',''),replace(proj_isbn_WKR,'-','') "
                + " from projects WHERE (replace(proj_isbn_CSXML,'-','')='"+chkEisbn+"' or replace(proj_isbn_EPUB2_0,'-','')='"+chkEisbn+"' or "
                + " replace(proj_isbn_EPUB3_0,'-','')='"+chkEisbn+"' or replace(proj_isbn_EPUBFL,'-','')='"+chkEisbn+"' or "
                + " replace(proj_isbn_ESTR,'-','')='"+chkEisbn+"' or replace(proj_isbn_ESTRKNDL,'-','')='"+chkEisbn+"' or "
                + " replace(proj_isbn_ESTRKPAG,'-','')='"+chkEisbn+"' or replace(proj_isbn_ETXT,'-','')='"+chkEisbn+"' or "
                + " replace(proj_isbn_ETXTHSPT,'-','')='"+chkEisbn+"' or replace(proj_isbn_KF8,'-','')='"+chkEisbn+"' or "
                + " replace(proj_isbn_KNDL,'-','')='"+chkEisbn+"' or replace(proj_isbn_KNDLPAG,'-','')='"+chkEisbn+"' or "
                + " replace(proj_isbn_LC,'-','')='"+chkEisbn+"' or replace(proj_isbn_NC,'-','')='"+chkEisbn+"' or "
                + " replace(proj_isbn_PR,'-','')='"+chkEisbn+"' or replace(proj_isbn_SIMPUB,'-','')='"+chkEisbn+"' or "
                + " replace(proj_isbn_SITB,'-','')='"+chkEisbn+"' or replace(proj_isbn_SMRTBK,'-','')='"+chkEisbn+"' or "
                + " replace(proj_isbn_VST,'-','')='"+chkEisbn+"' or replace(proj_isbn_WBPDF,'-','')='"+chkEisbn+"' or "
                + " replace(proj_isbn_WKR,'-','')='"+chkEisbn+"') and proj_id!='"+projid+"' ");
        while(rsGetISB13.next()){
            dbEISBNProjID = rsGetISB13.getString(1);
                if(rsGetISB13.wasNull()){
                    dbEISBNProjID="";
                }
            }
        }

        if(!dbEISBNProjID.equals("")){
            returnEISBNAlert = "E-ISBN already exists";
        }
        else{
            returnEISBNAlert = "";
        }

        /*if(dbIsbn10.equals(chkIsbn10)){
            returnISBN10Alert = "ISBN-10 already exists";
        }*/

        //System.out.println("dbEISBNProjID:"+dbEISBNProjID);
        //System.out.println("returnISBN13Alert:"+returnEISBNAlert);

    }catch(SQLException sqle){
        sqle.printStackTrace();
    }
    finally{
        try{
            con.close();
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }
    }
}

}
