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
       "!ABVDM,1,1,1,B,16:>;3PP!ABVDM,1,1,3,A,403soaQuvD1R`8RfA:BKkl700@GO,0*47\n"+
       "!ABVDM,1,1,6,B,H6:b8iU61=3PPPPhPPPPPP7p:670,0*17\n"+
       "!ABVDM,1,1,1,B,16990d001s`jSqNE7qRuwP3R2<9M,0*70\n"+
       "!ABVDM,1,1,8,A,B6:W20000:97L14WqwaJ18N021Mk,0*6B\n"+
       "!ABVDM,1,1,9,B,C6:lSMP0CB9q;KTo31O@NVoRVc0HB2M0@2C1ii111110S4@7T3P7,0*31\n"+
       "!ABVDM,1,1,4,B,B1P>Llh08r96g84ts0PRSwo6SP06,0*1C\n"+
       "!ABVDM,1,1,1,B,B=JEEmh0<B;C6VTUo>J;3woSQP06,0*27\n"+
       "!ABVDM,1,1,3,A,16:tft0024`TqvpBGqJ6LE:@0d68,0*21\n"+
       "!ABVDM,1,1,2,A,15@gTj0PAb8cEt>B448Lfr;R0HLV,0*3C\n"+
       "!ABVDM,1,1,6,A,168tHcP01J`TFv>CfWBAIiAP2HLU,0*79\n"+
       "!ABVDM,1,1,1,B,16:>;3PP!ABVDM,1,1,3,A,403soaQuvD1R`8RfA:BKkl700@GO,0*47\n"+
       "!ABVDM,1,1,6,B,H6:b8iU61=3PPPPhPPPPPP7p:670,0*17\n"+
       "!ABVDM,1,1,1,B,16990d001s`jSqNE7qRuwP3R2<9M,0*70\n"+
       "!ABVDM,1,1,8,A,B6:W20000:97L14WqwaJ18N021Mk,0*6B\n"+
       "!ABVDM,1,1,9,B,C6:lSMP0CB9q;KTo31O@NVoRVc0HB2M0@2C1ii111110S4@7T3P7,0*31\n"+
       "!ABVDM,1,1,4,B,B1P>Llh08r96g84ts0PRSwo6SP06,0*1C\n"+
       "!ABVDM,1,1,1,B,B=JEEmh0<B;C6VTUo>J;3woSQP06,0*27\n"+
       "!ABVDM,1,1,3,A,16:tft0024`TqvpBGqJ6LE:@0d68,0*21\n"+
       "!ABVDM,1,1,2,A,15@gTj0PAb8cEt>B448Lfr;R0HLV,0*3C\n"+
       "!ABVDM,1,1,6,A,168tHcP01J`TFv>CfWBAIiAP2HLU,0*79\n"+
       "!ABVDM,1,1,1,B,16:>;3PP!ABVDM,1,1,3,A,403soaQuvD1R`8RfA:BKkl700@GO,0*47\n"+
       "!ABVDM,1,1,6,B,H6:b8iU61=3PPPPhPPPPPP7p:670,0*17\n"+
       "!ABVDM,1,1,1,B,16990d001s`jSqNE7qRuwP3R2<9M,0*70\n"+
       "!ABVDM,1,1,8,A,B6:W20000:97L14WqwaJ18N021Mk,0*6B\n"+
       "!ABVDM,1,1,9,B,C6:lSMP0CB9q;KTo31O@NVoRVc0HB2M0@2C1ii111110S4@7T3P7,0*31\n"+
       "!ABVDM,1,1,4,B,B1P>Llh08r96g84ts0PRSwo6SP06,0*1C\n"+
       "!ABVDM,1,1,1,B,B=JEEmh0<B;C6VTUo>J;3woSQP06,0*27\n"+
       "!ABVDM,1,1,3,A,16:tft0024`TqvpBGqJ6LE:@0d68,0*21\n"+
       "!ABVDM,1,1,2,A,15@gTj0PAb8cEt>B448Lfr;R0HLV,0*3C\n"+
       "!ABVDM,1,1,6,A,168tHcP01J`TFv>CfWBAIiAP2HLU,0*79\n"+
       "!ABVDM,1,1,1,B,16:>;3PP!ABVDM,1,1,3,A,403soaQuvD1R`8RfA:BKkl700@GO,0*47\n"+
       "!ABVDM,1,1,6,B,H6:b8iU61=3PPPPhPPPPPP7p:670,0*17\n"+
       "!ABVDM,1,1,1,B,16990d001s`jSqNE7qRuwP3R2<9M,0*70\n"+
       "!ABVDM,1,1,8,A,B6:W20000:97L14WqwaJ18N021Mk,0*6B\n"+
       "!ABVDM,1,1,9,B,C6:lSMP0CB9q;KTo31O@NVoRVc0HB2M0@2C1ii111110S4@7T3P7,0*31\n"+
       "!ABVDM,1,1,4,B,B1P>Llh08r96g84ts0PRSwo6SP06,0*1C\n"+
       "!ABVDM,1,1,1,B,B=JEEmh0<B;C6VTUo>J;3woSQP06,0*27\n"+
       "!ABVDM,1,1,3,A,16:tft0024`TqvpBGqJ6LE:@0d68,0*21\n"+
       "!ABVDM,1,1,2,A,15@gTj0PAb8cEt>B448Lfr;R0HLV,0*3C\n"+
       "!ABVDM,1,1,6,A,168tHcP01J`TFv>CfWBAIiAP2HLU,0*79\n"+
       "!ABVDM,1,1,1,B,16:>;3PP!ABVDM,1,1,3,A,403soaQuvD1R`8RfA:BKkl700@GO,0*47\n"+
       "!ABVDM,1,1,6,B,H6:b8iU61=3PPPPhPPPPPP7p:670,0*17\n"+
       "!ABVDM,1,1,1,B,16990d001s`jSqNE7qRuwP3R2<9M,0*70\n"+
       "!ABVDM,1,1,8,A,B6:W20000:97L14WqwaJ18N021Mk,0*6B\n"+
       "!ABVDM,1,1,9,B,C6:lSMP0CB9q;KTo31O@NVoRVc0HB2M0@2C1ii111110S4@7T3P7,0*31\n"+
       "!ABVDM,1,1,4,B,B1P>Llh08r96g84ts0PRSwo6SP06,0*1C\n"+
       "!ABVDM,1,1,1,B,B=JEEmh0<B;C6VTUo>J;3woSQP06,0*27\n"+
       "!ABVDM,1,1,3,A,16:tft0024`TqvpBGqJ6LE:@0d68,0*21\n"+
       "!ABVDM,1,1,2,A,15@gTj0PAb8cEt>B448Lfr;R0HLV,0*3C\n"+
       "!ABVDM,1,1,6,A,168tHcP01J`TFv>CfWBAIiAP2HLU,0*79\n"+
       "!ABVDM,1,1,1,B,16:>;3PP!ABVDM,1,1,3,A,403soaQuvD1R`8RfA:BKkl700@GO,0*47\n"+
       "!ABVDM,1,1,6,B,H6:b8iU61=3PPPPhPPPPPP7p:670,0*17\n"+
       "!ABVDM,1,1,1,B,16990d001s`jSqNE7qRuwP3R2<9M,0*70\n"+
       "!ABVDM,1,1,8,A,B6:W20000:97L14WqwaJ18N021Mk,0*6B\n"+
       "!ABVDM,1,1,9,B,C6:lSMP0CB9q;KTo31O@NVoRVc0HB2M0@2C1ii111110S4@7T3P7,0*31\n"+
       "!ABVDM,1,1,4,B,B1P>Llh08r96g84ts0PRSwo6SP06,0*1C\n"+
       "!ABVDM,1,1,1,B,B=JEEmh0<B;C6VTUo>J;3woSQP06,0*27\n"+
       "!ABVDM,1,1,3,A,16:tft0024`TqvpBGqJ6LE:@0d68,0*21\n"+
       "!ABVDM,1,1,2,A,15@gTj0PAb8cEt>B448Lfr;R0HLV,0*3C\n"+
       "!ABVDM,1,1,6,A,168tHcP01J`TFv>CfWBAIiAP2HLU,0*79\n"+
       "!ABVDM,1,1,1,B,16:>;3PP!ABVDM,1,1,3,A,403soaQuvD1R`8RfA:BKkl700@GO,0*47\n"+
       "!ABVDM,1,1,6,B,H6:b8iU61=3PPPPhPPPPPP7p:670,0*17\n"+
       "!ABVDM,1,1,1,B,16990d001s`jSqNE7qRuwP3R2<9M,0*70\n"+
       "!ABVDM,1,1,8,A,B6:W20000:97L14WqwaJ18N021Mk,0*6B\n"+
       "!ABVDM,1,1,9,B,C6:lSMP0CB9q;KTo31O@NVoRVc0HB2M0@2C1ii111110S4@7T3P7,0*31\n"+
       "!ABVDM,1,1,4,B,B1P>Llh08r96g84ts0PRSwo6SP06,0*1C\n"+
       "!ABVDM,1,1,1,B,B=JEEmh0<B;C6VTUo>J;3woSQP06,0*27\n"+
       "!ABVDM,1,1,3,A,16:tft0024`TqvpBGqJ6LE:@0d68,0*21\n"+
       "!ABVDM,1,1,2,A,15@gTj0PAb8cEt>B448Lfr;R0HLV,0*3C\n"+
       "!ABVDM,1,1,6,A,168tHcP01J`TFv>CfWBAIiAP2HLU,0*79\n"+
       "!ABVDM,1,1,1,B,16:>;3PP!ABVDM,1,1,3,A,403soaQuvD1R`8RfA:BKkl700@GO,0*47\n"+
       "!ABVDM,1,1,6,B,H6:b8iU61=3PPPPhPPPPPP7p:670,0*17\n"+
       "!ABVDM,1,1,1,B,16990d001s`jSqNE7qRuwP3R2<9M,0*70\n"+
       "!ABVDM,1,1,8,A,B6:W20000:97L14WqwaJ18N021Mk,0*6B\n"+
       "!ABVDM,1,1,9,B,C6:lSMP0CB9q;KTo31O@NVoRVc0HB2M0@2C1ii111110S4@7T3P7,0*31\n"+
       "!ABVDM,1,1,4,B,B1P>Llh08r96g84ts0PRSwo6SP06,0*1C\n"+
       "!ABVDM,1,1,1,B,B=JEEmh0<B;C6VTUo>J;3woSQP06,0*27\n"+
       "!ABVDM,1,1,3,A,16:tft0024`TqvpBGqJ6LE:@0d68,0*21\n"+
       "!ABVDM,1,1,2,A,15@gTj0PAb8cEt>B448Lfr;R0HLV,0*3C\n"+
       "!ABVDM,1,1,6,A,168tHcP01J`TFv>CfWBAIiAP2HLU,0*79\n"+
       "!ABVDM,1,1,1,B,16:>;3PP!ABVDM,1,1,3,A,403soaQuvD1R`8RfA:BKkl700@GO,0*47\n"+
       "!ABVDM,1,1,6,B,H6:b8iU61=3PPPPhPPPPPP7p:670,0*17\n"+
       "!ABVDM,1,1,1,B,16990d001s`jSqNE7qRuwP3R2<9M,0*70\n"+
       "!ABVDM,1,1,8,A,B6:W20000:97L14WqwaJ18N021Mk,0*6B\n"+
       "!ABVDM,1,1,9,B,C6:lSMP0CB9q;KTo31O@NVoRVc0HB2M0@2C1ii111110S4@7T3P7,0*31\n"+
       "!ABVDM,1,1,4,B,B1P>Llh08r96g84ts0PRSwo6SP06,0*1C\n"+
       "!ABVDM,1,1,1,B,B=JEEmh0<B;C6VTUo>J;3woSQP06,0*27\n"+
       "!ABVDM,1,1,3,A,16:tft0024`TqvpBGqJ6LE:@0d68,0*21\n"+
       "!ABVDM,1,1,2,A,15@gTj0PAb8cEt>B448Lfr;R0HLV,0*3C\n"+
       "!ABVDM,1,1,6,A,168tHcP01J`TFv>CfWBAIiAP2HLU,0*79\n"+
       "!ABVDM,1,1,1,B,16:>;3PP!ABVDM,1,1,3,A,403soaQuvD1R`8RfA:BKkl700@GO,0*47\n"+
       "!ABVDM,1,1,6,B,H6:b8iU61=3PPPPhPPPPPP7p:670,0*17\n"+
       "!ABVDM,1,1,1,B,16990d001s`jSqNE7qRuwP3R2<9M,0*70\n"+
       "!ABVDM,1,1,8,A,B6:W20000:97L14WqwaJ18N021Mk,0*6B\n"+
       "!ABVDM,1,1,9,B,C6:lSMP0CB9q;KTo31O@NVoRVc0HB2M0@2C1ii111110S4@7T3P7,0*31\n"+
       "!ABVDM,1,1,4,B,B1P>Llh08r96g84ts0PRSwo6SP06,0*1C\n"+
       "!ABVDM,1,1,1,B,B=JEEmh0<B;C6VTUo>J;3woSQP06,0*27\n"+
       "!ABVDM,1,1,3,A,16:tft0024`TqvpBGqJ6LE:@0d68,0*21\n"+
       "!ABVDM,1,1,2,A,15@gTj0PAb8cEt>B448Lfr;R0HLV,0*3C\n"+
       "!ABVDM,1,1,6,A,168tHcP01J`TFv>CfWBAIiAP2HLU,0*79\n"
        		

    );

}