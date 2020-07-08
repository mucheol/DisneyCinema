package MovieMain.Service;

import javafx.event.ActionEvent;
import javafx.scene.Parent;

public interface MainServiceInter {

	public void movieDetail(ActionEvent event, Parent parentMain);
	public void cancelReserve(String getID, Parent getRsParent);
	public void cancelMenu(String getID, Parent getRsParent);
	
}
