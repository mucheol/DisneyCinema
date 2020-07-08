package reservationForm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import MovieMain.ControllerMain;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import movieSeat.Service.openSeatService;
import movieSeat.Service.openSeatServiceImpl;
import reservationDbconn.Dbconnect;
import reservationDbconn.Dbconnect_I;

public class ResService implements ResService_I {

	private AlterService_I alter = new AlterService();

	private Calendar cal = Calendar.getInstance();
	private int nowday = cal.get(Calendar.DATE);
	private int nowmonth = cal.get(Calendar.MONTH) + 1;
	private ListView listView = new ListView();

	private ImageView imageview;
	private Image imagelogo = new Image("reservationForm/mainlogo.png");
	private ImageView logoimageview;

	private Button btn;
	private Button btn1;
	private Button btn2;
	private Button btn3;
	private Label lb;
	private Label lb1;
	private Label lb2;

	private Dbconnect_I dbcon = new Dbconnect();
	
	
	
	
	
	
	
	@Override
	public void WindowClose(ActionEvent event) {
		Parent parent = (Parent) event.getSource();
		Stage stage = (Stage) parent.getScene().getWindow();
		stage.close();

	}

	private String getComboBoxString(Parent parent) { // 변경 되었음
		ComboBox<String> cmbname = (ComboBox<String>) parent.lookup("#comboname");
		String name = "";

		name = cmbname.getValue().toString();

		return name;
	}

	@Override
	public void AddComboBox(Parent parent) {
		ComboBox<String> cmbName = (ComboBox<String>) parent.lookup("#comboname");
		if (cmbName != null) {
			cmbName.getItems().addAll("명동영화관", "종로영화관", "충무로영화관", "청량리영화관");
		}

	}

	@Override
	public void openReservation(String movie) {
		Stage ReservationStage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Reservation.fxml"));
		Parent root = null;

		try {
			root = loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Scene scene = new Scene(root);
		RsController ctrler = loader.getController();
		ctrler.setStaticReserveParent(root);

		ctrler.setResServiceForm(root);
		ctrler.resSetting();
		
		RsController controllerRs = new RsController();
		controllerRs.setMovieName(movie);
		//this.movieName = movie;		movieName변수 -> RsController 클래스로 이동
		setImage(root, movie);
		setListView(root, dbcon.Selecttime(movie));

		ctrler.AddComboBox();

		ReservationStage.setTitle("Reservation");
		ReservationStage.initStyle(StageStyle.UNDECORATED);
		ReservationStage.setScene(scene);

		ReservationStage.show();

	}

	public String daytoString(int day) {
		String nowday = Integer.toString(day);
		return nowday;
	}

	public String monthtoString(int month) {
		String nowmonth = Integer.toString(month);
		return nowmonth;
	}

	public int getNowday() {
		return nowday;
	}

	public void setNowday(int nowday) {
		this.nowday = nowday;
	}

	public int getNowmonth() {
		return nowmonth;
	}

	public void setNowmonth(int nowmonth) {
		this.nowmonth = nowmonth;
	}

	public void resSetting(Parent parent) {

		btn1 = (Button) parent.lookup("#sel1");
		btn2 = (Button) parent.lookup("#sel2");
		btn3 = (Button) parent.lookup("#sel3");
		lb = (Label) parent.lookup("#seldate");
		lb1 = (Label) parent.lookup("#seldate1");
		logoimageview = (ImageView) parent.lookup("#mainlogo");

		logoimageview.setImage(imagelogo);

		btn1.setText(monthtoString(nowmonth) + "월 " + daytoString(nowday) + "일");
		btn2.setText(monthtoString(nowmonth) + "월 " + daytoString(nowday + 1) + "일");
		btn3.setText(monthtoString(nowmonth) + "월 " + daytoString(nowday + 2) + "일");
		lb.setText("날짜 : " + monthtoString(nowmonth) + "월  " + daytoString(nowday) + "일");
	}

	public void setImage(Parent parent, String name) { // 변경 되었음
		lb2 = (Label) parent.lookup("#seldate2");
		imageview = (ImageView) parent.lookup("#imageview");

		imageview.setImage(new Image("reservationForm/" + name + ".jpg"));
		if (name.equals("Frozen2")) {
			lb2.setText("영화 : 겨울 왕국 2");
		} else if (name.equals("Baekdusan")) {
			lb2.setText("영화 : 백두산");
		} else if (name.equals("Sidong")) {
			lb2.setText("영화 : 시동");
		} else {
			lb2.setText("영화 : 캣츠");
		}
	}

	public void setListView(Parent parent, ArrayList<String> time) { // 변경 되었음
		listView = (ListView) parent.lookup("#timelist");
		Label lb3 = (Label) parent.lookup("#seldate2");
		Image image = new Image("Image/겨울왕국 연령.PNG");

		if (lb3.getText().equals("영화 : 백두산")) {
			image = new Image("Image/백두산 연령.PNG");
		} else if (lb3.getText().equals("영화 : 시동")) {
			image = new Image("Image/시동 연령.PNG");
		} else if (lb3.getText().equals("영화 : 캣츠")){
			image = new Image("Image/캣츠 연령.PNG");
		} else {
			image = new Image("Image/겨울왕국 연령.PNG");
		}

		for (int i = 0; i < time.size(); i++) {
			HBox hbox = new HBox();
			Label label = new Label(time.get(i));
			ImageView imageageView = new ImageView(image);
			hbox.getChildren().addAll(label, imageageView);
			hbox.setAlignment(Pos.CENTER_LEFT);
			hbox.setSpacing(360);
			listView.getItems().addAll(hbox);
			listView.setFixedCellSize(60);
			listView.setStyle("-fx-font-size:18px;-fx-font-family:bold");
		}
	}

	public void selectBtn(ActionEvent event) {
		if (btn == null) {
			btn = (Button) event.getSource();
			btn.setStyle("-fx-background-color:lavender");
			lb.setText("날짜 :  " + btn.getText());
			return;
		} else if (btn != (Button) event.getSource()) {
			btn.setStyle("-fx-background-color:white");
		}
		btn = (Button) event.getSource();
		btn.setStyle("-fx-background-color:lavender");
		lb.setText("날짜 :  " + btn.getText());

	}

	public void dateBeforeBtn(Parent parent) { // 변경 되었음
		if (nowday == cal.get(Calendar.DATE)) {
			return;
		}
		setNowday(nowday - 1);
		resSetting(parent);
	}

	public void dateNextBtn(Parent parent) { // 변경 되었음
		if (nowday == cal.get(Calendar.DATE) + 2) {
			alter.ErrorMag("오류", "날짜오류", "오늘을 기준으로 4일까지만 예약 가능 합니다.");
			return;
		}
		setNowday(nowday + 1);
		resSetting(parent);
	}

	public void comboBox(ActionEvent event) {
		ComboBox<String> name = (ComboBox<String>) event.getSource();
		lb1.setText("극장 : " + name.getValue());
	}

	public void isSelected(Parent parent) { // 추가 되었음
		listView = (ListView) parent.lookup("#timelist");
		ComboBox<String> cmbname = (ComboBox<String>) parent.lookup("#comboname");
		btn1 = (Button) parent.lookup("#sel1");
		btn2 = (Button) parent.lookup("#sel2");
		btn3 = (Button) parent.lookup("#sel3");
		
		String strDate = "";
		Label lblTime;
		if (listView.getSelectionModel().getSelectedItem() == null) {
			alter.ErrorMag("시간대를 선택해 주세요!!");
			return;
		} else {
			HBox hBox = (HBox)listView.getSelectionModel().getSelectedItem();
			lblTime = (Label)hBox.getChildren().get(0);
		}
		
		
		if(cmbname.getSelectionModel().getSelectedItem() == null) {
			
			alter.ErrorMag("극장을 선택해 주세요!!");
			return;
		} else {
			System.out.println(cmbname.getSelectionModel().getSelectedItem());
		}
		
		if(!btn1.getStyle().equals("-fx-background-color:lavender") 
				&& !btn2.getStyle().equals("-fx-background-color:lavender")
				&& !btn3.getStyle().equals("-fx-background-color:lavender")) {
			alter.ErrorMag("날짜를 선택해주세요!");
			return;
		} else {
			if (btn1.getStyle().equals("-fx-background-color:lavender")) {
				strDate = btn1.getText();
				System.out.println(btn1.getText());
			} else if (btn2.getStyle().equals("-fx-background-color:lavender")) {
				strDate = btn2.getText();
				System.out.println(btn2.getText());
			} else if (btn3.getStyle().equals("-fx-background-color:lavender")) {
				strDate = btn3.getText();
				System.out.println(btn3.getText());
			}
		}

		ControllerMain controllerMain = new ControllerMain();
		openSeatService openSeatServ = new openSeatServiceImpl();		// 좌석 선택 폼 열기
		
		RsController controllerRs = new RsController();
		controllerRs.setMovieDate(strDate);
		controllerRs.setMovieTheater((String)cmbname.getSelectionModel().getSelectedItem());
		controllerRs.setMovieTime(lblTime.getText());
		
		try {
			//dbServ.Insert(controllerMain.getCurrentLoginID(), strDate, (String)cmbname.getSelectionModel().getSelectedItem(), controllerRs.getMovieName(), lblTime.getText());
			// 위에 DB 삽입 부분은 좌석을 고른 후에 예매하기 버튼 클릭시 한 번에 넣어줌
			
			openSeatServ.openSeat(controllerRs.getMovieName());		// 영화 이름 넘김, 현재 로그인 중인 아이디 넘김
		} catch (Exception e) {System.out.println(e);}

	}
}
