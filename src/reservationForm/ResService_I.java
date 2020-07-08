package reservationForm;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.ListView;

public interface ResService_I {
	public void WindowClose(ActionEvent event);
	public void AddComboBox(Parent parent);
	public void openReservation(String movie);
	public void selectBtn(ActionEvent event);
	public void dateBeforeBtn(Parent parent);
	public void dateNextBtn(Parent parent);
	public void comboBox(ActionEvent event);
	public void resSetting(Parent parent);
	public void isSelected(Parent parent);
	
}
