package dk.tbsalling.aismessages;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.function.Consumer;

import dk.tbsalling.aismessages.ais.exceptions.UnsupportedMessageType;
import dk.tbsalling.aismessages.ais.messages.AISMessage;
import dk.tbsalling.aismessages.ais.messages.BaseStationReport;
import dk.tbsalling.aismessages.ais.messages.types.AISMessageType;
import dk.tbsalling.aismessages.nmea.ConnectionPool;

import dk.tbsalling.aismessages.nmea.messages.NMEAMessage;

public class getAisFromDB implements Consumer<AISMessage> {
	Consumer<? super NMEAMessage> nmeaMessageHandler;
	ConnectionPool connPool;

	// 重写accept方法，对返回aisMessage 进行处理
	@Override
	public void accept(AISMessage aisMessage) {
		// 将aismessage存入数据库中
		// System.out.println(count+"Received AIS message: " + aisMessage);
		//TODO insert2DB做成单实例模式？？？
		new insert2DB(aisMessage).insert();
	}

	public void getRun() {
		try {
			Connection conn = getDBConnection();
			PreparedStatement ps = conn.prepareStatement("select * from ais_nmea");
			// 设置每次读取的消息条数
			ps.setFetchSize(Integer.MIN_VALUE);
			ResultSet rs = ps.executeQuery();
			InputStream inputstream = null;
			while (rs.next()) {
				String str = rs.getString("message");
				if (str == null)
					return;
				inputstream = new ByteArrayInputStream(str.getBytes());
				AISInputStreamReader streamReader = new AISInputStreamReader(inputstream, this);
				streamReader.run();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Connection getDBConnection() {
		Connection conn;
		try {
			// 创建数据库连接库对象
			if (connPool == null) {
				connPool = ConnectionPool.getInstance();
				// 新建数据库连接库
				connPool.createPool();
			}
			conn = connPool.getConnection(); // 从连接库中获取一个可用的连接
		} catch (Exception e) {
			return null;
		}
		return conn;
	}

	public static void main(String[] args) {

		new getAisFromDB().getRun();
		/*
		 * String sql[] = { "!ABVDM,1,1,7,A,15DEGl001k`aS1vB<acLpr?R08Lj,0*0A",
		 * "!ABVDM,1,1,8,A,2AF40003wk<tSF0l4Q@13wv2Pcp0,0*6D",
		 * "!ABVDM,1,1,0,A,35SU1>10008TvG:BGVva0rQRP000,0*11",
		 * "!ABVDM,1,1,2,A,403t9PQuug78b8SC4TD@;sW000S:,0*64",
		 * "!ABVDM,2,1,3,B,56:aw:026H4K33P00019DqPTpLQDw33P000000160`e5400006vMf0000000,0*37",
		 * "!AIVDM,1,1,4,B,6>jR0600V:C0>da4P106P00,2*02",
		 * "!ABVDM,1,1,3,A,81Nkf03oaQNkf008Mh70@000,0*21",
		 * "!ABVDM,1,1,1,A,;03t=Kiuuo0Mi`eiOLAb3ro00000,0*66",
		 * "!AIVDM,1,1,,B,>>M4fWA<59B1@E=@,0*17",
		 * "!AIVDM,1,1,,A,?h3OvkB6kR70D00,2*1C",
		 * "!ABVDM,1,1,4,B,B6:a1bh0>B9usI4U3II4owp3QP06,0*0E",
		 * "!ABVDM,1,1,2,A,C6:b4RP06r7se>4V?eRU2400`NL>83kih00000000000S6@85400,0*47",
		 * "!ABVDM,1,1,2,B,D03t9PQUHNfp00N000,4*28",
		 * "!ABVDM,1,1,9,A,E0WN<TGU4WHHh@@@@@@@@@@@@@@DCCNp98pl0A00;8b000,4*4A",
		 * "!ABVDM,1,1,2,B,H6:W:mE66DeijihPPPPPPP5p6450,0*2C",
		 * "!AIVDM,1,1,,A,KCQ9r=hrFUnH7P00,0*41" }; try { Connection conn = new
		 * getAisFromDB().getDBConnection(); Statement stmt =
		 * conn.createStatement(); for (int i = 0; i < sql.length; i++) { String
		 * sqlString = "insert into ais_nmea (message) values ('" + sql[i] +
		 * "')"; stmt.execute(sqlString); } } catch (Exception e) {
		 * 
		 * }
		 */
	}

}
