/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Aravindhanj
 */
public class OutboundTransmittalServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String getProjIdParam = request.getParameter("project_name_hidden") != null ? request.getParameter("project_name_hidden") : "";//SearchProjid
        String project_name = request.getParameter("project_name") != null ? request.getParameter("project_name") : "";
        String action = request.getParameter("action") != null ? request.getParameter("action") : "";

        String responseText = "";
        int result = 0;
        try {
            pathfinder.functions.revenue.TransmittalDAO transmittalDAO = new pathfinder.functions.revenue.TransmittalDAO();
            pathfinder.functions.revenue.TransmittalVO transmittalVO = new pathfinder.functions.revenue.TransmittalVO();

            javax.servlet.http.HttpSession session = request.getSession();
            String empID = "";
            String shipId = "";
            String projId = "";
            String mailDate = "";
            String carrier = "";
            String shipWithAcct = "";
            String author = "";
            String title = "";
            String isbn10 = "";
            String isbn13 = "";
            String billingOption = "";
            String billingReference = "";
            String contentNote = "";
            String packageContent = "";
            String remarks = "";
            String receiver = "";
            //String noteId = "";
            String shipMethod = "";
            String boundType = "";
            //String inBoundFlag = "";
            String carrierTrackNo = "";
            String carrierCost = "";
            String shipToLocation = "";
            String shipFromLocation = "";
            String requestDate = "";
            String shipTo = "";
            String shipToAttention = "";
            String shipDivision = "";
            String shipFrom = "";
            String shipToName = "";
            String shipFromName = "";
            String encloseCount = "";
            String tempString = "";

            String[] keySplit;

            if(action.equals("save")) {
                session = request.getSession();
                empID = session.getAttribute("theEid").toString();
                shipId = request.getParameter("shippingId") != null ? request.getParameter("shippingId") : "";
                projId = request.getParameter("project_name_hidden") != null ? request.getParameter("project_name_hidden") : "";
                mailDate = request.getParameter("mailDate") != null ? request.getParameter("mailDate") : "";
                carrier = request.getParameter("carrier") != null ? request.getParameter("carrier") : "";
                shipWithAcct = request.getParameter("shipWithAcct") != null ? request.getParameter("shipWithAcct") : "";
                author = request.getParameter("author") != null ? request.getParameter("author") : "";
                title = request.getParameter("title") != null ? request.getParameter("title") : "";
                isbn10 = request.getParameter("isbn10") != null ? request.getParameter("isbn10") : "";
                isbn13 = request.getParameter("isbn13") != null ? request.getParameter("isbn13") : "";
                billingOption = request.getParameter("billingOption") != null ? request.getParameter("billingOption") : "";
                billingReference = request.getParameter("billingReference") != null ? request.getParameter("billingReference") : "";
                remarks = request.getParameter("remarks") != null ? request.getParameter("remarks") : "";
                receiver = request.getParameter("receiver") != null ? request.getParameter("receiver") : "";
                //noteId = request.getParameter("noteId") != null ? request.getParameter("noteId") : "";
                shipMethod = request.getParameter("shipMethod") != null ? request.getParameter("shipMethod") : "";
                boundType = request.getParameter("boundType") != null ? request.getParameter("boundType") : "";
                //inBoundFlag = request.getParameter("inBoundFlag") != null ? request.getParameter("inBoundFlag") : "";
                carrierTrackNo = request.getParameter("carrierTrackNo") != null ? request.getParameter("carrierTrackNo") : "";
                carrierCost = request.getParameter("carrierCost") != null ? request.getParameter("carrierCost") : "";
                shipToLocation = request.getParameter("shipToLocation") != null ? request.getParameter("shipToLocation") : "";
                shipFromLocation = request.getParameter("shipFromLocation") != null ? request.getParameter("shipFromLocation") : "";
                requestDate = request.getParameter("requestDate") != null ? request.getParameter("requestDate") : "";
                shipTo = request.getParameter("shipTo_hidden") != null ? request.getParameter("shipTo_hidden") : "";
                shipToAttention = request.getParameter("attention") != null ? request.getParameter("attention") : "";
                shipDivision = request.getParameter("division") != null ? request.getParameter("division") : "";
                shipFrom = request.getParameter("shipFrom_hidden") != null ? request.getParameter("shipFrom_hidden") : "";
                shipToName = request.getParameter("shipTo") != null ? request.getParameter("shipTo") : "";
                shipFromName = request.getParameter("shipFrom") != null ? request.getParameter("shipFrom") : "";
                encloseCount = request.getParameter("encloseCount") != null ? request.getParameter("encloseCount") : "0";

                // Union the Content Note & Package Content lists values
                for(int idx=0; idx<Integer.parseInt(encloseCount); idx++) {
                    contentNote += contentNote.length() != 0 ? "###":"";
                    packageContent += packageContent.length() != 0 ? "###":"";
                    tempString = request.getParameter("contentNote"+idx) != null && !request.getParameter("contentNote"+idx).equals("") ? request.getParameter("contentNote"+idx) : "-";
                    contentNote += tempString;
                    tempString = request.getParameter("packageContent"+idx) != null && !request.getParameter("packageContent"+idx).equals("") ? request.getParameter("packageContent"+idx) : "-";
                    packageContent += tempString;
                }

                // Extract Employee ID from employee detail, which has Employee ID and Employee Designation in the form of '<empId>@@@<empDesig>'
                if(boundType.equals("Inbound")) {
                    keySplit = shipTo.split("@@@");
                    shipTo = keySplit.length > 0 ? keySplit[0] : "";
                } else {
                    keySplit = shipFrom.split("@@@");
                    shipFrom = keySplit.length > 0 ? keySplit[0] : "";
                }

                transmittalVO.setProjId(projId);
                transmittalVO.setMailDate(mailDate);
                transmittalVO.setCarrier(carrier);
                transmittalVO.setShipWithAcct(shipWithAcct);
                transmittalVO.setAuthor(author);
                transmittalVO.setTitle(title);
                transmittalVO.setIsbn10(isbn10);
                transmittalVO.setIsbn13(isbn13);
                transmittalVO.setOption(billingOption);
                billingReference=billingReference.replace("'", "\\'");
                transmittalVO.setReference(billingReference);
                transmittalVO.setContentNote(contentNote);
                packageContent=packageContent.replace("'", "\\'");
                transmittalVO.setPackageContent(packageContent);
                remarks=remarks.replace("'", "\\'");
                transmittalVO.setRemarks(remarks);
                transmittalVO.setEmpId(empID);
                transmittalVO.setReceiver(receiver);
                transmittalVO.setShipMethod(shipMethod);
                transmittalVO.setBoundType(boundType);
                carrierTrackNo=carrierTrackNo.replace("'", "\\'");
                transmittalVO.setCarrierTrackNo(carrierTrackNo);
                transmittalVO.setCarrierCost(carrierCost);
                transmittalVO.setShipToLocation(shipToLocation);
                transmittalVO.setShipFromLocation(shipFromLocation);
                transmittalVO.setRequestDate(requestDate);
                transmittalVO.setShipTo(shipTo);
                transmittalVO.setShipToAttention(shipToAttention);
                transmittalVO.setShipDivision(shipDivision);
                transmittalVO.setShipFrom(shipFrom);
                transmittalVO.setShipToName(shipToName);
                transmittalVO.setShipFromName(shipFromName);
                if(shipId.equals("")) {
                    transmittalDAO.SaveTransmittal(transmittalVO);
                } else {
                    transmittalVO.setShipId(shipId);
                    transmittalDAO.UpdateTransmittal(transmittalVO);
                }
                result = transmittalVO.getResult();
                shipId = transmittalVO.getShipId();
                projId = transmittalVO.getProjId();
                responseText = "project_name="+project_name+"&project_name_hidden="+getProjIdParam+"&Result="+result;
                if(!shipId.equals("")) {
                    responseText += "&shipId="+shipId;
                }
            } else if (action.equals("delete")) {
                shipId = request.getParameter("shippingId") != null ? request.getParameter("shippingId") : "";
                transmittalVO.setShipId(shipId);
                transmittalDAO.DeleteTransmittal(transmittalVO);
                result = transmittalVO.getResult();
                projId = transmittalVO.getProjId();
                responseText = "project_name="+project_name+"&project_name_hidden="+getProjIdParam+"&DelResult="+result;
            } else {
                // Do Nothing
            }
            response.sendRedirect("transmittal.jsp?"+responseText);
        } finally { 
            out.close();
        }

    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
