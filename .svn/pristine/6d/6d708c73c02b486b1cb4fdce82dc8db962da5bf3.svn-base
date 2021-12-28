/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package freelanceSkill.services;
import connection.DBconnection;
import java.sql.*;
import java.util.*;

/**
 *
 * @author pathfinder
 */
public class AddFreelancerServicesDetails {
private List freelancerId1 = new ArrayList();
private List freelancerName1 = new ArrayList();
private List freelancerPh1 = new ArrayList();
private List freelancerAltPh1 = new ArrayList();
private List freelanceremail1 = new ArrayList();
private List country1 = new ArrayList();
private List serviceName1 = new ArrayList();

public boolean addDetails(Map<String,String> freelancerservicemap, String freelancerId){
    boolean chk = true;
try {
DBconnection dbcon = new DBconnection();
Connection con = dbcon.getSampleProperty();
  for(Map.Entry m:freelancerservicemap.entrySet()){

ResultSet rs;
String sql="INSERT INTO freelancerandservice (freelancer_id,freelancer_type_id,freelancer_service) values(?,?,?)";
PreparedStatement preparedStmt = con.prepareStatement(sql);
preparedStmt.setString (1, freelancerId);
preparedStmt.setString (2, m.getValue().toString());
preparedStmt.setString (3, m.getKey().toString());
chk = preparedStmt.execute();
System.out.println("chk"+chk);
    }
  }
catch(SQLException e){
e.printStackTrace();

}
return chk;
}

public void getDetailsofFreelancer(List freelancerServiceList){
String freelanid="";
String freelanid1="";
String where="";
int count=0;
int count1=0;
String serviceNameString ="";
String serviceNameString1 ="";
List freelancerId = new ArrayList();
List freelancerName = new ArrayList();
List freelancerPh = new ArrayList();
List freelancerAltPh = new ArrayList();
List freelanceremail = new ArrayList();
List country = new ArrayList();
List serviceName = new ArrayList();
for (int i=0; i<freelancerServiceList.size();i++){
where += freelancerServiceList.get(i).toString()+", ";
}
try {
DBconnection dbcon = new DBconnection();
Connection con = dbcon.getSampleProperty();
Statement statement = con.createStatement();

//System.out.println("select fs.freelancer_id, fs.freelancer_name, fs.phonenumber, fs.mobilenumber, fs.email, fs.country, fn.service_name from freelancerdatafromsurvey fs,freelancerservicesname fn,freelancerandservice fss where "
  //      + "fn.id=fss.freelancer_service and fss.freelancer_service IN("+where+"0) AND fs.freelancer_id=fss.freelancer_id ORDER BY fs.freelancer_id" );
ResultSet rsPid=statement.executeQuery("select fs.freelancer_id, fs.freelancer_name, fs.phonenumber, fs.mobilenumber, fs.email, fs.country, fn.service_name from freelancerdatafromsurvey fs,freelancerservicesname fn,freelancerandservice fss where "
        + "fn.id=fss.freelancer_service and fss.freelancer_service IN("+where+"0) AND fs.freelancer_id=fss.freelancer_id ORDER BY fs.freelancer_id");
while (rsPid.next()){
 freelancerId.add(rsPid.getString(1).toString());
 freelancerName.add(rsPid.getString(2).toString());
 freelancerPh.add(rsPid.getString(3).toString());
 if(rsPid.getString(4).toString()==null){
     freelancerAltPh.add("N/a");
 }
 else{
 freelancerAltPh.add(rsPid.getString(4).toString());
    }
 freelanceremail.add(rsPid.getString(5).toString());
 if(rsPid.getString(6).toString()==null){
     country.add("N/a");
 }
 else{
 country.add(rsPid.getString(6).toString());
    }
 
 serviceName.add(rsPid.getString(7).toString());
  }
}
catch(SQLException e){
e.printStackTrace();

}
freelancerId.add("NA");
freelancerName.add("NA");
freelancerPh.add("NA");
freelancerAltPh.add("NA");
freelanceremail.add("NA");
country.add("NA");
serviceName.add("NA");

int chk=0;
String nameofser="";
for (int j=0; j<freelancerName.size(); j++){
    try{
    if((j+1)<=freelancerName.size()){
    if(freelancerName.get(j).toString().equals(freelancerName.get(j+1).toString())){
        chk++;
       if(chk==1){
nameofser+=serviceName.get(j).toString()+", ";
    }
        nameofser+=serviceName.get(j+1).toString()+", ";
    }
        else{
        freelancerId1.add(freelancerId.get(j).toString());
        freelancerName1.add(freelancerName.get(j).toString());
        freelancerPh1.add(freelancerPh.get(j).toString());
        freelancerAltPh1.add(freelancerAltPh.get(j).toString());
        freelanceremail1.add(freelanceremail.get(j).toString());
        country1.add(country.get(j).toString());
        serviceName1.add(nameofser);
//System.out.println(freelancerName.get(j).toString());
//System.out.println(freelancerPh.get(j).toString());
//System.out.println(freelancerAltPh.get(j).toString());
//System.out.println(nameofser);
nameofser="";
 chk=0;
 }
    }
}
catch(Exception e){
}}

}
public List returnfreelancerDetails1(){
List freelancerId = new ArrayList();
List freelancerName = new ArrayList();
List freelancerPh = new ArrayList();
List freelancerAltPh = new ArrayList();
List freelanceremail = new ArrayList();
List country = new ArrayList();
List serviceName = new ArrayList();
freelancerId=freelancerId1;
freelancerName = freelancerName1;
freelancerPh=freelancerPh1;
freelancerAltPh=freelancerAltPh1;
freelanceremail=freelanceremail1;
country=country1;
serviceName=serviceName1;
List rsult = new ArrayList();
rsult.add(freelancerId);
rsult.add(freelancerName);
rsult.add(freelancerPh);
rsult.add(freelancerAltPh);
rsult.add(freelanceremail);
rsult.add(country);
rsult.add(serviceName);
return rsult;
}

public void getDetailsofFreelancerName(List freelancerServiceList){
String freelanid="";
String freelanid1="";
String where="";
int count=0;
int count1=0;
String serviceNameString ="";
String serviceNameString1 ="";
List freelancerId = new ArrayList();
List freelancerName = new ArrayList();
List freelancerPh = new ArrayList();
List freelancerAltPh = new ArrayList();
List freelanceremail = new ArrayList();
List country = new ArrayList();
List serviceName = new ArrayList();
for (int i=0; i<freelancerServiceList.size();i++){
where += freelancerServiceList.get(i).toString()+", ";
}
try {
DBconnection dbcon = new DBconnection();
Connection con = dbcon.getSampleProperty();
Statement statement = con.createStatement();

//System.out.println("select fs.freelancer_id, fs.freelancer_name, fs.phonenumber, fs.mobilenumber, fs.email, fs.country, fn.service_name from freelancerdatafromsurvey fs,freelancerservicesname fn,freelancerandservice fss where "
  //      + "fn.id=fss.freelancer_service and fss.freelancer_service IN("+where+"0) AND fs.freelancer_id=fss.freelancer_id ORDER BY fs.freelancer_id" );
ResultSet rsPid=statement.executeQuery("select fs.freelancer_id, fs.freelancer_name, fs.phonenumber, fs.mobilenumber, fs.email, fs.country, fn.service_name from freelancerdatafromsurvey fs,freelancerservicesname fn,freelancerandservice fss where "
        + "fn.id=fss.freelancer_service AND fs.freelancer_id=fss.freelancer_id ORDER BY fs.freelancer_id");
while (rsPid.next()){
 freelancerId.add(rsPid.getString(1).toString());
 freelancerName.add(rsPid.getString(2).toString());
 freelancerPh.add(rsPid.getString(3).toString());
 if(rsPid.getString(4).toString()==null){
     freelancerAltPh.add("N/a");
 }
 else{
 freelancerAltPh.add(rsPid.getString(4).toString());
    }
 freelanceremail.add(rsPid.getString(5).toString());
 if(rsPid.getString(6).toString()==null){
     country.add("N/a");
 }
 else{
 country.add(rsPid.getString(6).toString());
    }

 serviceName.add(rsPid.getString(7).toString());
  }
}
catch(SQLException e){
e.printStackTrace();

}
freelancerId.add("NA");
freelancerName.add("NA");
freelancerPh.add("NA");
freelancerAltPh.add("NA");
freelanceremail.add("NA");
country.add("NA");
serviceName.add("NA");

int chk=0;
String nameofser="";
for (int j=0; j<freelancerName.size(); j++){
    try{
    if((j+1)<=freelancerName.size()){
    if(freelancerName.get(j).toString().equals(freelancerName.get(j+1).toString())){
        chk++;
       if(chk==1){
nameofser+=serviceName.get(j).toString()+", ";
    }
        nameofser+=serviceName.get(j+1).toString()+", ";
    }
        else{
        freelancerId1.add(freelancerId.get(j).toString());
        freelancerName1.add(freelancerName.get(j).toString());
        freelancerPh1.add(freelancerPh.get(j).toString());
        freelancerAltPh1.add(freelancerAltPh.get(j).toString());
        freelanceremail1.add(freelanceremail.get(j).toString());
        country1.add(country.get(j).toString());
        serviceName1.add(nameofser);
//System.out.println(freelancerName.get(j).toString());
//System.out.println(freelancerPh.get(j).toString());
//System.out.println(freelancerAltPh.get(j).toString());
//System.out.println(nameofser);
nameofser="";
 chk=0;
 }
    }
}
catch(Exception e){
}}

}
}

