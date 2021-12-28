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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Parameshwarant
 */
public class AppraisalQuestion {

    String appraisalType="";
    String queryForGetApprslQues="";
    String appraisalPerfmzParam="";
    String queryForGetPerfmnzParam="";
    String queryForGetReviewParam = "";
    String selfParam = "";
    List appraisalQuestion=new ArrayList();
    List appraisalQuestionID=new ArrayList();
    List appraisalQuestionRate = new ArrayList();
    List appraisalPerfomanceParam= new ArrayList();
    List appraisalPerfomanceParamID= new ArrayList();
    List appraisalReviewParam1= new ArrayList();
    List appraisalReviewFlag1= new ArrayList();
    List appraisalReviewParam2= new ArrayList();
    List appraisalReviewFlag2= new ArrayList();
    List questions_group = new ArrayList();
    List desig_code = new ArrayList();
    List forall_dept=new ArrayList();
    List question_type = new ArrayList();
    List sub_question_flag = new ArrayList();
    List noOfSubQustin = new ArrayList();

    public void setNoOfSubQustin(List noOfSubQustin) {
        this.noOfSubQustin = noOfSubQustin;
    }

    public List getNoOfSubQustin() {
        return noOfSubQustin;
    }
    
    String desig_code1 ="";

    public void setDesig_code1(String desig_code1) {
        this.desig_code1 = desig_code1;
    }

    public String getDesig_code1() {
        return desig_code1;
    }

    
    public void setDesig_code(List desig_code) {
        this.desig_code = desig_code;
    }

    public void setForall_dept(List forall_dept) {
        this.forall_dept = forall_dept;
    }

    public List getQuestions_group() {
        return questions_group;
    }

    public void setSub_question_flag(List sub_question_flag) {
        this.sub_question_flag = sub_question_flag;
    }

    public void setQuestions_group(List questions_group) {
        this.questions_group = questions_group;
    }

    public void setQuestion_type(List question_type) {
        this.question_type = question_type;
    }

    public List getDesig_code() {
        return desig_code;
    }

    public List getForall_dept() {
        return forall_dept;
    }

    public List getQuestion_type() {
        return question_type;
    }

    public List getSub_question_flag() {
        return sub_question_flag;
    }

    public String getAppraisalType() {
        return appraisalType;
    }


    Set<String> set = new HashSet<String>();

    public void setSelfParam(String selfParam) {
        this.selfParam = selfParam;
    }

    public String getSelfParam() {
        return selfParam;
    }

    

    public void setAppraisalType(String appraisalType)
    {
        this.appraisalType=appraisalType;
    }

    public List getAppraisalQuestion()
    {
        return appraisalQuestion;
    }

     public List getAppraisalQuestionID()
    {
        return appraisalQuestionID;
    }

     public List getAppraisalPerfomanceParam()
    {
        return appraisalPerfomanceParam;
    }

     public List getAppraisalPerfomanceParamID()
    {
        return appraisalPerfomanceParamID;
    }

     public List getAppraisalReviewParam1()
    {
        return appraisalReviewParam1;
    }

     public List getAppraisalReviewFlag1()
    {
        return appraisalReviewFlag1;
    }

     public List getAppraisalReviewParam2()
    {
        return appraisalReviewParam2;
    }

     public List getAppraisalReviewFlag2()
    {
        return appraisalReviewFlag2;
    }

    public List getAppraisalQuestionRate() {
        return appraisalQuestionRate;
    }

    public void getAppraisalQuestions()
    {

    try
    {
        DBconnection dbCon = new DBconnection();

        Connection con=dbCon.getSampleProperty();
        Statement st=con.createStatement();
        queryForGetApprslQues="SELECT a.appraisal_question,a.question_Number, a.rate_flag,a.questions_group,a.desig_code,a.forall_dept,a.question_type,a.sub_question_flag,a.numbersOfsubquestion FROM appraisal_question a, `appraisal_type` b"
                        +" WHERE a.appraisal_type_id=b.appraisal_type_id AND appraisal_type='"+appraisalType+"' AND question_flag='1' and desig_code='"+desig_code1+"'";
//System.out.print("queryForGetApprslQues"+queryForGetApprslQues);
        ResultSet rs=st.executeQuery(queryForGetApprslQues);

        while(rs.next())
        {
         appraisalQuestion.add(rs.getString(1));
         appraisalQuestionID.add(rs.getString(2));
         appraisalQuestionRate.add(rs.getString(3) != null ? rs.getString(3).toString() : "0");
         questions_group.add(rs.getString(4) != null ? rs.getString(4).toString() : "0");
         desig_code.add(rs.getString(5) != null ? rs.getString(5).toString() : "0");
         forall_dept.add(rs.getString(6) != null ? rs.getString(6).toString() : "0");
         question_type.add(rs.getString(7) != null ? rs.getString(7).toString() : "0");
         sub_question_flag.add(rs.getString(8) != null ? rs.getString(8).toString() : "0");
         noOfSubQustin.add(rs.getString(9) != null ? rs.getString(9).toString() : "0");
        }
        rs.close();
        

//        if (appraisalType.equals("Self Appraisal")){
//
//appraisalQuestion.add("4.a");
//appraisalQuestion.add("4.b");
//appraisalQuestion.add("4.c");
//appraisalQuestion.add("4.d");
//appraisalQuestion.add("4.e");
//appraisalQuestion.add("4.f");
//appraisalQuestion.add("5.a");
//appraisalQuestionID.add("4.a");
//appraisalQuestionID.add("4.b");
//appraisalQuestionID.add("4.c");
//appraisalQuestionID.add("4.d");
//appraisalQuestionID.add("4.e");
//appraisalQuestionID.add("4.f");
//appraisalQuestionID.add("5.a");
//appraisalQuestionRate.add("0");
//appraisalQuestionRate.add("0");
//appraisalQuestionRate.add("0");
//appraisalQuestionRate.add("0");
//appraisalQuestionRate.add("0");
//appraisalQuestionRate.add("0");
//appraisalQuestionRate.add("0");
//        }
         if(appraisalQuestionID.contains("2"))
         {
             queryForGetPerfmnzParam="SELECT param_id,param_value FROM self_performance_parameters";
             ResultSet rst=st.executeQuery(queryForGetPerfmnzParam);
             while(rst.next())
             {
                 appraisalPerfomanceParamID.add(rst.getString(1));
                 appraisalPerfomanceParam.add(rst.getString(2));
             }
             rst.close();
        }

        st.close();
        con.close();

    
        }
    catch(SQLException e)
    {
        e.printStackTrace();
        System.out.println(e.toString());
    }
    }

}

