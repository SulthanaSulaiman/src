/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfinder.functions;

import java.text.DateFormatSymbols;

/**
 *
 * @author Aravindhanj
 */
public class BaseClass {
    
    
    public String getMonthForInt(int m) {
    String month = "invalid";
    DateFormatSymbols dfs = new DateFormatSymbols();
    String[] months = dfs.getMonths();
    if(m==0)
    {
        month="due date not entered yet";
    }
   else if (m >= 1 && m <= 12 ) {
        month = months[m-1];
    }
    return month;
}

    
    
}
