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
import pathfinder.functions.revenue.EstimateDAO;
import pathfinder.functions.revenue.EstimateVO;

/**
 *
 * @author Pattany
 */
public class EstimateServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EstimateVO estimateVO = new EstimateVO();
        EstimateDAO estimateDAO = new EstimateDAO();
        String opName = "";

        String projectId = request.getParameter("project_name_hidden") != null ? request.getParameter("project_name_hidden") : "";
        String projectName = request.getParameter("project_name") != null ? request.getParameter("project_name") : "";
        String responseText = "project_name="+projectName+"&project_name_hidden="+projectId+"&estValid=ok";

        // System.out.println(request.getParameter("count"));

        int chkcount = Integer.parseInt(request.getParameter("count"));
        int count = 0;
        for (int i = 0; i < chkcount; i++) {
            //String val=     request.getParameter("cb"+String.valueOf(i));
            String r = String.valueOf(i);
            String selBox = "cbe" + r;

            String actQuan = "actQuant" + r;


            //System.out.println("value is::"+selBox);
            if (request.getParameter(selBox) != null) {
                //count++;
                String checkid = request.getParameter(selBox);

                String actQuantity = request.getParameter(actQuan);
                //System.out.println("::" + checkid);
                 //System.out.println("Check box Testing +++++++++++:::" + checkid + "::" + actQuantity);


                estimateVO.setEstId(checkid);
                estimateVO.setActualQuant(actQuantity);
                count = estimateDAO.updateEstimate(estimateVO);
                if (count != 0) {
                    opName = "update";
                }

            }
        }


        response.sendRedirect("BillingNotes.jsp?"+responseText);
        PrintWriter out = response.getWriter();

    }
}
