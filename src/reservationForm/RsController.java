package reservationForm;

import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class RsController implements Initializable {

	private ResService_I resServ;
	private Parent resForm;

	@FXML private ListView<String> timelist;

	
	static String movieName = "";
	static String movieDate = "";
	static String movieTime = "";
	static String movieTheater = "";
	
	static Parent staticReserveParent;
	
	public void setStaticReserveParent(Parent staticReserveParent) {
		this.staticReserveParent = staticReserveParent;
	}
	
	public Parent getStaticReserveParent() {
		return this.staticReserveParent;
	}
	
	
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	
	public void setMovieDate(String movieDate) {
		this.movieDate = movieDate;
	}
	
	public void setMovieTime(String movieTime) {
		this.movieTime = movieTime;
	}
	
	public void setMovieTheater(String movieTheater) {
		this.movieTheater = movieTheater;
	}
	
	
	public String getMovieName() {
		return this.movieName;
	}
	
	public String getMovieDate() {
		return this.movieDate;
	}
	
	public String getMovieTime() {
		return this.movieTime;
	}
	
	public String getMovieTheater() {
		return this.movieTheater;
	}
	
	
	
	public void setResServiceForm(Parent resForm) {
		this.resForm = resForm;
	}

	public Parent getResForm() {
		return resForm;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		this.resServ = new ResService();

	}

	// -------------------- 취소 버튼 ----------------------------------

	public void cancelBtn(ActionEvent event) {
		resServ.WindowClose(event);
	}
	
	// -------------------- 예약 창 초기 셋팅 ----------------------------------

	public void resSetting() {
		resServ.resSetting(resForm);
	}

	// -------------------- 날짜 이전 버튼 ----------------------------------

	public void dateBeforeBtn() {
		resServ.dateBeforeBtn(resForm);
	}

	// -------------------- 날짜 다음 버튼 ----------------------------------

	public void dateNextBtn() {
		resServ.dateNextBtn(resForm);
	}

	// -------------------- 날짜 선택 버튼 ----------------------------------

	public void selectBtn(ActionEvent event) {
		resServ.selectBtn(event);

	}

	// -------------------- 콤보박스 선택값 라벨추가 버튼 ----------------------------------

	public void comboBox(ActionEvent event) {
		resServ.comboBox(event);
	}

	// -------------------- 콤보박스 선택값 추가 ----------------------------------
	
	public void AddComboBox() {
		resServ.AddComboBox(resForm);
	}
	
	public void isSelected() {
		resServ.isSelected(resForm);
	}
	
}
