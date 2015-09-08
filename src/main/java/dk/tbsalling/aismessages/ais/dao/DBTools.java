package dk.tbsalling.aismessages.ais.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;

import dk.tbsalling.aismessages.nmea.ConnectionPool;

public class DBTools {
	public static ConnectionPool connPool;
	public static Connection conn;
	
	
	static{
		connPool = ConnectionPool.getInstance();
		
		try {
			connPool.createPool();
			conn = connPool.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	// 向基站表写数据
	public static void insert2baseStation(String dateStr, int mmsi, float latitude, float longitude) {
		String sqlStr = "insert into ais_basestation (time,mmsi,latitude,longitude) values('" + dateStr + "'," + mmsi
				+ "," + latitude + "," + longitude + ")";

		long date1=System.currentTimeMillis();
		try {
			
			
			Statement stm = conn.createStatement();
			stm.execute(sqlStr);
			stm.close();
			//conn.close();
			//connPool.returnConnection(conn); // 连接使用完后释放连接到连接池
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		long date2=System.currentTimeMillis();
		System.out.println("插入基站信息表耗时"+(date2-date1));
	}

	// 向位置表中写数据
	public static void insert2Position(int timeStamp, int mmsi, float latitude, float longitude, float heading, float course,
			float speed) {
		long date1=System.currentTimeMillis();
		String sqlStr = "insert into ais_position (time,mmsi,latitude,longitude,heading,course,speed) values("
				+ timeStamp + "," + mmsi + "," + latitude + "," + longitude + "," + heading + "," + course + "," + speed
				+ ")";
		try {

//			conn = connPool.getConnection();
			Statement stm = conn.createStatement();
			stm.execute(sqlStr);
			stm.close();
			//conn.close();
			//connPool.returnConnection(conn); // 连接使用完后释放连接到连接池
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		long date2=System.currentTimeMillis();
		System.out.println("插入位置信息表耗时"+(date2-date1));
	}

	// 向船信息表中写数据
	public static void insert2vessl( int mmsi, String name, String destination, float draught) {
		long date1=System.currentTimeMillis();
		String sqlStr = "insert into ais_vesseldata (mmsi,name,destination,draught) values("
				+ mmsi + ",'" + name + "','" + destination + "'," + draught + ")";
		try {

//			conn = connPool.getConnection();
			Statement stm = conn.createStatement();
			stm.execute(sqlStr);
			stm.close();
			//conn.close();
			//connPool.returnConnection(conn); // 连接使用完后释放连接到连接池
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		long date2=System.currentTimeMillis();
		System.out.println("插入船信息表耗时"+(date2-date1));
	}
	
	
}
