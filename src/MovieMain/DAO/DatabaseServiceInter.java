package MovieMain.DAO;

import java.util.ArrayList;

import MovieMain.Member;

public interface DatabaseServiceInter {

	public boolean open();
	public void Insert(Member member);
	public ArrayList<String> SelectIDAll();
	public String SelectPW(String id);
	public String SelectName(String id);
	
}
