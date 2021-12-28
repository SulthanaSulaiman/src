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
public class CreateBillServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        javax.servlet.http.HttpSession session = request.getSession();
        String sesEmp = (String) session.getAttribute("theEid");
        int intAddRes = 0;

        int intItemRes = 0;

        List itemCount = new ArrayList();
        String editParam = "Edit";
        String getEditParam = request.getParameter("toEdit") != null ? request.getParameter("toEdit") : "";//SearchProj
        String getBillNumber = request.getParameter("billNumber") != null ? request.getParameter("billNumber") : "";
         //System.out.println("bill number:"+getBillNumber);

        String getProjIdParam = request.getParameter("project_name_hidden") != null ? request.getParameter("project_name_hidden") : "";//SearchProjid
        String getBillStatusIDParam = request.getParameter("billStatus") != null ? request.getParameter("billStatus") : "";
        String getGrandTotalParam = request.getParameter("grand_total") != null ? request.getParameter("grand_total") : "";

        String getItemCountParam = request.getParameter("item_count"); //!=null?request.getParameter("item_count"):"1";
        if (getItemCountParam == null || getItemCountParam.equals("")) {
            getItemCountParam = "1";
        }

        String str_getItemParam = "";
        String str_getUnitPriceParam = "";
        String str_getQuantityParam = "";
        String str_getDescParam = "";
        String str_getTotalParam = "";
        String str_getHidItemParam = "";
        String responseText = "";
          String str_getUnitParam="";

        List getItemParam = new ArrayList();
        List itemHidParamList = new ArrayList();
        List getUnitPriceParam = new ArrayList();
        List getQuantityParam = new ArrayList();
        List getDescParam = new ArrayList();
        List getTotalParam = new ArrayList();
        List mod_list = new ArrayList();
        List setItemType = new ArrayList();
           List getUnitParam = new ArrayList();

        if (request.getParameter("save") != null) {

            intAddRes = 0;
            pathfinder.functions.revenue.SaveBill saveBill = new pathfinder.functions.revenue.SaveBill();

            if (editParam.equals(getEditParam)) {
                //System.out.println("ModifyPram:" + getEditParam);
                saveBill.setText("modify");
                saveBill.setBillNumber(Integer.parseInt(getBillNumber));
            }


            saveBill.setSesEmp(sesEmp);
            saveBill.setProjId(getProjIdParam);
            saveBill.setStatus(getBillStatusIDParam);




            itemCount.clear();
            // to add values of different line items to arraylist and set to ths saveEst.java
            saveBill.setBillValue(getGrandTotalParam);
            for (int loop = 0; loop < Integer.parseInt(getItemCountParam); loop++) {
                intItemRes = 0;
                str_getItemParam = request.getParameter("item" + loop) != null ? request.getParameter("item" + loop) : "";
                str_getHidItemParam = request.getParameter("edit_hid_item" + loop) != null ? request.getParameter("edit_hid_item" + loop) : "";
                str_getUnitPriceParam = request.getParameter("unit_rate" + loop) != null ? request.getParameter("unit_rate" + loop) : "0";
                str_getQuantityParam = request.getParameter("quantity" + loop) != null ? request.getParameter("quantity" + loop) : "0";
                str_getDescParam = request.getParameter("description" + loop) != null ? request.getParameter("description" + loop) : "";
 str_getUnitParam = request.getParameter("unit_id" + loop) != null ? request.getParameter("unit_id" + loop) : "0";

                str_getTotalParam = request.getParameter("total_price" + loop) != null ? request.getParameter("total_price" + loop) : "0.00";
                //System.out.println("str_getQuantityParam : " + request.getParameter("quantity" + loop));

                if (!str_getHidItemParam.equals("") && (str_getItemParam.equals("") || str_getItemParam.equals("SELECT"))) {


                    if(getBillNumber!=null && !("").equals(getBillNumber))
                    {
                       // System.out.println("Inside del:"+getBillNumber);

                    setItemType.add("delete");

                    itemHidParamList.add(str_getHidItemParam);

                    getItemParam.add(str_getItemParam);

                    getUnitPriceParam.add(str_getUnitPriceParam);
                      getUnitParam.add(str_getUnitParam);
                    getQuantityParam.add(str_getQuantityParam);
                    getDescParam.add(str_getDescParam);
                    getTotalParam.add(str_getTotalParam);
                    itemCount.add("");
                    }

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
                    getQuantityParam.add(str_getQuantityParam);
                    getDescParam.add(str_getDescParam);
                    getTotalParam.add(str_getTotalParam);
                      getUnitParam.add(str_getUnitParam);
                    //saveEst.addPOLineItem();
                    //intItemRes = saveEst.getAddPOLineItem();
                    itemCount.add("");//to insert a new line item value, for updating the arraylist is added with string value modify
                }
            }

            saveBill.setItemIdList(getItemParam);
            saveBill.setHidItemIdList(itemHidParamList);
            saveBill.setUnitPriceList(getUnitPriceParam);
            saveBill.setQuantityList(getQuantityParam);
            saveBill.setDescList(getDescParam);
            saveBill.setTotalList(getTotalParam);
            saveBill.setTextList(setItemType);
               saveBill.setUnit(getUnitParam);



            saveBill.addBill();
            //get the result of the update executed
            intAddRes = saveBill.getAddBill();
            getBillNumber = String.valueOf(saveBill.getBillNumber());
            //System.out.println("savedBillNumber" + getBillNumber);

            responseText = "save=save&project_name_hidden=" + getProjIdParam + "&intAddRes=" + intAddRes + "&billNumber=" + getBillNumber;
//System.out.append("response:"+responseText);
        }

        PrintWriter out = response.getWriter();
//out.println(getProjIdParam.trim());
        out.println(responseText.trim());










    }
}
