/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder.functions.timesheet;

import connection.DBconnection;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.*;


    //This function is used to calculate empOverheadValue
/**
 *
 * @author Thanu
 */
public class SaveEmpDetpOverheadValue {

    connection.DBconnection conProp = new connection.DBconnection();
    private int empOverhead;
    private int deptOverhead;
    private double empOverheadVal;
    private double deptOverheadVal;
    private String empId;
    private String deptCode;
    private int activityId;
    private java.util.Date startTime;
    private java.util.Date endTime;


    public int getEmpOverhead() {
        return empOverhead;
    }

    public void setEmpOverhead(int empOverhead) {
        this.empOverhead = empOverhead;
    }

    public int getDeptOverhead() {
        return deptOverhead;
    }

    public void setDeptOverhead(int deptOverhead) {
        this.deptOverhead = deptOverhead;
    }

    public double getEmpOverheadVal() {
        return empOverheadVal;
    }

    public void setEmpOverheadVal(double empOverheadVal) {
        this.empOverheadVal = empOverheadVal;
    }

    public double getDeptOverheadVal() {
        return deptOverheadVal;
    }

    public void setDeptOverheadVal(int deptOverheadVal) {
        this.deptOverheadVal = deptOverheadVal;
    }

    public String setEmpId(String empId) {
        return this.empId = empId;
    }

    public String setDeptCode(String deptCode) {
        return this.deptCode = deptCode;
    }

    public int setActivityId(int activityId) {
        return this.activityId = activityId;
    }

      public java.util.Date setStartTime(java.util.Date startTime) {
        return this.startTime = startTime;
    }

    public java.util.Date setEndTime(java.util.Date endTime) {
        return this.endTime = endTime;
    }


    //This function returns the employee overhead from the emp_overhead table.
    public void collectEmpOverhead() {
        //System.out.println("collectEmpOverhead");
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;
        DBconnection dbcon = new DBconnection();

        try {

            con = dbcon.getSampleProperty();

            String sql = "SELECT emp_overhead_val "
                    + "FROM emp_overhead "
                    + "WHERE emp_id=? "
                    + "ORDER BY emp_overhead_order DESC "
                    + "LIMIT 1 ";

            stmt = con.prepareStatement(sql);
            stmt.setString(1, empId);

            rs = stmt.executeQuery();
            while (rs.next()) {
                if(rs!=null)
                    empOverhead = Integer.parseInt(rs.getString(1));
                else
                    empOverhead = 0;
            }

            //System.out.println("----empOverhead:"+empOverhead);

        } catch (SQLException sqle) {
            System.out.println("SQLException in collectEmpOverhead:" + sqle);
        } catch (Exception e) {
            System.out.println("Exception in collectEmpOverhead:" + e);
        } finally {

            //Close resultset
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException se) {
                    System.out.println("Exception while closing resultset:se.getMessage()");
                }

            }

            //Close statement
            if (stmt != null) {
                try {
                    stmt.close();
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

    }

     //This function returns the department overhead from the emp_overhead table.
    public void collectDeptOverhead() {
        //System.out.println("collectDeptOverhead");
        Connection con = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;
        DBconnection dbcon = new DBconnection();

        try {

            con = dbcon.getSampleProperty();

            String sql = "SELECT dept_overhead_val "
                    + "FROM dept_overhead "
                    + "WHERE dept_code=? "
                    + "ORDER BY dept_overhead_order DESC "
                    + "LIMIT 1 ";

            stmt = con.prepareStatement(sql);
            stmt.setString(1, deptCode);
//System.out.println("deptCode:"+deptCode);
            rs = stmt.executeQuery();
            while (rs.next()) {
                if(rs!=null)
                    deptOverhead = Integer.parseInt(rs.getString(1));
                else
                    deptOverhead = 0;
            }

            //System.out.println("----deptOverhead:"+deptOverhead);

        } catch (SQLException sqle) {
            System.out.println("SQLException in collectDeptOverhead:" + sqle);
        } catch (Exception e) {
            System.out.println("Exception in collectDeptOverhead:" + e);
        } finally {

            //Close resultset
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException se) {
                    System.out.println("Exception while closing resultset:se.getMessage()");
                }

            }

            //Close statement
            if (stmt != null) {
                try {
                    stmt.close();
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

    }

    //This function is used to get activity details
    public String getActivityTime()
    {
        //System.out.println("getActivityTime");
         Connection con = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;
        DBconnection dbcon = new DBconnection();

       String timeDiff="";



        try {
             con = dbcon.getSampleProperty();

             String sql = "SELECT ((HOUR(TIMEDIFF(end_time,begin_time))*60)+(MINUTE(TIMEDIFF(end_time,begin_time))))/60 AS total "+
                          "FROM activity "+
                          "WHERE activity_id=? ";

              stmt = con.prepareStatement(sql);
            stmt.setInt(1, activityId);

            rs = stmt.executeQuery();
            while(rs.next())
            {
               timeDiff = rs.getString(1);
            }

            //System.out.println("----timeDiff:"+timeDiff);

         } catch (SQLException sqle) {
            System.out.println("SQLException in collectActivityDetails:" + sqle);
        } catch (Exception e) {
            System.out.println("Exception in collectActivityDetails:" + e);
        } finally {

            //Close resultset
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException se) {
                    System.out.println("Exception while closing resultset:se.getMessage()");
                }

            }

            //Close statement
            if (stmt != null) {
                try {
                    stmt.close();
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
        return timeDiff;

    }
    public void calcEmpOverheadVal(String time_diff)
    {
       //System.out.println("calcEmpOverheadVal");
        //System.out.println("TimeDiff in calcemp:"+time_diff);
        double value = 0 ;



        try {


             //Get the employee overhead
             collectEmpOverhead();

             //Calculate the time taken for that activity
             if(time_diff!=null && !time_diff.equals(""))
             {
                  //System.out.println("----------------timediff:"+time_diff);
                value = Double.parseDouble(time_diff);

                // System.out.println("value:"+value);
                value = value*empOverhead;
             }

             //System.out.println("-----------------value:"+value);
             //Round of the value to 2 decimal places
             pathfinder.functions.generic.PFUtil pfUtil = new pathfinder.functions.generic.PFUtil();
             empOverheadVal = pfUtil.roundOff(value);


//System.out.println("----empOverheadVal:"+empOverheadVal);

         } catch (Exception e) {
            System.out.println("Exception in calcEmpOverheadVal:" + e);
        } finally {


        }

    }

    //This function is to calculate department overhead value
     public void calcDeptOverheadVal(String time_diff)
    {
         //System.out.println("TimeDiff in calcdept:"+time_diff);
         //System.out.println("calcDeptOverheadVal");

        double value = 0 ;



        try {


             //Get the employee overhead
             collectDeptOverhead();


             //Calculate the time taken for that activity
             if(time_diff!=null && !time_diff.equals(""))
             {
                 //System.out.println("----------------timediff:"+time_diff);
                value = Double.parseDouble(time_diff)*deptOverhead;
                System.out.println("value:"+value);
             }
               //System.out.println("-----------------value:"+value);
             //Round of the value to 2 decimal places
             pathfinder.functions.generic.PFUtil pfUtil = new pathfinder.functions.generic.PFUtil();
             deptOverheadVal = pfUtil.roundOff(value);

//System.out.println("----deptOverheadVal:"+deptOverheadVal);


         } catch (Exception e) {
            System.out.println("Exception in calcDeptOverheadVal:" + e);
        } finally {


        }

    }

     public void updateOverheadVal()
     {
         //System.out.println("ovehreadheadval");

          Connection con = null;
        ResultSet rs = null;
        PreparedStatement stmt = null;
        DBconnection dbcon = new DBconnection();
        String sql="";
        int recordsUpdated = 0;
        String timeDiff="";


        try
        {


 con = dbcon.getSampleProperty();


         //Get the time taken for the activity
         timeDiff = getActivityTime();
         //System.out.println("TimeDiff:"+timeDiff);

         //Calculate emp overhead value
         calcEmpOverheadVal(timeDiff);

         //Calculate dept overhead value
         calcDeptOverheadVal(timeDiff);

         //Update in activity table with emp and dept overhead for the given activity_id
         sql="UPDATE activity "+
             "SET emp_overhead_val=?,"    +
                  "dept_overhead_val=? " +
             "WHERE activity_id=?";
           stmt = con.prepareStatement(sql);
           stmt.setDouble(1, empOverheadVal);
           stmt.setDouble(2, deptOverheadVal);
           stmt.setInt(3, activityId);

         recordsUpdated = stmt.executeUpdate();
//System.out.println("----recordsUpdated:"+recordsUpdated);

          } catch (SQLException sqle) {
            System.out.println("SQLException in updateOverheadVal:" + sqle);
        } catch (Exception e) {
            System.out.println("Exception in updateOverheadVal:" + e);
        } finally {

            //Close resultset
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException se) {
                    System.out.println("Exception while closing resultset:se.getMessage()");
                }

            }

            //Close statement
            if (stmt != null) {
                try {
                    stmt.close();
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


     }
}
