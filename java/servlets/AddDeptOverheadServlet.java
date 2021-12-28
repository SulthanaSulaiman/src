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
public class AddDeptOverheadServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        javax.servlet.http.HttpSession session = request.getSession();
        String sesEmp = (String) session.getAttribute("theEid");
        String responseText = "";
        int returnCode = 0;

        List dept_code = new ArrayList();
        List overheadId = new ArrayList();

        String deptOverhead = request.getParameter("dept_overhead") != null ? request.getParameter("dept_overhead") : "0";
        //System.out.println("deptOverhead----:"+deptOverhead);
        if (deptOverhead.equals("")) {
            deptOverhead = "0";
        }


        String count = request.getParameter("deptSize");
        if (count == null || count.equals("")) {
            count = "1";
        }

        String overheadCount = request.getParameter("deptOverheadSize");
        if (overheadCount == null || overheadCount.equals("")) {
            overheadCount = "1";
        }




        pathfinder.functions.timesheet.AllocateOverhead overhead = new pathfinder.functions.timesheet.AllocateOverhead();
        String name;
        String operation = "";
        if (request.getParameter("save") != null) {
            operation = request.getParameter("save");
        }
        if (operation.equals("save")) {
            for (int i = 0; i < Integer.parseInt(count); i++) {
                name = String.valueOf("emp" + i);

                if (request.getParameter(name) != null) {

                    dept_code.add(request.getParameter(name));

                }
            }

            overhead.setDeptCode(dept_code);
            overhead.setDeptOverheadVal(Double.parseDouble(deptOverhead));
            returnCode = overhead.saveDeptOverhead();

            responseText = overhead.getReturnMessage();
            responseText = "save=save&returnCode=" + returnCode + "&returnMsg=" + responseText + "&" + request.getQueryString();

        } else if (operation.equals("delete")) {
           // System.out.println("overheadId:" + overheadCount);
            for (int i = 0; i < Integer.parseInt(overheadCount); i++) {
                name = String.valueOf("overhead" + i);

                if (request.getParameter(name) != null) {

                    overheadId.add(request.getParameter(name));

                }
            }
           // System.out.println("overheadId:" + overheadId.size());
            overhead.setDeptOverheadId(overheadId);

            returnCode = overhead.deleteDeptOverhead();

            responseText = overhead.getReturnMessage();
            responseText = "save=delete&returnCode=" + returnCode + "&returnMsg=" + responseText + "&" + request.getQueryString();
        }else if (operation.equals("update")) {
            List updatedCost = new ArrayList();
            String costName;
            for (int i = 0; i < Integer.parseInt(overheadCount); i++) {
                name = String.valueOf("overhead" + i);
                //costName = String.valueOf("updateCost"+i);

                if (request.getParameter(name) != null) {

                    overheadId.add(request.getParameter(name));
                    //updatedCost.add(request.getParameter(costName));

                }
            }

            overhead.setDeptOverheadId(overheadId);
            //overhead.setOverheadCost(updatedCost);
             overhead.setDeptOverheadVal(Double.parseDouble(deptOverhead));
            returnCode = overhead.updateDeptOverhead();

            responseText = overhead.getReturnMessage();
            responseText = "save=update&returnCode=" + returnCode + "&returnMsg=" + responseText + "&" + request.getQueryString();

        }

        PrintWriter out = response.getWriter();
//out.println(getProjIdParam.trim());
        out.println(responseText.trim());
    }
}

