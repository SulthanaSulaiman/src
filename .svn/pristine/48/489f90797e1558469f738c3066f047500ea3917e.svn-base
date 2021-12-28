/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.hr;
import java.util.*;
import java.sql.*;
import connection.DBconnection;
/**
 *
 * @author pathfinder
 */
public class GetSalarydetailsfordisp {
private String slno="";
private String emp_code="";
private String pfno="";
private String esino="";
private String designation="";
private String monthSyear="";
private String name="";
private String doj="";
private String department="";
private String noofdays="";
private String dayspayable="";
private String lop="";
private String basic="";
private String hra="";
private String va="";
private String ta="";
private String lta="";
private String ma="";
private String actual_gross_salary="";
private String earned_basic="";
private String earned_hra="";
private String earnedva="";
private String earnedta="";
private String earnedlta="";
private String earnedma="";
private String gross_earnedsalary="";
private String arrears="";
private String bonus="";
private String att_bonus="";
private String overtime="";
private String misc="";
private String other_earnings_total="";
private String pf_ded="";
private String esi_ded="";
private String salary_adv="";
private String tds="";
private String proff_tax="";
private String lwf="";
private String other_deductions="";
private String total_ded="";
private String net_transfer="";
private String amount_in_words="";
private String email="";
private String clsl_balance="";
private String pl_balance="";
private String comp_off="";
private String remarks="";
private String uan_no="";
private String monthforsalarydisp="";
private String yearforsalarydisp="";
private ArrayList emp_codeL = new ArrayList();
public GetSalarydetailsfordisp() {}

    public void setEmp_codeL(ArrayList emp_codeL) {
        this.emp_codeL = emp_codeL;
    }

    public ArrayList getEmp_codeL() {
        return emp_codeL;
    }
    

public void setYearforsalarydisp(String yearforsalarydisp) {
        this.yearforsalarydisp = yearforsalarydisp;
    }

    public String getMonthforsalarydisp() {
        return monthforsalarydisp;
    }

    public String getYearforsalarydisp() {
        return yearforsalarydisp;
    }

    public void setMonthforsalarydisp(String monthforsalarydisp) {
        this.monthforsalarydisp = monthforsalarydisp;
    }

String month="";
String year="";

    public void setMonth(String month) {
        this.month = month;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }

    public String getActual_gross_salary() {
        return actual_gross_salary;
    }

    public String getAmount_in_words() {
        return amount_in_words;
    }

    public String getArrears() {
        return arrears;
    }

    public String getAtt_bonus() {
        return att_bonus;
    }

    public String getBasic() {
        return basic;
    }

    public String getBonus() {
        return bonus;
    }

    public String getClsl_balance() {
        return clsl_balance;
    }

    public String getComp_off() {
        return comp_off;
    }

    public String getDayspayable() {
        return dayspayable;
    }

    public String getDepartment() {
        return department;
    }

    public String getDesignation() {
        return designation;
    }

    public String getDoj() {
        return doj;
    }

    public String getEarned_basic() {
        return earned_basic;
    }

    public String getEarned_hra() {
        return earned_hra;
    }

    public String getEarnedlta() {
        return earnedlta;
    }

    public String getEarnedma() {
        return earnedma;
    }

    public String getEarnedta() {
        return earnedta;
    }

    public String getEarnedva() {
        return earnedva;
    }

    public String getEmail() {
        return email;
    }

    public String getEmp_code() {
        return emp_code;
    }

    public String getEsi_ded() {
        return esi_ded;
    }

    public String getGross_earnedsalary() {
        return gross_earnedsalary;
    }

    public String getHra() {
        return hra;
    }

    public String getLop() {
        return lop;
    }

    public String getLta() {
        return lta;
    }

    public String getLwf() {
        return lwf;
    }

    public String getMa() {
        return ma;
    }

    public String getMisc() {
        return misc;
    }

    public String getMonthSyear() {
        return monthSyear;
    }

    public String getName() {
        return name;
    }

    public String getNet_transfer() {
        return net_transfer;
    }

    public String getNoofdays() {
        return noofdays;
    }

    public String getOther_deductions() {
        return other_deductions;
    }

    public String getOther_earnings_total() {
        return other_earnings_total;
    }

    public String getOvertime() {
        return overtime;
    }

    public String getPf_ded() {
        return pf_ded;
    }

    public String getPl_balance() {
        return pl_balance;
    }

    public String getProff_tax() {
        return proff_tax;
    }

    public String getRemarks() {
        return remarks;
    }

    public String getSalary_adv() {
        return salary_adv;
    }

    public String getSlno() {
        return slno;
    }

    public String getTa() {
        return ta;
    }

    public String getTds() {
        return tds;
    }

    public String getTotal_ded() {
        return total_ded;
    }

    public String getUan_no() {
        return uan_no;
    }

    public String getVa() {
        return va;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public void setActual_gross_salary(String actual_gross_salary) {
        this.actual_gross_salary = actual_gross_salary;
    }

    public void setAmount_in_words(String amount_in_words) {
        this.amount_in_words = amount_in_words;
    }

    public void setArrears(String arrears) {
        this.arrears = arrears;
    }

    public void setAtt_bonus(String att_bonus) {
        this.att_bonus = att_bonus;
    }

    public void setBasic(String basic) {
        this.basic = basic;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    public void setClsl_balance(String clsl_balance) {
        this.clsl_balance = clsl_balance;
    }

    public void setComp_off(String comp_off) {
        this.comp_off = comp_off;
    }

    public void setDayspayable(String dayspayable) {
        this.dayspayable = dayspayable;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setDoj(String doj) {
        this.doj = doj;
    }

    public void setEarned_basic(String earned_basic) {
        this.earned_basic = earned_basic;
    }

    public void setEarned_hra(String earned_hra) {
        this.earned_hra = earned_hra;
    }

    public void setEarnedlta(String earnedlta) {
        this.earnedlta = earnedlta;
    }

    public void setEarnedma(String earnedma) {
        this.earnedma = earnedma;
    }

    public void setEarnedta(String earnedta) {
        this.earnedta = earnedta;
    }

    public void setEarnedva(String earnedva) {
        this.earnedva = earnedva;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEmp_code(String emp_code) {
        this.emp_code = emp_code;
    }

    public void setEsi_ded(String esi_ded) {
        this.esi_ded = esi_ded;
    }

    public void setGross_earnedsalary(String gross_earnedsalary) {
        this.gross_earnedsalary = gross_earnedsalary;
    }

    public void setHra(String hra) {
        this.hra = hra;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    public void setLta(String lta) {
        this.lta = lta;
    }

    public void setLwf(String lwf) {
        this.lwf = lwf;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public void setMisc(String misc) {
        this.misc = misc;
    }

    public void setMonthSyear(String monthSyear) {
        this.monthSyear = monthSyear;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNet_transfer(String net_transfer) {
        this.net_transfer = net_transfer;
    }

    public void setNoofdays(String noofdays) {
        this.noofdays = noofdays;
    }

    public void setOther_deductions(String other_deductions) {
        this.other_deductions = other_deductions;
    }

    public void setOther_earnings_total(String other_earnings_total) {
        this.other_earnings_total = other_earnings_total;
    }

    public void setOvertime(String overtime) {
        this.overtime = overtime;
    }

    public void setPf_ded(String pf_ded) {
        this.pf_ded = pf_ded;
    }

    public void setPl_balance(String pl_balance) {
        this.pl_balance = pl_balance;
    }

    public void setProff_tax(String proff_tax) {
        this.proff_tax = proff_tax;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public void setSalary_adv(String salary_adv) {
        this.salary_adv = salary_adv;
    }

    public void setSlno(String slno) {
        this.slno = slno;
    }

    public void setTa(String ta) {
        this.ta = ta;
    }

    public void setTds(String tds) {
        this.tds = tds;
    }

    public void setTotal_ded(String total_ded) {
        this.total_ded = total_ded;
    }

    public void setUan_no(String uan_no) {
        this.uan_no = uan_no;
    }

    public void setVa(String va) {
        this.va = va;
    }

    public void setEsi(String esino) {
        this.esino = esino;
    }

    public void setPfno(String pfno) {
        this.pfno = pfno;
    }

    public String getEsi() {
        return esino;
    }

    public String getPfno() {
        return pfno;
    }
public void getsalarydetails(){
DBconnection dbcon = new DBconnection();
Connection con = null;
PreparedStatement statement = null;
con =dbcon.getSampleProperty();
ResultSet rs = null;
String saveQry ="SELECT empcode,designation,monthYear,name,doj,department,noofdays,dayspayable,lop,basic,hra,va,lta,ta,ma,actualgrosssalary,earnedbasic,earnedhra,"+
       "earnedva,earnedta,earnedlta,earnedma,earnedgrosss,arrears,bonus,atbonus,overtime,misc,otherearningtotal,pfded,esided,salaryadva,tds,profftax,lwf,otherded,total,nettransfer,"+
       "amountinwords,email,clslblance,plblance,compoff,remarks,uanno,month,year,pfno,esino from salarydetailsinsert where month=? and year=? and empcode=?";
try{
    //System.out.println("monthforsalarydisp"+monthforsalarydisp);
   // System.out.println("year"+yearforsalarydisp);
   // System.out.println("emp"+saveQry);
statement = con.prepareStatement(saveQry);
statement.setString(1, monthforsalarydisp);
statement.setString(2, yearforsalarydisp);
statement.setString(3, emp_code);
rs = statement.executeQuery();
while (rs.next()) {
emp_code=rs.getString(1);
designation=rs.getString(2);
monthSyear=rs.getString(3);
name=rs.getString(4);
System.out.println("names"+name);
doj=rs.getString(5);
department=rs.getString(6);
noofdays=rs.getString(7);
dayspayable=rs.getString(8);
lop=rs.getString(9);
basic=rs.getString(10);
hra=rs.getString(11);
va=rs.getString(12);
ta=rs.getString(13);
lta=rs.getString(14);
ma=rs.getString(15);
actual_gross_salary=rs.getString(16);
earned_basic=rs.getString(17);
earned_hra=rs.getString(18);
earnedva=rs.getString(19);
earnedta=rs.getString(20);
earnedlta=rs.getString(21);
earnedma=rs.getString(22);
gross_earnedsalary=rs.getString(23);
arrears=rs.getString(24);
bonus=rs.getString(25);
att_bonus=rs.getString(26);
overtime=rs.getString(27);
misc=rs.getString(28);
other_earnings_total=rs.getString(29);
pf_ded=rs.getString(30);
esi_ded=rs.getString(31);
salary_adv=rs.getString(32);
tds=rs.getString(33);
proff_tax=rs.getString(34);
lwf=rs.getString(35);
other_deductions=rs.getString(36);
total_ded=rs.getString(37);
net_transfer=rs.getString(38);
amount_in_words=rs.getString(39);
email=rs.getString(40);
System.out.println("emp_codeL"+email);
clsl_balance=rs.getString(41);
pl_balance=rs.getString(42);
comp_off=rs.getString(43);
remarks=rs.getString(44);
uan_no=rs.getString(45);
monthforsalarydisp=rs.getString(46);
yearforsalarydisp=rs.getString(47);
pfno=rs.getString(48);
esino=rs.getString(49);
}

}
catch(SQLException e){
    e.printStackTrace();
}
finally {

            //Close resultset
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException se) {
                    System.out.println("Exception while closing resultset:se.getMessage()");
                }

            }

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
}
public void getsalarydetailsForchk(){
DBconnection dbcon = new DBconnection();
Connection con = null;
PreparedStatement statement = null;
ResultSet rs = null;
con =dbcon.getSampleProperty();
String saveQry ="SELECT empcode,designation,monthYear,name,doj,department,noofdays,dayspayable,lop,basic,hra,va,lta,ta,ma,actualgrosssalary,earnedbasic,earnedhra,"+
       "earnedva,earnedta,earnedlta,earnedma,earnedgrosss,arrears,bonus,atbonus,overtime,misc,otherearningtotal,pfded,esided,salaryadva,tds,profftax,lwf,otherded,total,nettransfer,"+
       "amountinwords,email,clslblance,plblance,compoff,remarks,uanno,month,year,pfno,esino from salarydetailsinsert where month=? and year=?";
try{
statement = con.prepareStatement(saveQry);
statement.setString(1, monthforsalarydisp);
statement.setString(2, yearforsalarydisp);
rs = statement.executeQuery();
while (rs.next()) {
emp_codeL.add(rs.getString(1));
}
System.out.println("emp_codeL"+monthforsalarydisp);
}
catch(SQLException e){
    e.printStackTrace();
}


finally {

            //Close resultset
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException se) {
                    System.out.println("Exception while closing resultset:se.getMessage()");
                }

            }

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
}
public void deleteSalaryifalreadyexist(String empcode,String month,String year){
DBconnection dbcon = new DBconnection();
Connection con = null;
PreparedStatement statement = null;
con =dbcon.getSampleProperty();
String saveQry ="DELETE FROM salarydetailsinsert WHERE empcode=? and month=? and year=?";
try{
statement = con.prepareStatement(saveQry);
statement.setString(1, empcode);
statement.setString(2, month);
statement.setString(3, year);
statement.execute();
System.out.println("deleted");

}
catch(SQLException e){
    e.printStackTrace();
}


finally {

          
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
}
}
