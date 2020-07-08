package movieSeat.Service;

import java.util.ArrayList;

import javafx.scene.Parent;

public interface SeatService {
	public boolean reserveProc(Parent seatForm);
	public void addComboBox(Parent seatForm);
	public void addTxt(Parent seatForm);
	public void changeAdult(Parent seatForm);
	public void changeOld(Parent seatForm);
	public void againProc(Parent seatForm);
	public void changeTeen(Parent seatForm);
	public ArrayList<String> getSeatProc();
	public String[][] getDbSeat();
	public void seatProc(Parent seatForm);
}
