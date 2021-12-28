/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package filters.generic;

import filters.*;
import java.io.Serializable;
import connection.DBconnection;
import java.sql.*;
import java.util.*;

/**
 *
 * @author ramyams
 */
public class FacilityList implements Serializable {

    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;

    private List facilityNameList = new ArrayList();
    private List facilityIdList = new ArrayList();
    private List facility_address = new ArrayList();
    private List facility_city = new ArrayList();
    private List facility_country = new ArrayList();
    private List facility_state = new ArrayList();
    private List facility_zipcode = new ArrayList();
    private List facility_phone = new ArrayList();
    private List facility_fax = new ArrayList();
     private String searchKey = "";

    private String temp_val = "";
    private String testcode = "";
    private String testname = "";


    private String sql_select = "";
    private String sql_from = "";

  private String isSuggest="";

      public void setIsSuggest(String isSuggest)
    {
        this.isSuggest = isSuggest;
    }


    public void collectFacilitytList(){
        sql_select = "";
        sql_from = "";

        sql_select = "select facility_id,facility_name,fcy_address,fcy_city,fcy_country,fcy_state,fcy_zipcode,fcy_phone,fcy_fax ";
        sql_from = "from facility fc ";

        sql_select +=sql_from;

        if(searchKey!=null && !searchKey.equals(""))
            {
                sql_select += " where facility_name LIKE '"+searchKey+"%' ";
            }

   if(isSuggest.equals("1"))
        {
            sql_select = sql_select + " LIMIT 10";
        }

        try {

            DBconnection connection = new DBconnection();
            con = connection.getSampleProperty();
            st = con.createStatement();
            rs = st.executeQuery(sql_select);

            facilityIdList.clear();
            facilityNameList.clear();
            facility_address.clear();
            facility_city.clear();
            facility_country.clear();
            facility_state.clear();
            facility_zipcode.clear();
            facility_phone.clear();
            facility_fax.clear();

            while(rs.next()){
                facilityIdList.add(rs.getString(1));
                facilityNameList.add(rs.getString(2));


                temp_val =  rs.getString(3);
                if(rs.wasNull()) {
                    temp_val="";
                }
                facility_address.add(temp_val);

                temp_val =  rs.getString(4);
                if(rs.wasNull()) {
                    temp_val="";
                }
                facility_city.add(temp_val);

                temp_val =  rs.getString(5);
                if(rs.wasNull()) {
                    temp_val="";
                }
                facility_country.add(temp_val);

                temp_val =  rs.getString(6);
                if(rs.wasNull()) {
                    temp_val="";
                }
                facility_state.add(temp_val);

                temp_val =  rs.getString(7);
                if(rs.wasNull()) {
                    temp_val="";
                }
                facility_zipcode.add(temp_val);

                temp_val =  rs.getString(8);
                if(rs.wasNull()) {
                    temp_val="";
                }
                facility_phone.add(temp_val);

                temp_val =  rs.getString(9);
                if(rs.wasNull()) {
                    temp_val="";
                }
                facility_fax.add(temp_val);
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
 public void setSearchKey(String searchKey){
        this.searchKey = searchKey;
    }
    public List getFacilityId() {
        return facilityIdList;
    }

    public List getFacilityName() {
        return facilityNameList;
    }

    public List getFacilityAdd() {
        return facility_address;
    }

    public List getFacilityCity() {
        return facility_city;
    }

    public List getFacilityCountry() {
        return facility_country;
    }

    public List getFacilityState() {
        return facility_state;
    }

    public List getFacilityZipCode() {
        return facility_zipcode;
    }

    public List getFacilityPhone() {
        return facility_phone;
    }

    public List getFacilityFax() {
        return facility_fax;
    }

}
