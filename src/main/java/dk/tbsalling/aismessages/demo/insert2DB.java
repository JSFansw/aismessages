package dk.tbsalling.aismessages.demo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.omg.CORBA.PRIVATE_MEMBER;

import dk.tbsalling.aismessages.ais.exceptions.UnsupportedMessageType;
import dk.tbsalling.aismessages.ais.messages.AISMessage;
import dk.tbsalling.aismessages.ais.messages.BaseStationReport;
import dk.tbsalling.aismessages.ais.messages.ShipAndVoyageData;
import dk.tbsalling.aismessages.ais.messages.types.AISMessageType;
import dk.tbsalling.aismessages.nmea.ConnectionPool;

public class insert2DB {
	
public AISMessage aisMessage;
	public insert2DB(AISMessage aisMessage){
		this.aisMessage=aisMessage;		
	}
	
	public insert2DB(){
		
	}
	
	public void insert(){
		int year=0,month=0,day=0,hour=0,minuter=0,second=0;
		// 将aismessage存入数据库中
		AISMessageType messageType = aisMessage.getMessageType();
		// 判断aisMessage是哪一种类型
		if (messageType != null) {
			switch (messageType) {
			case ShipAndVoyageRelatedData:
				ShipAndVoyageData shipAndVoyageData=(ShipAndVoyageData)aisMessage;
				
				
				// aisMessageConstructor = ShipAndVoyageData::new;
				break;
			case PositionReportClassAScheduled:
				/// aisMessageConstructor = PositionReportClassAScheduled::new;
				break;
			case PositionReportClassAAssignedSchedule:
				// aisMessageConstructor =
				// PositionReportClassAAssignedSchedule::new;
				break;
			case PositionReportClassAResponseToInterrogation:
				// aisMessageConstructor =
				// PositionReportClassAResponseToInterrogation::new;
				break;
			case BaseStationReport:		
			//	String dateStr=getDate(aisMessage);
				BaseStationReport baseStationReport = (BaseStationReport) aisMessage;
				year = baseStationReport.getYear();
				month = baseStationReport.getMonth();
				day = baseStationReport.getDay();
				hour = baseStationReport.getHour();
				minuter = baseStationReport.getMinute();
				second = baseStationReport.getSecond();
				Integer mmsi = baseStationReport.getSourceMmsi().getMMSI();
				float latitude = baseStationReport.getLatitude();
				float longtitude = baseStationReport.getLongitude();
				String dateStr = year + "-" + month + "-" + day + " " + hour + ":" + minuter + ":" + second;
				insert2baseStation(dateStr,mmsi,latitude,longtitude);
				break;
			case AddressedBinaryMessage:
				// aisMessageConstructor = AddressedBinaryMessage::new;
				break;
			case BinaryAcknowledge:
				// aisMessageConstructor = BinaryAcknowledge::new;
				break;
			case BinaryBroadcastMessage:
				// aisMessageConstructor = BinaryBroadcastMessage::new;
				break;
			case StandardSARAircraftPositionReport:
				// aisMessageConstructor =
				// StandardSARAircraftPositionReport::new;
				break;
			case UTCAndDateInquiry:
				// aisMessageConstructor = UTCAndDateInquiry::new;
				break;
			case UTCAndDateResponse:
				// aisMessageConstructor = UTCAndDateResponse::new;
				break;
			case AddressedSafetyRelatedMessage:
				// aisMessageConstructor = AddressedSafetyRelatedMessage::new;
				break;
			case SafetyRelatedAcknowledge:
				// aisMessageConstructor = SafetyRelatedAcknowledge::new;
				break;
			case SafetyRelatedBroadcastMessage:
				// aisMessageConstructor = SafetyRelatedBroadcastMessage::new;
				break;
			case Interrogation:
				// aisMessageConstructor = Interrogation::new;
				break;
			case AssignedModeCommand:
				// aisMessageConstructor = AssignedModeCommand::new;
				break;
			case GNSSBinaryBroadcastMessage:
				// aisMessageConstructor = GNSSBinaryBroadcastMessage::new;
				break;
			case StandardClassBCSPositionReport:
				// aisMessageConstructor = StandardClassBCSPositionReport::new;
				break;
			case ExtendedClassBEquipmentPositionReport:
				// aisMessageConstructor =
				// ExtendedClassBEquipmentPositionReport::new;
				break;
			case DataLinkManagement:
				// aisMessageConstructor = DataLinkManagement::new;
				break;
			case AidToNavigationReport:
				// aisMessageConstructor = AidToNavigationReport::new;
				break;
			case ChannelManagement:
				// aisMessageConstructor = ChannelManagement::new;
				break;
			case GroupAssignmentCommand:
				// aisMessageConstructor = GroupAssignmentCommand::new;
				break;
			case ClassBCSStaticDataReport:
				// aisMessageConstructor = ClassBCSStaticDataReport::new;
				break;
			case BinaryMessageSingleSlot:
				/// aisMessageConstructor = BinaryMessageSingleSlot::new;
				break;
			case BinaryMessageMultipleSlot:
				// aisMessageConstructor = BinaryMessageMultipleSlot::new;
				break;
			case LongRangeBroadcastMessage:
				// aisMessageConstructor = LongRangeBroadcastMessage::new;
				break;
			default:
				throw new UnsupportedMessageType(messageType.getCode());
			}
		}
	}
	//获取各种类型时间
	private String  getDate(AISMessage aisMessage){
		int year=0,month=0,day=0,hour=0,minuter=0,second=0;
		if (aisMessage instanceof BaseStationReport) {
			BaseStationReport message = (BaseStationReport) aisMessage;
			year = message.getYear();
			month = message.getMonth();
			day = message.getDay();
			hour = message.getHour();
			minuter = message.getMinute();
			second = message.getSecond();
		}
		String dateStr = year + "-" + month + "-" + day + " " + hour + ":" + minuter + ":" + second;
		return dateStr;
		
	}
	//向基站表写数据
	public void insert2baseStation(String dateStr,int mmsi,float latitude,float longitude){
		String sqlStr = "insert into ais_basestation (time,mmsi,latitude,longitude) values('" + dateStr + "'," + mmsi
				+ "," + latitude + "," + longitude + ")";		
		ConnectionPool connPool=ConnectionPool.getInstance();	
		try {
			connPool.createPool();
			Connection conn =connPool.getConnection();
			Statement stm = conn.createStatement();
			stm.execute(sqlStr);
			stm.close();
			conn.close();
			connPool.returnConnection(conn); // 连接使用完后释放连接到连接池
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//向位置表中写数据
	public void insert2Position(String dateStr,int mmsi,float latitude,float longitude,float heading,float course,float speed){
		String sqlStr = "insert into ais_position (time,mmsi,latitude,longitude,heading,course,speed) values('" + dateStr + "'," + mmsi
				+ "," + latitude + "," + longitude +  "," + heading + "," + course + "," + speed +")";		
		ConnectionPool connPool=ConnectionPool.getInstance();	
		try {
			connPool.createPool();
			Connection conn =connPool.getConnection();
			Statement stm = conn.createStatement();
			stm.execute(sqlStr);
			stm.close();
			conn.close();
			connPool.returnConnection(conn); // 连接使用完后释放连接到连接池
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//向船信息表中写数据
	public  void insert2vessl(String dateStr,int mmsi,String name,String  destination,float draught ){
		String sqlStr = "insert into ais_vesseldata (time,mmsi,name,destination,draught) values('" + dateStr + "'," + mmsi
				+ ",'" + name + "','" + destination +  "'," + draught+")";		
		ConnectionPool connPool=ConnectionPool.getInstance();	
		try {
			connPool.createPool();
			Connection conn =connPool.getConnection();
			Statement stm = conn.createStatement();
			stm.execute(sqlStr);
			stm.close();
			conn.close();
			connPool.returnConnection(conn); // 连接使用完后释放连接到连接池
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
