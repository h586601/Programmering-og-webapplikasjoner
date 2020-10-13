package oppg1;

import java.util.ArrayList;
import java.util.List;

public class Liste {
    
    private List<String> items = new ArrayList<>();
    
    public void addItem(String item) {
        items.add(item);
    }
    
    public synchronized void deleteItem(String item) {
    	items.remove(item);
    }
    
    public List<String> getItems() {
        return items;
    }  
    
    public synchronized boolean finnes(String varenavn) {
    	return items.stream().anyMatch(x -> x.equalsIgnoreCase(varenavn));
    }
   
}
