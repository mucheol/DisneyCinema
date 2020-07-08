package MovieMain;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class MovieMain extends Application {
	
	public void start(Stage primaryStage) {
		
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("MovieMain.fxml"));
			Parent parentMain = loader.load();
			
			Scene scene = new Scene(parentMain); 
			ControllerMain controller = loader.getController();
			controller.setStaticMainParent(parentMain);		// 메뉴창까지 완료했을때 모든 폼 닫아주기 위해서 만듬
			controller.setMainForm(parentMain);
			
			
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch (Exception e) {System.out.println(e);}
		
	}

	public static void main(String[] args) {
	
		launch(args);
		
	}
	
}
