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

	// -------------------- ��� ��ư ----------------------------------

	public void cancelBtn(ActionEvent event) {
		resServ.WindowClose(event);
	}
	
	// -------------------- ���� â �ʱ� ���� ----------------------------------

	public void resSetting() {
		resServ.resSetting(resForm);
	}

	// -------------------- ��¥ ���� ��ư ----------------------------------

	public void dateBeforeBtn() {
		resServ.dateBeforeBtn(resForm);
	}

	// -------------------- ��¥ ���� ��ư ----------------------------------

	public void dateNextBtn() {
		resServ.dateNextBtn(resForm);
	}

	// -------------------- ��¥ ���� ��ư ----------------------------------

	public void selectBtn(ActionEvent event) {
		resServ.selectBtn(event);

	}

	// -------------------- �޺��ڽ� ���ð� ���߰� ��ư ----------------------------------

	public void comboBox(ActionEvent event) {
		resServ.comboBox(event);
	}

	// -------------------- �޺��ڽ� ���ð� �߰� ----------------------------------
	
	public void AddComboBox() {
		resServ.AddComboBox(resForm);
	}
	
	public void isSelected() {
		resServ.isSelected(resForm);
	}
	
}
