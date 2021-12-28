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
import pathfinder.functions.projects.ManageCategoryMilestonesDAO;
import pathfinder.functions.projects.ManageCategoryMilestonesVO;

/**
 *
 * @author Aravindhanj
 */
public class ManageCategoryMilestonesServlet extends HttpServlet {
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        pathfinder.functions.projects.ManageCategoryMilestonesDAO manageCategoryMilestonesDAO = new ManageCategoryMilestonesDAO();
        pathfinder.functions.projects.ManageCategoryMilestonesVO manageCategoryMilestonesVO = new ManageCategoryMilestonesVO();

        String action = request.getParameter("action")!=null?request.getParameter("action"):"";

        String param = "";
        String[] splitStr;
        String result = "";

        String milestone = "";
        String category = "";
        String superCategory = "";
        String poActivity = "";

        List milestoneIdList = new ArrayList();
        List categoryIdList = new ArrayList();
        List superCategoryIdList = new ArrayList();
        List poActivityIdList = new ArrayList();

        try {

            // Map Category under Super Category
            if(action.equals("saveCatSupCat")) {
                manageCategoryMilestonesVO = new ManageCategoryMilestonesVO();
                String getCategoryCount = request.getParameter("categoryCount")!=null?request.getParameter("categoryCount"):"0";
                int categorySize = Integer.parseInt(getCategoryCount);
                for(int i=0;i<categorySize;i++)
                {
                    param = request.getParameter("catSupCatHidden"+i);
                    splitStr = param.split("_");
                    
                    category = splitStr[0];
                    superCategory = splitStr[1];
                    categoryIdList.add(category);
                    superCategoryIdList.add(superCategory);
                }
                manageCategoryMilestonesVO.setCategoryIdList(categoryIdList);
                manageCategoryMilestonesVO.setSuperCategoryIdList(superCategoryIdList);
                manageCategoryMilestonesDAO.mapCategoryUnderSuperCategory(manageCategoryMilestonesVO);
                result = "result="+String.valueOf(manageCategoryMilestonesVO.getResult());
                result += "&action=category";
                response.sendRedirect("ManageCategoryMilestones.jsp?"+result);
            }

            // Map Milestones under Category
            if(action.equals("saveMileCat")) {
                manageCategoryMilestonesVO = new ManageCategoryMilestonesVO();
                String getMilestoneCount = request.getParameter("milestoneCount")!=null?request.getParameter("milestoneCount"):"0";
                int milestoneSize = Integer.parseInt(getMilestoneCount);
                for(int i=0;i<milestoneSize;i++)
                {
                    param = request.getParameter("mileCatHidden"+i);
                    splitStr = param.split("_");

                    milestone = splitStr[0];
                    category = splitStr[1];
                    milestoneIdList.add(milestone);
                    categoryIdList.add(category);
                }
                manageCategoryMilestonesVO.setMilestoneIdList(milestoneIdList);
                manageCategoryMilestonesVO.setCategoryIdList(categoryIdList);
                manageCategoryMilestonesDAO.mapMilestoneUnderCategory(manageCategoryMilestonesVO);
                result = "result="+String.valueOf(manageCategoryMilestonesVO.getResult());
                result += "&action=milestone";
                response.sendRedirect("ManageCategoryMilestones.jsp?"+result);
            }

            // Map Milestones under Category
            if(action.equals("savePOSupCat")) {
                manageCategoryMilestonesVO = new ManageCategoryMilestonesVO();
                String getActivityCount = request.getParameter("poActivityCount")!=null?request.getParameter("poActivityCount"):"0";
                int poActivitySize = Integer.parseInt(getActivityCount);
                for(int i=0;i<poActivitySize;i++)
                {
                    param = request.getParameter("poSupCatHidden"+i);
                    splitStr = param.split("_");

                    poActivity = splitStr[0];
                    superCategory = splitStr[1];
                    poActivityIdList.add(poActivity);
                    superCategoryIdList.add(superCategory);
                }
                manageCategoryMilestonesVO.setPoActivityIdList(poActivityIdList);
                manageCategoryMilestonesVO.setPoSuperCategoryList(superCategoryIdList);
                manageCategoryMilestonesDAO.mapPOActivityUnderSupCategory(manageCategoryMilestonesVO);
                result = "result="+String.valueOf(manageCategoryMilestonesVO.getResult());
                result += "&action=po";
                response.sendRedirect("ManageCategoryMilestones.jsp?"+result);
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
