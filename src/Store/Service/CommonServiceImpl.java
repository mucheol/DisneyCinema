package Store.Service;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class CommonServiceImpl implements CommonService{

	@Override
	public void WindowClose(ActionEvent event) {
		// TODO Auto-generated method stub
		Parent parent = (Parent)event.getSource();
        Stage stage = (Stage)parent.getScene().getWindow();
        stage.close();
	}

	@Override
	public void ErrorMsg(String title, String headerStr, String contentTxt) {
		// TODO Auto-generated method stub
		 Alert alert = new Alert(AlertType.INFORMATION);
	        alert.setTitle(title);
	        alert.setHeaderText(headerStr);
	        alert.setContentText(contentTxt);
	        alert.showAndWait();
	}

	@Override
	public void ErrorMsg(String headerStr, String contentTxt) {
		// TODO Auto-generated method stub
		ErrorMsg("error",headerStr,contentTxt);

	}

	@Override
	public void ErrorMsg(String contentTxt) {
		// TODO Auto-generated method stub
		ErrorMsg("error","error",contentTxt);

	}
}
