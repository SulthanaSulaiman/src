/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.functions.depttarget;

import java.beans.*;
import java.io.Serializable;
import java.util.*;
/**
 *
 * @author ramesh
 */
public class StringToArrayList implements Serializable {

    
    private String[] tempArr;
    private String srcString="";
    private List resultList=new ArrayList();
    private List resultStringArrList=new ArrayList();//IF the input is the arrylist whose elements are string arrays by themself then this varaible should be used and returned
  
    public List getResultList() {
        return resultList;
    }

    public List getResultArrList() {
        return resultStringArrList;
    }

    public void setSourceString(String srcString) {
       this.srcString = srcString;
       //System.out.println("srcString:"+srcString);
       srcString=srcString.substring(1,srcString.length()-1);
       tempArr = srcString.split(",");
       resultList= Arrays.asList(tempArr);
    
    }

 


 /*  public void setSourceStringArr(List resultList) {
       String[] stgArr;
       for(int idx=0;idx<resultList.size();idx++){
               stgArr=(String[]) resultList.get(idx);
               for(int i=0;i<stgArr.length;i++){
                   resultStringArrList.add(stgArr[i]);
               }
       }
   }*/


}
