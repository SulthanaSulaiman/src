/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import pathfinder.functions.projects.*;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Pattany
 */
public class AddNewBillingNotesServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String projectId = request.getParameter("project_name_hidden") != null ? request.getParameter("project_name_hidden") : "";
        String projectName = request.getParameter("project_name") != null ? request.getParameter("project_name") : "";
        String responseText = "project_name="+projectName+"&project_name_hidden="+projectId;
        BillingNotesVO billingNotesVO = new BillingNotesVO();
        BillingNotesDAO billingNotesDAO = new BillingNotesDAO();
        String opName="";

        String actionType = request.getParameter("name");
        // System.out.println(request.getParameter("count"));
        if (actionType != null) {
            if (actionType.equals("update")) {
                int chkcount = Integer.parseInt(request.getParameter("count"));
                int count = 0;
                for (int i = 0; i <= chkcount; i++) {
                    //String val=     request.getParameter("cb"+String.valueOf(i));
                    String r = String.valueOf(i);
                    String selBox = "cb" + r;
                    String radioBut = "radCharge" + r;
                    String radiononcharge="radNonCharge"+r;
                    String notes = "notesTxt" + r;

                    //System.out.println("value is::"+selBox);
                    if (request.getParameter(selBox) != null) {
                        //count++;
                        String checkid = request.getParameter(selBox);
                        String selRadio=null;
                        if(request.getParameter(radioBut)!=null)
                        {
                         selRadio = request.getParameter(radioBut);
                        }
                        if(request.getParameter(radiononcharge)!=null)
                        {
                         selRadio = request.getParameter(radiononcharge);
                        }
                        
                        String notesBilling = request.getParameter(notes);
                        //System.out.println("::" + checkid);
                        //System.out.println("Check box Testing +++++++++++:::" + checkid + "::" + selRadio + "::" + notesBilling);

                        BillingNotesDAO billingNotesUpdateDao = new BillingNotesDAO();
                        BillingNotesVO billingvo = new BillingNotesVO();
                        billingvo.setBillingNotesID(checkid);
                        billingvo.setBillingNotes(notesBilling);
                        billingvo.setChargeable(selRadio);
                       count= billingNotesUpdateDao.updateBillingNotes(billingvo);
                       if(count!=0)
                       {
                           opName="update";
                       }




                    }
                }
                responseText += "&valid=ok";
            } else if (actionType.equals("delete")) {
                int chkcount = Integer.parseInt(request.getParameter("count"));
                int count = 0;
                for (int i = 0; i < chkcount; i++) {
                    //String val=     request.getParameter("cb"+String.valueOf(i));
                    String r = String.valueOf(i);
                    String selBox = "cb" + r;
                    // String radioBut = "radCharge" + r;
                    //String notes = "notesTxt" + r;

                    //System.out.println("value is::"+selBox);
                    if (request.getParameter(selBox) != null) {
                        //count++;
                        String checkid = request.getParameter(selBox);
                        //String selRadio = request.getParameter(radioBut);
                        // String notesBilling = request.getParameter(notes);
                        System.out.println("::" + checkid);
                        // System.out.println("Check box Testing +++++++++++:::" + checkid + "::" + selRadio + "::" + notesBilling);

                        BillingNotesDAO billingNotesDeleteDao = new BillingNotesDAO();
                        BillingNotesVO billingvo = new BillingNotesVO();
                        billingvo.setBillingNotesID(checkid);
                        // billingvo.setBillingNotes(notesBilling);
                        // billingvo.setChargeable(selRadio);
                       count= billingNotesDeleteDao.deleteBillingNotes(billingvo);

                      if(count!=0)
                       {
                           opName="delete";
                       }


                    }
                }
            }

        } else {
            int count=0;
            HttpSession session = request.getSession();
            String sesEmp = (String) session.getAttribute("theEid");
            String billingNotesValue = request.getParameter("billing_notes_text") != null ? request.getParameter("billing_notes_text") : "";
            String projId = request.getParameter("project_name_hidden") != null ? request.getParameter("project_name_hidden") : "";
            String chargebleVal = request.getParameter("radCharge") != null ? request.getParameter("radCharge") : "0";
            String getUpdateNotesList = request.getParameter("notes_listsize") != null ? request.getParameter("notes_listsize") : "";
            //String nonChargebleVal = request.getParameter("radNonCharge") != null ? request.getParameter("radNonCharge") : "";

            boolean chkState = request.getParameter("chk") != null;

            //System.out.println("Chargeable val : " + chargebleVal);



            billingNotesVO.setEmpID(sesEmp);
            billingNotesVO.setBillingNotes(billingNotesValue);
            billingNotesVO.setProjId(projId);
            billingNotesVO.setChargeable(chargebleVal);
            //billingNotesVO.setNonChargeble(nonChargebleVal);


            count=billingNotesDAO.setBillingNotes(billingNotesVO);
             if(count!=0)
                       {
                           opName="insert";
                       }
            responseText += "&newnote=ok";
        }
        response.sendRedirect("BillingNotes.jsp?"+responseText);



        PrintWriter out = response.getWriter();
      //  out.println(opName);
      response.getWriter().write("ok");
      //  response.getWriter().write("newnotes");


    }
}
