/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.hr;

import java.sql.*;
import java.util.*;
/**
 *
 * @author Parameshwarant
 */
public class EmployeeShiftAllocationDAO {

    
   public EmployeeShiftAllocationVO getDeptEmpName(EmployeeShiftAllocationVO getEmpDetailsVO) {

        List empIdList = new ArrayList();
        List empNameList = new ArrayList();
        
        String empId = "";
        String deptCode = getEmpDetailsVO.getDeptCode();
        String desigCode = getEmpDetailsVO.getDesigCode();
        
        
        try {

            String query = "SELECT emp_id,emp_name FROM USER where status=1 ";
            if(!deptCode.equals("")) {
                 query += " and dept_code='"+deptCode+"'";
            }
            if(!desigCode.equals("")) {
                 query += " and desig_code='"+desigCode+"'";
            }
            query += " order by emp_name ";
            
            connection.DBconnection dbCon = new connection.DBconnection();
            Connection con = dbCon.getSampleProperty();
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                empId = rs.getString(1);
                empIdList.add(empId);
                empNameList.add(rs.getString(2));

            }
            
            getEmpDetailsVO.setEmpId(empIdList);
            getEmpDetailsVO.setEmpName(empNameList);

            rs.close();
            st.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Exception in EmployeeShiftAllocationDAO class " + e);
        }
        return getEmpDetailsVO;
    }


    public EmployeeShiftAllocationVO getShiftType(EmployeeShiftAllocationVO getEmpShiftType) {

            List shiftId = new ArrayList();
            List shiftName = new ArrayList();

        try {
            connection.DBconnection dbCon = new connection.DBconnection();
            
            Connection con = dbCon.getSampleProperty();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select shift_id,shift_name from shift_type");
            
            while(rs.next()) {
                shiftId.add(rs.getString(1));
                shiftName.add(rs.getString(2));
            }
            
            getEmpShiftType.setShiftId(shiftId);
            getEmpShiftType.setShiftName(shiftName);

            rs.close();
            st.close();
            con.close();

        } catch(SQLException e) {
            System.out.println("Exception in EmployeeShiftAllocationDAO class "+e);
        }

       return getEmpShiftType;
    }


   public void addUpdateEmpShift(EmployeeShiftAllocationVO addEmpShiftVO) {

       int insertShift = 0;
        List empId = addEmpShiftVO.getEmpIdToAdd();
        List shiftId = addEmpShiftVO.getShiftIdToAdd();
        List shiftFrom = addEmpShiftVO.getFromDate();
        List shiftTo = addEmpShiftVO.getToDate();
        List empShiftStatusIdToChk = addEmpShiftVO.getShiftStatusIdToChk();
        
        String currentEmpId = addEmpShiftVO.getCurrEmpId();
        String empShiftStatusChk = "";
        String shiftComplete = addEmpShiftVO.getShiftComplete();
        String empIdForShiftComplete = addEmpShiftVO.getEmpIdForShiftComplete();
        
        try {

            connection.DBconnection dbCon = new connection.DBconnection();
            /* Status code description: 0-shift completed, 1-shift in progress, 2-ready to shift allocation(this code won't be save in database, but it's an assumption) */

            String queryToAddEmpShift = "insert into emp_shift_allocation(emp_id, shift_id, from_date, to_date,allocated_date,allocated_by) values (?,?,?,?,current_timestamp(),?)";
            String queryToUpdateEmpShift = "update emp_shift_allocation set shift_id=?, from_date=?, to_date=?,modified_by=?,modified_date=current_timestamp() where emp_id=? and status=1";

            Connection con = dbCon.getSampleProperty();
            Statement stToUpdate = con.createStatement();
            PreparedStatement pstToInsert = con.prepareStatement(queryToAddEmpShift);
            PreparedStatement pstToUpdate = con.prepareStatement(queryToUpdateEmpShift);
            for(int i=0;i<empId.size();i++) {
                empShiftStatusChk = empShiftStatusIdToChk.get(i).toString();
                if(empShiftStatusChk.equals("2")) {
                    pstToInsert.setString(1, empId.get(i).toString());
                    pstToInsert.setInt(2, Integer.parseInt(shiftId.get(i).toString()));
                    pstToInsert.setString(3, shiftFrom.get(i).toString());
                    pstToInsert.setString(4, shiftTo.get(i).toString());
                    pstToInsert.setString(5, currentEmpId);
                    
                    insertShift = pstToInsert.executeUpdate();
                } else if(empShiftStatusChk.equals("1")) {

                    pstToUpdate.setInt(1, Integer.parseInt(shiftId.get(i).toString()));
                    pstToUpdate.setString(2, shiftFrom.get(i).toString());
                    pstToUpdate.setString(3, shiftTo.get(i).toString());
                    pstToUpdate.setString(4, currentEmpId);
                    pstToUpdate.setString(5, empId.get(i).toString());
                    
                    insertShift = pstToUpdate.executeUpdate();
                }

            }
            if(shiftComplete.equals("yes")) {
                stToUpdate.executeUpdate("update emp_shift_allocation set status=0 where emp_id='"+empIdForShiftComplete+"'" );
                
            }

            pstToInsert.close();
            pstToUpdate.close();
            con.close();

        } catch(SQLException e) {
            System.out.println("Exception in EmployeeShiftAllocationDAO class "+e);
        }


   }
   public EmployeeShiftAllocationVO getEmpShiftDetails(EmployeeShiftAllocationVO getEmpShftAllocatedData) {

        List empShiftId = new ArrayList();
        List empShiftName = new ArrayList();
        List empShiftFromDate = new ArrayList();
        List empShiftToDate = new ArrayList();
        List empShiftStatusId = new ArrayList();
        List empLastShiftFromDate = new ArrayList();
        List empLastShiftToDate = new ArrayList();
        List employeesId = getEmpShftAllocatedData.getEmpId();
        
        String currEmpId = getEmpShftAllocatedData.getCurrEmpId();
        String empId = "";
        String queryToGetEmpShiftInfo = "";
        String queryToGetEmpLastShiftInfo = "";
        
        try {

            connection.DBconnection dbCon = new connection.DBconnection();
            Connection con = dbCon.getSampleProperty();
            Statement st = con.createStatement();
            Statement st1 = con.createStatement();
            ResultSet rs = null;
            ResultSet rs1 = null;
            if (!employeesId.isEmpty()) {
                for (int i = 0; i < employeesId.size(); i++) {
                    
                    empId = employeesId.get(i).toString();
                    queryToGetEmpShiftInfo = "SELECT IFNULL(a.shift_id,''),IFNULL(b.shift_name,''),IFNULL(a.from_date,''),IFNULL(a.to_date,''),IFNULL(a.status,'2'),"
                            + " CASE WHEN c.status=0 THEN IFNULL(c.from_date,'') ELSE '' END from_date, CASE WHEN c.status=0 THEN IFNULL(c.to_date,'') ELSE '' END AS to_date "
                            + " FROM emp_shift_allocation a LEFT JOIN  shift_type b USING (shift_id) LEFT JOIN  emp_shift_allocation c USING (emp_id) "
                            + " WHERE (a.status=1 and c.status=0 and a.emp_id='" + empId + "') or (a.status=1 and a.emp_id='" + empId + "') ORDER BY c.status!=0, c.emp_shift_id DESC LIMIT 1 ";

                    queryToGetEmpLastShiftInfo = "SELECT IFNULL(from_date,''),IFNULL(to_date,'') FROM emp_shift_allocation WHERE emp_id='"+empId+"' AND STATUS=0 ORDER BY emp_shift_id DESC LIMIT 1";

                    rs = st.executeQuery(queryToGetEmpShiftInfo);
                    if (rs.next()) {
                        rs = st.executeQuery(queryToGetEmpShiftInfo);
                        while (rs.next()) {
                            empShiftId.add(rs.getString(1));
                            empShiftName.add(rs.getString(2));
                            empShiftFromDate.add(rs.getString(3));
                            empShiftToDate.add(rs.getString(4));
                            empShiftStatusId.add(rs.getString(5));
                            empLastShiftFromDate.add(rs.getString(6));
                            empLastShiftToDate.add(rs.getString(7));

                            
                        }
                    } else {
                        empShiftId.add("");
                        empShiftName.add("");
                        empShiftFromDate.add("");
                        empShiftToDate.add("");
                        empShiftStatusId.add("2");
                        rs1 = st1.executeQuery(queryToGetEmpLastShiftInfo);
                            if (rs1.next()) {
                                rs1 = st1.executeQuery(queryToGetEmpLastShiftInfo);
                                while (rs1.next()) {
                                    empLastShiftFromDate.add(rs1.getString(1));
                                    empLastShiftToDate.add(rs1.getString(2));
                                }
                            } else {
                                empLastShiftFromDate.add("");
                                empLastShiftToDate.add("");
                            }
                        
                    }
                }
            } else {
                queryToGetEmpShiftInfo = "SELECT b.shift_name,IFNULL(a.from_date,''),IFNULL(a.to_date,'') FROM emp_shift_allocation a LEFT JOIN  shift_type b USING (shift_id) "
                        + " WHERE a.status=1 AND a.emp_id = '" + currEmpId + "'";
                
                rs = st.executeQuery(queryToGetEmpShiftInfo);
                if (rs.next()) {
                    rs = st.executeQuery(queryToGetEmpShiftInfo);
                    while (rs.next()) {
                        getEmpShftAllocatedData.setCurrEmpShift(rs.getString(1));
                        getEmpShftAllocatedData.setCurrEmpShiftFromDate(rs.getString(2));
                        getEmpShftAllocatedData.setCurrEmpShiftToDate(rs.getString(3));
                    }
                } else {
                    getEmpShftAllocatedData.setCurrEmpShift("N/A");
                    getEmpShftAllocatedData.setCurrEmpShiftFromDate("N/A");
                    getEmpShftAllocatedData.setCurrEmpShiftToDate("N/A");
                }
            }


            getEmpShftAllocatedData.setEmpShiftId(empShiftId);
            getEmpShftAllocatedData.setShiftName(empShiftName);
            getEmpShftAllocatedData.setEmpShiftFromDate(empShiftFromDate);
            getEmpShftAllocatedData.setEmpShiftToDate(empShiftToDate);
            getEmpShftAllocatedData.setShiftStatusId(empShiftStatusId);
            getEmpShftAllocatedData.setEmpLastShiftFromDate(empLastShiftFromDate);
            getEmpShftAllocatedData.setEmpLastShiftToDate(empLastShiftToDate);
            

            st.close();
            con.close();

        }
        catch(SQLException e) {
            System.out.println("Exception in EmployeeShiftAllocationDAO class "+e);
        }

       return getEmpShftAllocatedData;
    }

}
