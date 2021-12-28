/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.contacts;



import java.io.Serializable;
import connection.DBconnection;
import java.sql.*;
import java.util.*;
/**
 *
 * @author ramesh
 * class to collect the contact type and return them
 */

public class ProjAuthorInfo implements Serializable {

    private String prjId="";
    private List contactFirstName=new ArrayList();
    private List contactSecondName=new ArrayList();
    private List contactDesig=new ArrayList();
    private List contactCompany=new ArrayList();

    private List contactCity=new ArrayList();
    private List contactCountry=new ArrayList();
    private List cntState=new ArrayList();
    private List contactCountryName=new ArrayList();
    private List primaryAuthor=new ArrayList();
    private List authorId=new ArrayList();
    private String contactId="";
    private List cntStateName=new ArrayList();
    
    public ProjAuthorInfo(){

    }

   
    public void setPrjId(String prjId){
        this.prjId=prjId;
    }

    
    public List getContactFirstName(){
        return contactFirstName;
    }

    
    public List getContactSecondName(){
        return contactSecondName;
    }

   
    public List getContactDesig(){
        return contactDesig;
    }

    
    public List getContactCompany(){
        return contactCompany;
    }

   
     public List getContactCity(){
        return contactCity;
    }

 
     public List getContactCountry(){
        return contactCountry;
    }


    public List getContactCountryName(){
        return contactCountryName;
    }

    public List getAuthorId(){
        return authorId;
    }

    public List getCntState(){
        return cntState;
    }

    public List getCntStateName(){
        return cntStateName;
    }

    public List getPrimaryAuthor(){
        return primaryAuthor;
    }


    public void collectAuthorInfo(){
        pathfinder.util.SpecialCharHandler splChar = new pathfinder.util.SpecialCharHandler();
        try{
        DBconnection dbcon = new DBconnection();
        Connection con = dbcon.getSampleProperty();
        String resultTemp = "";

        Statement statement = con.createStatement();
 
        String contactsInfo_Sql = " select cnt.contact_id,cnt.firstname,cnt.surname," +
                                  " cnt.company,"+
                                    " cnt.city," +
                                    " cnt.designation," +
                                    " pra.primary_author "+
                                    " from contacts cnt,project_authors pra " +
                                    " where  pra.proj_id='"+prjId+"' and pra.author_id=cnt.contact_id ";

        //System.out.println("contactsInfo_Sql:"+contactsInfo_Sql);

        ResultSet rsGetContactInfo = statement.executeQuery(contactsInfo_Sql);
        while(rsGetContactInfo.next()){
        //System.out.println("contactsInfo_Sql:"+contactsInfo_Sql);
          resultTemp =  rsGetContactInfo.getString("cnt.contact_id");
           if(rsGetContactInfo.wasNull()) {
               resultTemp="";
           }
           contactId=resultTemp;

           authorId.add(contactId);

//System.out.println("contactId:"+contactId);

           resultTemp =  rsGetContactInfo.getString("pra.primary_author");
           if(rsGetContactInfo.wasNull()) {
               resultTemp="";
           }
           primaryAuthor.add(resultTemp);


           resultTemp =  splChar.decoding(rsGetContactInfo.getString("cnt.firstname"));
           if(rsGetContactInfo.wasNull()) {
               resultTemp="";
           }
           contactFirstName.add(resultTemp);

           resultTemp =  splChar.decoding(rsGetContactInfo.getString("cnt.surname"));
           if(rsGetContactInfo.wasNull()) {
               resultTemp="";
           }
           contactSecondName.add(resultTemp);

           resultTemp =  splChar.decoding(rsGetContactInfo.getString("cnt.company"));
           if(rsGetContactInfo.wasNull()) {
               resultTemp="";
           }
           contactCompany.add(resultTemp);


           resultTemp =  rsGetContactInfo.getString("cnt.city");
           if(rsGetContactInfo.wasNull()) {
               resultTemp="";
           }
           contactCity.add(resultTemp);

           resultTemp =  rsGetContactInfo.getString("cnt.designation");
               if(rsGetContactInfo.wasNull()) {
                   resultTemp="";
               }
               contactDesig.add(resultTemp);
        

        }


        //System.out.println("authorId:"+authorId);
    //query to collect country name. Since for some contact country would have not been added to avoid Empty ResultSet the country name retrieval has been seperated
        for(int idx=0;idx<authorId.size();idx++){
            contactId=authorId.get(idx).toString();
                    contactsInfo_Sql = " select cty.name" +
                                    " from contacts cnt,country cty " +
                                    " where  cnt.contact_id='"+contactId+"' and cnt.country=cty.code ";

    
        resultTemp="";
            rsGetContactInfo = statement.executeQuery(contactsInfo_Sql);
            while(rsGetContactInfo.next()){
              resultTemp =  rsGetContactInfo.getString("cty.name");
               if(rsGetContactInfo.wasNull()) {
                   resultTemp="";
               }
            }
           contactCountryName.add(resultTemp);
        }

        for(int idx=0;idx<authorId.size();idx++){
            contactId=authorId.get(idx).toString();
                    contactsInfo_Sql = " select cnt.state,sta.state_name" +
                                    " from contacts cnt,state sta " +
                                    " where  cnt.contact_id='"+contactId+"' and cnt.state=sta.state_id ";
               //System.out.println("contactsInfo_Sql:"+contactsInfo_Sql);
           resultTemp  ="";

            rsGetContactInfo = statement.executeQuery(contactsInfo_Sql);
            if(rsGetContactInfo.next()){
                 rsGetContactInfo = statement.executeQuery(contactsInfo_Sql);
            while(rsGetContactInfo.next()){
               resultTemp   =  rsGetContactInfo.getString("cnt.state");
               if(rsGetContactInfo.wasNull()) {
                   resultTemp  ="";
               }
               cntState.add(resultTemp);

              resultTemp   =  rsGetContactInfo.getString("sta.state_name");
               if(rsGetContactInfo.wasNull()) {
                   resultTemp  ="";
               }
               cntStateName.add(resultTemp);
            }
            }/*close of if*/
            else{
                cntState.add(resultTemp);
                cntStateName.add(resultTemp);
            }
        }

//        System.out.println("cntState:"+cntState);
//        System.out.println("cntStateName:"+cntStateName);

        rsGetContactInfo.close();
        statement.close();
        con.close();
        }catch(SQLException sqle){
            System.out.println("SQLException in collecting ContactInfo:"+sqle);
        }catch(Exception e){

            System.out.println("Exception in collecting ContactInfo:"+e);
        }

    }


  
}
