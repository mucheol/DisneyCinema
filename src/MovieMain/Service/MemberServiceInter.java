package MovieMain.Service;

import javafx.event.ActionEvent;
import javafx.scene.Parent;

public interface MemberServiceInter {

	public void memberOpen();
	public void memberProc(Parent memberParent);
	public void chkHobbyAction(Parent memberParent);
	public void chkHobbyEtcAction(Parent memberParent);
	public void overlapID(Parent memberParent);
	public void reserveMovieAction(ActionEvent event);
	public void myReserveAction();
	
}
