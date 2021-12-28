/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.projects;

import java.io.Serializable;
import connection.DBconnection;
import java.sql.*;
/**
 *
 * @author ramesh
 */
public class ProjPropertyValues implements Serializable {
   
    private String prjid="";
    private String propId="";
    private String propValue="";

    private int addPropValue=0;


    public ProjPropertyValues() {
        
    }

    public void setPropId(String propId){
        this.propId=propId;
    }

   public void setProjId(String prjid){
        this.prjid=prjid;
       // System.out.println("getProjId:"+prjid);
   }
//addPropValue

    public String getPropValue(){
        return propValue;
    }

   

public void projPropertyValue(){

Connection con=null;
        try{

        propValue="";
        //System.out.println("getProjId in method:"+prjid);
        DBconnection dbcon = new DBconnection();
        con = dbcon.getSampleProperty();
       
     Statement statement = con.createStatement(); 
     String chkValue = "";
//" select pp.value from project_properties_value_master ppvm,project_properties pp where ppvm.value=pp.value and pp.proj_id='"+prjid+"' and pp.property_id='"+propId+"'   ");
     ResultSet rsCheckProPValue =  statement.executeQuery(" select pp.value from project_properties pp where pp.proj_id='"+prjid+"' and pp.property_id='"+propId+"'   ");
     while(rsCheckProPValue.next()){
       propValue=rsCheckProPValue.getString(1);
       if(rsCheckProPValue.wasNull()){
         propValue="";
        }
       }

     
       rsCheckProPValue.close();
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
            //System.out.println(e);
            }
            catch(NullPointerException npe){
            //System.out.println(e);
            }catch(Exception npe){
            //System.out.println(e);
            }


        }     
                prjid="";
                propId="";
               
}

}
