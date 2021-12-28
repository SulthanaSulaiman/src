/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.hr;

import java.sql.SQLException;
import java.util.*;
import connection.DBconnection;
import java.sql.*;

/**
 *
 * @author Parameshwarant
 */
public class SaveSupervisorAppraisalData {

    String superAppraisalStatus="";
    String cycleCode="";
    String superAppraisalID="";
    String superEmpID="";
    String selfAppFlag="";
    String quesID="";
    String remark="";
    String selfAppQustF="";
    String submitFlag="";
    String appraisedDate="";
    String saveOrModify="";
    String empEncryptKey="";
    String memberEmpID="";
    String empForSuperDept="";
    String empForSuperDesig="";

    String queryForGetApprsDate="";
    String superAppStartDate="";
    String superAppEndDate="";
    String cycleId="";

    List superAppraisalStatusList=new ArrayList();
    List cycleCodeList=new ArrayList();
    List superAppraisalIDList=new ArrayList();
    List appraisedDateList=new ArrayList();
    List quesIDList=new ArrayList();
    List remarkList=new ArrayList();
    List submitFlagList=new ArrayList();
    List memberEmpList=new ArrayList();
    //List submitFlagList

    List empForSuperNameList=new ArrayList();
    List empForSuperIDList=new ArrayList();
    List empTempId = new ArrayList();
    List empTempName = new ArrayList();
    List empForSuperDeptList=new ArrayList();
    List empForSuperDesigList=new ArrayList();

    List questionIDList = new ArrayList();
    List remarksList = new ArrayList();
    List selfAppQustnFlag = new ArrayList();

    int supervisorAppSavechk=0;
    int maxSupervisorAppraisalID=0;

    public void setSelfAppFlag(String selfAppFlag) {
        this.selfAppFlag = selfAppFlag;
    }

    public String getSelfAppFlag() {
        return selfAppFlag;
    }


    public void setSelfAppQustnFlag(List selfAppQustnFlag) {
        this.selfAppQustnFlag = selfAppQustnFlag;
    }

    public List getSelfAppQustnFlag() {
        return selfAppQustnFlag;
    }



     public void setSuperQuestionIDList(List questionIDList)
    {
        this.questionIDList=questionIDList;
    }

    public void setSuperRemarkList(List remarksList)
    {
        this.remarksList=remarksList;
    }

    public void setSuperQuesID(String quesID)
    {
        this.quesID=quesID;
    }
    public void setSuperRemarks(String remark)
    {
        this.remark=remark;
    }

    public void setSuperSaveOrModify(String saveOrModify)
    {
        this.saveOrModify=saveOrModify;
    }

    public void setSuperEmpEncryptKey(String empEncryptKey)
    {
        this.empEncryptKey=empEncryptKey;
    }



    public void setSuperApprisalStatus(String superAppraisalStatus)
    {
        this.superAppraisalStatus=superAppraisalStatus;
    }

    public String getSuperAppraisalStatus()
    {
        return superAppraisalStatus;
    }

    public void setSuperEmployeeID(String superEmpID)
    {
        this.superEmpID=superEmpID;
    }

    public void setMemberEmployeeID(String memberEmpID)
    {
        this.memberEmpID=memberEmpID;
    }

    public void setCycleCode(String cycleCode)
    {
        this.cycleCode=cycleCode;
    }

    public void setSuperSubmitFlag(String submitFlag)
    {
        this.submitFlag=submitFlag;
    }

    public List getSuperAppraisedDateList()
    {
        return appraisedDateList;
    }

    public List getSuperRemarkList()
    {
        return remarkList;
    }

    public List getSuperQuestionIDList()
    {
        return quesIDList;
    }

    public List getSuperAppraisalIDList()
    {
        return superAppraisalIDList;
    }

    public List getSuperAppraisalSubmitFlagList()
    {
        return submitFlagList;
    }

     public List getSuperAppraisalStatusFlagList()
    {
        return superAppraisalStatusList;
    }

      public List getSuperAppraisalMemberEmpList()
    {
        return memberEmpList;
    }
      
      
      public void setEmpForSuperDept(String empForSuperDept)
    {
          this.empForSuperDept=empForSuperDept;
      }
      public void setEmpForSuperDesig(String empForSuperDesig)
    {
          this.empForSuperDesig=empForSuperDesig;
      }

      public List getEmpForSuperNameList()
    {
          return empForSuperNameList;
    }
      public List getEmpForSuperIDList()
    {
          return empForSuperIDList;
    }

      public List getEmpForSuperDeptList()
    {
          return empForSuperDeptList;
    }
      public List getEmpForSuperDesigList()
    {
          return empForSuperDesigList;
    }
      
      public String getSuperAppStartDate()
    {
          return superAppStartDate;
    }
      
      public String getSuperAppEndDate()
    {
          return superAppEndDate;
    }
      
      public String getCycleID()
    {
          return cycleId;
    }
    public static String nl2br(String s) {
         s = s.replaceAll("\n", "<br>");
         //System.out.println("The string is"+s);
         return s;
    }

    public void superAppraisalDateVerify()
        {
        try
        {
            DBconnection connectToDB=new DBconnection();
            Connection con=connectToDB.getSampleProperty();
            Statement st=con.createStatement();
            queryForGetApprsDate="SELECT super_app_start_date,super_app_end_date,cycle_id FROM appraisal_cycle "
                    + "WHERE super_app_start_date<=(DATE_FORMAT(CURRENT_TIMESTAMP(),'%Y-%m-%d')) AND super_app_end_date>=(DATE_FORMAT(CURRENT_TIMESTAMP(),'%Y-%m-%d'))";
            ResultSet rs=st.executeQuery(queryForGetApprsDate);

            if(!rs.wasNull())
            while(rs.next())
            {
             superAppStartDate=rs.getString(1);
             superAppEndDate=rs.getString(2);
             cycleId=rs.getString(3);
            }
            }

        catch(SQLException e)
        {
            e.printStackTrace();
            System.out.println(e.toString());
        }
        }


    public void supervisorAppraisalCheck()
    {
        try
        {
            DBconnection connectToDB=new DBconnection();
            Connection con=connectToDB.getSampleProperty();
            Statement st=con.createStatement();
            String queryForSuperAppChk="SELECT supervisor_appraisal_id,member_emp_id, question_id,AES_DECRYPT(remark,'"+empEncryptKey+"'),entry_date,submit_flag,supervisor_appraisal_status FROM supervisor_appraisal_remark "
                + "WHERE cycle_code='"+cycleCode+"' AND supervisor_emp_id='"+superEmpID+"' AND member_emp_id='"+memberEmpID+"' AND qustionfromSelfApp ='"+selfAppFlag+"'";
System.out.println("queryForSuperAppChk"+queryForSuperAppChk);
            ResultSet rs=st.executeQuery(queryForSuperAppChk);
            while(rs.next())
            {
                 superAppraisalIDList.add(rs.getString(1));
                 memberEmpList.add(rs.getString(2));
                 quesIDList.add(rs.getString(3));
                 remarkList.add(rs.getString(4));
                 appraisedDateList.add(rs.getString(5));
                 submitFlagList.add(rs.getString(6));
                 superAppraisalStatusList.add(rs.getString(7));

            }

        }
        catch(SQLException e)
        {
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }

    public int saveSupervisorApparaisal()
    {
        try
        {
            DBconnection connectToDB=new DBconnection();
            Connection con=connectToDB.getSampleProperty();
            Statement st=con.createStatement();
           if(saveOrModify.equals("saveNew"))
               {
               String queryForGetMaxID="SELECT max(supervisor_appraisal_id) FROM supervisor_appraisal_remark";

               ResultSet rs=st.executeQuery(queryForGetMaxID);

            while(rs.next())
            {
             maxSupervisorAppraisalID=Integer.parseInt(rs.getString(1));

            }
                maxSupervisorAppraisalID=maxSupervisorAppraisalID+1;
System.out.println(questionIDList);
System.out.println(remarksList);
System.out.println(selfAppQustnFlag);
            for(int i=0;i<questionIDList.size();i++)
            {
                quesID=questionIDList.get(i).toString();
                remark=remarksList.get(i).toString();
                //selfAppQustnFlag
                selfAppQustF=selfAppQustnFlag.get(i).toString();
                
                String queryForSaveSuperApp="INSERT INTO supervisor_appraisal_remark(supervisor_appraisal_id,cycle_code,member_emp_id,supervisor_emp_id,question_id,remark,entry_date,submit_flag,supervisor_appraisal_status,qustionfromSelfApp)"
                    + "VALUES('"+maxSupervisorAppraisalID+"','"+cycleCode+"','"+memberEmpID+"','"+superEmpID+"','"+quesID+"',AES_ENCRYPT('"+remark+"','"+empEncryptKey+"'), CURRENT_TIMESTAMP(),'"+submitFlag+"','"+superAppraisalStatus+"','"+selfAppQustF+"')";
System.out.println("testtt"+queryForSaveSuperApp);
                supervisorAppSavechk=st.executeUpdate(queryForSaveSuperApp);


            }
                }   else    {
                System.out.println("quesID=questionIDList"+questionIDList.size());
                    System.out.println("quesID=remarksList"+remarksList.size());
                    System.out.println("quesID=selfAppQustnFlag"+selfAppQustnFlag.size());
                for(int i=0;i<questionIDList.size();i++)
                {

                quesID=questionIDList.get(i).toString();
                remark=remarksList.get(i).toString();
                selfAppQustF=selfAppQustnFlag.get(i).toString();
                String queryForUpdateSuperApp="UPDATE supervisor_appraisal_remark SET remark=AES_ENCRYPT('"+remark+"','"+empEncryptKey+"'),submit_flag='"+submitFlag+"',entry_date=CURRENT_TIMESTAMP()"
                        + " WHERE supervisor_emp_id='"+superEmpID+"' AND member_emp_id='"+memberEmpID+"' AND question_id='"+quesID+"' AND cycle_code='"+cycleCode+"' AND qustionfromSelfApp='"+selfAppQustF+"'";
                supervisorAppSavechk=st.executeUpdate(queryForUpdateSuperApp);
System.out.println("Inside updating: "+queryForUpdateSuperApp);
                }
                }
            }
        catch(SQLException e)
        {
            System.out.println(e.toString());
            e.printStackTrace();
        }
        return supervisorAppSavechk;
    }

    public void getEmployeeForSupervisor()
    {
        try
        {
            DBconnection connectToDB=new DBconnection();
            Connection con=connectToDB.getSampleProperty();
            Statement st=con.createStatement();
            String queryForEmpForSuper="SELECT emp_name,emp_id FROM USER WHERE STATUS=1 AND supervisor_id='"+superEmpID+"' AND dept_code='"+empForSuperDept+"' AND desig_code='"+empForSuperDesig+"'";

            ResultSet rs=st.executeQuery(queryForEmpForSuper);
            while(rs.next())
            {
                 empTempName.add(rs.getString(1));
                 empTempId.add(rs.getString(2));
            }
            for(int i=0; i<empTempId.size(); i++){
                String queryForAppraisal="SELECT * FROM supervisor_appraisal_remark WHERE cycle_code='"+cycleCode+"'  and member_emp_id='"+empTempId.get(i)+"'";
                ResultSet result = st.executeQuery(queryForAppraisal);
                if(!result.next()){
                    empForSuperNameList.add(empTempName.get(i));
                    empForSuperIDList.add(empTempId.get(i));
               }
              else{
                    empForSuperNameList.add(empTempName.get(i));
                    empForSuperIDList.add(empTempId.get(i));

 }
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.toString());
            e.printStackTrace();
        }
    }

}
