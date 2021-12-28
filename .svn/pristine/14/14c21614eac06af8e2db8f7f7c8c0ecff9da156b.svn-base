/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Aravindhanj
 */
public class CreateProjPOServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        pathfinder.functions.revenue.PODetailsDAO poDetailsDAO = new pathfinder.functions.revenue.PODetailsDAO();
        pathfinder.functions.revenue.PODetailsVO poDetailsVO = new pathfinder.functions.revenue.PODetailsVO();
        pathfinder.util.SpecialCharHandler splChar = new pathfinder.util.SpecialCharHandler();

        String PONumber = request.getParameter("PO") != null ? request.getParameter("PO") : "";
        String vendorId = request.getParameter("vendor_name_hidden") != null ? request.getParameter("vendor_name_hidden") : "";
        String fromDate = request.getParameter("from_date") != null ? request.getParameter("from_date") : "";
        String toDate = request.getParameter("to_date") != null ? request.getParameter("to_date") : "";
        String dateModified = request.getParameter("DateModified") != null ? request.getParameter("DateModified") : "";
        String grandTotal = request.getParameter("grand_total") != null ? request.getParameter("grand_total") : "";
        String currencyCode = request.getParameter("currencyCode") != null ? request.getParameter("currencyCode") : "";
        String contactPersonNameforShipp = request.getParameter("contactNameforship") != null ? request.getParameter("contactNameforship") : "";//PM names
        String ponotes = request.getParameter("limitedtextarea") != null ? request.getParameter("limitedtextarea") : "";
        javax.servlet.http.HttpSession session = request.getSession();
        String empID = session.getAttribute("theEid").toString();

        String responseText = "";
        String poNum = "";
        int insRes = 0;
        String checkValue = "";
        String getTotalParam = "";
        String lineItemId = "";
        String getActivity;
        String getdeptcode;
        String poId = "";
        String getProjectId = "";
        String getProjectName = "";
        String getCategoryId = "";
        String getCategoryName = "";
        String getItemId = "";
        String getItemName = "";
        String getUnitPrice = "";
        String getUnit = "";
        String getAlteration = "";
        String getReceived = "";
        String getQuantity="";
        String getTotal="";
        String getGrandTotal="";
        String poFrom = "";
        String poTo = "";
        String duedate="";
        String remarks="";

        ArrayList LineItemId = new ArrayList();
        ArrayList POId = new ArrayList();
        ArrayList ProjId = new ArrayList();
        ArrayList ProjName = new ArrayList();
        ArrayList dept_code = new ArrayList();
        ArrayList ActivityId = new ArrayList();
        ArrayList CategoryId = new ArrayList();
        ArrayList ItemId = new ArrayList();
        ArrayList UnitPrice = new ArrayList();
        ArrayList Unit = new ArrayList();
        ArrayList Alteration = new ArrayList();
        ArrayList Received = new ArrayList();
        ArrayList Quantity = new ArrayList();
        ArrayList Total = new ArrayList();
        ArrayList dueDate = new ArrayList();
        ArrayList reMarks = new ArrayList();

        ArrayList updateLineItemId = new ArrayList();
        ArrayList updatePOId = new ArrayList();
        ArrayList updateProjId = new ArrayList();
        ArrayList updateProjName = new ArrayList();
        ArrayList updatedept_code = new ArrayList();
        ArrayList updateActivityId = new ArrayList();
        ArrayList updateCategoryId = new ArrayList();
        ArrayList updateItemId = new ArrayList();
        ArrayList updateUnitPrice = new ArrayList();
        ArrayList updateUnit = new ArrayList();
        ArrayList updateAlteration = new ArrayList();
        ArrayList updateReceived = new ArrayList();
        ArrayList updateQuantity = new ArrayList();
        ArrayList updateTotal = new ArrayList();
        ArrayList updatedueDate = new ArrayList();
        ArrayList updatereMarks = new ArrayList();

        String getItemCountParam = request.getParameter("item_count"); //!=null?request.getParameter("item_count"):"1";
        if (getItemCountParam == null || getItemCountParam.equals("")) {
            getItemCountParam = "1";
        }
        if(request.getParameter("save") != null) {
            for(int index = 0; index<Integer.parseInt(getItemCountParam); index++) {

                checkValue = request.getParameter("check" + index) != null ? request.getParameter("check" + index) : "";
                //System.out.println(" Checkbox "+ checkValue);
                if(checkValue=="1" || checkValue.equals("1")) {
                    getProjectName = request.getParameter("project_name"+ index) != null ? request.getParameter("project_name"+ index) : "";
                    getProjectId = request.getParameter("project_name"+index+"_hidden") != null ? request.getParameter("project_name"+index+"_hidden") : "";
                    lineItemId = request.getParameter("lineItemId"+index) != null ? request.getParameter("lineItemId"+index) : "";
                    poId = request.getParameter("POId"+index) != null ? request.getParameter("POId"+index) : "";
                    //if(index == 0) { poNum = poId; }
                    getActivity = request.getParameter("activity"+index) != null ? request.getParameter("activity"+index) : "";
                    getdeptcode = request.getParameter("dept_code"+index) != null ? request.getParameter("dept_code"+index) : "";
                    getUnit = request.getParameter("unit" + index) != null ? request.getParameter("unit" + index) : "0";
                    getUnitPrice = request.getParameter("unitPrice" + index) != null ? request.getParameter("unitPrice" + index) : "0";
                    getQuantity = request.getParameter("quantity" + index) != null ? request.getParameter("quantity" + index) : "0";
                    getCategoryId = request.getParameter("category_name"+index) != null ? request.getParameter("category_name"+index) : "";
                    getItemId = splChar.encoding(request.getParameter("item_name"+ index) != null ? request.getParameter("item_name"+index) : "");
                    getAlteration = request.getParameter("alteration" + index) != null ? request.getParameter("alteration" + index) : "0";
                    getReceived = request.getParameter("received" + index) != null ? request.getParameter("received" + index) : "0";
                    getTotal = request.getParameter("total" + index) != null ? request.getParameter("total" + index) : "0";
                    poFrom = request.getParameter("from_date") != null ? request.getParameter("from_date") : "";
                    poTo = request.getParameter("to_date") != null ? request.getParameter("to_date") : "";
                    getGrandTotal = request.getParameter("getGrandTotal") != null ? request.getParameter("getGrandTotal") : "0";
                    duedate = request.getParameter("duedate" + index) != null ? request.getParameter("duedate" + index) : "0000-00-00";
                    remarks = request.getParameter("remarks" + index) != null ? request.getParameter("remarks" + index) : "";
                    //duedate0 remark0

                    if(lineItemId == "" || lineItemId.equals("")) {
                        LineItemId.add(lineItemId);
                        POId.add(poId);
                        ProjName.add(getProjectName);
                        ActivityId.add(getActivity);
                        dept_code.add(getdeptcode);
                        //ProjId.add(getProjectId);
                        CategoryId.add(getCategoryId);
                        ItemId.add(getItemId);
                        UnitPrice.add(getUnitPrice);
                        Unit.add(getUnit);
                        Alteration.add(getAlteration);
                        Received.add(getReceived);
                        Quantity.add(getQuantity);
                        Total.add(getTotal);
                        dueDate.add(duedate);
                        reMarks.add(remarks);
                    } else {
                        updateLineItemId.add(lineItemId);
                        updatePOId.add(poId);
                        updateProjName.add(getProjectName);
                        updateActivityId.add(getActivity);
                        updatedept_code.add(getdeptcode);
                        updateCategoryId.add(getCategoryId);
                        updateItemId.add(getItemId);
                        updateUnitPrice.add(getUnitPrice);
                        updateUnit.add(getUnit);
                        updateAlteration.add(getAlteration);
                        updateReceived.add(getReceived);
                        updateQuantity.add(getQuantity);
                        updateTotal.add(getTotal);
                        updatedueDate.add(duedate);
                        updatereMarks.add(remarks);
                    }
                }
            }
            poDetailsVO.setVendorId(vendorId);
            poDetailsVO.setPoNum(PONumber);
            poDetailsVO.setFromDate(fromDate);
            poDetailsVO.setToDate(toDate);
            poDetailsVO.setGrandTotal(grandTotal);
            poDetailsVO.setInsertedCurrency(currencyCode);
            poDetailsVO.setContactNameforInsert(contactPersonNameforShipp);
            poDetailsVO.setpoNotesInsert(splChar.escapeSplCharsHTML(ponotes));
            poDetailsVO.setEmpId(empID);

            //System.out.println("ADD LINE ITEM : "+LineItemId.size());
            if(LineItemId.size() > 0 || dateModified.equals("1")||!currencyCode.equals("SELECT")||!contactPersonNameforShipp.equals("SELECT")) {
                poDetailsVO.setLineItemId(LineItemId);
                poDetailsVO.setPOId(POId);
                poDetailsVO.setProjDispName(ProjName);
                poDetailsVO.setdeptCode(dept_code);
                poDetailsVO.setActivityId(ActivityId);
                poDetailsVO.setCategoryId(CategoryId);
                poDetailsVO.setItemId(ItemId);
                poDetailsVO.setUnitPrice(UnitPrice);
                poDetailsVO.setUnit(Unit);
                poDetailsVO.setAlteration(Alteration);
                poDetailsVO.setReceived(Received);
                poDetailsVO.setQuantity(Quantity);
                poDetailsVO.setTotal(Total);
                poDetailsVO.setdueDate(dueDate);
                poDetailsVO.setremarks(reMarks);
                poDetailsDAO.SavePO(poDetailsVO); // Save New PO Details

            }
            //System.out.println("UPDATE LINE ITEM : "+updateLineItemId.size());
            if(updateLineItemId.size() > 0 || dateModified.equals("1")||!currencyCode.equals("SELECT")||!contactPersonNameforShipp.equals("SELECT")) {

                poDetailsVO.setUpdateLineItemId(updateLineItemId);
                poDetailsVO.setUpdatePOId(updatePOId);
                poDetailsVO.setUpdateProjDispName(updateProjName);
                poDetailsVO.setUpdatedeptCode(updatedept_code);
                poDetailsVO.setUpdateActivityId(updateActivityId);
                poDetailsVO.setUpdateCategoryId(updateCategoryId);
                poDetailsVO.setUpdateItemId(updateItemId);
                poDetailsVO.setUpdateUnitPrice(updateUnitPrice);
                poDetailsVO.setUpdateUnit(updateUnit);
                poDetailsVO.setUpdateAlteration(updateAlteration);
                poDetailsVO.setUpdateReceived(updateReceived);
                poDetailsVO.setUpdateQuantity(updateQuantity);
                poDetailsVO.setUpdateTotal(updateTotal);
                poDetailsVO.setUpdatedueDate(updatedueDate);
                poDetailsVO.setUpdateremarks(updatereMarks);
                poDetailsDAO.UpdatePO(poDetailsVO); // Update PO Details
            }
            poNum = poDetailsVO.getPoNum();
            insRes = poDetailsVO.getInsResult();
            responseText = "PO="+poNum+"&insertResult="+insRes;
        }

        //To Delete the Estimations
        if(request.getParameter("delete") != null) {
            double tempTotal = 0.0;
            //System.out.println("DELETE FUNCTIONALITY :");
            for (int index = 0; index < Integer.parseInt(getItemCountParam); index++) {
                checkValue = request.getParameter("check" + index) != null ? request.getParameter("check" + index) : "";
                //System.out.println(" Checkbox "+ checkValue);
                if(checkValue=="1" || checkValue.equals("1")) {
                    lineItemId = request.getParameter("lineItemId"+index) != null ? request.getParameter("lineItemId"+index) : "";
                    getTotalParam = request.getParameter("total" + index) != null ? request.getParameter("total" + index) : "0.0";
                    tempTotal += Double.parseDouble(getTotalParam);
                    LineItemId.add(lineItemId);
                }
            }
            poDetailsVO.setLineItemId(LineItemId);
            if(grandTotal == null || grandTotal == ""){
                grandTotal = "0";
            }
            //System.out.println(" Get Total : "+grandTotal);
            tempTotal = Double.parseDouble(grandTotal) - tempTotal;
            poDetailsVO.setGrandTotal(String.valueOf(tempTotal));
            poDetailsVO.setLineItemId(LineItemId);
            poDetailsVO.setVendorId(vendorId);
            poDetailsVO.setPoNum(PONumber);
            poDetailsDAO.DeletePO(poDetailsVO); //Delete PO Details

            poNum = poDetailsVO.getPoNum();
            insRes = poDetailsVO.getDeleteResult();
            responseText = "PO="+poNum+"&deleteResult="+insRes;
        }
        PrintWriter out = response.getWriter();
        //out.println(getProjIdParam.trim());
        out.println(responseText.trim());

    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
