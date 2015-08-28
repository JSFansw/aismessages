/*
 * AISMessages
 * - a java-based library for decoding of AIS messages from digital VHF radio traffic related
 * to maritime navigation and safety in compliance with ITU 1371.
 * 
 * (C) Copyright 2011- by S-Consult ApS, DK31327490, http://s-consult.dk, Denmark.
 * 
 * Released under the Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
 * For details of this license see the nearby LICENCE-full file, visit http://creativecommons.org/licenses/by-nc-sa/3.0/
 * or send a letter to Creative Commons, 171 Second Street, Suite 300, San Francisco, California, 94105, USA.
 * 
 * NOT FOR COMMERCIAL USE!
 * Contact sales@s-consult.dk to obtain a commercially licensed version of this software.
 * 
 */

package dk.tbsalling.aismessages.demo;

import dk.tbsalling.aismessages.AISInputStreamReader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class SimpleDemoApp {

    public void runDemo() throws IOException {

        InputStream inputStream = new ByteArrayInputStream(demoNmeaStrings.getBytes());

		System.out.println("AISMessages Demo App");
		System.out.println("--------------------");

        AISInputStreamReader streamReader = new AISInputStreamReader(inputStream, aisMessage ->
            System.out.println("Received AIS message from MMSI " + aisMessage.getSourceMmsi().getMMSI() + ": " + aisMessage)
        );
//		 AISInputStreamReader streamReader = new AISInputStreamReader(inputStream, aisMessage ->
//         {
//        	 System.out.println(aisMessage.getMessageType());
//         }
//     );

        streamReader.run();
	}

    public static void main(String[] args) throws IOException {
		new SimpleDemoApp().runDemo();
	}

    private final String demoNmeaStrings = new String(
    		
 //       "!AIVDM,1,1,,A,18UG;P0012G?Uq4EdHa=c;7@051@,0*53\n" +
  //      "!ABVDM,1,1,8,B,t0001AP012:?AH4To?8?WwWRP<00,0*33"
       "!AIVDM,2,1,0,B,54Wuq@02<@qu`<@7T00Lth@Dr118Tp<E=<0000154cuBB6;fNEPklSUh,0*3C"+
       "!AIVDM,2,2,0,A,H88888888888880,2*54"
		
        		

    );

}