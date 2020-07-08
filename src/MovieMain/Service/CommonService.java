package MovieMain.Service;

import MovieMain.ControllerMain;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class CommonService implements CommonServiceInter {

	@Override
	public void cancelAction(ActionEvent event) {
		// TODO Auto-generated method stub
		
		try {
			
			Parent parent = (Parent)event.getSource();
			Scene scene = parent.getScene();
			Stage stage = (Stage)scene.getWindow();
			stage.close();
			
		} catch (Exception e) {System.out.println(e);}
		
	}
	
	
	@Override
	public void showMsg(String strTitle, String strContent) {
		
		try {
	
			Stage stage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../MessageBox.fxml"));
			Parent parent = loader.load();
			Scene scene = new Scene(parent);
			
			Label lblTitle = (Label)parent.lookup("#lblTitle");
			Label lblContent = (Label)parent.lookup("#lblContent");
			
			lblTitle.setText(strTitle);
			lblContent.setText(strContent);
			
			stage.setScene(scene);
			stage.show();
			
		} catch (Exception e) {System.out.println(e);}
		
	}
	
	
	
	public void showMyReserve(String strTitle, String strContent) {
		
		try {
			
			Stage stage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../MyReserveForm.fxml"));
			Parent parent = loader.load();
			Scene scene = new Scene(parent);
			
			Label lblTitle = (Label)parent.lookup("#lblTitle");
			Label lblContent = (Label)parent.lookup("#lblContent");
			
			lblTitle.setText(strTitle);
			lblContent.setText(strContent);
			
			ControllerMain controllerMain = new ControllerMain();
			controllerMain.setStaticRsParent(parent);
			
			stage.setScene(scene);
			stage.show();
			
		} catch (Exception e) {System.out.println(e);}
		
	}
	

}
