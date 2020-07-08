package Store.Service;

import java.io.IOException;
import java.util.ArrayList;

import Store.Controller;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MenuServiceImpl implements MenuService {
	private Parent parent;

	private ArrayList<HBox> arr;
	private ArrayList arr2 = new ArrayList();
	public ListView list;

	private int cnt;
	private Button delBtn;
	private Label lbl11;
	private Label lbl22;
	private Label lbl1;
	private Label lbl2;
	private int Amount;
	private String Str_Amount;

	// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
//	private DatabaseService dbsrv = new DatabaseServiceImpl();

	public void DbLoginProc(Parent root) {
//		dbsrv.open();
	}

	
	public void openMenu() {
		
		try {
			
			Stage seatForm = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../Menu.fxml"));
			Parent parent = null;
			
			try {
				parent = loader.load();
			} catch(IOException e) {
				e.printStackTrace();
			}
			
			Controller ctrler = loader.getController();
			ctrler.setMenuForm(parent);
			
			MenuService ms = new MenuServiceImpl();
			ms.AddComboBox(parent);
			
			seatForm.setScene(new Scene(parent));
			seatForm.show();
			
		} catch (Exception e) {System.out.println(e);}
		
	}
	
	
	public ArrayList<HBox> PickMenu(Parent MenuForm) { // 메뉴 선택시
		CommonService commonSrv = new CommonServiceImpl();
		arr = new ArrayList<HBox>();
		list = (ListView) MenuForm.lookup("#lstV");
		cnt = 0;
		boolean checkBtn = true;

		RadioButton BtnBest1 = (RadioButton) MenuForm.lookup("#BB1");
		RadioButton BtnBest2 = (RadioButton) MenuForm.lookup("#BB2");
		RadioButton BtnBest3 = (RadioButton) MenuForm.lookup("#BB3");
		RadioButton BtnBest4 = (RadioButton) MenuForm.lookup("#BB4");
		RadioButton BtnPackage1 = (RadioButton) MenuForm.lookup("#BP1");
		RadioButton BtnPackage2 = (RadioButton) MenuForm.lookup("#BP2");
		RadioButton BtnPackage3 = (RadioButton) MenuForm.lookup("#BP3");
		RadioButton BtnPackage4 = (RadioButton) MenuForm.lookup("#BP4");
		RadioButton BtnSolo1 = (RadioButton) MenuForm.lookup("#BS1");
		RadioButton BtnSolo2 = (RadioButton) MenuForm.lookup("#BS2");
		RadioButton BtnSolo3 = (RadioButton) MenuForm.lookup("#BS3");
		RadioButton BtnSolo4 = (RadioButton) MenuForm.lookup("#BS4");
		ComboBox<String> cC1 = (ComboBox<String>) MenuForm.lookup("#cmbChoice1");
		ComboBox<String> cC2 = (ComboBox<String>) MenuForm.lookup("#cmbChoice2");
		ComboBox<String> cC3 = (ComboBox<String>) MenuForm.lookup("#cmbChoice3");
		ComboBox<String> cC4 = (ComboBox<String>) MenuForm.lookup("#cmbChoice4");

		if (BtnBest1.isSelected()) {
			arr.add(cnt, Cart1(MenuForm, "#BT1", "#Bprice1"));
			arr2.add(cnt, saveInfo1(MenuForm, "#BT1", "#Bprice1"));
			cnt++;
		}
		if (BtnBest2.isSelected()) {
			arr.add(cnt, Cart1(MenuForm, "#BT2", "#Bprice2"));
			arr2.add(cnt, saveInfo1(MenuForm, "#BT2", "#Bprice2"));
			cnt++;
		}
		if (BtnBest3.isSelected()) {
			arr.add(cnt, Cart1(MenuForm, "#BT3", "#Bprice3"));
			arr2.add(cnt, saveInfo1(MenuForm, "#BT3", "#Bprice3"));
			cnt++;
		}
		if (BtnBest4.isSelected()) {
			arr.add(cnt, Cart1(MenuForm, "#BT4", "#Bprice4"));
			arr2.add(cnt, saveInfo1(MenuForm, "#BT4", "#Bprice4"));
			cnt++;
		}

		if (BtnPackage1.isSelected()) {
			arr.add(cnt, Cart1(MenuForm, "#PT1", "#Pprice1"));
			arr2.add(cnt, saveInfo1(MenuForm, "#PT1", "#Pprice1"));
			cnt++;
		}
		if (BtnPackage2.isSelected()) {
			arr.add(cnt, Cart1(MenuForm, "#PT2", "#Pprice2"));
			arr2.add(cnt, saveInfo1(MenuForm, "#PT2", "#Pprice2"));
			cnt++;
		}
		if (BtnPackage3.isSelected()) {
			arr.add(cnt, Cart1(MenuForm, "#PT3", "#Pprice3"));
			arr2.add(cnt, saveInfo1(MenuForm, "#PT3", "#Pprice3"));
			cnt++;
		}
		if (BtnPackage4.isSelected()) {
			arr.add(cnt, Cart1(MenuForm, "#PT4", "#Pprice4"));
			arr2.add(cnt, saveInfo1(MenuForm, "#PT4", "#Pprice4"));
			cnt++;
		}

		if (BtnSolo1.isSelected()) {
			if (cC1.getValue() == null) {
				commonSrv.ErrorMsg("팝콘맛을 선택해주세요");
				cnt++;
			} else if (!cC1.getValue().toString().equals("상품선택")) {
				arr.add(cnt, Cart2(MenuForm, "#cmbChoice1", "#Sprice1"));
				arr2.add(cnt, saveInfo1_1(MenuForm, "#cmbChoice1", "#Sprice1"));
				cnt++;
			}
		}

		if (BtnSolo2.isSelected()) {
			if (cC2.getValue() == null) {
				commonSrv.ErrorMsg("탄산음료를 선택해주세요");
				cnt++;
			} else if (!cC2.getValue().toString().equals("상품선택")) {
				arr.add(cnt, Cart2(MenuForm, "#cmbChoice2", "#Sprice2"));
				arr2.add(cnt, saveInfo1_1(MenuForm, "#cmbChoice2", "#Sprice2"));
				cnt++;
			}

		}
		if (BtnSolo3.isSelected()) {
			if (cC3.getValue() == null) {
				commonSrv.ErrorMsg("에이드를 선택해주세요");
				cnt++;
			} else if (!cC3.getValue().toString().equals("상품선택")) {
				arr.add(cnt, Cart2(MenuForm, "#cmbChoice3", "#Sprice3"));
				arr2.add(cnt, saveInfo1_1(MenuForm, "#cmbChoice3", "#Sprice3"));
				cnt++;
			}
		}
		if (BtnSolo4.isSelected()) {
			if (cC4.getValue() == null) {
				commonSrv.ErrorMsg("기타상품을 선택해주세요");
				cnt++;
			} else if (!cC4.getValue().toString().equals("상품선택")) {
				arr.add(cnt, Cart2(MenuForm, "#cmbChoice4", "#Sprice4"));
				arr2.add(cnt, saveInfo1_1(MenuForm, "#cmbChoice4", "#Sprice4"));
				cnt++;
			}
		}
		Chogihwa(MenuForm);
		if (cnt == 0) {
			commonSrv.ErrorMsg("상품을 선택해주세요");
		}
		return arr;
	}

	@Override
	public void AddComboBox(Parent MenuForm) { // 콤보박스 추가
		// TODO Auto-generated method stub

		parent = MenuForm;
		ComboBox<String> cC1 = (ComboBox<String>) MenuForm.lookup("#cmbChoice1");
		ComboBox<String> cC2 = (ComboBox<String>) MenuForm.lookup("#cmbChoice2");
		ComboBox<String> cC3 = (ComboBox<String>) MenuForm.lookup("#cmbChoice3");
		ComboBox<String> cC4 = (ComboBox<String>) MenuForm.lookup("#cmbChoice4");

		cC1.getItems().addAll("상품선택", "기본맛 팝콘", "캬라멜맛 팝콘", "갈릭맛 팝콘", "어니언맛 팝콘");
		cC2.getItems().addAll("상품선택", "콜라", "사이다", "오렌지맛", "파인애플맛");
		cC3.getItems().addAll("상품선택", "레몬에이드", "자몽에이드", "사과에이드", "딸기에이드");
		cC4.getItems().addAll("상품선택", "나초", "구운오징어", "핫도그", "감자튀김");

	}

	public String saveInfo1(Parent MenuForm, String s1, String s2) {
		lbl11 = (Label) MenuForm.lookup(s1);
		lbl22 = (Label) MenuForm.lookup(s2);
		String a = lbl11.getText();
		String b = lbl22.getText();
		String c = a + " " + b;
		return c;
	}

	public String saveInfo1_1(Parent MenuForm, String s1, String s2) { // 콤보박스꺼
		ComboBox cb = new ComboBox();
		cb = (ComboBox) MenuForm.lookup(s1);
		lbl22 = (Label) MenuForm.lookup(s2);
		String a = cb.getValue().toString();
		String b = lbl22.getText();
		String c = a + " " + b;
		return c;
	}

	public HBox Cart1(Parent MenuForm, String s1, String s2) { // 장바구니 버튼
		lbl11 = (Label) MenuForm.lookup(s1);
		lbl22 = (Label) MenuForm.lookup(s2);
		Button delBtn = new Button("-");
		lbl1 = new Label();
		lbl2 = new Label();
		lbl1.setText(lbl11.getText());
		lbl2.setText(lbl22.getText());

		HBox hbox = new HBox();
		hbox.setSpacing(40);
		lbl2.setPrefWidth(100);
		lbl2.setAlignment(Pos.CENTER_RIGHT);
		hbox.getChildren().addAll(lbl1, lbl2, delBtn);
		hbox.setAlignment(Pos.CENTER_RIGHT);

		// 총합계 추가
		String lbl2_txt = lbl2.getText();
		String lbl2_int = new String();
		for (int i = 0; i < lbl2_txt.length(); i++) {
			if (48 <= lbl2_txt.charAt(i) && lbl2_txt.charAt(i) <= 57)
				lbl2_int += lbl2_txt.charAt(i);
		}
		Amount += Integer.parseInt(lbl2_int);
		int aa = Integer.parseInt(lbl2_int);
		Str_Amount = Integer.toString(Amount);
		Label ll = (Label) MenuForm.lookup("#Price");
		ll.setText(Str_Amount);

		// 삭제버튼 누를 시 저장되는 텍스트 삭제
		delBtn.setOnAction((ActionEvent) -> {
			arr2.remove(saveInfo1(MenuForm, s1, s2));
			list.getItems().remove(hbox);
			Amount -= aa;
			Str_Amount = Integer.toString(Amount);
			Label ll2 = (Label) MenuForm.lookup("#Price");
			ll2.setText(Str_Amount);
		});
		return hbox;
	}

	private HBox Cart2(Parent MenuForm, String s1, String s2) {
		// TODO Auto-generated method stub
		ComboBox cb = new ComboBox();
		cb = (ComboBox) MenuForm.lookup(s1);
		String s = cb.getValue().toString();
		lbl22 = (Label) MenuForm.lookup(s2);
		Button delBtn = new Button("-");
		lbl1 = new Label();
		lbl2 = new Label();
		lbl1.setText(s);
		lbl2.setText(lbl22.getText());

		HBox hbox = new HBox();
		hbox.setSpacing(40);
		hbox.setSpacing(40);
		lbl2.setPrefWidth(100);
		lbl2.setAlignment(Pos.CENTER_RIGHT);
		hbox.getChildren().addAll(lbl1, lbl2, delBtn);
		hbox.setAlignment(Pos.CENTER_RIGHT);

		// 총합계 추가
		String lbl2_txt = lbl2.getText();
		String lbl2_int = new String();
		for (int i = 0; i < lbl2_txt.length(); i++) {
			if (48 <= lbl2_txt.charAt(i) && lbl2_txt.charAt(i) <= 57)
				lbl2_int += lbl2_txt.charAt(i);
		}
		Amount += Integer.parseInt(lbl2_int);
		int aa = Integer.parseInt(lbl2_int);
		Str_Amount = Integer.toString(Amount);
		Label ll = (Label) MenuForm.lookup("#Price");
		ll.setText(Str_Amount);

		// 삭제버튼 누를 시 1.HBox 삭제 2.총금액에 (-)처리
		delBtn.setOnAction((ActionEvent) -> {
			arr2.remove(saveInfo1_1(MenuForm, s1, s2));
			list.getItems().remove(hbox);
			Amount -= aa;
			Str_Amount = Integer.toString(Amount);
			Label ll2 = (Label) MenuForm.lookup("#Price");
			ll2.setText(Str_Amount);
		});
		return hbox;
	}

	public void Chogihwa(Parent MenuForm) {
		RadioButton BtnBest1 = (RadioButton) MenuForm.lookup("#BB1");
		BtnBest1.setSelected(false);
		RadioButton BtnBest2 = (RadioButton) MenuForm.lookup("#BB2");
		BtnBest2.setSelected(false);
		RadioButton BtnBest3 = (RadioButton) MenuForm.lookup("#BB3");
		BtnBest3.setSelected(false);
		RadioButton BtnBest4 = (RadioButton) MenuForm.lookup("#BB4");
		BtnBest4.setSelected(false);
		RadioButton BtnPackage1 = (RadioButton) MenuForm.lookup("#BP1");
		BtnPackage1.setSelected(false);
		RadioButton BtnPackage2 = (RadioButton) MenuForm.lookup("#BP2");
		BtnPackage2.setSelected(false);
		RadioButton BtnPackage3 = (RadioButton) MenuForm.lookup("#BP3");
		BtnPackage3.setSelected(false);
		RadioButton BtnPackage4 = (RadioButton) MenuForm.lookup("#BP4");
		BtnPackage4.setSelected(false);
		RadioButton BtnSolo1 = (RadioButton) MenuForm.lookup("#BS1");
		BtnSolo1.setSelected(false);
		RadioButton BtnSolo2 = (RadioButton) MenuForm.lookup("#BS2");
		BtnSolo2.setSelected(false);
		RadioButton BtnSolo3 = (RadioButton) MenuForm.lookup("#BS3");
		BtnSolo3.setSelected(false);
		RadioButton BtnSolo4 = (RadioButton) MenuForm.lookup("#BS4");
		BtnSolo4.setSelected(false);
		ComboBox<String> cC1 = (ComboBox<String>) MenuForm.lookup("#cmbChoice1");
		cC1.setValue("상품선택");
		ComboBox<String> cC2 = (ComboBox<String>) MenuForm.lookup("#cmbChoice2");
		cC2.setValue("상품선택");
		ComboBox<String> cC3 = (ComboBox<String>) MenuForm.lookup("#cmbChoice3");
		cC3.setValue("상품선택");
		ComboBox<String> cC4 = (ComboBox<String>) MenuForm.lookup("#cmbChoice4");
		cC4.setValue("상품선택");
	}

}
