/*
 * BuyerList.java
 *
 * Created on February 17, 2010, 2:54 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pathfinder.functions.revenue;

import java.io.Serializable;
import java.util.*;
import connection.DBconnection;
import java.sql.*;
/**
 *
 * @author ramyamaims
 */
public class SellerList {

    private List seller_id = new ArrayList();
    private List seller_name = new ArrayList();
    private List seller_address = new ArrayList();
    private List seller_city = new ArrayList();
    private List seller_country = new ArrayList();
    private List seller_state = new ArrayList();
    private List seller_zipcode = new ArrayList();
    private List seller_phone = new ArrayList();
    private List seller_fax = new ArrayList();
    private List seller_type = new ArrayList();
    private List seller_person= new ArrayList();

    private String temp_val = "";
      private String searchKey = "";

    DBconnection dbcon = new DBconnection();
    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;

    private String sql_select = "";
    private String sql_from = "";
    private String sql_where = "";

    private String isSuggest="";

      public void setIsSuggest(String isSuggest)
    {
        this.isSuggest = isSuggest;
    }


     public void setSearchKey(String searchKey){
        this.searchKey = searchKey;
    }


    public List getSellerID(){
        //System.out.println("seller_id in buyerList of pathfinder.functions.revenue : "+seller_id);
        return seller_id;
    }

    public List getSellerName(){
        return seller_name;
    }

    public List getSellerAddress(){
        return seller_address;
    }

    public List getSellerCity(){
        return seller_city;
    }

    public List getSellerCountry(){
        return seller_country;
    }

    public List getSellerState(){
        return seller_state;
    }

    public List getSellerZipCode(){
        return seller_zipcode;
    }

    public List getSellerPhone(){
        return seller_phone;
    }

    public List getSellerFax(){
        return seller_fax;
    }

    public List getSellerType(){
        return seller_type;
    }

    public List getSellerPerson()
    {
        return seller_person;
    }

    public void collectSellerList(){
    pathfinder.util.SpecialCharHandler splChar = new pathfinder.util.SpecialCharHandler();
        try{
            con = dbcon.getSampleProperty();
            stmt = con.createStatement();

            sql_select = "select c.contact_id,concat(firstname,' ', surname),concat(address_1,',', address_2),city,country,state,zipcode,concat(phone_primary,'(primary), ', phone_secondary,'(secondary)'),concat(fax1,', ', fax2,', ',fax3),company,c.is_person ";
            sql_from = "from contacts c, contacttype_map cm, contacts_type_master ctm ";
            sql_where = "c.contact_id = cm.contact_id and cm.type_id = ctm.type_id and cm.type_id = 8 AND (c.is_person='1' OR c.is_person='2') ";

            seller_id.clear();
            seller_name.clear();
            seller_address.clear();
            seller_city.clear();
            seller_country.clear();
            seller_state.clear();
            seller_zipcode.clear();
            seller_phone.clear();
            seller_fax.clear();
            seller_type.clear();

              if(searchKey!=null && !searchKey.equals(""))
            {
                sql_where += " and (firstname LIKE '"+searchKey+"%' or company LIKE '"+searchKey+"%' )  ";

            }
            String isPerson = "";
            sql_select += sql_from+"where "+sql_where;

               if(isSuggest.equals("1"))
        {
            sql_select = sql_select + " LIMIT 5";
        }
            
            //System.out.println("sql_select in sellerList of pathfinder.functions.revenue sellerList : "+sql_select);
            rs = stmt.executeQuery(sql_select);
            while(rs.next()){

                temp_val = splChar.decoding(rs.getString(11));
                 if(rs.wasNull()) {
                    temp_val="";
                }
                isPerson = temp_val;
                seller_person.add(temp_val);

                temp_val =  rs.getString(1);
                if(rs.wasNull()) {
                    temp_val="";
                }
                seller_id.add(temp_val);

                if(isPerson.equals("1")) //person
                {
                temp_val =  splChar.decoding(rs.getString(2));
                if(rs.wasNull()) {
                    temp_val="";
                }
                seller_name.add(temp_val);
                }
                else if(isPerson.equals("2")) //company
                {
                     temp_val =  splChar.decoding(rs.getString(10));
                if(rs.wasNull()) {
                    temp_val="";
                }
                seller_name.add(temp_val);
                }
//System.out.println("seller_name:"+seller_name);

                temp_val =  rs.getString(3);
                if(rs.wasNull()) {
                    temp_val="";
                }
                seller_address.add(temp_val);

                temp_val =  rs.getString(4);
                if(rs.wasNull()) {
                    temp_val="";
                }
                seller_city.add(temp_val);

                temp_val =  rs.getString(5);
                if(rs.wasNull()) {
                    temp_val="";
                }
                seller_country.add(temp_val);

                temp_val =  rs.getString(6);
                if(rs.wasNull()) {
                    temp_val="";
                }
                seller_state.add(temp_val);

                temp_val =  rs.getString(7);
                if(rs.wasNull()) {
                    temp_val="";
                }
                seller_zipcode.add(temp_val);

                temp_val =  rs.getString(8);
                if(rs.wasNull()) {
                    temp_val="";
                }
                seller_phone.add(temp_val);

                temp_val =  rs.getString(9);
                if(rs.wasNull()) {
                    temp_val="";
                }
                seller_fax.add(temp_val);
                seller_type.add("contacts");
            }
            rs.close();
            stmt.close();
            con.close();
        }catch(SQLException sqle){
            System.out.println("SQLException in collecting sellerList:"+sqle);
        }catch(Exception e){
            System.out.println("Exception in collecting sellerList:"+e);
        }
    }
}
