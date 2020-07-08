package MovieMain.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import MovieMain.Member;

public class DatabaseService implements DatabaseServiceInter {

	final static String DRIVER = "oracle.jdbc.driver.OracleDriver";
	final static String url = "jdbc:oracle:thin:@192.168.100.10:1521:xe";
	final String INSERTSQL = "insert into member(id, pw, name, email, gender, age, hobby)" + " values(?,?,?,?,?,?,?)";
	//final String LOGINSQL = "SELECT COUNT(id) "+"FROM member "+"WHERE id = ? AND pw = ?";
	final String LOGINSQL = "SELECT id "+"FROM member";
	final String LOGINSQL_PW = "SELECT pw " + "FROM member " + "WHERE id = ? ";
	final String LOGINSQL_NAME = "SELECT name " + "FROM member " + "WHERE id = ? ";
	
	private Connection dbConn;
	
	
	static { 
		try { Class.forName(DRIVER); } 
		catch(Exception e) { e.printStackTrace(); } 
	} 
	
	
	@Override
	public boolean open()  {
		// TODO Auto-generated method stub
		try {
			
			String id= "kgitbank";
			String pw= "itbank";
			dbConn = DriverManager.getConnection(url,id,pw);
			System.out.println("fghfgh");
			
		} catch(SQLException e) { 
			e.printStackTrace(); 
			return false; 
		} 
		
		return true; 
	}

	@Override
	public void Insert(Member member) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement prep = dbConn.prepareStatement(INSERTSQL);
			
			prep.setString(1, member.getStrID());
			prep.setString(2, member.getStrPW());
			prep.setString(3, member.getStrName());
			prep.setString(4, member.getStrEmail());
			prep.setString(5, member.getStrGender());
			prep.setString(6, member.getStrAge());
			prep.setInt(7, member.getIntHobby());
			
			System.out.println("��ϿϷ�");
			prep.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<String> SelectIDAll() {
		// TODO Auto-generated method stub
		boolean result = true;
		
		try {
			
			PreparedStatement prep = dbConn.prepareStatement(LOGINSQL);
			ArrayList<String> arrID = new ArrayList<String>();

			// prep.setString(1, member.getStrGender());
			ResultSet rs = prep.executeQuery();
			
			while(rs.next()){
				arrID.add(rs.getString("id"));
			}
			
			rs.close();
			prep.close();
			
			return arrID;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	

	@Override
	public String SelectPW(String id) {
		// TODO Auto-generated method stub
		
		try {
			
			PreparedStatement prep = dbConn.prepareStatement(LOGINSQL_PW);
			String strPW = null;

			prep.setString(1, id);
			ResultSet rs = prep.executeQuery();
			
			while(rs.next()){
				strPW = rs.getString("pw");
			}
			
			rs.close();
			prep.close();
			
			return strPW;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
	@Override
	public String SelectName(String id) {
		// TODO Auto-generated method stub
		
		try {
			
			PreparedStatement prep = dbConn.prepareStatement(LOGINSQL_NAME);
			String strName = null;

			prep.setString(1, id);
			ResultSet rs = prep.executeQuery();
			
			while(rs.next()){
				strName = rs.getString("name");
			}
			
			rs.close();
			prep.close();
			
			return strName;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
}
