package reservationDbconn;

import java.util.ArrayList;

public interface Dbconnect_I { // 변경 되었음
	
	public ArrayList<String> Selecttime(String moviename);
	public void Insert(String getID, String getDate, String getTheater, String getName, String getTime);
	public String SelectDate(String getID);
	public String SelectTheater(String getID);
	public String SelectName(String getID);
	public String SelectTime(String getID);
	public boolean DeleteID(String getID);
	
}
