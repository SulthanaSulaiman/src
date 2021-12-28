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
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author rajac
 */
public class AddProjAssignServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        filters.projects.ProjAssignmentDAO prjAssignDAO = null;
        filters.projects.ProjAssignmentVO prjAssignVO = null;
        filters.generic.DeptCellList deptCode = new filters.generic.DeptCellList();

        javax.servlet.http.HttpSession session = request.getSession();

        String responseText = "";
        String operation = "";
        String returnMsg = "";
        String empFacIDParam = "";
        String projDeptCode = "";
        String projCellId = "";

        boolean isAllowed = true;

        int intAddRes = 0;

        HashSet unAssignedRmvDup = new HashSet();

        List unAssignedProjID = new ArrayList();
        List unAssignedProjEmpID = new ArrayList();
        List unAssignedProjEstID = new ArrayList();
        List unAssignedProjEstrID = new ArrayList();
        List unAssignedCellProjID = new ArrayList();
        List unAssignedCellID = new ArrayList();
        List unAssignedCellEmpID = new ArrayList();
        List empFacilityID = new ArrayList();
        List rqstList = new ArrayList();
        List unAssignedFunProjID = new ArrayList();
        List fcyID = new ArrayList();
        List funID = new ArrayList();

        if (request.getParameter("action") != null) {
            operation = request.getParameter("action");
            //System.out.println("operation :" + operation);
        }

        String assignProjID = request.getParameter("proj_id") != null ? request.getParameter("proj_id") : "";
        String assignProjEmpID = request.getParameter("user_id") != null ? request.getParameter("user_id") : "";
        String assignProjEstID = request.getParameter("proj_id") != null ? request.getParameter("proj_id") : "";
        String assignProjEstrID = request.getParameter("user_id") != null ? request.getParameter("user_id") : "";
        String assignCellProjID = request.getParameter("cell_proj_id") != null ? request.getParameter("cell_proj_id") : "";
        String assignCellID = request.getParameter("cell_id") != null ? request.getParameter("cell_id") : "";
        String assignCellEmpID = request.getParameter("cell_emp_id") != null ? request.getParameter("cell_emp_id") : "";
        String assignFunProjID = request.getParameter("proj_fun_id") != null ? request.getParameter("proj_fun_id") : "";
        String assignFcyID = request.getParameter("fcy_id") != null ? request.getParameter("fcy_id") : "";
        String assignFunID = request.getParameter("fun_id") != null ? request.getParameter("fun_id") : "";
        String projEstID = request.getParameter("projEstID") != null ? request.getParameter("projEstID") : "";
        String estrID = request.getParameter("estrID") != null ? request.getParameter("estrID") : "";

        //System.out.println("Proj :" + projEstID+" _ "+estrID);
        //System.out.println("assignCellEmpID :" + assignCellEmpID);

        unAssignedProjEstID.clear();
        unAssignedProjEstrID.clear();
        unAssignedProjID.clear();
        unAssignedProjEmpID.clear();
        unAssignedCellProjID.clear();
        unAssignedCellID.clear();
        unAssignedCellEmpID.clear();
        empFacilityID.clear();
        rqstList.clear();
        unAssignedFunProjID.clear();
        fcyID.clear();
        funID.clear();

        if (!assignFunProjID.equals("")) {
            String[] prj_token = assignFunProjID.split(",");
            for (String prj_id_val : prj_token) {
                //System.out.println("prj_id_val : "+prj_id_val);
                unAssignedFunProjID.add(prj_id_val);
            }
        }
        if (!assignFcyID.equals("")) {
            String[] fcy_token = assignFcyID.split(",");
            for (String fcy_id_val : fcy_token) {
                //System.out.println("prj_id_val : "+prj_id_val);
                fcyID.add(fcy_id_val);
            }
        }
        if (!assignFunID.equals("")) {
            String[] fun_token = assignFunID.split(",");
            for (String fun_id_val : fun_token) {
                //System.out.println("prj_id_val : "+prj_id_val);
                funID.add(fun_id_val);
            }
        }
        //System.out.println("FCY Size :"+fcyID.size());
        if (fcyID.size() != 0) {
            prjAssignVO = new filters.projects.ProjAssignmentVO();
            prjAssignDAO = new filters.projects.ProjAssignmentDAO();
            List functionList = new ArrayList();
            for (int fcyIdx = 0; fcyIdx < fcyID.size(); fcyIdx++) {
                prjAssignVO.setFcyId(fcyID.get(fcyIdx).toString());
                functionList =  prjAssignDAO.getFacilityFunctions(prjAssignVO);
                for (int funIdx = 0; funIdx < functionList.size(); funIdx++) {
                    funID.add(functionList.get(funIdx));
                }
            }
        }

        //System.out.println("Proj Functions : " + unAssignedFunProjID);
        //System.out.println("Fcy : " + fcyID);
        //System.out.println("Functions : " + funID);

        if (!assignProjID.equals("")) {
            String[] prj_token = assignProjID.split(",");
            for (String prj_id_val : prj_token) {
                //System.out.println("prj_id_val : "+prj_id_val);
                unAssignedProjID.add(prj_id_val);
            }
        }

        if (!assignProjEmpID.equals("")) {
            String[] emp_id_token = assignProjEmpID.split(",");
            for (String emp_id_val : emp_id_token) {
                //System.out.println("emp_id_val : "+emp_id_val);
                unAssignedProjEmpID.add(emp_id_val);
            }
        }
        
        if (!projEstID.equals("")) {
            String[] prj_token = projEstID.split(",");
            for (String prj_id_val : prj_token) {
                unAssignedProjEstID.add(prj_id_val);
            }
        }

        if (!estrID.equals("")) {
            String[] emp_id_token = estrID.split(",");
            for (String emp_id_val : emp_id_token) {
                unAssignedProjEstrID.add(emp_id_val);
            }
        }

        if (!assignCellProjID.equals("")) {
            String[] cell_prj_token = assignCellProjID.split(",");
            for (String cell_prj_id_val : cell_prj_token) {
                //System.out.println("prj_id_val : "+prj_id_val);
                unAssignedCellProjID.add(cell_prj_id_val);
            }
        }

        if (!assignCellID.equals("")) {
            String[] cell_id_token = assignCellID.split(",");
            for (String cell_id_val : cell_id_token) {
                //System.out.println("emp_id_val : "+emp_id_val);
                unAssignedCellID.add(cell_id_val);
            }
        }

        if (!assignCellEmpID.equals("")) {
            String[] cell_emp_id_token = assignCellEmpID.split(",");
            for (String cell_emp_id_val : cell_emp_id_token) {
                //System.out.println("emp_id_val : "+emp_id_val);
                unAssignedCellEmpID.add(cell_emp_id_val);
            }
        }

        //System.out.println("before Hash unAssignedCellEmpID :" + unAssignedCellEmpID);
        /*//System.out.println("before Hash unAssignedProjID :" + unAssignedProjID.size());
        //System.out.println("before Hash unAssignedProjEmpID :" + unAssignedProjEmpID.size());
        //System.out.println("before Hash unAssignedCellProjID :" + unAssignedCellProjID.size());
        //System.out.println("before Hash unAssignedCellID :" + unAssignedCellID.size());
        //System.out.println("before Hash unAssignedCellEmpID :" + unAssignedCellEmpID.size());
        //System.out.println("before Hash unAssignedRmvDup :" + unAssignedRmvDup.size());*/

        /*if (!unAssignedCellEmpID.isEmpty()) {
        unAssignedRmvDup.clear();
        unAssignedRmvDup.add(unAssignedCellEmpID);
        unAssignedCellEmpID.clear();
        unAssignedCellEmpID.addAll(unAssignedRmvDup);
        }*/

        //System.out.println("After Hash unAssignedRmvDup :" + unAssignedRmvDup.size());
        //System.out.println("After Hash unAssignedCellEmpID :" + unAssignedCellEmpID.size());

        if (unAssignedCellEmpID.size() != 0) {
            for (int empIdx = 0; empIdx < unAssignedCellEmpID.size(); empIdx++) {
                String EmpID = unAssignedCellEmpID.get(empIdx).toString();
                //System.out.println("EmpID :" + EmpID);
                deptCode.setEmpIDParam(EmpID);
                deptCode.collectEmpFacilityList();
                empFacIDParam = deptCode.getEmpFacilityID();
                empFacilityID.add(empFacIDParam);
            }
        }

        if (unAssignedCellID.size() != 0) {
            projCellId = unAssignedCellID.get(0).toString();
            deptCode.setCellCodeParam(projCellId);
            deptCode.collectDeptList();
            projDeptCode = deptCode.getDeptCode();
        }

        //System.out.println("unAssignedProjID :" + unAssignedProjID);
        //System.out.println("unAssignedProjEmpID :" + unAssignedProjEmpID);
        //System.out.println("unAssignedCellProjID :" + unAssignedCellProjID);
        //System.out.println("unAssignedCellID :" + unAssignedCellID);
        //System.out.println("unAssignedCellEmpID :" + unAssignedCellEmpID);
        //System.out.println("empFacilityID :" + empFacilityID);

        if (operation.equals("save")) {

            if (!unAssignedProjID.isEmpty()) {
                if (!unAssignedProjID.isEmpty() && !unAssignedProjEmpID.isEmpty() && unAssignedProjEmpID.size() > 1) {
                    isAllowed = false;
                    returnMsg = "Allocation Failed!!! Planner should not be more than one.";
                } else {
                    isAllowed = true;
                }
            }

            String projPlannerId = unAssignedProjEmpID.get(0).toString();

            if (isAllowed) {
                //System.out.println("Inside - Save : " + operation);
                //System.out.println("Inside - Save - isAllowed : " + isAllowed);
                for (int unAssProjIdx = 0; unAssProjIdx < unAssignedProjID.size(); unAssProjIdx++) {
                    prjAssignVO = new filters.projects.ProjAssignmentVO();
                    prjAssignVO.setUnAssignedProjId(unAssignedProjID.get(unAssProjIdx).toString());
                    prjAssignVO.setProjPlannerID(projPlannerId);
                    rqstList.add(prjAssignVO);
                }
                prjAssignDAO = new filters.projects.ProjAssignmentDAO();
                intAddRes = prjAssignDAO.saveProjAssignment(rqstList);
                if (intAddRes != 0 && intAddRes >= 1) {
                    returnMsg = "Project assignment successfully completed!!!";
                    responseText = "proj_assign=saved&intAddRes=" + intAddRes + "&returnMsg=" + returnMsg;
                }
            } else {
                //System.out.println("Inside - Save : " + operation);
                //System.out.println("Inside - Save - isAllowed : " + isAllowed);
                responseText = "proj_assign=unsave&intAddRes=" + intAddRes + "&returnMsg=" + returnMsg;
            }
        } else if (operation.equals("saveEst")) {
            String sesEmp = (String) session.getAttribute("theEid");
            if (!unAssignedProjEstID.isEmpty()) {
                if (!unAssignedProjEstID.isEmpty() && !unAssignedProjEstrID.isEmpty() && unAssignedProjEstrID.size() > 1) {
                    isAllowed = false;
                    returnMsg = "Allocation Failed!!! Estimator should not be more than one.";
                } else {
                    isAllowed = true;
                }
            }
            
            //System.out.println("Assign : "+unAssignedProjEstID);
            //System.out.println("Assign : "+unAssignedProjEstrID);

            String projEstrId = unAssignedProjEstrID.get(0).toString();

            if (isAllowed) {
                //System.out.println("Inside - Save : " + operation);
                //System.out.println("Inside - Save - isAllowed : " + isAllowed);
                for (int unAssProjIdx = 0; unAssProjIdx < unAssignedProjEstID.size(); unAssProjIdx++) {
                    prjAssignVO = new filters.projects.ProjAssignmentVO();
                    prjAssignVO.setUnAssignedEstimatorProjId(unAssignedProjEstID.get(unAssProjIdx).toString());
                    prjAssignVO.setEstimatorId(projEstrId);
                    prjAssignVO.setAssignedUser(sesEmp);
                    rqstList.add(prjAssignVO);
                }
                prjAssignDAO = new filters.projects.ProjAssignmentDAO();
                intAddRes = prjAssignDAO.saveProjEstimatorAssignment(rqstList);
                if (intAddRes != 0 && intAddRes >= 1) {
                    returnMsg = "Estimator assignment successfully completed!!!";
                    responseText = "proj_assign=saved&intAddRes=" + intAddRes + "&returnMsg=" + returnMsg;
                }
            } else {
                //System.out.println("Inside - Save : " + operation);
                //System.out.println("Inside - Save - isAllowed : " + isAllowed);
                responseText = "proj_assign=unsave&intAddRes=" + intAddRes + "&returnMsg=" + returnMsg;
            }
        } else if (operation.equals("cellSave")) {

            if (!unAssignedCellProjID.isEmpty()) {
                if (!unAssignedCellProjID.isEmpty() && !unAssignedCellID.isEmpty() && unAssignedCellID.size() > 1) {
                    isAllowed = false;
                    returnMsg = "Allocation Failed!!! Cell should not be more than one.";
                } else {
                    isAllowed = true;
                }
            }

            if (isAllowed) {
                //System.out.println("Inside - Save : " + operation);
                //System.out.println("Inside - Save - isAllowed : " + isAllowed);

                //System.out.println("unAssignedCellProjID :" + unAssignedCellProjID);
                //System.out.println("unAssignedCellProjID_size :" + unAssignedCellProjID.size());
                //System.out.println("unAssignedCellID :" + unAssignedCellID);
                //System.out.println("unAssignedCellID_size :" + unAssignedCellID.size());
                //System.out.println("unAssignedCellEmpID :" + unAssignedCellEmpID);
                //System.out.println("unAssignedCellEmpID_size :" + unAssignedCellEmpID.size());
                //System.out.println("empFacilityID :" + empFacilityID);
                //System.out.println("empFacilityID_size :" + empFacilityID.size());

                if (!unAssignedCellProjID.isEmpty()) {
                    if (!unAssignedCellID.isEmpty() && !unAssignedCellEmpID.isEmpty()) {
                        //System.out.println("Inside - Condition 1");
                        for (int unAssProjCellIdx = 0; unAssProjCellIdx < unAssignedCellProjID.size(); unAssProjCellIdx++) {
                            prjAssignVO = new filters.projects.ProjAssignmentVO();
                            prjAssignVO.setUnAssignedCellProjId(unAssignedCellProjID.get(unAssProjCellIdx).toString());
                            prjAssignVO.setProjCellID(projCellId);
                            prjAssignVO.setProjDeptCode(projDeptCode);
                            prjAssignVO.setProjCellEmpID(unAssignedCellEmpID);
                            prjAssignVO.setProjEmpFacilityID(empFacilityID);
                            rqstList.add(prjAssignVO);
                        }
                    } else if (!unAssignedCellID.isEmpty()) {
                        //System.out.println("Inside - Condition 2");
                        for (int unAssProjCellIdx = 0; unAssProjCellIdx < unAssignedCellProjID.size(); unAssProjCellIdx++) {
                            prjAssignVO = new filters.projects.ProjAssignmentVO();
                            prjAssignVO.setUnAssignedCellProjId(unAssignedCellProjID.get(unAssProjCellIdx).toString());
                            prjAssignVO.setProjCellID(projCellId);
                            prjAssignVO.setProjDeptCode(projDeptCode);
                            rqstList.add(prjAssignVO);
                        }
                    } else if (!unAssignedCellEmpID.isEmpty()) {
                        //System.out.println("Inside - Condition 3");
                        for (int unAssProjCellIdx = 0; unAssProjCellIdx < unAssignedCellProjID.size(); unAssProjCellIdx++) {
                            prjAssignVO = new filters.projects.ProjAssignmentVO();
                            prjAssignVO.setUnAssignedCellProjId(unAssignedCellProjID.get(unAssProjCellIdx).toString());
                            prjAssignVO.setProjCellEmpID(unAssignedCellEmpID);
                            prjAssignVO.setProjEmpFacilityID(empFacilityID);
                            rqstList.add(prjAssignVO);
                        }
                    }
                }

                prjAssignDAO = new filters.projects.ProjAssignmentDAO();
                intAddRes = prjAssignDAO.saveProjCellAssignment(rqstList);
                if (intAddRes != 0 && intAddRes >= 1) {
                    returnMsg = "Project assignment successfully completed!!!";
                    responseText = "proj_assign=saved&intAddRes=" + intAddRes + "&returnMsg=" + returnMsg;
                }
            } else {
                //System.out.println("Inside - Save : " + operation);
                //System.out.println("Inside - Save - isAllowed : " + isAllowed);
                responseText = "proj_assign=unsave&intAddRes=" + intAddRes + "&returnMsg=" + returnMsg;
            }
        } else if (operation.equals("funSave")) {
            String sesEmp = (String) session.getAttribute("theEid");
            if (!unAssignedFunProjID.isEmpty()) {
                if (!unAssignedFunProjID.isEmpty() && !funID.isEmpty() && unAssignedFunProjID.size() > 1) {
                    isAllowed = false;
                    returnMsg = "Allocation Failed!!! Project should not be more than one.";
                } else {
                    isAllowed = true;
                }
            }

            if (isAllowed) {

                if (!unAssignedFunProjID.isEmpty() && !funID.isEmpty()) {

                for (int unAssignedFunIDx = 0; unAssignedFunIDx < funID.size(); unAssignedFunIDx++) {
                        prjAssignVO = new filters.projects.ProjAssignmentVO();
                        //Function Assigned by the current login user
                        prjAssignVO.setAssignedUser(sesEmp);
                        //Only one project can be assigned at a time so we getting the first project
                        prjAssignVO.setUnAssignedFunctionProjId(unAssignedFunProjID.get(0).toString());
                        prjAssignVO.setFunctionDeptId(funID.get(unAssignedFunIDx).toString());
                        rqstList.add(prjAssignVO);
                    }
                }
                //System.out.println(">"+prjAssignVO.getFunctionDeptId());
                prjAssignDAO = new filters.projects.ProjAssignmentDAO();
                intAddRes = prjAssignDAO.saveProjFunctionAssignment(rqstList);
                if (intAddRes != 0 && intAddRes >= 1) {
                    returnMsg = "Function assignment successfully completed!!!";
                    responseText = "proj_assign=saved&intAddRes=" + intAddRes + "&returnMsg=" + returnMsg;
                }
            } else {
                //System.out.println("Inside - Save : " + operation);
                //System.out.println("Inside - Save - isAllowed : " + isAllowed);
                responseText = "proj_assign=unsave&intAddRes=" + intAddRes + "&returnMsg=" + returnMsg;
            }
        }

        //System.out.println("isAllowed :" + isAllowed);
        //System.out.println("returnMsg :" + returnMsg);

        PrintWriter out = response.getWriter();
        //out.println(getProjIdParam.trim());
        ////System.out.println("responseText: " + responseText.trim());
        out.println(responseText.trim());
    }
}
