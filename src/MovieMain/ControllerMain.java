package MovieMain;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import MovieMain.Service.CommonService;
import MovieMain.Service.CommonServiceInter;
import MovieMain.Service.LoginService;
import MovieMain.Service.LoginServiceInter;
import MovieMain.Service.MainService;
import MovieMain.Service.MainServiceInter;
import MovieMain.Service.MemberService;
import MovieMain.Service.MemberServiceInter;
import Store.Service.MenuService;
import Store.Service.MenuServiceImpl;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class ControllerMain implements Initializable {

	MainServiceInter mainServ;
	LoginServiceInter loginServ; 
	MemberServiceInter memberServ;
	CommonServiceInter commonServ;
	MenuService menuServ;
	
	Parent mainParent;
	Parent loginParent;
	Parent memberParent;
	
	static String currentLoginID = "";		// 현재 로그인중인 아이디
	static String currentLoginPW = "";		// 현재 로그인중인 패스워드
	
	static Parent staticMainParent;
	static Parent staticRsParent;
	
	
	public void setStaticRsParent(Parent staticRsParent) {
		this.staticRsParent = staticRsParent;
	}
	
	public Parent getStaticRsParent() {
		return this.staticRsParent;
	}
	
	public void setStaticMainParent(Parent staticMainParent) {
		this.staticMainParent = staticMainParent;
	}
	
	public Parent getStaticMainParent() {
		return this.staticMainParent;
	}
	
	public void setCurrentLoginID(String strID) {
		this.currentLoginID = strID;
	}
	
	public void setCurrentLoginPW(String strPW) {
		this.currentLoginPW = strPW;
	}
	
	public String getCurrentLoginID() {
		return this.currentLoginID;
	}
	
	public String getCurrentLoginPW() {
		return this.currentLoginPW;
	}
	
	public void setMainForm(Parent mainParent) {
		
		this.mainParent = mainParent;
		
	}
	
	public void setLoginForm(Parent loginParent) {
		
		this.loginParent = loginParent;
		
	}
	
	
	public void setMemberForm(Parent memberParent) {
		
		this.memberParent = memberParent;
		
	}
	
			
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		mainServ = new MainService();
		loginServ = new LoginService();
		memberServ = new MemberService();
		commonServ = new CommonService();
		menuServ = new MenuServiceImpl();
		
	}
	
	public void cancelAction(ActionEvent event) {
		
		commonServ.cancelAction(event);
		
	}
	
	
	public void loginProcAction() {
		
		loginServ.loginProc(this.loginParent, this.mainParent);
		
	}
	
	public void loginOpenAction() {
		
		loginServ.loginOpen(this.mainParent);
		
	}
	
	
	public void memberOpenAction() {
		
		memberServ.memberOpen();
		
	}
	
	
	public void memberAction() {

		memberServ.memberProc(this.memberParent);
		
	}
	
	public void chkHobbyAction() {
		
		memberServ.chkHobbyAction(this.memberParent);
		
	}
	
	public void chkHobbyEtcAction() {
		
		memberServ.chkHobbyEtcAction(this.memberParent);
		
	}
	
	
	public void overlapAction() {
		
		memberServ.overlapID(this.memberParent);
		
	}
	
	
	public void reserveMovieAction(ActionEvent event) {
		
		memberServ.reserveMovieAction(event);
		
	}
	
	
	public void saveIDAction() {
		
		loginServ.saveLoginID(loginParent);
		
	}
	
	
	public void myReserveAction() {
		
		memberServ.myReserveAction();
		
	}
	
	
	public void movieDetailAction(ActionEvent event) {
		
		mainServ.movieDetail(event, mainParent);
		
	}
	
	
	public void cancelReserveAction() {
		
		mainServ.cancelReserve(getCurrentLoginID(), getStaticRsParent());
		
	}
	
	
	public void cancelMenuAction() {
		
		mainServ.cancelMenu(getCurrentLoginID(), getStaticRsParent());
		
	}
	
	
	public void openStoreAction() {
		if (getCurrentLoginID() == "") {
			commonServ.showMsg("알림", "로그인을 하세요.");
		} else {
			menuServ.openMenu();
		}
		
		
	}
	
	
}
