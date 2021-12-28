/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.projects;

import java.io.Serializable;
import connection.DBconnection;
import java.sql.*;
import java.util.*;
/**
 *
 * @author ramesh
 */
public class ProjTeamValues implements Serializable {
   
    private String prjid="";

    private List roleName = new ArrayList();
    private List empName = new ArrayList();
    private List empId = new ArrayList();
    private List primaryIncharge = new ArrayList();
    private List facilityName = new ArrayList();
    private List facilityId = new ArrayList();
    private List teamMapId = new ArrayList();

    private List projAllocType = new ArrayList();
    private List projCellFacilName = new ArrayList();
    private List projCellFacilId = new ArrayList();
    private List projCellDeptName = new ArrayList();
    private List projCellCellName = new ArrayList();
    private List projCellTeamId = new ArrayList();

    private List projAssignedEmpId = new ArrayList();
    private List projAssignedEmpName = new ArrayList();
    private List projAssignedEmpDept = new ArrayList();
    private List projAssignedEmpDesig = new ArrayList();
    private List projAssignedEmpFacName = new ArrayList();
    private List projAssignedEmpRoleName = new ArrayList();
    private List projAssignedEmpFacId = new ArrayList();
    private List projAssignedEmpMapId = new ArrayList();
    private List projAssignedEmpPrimaryInch = new ArrayList();
    private List mappedEmpNameForCell = new ArrayList();
    private List mappedEmpIDForCell = new ArrayList();
    private List getProjAllocType = new ArrayList();

    
    public ProjTeamValues() {
        
    }

    public List getProjAssignedEmpPrimaryInch() {
        return projAssignedEmpPrimaryInch;
    }

    public List getRoleName(){
        return roleName;
    }

     public List getEmpName(){
        return empName;
    }

     public List getEmpId(){
        return empId;
    }

     public List getPrimaryIncharge(){
        return primaryIncharge;
    }

    public List getFacilityName(){
        return facilityName;
    }

    public List getFacilityId(){
        return facilityId;
    }

   public void setProjId(String prjid){
        this.prjid=prjid;
       // System.out.println("getProjId:"+prjid);
   }

   public List getMapId(){
        return teamMapId;
       // System.out.println("getProjId:"+prjid);
   }

    public List getProjAllocType() {
        return projAllocType;
    }

    public List getProjCellCellName() {
        return projCellCellName;
    }

    public List getProjCellDeptName() {
        return projCellDeptName;
    }

    public List getProjCellFacilId() {
        return projCellFacilId;
    }

    public List getProjCellFacilName() {
        return projCellFacilName;
    }

    public List getProjCellTeamId() {
        return projCellTeamId;
    }

    public List getProjAssignedEmpDept() {
        return projAssignedEmpDept;
    }

    public List getProjAssignedEmpDesig() {
        return projAssignedEmpDesig;
    }

    public List getProjAssignedEmpFacId() {
        return projAssignedEmpFacId;
    }

    public List getProjAssignedEmpFacName() {
        return projAssignedEmpFacName;
    }

    public List getProjAssignedEmpId() {
        return projAssignedEmpId;
    }

    public List getProjAssignedEmpMapId() {
        return projAssignedEmpMapId;
    }

    public List getProjAssignedEmpName() {
        return projAssignedEmpName;
    }

    public List getProjAssignedEmpRoleName() {
        return projAssignedEmpRoleName;
    }

    public List getMappedEmpIDForCell()
    {
        return mappedEmpIDForCell;
    }

    public List getMappedEmpNameForCell()
    {
        return mappedEmpNameForCell;
    }

    public List getProjAllocTypes()
    {
        return getProjAllocType;
    }

    //addPropValue  
   

public void projTeamValue(){

Connection con=null;
        try{
            roleName.clear();
            empName.clear();
            empId.clear();
            primaryIncharge.clear();
            facilityName.clear();
            facilityId.clear();
            teamMapId.clear();
            projAllocType.clear();
            projCellFacilName.clear();
            projCellFacilId.clear();
            projCellDeptName.clear();
            projCellCellName.clear(); 
            projCellTeamId.clear();
            projAssignedEmpId.clear();
            projAssignedEmpName.clear();
            projAssignedEmpDept.clear();
            projAssignedEmpDesig.clear();
            projAssignedEmpFacName.clear();
            projAssignedEmpFacId.clear();
            projAssignedEmpMapId.clear();
            projAssignedEmpPrimaryInch.clear();

        //System.out.println("getProjId in method:"+prjid);
        DBconnection dbcon = new DBconnection();
        con = dbcon.getSampleProperty();
       
     Statement statement = con.createStatement(); 
     String propValue = "";

     /*System.out.println("select atp.activity,ptm.emp_id,u.emp_name,ptm.primary_incharge,fc.facility_name,fc.facility_id,ptm.teammap_id " +
             "from project_team ptm,user u,facility fc,activity_type atp " +
             "where u.emp_id=ptm.emp_id and ptm.role_id=atp.activity_code" +
             " and ptm.facility_id=fc.facility_id and ptm.proj_id='"+prjid+"' " +
             "order by atp.activity,ptm.primary_incharge desc");
     */

     //the query below is executed twice. First to retrieve records for which Employee has been mapped and the
     //second is to retrieve the records for which only the location has been mapped

     /*System.out.println("With EmpID:"+"select atp.activity,ptm.emp_id,u.emp_name,ptm.primary_incharge,fc.facility_name,fc.facility_id,ptm.teammap_id " +
             "from project_team ptm,user u,facility fc,activity_type atp " +
             "where u.emp_id=ptm.emp_id and ptm.role_id=atp.activity_code " +
             "and ptm.facility_id=fc.facility_id and ptm.proj_id='"+prjid+"' and ptm.emp_id is not null " +
             "order by atp.activity,ptm.primary_incharge desc");*/

     ResultSet rsProjAllocValue =  statement.executeQuery(" SELECT ptm.proj_alloc_type FROM project_team ptm WHERE ptm.proj_id='"+prjid+"' group by ptm.proj_alloc_type ");

     while(rsProjAllocValue.next()){

         propValue=rsProjAllocValue.getString("ptm.proj_alloc_type");
         if(rsProjAllocValue.wasNull()){
            propValue="";
         }
         projAllocType.add(propValue);         
     }

     ResultSet rsProjCellValue =  statement.executeQuery("select fc.facility_name,fc.facility_id,d.department,c.cell_name,ptm.teammap_id from project_team ptm,facility fc,department d,cell c " +
             " where ptm.facility_id=fc.facility_id and d.dept_code=c.dept_code and c.cell_code=ptm.cell_code AND ptm.proj_id='"+prjid+"' " +
             " and ptm.emp_id IS NULL and ptm.role_id IS NULL and ptm.proj_alloc_type='1' order by fc.facility_id ");

     while(rsProjCellValue.next()){

         propValue=rsProjCellValue.getString("fc.facility_name");
         if(rsProjCellValue.wasNull()){
            propValue="";
         }
         projCellFacilName.add(propValue);


         propValue=rsProjCellValue.getString("fc.facility_id");
         if(rsProjCellValue.wasNull()){
            propValue="";
         }
         projCellFacilId.add(propValue);

         propValue=rsProjCellValue.getString("d.department");
         if(rsProjCellValue.wasNull()){
            propValue="";
         }
         projCellDeptName.add(propValue);

         propValue=rsProjCellValue.getString("c.cell_name");
         if(rsProjCellValue.wasNull()){
            propValue="";
         }
         projCellCellName.add(propValue);

         propValue=rsProjCellValue.getString("ptm.teammap_id");
         if(rsProjCellValue.wasNull()){
            propValue="";
         }
         projCellTeamId.add(propValue);
     }
     
rsProjCellValue.close();
    /* System.out.println("SELECT milestone_name, emp_id, emp_name,primary_incharge,facility_name,facility_id,teammap_id "
                                            +" FROM"
                                            +" (SELECT atp.milestone_act_name AS milestone_name,ptm.emp_id AS emp_id,u.emp_name AS emp_name,ptm.primary_incharge AS primary_incharge,fc.facility_name AS facility_name,fc.facility_id AS facility_id,ptm.teammap_id AS teammap_id "
                                            +" FROM project_team ptm,USER u,facility fc,proj_milestone_act atp"
                                            +" WHERE u.emp_id=ptm.emp_id AND ptm.role_id=atp.milestone_act_code"
                                            +" AND ptm.facility_id=fc.facility_id AND ptm.proj_id='"+prjid+"' AND ptm.emp_id IS NOT NULL"
                                            +" UNION"
                                            +" SELECT atp.milestone_act_name AS milesone_name,ptm.emp_id AS emp_id, (SELECT CONCAT(cnt.firstname,' ', cnt.surname)"
                                            +" FROM contacts cnt, contacttype_map cntm  "
                                            +" WHERE cntm.type_id=9 AND cnt.contact_id=cntm.contact_id AND cnt.contact_id=ptm.emp_id) AS emp_name, ptm.primary_incharge AS primary_incharge,"
                                            +" CONCAT('Freelancer') AS facility_name, ptm.facility_id AS facility_id, ptm.teammap_id AS teammap_id "
                                            +" FROM project_team ptm, proj_milestone_act atp  WHERE facility_id='"+facilityId+"' AND ptm.proj_id ='"+prjid+"' AND ptm.role_id=atp.milestone_act_code ) a ORDER BY milestone_name,primary_incharge DESC ");

                  ResultSet rsTeamValue =  statement.executeQuery("SELECT milestone_name, emp_id, emp_name,primary_incharge,facility_name,facility_id,teammap_id "
                                            +" FROM"
                                            +" (SELECT atp.milestone_act_name AS milestone_name,ptm.emp_id AS emp_id,u.emp_name AS emp_name,ptm.primary_incharge AS primary_incharge,fc.facility_name AS facility_name,fc.facility_id AS facility_id,ptm.teammap_id AS teammap_id "
                                            +" FROM project_team ptm,USER u,facility fc,proj_milestone_act atp"
                                            +" WHERE u.emp_id=ptm.emp_id AND ptm.role_id=atp.milestone_act_code"
                                            +" AND ptm.facility_id=fc.facility_id AND ptm.proj_id='"+prjid+"' AND ptm.emp_id IS NOT NULL"
                                            +" UNION"
                                            +" SELECT atp.milestone_act_name AS milesone_name,ptm.emp_id AS emp_id, (SELECT CONCAT(cnt.firstname,' ', cnt.surname)"
                                            +" FROM contacts cnt, contacttype_map cntm  "
                                            +" WHERE cntm.type_id=9 AND cnt.contact_id=cntm.contact_id AND cnt.contact_id=ptm.emp_id) AS emp_name, ptm.primary_incharge AS primary_incharge,"
                                            +" CONCAT('Freelancer') AS facility_name, ptm.facility_id AS facility_id, ptm.teammap_id AS teammap_id "
                                            +" FROM project_team ptm, proj_milestone_act atp  WHERE facility_id='"+facilityId+"' AND ptm.proj_id ='"+prjid+"' AND ptm.role_id=atp.milestone_act_code ) a ORDER BY milestone_name,primary_incharge DESC ");
 */
     String query = "SELECT d.designation, u.emp_id, u.emp_name, pt.primary_incharge, f.facility_name, f.facility_id, pt.teammap_id "
             + "FROM facility f, designation d, "
             + "project_team pt LEFT JOIN USER u ON pt.emp_id=u.emp_id "
             + "WHERE pt.proj_id='"+prjid+"' AND u.desig_code=d.desig_code AND pt.facility_id=f.facility_id "
             + "ORDER BY pt.teammap_id";
     ResultSet rsTeamValue =  statement.executeQuery(query);
//     ResultSet rsTeamValue =  statement.executeQuery("select atp.milestone_act_name,ptm.emp_id,u.emp_name,ptm.primary_incharge,fc.facility_name,fc.facility_id,ptm.teammap_id " +
//             "from project_team ptm,user u,facility fc,proj_milestone_act atp " +
//             "where u.emp_id=ptm.emp_id and ptm.role_id=atp.milestone_act_code " +
//             "and ptm.facility_id=fc.facility_id and ptm.proj_id='"+prjid+"' and ptm.emp_id is not null " +
//             "order by atp.milestone_act_name,ptm.primary_incharge desc");

     
     while(rsTeamValue.next()){

       propValue=rsTeamValue.getString("designation");
       if(rsTeamValue.wasNull()){
         propValue="";
        }
        roleName.add(propValue);
//        System.out.println("First MileStone Act Name:"+roleName);

       propValue=rsTeamValue.getString("emp_id");
       if(rsTeamValue.wasNull()){
         propValue="";
        }
        empId.add(propValue);
//        System.out.println("First EMP ID:"+empId);

      propValue=rsTeamValue.getString("emp_name");
       if(rsTeamValue.wasNull()){
         propValue="";
        }
        empName.add(propValue);
//        System.out.println("First Emp Name:"+empName);
        //ptm.primary_incharge,fc.facility_name,fc.facility_id
       propValue=rsTeamValue.getString("primary_incharge");
       if(rsTeamValue.wasNull()){
         propValue="";
        }
        primaryIncharge.add(propValue);
//        System.out.println("First Primary Incharge:"+primaryIncharge);

       propValue=rsTeamValue.getString("facility_name");
       if(rsTeamValue.wasNull()){
         propValue="";
        }
        facilityName.add(propValue);
//        System.out.println("First Facility Name:"+facilityName);

        propValue=rsTeamValue.getString("facility_id");
       if(rsTeamValue.wasNull()){
         propValue="";
        }
        facilityId.add(propValue);
//        System.out.println("First Facility ID:"+facilityId);


       propValue=rsTeamValue.getString("teammap_id");
       if(rsTeamValue.wasNull()){
         propValue="";
        }
        teamMapId.add(propValue);
//        System.out.println("First Team Map Id:"+teamMapId);

       }
/*System.out.println("without EmpId:"+"SELECT milestone_name,primary_incharge,facility_name,facility_id,teammap_id "
                                                    +" FROM "
                                                    +" SELECT atp.milestone_act_name as milestone_name,ptm.primary_incharge as primary_incharge,fc.facility_name as facility_name,fc.facility_id as facility_id,ptm.teammap_id as teammap_id "
                                                    +" FROM project_team ptm,facility fc,proj_milestone_act atp "
                                                    +" WHERE ptm.role_id=atp.milestone_act_code "
                                                    +" AND ptm.facility_id=fc.facility_id AND ptm.proj_id='"+prjid+"' AND ptm.emp_id IS NULL  "
                                                    +"  UNION "
                                                    +" SELECT atp.milestone_act_name as milestone_name, ptm.primary_incharge as primary_incharge, "
                                                    +" CONCAT('Freelancer') AS facility_name, ptm.facility_id as facility_id, ptm.teammap_id as teammap_id"
                                                    +" FROM project_team ptm, proj_milestone_act atp  WHERE ptm.facility_id='"+facilityId+"' and ptm.proj_id ='"+prjid+"' AND ptm.role_id=atp.milestone_act_code) FROM a ORDER BY milestone_name");*/

             rsTeamValue =  statement.executeQuery("SELECT atp.milestone_act_name as milestone_name,ptm.primary_incharge as primary_incharge,fc.facility_name as facility_name,fc.facility_id as facility_id,ptm.teammap_id as teammap_id "
                                                    +" FROM project_team ptm,facility fc,proj_milestone_act atp "
                                                    +" WHERE ptm.role_id=atp.milestone_act_code "
                                                    +" AND ptm.facility_id=fc.facility_id AND ptm.proj_id='"+prjid+"' AND ptm.emp_id IS NULL  "
                                                    +"  -- UNION "
                                                    +" SELECT atp.milestone_act_name as milestone_name, ptm.primary_incharge as primary_incharge, "
                                                    +" CONCAT('Independent Contractor') AS facility_name, ptm.facility_id as facility_id, ptm.teammap_id as teammap_id"
                                                    +" FROM project_team ptm, proj_milestone_act atp  WHERE ptm.facility_id='"+facilityId+"' and ptm.proj_id ='"+prjid+"' AND ptm.role_id=atp.milestone_act_code");


//     rsTeamValue =  statement.executeQuery("select atp.milestone_act_name,ptm.primary_incharge,fc.facility_name,fc.facility_id,ptm.teammap_id " +
//             "from project_team ptm,facility fc,proj_milestone_act atp " +
//             "where ptm.role_id=atp.milestone_act_code " +
//             "and ptm.facility_id=fc.facility_id and ptm.proj_id='"+prjid+"' and ptm.emp_id is null " +
//             "order by atp.milestone_act_name,ptm.primary_incharge desc");


     while(rsTeamValue.next()){

       propValue=rsTeamValue.getString("milestone_name");
       if(rsTeamValue.wasNull()){
         propValue="";
        }
        roleName.add(propValue);
//        System.out.println("Second MileStone Act Name:"+roleName);

       //THE BELOW is to avoid arrayIndexOutOfBounds Exception while looping in view parts
        empId.add("");
        empName.add("");
        //ptm.primary_incharge,fc.facility_name,fc.facility_id
       propValue=rsTeamValue.getString("primary_incharge");
       if(rsTeamValue.wasNull()){
         propValue="";
        }
        primaryIncharge.add(propValue);
//        System.out.println("Second Primary Incharge:"+primaryIncharge);

       propValue=rsTeamValue.getString("facility_name");
       if(rsTeamValue.wasNull()){
         propValue="";
        }
        facilityName.add(propValue);
//        System.out.println("Second facility Name:"+facilityName);

        propValue=rsTeamValue.getString("facility_id");
       if(rsTeamValue.wasNull()){
         propValue="";
        }
        facilityId.add(propValue);
//        System.out.println("Second facility Id:"+facilityId);


       propValue=rsTeamValue.getString("teammap_id");
       if(rsTeamValue.wasNull()){
         propValue="";
        }
        teamMapId.add(propValue);
        //System.out.println("Second Team Map ID:"+teamMapId);

       }
             rsTeamValue.close();
           ResultSet rsProjAssignedEmpValue =  statement.executeQuery(" SELECT emp_id,emp_name,department,designation,facility_name,facility_id,teammap_id,primary_incharge"
                                                                        +" FROM"
                                                                        +"(SELECT ptm.emp_id AS emp_id,u.emp_name AS emp_name,d.department AS department,des.designation AS designation,fc.facility_name AS facility_name,fc.facility_id AS facility_id,ptm.teammap_id AS teammap_id,ptm.primary_incharge AS primary_incharge "
                                                                        +" FROM project_team ptm,USER u,facility fc,department d,designation des WHERE u.emp_id=ptm.emp_id AND ptm.facility_id=fc.facility_id AND "
                                                                        +" ptm.proj_id='"+prjid+"' AND ptm.emp_id IS NOT NULL AND u.dept_code=d.dept_code AND u.desig_code=des.desig_code "
                                                                        +" UNION "
                                                                        +" SELECT ptm.emp_id AS emp_id, (SELECT CONCAT(cnt.firstname,' ', cnt.surname)"
                                                                        +" FROM contacts cnt, contacttype_map cntm "
                                                                        +" WHERE cntm.type_id=9 AND cnt.contact_id=cntm.contact_id AND cnt.contact_id=ptm.emp_id) AS emp_name, CONCAT('Independent Contractor') AS department,CONCAT('Independent Contractor') AS designation, "
                                                                        +" CONCAT('Independent Contractor') AS facility_name, ptm.facility_id AS facility_id,ptm.teammap_id AS teammap_id,ptm.primary_incharge AS primary_incharge "
                                                                        +" FROM project_team ptm WHERE facility_id='"+facilityId+"' AND ptm.proj_id ='"+prjid+"' AND ptm.proj_alloc_type='2') a GROUP BY teammap_id ORDER BY primary_incharge DESC");

//     ResultSet rsProjAssignedEmpValue =  statement.executeQuery(" SELECT ptm.emp_id,u.emp_name,d.department,des.designation,fc.facility_name,fc.facility_id,ptm.teammap_id,ptm.primary_incharge "
//             + " FROM project_team ptm,USER u,facility fc,department d,designation des WHERE u.emp_id=ptm.emp_id AND ptm.facility_id=fc.facility_id AND "
//             + " ptm.proj_id='"+prjid+"' AND ptm.emp_id IS NOT NULL AND u.dept_code=d.dept_code AND u.desig_code=des.desig_code ORDER BY fc.facility_name,d.department,des.designation ");

     while(rsProjAssignedEmpValue.next()){

         propValue=rsProjAssignedEmpValue.getString(1);
         if(rsProjAssignedEmpValue.wasNull()){
            propValue="";
         }
         projAssignedEmpId.add(propValue);
//         System.out.println("Third Proj Assign Emp ID:"+projAssignedEmpId);

         propValue=rsProjAssignedEmpValue.getString(2);
         if(rsProjAssignedEmpValue.wasNull()){
            propValue="";
         }
         projAssignedEmpName.add(propValue);
//         System.out.println("Third Proj Assigned Name:"+projAssignedEmpName);

         propValue=rsProjAssignedEmpValue.getString(3);
         if(rsProjAssignedEmpValue.wasNull()){
            propValue="";
         }
         projAssignedEmpDept.add(propValue);
//         System.out.println("Third Proj Assigned Emp Dept:"+projAssignedEmpDept);

         propValue=rsProjAssignedEmpValue.getString(4);
         if(rsProjAssignedEmpValue.wasNull()){
            propValue="";
         }
         projAssignedEmpDesig.add(propValue);
//         System.out.println("Third Proj Assigned Emp Desig:"+projAssignedEmpDesig);

         propValue=rsProjAssignedEmpValue.getString(5);
         if(rsProjAssignedEmpValue.wasNull()){
            propValue="";
         }
         projAssignedEmpFacName.add(propValue);
//         System.out.println("Third Proj Assigned Emp Facilty Name:"+projAssignedEmpFacName);

         propValue=rsProjAssignedEmpValue.getString(6);
         if(rsProjAssignedEmpValue.wasNull()){
            propValue="";
         }
         projAssignedEmpFacId.add(propValue);
//         System.out.println("Third Proj Assigned Emp Facility ID:"+projAssignedEmpFacId);

         propValue=rsProjAssignedEmpValue.getString(7);
         if(rsProjAssignedEmpValue.wasNull()){
            propValue="";
         }
         projAssignedEmpMapId.add(propValue);
//         System.out.println("Third Proj Assigned EMp Map ID:"+projAssignedEmpMapId);

         propValue=rsProjAssignedEmpValue.getString(8);
         if(rsProjAssignedEmpValue.wasNull()){
            propValue="";
         }
         projAssignedEmpPrimaryInch.add(propValue);
//         System.out.println("Third Proj Assigned EMP Primary Incharge:"+projAssignedEmpPrimaryInch);

         /*
         propValue=rsProjAssignedEmpValue.getString(9);
         if(rsProjAssignedEmpValue.wasNull()){
            propValue="";
         }
          */
         propValue="";
         projAssignedEmpRoleName.add(propValue);
//         System.out.println("Third Proj Assigned EMP Primary Incharge:"+projAssignedEmpPrimaryInch);
     }
           rsProjAssignedEmpValue.close();
           statement.close();


       
//System.out.println("addContact:"+addContact);
        }catch(SQLException sqle){
            System.out.println("SQLException in creating Proj ID:"+sqle);
        }catch(Exception e){
            System.out.println("Exception in creating Proj ID:"+e);
        }finally{
            try{
              con.close();
            }catch(SQLException e){
            System.out.println(e);
            }

        }     
               
               
}

    public void getMappedEmpNameForCell(String projId) {
        String data = "";
        String cellCode = "";
        String queryForGetProjAllocType = "";
        String queryForGetCellCode = "";
        String queryForGetEmpForCell = "";
        try {
            DBconnection dbcon = new DBconnection();
            Connection con = dbcon.getSampleProperty();
            Statement statement = con.createStatement();
            statement = con.createStatement();

            queryForGetProjAllocType = "SELECT proj_alloc_type FROM project_team WHERE proj_id='" + projId + "' GROUP BY proj_alloc_type";
            ResultSet rsForGetProjAllocType = statement.executeQuery(queryForGetProjAllocType);
            while (rsForGetProjAllocType.next()) {
                getProjAllocType.add(rsForGetProjAllocType.getString(1));
            }
            rsForGetProjAllocType.close();

            queryForGetCellCode = "SELECT cell_code FROM project_team WHERE proj_id='" + projId + "' AND cell_code IS NOT NULL ";
            ResultSet rsForGetCellCode = statement.executeQuery(queryForGetCellCode);
            //System.out.println(queryForGetCellCode);
            while (rsForGetCellCode.next()) {
                cellCode = rsForGetCellCode.getString(1);
            }
            rsForGetCellCode.close();

            queryForGetEmpForCell = "SELECT b.emp_id,b.emp_name FROM emp_cell_map a, USER b WHERE STATUS=1 AND a.emp_id=b.emp_id AND a.cell_code='" + cellCode + "'";
            ResultSet rsForGetEmpForCell = statement.executeQuery(queryForGetEmpForCell);
            //System.out.println(queryForGetEmpForCell);
            while (rsForGetEmpForCell.next()) {
                data = rsForGetEmpForCell.getString(1);
                if (rsForGetEmpForCell.wasNull()) {
                    data = "";
                }
                mappedEmpIDForCell.add(data);

                data = rsForGetEmpForCell.getString(2);
                if (rsForGetEmpForCell.wasNull()) {
                    data = "";
                }
                mappedEmpNameForCell.add(data);
            }
            rsForGetEmpForCell.close();
            statement.close();
            con.close();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NullPointerException npe) {
            npe.printStackTrace();
        }

    }

}
