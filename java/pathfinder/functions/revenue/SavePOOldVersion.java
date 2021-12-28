/*
 * SavePO.java
 *
 * Created on February 17, 2010, 6:08 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pathfinder.functions.revenue;

//import pathfinder.functions.revenue.*;
import java.io.Serializable;
import connection.DBconnection;
import java.sql.*;
import java.util.*;
/**
 *
 * @author ramyamaims
 */
public class SavePOOldVersion implements Serializable {
    connection.DBconnection conProp = new connection.DBconnection();
    POInfo poInfo = new POInfo();
    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private int intAddRes = 0;
    private int intTaxRes = 0;
    private int intItemRes = 0;
    
    private List log_table_name = new ArrayList();
    private List log_field_name = new ArrayList();
    private List log_linked_field_name = new ArrayList();
    private List log_old_value = new ArrayList();
    private List log_new_value = new ArrayList();
    private List log_changed_by = new ArrayList();
    
    private int po_number = 0;
    private String proj_id = "";
    private String text = "";
    private String buyer = "";
    private String shipping_address = "";
    private String shipping_city = "";
    private String shipping_country = "";
    private String shipping_state = "";
    private String shipping_zipcode = "";
    private String shipping_phone = "";
    private String shipping_fax = "";
    private String buyer_type = "";
    private String mode_of_payment = "";
    private String seller = "";
    private String seller_address = "";
    private String seller_city = "";
    private String seller_country = "";
    private String seller_state = "";
    private String seller_zipcode = "";
    private String seller_phone = "";
    private String seller_fax = "";
    private String seller_type = "";
    private String mode_of_transport = "";
    private String po_currency = "";
    private String terms_and_condition = "";
    private String status = "";
    private String po_value = "";
        
    private String mod_proj_id = "";
    private String mod_buyer = "";
    private String mod_shipping_address = "";
    private String mod_shipping_city = "";
    private String mod_shipping_country = "";
    private String mod_shipping_state = "";
    private String mod_shipping_zipcode = "";
    private String mod_shipping_phone = "";
    private String mod_shipping_fax = "";
    private String mod_buyer_type = "";
    private String mod_mode_of_payment = "";
    private String mod_seller = "";
    private String mod_seller_address = "";
    private String mod_seller_city = "";
    private String mod_seller_country = "";
    private String mod_seller_state = "";
    private String mod_seller_zipcode = "";
    private String mod_seller_phone = "";
    private String mod_seller_fax = "";
    private String mod_seller_type = "";
    private String mod_mode_of_transport = "";
    private String mod_po_currency = "";
    private String mod_terms_and_condition = "";
    private String mod_status = "";
    private String mod_po_value = "";
    
    private String getTaxParam = "";
    private String getHidTaxParam = "";
    private String getTaxValueParam = "";
    private String getTaxPriceParam = "";
    
    private String mod_getTaxParam = "";
    private String mod_getHidTaxParam = "";
    private String mod_getTaxValueParam = "";
    private String mod_getTaxPriceParam = "";
        
    private String getItemParam = "";
    private String getHidItemParam = "";
    private String getUnitPriceParam = "";
    private String getQuantityParam = "";
    private String getDescParam = "";
    private String getTotalParam = "";
    
    private String mod_getItemParam = "";
    private String mod_getHidItemParam = "";
    private String mod_getUnitPriceParam = "";
    private String mod_getQuantityParam = "";
    private String mod_getDescParam = "";
    private String mod_getTotalParam = "";
    
    public void setProjId(String proj_id){
        this.proj_id = proj_id;
    }
    public void setBuyer(String buyer){
        this.buyer = buyer;
    }
    public void setShipAdd(String shipping_address){
        this.shipping_address = shipping_address;
    }
    public void setShipCity(String shipping_city){
        this.shipping_city = shipping_city;
    }
    public void setShipCountry(String shipping_country){
        this.shipping_country = shipping_country;
    }
    public void setShipState(String shipping_state){
        this.shipping_state = shipping_state;
    }
    public void setShipZipcode(String shipping_zipcode){
        this.shipping_zipcode = shipping_zipcode;
    }
    public void setShipPhone(String shipping_phone){
        this.shipping_phone = shipping_phone;
    }
    public void setShipFax(String shipping_fax){
        this.shipping_fax = shipping_fax;
    }
    public void setPayMode(String mode_of_payment){
        this.mode_of_payment = mode_of_payment;
    }
    public void setBuyerType(String buyer_type){
        this.buyer_type = buyer_type;
    }
    public void setSellerType(String seller_type){
        this.seller_type = seller_type;
    }
    public void setSeller(String seller){
        this.seller = seller;
    }
    public void setSellerAdd(String seller_address){
        this.seller_address = seller_address;
    }
    public void setSellerCity(String seller_city){
        this.seller_city = seller_city;
    }
    public void setSellerCountry(String seller_country){
        this.seller_country = seller_country;
    }
    public void setSellerState(String seller_state){
        this.seller_state = seller_state;
    }
    public void setSellerZipcode(String seller_zipcode){
        this.seller_zipcode = seller_zipcode;
    }
    public void setSellerPhone(String seller_phone){
        this.seller_phone = seller_phone;
    }
    public void setSellerFax(String seller_fax){
        this.seller_fax = seller_fax;
    }
    public void setTransMode(String mode_of_transport){
        this.mode_of_transport = mode_of_transport;
    }
    public void setPOCurrency(String po_currency){
        this.po_currency = po_currency;
    }
    public void setTermsAndCond(String terms_and_condition){
        this.terms_and_condition = terms_and_condition;
    }
    public void setStatus(String status){
        this.status = status;
    }
    public void setPOValue(String po_value){
        this.po_value = po_value;
    }
    
    public void setPONumber(int po_number){
        this.po_number = po_number;
    }
    
    public void setType(String text){
        this.text = text;
    }
    
    public void setTaxId(String getTaxParam){
        this.getTaxParam = getTaxParam;
    }
    
    public void setHidTaxId(String getHidTaxParam){
        this.getHidTaxParam = getHidTaxParam;
    }
    public void setTaxValue(String getTaxValueParam){
        this.getTaxValueParam = getTaxValueParam;
    }
    public void setTaxPrice(String getTaxPriceParam){
        this.getTaxPriceParam = getTaxPriceParam;
    }
    
    public void setItemId(String getItemParam){
        this.getItemParam = getItemParam;
    }
    public void setHidItemId(String getHidItemParam){
        this.getHidItemParam = getHidItemParam;
    }
    public void setUnitPrice(String getUnitPriceParam){
        this.getUnitPriceParam = getUnitPriceParam;
    }
    public void setQuantity(String getQuantityParam){
        this.getQuantityParam = getQuantityParam;
    }
    public void setDesc(String getDescParam){
        this.getDescParam = getDescParam;
    }
    public void setTotal(String getTotalParam){
        this.getTotalParam = getTotalParam;
    }
    
    public int getAddPO(){
        return intAddRes;
    }
    
    public int getAddPOLineItem(){
        return intItemRes;
    }
    
    public int getAddPOTaxes(){
        return intTaxRes;
    }
    
    public int getPONumber(){
        return po_number;
    }
    
    public void addPO(){
        
        log_table_name.clear();
        log_field_name.clear();
        log_linked_field_name.clear();
        log_old_value.clear();
        log_new_value.clear();
        log_changed_by.clear();
        
        try {
            con = conProp.getSampleProperty();
            stmt = con.createStatement();
            
            mod_proj_id = "";
            mod_buyer = "";
            mod_shipping_address = "";
            mod_shipping_city = "";
            mod_shipping_country = "";
            mod_shipping_state = "";
            mod_shipping_zipcode = "";
            mod_shipping_phone = "";
            mod_shipping_fax = "";
            mod_buyer_type = "";
            mod_mode_of_payment = "";
            mod_seller = "";
            mod_seller_address = "";
            mod_seller_city = "";
            mod_seller_country = "";
            mod_seller_state = "";
            mod_seller_zipcode = "";
            mod_seller_phone = "";
            mod_seller_fax = "";
            mod_seller_type = "";            
            mod_mode_of_transport = "";
            mod_po_currency = "";
            mod_terms_and_condition = "";
            mod_status = "";
            mod_po_value = "";
                                
            String updateBuyer_Sql="";
            String updateField="";
            updateBuyer_Sql=" update purchase_order set ";
            String where = " where po_number="+po_number+" ";
            
            if(text.equals("modify")){
                
                poInfo.setPONumber(String.valueOf(po_number));
                poInfo.getPOInfo();
                
                mod_proj_id = " '"+poInfo.getProjId()+"' ";
                mod_buyer = " '"+poInfo.getBuyerID()+"' ";
                mod_shipping_address = " '"+poInfo.getBuyerAdd()+"' ";
                mod_shipping_city = " '"+poInfo.getBuyerCity()+"' ";
                mod_shipping_country = " '"+poInfo.getBuyerCountry()+"' ";
                mod_shipping_state = " '"+poInfo.getBuyerState()+"' ";
                mod_shipping_zipcode = " '"+poInfo.getBuyerZip()+"' ";
                mod_shipping_phone = " '"+poInfo.getBuyerPhone()+"' ";
                mod_shipping_fax = " '"+poInfo.getBuyerFax()+"' ";
                mod_buyer_type = " '"+poInfo.getBuyerType()+"' ";
                mod_mode_of_payment = " '"+poInfo.getPayID()+"' ";
                mod_seller = " '"+poInfo.getSellerID()+"' ";
                mod_seller_address = " '"+poInfo.getSellerAdd()+"' ";
                mod_seller_city = " '"+poInfo.getSellerCity()+"' ";
                mod_seller_country = " '"+poInfo.getSellerCountry()+"' ";
                mod_seller_state = " '"+poInfo.getSellerState()+"' ";
                mod_seller_zipcode = " '"+poInfo.getSellerZip()+"' ";
                mod_seller_phone = " '"+poInfo.getSellerPhone()+"' ";
                mod_seller_fax = " '"+poInfo.getSellerFax()+"' ";
                mod_seller_type = " '"+poInfo.getSellerType()+"' ";
                mod_mode_of_transport = " '"+poInfo.getDelID()+"' ";
                mod_po_currency = " '"+poInfo.getCurrencyId()+"' ";
                mod_terms_and_condition = " '"+poInfo.getTermsID()+"' ";
                mod_status = " '"+poInfo.getStatusID()+"' ";
                mod_po_value = " '"+poInfo.getPOValue()+"' ";
            }
            
            if(text.equals("")){
                rs = stmt.executeQuery("select max(po_number) from purchase_order");
            
                while(rs.next()){
                    po_number = rs.getInt(1);
                    if(rs.wasNull()){
                        po_number = 0;
                    }
                }
            
                po_number++;
            }
            //System.out.println("po_number : "+po_number);
           
            
            if(proj_id.equals("") || proj_id.equals("All")){
                proj_id = null;
            }else{
                proj_id=" '"+proj_id+"' ";
                if(text.equals("modify")){
                    
                    if(!proj_id.equals(mod_proj_id)){
                       log_table_name.add("purchase_order");
                       log_field_name.add("proj_id");
                       log_linked_field_name.add(String.valueOf(po_number));
                       log_old_value.add(mod_proj_id);
                       log_new_value.add(proj_id);
                       log_changed_by.add("");        
                    }
                    
                    if(updateField.length()>0){
                        updateField += ", proj_id="+proj_id+" ";
                    }else{
                        updateField += " proj_id="+proj_id+" ";
                    }
                }
            }
                                    
            //System.out.println("updateField : "+updateField.length());
            
            //System.out.println("proj_id : "+proj_id);
            //System.out.println("updateField : "+updateField);
            
            if(buyer_type.equals("") || buyer_type.equals("All")){
                buyer_type = null;
            }else{
                buyer_type=" '"+buyer_type+"' ";
            }
            
            if(shipping_address.equals("") || shipping_address.equals("All")){
                shipping_address = null;
            }else{
                shipping_address=" '"+shipping_address+"' ";
            }
            
            if(shipping_city.equals("") || shipping_city.equals("All")){
                shipping_city = null;
            }else{
                shipping_city=" '"+shipping_city+"' ";                
            }
            
            if(shipping_country.equals("") || shipping_country.equals("All")){
                shipping_country = null;
            }else{
                shipping_country=" '"+shipping_country+"' ";
            }
            
            if(shipping_state.equals("") || shipping_state.equals("All")){
                shipping_state = null;
            }else{
                shipping_state=" '"+shipping_state+"' ";
            }
            
            if(shipping_zipcode.equals("") || shipping_zipcode.equals("All")){
                shipping_zipcode = null;
            }else{
                shipping_zipcode=" '"+shipping_zipcode+"' ";
            }
            
            if(shipping_phone.equals("") || shipping_phone.equals("All")){
                shipping_phone = null;
            }else{
                shipping_phone=" '"+shipping_phone+"' ";
            }
            
            if(shipping_fax.equals("") || shipping_fax.equals("All")){
                shipping_fax = null;
            }else{
                shipping_fax=" '"+shipping_fax+"' ";                
            }
            
            if(buyer.equals("") || buyer.equals("All")){
                buyer = null;
            }else{
                buyer=" '"+buyer+"' ";
                
                if(text.equals("modify")){
                    
                    if(updateField.length()>0){
                        updateField += ", buyer_id="+buyer+" ";
                    }else{
                        updateField += " buyer_id="+buyer+" ";
                    }
                    
                    if(updateField.length()>0){
                        updateField += ", shipping_address="+shipping_address+" ";
                    }else{
                        updateField += " shipping_address="+shipping_address+" ";
                    }
                    
                    if(updateField.length()>0){
                        updateField += ", shipping_city="+shipping_city+" ";
                    }else{
                        updateField += " shipping_city="+shipping_city+" ";
                    }
                    
                    if(updateField.length()>0){
                        updateField += ", shipping_country="+shipping_country+" ";
                    }else{
                        updateField += " shipping_country="+shipping_country+" ";
                    }
                                     
                    if(updateField.length()>0){
                        updateField += ", shipping_state="+shipping_state+" ";
                    }else{
                        updateField += " shipping_state="+shipping_state+" ";
                    }
                    
                    if(updateField.length()>0){
                        updateField += ", shipping_zipcode="+shipping_zipcode+" ";
                    }else{
                        updateField += " shipping_zipcode="+shipping_zipcode+" ";
                    }
                    
                    if(updateField.length()>0){
                        updateField += ", shipping_phone="+shipping_phone+" ";
                    }else{
                        updateField += " shipping_phone="+shipping_phone+" ";
                    }
                    
                    if(updateField.length()>0){
                        updateField += ", shipping_fax="+shipping_fax+" ";
                    }else{
                        updateField += " shipping_fax="+shipping_fax+" ";
                    }
                    
                    if(updateField.length()>0){
                        updateField += ", buyer_type="+buyer_type+" ";
                    }else{
                        updateField += " buyer_type="+buyer_type+" ";
                    }
                }
            }
            
            //System.out.println("buyer : "+buyer);
            //System.out.println("updateField : "+updateField);
            
            if(mode_of_payment.equals("") || mode_of_payment.equals("All")){
                mode_of_payment = null;
            }else{
                mode_of_payment=" '"+mode_of_payment+"' ";
                if(text.equals("modify")){
                    if(updateField.length()>0){
                        updateField += ", mode_of_payment="+mode_of_payment+" ";
                    }else{
                        updateField += " mode_of_payment="+mode_of_payment+" ";
                    }
                }
            }
            
            if(seller_type.equals("") || seller_type.equals("All")){
                seller_type = null;
            }else{
                seller_type=" '"+seller_type+"' ";
            }
            
            if(seller_address.equals("") || seller_address.equals("All")){
                seller_address = null;
            }else{
                seller_address=" '"+seller_address+"' ";
            }
            
            //System.out.println("seller_address : "+seller_address);
            if(seller_city.equals("") || seller_city.equals("All")){
                seller_city = null;
            }else{
                seller_city=" '"+seller_city+"' ";
            }
            
            if(seller_country.equals("") || seller_country.equals("All")){
                seller_country = null;
            }else{
                seller_country=" '"+seller_country+"' ";
            }
            
            if(seller_state.equals("") || seller_state.equals("All")){
                seller_state = null;
            }else{
                seller_state=" '"+seller_state+"' ";
            }
            
            if(seller_zipcode.equals("") || seller_zipcode.equals("All")){
                seller_zipcode = null;
            }else{
                seller_zipcode=" '"+seller_zipcode+"' ";
            }
            
            if(seller_phone.equals("") || seller_phone.equals("All")){
                seller_phone = null;
            }else{
                seller_phone=" '"+seller_phone+"' ";
            }
            
            if(seller_fax.equals("") || seller_fax.equals("All")){
                seller_fax = null;
            }else{
                seller_fax=" '"+seller_fax+"' ";
            }
            
            if(seller.equals("") || seller.equals("All")){
                seller = null;
            }else{
                seller=" '"+seller+"' ";
                if(text.equals("modify")){
                    if(updateField.length()>0){
                        updateField += ", seller_id="+seller+" ";
                    }else{
                        updateField += " seller_id="+seller+" ";
                    }
                    
                    if(updateField.length()>0){
                        updateField += ", seller_address="+seller_address+" ";
                    }else{
                        updateField += " seller_address="+seller_address+" ";
                    }
                    
                    if(updateField.length()>0){
                        updateField += ", seller_city="+seller_city+" ";
                    }else{
                        updateField += " seller_city="+seller_city+" ";
                    }
                    
                    if(updateField.length()>0){
                        updateField += ", seller_country="+seller_country+" ";
                    }else{
                        updateField += " seller_country="+seller_country+" ";
                    }
                    
                    if(updateField.length()>0){
                        updateField += ", seller_type="+seller_type+" ";
                    }else{
                        updateField += " seller_type="+seller_type+" ";
                    }
                    
                    if(updateField.length()>0){
                        updateField += ", seller_state="+seller_state+" ";
                    }else{
                        updateField += " seller_state="+seller_state+" ";
                    }
                    
                    if(updateField.length()>0){
                        updateField += ", seller_zipcode="+seller_zipcode+" ";
                    }else{
                        updateField += " seller_zipcode="+seller_zipcode+" ";
                    }
                    
                    if(updateField.length()>0){
                        updateField += ", seller_phone="+seller_phone+" ";
                    }else{
                        updateField += " seller_phone="+seller_phone+" ";
                    }
                    
                    if(updateField.length()>0){
                        updateField += ", seller_fax="+seller_fax+" ";
                    }else{
                        updateField += " seller_fax="+seller_fax+" ";
                    }
                }
            }
            
            //System.out.println("seller_address : "+seller_address);
            
            
            if(mode_of_transport.equals("") || mode_of_transport.equals("All")){
                mode_of_transport = null;
            }else{
                mode_of_transport=" '"+mode_of_transport+"' ";
                if(text.equals("modify")){
                    if(updateField.length()>0){
                        updateField += ", mode_of_transport="+mode_of_transport+" ";
                    }else{
                        updateField += " mode_of_transport="+mode_of_transport+" ";
                    }
                }
            }
            
            if(po_currency.equals("") || po_currency.equals("All")){
                po_currency = null;
            }else{
                po_currency=" '"+po_currency+"' ";
                if(text.equals("modify")){
                    if(updateField.length()>0){
                        updateField += ", po_currency="+po_currency+" ";
                    }else{
                        updateField += " po_currency="+po_currency+" ";
                    }
                }
            }
            
            if(status.equals("") || status.equals("All")){
                status = null;
            }else{
                status=" '"+status+"' ";
                if(text.equals("modify")){
                    if(updateField.length()>0){
                        updateField += ", status="+status+" ";
                    }else{
                        updateField += " status="+status+" ";
                    }
                }
            }
            
            if(terms_and_condition.equals("") || terms_and_condition.equals("All")){
                terms_and_condition = null;
            }else{
                terms_and_condition=" '"+terms_and_condition+"' ";
                if(text.equals("modify")){
                    if(updateField.length()>0){
                        updateField += ", terms_and_condition="+terms_and_condition+" ";
                    }else{
                        updateField += " terms_and_condition="+terms_and_condition+" ";
                    }
                }
            }
            
            if(po_value.equals("") || po_value.equals("All")){
                po_value = null;
            }else{
                po_value=" '"+po_value+"' ";
                if(text.equals("modify")){
                    if(updateField.length()>0){
                        updateField += ", po_value="+po_value+" ";
                    }else{
                        updateField += " po_value="+po_value+" ";
                    }
                }
            }
            
            intAddRes = 0;
            if(text.equals("modify")){
                updateBuyer_Sql=updateBuyer_Sql+updateField+where;
                //System.out.println("updateBuyer_Sql : "+updateBuyer_Sql);
                intAddRes = stmt.executeUpdate(updateBuyer_Sql);
            }else{
                /*System.out.println("insert into purchase_order(po_number,po_date,proj_id,buyer_type,buyer_id,shipping_address,shipping_city,shipping_country," +
                    "shipping_state,shipping_zipcode,shipping_phone,shipping_fax,mode_of_payment,seller_type,seller_id,seller_address,seller_city," +
                    "seller_country,seller_state,seller_zipcode,seller_phone,seller_fax,mode_of_transport,po_currency,terms_and_condition," +
                    "status,po_value) " +
                    "values('"+po_number+"',current_timestamp(),"+proj_id+","+buyer_type+","+buyer+","+shipping_address+","+shipping_city+","+shipping_country+"," +
                    ""+shipping_state+","+shipping_zipcode+","+shipping_phone+","+shipping_fax+","+mode_of_payment+","+seller_type+","+seller+","+seller_address+","+seller_city+"," +
                    ""+seller_country+","+seller_state+","+seller_zipcode+","+seller_phone+","+seller_fax+","+mode_of_transport+","+po_currency+","+terms_and_condition+"," +
                    ""+status+","+po_value+")");*/
            intAddRes = stmt.executeUpdate("insert into purchase_order(po_number,po_date,proj_id,buyer_type,buyer_id,shipping_address,shipping_city,shipping_country," +
                    "shipping_state,shipping_zipcode,shipping_phone,shipping_fax,mode_of_payment,seller_type,seller_id,seller_address,seller_city," +
                    "seller_country,seller_state,seller_zipcode,seller_phone,seller_fax,mode_of_transport,po_currency,terms_and_condition," +
                    "status,po_value) " +
                    "values('"+po_number+"',current_timestamp(),"+proj_id+","+buyer_type+","+buyer+","+shipping_address+","+shipping_city+","+shipping_country+"," +
                    ""+shipping_state+","+shipping_zipcode+","+shipping_phone+","+shipping_fax+","+mode_of_payment+","+seller_type+","+seller+","+seller_address+","+seller_city+"," +
                    ""+seller_country+","+seller_state+","+seller_zipcode+","+seller_phone+","+seller_fax+","+mode_of_transport+","+po_currency+","+terms_and_condition+"," +
                    ""+status+","+po_value+")");
            }
            
            //if(!text.equals("modify")){
                rs = stmt.executeQuery("select buyer_type,seller_type from purchase_order where po_number="+po_number+" ");
                
                while(rs.next()){
                    buyer_type = rs.getString(1);
                    seller_type = rs.getString(2);
                }
            //}
            con.close();
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }catch(NullPointerException npe){
            npe.printStackTrace();
        }
    }
    
    public void addPOTaxes(){
        
        try {
            con = conProp.getSampleProperty();
            stmt = con.createStatement();
            
            String updatePOTaxesSql="";
            String updateField="";        
            updatePOTaxesSql=" update po_taxes set ";
            String where = " where po_number="+po_number+" and tax_id = '"+getTaxParam+"' ";
        
            if(getTaxParam.equals("") || getTaxParam.equals("All")){
                getTaxParam = null;
            }else{
                getTaxParam=" '"+getTaxParam+"' ";
            }
            //System.out.println("getTaxParam : "+getTaxParam);
            
            //System.out.println("getHidTaxParam : "+getHidTaxParam);
            if(getHidTaxParam.equals("") || getHidTaxParam.equals("All")){
                getHidTaxParam = null;
            }else{
                getHidTaxParam=" '"+getHidTaxParam+"' ";
                
                if(text.equals("modify")){
                    if(updateField.length()>0){
                        updateField += ", tax_id="+getHidTaxParam+" ";
                    }else{
                        updateField += " tax_id="+getHidTaxParam+" ";
                    }
                }
            }
            
            //System.out.println("getHidTaxParam : "+getHidTaxParam);
            if(getTaxValueParam.equals("") || getTaxValueParam.equals("All")){
                getTaxValueParam = null;
            }else{
                getTaxValueParam=" '"+getTaxValueParam+"' ";
                
                if(text.equals("modify")){
                    if(updateField.length()>0){
                        updateField += ", value="+getTaxValueParam+" ";
                    }else{
                        updateField += " value="+getTaxValueParam+" ";
                    }
                }
            }
            //System.out.println("getTaxValueParam : "+getTaxValueParam);
            
            if(getTaxPriceParam.equals("") || getTaxPriceParam.equals("All")){
                getTaxPriceParam = null;
            }else{
                getTaxPriceParam=" '"+getTaxPriceParam+"' ";
                
                if(text.equals("modify")){
                    if(updateField.length()>0){
                        updateField += ", total="+getTaxPriceParam+" ";
                    }else{
                        updateField += " total="+getTaxPriceParam+" ";
                    }
                }
            }
            //System.out.println("getTaxPriceParam : "+getTaxPriceParam);
            //System.out.println("po_number : "+po_number);
            //System.out.println("text : "+text);
            
            intTaxRes = 0;
            if(text.equals("modify")){
                updatePOTaxesSql=updatePOTaxesSql+updateField+where;
                //System.out.println("updatePOTaxesSql : "+updatePOTaxesSql);
                intTaxRes = stmt.executeUpdate(updatePOTaxesSql);
            }else{
                
                //System.out.println("insert into po_taxes(po_number,tax_id,value,total) " +
                        //"values('"+po_number+"',"+getTaxParam+","+getTaxValueParam+","+getTaxPriceParam+")");
                intTaxRes = stmt.executeUpdate("insert into po_taxes(po_number,tax_id,value,total) " +
                        "values('"+po_number+"',"+getTaxParam+","+getTaxValueParam+","+getTaxPriceParam+")");
            }
            con.close();
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }catch(NullPointerException npe){
            npe.printStackTrace();
        }        
    }
    
    public void addPOLineItem(){
        
        try {
            con = conProp.getSampleProperty();            
            stmt = con.createStatement();
            
            String updatePOLineItems_Sql="";
            String updateField="";
            
            updatePOLineItems_Sql=" update po_lineitems set ";
            String where = " where po_number="+po_number+"  and item_id = '"+getItemParam+"' ";
            
            if(getItemParam.equals("") || getItemParam.equals("All")){
                getItemParam = null;
            }else{
                getItemParam=" '"+getItemParam+"' ";
            }
            
            if(getHidItemParam.equals("") || getHidItemParam.equals("All")){
                getHidItemParam = null;
            }else{
                getHidItemParam=" '"+getHidItemParam+"' ";
                if(text.equals("modify")){
                    if(updateField.length()>0){
                        updateField += ", item_id="+getHidItemParam+" ";
                    }else{
                        updateField += " item_id="+getHidItemParam+" ";
                    }
                }
            }
            
            if(getUnitPriceParam.equals("") || getUnitPriceParam.equals("All")){
                getUnitPriceParam = null;
            }else{
                getUnitPriceParam=" '"+getUnitPriceParam+"' ";
                if(text.equals("modify")){
                    if(updateField.length()>0){
                        updateField += ", rate="+getUnitPriceParam+" ";
                    }else{
                        updateField += " rate="+getUnitPriceParam+" ";
                    }
                }
            }
            
            if(getQuantityParam.equals("") || getQuantityParam.equals("All")){
                getQuantityParam = null;
            }else{
                getQuantityParam=" '"+getQuantityParam+"' ";
                if(text.equals("modify")){
                    if(updateField.length()>0){
                        updateField += ", quantity="+getQuantityParam+" ";
                    }else{
                        updateField += " quantity="+getQuantityParam+" ";
                    }
                }
            }
            
            if(getDescParam.equals("") || getDescParam.equals("All")){
                getDescParam = null;
            }else{
                getDescParam=" '"+getDescParam+"' ";
                if(text.equals("modify")){
                    if(updateField.length()>0){
                        updateField += ", description="+getDescParam+" ";
                    }else{
                        updateField += " description="+getDescParam+" ";
                    }
                }
            }
            
            if(getTotalParam.equals("") || getTotalParam.equals("All")){
                getTotalParam = null;
            }else{
                getTotalParam=" '"+getTotalParam+"' ";
                if(text.equals("modify")){
                    if(updateField.length()>0){
                        updateField += ", total="+getTotalParam+" ";
                    }else{
                        updateField += " total="+getTotalParam+" ";
                    }
                }
            }
            
            intItemRes = 0;
            //System.out.println("text : "+text);
            if(text.equals("modify")){
                updatePOLineItems_Sql=updatePOLineItems_Sql+updateField+where;
                //System.out.println("updatePOLineItems_Sql : "+updatePOLineItems_Sql);
                intItemRes = stmt.executeUpdate(updatePOLineItems_Sql);
            }else{
                
                //System.out.println("insert into po_lineitems(po_number,item_id,rate,quantity,description,total) " +
                        //"values('"+po_number+"',"+getItemParam+","+getUnitPriceParam+","+getQuantityParam+","+getDescParam+","+getTotalParam+")");
                intItemRes = stmt.executeUpdate("insert into po_lineitems(po_number,item_id,rate,quantity,description,total) " +
                        "values('"+po_number+"',"+getItemParam+","+getUnitPriceParam+","+getQuantityParam+","+getDescParam+","+getTotalParam+")");
            }
            con.close();
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }catch(NullPointerException npe){
            npe.printStackTrace();
        }        
    }
}
