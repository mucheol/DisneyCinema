package MovieMain.Service;

import Store.Service.DatabaseService;
import Store.Service.DatabaseServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import reservationDbconn.Dbconnect;
import reservationDbconn.Dbconnect_I;

public class MainService implements MainServiceInter {

	public void movieDetail(ActionEvent event, Parent parentMain) {
		
		try {
			
			Stage stage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../MovieDetail.fxml"));
			Parent parent = loader.load();
			Scene scene = new Scene(parent);
			
			Button btnDetail = (Button)event.getSource();
			String id = btnDetail.getId();

			if (id.equals("btnDetailBaek")) {
				Image image = new Image(getClass().getResourceAsStream("../../Image/��λ� ������.PNG"));
				ImageView imageView = (ImageView)parent.lookup("#imgDetail");
				imageView.setImage(image);
			} else if (id.equals("btnDetailSidong")) {
				Image image = new Image(getClass().getResourceAsStream("../../Image/�õ� ������.PNG"));
				ImageView imageView = (ImageView)parent.lookup("#imgDetail");
				imageView.setImage(image);
			} else if (id.equals("btnDetailCats")) {
				Image image = new Image(getClass().getResourceAsStream("../../Image/Ĺ�� ������.PNG"));
				ImageView imageView = (ImageView)parent.lookup("#imgDetail");
				imageView.setImage(image);
			} else if(id.equals("btnDetailFrozen")) {
				Image image = new Image(getClass().getResourceAsStream("../../Image/�ܿ�ձ� ������.PNG"));
				ImageView imageView = (ImageView)parent.lookup("#imgDetail");
				imageView.setImage(image);
			} 
			
			stage.setScene(scene);
			stage.show();
			
			
		} catch (Exception e) {System.out.println(e);}
		
	}
	
	
	public void cancelReserve(String getID, Parent getRsParent) {
		
		try {
			
		Dbconnect_I dbServRs = new Dbconnect();
		Boolean boolRsResult = dbServRs.DeleteID(getID);
		
		movieSeat.DBS.DatabaseService dbServSeat = new movieSeat.DBS.DatabaseServiceImpl();
		dbServSeat.open();
		Boolean boolSeatResult = dbServSeat.DeleteID(getID);
		
		CommonServiceInter commonServ = new CommonService();
		Stage stage = (Stage)getRsParent.getScene().getWindow();
		
		if (boolRsResult == true && boolSeatResult == true) {
			commonServ.showMsg("���� ��� �Ϸ�", "���Ű� ��ҵǾ����ϴ�.");
			stage.close();
		} else {
			commonServ.showMsg("���� ��� ����", "���Ÿ� ����� �� �����ϴ�.");
			stage.close();
		}
		
		} catch (Exception e) {System.out.println(e);}
		
	}
	
	
	public void cancelMenu(String getID, Parent getRsParent) {
		
		try {
			
			DatabaseService dbServStore = new DatabaseServiceImpl();
			dbServStore.open();
			
			CommonServiceInter commonServ = new CommonService();
			Stage stage = (Stage)getRsParent.getScene().getWindow();
			
			if (dbServStore.DeleteID(getID) == true) {
				commonServ.showMsg("��ǰ ��� �Ϸ�", "��ǰ�� ��ҵǾ����ϴ�.");
				stage.close();
			} else {
				commonServ.showMsg("��ǰ ��� ����", "��ǰ�� ����� �� �����ϴ�.");
				stage.close();
			}
			
		} catch (Exception e) {System.out.println(e);}
		
	}
	
}
