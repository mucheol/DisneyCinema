package reservationForm;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlterService implements AlterService_I {

	@Override
	public void ErrorMag(String title, String headerStr, String contentTxt) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(headerStr);
		alert.setContentText(contentTxt);
		alert.showAndWait();
		
	}

	@Override
	public void ErrorMag(String headerStr, String contentTxt) {
		ErrorMag("Error", headerStr, contentTxt);
		
	}

	@Override
	public void ErrorMag(String contentTxt) {
		ErrorMag("Error", "Error Header", contentTxt);
		
	}

}
