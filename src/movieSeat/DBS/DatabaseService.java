package movieSeat.DBS;

import java.sql.SQLException;
import java.util.ArrayList;

import movieSeat.SeatObject;

public interface DatabaseService {
	
	public boolean open();
	public void Insert(String getID, SeatObject seatOb);
	public ArrayList<String> Select();
	public String SelectSeat(String getID);
	public String SelectPeople(String getID);
	public String SelectPrice(String getID);
	public boolean DeleteID(String getID);
	
}
