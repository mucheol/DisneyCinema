package MovieMain.Service;

import java.util.ArrayList;

import MovieMain.ControllerMain;
import MovieMain.DAO.DatabaseService;
import MovieMain.DAO.DatabaseServiceInter;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginService implements LoginServiceInter {

	CommonServiceInter commonServ = new CommonService();
	static boolean bSaveID = false;
	
	@Override
	public void loginProc(Parent loginParent, Parent mainParent) {
		// TODO Auto-generated method stub
	
		try {
			
			TextField txtID = (TextField)loginParent.lookup("#txtID");
			PasswordField txtPW = (PasswordField)loginParent.lookup("#txtPW");
			CommonServiceInter commonServ = new CommonService();
			
			if (txtID.getText().equals("")) {		// equals() 안에 null 값 넣으면 작동안함.
				commonServ.showMsg("알림", "아이디를 입력하세요.");
			} else if (txtPW.getText().equals("")) {
				commonServ.showMsg("알림", "비밀번호를 입력하세요.");
			} else {
				System.out.println("------ 로그인 시도 ------");
				System.out.println("아이디 : " + txtID.getText() + "\n비밀번호 : " + txtPW.getText());
				
				DatabaseServiceInter dbServ = new DatabaseService();
				ArrayList<String> arrID = new ArrayList<String>();
				
				dbServ.open();
				arrID = dbServ.SelectIDAll();
				
				if (arrID.size() == 0) {
					commonServ.showMsg("알림", "회원이 존재하지 않습니다.");
				}
				
				
				boolean bFlag = false;
				for (int i=0; i<arrID.size(); i++) {
					if (arrID.get(i).equals(txtID.getText())) {
						
						if (dbServ.SelectPW(txtID.getText()).equals(txtPW.getText())) {
							
							System.out.println("로그인 성공(DB)");
							bFlag = true;
							
							ControllerMain controller = new ControllerMain();
							controller.setCurrentLoginID(txtID.getText());
							controller.setCurrentLoginPW(txtPW.getText());
							
							Stage stage = (Stage)loginParent.getScene().getWindow();
							stage.close();
							
							Button btnLogin = (Button)mainParent.lookup("#btnLogin");
							btnLogin.setText(controller.getCurrentLoginID() + " 님 \n로그인 중입니다.");
							
							break;
							
						} else {
							commonServ.showMsg("알림", "비밀번호가 틀렸습니다.");
							txtPW.clear();
							txtPW.requestFocus();
							bFlag = true;
							break;
						}
						
					} 
				}
				
				if (bFlag == false) {
					commonServ.showMsg("알림", "존재하지 않는 아이디입니다.");
					if (bSaveID == false) txtID.clear(); 
					txtPW.clear();
					txtID.requestFocus();
				}
				
			}
			
		} catch (Exception e) {System.out.println(e);}
		
	}

	
	
	@Override
	public void loginOpen(Parent mainParent) {
		// TODO Auto-generated method stub
		
		try {
			
			ControllerMain controller = new ControllerMain();
			
			if (controller.getCurrentLoginID().equals("") && controller.getCurrentLoginPW().equals("")) {
				
				Stage stage = new Stage();
				
				FXMLLoader loader = new FXMLLoader(getClass().getResource("../MovieLogin.fxml"));
				Parent parent = loader.load();
				Scene scene = new Scene(parent);
				
				RadioButton radioSaveID = (RadioButton)parent.lookup("#radioSaveID");
				if (bSaveID == true) radioSaveID.setSelected(true);
				else radioSaveID.setSelected(false);
				
				controller = loader.getController();		// 없어도 폼은 열리나 loginProc(Parent loginParent)의 매개변수를 넘겨주기 위함
				controller.setLoginForm(parent);
				controller.setMainForm(mainParent);
				
				stage.setScene(scene);
				stage.show();
				
				System.out.println("로그인 창 열기");
			
			} else {
				
				
				controller.setCurrentLoginID("");
				controller.setCurrentLoginPW("");
				
				Button btnLogin = (Button)mainParent.lookup("#btnLogin");
				btnLogin.setText("로그인");
				
				commonServ.showMsg("알림", "로그아웃 되었습니다.");
				
			}
			
		} catch (Exception e) {System.out.println(e);}
		
	}



	@Override
	public void saveLoginID(Parent loginParent) {
		// TODO Auto-generated method stub
		try {
			
			RadioButton radioSaveID = (RadioButton)loginParent.lookup("#radioSaveID");
			
			if (radioSaveID.isSelected() == true) {
				bSaveID = true;
			} else {
				bSaveID = false;
			}
			
		} catch (Exception e) {System.out.println(e);}
	}
	

	
}
