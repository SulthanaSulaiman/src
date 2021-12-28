/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author thanuja
 */
public class CreatePOServlet extends HttpServlet {
    private boolean exp;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        javax.servlet.http.HttpSession session = request.getSession();
        String sesEmp = (String) session.getAttribute("theEid");

        int intAddRes = 0;
        int intTaxRes = 0;
        int intItemRes = 0;

        List taxCount = new ArrayList();
        List itemCount = new ArrayList();
        String editParam = "Edit";
        String responseText = "";
        String getEditParam = request.getParameter("toEdit") != null ? request.getParameter("toEdit") : "";//SearchProj
        String getPoNumber = request.getParameter("poNumber") != null ? request.getParameter("poNumber") : "";

        String getProjIdParam = request.getParameter("project_name_hidden") != null ? request.getParameter("project_name_hidden") : "";//SearchProjid

        String getCurrencyParam = request.getParameter("currency") != null ? request.getParameter("currency") : "";
        //out.println("getCurrencyParam:"+getCurrencyParam);
        String getTermsParam = request.getParameter("terms") != null ? request.getParameter("terms") : "";
        String getPOStatusParam = request.getParameter("poStatus") != null ? request.getParameter("poStatus") : "";
        String getDelParam = request.getParameter("del") != null ? request.getParameter("del") : "";
        String getPayParam = request.getParameter("pay") != null ? request.getParameter("pay") : "";
        String getBuyerParam = request.getParameter("buyer_hidden") != null ? request.getParameter("buyer_hidden") : "";
 String getUnit = request.getParameter("unit")!=null?request.getParameter("unit"):"";
        String getSellerParam = request.getParameter("seller_hidden") != null ? request.getParameter("seller_hidden") : "";

        String getGrandTotalParam = request.getParameter("grand_total") != null ? request.getParameter("grand_total") : "";
        String getTaxCountParam = request.getParameter("tax_count");
        if (getTaxCountParam == null || getTaxCountParam.equals("")) {
            getTaxCountParam = "1";
        }
        String getItemCountParam = request.getParameter("item_count"); //!=null?request.getParameter("item_count"):"1";
        if (getItemCountParam == null || getItemCountParam.equals("")) {
            getItemCountParam = "1";
        }
        //out.println("getItemCount:"+getItemCountParam);
        String getBuyerAddParam = request.getParameter("buyer_add") != null ? request.getParameter("buyer_add") : "";
        String getBuyerCityParam = request.getParameter("buyer_city") != null ? request.getParameter("buyer_city") : "";
        String getBuyerStateParam = request.getParameter("buyer_state") != null ? request.getParameter("buyer_state") : "";
        String getBuyerZipParam = request.getParameter("buyer_zip") != null ? request.getParameter("buyer_zip") : "";
        String getBuyerCountryParam = request.getParameter("buyer_country") != null ? request.getParameter("buyer_country") : "";
        String getBuyerPhoneParam = request.getParameter("buyer_phone") != null ? request.getParameter("buyer_phone") : "";
        String getBuyerFaxParam = request.getParameter("buyer_fax") != null ? request.getParameter("buyer_fax") : "";
        String getSellerAddParam = request.getParameter("seller_add") != null ? request.getParameter("seller_add") : "";
        String getSellerCityParam = request.getParameter("seller_city") != null ? request.getParameter("seller_city") : "";
        String getSellerStateParam = request.getParameter("seller_state") != null ? request.getParameter("seller_state") : "";
        String getSellerZipParam = request.getParameter("seller_zip") != null ? request.getParameter("seller_zip") : "";
        String getSellerCountryParam = request.getParameter("seller_country") != null ? request.getParameter("seller_country") : "";
        String getSellerPhoneParam = request.getParameter("seller_phone") != null ? request.getParameter("seller_phone") : "";
        String getSellerFaxParam = request.getParameter("seller_fax") != null ? request.getParameter("seller_fax") : "";
        String getBuyerContactParam = request.getParameter("buyer_contact") != null ? request.getParameter("buyer_contact") : "";
        String getSellerContactParam = request.getParameter("seller_contact") != null ? request.getParameter("seller_contact") : "";


        List getTaxParam = new ArrayList();
        List taxHidParamList = new ArrayList();
        List getTaxValueParam = new ArrayList();
        List getTaxPriceParam = new ArrayList();
        List getItemParam = new ArrayList();
        List itemHidParamList = new ArrayList();
        List getUnitPriceParam = new ArrayList();
        List getUnitParam = new ArrayList();
        List getQuantityParam = new ArrayList();
        List getDescParam = new ArrayList();
        List getTotalParam = new ArrayList();

        String str_getTaxParam = "";
        String str_getTaxValueParam = "";
        String str_getTaxPriceParam = "";
        String str_getItemParam = "";
        String str_getUnitParam="";
        String str_getUnitPriceParam = "";
        String str_getQuantityParam = "";
        String str_getDescParam = "";
        String str_getTotalParam = "";
        String str_getHidTaxParam = "";
        String str_getHidItemParam = "";

        List setTaxType = new ArrayList();
        List setItemType = new ArrayList();

//System.out.println("inside servlet");

        if (request.getParameter("save") != null) {

//System.out.println("Inside Save")            ;

            intAddRes = 0;
            pathfinder.functions.revenue.SavePO savePO = new pathfinder.functions.revenue.SavePO();

            if (editParam.equals(getEditParam)) {
                //System.out.println("ModifyPram:" + getEditParam);
                savePO.setType("modify");
                savePO.setPONumber(Integer.parseInt(getPoNumber));
            }


            savePO.setSesEmp(sesEmp);
            savePO.setProjId(getProjIdParam);

            savePO.setBuyer(getBuyerParam);
            savePO.setShipAdd(getBuyerAddParam);
            savePO.setShipCity(getBuyerCityParam);
            savePO.setShipState(getBuyerStateParam);
            savePO.setShipZipcode(getBuyerZipParam);
            savePO.setShipCountry(getBuyerCountryParam);
            savePO.setShipPhone(getBuyerPhoneParam);
            savePO.setShipFax(getBuyerFaxParam);
            savePO.setSeller(getSellerParam);
            savePO.setSellerAdd(getSellerAddParam);
            savePO.setSellerCity(getSellerCityParam);
            savePO.setSellerState(getSellerStateParam);
            savePO.setSellerZipcode(getSellerZipParam);
            savePO.setSellerCountry(getSellerCountryParam);
            savePO.setSellerPhone(getSellerPhoneParam);
            savePO.setSellerFax(getSellerFaxParam);
            savePO.setStatus(getPOStatusParam);
            savePO.setTermsAndCond(getTermsParam);
            savePO.setTransMode(getDelParam);
            savePO.setPOCurrency(getCurrencyParam);
            savePO.setPOValue(getGrandTotalParam);
            savePO.setPayMode(getPayParam);
            //out.println("getBuyerContactParam : "+getBuyerContactParam);
            savePO.setBuyerType(getBuyerContactParam);
            savePO.setSellerType(getSellerContactParam);

            // if(intAddRes!=0){

            taxCount.clear();
            itemCount.clear();

            // to add values of different taxes to arraylist and set to ths SavePO.java
            for (int loop = 0; loop < Integer.parseInt(getTaxCountParam); loop++) {

                intTaxRes = 0;
                str_getTaxParam = request.getParameter("tax" + loop) != null ? request.getParameter("tax" + loop) : "";
                str_getHidTaxParam = request.getParameter("edit_hid_tax" + loop) != null ? request.getParameter("edit_hid_tax" + loop) : "";
                str_getTaxValueParam = request.getParameter("taxValue" + loop) != null ? request.getParameter("taxValue" + loop) : "0";
                str_getTaxPriceParam = request.getParameter("taxPrice" + loop) != null ? request.getParameter("taxPrice" + loop) : "0.00";

                if (!str_getHidTaxParam.equals("") && (str_getTaxParam.equals("") || str_getTaxParam.equals("SELECT"))) {

                    setTaxType.add("delete");
                    taxHidParamList.add(str_getHidTaxParam);

                    getTaxParam.add(str_getTaxParam);

                    getTaxValueParam.add(str_getTaxValueParam);
                    getTaxPriceParam.add(str_getTaxPriceParam);
                    taxCount.add("");//to insert a new tax value, for updating the arraylist is added with string value modify
                } else if (!str_getTaxPriceParam.equals("") && Float.parseFloat(str_getTaxValueParam) > 0 && Double.parseDouble(str_getTaxPriceParam) > 0 && !str_getTaxParam.equals("")) {

                    if (editParam.equals(getEditParam)) {
                        //System.out.println("TaxModifyPram:" + getEditParam);
                        if (loop < Integer.parseInt(request.getParameter("hid_tax_size"))) {



                            setTaxType.add("modify");

                        } else {
                            setTaxType.add("");
                        }

                        taxHidParamList.add(str_getHidTaxParam);

                    } else {
                        setTaxType.add(taxCount);
                        taxHidParamList.add("");
                    }


                    getTaxParam.add(str_getTaxParam);
                    //taxHidParamList.add("");//to insert a new tax value, for updating the arraylist is added with the current tax id
                    getTaxValueParam.add(str_getTaxValueParam);
                    getTaxPriceParam.add(str_getTaxPriceParam);
                    //savePO.addPOTaxes();
                    //intTaxRes = savePO.getAddPOTaxes();
                    taxCount.add("");//to insert a new tax value, for updating the arraylist is added with string value modify
                }
            }

            savePO.setTaxId(getTaxParam);
            savePO.setHidTaxId(taxHidParamList);
            savePO.setTaxValue(getTaxValueParam);
            savePO.setTaxPrice(getTaxPriceParam);
            savePO.setTaxType(setTaxType);
            //savePO.setHidTaxId("");

            // to add values of different line items to arraylist and set to ths SavePO.java
            for (int loop = 0; loop < Integer.parseInt(getItemCountParam); loop++) {
                intItemRes = 0;
                str_getItemParam = request.getParameter("item" + loop) != null ? request.getParameter("item" + loop) : "";
                str_getHidItemParam = request.getParameter("edit_hid_item" + loop) != null ? request.getParameter("edit_hid_item" + loop) : "";
                str_getUnitPriceParam = request.getParameter("unit_rate" + loop) != null ? request.getParameter("unit_rate" + loop) : "0";
                 str_getUnitParam = request.getParameter("unit_id" + loop) != null ? request.getParameter("unit_id" + loop) : "0";

                str_getQuantityParam = request.getParameter("quantity" + loop) != null ? request.getParameter("quantity" + loop) : "0";
                str_getDescParam = request.getParameter("description" + loop) != null ? request.getParameter("description" + loop) : "";


                str_getTotalParam = request.getParameter("total_price" + loop) != null ? request.getParameter("total_price" + loop) : "0.00";

                if (!str_getHidItemParam.equals("") && (str_getItemParam.equals("") || str_getItemParam.equals("SELECT"))) {

                    setItemType.add("delete");

                    itemHidParamList.add(str_getHidItemParam);

                    getItemParam.add(str_getItemParam);

                    getUnitPriceParam.add(str_getUnitPriceParam);
                      getUnitParam.add(str_getUnitParam);
                    getQuantityParam.add(str_getQuantityParam);
                    getDescParam.add(str_getDescParam);
                    getTotalParam.add(str_getTotalParam);
                    itemCount.add("");
                } else if (!str_getTotalParam.equals("") && Float.parseFloat(str_getUnitPriceParam) > 0 && Integer.parseInt(str_getQuantityParam) > 0 && Double.parseDouble(str_getTotalParam) > 0 && !str_getItemParam.equals("")) {


                    if (editParam.equals(getEditParam)) {
                        //System.out.println("LineItemModifyPram:" + getEditParam);
                        if (loop < Integer.parseInt(request.getParameter("hid_item_size"))) {
                            setItemType.add("modify");
                        } else {
                            setItemType.add("");
                        }

                        itemHidParamList.add(str_getHidItemParam);
                    } else {
                        setItemType.add(itemCount);
                        itemHidParamList.add("");
                    }


                    getItemParam.add(str_getItemParam);
                    //itemHidParamList.add("");//to insert a new line item value, for updating the arraylist is added with the current line item
                    getUnitPriceParam.add(str_getUnitPriceParam);
                    getUnitParam.add(str_getUnitParam);
                    getQuantityParam.add(str_getQuantityParam);
                    getDescParam.add(str_getDescParam);
                    getTotalParam.add(str_getTotalParam);
                    //savePO.addPOLineItem();
                    //intItemRes = savePO.getAddPOLineItem();
                    itemCount.add("");//to insert a new line item value, for updating the arraylist is added with string value modify
                }
            }

            savePO.setItemId(getItemParam);
            savePO.setHidItemId(itemHidParamList);
            savePO.setUnit(getUnitParam);
            savePO.setUnitPrice(getUnitPriceParam);
            savePO.setQuantity(getQuantityParam);
            savePO.setDesc(getDescParam);
            savePO.setTotal(getTotalParam);
            savePO.setLineType(setItemType);
            savePO.addPO();
            //get the result of the update executed
            intAddRes = savePO.getAddPO();
            getPoNumber = String.valueOf(savePO.getPONumber());
            //System.out.println("SavedPO:" + getPoNumber);

            responseText = "save=save&project_name_hidden=" + getProjIdParam + "&intAddRes=" + intAddRes + "&poNumber=" + getPoNumber;//+"&"+request.getQueryString();
//System.out.append("response:"+responseText);
        }

        PrintWriter out = null;
        do {
            out = response.getWriter();
        } while (exp);
         //out.println(getProjIdParam.trim());
        out.println(responseText.trim());
    }
}
