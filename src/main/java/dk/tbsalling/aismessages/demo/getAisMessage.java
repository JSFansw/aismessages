package dk.tbsalling.aismessages.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import org.ietf.jgss.Oid;

public class getAisMessage {
	
	public String getString() throws IOException{
		String aismessage=null;
		File myFile=new File("/home/fansw/Documents/AIS/s10.txt");
		  BufferedReader in = new BufferedReader(new FileReader(myFile));
		  String str;
          while ((str = in.readLine()) != null) 
          {
              //  System.out.println(str);
        	  if((str.startsWith("!")||str.startsWith("$"))&&!str.endsWith("]")&&!str.startsWith("$ABVSI"))
        		//  System.out.println(str);
        	 aismessage+=str+"\n";
          }
          in.close();
		return aismessage;
	}

	public void outToFile(String value) throws IOException{
		File outFile=new File("/home/fansw/Documents/AIS/result.txt");
		FileOutputStream txtfile = new FileOutputStream(outFile);
		PrintStream p = new PrintStream(txtfile);
		p.println(value);
		txtfile.close();
		p.close();
	}
	public static void main(String[] args) throws IOException{
	//	new getAisMessage().getString();
		new getAisMessage().outToFile("hello");
	}
}
