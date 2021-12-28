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
import pathfinder.functions.revenue.CategoryCostDAO;
import pathfinder.functions.revenue.CategoryCostVO;

/**
 *
 * @author Aravindhanj
 */
public class ManageCategoryCostServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        pathfinder.functions.revenue.CategoryCostDAO categoryCostDAO = new CategoryCostDAO();
        pathfinder.functions.revenue.CategoryCostVO categoryCostVO = new CategoryCostVO();

        String action = request.getParameter("action")!=null?request.getParameter("action"):"";

        String categoryId = "";
        String categoryCost = "";
        String categoryCostId = "";

        String result = "";
        List categoryCostIdList = new ArrayList();
        List categoryIdList = new ArrayList();
        List categoryCostList = new ArrayList();
        List newCategoryIdList = new ArrayList();
        List newCategoryCostList = new ArrayList();
        List delCategoryCostIdList = new ArrayList();
        try {

            // Update Category Values
            if(action.equals("SaveClientCost")) {
                categoryCostVO = new CategoryCostVO();
                String getClientIdParam = request.getParameter("client_name_hidden")!=null?request.getParameter("client_name_hidden"):"0";
                String getClientNameParam = request.getParameter("client_name")!=null?request.getParameter("client_name"):"0";
                String getCategoryCount = request.getParameter("categoryCount")!=null?request.getParameter("categoryCount"):"0";
                int categorySize = Integer.parseInt(getCategoryCount);
                for(int i=0;i<categorySize;i++)
                {
                    categoryId = request.getParameter("categoryId"+i)!=null ? request.getParameter("categoryId"+i) : "";
                    categoryCost = request.getParameter("categoryCost"+i)!=null ? request.getParameter("categoryCost"+i) : "";
                    categoryCostId = request.getParameter("categoryCostId"+i)!=null ? request.getParameter("categoryCostId"+i) : "";
                    if(categoryCostId.equals("") && !categoryCost.equals("")) {
                        newCategoryIdList.add(categoryId);
                        newCategoryCostList.add(categoryCost);
                    } else if (!categoryCostId.equals("") && !categoryCost.equals("")) {
                        categoryCostIdList.add(categoryCostId);
                        categoryIdList.add(categoryId);
                        categoryCostList.add(categoryCost);
                    } else if (!categoryCostId.equals("") && categoryCost.equals("")) {
                        delCategoryCostIdList.add(categoryCostId);
                    } else {
                        // Do nothing
                    }
                }
                categoryCostVO.setClientId(getClientIdParam);

                // Add new Category cost
                categoryCostVO.setNewCategoryIdList(newCategoryIdList);
                categoryCostVO.setNewCategoryCostList(newCategoryCostList);
                categoryCostDAO.InsertCategoryValue(categoryCostVO);

                // Update Category Cost
                categoryCostVO.setCostCategoryId(categoryCostIdList);
                categoryCostVO.setCategoryId(categoryIdList);
                categoryCostVO.setCategoryValue(categoryCostList);
                categoryCostDAO.UpdateCategoryValue(categoryCostVO);

                // Delete Category Cost
                categoryCostVO.setDelCategoryIdList(delCategoryCostIdList);
                categoryCostDAO.DeleteCategoryValue(categoryCostVO);

                result = "result="+String.valueOf(categoryCostVO.getResult());
                result += "&client_name_hidden="+getClientIdParam+"&client_name="+getClientNameParam+"&currentTab=Client";
                response.sendRedirect("CategoryCost.jsp?"+result);

            } else if(action.equals("SaveGeneralCost")) {

                categoryCostVO = new CategoryCostVO();
                String getCategoryCount = request.getParameter("categoryCount")!=null?request.getParameter("categoryCount"):"0";
                int categorySize = Integer.parseInt(getCategoryCount);
                for(int i=0;i<categorySize;i++)
                {
                    categoryId = request.getParameter("genCategoryId"+i)!=null ? request.getParameter("genCategoryId"+i) : "";
                    categoryCost = request.getParameter("genCategoryCost"+i)!=null ? request.getParameter("genCategoryCost"+i) : "";
                    categoryCostId = request.getParameter("genCategoryCostId"+i)!=null ? request.getParameter("genCategoryCostId"+i) : "";
                    if(categoryCostId.equals("") && !categoryCost.equals("")) {
                        newCategoryIdList.add(categoryId);
                        newCategoryCostList.add(categoryCost);
                    } else if (!categoryCostId.equals("") && !categoryCost.equals("")) {
                        categoryCostIdList.add(categoryCostId);
                        categoryIdList.add(categoryId);
                        categoryCostList.add(categoryCost);
                    } else if (!categoryCostId.equals("") && categoryCost.equals("")) {
                        delCategoryCostIdList.add(categoryCostId);
                    } else {
                        // Do nothing
                    }
                }
                //Set Client ID as General Category Id 0(Zero)
                categoryCostVO.setClientId("0");

                // Add new Category cost
                categoryCostVO.setNewCategoryIdList(newCategoryIdList);
                categoryCostVO.setNewCategoryCostList(newCategoryCostList);
                categoryCostDAO.InsertCategoryValue(categoryCostVO);

                // Update Category Cost
                categoryCostVO.setCostCategoryId(categoryCostIdList);
                categoryCostVO.setCategoryId(categoryIdList);
                categoryCostVO.setCategoryValue(categoryCostList);
                categoryCostDAO.UpdateCategoryValue(categoryCostVO);

                // Delete Category Cost
                categoryCostVO.setDelCategoryIdList(delCategoryCostIdList);
                categoryCostDAO.DeleteCategoryValue(categoryCostVO);

                result = "result="+String.valueOf(categoryCostVO.getResult());
                result += "&currentTab=General";
                response.sendRedirect("CategoryCost.jsp?"+result);

            }
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
