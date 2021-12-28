/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder.functions.revenue;

import java.io.Serializable;
import java.util.*;
import connection.DBconnection;
import java.sql.*;

/**
 *
 * @author ramesh
 * class to retrieve the Contact Name List based on their type or whole list
 */
public class CurrencyList implements Serializable {

//Values from look up table for the currency table
    private List currencyId = new ArrayList();
    private List short_cur_name = new ArrayList();
    private List long_cur_name = new ArrayList();
    private List cur_symbol = new ArrayList();
    private List cur_val = new ArrayList();
    private String temp_val = "";
    DBconnection dbcon = new DBconnection();
    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private String sql_select = "";
    private String sql_from = "";
    private String sql_where = "";
    private ArrayList currency_code = new ArrayList();
    private ArrayList currency_description = new ArrayList();
    private ArrayList currency_html_code = new ArrayList();
    private String currencyCode = "";
    private String currencyUnicode = "";

    public List getCurrencyID() {
        //System.out.println("currencyId in currencyList of pathfinder.functions.revenue : " + currencyId);
        return currencyId;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyUnicode() {
        return currencyUnicode;
    }

    public void setCurrencyUnicode(String currencyUnicode) {
        this.currencyUnicode = currencyUnicode;
    }

    public ArrayList getCurrency_code() {
        return currency_code;
    }

    public void setCurrency_code(ArrayList currency_code) {
        this.currency_code = currency_code;
    }

    public ArrayList getCurrency_description() {
        return currency_description;
    }

    public void setCurrency_description(ArrayList currency_description) {
        this.currency_description = currency_description;
    }

    public ArrayList getCurrency_html_code() {
        return currency_html_code;
    }

    public void setCurrency_html_code(ArrayList currency_html_code) {
        this.currency_html_code = currency_html_code;
    }

    public void setCurrencyID(List currencyId) {
        this.currencyId = currencyId;
    }

    public List getCurrencyShortName() {
        return short_cur_name;
    }

    public void setCurrencyShortName(List short_cur_name) {
        this.short_cur_name = short_cur_name;
    }

    public List getCurrencyLongName() {
        return long_cur_name;
    }

    public void setCurrencyLongName(List long_cur_name) {
        this.long_cur_name = long_cur_name;
    }

    public List getCurrencySymbol() {
        return cur_symbol;
    }

    public void setCurrencySymbol(List cur_symbol) {
        this.cur_symbol = cur_symbol;
    }

    public List getCurrencyValue() {
        return cur_val;
    }

    public void setCurrencyValue(List cur_val) {
        this.cur_val = cur_val;
    }

    public void collectCurrencyList() {

        try {
            con = dbcon.getSampleProperty();
            stmt = con.createStatement();

            sql_select = "select currency_id,short_name,long_name,symbol,value ";
            sql_from = "from currency ";

            String where = "";
            String condition = "";

            currencyId.clear();
            short_cur_name.clear();
            long_cur_name.clear();
            cur_symbol.clear();
            cur_val.clear();

            sql_select += sql_from;
            //System.out.println("sql_select in currencyList of pathfinder.functions.revenue : " + sql_select);
            rs = stmt.executeQuery(sql_select);
            while (rs.next()) {

                temp_val = rs.getString(1);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                currencyId.add(temp_val);

                temp_val = rs.getString(2);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                short_cur_name.add(temp_val);

                temp_val = rs.getString(3);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                long_cur_name.add(temp_val);

                temp_val = rs.getString(4);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                cur_symbol.add(temp_val);

                temp_val = rs.getString(5);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                else if(temp_val.equals("0"))
                    temp_val="";
                
                cur_val.add(temp_val);

            }
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException sqle) {
            System.out.println("SQLException in collecting CurrencyList:" + sqle);
        } catch (Exception e) {
            System.out.println("Exception in collecting CurrencyList:" + e);
        }
    }

    public int saveCurrency() {
        //System.out.println("saveCurrency");

        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;
        DBconnection dbcon = new DBconnection();
        String sql = "";
        int recordsUpdated = 0;

        try {


            con = dbcon.getSampleProperty();


            for (int i = 0; i < long_cur_name.size(); i++) {
                sql = "INSERT INTO currency(short_name,long_name,symbol,VALUE) VALUES(?,?,?,?)";


                stmt = con.prepareStatement(sql);
                stmt.setString(1, short_cur_name.get(i).toString());
                stmt.setString(2, long_cur_name.get(i).toString());
                stmt.setString(3, cur_symbol.get(i).toString());
                stmt.setFloat(4, Float.parseFloat(cur_val.get(i).toString()));

                recordsUpdated = stmt.executeUpdate();
                //System.out.println("----recordsUpdated:" + recordsUpdated);
            }
        } catch (SQLException sqle) {
            System.out.println("SQLException in saveCurrency:" + sqle);
        } catch (Exception e) {
            System.out.println("Exception in saveCurrency:" + e);
        } finally {



            //Close statement
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException se) {
                    System.out.println("Exception while closing Statement:se.getMessage()");
                }
            }

            //Close connection
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException se) {
                    System.out.println("Exception while closing connection:se.getMessage()");
                }
            }

        }

        return recordsUpdated;
    }

    public int updateCurrecny() {

        //System.out.println("updateCurrecnies");

        Connection con = null;
        ResultSet rs = null;
        Statement stmt = null;
        DBconnection dbcon = new DBconnection();
        String sql = "";
        int recordsUpdated = 0;

        try {


            con = dbcon.getSampleProperty();
            stmt = con.createStatement();

            //System.out.println("CurrenctyID size:" + currencyId.size());
            for (int i = 0; i < currencyId.size(); i++) {
                int curVal = 0;
                if(cur_val.get(i).equals(""))
                    curVal = 0;
                else
                    curVal = Integer.parseInt(cur_val.get(i).toString());

                sql = "UPDATE currency "
                        + "SET short_name='" + short_cur_name.get(i) + "',"
                        + "long_name='" + long_cur_name.get(i) + "', "
                        + "symbol='" + cur_symbol.get(i) + "', "
                        + "VALUE= " + curVal + " "
                        + "WHERE currency_id=" + currencyId.get(i) + "";
                //System.out.println("sql:" + sql);
                stmt.addBatch(sql);

               

            }
            int[] count = stmt.executeBatch();
            
            for (int i = 0; i < count.length; i++) {

                if (count[i] == 1) {
                    recordsUpdated++;
                }
            }
            //System.out.println("----recordsUpdated:" + recordsUpdated);

        } catch (SQLException sqle) {
            System.out.println("SQLException in updateCurrecny:" + sqle);
        } catch (Exception e) {
            System.out.println("Exception in updateCurrecny:" + e);
        } finally {



            //Close statement
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException se) {
                    System.out.println("Exception while closing Statement:se.getMessage()");
                }
            }

            //Close connection
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException se) {
                    System.out.println("Exception while closing connection:se.getMessage()");
                }
            }

        }

        return recordsUpdated;
    }

    public void GetCurrencySymbols() {
        try {
            con = dbcon.getSampleProperty();
            stmt = con.createStatement();

            sql_select = "SELECT currency_code, description, html_code FROM currency_symbols";

            //System.out.println("Query to get Currency Symbols : " + sql_select);

            rs = stmt.executeQuery(sql_select);

            while (rs.next()) {

                temp_val = rs.getString(1);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                currency_code.add(temp_val);

                temp_val = rs.getString(2);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                currency_description.add(temp_val);

                temp_val = rs.getString(3);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                currency_html_code.add(temp_val);
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException sqle) {
            System.out.println("SQLException in CurrencyList.java - GetCurrencySymbols : " + sqle);
        } catch (Exception e) {
            System.out.println("Exception in CurrencyList.java - GetCurrencySymbols : " + e);
        }
    }
    public void GetUnicodeForCurrencyCode() {
        try {
            con = dbcon.getSampleProperty();
            stmt = con.createStatement();

            sql_select = "SELECT UNICODE FROM currency_symbols WHERE currency_code='"+currencyCode+"'";

            //System.out.println("Query to get Currency Unicode : " + sql_select);

            rs = stmt.executeQuery(sql_select);

            while (rs.next()) {

                temp_val = rs.getString(1);
                if (rs.wasNull()) {
                    temp_val = "";
                }
                currencyUnicode = temp_val;
            }
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException sqle) {
            System.out.println("SQLException in CurrencyList.java - GetUnicodeForCurrencyCode : " + sqle);
        } catch (Exception e) {
            System.out.println("Exception in CurrencyList.java - GetUnicodeForCurrencyCode : " + e);
        }
    }
    public String unicodeToCharacter(String unicode, String formatFlag){
        String currencySymbol = "";
        if(formatFlag.equals("1") || formatFlag == "1") {
            char currencyCharacter = (char) Integer.parseInt( unicode.substring(2), 16 );
            currencySymbol = currencyCharacter+" ";
        } else {
            currencySymbol = unicode+" ";
        }
        return currencySymbol;
    }
}
