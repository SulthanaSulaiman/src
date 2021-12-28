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
public class CreateProjEstimationServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        javax.servlet.http.HttpSession session = request.getSession();
        String sesEmp = (String) session.getAttribute("theEid");

        int intAddRes = 0;
        int intDelEstRes = 0;
        int intItemRes = 0;

        List itemCount = new ArrayList();
        String responseText = "";
        String estNumberDispText = "";
        String editParam = "Edit";
        String salesBidding = "1";
        String productionBidding = "2";
        String getEditParam = request.getParameter("toEdit") != null ? request.getParameter("toEdit") : "";//SearchProj
        String getEstimationNumber = request.getParameter("estimationNumber") != null ? request.getParameter("estimationNumber") : "";
        String getProjEstprocessParam = request.getParameter("estimate_flag") != null ? request.getParameter("estimate_flag") : "";
        String getProjCustomerNameParam = request.getParameter("customer_name") != null ? request.getParameter("customer_name") : "";
        String getProjIdParam = request.getParameter("project_name_hidden") != null ? request.getParameter("project_name_hidden") : "";//SearchProjid
        String project_name = request.getParameter("project_name") != null ? request.getParameter("project_name") : "";
        String getBuyerParam = request.getParameter("buyer_hidden") != null ? request.getParameter("buyer_hidden") : "";
        String getBuyerAddParam = request.getParameter("buyer_add") != null ? request.getParameter("buyer_add") : "";
        String getBuyerCityParam = request.getParameter("buyer_city") != null ? request.getParameter("buyer_city") : "";
        String getBuyerStateParam = request.getParameter("buyer_state") != null ? request.getParameter("buyer_state") : "";
        String getBuyerZipParam = request.getParameter("buyer_zip") != null ? request.getParameter("buyer_zip") : "";
        String getBuyerCountryParam = request.getParameter("buyer_country") != null ? request.getParameter("buyer_country") : "";
        String getBuyerPhoneParam = request.getParameter("buyer_phone") != null ? request.getParameter("buyer_phone") : "";
        String getBuyerFaxParam = request.getParameter("buyer_fax") != null ? request.getParameter("buyer_fax") : "";
        String getBuyerContactParam = request.getParameter("buyer_contact") != null ? request.getParameter("buyer_contact") : "";
        String getSellerParam = request.getParameter("seller_hidden") != null ? request.getParameter("seller_hidden") : "";
        String getTermsParam = request.getParameter("terms") != null ? request.getParameter("terms") : "";
        String getStatusParam = request.getParameter("poStatus") != null ? request.getParameter("poStatus") : "6";
        String getPayParam = request.getParameter("pay") != null ? request.getParameter("pay") : "";
        String getCurrencyParam = request.getParameter("currency") != null ? request.getParameter("currency") : "";
        String getLineItems = request.getParameter("line_items") != null ? request.getParameter("line_items") : "";
        String getEstimationTypeNo = request.getParameter("estimation") != null ? request.getParameter("estimation") : productionBidding;
        //out.println("Request:"+request.getParameter("estimation"));
        //out.println("estmiation:"+getEstimationTypeNo);
        if (getEstimationTypeNo.equals("")) {
            getEstimationTypeNo = "2";
        }
        String getEstValue = request.getParameter("estValue") != null ? request.getParameter("estValue") : "";
        String getGrandTotalParam = request.getParameter("grand_total") != null ? request.getParameter("grand_total") : "";
        String getCurrencyCodeParam = request.getParameter("currencyCode") != null ? request.getParameter("currencyCode") : "";
        String getEstimateDisplayDate = request.getParameter("estimateDisplayDate") != null ? request.getParameter("estimateDisplayDate") : "";
        String getItemCountParam = request.getParameter("item_count"); //!=null?request.getParameter("item_count"):"1";
        if (getItemCountParam == null || getItemCountParam.equals("")) {
            getItemCountParam = "1";
        }

        if(getProjCustomerNameParam.contains("cengage") || getProjCustomerNameParam.contains("Cengage") || getProjCustomerNameParam.startsWith("cengage") || getProjCustomerNameParam.startsWith("Cengage")){
            //System.out.println("getProjCustomerNameParam : " + getProjCustomerNameParam);
            getProjEstprocessParam = getProjEstprocessParam;
        } else {
            //System.out.println("getProjCustomerNameParam : " + getProjCustomerNameParam);
            //System.out.println("Inside else - getProjEstprocessParam : " + getProjEstprocessParam);
            getProjEstprocessParam = "1";
        }
        
        //System.out.println("getProjEstprocessParam : " + getProjEstprocessParam);
        filters.projects.ProjDetailsDAO projectDetail = new filters.projects.ProjDetailsDAO();
        if (!getProjEstprocessParam.equals("")) {
            if (getProjEstprocessParam.equals("1")) {
                estNumberDispText = projectDetail.getProjectId(getProjIdParam)+"E";
            } else {
                estNumberDispText = projectDetail.getProjectId(getProjIdParam)+"C";
            }
        }

        //System.out.println("estNumberDispText : " + estNumberDispText);
        
        String checkValue = "";
        String str_getItemParam = "";
        String str_getCategoryParam = "";
        String str_getUnitPriceParam = "";
        String str_getQuantityParam = "";
        String str_getDescParam = "";
        String str_getTotalParam = "";
        String str_getHidItemParam = "";
        String str_getUnitParam = "";
        String str_getItemCode = "";
        String str_getItemType = "";
        String str_getItemTrim = "";
        String str_getLineItemId="";
        String str_getLineItemDesc="";
        String str_getLineItem="";

        List getItemParam = new ArrayList();
        List getCategoryParam = new ArrayList();
        List itemHidParamList = new ArrayList();
        List getUnitPriceParam = new ArrayList();
        List getQuantityParam = new ArrayList();
        List getDescParam = new ArrayList();
        List getTypeParam = new ArrayList();
        List getTrimParam = new ArrayList();
        List getCodeParam = new ArrayList();
        List getTotalParam = new ArrayList();
        List setItemType = new ArrayList();
        List getUnitParam = new ArrayList();
        List getLineItemIdParam = new ArrayList();
        List getLineItemDescParam = new ArrayList();
        List getLineItem = new ArrayList();

        String component = "";
        String actual = "";
        String cmponentId = "";
        List components = new ArrayList();
        List actuals = new ArrayList();
        List componentIds = new ArrayList();
        
        List estLineItemIdList = new ArrayList();
        
        //To Update the Actual Estimation
        if(request.getParameter("saveActualEst") != null) {
            pathfinder.functions.projects.chapters.ActualEstimationVO actualEstimationVO= new pathfinder.functions.projects.chapters.ActualEstimationVO();
            pathfinder.functions.projects.chapters.ActualEstimationDAO actualEstimationDAO= new pathfinder.functions.projects.chapters.ActualEstimationDAO();
            String actualEstCount = request.getParameter("actualCount") != null ? request.getParameter("actualCount") : "";
            //System.out.println("actualEstCount : "+actualEstCount);
            for(int index=0; index<Integer.parseInt(actualEstCount); index++) {
                componentIds.add(request.getParameter("component_id_"+index) != "" ? request.getParameter("component_id_"+index) : "0");
                actuals.add(request.getParameter("actual_"+index) != "" ? request.getParameter("actual_"+index) : "0");
            }
            actualEstimationVO.setProjId(getProjIdParam);
            actualEstimationVO.setActualEstCount(Integer.parseInt(actualEstCount));
            actualEstimationVO.setActual(actuals);
            actualEstimationVO.setComponentId(componentIds);
            actualEstimationDAO.SetActualEstimationDetails(actualEstimationVO);
            //System.out.println("Actuals : "+actuals);
            responseText = "saveActualEst=save&project_name_hidden=" + getProjIdParam+ "&project_name=" + project_name;
            response.sendRedirect("CreateProjEstimation.jsp?"+responseText);
        }
        
        //To Delete the Estimations
        if(request.getParameter("deleteEst") != null) {
            double tempTotal = 0.0;
            pathfinder.functions.revenue.SaveEstimate deleteEst = new pathfinder.functions.revenue.SaveEstimate();
            ////System.out.println("getItemCountParam :"+getItemCountParam);
            for (int loop = 0; loop < Integer.parseInt(getItemCountParam); loop++) {
                checkValue = request.getParameter("check" + loop) != null ? request.getParameter("check" + loop) : "";
                ////System.out.println(" Checkbox "+ checkValue);
                if(checkValue=="1" || checkValue.equals("1")) {
                    str_getHidItemParam = request.getParameter("lineItemId" + loop) != null ? request.getParameter("lineItemId" + loop) : "";
                    str_getTotalParam = request.getParameter("total" + loop) != null ? request.getParameter("total" + loop) : "0.0";
                    tempTotal += Double.parseDouble(str_getTotalParam);
                    estLineItemIdList.add(str_getHidItemParam);
                }
            }
            deleteEst.setProjId(getProjIdParam);
            if(getGrandTotalParam == null || getGrandTotalParam == ""){
                getGrandTotalParam = "0";
            }
            //System.out.println(" Get Total : "+getGrandTotalParam);
            tempTotal = Double.parseDouble(getGrandTotalParam) - tempTotal;
            deleteEst.setEstValue(String.valueOf(tempTotal));
            deleteEst.setEstLineItemIdList(estLineItemIdList);
            deleteEst.setQueryType("delete"); 
            deleteEst.DeleteEst();
            intAddRes = deleteEst.getIntDelRes();
            intDelEstRes = deleteEst.getIntDelEstRes();
            //System.out.println("Check in Servlet : "+intDelEstRes);
            //response.sendRedirect("CreateProjEstimation.jsp");
            if(intDelEstRes == 1) {
                //request.removeAttribute("estimationNumber");
                //request.removeAttribute("estimation");
                //response.sendRedirect("CreateProjEstimation.jsp");
                //request.getRequestDispatcher("/CreateProjEstimation.jsp").forward(request, response);
                responseText = "delete=delete&project_name_hidden=" + getProjIdParam + "&project_name=" + project_name + "&intAddRes=" + intAddRes + "&intDelEstRes="+intDelEstRes;
            } else {
                responseText = "delete=delete&project_name_hidden=" + getProjIdParam + "&project_name=" + project_name + "&intAddRes=" + intAddRes + "&estimationNumber=" + getEstimationNumber + "&estimation=" + getEstimationTypeNo+"&intDelEstRes="+intDelEstRes;
            }
        }
        
        //To save the newly created Estimation

        if (request.getParameter("save") != null) {

            intAddRes = 0;
            pathfinder.functions.revenue.SaveEstimate saveEst = new pathfinder.functions.revenue.SaveEstimate();

            if (editParam.equals(getEditParam)) {
                //System.out.println("ModifyPram:" + getEditParam);
                saveEst.setQueryType("modify");
                saveEst.setEstNumber(Integer.parseInt(getEstimationNumber));
            }


            saveEst.setSesEmp(sesEmp);
            saveEst.setProjId(getProjIdParam);
            saveEst.setProjEstProcessId(getProjEstprocessParam);
            saveEst.setProjEstDispNumber(estNumberDispText);
            saveEst.setBuyer(getBuyerParam);
            saveEst.setShipAdd(getBuyerAddParam);
            saveEst.setShipCity(getBuyerCityParam);
            saveEst.setShipState(getBuyerStateParam);
            saveEst.setShipZipcode(getBuyerZipParam);
            saveEst.setShipCountry(getBuyerCountryParam);
            saveEst.setShipPhone(getBuyerPhoneParam);
            saveEst.setShipFax(getBuyerFaxParam);
            saveEst.setSeller(getSellerParam);

            saveEst.setStatus(getStatusParam);
            saveEst.setTermsAndCond(getTermsParam);

            saveEst.setEstCurrency(getCurrencyParam);

            saveEst.setType(getEstimationTypeNo);
            saveEst.setPayId(getPayParam);






            itemCount.clear();
            // to add values of different line items to arraylist and set to ths saveEst.java
            saveEst.setEstValue(getGrandTotalParam);
            saveEst.setCurrencyCode(getCurrencyCodeParam);
            saveEst.setEstimateDisplayDate(getEstimateDisplayDate);
            for (int loop = 0; loop < Integer.parseInt(getItemCountParam); loop++) {
                intItemRes = 0;
                checkValue = request.getParameter("check" + loop) != null ? request.getParameter("check" + loop) : "";
                ////System.out.println(" Checkbox "+ checkValue);
                if(checkValue=="1" || checkValue.equals("1")) {
                    str_getItemParam = request.getParameter("item_name" + loop) != null ? request.getParameter("item_name" + loop) : "";
                    str_getCategoryParam = request.getParameter("category_name" + loop) != null ? request.getParameter("category_name" + loop) : "";
                    str_getLineItemDesc = request.getParameter("notes" + loop) != null ? request.getParameter("notes" + loop) : "";
                    //System.out.println("str_getItemParam:" + str_getItemParam);
                    //System.out.println("str_getCategoryParam:" + str_getCategoryParam);
                    str_getHidItemParam = request.getParameter("lineItemId" + loop) != null ? request.getParameter("lineItemId" + loop) : "";
                    str_getUnitPriceParam = request.getParameter("unitPrice" + loop) != null ? request.getParameter("unitPrice" + loop) : "0";
                    str_getQuantityParam = request.getParameter("quantity" + loop) != null ? request.getParameter("quantity" + loop) : "0";
                    str_getDescParam = request.getParameter("description" + loop) != null ? request.getParameter("description" + loop) : "";
                    str_getUnitParam = request.getParameter("unit" + loop) != null ? request.getParameter("unit" + loop) : "";
                    str_getItemCode = request.getParameter("itemCode" + loop) != null ? request.getParameter("itemCode" + loop) : "0";
                    str_getItemType = request.getParameter("type" + loop) != null ? request.getParameter("type" + loop) : "";
                    str_getItemTrim = request.getParameter("trim" + loop) != null ? request.getParameter("trim" + loop) : "";
                    str_getLineItem = request.getParameter("line_items" + loop) != null ? request.getParameter("line_items" + loop) : "";
                    str_getLineItemId = request.getParameter("lineItemId" + loop) != null ? request.getParameter("lineItemId" + loop) : "";
                    str_getTotalParam = request.getParameter("total" + loop) != null ? request.getParameter("total" + loop) : "0.00";
                   /* //System.out.println("str_getQuantityParam : " + str_getQuantityParam);
                    //System.out.println("str_getTotalParam : " + str_getTotalParam);
                    //System.out.println("str_getUnitPriceParam : " + str_getUnitPriceParam);
                    //System.out.println("str_getLineItemId : " + str_getLineItemId);*/

                    if (!str_getHidItemParam.equals("") && (str_getItemParam.equals("") || str_getItemParam.equals("SELECT"))) {

                        setItemType.add("delete");

                        itemHidParamList.add(str_getHidItemParam);

                        getItemParam.add(str_getItemParam);
                        getCategoryParam.add(str_getCategoryParam);
                        getLineItemDescParam.add(str_getLineItemDesc);
                        getUnitPriceParam.add(str_getUnitPriceParam);
                        getUnitParam.add(str_getUnitParam);
                        getQuantityParam.add(str_getQuantityParam);
                        getDescParam.add(str_getDescParam);
                        getTypeParam.add(str_getItemType);
                        getTrimParam.add(str_getItemTrim);
                        getCodeParam.add(str_getItemCode);
                        getTotalParam.add(str_getTotalParam);
                        getLineItem.add(str_getLineItem);
                        itemCount.add("");

                    } else if (!str_getTotalParam.equals("") && !str_getItemParam.equals("")) {

                       // //System.out.println("else if");
                        if (editParam.equals(getEditParam)) {
                           // //System.out.println("LineItemModifyPram:" + getEditParam);
                            if (loop < Integer.parseInt(request.getParameter("hid_item_size"))) {
                                setItemType.add("modify");
                            } else {
                                setItemType.add("");
                            }

                            itemHidParamList.add(str_getLineItemId);
                        } else {
                           // //System.out.println("else ");
                            setItemType.add(itemCount);
                            itemHidParamList.add("");
                        }
                       // //System.out.println("after else");
                        getItemParam.add(str_getItemParam);
                        getCategoryParam.add(str_getCategoryParam);
                        getLineItemDescParam.add(str_getLineItemDesc);
                        //itemHidParamList.add("");//to insert a new line item value, for updating the arraylist is added with the current line item
                        getUnitPriceParam.add(str_getUnitPriceParam);
                        getUnitParam.add(str_getUnitParam);
                        getQuantityParam.add(str_getQuantityParam);
                        getDescParam.add(str_getDescParam);
                        getTypeParam.add(str_getItemType);
                        getTrimParam.add(str_getItemTrim);
                        getCodeParam.add(str_getItemCode);
                        getTotalParam.add(str_getTotalParam);
                        getLineItem.add(str_getLineItem);

                        //saveEst.addPOLineItem();
                        //intItemRes = saveEst.getAddPOLineItem();
                        itemCount.add("");//to insert a new line item value, for updating the arraylist is added with string value modify
                    }
                }
            }
           // //System.out.println("getItemparam:" + getItemParam);
            saveEst.setItemIdList(getItemParam);
            saveEst.setCategoryList(getCategoryParam);
            saveEst.setItemDescList(getLineItemDescParam);
            saveEst.setHidItemIdList(itemHidParamList);
            saveEst.setUnitPriceList(getUnitPriceParam);
            saveEst.setUnit(getUnitParam);
            saveEst.setTypeList(getTypeParam);
            saveEst.setTrimList(getTrimParam);
            saveEst.setCodeList(getCodeParam);
            saveEst.setLineItems(getLineItem);

            saveEst.setQuantityList(getQuantityParam);
            saveEst.setDescList(getDescParam);
            saveEst.setTotalList(getTotalParam);
            saveEst.setTextList(setItemType);
            


             saveEst.addEst();
            //get the result of the update executed
            intAddRes = saveEst.getAddEst();
            getEstimationNumber = String.valueOf(saveEst.getEstNumber());
            // //System.out.println("SavedEstimation" + getEstimationNumber);
            responseText = "save=save&project_name_hidden=" + getProjIdParam + "&project_name=" + project_name + "&intAddRes=" + intAddRes + "&estimationNumber=" + getEstimationNumber + "&estimation=" + getEstimationTypeNo;
            response.sendRedirect("CreateProjEstimation.jsp?"+responseText);
//System.out.append("response:"+responseText);
        }

        PrintWriter out = response.getWriter();
//out.println(getProjIdParam.trim());
        out.println(responseText.trim());




    }
}
    