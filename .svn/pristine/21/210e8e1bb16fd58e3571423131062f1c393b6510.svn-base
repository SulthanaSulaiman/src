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
public class BuyerList {
//this acts as the list of the buyer details for po, seller list for billing, invoice and estimation
    private List buyer_id = new ArrayList();
    private List buyer_name = new ArrayList();
    private List buyer_address = new ArrayList();
    private List buyer_city = new ArrayList();
    private List buyer_country = new ArrayList();
    private List buyer_state = new ArrayList();
    private List buyer_zipcode = new ArrayList();
    private List buyer_phone = new ArrayList();
    private List buyer_fax = new ArrayList();
    private List buyer_type = new ArrayList();
    private String searchKey = "";
        private List buyer_person= new ArrayList();
    
    private String temp_val = "";
    
    DBconnection dbcon = new DBconnection();
    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    
    private String sql_select = "";
    private String sql_from = "";
    private String sql_where = "";

     String isSuggest="";

     public List getBuyerrPerson()
    {
        return buyer_person;
    }

      public void setIsSuggest(String isSuggest)
    {
        this.isSuggest = isSuggest;
    }

    public List getBuyerID(){
        //System.out.println("facility_id in buyerList of pathfinder.functions.revenue : "+buyer_id);
        return buyer_id;
    }

    public List getBuyerName(){
        return buyer_name;
    }

    public List getBuyerAddress(){
        return buyer_address;
    }

    public List getBuyerCity(){
        return buyer_city;
    }

    public List getBuyerCountry(){
        return buyer_country;
    }

    public List getBuyerState(){
        return buyer_state;
    }

    public List getBuyerZipCode(){
        return buyer_zipcode;
    }

    public List getBuyerPhone(){
        return buyer_phone;
    }

    public List getBuyerFax(){
        return buyer_fax;
    }
    
    public List getBuyerType(){
        return buyer_type;
    }

    public void setSearchKey(String searchKey){
        this.searchKey = searchKey;
    }
    
    public void collectBuyerList(){

        pathfinder.util.SpecialCharHandler splChar = new pathfinder.util.SpecialCharHandler();
        try{
            con = dbcon.getSampleProperty();
            stmt = con.createStatement();
            
            sql_select = "select facility_id,facility_name,fcy_address,fcy_city,fcy_country,fcy_state,fcy_zipcode,fcy_phone,fcy_fax ";
            sql_from = "from facility ";
            sql_where = " where " ;
                        
            buyer_id.clear();
            buyer_name.clear();
            buyer_address.clear();
            buyer_city.clear();
            buyer_country.clear();
            buyer_state.clear();
            buyer_zipcode.clear();
            buyer_phone.clear();
            buyer_fax.clear();
            buyer_type.clear();

             sql_select += sql_from;

            if(searchKey!=null && !searchKey.equals(""))
            {
                sql_where += "facility_name LIKE '%"+searchKey+"%' ";
                sql_select = sql_select + sql_where;
            }

             if(isSuggest.equals("1"))
        {
            sql_select = sql_select + " LIMIT 5";
        }
             
            //System.out.println("sql_select in buyerList of pathfinder.functions.revenue facility : "+sql_select);
            rs = stmt.executeQuery(sql_select);
            while(rs.next()){
                
                temp_val =  rs.getString(1);
                if(rs.wasNull()) {
                    temp_val="";
                }
                buyer_id.add(temp_val);
                
                temp_val =  rs.getString(2);
                if(rs.wasNull()) {
                    temp_val="";
                }
                buyer_name.add(temp_val);
                
                temp_val =  rs.getString(3);
                if(rs.wasNull()) {
                    temp_val="";
                }
                buyer_address.add(temp_val);
                
                temp_val =  rs.getString(4);
                if(rs.wasNull()) {
                    temp_val="";
                }
                buyer_city.add(temp_val);
                
                temp_val =  rs.getString(5);
                if(rs.wasNull()) {
                    temp_val="";
                }
                buyer_country.add(temp_val);
                
                temp_val =  rs.getString(6);
                if(rs.wasNull()) {
                    temp_val="";
                }
                buyer_state.add(temp_val);
                
                temp_val =  rs.getString(7);
                if(rs.wasNull()) {
                    temp_val="";
                }
                buyer_zipcode.add(temp_val);
                
                temp_val =  rs.getString(8);
                if(rs.wasNull()) {
                    temp_val="";
                }
                buyer_phone.add(temp_val);
                
                temp_val =  rs.getString(9);
                if(rs.wasNull()) {
                    temp_val="";
                }
                buyer_fax.add(temp_val);
                buyer_type.add("facility");
            }
           /* buyer_id.add("-1");
            buyer_name.add("-----");
            buyer_address.add("");
            buyer_city.add("");
            buyer_country.add("");
            buyer_state.add("");
            buyer_zipcode.add("");
            buyer_phone.add("");
            buyer_fax.add("");
            buyer_type.add("");*/
            
            sql_select = "";
            sql_from = "";
            sql_where = "";
            sql_select = "select distinct(d.dept_code),d.department,f.fcy_address,f.fcy_city,f.fcy_country,f.fcy_state,f.fcy_zipcode,f.fcy_phone,f.fcy_fax ";
            sql_from = "from department d, facility f, user u ";
            sql_where = "u.dept_code = d.dept_code and f.facility_id = u.facility_id ";
            
           

             if(searchKey!=null && !searchKey.equals(""))
            {
                sql_where += " and d.department LIKE '%"+searchKey+"%' ";
                
            }



            sql_select += sql_from+"where "+sql_where;

                if(isSuggest.equals("1"))
        {
            sql_select = sql_select + " LIMIT 5";
        }

            //System.out.println("sql_select in buyerList of pathfinder.functions.revenue department : "+sql_select);
            rs = stmt.executeQuery(sql_select);
            while(rs.next()){
                
                temp_val =  rs.getString(1);
                if(rs.wasNull()) {
                    temp_val="";
                }
                buyer_id.add(temp_val);
                
                temp_val =  rs.getString(2);
                if(rs.wasNull()) {
                    temp_val="";
                }
                buyer_name.add(temp_val);
                
                temp_val =  rs.getString(3);
                if(rs.wasNull()) {
                    temp_val="";
                }
                buyer_address.add(temp_val);
                
                temp_val =  rs.getString(4);
                if(rs.wasNull()) {
                    temp_val="";
                }
                buyer_city.add(temp_val);
                
                temp_val =  rs.getString(5);
                if(rs.wasNull()) {
                    temp_val="";
                }
                buyer_country.add(temp_val);
                
                temp_val =  rs.getString(6);
                if(rs.wasNull()) {
                    temp_val="";
                }
                buyer_state.add(temp_val);
                
                temp_val =  rs.getString(7);
                if(rs.wasNull()) {
                    temp_val="";
                }
                buyer_zipcode.add(temp_val);
                
                temp_val =  rs.getString(8);
                if(rs.wasNull()) {
                    temp_val="";
                }
                buyer_phone.add(temp_val);
                
                temp_val =  rs.getString(9);
                if(rs.wasNull()) {
                    temp_val="";
                }
                buyer_fax.add(temp_val);
                buyer_type.add("department");
            }
           /* buyer_id.add("-1");
            buyer_name.add("-----");
            buyer_address.add("");
            buyer_city.add("");
            buyer_country.add("");
            buyer_state.add("");
            buyer_zipcode.add("");
            buyer_phone.add("");
            buyer_fax.add("");
            buyer_type.add("");  */
            
            sql_select = "";
            sql_from = "";
            sql_where ="" ;
            sql_select = "select u.emp_id,u.emp_name,f.fcy_address,f.fcy_city,f.fcy_country,f.fcy_state,f.fcy_zipcode,f.fcy_phone,f.fcy_fax ";
            sql_from = "from facility f, user u ";
            
            sql_where = "f.facility_id = u.facility_id and u.status=1 ";
            
           


             if(searchKey!=null && !searchKey.equals(""))
            {
                sql_where += " and u.emp_name LIKE '%"+searchKey+"%' ";
               
            }
             sql_select += sql_from+"where "+sql_where;

                 if(isSuggest.equals("1"))
        {
            sql_select = sql_select + " LIMIT 5";
        }
             
            //System.out.println("sql_select in buyerList of pathfinder.functions.revenue user : "+sql_select);
            rs = stmt.executeQuery(sql_select);
            while(rs.next()){
                
                temp_val =  rs.getString(1);
                if(rs.wasNull()) {
                    temp_val="";
                }
                buyer_id.add(temp_val);
                
                temp_val =  rs.getString(2);
                if(rs.wasNull()) {
                    temp_val="";
                }
                buyer_name.add(temp_val);
                
                temp_val =  rs.getString(3);
                if(rs.wasNull()) {
                    temp_val="";
                }
                buyer_address.add(temp_val);
                
                temp_val =  rs.getString(4);
                if(rs.wasNull()) {
                    temp_val="";
                }
                buyer_city.add(temp_val);
                
                temp_val =  rs.getString(5);
                if(rs.wasNull()) {
                    temp_val="";
                }
                buyer_country.add(temp_val);
                
                temp_val =  rs.getString(6);
                if(rs.wasNull()) {
                    temp_val="";
                }
                buyer_state.add(temp_val);
                
                temp_val =  rs.getString(7);
                if(rs.wasNull()) {
                    temp_val="";
                }
                buyer_zipcode.add(temp_val);
                
                temp_val =  rs.getString(8);
                if(rs.wasNull()) {
                    temp_val="";
                }
                buyer_phone.add(temp_val);
                
                temp_val =  rs.getString(9);
                if(rs.wasNull()) {
                    temp_val="";
                }
                buyer_fax.add(temp_val);
                buyer_type.add("user");
            }

            //select contact type=buyer from contacts table
            sql_select = "";
            sql_from = "";
            sql_where ="" ;
            sql_select = "select c.contact_id,concat(firstname,' ', surname),concat(address_1,',', address_2),city,country,state,zipcode,concat(phone_primary,'(primary), ', phone_secondary,'(secondary)'),concat(fax1,', ', fax2,', ',fax3),company,c.is_person ";
            sql_from = "from contacts c, contacttype_map cm, contacts_type_master ctm ";
            sql_where = "c.contact_id = cm.contact_id and cm.type_id = ctm.type_id and cm.type_id = 3 AND (c.is_person='1' OR c.is_person='2') ";





            if(searchKey!=null && !searchKey.equals(""))
            {
                sql_where += " and (firstname LIKE '%"+searchKey+"%' or company LIKE '%"+searchKey+"%' )  ";

            }
            String isPerson = "";
            sql_select += sql_from+"where "+sql_where;

               if(isSuggest.equals("1"))
        {
            sql_select = sql_select + " LIMIT 5";
        }

            //System.out.println("sql_select in buyer contact list of pathfinder.functions.revenue contacts : "+sql_select);
            rs = stmt.executeQuery(sql_select);
            while(rs.next()){

                temp_val = rs.getString(11);
                 if(rs.wasNull()) {
                    temp_val="";
                }
                isPerson = temp_val;
                buyer_person.add(temp_val);

                temp_val =  rs.getString(1);
                if(rs.wasNull()) {
                    temp_val="";
                }
                buyer_id.add(temp_val);

                if(isPerson.equals("1")) //person
                {
                temp_val =  splChar.decoding(rs.getString(2));
                if(rs.wasNull()) {
                    temp_val="";
                }
                buyer_name.add(temp_val);
                }
                else if(isPerson.equals("2")) //company
                {
                     temp_val =  rs.getString(10);
                if(rs.wasNull()) {
                    temp_val="";
                }
               buyer_name.add(temp_val);
                }

                
                temp_val =  rs.getString(3);
                if(rs.wasNull()) {
                    temp_val="";
                }
                buyer_address.add(temp_val);

                temp_val =  rs.getString(4);
                if(rs.wasNull()) {
                    temp_val="";
                }
                buyer_city.add(temp_val);

                temp_val =  rs.getString(5);
                if(rs.wasNull()) {
                    temp_val="";
                }
                buyer_country.add(temp_val);

                temp_val =  rs.getString(6);
                if(rs.wasNull()) {
                    temp_val="";
                }
                buyer_state.add(temp_val);

                temp_val =  rs.getString(7);
                if(rs.wasNull()) {
                    temp_val="";
                }
                buyer_zipcode.add(temp_val);

                temp_val =  rs.getString(8);
                if(rs.wasNull()) {
                    temp_val="";
                }
                buyer_phone.add(temp_val);

                temp_val =  rs.getString(9);
                if(rs.wasNull()) {
                    temp_val="";
                }
                buyer_fax.add(temp_val);
                buyer_type.add("contacts");
            }

            rs.close();
            stmt.close();
            con.close();
        }catch(SQLException sqle){
            System.out.println("SQLException in collecting buyerList:"+sqle);
        }catch(Exception e){
            System.out.println("Exception in collecting buyerList:"+e);
        }
    }    
}
