/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.hr;
import java.sql.*;
import java.util.*;
import connection.DBconnection;
/**
 *
 * @author Raghuramanm
 */
public class AppraisalDAO {

    String appraisalYear="";
    String appraisalMonth="";
    String appraisalDesc="";
    String selfAppStart="";
    String selfAppEnd="";
    String supAppStart="";
    String supAppEnd="";
    String appAppStart="";
    String appAppEnd="";

    //List

    public void getCurrentAppraisalCycle(AppraisalVO appraisalVO){

        try{
            DBconnection conToDB = new DBconnection();
            Connection con = conToDB.getSampleProperty();
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT self_app_start_date,self_app_end_date,super_app_start_date,super_app_end_date,approval_app_start_date,"
                    + "approval_app_end_date,cycle_name FROM appraisal_cycle WHERE cycle_id = (SELECT MAX(cycle_id) FROM appraisal_cycle)");
            while (rs.next()) {
                selfAppStart = rs.getString(1);
                selfAppEnd = rs.getString(2);
                supAppStart = rs.getString(3);
                supAppEnd = rs.getString(4);
                appAppStart = rs.getString(5);
                appAppEnd = rs.getString(6);
                appraisalMonth = rs.getString(7);
            }
            appraisalVO.setSelfAppStart(selfAppStart);
            appraisalVO.setSelfAppEnd(selfAppEnd);
            appraisalVO.setSupAppStart(supAppStart);
            appraisalVO.setSupAppEnd(supAppEnd);
            appraisalVO.setAppAppStart(appAppStart);
            appraisalVO.setAppAppEnd(appAppEnd);
            appraisalVO.setAppraisalMonth(appraisalMonth);
            
        }catch(Exception e){
                System.out.println("The Exception in getCurrentAppraisalCycle() of AppraisalDAO()"+e);
        }
    }

    public AppraisalVO addAppraisal(AppraisalVO appraisalVO){

        int saveVar=0;
        String sql="";
        try{
            DBconnection conToDB = new DBconnection();
            Connection con = conToDB.getSampleProperty();
            Statement st = con.createStatement();

            appraisalYear = appraisalVO.getAppraisalYear();
            appraisalMonth = appraisalVO.getAppraisalMonth();
            appraisalDesc = appraisalVO.getAppraisalDesc();
            selfAppStart = appraisalVO.getSelfAppStart();
            selfAppEnd = appraisalVO.getSelfAppEnd();
            supAppStart = appraisalVO.getSupAppStart();
            supAppEnd = appraisalVO.getSupAppEnd();
            appAppStart = appraisalVO.getAppAppStart();
            appAppEnd = appraisalVO.getAppAppEnd();

            sql = "insert into appraisal_cycle(cycle_year,cycle_name,description,self_app_start_date,self_app_end_date,super_app_start_date,"
                    +" super_app_end_date,approval_app_start_date,approval_app_end_date) values('"+appraisalYear+"', '"+appraisalMonth+"', '"+appraisalDesc+
                    "', '"+selfAppStart+"', '"+selfAppEnd+"', '"+supAppStart+"', '"+supAppEnd+"', '"+appAppStart+"', '"+appAppEnd+"')";

            //System.out.println("Querey" +sql);

            saveVar = st.executeUpdate(sql);
            appraisalVO.setSaveVar(saveVar);
        
        }catch(Exception e){
            System.out.println("The Exception in addAppraisal() of AppraisalDAO()"+e);
        }

        return appraisalVO;
    }

    public AppraisalVO updateAppraisal(AppraisalVO appraisalVO){

        int updateVar=0;
        String sql="";
        String cycleId="";
        try{
            DBconnection conToDB = new DBconnection();
            Connection con = conToDB.getSampleProperty();
            Statement st = con.createStatement();


            appraisalYear = appraisalVO.getAppraisalYear();
            appraisalMonth = appraisalVO.getAppraisalMonth();
            appraisalDesc = appraisalVO.getAppraisalDesc();
            selfAppStart = appraisalVO.getSelfAppStart();
            selfAppEnd = appraisalVO.getSelfAppEnd();
            supAppStart = appraisalVO.getSupAppStart();
            supAppEnd = appraisalVO.getSupAppEnd();
            appAppStart = appraisalVO.getAppAppStart();
            appAppEnd = appraisalVO.getAppAppEnd();

            ResultSet rs = st.executeQuery("SELECT MAX(cycle_id) FROM appraisal_cycle");
            while(rs.next()){
                cycleId=rs.getString(1);
            }
            sql = "update appraisal_cycle set self_app_start_date='"+selfAppStart+"', self_app_end_date='"+selfAppEnd+"', super_app_start_date='"+supAppStart+"', "
                    +" super_app_end_date='"+supAppEnd+"', approval_app_start_date='"+appAppStart+"', approval_app_end_date='"+appAppEnd+"' "
                    +" where cycle_id = "+cycleId+"";

            //System.out.println("Querey" +sql);

            updateVar = st.executeUpdate(sql);
            appraisalVO.setUpdateVar(updateVar);

        }catch(Exception e){
            System.out.println("The Exception in updateAppraisal() of AppraisalDAO()"+e);
        }

        return appraisalVO;
    }

    public AppraisalVO getAppraisalCycles(AppraisalVO appraisalVO){

        String sql="";
        List cycleIdList = new ArrayList();
        List cycleYearList = new ArrayList();
        List cycleMonthList = new ArrayList();
        List cycleDescList = new ArrayList();
        List selfStartList = new ArrayList();
        List selfEndList = new ArrayList();
        List supStartList = new ArrayList();
        List supEndList = new ArrayList();
        List appStartList = new ArrayList();
        List appEndList = new ArrayList();

        try{
            DBconnection conToDB = new DBconnection();
            Connection con = conToDB.getSampleProperty();
            Statement st = con.createStatement();

            sql = "SELECT cycle_year,cycle_name,description,self_app_start_date,self_app_end_date,super_app_start_date,super_app_end_date,approval_app_start_date,"
                    + "approval_app_end_date,cycle_id FROM appraisal_cycle";

            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                cycleYearList.add(rs.getString(1));
                cycleMonthList.add(rs.getString(2));
                cycleDescList.add(rs.getString(3));
                selfStartList.add(rs.getString(4));
                selfEndList.add(rs.getString(5));
                supStartList.add(rs.getString(6));
                supEndList.add(rs.getString(7));
                appStartList.add(rs.getString(8));
                appEndList.add(rs.getString(9));
                cycleIdList.add(rs.getString(10));
            }

            appraisalVO.setCycleYearList(cycleYearList);
            appraisalVO.setCycleMonthList(cycleMonthList);
            appraisalVO.setCycleDescList(cycleDescList);
            appraisalVO.setSelfStartList(selfStartList);
            appraisalVO.setSelfEndList(selfEndList);
            appraisalVO.setSupStartList(supStartList);
            appraisalVO.setSupEndList(supEndList);
            appraisalVO.setAppStartList(appStartList);
            appraisalVO.setAppEndList(appEndList);
            appraisalVO.setCycleIdList(cycleIdList);
            
        }catch(Exception e){
            System.out.println("The Exception in getAppraisalCycles() of AppraisalDAO()"+e);
        }
        return appraisalVO;
    }

    public AppraisalVO getappraisalName(AppraisalVO appraisalVO){
        
        String appYear="";
        String appMonth="";

        int tempYear;
        int tempNxtYear;
        String tempNextYear="";
        String newAppraisalYear="";
        String newAppraisalMonth="";
        try{

            appYear=appraisalVO.getAppraisalYear();
            appMonth=appraisalVO.getAppraisalMonth();

            tempYear=Integer.parseInt(appYear);
            tempNxtYear = tempYear+1;
            tempNextYear = Integer.toString(tempNxtYear);
            newAppraisalYear = tempYear+"-"+tempNextYear;

            if(appMonth.equals("January")){
                newAppraisalMonth = appMonth+"-"+tempNextYear;
            }else if(appMonth.equals("February")){
                newAppraisalMonth = appMonth+"-"+tempNextYear;
            }else if(appMonth.equals("March")){
                newAppraisalMonth = appMonth+"-"+tempNextYear;
            }else{
                newAppraisalMonth = appMonth+"-"+appYear;
            }

            appraisalVO.setAppraisalYear(newAppraisalYear);
            appraisalVO.setAppraisalMonth(newAppraisalMonth);
        }catch(Exception e){
            System.out.println("Exception in getappraisalName() of AppraisalDAO()"+e);
        }
        return appraisalVO;
    }
}
