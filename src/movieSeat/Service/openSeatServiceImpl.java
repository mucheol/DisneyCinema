package movieSeat.Service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import movieSeat.Controller;
import movieSeat.DBS.DatabaseService;
import movieSeat.DBS.DatabaseServiceImpl;

public class openSeatServiceImpl implements openSeatService {
	public void addComboBox(Parent root) {
		SeatService seatServ = new SeatServiceImpl();
		seatServ.addComboBox(root);
	}
	
	public void DbCheck(Parent root,DatabaseService dbsrv) throws SQLException {
		SeatService seatServ = new SeatServiceImpl();
		ArrayList<String> seatList = dbsrv.Select();
		String[][] Arr = seatServ.getDbSeat();
		
		
		for(int i=0;i<seatList.size();i++) {
			
			ToggleButton btn = (ToggleButton)root.lookup("#" + seatList.get(i));
			btn.setDisable(true);
			Image image = new Image(getClass().getResourceAsStream("../../image/seatcomp.png"));
			ImageView imV = new ImageView(image);
			imV.setFitWidth(20);
			imV.setFitHeight(20);
			btn.setText("");
			btn.setGraphic(imV);
		}
	}
	
	
	
	public void openSeat(String movieName) throws SQLException {
		Stage seatForm = new Stage();
		DatabaseService dbsrv = new DatabaseServiceImpl();
		SeatService seatServ = new SeatServiceImpl();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../seat.fxml"));
		Parent parent = null;
		
		try {
			parent = loader.load();
		} catch(IOException e) {
			e.printStackTrace();
		}
		seatForm.setScene(new Scene(parent));
		
		Controller ctrler = loader.getController();
		ctrler.setStaticSeatParent(parent);
		ctrler.setSeatForm(parent);
		
		if (movieName.equals("Baekdusan")) {
			Image image = new Image(getClass().getResourceAsStream("../../Image/��λ� �̹���.jpg"));
			ImageView imgMovieName = (ImageView)parent.lookup("#imgMovieName");
			Image imageAge = new Image(getClass().getResourceAsStream("../../Image/��λ� ����.PNG"));
			ImageView imgMovieAge = (ImageView)parent.lookup("#imgMovieAge");
			imgMovieName.setImage(image);
			imgMovieAge.setImage(imageAge);
		} else if (movieName.equals("Sidong")) {
			Image image = new Image(getClass().getResourceAsStream("../../Image/�õ� �̹���.jpg"));
			ImageView imgMovieName = (ImageView)parent.lookup("#imgMovieName");
			Image imageAge = new Image(getClass().getResourceAsStream("../../Image/�õ� ����.PNG"));
			ImageView imgMovieAge = (ImageView)parent.lookup("#imgMovieAge");
			imgMovieName.setImage(image);
			imgMovieAge.setImage(imageAge);
		} else if (movieName.equals("Cats")) {
			Image image = new Image(getClass().getResourceAsStream("../../Image/Ĺ�� �̹���.jpg"));
			ImageView imgMovieName = (ImageView)parent.lookup("#imgMovieName");
			Image imageAge = new Image(getClass().getResourceAsStream("../../Image/Ĺ�� ����.PNG"));
			ImageView imgMovieAge = (ImageView)parent.lookup("#imgMovieAge");
			imgMovieName.setImage(image);
			imgMovieAge.setImage(imageAge);
		} else if(movieName.equals("Frozen2")) {
			Image image = new Image(getClass().getResourceAsStream("../../Image/�ܿ�ձ� �̹���.jpg"));
			ImageView imgMovieName = (ImageView)parent.lookup("#imgMovieName");
			Image imageAge = new Image(getClass().getResourceAsStream("../../Image/�ܿ�ձ� ����.PNG"));
			ImageView imgMovieAge = (ImageView)parent.lookup("#imgMovieAge");
			imgMovieName.setImage(image);
			imgMovieAge.setImage(imageAge);
		} 
		
		
		dbsrv.open();
		
		addComboBox(parent);
		DbCheck(parent,dbsrv);
		seatServ.againProc(parent);
		
		seatForm.show();
		
	}
	
}
