/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder.functions.hr;

import pathfinder.functions.projects.*;
import connection.DBconnection;
import java.sql.*;
import java.util.*;
import java.io.*;

public class EmpNameSearchBean {

    DBconnection connection = new DBconnection();
    Connection con = null;
    java.sql.Statement stmt = null;
    ResultSet rs = null;
    private String sql_select = "";
    private String empName;
    private String empID;
    private String nextToken = "";
    public String setSearchKey = "";
    private StringTokenizer stk;
    String isSuggest = "";
    private List searchWordList = new ArrayList();
    private List empIDList = new ArrayList();
    private List empNameList = new ArrayList();
    private List hybridEmpIDList = new ArrayList();
    private List hybridEmpNameList = new ArrayList();

    private List allEmpIDList = new ArrayList();
    private List allEmpNameList = new ArrayList();
    private List allEmpDesigList = new ArrayList();

    private List supervisorIDList = new ArrayList();
    private List supervisorNameList = new ArrayList();
    private List supervisorDesigList = new ArrayList();

    private List approverIDList = new ArrayList();
    private List approverNameList = new ArrayList();
    private List approverDesigList = new ArrayList();

    private List empDesigCode = new ArrayList();
    private List empDesigName = new ArrayList();

    public void setSearchKey(String setSearchKey) {
        this.setSearchKey = setSearchKey;
        //System.out.println("setSearchKey-Bean:"+setSearchKey);      
    }

    public void setIsSuggest(String isSuggest) {
        this.isSuggest = isSuggest;
    }

    public EmpNameSearchBean() {
    }

    public List getEmpIDList() {
        return empIDList;
    }

    public void setEmpIDList(List empIDList) {
        this.empIDList = empIDList;
    }

    public List getEmpNameList() {
        return empNameList;
    }

    public void setEmpNameList(List empNameList) {
        this.empNameList = empNameList;
    }

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public List getHybridEmpNameList() {
        return hybridEmpNameList;
    }

    public void setHybridEmpNameList(List hybridEmpNameList) {
        this.hybridEmpNameList = hybridEmpNameList;
    }

    public List getHybridEmpIDList() {
        return hybridEmpIDList;
    }

    public void setHybridEmpIDList(List hybridEmpIDList) {
        this.hybridEmpIDList = hybridEmpIDList;
    }

    public List getAllEmpDesigList() {
        return allEmpDesigList;
    }

    public void setAllEmpDesigList(List allEmpDesigList) {
        this.allEmpDesigList = allEmpDesigList;
    }

    public List getAllEmpIDList() {
        return allEmpIDList;
    }

    public void setAllEmpIDList(List allEmpIDList) {
        this.allEmpIDList = allEmpIDList;
    }

    public List getAllEmpNameList() {
        return allEmpNameList;
    }

    public void setAllEmpNameList(List allEmpNameList) {
        this.allEmpNameList = allEmpNameList;
    }

    public List getEmpDesigCode() {
        return empDesigCode;
    }

    public void setEmpDesigCode(List empDesigCode) {
        this.empDesigCode = empDesigCode;
    }

    public List getEmpDesigName() {
        return empDesigName;
    }

    public void setEmpDesigName(List empDesigName) {
        this.empDesigName = empDesigName;
    }

    public void setSupervisorIDList(List supervisorIdList)
    {
        this.supervisorIDList=supervisorIdList;
    }

    public List getSupervisorIDList()
    {
        return supervisorIDList;
    }

    public void setSupervisorNameList(List supervisorNameList)
    {
        this.supervisorNameList=supervisorNameList;
    }

    public List getSupervisorNameList()
    {
        return supervisorNameList;
    }

    public void setApproverIDList(List approverIdList)
    {
        this.approverIDList=approverIdList;
    }

    public List getApproverIDList()
    {
        return approverIDList;
    }

    public void setApproverNameList(List approverNameList)
    {
        this.approverNameList=approverNameList;
    }

    public List getApproverNameList()
    {
        return approverNameList;
    }

    public void collectEmpNameList() {


        try {
            con = connection.getSampleProperty();
            //System.out.println("con : "+con);
            stmt = con.createStatement();


            stk = new StringTokenizer(setSearchKey, " ");
            nextToken = "%";
            while (stk.hasMoreTokens()) {
                nextToken += stk.nextToken() + "%";
            }

            searchWordList.add(nextToken);

            sql_select = "";

//            sql_select= "select u.emp_id,u.emp_name from user u where u.emp_name like '"+searchWordList.get(0)+"' and u.status='1' ";

            /*
            sql_select = " SELECT * FROM "
                    + " (SELECT DISTINCT(u.emp_id),u.emp_name FROM USER u WHERE u.emp_name LIKE '" + searchWordList.get(0) + "' AND u.status='1' AND facility_id='2' "
                    + " UNION ALL "
                    + " SELECT DISTINCT(u.emp_id),u.emp_name FROM USER u WHERE u.emp_name LIKE '" + searchWordList.get(0) + "' AND u.status='1' AND facility_id='1' "
                    + " AND desig_code IN ('ACNMGR','PM','SNPM','LPM','TRG-PRMG','CSRE','PRCRD','COO','AGM-PMS')) a ORDER BY emp_name limit 0,10";
//            System.out.println("Select Query for Planner: "+sql_select);
             */
            sql_select = "SELECT u.emp_id, u.emp_name FROM USER u WHERE ("
                    + "  (u.facility_id='2') OR "
                    + "  (u.facility_id='1' AND "
                    + "    ("
                    + "      (u.desig_code IN ('GM','ACNMGR','PM','SNPM','LPM','TRG-PRMG','CSRE','PRCRD','COO','AGM-PMS','APM','AVPO','SNMGR')) OR (EXISTS (SELECT * FROM groups g WHERE g.emp_id=u.emp_id AND g.group_id='46') )"
                    + "    )"
                    + "  )"
                    + "  )"
                    + "  AND STATUS='1' AND emp_name LIKE '"+searchWordList.get(0)+"' ORDER BY emp_name LIMIT 0,10";

            rs = stmt.executeQuery(sql_select);

            empIDList.clear();
            empNameList.clear();

            while (rs.next()) {
                empIDList.add(rs.getString(1));
                empNameList.add(rs.getString(2));
            }

            rs.close();
            stmt.close();
            con.close();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }

    }

    public void collectHybridEmpNameList() {


        try {
            con = connection.getSampleProperty();
            //System.out.println("con : "+con);
            stmt = con.createStatement();


            stk = new StringTokenizer(setSearchKey, " ");
            nextToken = "%";
            while (stk.hasMoreTokens()) {
                nextToken += stk.nextToken() + "%";
            }

            searchWordList.add(nextToken);

            sql_select = "";

//            sql_select= "select u.emp_id,u.emp_name from user u where u.emp_name like '"+searchWordList.get(0)+"' and u.status='1' ";

            /*sql_select= "SELECT * FROM "
             + " (SELECT DISTINCT(u.emp_id),u.emp_name FROM USER u WHERE u.emp_name LIKE '"+searchWordList.get(0)+"' AND u.status='1' AND facility_id='1' AND dept_code='HYB' GROUP BY u.emp_name "
             + " UNION ALL"
             + " SELECT DISTINCT(u.emp_id),u.emp_name FROM USER u WHERE u.emp_name LIKE '"+searchWordList.get(0)+"' AND u.status='1' AND facility_id='1' "
             + " AND desig_code IN ('ACNMGR','PM','SNPM','LPM') GROUP BY u.emp_name) a "
             + " ORDER BY emp_name";*/
            sql_select = "SELECT u.emp_id,u.emp_name FROM USER u,designation d,department dep WHERE u.emp_name LIKE '"+searchWordList.get(0)+"' "
                    + " AND "
                    + "   ( "
                    + "     (u.desig_code IN ('ACNMGR','PM','SNPM','LPM','TRG-PRMG','PRCRD','COO','AGM-PMS','APM') OR u.dept_code='HYB') "
                    + "     OR "
                    + "     (EXISTS (SELECT * FROM groups g WHERE g.emp_id=u.emp_id AND g.group_id='47')) "
                    + "   ) "
                    + " AND u.desig_code = d.desig_code AND u.dept_code = dep.dept_code "
                    + " AND u.status='1' AND u.facility_id='1' "
                    + " ORDER BY u.emp_name LIMIT 0,10 ";
            //            System.out.println("Select Query for Hybrid Planner: "+sql_select);
            rs = stmt.executeQuery(sql_select);

            hybridEmpIDList.clear();
            hybridEmpNameList.clear();

            while (rs.next()) {
                hybridEmpIDList.add(rs.getString(1));
                hybridEmpNameList.add(rs.getString(2));
            }

            rs.close();
            stmt.close();
            con.close();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }

    }

    public void collectAllEmpNameList(){
        try {
            con = connection.getSampleProperty();
            //System.out.println("con : "+con);
            stmt = con.createStatement();
            stk=new StringTokenizer(setSearchKey," ");
                //nextToken = "%";
                while(stk.hasMoreTokens()) {
                    nextToken+=stk.nextToken()+"%";
                }
            searchWordList.add(nextToken);
            sql_select= " SELECT u.emp_id,u.emp_name,u.desig_code FROM USER u WHERE u.status=1 and u.emp_name LIKE '"+searchWordList.get(0)+"' OR u.emp_id LIKE '%"+searchWordList.get(0)+"' ORDER BY emp_name LIMIT 10";

            rs = stmt.executeQuery(sql_select);

            allEmpIDList.clear();
            allEmpNameList.clear();
            allEmpDesigList.clear();

            while(rs.next()){
                if(rs.getString(1) != null) {
                    String s = rs.getString(1)+"@@@"+rs.getString(3);
                    allEmpIDList.add(s.trim());
                } else {
                    allEmpIDList.add("");
                }
                if(rs.getString(2) != null) {
                    allEmpNameList.add(rs.getString(2));
                } else {
                    allEmpNameList.add("");
                }
//                if(rs.getString(3) != null) {
//                    allEmpDesigList.add(rs.getString(3));
//                } else {
//                    allEmpDesigList.add("");
//                }
            }

           rs.close();
            stmt.close();
            con.close();

        }catch(SQLException sqle){
            sqle.printStackTrace();
        }catch(NullPointerException npe){
            npe.printStackTrace();
        }
    }

    public void collectSupervisorNameList(){
        try {
            con = connection.getSampleProperty();
            //System.out.println("con : "+con);
            stmt = con.createStatement();
            stk=new StringTokenizer(setSearchKey," ");
                nextToken = "%";
                while(stk.hasMoreTokens()) {
                    nextToken+=stk.nextToken()+"%";
                }
            searchWordList.add(nextToken);
            sql_select= " SELECT emp_id,emp_name,desig_code FROM USER  WHERE STATUS=1 AND is_supervisor=1 AND emp_name NOT IN ('R&D') AND (emp_name LIKE '"+searchWordList.get(0)+"' OR emp_id LIKE '"+searchWordList.get(0)+"') ORDER BY emp_name LIMIT 10";
            //System.out.println("Supervisor selection query : "+sql_select);
            rs = stmt.executeQuery(sql_select);

            supervisorIDList.clear();
            supervisorNameList.clear();
            supervisorDesigList.clear();

            while(rs.next()){
                if(rs.getString(1) != null) {
                    supervisorIDList.add(rs.getString(1));
                } else {
                    supervisorIDList.add("");
                }
                if(rs.getString(2) != null) {
                    supervisorNameList.add(rs.getString(2));
                } else {
                    supervisorNameList.add("");
                }

            }

           rs.close();
            stmt.close();
            con.close();

        }catch(SQLException sqle){
            sqle.printStackTrace();
        }catch(NullPointerException npe){
            npe.printStackTrace();
        }
    }
    public void collectApproverNameList(){
        try {
            con = connection.getSampleProperty();
            //System.out.println("con : "+con);
            stmt = con.createStatement();
            stk=new StringTokenizer(setSearchKey," ");
                nextToken = "%";
                while(stk.hasMoreTokens()) {
                    nextToken+=stk.nextToken()+"%";
                }
            searchWordList.add(nextToken);
            sql_select= " SELECT emp_id,emp_name,desig_code FROM USER  WHERE STATUS=1 AND is_app_authority=1 AND emp_name NOT IN ('R&D') AND (emp_name LIKE '"+searchWordList.get(0)+"' OR emp_id LIKE '"+searchWordList.get(0)+"') ORDER BY emp_name LIMIT 10";
            //System.out.println("Approver selection query : "+sql_select);
            rs = stmt.executeQuery(sql_select);

            approverIDList.clear();
            approverNameList.clear();
            approverDesigList.clear();

            while(rs.next()){
                if(rs.getString(1) != null) {
                    approverIDList.add(rs.getString(1));
                } else {
                    approverIDList.add("");
                }
                if(rs.getString(2) != null) {
                    approverNameList.add(rs.getString(2));
                } else {
                    approverNameList.add("");
                }

            }

           rs.close();
            stmt.close();
            con.close();

        }catch(SQLException sqle){
            sqle.printStackTrace();
        }catch(NullPointerException npe){
            npe.printStackTrace();
        }
    }
}
