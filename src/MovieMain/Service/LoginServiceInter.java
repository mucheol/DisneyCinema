package MovieMain.Service;

import javafx.scene.Parent;
import javafx.scene.control.Button;

public interface LoginServiceInter {

	public void loginProc(Parent loginParent, Parent mainParent);
	public void loginOpen(Parent mainParent);
	public void saveLoginID(Parent loginParent);
	
}
