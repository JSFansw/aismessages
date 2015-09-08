/*
 * AISMessages
 * - a java-based library for decoding of AIS messages from digital VHF radio traffic related
 * to maritime navigation and safety in compliance with ITU 1371.
 * 
 * (C) Copyright 2011-2013 by S-Consult ApS, DK31327490, http://s-consult.dk, Denmark.
 * 
 * Released under the Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
 * For details of this license see the nearby LICENCE-full file, visit http://creativecommons.org/licenses/by-nc-sa/3.0/
 * or send a letter to Creative Commons, 171 Second Street, Suite 300, San Francisco, California, 94105, USA.
 * 
 * NOT FOR COMMERCIAL USE!
 * Contact sales@s-consult.dk to obtain a commercially licensed version of this software.
 * 
 */

package dk.tbsalling.aismessages.nmea.messages;

import dk.tbsalling.aismessages.nmea.exceptions.NMEAParseException;
import dk.tbsalling.aismessages.nmea.exceptions.UnsupportedMessageType;

import java.io.Serializable;

/*
 * AISMessages
 * - a java-based library for decoding of AIS messages from digital VHF radio traffic related
 * to maritime navigation and safety in compliance with ITU 1371.
 * 
 * (C) Copyright 2011-2013 by S-Consult ApS, DK31327490, http://s-consult.dk, Denmark.
 * 
 * Released under the Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
 * For details of this license see the nearby LICENCE-full file, visit http://creativecommons.org/licenses/by-nc-sa/3.0/
 * or send a letter to Creative Commons, 171 Second Street, Suite 300, San Francisco, California, 94105, USA.
 * 
 * NOT FOR COMMERCIAL USE!
 * Contact sales@s-consult.dk to obtain a commercially licensed version of this software.
 * 
 */

// TODO optimize getters
public class NMEAMessage implements Serializable {

	/**
	 * 根据字符串生成NMEAMessage对象
	 * @param nmeaString 用于生成NMEAMessage对象的原始数据
	 * @return NMEAMessage
	 */
	public static NMEAMessage fromString(String nmeaString) {
		return new NMEAMessage(nmeaString);
	}

	/**
	 * 
	 * @return Boolean 对数据合法性的判断
	 */
	public final boolean isValid() {
        String messageType = getMessageType();
        if(messageType == null || messageType.length() != 5) return false;		
		String type = messageType.substring(2);
		if (! ("VDM".equals(type) || "VDO".equals(type))) {
			return false;
		}
		int CRCString=getChecksum();
		if(calcCRC()!=getChecksum()){
			return false;
		}
		return true;
	}

	/**
	 *  对数据进行CRC校验，计算CRC值
	 * @param ais 原始数据
	 * @return 计算出来的CRC校验值
	 */
	public  int calcCRC( ) {
		byte[] data = null;
		if (rawMessage.contains("!") && rawMessage.contains("*")) {
			data = rawMessage.substring(1, rawMessage.indexOf("*")).getBytes();
		} else {
			data = rawMessage.getBytes();
		}
		int crc = 0;
		for (byte pos : data) {
			if (crc == 0) {
				crc = pos;
			} else {
				crc ^= pos;
			}
		}
//		String result = String.format("%1$02X", crc);
//		System.out.println("calcCRC--"+result);
		//logger.info("calcCRC(ais) - Exit");
		return crc;
	}
	
    @SuppressWarnings("unused")
	public String getMessageType() {
        String[] msg = rawMessage.split(",");
		return isBlank(msg[0]) ? null : msg[0].replace("!", "");
	}

    @SuppressWarnings("unused")
    public Integer getNumberOfFragments() {
        String[] msg = rawMessage.split(",");
        return isBlank(msg[1]) ? null : Integer.valueOf(msg[1]);
	}

    /**
     *  获取一条完整数据包含条数的标识
     * @return
     */
    @SuppressWarnings("unused")
    public Integer getFragmentNumber() {
        String[] msg = rawMessage.split(",");
        return isBlank(msg[2]) ? null : Integer.valueOf(msg[2]);
	}

    @SuppressWarnings("unused")
    public Integer getSequenceNumber() {
        String[] msg = rawMessage.split(",");
        return isBlank(msg[3]) ? null : Integer.valueOf(msg[3]);
	}

    @SuppressWarnings("unused")
    public String getRadioChannelCode() {
        String[] msg = rawMessage.split(",");
        return isBlank(msg[4]) ? null : msg[4];
	}

    @SuppressWarnings("unused")
    public String getEncodedPayload() {
        String[] msg = rawMessage.split(",");
        return isBlank(msg[5]) ? null : msg[5];
	}

    @SuppressWarnings("unused")
    public Integer getFillBits() {
        String[] msg = rawMessage.split(",");
        String msg1[] = msg[6].split("\\*");
        return isBlank(msg1[0]) ? null : Integer.valueOf(msg1[0]);
	}

    @SuppressWarnings("unused")
    public Integer getChecksum() {
        String[] msg = rawMessage.split(",");
        String msg1[] = msg[msg.length-1].split("\\*");
		return isBlank(msg1[1]) ? null : Integer.valueOf(msg1[1], 16);
		
		//!ABVDM,1,1,1,B,16:>;3PP!ABVDM,1,1,3,A,403soaQuvD1R`8RfA:BKkl700@GO,0*47
	}

    @SuppressWarnings("unused")
    public String getRawMessage() {
		return rawMessage;
	}

	private NMEAMessage(String rawMessage) {
        this.rawMessage = rawMessage;
        validate();
	}

	/**
	 *  对数据进行格式校验
	 */
	private void validate() {
        // !AIVDM,1,1,,B,15MvlfPOh2G?nwbEdVDsnSTR00S?,0*41

		if(!isValid()) {
			throw new UnsupportedMessageType(getMessageType());
		}

        final String nmeaMessageRegExp = "^!.*\\*[0-9A-Fa-f]{2}$";

        if (! rawMessage.matches(nmeaMessageRegExp))
            throw new NMEAParseException(rawMessage, "Message does not comply with regexp \"" + nmeaMessageRegExp + "\"");

        String[] msg = rawMessage.split(",");
        if (msg.length != 7)
            throw new NMEAParseException(rawMessage, "Expected 7 fields separated by commas; got " + msg.length);

        String msg1[] = msg[6].split("\\*");
        if (msg1.length != 2)
            throw new NMEAParseException(rawMessage, "Expected checksum fields to start with *");
    }

    @Override
    public String toString() {
        return "NMEAMessage{" +
                "rawMessage='" + rawMessage + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NMEAMessage that = (NMEAMessage) o;

        if (!rawMessage.equals(that.rawMessage)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return rawMessage.hashCode();
    }

    /**
     * 判断字符串是不是空
     * @param s 需要验证的字符串
     * @return 是否为空
     */
    private static boolean isBlank(String s) {
		return s == null || s.trim().length() == 0;
	}

	private final String rawMessage;
}
