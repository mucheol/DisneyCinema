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

	private String getComboBoxString(Parent parent) { // ���� �Ǿ���
		ComboBox<String> cmbname = (ComboBox<String>) parent.lookup("#comboname");
		String name = "";

		name = cmbname.getValue().toString();

		return name;
	}

	@Override
	public void AddComboBox(Parent parent) {
		ComboBox<String> cmbName = (ComboBox<String>) parent.lookup("#comboname");
		if (cmbName != null) {
			cmbName.getItems().addAll("����ȭ��", "���ο�ȭ��", "�湫�ο�ȭ��", "û������ȭ��");
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
		//this.movieName = movie;		movieName���� -> RsController Ŭ������ �̵�
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

		btn1.setText(monthtoString(nowmonth) + "�� " + daytoString(nowday) + "��");
		btn2.setText(monthtoString(nowmonth) + "�� " + daytoString(nowday + 1) + "��");
		btn3.setText(monthtoString(nowmonth) + "�� " + daytoString(nowday + 2) + "��");
		lb.setText("��¥ : " + monthtoString(nowmonth) + "��  " + daytoString(nowday) + "��");
	}

	public void setImage(Parent parent, String name) { // ���� �Ǿ���
		lb2 = (Label) parent.lookup("#seldate2");
		imageview = (ImageView) parent.lookup("#imageview");

		imageview.setImage(new Image("reservationForm/" + name + ".jpg"));
		if (name.equals("Frozen2")) {
			lb2.setText("��ȭ : �ܿ� �ձ� 2");
		} else if (name.equals("Baekdusan")) {
			lb2.setText("��ȭ : ��λ�");
		} else if (name.equals("Sidong")) {
			lb2.setText("��ȭ : �õ�");
		} else {
			lb2.setText("��ȭ : Ĺ��");
		}
	}

	public void setListView(Parent parent, ArrayList<String> time) { // ���� �Ǿ���
		listView = (ListView) parent.lookup("#timelist");
		Label lb3 = (Label) parent.lookup("#seldate2");
		Image image = new Image("Image/�ܿ�ձ� ����.PNG");

		if (lb3.getText().equals("��ȭ : ��λ�")) {
			image = new Image("Image/��λ� ����.PNG");
		} else if (lb3.getText().equals("��ȭ : �õ�")) {
			image = new Image("Image/�õ� ����.PNG");
		} else if (lb3.getText().equals("��ȭ : Ĺ��")){
			image = new Image("Image/Ĺ�� ����.PNG");
		} else {
			image = new Image("Image/�ܿ�ձ� ����.PNG");
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
			lb.setText("��¥ :  " + btn.getText());
			return;
		} else if (btn != (Button) event.getSource()) {
			btn.setStyle("-fx-background-color:white");
		}
		btn = (Button) event.getSource();
		btn.setStyle("-fx-background-color:lavender");
		lb.setText("��¥ :  " + btn.getText());

	}

	public void dateBeforeBtn(Parent parent) { // ���� �Ǿ���
		if (nowday == cal.get(Calendar.DATE)) {
			return;
		}
		setNowday(nowday - 1);
		resSetting(parent);
	}

	public void dateNextBtn(Parent parent) { // ���� �Ǿ���
		if (nowday == cal.get(Calendar.DATE) + 2) {
			alter.ErrorMag("����", "��¥����", "������ �������� 4�ϱ����� ���� ���� �մϴ�.");
			return;
		}
		setNowday(nowday + 1);
		resSetting(parent);
	}

	public void comboBox(ActionEvent event) {
		ComboBox<String> name = (ComboBox<String>) event.getSource();
		lb1.setText("���� : " + name.getValue());
	}

	public void isSelected(Parent parent) { // �߰� �Ǿ���
		listView = (ListView) parent.lookup("#timelist");
		ComboBox<String> cmbname = (ComboBox<String>) parent.lookup("#comboname");
		btn1 = (Button) parent.lookup("#sel1");
		btn2 = (Button) parent.lookup("#sel2");
		btn3 = (Button) parent.lookup("#sel3");
		
		String strDate = "";
		Label lblTime;
		if (listView.getSelectionModel().getSelectedItem() == null) {
			alter.ErrorMag("�ð��븦 ������ �ּ���!!");
			return;
		} else {
			HBox hBox = (HBox)listView.getSelectionModel().getSelectedItem();
			lblTime = (Label)hBox.getChildren().get(0);
		}
		
		
		if(cmbname.getSelectionModel().getSelectedItem() == null) {
			
			alter.ErrorMag("������ ������ �ּ���!!");
			return;
		} else {
			System.out.println(cmbname.getSelectionModel().getSelectedItem());
		}
		
		if(!btn1.getStyle().equals("-fx-background-color:lavender") 
				&& !btn2.getStyle().equals("-fx-background-color:lavender")
				&& !btn3.getStyle().equals("-fx-background-color:lavender")) {
			alter.ErrorMag("��¥�� �������ּ���!");
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
		openSeatService openSeatServ = new openSeatServiceImpl();		// �¼� ���� �� ����
		
		RsController controllerRs = new RsController();
		controllerRs.setMovieDate(strDate);
		controllerRs.setMovieTheater((String)cmbname.getSelectionModel().getSelectedItem());
		controllerRs.setMovieTime(lblTime.getText());
		
		try {
			//dbServ.Insert(controllerMain.getCurrentLoginID(), strDate, (String)cmbname.getSelectionModel().getSelectedItem(), controllerRs.getMovieName(), lblTime.getText());
			// ���� DB ���� �κ��� �¼��� �� �Ŀ� �����ϱ� ��ư Ŭ���� �� ���� �־���
			
			openSeatServ.openSeat(controllerRs.getMovieName());		// ��ȭ �̸� �ѱ�, ���� �α��� ���� ���̵� �ѱ�
		} catch (Exception e) {System.out.println(e);}

	}
}
