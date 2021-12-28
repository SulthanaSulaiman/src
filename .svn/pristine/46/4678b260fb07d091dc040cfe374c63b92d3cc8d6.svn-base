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
public class ShippingMethodServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //System.out.println("servlet executing >>>>>>>>>>>>>>>>>>>>");
        ShippingMethodVO shippingMethodVO = new ShippingMethodVO();
        ShippingMethodDAO shippingMethodDAO = new ShippingMethodDAO();

        HttpSession session = request.getSession();
        String sesEmp = (String) session.getAttribute("theEid");
        String projId = request.getParameter("project_name_hidden") != null ? request.getParameter("project_name_hidden") : "";
        String querierId = request.getParameter("id_querier") != null ? request.getParameter("id_querier") : "";
        String shipmentModeId = request.getParameter("id_shipment_mode") != null ? request.getParameter("id_shipment_mode") : "";
        String shippingValue = request.getParameter("shipping_method_value") != null ? request.getParameter("shipping_method_value") : "";

//System.out.println("Project ID: "+projId);
       shippingMethodVO.setEmpId(sesEmp);
       shippingMethodVO.setQerierId(querierId);
       shippingMethodVO.setProjId(projId);
       shippingMethodVO.setModeOfShippmentId(shipmentModeId);
       shippingMethodVO.setValue(shippingValue);

       shippingMethodDAO.setShippingMethod(shippingMethodVO);

        PrintWriter out = response.getWriter();

        // out.println(responseText.trim());
      }
}
