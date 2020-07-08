package reservationDbconn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import MovieMain.Member;

public class Dbconnect implements Dbconnect_I { // 메소드 불필요한거 삭제

	private static String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static String URL = "jdbc:oracle:thin:@192.168.100.10:1521:xe";
	private static String NAME = "select imagename from movie where name like ";
	private static String TIME = "select * from movietime where name like ";
	private static String INSERT = "insert into reservedinfo(movieid, moviedate, movietheater, moviename, movietime) " + "values(?, ?, ?, ?, ?) ";
	
	private static String SELECT_DATE = "SELECT moviedate " + "FROM reservedinfo " + "WHERE movieid = ? ";
	private static String SELECT_THEATER = "SELECT movietheater " + "FROM reservedinfo " + "WHERE movieid = ? ";
	private static String SELECT_NAME = "SELECT moviename " + "FROM reservedinfo " + "WHERE movieid = ? ";
	private static String SELECT_TIME = "SELECT movietime " + "FROM reservedinfo " + "WHERE movieid = ? ";
	private static String DELETE = "DELETE FROM reservedinfo WHERE movieid = ? ";
	
	private static Connection Conn = null;
	private PreparedStatement pstmt = null;
	private static ResultSet rs = null;
	private static Statement stmt = null;

	@Override
	public ArrayList<String> Selecttime(String moviename) {
		ArrayList<String> time = new ArrayList<String>();
		
		try {
			
			Class.forName(DRIVER);
			if (URL == "jdbc:oracle:thin:@192.168.100.10:1521:xe" || URL == "jdbc:oracle:thin:@localhost:1521:xe") {
				Conn = DriverManager.getConnection(URL, "kgitbank", "itbank");		// 192.168.100.10 에선 "kgitbank", "itbank"
			} else if (URL == "jdbc:oracle:thin:@192.168.0.2:1521:xe") {
				Conn = DriverManager.getConnection(URL, "movie", "movie");		// 192.168.0.2 에선 "movie", "movie"
			}
			
			stmt = Conn.createStatement();
			rs = stmt.executeQuery(TIME + "'" + moviename + "'");

			while (rs.next()) {
				
				String time1 = rs.getString("time1");
				String time2 = rs.getString("time2");
				String time3 = rs.getString("time3");
				String time4 = rs.getString("time4");
				String time5 = rs.getString("time5");
				String time6 = rs.getString("time6");

				time.add(time1);
				time.add(time2);
				time.add(time3);
				time.add(time4);
				time.add(time5);
				time.add(time6);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
				if (stmt != null && !stmt.isClosed()) {
					stmt.close();
				}
				if (Conn != null && !Conn.isClosed()) {
					Conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return time;
	}
	
	
	@Override
	public void Insert(String getID, String getDate, String getTheater, String getName, String getTime) {
		// TODO Auto-generated method stub
		try {
			
			Class.forName(DRIVER);
			if (URL == "jdbc:oracle:thin:@192.168.100.10:1521:xe" || URL == "jdbc:oracle:thin:@localhost:1521:xe") {
				Conn = DriverManager.getConnection(URL, "kgitbank", "itbank");		// 192.168.100.10 에선 "kgitbank", "itbank"
			} else if (URL == "jdbc:oracle:thin:@192.168.0.2:1521:xe") {
				Conn = DriverManager.getConnection(URL, "movie", "movie");		// 192.168.0.2 에선 "movie", "movie"
			}

			PreparedStatement prep = Conn.prepareStatement(INSERT);
			
			prep.setString(1, getID);
			prep.setString(2, getDate);
			prep.setString(3, getTheater);
			prep.setString(4, getName);
			prep.setString(5, getTime);
			
			System.out.println("데이터 삽입완료(영화날짜시간)");
			prep.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	@Override
	public String SelectDate(String getID) {
		
		try {
			
			Class.forName(DRIVER);
			if (URL == "jdbc:oracle:thin:@192.168.100.10:1521:xe" || URL == "jdbc:oracle:thin:@localhost:1521:xe") {
				Conn = DriverManager.getConnection(URL, "kgitbank", "itbank");		// 192.168.100.10 에선 "kgitbank", "itbank"
			} else if (URL == "jdbc:oracle:thin:@192.168.0.2:1521:xe") {
				Conn = DriverManager.getConnection(URL, "movie", "movie");		// 192.168.0.2 에선 "movie", "movie"
			}
			
			PreparedStatement prep = Conn.prepareStatement(SELECT_DATE);
			String strDate = null;
			
			prep.setString(1, getID);
			ResultSet rs = prep.executeQuery();
			
			while(rs.next()){
				strDate = rs.getString("moviedate");
			}
			
			rs.close();
			prep.close();
			
			return strDate;
			
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}
	
	
	@Override
	public String SelectTheater(String getID) {
		
		try {
			
			Class.forName(DRIVER);
			if (URL == "jdbc:oracle:thin:@192.168.100.10:1521:xe" || URL == "jdbc:oracle:thin:@localhost:1521:xe") {
				Conn = DriverManager.getConnection(URL, "kgitbank", "itbank");		// 192.168.100.10 에선 "kgitbank", "itbank"
			} else if (URL == "jdbc:oracle:thin:@192.168.0.2:1521:xe") {
				Conn = DriverManager.getConnection(URL, "movie", "movie");		// 192.168.0.2 에선 "movie", "movie"
			}
			
			PreparedStatement prep = Conn.prepareStatement(SELECT_THEATER);
			String strTheater = null;
			
			prep.setString(1, getID);
			ResultSet rs = prep.executeQuery();
			
			while(rs.next()){
				strTheater = rs.getString("movietheater");
			}
			
			rs.close();
			prep.close();
			
			return strTheater;
			
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}

	}


	@Override
	public String SelectName(String getID) {
		// TODO Auto-generated method stub
		
		try {
			
			Class.forName(DRIVER);
			if (URL == "jdbc:oracle:thin:@192.168.100.10:1521:xe" || URL == "jdbc:oracle:thin:@localhost:1521:xe") {
				Conn = DriverManager.getConnection(URL, "kgitbank", "itbank");		// 192.168.100.10 에선 "kgitbank", "itbank"
			} else if (URL == "jdbc:oracle:thin:@192.168.0.2:1521:xe") {
				Conn = DriverManager.getConnection(URL, "movie", "movie");		// 192.168.0.2 에선 "movie", "movie"
			}
			
			PreparedStatement prep = Conn.prepareStatement(SELECT_NAME);
			String strName = null;
			
			prep.setString(1, getID);
			ResultSet rs = prep.executeQuery();
			
			while(rs.next()){
				strName = rs.getString("moviename");
			}
			
			rs.close();
			prep.close();
			
			return strName;
			
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	
	}


	@Override
	public String SelectTime(String getID) {
		// TODO Auto-generated method stub
		try {
			
			Class.forName(DRIVER);
			if (URL == "jdbc:oracle:thin:@192.168.100.10:1521:xe" || URL == "jdbc:oracle:thin:@localhost:1521:xe") {
				Conn = DriverManager.getConnection(URL, "kgitbank", "itbank");		// 192.168.100.10 에선 "kgitbank", "itbank"
			} else if (URL == "jdbc:oracle:thin:@192.168.0.2:1521:xe") {
				Conn = DriverManager.getConnection(URL, "movie", "movie");		// 192.168.0.2 에선 "movie", "movie"
			}
			
			PreparedStatement prep = Conn.prepareStatement(SELECT_TIME);
			String strTime = null;
			
			prep.setString(1, getID);
			ResultSet rs = prep.executeQuery();
			
			while(rs.next()){
				strTime = rs.getString("movietime");
			}
			
			rs.close();
			prep.close();
			
			return strTime;
			
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}


	
	@Override
	public boolean DeleteID(String getID) {
		// TODO Auto-generated method stub
				try {
					
					Class.forName(DRIVER);
					if (URL == "jdbc:oracle:thin:@192.168.100.10:1521:xe" || URL == "jdbc:oracle:thin:@localhost:1521:xe") {
						Conn = DriverManager.getConnection(URL, "kgitbank", "itbank");		// 192.168.100.10 에선 "kgitbank", "itbank"
					} else if (URL == "jdbc:oracle:thin:@192.168.0.2:1521:xe") {
						Conn = DriverManager.getConnection(URL, "movie", "movie");		// 192.168.0.2 에선 "movie", "movie"
					}
					
					PreparedStatement prep = Conn.prepareStatement(DELETE);
					
					prep.setString(1, getID);
					ResultSet rs = prep.executeQuery();
					
					// rs.close();
					prep.close();
					
					return true;
					
				} catch (Exception e) {
					System.out.println(e);
					return false;
				}
	}
	
	
	

}