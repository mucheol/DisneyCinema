package MovieMain.Service;

import java.util.ArrayList;

import MovieMain.ControllerMain;
import MovieMain.Member;
import MovieMain.DAO.DatabaseService;
import MovieMain.DAO.DatabaseServiceInter;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import movieSeat.DBS.DatabaseServiceImpl;
import reservationDbconn.Dbconnect;
import reservationDbconn.Dbconnect_I;
import reservationForm.ResService;
import reservationForm.ResService_I;

public class MemberService implements MemberServiceInter {

	CommonServiceInter commonServ = new CommonService();
	boolean bOverlapID = false;
	
	@Override
	public void memberOpen() {
		// TODO Auto-generated method stub
		
		try {
			
			Stage stage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../MovieMember.fxml"));
			Parent parent = loader.load();
			Scene scene = new Scene(parent);
			
			ControllerMain controller = loader.getController();
			controller.setMemberForm(parent);
			
			addCmbAge(parent);
			
			stage.setScene(scene);
			stage.show();
			
			
		} catch (Exception e) {System.out.println(e);}
		
	}
	
	
	@Override
	public void memberProc(Parent memberParent) {
		
		try {
			
			Member member = getMember(memberParent);
			
			ArrayList<String> arrID;
			TextField txtID = (TextField)memberParent.lookup("#txtID");
			
			DatabaseServiceInter dbServ = new DatabaseService();
			dbServ.open();
			arrID = dbServ.SelectIDAll();
			
			for (int i=0; i<arrID.size(); i++) {
				if (arrID.get(i).equals(txtID.getText())) {
					bOverlapID = false;
					txtID.clear();
					txtID.requestFocus();
					break;
				}
			}
			
			
			if (member == null) {
				
			} else if (bOverlapID == false) {
				
				commonServ.showMsg("알림", "아이디 중복확인을 하세요.");
				
			} else if (checkID(memberParent) == false) {
				
				commonServ.showMsg("알림", "아이디 : 영문이나 숫자 혹은 그 조합 8~12 자리");
				
			} else if (checkPW(memberParent) == false) {
				
				commonServ.showMsg("알림", "비밀번호 : 영문, 숫자, 특수문자 중 2가지 이상 조합 10자리 이상");
				
			} else {	
				
				System.out.println("------- 회원가입 -------");
				System.out.println("아이디 : " + member.getStrID());
				System.out.println("비밀번호 : " + member.getStrPW());
				System.out.println("비밀번호 확인 : " + member.getStrPWOK());
				System.out.println("이름 : " + member.getStrName());
				System.out.println("이메일 : " + member.getStrEmail());
				System.out.println("성별 : " + member.getStrGender());
				System.out.println("나이 : " + member.getStrAge());
				System.out.println("취미 : " + getHobbyString(member.getIntHobby()));
				
				dbServ.Insert(member);
				Stage stageMember = (Stage)memberParent.getScene().getWindow();
				stageMember.close();
				
				commonServ.showMsg("알림", "회원가입이 완료되었습니다.");
				
				// dbServ.Select(member);
				// System.out.println("회원가입 성공!");
				
			}
			
		} catch (Exception e) {System.out.println(e);}
		
	}
	
	
	
	@Override
	public void chkHobbyAction(Parent memberParent) {
		
		try {
			
			CheckBox chkBook = (CheckBox)memberParent.lookup("#chkBook");
			CheckBox chkGame = (CheckBox)memberParent.lookup("#chkGame");
			CheckBox chkMovie = (CheckBox)memberParent.lookup("#chkMovie");
			CheckBox chkMusic = (CheckBox)memberParent.lookup("#chkMusic");
			CheckBox chkEtc = (CheckBox)memberParent.lookup("#chkEtc");
			
			if (chkBook.isSelected() == true || chkGame.isSelected() == true || chkMovie.isSelected() == true || chkMusic.isSelected() == true) {
				chkEtc.setSelected(false);
			}
			
			
		} catch (Exception e) {System.out.println(e);}
		
	}
	
	
	
	@Override
	public void chkHobbyEtcAction(Parent memberParent) {
		
		try {
			
			CheckBox chkBook = (CheckBox)memberParent.lookup("#chkBook");
			CheckBox chkGame = (CheckBox)memberParent.lookup("#chkGame");
			CheckBox chkMovie = (CheckBox)memberParent.lookup("#chkMovie");
			CheckBox chkMusic = (CheckBox)memberParent.lookup("#chkMusic");
			CheckBox chkEtc = (CheckBox)memberParent.lookup("#chkEtc");
			
			if (chkEtc.isSelected() == true) {
				chkBook.setSelected(false);
				chkGame.setSelected(false);
				chkMovie.setSelected(false);
				chkMusic.setSelected(false);
			}
			
		} catch (Exception e) {System.out.println(e);}
		
	}
	
	
	@Override
	public void reserveMovieAction(ActionEvent event) {
		
		try {
			
			ControllerMain controller = new ControllerMain();
			
			if (controller.getCurrentLoginID() == "") {
				
				commonServ.showMsg("알림", "로그인을 하세요.");
				
			} else {
				
				System.out.println("현재 접속중인 아이디 : " + controller.getCurrentLoginID());
				
				Button btn = (Button) event.getSource();
				String id = btn.getId();
				String moviename = "";
				
				if (id.equals("btnBaek")) {
					moviename = "Baekdusan";
				} else if (id.equals("btnSidong")) {
					moviename = "Sidong";
				} else if (id.equals("btnCats")) {
					moviename = "Cats";
				} else if(id.equals("btnFrozen2")) {
					moviename = "Frozen2";
				} 
				
				ResService_I res = new ResService();
				res.openReservation(moviename);
			}
			
		} catch (Exception e) {System.out.println(e);}
		
	}
	
	
	
	@Override
	public void myReserveAction() {
		
		try {
			
			ControllerMain controllerMain = new ControllerMain();
			String currentLoginID = controllerMain.getCurrentLoginID();
			
			Dbconnect_I dbServMovieTime = new Dbconnect();
			String strDate = dbServMovieTime.SelectDate(currentLoginID);
			
			Store.Service.DatabaseService dbServStore = new Store.Service.DatabaseServiceImpl();
			dbServStore.open();
			String strShoppingList = dbServStore.SelectShoppingList(currentLoginID);
			
			if (currentLoginID == "") {
				
				commonServ.showMsg("알림", "로그인을 하세요.");
				
			} else if (strDate == null && strShoppingList == null) {
				
				commonServ.showMsg("알림", "예매정보가 없습니다.");
				
			} else {
			
				DatabaseServiceInter dbServMain = new DatabaseService();
				dbServMain.open();
				String strName = dbServMain.SelectName(currentLoginID);
				
				String strTeater = dbServMovieTime.SelectTheater(currentLoginID);
				String strMovieName = dbServMovieTime.SelectName(currentLoginID);
				String strTime = dbServMovieTime.SelectTime(currentLoginID);
				
				movieSeat.DBS.DatabaseService dbServSeat = new DatabaseServiceImpl();
				dbServSeat.open();
				String strSeat = dbServSeat.SelectSeat(currentLoginID);
				String strPeople = dbServSeat.SelectPeople(currentLoginID);
				String strPrice = dbServSeat.SelectPrice(currentLoginID);
				
				String strAmount = dbServStore.SelectAmount(currentLoginID);
				
				String strMyReserve;
				strMyReserve = strName + " 님의 예매내역"
						+ "\n영화이름 : " + strMovieName
						+ "\n날짜 : " + strDate
						+ "\n영화관 : " + strTeater
						+ "\n상영시간 : " + strTime
						+ "\n좌석 : " + strSeat
						+ "\n인원 : " + strPeople
						+ "\n금액 : " + strPrice + "원"
						+ "\n\n쇼핑리스트\n" + strShoppingList
						+ "가격 : " + strAmount + "원\n";
				
				commonServ.showMyReserve("예매내역", strMyReserve);
				
			}
			
			
		} catch (Exception e) {System.out.println(e);}
		
	}
	
	
	
	public Member getMember(Parent memberParent) {
		
		try {
			
			TextField txtID = (TextField)memberParent.lookup("#txtID");
			PasswordField txtPW = (PasswordField)memberParent.lookup("#txtPW");
			PasswordField txtPWOK = (PasswordField)memberParent.lookup("#txtPWOK");
			TextField txtName = (TextField)memberParent.lookup("#txtName");
			TextField txtEmail = (TextField)memberParent.lookup("#txtEmail");
			
			RadioButton radioMail = (RadioButton)memberParent.lookup("#radioMail");
			RadioButton radioFemail = (RadioButton)memberParent.lookup("#radioFemail");
			
			

			ComboBox<String> cmbAge = (ComboBox<String>)memberParent.lookup("#cmbAge");
			
			Member member = new Member();
			
			if (getInform(memberParent) == true) {
			
				member.setStrID(txtID.getText());
				member.setStrPW(txtPW.getText());
				member.setStrPWOK(txtPWOK.getText());
				member.setStrName(txtName.getText());
				member.setStrEmail(txtEmail.getText());
				
			} else {
				
				return null;
				
			}
			
			
			if (getGender(memberParent) != null) {
				member.setStrGender(getGender(memberParent));
			} else {
				return null;
			}
			
			
			if (getAge(memberParent) != null) {
				member.setStrAge(getAge(memberParent));
			} else {
				return null;
			}
			
			
			if (getHobbyInt(memberParent) != 0) {
				member.setIntHobby(getHobbyInt(memberParent));
			} else {
				return null;
			}
			
			return member;
			
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		
	}
	
	
	
	public boolean getInform(Parent memberParent) {
		
		try {
			
			TextField txtID = (TextField)memberParent.lookup("#txtID");
			PasswordField txtPW = (PasswordField)memberParent.lookup("#txtPW");
			PasswordField txtPWOK = (PasswordField)memberParent.lookup("#txtPWOK");
			TextField txtName = (TextField)memberParent.lookup("#txtName");
			TextField txtEmail = (TextField)memberParent.lookup("#txtEmail");
			
			if (txtID.getText().equals("")) {
				
				commonServ.showMsg("알림", "아이디를 입력하세요.");
				txtID.requestFocus();
				return false;
				
			} else if (txtPW.getText().equals("")) {
				
				commonServ.showMsg("알림", "비밀번호를 입력하세요.");
				txtPW.requestFocus();
				return false;
				
			} else if (txtPWOK.getText().equals("")) {
				
				commonServ.showMsg("알림", "비밀번호를 확인하세요.");
				txtPWOK.requestFocus();
				return false;
				
			} else if (!txtPW.getText().equals(txtPWOK.getText())) {
			
				commonServ.showMsg("알림", "비밀번호가 다릅니다.");
				txtPW.clear();
				txtPWOK.clear();
				txtPW.requestFocus();
				return false;
				
			} else if (txtName.getText().equals("")) {
				
				commonServ.showMsg("알림", "이름을 입력하세요.");
				txtName.requestFocus();
				return false;
				
			} else if (txtEmail.getText().equals("")) {
				
				commonServ.showMsg("알림", "이메일을 입력하세요.");
				txtEmail.requestFocus();
				return false;
				
			} else {
				
				return true;
				
			}
			
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		
	}
	
	
	public String getGender(Parent memberParent) {
		
		try {
			
			RadioButton radioMale = (RadioButton)memberParent.lookup("#radioMale");
			RadioButton radioFemale = (RadioButton)memberParent.lookup("#radioFemale");
			
			String strGender = null;
			
			if (radioMale.isSelected() == false && radioFemale.isSelected() == false) {
				
				commonServ.showMsg("알림", "성별을 선택하세요.");
				radioMale.requestFocus();
				return null;
				
			} else if (radioMale.isSelected() == true) {
				
				strGender = "Male";
				
			} else if (radioFemale.isSelected() == true) {
				
				strGender = "Female";
				
			}
			
			return strGender;
			
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		
	}
	
	
	public int getHobbyInt(Parent memberParent) {
		
		try {
			
			CheckBox chkBook = (CheckBox)memberParent.lookup("#chkBook");		// 1
			CheckBox chkGame = (CheckBox)memberParent.lookup("#chkGame");		// 2
			CheckBox chkMovie = (CheckBox)memberParent.lookup("#chkMovie");		// 4
			CheckBox chkMusic = (CheckBox)memberParent.lookup("#chkMusic");		// 8
			CheckBox chkEtc = (CheckBox)memberParent.lookup("#chkEtc");			// 16
			
			int result = 0;
			
			if (chkBook.isSelected() == false && chkGame.isSelected() == false && chkMovie.isSelected() == false && chkMusic.isSelected() == false && chkEtc.isSelected() == false) {
				
				commonServ.showMsg("알림", "취미를 선택하세요.");
				chkBook.requestFocus();
				return 0;
				
			} 

			if (chkEtc.isSelected() == true) {
				result = 16;
			}
			
			if (chkBook.isSelected() == true) {
				result += 1;
			}
			
			if (chkGame.isSelected() == true) {
				result += 2;
			}
			
			if (chkMovie.isSelected() == true) {
				result += 4;
			}
			
			if (chkMusic.isSelected() == true) {
				result += 8;
			}
			
			return result;
			
		} catch (Exception e) {
			System.out.println(e);
			return 0;
		}
		
	}
	
	
	
	public String getHobbyString(int intHobbyValue) {
		
		String str = null;
		
		
		if (intHobbyValue == 16) {
			str = "기타";
		} else if (intHobbyValue == 1) {
			str = "독서";
		} else if (intHobbyValue == 2) {
			str = "게임";
		} else if (intHobbyValue == 4) {
			str = "영화감상";
		} else if (intHobbyValue == 8) {
			str = "음악감상";
		} else if (intHobbyValue == 3) {
			str = "독서, 게임";
		} else if (intHobbyValue == 5) {
			str = "독서, 영화감상";
		} else if (intHobbyValue == 9) {
			str = "독서, 음악감상";
		} else if (intHobbyValue == 6) {
			str = "게임, 영화감상";
		} else if (intHobbyValue == 10) {
			str = "게임, 음악감상";
		} else if (intHobbyValue == 12) {
			str = "영화감상, 음악감상";
		}
		
		return str;
		
	}
	
	
	public String getAge(Parent memberParent) {
		
		try {
			
			ComboBox<String> cmbAge = (ComboBox<String>)memberParent.lookup("#cmbAge");

			if (cmbAge.getSelectionModel().getSelectedIndex() == -1) {
				
				commonServ.showMsg("알림", "나이를 선택하세요.");
				cmbAge.requestFocus();
				return null;
			
			} else {
				
				return cmbAge.getSelectionModel().getSelectedItem();

			}
			
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		
	}
	
	
	public void addCmbAge(Parent memberParent) {
		
		try {
			
			ComboBox<String> cmbAge = (ComboBox<String>)memberParent.lookup("#cmbAge");
			
			cmbAge.getItems().addAll("20대 미만", "20대", "30대", "40대", "50대", "60대 이상");
			
		} catch (Exception e) {System.out.println(e);}
		
	}
	
	
	public boolean checkID(Parent memberParent) {
		
		try {
			
			TextField txtID = (TextField)memberParent.lookup("#txtID");
			boolean checkNum = false;
			boolean checkEng = false;
			
			if (txtID.getText().length() < 8 || txtID.getText().length() > 12 ) {
				txtID.clear();
				txtID.requestFocus();
				return false;
			}
			
			if (txtID.getText().charAt(0) >= 48 && (int)txtID.getText().charAt(0) <= 57) {
				// 첫 번째 글자 숫자 안됨
				return false;
			}
			
			for (int i=0; i<txtID.getText().length(); i++) {
				
				if ((int)txtID.getText().charAt(i) >= 48 && (int)txtID.getText().charAt(i) <= 57) {
					// 숫자
					checkNum = true;
				} else if (((int)txtID.getText().charAt(i) >= 65 && (int)txtID.getText().charAt(i) <= 90) || ((int)txtID.getText().charAt(i) >= 97 && (int)txtID.getText().charAt(i) <= 122)) {
					// 대문자 영어, 소문자 영어
					checkEng = true;
				} 
				
			}
			
			if (checkNum == true || checkEng == true) {
				return true;
			} else {
				txtID.clear();
				txtID.requestFocus();
				return false;
			}
				
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		
	}
	
	
	public boolean checkPW(Parent memberParent) {
		
		try {
			
			PasswordField txtPW = (PasswordField)memberParent.lookup("#txtPW");
			PasswordField txtPWOK = (PasswordField)memberParent.lookup("#txtPWOK");
			boolean checkNum = false;
			boolean checkEng = false;
			boolean checkSpecial = false;
			
			if (txtPW.getText().length() < 10 ) {
				txtPW.clear();
				txtPWOK.clear();
				txtPW.requestFocus();
				return false;
			}
			
			for (int i=0; i<txtPW.getText().length(); i++) {
				
				if ((int)txtPW.getText().charAt(i) >= 48 && (int)txtPW.getText().charAt(i) <= 57) {
					// 숫자
					checkNum = true;
				} else if (((int)txtPW.getText().charAt(i) >= 65 && (int)txtPW.getText().charAt(i) <= 90) || ((int)txtPW.getText().charAt(i) >= 97 && (int)txtPW.getText().charAt(i) <= 122)) {
					// 대문자 영어, 소문자 영어
					checkEng = true;
				} else if ((int)txtPW.getText().charAt(i) >= 33 && (int)txtPW.getText().charAt(i) <= 125) {
					// 그 외의 특수문자
					checkSpecial = true;
				}
				
			}
			
			if ((checkNum == true && checkEng == true) || (checkNum == true && checkSpecial == true) || (checkEng == true && checkSpecial == true)) {
				return true;
			} else {
				txtPW.clear();
				txtPWOK.clear();
				txtPW.requestFocus();
				return false;
			}
			
			
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		
	}
	
	
	public void overlapID(Parent memberParent) {
		
		try {
			
			ArrayList<String> arrID;
			
			TextField txtID = (TextField)memberParent.lookup("#txtID");
			PasswordField txtPW = (PasswordField)memberParent.lookup("#txtPW");
			
			DatabaseServiceInter dbServ = new DatabaseService();
			dbServ.open();
			arrID = dbServ.SelectIDAll();
			
			if (txtID.getText().equals("")) {
				commonServ.showMsg("알림", "아이디를 입력하세요.");
				txtID.requestFocus();
			} else {
				
				if (arrID.size() == 0) {
					commonServ.showMsg("알림", "사용 가능한 아이디입니다.");
					bOverlapID = true;
					txtPW.requestFocus();
				} else {
					for (int i=0; i<arrID.size(); i++) {
						if (arrID.get(i).equals(txtID.getText())) {
							commonServ.showMsg("알림", "이미 존재하는 아이디입니다.");
							bOverlapID = false;
							txtID.clear();
							txtID.requestFocus();
							break;
						} else if (i == arrID.size()-1) {
							commonServ.showMsg("알림", "사용 가능한 아이디입니다.");
							bOverlapID = true;
							txtPW.requestFocus();
							break;
						}
					}
				}
			}
			
		} catch (Exception e) {System.out.println(e);}
		
	}



	
	
	
	
	
}
