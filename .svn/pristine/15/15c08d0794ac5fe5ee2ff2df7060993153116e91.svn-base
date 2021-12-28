/*
 * LeaveBalance.java
 *
 * Created on November 30, 2009, 6:23 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package pathfinder.functions.leave;
import java.sql.*;
import java.util.*;
import connection.*;

/**
 *
 * @author ramyamaims
 */
public class LeaveBalance {

    CalculateInitialLeaveBalance clib = new CalculateInitialLeaveBalance();
    DBconnection connection = new DBconnection();

    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;
    private int intResult = 0;

    private String leave_id = "";
    private String emp_id = "";
    private String set_facilityId = "";
    private String dateOfJoining = "";
    private String currMon = "";

    private int intTwd = 0;
    private int modTwd = 0;
    private int total_workDays = 0;

    private List leave_type_id = new ArrayList();
    private List leave_type = new ArrayList();
    private List initial_balance = new ArrayList();
    private List current_balance = new ArrayList();
    private List balance_ID = new ArrayList();

    String leaveYear = "";

    private String setEmp;
    private String addBal_sql = "";
    private String balID_sql = "";

    /** Creates a new instance of LeaveBalance */
    public LeaveBalance() {
    }

    public void setEmpId(String emp_id){
        this.emp_id = emp_id;
    }

    public void setFacilityId(String set_facilityId){
        this.set_facilityId = set_facilityId;
    }

     public void setLeaveYear(String leaveYear){
        this.leaveYear = leaveYear;
        //In case of leave Deny, TL applying leave for subordinates the Laave year(mainly for the sake of applying Dec leav in next year Jan) could be passed so that the exact leave balance would be updated
    }

    public List getLeaveTypeId(){
        return leave_type_id;
    }

    public List getLeaveType(){
        return leave_type;
    }

    public List getInitialBal(){
        return initial_balance;
    }

    public List getCurrentBal(){
        return current_balance;
    }

    public List getBalanceId(){
        return balance_ID;
    }

    public void getValue(){


        //System.out.println("set_facilityId : "+set_facilityId);

        try {
            con = connection.getSampleProperty();
            stmt = con.createStatement();

            rs = stmt.executeQuery("select date(doj) from user where emp_id = '"+emp_id+"'");

            while(rs.next()){
                dateOfJoining = rs.getString(1);
            }
            //System.out.println("select datediff(current_date ,'"+dateOfJoining+"'), month(current_date)");
            rs = stmt.executeQuery("select datediff(current_date ,'"+dateOfJoining+"'), month(current_date)");

            while(rs.next()){
                total_workDays = rs.getInt(1);
                currMon = rs.getString(2);
            }

            intTwd = total_workDays/365;
            modTwd = total_workDays%365;

            leave_type_id.clear();
            leave_type.clear();
            initial_balance.clear();
            current_balance.clear();
            balance_ID.clear();
            ////System.out.println("select lt.lv_type_id,lt.lv_type,lb.initial_bal,lb.current_bal from leave_balance lb, leave_type lt where emp_id = '"+emp_id+"' and year = year(current_date) and lt.lv_type_id = lb.lv_type_id order by lv_type_id");

            String bal_sql = "";
            if(leaveYear.equals("")){
                bal_sql = "select lt.lv_type_id,lt.lv_type,lb.initial_bal,lb.current_bal,lb.lv_balance_id "+
                          " from leave_balance lb, leave_type lt where emp_id = '"+emp_id+"' and "+
                          " year = year(current_date) and lt.lv_type_id = lb.lv_type_id "+
                          " order by lv_type_id";
            }else{
                bal_sql = "select lt.lv_type_id,lt.lv_type,lb.initial_bal,lb.current_bal,lb.lv_balance_id "+
                          " from leave_balance lb, leave_type lt where emp_id = '"+emp_id+"' and "+
                          " year = '"+leaveYear+"' and lt.lv_type_id = lb.lv_type_id "+
                          " order by lv_type_id";
            }

            clib.setEmpId(emp_id);
            clib.setFacilityId(set_facilityId);
            clib.setIntTwd(intTwd);
            clib.setModTwd(modTwd);
            clib.setCurrMon(currMon);

            //System.out.println("bal_sql:"+bal_sql);

            rs = stmt.executeQuery(bal_sql);

            if(rs.next()){
                //If the Leave blaance exists for the employee then the blance would be shown or else in the below else condition the balance would be calculated
                //System.out.println("if ************* if");
                rs = stmt.executeQuery(bal_sql);

                while(rs.next()){
                    leave_type_id.add(rs.getString(1));
                    leave_type.add(rs.getString(2));
                    initial_balance.add(rs.getString(3));
                    current_balance.add(rs.getString(4));
                    balance_ID.add(rs.getString(5));

                }
                //System.out.println("leave_type_id : "+leave_type_id);
                    if(!leave_type_id.contains("3")){

                        if(intTwd>0){
                            clib.calculateLeaveBal();
                            leave_type_id = clib.getLeaveTypeId();
                            leave_type = clib.getLeaveType();
                            initial_balance = clib.getLeaveLimit();
                            current_balance = clib.getBalanceLimit();

                            if(leaveYear.equals("")){
                                addBal_sql="insert into leave_balance(emp_id,lv_type_id,year,initial_bal,current_bal) " +
                                        " values ('"+emp_id+"','"+leave_type_id.get(leave_type_id.size()-1)+"',year(current_timestamp()),'"+initial_balance.get(leave_type_id.size()-1)+"','"+current_balance.get(leave_type_id.size()-1)+"') ";
                            }else {
                                addBal_sql="insert into leave_balance(emp_id,lv_type_id,year,initial_bal,current_bal) " +
                                        " values ('"+emp_id+"','"+leave_type_id.get(leave_type_id.size()-1)+"','"+leaveYear+"','"+initial_balance.get(leave_type_id.size()-1)+"','"+current_balance.get(leave_type_id.size()-1)+"') ";
                            }

                            try {
                                intResult = stmt.executeUpdate(addBal_sql);
                            }catch(SQLException sqle){
                                sqle.printStackTrace();
                            }

                            if(leaveYear.equals("")){
                                balID_sql = "select lv_balance_id from leave_balance where emp_id='"+emp_id+"' and lv_type_id='"+leave_type_id.get(leave_type_id.size()-1)+"' and year=year(current_timestamp()) ";
                            }else{
                                balID_sql = "select lv_balance_id from leave_balance where emp_id='"+emp_id+"' and lv_type_id='"+leave_type_id.get(leave_type_id.size()-1)+"' and year='"+leaveYear+"' ";
                            }

                            ResultSet rsBalanceId =  stmt.executeQuery(balID_sql);
                            while(rsBalanceId.next()){
                                balance_ID.add(rsBalanceId.getString(1));
                            }
                            rsBalanceId.close();
                        }
                    }
            }else {

                //System.out.println("else ************* else");

                clib.calculateLeaveBal();
                balance_ID.clear();
                leave_type_id = clib.getLeaveTypeId();
                leave_type = clib.getLeaveType();
                initial_balance = clib.getLeaveLimit();
                current_balance = clib.getBalanceLimit();

                if(leave_type_id.size()>0){

                    for(int loop=0;loop<leave_type_id.size();loop++) {

                        if(leaveYear.equals("")){
                            addBal_sql="insert into leave_balance(emp_id,lv_type_id,year,initial_bal,current_bal) " +
                                    " values ('"+emp_id+"','"+leave_type_id.get(loop)+"',year(current_timestamp()),'"+initial_balance.get(loop)+"','"+current_balance.get(loop)+"') ";
                        }else {
                            addBal_sql="insert into leave_balance(emp_id,lv_type_id,year,initial_bal,current_bal) " +
                                    " values ('"+emp_id+"','"+leave_type_id.get(loop)+"','"+leaveYear+"','"+initial_balance.get(loop)+"','"+current_balance.get(loop)+"') ";
                        }

                        try {
                            intResult = stmt.executeUpdate(addBal_sql);
                        }catch(SQLException sqle){
                            sqle.printStackTrace();
                        }
                    //since balance ID is needed in the jsp pages that should be collected in an arraylist
                        if(leaveYear.equals("")){
                            balID_sql = "select lv_balance_id from leave_balance where emp_id='"+emp_id+"' and lv_type_id='"+leave_type_id.get(loop)+"' and year=year(current_timestamp()) ";
                        }else{
                            balID_sql = "select lv_balance_id from leave_balance where emp_id='"+emp_id+"' and lv_type_id='"+leave_type_id.get(loop)+"' and year='"+leaveYear+"' ";
                        }

                       ResultSet rsBalanceId =  stmt.executeQuery(balID_sql);
                       while(rsBalanceId.next()){
                           balance_ID.add(rsBalanceId.getString(1));
                       }
                       rsBalanceId.close();
                    }
                }
            }

        }catch(SQLException sqle){
            sqle.printStackTrace();
        }catch(NullPointerException npe){
            npe.printStackTrace();
        }
        finally {
            try {
                rs.close();
                stmt.close();
                con.close();
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
}

        //System.out.println("leave_type_id Leave balance : "+leave_type_id);
        //System.out.println("leave_type Leave balance : "+leave_type);
        //System.out.println("initial_balance Leave balance : "+initial_balance);
        //System.out.println("current_balance Leave balance : "+current_balance);
    }
}
