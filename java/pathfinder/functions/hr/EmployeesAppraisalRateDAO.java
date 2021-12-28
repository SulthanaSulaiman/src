/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder.functions.hr;

import java.sql.*;
import java.util.*;
import connection.DBconnection;

/**
 *
 * @author Parameshwarant
 */
public class EmployeesAppraisalRateDAO {

    List supervisorRateList = new ArrayList();
    List supervisorNameList = new ArrayList();
    List approvalAuthorityRateList = new ArrayList();
    List approvalAuthorityNameList = new ArrayList();
    List cycleIdList = new ArrayList();
    List cycleNameList = new ArrayList();
    List cycleCodeList = new ArrayList();
    String queryToGetSupervisor = "";
    String queryToGetApprovalAuthority = "";
    String queryToGetAppraisalRate = "";
    String supervisorName = "";
    String supervisorID = "";
    String approvalAuthorityName = "";
    String approvalAuthorityID = "";
    String supervisorAppRate = "";
    String approvalAuthorityAppRate = "";
    String supervisorAppcycleName = "";
    String approvalAuthorityAppcycleName = "";
   
   

    public void getEmployeesAppraisalRate(String empId, EmployeesAppraisalRateVO setAppraisalRate) {


        String supervisorEncryptKey = "";
        String approvalAuthorityEncryptKey = "";
        String selfAppraisalCheck = "";

        try {

            DBconnection conToDB = new DBconnection();
            Connection con = conToDB.getSampleProperty();
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT EXISTS(SELECT submit_flag FROM self_appraisal_remark WHERE emp_id='" + empId + "') AS selfAppraisal");
            while (rs.next()) {
                selfAppraisalCheck = rs.getString(1);
            }
            if (selfAppraisalCheck.equals("1")) {

                queryToGetSupervisor = "SELECT b.emp_name,b.emp_id,b.emp_encrkey FROM supervisor_appraisal_remark a, USER b WHERE a.supervisor_emp_id=b.emp_id AND a.member_emp_id='" + empId + "' GROUP BY b.emp_id";
                ResultSet rsToGetSupervisor = st.executeQuery(queryToGetSupervisor);
                if (!rsToGetSupervisor.wasNull()) {
                    while (rsToGetSupervisor.next()) {
                        supervisorName = rsToGetSupervisor.getString(1);
                        supervisorID = rsToGetSupervisor.getString(2);
                        supervisorEncryptKey = rsToGetSupervisor.getString(3);
                    }
                }

                queryToGetApprovalAuthority = "SELECT b.emp_name,b.emp_id,b.emp_encrkey FROM approval_appraisal_remark a, USER b WHERE a.approval_emp_id=b.emp_id AND a.member_emp_id='" + empId + "' GROUP BY b.emp_id";
                ResultSet rsToGetApprovalAuthority = st.executeQuery(queryToGetApprovalAuthority);
                if (!rsToGetApprovalAuthority.wasNull()) {
                    while (rsToGetApprovalAuthority.next()) {
                        supervisorName = rsToGetApprovalAuthority.getString(1);
                        supervisorID = rsToGetApprovalAuthority.getString(2);
                        approvalAuthorityEncryptKey = rsToGetApprovalAuthority.getString(3);

                    }

                }
                queryToGetAppraisalRate = "SELECT a.cycle_name,a.cycle_id,a.cycle_code ";
                if(!supervisorEncryptKey.equals("")) {
                    queryToGetAppraisalRate += ", AES_DECRYPT(b.remark,'" + supervisorEncryptKey + "') AS SupervisorRate ";
                    if(!approvalAuthorityEncryptKey.equals("")) {
                        queryToGetAppraisalRate += ", AES_DECRYPT(d.approval_app_remark,'" + approvalAuthorityEncryptKey + "') AS ApprovalAuthorityRate "
                    
                        + " FROM appraisal_cycle a, supervisor_appraisal_remark b, self_appraisal_remark c,approval_appraisal_remark d "
                        + " WHERE a.cycle_id=b.cycle_code AND b.member_emp_id='" + empId + "' AND b.question_id=14 AND c.emp_id='" + empId + "' "
                        + " AND a.cycle_id=d.cycle_code AND b.cycle_code=d.cycle_code AND d.member_emp_id='" + empId + "' AND d.approval_app_ques_id=15 ";
                    } else {
                        queryToGetAppraisalRate+= ",'Not appraised yet' FROM appraisal_cycle a, supervisor_appraisal_remark b, self_appraisal_remark c,approval_appraisal_remark d "
                        + " WHERE a.cycle_id=b.cycle_code AND b.member_emp_id='" + empId + "' AND b.question_id=14 AND c.emp_id='" + empId + "' ";
                     }
                } else {
                    queryToGetAppraisalRate +=" ,'Not appraised yet','Not appraised yet' FROM appraisal_cycle a ";
                }
              //  System.out.println("queryToGetAppraisalRate= "+queryToGetAppraisalRate+" GROUP BY a.cycle_id");
                ResultSet rsToGetAppraisalRate = st.executeQuery(queryToGetAppraisalRate+" GROUP BY a.cycle_id");
                
                
                if (!rsToGetAppraisalRate.wasNull()) {
                    while (rsToGetAppraisalRate.next()) {
                        cycleNameList.add(rsToGetAppraisalRate.getString(1));
                        cycleIdList.add(rsToGetAppraisalRate.getString(2));
                        cycleCodeList.add(rsToGetAppraisalRate.getString(3));
                        supervisorRateList.add(rsToGetAppraisalRate.getString(4));
                        approvalAuthorityRateList.add(rsToGetAppraisalRate.getString(5));
                    }
                }
                setAppraisalRate.setCycleID(cycleIdList);
                setAppraisalRate.setCycleName(cycleNameList);
                setAppraisalRate.setSupervisorRate(supervisorRateList);
                setAppraisalRate.setApprovalAuthorityRate(approvalAuthorityRateList);
                setAppraisalRate.setCycleCode(cycleCodeList);

                rsToGetAppraisalRate.close();
                rsToGetApprovalAuthority.close();
                rsToGetSupervisor.close();
            }
            rs.close();
            st.close();
            con.close();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    public EmployeesAppraisalRateVO getRatesOfEmployee(EmployeesAppraisalRateVO employeesAppraisalRateVO) {
        String empId = "";
        String cycleId = "";
        String approverName = "";
        String approverRate = "";
        String supervisorName = "";
        String supervisorRate = "";
        String cycleDescription = "";
        String dept_code="";
        try {
            dept_code=employeesAppraisalRateVO.getDept_code();
            empId = employeesAppraisalRateVO.getEmpId();
            cycleId = employeesAppraisalRateVO.getCycleId();
            DBconnection conToDB = new DBconnection();
            Connection con = conToDB.getSampleProperty();
            Statement st = con.createStatement();

            String query = "SELECT a.emp_name, AES_DECRYPT(ap.approval_app_remark, a.emp_encrkey) "
                    + "FROM approval_appraisal_remark ap, USER e, USER a, appraisal_question aq, appraisal_cycle ac "
                    + "WHERE ap.member_emp_id='" + empId + "' AND ap.cycle_code='" + cycleId + "' "
                    + "AND ap.approval_emp_id=a.emp_id AND ap.member_emp_id=e.emp_id AND approval_app_ques_id='1' AND ap.approval_app_ques_id=aq.question_Number AND ac.cycle_id=ap.cycle_code AND aq.rate_flag='1' AND aq.desig_code='"+dept_code+"'";
//System.out.println(query);
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                if(rs.getString(1) != null) {
                    approverName = rs.getString(1).toString();
                } else {
                    approverName = "";
                }
                if(rs.getString(2) != null) {
                    approverRate = rs.getString(2).toString();
                } else {
                    approverRate = "";
                }
            }

            query = "SELECT s.emp_name, AES_DECRYPT(sp.remark, s.emp_encrkey), ac.description "
                    + "FROM supervisor_appraisal_remark sp, USER e, USER s, appraisal_question aq, appraisal_cycle ac "
                    + "WHERE sp.member_emp_id='" + empId + "' AND sp.cycle_code='" + cycleId + "' "
                    + "AND sp.supervisor_emp_id=s.emp_id AND sp.member_emp_id=e.emp_id AND sp.question_id='9' AND sp.question_id=aq.question_Number AND ac.cycle_id=sp.cycle_code AND aq.rate_flag='1' AND aq.desig_code='"+dept_code+"'";

            rs = st.executeQuery(query);
            //System.out.println(query);
            while (rs.next()) {
                if(rs.getString(1) != null) {
                    supervisorName = rs.getString(1).toString();
                } else {
                    supervisorName = "";
                }
                if(rs.getString(2) != null) {
                    supervisorRate = rs.getString(2).toString();
                } else {
                    supervisorRate = "";
                }
                if(rs.getString(3) != null) {
                    cycleDescription = rs.getString(3).toString();
                } else {
                    cycleDescription = "";
                }
            }
            employeesAppraisalRateVO.setAppName(approverName);
            employeesAppraisalRateVO.setAppRate(approverRate);
            employeesAppraisalRateVO.setSupName(supervisorName);
            employeesAppraisalRateVO.setSupRate(supervisorRate);
            employeesAppraisalRateVO.setCycleDescription(cycleDescription);
        } catch (Exception e) {
            System.out.println("EmployeesAppraisalRateDAO - getRatesOfEmployee() : " + e);
        }
        return employeesAppraisalRateVO;
    }

    public EmployeesAppraisalRateVO getOverallAppraisalDetail(EmployeesAppraisalRateVO employeesAppraisalRateVO) {
        String query = "";
        String empId = "";
        String dept_codeS = "";
        String cycleId = "";
        
        List cycleIdList = new ArrayList();
        List cycleNameList = new ArrayList();
        List cycleYearList = new ArrayList();
        List empIdList = new ArrayList();
        List empNameList = new ArrayList();
        List dpt_Name = new  ArrayList();
        List dept_code = new  ArrayList();
        String deptcodefromfilter="";


        String dropdownCycleYear = employeesAppraisalRateVO.getCycleYear();
        cycleId =employeesAppraisalRateVO.getCycleId();
        System.out.println("cycleId"+dropdownCycleYear);
        HashMap yearlyAppraisal = new HashMap();
        HashMap appraisal = new HashMap();
        List supAppList = new ArrayList();
        List appAppList = new ArrayList();
        List selfApp = new ArrayList();
        Connection con = null;
        PreparedStatement pre = null;
        ResultSet res = null;

        try {
            DBconnection dbConnection = new DBconnection();
            con = dbConnection.getSampleProperty();

            EmployeesAppraisalRateDAO employeesAppraisalRateDAO = new EmployeesAppraisalRateDAO();
//System.out.println("dropdownCycleYear "+dropdownCycleYear);
            employeesAppraisalRateVO.setCycleYear(dropdownCycleYear);
            employeesAppraisalRateVO.setCycleId(cycleId);
            employeesAppraisalRateVO = employeesAppraisalRateDAO.getAppraisalCycles(employeesAppraisalRateVO);
            cycleIdList = employeesAppraisalRateVO.getCycleID();
            cycleNameList = employeesAppraisalRateVO.getCycleName();
            cycleYearList = employeesAppraisalRateVO.getCycleYearList();
            deptcodefromfilter = employeesAppraisalRateVO.getDept_codeForfilter();
            employeesAppraisalRateVO.setDept_codeForfilter(deptcodefromfilter);
            employeesAppraisalRateVO = employeesAppraisalRateDAO.collectEmployees(employeesAppraisalRateVO);
            empIdList = employeesAppraisalRateVO.getEmpIdList();
            empNameList = employeesAppraisalRateVO.getEmpNameList();
            dpt_Name = employeesAppraisalRateVO.getDeptNameList();
            dept_code = employeesAppraisalRateVO.getDeptcodeList();
            
            //for(int idx = 0; idx < cycleIdList.size(); idx++) {

                //cycleId = "9";
             String selfapprchk1 = "SELECT cycle_id FROM appraisal_cycle WHERE cycle_year'"+dropdownCycleYear+"'";
                   // pre = con.prepareStatement(selfapprchk1);

                   //  res = pre.executeQuery();
//while(res.next()){
//cycleId = res.getString(1);
//}
System.out.println("cycleIdss"+cycleId);
                     cycleId = "9";
           // cycleId = employeesAppraisalRateVO.getCycleId();
                
                supAppList = new ArrayList();
                appAppList = new ArrayList();
                appraisal = new HashMap();
                
                for(int sidx = 0; sidx < empIdList.size(); sidx++) {
                    
                    empId = empIdList.get(sidx).toString();
                    dept_codeS = dept_code.get(sidx).toString();

                    String selfapprchk = "SELECT submit_flag from self_appraisal_remark where emp_id='"+empId+"' AND cycle_code='"+cycleId+"'";
                    pre = con.prepareStatement(selfapprchk);
                   
                     res = pre.executeQuery();
                     int count1=0;
                     while(res.next()){
                        count1++;
                        if(res.getString(1) != null) {
                             //System.out.println("rate- getRatesOfEmployee() :empId "+empId +"superrate" +res.getString(1));
                            if(count1==1){
                                if (res.getString(1).equals("0")){
                                selfApp.add("Yet to Complete");
                                }
                                else if(res.getString(1).equals("1"))
                                {
                                selfApp.add("Completed");
                                }
                            }}
                             else {
                                selfApp.add("N/A");
                            }
                        }
                     if(count1==0){
                          //System.out.println("selfapprchk"+selfapprchk);
                            selfApp.add("Yet to Start");
                        }

                    query = "SELECT AES_DECRYPT(sp.remark, s.emp_encrkey) "
                     + " FROM supervisor_appraisal_remark sp, USER e, USER s, appraisal_question aq, appraisal_cycle ac "
                     + " WHERE sp.supervisor_emp_id=s.emp_id AND sp.member_emp_id=e.emp_id AND sp.question_id='9'AND sp.question_id=aq.question_Number AND aq.rate_flag='1' and sp.cycle_code=ac.cycle_id AND aq.desig_code='"+dept_codeS+"'"
                     + " and sp.submit_flag='1' and sp.cycle_code='9' and e.emp_id='"+empId+"' and ac.cycle_year = '" + dropdownCycleYear + "' AND qustionfromSelfApp=0";
                     System.out.println("query"+query);
                    pre = con.prepareStatement(query);
                    //pre.setString(1, cycleId);
                   // pre.setString(2, empId);
                    
                    res = pre.executeQuery();
                    int count=0;
//                    if (res.next() == false) {
//                        // System.out.println("rate:");
//        supAppList.add("N/A");
//      }
                  while(res.next()){
                        count++;
                        if(res.getString(1) != null) {
                            // System.out.println("rate- getRatesOfEmployee() :empId "+empId +"superrate" +res.getString(1));
                                supAppList.add(res.getString(1));
                            } else {
                                supAppList.add("N/A");
                            }
                    }
                    if(count == 0) {
                       supAppList.add("Yet to Complete");
                    }
                    
                    query = "SELECT AES_DECRYPT(ap.approval_app_remark, a.emp_encrkey) "
                    + " FROM approval_appraisal_remark ap, USER e, USER a, appraisal_question aq, appraisal_cycle ac "
                    + " WHERE ap.approval_emp_id=a.emp_id AND ap.member_emp_id=e.emp_id AND ap.approval_app_ques_id='1' AND ap.approval_app_ques_id=aq.question_Number AND aq.rate_flag='1' and ap.cycle_code=ac.cycle_id AND aq.desig_code='"+dept_codeS+"'"
                    + " AND ap.submit_flag='1' AND ap.cycle_code='9' and e.emp_id='"+empId+"' and ac.cycle_year = '" + dropdownCycleYear + "'";

                    pre = con.prepareStatement(query);
                    //pre.setString(1, cycleId);
                    //pre.setString(2, empId);
                    //System.out.println("rate:"+query);
                    
                    res = pre.executeQuery();
                    int val=0;

//    if (res.next() == false) {
//        appAppList.add("N/A");
//      }



                    while(res.next()){
                        val++;
                         // System.out.println("test1"+empId +"rate" +res.getString(1));
                        if(res.getString(1) != null) {
                                appAppList.add(res.getString(1));
                               // System.out.println("rate- getRatesOfEmployee() :empId "+empId +"rate" +res.getString(1));
                            } else {
                              //System.out.println("fornull"+empId +"rate");
                                appAppList.add("N/A");
                            }
                    }
                    
                    if(val == 0) {
                       appAppList.add("Yet to Complete");
                    }
                }
                appraisal.put("app", appAppList);
                appraisal.put("sup", supAppList);
                yearlyAppraisal.put(cycleId, appraisal);
            //}
//System.out.println("emp size "+empIdList.size()+"supAppList"+supAppList.size()+"appAppList"+appAppList.size());
            employeesAppraisalRateVO.setEmpIdList(empIdList);
            employeesAppraisalRateVO.setEmpNameList(empNameList);
            employeesAppraisalRateVO.setOverallAppraisal(yearlyAppraisal);
            employeesAppraisalRateVO.setDeptNameList(dpt_Name);
            employeesAppraisalRateVO.setDeptcodeList(dept_code);
            employeesAppraisalRateVO.setSupervisorRateL(supAppList);
            employeesAppraisalRateVO.setApproverRate(appAppList);
            employeesAppraisalRateVO.setSelfappstatuscheck(selfApp);

        } catch (Exception e) {
            System.out.println("EmployeesAppraisalRateDAO - getRatesOfEmployee() : " + e);
        }
        return employeesAppraisalRateVO;
    }

    public EmployeesAppraisalRateVO getAppraisalCycles(EmployeesAppraisalRateVO employeesAppraisalRateVO) {
        List cycleIdList = new ArrayList();
        List cycleNameList = new ArrayList();
        List cycleYearList = new ArrayList();
        String cycleYear = "";
        String sqlQuery = "";
        try {
            DBconnection conToDB = new DBconnection();
            Connection con = conToDB.getSampleProperty();
            Statement st = con.createStatement();

            cycleYear = employeesAppraisalRateVO.getCycleYear();
            sqlQuery = "SELECT cycle_id, cycle_name, cycle_year FROM appraisal_cycle";
            if(!cycleYear.equals("")) {
                sqlQuery += " WHERE cycle_year='"+cycleYear+"'";
            }
            ResultSet rs = st.executeQuery(sqlQuery);

            while (rs.next()) {
                if(rs.getString(2) != null) {
                    cycleIdList.add(rs.getString(1).toString());
                } else {
                    cycleIdList.add("");
                }
                if(rs.getString(2) != null) {
                    cycleNameList.add(rs.getString(2).toString());
                } else {
                    cycleNameList.add("");
                }
                if(rs.getString(3) != null) {
                    cycleYearList.add(rs.getString(3).toString());
                } else {
                    cycleYearList.add("");
                }
            }
            employeesAppraisalRateVO.setCycleID(cycleIdList);
            employeesAppraisalRateVO.setCycleName(cycleNameList);
            employeesAppraisalRateVO.setCycleYearList(cycleYearList);

        } catch (Exception e) {
            System.out.println("Exception : " + e);
        }
        return employeesAppraisalRateVO;
    }

    public EmployeesAppraisalRateVO collectAppraisalYears(EmployeesAppraisalRateVO employeesAppraisalRateVO) {

        Connection con = null;
        //PreparedStatement stmt = null;
        Statement stmt = null;
        ResultSet res = null;
        List appraisalYearList = new ArrayList();
        List appraisalCycleIdList = new ArrayList();
        List appraisalCycleCountList = new ArrayList();
        
        //String sql_select = "select cycle_year from appraisal_cycle group by cycle_year";
        String sql_select = " SELECT cycle_id, cycle_year, COUNT(*) as cycles FROM appraisal_cycle GROUP BY cycle_year ";
        
        try {
            DBconnection connection = new DBconnection();
            con = connection.getSampleProperty();
            //stmt = con.prepareStatement(sql_select);
            stmt = con.createStatement();
            res = stmt.executeQuery(sql_select);
            //System.out.println("The execute querey "+res);
            while (res.next()) {
                if(res.getString(1) != null) {
                    appraisalCycleIdList.add(res.getString(1));
                } else {
                    appraisalCycleIdList.add("");
                }
                
                if(res.getString(2) != null) {
                    appraisalYearList.add(res.getString(2));
                } else {
                    appraisalYearList.add("");
                }
                if(res.getString(3) != null) {
                    appraisalCycleCountList.add(res.getString(3));
                } else {
                    appraisalCycleCountList.add("");
                }
            }
            employeesAppraisalRateVO.setAppraisalCycleIdList(appraisalCycleIdList);
            employeesAppraisalRateVO.setAppraisalYearList(appraisalYearList);
            employeesAppraisalRateVO.setAppraisalCycleCountList(appraisalCycleCountList);
        } catch (SQLException sqle) {
            System.out.println("SQLException in collectDeptartment:" + sqle);
        } catch (Exception e) {
            System.out.println("Exception in collectDeptartment:" + e);
        } finally {
            try {
                res.close();
                stmt.close();
                con.close();
            } catch (SQLException se) {
                System.out.println("Exception while closing resultset:"+ se.getMessage());
            }
        }
        return employeesAppraisalRateVO;
    }

    public EmployeesAppraisalRateVO collectEmployees(EmployeesAppraisalRateVO employeesAppraisalRateVO) {

        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List empIdList = new ArrayList();
        List empNameList = new ArrayList();
        List empDeptList = new ArrayList();
        List deptcodeList = new ArrayList();
        List deptNameList = new ArrayList();
        String deptcodeFromfilter = "";
        deptcodeFromfilter = employeesAppraisalRateVO.getDept_codeForfilter();
        
        String sql_select="";
        if(deptcodeFromfilter.equals("ALL")){
        sql_select = "SELECT u.emp_id, u.emp_name,u.dept_code,d.department FROM USER u, department d WHERE u.dept_code=d.dept_code and u.facility_id='1' and status='1'";
        System.out.println(deptcodeFromfilter);
        }
 else{
System.out.println("dept"+deptcodeFromfilter);
 sql_select = "SELECT u.emp_id, u.emp_name,u.dept_code,d.department FROM USER u, department d WHERE "
         + "u.dept_code='"+deptcodeFromfilter+"' AND u.dept_code=d.dept_code and u.facility_id='1' and status='1'";
 }
        try {
            DBconnection connection = new DBconnection();
            con = connection.getSampleProperty();
            stmt = con.prepareStatement(sql_select);
            rs = stmt.executeQuery(sql_select);
            while (rs.next()) {
                if(rs.getString(1) != null) {
                    empIdList.add(rs.getString(1));
                } else {
                    empIdList.add("N/A");
                }
                if(rs.getString(2) != null) {
                    empNameList.add(rs.getString(2));
                } else {
                    empNameList.add("N/A");
                }
                if(rs.getString(3) != null) {
                    deptcodeList.add(rs.getString(3));
                } else {
                    deptcodeList.add("N/A");
                }
                if(rs.getString(4) != null) {
                    deptNameList.add(rs.getString(4));
                } else {
                    deptNameList.add("N/A");
                }
            }
            employeesAppraisalRateVO.setEmpIdList(empIdList);
            employeesAppraisalRateVO.setEmpNameList(empNameList);
            employeesAppraisalRateVO.setDeptNameList(deptNameList);
            employeesAppraisalRateVO.setDeptcodeList(deptcodeList);
        } catch (SQLException sqle) {
            System.out.println("SQLException in collectDeptartment:" + sqle);
        } catch (Exception e) {
            System.out.println("Exception in collectDeptartment:" + e);
        } finally {
            try {
                rs.close();
                stmt.close();
                con.close();
            } catch (SQLException se) {
                System.out.println("Exception while closing resultset:"+ se.getMessage());
            }
        }
        return employeesAppraisalRateVO;
    }
}