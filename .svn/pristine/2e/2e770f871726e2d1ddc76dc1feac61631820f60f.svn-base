/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.hr;
import java.util.ArrayList;
import connection.DBconnection;
import java.util.*;
import java.sql.*;
/**
 *
 * @author pathfinder
 */
public class SalarydetailsDAO {
ArrayList slno=new ArrayList();
ArrayList emp_code=new ArrayList();
ArrayList pfno=new ArrayList();
ArrayList esino=new ArrayList();
ArrayList designation=new ArrayList();
ArrayList monthSyear=new ArrayList();
ArrayList name=new ArrayList();
ArrayList doj=new ArrayList();
ArrayList department=new ArrayList();
ArrayList noofdays=new ArrayList();
ArrayList dayspayable=new ArrayList();
ArrayList lop=new ArrayList();
ArrayList basic=new ArrayList();
ArrayList hra=new ArrayList();
ArrayList va=new ArrayList();
ArrayList ta=new ArrayList();
ArrayList lta=new ArrayList();
ArrayList ma=new ArrayList();
ArrayList actual_gross_salary=new ArrayList();
ArrayList earned_basic=new ArrayList();
ArrayList earned_hra=new ArrayList();
ArrayList earnedva=new ArrayList();
ArrayList earnedta=new ArrayList();
ArrayList earnedlta=new ArrayList();
ArrayList earnedma=new ArrayList();
ArrayList gross_earnedsalary=new ArrayList();
ArrayList arrears=new ArrayList();
ArrayList bonus=new ArrayList();
ArrayList att_bonus=new ArrayList();
ArrayList overtime=new ArrayList();
ArrayList misc=new ArrayList();
ArrayList other_earnings_total=new ArrayList();
ArrayList pf_ded=new ArrayList();
ArrayList esi_ded=new ArrayList();
ArrayList salary_adv=new ArrayList();
ArrayList tds=new ArrayList();
ArrayList proff_tax=new ArrayList();
ArrayList lwf=new ArrayList();
ArrayList other_deductions=new ArrayList();
ArrayList total_ded=new ArrayList();
ArrayList net_transfer=new ArrayList();
ArrayList amount_in_words=new ArrayList();
ArrayList email=new ArrayList();
ArrayList clsl_balance=new ArrayList();
ArrayList pl_balance=new ArrayList();
ArrayList comp_off=new ArrayList();
ArrayList remarks=new ArrayList();
ArrayList uan_no=new ArrayList();
ArrayList monthforsalarydisp=new ArrayList();
ArrayList yearforsalarydisp=new ArrayList();
ArrayList clavailableForUpdate=new ArrayList();
ArrayList plavailableForUpdate=new ArrayList();
String month="";
String year="";
    //SalarydetailsVO s1 = new SalarydetailsVO();
public int insertsalarydetails(SalarydetailsVO sdvo){
emp_code=sdvo.getEmp_code();
pfno=sdvo.getPfno();
esino=sdvo.getEsi();
designation=sdvo.getDesignation();
monthSyear=sdvo.getMonthSyear();
name=sdvo.getName();
doj=sdvo.getDoj();
department=sdvo.getDepartment();
noofdays=sdvo.getNoofdays();
dayspayable=sdvo.getDayspayable();
lop=sdvo.getLop();
basic=sdvo.getBasic();
hra=sdvo.getHra();
va=sdvo.getVa();
ta=sdvo.getTa();
lta=sdvo.getLta();
ma=sdvo.getMa();
actual_gross_salary=sdvo.getActual_gross_salary();
earned_basic=sdvo.getEarned_basic();
earned_hra=sdvo.getEarned_hra();
earnedva=sdvo.getEarnedva();
earnedta=sdvo.getEarnedta();
earnedlta=sdvo.getEarnedlta();
earnedma=sdvo.getEarnedma();
gross_earnedsalary=sdvo.getGross_earnedsalary();
arrears=sdvo.getArrears();
bonus=sdvo.getBonus();
att_bonus=sdvo.getAtt_bonus();
overtime=sdvo.getOvertime();
misc=sdvo.getMisc();
other_earnings_total=sdvo.getOther_earnings_total();
pf_ded=sdvo.getPf_ded();
esi_ded=sdvo.getEsi_ded();
salary_adv=sdvo.getSalary_adv();
tds=sdvo.getTds();
proff_tax=sdvo.getProff_tax();
lwf=sdvo.getLwf();
other_deductions=sdvo.getOther_deductions();
total_ded=sdvo.getTotal_ded();
net_transfer=sdvo.getNet_transfer();
amount_in_words=sdvo.getAmount_in_words();
email=sdvo.getEmail();
clsl_balance=sdvo.getClsl_balance();
pl_balance=sdvo.getPl_balance();
comp_off=sdvo.getComp_off();
remarks=sdvo.getRemarks();
uan_no=sdvo.getUan_no();
month=sdvo.getMonth();
year=sdvo.getYear();

DBconnection dbcon = new DBconnection();
Connection con = null;
PreparedStatement statement = null;
con =dbcon.getSampleProperty();
String saveQry ="INSERT INTO salarydetailsinsert(empcode,designation,monthYear,name,doj,department,noofdays,dayspayable,lop,basic,hra,va,ta,lta,ma,actualgrosssalary,earnedbasic,earnedhra,"+
       "earnedva,earnedta,earnedlta,earnedma,earnedgrosss,arrears,bonus,atbonus,overtime,misc,otherearningtotal,pfded,esided,salaryadva,tds,profftax,lwf,otherded,total,nettransfer,"+
       "amountinwords,email,clslblance,plblance,compoff,remarks,uanno,month,year,pfno,esino) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

for (int i=0;i<emp_code.size();i++){
try{
statement = con.prepareStatement(saveQry);
statement.setString(1, emp_code.get(i).toString());
statement.setString(2, designation.get(i).toString());
statement.setString(3, monthSyear.get(i).toString());
statement.setString(4, name.get(i).toString());
statement.setString(5, doj.get(i).toString());
statement.setString(6, department.get(i).toString());
statement.setString(7, noofdays.get(i).toString());
statement.setString(8, dayspayable.get(i).toString());
statement.setString(9, lop.get(i).toString());
statement.setString(10, basic.get(i).toString());
statement.setString(11, hra.get(i).toString());
statement.setString(12, va.get(i).toString());
statement.setString(13, ta.get(i).toString());
statement.setString(14, lta.get(i).toString());
statement.setString(15, ma.get(i).toString());
statement.setString(16, actual_gross_salary.get(i).toString());
statement.setString(17, earned_basic.get(i).toString());
statement.setString(18, earned_hra.get(i).toString());
statement.setString(19, earnedva.get(i).toString());
statement.setString(20, earnedta.get(i).toString());
statement.setString(21, earnedlta.get(i).toString());
statement.setString(22, earnedma.get(i).toString());
statement.setString(23, gross_earnedsalary.get(i).toString());
statement.setString(24, arrears.get(i).toString());
statement.setString(25, bonus.get(i).toString());
statement.setString(26, att_bonus.get(i).toString());
statement.setString(27, overtime.get(i).toString());
statement.setString(28, misc.get(i).toString());
statement.setString(29, other_earnings_total.get(i).toString());
statement.setString(30, pf_ded.get(i).toString());
statement.setString(31, esi_ded.get(i).toString());
statement.setString(32, salary_adv.get(i).toString());
statement.setString(33, tds.get(i).toString());
statement.setString(34, proff_tax.get(i).toString());
statement.setString(35, lwf.get(i).toString());
statement.setString(36, other_deductions.get(i).toString());
statement.setString(37, total_ded.get(i).toString());
statement.setString(38, net_transfer.get(i).toString());
statement.setString(39, amount_in_words.get(i).toString());
statement.setString(40, email.get(i).toString());
statement.setString(41, clsl_balance.get(i).toString());
statement.setString(42, pl_balance.get(i).toString());
statement.setString(43, comp_off.get(i).toString());
statement.setString(44, remarks.get(i).toString());
statement.setString(45, uan_no.get(i).toString());
statement.setString(46, month);
statement.setString(47, year);
statement.setString(48, pfno.get(i).toString());
statement.setString(49, esino.get(i).toString());
statement.executeUpdate();
}
catch(SQLException sql){
    sql.printStackTrace();
}
}
return 1;
}
public int UpdateLeavebalanceFromExcel(SalarydetailsVO sdvo){
    emp_code=sdvo.getEmp_code();
    clavailableForUpdate=sdvo.getClavailableForUpdate();
    plavailableForUpdate=sdvo.getplavailableForUpdate();
    
    try{
DBconnection dbcon = new DBconnection();
Connection con = null;
con =dbcon.getSampleProperty();
    System.out.println("emp_code"+plavailableForUpdate.size());
    System.out.println("emp_code1"+clavailableForUpdate.size());
    System.out.println("emp_code2"+emp_code.size());
    for (int i=0;i<emp_code.size();i++){
        PreparedStatement ps = con.prepareStatement("Update emp_available_leave_details SET cl_available=?, pl_available=? where emp_id=?");
    
    
    ps.setString(1,clavailableForUpdate.get(i).toString());
    ps.setString(2,plavailableForUpdate.get(i).toString());
    ps.setString(3,emp_code.get(i).toString());
    ps.executeUpdate();
        }

    // call executeUpdate to execute our sql update statement
    
    }
    catch(SQLException sql){
    sql.printStackTrace();
}
    return 1;
}
}
