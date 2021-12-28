/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.util;

/**
 *
 * @author Raghuramanm
 */
public class SpecialCharHandler {
    public String encoding(String encodedString)
    {
        if(encodedString!=null){
            try{
              if(encodedString.contains("\"")){
                encodedString = encodedString.replaceAll("\"", "%22");
              }
              if(encodedString.contains("'")){
                encodedString = encodedString.replaceAll("'", "%27");
              }
               if(encodedString.contains("&")){
                encodedString = encodedString.replaceAll("&", "%26");
              }
            }catch(Exception e){
              System.out.println("Exception in encoding "+e);
            }
        }
        return encodedString;
    }
    public String decoding(String decodedString)
    {
        if(decodedString!=null){
            try{
                if(decodedString.contains("%22")){
                    decodedString = decodedString.replaceAll("%22", "\"");
                }
                if(decodedString.contains("%27")){
                    decodedString = decodedString.replaceAll("%27", "'");
                }
                 if(decodedString.contains("%26")){
                decodedString = decodedString.replaceAll("%26", "&");
              }
            }catch(Exception e){
                System.out.println("Exception in decoding "+e);
            }
        }
        return decodedString;
    }

    public String escapeSplChars(String string)
    {
       if(string!=null){
           try{
               if(string.contains("'")) {
                string= string.replaceAll("'", "\\\\'");

               }
               if(string.contains("\"")){
                string= string.replaceAll("\"", "\\\\\"");
               }
          }catch(Exception e){
               System.out.println("Exception in escapeSplChars "+e);
          }
        }
      return string  ;

    }

    public String escapeSplCharsHTML(String string)
    {
      if(string!=null){
          try{
              if(string.contains("'"))
              {
                string= string.replaceAll("'", "&#39;");
              }
              if(string.contains("\""))
              {
                  string= string.replaceAll("\"", "&quot;");
              }
          }catch(Exception e){
              System.out.println("Exception in escapeSplCharsHTML "+e);
          }
      }
      return string  ;
    }

    public String ignoreSplChars(String string)
    {
        if(string!=null){
            try{
                string = string.replaceAll("[\'\":*<>|\\\\/?]+", "");
                //string = string.replaceAll("[~`!@#$%^&*()-_=+\\\\/|}{;:\'\"?.>,<&]+", "");
                //System.out.println("The ignored string "+string);
            }catch(Exception e){
                System.out.println("Exception in ignoreSplChars "+e);
            }
        }
        return string;
    }
}
