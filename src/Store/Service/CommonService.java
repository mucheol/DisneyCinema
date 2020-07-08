package Store.Service;

import javafx.event.ActionEvent;

public interface CommonService {
	public void WindowClose(ActionEvent event);
	public void ErrorMsg(String title, String headerStr, String contentTxt);
    public void ErrorMsg(String headerStr, String contentTxt);
    public void ErrorMsg(String contentTxt) ;

}
