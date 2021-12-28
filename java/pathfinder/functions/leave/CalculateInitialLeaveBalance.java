/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.leave;

import java.io.Serializable;

import java.util.*;
import java.sql.*;
import connection.DBconnection;


/**
 *
 * @author  ramesh
 */
public class CalculateInitialLeaveBalance implements Serializable {

    DBconnection connection = new DBconnection();
    private Connection con = null;
    private Statement stmt = null;
    private ResultSet rs = null;

    private String empId = "";
    private String doc = "";
    private String doj = "";
    private String set_facilityId = "";
    private String date_of_joining = null;
    private String date_of_confirm = null;

    private int currentYear=0;
    private String currentMonth="";
    private String currentDay="";
    private String currentDate="";
    private int DOJ_year=0;
    private int DOC_year=0;
    private int DOJ_diff = 0;
    private int intTwd = 0;
    private int modTwd = 0;
    private String currMon = "";

//varaiable top calculate the difference between the current year and the year of joining

    private List leaveType_Id = new ArrayList();
    private List leaveType = new ArrayList();
    private List leaveLimit = new ArrayList();
    private List balanceLimit = new ArrayList();
    private List tempLeaveLimit = new ArrayList();

    public void setEmpId(String empId){
        this.empId=empId;
    }

    public void setIntTwd(int intTwd){
        this.intTwd = intTwd;
    }

    public void setModTwd(int modTwd){
        this.modTwd = modTwd;
    }

    public void setCurrMon(String currMon){
        this.currMon = currMon;
    }

    public void setFacilityId(String set_facilityId){
        this.set_facilityId = set_facilityId;
    }

    public void calculateLeaveBal(){

        String setLeave_Limit = "";

        PresetLeaveBalance plvb = new PresetLeaveBalance();
        plvb.setEmpId(empId);
        plvb.setFacilityId(set_facilityId);
        //System.out.println("set_facilityId:"+set_facilityId);
        //System.out.println("intTwd : "+intTwd);
        plvb.setIntTwd(intTwd);
        plvb.setModTwd(modTwd);
        plvb.setCurrMon(currMon);
        //method call to retrieve the balance for each applicable leaves preset in DB

        /*****the below block passes the empid to the employee dates and then collects the doj and dateofconfirmation for the employee**/
        //also the current year and date
            SampleData sd = new SampleData();
            sd.testData();

        try{


                    EmployeeDates1 empdt = new EmployeeDates1();
        empdt.setEmpId(empId);
        empdt.getDates();

            date_of_joining = empdt.getDateOfJoin();
            date_of_confirm = empdt.getDateOfConfirm();
            currentYear =  Integer.parseInt(empdt.getCurrentYear());
            currentMonth = empdt.getCurrentMonth();
            currentDay = empdt.getCurrentDay();
            currentDate = empdt.getCurrentDate();

            DOJ_year = Integer.parseInt(empdt.getDOJYear());
            DOC_year = Integer.parseInt(empdt.getDOCYear());
        }catch(NumberFormatException nfe) {
            System.out.println("NumberFormatException in CLIB:"+nfe);
        }

        DOJ_diff = currentYear-DOJ_year;

        //System.out.println("date_of_joining in CLIB:"+date_of_joining);
        //System.out.println("date_of_confirm  in CLIB:"+date_of_confirm);
        //System.out.println("currentDate:"+currentDate);
        //System.out.println("currentYear:"+currentYear);
        //System.out.println("DOJ_year:"+DOJ_year);
        //System.out.println("DOC_year:"+DOC_year);
        //System.out.println("DOJ_diff:"+DOJ_diff);

        //with the above variables based on the below 5 conditions the
        //leave balance will be calculated when the
        //employee enters the leave application page for the first in a year

        /* 1.Employee joined current year and not yet confirmed all leaves will be LossOfPay
         * 2.Employee joined current year and confirmed this year - calculate leave balance
         * 3.Employee joined last year and confirmed this year - calculate leave balance
         * 4.Employee joined last year and confirmed last year - full leave balance as set for the current year in DB is applicable
         * 5.Employee joined one year before last year and date of confirmation not available - full leave balance is applicable
         */

        if(DOJ_diff<=1&&DOC_year==0){
    //System.out.println("execute conditions 1");
            //execute conditions 1
            leaveType_Id.clear();
            leaveType.clear();
            leaveLimit.clear();
            balanceLimit.clear();
            try {

                con = connection.getSampleProperty();
                stmt = con.createStatement();

                rs = stmt.executeQuery("select lv.lv_type_id, lv.lv_type from leave_type lv, leavetype_limit ll where  lv.lv_type_id = ll.lv_type_id and ll.lv_applicable = 0 ");

                while(rs.next()){
                    leaveType_Id.add(rs.getString(1));
                    leaveType.add(rs.getString(2));
                    leaveLimit.add("0");
                    balanceLimit.add("0");
                }

                rs.close();
                stmt.close();
                con.close();
            }catch(SQLException sqle){
                sqle.printStackTrace();
            }

        }else if(DOJ_year<=currentYear&&DOC_year==currentYear) {
            //System.out.println("execute conditions 2 and 3");
            //execute conditions 2 and 3
            //first get the maximum limit for each leave as this will be used in calculating the balance

            plvb.collectLeaveBalance();
            leaveType_Id = plvb.getLvTypeID();
            leaveType = plvb.getLvType();
            leaveLimit = plvb.getLvLimit();

            CalculateLeaveBalance clb = new CalculateLeaveBalance();
            clb.setDateOfConfirm(date_of_confirm);
            clb.setCurrentYear(String.valueOf(currentYear));

            String calculatedLimit = "";

            for(int idx=0;idx<leaveType_Id.size();idx++) {
                //if the leave type is casual leave or sick leave then the balance need to be calculated
                if(leaveType_Id.get(idx).equals("1") || leaveType_Id.get(idx).equals("2")){
                    clb.setLeaveLimit(leaveLimit.get(idx).toString());
                    clb.calculateBal();
                    calculatedLimit = clb.getCalcBalance();
                }else {
                    calculatedLimit = String.valueOf(leaveLimit.get(idx));
                }
                if(intTwd>0){
                    if(leaveType_Id.get(idx).equals("3")){
                        //System.out.println("intTwd : "+intTwd);

                        calculatedLimit = String.valueOf(leaveLimit.get(idx));
                        /**if(modTwd>0 && currMon.equals("1")){
                            clb.setDateOfConfirm(date_of_joining);
                            calculatedLimit = Integer.parseInt(calculatedLimit)+clb.getCalcBalance();
                        }*/
                    }
                }else{
                        calculatedLimit = "0";
                }
                //System.out.println("calculatedLimit : "+calculatedLimit);

                balanceLimit.add(calculatedLimit);
                tempLeaveLimit.add(calculatedLimit);
            }
        }else if((DOJ_diff>=2&&DOC_year!=0)||(DOJ_diff>=2&&DOC_year==0)||(DOC_year==DOJ_year&&DOJ_diff==1)){
        //execute condition 4 and 5
            //System.out.println("execute conditions 4 and 5");

            plvb.collectLeaveBalance();
            leaveType_Id = plvb.getLvTypeID();
            leaveType = plvb.getLvType();
            leaveLimit = plvb.getLvLimit();
            balanceLimit = plvb.getLvLimit();

            //System.out.println("execute conditions 4 and 5 : leaveType_Id.contains(3) "+leaveType_Id.contains("3"));
            if(!leaveType_Id.contains("3")){
                if(intTwd>0){
                    plvb.setType("1");
                    plvb.collectLeaveBalance();
                    leaveType_Id = plvb.getLvTypeID();
                    leaveType = plvb.getLvType();
                    leaveLimit = plvb.getLvLimit();
                    balanceLimit = plvb.getLvLimit();
                }
            }
        }
//(DOC_year==DOJ_year&&DOJ_year==currentYear-1)//(DOJ_diff>2&&DOC_year==0)||//&&DOJ_diff==1)
        //System.out.println("leaveType_Id:"+leaveType_Id);
        //System.out.println("leaveType:"+leaveType);
        //System.out.println("leaveLimit:"+leaveLimit);
        //System.out.println("balanceLimit:"+balanceLimit);
        if(tempLeaveLimit.size()>0){
            leaveLimit.clear();
            leaveLimit = tempLeaveLimit;
        }
        //System.out.println("leaveLimit:"+leaveLimit);
        //System.out.println("tempLeaveLimit:"+tempLeaveLimit);

    }//close of calculateLEaveBal()

    public List getLeaveTypeId(){
        return leaveType_Id;
    }

    public List getLeaveType(){
        return leaveType;
    }

    public List getLeaveLimit(){
        return leaveLimit;
    }

    public List getBalanceLimit(){
        return balanceLimit;
    }
}