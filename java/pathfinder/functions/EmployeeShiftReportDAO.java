/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions;
import java.sql.*;
import java.util.*;
/**
 *
 * @author Parameshwarant
 */
public class EmployeeShiftReportDAO {

    public EmployeeShiftReportVO getEmployeeShiftReport (EmployeeShiftReportVO employeeShiftReportVO) {

        connection.DBconnection dbconnection = new connection.DBconnection();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String sqlSelect = "";
        String sqlFrom = "";
        String sqlWhere = "";

        String facilityID = employeeShiftReportVO.getFacilityID();
        String deptID = employeeShiftReportVO.getDeptID();
        String desigID = employeeShiftReportVO.getDesigID();
        String fromDate = employeeShiftReportVO.getFormDate();
        String toDate = employeeShiftReportVO.getToDate();

        String totalDays = "";
        String workingDays = "";
        String paidHolidays = "";
        String unPaidHolidays = "";

        List empIdList = new ArrayList();
        List empNameList = new ArrayList();
        List deptList = new ArrayList();
        List facilityList = new ArrayList();
        List totWorkingDaysList = new ArrayList();

        

        try {
            connection = dbconnection.getSampleProperty();

            sqlSelect = " SELECT "
                    + " SUM(1), "
                    + " SUM(CASE WHEN (holiday=0) THEN 1 ELSE 0 END), "
                    + " SUM(CASE WHEN (holiday=1) THEN 1 ELSE 0 END), "
                    + " SUM(CASE WHEN (holiday=2) THEN 1 ELSE 0 END) "
                    + " FROM calendar "
                    + " WHERE date_value BETWEEN DATE('"+fromDate+"') AND DATE('"+toDate+"') ";

            preparedStatement = connection.prepareStatement(sqlSelect);

            resultSet = preparedStatement.executeQuery(sqlSelect);

            while(resultSet.next()) {
                if(resultSet.getString(1) != null) {
                    totalDays = resultSet.getString(1) != null ? resultSet.getString(1).toString() : "";
                } else {
                    totalDays = "";
                }
                if(resultSet.getString(2) != null) {
                    workingDays = resultSet.getString(2) != null ? resultSet.getString(2).toString() : "";
                } else {
                    workingDays = "";
                }
                if(resultSet.getString(3) != null) {
                    paidHolidays = resultSet.getString(3) != null ? resultSet.getString(3).toString() : "";
                } else {
                    paidHolidays = "";
                }
                if(resultSet.getString(4) != null) {
                    unPaidHolidays = resultSet.getString(4) != null ? resultSet.getString(4).toString() : "";
                } else {
                    unPaidHolidays = "";
                }
            }
            employeeShiftReportVO.setTotalDays(totalDays);
            employeeShiftReportVO.setWorkingDays(workingDays);
            employeeShiftReportVO.setPaidHolidays(paidHolidays);
            employeeShiftReportVO.setUnPaidHolidays(unPaidHolidays);

            sqlSelect = " select emp_name, COUNT(emp_id), emp_id, department, facility_name from (select u.emp_name, COUNT(u.emp_id) as nos, u.emp_id, dpt.department, f.facility_name ";
            sqlFrom = " from user u left join shift sh on u.emp_id = sh.emp_id, department dpt, designation desig, facility f ";
            sqlWhere = " where u.status = '1' and u.dept_code = dpt.dept_code and u.facility_id = f.facility_id and u.desig_code = desig.desig_code "
                                + " and sh.shift_start_time between '" + fromDate + "' and '" + toDate + "' ";
            if(facilityID.equals("ALL")) {
                sqlWhere += " and u.facility_id is not null ";
            } else {
                sqlWhere += " and u.facility_id = '" + facilityID + "' ";
            }
            if(deptID.equals("ALL")) {
                   sqlWhere += " and u.dept_code is not null ";
            }   else {
                   sqlWhere += " and u.dept_code = '" + deptID + "' ";
            }
            if(desigID.equals("ALL")) {
                sqlWhere += " and u.desig_code is not null ";
            }    else {
                    sqlWhere += " and u.desig_code = '" + desigID + "' ";
            }
            sqlSelect = sqlSelect + sqlFrom + sqlWhere + " group by u.emp_id, DATE(sh.shift_start_time)) daysworked group by emp_id " ;
            //System.out.println("The query is "+sqlSelect);
            resultSet = preparedStatement.executeQuery(sqlSelect);
            
            while(resultSet.next()) {
                    if(resultSet.getString(1) != null) {
                        empNameList.add(resultSet.getString(1));
                    } else {
                        empNameList.add("");
                    }
                    if(resultSet.getString(2) != null) {
                        totWorkingDaysList.add(resultSet.getString(2));
                    } else {
                        totWorkingDaysList.add("");
                    }
                    if(resultSet.getString(3) != null) {
                        empIdList.add(resultSet.getString(3));
                    } else {
                        empIdList.add("");
                    }
                    if(resultSet.getString(4) != null) {
                        deptList.add(resultSet.getString(4));
                    } else {
                        deptList.add("");
                    }
                    if(resultSet.getString(5) != null) {
                        facilityList.add(resultSet.getString(5));
                    } else {
                        facilityList.add("");
                    }
            }
            employeeShiftReportVO.setEmpNameList(empNameList);
            employeeShiftReportVO.setTotWorkingDaysList(totWorkingDaysList);
            employeeShiftReportVO.setEmpIdList(empIdList);
            employeeShiftReportVO.setDeptNameList(deptList);
            employeeShiftReportVO.setFaciliytList(facilityList);

        } catch (SQLException e) {
            System.out.println("SQLException : EmployeeShiftReportDAO - getEmployeeShiftReport()" + e);
        } finally {
            try {
                resultSet.close();
                preparedStatement.close();
                connection.close();
            } catch (SQLException ex) {
                System.out.println("SQLException : EmployeeShiftReportDAO - getEmployeeShiftReport()" + ex);
            }
        }

        return employeeShiftReportVO;
    }
}
