package movieSeat.Service;

import javafx.event.ActionEvent;

public interface CommonService {
	public void Close(ActionEvent event);
	public void ErrorMag(String title, String headerStr, String contentTxt);
}
