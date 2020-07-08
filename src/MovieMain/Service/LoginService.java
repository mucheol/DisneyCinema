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
			
			if (txtID.getText().equals("")) {		// equals() �ȿ� null �� ������ �۵�����.
				commonServ.showMsg("�˸�", "���̵� �Է��ϼ���.");
			} else if (txtPW.getText().equals("")) {
				commonServ.showMsg("�˸�", "��й�ȣ�� �Է��ϼ���.");
			} else {
				System.out.println("------ �α��� �õ� ------");
				System.out.println("���̵� : " + txtID.getText() + "\n��й�ȣ : " + txtPW.getText());
				
				DatabaseServiceInter dbServ = new DatabaseService();
				ArrayList<String> arrID = new ArrayList<String>();
				
				dbServ.open();
				arrID = dbServ.SelectIDAll();
				
				if (arrID.size() == 0) {
					commonServ.showMsg("�˸�", "ȸ���� �������� �ʽ��ϴ�.");
				}
				
				
				boolean bFlag = false;
				for (int i=0; i<arrID.size(); i++) {
					if (arrID.get(i).equals(txtID.getText())) {
						
						if (dbServ.SelectPW(txtID.getText()).equals(txtPW.getText())) {
							
							System.out.println("�α��� ����(DB)");
							bFlag = true;
							
							ControllerMain controller = new ControllerMain();
							controller.setCurrentLoginID(txtID.getText());
							controller.setCurrentLoginPW(txtPW.getText());
							
							Stage stage = (Stage)loginParent.getScene().getWindow();
							stage.close();
							
							Button btnLogin = (Button)mainParent.lookup("#btnLogin");
							btnLogin.setText(controller.getCurrentLoginID() + " �� \n�α��� ���Դϴ�.");
							
							break;
							
						} else {
							commonServ.showMsg("�˸�", "��й�ȣ�� Ʋ�Ƚ��ϴ�.");
							txtPW.clear();
							txtPW.requestFocus();
							bFlag = true;
							break;
						}
						
					} 
				}
				
				if (bFlag == false) {
					commonServ.showMsg("�˸�", "�������� �ʴ� ���̵��Դϴ�.");
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
				
				controller = loader.getController();		// ��� ���� ������ loginProc(Parent loginParent)�� �Ű������� �Ѱ��ֱ� ����
				controller.setLoginForm(parent);
				controller.setMainForm(mainParent);
				
				stage.setScene(scene);
				stage.show();
				
				System.out.println("�α��� â ����");
			
			} else {
				
				
				controller.setCurrentLoginID("");
				controller.setCurrentLoginPW("");
				
				Button btnLogin = (Button)mainParent.lookup("#btnLogin");
				btnLogin.setText("�α���");
				
				commonServ.showMsg("�˸�", "�α׾ƿ� �Ǿ����ϴ�.");
				
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
