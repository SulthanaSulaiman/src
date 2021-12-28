/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.projects;
import connection.DBconnection;
import java.sql.*;
import java.util.*;

/**
 *
 * @author satyanarayanar
 */
public class VendorDetialDAO {

    DBconnection connection = new DBconnection();
    Connection con = null;
    java.sql.Statement stmt = null;
    ResultSet rs = null;
    
     private String sql_select = "";
    private String sql_from = "";
    private String sql_where = "";
    String firstName="";
    String surName="";
    String company="";
    String division="";
    String address1="";
    String address2="";
    String city="";
    String vendorNo="";
    String zipCode="";

    List firstNameList = new ArrayList();
    List surNameList = new ArrayList();
    List companyList = new ArrayList();
    List divisionList = new ArrayList();
    List add1List = new ArrayList();
    List add2List = new ArrayList();
    List cityList = new ArrayList();
    List vendorNoList = new ArrayList();
    List zipCodeList = new ArrayList();

    pathfinder.util.SpecialCharHandler splChar = new pathfinder.util.SpecialCharHandler();

    public void getVendorDetail(VendorDetailVO venDetVO) {
        sql_select = "";
        sql_from = "";
        sql_where = "";




        //System.out.println("sql_select VendorSearchBean : " + sql_select);
        //System.out.println("searchWordList : "+searchWordList);
        sql_select = "SELECT firstname,surname,country,division,address_1,address_2,city,vendor_number,Zipcode ";
        //sql_from = "from projects proj,client cli ";
        sql_from = "from contacts cnt ";
        //sql_where = "where (proj_name like '"+searchWordList.get(0)+"' or proj_bktitle like '"+searchWordList.get(0)+"') and cli.client_code=proj.client_name ";
        String venNo=venDetVO.getVendorNO();
        sql_where = " where contact_id='"+venNo+"'";




        sql_select += sql_from + sql_where;



        //System.out.println("Vendor Details Query : " + sql_select);

        try {
            con = connection.getSampleProperty();
            //System.out.println("con : "+con);
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql_select);
            firstNameList.clear();
            surNameList.clear();
            companyList.clear();
            divisionList.clear();
            add1List.clear();
            add2List.clear();
            cityList.clear();
            vendorNoList.clear();
            zipCodeList.clear();

            // projTitleList.clear();

            while (rs.next()) {
                firstName = splChar.decoding(rs.getString(1));
                surName = splChar.decoding(rs.getString(2));
                // testCliName = rs.getString(3);
                company = rs.getString(3);
                division = splChar.decoding(rs.getString(4));
                address1 = rs.getString(5);
                address2 =  rs.getString(6);
                city =  rs.getString(7);
                vendorNo =  rs.getString(8);
                zipCode =  rs.getString(9);

                if(firstName == null)
                {
                   firstName="" ;
                }
                if(surName == null)
                {
                   surName="" ;
                }
                 if(company == null)
                {
                   company="" ;
                }
                 if(division == null)
                {
                   division="" ;
                }
                 if(address1 == null)
                {
                   address1="" ;
                }
                 if(address2 == null)
                {
                   address2="" ;
                }
                 if(city == null)
                {
                   city="" ;
                }
                  if(vendorNo == null)
                {
                   vendorNo="" ;
                }
                  if(zipCode == null)
                {
                   zipCode="" ;
                }




                firstNameList.add(firstName);
                surNameList.add(surName);
                companyList.add(company);
                divisionList.add(division);
                add1List.add(address1);
                add2List.add(address2);
                cityList.add(city);
                vendorNoList.add(vendorNo);
                zipCodeList.add(zipCode);
                
            }
            
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }
        finally {
            try {
                rs.close();
                stmt.close();
                con.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
}


    }


    public List getFirstName(){
        return firstNameList;
    }

    public List getSurName(){
        return surNameList;
    }

    public List getCountry(){
        return companyList;
    }

    public List getDivision(){
        return divisionList;
    }
     public List getAdd1(){
        return add1List;
    }
      public List getAdd2(){
        return add2List;
    }

    public List getCity(){
        return cityList;
    }

    public List getVendorNo(){
        return vendorNoList;
    }
     public List getzipCode(){
        return zipCodeList;
    }

}
