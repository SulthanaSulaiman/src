/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.hr;

import connection.DBconnection;
import java.io.Serializable;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Parameshwarant
 */
public class SaveAppraisalData {

    String appraisalStatus="";
    String cycleCode="";
    String seflAppraisalID="";
    String empID="";
    String quesID="";
    String remark="";
    String submitFlag="";
    String appraisedDate="";
    String saveOrModify="";
    String empEncryptKey="";
    int selfAppraisalSaveStatus1;
    List appraisalStatusList=new ArrayList();
    List cycleCodeList=new ArrayList();
    List seflAppraisalIDList=new ArrayList();
    List appraisedDateList=new ArrayList();
    List quesIDList=new ArrayList();
    List remarkList=new ArrayList();
    List submitFlagList=new ArrayList();

    List questionIDList = new ArrayList();
    List remarksList = new ArrayList();

    int selfAppraisalID;

    public void setSelfAppraisalSaveStatus1(int selfAppraisalSaveStatus1) {
        this.selfAppraisalSaveStatus1 = selfAppraisalSaveStatus1;
    }

    public int getSelfAppraisalSaveStatus1() {
        return selfAppraisalSaveStatus1;
    }

     

    public void setQuestionIDList(List questionIDList)
    {
        this.questionIDList=questionIDList;
    }

    public void setRemarkList(List remarksList)
    {
        this.remarksList=remarksList;
    }

    public void setQuesID(String quesID)
    {
        this.quesID=quesID;
    }
    public void setRemarks(String remark)
    {
        this.remark=remark;
    }

    public void setSaveOrModify(String saveOrModify)
    {
        this.saveOrModify=saveOrModify;
    }

    public void setEmpEncryptKey(String empEncryptKey)
    {
        this.empEncryptKey=empEncryptKey;
    }



    public void setApprisalStatus(String apprisalStatus)
    {
        this.appraisalStatus=apprisalStatus;
    }

    public String getAppraisalStatus()
    {
        return appraisalStatus;
    }

    public void setEmployeeID(String empID)
    {
        this.empID=empID;
    }

    public void setCyclecode(String cycleCode)
    {
        this.cycleCode=cycleCode;
    }

    public void setSubmitFlag(String submitFlag)
    {
        this.submitFlag=submitFlag;
    }

    public List getAppraisedDateList()
    {
        return appraisedDateList;
    }

    public List getRemarkList()
    {
        return remarkList;
    }

    public List getQuestionIDList()
    {
        return quesIDList;
    }

    public List getSelfAppraisalIDList()
    {
        return seflAppraisalIDList;
    }

    public List getSelfAppraisalSubmitFlagList()
    {
        return submitFlagList;
    }

     public List getSelfAppraisalStatusFlagList()
    {
        return appraisalStatusList;
    }
    public static String nl2br(String s) {
         s = s.replaceAll("\n", "<br>");
         //System.out.println("The string is"+s);
         return s;
    }

    public void selfApprisalCheck()
    {
        try
    {
        DBconnection dbCon = new DBconnection();

        Connection con=dbCon.getSampleProperty();
        Statement st=con.createStatement();
        String queryForAppraisalChk="SELECT self_appraisal_id,question_id,AES_DECRYPT(remark,'"+empEncryptKey+"'),entry_date,submit_flag,appraisal_status FROM self_appraisal_remark "
                + "WHERE cycle_code='"+cycleCode+"' AND emp_id='"+empID+"'";
System.out.println("queryForAppraisalChk"+queryForAppraisalChk);
        ResultSet rs=st.executeQuery(queryForAppraisalChk);

        while(rs.next())
        {
            if(!rs.getString(2).equals("")){
         seflAppraisalIDList.add(rs.getString(1));
         quesIDList.add(rs.getString(2));
       //System.out.println("db"+rs.getString(2)+"==>"+rs.getString(3));
         remarkList.add(rs.getString(3));
         appraisedDateList.add(rs.getString(4));
         submitFlagList.add(rs.getString(5));
         appraisalStatusList.add(rs.getString(6));
        }
        }


        rs.close();
        st.close();
        con.close();
        }

    catch(SQLException e)
    {
        e.printStackTrace();
        System.out.println(e.toString());
    }

    }

     public int saveSelfApprisal()
    {
         int maxSelfAppraisalID=0;
         int selfAppraisalSaveStatus=0;
         //int selfAppraisalSaveStatus1=0;
         SaveAppraisalData s1 = new SaveAppraisalData();
       // selfAppraisalSaveStatus1= s1.getSelfAppraisalSaveStatus1();
        System.out.println("ss"+selfAppraisalSaveStatus1);
        try
    {
        DBconnection dbCon = new DBconnection();

        Connection con=dbCon.getSampleProperty();
        Statement st=con.createStatement();
        if(saveOrModify.equals("saveNew"))
        {
        String queryForGetMaxID="SELECT max(self_appraisal_id) FROM self_appraisal_remark";

        ResultSet rs=st.executeQuery(queryForGetMaxID);

        while(rs.next())
        {
         maxSelfAppraisalID=Integer.parseInt(rs.getString(1));

        }
        rs.close();
        maxSelfAppraisalID=maxSelfAppraisalID+1;

        for(int i=0;i<questionIDList.size();i++)
        {
            
            quesID=questionIDList.get(i).toString();
            remark=remarksList.get(i).toString();
           // System.out.println("remar"+remark);
        String queryForSaveAppraisal="INSERT INTO self_appraisal_remark(self_appraisal_id,cycle_code,emp_id,question_id,remark,entry_date,submit_flag,appraisal_status)"
                            +"VALUES("+maxSelfAppraisalID+",'"+cycleCode+"','"+empID+"','"+quesID+"',AES_ENCRYPT('"+remark+"','"+empEncryptKey+"'), CURRENT_TIMESTAMP(),'"+submitFlag+"','"+appraisalStatus+"')";

        st.executeUpdate(queryForSaveAppraisal);
        if (selfAppraisalSaveStatus1==2){
            selfAppraisalSaveStatus=2;
        }
        else{
        selfAppraisalSaveStatus=1;
        }
        }}   else    {
            //System.out.println(questionIDList+"Inside updating self appraisal"+remarksList);
            for(int i=0;i<questionIDList.size();i++)
        {
            quesID=questionIDList.get(i).toString();
            remark=remarksList.get(i).toString();
        String queryForSaveAppraisal="update self_appraisal_remark set remark=AES_ENCRYPT('"+remark+"','"+empEncryptKey+"'), submit_flag='"+submitFlag+"',entry_date=CURRENT_TIMESTAMP() where question_id='"+quesID+"' and emp_id='"+empID+"' and cycle_code='"+cycleCode+"'";
        selfAppraisalSaveStatus=st.executeUpdate(queryForSaveAppraisal);
        //saveSelfApprisal.selfAppraisalSaveStatus();
        selfAppraisalSaveStatus=2;
       
        }
        }
        st.close();
        con.close();
        }


    catch(SQLException e)
    {
        e.printStackTrace();
        System.out.println(e.toString());
    }
         return selfAppraisalSaveStatus;

    }

}
