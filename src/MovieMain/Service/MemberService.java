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
				
				commonServ.showMsg("�˸�", "���̵� �ߺ�Ȯ���� �ϼ���.");
				
			} else if (checkID(memberParent) == false) {
				
				commonServ.showMsg("�˸�", "���̵� : �����̳� ���� Ȥ�� �� ���� 8~12 �ڸ�");
				
			} else if (checkPW(memberParent) == false) {
				
				commonServ.showMsg("�˸�", "��й�ȣ : ����, ����, Ư������ �� 2���� �̻� ���� 10�ڸ� �̻�");
				
			} else {	
				
				System.out.println("------- ȸ������ -------");
				System.out.println("���̵� : " + member.getStrID());
				System.out.println("��й�ȣ : " + member.getStrPW());
				System.out.println("��й�ȣ Ȯ�� : " + member.getStrPWOK());
				System.out.println("�̸� : " + member.getStrName());
				System.out.println("�̸��� : " + member.getStrEmail());
				System.out.println("���� : " + member.getStrGender());
				System.out.println("���� : " + member.getStrAge());
				System.out.println("��� : " + getHobbyString(member.getIntHobby()));
				
				dbServ.Insert(member);
				Stage stageMember = (Stage)memberParent.getScene().getWindow();
				stageMember.close();
				
				commonServ.showMsg("�˸�", "ȸ�������� �Ϸ�Ǿ����ϴ�.");
				
				// dbServ.Select(member);
				// System.out.println("ȸ������ ����!");
				
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
				
				commonServ.showMsg("�˸�", "�α����� �ϼ���.");
				
			} else {
				
				System.out.println("���� �������� ���̵� : " + controller.getCurrentLoginID());
				
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
				
				commonServ.showMsg("�˸�", "�α����� �ϼ���.");
				
			} else if (strDate == null && strShoppingList == null) {
				
				commonServ.showMsg("�˸�", "���������� �����ϴ�.");
				
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
				strMyReserve = strName + " ���� ���ų���"
						+ "\n��ȭ�̸� : " + strMovieName
						+ "\n��¥ : " + strDate
						+ "\n��ȭ�� : " + strTeater
						+ "\n�󿵽ð� : " + strTime
						+ "\n�¼� : " + strSeat
						+ "\n�ο� : " + strPeople
						+ "\n�ݾ� : " + strPrice + "��"
						+ "\n\n���θ���Ʈ\n" + strShoppingList
						+ "���� : " + strAmount + "��\n";
				
				commonServ.showMyReserve("���ų���", strMyReserve);
				
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
				
				commonServ.showMsg("�˸�", "���̵� �Է��ϼ���.");
				txtID.requestFocus();
				return false;
				
			} else if (txtPW.getText().equals("")) {
				
				commonServ.showMsg("�˸�", "��й�ȣ�� �Է��ϼ���.");
				txtPW.requestFocus();
				return false;
				
			} else if (txtPWOK.getText().equals("")) {
				
				commonServ.showMsg("�˸�", "��й�ȣ�� Ȯ���ϼ���.");
				txtPWOK.requestFocus();
				return false;
				
			} else if (!txtPW.getText().equals(txtPWOK.getText())) {
			
				commonServ.showMsg("�˸�", "��й�ȣ�� �ٸ��ϴ�.");
				txtPW.clear();
				txtPWOK.clear();
				txtPW.requestFocus();
				return false;
				
			} else if (txtName.getText().equals("")) {
				
				commonServ.showMsg("�˸�", "�̸��� �Է��ϼ���.");
				txtName.requestFocus();
				return false;
				
			} else if (txtEmail.getText().equals("")) {
				
				commonServ.showMsg("�˸�", "�̸����� �Է��ϼ���.");
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
				
				commonServ.showMsg("�˸�", "������ �����ϼ���.");
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
				
				commonServ.showMsg("�˸�", "��̸� �����ϼ���.");
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
			str = "��Ÿ";
		} else if (intHobbyValue == 1) {
			str = "����";
		} else if (intHobbyValue == 2) {
			str = "����";
		} else if (intHobbyValue == 4) {
			str = "��ȭ����";
		} else if (intHobbyValue == 8) {
			str = "���ǰ���";
		} else if (intHobbyValue == 3) {
			str = "����, ����";
		} else if (intHobbyValue == 5) {
			str = "����, ��ȭ����";
		} else if (intHobbyValue == 9) {
			str = "����, ���ǰ���";
		} else if (intHobbyValue == 6) {
			str = "����, ��ȭ����";
		} else if (intHobbyValue == 10) {
			str = "����, ���ǰ���";
		} else if (intHobbyValue == 12) {
			str = "��ȭ����, ���ǰ���";
		}
		
		return str;
		
	}
	
	
	public String getAge(Parent memberParent) {
		
		try {
			
			ComboBox<String> cmbAge = (ComboBox<String>)memberParent.lookup("#cmbAge");

			if (cmbAge.getSelectionModel().getSelectedIndex() == -1) {
				
				commonServ.showMsg("�˸�", "���̸� �����ϼ���.");
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
			
			cmbAge.getItems().addAll("20�� �̸�", "20��", "30��", "40��", "50��", "60�� �̻�");
			
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
				// ù ��° ���� ���� �ȵ�
				return false;
			}
			
			for (int i=0; i<txtID.getText().length(); i++) {
				
				if ((int)txtID.getText().charAt(i) >= 48 && (int)txtID.getText().charAt(i) <= 57) {
					// ����
					checkNum = true;
				} else if (((int)txtID.getText().charAt(i) >= 65 && (int)txtID.getText().charAt(i) <= 90) || ((int)txtID.getText().charAt(i) >= 97 && (int)txtID.getText().charAt(i) <= 122)) {
					// �빮�� ����, �ҹ��� ����
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
					// ����
					checkNum = true;
				} else if (((int)txtPW.getText().charAt(i) >= 65 && (int)txtPW.getText().charAt(i) <= 90) || ((int)txtPW.getText().charAt(i) >= 97 && (int)txtPW.getText().charAt(i) <= 122)) {
					// �빮�� ����, �ҹ��� ����
					checkEng = true;
				} else if ((int)txtPW.getText().charAt(i) >= 33 && (int)txtPW.getText().charAt(i) <= 125) {
					// �� ���� Ư������
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
				commonServ.showMsg("�˸�", "���̵� �Է��ϼ���.");
				txtID.requestFocus();
			} else {
				
				if (arrID.size() == 0) {
					commonServ.showMsg("�˸�", "��� ������ ���̵��Դϴ�.");
					bOverlapID = true;
					txtPW.requestFocus();
				} else {
					for (int i=0; i<arrID.size(); i++) {
						if (arrID.get(i).equals(txtID.getText())) {
							commonServ.showMsg("�˸�", "�̹� �����ϴ� ���̵��Դϴ�.");
							bOverlapID = false;
							txtID.clear();
							txtID.requestFocus();
							break;
						} else if (i == arrID.size()-1) {
							commonServ.showMsg("�˸�", "��� ������ ���̵��Դϴ�.");
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
