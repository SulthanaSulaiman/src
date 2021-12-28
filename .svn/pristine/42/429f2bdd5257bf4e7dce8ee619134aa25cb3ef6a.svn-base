/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.generic;

import java.util.*;
/**
 *
 * @author ramesh
 */
public class PageAuthentication {

    private String pageName="";
    private Map pageNameList=new TreeMap();
    private String accessType = "";
    private String alertMessage = "";

    private List chkPageNameList = new ArrayList();
    private List chkPageAccessList = new ArrayList();

    public void setPageName(String pageName){
        this.pageName=pageName;
    }

    public String getPageAccessType(){
        return accessType;
    }

    public String getAlertMessage(){
        return alertMessage;
    }

public void setPageNameList(Map pageNameList)
{
    this.pageNameList=pageNameList;
}

public void checkAccessType(){

    try{
        String getPageName = "";
        String getPageAccessType = "";
        Set pageNameSet = pageNameList.entrySet();
        Iterator pgNameItr = pageNameSet.iterator();
        Map.Entry pageNameEntry;

        while(pgNameItr.hasNext()){
            pageNameEntry = (Map.Entry) pgNameItr.next();
            getPageName = (String) pageNameEntry.getKey();
            getPageAccessType = (String) pageNameEntry.getValue();
            chkPageNameList.add(getPageName);
            chkPageAccessList.add(getPageAccessType);
        }

getPageAccessType="";
//System.out.println("pageName:"+pageName);
//System.out.println("pageName Present:"+chkPageNameList.contains(pageName));


        if(chkPageNameList.contains(pageName)){
            int pgNameIndex = chkPageNameList.indexOf(pageName);
            getPageAccessType = chkPageAccessList.get(pgNameIndex).toString();
            accessType=chkPageAccessList.get(pgNameIndex).toString();
            alertMessage="";//this blank allocation is needed for an IF condition to work in the jsp page
        }
         else{
            alertMessage="You are not authorized to view this page";
           // accessType="";//this blank allocation is needed for an IF condition to work in the jsp page
         }

  /*  System.out.println("accessType:"+accessType);
    System.out.println("chkPageAccessList :"+chkPageAccessList);
    System.out.println("chkPageNameList:"+chkPageNameList);
    System.out.println("getPageAccessType :"+getPageAccessType);

    System.out.println("pageNameList Size:"+pageNameList.size());
    System.out.println("pageNameList :"+pageNameList);*/
    }catch(Exception e)
    {
        e.printStackTrace();
    }
}




}
