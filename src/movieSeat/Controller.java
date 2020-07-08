package movieSeat;

import java.net.URL;
import java.util.ResourceBundle;

import Store.Service.MenuService;
import Store.Service.MenuServiceImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import movieSeat.Service.CommonService;
import movieSeat.Service.CommonServiceImpl;
import movieSeat.Service.SeatService;
import movieSeat.Service.SeatServiceImpl;
import movieSeat.Service.openSeatService;
import movieSeat.Service.openSeatServiceImpl;

public class Controller implements Initializable {
	private Parent seatForm;
	private SeatService seatServ;
	private CommonService commonServ;
	private openSeatService openSeatServ;
	private MenuService menuServ;
	
	public void setSeatForm(Parent seatForm) {
		this.seatForm = seatForm;
	}
	
	@FXML private TextField txtPeople;
	@FXML private TextField txtPrice;
	@FXML private TextField txtSeat;
	@FXML private ImageView imgMovieName; 
	
	
	static Parent staticSeatParent;
	
	public void setStaticSeatParent(Parent staticSeatParent) {
		this.staticSeatParent = staticSeatParent;
	}
	
	public Parent getStaticSeatParent() {
		return this.staticSeatParent;
	}
	
	
	public void setImageView(String str) {
		Image image = new Image(getClass().getResourceAsStream(str));
		this.imgMovieName = new ImageView(image);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		seatServ = new SeatServiceImpl();
		commonServ = new CommonServiceImpl();
		openSeatServ = new openSeatServiceImpl();
		menuServ = new MenuServiceImpl();
		
	}
	public void cancelProc(ActionEvent event) {
		commonServ.Close(event);
	}
	public void changeTeen(ActionEvent event) {
		seatServ.changeTeen(seatForm);
	}
	public void changeAdult(ActionEvent event) {
		seatServ.changeAdult(seatForm);
	}
	public void changeOld(ActionEvent event) {
		seatServ.changeOld(seatForm);
	}
	public void reProc(ActionEvent event) {
		seatServ.againProc(seatForm);
	}
	public void reserveProc(ActionEvent event) {
		if (seatServ.reserveProc(seatForm) == true) {
			menuServ.openMenu();
		};
	}
	public void seatProc(ActionEvent event) {
		seatServ.seatProc(seatForm);
	}
	
	/*
	public void seatOpen(ActionEvent event) throws SQLException {
		openSeatServ.openSeat();
	}
	*/
}
