package Store;

import Store.Service.MenuService;
import Store.Service.MenuServiceImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StoreMain extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		MenuService ms = new MenuServiceImpl();
		
		try {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
		Parent root = loader.load();
		    
		Scene scene =  new Scene(root);
		Controller ctr = loader.getController();
		ctr.setMenuForm(root); 
		
		ms.AddComboBox(root);
		primaryStage.setTitle("STORE");
		primaryStage.setScene(scene);
		primaryStage.show();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		launch(args);
	}
}