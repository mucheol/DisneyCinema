package Store;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import MovieMain.ControllerMain;
import MovieMain.Service.CommonServiceInter;
import Store.Service.CommonService;
import Store.Service.CommonServiceImpl;
import Store.Service.DatabaseService;
import Store.Service.DatabaseServiceImpl;
import Store.Service.MenuService;
import Store.Service.MenuServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Controller implements Initializable {
	private Parent MenuForm;
	private MenuServiceImpl ms = new MenuServiceImpl();

	private ArrayList<HBox> arr;
	private MenuService menuServ;
	private CommonService commonServ;

	HBox h = new HBox();
	Label la1 = new Label();
	Label la2 = new Label();
	String shoppingList = "";
	String txt = "";

	public void setMenuForm(Parent MenuForm) {
		this.MenuForm = MenuForm;
	}

	public Parent getMenuForm() {
		return MenuForm;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		menuServ = new MenuServiceImpl();
		commonServ = new CommonServiceImpl();
	}

	public void AddCart(ActionEvent event) {
		arr = ms.PickMenu(getMenuForm());
		for (int i = 0; i < arr.size(); i++) {
			ms.list.getItems().add(arr.get(i));
		}
	}

	public void CancelProc(ActionEvent event) {
		commonServ.WindowClose(event);
	}

	public void Buy() {
		Info info = new Info();
		Alert alert = new Alert(AlertType.INFORMATION);
		Button btn = new Button();
		int amount = 0;
		
		if(ms.list != null) {
			for (int i = 0; i <ms.list.getItems().size(); i++) {
				h = (HBox) ms.list.getItems().get(i);
				la1 = (Label) h.getChildren().get(0);
				la2 = (Label) h.getChildren().get(1);
				shoppingList += la1.getText() + " " + la2.getText() + "\n";
				
				String la2_txt = la2.getText();
				String la2_int = new String();
				for (int j = 0; j < la2_txt.length(); j++) {
					if (48 <= la2_txt.charAt(j) && la2_txt.charAt(j) <= 57)
						la2_int += la2_txt.charAt(j);
				}
				int aa = Integer.parseInt(la2_int);
				amount += aa;
			}
			
			if(amount == 0) {
				commonServ.ErrorMsg("구매목록이 없습니다.");
			} else {
				
				info.setCart(shoppingList);
				info.setSumPrice(amount);
				
				CommonServiceInter commonServMain = new MovieMain.Service.CommonService();
				commonServMain.showMsg("구매 완료", shoppingList);
				
				/* 대신 위에꺼로 대체
				alert.setHeaderText("구매완료");
				alert.setContentText(shoppingList);
				alert.show();
				*/
				System.out.println(shoppingList);
				System.out.println(amount);
				
				ControllerMain controllerMain = new ControllerMain();
				DatabaseService dbServ = new DatabaseServiceImpl();
				dbServ.open();
				dbServ.Insert(info, controllerMain.getCurrentLoginID());
				
				Stage stageMenu = (Stage)MenuForm.getScene().getWindow();
				stageMenu.close();
				
			}
		
		} else {
			commonServ.ErrorMsg("구매목록이 없습니다");
		}
	}
}
