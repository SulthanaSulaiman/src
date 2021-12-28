/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.hr;
import java.util.ArrayList;
/**
 *
 * @author pathfinder
 */
public class SalarydetailsVO {
ArrayList slno=new ArrayList();
ArrayList emp_code=new ArrayList();
ArrayList pfno=new ArrayList();
ArrayList esi=new ArrayList();
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

    public void setplavailableForUpdate(ArrayList plavailableForUpdate) {
        this.plavailableForUpdate = plavailableForUpdate;
    }

    public void setClavailableForUpdate(ArrayList clavailableForUpdate) {
        this.clavailableForUpdate = clavailableForUpdate;
    }

    public ArrayList getClavailableForUpdate() {
        return clavailableForUpdate;
    }

    public ArrayList getplavailableForUpdate() {
        return plavailableForUpdate;
    }

  

    public void setYearforsalarydisp(ArrayList yearforsalarydisp) {
        this.yearforsalarydisp = yearforsalarydisp;
    }

    public ArrayList getMonthforsalarydisp() {
        return monthforsalarydisp;
    }

    public ArrayList getYearforsalarydisp() {
        return yearforsalarydisp;
    }

    public void setMonthforsalarydisp(ArrayList monthforsalarydisp) {
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
 
    public ArrayList getActual_gross_salary() {
        return actual_gross_salary;
    }

    public ArrayList getAmount_in_words() {
        return amount_in_words;
    }

    public ArrayList getArrears() {
        return arrears;
    }

    public ArrayList getAtt_bonus() {
        return att_bonus;
    }

    public ArrayList getBasic() {
        return basic;
    }

    public ArrayList getBonus() {
        return bonus;
    }

    public ArrayList getClsl_balance() {
        return clsl_balance;
    }

    public ArrayList getComp_off() {
        return comp_off;
    }

    public ArrayList getDayspayable() {
        return dayspayable;
    }

    public ArrayList getDepartment() {
        return department;
    }

    public ArrayList getDesignation() {
        return designation;
    }

    public ArrayList getDoj() {
        return doj;
    }

    public ArrayList getEarned_basic() {
        return earned_basic;
    }

    public ArrayList getEarned_hra() {
        return earned_hra;
    }

    public ArrayList getEarnedlta() {
        return earnedlta;
    }

    public ArrayList getEarnedma() {
        return earnedma;
    }

    public ArrayList getEarnedta() {
        return earnedta;
    }

    public ArrayList getEarnedva() {
        return earnedva;
    }

    public ArrayList getEmail() {
        return email;
    }

    public ArrayList getEmp_code() {
        return emp_code;
    }

    public ArrayList getEsi_ded() {
        return esi_ded;
    }

    public ArrayList getGross_earnedsalary() {
        return gross_earnedsalary;
    }

    public ArrayList getHra() {
        return hra;
    }

    public ArrayList getLop() {
        return lop;
    }

    public ArrayList getLta() {
        return lta;
    }

    public ArrayList getLwf() {
        return lwf;
    }

    public ArrayList getMa() {
        return ma;
    }

    public ArrayList getMisc() {
        return misc;
    }

    public ArrayList getMonthSyear() {
        return monthSyear;
    }

    public ArrayList getName() {
        return name;
    }

    public ArrayList getNet_transfer() {
        return net_transfer;
    }

    public ArrayList getNoofdays() {
        return noofdays;
    }

    public ArrayList getOther_deductions() {
        return other_deductions;
    }

    public ArrayList getOther_earnings_total() {
        return other_earnings_total;
    }

    public ArrayList getOvertime() {
        return overtime;
    }

    public ArrayList getPf_ded() {
        return pf_ded;
    }

    public ArrayList getPl_balance() {
        return pl_balance;
    }

    public ArrayList getProff_tax() {
        return proff_tax;
    }

    public ArrayList getRemarks() {
        return remarks;
    }

    public ArrayList getSalary_adv() {
        return salary_adv;
    }

    public ArrayList getSlno() {
        return slno;
    }

    public ArrayList getTa() {
        return ta;
    }

    public ArrayList getTds() {
        return tds;
    }

    public ArrayList getTotal_ded() {
        return total_ded;
    }

    public ArrayList getUan_no() {
        return uan_no;
    }

    public ArrayList getVa() {
        return va;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public void setActual_gross_salary(ArrayList actual_gross_salary) {
        this.actual_gross_salary = actual_gross_salary;
    }

    public void setAmount_in_words(ArrayList amount_in_words) {
        this.amount_in_words = amount_in_words;
    }

    public void setArrears(ArrayList arrears) {
        this.arrears = arrears;
    }

    public void setAtt_bonus(ArrayList att_bonus) {
        this.att_bonus = att_bonus;
    }

    public void setBasic(ArrayList basic) {
        this.basic = basic;
    }

    public void setBonus(ArrayList bonus) {
        this.bonus = bonus;
    }

    public void setClsl_balance(ArrayList clsl_balance) {
        this.clsl_balance = clsl_balance;
    }

    public void setComp_off(ArrayList comp_off) {
        this.comp_off = comp_off;
    }

    public void setDayspayable(ArrayList dayspayable) {
        this.dayspayable = dayspayable;
    }

    public void setDepartment(ArrayList department) {
        this.department = department;
    }

    public void setDesignation(ArrayList designation) {
        this.designation = designation;
    }

    public void setDoj(ArrayList doj) {
        this.doj = doj;
    }

    public void setEarned_basic(ArrayList earned_basic) {
        this.earned_basic = earned_basic;
    }

    public void setEarned_hra(ArrayList earned_hra) {
        this.earned_hra = earned_hra;
    }

    public void setEarnedlta(ArrayList earnedlta) {
        this.earnedlta = earnedlta;
    }

    public void setEarnedma(ArrayList earnedma) {
        this.earnedma = earnedma;
    }

    public void setEarnedta(ArrayList earnedta) {
        this.earnedta = earnedta;
    }

    public void setEarnedva(ArrayList earnedva) {
        this.earnedva = earnedva;
    }

    public void setEmail(ArrayList email) {
        this.email = email;
    }

    public void setEmp_code(ArrayList emp_code) {
        this.emp_code = emp_code;
    }

    public void setEsi_ded(ArrayList esi_ded) {
        this.esi_ded = esi_ded;
    }

    public void setGross_earnedsalary(ArrayList gross_earnedsalary) {
        this.gross_earnedsalary = gross_earnedsalary;
    }

    public void setHra(ArrayList hra) {
        this.hra = hra;
    }

    public void setLop(ArrayList lop) {
        this.lop = lop;
    }

    public void setLta(ArrayList lta) {
        this.lta = lta;
    }

    public void setLwf(ArrayList lwf) {
        this.lwf = lwf;
    }

    public void setMa(ArrayList ma) {
        this.ma = ma;
    }

    public void setMisc(ArrayList misc) {
        this.misc = misc;
    }

    public void setMonthSyear(ArrayList monthSyear) {
        this.monthSyear = monthSyear;
    }

    public void setName(ArrayList name) {
        this.name = name;
    }

    public void setNet_transfer(ArrayList net_transfer) {
        this.net_transfer = net_transfer;
    }

    public void setNoofdays(ArrayList noofdays) {
        this.noofdays = noofdays;
    }

    public void setOther_deductions(ArrayList other_deductions) {
        this.other_deductions = other_deductions;
    }

    public void setOther_earnings_total(ArrayList other_earnings_total) {
        this.other_earnings_total = other_earnings_total;
    }

    public void setOvertime(ArrayList overtime) {
        this.overtime = overtime;
    }

    public void setPf_ded(ArrayList pf_ded) {
        this.pf_ded = pf_ded;
    }

    public void setPl_balance(ArrayList pl_balance) {
        this.pl_balance = pl_balance;
    }

    public void setProff_tax(ArrayList proff_tax) {
        this.proff_tax = proff_tax;
    }

    public void setRemarks(ArrayList remarks) {
        this.remarks = remarks;
    }

    public void setSalary_adv(ArrayList salary_adv) {
        this.salary_adv = salary_adv;
    }

    public void setSlno(ArrayList slno) {
        this.slno = slno;
    }

    public void setTa(ArrayList ta) {
        this.ta = ta;
    }

    public void setTds(ArrayList tds) {
        this.tds = tds;
    }

    public void setTotal_ded(ArrayList total_ded) {
        this.total_ded = total_ded;
    }

    public void setUan_no(ArrayList uan_no) {
        this.uan_no = uan_no;
    }

    public void setVa(ArrayList va) {
        this.va = va;
    }

    public void setEsi(ArrayList esi) {
        this.esi = esi;
    }

    public void setPfno(ArrayList pfno) {
        this.pfno = pfno;
    }

    public ArrayList getEsi() {
        return esi;
    }

    public ArrayList getPfno() {
        return pfno;
    }


}
