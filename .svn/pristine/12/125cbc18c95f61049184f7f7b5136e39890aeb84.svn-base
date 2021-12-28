/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package filters.projects;

/**
 *
 * @author rajac
 */
import connection.DBconnection;
import java.io.Serializable;
import java.sql.*;
import java.util.*;

public class ProjAssignmentDAO implements Serializable {

    DBconnection dbconnection = new DBconnection();
    private Connection con = null;
    private Statement st = null;
    private ResultSet rs = null;
    private String unassigned_proj_query = "";
    private String unassigned_proj_cell_query = "";
    private String unassigned_proj_function_query = "";
    private String proj_planner_query = "";
    private String facility_function_list = "";

    public List getUnAssignedProjDetails() {

        ProjAssignmentVO projAssignVO = null;
        List projUnassignedList = new ArrayList();

        try {

            con = dbconnection.getSampleProperty();
            st = con.createStatement();

            unassigned_proj_query = " select proj_id,proj_name,DATE_FORMAT(proj_date,'%d-%b-%Y') "
                    + " from projects "
                    + " where project_status <> '2' and planner is null and proj_date > '2013-01-25 00:00:00' order by proj_date ";

            rs = st.executeQuery(unassigned_proj_query);

            while (rs.next()) {

                projAssignVO = new ProjAssignmentVO();

                if (rs.getString(1) != null) {
                    projAssignVO.setUnAssignedProjId(rs.getString(1));
                } else {
                    projAssignVO.setUnAssignedProjId("");
                }

                if (rs.getString(2) != null) {
                    projAssignVO.setUnAssignedProjName(rs.getString(2));
                } else {
                    projAssignVO.setUnAssignedProjName("");
                }

                if (rs.getString(3) != null) {
                    projAssignVO.setUnAssignedProjRecDate(rs.getString(3));
                } else {
                    projAssignVO.setUnAssignedProjRecDate("");
                }

                projUnassignedList.add(projAssignVO);
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } catch (NullPointerException npe) {
                npe.printStackTrace();
            }
        }

        return projUnassignedList;
    }

    public List getUnAssignedCellProjDetails() {

        ProjAssignmentVO projAssignVO = null;
        List projUnassignedCellList = new ArrayList();

        try {

            con = dbconnection.getSampleProperty();
            st = con.createStatement();

            unassigned_proj_cell_query = " select proj.proj_id,proj.proj_name,DATE_FORMAT(proj.proj_date,'%d-%b-%Y') "
                    + " from projects proj "
                    + " where proj.project_status <> '2' and proj.proj_date > '2013-01-25 00:00:00' and proj.cell_code is null and "
                    + " proj.proj_id not in (select t.proj_id from project_team t) order by proj.proj_date ";

            rs = st.executeQuery(unassigned_proj_cell_query);

            while (rs.next()) {

                projAssignVO = new ProjAssignmentVO();

                if (rs.getString(1) != null) {
                    projAssignVO.setUnAssignedCellProjId(rs.getString(1));
                } else {
                    projAssignVO.setUnAssignedCellProjId("");
                }

                if (rs.getString(2) != null) {
                    projAssignVO.setUnAssignedCellProjName(rs.getString(2));
                } else {
                    projAssignVO.setUnAssignedCellProjName("");
                }

                if (rs.getString(3) != null) {
                    projAssignVO.setUnAssignedCellProjRecDate(rs.getString(3));
                } else {
                    projAssignVO.setUnAssignedCellProjRecDate("");
                }

                projUnassignedCellList.add(projAssignVO);
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } catch (NullPointerException npe) {
                npe.printStackTrace();
            }
        }

        return projUnassignedCellList;
    }

    public List getUnAssignedEstimationProjDetails() {

        ProjAssignmentVO estimatorProjAssignVO = null;
        List projUnassignedEstimatorList = new ArrayList();

        try {

            con = dbconnection.getSampleProperty();
            st = con.createStatement();

            unassigned_proj_function_query = "SELECT proj.proj_id,proj.proj_name,DATE_FORMAT(proj.proj_date,'%d-%b-%Y') "
                                        +"FROM projects proj "
                                        +"WHERE proj.project_status <> '2' AND proj.proj_date > '2013-01-25 00:00:00' AND "
                                        +"proj.proj_id NOT IN (SELECT f.proj_id FROM project_estimator f) ORDER BY proj.proj_date";

            rs = st.executeQuery(unassigned_proj_function_query);

            while (rs.next()) {

                estimatorProjAssignVO = new ProjAssignmentVO();

                if (rs.getString(1) != null) {
                    estimatorProjAssignVO.setUnAssignedEstimatorProjId(rs.getString(1));
                } else {
                    estimatorProjAssignVO.setUnAssignedEstimatorProjId("");
                }

                if (rs.getString(2) != null) {
                    estimatorProjAssignVO.setUnAssignedEstimatorProjName(rs.getString(2));
                } else {
                    estimatorProjAssignVO.setUnAssignedEstimatorProjName("");
                }

                if (rs.getString(3) != null) {
                    estimatorProjAssignVO.setUnAssignedEstimatorProjRecDate(rs.getString(3));
                } else {
                    estimatorProjAssignVO.setUnAssignedEstimatorProjRecDate("");
                }

                projUnassignedEstimatorList.add(estimatorProjAssignVO);
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } catch (NullPointerException npe) {
                npe.printStackTrace();
            }
        }

        return projUnassignedEstimatorList;
    }
    public List getProjEstimatorDetails() {

        ProjAssignmentVO projAssignVO = null;
        List projEstimatorsList = new ArrayList();

        try {

            con = dbconnection.getSampleProperty();
            st = con.createStatement();

            proj_planner_query = " select u.emp_id,u.emp_name "
                    + " from user u,department dept,designation des "
                    + " where u.status='1' and u.desig_code in ('EBE') and u.desig_code=des.desig_code and "
                    + " u.dept_code=dept.dept_code order by u.emp_name ";

            rs = st.executeQuery(proj_planner_query);

            while (rs.next()) {

                projAssignVO = new ProjAssignmentVO();

                if (rs.getString(1) != null) {
                    projAssignVO.setEstimatorId(rs.getString(1));
                } else {
                    projAssignVO.setEstimatorId("");
                }

                if (rs.getString(2) != null) {
                    projAssignVO.setEstimatorName(rs.getString(2));
                } else {
                    projAssignVO.setEstimatorName("");
                }
                projEstimatorsList.add(projAssignVO);
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } catch (NullPointerException npe) {
                npe.printStackTrace();
            }
        }

        return projEstimatorsList;
    }
            
    public List getUnAssignedFunctionProjDetails() {

        ProjAssignmentVO functionProjAssignVO = null;
        List projUnassignedFunctionList = new ArrayList();

        try {

            con = dbconnection.getSampleProperty();
            st = con.createStatement();

            unassigned_proj_function_query = "SELECT proj.proj_id,proj.proj_name,DATE_FORMAT(proj.proj_date,'%d-%b-%Y') "
                                        +"FROM projects proj "
                                        +"WHERE proj.project_status <> '2' AND proj.proj_date > '2013-01-25 00:00:00' AND "
                                        +"proj.proj_id NOT IN (SELECT f.proj_id FROM project_function f) ORDER BY proj.proj_date";

            rs = st.executeQuery(unassigned_proj_function_query);

            while (rs.next()) {

                functionProjAssignVO = new ProjAssignmentVO();

                if (rs.getString(1) != null) {
                    functionProjAssignVO.setUnAssignedFunctionProjId(rs.getString(1));
                } else {
                    functionProjAssignVO.setUnAssignedFunctionProjId("");
                }

                if (rs.getString(2) != null) {
                    functionProjAssignVO.setUnAssignedFunctionProjName(rs.getString(2));
                } else {
                    functionProjAssignVO.setUnAssignedFunctionProjName("");
                }

                if (rs.getString(3) != null) {
                    functionProjAssignVO.setUnAssignedFunctionProjRecDate(rs.getString(3));
                } else {
                    functionProjAssignVO.setUnAssignedFunctionProjRecDate("");
                }

                projUnassignedFunctionList.add(functionProjAssignVO);
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } catch (NullPointerException npe) {
                npe.printStackTrace();
            }
        }

        return projUnassignedFunctionList;
    }

    public List getFacilityIDList() {
        ProjAssignmentVO facilityListProjAssignVO = null;
        List facilityList = new ArrayList();
//        List facilityList = new ArrayList();
//        List facilityNameList = new ArrayList();

        try {

            con = dbconnection.getSampleProperty();
            st = con.createStatement();

            unassigned_proj_function_query = "SELECT facility_id, facility_name FROM facility";

            rs = st.executeQuery(unassigned_proj_function_query);

            while (rs.next()) {
                facilityListProjAssignVO = new ProjAssignmentVO();
                if (rs.getString(1) != null) {
                    facilityListProjAssignVO.setFunctionFcyId(rs.getString(1));
                } else {
                    facilityListProjAssignVO.setFunctionFcyId("");
                }

                if (rs.getString(2) != null) {
                    facilityListProjAssignVO.setFunctionFcyName(rs.getString(2));
                } else {
                    facilityListProjAssignVO.setFunctionFcyName("");
                }
                facilityList.add(facilityListProjAssignVO);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } catch (NullPointerException npe) {
                npe.printStackTrace();
            }
        }
        return facilityList;
    }

    public List getFacilityFunctionDetails(ProjAssignmentVO facilityFunctionsVO) {

        List facilityFunctionList = new ArrayList();
        String fcyId = facilityFunctionsVO.getFunctionFcyId();
        try {

            Connection con = dbconnection.getSampleProperty();
            st = con.createStatement();
/*
            unassigned_proj_function_query = "SELECT d.dept_code,d.dept_function "
                    +"FROM department d, facility f "
                    +"WHERE d.facility_id=f.facility_id AND f.facility_id = '"+fcyId+"'";
*/
            unassigned_proj_function_query = "SELECT d.dept_code,d.dept_function FROM department d, facility f "
                                                +"WHERE d.facility_id=f.facility_id AND f.facility_id = '"+fcyId+"' "
                                                +"GROUP BY dept_function";
            ResultSet localrs = st.executeQuery(unassigned_proj_function_query);
            //System.out.println("QUERY :"+unassigned_proj_function_query);

            while (localrs.next()) {

                facilityFunctionsVO = new ProjAssignmentVO();

                if (localrs.getString(1) != null) {
                    facilityFunctionsVO.setFunctionDeptId(localrs.getString(1));
                } else {
                    facilityFunctionsVO.setFunctionDeptId("");
                }

                if (localrs.getString(2) != null) {
                    facilityFunctionsVO.setFunctionDeptName(localrs.getString(2));
                } else {
                    facilityFunctionsVO.setFunctionDeptName("");
                }

//                System.out.println("FacilityFunDAO :" + facilityFunctionsVO.getFunctionFcyId()+"_"+facilityFunctionsVO.getFunctionFcyName()+"_"+facilityFunctionsVO.getFunctionDeptId()+"_"+facilityFunctionsVO.getFunctionDeptName());
                facilityFunctionList.add(facilityFunctionsVO);
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } catch (NullPointerException npe) {
                npe.printStackTrace();
            }
        }

        return facilityFunctionList;
    }

    public List getFacilityFunctions(ProjAssignmentVO facilityFunctionsVO) {

        List facilityFunctionList = new ArrayList();
        String fcyId = facilityFunctionsVO.getFcyId();
        try {

            Connection con = dbconnection.getSampleProperty();
            st = con.createStatement();

            facility_function_list = "SELECT dept_code "
                    +"FROM department "
                    +"WHERE facility_id='"+fcyId+"' GROUP BY dept_function";

            //System.out.println("Facility Functions :"+facility_function_list);
            ResultSet localrs = st.executeQuery(facility_function_list);

            while (localrs.next()) {
                if (localrs.getString(1) != null) {
                    facilityFunctionList.add(localrs.getString(1));
                } else {
                    facilityFunctionList.add("");
                }
                //System.out.println("Value Of "+localrs.getString(1));
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } catch (NullPointerException npe) {
                npe.printStackTrace();
            }
        }

        return facilityFunctionList;
    }

    public List getProjPlannerDetails() {

        ProjAssignmentVO projAssignVO = null;
        List projPlannersList = new ArrayList();

        try {

            con = dbconnection.getSampleProperty();
            st = con.createStatement();

            proj_planner_query = " select u.emp_id,u.emp_name,u.desig_code,u.dept_code,dept.department,des.designation "
                    + " from user u,department dept,designation des "
                    + " where u.status='1' and u.desig_code in ('ACNMGR','PM','PJLD','SNMGR','SNPM','TRG-PRMG','LPM') and u.desig_code=des.desig_code and "
                    + " u.dept_code=dept.dept_code order by u.emp_name ";

            rs = st.executeQuery(proj_planner_query);

            while (rs.next()) {

                projAssignVO = new ProjAssignmentVO();

                if (rs.getString(1) != null) {
                    projAssignVO.setProjPlannerID(rs.getString(1));
                } else {
                    projAssignVO.setProjPlannerID("");
                }

                if (rs.getString(2) != null) {
                    projAssignVO.setProjPlannerName(rs.getString(2));
                } else {
                    projAssignVO.setProjPlannerName("");
                }

                if (rs.getString(3) != null) {
                    projAssignVO.setProjPlannerDesigCode(rs.getString(3));
                } else {
                    projAssignVO.setProjPlannerDesigCode("");
                }

                if (rs.getString(4) != null) {
                    projAssignVO.setProjPlannerDeptCode(rs.getString(4));
                } else {
                    projAssignVO.setProjPlannerDeptCode("");
                }

                if (rs.getString(5) != null) {
                    projAssignVO.setProjPlannerDeptName(rs.getString(5));
                } else {
                    projAssignVO.setProjPlannerDeptName("");
                }

                if (rs.getString(6) != null) {
                    projAssignVO.setProjPlannerDesigName(rs.getString(6));
                } else {
                    projAssignVO.setProjPlannerDesigName("");
                }

                projPlannersList.add(projAssignVO);
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        } finally {
            try {
                rs.close();
                st.close();
                con.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            } catch (NullPointerException npe) {
                npe.printStackTrace();
            }
        }

        return projPlannersList;
    }

    public int saveProjAssignment(List rqstList) {

        Connection con = null;
        PreparedStatement statement = null;
        int addProjPlan = 0;
        ProjAssignmentVO proj_assign_VO = null;

        try {
            DBconnection dbcon = new DBconnection();
            con = dbcon.getSampleProperty();
            String saveQry = " update projects set planner = ? where proj_id = ? ";

            statement = con.prepareStatement(saveQry);

            String projId = "";
            String empId = "";

            Iterator itr = rqstList.iterator();
            while (itr.hasNext()) {
                proj_assign_VO = (ProjAssignmentVO) itr.next();
                //planId = rqstVO.getPlanId();
                projId = proj_assign_VO.getUnAssignedProjId();
                empId = proj_assign_VO.getProjPlannerID();

                //System.out.println("projId:" + projId);
                //System.out.println("empId:" + empId);

                statement.setString(1, empId);
                statement.setString(2, projId);

                statement.executeUpdate();
                addProjPlan++;
                //System.out.println("saveQry:" + saveQry);
                //System.out.println("addProjPlan:" + addProjPlan);
            }
        } catch (SQLException sqle) {
            System.out.println("SQLException in creating Plan Creation:" + sqle);
        } catch (Exception e) {
            System.out.println("Exception in creating Plan Creation:" + e);
        } finally {
            //Close statement
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException se) {
                    System.out.println("Exception while closing Statement:se.getMessage()");
                }
            }

            //Close connection
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException se) {
                    System.out.println("Exception while closing connection:se.getMessage()");
                }
            }
        }

        return addProjPlan;
    }

    public int saveProjEstimatorAssignment(List rqstList) {

        Connection con = null;
        PreparedStatement statement = null;
        int addProjPlan = 0;
        ProjAssignmentVO proj_assign_VO = null;

        //System.out.println("IN DAO");
        
        try {
            DBconnection dbcon = new DBconnection();
            con = dbcon.getSampleProperty();
            String saveQry = " INSERT INTO project_estimator (proj_id, estimator_id, assigned_by, assigned_date) VALUES (?, ?, ?, CURRENT_TIMESTAMP()) ";

            statement = con.prepareStatement(saveQry);

            String projId = "";
            String estrId = "";
            String assignedBy = "";
            Iterator itr = rqstList.iterator();
            while (itr.hasNext()) {
                proj_assign_VO = (ProjAssignmentVO) itr.next();
                //planId = rqstVO.getPlanId();
                projId = proj_assign_VO.getUnAssignedEstimatorProjId();
                estrId = proj_assign_VO.getEstimatorId();
                assignedBy = proj_assign_VO.getAssignedUser();

                //System.out.println("projId:" + projId);
                //System.out.println("empId:" + empId);

                statement.setString(1, projId);
                statement.setString(2, estrId);
                statement.setString(3, assignedBy);

                statement.executeUpdate();
                addProjPlan++;
                //System.out.println("saveQry:" + saveQry);
                //System.out.println("addProjPlan:" + addProjPlan);
            }
        } catch (SQLException sqle) {
            System.out.println("SQLException in creating Plan Creation:" + sqle);
        } catch (Exception e) {
            System.out.println("Exception in creating Plan Creation:" + e);
        } finally {
            //Close statement
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException se) {
                    System.out.println("Exception while closing Statement:se.getMessage()");
                }
            }

            //Close connection
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException se) {
                    System.out.println("Exception while closing connection:se.getMessage()");
                }
            }
        }

        return addProjPlan;
    }
    
    public int saveProjCellAssignment(List rqstList) {

        Connection con = null;
        PreparedStatement statement = null;
        PreparedStatement statement1 = null;
        PreparedStatement statement2 = null;
        int addProjPlan = 0;
        ProjAssignmentVO proj_assign_VO = null;

        //System.out.println("Inside - saveProjCellAssignment");

        try {
            DBconnection dbcon = new DBconnection();
            con = dbcon.getSampleProperty();
            String saveCellQry = " insert into project_team(proj_id,proj_alloc_type,cell_code,primary_incharge, "
                    + " facility_id,date_assigned) values (?,?,?,?,?,CURRENT_TIMESTAMP()) ";

            String saveEmpQry = " insert into project_team(proj_id,proj_alloc_type,emp_id,facility_id,date_assigned) "
                    + " values (?,?,?,?,CURRENT_TIMESTAMP()) ";

            String updateCellVal = " update projects set dept_code = ?, cell_code = ? WHERE proj_id = ? ";

            statement = con.prepareStatement(saveCellQry);
            statement1 = con.prepareStatement(saveEmpQry);
            statement2 = con.prepareStatement(updateCellVal);

            String projId = "";
            String cellId = "";
            String allocEmpId = "";
            String allocEmpFacilityId = "";
            List cellEmpId = new ArrayList();
            List cellEmpFacilityId = new ArrayList();
            String deptCode = "";
            int flagSet = 1;
            int flagReSet = 2;
            int empIdParams = 0;
            int empFacilityIdParams = 0;

            cellEmpId.clear();
            cellEmpFacilityId.clear();

            Iterator itr = rqstList.iterator();
            while (itr.hasNext()) {

                //System.out.println("Inside - saveProjCellAssignment - itr");

                proj_assign_VO = (ProjAssignmentVO) itr.next();
                //planId = rqstVO.getPlanId();
                projId = proj_assign_VO.getUnAssignedCellProjId();
                cellId = proj_assign_VO.getProjCellID();
                deptCode = proj_assign_VO.getProjDeptCode();
                cellEmpId = proj_assign_VO.getProjCellEmpID();
                cellEmpFacilityId = proj_assign_VO.getProjEmpFacilityID();

//                System.out.println("projId:" + projId);
//                System.out.println("deptCode:" + deptCode);
//                System.out.println("cellId:" + cellId);
//                System.out.println("cellEmpId:" + cellEmpId);
//                System.out.println("cellEmpFacilityId:" + cellEmpFacilityId);

                if (!projId.equals("")) {

                    if (!cellId.equals("") && !cellEmpId.isEmpty()) {

                        //System.out.println("Inside Execution 1");

                        statement.setString(1, projId);
                        statement.setInt(2, flagSet);
                        statement.setString(3, cellId);
                        statement.setInt(4, flagSet);
                        statement.setInt(5, flagSet);

                        statement2.setString(1, deptCode);
                        statement2.setString(2, cellId);
                        statement2.setString(3, projId);

                        statement.executeUpdate();
                        statement2.executeUpdate();

                        for (int cell_emp_Id = 0; cell_emp_Id < cellEmpId.size(); cell_emp_Id++) {

                            allocEmpId = cellEmpId.get(cell_emp_Id).toString();
                            allocEmpFacilityId = cellEmpFacilityId.get(cell_emp_Id).toString();

//                            System.out.println("Inside allocEmpId : " + allocEmpId);
//                            System.out.println("Inside allocEmpFacilityId : " + allocEmpFacilityId);

                            empIdParams = Integer.parseInt(allocEmpId);
                            empFacilityIdParams = Integer.parseInt(allocEmpFacilityId);

//                            System.out.println("Inside empIdParams : " + empIdParams);
//                            System.out.println("Inside empFacilityIdParams : " + empFacilityIdParams);

                            statement1.setString(1, projId);
                            statement1.setInt(2, flagReSet);
                            statement1.setInt(3, empIdParams);
                            statement1.setInt(4, empFacilityIdParams);

                            statement1.executeUpdate();
                        }
                    } else if (!cellId.equals("")) {

                        //System.out.println("Inside Execution 2");

                        statement.setString(1, projId);
                        statement.setInt(2, flagSet);
                        statement.setString(3, cellId);
                        statement.setInt(4, flagSet);
                        statement.setInt(5, flagSet);

                        statement2.setString(1, deptCode);
                        statement2.setString(2, cellId);
                        statement2.setString(3, projId);

                        statement.executeUpdate();
                        statement2.executeUpdate();
                    } else if (!cellEmpId.isEmpty()) {

                        //System.out.println("Inside Execution 3");

                        for (int cell_emp_Id = 0; cell_emp_Id < cellEmpId.size(); cell_emp_Id++) {

                            allocEmpId = cellEmpId.get(cell_emp_Id).toString();
                            allocEmpFacilityId = cellEmpFacilityId.get(cell_emp_Id).toString();

//                            System.out.println("Inside allocEmpId : " + allocEmpId);
//                            System.out.println("Inside allocEmpFacilityId : " + allocEmpFacilityId);

                            empIdParams = Integer.parseInt(allocEmpId);
                            empFacilityIdParams = Integer.parseInt(allocEmpFacilityId);

//                            System.out.println("Inside empIdParams : " + empIdParams);
//                            System.out.println("Inside empFacilityIdParams : " + empFacilityIdParams);

                            statement1.setString(1, projId);
                            statement1.setInt(2, flagReSet);
                            statement1.setInt(3, empIdParams);
                            statement1.setInt(4, empFacilityIdParams);

                            statement1.executeUpdate();
                        }
                    }
                }

                addProjPlan++;

                //System.out.println("saveQry:" + saveCellQry);
                //System.out.println("saveEmpQry:" + saveEmpQry);
                //System.out.println("updateCellVal:" + updateCellVal);
                //System.out.println("addProjPlan:" + addProjPlan);
            }
        } catch (SQLException sqle) {
            System.out.println("SQLException in creating Plan Creation:" + sqle);
        } catch (Exception e) {
            System.out.println("Exception in creating Plan Creation:" + e);
        } finally {
            //Close statement
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException se) {
                    System.out.println("Exception while closing Statement:se.getMessage()");
                }
            }

            //Close connection
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException se) {
                    System.out.println("Exception while closing connection:se.getMessage()");
                }
            }
        }

        return addProjPlan;
    }

    public int saveProjFunctionAssignment(List rqstList) {

        Connection con = null;
        PreparedStatement statement = null;
        ProjAssignmentVO proj_assign_VO = null;
        int addProjFun = 0;

        try {
            DBconnection dbcon = new DBconnection();
            con = dbcon.getSampleProperty();
//            Iterator itr = rqstList.iterator();

            String projId = "";
            String deptId = "";
            String assignedUser = "";
            Iterator funitr = rqstList.iterator();
            while (funitr.hasNext()) {
                String saveQry = "INSERT INTO project_function (proj_id, facility_id, dept_function, assigned_by, assigned_date) VALUES (?, (SELECT facility_id from department WHERE dept_code=?), (SELECT dept_function from department WHERE dept_code=?), ?, CURRENT_TIMESTAMP())";
                statement = con.prepareStatement(saveQry);

                proj_assign_VO = (ProjAssignmentVO) funitr.next();
                projId = proj_assign_VO.getUnAssignedFunctionProjId();
                deptId = proj_assign_VO.getFunctionDeptId();
                assignedUser = proj_assign_VO.getAssignedUser();

//                System.out.println("projId:" + projId);
//                System.out.println("deptId for Fcy:" + deptId);
//                System.out.println("deptId:" + deptId);

                statement.setString(1, projId);
                statement.setString(2, deptId);
                statement.setString(3, deptId);
                statement.setString(4, assignedUser);

                statement.executeUpdate();
                addProjFun++;
                //System.out.println("Function Save Query:" + saveQry);
            }
        } catch (SQLException sqle) {
            System.out.println("SQLException in Function Assignment :" + sqle);
        } catch (Exception e) {
            System.out.println("Exception in Function Assignment :" + e);
        } finally {
            //Close statement
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException se) {
                    System.out.println("Exception while closing Statement:se.getMessage()");
                }
            }

            //Close connection
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException se) {
                    System.out.println("Exception while closing connection:se.getMessage()");
                }
            }
        }

        return addProjFun;
    }
    public ProjAssignmentVO getUnAssignedProjects(ProjAssignmentVO projAssignmentVO) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet resultSet = null;

        List unAsProjId = new ArrayList();
        List unAsProjName = new ArrayList();
        List unAsProjClientId = new ArrayList();
        List unAsProjClientName = new ArrayList();
        List unAsProjDate = new ArrayList();
        List unAsProjPlanner = new ArrayList();

        pathfinder.util.SpecialCharHandler splChar = new pathfinder.util.SpecialCharHandler();
        try {

            conn = dbconnection.getSampleProperty();
            stmt = conn.createStatement();

            String query = " SELECT p.proj_id, p.proj_name, c.contact_id, c.company, DATE_FORMAT(p.proj_date,'%d-%b-%Y'), u.emp_name "
                    + "    FROM projects p "
                    + "    LEFT OUTER JOIN contacts c ON p.client_name = c.contact_id "
                    + "    LEFT OUTER JOIN USER u ON p.planner=u.emp_id "
                    + "    WHERE p.dept_code IS NULL AND p.project_status NOT IN ('2','21') "
                    + "    ORDER BY c.company IS NULL, c.company, p.proj_date ";

            resultSet = stmt.executeQuery(query);

            while (resultSet.next()) {
                if (resultSet.getString(1) != null) {
                    unAsProjId.add(resultSet.getString(1));
                } else {
                    unAsProjId.add("");
                }
                if (resultSet.getString(2) != null) {
                    unAsProjName.add(resultSet.getString(2));
                } else {
                    unAsProjName.add("");
                }
                if (resultSet.getString(3) != null) {
                    unAsProjClientId.add(resultSet.getString(3));
                } else {
                    unAsProjClientId.add("");
                }
                if (resultSet.getString(4) != null) {
                    unAsProjClientName.add(splChar.decoding(resultSet.getString(4)));
                } else {
                    unAsProjClientName.add("");
                }
                if (resultSet.getString(5) != null) {
                    unAsProjDate.add(resultSet.getString(5));
                } else {
                    unAsProjDate.add("");
                }
                if (resultSet.getString(6) != null) {
                    unAsProjPlanner.add(resultSet.getString(6));
                } else {
                    unAsProjPlanner.add("");
                }
            }
            projAssignmentVO.setUnAsProjId(unAsProjId);
            projAssignmentVO.setUnAsProjName(unAsProjName);
            projAssignmentVO.setUnAsProjClientId(unAsProjClientId);
            projAssignmentVO.setUnAsProjClientName(unAsProjClientName);
            projAssignmentVO.setUnAsProjDate(unAsProjDate);
            projAssignmentVO.setUnAsProjPlanner(unAsProjPlanner);
        } catch (SQLException sqle) {
            System.out.println("SQLException in ProjAssignmentDAO : "+sqle);
        } catch (NullPointerException npe) {
            System.out.println("NullPointerException in ProjAssignmentDAO : "+npe);
        } finally {
            try {
                resultSet.close();
                stmt.close();
                conn.close();
            } catch (SQLException sqle) {
                System.out.println("SQLException in ProjAssignmentDAO : "+sqle);
            } catch (NullPointerException npe) {
                System.out.println("NullPointerException in ProjAssignmentDAO : "+npe);
            }
        }
        return projAssignmentVO;
    }
}
