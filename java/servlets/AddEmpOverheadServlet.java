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
public class AddEmpOverheadServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        javax.servlet.http.HttpSession session = request.getSession();
        String sesEmp = (String) session.getAttribute("theEid");
        String responseText = "";
        int returnCode = 0;

        List emp_id = new ArrayList();
        List overheadId = new ArrayList();

        String empOverhead = request.getParameter("emp_overhead") != null ? request.getParameter("emp_overhead") : "0";
        if (empOverhead.equals("")) {
            empOverhead = "0";
        }
        String count = request.getParameter("empSize");
        if (count == null || count.equals("")) {
            count = "1";
        }

        String overheadCount = request.getParameter("empOverheadSize");
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

                    emp_id.add(request.getParameter(name));

                }
            }

            overhead.setEmpId(emp_id);
            overhead.setEmpOverheadVal(Double.parseDouble(empOverhead));
            returnCode = overhead.saveEmpOverhead();

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
            overhead.setEmpOverheadId(overheadId);

            returnCode = overhead.deleteEmpOverhead();

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

            overhead.setEmpOverheadId(overheadId);
            //overhead.setOverheadCost(updatedCost);
             overhead.setEmpOverheadVal(Double.parseDouble(empOverhead));
            returnCode = overhead.updateEmpOverhead();

            responseText = overhead.getReturnMessage();
            responseText = "save=update&returnCode=" + returnCode + "&returnMsg=" + responseText + "&" + request.getQueryString();

        }

        PrintWriter out = response.getWriter();
//out.println(getProjIdParam.trim());
        out.println(responseText.trim());
    }
}

