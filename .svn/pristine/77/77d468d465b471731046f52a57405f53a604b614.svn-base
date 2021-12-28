package pathfinder.functions;
import java.util.Calendar;
import java.sql.*;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;
import java.util.ArrayList;
import java.util.Calendar;
public class Birthday {
    List EmployeeName = new ArrayList();
    List Employees = new ArrayList();
    List deptmnt = new ArrayList();
    List empId  = new ArrayList();
    int month;
    int date;
    int year;
    private List empNameYearsOfExp = new ArrayList();
    private List empIdYearsOfExp = new ArrayList();
    private List deptYearsOfExp = new ArrayList();
    private List NoYearsOfExp = new ArrayList();
    Calendar now = Calendar.getInstance();
    int currentYear = now.get(Calendar.YEAR);

    public void DateAndMonth(){
    Calendar now = Calendar.getInstance();
    month=now.get(Calendar.MONTH);
    date=now.get(Calendar.DATE);
    year=now.get(Calendar.YEAR);

    }

    public void setempId(List empId) {
        this.empId = empId;
    }

    public List getempId() {
        return empId;
    }

public List EmlpoyeeDateBirth(){
    return EmployeeName;
        }
public List departmnt(){
        return deptmnt;
    }
public List EmlpoyeesName(){
    return Employees;
    }

    public void setEmpIdYearsOfExp(List empIdYearsOfExp) {
        this.empIdYearsOfExp = empIdYearsOfExp;
    }

    public void setDeptYearsOfExp(List deptYearsOfExp) {
        this.deptYearsOfExp = deptYearsOfExp;
    }

    public void setEmpNameYearsOfExp(List empNameYearsOfExp) {
        this.empNameYearsOfExp = empNameYearsOfExp;
    }

    public void setNoYearsOfExp(List NoYearsOfExp) {
        this.NoYearsOfExp = NoYearsOfExp;
    }

    public List getDeptYearsOfExp() {
        return deptYearsOfExp;
    }

    public List getEmpIdYearsOfExp() {
        return empIdYearsOfExp;
    }

    public List getNoYearsOfExp() {
        return NoYearsOfExp;
    }

    public List getEmpNameYearsOfExp() {
        return empNameYearsOfExp;
    }
    

 public void SqlDateAndMonth(){
    
    month = month+1;
    String ConvrtTwodigitmnth="";
     String ConvrtTwodigitdat="";
   if (month <='9')
   {
      // DecimalFormat formatter = new DecimalFormat("00");
      //System.out.println(new DecimalFormat("00").format(monthFrmdb));
       ConvrtTwodigitmnth=String.format("%02d", month);
       ConvrtTwodigitdat=String.format("%02d", date);
      //System.out.println(String.format(ConvrtTwodigitmnth));
      //System.out.println(String.format(ConvrtTwodigitdat));
    }
   int dateFrmdb = date;
      String sql="";
    try {
        connection.DBconnection conToDB = new connection.DBconnection();
           // DBconnection conToDB = new DBconnection();
            Connection con = conToDB.getSampleProperty();
            Statement st = con.createStatement();
            ResultSet rs;
            sql = "select u.emp_name,d.departmentFordisplay,u.emp_id"
                    + " from user u, department d"
                    //+ " where dateofbirth like '%"+teststng+"-"+testst+"%' and status='1'";
                    + " where dateofbirth like '%"+ConvrtTwodigitmnth+"-"+ConvrtTwodigitdat+"' and status='1' and u.dept_code=d.dept_code";
             //
            rs = st.executeQuery(sql);
            while (rs.next()) {
               // System.out.println(rs.getString(1));
                //System.out.println(rs.getString(2));
                EmployeeName.add(rs.getString(1));
                Employees.add(rs.getString(1));
                deptmnt.add(rs.getString(2));
                empId.add(rs.getString(3));
                NoYearsOfExp.add("N/A");
                }
            String sql1 = "select u.emp_name,d.departmentFordisplay,u.emp_id,u.doj"
                    + " from user u, department d"
                    //+ " where dateofbirth like '%"+teststng+"-"+testst+"%' and status='1'";
                    + " where u.doj like '%"+ConvrtTwodigitmnth+"-"+ConvrtTwodigitdat+"' and u.status='1' and u.dept_code=d.dept_code";
            rs = st.executeQuery(sql1);
            //System.out.println(sql1);
            while (rs.next()) {
               // System.out.println(rs.getString(1));
                //System.out.println(rs.getString(2));
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date parse = sdf.parse(rs.getString(4));
                Calendar c = Calendar.getInstance();
                c.setTime(parse);
                if((currentYear-c.get(Calendar.YEAR))>0){
                EmployeeName.add(rs.getString(1));
                deptmnt.add(rs.getString(2));
                empId.add(rs.getString(3));
                NoYearsOfExp.add((currentYear-c.get(Calendar.YEAR)));
                }}
             rs.close();
             st.close();
            }
        catch (Exception e) {
            System.out.println("The Exception in dateofbirth" + e);
        }
      //Iterator itr=EmployeeName.iterator();//getting Iterator from arraylist to traverse elements
 // while(itr.hasNext()){
 //  System.out.println(itr.next());
  //}
//     for (int j=0; j<EmployeeName.size(); j++)
//      {
//      System.out.println(EmployeeName.get(j));
//     }
 
 }
 //public static void main(String[] args) {
   //Testing nameWithdob = new Testing();
   // nameWithdob.DateAndMonth();
    //nameWithdob.SqlDateAndMonth();
   // List nameDOB = new ArrayList();
   // nameDOB =nameWithdob.EmlpoyeeDateBirth();
  //}
 public List getCurrentopenings(){
     List openingsJob = new ArrayList();
 try {
        connection.DBconnection conToDB = new connection.DBconnection();
           // DBconnection conToDB = new DBconnection();
            Connection con = conToDB.getSampleProperty();
            Statement st = con.createStatement();
            ResultSet rs;
            String sql1 = "select Openings_for"
                    + " from job_openings where status='1'";
             //
            rs = st.executeQuery(sql1);
            while (rs.next()) {
               // System.out.println(rs.getString(1));
                //System.out.println(rs.getString(2));
                openingsJob.add(rs.getString(1));
               }
             rs.close();
        st.close();
            }
        catch (Exception e) {
            System.out.println("The Exception in dateofbirth" + e);
        }
 return openingsJob;
 }
}