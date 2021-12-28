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

import pathfinder.functions.projects.*;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 *
 * @author Pattany
 */
public class ManageMilestoneServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
        {
            ManageMilestonesVO manageMilestonesVO = new ManageMilestonesVO();
            ManageMilestonesDAO manageMilestonesDAO = new ManageMilestonesDAO();
            List mappedMilestones = new ArrayList();
            List unMappedMilestones = new ArrayList();
            List mappedMilestoneOrder = new ArrayList();
            String param = "";
            String[] splitStr;
            String result = "";

            HttpSession session = request.getSession();
            String stageCode = request.getParameter("id_stage") != null ? request.getParameter("id_stage") : "";
            String milestoneCode = request.getParameter("id_milestone") != null ? request.getParameter("id_milestone") : "";

            String getActivity_CodeSize = request.getParameter("activity_codesize")!=null?request.getParameter("activity_codesize"):"0";
            String getallocActivity_CodeSize = request.getParameter("allocActivity_CodeSize")!=null?request.getParameter("allocActivity_CodeSize"):"0";

            String getMappedMileCount = request.getParameter("mappedMileCount")!=null?request.getParameter("mappedMileCount"):"0";
            String getUnMappedMileCount = request.getParameter("unMappedMileCount")!=null?request.getParameter("unMappedMileCount"):"0";
            String getAction = request.getParameter("Action")!=null?request.getParameter("Action"):"";

            manageMilestonesVO.setStageCode(stageCode);
            manageMilestonesVO.setMilestoneCode(milestoneCode);

            // Manage Milestone's Activity Segment below..

            if(request.getParameter("allocateButton")!=null)
            {
                int act_allocSize = Integer.parseInt(getActivity_CodeSize);

                //List allocActivityNameList = new ArrayList();
                for(int i=0;i<act_allocSize;i++)
                {
                    String chk = "addChk"+String.valueOf(i);
                    String chk_value = "";

                    if(request.getParameter(chk)!=null)
                    {
                        ManageMilestonesVO activityNamesVO = new ManageMilestonesVO();
                        chk_value = request.getParameter(chk);
                        activityNamesVO.setMilestoneCode(milestoneCode);
                        activityNamesVO.setActivityMapCode(chk_value);
                        activityNamesVO.setStageCode(stageCode);
                        manageMilestonesDAO.setActivityNames(activityNamesVO);
                        //allocActivityNameList.add(activityNamesVO);
                    }

                }
                response.sendRedirect("ManageMilestones.jsp?id_stage="+stageCode+"&id_milestone="+milestoneCode);
            }

            if(request.getParameter("removeButton")!=null)
            {
                int act_allocSize = Integer.parseInt(getallocActivity_CodeSize);

                for(int i=0;i<act_allocSize;i++)
                {
                    String chk = "remChk"+String.valueOf(i);
                    String chk_value = "";

                    if(request.getParameter(chk)!=null)
                    {
                        ManageMilestonesVO unMapactivityNamesVO = new ManageMilestonesVO();
                        chk_value = request.getParameter(chk);
                        //System.out.println("chk_value:"+chk_value);
                        unMapactivityNamesVO.setMilestoneCode(milestoneCode);
                        //unMapactivityNamesVO.setActivityUnmapCode(chk_value);
                        unMapactivityNamesVO.setMilestoneActId(chk_value);
                        unMapactivityNamesVO.setStageCode(stageCode);
                        manageMilestonesDAO.delActivityNames(unMapactivityNamesVO);
                    }
                }
                response.sendRedirect("ManageMilestones.jsp?id_stage="+stageCode+"&id_milestone="+milestoneCode);
            }

            // Manage Stage's Milestones Segment below..

            if(getAction.equals("allocateMilestone"))
            {
                int mapMileSize = Integer.parseInt(getUnMappedMileCount);
                for(int i=0;i<mapMileSize;i++)
                {
                    if(request.getParameter("unMapMilestone"+i)!=null)
                    {
                        mappedMilestones.add(request.getParameter("unMapMilestone"+i));
                    }
                }
                manageMilestonesVO = new ManageMilestonesVO();
                manageMilestonesDAO = new ManageMilestonesDAO();
                manageMilestonesVO.setStageCode(stageCode);
                manageMilestonesVO.setMappedMilestoneId(mappedMilestones);
                manageMilestonesDAO.AllocateStageMilestone(manageMilestonesVO);
                result = String.valueOf(manageMilestonesVO.getResult()).equals("1")?"1":"2";
                result = "&result=" + result;
                response.sendRedirect("ManageStageMilestones.jsp?id_stage="+stageCode+result);

            }

            if(getAction.equals("unAllocateMilestone"))
            {
                String mappedMilestone = "";
                int unMapMileSize = Integer.parseInt(getMappedMileCount);
                for(int i=0;i<unMapMileSize;i++)
                {
                    if(request.getParameter("mapMilestone"+i)!=null)
                    {
                        param = request.getParameter("mapMilestone"+i)==null?"":request.getParameter("mapMilestone"+i);
                        splitStr = param.split("_");
                        mappedMilestone = splitStr[0];
                        unMappedMilestones.add(mappedMilestone);
                    }
                }
                manageMilestonesVO = new ManageMilestonesVO();
                manageMilestonesDAO = new ManageMilestonesDAO();
                manageMilestonesVO.setStageCode(stageCode);
                manageMilestonesVO.setUnMappedMilestoneId(unMappedMilestones);
                manageMilestonesDAO.UnAllocateStageMilestone(manageMilestonesVO);
                result = String.valueOf(manageMilestonesVO.getResult()).equals("1")?"3":"4";
                result = "&result=" + result;
                response.sendRedirect("ManageStageMilestones.jsp?id_stage="+stageCode+result);
            }

            if(getAction.equals("saveOrder"))
            {
                String mappedMilestone = "";
                String milestoneOrder = "";
                int mappedMileSize = Integer.parseInt(getMappedMileCount);
                for(int i=0;i<mappedMileSize;i++)
                {
//                    milestoneOrder = request.getParameter("mileOrder"+i)!=null?request.getParameter("mileOrder"+i):"";
//                    mappedMilestone = request.getParameter("mapMilestone"+i)!=null?request.getParameter("mapMilestone"+i):"";
                    param = request.getParameter("mapMilestone"+i)==null?"":request.getParameter("mapMilestone"+i);
                    splitStr = param.split("_");
                    mappedMilestone = splitStr[0];
                    milestoneOrder = splitStr[1];
                    mappedMilestones.add(mappedMilestone);
                    mappedMilestoneOrder.add(milestoneOrder);
                }
                manageMilestonesVO = new ManageMilestonesVO();
                manageMilestonesDAO = new ManageMilestonesDAO();
                manageMilestonesVO.setStageCode(stageCode);
                manageMilestonesVO.setMappedMilestoneId(mappedMilestones);
                manageMilestonesVO.setMappedMilestoneOrder(mappedMilestoneOrder);
                manageMilestonesDAO.RedefineMilestoneOrder(manageMilestonesVO);
                result = String.valueOf(manageMilestonesVO.getResult()).equals("1")?"5":"6";
                result = "&result=" + result;
                response.sendRedirect("ManageStageMilestones.jsp?id_stage="+stageCode+result);
            }

            PrintWriter out = response.getWriter();

    }
}
