package dk.tbsalling.aismessages.ais.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import dk.tbsalling.aismessages.nmea.ConnectionPool;

public class DBTools {

	// 向基站表写数据
	public void insert2baseStation(String dateStr, int mmsi, float latitude, float longitude) {
		String sqlStr = "insert into ais_basestation (time,mmsi,latitude,longitude) values('" + dateStr + "'," + mmsi
				+ "," + latitude + "," + longitude + ")";
		ConnectionPool connPool = ConnectionPool.getInstance();
		try {
			connPool.createPool();
			Connection conn = connPool.getConnection();
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

	// 向位置表中写数据
	public void insert2Position(int timeStamp, int mmsi, float latitude, float longitude, float heading, float course,
			float speed) {
		String sqlStr = "insert into ais_position (time,mmsi,latitude,longitude,heading,course,speed) values("
				+ timeStamp + "," + mmsi + "," + latitude + "," + longitude + "," + heading + "," + course + "," + speed
				+ ")";
		ConnectionPool connPool = ConnectionPool.getInstance();
		try {
			connPool.createPool();
			Connection conn = connPool.getConnection();
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

	// 向船信息表中写数据
	public void insert2vessl(String dateStr, int mmsi, String name, String destination, float draught) {
		String sqlStr = "insert into ais_vesseldata (time,mmsi,name,destination,draught) values('" + dateStr + "',"
				+ mmsi + ",'" + name + "','" + destination + "'," + draught + ")";
		ConnectionPool connPool = ConnectionPool.getInstance();
		try {
			connPool.createPool();
			Connection conn = connPool.getConnection();
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
