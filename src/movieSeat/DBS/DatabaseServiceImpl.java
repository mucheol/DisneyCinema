package movieSeat.DBS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import MovieMain.Service.CommonService;
import MovieMain.Service.CommonServiceInter;
import movieSeat.SeatObject;

public class DatabaseServiceImpl implements DatabaseService {
	final static String DRIVER = "oracle.jdbc.driver.OracleDriver";
	final static String URL = "jdbc:oracle:thin:@192.168.100.10:1521:xe";
	final String INSERTSQL = "insert into movieseat(id, seat, people, price)"+" values(?, ?, ?, ?)";
	final String LOGINSQL = "select * from movieseat";
	
	private static String SELECT_SEAT = "SELECT seat " + "FROM movieseat " + "WHERE id = ? ";
	private static String SELECT_PEOPLE = "SELECT people " + "FROM movieseat " + "WHERE id = ? ";
	private static String SELECT_PRICE = "SELECT price " + "FROM movieseat " + "WHERE id = ? ";
	private static String DELETE = "DELETE FROM movieseat WHERE id = ? ";
	
	private Connection dbConn;
	static {
		try {
			Class.forName(DRIVER);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean open() {  // 오라클 연결
		try {
			String id = "kgitbank";
			String pw = "itbank";
			dbConn = DriverManager.getConnection(URL,id,pw);
			System.out.println("오라클 연결 성공");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public void Insert(String getID, SeatObject seatOb) { // 오라클 내용 추가
		try {

			if (getID == "") {
				CommonServiceInter commonServ = new CommonService();
				commonServ.showMsg("알림", "로그인을 해주세요.");
			}
			
			PreparedStatement prep = dbConn.prepareStatement(INSERTSQL);
			prep.setString(1, getID);
			prep.setString(2, seatOb.getSeat());
			prep.setInt(3, seatOb.getPeople());
			prep.setInt(4, seatOb.getPrice());
			System.out.println("영화 에매 완료");
			prep.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public ArrayList<String> Select() {
		ArrayList<String> seatList = new ArrayList<String>();
		
		
		try {
			PreparedStatement prep = dbConn.prepareStatement(LOGINSQL);
			String[] strArr;
			ResultSet rs = prep.executeQuery();
			while(rs.next()) {
				int i=0;
				String[] str;
				str = rs.getString("seat").split(",");
				
				for(i=0; i < str.length; i++) {
					seatList.add(str[i]);
				}
				
			}
			
			rs.close();
			prep.close();
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return seatList;
	}
	
	
	@Override
	public String SelectSeat(String getID) {
		// TODO Auto-generated method stub
		try {
			
			PreparedStatement prep = dbConn.prepareStatement(SELECT_SEAT);
			String strSeat = null;

			prep.setString(1, getID);
			ResultSet rs = prep.executeQuery();
			
			while(rs.next()){
				strSeat = rs.getString("seat");
			}
			
			rs.close();
			prep.close();
			
			return strSeat;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	

	@Override
	public String SelectPeople(String getID) {
		// TODO Auto-generated method stub
		try {
			
			PreparedStatement prep = dbConn.prepareStatement(SELECT_PEOPLE);
			String strPeople = null;

			prep.setString(1, getID);
			ResultSet rs = prep.executeQuery();
			
			while(rs.next()){
				strPeople = rs.getString("people");
			}
			
			rs.close();
			prep.close();
			
			return strPeople;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	
	
	@Override
	public String SelectPrice(String getID) {
		// TODO Auto-generated method stub
		try {
			
			PreparedStatement prep = dbConn.prepareStatement(SELECT_PRICE);
			String strPrice = null;

			prep.setString(1, getID);
			ResultSet rs = prep.executeQuery();
			
			while(rs.next()){
				strPrice = rs.getString("price");
			}
			
			rs.close();
			prep.close();
			
			return strPrice;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	
	
	@Override
	public boolean DeleteID(String getID) {
		// TODO Auto-generated method stub
		try {
			
			PreparedStatement prep = dbConn.prepareStatement(DELETE);

			prep.setString(1, getID);
			ResultSet rs = prep.executeQuery();
			
			/*
			while(rs.next()){
				System.out.println(rs);
			}
			*/
			
			// rs.close();
			prep.close();
			
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	
	
	
	
}
