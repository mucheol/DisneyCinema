package movieSeat.Service;

import java.util.ArrayList;
import java.util.Map;

import MovieMain.ControllerMain;
import MovieMain.Service.CommonServiceInter;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;
import movieSeat.SeatObject;
import movieSeat.DBS.DatabaseService;
import movieSeat.DBS.DatabaseServiceImpl;
import reservationDbconn.Dbconnect;
import reservationDbconn.Dbconnect_I;
import reservationForm.RsController;

public class SeatServiceImpl implements SeatService {
	int teen,adult,old ;
	int people,price;
	String strPp,strPr;
	String str ="";
	final String[] AArr = {"#A1","#A2", "#A3","#A4","#A5","#A6","#A7","#A8","#A9","#A10","#A11","#A12"};
	final String[] BArr = {"#B1","#B2", "#B3","#B4","#B5","#B6","#B7","#B8","#B9","#B10","#B11","#B12"};
	final String[] CArr = {"#C1","#C2", "#C3","#C4","#C5","#C6","#C7","#C8","#C9","#C10","#C11","#C12"};
	final String[] DArr = {"#D1","#D2", "#D3","#D4","#D5","#D6","#D7","#D8","#D9","#D10","#D11","#D12"};
	final String[] EArr = {"#E1","#E2", "#E3","#E4","#E5","#E6","#E7","#E8","#E9","#E10","#E11","#E12"};
	final String[] FArr = {"#F1","#F2", "#F3","#F4","#F5","#F6","#F7","#F8","#F9","#F10","#F11","#F12"};
	final String[] GArr = {"#G1","#G2", "#G3","#G4","#G5","#G6","#G7","#G8","#G9","#G10","#G11","#G12"};
	final String[] HArr = {"#H1","#H2", "#H3","#H4","#H5","#H6","#H7","#H8","#H9","#H10","#H11","#H12"};
	final String[] IArr = {"#I1","#I2", "#I3","#I4","#I5","#I6","#I7","#I8","#I9","#I10","#I11","#I12"};
	final String[] JArr = {"#J1","#J2", "#J3","#J4","#J5","#J6","#J7","#J8","#J9","#J10","#J11","#J12"};
	final String[] KArr = {"#K1","#K2", "#K3","#K4","#K5","#K6","#K7","#K8","#K9","#K10","#K11","#K12"};
	final String[] LArr = {"#L1","#L2", "#L3","#L4","#L5","#L6","#L7","#L8","#L9","#L10","#L11","#L12"};
	final String[][] Arr = {AArr,BArr,CArr,DArr,EArr,FArr,GArr,HArr,IArr,JArr,KArr,LArr};
	ArrayList<String> ArrSelectedSeat = new ArrayList<String>();
	ArrayList<String> ArrDbSeat = new ArrayList<String>();
	
	
	public boolean reserveProc(Parent seatForm) {
		CommonService comServ = new CommonServiceImpl();
		SeatObject seatOb = getObject(seatForm);
		CommonServiceInter commonServMain = new MovieMain.Service.CommonService();
		
		if(seatOb == null) {
			return false;
		}
		
		if (seatOb.getPeople() !=  ArrSelectedSeat.size()) {
			commonServMain.showMsg("알림", "인원수에 맞게 좌석을 선택하세요.");
			//comServ.ErrorMag("error", "인원수랑 좌석수가 맞지않습니다.", "인원수에 맞게 좌석수를 선택해주세요");
			ArrSelectedSeat.clear();
			seatOb.setPeople(0);
			return false;
			
		} else {
		
			System.out.println("에약좌석 : " + seatOb.getSeat());
			System.out.println("인원 : "+ seatOb.getPeople() +"명");
			System.out.println("가격 : "+ seatOb.getPrice()+"원");
				
			ControllerMain controllerMain = new ControllerMain();
			RsController controllerRs = new RsController();
			
			Dbconnect_I dbServTime = new Dbconnect();
			dbServTime.Insert(controllerMain.getCurrentLoginID(), controllerRs.getMovieDate(), controllerRs.getMovieTheater(), controllerRs.getMovieName(), controllerRs.getMovieTime());
			
			DatabaseService dbServ = new DatabaseServiceImpl();
			dbServ.open();
			dbServ.Insert(controllerMain.getCurrentLoginID(), seatOb);
			
			Stage stageSeat = (Stage)seatForm.getScene().getWindow();
			stageSeat.close();
			Stage stageReserve = (Stage)controllerRs.getStaticReserveParent().getScene().getWindow();
			stageReserve.close();
			
			commonServMain.showMsg("예매 완료", "영화 예매가 완료되었습니다.");
			
			return true;
			
		}
	}
	
	
	public String[][] getDbSeat() {
		return Arr;
	}
	public ArrayList<String> getSeatProc() {
		return ArrSelectedSeat;
	}
	
	private SeatObject getObject(Parent seatForm) {	// seatObject에 저장
		SeatObject seatOb = new SeatObject();

		for (int i=0;i<12;i++) {
			for(int j=0;j<12;j++) {
				ToggleButton btn = (ToggleButton)seatForm.lookup(Arr[i][j].toString());
				if(btn.isDisable()== true) {
					
				} else if(btn.isSelected() == true) {
					ArrSelectedSeat.add(Arr[i][j].toString().substring(1, Arr[i][j].length()));
				}
			}
		}
		seatOb.setSeat(ArrSelectedSeat);
		seatOb.setPeople(people);
		seatOb.setPrice(price);
		return seatOb;
	}
	
	public void againProc(Parent seatForm) { //다시선택하기 버튼 이벤트
		TextField txtSeat = (TextField)seatForm.lookup("#txtSeat");
		for (int i=0;i<12;i++) {
			for(int j=0;j<12;j++) {
				ToggleButton btn = (ToggleButton)seatForm.lookup(Arr[i][j].toString());
				if(btn.isSelected() == true) {
					btn.setSelected(false);
				}
			}
		}
		txtSeat.setText("");
	}
	
	//콤보박스 적용
	public void addComboBox(Parent seatForm) {
		ComboBox<Integer> cbTeen = (ComboBox<Integer>)seatForm.lookup("#cbTeen");
		ComboBox<Integer> cbAdult = (ComboBox<Integer>)seatForm.lookup("#cbAdult");
		ComboBox<Integer> cbOld = (ComboBox<Integer>)seatForm.lookup("#cbOld");

		cbTeen.getItems().addAll(0,1,2,3,4);
		
		
		cbAdult.getItems().addAll(0,1,2,3,4);
		
		
		cbOld.getItems().addAll(0,1,2,3,4);
		
		
	}
	
	public void changeTeen(Parent seatForm) {	//청소년 콤보박스 바꿨을때 이벤트
		ComboBox<Integer> cbTeen = (ComboBox<Integer>)seatForm.lookup("#cbTeen");
		
		teen = cbTeen.getValue();
		addTxt(seatForm);
			
	}
	public void changeAdult(Parent seatForm) {	//성인 콤보박스 바꿨을때 이벤트
		ComboBox<Integer> cbAdult = (ComboBox<Integer>)seatForm.lookup("#cbAdult");
		adult = cbAdult.getValue();
		addTxt(seatForm);
		
	}
	public void changeOld(Parent seatForm) {	//노약자 콤보박스 바꿨을때 이벤트
		ComboBox<Integer> cbOld = (ComboBox<Integer>)seatForm.lookup("#cbOld");
		
		old = cbOld.getValue();
		addTxt(seatForm);
		
	}
	public void addTxt(Parent seatForm) {	// 인원이랑 가격 텍스트적용
		TextField txtPeople = (TextField)seatForm.lookup("#txtPeople");
		TextField txtPrice = (TextField)seatForm.lookup("#txtPrice");
		
		people = old + adult + teen;
		if(old!=0||teen!=0) {
			price = ((old+teen) * 8000) + (adult *10000);
		} else {
			price = people * 10000;
		}
		
		strPp = String.valueOf(people);
		strPr = String.valueOf(price);
		txtPeople.setStyle("-fx-text-inner-color: blue;");
		txtPrice.setStyle("-fx-text-inner-color: blue;");
		txtPeople.setText(strPp);
		txtPrice.setText(strPr);
		txtPeople.setDisable(true);
		txtPrice.setDisable(true);
	}

	@Override
	public void seatProc(Parent seatForm) {
		TextField txtSeat = (TextField)seatForm.lookup("#txtSeat");
		str = "";
		boolean bFlag = false;
		
		for (int i=0;i<12;i++) {
			
			for(int j=0;j<12;j++) {
				ToggleButton btn = (ToggleButton)seatForm.lookup(Arr[i][j].toString());
				if(btn.isDisable()== true) {
					
				} else if(btn.isSelected() == true) {
					bFlag = true;
					str += Arr[i][j].substring(1, Arr[i][j].length())+", ";
					txtSeat.setStyle("-fx-text-inner-color: blue;");
					txtSeat.setText(str.substring(0, str.length()-2));
					txtSeat.setDisable(true);
				} 
			}
		}
		
		if (bFlag == false) {
			txtSeat.setText("");
			txtSeat.setDisable(true);
		}
		
	}

}
