package dk.tbsalling.aismessages.demo;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.function.Consumer;

import dk.tbsalling.aismessages.AISInputStreamReader;
import dk.tbsalling.aismessages.ais.exceptions.UnsupportedMessageType;
import dk.tbsalling.aismessages.ais.messages.AISMessage;
import dk.tbsalling.aismessages.ais.messages.BaseStationReport;
import dk.tbsalling.aismessages.ais.messages.types.AISMessageType;
import dk.tbsalling.aismessages.nmea.ConnectionPool;

import dk.tbsalling.aismessages.nmea.messages.NMEAMessage;

public class getAisFromDB implements Consumer<AISMessage> {
	Consumer<? super NMEAMessage> nmeaMessageHandler;
	ConnectionPool connPool;
	int messageId;

	// 重写accept方法，对返回aisMessage 进行处理
	@Override
	public void accept(AISMessage aisMessage) {
		// 将aismessage存入数据库中
	System.out.println("Received AIS message: " + aisMessage);
	
		aisMessage.insert2DB();
//		
//		Connection conn = getDBConnection();
//		try {
//			Statement stm=conn.createStatement();
//			stm.execute("update ais_nmea set isread=1 where id="+messageId);
//			stm.close();
//			conn.close();
//			connPool.returnConnection(conn); // 连接使用完后释放连接到连接池
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		
	}

	public void getRun() {

		try {
			long date1=System.currentTimeMillis();
			Connection conn = getDBConnection();
//			Statement ps=conn.createStatement();
			PreparedStatement ps = conn.prepareStatement("select * from ais_nmea where isread=0 limit 10000");
			// 设置每次读取的消息条数
//			ps.setFetchSize(1000);
			//循环读取数据库
//			while(true){
				ResultSet rs = ps.executeQuery();
				InputStream inputstream = null;
				StringBuffer str=new StringBuffer();
				while (rs.next()) {
					str.append(rs.getString("message")).append("\n");
					messageId=rs.getInt("id");
				}
				inputstream = new ByteArrayInputStream(str.toString().getBytes());
				// 对字节流中的信息进行NMEAMessageHandler的组装，NMEAMessageInputStreamReader的组装
				AISInputStreamReader streamReader = new AISInputStreamReader(inputstream, this);
				streamReader.run();
//			}

//				long date2=System.currentTimeMillis();
//				System.out.println("插入ddddd信息表耗时"+(date2-date1));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 返回一个可用的数据库连接对象
	 * 
	 * @return Connection 数据库的连接对象
	 */
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
		long date1=System.currentTimeMillis();
		new getAisFromDB().getRun();
		long date2=System.currentTimeMillis();
		System.out.println("插入ddddd信息表耗时"+(date2-date1));

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
