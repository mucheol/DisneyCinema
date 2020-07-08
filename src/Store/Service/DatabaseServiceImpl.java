package Store.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Store.Info;

public class DatabaseServiceImpl implements DatabaseService{
	 final static String DRIVER = "oracle.jdbc.driver.OracleDriver";
	    final static String url = "jdbc:oracle:thin:@192.168.100.10:1521:xe";
	    final String INSERTSQL = "insert into menu(id, shoppingList, amount)" + " values(?, ?, ?)";
		private static String SELECT_SHOPPINGLIST = "SELECT shoppingList " + "FROM menu " + "WHERE id = ? ";
		private static String SELECT_AMOUNT = "SELECT amount " + "FROM menu " + "WHERE id = ? ";
		private static String DELETE = "DELETE FROM menu WHERE id = ? ";
	    
	    private Connection dbConn;

	    static { 
	        try { Class.forName(DRIVER); } 
	        catch(Exception e) { e.printStackTrace(); } 
	    } 
	    
	    @Override
		public boolean open() {
			// TODO Auto-generated method stub
	    	try {
	    		String id= "kgitbank";
	            String pw= "itbank";
	            dbConn = DriverManager.getConnection(url,id,pw);
	            System.out.println("오라클 연결 성공(메뉴)");
	    	}catch(SQLException e) { 
	            e.printStackTrace(); 
	            return false; 
	        } 
	        return true; 
	    }

	    
	@Override
	public void Insert(Info info, String getID) {
		// TODO Auto-generated method stub
		try {
			
			PreparedStatement prep = dbConn.prepareStatement(INSERTSQL);
			prep.setString(1, getID); 
			prep.setString(2, info.getCart()); 
			prep.setInt(3, info.getSumPrice());
			
			System.out.println("데이터 삽입완료(스토어메뉴)");
			prep.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}


	@Override
	public String SelectShoppingList(String getID) {
		// TODO Auto-generated method stub
		try {
			
			PreparedStatement prep = dbConn.prepareStatement(SELECT_SHOPPINGLIST);
			String strShoppingList = null;

			prep.setString(1, getID);
			ResultSet rs = prep.executeQuery();
			
			while(rs.next()){
				strShoppingList = rs.getString("shoppinglist");
			}
			
			rs.close();
			prep.close();
			
			return strShoppingList;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}


	@Override
	public String SelectAmount(String getID) {
		// TODO Auto-generated method stub
		try {
			
			PreparedStatement prep = dbConn.prepareStatement(SELECT_AMOUNT);
			String strAmount = null;

			prep.setString(1, getID);
			ResultSet rs = prep.executeQuery();
			
			while(rs.next()){
				strAmount = rs.getString("amount");
			}
			
			rs.close();
			prep.close();
			
			return strAmount;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	
	@Override
	public boolean DeleteID(String getID) {
		
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
