package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pathfinder.functions.generic.FTPClientDAO;
import pathfinder.functions.generic.FTPClientVO;

/**
 *
 * @author Aravindhanj
 */
public class FTPClientServlet extends HttpServlet {
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            System.out.println("processRequest");
        } finally {
            out.close();
        }
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        //processRequest(request, response);
        System.out.println("doPost");
        String ftpCount = request.getParameter("ftpCount") != null ? request.getParameter("ftpCount") : "0";
        String projId = request.getParameter("projectId") != null ? request.getParameter("projectId") : "";
        String projName = request.getParameter("project_name") != null ? request.getParameter("project_name") : "";
        String action = request.getParameter("action") != null? request.getParameter("action"): "";
        String redirect = "MapFtpServer.jsp?project_name_hidden="+projId+"&project_name="+projName+"&action="+action;
        int result = 0;
        FTPClientVO ftpClientVO = new FTPClientVO();
        FTPClientDAO ftpClientDAO = new FTPClientDAO();
        int count = Integer.parseInt(ftpCount);
        System.out.println("1"+ftpCount);
        System.out.println("2"+projId);
        System.out.println("3"+projName);
        System.out.println("4"+action);

        if(action.equals("save")) {
            List getFTPId = new ArrayList();
            List getFTPServer = new ArrayList();
            List getFTPPath = new ArrayList();
            List getUsername = new ArrayList();
            List getPassword = new ArrayList();

            for(int idx=0; idx<count; idx++) {
                String getCheck = request.getParameter("check"+idx) != null ? request.getParameter("check"+idx) : "0";
                System.out.println("CHK: "+getCheck);
                if(getCheck.equals("1")) {
                    getFTPId.add(request.getParameter("ftpId"+idx) != null ? request.getParameter("ftpId"+idx) : "0");
                    getFTPServer.add(request.getParameter("ftpServer"+idx) != null ? request.getParameter("ftpServer"+idx) : "");
                    getFTPPath.add(request.getParameter("ftpPath"+idx) != null ? request.getParameter("ftpPath"+idx) : "");
                    getUsername.add(request.getParameter("username"+idx) != null ? request.getParameter("username"+idx) : "");
                    getPassword.add(request.getParameter("password"+idx) != null ? request.getParameter("password"+idx) : "");
                }
            }
            System.out.println(getFTPId);
            System.out.println(getFTPServer);
            System.out.println(getFTPPath);
            System.out.println(getUsername);
            System.out.println(getPassword);
            if(getFTPId.size() > 0) {
                ftpClientVO.setProjectId(projId);
                ftpClientVO.setFtpIdList(getFTPId);
                ftpClientVO.setFtpServerList(getFTPServer);
                ftpClientVO.setFtpPathList(getFTPPath);
                ftpClientVO.setUsernameList(getUsername);
                ftpClientVO.setPasswordList(getPassword);
                ftpClientDAO.saveClientFTP(ftpClientVO);
                result = ftpClientVO.getResult();
            }
            
        } else if(action.equals("delete")) {
            List getFTPId = new ArrayList();
            for(int idx=0; idx<count; idx++) {
                String getCheck = request.getParameter("check"+idx) != null ? request.getParameter("check"+idx) : "0";
                if(getCheck.equals("1")) {
                    getFTPId.add(request.getParameter("ftpId"+idx) != null ? request.getParameter("ftpId"+idx) : "");
                }
            }
            if(getFTPId.size() > 0) {
                ftpClientVO.setFtpIdList(getFTPId);
                ftpClientDAO.deleteClientFTP(ftpClientVO);
                result = ftpClientVO.getResult();
            }
        }
        response.sendRedirect("MapFtpServer.jsp?project_name_hidden="+projId+"&project_name="+projName+"&action="+action+"&result="+result);
    }

}
