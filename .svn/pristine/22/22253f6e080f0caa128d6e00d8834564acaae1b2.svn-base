/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pathfinder.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
/**
 *
 * @author Gandhimathidevic
 */
public class CopyFileExample {

    public void copyfiles(String afile1, String bfile1){
        InputStream inStream = null;
	OutputStream outStream = null;
//System.out.println("afile1"+afile1);
    	try{

    	    File afile =new File(afile1);
    	    File bfile =new File(bfile1);

    	    inStream = new FileInputStream(afile);
    	    outStream = new FileOutputStream(bfile);

    	    byte[] buffer = new byte[1024];

    	    int length;
    	    //copy the file content in bytes
    	    while ((length = inStream.read(buffer)) > 0){

    	    	outStream.write(buffer, 0, length);

    	    }

    	    inStream.close();
    	    outStream.close();

    	    System.out.println("File is copied successful!");

    	}catch(IOException e){
    		e.printStackTrace();
    	}}
}
