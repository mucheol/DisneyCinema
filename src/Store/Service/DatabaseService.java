package Store.Service;

import Store.Info;

public interface DatabaseService {
	public void Insert(Info info, String getID);
	public boolean open();
	public String SelectShoppingList(String getID);
	public String SelectAmount(String getID);
	public boolean DeleteID(String getID);
}
