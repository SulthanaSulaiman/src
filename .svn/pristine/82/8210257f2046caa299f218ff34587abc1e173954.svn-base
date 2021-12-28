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
import java.util.Calendar;
import java.text.SimpleDateFormat;

/**
 *
 * @author thanuja
 */
public class CreateInvoiceServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


            javax.servlet.http.HttpSession session = request.getSession();
        String sesEmp = (String) session.getAttribute("theEid");
        String responseText = "";
        String invNumberDispText = "";
        int intAddRes = 0;
        int intTaxRes = 0;
        int intItemRes = 0;
        int changeTheinvoicenoByyear = 0;
        List taxCount = new ArrayList();
        List itemCount = new ArrayList();
        String editParam = "Edit";
        String getPartialParam = request.getParameter("partial") != null ? request.getParameter("partial") : "0";//SearchProj
        String getInvoiceStatusParam = request.getParameter("invoice_status") != null ? request.getParameter("invoice_status") : "0";
        String getProjIdParam = request.getParameter("project_name_hidden") != null ? request.getParameter("project_name_hidden") : "";//SearchProjid
        String project_name = request.getParameter("project_name") != null ? request.getParameter("project_name") : "";
        String invoice_display_date = request.getParameter("invoice_display_date") != null ? request.getParameter("invoice_display_date") : "";
        String getInvoiceNumber = request.getParameter("invoiceNumber") != null ? request.getParameter("invoiceNumber") : "0";
        /* if(!getPartialParam.equals(""))
        {
        getPartialParam="1";

        }*/
         //System.out.println("getPartialParamServlet:"+getPartialParam);
        pathfinder.functions.revenue.SaveInvoice billLocation = new pathfinder.functions.revenue.SaveInvoice();
            String billingAddress = "";
            String invoiceNoDispBsdOnLo="";
            String checkInvoiceNumbrdisp="";
            int invoiceNoDisp=0;
            billLocation.setProjId(getProjIdParam);
            String billingLocation = billLocation.projectBillingLoction();;
            //String invoiceNumToDisplay = String.format("%04d", "10");  // 00009
                String invoiceNumToDisplay = "000";  // 00009
            //checkInvoiceNumbrdisp=

                if (!getInvoiceStatusParam.equals("2")){
            if (getInvoiceNumber.equals("")){
                //System.out.println("getInvoiceStatusParam.equals"+getInvoiceStatusParam);
                billLocation.setBillLocation(billingLocation);
                billLocation.setProjId(getProjIdParam);
if (billingLocation.equals("1")){
invoiceNoDispBsdOnLo = billLocation.maxInvoiceNumber(billingLocation);
System.out.println(Integer.parseInt(invoiceNoDispBsdOnLo));
//1091- is upto-2017
//1631 - 2017-2018
//2341 - 2018-2019
//3267 - 2019-2020
//4269 - 2020-2021
invoiceNoDisp =(Integer.parseInt(invoiceNoDispBsdOnLo)-4269)+1; // every year april 01st check SELECT MAX(invoice_disp_number_chennai) FROM invoices and change the value eg., 915 is the max value of april 1st 2017
}
        else if(billingLocation.equals("3"))
        {
            //1393- is upto-2017
            //2966 2017-2018
            //4013 2018-2019
            //4885 - 2019-2020
            //6052 -2020-2021
invoiceNoDispBsdOnLo = billLocation.maxInvoiceNumber(billingLocation);
invoiceNoDisp =(Integer.parseInt(invoiceNoDispBsdOnLo)-6052)+1;// every year april 01st check SELECT MAX(invoice_disp_number_singapore) FROM invoices and change the value eg., 1393 is the max value of april 1st 2017
}
            else if(billingLocation.equals("4"))
        {
                //337 is 2017
                //815 2017-2018
                //1362 2018-2019
                //1934 2019-2020
                //2492 2020-2021
invoiceNoDispBsdOnLo = billLocation.maxInvoiceNumber(billingLocation);
invoiceNoDisp =(Integer.parseInt(invoiceNoDispBsdOnLo)-2492)+1;// every year april 01st check SELECT MAX(invoice_disp_number_tm) FROM invoices and change the value eg., 337 is the max value of april 1st 2017

}
      else if(billingLocation.equals("5"))
        {
          //2 ---2017
          //3 --- 2017-2018
          //5 2018-2019
          //6 2019-2020
          //7 2020-2021
invoiceNoDispBsdOnLo = billLocation.maxInvoiceNumber(billingLocation);
invoiceNoDisp =(Integer.parseInt(invoiceNoDispBsdOnLo)-7)+1;// every year april 01st check SELECT MAX(invoice_disp_number_SG2) FROM invoices and change the value eg., 2 is the max value of april 1st 2017
}
 else{}//invoiceNoDispBsdOnLo = billLocation.maxInvoiceNumber(billingLocation);
                //invoiceNoDisp =Integer.parseInt(invoiceNoDispBsdOnLo)+1;
                //billLocation.setInvoiceNumber(Integer.parseInt(getInvoiceNumber));
                //invoiceNoDispBsdOnLo=billLocation.getInvoiceNumberforDisp();
                invoiceNumToDisplay = String.format("%04d", invoiceNoDisp);  // 00009
                //System.out.println("getInvoiceStatusParam.equals"+getInvoiceStatusParam);

                //invoiceNoDisp =Integer.parseInt(invoiceNoDispBsdOnLo);
            }
 else{
                billLocation.setBillLocation(billingLocation);
                billLocation.setProjId(getProjIdParam);
                billLocation.setInvoiceNumber(Integer.parseInt(getInvoiceNumber));
                invoiceNoDispBsdOnLo=billLocation.getInvoiceNumberforDisp();
                //invoiceNoDisp =Integer.parseInt(invoiceNoDispBsdOnLo);
                invoiceNumToDisplay = invoiceNoDispBsdOnLo;  // 00009
                //System.out.println("invoiceNoDispBsdOnLo1"+invoiceNoDispBsdOnLo);

     //           billLocation.setBillLocation(billingLocation);
       //     invoiceNoDispBsdOnLo = billLocation.maxInvoiceNumber(billingLocation);
         //   invoiceNoDisp =Integer.parseInt(invoiceNoDispBsdOnLo)+1;
      }}
            //String invoiceNoDispBsdOnLo = billLocation.maxInvoiceNumber(billingLocation);


            int month=0;
            Calendar now = Calendar.getInstance();
            String formattedDate1="";
            int formattedDate=0;
            SimpleDateFormat df = new SimpleDateFormat("yy"); // Just the year, with 2 digits
            formattedDate1 = df.format(Calendar.getInstance().getTime());
            month = now.get(Calendar.MONTH) + 1;
            if (month >= 4){
            formattedDate = Integer.parseInt(formattedDate1);
            }
 else{
formattedDate = Integer.parseInt(formattedDate1)-1;
 }

if (billingLocation.equals("1") && getInvoiceNumber.equals("")){
invoiceNumToDisplay = "S4CCH"+formattedDate+"-"+invoiceNumToDisplay;
    //System.out.println("chennai"+invoiceNumToDisplay);
}
        else if(billingLocation.equals("3") && getInvoiceNumber.equals(""))
        {
invoiceNumToDisplay = "S4CSG"+formattedDate+"-"+invoiceNumToDisplay;
//System.out.println("singapore"+invoiceNumToDisplay);
}
            else if(billingLocation.equals("4") && getInvoiceNumber.equals(""))
        {
invoiceNumToDisplay = "S4CTI"+formattedDate+"-"+invoiceNumToDisplay;
//System.out.println("Tm"+invoiceNumToDisplay);
}
      else if(billingLocation.equals("5") && getInvoiceNumber.equals(""))
        {
invoiceNumToDisplay = "S4CSGD"+formattedDate+"-"+invoiceNumToDisplay;
//System.out.println("Tm"+invoiceNumToDisplay);
}

        filters.projects.ProjDetailsDAO projectDetail = new filters.projects.ProjDetailsDAO();
        //System.out.println("getPartialParam.equals:" + getPartialParam);
        //System.out.println("getPartialParam.equals1:" + getInvoiceStatusParam);
        if ((!getPartialParam.equals("") && getPartialParam.equals("1")) || (getInvoiceStatusParam.equals("2"))) {
            if(getPartialParam.equals("2")) {
                invNumberDispText = projectDetail.getProjectId(getProjIdParam) + "P";
            } else {
                invNumberDispText = invoiceNumToDisplay;
            }

        } else {
            if (getInvoiceNumber.equals("")){
            invNumberDispText = invoiceNumToDisplay + "F";
            }
        }
if(getInvoiceStatusParam.equals("2")){
invNumberDispText = projectDetail.getProjectId(getProjIdParam) + "P";
}

        String getOldPartialFlag = request.getParameter("oldPartialFlag") != null ? request.getParameter("oldPartialFlag") : "";//SearchProj

        String getEditParam = request.getParameter("toEdit") != null ? request.getParameter("toEdit") : "";//SearchProj
        //String getInvoiceNumber = request.getParameter("invoiceNumber") != null ? request.getParameter("invoiceNumber") : "";
        // System.out.println("getInvoiceNumber:" + getInvoiceNumber);
        String getStatusParam = request.getParameter("status") != null ? request.getParameter("status") : "";
        String getTermsParam = request.getParameter("terms") != null ? request.getParameter("terms") : "";
        String getCurrencyParam = request.getParameter("currency") != null ? request.getParameter("currency") : "";
        String getCurrencyCode = request.getParameter("currencyCode") != null ? request.getParameter("currencyCode") : "";
        System.out.println("getCurrencyCode" + getCurrencyCode);
        String getPayParam = request.getParameter("pay") != null ? request.getParameter("pay") : "";
        String getBuyerParam = request.getParameter("buyer_hidden") != null ? request.getParameter("buyer_hidden") : "";



        String getGrandTotalParam = request.getParameter("grand_total") != null ? request.getParameter("grand_total") : "";
        String getTrems = request.getParameter("terms") != null ? request.getParameter("terms") : "";
        //System.out.print(getTermsParam+"getTrems"+getTrems);
        String getTaxCountParam = request.getParameter("tax_count");
        if (getTaxCountParam == null || getTaxCountParam.equals("")) {
            getTaxCountParam = "1";
        }
        String getItemCountParam = request.getParameter("item_count"); //!=null?request.getParameter("item_count"):"1";
        if (getItemCountParam == null || getItemCountParam.equals("")) {
            getItemCountParam = "1";
        }

        //System.out.println("getItemCountParam:"+getItemCountParam);
        //out.println("getItemCount:"+getItemCountParam);
        String getBuyerAddParam = request.getParameter("buyer_add") != null ? request.getParameter("buyer_add") : "";
        String getBuyerCityParam = request.getParameter("buyer_city") != null ? request.getParameter("buyer_city") : "";
        String getBuyerStateParam = request.getParameter("buyer_state") != null ? request.getParameter("buyer_state") : "";
        String getBuyerZipParam = request.getParameter("buyer_zip") != null ? request.getParameter("buyer_zip") : "";
        String getBuyerCountryParam = request.getParameter("buyer_country") != null ? request.getParameter("buyer_country") : "";
        String getBuyerPhoneParam = request.getParameter("buyer_phone") != null ? request.getParameter("buyer_phone") : "";
        String getBuyerFaxParam = request.getParameter("buyer_fax") != null ? request.getParameter("buyer_fax") : "";

        String getBuyerContactParam = request.getParameter("buyer_contact") != null ? request.getParameter("buyer_contact") : "";


        List getTaxParam = new ArrayList();
        List taxHidParamList = new ArrayList();
        List getTaxValueParam = new ArrayList();
        List getTaxPriceParam = new ArrayList();
        List getCategoryParam = new ArrayList();
        List getItemParam = new ArrayList();
        List itemHidParamList = new ArrayList();
        List estLineItemIdList = new ArrayList();
        List getUnitPriceParam = new ArrayList();
        List getQuantityParam = new ArrayList();
        List getLineItemDescParam = new ArrayList();
        List getDescParam = new ArrayList();
        List getTotalParam = new ArrayList();
        List getTypeParam = new ArrayList();
        List getTrimParam = new ArrayList();
        List getCodeParam = new ArrayList();
        List getLineItemNo = new ArrayList();
        List getTermsConditions = new ArrayList();

        String str_getTaxParam = "";
        String str_getTaxValueParam = "";
        String str_getTaxPriceParam = "";
        String str_getCategoryParam = "";
        String str_getItemParam = "";
        String str_getUnitPriceParam = "";
        String str_getQuantityParam = "";
        String str_getDescParam = "";
        String str_getTotalParam = "";
        String str_getLineItemDesc = "";
        String str_getHidTaxParam = "";
        String str_getHidItemParam = "";
        String str_getUnitParam = "";
        String str_getItemCode = "";
        String str_getItemType = "";
        String str_getItemTrim = "";
        String str_getLineItemId = "";
        String str_getEstLineItemId = "";
        String str_getLineItemNo = "";

        String invoiceLineId = "";
        String invoiceTotalPrice = "";
        List invoiceLineIdList = new ArrayList();

        List setTaxType = new ArrayList();
        List setItemType = new ArrayList();
        List getUnitParam = new ArrayList();
//System.out.println("inside servlet");


        if(request.getParameter("delete") != null) {
            double tempTotal = 0.0;
            intAddRes = 0;
            pathfinder.functions.revenue.SaveInvoice deleteInvoice = new pathfinder.functions.revenue.SaveInvoice();
            //System.out.println("Triggered");
            for (int loop = 0; loop < Integer.parseInt(getItemCountParam); loop++) {
                //System.out.println("Servlets :"+loop);
                intItemRes = 0;
                String checkValue = request.getParameter("check" + loop) != null ? request.getParameter("check" + loop) : "";
                if(checkValue.equals("1")) {
                    invoiceLineId = request.getParameter("line_id" + loop) != null ? request.getParameter("line_id" + loop) : "";
                    invoiceTotalPrice = request.getParameter("total_price"+loop) != null ? request.getParameter("total_price"+loop) : "0.0";
                    tempTotal += Double.parseDouble(invoiceTotalPrice);
                }
                if(invoiceLineId != "") {
                    invoiceLineIdList.add(invoiceLineId);
                }
            }
            if(invoiceLineIdList.size() > 0) {
                tempTotal = Double.parseDouble(getGrandTotalParam) - tempTotal;
                deleteInvoice.setInvoiceValue(String.valueOf(tempTotal));
                deleteInvoice.setInvoiceLineItemIdList(invoiceLineIdList);
                deleteInvoice.setProjId(getProjIdParam);
                deleteInvoice.deleteInvoiceLineItem();
//                System.out.println(" Line Grand Total : "+getGrandTotalParam);
//                System.out.println(" Line Item Id : "+invoiceLineIdList);
                intAddRes = deleteInvoice.getIntDelInvRes();

                responseText = "delete=delete&project_name_hidden=" + getProjIdParam + "&project_name="+project_name+"&intAddRes=" + intAddRes + "&invoiceNumber=" + getInvoiceNumber;//+"&"+request.getQueryString();
            } else {
                responseText = "project_name_hidden=" + getProjIdParam + "&project_name="+project_name+"&intAddRes=" + intAddRes + "&invoiceNumber=" + getInvoiceNumber+"&termsValue="+getTrems;//+"&"+request.getQueryString();
            }
        }


        if (request.getParameter("save") != null) {

//System.out.println("Inside Save")            ;
            intAddRes = 0;
            pathfinder.functions.revenue.SaveInvoice saveInvoice = new pathfinder.functions.revenue.SaveInvoice();
            saveInvoice.setBillLocation(billingLocation);
            if (editParam.equals(getEditParam)) {
                // System.out.println("ModifyPram:" + getEditParam);

                saveInvoice.setType("modify");
                saveInvoice.setInvoiceNumber(Integer.parseInt(getInvoiceNumber));
                saveInvoice.setOldPartialFlag(getOldPartialFlag);
            }


            saveInvoice.setInvoiceEmp(sesEmp);
            saveInvoice.setProjId(getProjIdParam);
            saveInvoice.setInvoiceDispDate(invoice_display_date);

            saveInvoice.setBuyer(getBuyerParam);
            saveInvoice.setShipAdd(getBuyerAddParam);
            saveInvoice.setShipCity(getBuyerCityParam);
            saveInvoice.setShipState(getBuyerStateParam);
            saveInvoice.setShipZipcode(getBuyerZipParam);
            saveInvoice.setShipCountry(getBuyerCountryParam);
            saveInvoice.setShipPhone(getBuyerPhoneParam);
            saveInvoice.setShipFax(getBuyerFaxParam);

            saveInvoice.setInvoiceStatus(getStatusParam);
            saveInvoice.setTermsAndCond(getTermsParam);

            saveInvoice.setInvoiceCurrency(getCurrencyParam);
            saveInvoice.setInvoiceValue(getGrandTotalParam);
            saveInvoice.setPayId(getPayParam);
            saveInvoice.setBuyerType(getBuyerContactParam);
            saveInvoice.setPartialFlag(getPartialParam);
            saveInvoice.setInvoiceFlag(getInvoiceStatusParam);
            saveInvoice.setProjInvDispNumber(invNumberDispText);
            //out.println("getBuyerContactParam : "+getBuyerContactParam);


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
                        // System.out.println("TaxModifyPram:" + getEditParam);
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
                    //saveInvoice.addPOTaxes();
                    //intTaxRes = saveInvoice.getAddPOTaxes();
                    taxCount.add("");//to insert a new tax value, for updating the arraylist is added with string value modify
                }
            }


            saveInvoice.setTaxIdList(getTaxParam);
            saveInvoice.setHidTaxIdList(taxHidParamList);
            saveInvoice.setTaxValueList(getTaxValueParam);
            saveInvoice.setTaxPriceList(getTaxPriceParam);
            saveInvoice.setTaxList(setTaxType);
            //saveInvoice.setHidTaxId("");

            // to add values of different line items to arraylist and set to ths SavePO.java

            for (int loop = 0; loop < Integer.parseInt(getItemCountParam); loop++) {
//System.out.println("Servlets :"+loop);
                intItemRes = 0;
                String checkValue = request.getParameter("check" + loop) != null ? request.getParameter("check" + loop) : "";
                if(checkValue.equals("1")) {
                    str_getCategoryParam = request.getParameter("category_name" + loop) != null ? request.getParameter("category_name" + loop) : "";
                    str_getItemParam = request.getParameter("itemName" + loop) != null ? request.getParameter("itemName" + loop) : "";
                    str_getHidItemParam = request.getParameter("itemName" + loop) != null ? request.getParameter("itemName" + loop) : "";
                    str_getUnitPriceParam = request.getParameter("unit_rate" + loop) != null ? request.getParameter("unit_rate" + loop) : "0";
                    str_getQuantityParam = request.getParameter("quantity" + loop) != null ? request.getParameter("quantity" + loop) : "0";
                    str_getDescParam = request.getParameter("description" + loop) != null ? request.getParameter("description" + loop) : "";
                    str_getItemCode = request.getParameter("itemCode" + loop) != null ? request.getParameter("itemCode" + loop) : "0";
                    str_getItemType = request.getParameter("type" + loop) != null ? request.getParameter("type" + loop) : "";
                    str_getItemTrim = request.getParameter("trim" + loop) != null ? request.getParameter("trim" + loop) : "";
                    str_getLineItemId = request.getParameter("line_id" + loop) != null ? request.getParameter("line_id" + loop) : "";
                    str_getEstLineItemId = request.getParameter("est_line_id" + loop) != null ? request.getParameter("est_line_id" + loop) : "";
                    str_getUnitParam = request.getParameter("unit" + loop) != null ? request.getParameter("unit" + loop) : "";
                    str_getTotalParam = request.getParameter("total_price" + loop) != null ? request.getParameter("total_price" + loop) : "0.00";
                    str_getLineItemDesc = request.getParameter("notes" + loop) != null ? request.getParameter("notes" + loop) : "";
                    //str_getLineItemNo = request.getParameter("line_item_hidden" + loop) != null ? request.getParameter("line_item_hidden" + loop) : "";

//                    System.out.println("Servlet - loop - str_getCategoryParam :" + str_getCategoryParam);
//                    System.out.println("Servlet - loop - str_getItemParam :" + str_getItemParam);

                    if (!str_getHidItemParam.equals("") && (str_getItemParam.equals("") || str_getItemParam.equals("SELECT"))) {
                        if (getInvoiceNumber != null && !("").equals(getInvoiceNumber)) {
                            setItemType.add("delete");

                            itemHidParamList.add(str_getHidItemParam);
                            getCategoryParam.add(str_getCategoryParam);
                            getItemParam.add(str_getItemParam);
                            getUnitPriceParam.add(str_getUnitPriceParam);
                            getUnitParam.add(str_getUnitParam);
                            getQuantityParam.add(str_getQuantityParam);
                            getLineItemDescParam.add(str_getLineItemDesc);
                            getDescParam.add(str_getDescParam);
                            getTotalParam.add(str_getTotalParam);
                            //getLineItemNo.add(str_getLineItemNo);
                            itemCount.add("");

                        }
                    } else if (!str_getTotalParam.equals("")  && !str_getItemParam.equals("")) {
                        //  else if (!str_getTotalParam.equals("")) {

    //System.out.println("Servlet loop saves:"+loop);

                        if (editParam.equals(getEditParam)) {
                            //  System.out.println("LineItemModifyPram:" + getEditParam);
                            if (loop < Integer.parseInt(request.getParameter("hid_item_size"))) {
                                setItemType.add("modify");
                            } else {
                                setItemType.add("");
                            }

                            itemHidParamList.add(str_getLineItemId);
                        } else {
                            setItemType.add(itemCount);
                            itemHidParamList.add("");
                        }
                        estLineItemIdList.add(str_getEstLineItemId);
                        getCategoryParam.add(str_getCategoryParam);
                        getItemParam.add(str_getItemParam);
                        getUnitParam.add(str_getUnitParam);
                        //itemHidParamList.add("");//to insert a new line item value, for updating the arraylist is added with the current line item
                        getUnitPriceParam.add(str_getUnitPriceParam);
                        getQuantityParam.add(str_getQuantityParam);
                        getLineItemDescParam.add(str_getLineItemDesc);
                        getDescParam.add(str_getDescParam);
                        getTypeParam.add(str_getItemType);
                        getTrimParam.add(str_getItemTrim);
                        getCodeParam.add(str_getItemCode);
                        getTotalParam.add(str_getTotalParam);
                        //getLineItemNo.add(str_getLineItemNo);
                        //saveInvoice.addPOLineItem();
                        //intItemRes = saveInvoice.getAddPOLineItem();
                        itemCount.add("");//to insert a new line item value, for updating the arraylist is added with string value modify
                    }
                }
            }


//System.out.println("getItemParam-------------:"+getItemParam);
            if(getItemParam.size() > 0) {
                saveInvoice.setCategoryList(getCategoryParam);
                saveInvoice.setItemIdList(getItemParam);
                //saveEst.setHidItemIdList(itemHidParamList);
                saveInvoice.setHidItemIdList(itemHidParamList);
                saveInvoice.setEstLineItemIdList(estLineItemIdList);
                saveInvoice.setUnitPriceList(getUnitPriceParam);
                saveInvoice.setUnit(getUnitParam);
                saveInvoice.setQuantityList(getQuantityParam);
                saveInvoice.setLineItemDescList(getLineItemDescParam);
                saveInvoice.setDescList(getDescParam);
                saveInvoice.setTotalList(getTotalParam);
                saveInvoice.setItemList(setItemType);
                saveInvoice.setTypeList(getTypeParam);
                saveInvoice.setTrimList(getTrimParam);
                saveInvoice.setCodeList(getCodeParam);
                saveInvoice.setCurrencyCode(getCurrencyCode);
                //saveInvoice.setLineItemNum(getLineItemNo);
                saveInvoice.setTermsAndCond(getTermsParam);
                saveInvoice.addInvoice();
                intAddRes = saveInvoice.getAddInvoice();


                getInvoiceNumber = String.valueOf(saveInvoice.getInvoiceNumber());
                //System.out.println("SavedPO:" + getPoNumber);

                responseText = "save=save&project_name_hidden=" + getProjIdParam + "&project_name="+project_name+"&intAddRes=" + intAddRes + "&invoiceNumber=" + getInvoiceNumber+"&termsValue="+getTrems;//+"&"+request.getQueryString();
                response.sendRedirect("CreateInvoice.jsp?"+responseText);
            } else {
                responseText = "project_name_hidden=" + getProjIdParam + "&project_name="+project_name+"&intAddRes=" + intAddRes + "&invoiceNumber=" + getInvoiceNumber;//+"&"+request.getQueryString();
                response.sendRedirect("CreateInvoice.jsp?"+responseText);
            }
//System.out.append("response:"+responseText);
        }

        PrintWriter out = response.getWriter();
//out.println(getProjIdParam.trim());
        out.println(responseText.trim());
    }
}
